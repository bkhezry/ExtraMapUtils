package com.github.bkhezry.extramapview.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class OptionView {
    private LatLng centerLatLng;
    private boolean forceCenterMap;
    private float mapsZoom;
    private List<ExtraMarker> markers = new ArrayList<>();
    private List<ExtraPolygon> polygons;
    private List<ExtraPolyline> polylines;


    public OptionView(LatLng centerCoordinates, boolean forceCenterMap, float mapsZoom, List<ExtraMarker> markers, List<ExtraPolygon> polygons, List<ExtraPolyline> polylines) {
        this.centerLatLng = centerCoordinates;
        this.forceCenterMap = forceCenterMap;
        this.mapsZoom = mapsZoom;
        this.markers = markers;
        this.polygons = polygons;
        this.polylines = polylines;
    }

    public LatLng getCenterLatLng() {
        return centerLatLng;
    }

    public void setCenterLatLng(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
    }

    public boolean isForceCenterMap() {
        return forceCenterMap;
    }

    public void setForceCenterMap(boolean forceCenterMap) {
        this.forceCenterMap = forceCenterMap;
    }

    public float getMapsZoom() {
        return mapsZoom;
    }

    public void setMapsZoom(float mapsZoom) {
        this.mapsZoom = mapsZoom;
    }

    public List<ExtraMarker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<ExtraMarker> markers) {
        this.markers = markers;
    }

    public List<ExtraPolygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<ExtraPolygon> polygons) {
        this.polygons = polygons;
    }

    public List<ExtraPolyline> getPolylines() {
        return polylines;
    }

    public void setPolylines(List<ExtraPolyline> polylines) {
        this.polylines = polylines;
    }

}
