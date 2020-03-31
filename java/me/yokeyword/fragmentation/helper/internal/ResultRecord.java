package me.yokeyword.fragmentation.helper.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class ResultRecord implements Parcelable {
    public static final Creator<ResultRecord> CREATOR = new Creator<ResultRecord>() {
        /* renamed from: a */
        public ResultRecord createFromParcel(Parcel parcel) {
            return new ResultRecord(parcel);
        }

        /* renamed from: a */
        public ResultRecord[] newArray(int i) {
            return new ResultRecord[i];
        }
    };
    public int a;
    public int b = 0;
    public Bundle c;

    public ResultRecord() {
    }

    protected ResultRecord(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readBundle(getClass().getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeBundle(this.c);
    }
}
