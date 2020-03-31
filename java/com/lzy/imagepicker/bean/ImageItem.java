package com.lzy.imagepicker.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ImageItem implements Parcelable, Serializable {
    public static final Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
        /* renamed from: a */
        public ImageItem createFromParcel(Parcel parcel) {
            return new ImageItem(parcel);
        }

        /* renamed from: a */
        public ImageItem[] newArray(int i) {
            return new ImageItem[i];
        }
    };
    public String a;
    public String b;
    public long c;
    public int d;
    public int e;
    public String f;
    public long g;

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageItem)) {
            return super.equals(obj);
        }
        ImageItem imageItem = (ImageItem) obj;
        return this.b.equalsIgnoreCase(imageItem.b) && this.g == imageItem.g;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeLong(this.g);
    }

    public ImageItem() {
    }

    protected ImageItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readLong();
    }
}
