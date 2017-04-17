package com.github.bkhezry.extramapview.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class ExtraPolygon extends UiOptions implements Parcelable {
    private LatLng[] points;
    private int fillColor;

    public ExtraPolygon(LatLng[] points, int fillColor, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        this.fillColor = fillColor;
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

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.points, flags);
        dest.writeInt(this.fillColor);
    }

    protected ExtraPolygon(Parcel in) {
        this.points = in.createTypedArray(LatLng.CREATOR);
        this.fillColor = in.readInt();
    }

    public static final Parcelable.Creator<ExtraPolygon> CREATOR = new Parcelable.Creator<ExtraPolygon>() {
        @Override
        public ExtraPolygon createFromParcel(Parcel source) {
            return new ExtraPolygon(source);
        }

        @Override
        public ExtraPolygon[] newArray(int size) {
            return new ExtraPolygon[size];
        }
    };
}
