package com.bumptech.glide.d.d.e;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.d.a.l;
import com.bumptech.glide.d.d.a.o;
import com.bumptech.glide.d.e;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: GifBitmapWrapperResourceDecoder */
public class c implements e<g, a> {
    private static final b a = new b();
    private static final a b = new a();
    private final e<g, Bitmap> c;
    private final e<InputStream, com.bumptech.glide.d.d.d.b> d;
    private final com.bumptech.glide.d.b.a.c e;
    private final b f;
    private final a g;
    private String h;

    /* compiled from: GifBitmapWrapperResourceDecoder */
    static class a {
        a() {
        }

        public InputStream a(InputStream inputStream, byte[] bArr) {
            return new o(inputStream, bArr);
        }
    }

    /* compiled from: GifBitmapWrapperResourceDecoder */
    static class b {
        b() {
        }

        public com.bumptech.glide.d.d.a.l.a a(InputStream inputStream) throws IOException {
            return new l(inputStream).b();
        }
    }

    public c(e<g, Bitmap> eVar, e<InputStream, com.bumptech.glide.d.d.d.b> eVar2, com.bumptech.glide.d.b.a.c cVar) {
        this(eVar, eVar2, cVar, a, b);
    }

    c(e<g, Bitmap> eVar, e<InputStream, com.bumptech.glide.d.d.d.b> eVar2, com.bumptech.glide.d.b.a.c cVar, b bVar, a aVar) {
        this.c = eVar;
        this.d = eVar2;
        this.e = cVar;
        this.f = bVar;
        this.g = aVar;
    }

    public k<a> a(g gVar, int i, int i2) throws IOException {
        com.bumptech.glide.i.a a2 = com.bumptech.glide.i.a.a();
        byte[] b2 = a2.b();
        try {
            a a3 = a(gVar, i, i2, b2);
            if (a3 != null) {
                return new b(a3);
            }
            return null;
        } finally {
            a2.a(b2);
        }
    }

    private a a(g gVar, int i, int i2, byte[] bArr) throws IOException {
        if (gVar.a() != null) {
            return b(gVar, i, i2, bArr);
        }
        return b(gVar, i, i2);
    }

    private a b(g gVar, int i, int i2, byte[] bArr) throws IOException {
        InputStream a2 = this.g.a(gVar.a(), bArr);
        a2.mark(2048);
        com.bumptech.glide.d.d.a.l.a a3 = this.f.a(a2);
        a2.reset();
        a aVar = null;
        if (a3 == com.bumptech.glide.d.d.a.l.a.GIF) {
            aVar = a(a2, i, i2);
        }
        if (aVar == null) {
            return b(new g(a2, gVar.b()), i, i2);
        }
        return aVar;
    }

    private a a(InputStream inputStream, int i, int i2) throws IOException {
        k a2 = this.d.a(inputStream, i, i2);
        if (a2 == null) {
            return null;
        }
        com.bumptech.glide.d.d.d.b bVar = (com.bumptech.glide.d.d.d.b) a2.b();
        if (bVar.e() > 1) {
            return new a(null, a2);
        }
        return new a(new com.bumptech.glide.d.d.a.c(bVar.b(), this.e), null);
    }

    private a b(g gVar, int i, int i2) throws IOException {
        k a2 = this.c.a(gVar, i, i2);
        if (a2 != null) {
            return new a(a2, null);
        }
        return null;
    }

    public String a() {
        if (this.h == null) {
            this.h = this.d.a() + this.c.a();
        }
        return this.h;
    }
}
