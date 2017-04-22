package com.github.bkhezry.demoextramaputils.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.utils.AppUtils;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.github.bkhezry.extramaputils.onGeoJsonEventListener;
import com.github.bkhezry.extramaputils.utils.MapUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;

public class GeoJsonActivity extends FragmentActivity implements OnMapReadyCallback {
    private ViewOption viewOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Bundle bundle = getIntent().getParcelableExtra("args");
        viewOption = bundle.getParcelable("optionView");
        viewOption.setEventListener(new onGeoJsonEventListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                Toast.makeText(GeoJsonActivity.this,
                        "Feature clicked: " + feature.getProperty("title"),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGeoJsonLoaded(GeoJsonLayer geoJsonLayer) {
                AppUtils.addColorsToMarkers(geoJsonLayer);
            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapUtils.showElements(viewOption, googleMap, this);
    }
}
