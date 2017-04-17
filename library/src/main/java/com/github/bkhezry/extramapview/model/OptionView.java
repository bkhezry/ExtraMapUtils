package com.github.bkhezry.extramapview.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class OptionView implements Parcelable {
    private String title;
    private LatLng centerLatLng;
    private boolean forceCenterMap;
    private float mapsZoom;
    private List<ExtraMarker> markers = new ArrayList<>();
    private List<ExtraPolygon> polygons;
    private List<ExtraPolyline> polylines;
    private boolean isListView;

    public OptionView(String title, LatLng centerCoordinates, boolean forceCenterMap, float mapsZoom, List<ExtraMarker> markers, List<ExtraPolygon> polygons, List<ExtraPolyline> polylines, boolean isListView) {
        this.title = title;
        this.centerLatLng = centerCoordinates;
        this.forceCenterMap = forceCenterMap;
        this.mapsZoom = mapsZoom;
        this.markers = markers;
        this.polygons = polygons;
        this.polylines = polylines;
        this.isListView = isListView;
    }

    public LatLng getCenterLatLng() {
        return centerLatLng;
    }

    public void setCenterLatLng(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
    }

    public boolean isForceCenterMap() {
        return forceCenterMap;
    }

    public void setForceCenterMap(boolean forceCenterMap) {
        this.forceCenterMap = forceCenterMap;
    }

    public float getMapsZoom() {
        return mapsZoom;
    }

    public void setMapsZoom(float mapsZoom) {
        this.mapsZoom = mapsZoom;
    }

    public List<ExtraMarker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<ExtraMarker> markers) {
        this.markers = markers;
    }

    public List<ExtraPolygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<ExtraPolygon> polygons) {
        this.polygons = polygons;
    }

    public List<ExtraPolyline> getPolylines() {
        return polylines;
    }

    public void setPolylines(List<ExtraPolyline> polylines) {
        this.polylines = polylines;
    }

    public boolean isListView() {
        return isListView;
    }

    public void setListView(boolean listView) {
        isListView = listView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.centerLatLng, flags);
        dest.writeByte(this.forceCenterMap ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.mapsZoom);
        dest.writeTypedList(this.markers);
        dest.writeTypedList(this.polygons);
        dest.writeTypedList(this.polylines);
        dest.writeByte(this.isListView ? (byte) 1 : (byte) 0);
    }

    protected OptionView(Parcel in) {
        this.title = in.readString();
        this.centerLatLng = in.readParcelable(LatLng.class.getClassLoader());
        this.forceCenterMap = in.readByte() != 0;
        this.mapsZoom = in.readFloat();
        this.markers = in.createTypedArrayList(ExtraMarker.CREATOR);
        this.polygons = in.createTypedArrayList(ExtraPolygon.CREATOR);
        this.polylines = in.createTypedArrayList(ExtraPolyline.CREATOR);
        this.isListView = in.readByte() != 0;
    }

    public static final Creator<OptionView> CREATOR = new Creator<OptionView>() {
        @Override
        public OptionView createFromParcel(Parcel source) {
            return new OptionView(source);
        }

        @Override
        public OptionView[] newArray(int size) {
            return new OptionView[size];
        }
    };
}
