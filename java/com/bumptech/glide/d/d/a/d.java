package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.g;
import com.bumptech.glide.i.h;

/* compiled from: BitmapTransformation */
public abstract class d implements g<Bitmap> {
    private c a;

    /* access modifiers changed from: protected */
    public abstract Bitmap a(c cVar, Bitmap bitmap, int i, int i2);

    public d(c cVar) {
        this.a = cVar;
    }

    public final k<Bitmap> a(k<Bitmap> kVar, int i, int i2) {
        if (!h.a(i, i2)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i + " or height: " + i2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        Bitmap bitmap = (Bitmap) kVar.b();
        if (i == Integer.MIN_VALUE) {
            i = bitmap.getWidth();
        }
        if (i2 == Integer.MIN_VALUE) {
            i2 = bitmap.getHeight();
        }
        Bitmap a2 = a(this.a, bitmap, i, i2);
        if (bitmap.equals(a2)) {
            return kVar;
        }
        return c.a(a2, this.a);
    }
}
