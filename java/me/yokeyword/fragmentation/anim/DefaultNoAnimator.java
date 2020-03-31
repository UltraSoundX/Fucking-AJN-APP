package me.yokeyword.fragmentation.anim;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DefaultNoAnimator extends FragmentAnimator implements Parcelable {
    public static final Creator<DefaultNoAnimator> CREATOR = new Creator<DefaultNoAnimator>() {
        /* renamed from: a */
        public DefaultNoAnimator createFromParcel(Parcel parcel) {
            return new DefaultNoAnimator(parcel);
        }

        /* renamed from: a */
        public DefaultNoAnimator[] newArray(int i) {
            return new DefaultNoAnimator[i];
        }
    };

    public DefaultNoAnimator() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
    }

    protected DefaultNoAnimator(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public int describeContents() {
        return 0;
    }
}
