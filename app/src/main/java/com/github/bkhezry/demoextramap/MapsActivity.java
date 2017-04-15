package com.github.bkhezry.demoextramap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.github.bkhezry.extramapview.ExtraMapView;
import com.github.bkhezry.extramapview.model.OptionView;
import com.github.bkhezry.extramapview.model.OptionViewBuilder;
import com.github.bkhezry.demoextramap.utils.DataGenerator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ExtraMapView mMap;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mMap = (ExtraMapView) findViewById(R.id.mapLite);
        mMap.onCreate(savedInstanceState);
        mMap.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        MapsInitializer.initialize(getApplicationContext());
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        final OptionView optionView =
                new OptionViewBuilder()
                        .withCenterCoordinates(35.6892, 51.3890)
                        .withMarkers(DataGenerator.getListExtraMarker())
                        .withIsMultipleMarker(true)
                        .withPolygon(DataGenerator.getPolygon())
                        .withIsSinglePolygon(false)
                        .withPolyline(DataGenerator.getPolyline())
                        .withIsSinglePolyline(false)
                        .withPolygons(
                                DataGenerator.getPolygon_1(),
                                DataGenerator.getPolygon_2()
                        )
                        .withIsMultiplePolygon(true)
                        .withPolylines(
                                DataGenerator.getPolyline_1(),
                                DataGenerator.getPolyline_2()
                        )
                        .withIsMultiplePolyline(false)
                        .build();
        mMap.showExtraMap(optionView, map);
    }
}
