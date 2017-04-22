package com.github.bkhezry.extramaputils.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.webkit.URLUtil;

import com.github.bkhezry.extramaputils.R;
import com.github.bkhezry.extramaputils.listener.onGeoJsonEventListener;
import com.github.bkhezry.extramaputils.listener.onKMLEventListener;
import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.github.bkhezry.extramaputils.model.UiOptions;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.github.bkhezry.extramaputils.model.ViewOption.StyleDef;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPolygon;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapUtils {
    private static final int PATTERN_DASH_LENGTH_PX = 50;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final Dot DOT = new Dot();
    private static final Dash DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final Gap GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_DOTTED = Arrays.asList(DOT, GAP);
    private static final List<PatternItem> PATTERN_DASHED = Arrays.asList(DASH, GAP);
    private static final List<PatternItem> PATTERN_MIXED = Arrays.asList(DOT, GAP, DOT, DASH, GAP);

    public static void showElements(final ViewOption viewOption, final GoogleMap googleMap, final Context context) {

        setSelectedStyle(viewOption.getStyleName(), googleMap, context);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                int count = 0;
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (ExtraMarker extraMarker : viewOption.getMarkers()) {
                    count++;
                    builder.include(extraMarker.getCenter());
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(getBitmapFromDrawable(context, extraMarker.getIcon()));
                    googleMap.addMarker(
                            new MarkerOptions()
                                    .icon(icon)
                                    .position(extraMarker.getCenter())
                                    .title(extraMarker.getName())
                    );
                }
                for (ExtraPolygon polygon : viewOption.getPolygons()) {
                    count++;
                    googleMap.addPolygon(
                            new PolygonOptions()
                                    .fillColor(polygon.getFillColor())
                                    .strokeColor(polygon.getUiOptions().getColor())
                                    .strokeWidth(polygon.getUiOptions().getWidth())
                                    .strokePattern(getStrokePattern(polygon.getUiOptions().getStrokePattern()))
                                    .zIndex(polygon.getUiOptions().getzIndex())
                                    .add(polygon.getPoints())
                    );
                    for (LatLng latLng : polygon.getPoints()) {
                        builder.include(latLng);
                    }
                }
                for (ExtraPolyline polyline : viewOption.getPolylines()) {
                    count++;
                    googleMap.addPolyline(
                            new PolylineOptions()
                                    .color(polyline.getUiOptions().getColor())
                                    .width(polyline.getUiOptions().getWidth())
                                    .pattern(getStrokePattern(polyline.getUiOptions().getStrokePattern()))
                                    .zIndex(polyline.getUiOptions().getzIndex())
                                    .add(polyline.getPoints())
                    );
                    for (LatLng latLng : polyline.getPoints()) {
                        builder.include(latLng);
                    }
                }

                if (viewOption.isForceCenterMap()) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(viewOption.getCenterLatLng(), viewOption.getMapsZoom()));
                } else if (count != 0) {
                    boundMap(viewOption.isListView(), builder, googleMap);
                }
                if (!viewOption.getGeoJsonUrl().equals("") && URLUtil.isValidUrl(viewOption.getGeoJsonUrl())) {
                    loadGeoJson(viewOption.getGeoJsonUrl(), googleMap, context, viewOption.getGeoJsonEventListener());
                }
                if (viewOption.getGeoJsonRes() != Integer.MAX_VALUE) {
                    loadGeoJsonRes(viewOption.getGeoJsonRes(), googleMap, context, viewOption.getGeoJsonEventListener());
                }
                if (!viewOption.getKmlUrl().equals("") && URLUtil.isValidUrl(viewOption.getKmlUrl())) {
                    loadKML(viewOption.getKmlUrl(), googleMap, context, viewOption.getKmlEventListener());
                }
                if (viewOption.getKmlRes() != Integer.MAX_VALUE) {
                    loadKMLRes(viewOption.getKmlRes(), googleMap, context, viewOption.getKmlEventListener());
                }
            }
        });

    }

    private static void loadKMLRes(int kmlRes, GoogleMap googleMap, Context context, onKMLEventListener kmlEventListener) {
        try {
            KmlLayer kmlLayer = new KmlLayer(googleMap, kmlRes, context);
            if (kmlEventListener != null)
                kmlEventListener.onKMLLoaded(kmlLayer);
            addKMLLayerToMap(kmlLayer, googleMap, context, kmlEventListener);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private static void loadKML(String kmlUrl, final GoogleMap googleMap, final Context context, final onKMLEventListener kmlEventListener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(kmlUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("LoadKMLException:", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String kml = response.body().string();
                if (!kml.equals("")) {
                    handleKMLString(kml, googleMap, context, kmlEventListener);
                }
            }
        });
    }

    private static void handleKMLString(String kml, GoogleMap googleMap, Context context, onKMLEventListener kmlEventListener) {
        InputStream stream = new ByteArrayInputStream(kml.getBytes());
        KmlLayer kmlLayer = null;
        try {
            kmlLayer = new KmlLayer(googleMap, stream, context);
            if (kmlEventListener != null)
                kmlEventListener.onKMLLoaded(kmlLayer);
            addKMLLayerToMap(kmlLayer, googleMap, context, kmlEventListener);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addKMLLayerToMap(final KmlLayer kmlLayer, final GoogleMap googleMap, final Context context, final onKMLEventListener kmlEventListener) {
        Handler mainHandler = new Handler(context.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    kmlLayer.addLayerToMap();
                    kmlLayer.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                        @Override
                        public void onFeatureClick(Feature feature) {
                            if (kmlEventListener != null)
                                kmlEventListener.onFeatureClick(feature);
                        }
                    });
                    moveCameraToKml(kmlLayer, googleMap);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private static void loadGeoJsonRes(int geoJsonRes, GoogleMap googleMap, Context context
            , onGeoJsonEventListener eventListener) {
        try {
            GeoJsonLayer geoJsonLayer = new GeoJsonLayer(googleMap, geoJsonRes, context);
            addGeoJsonLayerToMap(geoJsonLayer, googleMap, context, eventListener);
            if (eventListener != null)
                eventListener.onGeoJsonLoaded(geoJsonLayer);
        } catch (IOException e) {
            Log.e("GeoJsonResException:", "GeoJSON file could not be read");
        } catch (JSONException e) {
            Log.e("GeoJsonResException:", "GeoJSON file could not be converted to a JSONObject");
        }
    }

    private static void loadGeoJson(String geoJsonUrl, final GoogleMap googleMap, final Context context, final onGeoJsonEventListener eventListener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(geoJsonUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("LoadGeoJsonException:", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String geoJson = response.body().string();
                if (!geoJson.equals("")) {
                    handleGeoJsonString(geoJson, googleMap, context, eventListener);
                }
            }
        });
    }

    private static void handleGeoJsonString(String geoJson, GoogleMap googleMap, final Context context, onGeoJsonEventListener eventListener) {
        try {
            GeoJsonLayer geoJsonLayer = new GeoJsonLayer(googleMap, new JSONObject(geoJson));
            if (eventListener != null)
                eventListener.onGeoJsonLoaded(geoJsonLayer);
            addGeoJsonLayerToMap(geoJsonLayer, googleMap, context, eventListener);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static void addGeoJsonLayerToMap(final GeoJsonLayer layer, final GoogleMap googleMap, final Context context, final onGeoJsonEventListener eventListener) {
        Handler mainHandler = new Handler(context.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                layer.addLayerToMap();
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(layer.getBoundingBox(), 50));
                layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
                    @Override
                    public void onFeatureClick(Feature feature) {
                        if (eventListener != null)
                            eventListener.onFeatureClick(feature);
                    }

                });
            }
        });


    }


    private static void boundMap(final boolean isListView, final LatLngBounds.Builder builder, final GoogleMap googleMap) {
        LatLngBounds bounds = builder.build();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        float zoom = googleMap.getCameraPosition().zoom;
        if (isListView)
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoom - 1f));
    }

    private static void setSelectedStyle(StyleDef styleName, GoogleMap googleMap, Context context) {
        MapStyleOptions style;
        switch (styleName) {
            case RETRO:
                style = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle_retro);
                break;
            case NIGHT:
                style = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle_night);
                break;
            case GRAY_SCALE:
                style = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle_grayscale);
                break;
            case DEFAULT:
                style = null;
                break;
            default:
                return;
        }
        googleMap.setMapStyle(style);
    }

    private static Bitmap getBitmapFromDrawable(Context context, int icon) {
        Drawable drawable = ContextCompat.getDrawable(context, icon);
        Bitmap obm = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(obm);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return obm;
    }

    private static List<PatternItem> getStrokePattern(UiOptions.StrokePatternDef strokePatternDef) {
        List<PatternItem> patternItems = new ArrayList<>();
        switch (strokePatternDef) {
            case DEFAULT:
                patternItems = null;
                break;
            case DASHED:
                patternItems = PATTERN_DASHED;
                break;
            case DOTTED:
                patternItems = PATTERN_DOTTED;
                break;
            case MIXED:
                patternItems = PATTERN_MIXED;
                break;
        }
        return patternItems;
    }

    public static void moveCameraToKml(KmlLayer kmlLayer, GoogleMap googleMap) {
        //TODO fixed error with some kml file
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        KmlContainer container = kmlLayer.getContainers().iterator().next();
        container = container.getContainers().iterator().next();
        KmlPlacemark placemark = container.getPlacemarks().iterator().next();

        KmlPolygon polygon = (KmlPolygon) placemark.getGeometry();
        for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
            builder.include(latLng);
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 50));
    }
}
