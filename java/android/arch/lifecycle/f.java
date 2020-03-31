package android.arch.lifecycle;

import android.arch.lifecycle.c.b;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: LifecycleRegistry */
public class f extends c {
    private android.arch.core.b.a<d, a> a = new android.arch.core.b.a<>();
    private b b;
    private final WeakReference<e> c;
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<b> g = new ArrayList<>();

    /* compiled from: LifecycleRegistry */
    static class a {
        b a;
        GenericLifecycleObserver b;

        a(d dVar, b bVar) {
            this.b = h.a((Object) dVar);
            this.a = bVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(e eVar, android.arch.lifecycle.c.a aVar) {
            b b2 = f.b(aVar);
            this.a = f.a(this.a, b2);
            this.b.a(eVar, aVar);
            this.a = b2;
        }
    }

    public f(e eVar) {
        this.c = new WeakReference<>(eVar);
        this.b = b.INITIALIZED;
    }

    public void a(b bVar) {
        b(bVar);
    }

    public void a(android.arch.lifecycle.c.a aVar) {
        b(b(aVar));
    }

    private void b(b bVar) {
        if (this.b != bVar) {
            this.b = bVar;
            if (this.e || this.d != 0) {
                this.f = true;
                return;
            }
            this.e = true;
            d();
            this.e = false;
        }
    }

    private boolean b() {
        if (this.a.a() == 0) {
            return true;
        }
        b bVar = ((a) this.a.d().getValue()).a;
        b bVar2 = ((a) this.a.e().getValue()).a;
        return bVar == bVar2 && this.b == bVar2;
    }

    private b c(d dVar) {
        b bVar;
        b bVar2;
        Entry d2 = this.a.d(dVar);
        if (d2 != null) {
            bVar = ((a) d2.getValue()).a;
        } else {
            bVar = null;
        }
        if (!this.g.isEmpty()) {
            bVar2 = (b) this.g.get(this.g.size() - 1);
        } else {
            bVar2 = null;
        }
        return a(a(this.b, bVar), bVar2);
    }

    public void a(d dVar) {
        a aVar = new a(dVar, this.b == b.DESTROYED ? b.DESTROYED : b.INITIALIZED);
        if (((a) this.a.a(dVar, aVar)) == null) {
            e eVar = (e) this.c.get();
            if (eVar != null) {
                boolean z = this.d != 0 || this.e;
                b c2 = c(dVar);
                this.d++;
                while (aVar.a.compareTo(c2) < 0 && this.a.c(dVar)) {
                    c(aVar.a);
                    aVar.a(eVar, e(aVar.a));
                    c();
                    c2 = c(dVar);
                }
                if (!z) {
                    d();
                }
                this.d--;
            }
        }
    }

    private void c() {
        this.g.remove(this.g.size() - 1);
    }

    private void c(b bVar) {
        this.g.add(bVar);
    }

    public void b(d dVar) {
        this.a.b(dVar);
    }

    public b a() {
        return this.b;
    }

    static b b(android.arch.lifecycle.c.a aVar) {
        switch (aVar) {
            case ON_CREATE:
            case ON_STOP:
                return b.CREATED;
            case ON_START:
            case ON_PAUSE:
                return b.STARTED;
            case ON_RESUME:
                return b.RESUMED;
            case ON_DESTROY:
                return b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private static android.arch.lifecycle.c.a d(b bVar) {
        switch (bVar) {
            case INITIALIZED:
                throw new IllegalArgumentException();
            case CREATED:
                return android.arch.lifecycle.c.a.ON_DESTROY;
            case STARTED:
                return android.arch.lifecycle.c.a.ON_STOP;
            case RESUMED:
                return android.arch.lifecycle.c.a.ON_PAUSE;
            case DESTROYED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + bVar);
        }
    }

    private static android.arch.lifecycle.c.a e(b bVar) {
        switch (bVar) {
            case INITIALIZED:
            case DESTROYED:
                return android.arch.lifecycle.c.a.ON_CREATE;
            case CREATED:
                return android.arch.lifecycle.c.a.ON_START;
            case STARTED:
                return android.arch.lifecycle.c.a.ON_RESUME;
            case RESUMED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + bVar);
        }
    }

    private void a(e eVar) {
        d c2 = this.a.c();
        while (c2.hasNext() && !this.f) {
            Entry entry = (Entry) c2.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) < 0 && !this.f && this.a.c(entry.getKey())) {
                c(aVar.a);
                aVar.a(eVar, e(aVar.a));
                c();
            }
        }
    }

    private void b(e eVar) {
        Iterator b2 = this.a.b();
        while (b2.hasNext() && !this.f) {
            Entry entry = (Entry) b2.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) > 0 && !this.f && this.a.c(entry.getKey())) {
                android.arch.lifecycle.c.a d2 = d(aVar.a);
                c(b(d2));
                aVar.a(eVar, d2);
                c();
            }
        }
    }

    private void d() {
        e eVar = (e) this.c.get();
        if (eVar == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!b()) {
            this.f = false;
            if (this.b.compareTo(((a) this.a.d().getValue()).a) < 0) {
                b(eVar);
            }
            Entry e2 = this.a.e();
            if (!this.f && e2 != null && this.b.compareTo(((a) e2.getValue()).a) > 0) {
                a(eVar);
            }
        }
        this.f = false;
    }

    static b a(b bVar, b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }
}
