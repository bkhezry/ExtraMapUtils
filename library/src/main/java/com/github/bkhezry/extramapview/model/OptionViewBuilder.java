package com.github.bkhezry.extramapview.model;

import com.google.android.gms.maps.model.LatLng;

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
    private List<LatLng[]> polygons;
    private boolean isMultiplePolygon = false;
    private List<LatLng[]> polylines;
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

    public OptionViewBuilder withPolygons(List<LatLng[]> polygons) {
        this.polygons = polygons;
        return this;
    }

    public OptionViewBuilder withIsMultiplePolygon(boolean isMultiplePolygon) {
        this.isMultiplePolygon = isMultiplePolygon;
        return this;
    }


    public OptionViewBuilder withMultiplePolyline(List<LatLng[]> polylines) {
        this.polylines = polylines;
        return this;
    }

    public OptionViewBuilder withIsMultiplePolyline(boolean isMultiplePolyline) {
        this.isMultiplePolyline = isMultiplePolyline;
        return this;
    }

    public OptionView build() {
        return new OptionView(centerLatLng, isMultipleMarker, isSingleMarker, mapsZoom, markers, marker, isSinglePolygon, polygon, isSinglePolyline, polyline, isMultiplePolygon, polygons,isMultiplePolyline,polylines);
    }


}