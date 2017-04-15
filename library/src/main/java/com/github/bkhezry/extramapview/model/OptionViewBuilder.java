package com.github.bkhezry.extramapview.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionViewBuilder {
    private LatLng centerLatLng;
    private boolean isMultipleMarker = false;
    private boolean isSingleMarker = false;
    private float mapsZoom = 14;
    private List<ExtraMarker> markers;
    private ExtraMarker marker;
    private LatLng[] polygon;
    private boolean isSinglePolygon = false;
    private LatLng[] polyline;
    private boolean isSinglePolyline = false;
    private List<LatLng[]> polygons = new ArrayList<>();
    private boolean isMultiplePolygon = false;
    private List<LatLng[]> polylines = new ArrayList<>();
    private boolean isMultiplePolyline = false;


    public OptionViewBuilder withCenterCoordinates(double centerLatitude, double centerLongitude) {
        this.centerLatLng = new LatLng(centerLatitude, centerLongitude);
        return this;

    }

    public OptionViewBuilder withIsMultipleMarker(boolean multipleMarker) {
        this.isMultipleMarker = multipleMarker;
        return this;
    }

    public OptionViewBuilder withIsSingleMarker(boolean singleMarker) {
        this.isSingleMarker = singleMarker;
        return this;
    }

    public OptionViewBuilder withMapsZoom(float mapsZoom) {
        this.mapsZoom = mapsZoom;
        return this;
    }

    public OptionViewBuilder withMarkers(List<ExtraMarker> markers) {
        this.markers = markers;
        return this;
    }

    public OptionViewBuilder withMarker(ExtraMarker marker) {
        this.marker = marker;
        return this;
    }


    public OptionViewBuilder withPolygon(LatLng[] polygon) {
        this.polygon = polygon;
        return this;
    }


    public OptionViewBuilder withIsSinglePolygon(boolean isSinglePolygon) {
        this.isSinglePolygon = isSinglePolygon;
        return this;
    }

    public OptionViewBuilder withPolyline(LatLng[] polyline) {
        this.polyline = polyline;
        return this;
    }

    public OptionViewBuilder withIsSinglePolyline(boolean isSinglePolyline) {
        this.isSinglePolyline = isSinglePolyline;
        return this;
    }

    public OptionViewBuilder withPolygons(LatLng[]... polygons) {
        this.polygons.addAll(Arrays.asList(polygons));
        return this;
    }

    public OptionViewBuilder withIsMultiplePolygon(boolean isMultiplePolygon) {
        this.isMultiplePolygon = isMultiplePolygon;
        return this;
    }


    public OptionViewBuilder withPolylines(LatLng[]... polylines) {
        this.polylines.addAll(Arrays.asList(polylines));
        return this;
    }

    public OptionViewBuilder withIsMultiplePolyline(boolean isMultiplePolyline) {
        this.isMultiplePolyline = isMultiplePolyline;
        return this;
    }

    public OptionView build() {
        return new OptionView(centerLatLng, isMultipleMarker, isSingleMarker, mapsZoom, markers, marker, isSinglePolygon, polygon, isSinglePolyline, polyline, isMultiplePolygon, polygons, isMultiplePolyline, polylines);
    }


}