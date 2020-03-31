package com.bumptech.glide.g.b;

import android.support.v4.widget.ExploreByTouchHelper;
import com.bumptech.glide.i.h;

/* compiled from: SimpleTarget */
public abstract class g<Z> extends a<Z> {
    private final int a;
    private final int b;

    public g() {
        this(ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID);
    }

    public g(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final void a(h hVar) {
        if (!h.a(this.a, this.b)) {
            throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.a + " and height: " + this.b + ", either provide dimensions in the constructor" + " or call override()");
        }
        hVar.a(this.a, this.b);
    }
}
