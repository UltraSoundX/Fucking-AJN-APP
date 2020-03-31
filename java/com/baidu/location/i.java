package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class i implements Creator<BDLocation> {
    i() {
    }

    /* renamed from: a */
    public BDLocation createFromParcel(Parcel parcel) {
        return new BDLocation(parcel, null);
    }

    /* renamed from: a */
    public BDLocation[] newArray(int i) {
        return new BDLocation[i];
    }
}
