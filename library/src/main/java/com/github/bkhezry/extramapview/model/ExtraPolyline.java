package com.github.bkhezry.extramapview.model;


import com.google.android.gms.maps.model.LatLng;

public class ExtraPolyline extends UiOptions {
    private LatLng[] points;

    public ExtraPolyline(LatLng[] points, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        setStrokeColor(strokeColor);
        setStrokeWidth(strokeWidth);
        setzIndex(zIndex);
    }

}
