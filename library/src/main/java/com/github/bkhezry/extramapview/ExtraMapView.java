package com.github.bkhezry.extramapview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.github.bkhezry.extramapview.model.ExtraMarker;
import com.github.bkhezry.extramapview.model.ExtraPolygon;
import com.github.bkhezry.extramapview.model.ExtraPolyline;
import com.github.bkhezry.extramapview.model.OptionView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class ExtraMapView extends MapView {
    private MapView mapView;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();
    private GoogleMap googleMap;

    public ExtraMapView(Context context) {
        super(context);
        this.mapView = this;
    }

    public ExtraMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mapView = this;
    }

    public ExtraMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mapView = this;
    }

    public ExtraMapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);
        this.mapView = this;
    }

    private void boundMap() {
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LatLngBounds bounds = builder.build();
                    mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
                }
            });
        }

    }

    public void showExtraMap(final OptionView optionView, final GoogleMap googleMap) {
        this.googleMap = googleMap;
        for (ExtraMarker extraMarker : optionView.getMarkers()) {
            builder.include(extraMarker.getCenter());
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(extraMarker.getIcon());
            googleMap.addMarker(
                    new MarkerOptions()
                            .icon(icon)
                            .position(extraMarker.getCenter())
                            .title(extraMarker.getName())
            );
        }
        for (ExtraPolygon polygon : optionView.getPolygons()) {
            googleMap.addPolygon(
                    new PolygonOptions()
                            .fillColor(polygon.getFillColor())
                            .strokeColor(polygon.getColor())
                            .strokeWidth(polygon.getWidth())
                            .zIndex(polygon.getzIndex())
                            .add(polygon.getPoints())
            );
            for (LatLng latLng : polygon.getPoints()) {
                builder.include(latLng);
            }
        }
        for (ExtraPolyline polyline : optionView.getPolylines()) {
            googleMap.addPolyline(
                    new PolylineOptions()
                            .color(polyline.getColor())
                            .width(polyline.getWidth())
                            .zIndex(polyline.getzIndex())
                            .add(polyline.getPoints())
            );
            for (LatLng latLng : polyline.getPoints()) {
                builder.include(latLng);
            }
        }
        if (optionView.isForceCenterMap()) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(optionView.getCenterLatLng(), optionView.getMapsZoom()));
        } else {
            boundMap();
        }
    }
}
