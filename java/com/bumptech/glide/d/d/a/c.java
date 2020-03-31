package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.i.h;

/* compiled from: BitmapResource */
public class c implements k<Bitmap> {
    private final Bitmap a;
    private final com.bumptech.glide.d.b.a.c b;

    public static c a(Bitmap bitmap, com.bumptech.glide.d.b.a.c cVar) {
        if (bitmap == null) {
            return null;
        }
        return new c(bitmap, cVar);
    }

    public c(Bitmap bitmap, com.bumptech.glide.d.b.a.c cVar) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (cVar == null) {
            throw new NullPointerException("BitmapPool must not be null");
        } else {
            this.a = bitmap;
            this.b = cVar;
        }
    }

    /* renamed from: a */
    public Bitmap b() {
        return this.a;
    }

    public int c() {
        return h.a(this.a);
    }

    public void d() {
        if (!this.b.a(this.a)) {
            this.a.recycle();
        }
    }
}
