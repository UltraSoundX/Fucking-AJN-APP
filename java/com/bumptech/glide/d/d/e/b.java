package com.bumptech.glide.d.d.e;

import com.bumptech.glide.d.b.k;

/* compiled from: GifBitmapWrapperResource */
public class b implements k<a> {
    private final a a;

    public b(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = aVar;
    }

    /* renamed from: a */
    public a b() {
        return this.a;
    }

    public int c() {
        return this.a.a();
    }

    public void d() {
        k b = this.a.b();
        if (b != null) {
            b.d();
        }
        k c = this.a.c();
        if (c != null) {
            c.d();
        }
    }
}
