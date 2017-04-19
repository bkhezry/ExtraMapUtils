package com.github.bkhezry.extramaputils.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.PatternItem;

import java.util.Arrays;
import java.util.List;

public class UiOptions implements Parcelable {
    private int color;
    private float width;
    private float zIndex;


    public enum StrokePatternDef {
        DEFAULT, DOTTED, DASHED, MIXED
    }

    private StrokePatternDef strokePattern;

    public StrokePatternDef getStrokePattern() {
        return strokePattern;
    }

    public void setStrokePattern(StrokePatternDef strokePattern) {
        this.strokePattern = strokePattern;
    }

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

    public UiOptions() {
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
        dest.writeInt(this.strokePattern == null ? -1 : this.strokePattern.ordinal());
    }

    protected UiOptions(Parcel in) {
        this.color = in.readInt();
        this.width = in.readFloat();
        this.zIndex = in.readFloat();
        int tmpStrokePattern = in.readInt();
        this.strokePattern = tmpStrokePattern == -1 ? null : StrokePatternDef.values()[tmpStrokePattern];
    }

    public static final Creator<UiOptions> CREATOR = new Creator<UiOptions>() {
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
