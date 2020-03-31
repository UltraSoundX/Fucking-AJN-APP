package com.bumptech.glide.d.b;

import android.util.Log;
import com.bumptech.glide.g.e;
import com.bumptech.glide.k;

/* compiled from: EngineRunnable */
class i implements com.bumptech.glide.d.b.c.b, Runnable {
    private final k a;
    private final a b;
    private final a<?, ?, ?> c;
    private b d = b.CACHE;
    private volatile boolean e;

    /* compiled from: EngineRunnable */
    interface a extends e {
        void b(i iVar);
    }

    /* compiled from: EngineRunnable */
    private enum b {
        CACHE,
        SOURCE
    }

    public i(a aVar, a<?, ?, ?> aVar2, k kVar) {
        this.b = aVar;
        this.c = aVar2;
        this.a = kVar;
    }

    public void a() {
        this.e = true;
        this.c.d();
    }

    public void run() {
        k kVar;
        Exception exc = null;
        if (!this.e) {
            try {
                kVar = d();
            } catch (Exception e2) {
                if (Log.isLoggable("EngineRunnable", 2)) {
                    Log.v("EngineRunnable", "Exception decoding", e2);
                }
                exc = e2;
                kVar = null;
            }
            if (this.e) {
                if (kVar != null) {
                    kVar.d();
                }
            } else if (kVar == null) {
                a(exc);
            } else {
                a(kVar);
            }
        }
    }

    private boolean c() {
        return this.d == b.CACHE;
    }

    private void a(k kVar) {
        this.b.a(kVar);
    }

    private void a(Exception exc) {
        if (c()) {
            this.d = b.SOURCE;
            this.b.b(this);
            return;
        }
        this.b.a(exc);
    }

    private k<?> d() throws Exception {
        if (c()) {
            return e();
        }
        return f();
    }

    private k<?> e() throws Exception {
        k<?> kVar;
        try {
            kVar = this.c.a();
        } catch (Exception e2) {
            if (Log.isLoggable("EngineRunnable", 3)) {
                Log.d("EngineRunnable", "Exception decoding result from cache: " + e2);
            }
            kVar = null;
        }
        if (kVar == null) {
            return this.c.b();
        }
        return kVar;
    }

    private k<?> f() throws Exception {
        return this.c.c();
    }

    public int b() {
        return this.a.ordinal();
    }
}
