package com.github.bkhezry.extramaputils.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.bkhezry.extramaputils.R;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (ExtraMarker extraMarker : viewOption.getMarkers()) {
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
                } else {
                    boundMap(viewOption.isListView(), builder, googleMap);
                }
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
}
