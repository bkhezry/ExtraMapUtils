package com.github.bkhezry.extramapview.model;


import com.google.android.gms.maps.model.LatLng;

public class ExtraPolyline extends UiOptions {
    private LatLng[] points;

    public ExtraPolyline(LatLng[] points, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        setColor(strokeColor);
        setWidth(strokeWidth);
        setzIndex(zIndex);
    }

    public LatLng[] getPoints() {
        return points;
    }

    public void setPoints(LatLng[] points) {
        this.points = points;
    }
}
