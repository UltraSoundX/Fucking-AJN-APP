package me.yokeyword.fragmentation.anim;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FragmentAnimator implements Parcelable {
    public static final Creator<FragmentAnimator> CREATOR = new Creator<FragmentAnimator>() {
        /* renamed from: a */
        public FragmentAnimator createFromParcel(Parcel parcel) {
            return new FragmentAnimator(parcel);
        }

        /* renamed from: a */
        public FragmentAnimator[] newArray(int i) {
            return new FragmentAnimator[i];
        }
    };
    protected int a;
    protected int b;
    protected int c;
    protected int d;

    public FragmentAnimator() {
    }

    public FragmentAnimator(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public FragmentAnimator a() {
        return new FragmentAnimator(b(), c(), d(), e());
    }

    protected FragmentAnimator(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }
}
