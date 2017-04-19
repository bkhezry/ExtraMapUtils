package com.github.bkhezry.extramaputils.builder;

import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionViewBuilder {
    private String title;
    private LatLng centerLatLng;
    private float mapsZoom = 14;
    private boolean forceCenterMap = false;
    private List<ExtraMarker> markers = new ArrayList<>();
    private List<ExtraPolygon> polygons = new ArrayList<>();
    private List<ExtraPolyline> polylines = new ArrayList<>();
    private boolean isListView = false;

    public OptionViewBuilder withCenterCoordinates(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
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

    public OptionViewBuilder withMarkers(ExtraMarker... markers) {
        this.markers.addAll(Arrays.asList(markers));
        return this;
    }

    public OptionViewBuilder withPolygons(ExtraPolygon... polygons) {
        this.polygons.addAll(Arrays.asList(polygons));
        return this;
    }


    public OptionViewBuilder withPolylines(ExtraPolyline... polylines) {
        this.polylines.addAll(Arrays.asList(polylines));
        return this;
    }

    public OptionViewBuilder withForceCenterMap(boolean withForceCenterMap) {
        this.forceCenterMap = withForceCenterMap;
        return this;
    }

    public OptionViewBuilder withIsListView(boolean isListView) {
        this.isListView = isListView;
        return this;
    }

    public OptionViewBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ViewOption build() {
        return new ViewOption(title, centerLatLng, forceCenterMap, mapsZoom, markers, polygons, polylines, isListView);
    }


}