package com.bumptech.glide.d.d.d;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.b.a.C0036a;
import com.bumptech.glide.d.b.a.c;

/* compiled from: GifBitmapProvider */
class a implements C0036a {
    private final c a;

    public a(c cVar) {
        this.a = cVar;
    }

    public Bitmap a(int i, int i2, Config config) {
        return this.a.b(i, i2, config);
    }

    public void a(Bitmap bitmap) {
        if (!this.a.a(bitmap)) {
            bitmap.recycle();
        }
    }
}
