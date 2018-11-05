package com.github.bkhezry.extramaputils.builder;

import android.graphics.Color;
import androidx.annotation.NonNull;

import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.UiOptions;
import com.google.android.gms.maps.model.LatLng;

public class ExtraPolygonBuilder {
    private LatLng[] points;
    private int fillColor = Color.argb(0, 0, 0, 0);
    private int strokeColor = Color.argb(255, 0, 0, 0);
    private int strokeWidth = 10;
    private float zIndex = 0;
    private UiOptions.StrokePatternDef strokePattern = UiOptions.StrokePatternDef.DEFAULT;

    public ExtraPolygonBuilder setPoints(@NonNull LatLng[] points) {
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

    public ExtraPolygonBuilder setStrokePattern(UiOptions.StrokePatternDef strokePattern) {
        this.strokePattern = strokePattern;
        return this;
    }

    public ExtraPolygon build() {
        if (points == null || points.length < 3) {
            throw new IllegalStateException("please provide array list of latlng points with min size=3");
        }
        return new ExtraPolygon(points, fillColor, strokeColor, strokeWidth, zIndex, strokePattern);
    }
}