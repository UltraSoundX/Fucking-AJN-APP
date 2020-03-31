package com.b.a.b;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: LinkedTreeMap */
public final class g<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean f = (!g.class.desiredAssertionStatus());
    private static final Comparator<Comparable> g = new Comparator<Comparable>() {
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> a;
    d<K, V> b;
    int c;
    int d;
    final d<K, V> e;
    private a h;
    private b i;

    /* compiled from: LinkedTreeMap */
    class a extends AbstractSet<Entry<K, V>> {
        a() {
        }

        public int size() {
            return g.this.c;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new c<Entry<K, V>>() {
                {
                    g gVar = g.this;
                }

                /* renamed from: a */
                public Entry<K, V> next() {
                    return b();
                }
            };
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && g.this.a((Entry) obj) != null;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            d a2 = g.this.a((Entry) obj);
            if (a2 == null) {
                return false;
            }
            g.this.a(a2, true);
            return true;
        }

        public void clear() {
            g.this.clear();
        }
    }

    /* compiled from: LinkedTreeMap */
    final class b extends AbstractSet<K> {
        b() {
        }

        public int size() {
            return g.this.c;
        }

        public Iterator<K> iterator() {
            return new c<K>() {
                {
                    g gVar = g.this;
                }

                public K next() {
                    return b().f;
                }
            };
        }

        public boolean contains(Object obj) {
            return g.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return g.this.b(obj) != null;
        }

        public void clear() {
            g.this.clear();
        }
    }

    /* compiled from: LinkedTreeMap */
    private abstract class c<T> implements Iterator<T> {
        d<K, V> b = g.this.e.d;
        d<K, V> c = null;
        int d = g.this.d;

        c() {
        }

        public final boolean hasNext() {
            return this.b != g.this.e;
        }

        /* access modifiers changed from: 0000 */
        public final d<K, V> b() {
            d<K, V> dVar = this.b;
            if (dVar == g.this.e) {
                throw new NoSuchElementException();
            } else if (g.this.d != this.d) {
                throw new ConcurrentModificationException();
            } else {
                this.b = dVar.d;
                this.c = dVar;
                return dVar;
            }
        }

        public final void remove() {
            if (this.c == null) {
                throw new IllegalStateException();
            }
            g.this.a(this.c, true);
            this.c = null;
            this.d = g.this.d;
        }
    }

    /* compiled from: LinkedTreeMap */
    static final class d<K, V> implements Entry<K, V> {
        d<K, V> a;
        d<K, V> b;
        d<K, V> c;
        d<K, V> d;
        d<K, V> e;
        final K f;
        V g;
        int h;

        d() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d<K, V> dVar, K k, d<K, V> dVar2, d<K, V> dVar3) {
            this.a = dVar;
            this.f = k;
            this.h = 1;
            this.d = dVar2;
            this.e = dVar3;
            dVar3.d = this;
            dVar2.e = this;
        }

        public K getKey() {
            return this.f;
        }

        public V getValue() {
            return this.g;
        }

        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.f.equals(entry.getKey())) {
                return false;
            }
            if (this.g == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.g.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f == null ? 0 : this.f.hashCode();
            if (this.g != null) {
                i = this.g.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.f + "=" + this.g;
        }

        public d<K, V> a() {
            for (d<K, V> dVar = this.b; dVar != null; dVar = dVar.b) {
                this = dVar;
            }
            return this;
        }

        public d<K, V> b() {
            for (d<K, V> dVar = this.c; dVar != null; dVar = dVar.c) {
                this = dVar;
            }
            return this;
        }
    }

    public g() {
        this(g);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Comparator<? super K>, code=java.util.Comparator, for r2v0, types: [java.util.Comparator<? super K>, java.util.Comparator] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public g(java.util.Comparator r2) {
        /*
            r1 = this;
            r0 = 0
            r1.<init>()
            r1.c = r0
            r1.d = r0
            com.b.a.b.g$d r0 = new com.b.a.b.g$d
            r0.<init>()
            r1.e = r0
            if (r2 == 0) goto L_0x0014
        L_0x0011:
            r1.a = r2
            return
        L_0x0014:
            java.util.Comparator<java.lang.Comparable> r2 = g
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.g.<init>(java.util.Comparator):void");
    }

    public int size() {
        return this.c;
    }

    public V get(Object obj) {
        d a2 = a(obj);
        if (a2 != null) {
            return a2.g;
        }
        return null;
    }

    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        d a2 = a(k, true);
        V v2 = a2.g;
        a2.g = v;
        return v2;
    }

    public void clear() {
        this.b = null;
        this.c = 0;
        this.d++;
        d<K, V> dVar = this.e;
        dVar.e = dVar;
        dVar.d = dVar;
    }

    public V remove(Object obj) {
        d b2 = b(obj);
        if (b2 != null) {
            return b2.g;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> a(K k, boolean z) {
        d<K, V> dVar;
        int i2;
        d<K, V> dVar2;
        int compare;
        Comparator<? super K> comparator = this.a;
        d<K, V> dVar3 = this.b;
        if (dVar3 != null) {
            Comparable comparable = comparator == g ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    compare = comparable.compareTo(dVar3.f);
                } else {
                    compare = comparator.compare(k, dVar3.f);
                }
                if (compare == 0) {
                    return dVar3;
                }
                d<K, V> dVar4 = compare < 0 ? dVar3.b : dVar3.c;
                if (dVar4 == null) {
                    int i3 = compare;
                    dVar = dVar3;
                    i2 = i3;
                    break;
                }
                dVar3 = dVar4;
            }
        } else {
            dVar = dVar3;
            i2 = 0;
        }
        if (!z) {
            return null;
        }
        d<K, V> dVar5 = this.e;
        if (dVar != null) {
            dVar2 = new d<>(dVar, k, dVar5, dVar5.e);
            if (i2 < 0) {
                dVar.b = dVar2;
            } else {
                dVar.c = dVar2;
            }
            b(dVar, true);
        } else if (comparator != g || (k instanceof Comparable)) {
            dVar2 = new d<>(dVar, k, dVar5, dVar5.e);
            this.b = dVar2;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.c++;
        this.d++;
        return dVar2;
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> a(Object obj) {
        d<K, V> dVar = null;
        if (obj == null) {
            return dVar;
        }
        try {
            return a((K) obj, false);
        } catch (ClassCastException e2) {
            return dVar;
        }
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> a(Entry<?, ?> entry) {
        d<K, V> a2 = a(entry.getKey());
        if (a2 != null && a((Object) a2.g, entry.getValue())) {
            return a2;
        }
        return null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* access modifiers changed from: 0000 */
    public void a(d<K, V> dVar, boolean z) {
        int i2;
        int i3 = 0;
        if (z) {
            dVar.e.d = dVar.d;
            dVar.d.e = dVar.e;
        }
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar.a;
        if (dVar2 == null || dVar3 == null) {
            if (dVar2 != null) {
                a(dVar, dVar2);
                dVar.b = null;
            } else if (dVar3 != null) {
                a(dVar, dVar3);
                dVar.c = null;
            } else {
                a(dVar, null);
            }
            b(dVar4, false);
            this.c--;
            this.d++;
            return;
        }
        d<K, V> a2 = dVar2.h > dVar3.h ? dVar2.b() : dVar3.a();
        a(a2, false);
        d<K, V> dVar5 = dVar.b;
        if (dVar5 != null) {
            i2 = dVar5.h;
            a2.b = dVar5;
            dVar5.a = a2;
            dVar.b = null;
        } else {
            i2 = 0;
        }
        d<K, V> dVar6 = dVar.c;
        if (dVar6 != null) {
            i3 = dVar6.h;
            a2.c = dVar6;
            dVar6.a = a2;
            dVar.c = null;
        }
        a2.h = Math.max(i2, i3) + 1;
        a(dVar, a2);
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> b(Object obj) {
        d<K, V> a2 = a(obj);
        if (a2 != null) {
            a(a2, true);
        }
        return a2;
    }

    private void a(d<K, V> dVar, d<K, V> dVar2) {
        d<K, V> dVar3 = dVar.a;
        dVar.a = null;
        if (dVar2 != null) {
            dVar2.a = dVar3;
        }
        if (dVar3 == null) {
            this.b = dVar2;
        } else if (dVar3.b == dVar) {
            dVar3.b = dVar2;
        } else if (f || dVar3.c == dVar) {
            dVar3.c = dVar2;
        } else {
            throw new AssertionError();
        }
    }

    private void b(d<K, V> dVar, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        while (dVar != null) {
            d<K, V> dVar2 = dVar.b;
            d<K, V> dVar3 = dVar.c;
            int i6 = dVar2 != null ? dVar2.h : 0;
            if (dVar3 != null) {
                i2 = dVar3.h;
            } else {
                i2 = 0;
            }
            int i7 = i6 - i2;
            if (i7 == -2) {
                d<K, V> dVar4 = dVar3.b;
                d<K, V> dVar5 = dVar3.c;
                if (dVar5 != null) {
                    i4 = dVar5.h;
                } else {
                    i4 = 0;
                }
                if (dVar4 != null) {
                    i5 = dVar4.h;
                } else {
                    i5 = 0;
                }
                int i8 = i5 - i4;
                if (i8 == -1 || (i8 == 0 && !z)) {
                    a(dVar);
                } else if (f || i8 == 1) {
                    b(dVar3);
                    a(dVar);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i7 == 2) {
                d<K, V> dVar6 = dVar2.b;
                d<K, V> dVar7 = dVar2.c;
                int i9 = dVar7 != null ? dVar7.h : 0;
                if (dVar6 != null) {
                    i3 = dVar6.h;
                } else {
                    i3 = 0;
                }
                int i10 = i3 - i9;
                if (i10 == 1 || (i10 == 0 && !z)) {
                    b(dVar);
                } else if (f || i10 == -1) {
                    a(dVar2);
                    b(dVar);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i7 == 0) {
                dVar.h = i6 + 1;
                if (z) {
                    return;
                }
            } else if (f || i7 == -1 || i7 == 1) {
                dVar.h = Math.max(i6, i2) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            dVar = dVar.a;
        }
    }

    private void a(d<K, V> dVar) {
        int i2;
        int i3 = 0;
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar3.b;
        d<K, V> dVar5 = dVar3.c;
        dVar.c = dVar4;
        if (dVar4 != null) {
            dVar4.a = dVar;
        }
        a(dVar, dVar3);
        dVar3.b = dVar;
        dVar.a = dVar3;
        if (dVar2 != null) {
            i2 = dVar2.h;
        } else {
            i2 = 0;
        }
        dVar.h = Math.max(i2, dVar4 != null ? dVar4.h : 0) + 1;
        int i4 = dVar.h;
        if (dVar5 != null) {
            i3 = dVar5.h;
        }
        dVar3.h = Math.max(i4, i3) + 1;
    }

    private void b(d<K, V> dVar) {
        int i2;
        int i3 = 0;
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar2.b;
        d<K, V> dVar5 = dVar2.c;
        dVar.b = dVar5;
        if (dVar5 != null) {
            dVar5.a = dVar;
        }
        a(dVar, dVar2);
        dVar2.c = dVar;
        dVar.a = dVar2;
        if (dVar3 != null) {
            i2 = dVar3.h;
        } else {
            i2 = 0;
        }
        dVar.h = Math.max(i2, dVar5 != null ? dVar5.h : 0) + 1;
        int i4 = dVar.h;
        if (dVar4 != null) {
            i3 = dVar4.h;
        }
        dVar2.h = Math.max(i4, i3) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        a aVar = this.h;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a<>();
        this.h = aVar2;
        return aVar2;
    }

    public Set<K> keySet() {
        b bVar = this.i;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b<>();
        this.i = bVar2;
        return bVar2;
    }
}
