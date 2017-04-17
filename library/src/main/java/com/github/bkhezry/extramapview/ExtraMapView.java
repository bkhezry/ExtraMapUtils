package com.github.bkhezry.extramapview;

import android.content.Context;
import android.util.AttributeSet;

import com.github.bkhezry.extramapview.model.OptionView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class ExtraMapView extends MapView {

    public ExtraMapView(Context context) {
        super(context);
    }

    public ExtraMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExtraMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ExtraMapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);
    }

    public void showExtraMap(final OptionView optionView, GoogleMap googleMap) {

    }
}
