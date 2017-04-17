package com.github.bkhezry.demoextramapview.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.bkhezry.demoextramapview.R;
import com.github.bkhezry.demoextramapview.utils.AppUtils;
import com.github.bkhezry.extramapview.ExtraMapView;
import com.github.bkhezry.extramapview.Utils.MapsUtils;
import com.github.bkhezry.extramapview.builder.OptionViewBuilder;
import com.github.bkhezry.extramapview.model.OptionView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashSet;

public class ListViewFragment extends Fragment {
    private ListFragment mList;
    private MapAdapter mAdapter;

    @Override
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

    public Fragment newInstance() {
        return new ListViewFragment();
    }

    private class MapAdapter extends ArrayAdapter<OptionView> {

        private final HashSet<ExtraMapView> mMaps = new HashSet<>();

        public MapAdapter(Context context, OptionView[] optionViews) {
            super(context, R.layout.list_item, R.id.titleTextView, optionViews);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;


            if (row == null) {
                row = getActivity().getLayoutInflater().inflate(R.layout.list_item, null);

                holder = new ViewHolder();
                holder.mapView = (ExtraMapView) row.findViewById(R.id.mapLite);
                holder.title = (TextView) row.findViewById(R.id.titleTextView);

                row.setTag(holder);

                holder.initializeMapView();

                mMaps.add(holder.mapView);
            } else {

                holder = (ViewHolder) row.getTag();
            }

            OptionView optionView = getItem(position);
            holder.mapView.setTag(optionView);

            if (holder.map != null) {
                setMapLocation(optionView, holder.map);
            }

            //holder.title.setText(optionView.name);

            return row;
        }

        public HashSet<ExtraMapView> getMaps() {
            return mMaps;
        }
    }

    private static void setMapLocation(OptionView optionView, GoogleMap googleMap) {
        MapsUtils.showElements(optionView, googleMap);
    }

    class ViewHolder implements OnMapReadyCallback {

        ExtraMapView mapView;

        TextView title;

        GoogleMap map;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(getActivity());
            map = googleMap;
            OptionView optionView = (OptionView) mapView.getTag();
            if (optionView != null) {
                setMapLocation(optionView, map);
            }
        }

        public void initializeMapView() {
            if (mapView != null) {
                mapView.onCreate(null);
                mapView.getMapAsync(this);
            }
        }

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

    private static OptionView[] LIST_OPTION_VIEW = {
            new OptionViewBuilder()
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withMarkers(AppUtils.getListExtraMarker())
//                    .withPolygons(
//                            DataGenerator.getPolygon_1(),
//                            DataGenerator.getPolygon_2()
//                    )
//                    .withPolylines(
//                            DataGenerator.getPolyline_1(),
//                            DataGenerator.getPolyline_2()
//                    )
                    .withForceCenterMap(false)
                    .withIsListView(true)
                    .build(),
            new OptionViewBuilder()
                    .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                    .withMarkers(AppUtils.getListExtraMarker_2())
//                    .withPolygons(
//                            DataGenerator.getPolygon_1(),
//                            DataGenerator.getPolygon_2()
//                    )
//                    .withPolylines(
//                            DataGenerator.getPolyline_1(),
//                            DataGenerator.getPolyline_2()
//                    )
                    .withForceCenterMap(false)
                    .withIsListView(true)
                    .build()
    };
}
