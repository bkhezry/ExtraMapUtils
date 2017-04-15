package com.github.bkhezry.extramapview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.github.bkhezry.extramapview.model.ExtraMarker;
import com.github.bkhezry.extramapview.model.OptionView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
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
            googleMap.addMarker(new MarkerOptions().position(extraMarker.getCenter()).title(extraMarker.getName()));
        }
        for (LatLng[] latLngs : optionView.getPolygons()) {
            googleMap.addPolygon(new PolygonOptions().clickable(true).add(latLngs));
            for (LatLng latLng : latLngs) {
                builder.include(latLng);
            }
        }
        for (LatLng[] latLngs : optionView.getPolylines()) {
            googleMap.addPolyline(new PolylineOptions().clickable(true).add(latLngs));
            for (LatLng latLng : latLngs) {
                builder.include(latLng);
            }
        }
        boundMap();

    }
}
