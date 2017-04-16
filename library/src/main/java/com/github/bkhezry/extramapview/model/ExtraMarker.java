package com.github.bkhezry.extramapview.model;

import com.google.android.gms.maps.model.LatLng;

public class ExtraMarker {
    private String name;
    private LatLng center;
    private int icon;

    public ExtraMarker(String name, LatLng center, Integer icon) {
        this.name = name;
        this.icon = icon;
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
