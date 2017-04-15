package com.github.bkhezry.demoextramap.utils;

import android.graphics.Color;

import com.github.bkhezry.extramapview.model.ExtraMarker;
import com.github.bkhezry.extramapview.model.ExtraPolygon;
import com.github.bkhezry.extramapview.model.ExtraPolygonBuilder;
import com.github.bkhezry.extramapview.model.ExtraPolyline;
import com.github.bkhezry.extramapview.model.ExtraPolylineBuilder;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    private static LatLng[] latLngs_1 = {
            new LatLng(35.751221, 51.348371),
            new LatLng(35.749257, 51.371679),
            new LatLng(35.740067, 51.370048),
            new LatLng(35.740812, 51.346795)
    };
    private static LatLng[] latLngs_2 = {
            new LatLng(35.735607, 51.383739),
            new LatLng(35.735842, 51.386496),
            new LatLng(35.723379, 51.388689),
            new LatLng(35.724067, 51.384462)
    };

    public static List<ExtraMarker> getListExtraMarker() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        extraMarkers.add(new ExtraMarker("Marker_1", latLngs_1[0], 1));
        extraMarkers.add(new ExtraMarker("Marker_2", latLngs_1[1], 1));
        extraMarkers.add(new ExtraMarker("Marker_3", latLngs_1[2], 1));
        extraMarkers.add(new ExtraMarker("Marker_4", latLngs_1[3], 1));
        return extraMarkers;
    }

    public static ExtraPolygon getPolygon_1() {
        return new ExtraPolygonBuilder()
                .setPoints(latLngs_1)
                .setzIndex(0)
                .setStrokeWidth(10)
                .setStrokeColor(Color.argb(10, 255, 255, 0))
                .setFillColor(Color.argb(20, 200, 200, 200))
                .build();
    }

    public static ExtraPolygon getPolygon_2() {
        return new ExtraPolygonBuilder()
                .setPoints(latLngs_2)
                .setzIndex(0)
                .setStrokeWidth(5)
                .setStrokeColor(Color.argb(100, 50, 50, 0))
                .setFillColor(Color.argb(200, 100, 100, 100))
                .build();
    }

    public static ExtraPolyline getPolyline_1() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_1)
                .setzIndex(0)
                .setStrokeWidth(5)
                .setStrokeColor(Color.argb(100, 50, 50, 0))
                .build();
    }

    public static ExtraPolyline getPolyline_2() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_2)
                .setzIndex(0)
                .setStrokeWidth(10)
                .setStrokeColor(Color.argb(10, 255, 255, 0))
                .build();
    }
}
