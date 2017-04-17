package com.github.bkhezry.extramaputils.builder;

import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.google.android.gms.maps.model.LatLng;

public class ExtraPolylineBuilder {
    private LatLng[] points;
    private int strokeColor;
    private int strokeWidth;
    private float zIndex;

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

    public ExtraPolyline build() {
        return new ExtraPolyline(points, strokeColor, strokeWidth, zIndex);
    }
}