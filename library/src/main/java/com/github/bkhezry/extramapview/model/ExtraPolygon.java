package com.github.bkhezry.extramapview.model;


import com.google.android.gms.maps.model.LatLng;

public class ExtraPolygon extends UiOptions {
    private LatLng[] points;
    private int fillColor;

    public ExtraPolygon(LatLng[] points, int fillColor, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        this.fillColor = fillColor;
        setStrokeColor(strokeColor);
        setStrokeWidth(strokeWidth);
        setzIndex(zIndex);
    }
}
