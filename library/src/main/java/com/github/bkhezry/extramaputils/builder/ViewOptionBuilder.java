package com.github.bkhezry.extramaputils.builder;

import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewOptionBuilder {
    private String title;
    private LatLng centerLatLng;
    private float mapsZoom = 14;
    private boolean forceCenterMap = false;
    private List<ExtraMarker> markers = new ArrayList<>();
    private List<ExtraPolygon> polygons = new ArrayList<>();
    private List<ExtraPolyline> polylines = new ArrayList<>();
    private boolean isListView = false;
    private ViewOption.StyleDef styleName = ViewOption.StyleDef.DEFAULT;

    public ViewOptionBuilder withCenterCoordinates(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
        return this;

    }

    public ViewOptionBuilder withMapsZoom(float mapsZoom) {
        this.mapsZoom = mapsZoom;
        return this;
    }

    public ViewOptionBuilder withMarkers(List<ExtraMarker> markers) {
        this.markers = markers;
        return this;
    }

    public ViewOptionBuilder withMarkers(ExtraMarker... markers) {
        this.markers.addAll(Arrays.asList(markers));
        return this;
    }

    public ViewOptionBuilder withPolygons(ExtraPolygon... polygons) {
        this.polygons.addAll(Arrays.asList(polygons));
        return this;
    }


    public ViewOptionBuilder withPolylines(ExtraPolyline... polylines) {
        this.polylines.addAll(Arrays.asList(polylines));
        return this;
    }

    public ViewOptionBuilder withForceCenterMap(boolean withForceCenterMap) {
        this.forceCenterMap = withForceCenterMap;
        return this;
    }

    public ViewOptionBuilder withIsListView(boolean isListView) {
        this.isListView = isListView;
        return this;
    }

    public ViewOptionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ViewOptionBuilder withStyleName(ViewOption.StyleDef styleName) {
        this.styleName = styleName;
        return this;
    }

    public ViewOption build() {
        return new ViewOption(title, centerLatLng, forceCenterMap, mapsZoom, markers, polygons, polylines, isListView, styleName);
    }


}