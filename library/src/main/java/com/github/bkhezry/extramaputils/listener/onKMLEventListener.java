package com.github.bkhezry.extramaputils.listener;

import com.google.maps.android.data.Feature;
import com.google.maps.android.data.kml.KmlLayer;

public interface onKMLEventListener {
    void onFeatureClick(Feature feature);
    void onKMLLoaded(KmlLayer kmlLayer);
}