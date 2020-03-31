package com.bumptech.glide.f;

import com.bumptech.glide.d.b;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.d.f.c;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import java.io.File;

/* compiled from: ChildLoadProvider */
public class a<A, T, Z, R> implements f<A, T, Z, R>, Cloneable {
    private final f<A, T, Z, R> a;
    private e<File, Z> b;
    private e<T, Z> c;
    private f<Z> d;
    private c<Z, R> e;
    private b<T> f;

    public a(f<A, T, Z, R> fVar) {
        this.a = fVar;
    }

    public l<A, T> e() {
        return this.a.e();
    }

    public void a(e<T, Z> eVar) {
        this.c = eVar;
    }

    public void a(b<T> bVar) {
        this.f = bVar;
    }

    public e<File, Z> a() {
        if (this.b != null) {
            return this.b;
        }
        return this.a.a();
    }

    public e<T, Z> b() {
        if (this.c != null) {
            return this.c;
        }
        return this.a.b();
    }

    public b<T> c() {
        if (this.f != null) {
            return this.f;
        }
        return this.a.c();
    }

    public f<Z> d() {
        if (this.d != null) {
            return this.d;
        }
        return this.a.d();
    }

    public c<Z, R> f() {
        if (this.e != null) {
            return this.e;
        }
        return this.a.f();
    }

    /* renamed from: g */
    public a<A, T, Z, R> clone() {
        try {
            return (a) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }
}
