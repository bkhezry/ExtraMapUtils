package com.github.bkhezry.extramapview.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class OptionView {
    private LatLng centerLatLng;
    private boolean isMultipleMarker;
    private boolean isSingleMarker;
    private float mapsZoom;
    private List<ExtraMarker> markers = new ArrayList<>();
    private ExtraMarker marker;
    private LatLng[] polygon;
    private boolean isSinglePolygon;
    private LatLng[] polyline;
    private boolean isSinglePolyline;
    private List<LatLng[]> polygons;
    private boolean isMultiplePolygon;
    private List<LatLng[]> polylines;
    private boolean isMultiplePolyline;


    public OptionView(LatLng centerCoordinates, boolean isMultipleMarker, boolean isSingleMarker, float mapsZoom, List<ExtraMarker> markers, ExtraMarker marker, boolean isSinglePolygon, LatLng[] polygon, boolean isSinglePolyline, LatLng[] polyline, boolean isMultiplePolygon, List<LatLng[]> polygons, boolean isMultiplePolyline, List<LatLng[]> polylines) {
        this.centerLatLng = centerCoordinates;
        this.isMultipleMarker = isMultipleMarker;
        this.isSingleMarker = isSingleMarker;
        this.mapsZoom = mapsZoom;
        this.markers = markers;
        this.marker = marker;
        this.isSinglePolygon = isSinglePolygon;
        this.polygon = polygon;
        this.isSinglePolyline = isSinglePolyline;
        this.polyline = polyline;
        this.isMultiplePolygon = isMultiplePolygon;
        this.polygons = polygons;
        this.isMultiplePolyline = isMultiplePolyline;
        this.polylines = polylines;
    }

    public LatLng getCenterLatLng() {
        return centerLatLng;
    }

    public void setCenterLatLng(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
    }

    public boolean isMultipleMarker() {
        return isMultipleMarker;
    }

    public void setMultipleMarker(boolean multipleMarker) {
        this.isMultipleMarker = multipleMarker;
    }

    public boolean isSingleMarker() {
        return isSingleMarker;
    }

    public void setSingleMarker(boolean singleMarker) {
        this.isSingleMarker = singleMarker;
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

    public ExtraMarker getMarker() {
        return marker;
    }

    public void setMarker(ExtraMarker marker) {
        this.marker = marker;
    }

    public LatLng[] getPolygon() {
        return polygon;
    }

    public void setPolygon(LatLng[] polygon) {
        this.polygon = polygon;
    }

    public boolean isSinglePolygon() {
        return isSinglePolygon;
    }

    public void setSinglePolygon(boolean singlePolygon) {
        isSinglePolygon = singlePolygon;
    }

    public LatLng[] getPolyline() {
        return polyline;
    }

    public void setPolyline(LatLng[] polyline) {
        this.polyline = polyline;
    }

    public boolean isSinglePolyline() {
        return isSinglePolyline;
    }

    public void setSinglePolyline(boolean singlePolyline) {
        isSinglePolyline = singlePolyline;
    }

    public List<LatLng[]> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<LatLng[]> polygons) {
        this.polygons = polygons;
    }

    public boolean isMultiplePolygon() {
        return isMultiplePolygon;
    }

    public void setMultiplePolygon(boolean multiplePolygon) {
        isMultiplePolygon = multiplePolygon;
    }

    public List<LatLng[]> getPolylines() {
        return polylines;
    }

    public void setPolylines(List<LatLng[]> polylines) {
        this.polylines = polylines;
    }

    public boolean isMultiplePolyline() {
        return isMultiplePolyline;
    }

    public void setMultiplePolyline(boolean multiplePolyline) {
        isMultiplePolyline = multiplePolyline;
    }

}
