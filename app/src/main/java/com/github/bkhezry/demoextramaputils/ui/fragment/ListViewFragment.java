package com.github.bkhezry.demoextramaputils.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.ui.activity.GeoJsonActivity;
import com.github.bkhezry.demoextramaputils.ui.activity.MapsActivity;
import com.github.bkhezry.demoextramaputils.utils.AppUtils;
import com.github.bkhezry.extramaputils.builder.ViewOptionBuilder;
import com.github.bkhezry.extramaputils.listener.onKMLEventListener;
import com.github.bkhezry.extramaputils.model.ViewOption;
import com.github.bkhezry.extramaputils.listener.onGeoJsonEventListener;
import com.github.bkhezry.extramaputils.utils.MapUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.kml.KmlLayer;

import java.util.HashSet;

public class ListViewFragment extends Fragment {
    private ListFragment mList;
    private MapAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view,
                container, false);
        mAdapter = new MapAdapter(getActivity(), LIST_OPTION_VIEW);
        mList = (ListFragment) getChildFragmentManager().findFragmentById(R.id.list);
        mList.setListAdapter(mAdapter);
        AbsListView lv = mList.getListView();
        lv.setRecyclerListener(mRecycleListener);
        return view;

    }

    private class MapAdapter extends ArrayAdapter<ViewOption> {

        private final HashSet<MapView> mMaps = new HashSet<>();
        private ViewOption[] viewOptions;
        private Context context;

        public MapAdapter(Context context, ViewOption[] viewOptions) {
            super(context, R.layout.list_item, R.id.titleTextView, viewOptions);
            this.viewOptions = viewOptions;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item, null);
                holder = new ViewHolder();
                holder.mapView = (MapView) convertView.findViewById(R.id.mapLite);
                holder.title = (TextView) convertView.findViewById(R.id.titleTextView);
                holder.cardView = (CardView) convertView.findViewById(R.id.cardView);
                holder.context = context;
                convertView.setTag(holder);
                holder.initializeMapView();
                mMaps.add(holder.mapView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final ViewOption viewOption = viewOptions[position];
            holder.mapView.setTag(viewOption);
            if (holder.map != null) {
                setMapLocation(viewOption, holder.map, context);
            }
            holder.title.setText(viewOption.getTitle());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle args = new Bundle();
                    Intent intent;
                    args.putParcelable("optionView", viewOption);
                    if (viewOption.getTitle().equals("GeoJson") || viewOption.getTitle().equals("KML")) {
                        intent = new Intent(getActivity(), GeoJsonActivity.class);
                    } else {
                        intent = new Intent(getActivity(), MapsActivity.class);
                    }
                    intent.putExtra("args", args);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        public HashSet<MapView> getMaps() {
            return mMaps;
        }
    }

    private static void setMapLocation(ViewOption viewOption, GoogleMap googleMap, Context context) {
        MapUtils.showElements(viewOption, googleMap, context);
    }

    private static class ViewHolder implements OnMapReadyCallback {
        MapView mapView;
        TextView title;
        GoogleMap map;
        CardView cardView;
        Context context;

        private ViewHolder() {

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(context);
            MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle_night);
            map = googleMap;
            map.setMapStyle(style);
            final ViewOption viewOption = (ViewOption) mapView.getTag();
            if (viewOption != null) {
                setMapLocation(viewOption, map, context);
            }
        }

        private void initializeMapView() {
            if (mapView != null) {
                mapView.onCreate(null);
                mapView.getMapAsync(this);
            }
        }

    }

    public Fragment newInstance() {
        return new ListViewFragment();
    }

    private AbsListView.RecyclerListener mRecycleListener = new AbsListView.RecyclerListener() {

        @Override
        public void onMovedToScrapHeap(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder != null && holder.map != null) {
                holder.map.clear();
                holder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }

        }
    };

    private static ViewOption[] LIST_OPTION_VIEW = {
            new ViewOptionBuilder()
                    .withTitle("Gray Scale")
                    .withStyleName(ViewOption.StyleDef.GRAY_SCALE)
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withMarkers(AppUtils.getListExtraMarker())
                    .withForceCenterMap(false)
                    .withIsListView(true)
                    .build(),
            new ViewOptionBuilder()
                    .withTitle("Retro")
                    .withStyleName(ViewOption.StyleDef.RETRO)
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withPolygons(
                            AppUtils.getPolygon_1(),
                            AppUtils.getPolygon_2()
                    )
                    .withForceCenterMap(false)
                    .withIsListView(true)
                    .build(),
            new ViewOptionBuilder()
                    .withTitle("Default")
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withPolylines(
                            AppUtils.getPolyline_4()
                    )
                    .withForceCenterMap(false)
                    .withIsListView(true)
                    .build(),
            new ViewOptionBuilder()
                    .withTitle("Night Mode")
                    .withStyleName(ViewOption.StyleDef.NIGHT)
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withMarkers(AppUtils.getListMarker())
                    .withPolylines(AppUtils.getPolyline_3())
                    .withForceCenterMap(false)
                    .withIsListView(true)
                    .build(),
            new ViewOptionBuilder()
                    .withTitle("GeoJson")
                    .withGeoJson("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson")
                    //.withGeoJson(R.raw.all_day)
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
                    .build(),
            new ViewOptionBuilder()
                    .withTitle("KML")
                    .withKML("http://googlemaps.github.io/kml-samples/morekml/Polygons/Polygons.Google_Campus.kml")
                    .withStyleName(ViewOption.StyleDef.DEFAULT)
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withForceCenterMap(false)
                    .withKMLEventListener(new onKMLEventListener() {
                        @Override
                        public void onFeatureClick(Feature feature) {

                        }

                        @Override
                        public void onKMLLoaded(KmlLayer kmlLayer) {

                        }
                    })
                    .withIsListView(true)
                    .build()

    };
}
