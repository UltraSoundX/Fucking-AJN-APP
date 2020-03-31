package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.c.h;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.f.b;
import java.io.File;
import java.io.InputStream;

/* compiled from: ImageVideoDataLoadProvider */
public class n implements b<g, Bitmap> {
    private final m a;
    private final e<File, Bitmap> b;
    private final f<Bitmap> c;
    private final h d;

    public n(b<InputStream, Bitmap> bVar, b<ParcelFileDescriptor, Bitmap> bVar2) {
        this.c = bVar.d();
        this.d = new h(bVar.c(), bVar2.c());
        this.b = bVar.a();
        this.a = new m(bVar.b(), bVar2.b());
    }

    public e<File, Bitmap> a() {
        return this.b;
    }

    public e<g, Bitmap> b() {
        return this.a;
    }

    public com.bumptech.glide.d.b<g> c() {
        return this.d;
    }

    public f<Bitmap> d() {
        return this.c;
    }
}
