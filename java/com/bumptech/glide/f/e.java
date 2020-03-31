package com.bumptech.glide.f;

import com.bumptech.glide.d.b;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.d.f.c;
import com.bumptech.glide.d.f;
import java.io.File;

/* compiled from: FixedLoadProvider */
public class e<A, T, Z, R> implements f<A, T, Z, R> {
    private final l<A, T> a;
    private final c<Z, R> b;
    private final b<T, Z> c;

    public e(l<A, T> lVar, c<Z, R> cVar, b<T, Z> bVar) {
        if (lVar == null) {
            throw new NullPointerException("ModelLoader must not be null");
        }
        this.a = lVar;
        if (cVar == null) {
            throw new NullPointerException("Transcoder must not be null");
        }
        this.b = cVar;
        if (bVar == null) {
            throw new NullPointerException("DataLoadProvider must not be null");
        }
        this.c = bVar;
    }

    public l<A, T> e() {
        return this.a;
    }

    public c<Z, R> f() {
        return this.b;
    }

    public com.bumptech.glide.d.e<File, Z> a() {
        return this.c.a();
    }

    public com.bumptech.glide.d.e<T, Z> b() {
        return this.c.b();
    }

    public b<T> c() {
        return this.c.c();
    }

    public f<Z> d() {
        return this.c.d();
    }
}
