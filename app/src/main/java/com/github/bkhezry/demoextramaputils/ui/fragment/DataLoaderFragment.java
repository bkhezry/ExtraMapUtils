package com.github.bkhezry.demoextramaputils.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.ui.activity.DataLoaderActivity;
import com.github.bkhezry.demoextramaputils.utils.AppUtils;
import com.github.bkhezry.extramaputils.builder.ViewOptionBuilder;
import com.github.bkhezry.extramaputils.listener.onGeoJsonEventListener;
import com.github.bkhezry.extramaputils.listener.onKMLEventListener;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.github.bkhezry.extramaputils.utils.MapUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.kml.KmlLayer;


public class DataLoaderFragment extends Fragment implements OnMapReadyCallback {
    private MapView mMap;
    private GoogleMap maps;
    private static String KML = "kml";
    private static String GEOJSON = "geojson";
    private Button geoJsonButton;
    private Button kmlButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loader,
                container, false);
        geoJsonButton = (Button) view.findViewById(R.id.geojson_load);
        geoJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maps != null) {
                    final ViewOption viewOption = getViewOption(GEOJSON, getActivity());
                    MapUtils.showElements(viewOption, maps, getActivity());
                    maps.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            startDataLoaderActivity(viewOption);
                        }
                    });
                }
            }
        });
        kmlButton = (Button) view.findViewById(R.id.kml_load);
        kmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maps != null) {
                    final ViewOption viewOption = getViewOption(KML, getActivity());
                    MapUtils.showElements(viewOption, maps, getActivity());
                    maps.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            startDataLoaderActivity(viewOption);
                        }
                    });
                }
            }
        });
        mMap = (MapView) view.findViewById(R.id.mapLite);
        mMap.onCreate(savedInstanceState);
        mMap.getMapAsync(this);
        return view;
    }

    private void startDataLoaderActivity(ViewOption viewOption) {
        Bundle args = new Bundle();
        args.putParcelable("optionView", viewOption);
        Intent intent = new Intent(getActivity(), DataLoaderActivity.class);
        intent.putExtra("args", args);
        startActivity(intent);
    }

    public Fragment newInstance() {
        return new DataLoaderFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        maps = googleMap;
    }


    public static ViewOption getViewOption(String type, final Context context) {
        if (type.equals(GEOJSON)) {
            return new ViewOptionBuilder()
                    .withTitle("GeoJson")
                    .withGeoJson(context.getString(R.string.geo_json_url))
                    .withStyleName(ViewOption.StyleDef.DEFAULT)
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withForceCenterMap(false)
                    .withGeoJsonEventListener(new onGeoJsonEventListener() {
                        @Override
                        public void onFeatureClick(Feature feature) {
                            //Do more things.
                        }

                        @Override
                        public void onGeoJsonLoaded(GeoJsonLayer geoJsonLayer) {
                            AppUtils.addColorsToMarkers(geoJsonLayer);
                        }
                    })
                    .withIsListView(true)
                    .build();
        } else if (type.equals(KML)) {
            return new ViewOptionBuilder()
                    .withTitle("KML")
                    .withKML(context.getString(R.string.kml_url))
                    .withStyleName(ViewOption.StyleDef.DEFAULT)
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withForceCenterMap(false)
                    .withKMLEventListener(new onKMLEventListener() {
                        @Override
                        public void onFeatureClick(Feature feature) {
                            Toast.makeText(context,
                                    "Feature clicked: " + feature.getId(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onKMLLoaded(KmlLayer kmlLayer) {
                            //Do more things.
                        }
                    })
                    .withIsListView(true)
                    .build();
        } else {
            return null;
        }

    }

}
