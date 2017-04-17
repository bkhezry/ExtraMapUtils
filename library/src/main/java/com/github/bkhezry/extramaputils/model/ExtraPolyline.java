package com.github.bkhezry.extramaputils.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class ExtraPolyline implements Parcelable {
    private LatLng[] points;
    private UiOptions uiOptions;


    public ExtraPolyline(LatLng[] points, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        uiOptions = new UiOptions();
        uiOptions.setColor(strokeColor);
        uiOptions.setzIndex(zIndex);
        uiOptions.setWidth(strokeWidth);
    }

    public LatLng[] getPoints() {
        return points;
    }

    public void setPoints(LatLng[] points) {
        this.points = points;
    }

    public UiOptions getUiOptions() {
        return uiOptions;
    }

    public void setUiOptions(UiOptions uiOptions) {
        this.uiOptions = uiOptions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.points, flags);
        dest.writeParcelable(this.uiOptions, flags);
    }

    protected ExtraPolyline(Parcel in) {
        this.points = in.createTypedArray(LatLng.CREATOR);
        this.uiOptions = in.readParcelable(UiOptions.class.getClassLoader());
    }

    public static final Creator<ExtraPolyline> CREATOR = new Creator<ExtraPolyline>() {
        @Override
        public ExtraPolyline createFromParcel(Parcel source) {
            return new ExtraPolyline(source);
        }

        @Override
        public ExtraPolyline[] newArray(int size) {
            return new ExtraPolyline[size];
        }
    };
}
