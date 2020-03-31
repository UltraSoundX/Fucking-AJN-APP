package com.bumptech.glide.d.b;

import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.util.Log;
import com.bumptech.glide.d.b.b.a.C0039a;
import com.bumptech.glide.d.b.b.h;
import com.bumptech.glide.d.g;
import com.bumptech.glide.k;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: Engine */
public class c implements com.bumptech.glide.d.b.b.h.a, e, a {
    private final Map<com.bumptech.glide.d.c, d> a;
    private final g b;
    private final h c;
    private final a d;
    private final Map<com.bumptech.glide.d.c, WeakReference<h<?>>> e;
    private final l f;
    private final b g;
    private ReferenceQueue<h<?>> h;

    /* compiled from: Engine */
    static class a {
        private final ExecutorService a;
        private final ExecutorService b;
        private final e c;

        public a(ExecutorService executorService, ExecutorService executorService2, e eVar) {
            this.a = executorService;
            this.b = executorService2;
            this.c = eVar;
        }

        public d a(com.bumptech.glide.d.c cVar, boolean z) {
            return new d(cVar, this.a, this.b, z, this.c);
        }
    }

    /* compiled from: Engine */
    private static class b implements C0037a {
        private final C0039a a;
        private volatile com.bumptech.glide.d.b.b.a b;

        public b(C0039a aVar) {
            this.a = aVar;
        }

        public com.bumptech.glide.d.b.b.a a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.a.a();
                    }
                    if (this.b == null) {
                        this.b = new com.bumptech.glide.d.b.b.b();
                    }
                }
            }
            return this.b;
        }
    }

    /* renamed from: com.bumptech.glide.d.b.c$c reason: collision with other inner class name */
    /* compiled from: Engine */
    public static class C0041c {
        private final d a;
        private final com.bumptech.glide.g.e b;

        public C0041c(com.bumptech.glide.g.e eVar, d dVar) {
            this.b = eVar;
            this.a = dVar;
        }

        public void a() {
            this.a.b(this.b);
        }
    }

    /* compiled from: Engine */
    private static class d implements IdleHandler {
        private final Map<com.bumptech.glide.d.c, WeakReference<h<?>>> a;
        private final ReferenceQueue<h<?>> b;

        public d(Map<com.bumptech.glide.d.c, WeakReference<h<?>>> map, ReferenceQueue<h<?>> referenceQueue) {
            this.a = map;
            this.b = referenceQueue;
        }

        public boolean queueIdle() {
            e eVar = (e) this.b.poll();
            if (eVar != null) {
                this.a.remove(eVar.a);
            }
            return true;
        }
    }

    /* compiled from: Engine */
    private static class e extends WeakReference<h<?>> {
        /* access modifiers changed from: private */
        public final com.bumptech.glide.d.c a;

        public e(com.bumptech.glide.d.c cVar, h<?> hVar, ReferenceQueue<? super h<?>> referenceQueue) {
            super(hVar, referenceQueue);
            this.a = cVar;
        }
    }

    public c(h hVar, C0039a aVar, ExecutorService executorService, ExecutorService executorService2) {
        this(hVar, aVar, executorService, executorService2, null, null, null, null, null);
    }

    c(h hVar, C0039a aVar, ExecutorService executorService, ExecutorService executorService2, Map<com.bumptech.glide.d.c, d> map, g gVar, Map<com.bumptech.glide.d.c, WeakReference<h<?>>> map2, a aVar2, l lVar) {
        this.c = hVar;
        this.g = new b(aVar);
        if (map2 == null) {
            map2 = new HashMap<>();
        }
        this.e = map2;
        if (gVar == null) {
            gVar = new g();
        }
        this.b = gVar;
        if (map == null) {
            map = new HashMap<>();
        }
        this.a = map;
        if (aVar2 == null) {
            aVar2 = new a(executorService, executorService2, this);
        }
        this.d = aVar2;
        if (lVar == null) {
            lVar = new l();
        }
        this.f = lVar;
        hVar.a((com.bumptech.glide.d.b.b.h.a) this);
    }

    public <T, Z, R> C0041c a(com.bumptech.glide.d.c cVar, int i, int i2, com.bumptech.glide.d.a.c<T> cVar2, com.bumptech.glide.f.b<T, Z> bVar, g<Z> gVar, com.bumptech.glide.d.d.f.c<Z, R> cVar3, k kVar, boolean z, b bVar2, com.bumptech.glide.g.e eVar) {
        com.bumptech.glide.i.h.a();
        long a2 = com.bumptech.glide.i.d.a();
        f a3 = this.b.a(cVar2.b(), cVar, i, i2, bVar.a(), bVar.b(), gVar, bVar.d(), cVar3, bVar.c());
        h b2 = b((com.bumptech.glide.d.c) a3, z);
        if (b2 != null) {
            eVar.a((k<?>) b2);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from cache", a2, a3);
            }
            return null;
        }
        h a4 = a((com.bumptech.glide.d.c) a3, z);
        if (a4 != null) {
            eVar.a((k<?>) a4);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from active resources", a2, a3);
            }
            return null;
        }
        d dVar = (d) this.a.get(a3);
        if (dVar != null) {
            dVar.a(eVar);
            if (Log.isLoggable("Engine", 2)) {
                a("Added to existing load", a2, a3);
            }
            return new C0041c(eVar, dVar);
        }
        d a5 = this.d.a(a3, z);
        i iVar = new i(a5, new a(a3, i, i2, cVar2, bVar, gVar, cVar3, this.g, bVar2, kVar), kVar);
        this.a.put(a3, a5);
        a5.a(eVar);
        a5.a(iVar);
        if (Log.isLoggable("Engine", 2)) {
            a("Started new load", a2, a3);
        }
        return new C0041c(eVar, a5);
    }

    private static void a(String str, long j, com.bumptech.glide.d.c cVar) {
        Log.v("Engine", str + " in " + com.bumptech.glide.i.d.a(j) + "ms, key: " + cVar);
    }

    private h<?> a(com.bumptech.glide.d.c cVar, boolean z) {
        h hVar;
        if (!z) {
            return null;
        }
        WeakReference weakReference = (WeakReference) this.e.get(cVar);
        if (weakReference != null) {
            hVar = (h) weakReference.get();
            if (hVar != null) {
                hVar.e();
            } else {
                this.e.remove(cVar);
            }
        } else {
            hVar = null;
        }
        return hVar;
    }

    private h<?> b(com.bumptech.glide.d.c cVar, boolean z) {
        if (!z) {
            return null;
        }
        h<?> a2 = a(cVar);
        if (a2 == null) {
            return a2;
        }
        a2.e();
        this.e.put(cVar, new e(cVar, a2, b()));
        return a2;
    }

    private h<?> a(com.bumptech.glide.d.c cVar) {
        k a2 = this.c.a(cVar);
        if (a2 == null) {
            return null;
        }
        if (a2 instanceof h) {
            return (h) a2;
        }
        return new h(a2, true);
    }

    public void a(k kVar) {
        com.bumptech.glide.i.h.a();
        if (kVar instanceof h) {
            ((h) kVar).f();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public void a(com.bumptech.glide.d.c cVar, h<?> hVar) {
        com.bumptech.glide.i.h.a();
        if (hVar != null) {
            hVar.a(cVar, this);
            if (hVar.a()) {
                this.e.put(cVar, new e(cVar, hVar, b()));
            }
        }
        this.a.remove(cVar);
    }

    public void a(d dVar, com.bumptech.glide.d.c cVar) {
        com.bumptech.glide.i.h.a();
        if (dVar.equals((d) this.a.get(cVar))) {
            this.a.remove(cVar);
        }
    }

    public void b(k<?> kVar) {
        com.bumptech.glide.i.h.a();
        this.f.a(kVar);
    }

    public void b(com.bumptech.glide.d.c cVar, h hVar) {
        com.bumptech.glide.i.h.a();
        this.e.remove(cVar);
        if (hVar.a()) {
            this.c.b(cVar, hVar);
        } else {
            this.f.a(hVar);
        }
    }

    public void a() {
        this.g.a().a();
    }

    private ReferenceQueue<h<?>> b() {
        if (this.h == null) {
            this.h = new ReferenceQueue<>();
            Looper.myQueue().addIdleHandler(new d(this.e, this.h));
        }
        return this.h;
    }
}
