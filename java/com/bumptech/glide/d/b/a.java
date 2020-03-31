package com.bumptech.glide.d.b;

import android.util.Log;
import com.bumptech.glide.d.g;
import com.bumptech.glide.i.d;
import com.bumptech.glide.k;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: DecodeJob */
class a<A, T, Z> {
    private static final b a = new b();
    private final f b;
    private final int c;
    private final int d;
    private final com.bumptech.glide.d.a.c<A> e;
    private final com.bumptech.glide.f.b<A, T> f;
    private final g<T> g;
    private final com.bumptech.glide.d.d.f.c<T, Z> h;
    private final C0037a i;
    private final b j;
    private final k k;
    /* access modifiers changed from: private */
    public final b l;
    private volatile boolean m;

    /* renamed from: com.bumptech.glide.d.b.a$a reason: collision with other inner class name */
    /* compiled from: DecodeJob */
    interface C0037a {
        com.bumptech.glide.d.b.b.a a();
    }

    /* compiled from: DecodeJob */
    static class b {
        b() {
        }

        public OutputStream a(File file) throws FileNotFoundException {
            return new BufferedOutputStream(new FileOutputStream(file));
        }
    }

    /* compiled from: DecodeJob */
    class c<DataType> implements com.bumptech.glide.d.b.b.a.b {
        private final com.bumptech.glide.d.b<DataType> b;
        private final DataType c;

        public c(com.bumptech.glide.d.b<DataType> bVar, DataType datatype) {
            this.b = bVar;
            this.c = datatype;
        }

        public boolean a(File file) {
            boolean z = false;
            boolean z2 = null;
            try {
                z2 = a.this.l.a(file);
                z = this.b.a(this.c, z2);
                if (z2 != null) {
                    try {
                        z2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e2) {
                z2 = Log.isLoggable("DecodeJob", 3);
                if (z2) {
                    Log.d("DecodeJob", "Failed to find file to write to disk cache", e2);
                }
                if (z2 != null) {
                    try {
                        z2.close();
                    } catch (IOException e3) {
                    }
                }
            } finally {
                if (z2 != null) {
                    try {
                        z2.close();
                    } catch (IOException e4) {
                    }
                }
            }
            return z;
        }
    }

    public a(f fVar, int i2, int i3, com.bumptech.glide.d.a.c<A> cVar, com.bumptech.glide.f.b<A, T> bVar, g<T> gVar, com.bumptech.glide.d.d.f.c<T, Z> cVar2, C0037a aVar, b bVar2, k kVar) {
        this(fVar, i2, i3, cVar, bVar, gVar, cVar2, aVar, bVar2, kVar, a);
    }

    a(f fVar, int i2, int i3, com.bumptech.glide.d.a.c<A> cVar, com.bumptech.glide.f.b<A, T> bVar, g<T> gVar, com.bumptech.glide.d.d.f.c<T, Z> cVar2, C0037a aVar, b bVar2, k kVar, b bVar3) {
        this.b = fVar;
        this.c = i2;
        this.d = i3;
        this.e = cVar;
        this.f = bVar;
        this.g = gVar;
        this.h = cVar2;
        this.i = aVar;
        this.j = bVar2;
        this.k = kVar;
        this.l = bVar3;
    }

    public k<Z> a() throws Exception {
        if (!this.j.b()) {
            return null;
        }
        long a2 = d.a();
        k a3 = a((com.bumptech.glide.d.c) this.b);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Decoded transformed from cache", a2);
        }
        long a4 = d.a();
        k<Z> d2 = d(a3);
        if (!Log.isLoggable("DecodeJob", 2)) {
            return d2;
        }
        a("Transcoded transformed from cache", a4);
        return d2;
    }

    public k<Z> b() throws Exception {
        if (!this.j.a()) {
            return null;
        }
        long a2 = d.a();
        k a3 = a(this.b.a());
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Decoded source from cache", a2);
        }
        return a(a3);
    }

    public k<Z> c() throws Exception {
        return a(e());
    }

    public void d() {
        this.m = true;
        this.e.c();
    }

    private k<Z> a(k<T> kVar) {
        long a2 = d.a();
        k c2 = c(kVar);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transformed resource from source", a2);
        }
        b(c2);
        long a3 = d.a();
        k<Z> d2 = d(c2);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transcoded transformed from source", a3);
        }
        return d2;
    }

    private void b(k<T> kVar) {
        if (kVar != null && this.j.b()) {
            long a2 = d.a();
            this.i.a().a(this.b, new c(this.f.d(), kVar));
            if (Log.isLoggable("DecodeJob", 2)) {
                a("Wrote transformed from source to cache", a2);
            }
        }
    }

    private k<T> e() throws Exception {
        try {
            long a2 = d.a();
            Object a3 = this.e.a(this.k);
            if (Log.isLoggable("DecodeJob", 2)) {
                a("Fetched data", a2);
            }
            if (this.m) {
                return null;
            }
            k<T> a4 = a((A) a3);
            this.e.a();
            return a4;
        } finally {
            this.e.a();
        }
    }

    private k<T> a(A a2) throws IOException {
        if (this.j.a()) {
            return b(a2);
        }
        long a3 = d.a();
        k<T> a4 = this.f.b().a(a2, this.c, this.d);
        if (!Log.isLoggable("DecodeJob", 2)) {
            return a4;
        }
        a("Decoded from source", a3);
        return a4;
    }

    private k<T> b(A a2) throws IOException {
        long a3 = d.a();
        this.i.a().a(this.b.a(), new c(this.f.c(), a2));
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Wrote source to cache", a3);
        }
        long a4 = d.a();
        k<T> a5 = a(this.b.a());
        if (Log.isLoggable("DecodeJob", 2) && a5 != null) {
            a("Decoded source from cache", a4);
        }
        return a5;
    }

    private k<T> a(com.bumptech.glide.d.c cVar) throws IOException {
        k<T> kVar = null;
        File a2 = this.i.a().a(cVar);
        if (a2 != null) {
            try {
                kVar = this.f.a().a(a2, this.c, this.d);
                if (kVar == null) {
                    this.i.a().b(cVar);
                }
            } catch (Throwable th) {
                if (kVar == null) {
                    this.i.a().b(cVar);
                }
                throw th;
            }
        }
        return kVar;
    }

    private k<T> c(k<T> kVar) {
        if (kVar == null) {
            return null;
        }
        k<T> a2 = this.g.a(kVar, this.c, this.d);
        if (kVar.equals(a2)) {
            return a2;
        }
        kVar.d();
        return a2;
    }

    private k<Z> d(k<T> kVar) {
        if (kVar == null) {
            return null;
        }
        return this.h.a(kVar);
    }

    private void a(String str, long j2) {
        Log.v("DecodeJob", str + " in " + d.a(j2) + ", key: " + this.b);
    }
}
