package android.support.transition;

import android.os.Build.VERSION;
import android.view.ViewGroup;

/* compiled from: ViewGroupUtils */
class ab {
    static aa a(ViewGroup viewGroup) {
        if (VERSION.SDK_INT >= 18) {
            return new z(viewGroup);
        }
        return y.a(viewGroup);
    }

    static void a(ViewGroup viewGroup, boolean z) {
        if (VERSION.SDK_INT >= 18) {
            ad.a(viewGroup, z);
        } else {
            ac.a(viewGroup, z);
        }
    }
}
