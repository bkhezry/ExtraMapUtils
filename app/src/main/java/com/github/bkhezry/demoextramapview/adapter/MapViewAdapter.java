package com.github.bkhezry.demoextramapview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bkhezry.demoextramapview.R;
import com.github.bkhezry.extramapview.ExtraMapView;
import com.github.bkhezry.extramapview.model.OptionView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.HashSet;
import java.util.List;

public class MapViewAdapter extends RecyclerView.Adapter<MapViewAdapter.MapViewHolder> {
    List<OptionView> list;
    protected HashSet<ExtraMapView> mMapViews = new HashSet<>();

    public MapViewAdapter(List<OptionView> list) {
        this.list = list;
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MapViewHolder viewHolder = new MapViewHolder(parent.getContext(), view);
        mMapViews.add(viewHolder.mapView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {
        OptionView optionView = list.get(position);
        optionView.setGoogleMap(holder.mGoogleMap);
        holder.mapView.showExtraMap(optionView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MapViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        public GoogleMap mGoogleMap;
        public ExtraMapView mapView;
        public Context mContext;

        public MapViewHolder(Context context, View view) {
            super(view);
            mContext = context;
            mapView = (ExtraMapView) view.findViewById(R.id.mapItem);
            mapView.onCreate(null);
            mapView.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;
            MapsInitializer.initialize(mContext);

        }
    }
}
