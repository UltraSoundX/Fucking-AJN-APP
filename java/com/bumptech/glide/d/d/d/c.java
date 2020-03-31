package com.bumptech.glide.d.d.d;

import android.content.Context;
import com.bumptech.glide.d.c.o;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.f.b;
import java.io.File;
import java.io.InputStream;

/* compiled from: GifDrawableLoadProvider */
public class c implements b<InputStream, b> {
    private final i a;
    private final j b;
    private final o c;
    private final com.bumptech.glide.d.d.c.c<b> d = new com.bumptech.glide.d.d.c.c<>(this.a);

    public c(Context context, com.bumptech.glide.d.b.a.c cVar) {
        this.a = new i(context, cVar);
        this.b = new j(cVar);
        this.c = new o();
    }

    public e<File, b> a() {
        return this.d;
    }

    public e<InputStream, b> b() {
        return this.a;
    }

    public com.bumptech.glide.d.b<InputStream> c() {
        return this.c;
    }

    public f<b> d() {
        return this.b;
    }
}
