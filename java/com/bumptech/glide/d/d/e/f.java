package com.bumptech.glide.d.d.e;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.d.d.b;
import com.bumptech.glide.d.d.d.e;
import com.bumptech.glide.d.g;

/* compiled from: GifBitmapWrapperTransformation */
public class f implements g<a> {
    private final g<Bitmap> a;
    private final g<b> b;

    public f(c cVar, g<Bitmap> gVar) {
        this(gVar, (g<b>) new e<b>(gVar, cVar));
    }

    f(g<Bitmap> gVar, g<b> gVar2) {
        this.a = gVar;
        this.b = gVar2;
    }

    public k<a> a(k<a> kVar, int i, int i2) {
        k b2 = ((a) kVar.b()).b();
        k c = ((a) kVar.b()).c();
        if (b2 != null && this.a != null) {
            k a2 = this.a.a(b2, i, i2);
            if (!b2.equals(a2)) {
                return new b(new a(a2, ((a) kVar.b()).c()));
            }
            return kVar;
        } else if (c == null || this.b == null) {
            return kVar;
        } else {
            k a3 = this.b.a(c, i, i2);
            if (!c.equals(a3)) {
                return new b(new a(((a) kVar.b()).b(), a3));
            }
            return kVar;
        }
    }

    public String a() {
        return this.a.a();
    }
}
