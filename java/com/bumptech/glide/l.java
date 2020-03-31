package com.bumptech.glide;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.k;
import com.stub.StubApp;

/* compiled from: RequestManager */
public class l implements h {
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final g b;
    private final k c;
    /* access modifiers changed from: private */
    public final com.bumptech.glide.manager.l d;
    /* access modifiers changed from: private */
    public final i e;
    /* access modifiers changed from: private */
    public final c f;
    /* access modifiers changed from: private */
    public a g;

    /* compiled from: RequestManager */
    public interface a {
        <T> void a(e<T, ?, ?, ?> eVar);
    }

    /* compiled from: RequestManager */
    public final class b<A, T> {
        /* access modifiers changed from: private */
        public final com.bumptech.glide.d.c.l<A, T> b;
        /* access modifiers changed from: private */
        public final Class<T> c;

        /* compiled from: RequestManager */
        public final class a {
            private final A b;
            private final Class<A> c;
            private final boolean d = true;

            a(A a2) {
                this.b = a2;
                this.c = l.b(a2);
            }

            public <Z> f<A, T, Z> a(Class<Z> cls) {
                f<A, T, Z> fVar = (f) l.this.f.a(new f(l.this.a, l.this.e, this.c, b.this.b, b.this.c, cls, l.this.d, l.this.b, l.this.f));
                if (this.d) {
                    fVar.b(this.b);
                }
                return fVar;
            }
        }

        b(com.bumptech.glide.d.c.l<A, T> lVar, Class<T> cls) {
            this.b = lVar;
            this.c = cls;
        }

        public a a(A a2) {
            return new a<>(a2);
        }
    }

    /* compiled from: RequestManager */
    class c {
        c() {
        }

        public <A, X extends e<A, ?, ?, ?>> X a(X x) {
            if (l.this.g != null) {
                l.this.g.a(x);
            }
            return x;
        }
    }

    /* compiled from: RequestManager */
    private static class d implements com.bumptech.glide.manager.c.a {
        private final com.bumptech.glide.manager.l a;

        public d(com.bumptech.glide.manager.l lVar) {
            this.a = lVar;
        }

        public void a(boolean z) {
            if (z) {
                this.a.d();
            }
        }
    }

    public l(Context context, g gVar, k kVar) {
        this(context, gVar, kVar, new com.bumptech.glide.manager.l(), new com.bumptech.glide.manager.d());
    }

    l(Context context, final g gVar, k kVar, com.bumptech.glide.manager.l lVar, com.bumptech.glide.manager.d dVar) {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.b = gVar;
        this.c = kVar;
        this.d = lVar;
        this.e = i.a(context);
        this.f = new c();
        com.bumptech.glide.manager.c a2 = dVar.a(context, new d(lVar));
        if (com.bumptech.glide.i.h.d()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    gVar.a(l.this);
                }
            });
        } else {
            gVar.a(this);
        }
        gVar.a(a2);
    }

    public void a(int i) {
        this.e.a(i);
    }

    public void a() {
        this.e.h();
    }

    public void b() {
        com.bumptech.glide.i.h.a();
        this.d.a();
    }

    public void c() {
        com.bumptech.glide.i.h.a();
        this.d.b();
    }

    public void d() {
        c();
    }

    public void e() {
        b();
    }

    public void f() {
        this.d.c();
    }

    public <A, T> b<A, T> a(com.bumptech.glide.d.c.l<A, T> lVar, Class<T> cls) {
        return new b<>(lVar, cls);
    }

    public d<String> a(String str) {
        return (d) g().b(str);
    }

    public d<String> g() {
        return a(String.class);
    }

    public d<Uri> a(Uri uri) {
        return (d) h().b(uri);
    }

    public d<Uri> h() {
        return a(Uri.class);
    }

    private <T> d<T> a(Class<T> cls) {
        com.bumptech.glide.d.c.l a2 = i.a(cls, this.a);
        com.bumptech.glide.d.c.l b2 = i.b(cls, this.a);
        if (cls != null && a2 == null && b2 == null) {
            throw new IllegalArgumentException("Unknown type " + cls + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
        }
        return (d) this.f.a(new d(cls, a2, b2, this.a, this.e, this.d, this.b, this.f));
    }

    /* access modifiers changed from: private */
    public static <T> Class<T> b(T t) {
        if (t != null) {
            return t.getClass();
        }
        return null;
    }
}
