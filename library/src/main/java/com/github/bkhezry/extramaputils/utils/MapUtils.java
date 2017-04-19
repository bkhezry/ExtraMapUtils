package com.github.bkhezry.extramaputils.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.github.bkhezry.extramaputils.R;
import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.github.bkhezry.extramaputils.model.ViewOption.StyleDef;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapUtils {
    public static void showElements(final ViewOption viewOption, final GoogleMap googleMap, final Context context) {
        setSelectedStyle(viewOption.getStyleName(), googleMap, context);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (ExtraMarker extraMarker : viewOption.getMarkers()) {
                    builder.include(extraMarker.getCenter());
                    BitmapDescriptor icon;
                    if (extraMarker.getIconColor() == Integer.MAX_VALUE) {
                        icon = BitmapDescriptorFactory.fromResource(extraMarker.getIcon());
                    } else {
                        icon = BitmapDescriptorFactory.fromBitmap(changeBitmapColor(context, extraMarker.getIconColor(), extraMarker.getIcon()));
                    }
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

    private static Bitmap changeBitmapColor(Context context, int color, int icon) {
        Bitmap ob = BitmapFactory.decodeResource(context.getResources(), icon);
        Bitmap obm = Bitmap.createBitmap(ob.getWidth(), ob.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(obm);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(ob, 0f, 0f, paint);
        return obm;
    }
}
