package me.yokeyword.fragmentation.anim;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import me.yokeyword.fragmentation.R;

public class DefaultHorizontalAnimator extends FragmentAnimator implements Parcelable {
    public static final Creator<DefaultHorizontalAnimator> CREATOR = new Creator<DefaultHorizontalAnimator>() {
        /* renamed from: a */
        public DefaultHorizontalAnimator createFromParcel(Parcel parcel) {
            return new DefaultHorizontalAnimator(parcel);
        }

        /* renamed from: a */
        public DefaultHorizontalAnimator[] newArray(int i) {
            return new DefaultHorizontalAnimator[i];
        }
    };

    public DefaultHorizontalAnimator() {
        this.a = R.anim.h_fragment_enter;
        this.b = R.anim.h_fragment_exit;
        this.c = R.anim.h_fragment_pop_enter;
        this.d = R.anim.h_fragment_pop_exit;
    }

    protected DefaultHorizontalAnimator(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public int describeContents() {
        return 0;
    }
}
