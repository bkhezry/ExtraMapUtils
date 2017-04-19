package com.github.bkhezry.extramaputils.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.Arrays;

public class ExtraPolygon implements Parcelable {
    private LatLng[] points;
    private int fillColor;
    private UiOptions uiOptions;

    public ExtraPolygon(LatLng[] points, int fillColor, int strokeColor, int strokeWidth, float zIndex) {
        this.points = points;
        this.fillColor = fillColor;
        uiOptions = new UiOptions();
        uiOptions.setColor(strokeColor);
        uiOptions.setzIndex(zIndex);
        uiOptions.setWidth(strokeWidth);

    }

    public double getArea() {
        return SphericalUtil.computeArea(Arrays.asList(points));
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
        dest.writeInt(this.fillColor);
        dest.writeParcelable(this.uiOptions, flags);
    }

    protected ExtraPolygon(Parcel in) {
        this.points = in.createTypedArray(LatLng.CREATOR);
        this.fillColor = in.readInt();
        this.uiOptions = in.readParcelable(UiOptions.class.getClassLoader());
    }

    public static final Creator<ExtraPolygon> CREATOR = new Creator<ExtraPolygon>() {
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
