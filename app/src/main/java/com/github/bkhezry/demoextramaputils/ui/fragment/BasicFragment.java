package com.github.bkhezry.demoextramaputils.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.ui.MapsActivity;
import com.github.bkhezry.demoextramaputils.utils.AppUtils;
import com.github.bkhezry.extramaputils.utils.MapUtils;
import com.github.bkhezry.extramaputils.builder.ViewOptionBuilder;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class BasicFragment extends Fragment implements OnMapReadyCallback {
    private MapView mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic,
                container, false);
        mMap = (MapView) view.findViewById(R.id.mapLite);
        mMap.onCreate(savedInstanceState);
        mMap.getMapAsync(this);
        return view;
    }

    public Fragment newInstance() {
        return new BasicFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        final ViewOption viewOption =
                new ViewOptionBuilder()
                        .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                        .withMarkers(AppUtils.getListExtraMarker())
                        .withPolygons(
                                AppUtils.getPolygon_1(),
                                AppUtils.getPolygon_2()
                        )
                        .withPolylines(
                                AppUtils.getPolyline_1(),
                                AppUtils.getPolyline_2()
                        )
                        .withForceCenterMap(false)
                        .build();
        MapUtils.showElements(viewOption, googleMap,getActivity());
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Bundle args = new Bundle();
                args.putParcelable("optionView", viewOption);
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("args", args);
                startActivity(intent);
            }
        });
    }
}
