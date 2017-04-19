package com.github.bkhezry.extramaputils.builder;

import android.graphics.Color;

import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.github.bkhezry.extramaputils.model.UiOptions;
import com.google.android.gms.maps.model.LatLng;

public class ExtraPolylineBuilder {
    private LatLng[] points;
    private int strokeColor = Color.argb(255, 0, 0, 0);
    private int strokeWidth = 10;
    private float zIndex = 0;
    private UiOptions.StrokePatternDef strokePattern = UiOptions.StrokePatternDef.DEFAULT;

    public ExtraPolylineBuilder setPoints(LatLng[] points) {
        this.points = points;
        return this;
    }

    public ExtraPolylineBuilder setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public ExtraPolylineBuilder setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public ExtraPolylineBuilder setzIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public ExtraPolylineBuilder setStrokePattern(UiOptions.StrokePatternDef strokePattern) {
        this.strokePattern = strokePattern;
        return this;
    }

    public ExtraPolyline build() {
        if (points == null || points.length < 2) {
            throw new IllegalStateException("please provide array list of latlng points with min size=2");
        }
        return new ExtraPolyline(points, strokeColor, strokeWidth, zIndex, strokePattern);
    }
}