package com.github.bkhezry.extramaputils.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.IdRes;

import com.google.android.gms.maps.model.LatLng;

public class ExtraMarker implements Parcelable {
    private String name;
    private LatLng center;
    private @IdRes int icon;

    public ExtraMarker(String name, LatLng center,@IdRes int icon) {
        this.name = name;
        this.icon = icon;
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public @IdRes int getIcon() {
        return icon;
    }

    public void setIcon(@IdRes  int icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.center, flags);
        dest.writeInt(this.icon);
    }

    protected ExtraMarker(Parcel in) {
        this.name = in.readString();
        this.center = in.readParcelable(LatLng.class.getClassLoader());
        this.icon = in.readInt();
    }

    public static final Creator<ExtraMarker> CREATOR = new Creator<ExtraMarker>() {
        @Override
        public ExtraMarker createFromParcel(Parcel source) {
            return new ExtraMarker(source);
        }

        @Override
        public ExtraMarker[] newArray(int size) {
            return new ExtraMarker[size];
        }
    };
}
