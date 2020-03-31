package com.bumptech.glide.d.d.e;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.f.b;
import java.io.File;
import java.io.InputStream;

/* compiled from: ImageVideoGifDrawableLoadProvider */
public class g implements b<com.bumptech.glide.d.c.g, a> {
    private final e<File, a> a;
    private final e<com.bumptech.glide.d.c.g, a> b;
    private final f<a> c;
    private final com.bumptech.glide.d.b<com.bumptech.glide.d.c.g> d;

    public g(b<com.bumptech.glide.d.c.g, Bitmap> bVar, b<InputStream, com.bumptech.glide.d.d.d.b> bVar2, c cVar) {
        c cVar2 = new c(bVar.b(), bVar2.b(), cVar);
        this.a = new com.bumptech.glide.d.d.c.c(new e(cVar2));
        this.b = cVar2;
        this.c = new d(bVar.d(), bVar2.d());
        this.d = bVar.c();
    }

    public e<File, a> a() {
        return this.a;
    }

    public e<com.bumptech.glide.d.c.g, a> b() {
        return this.b;
    }

    public com.bumptech.glide.d.b<com.bumptech.glide.d.c.g> c() {
        return this.d;
    }

    public f<a> d() {
        return this.c;
    }
}
