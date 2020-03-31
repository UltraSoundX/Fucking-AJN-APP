package me.yokeyword.fragmentation.anim;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import me.yokeyword.fragmentation.R;

public class DefaultVerticalAnimator extends FragmentAnimator implements Parcelable {
    public static final Creator<DefaultVerticalAnimator> CREATOR = new Creator<DefaultVerticalAnimator>() {
        /* renamed from: a */
        public DefaultVerticalAnimator createFromParcel(Parcel parcel) {
            return new DefaultVerticalAnimator(parcel);
        }

        /* renamed from: a */
        public DefaultVerticalAnimator[] newArray(int i) {
            return new DefaultVerticalAnimator[i];
        }
    };

    public DefaultVerticalAnimator() {
        this.a = R.anim.v_fragment_enter;
        this.b = R.anim.v_fragment_exit;
        this.c = R.anim.v_fragment_pop_enter;
        this.d = R.anim.v_fragment_pop_exit;
    }

    protected DefaultVerticalAnimator(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public int describeContents() {
        return 0;
    }
}
