package com.bumptech.glide.d.b;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.d.c;
import com.bumptech.glide.g.e;
import com.bumptech.glide.i.h;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* compiled from: EngineJob */
class d implements a {
    private static final a a = new a();
    private static final Handler b = new Handler(Looper.getMainLooper(), new b());
    private final List<e> c;
    private final a d;
    private final e e;
    private final c f;
    private final ExecutorService g;
    private final ExecutorService h;
    private final boolean i;
    private boolean j;
    private k<?> k;
    private boolean l;
    private Exception m;
    private boolean n;
    private Set<e> o;
    private i p;

    /* renamed from: q reason: collision with root package name */
    private h<?> f391q;
    private volatile Future<?> r;

    /* compiled from: EngineJob */
    static class a {
        a() {
        }

        public <R> h<R> a(k<R> kVar, boolean z) {
            return new h<>(kVar, z);
        }
    }

    /* compiled from: EngineJob */
    private static class b implements Callback {
        private b() {
        }

        public boolean handleMessage(Message message) {
            if (1 != message.what && 2 != message.what) {
                return false;
            }
            d dVar = (d) message.obj;
            if (1 == message.what) {
                dVar.b();
            } else {
                dVar.c();
            }
            return true;
        }
    }

    public d(c cVar, ExecutorService executorService, ExecutorService executorService2, boolean z, e eVar) {
        this(cVar, executorService, executorService2, z, eVar, a);
    }

    public d(c cVar, ExecutorService executorService, ExecutorService executorService2, boolean z, e eVar, a aVar) {
        this.c = new ArrayList();
        this.f = cVar;
        this.g = executorService;
        this.h = executorService2;
        this.i = z;
        this.e = eVar;
        this.d = aVar;
    }

    public void a(i iVar) {
        this.p = iVar;
        this.r = this.g.submit(iVar);
    }

    public void b(i iVar) {
        this.r = this.h.submit(iVar);
    }

    public void a(e eVar) {
        h.a();
        if (this.l) {
            eVar.a((k<?>) this.f391q);
        } else if (this.n) {
            eVar.a(this.m);
        } else {
            this.c.add(eVar);
        }
    }

    public void b(e eVar) {
        h.a();
        if (this.l || this.n) {
            c(eVar);
            return;
        }
        this.c.remove(eVar);
        if (this.c.isEmpty()) {
            a();
        }
    }

    private void c(e eVar) {
        if (this.o == null) {
            this.o = new HashSet();
        }
        this.o.add(eVar);
    }

    private boolean d(e eVar) {
        return this.o != null && this.o.contains(eVar);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        if (!this.n && !this.l && !this.j) {
            this.p.a();
            Future<?> future = this.r;
            if (future != null) {
                future.cancel(true);
            }
            this.j = true;
            this.e.a(this, this.f);
        }
    }

    public void a(k<?> kVar) {
        this.k = kVar;
        b.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.j) {
            this.k.d();
        } else if (this.c.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        } else {
            this.f391q = this.d.a(this.k, this.i);
            this.l = true;
            this.f391q.e();
            this.e.a(this.f, this.f391q);
            for (e eVar : this.c) {
                if (!d(eVar)) {
                    this.f391q.e();
                    eVar.a((k<?>) this.f391q);
                }
            }
            this.f391q.f();
        }
    }

    public void a(Exception exc) {
        this.m = exc;
        b.obtainMessage(2, this).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!this.j) {
            if (this.c.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            this.n = true;
            this.e.a(this.f, null);
            for (e eVar : this.c) {
                if (!d(eVar)) {
                    eVar.a(this.m);
                }
            }
        }
    }
}
