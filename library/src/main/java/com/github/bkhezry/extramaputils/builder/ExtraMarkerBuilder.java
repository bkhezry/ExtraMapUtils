package com.github.bkhezry.extramaputils.builder;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.google.android.gms.maps.model.LatLng;

public class ExtraMarkerBuilder {
    private String name;
    private LatLng center;
    private
    @IdRes
    int icon;

    public ExtraMarkerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ExtraMarkerBuilder setCenter(@NonNull LatLng center) {
        this.center = center;
        return this;
    }

    public ExtraMarkerBuilder setIcon(@IdRes int icon) {
        this.icon = icon;
        return this;
    }

    public ExtraMarker build() {
        if (center == null) {
            throw new IllegalStateException("ExtraMarker should be has center coordinates!");
        }
        return new ExtraMarker(name, center, icon);
    }
}