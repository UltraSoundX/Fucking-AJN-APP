package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Poi implements Parcelable {
    public static final Creator<Poi> CREATOR = new l();
    private final double a;
    private final String b;
    private final String c;

    public Poi(String str, String str2, double d) {
        this.b = str;
        this.c = str2;
        this.a = d;
    }

    public String a() {
        return this.b;
    }

    public double b() {
        return this.a;
    }

    public String c() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeDouble(this.a);
    }
}
