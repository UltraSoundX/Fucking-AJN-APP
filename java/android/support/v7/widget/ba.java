package android.support.v7.widget;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.f.c;
import android.support.v7.widget.RecyclerView.v;

/* compiled from: ViewInfoStore */
class ba {
    final ArrayMap<v, a> a = new ArrayMap<>();
    final LongSparseArray<v> b = new LongSparseArray<>();

    /* compiled from: ViewInfoStore */
    static class a {
        static Pool<a> d = new SimplePool(20);
        int a;
        c b;
        c c;

        private a() {
        }

        static a a() {
            a aVar = (a) d.acquire();
            return aVar == null ? new a() : aVar;
        }

        static void a(a aVar) {
            aVar.a = 0;
            aVar.b = null;
            aVar.c = null;
            d.release(aVar);
        }

        static void b() {
            do {
            } while (d.acquire() != null);
        }
    }

    /* compiled from: ViewInfoStore */
    interface b {
        void a(v vVar);

        void a(v vVar, c cVar, c cVar2);

        void b(v vVar, c cVar, c cVar2);

        void c(v vVar, c cVar, c cVar2);
    }

    ba() {
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.a.clear();
        this.b.clear();
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar, c cVar) {
        a aVar = (a) this.a.get(vVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(vVar, aVar);
        }
        aVar.b = cVar;
        aVar.a |= 4;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(v vVar) {
        a aVar = (a) this.a.get(vVar);
        return (aVar == null || (aVar.a & 1) == 0) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public c b(v vVar) {
        return a(vVar, 4);
    }

    /* access modifiers changed from: 0000 */
    public c c(v vVar) {
        return a(vVar, 8);
    }

    private c a(v vVar, int i) {
        c cVar = null;
        int indexOfKey = this.a.indexOfKey(vVar);
        if (indexOfKey >= 0) {
            a aVar = (a) this.a.valueAt(indexOfKey);
            if (!(aVar == null || (aVar.a & i) == 0)) {
                aVar.a &= i ^ -1;
                if (i == 4) {
                    cVar = aVar.b;
                } else if (i == 8) {
                    cVar = aVar.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((aVar.a & 12) == 0) {
                    this.a.removeAt(indexOfKey);
                    a.a(aVar);
                }
            }
        }
        return cVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(long j, v vVar) {
        this.b.put(j, vVar);
    }

    /* access modifiers changed from: 0000 */
    public void b(v vVar, c cVar) {
        a aVar = (a) this.a.get(vVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(vVar, aVar);
        }
        aVar.a |= 2;
        aVar.b = cVar;
    }

    /* access modifiers changed from: 0000 */
    public boolean d(v vVar) {
        a aVar = (a) this.a.get(vVar);
        return (aVar == null || (aVar.a & 4) == 0) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public v a(long j) {
        return (v) this.b.get(j);
    }

    /* access modifiers changed from: 0000 */
    public void c(v vVar, c cVar) {
        a aVar = (a) this.a.get(vVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(vVar, aVar);
        }
        aVar.c = cVar;
        aVar.a |= 8;
    }

    /* access modifiers changed from: 0000 */
    public void e(v vVar) {
        a aVar = (a) this.a.get(vVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(vVar, aVar);
        }
        aVar.a |= 1;
    }

    /* access modifiers changed from: 0000 */
    public void f(v vVar) {
        a aVar = (a) this.a.get(vVar);
        if (aVar != null) {
            aVar.a &= -2;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            v vVar = (v) this.a.keyAt(size);
            a aVar = (a) this.a.removeAt(size);
            if ((aVar.a & 3) == 3) {
                bVar.a(vVar);
            } else if ((aVar.a & 1) != 0) {
                if (aVar.b == null) {
                    bVar.a(vVar);
                } else {
                    bVar.a(vVar, aVar.b, aVar.c);
                }
            } else if ((aVar.a & 14) == 14) {
                bVar.b(vVar, aVar.b, aVar.c);
            } else if ((aVar.a & 12) == 12) {
                bVar.c(vVar, aVar.b, aVar.c);
            } else if ((aVar.a & 4) != 0) {
                bVar.a(vVar, aVar.b, null);
            } else if ((aVar.a & 8) != 0) {
                bVar.b(vVar, aVar.b, aVar.c);
            } else if ((aVar.a & 2) != 0) {
            }
            a.a(aVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public void g(v vVar) {
        int size = this.b.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (vVar == this.b.valueAt(size)) {
                this.b.removeAt(size);
                break;
            } else {
                size--;
            }
        }
        a aVar = (a) this.a.remove(vVar);
        if (aVar != null) {
            a.a(aVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        a.b();
    }

    public void h(v vVar) {
        f(vVar);
    }
}
