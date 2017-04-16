package com.github.bkhezry.demoextramapview.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bkhezry.demoextramapview.R;
import com.github.bkhezry.demoextramapview.adapter.MapViewAdapter;
import com.github.bkhezry.demoextramapview.utils.DataGenerator;
import com.github.bkhezry.extramapview.builder.OptionViewBuilder;
import com.github.bkhezry.extramapview.model.OptionView;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RecycleViewFragment extends Fragment {
    protected MapViewAdapter mListAdapter;
    protected RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview,
                container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        int rows = 1;
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), rows, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = createMapListAdapter();
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();
        return view;
    }

    private MapViewAdapter createMapListAdapter() {
        ArrayList<OptionView> optionViews = new ArrayList<>();
        optionViews.add(
                new OptionViewBuilder()
                        .withCenterCoordinates(new LatLng(35.6892, 51.3890))
                        .withMarkers(DataGenerator.getListExtraMarker())
                        .withPolygons(
                                DataGenerator.getPolygon_1(),
                                DataGenerator.getPolygon_2()
                        )
                        .withPolylines(
                                DataGenerator.getPolyline_1(),
                                DataGenerator.getPolyline_2()
                        )
                        .withForceCenterMap(false)
                        .withGoogleMap(null)
                        .build()
        );

        return new MapViewAdapter(optionViews);
    }

    public Fragment newInstance() {
        return new RecycleViewFragment();
    }
}
