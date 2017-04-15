package com.github.bkhezry.extramapview.model;

import com.google.android.gms.maps.model.LatLng;

public class ExtraMarker {
    private String name;
    private LatLng center;
    private Integer icon;

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

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    @SuppressWarnings("unused")
    public ExtraMarker() {
    }

    public ExtraMarker(String name, LatLng center, Integer icon) {
        this.name = name;
        this.icon = icon;
        this.center = center;
    }
}
