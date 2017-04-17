package com.github.bkhezry.extramapview.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class ExtraPolyline extends UiOptions implements Parcelable {
    private LatLng[] points;

    public ExtraPolyline(LatLng[] points, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        setColor(strokeColor);
        setWidth(strokeWidth);
        setzIndex(zIndex);
    }

    public LatLng[] getPoints() {
        return points;
    }

    public void setPoints(LatLng[] points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.points, flags);
    }

    protected ExtraPolyline(Parcel in) {
        this.points = in.createTypedArray(LatLng.CREATOR);
    }

    public static final Parcelable.Creator<ExtraPolyline> CREATOR = new Parcelable.Creator<ExtraPolyline>() {
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
