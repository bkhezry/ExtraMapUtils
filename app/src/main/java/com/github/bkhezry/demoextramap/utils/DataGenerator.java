package com.github.bkhezry.demoextramap.utils;

import com.github.bkhezry.extramapview.model.ExtraMarker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    private static LatLng[] latLngs = {
            new LatLng(10.1, 100.5),
            new LatLng(9.7, 100.5),
            new LatLng(9.5, 100),
            new LatLng(9.9, 100)
    };
    private static LatLng[] latLngs_2 = {
            new LatLng(10.5, 101.5),
            new LatLng(10, 101.5),
            new LatLng(10, 100),
            new LatLng(10.3, 100)
    };

    public static List<ExtraMarker> getListExtraMarker() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        extraMarkers.add(new ExtraMarker("Marker_1", latLngs[0], 1));
        extraMarkers.add(new ExtraMarker("Marker_2", latLngs[1], 1));
        extraMarkers.add(new ExtraMarker("Marker_3", latLngs[2], 1));
        extraMarkers.add(new ExtraMarker("Marker_4", latLngs[3], 1));
        return extraMarkers;
    }

    public static LatLng[] getPolygon() {
        return latLngs;
    }

    public static LatLng[] getPolyline() {
        return latLngs;
    }

    public static List<LatLng[]> getPolygons() {
        List<LatLng[]> latLngList = new ArrayList<>();
        latLngList.add(latLngs);
        latLngList.add(latLngs_2);
        return latLngList;
    }

    public static List<LatLng[]> getPolylines() {
        List<LatLng[]> latLngList = new ArrayList<>();
        latLngList.add(latLngs);
        latLngList.add(latLngs_2);
        return latLngList;
    }
}
