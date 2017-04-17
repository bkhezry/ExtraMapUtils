package com.github.bkhezry.extramaputils.builder;

import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.google.android.gms.maps.model.LatLng;

public class ExtraPolygonBuilder {
    private LatLng[] points;
    private int fillColor;
    private int strokeColor;
    private int strokeWidth;
    private float zIndex;

    public ExtraPolygonBuilder setPoints(LatLng[] points) {
        this.points = points;
        return this;
    }

    public ExtraPolygonBuilder setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public ExtraPolygonBuilder setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public ExtraPolygonBuilder setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public ExtraPolygonBuilder setzIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public ExtraPolygon build() {
        return new ExtraPolygon(points, fillColor, strokeColor, strokeWidth, zIndex);
    }
}