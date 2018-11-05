package com.github.bkhezry.demoextramaputils.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.ui.activity.MapsActivity;
import com.github.bkhezry.demoextramaputils.utils.AppUtils;
import com.github.bkhezry.extramaputils.builder.ViewOptionBuilder;
import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.ExtraPolyline;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.github.bkhezry.extramaputils.utils.MapUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;


public class BasicFragment extends Fragment implements OnMapReadyCallback {
    private MapView mMap;
    private TextView areaTextView;
    private TextView lengthTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic,
                container, false);
        areaTextView = (TextView) view.findViewById(R.id.area);
        lengthTextView = (TextView) view.findViewById(R.id.length);
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
        final ViewOption viewOption = getViewOption();
        calculateAreaAndLength(viewOption);
        MapUtils.showElements(viewOption, googleMap, getActivity());
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

    private void calculateAreaAndLength(ViewOption viewOption) {
        double area = 0.0;
        double length = 0.0;
        for (ExtraPolygon extraPolygon : viewOption.getPolygons()) {
            area += extraPolygon.getArea();
        }
        areaTextView.setText(String.format(Locale.ENGLISH, "%.2f", area) + getString(R.string.square_meters));
        for (ExtraPolyline extraPolyline : viewOption.getPolylines()) {
            length += extraPolyline.getLength();
        }
        lengthTextView.setText(String.format(Locale.ENGLISH, "%.2f", length) + getString(R.string.meters));
    }

    public static ViewOption getViewOption() {
        return new ViewOptionBuilder()
                .withStyleName(ViewOption.StyleDef.RETRO)
                .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                .withMarkers(AppUtils.getListExtraMarker())
                .withPolygons(
                        AppUtils.getPolygon_1()
                )
                .withPolylines(
                        AppUtils.getPolyline_2(),
                        AppUtils.getPolyline_4()
                )
                .withForceCenterMap(false)
                .build();
    }
}
