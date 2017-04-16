package com.github.bkhezry.extramapview.builder;

import com.github.bkhezry.extramapview.model.ExtraMarker;
import com.google.android.gms.maps.model.LatLng;

public class ExtraMarkerBuilder {
    private String name;
    private LatLng center;
    private Integer icon;

    public ExtraMarkerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ExtraMarkerBuilder setCenter(LatLng center) {
        this.center = center;
        return this;
    }

    public ExtraMarkerBuilder setIcon(Integer icon) {
        this.icon = icon;
        return this;
    }

    public ExtraMarker build() {
        return new ExtraMarker(name, center, icon);
    }
}