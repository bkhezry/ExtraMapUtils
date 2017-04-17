package com.github.bkhezry.extramaputils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UiOptions implements Parcelable {
    private int color;
    private float width;
    private float zIndex;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getzIndex() {
        return zIndex;
    }

    public void setzIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.color);
        dest.writeFloat(this.width);
        dest.writeFloat(this.zIndex);
    }

    public UiOptions() {
    }

    protected UiOptions(Parcel in) {
        this.color = in.readInt();
        this.width = in.readFloat();
        this.zIndex = in.readFloat();
    }

    public static final Parcelable.Creator<UiOptions> CREATOR = new Parcelable.Creator<UiOptions>() {
        @Override
        public UiOptions createFromParcel(Parcel source) {
            return new UiOptions(source);
        }

        @Override
        public UiOptions[] newArray(int size) {
            return new UiOptions[size];
        }
    };
}
