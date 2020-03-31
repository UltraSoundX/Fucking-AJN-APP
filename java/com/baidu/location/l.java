package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l implements Creator<Poi> {
    l() {
    }

    /* renamed from: a */
    public Poi createFromParcel(Parcel parcel) {
        return new Poi(parcel.readString(), parcel.readString(), parcel.readDouble());
    }

    /* renamed from: a */
    public Poi[] newArray(int i) {
        return new Poi[i];
    }
}
