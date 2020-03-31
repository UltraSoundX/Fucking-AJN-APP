package com.bumptech.glide.d.d.e;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.d.d.b;

/* compiled from: GifBitmapWrapper */
public class a {
    private final k<b> a;
    private final k<Bitmap> b;

    public a(k<Bitmap> kVar, k<b> kVar2) {
        if (kVar != null && kVar2 != null) {
            throw new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
        } else if (kVar == null && kVar2 == null) {
            throw new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
        } else {
            this.b = kVar;
            this.a = kVar2;
        }
    }

    public int a() {
        if (this.b != null) {
            return this.b.c();
        }
        return this.a.c();
    }

    public k<Bitmap> b() {
        return this.b;
    }

    public k<b> c() {
        return this.a;
    }
}
