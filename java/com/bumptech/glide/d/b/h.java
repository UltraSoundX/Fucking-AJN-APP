package com.bumptech.glide.d.b;

import android.os.Looper;
import com.bumptech.glide.d.c;

/* compiled from: EngineResource */
class h<Z> implements k<Z> {
    private final k<Z> a;
    private final boolean b;
    private a c;
    private c d;
    private int e;
    private boolean f;

    /* compiled from: EngineResource */
    interface a {
        void b(c cVar, h<?> hVar);
    }

    h(k<Z> kVar, boolean z) {
        if (kVar == null) {
            throw new NullPointerException("Wrapped resource must not be null");
        }
        this.a = kVar;
        this.b = z;
    }

    /* access modifiers changed from: 0000 */
    public void a(c cVar, a aVar) {
        this.d = cVar;
        this.c = aVar;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.b;
    }

    public Z b() {
        return this.a.b();
    }

    public int c() {
        return this.a.c();
    }

    public void d() {
        if (this.e > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (this.f) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        } else {
            this.f = true;
            this.a.d();
        }
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        if (this.f) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        } else {
            this.e++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        if (this.e <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call release on the main thread");
        } else {
            int i = this.e - 1;
            this.e = i;
            if (i == 0) {
                this.c.b(this.d, this);
            }
        }
    }
}
