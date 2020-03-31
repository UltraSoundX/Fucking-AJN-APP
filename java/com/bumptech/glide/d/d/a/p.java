package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import com.bumptech.glide.d.a;
import com.bumptech.glide.d.c.o;
import com.bumptech.glide.d.d.c.c;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.f.b;
import java.io.File;
import java.io.InputStream;

/* compiled from: StreamBitmapDataLoadProvider */
public class p implements b<InputStream, Bitmap> {
    private final q a;
    private final b b;
    private final o c = new o();
    private final c<Bitmap> d;

    public p(com.bumptech.glide.d.b.a.c cVar, a aVar) {
        this.a = new q(cVar, aVar);
        this.b = new b();
        this.d = new c<>(this.a);
    }

    public e<File, Bitmap> a() {
        return this.d;
    }

    public e<InputStream, Bitmap> b() {
        return this.a;
    }

    public com.bumptech.glide.d.b<InputStream> c() {
        return this.c;
    }

    public f<Bitmap> d() {
        return this.b;
    }
}
