package com.github.bkhezry.extramaputils.builder;

import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;

import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.google.android.gms.maps.model.LatLng;

public class ExtraMarkerBuilder {
    private String name;
    private LatLng center;
    private @IdRes int icon = Integer.MAX_VALUE;
    private @ColorInt int iconColor = Integer.MAX_VALUE;

    public ExtraMarkerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ExtraMarkerBuilder setCenter(LatLng center) {
        this.center = center;
        return this;
    }

    public ExtraMarkerBuilder setIcon(@IdRes int icon) {
        this.icon = icon;
        return this;
    }

    public ExtraMarkerBuilder setIconColor(@ColorInt int iconColor) {
        this.iconColor = iconColor;
        return this;
    }

    public ExtraMarker build() {
        return new ExtraMarker(name, center, icon, iconColor);
    }
}