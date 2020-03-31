package android.arch.core.b;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* compiled from: SafeIterableMap */
public class b<K, V> implements Iterable<Entry<K, V>> {
    /* access modifiers changed from: private */
    public c<K, V> a;
    private c<K, V> b;
    private WeakHashMap<f<K, V>, Boolean> c = new WeakHashMap<>();
    private int d = 0;

    /* compiled from: SafeIterableMap */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> a(c<K, V> cVar) {
            return cVar.c;
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> b(c<K, V> cVar) {
            return cVar.d;
        }
    }

    /* renamed from: android.arch.core.b.b$b reason: collision with other inner class name */
    /* compiled from: SafeIterableMap */
    private static class C0000b<K, V> extends e<K, V> {
        C0000b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> a(c<K, V> cVar) {
            return cVar.d;
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> b(c<K, V> cVar) {
            return cVar.c;
        }
    }

    /* compiled from: SafeIterableMap */
    static class c<K, V> implements Entry<K, V> {
        final K a;
        final V b;
        c<K, V> c;
        c<K, V> d;

        c(K k, V v) {
            this.a = k;
            this.b = v;
        }

        public K getKey() {
            return this.a;
        }

        public V getValue() {
            return this.b;
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.a + "=" + this.b;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (!this.a.equals(cVar.a) || !this.b.equals(cVar.b)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: SafeIterableMap */
    private class d implements f<K, V>, Iterator<Entry<K, V>> {
        private c<K, V> b;
        private boolean c;

        private d() {
            this.c = true;
        }

        public void a_(c<K, V> cVar) {
            if (cVar == this.b) {
                this.b = this.b.d;
                this.c = this.b == null;
            }
        }

        public boolean hasNext() {
            if (this.c) {
                if (b.this.a != null) {
                    return true;
                }
                return false;
            } else if (this.b == null || this.b.c == null) {
                return false;
            } else {
                return true;
            }
        }

        /* renamed from: a */
        public Entry<K, V> next() {
            if (this.c) {
                this.c = false;
                this.b = b.this.a;
            } else {
                this.b = this.b != null ? this.b.c : null;
            }
            return this.b;
        }
    }

    /* compiled from: SafeIterableMap */
    private static abstract class e<K, V> implements f<K, V>, Iterator<Entry<K, V>> {
        c<K, V> a;
        c<K, V> b;

        /* access modifiers changed from: 0000 */
        public abstract c<K, V> a(c<K, V> cVar);

        /* access modifiers changed from: 0000 */
        public abstract c<K, V> b(c<K, V> cVar);

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.a = cVar2;
            this.b = cVar;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public void a_(c<K, V> cVar) {
            if (this.a == cVar && cVar == this.b) {
                this.b = null;
                this.a = null;
            }
            if (this.a == cVar) {
                this.a = b(this.a);
            }
            if (this.b == cVar) {
                this.b = b();
            }
        }

        private c<K, V> b() {
            if (this.b == this.a || this.a == null) {
                return null;
            }
            return a(this.b);
        }

        /* renamed from: a */
        public Entry<K, V> next() {
            c<K, V> cVar = this.b;
            this.b = b();
            return cVar;
        }
    }

    /* compiled from: SafeIterableMap */
    interface f<K, V> {
        void a_(c<K, V> cVar);
    }

    /* access modifiers changed from: protected */
    public c<K, V> a(K k) {
        c<K, V> cVar = this.a;
        while (cVar != null && !cVar.a.equals(k)) {
            cVar = cVar.c;
        }
        return cVar;
    }

    public V a(K k, V v) {
        c a2 = a(k);
        if (a2 != null) {
            return a2.b;
        }
        b(k, v);
        return null;
    }

    /* access modifiers changed from: protected */
    public c<K, V> b(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.d++;
        if (this.b == null) {
            this.a = cVar;
            this.b = this.a;
        } else {
            this.b.c = cVar;
            cVar.d = this.b;
            this.b = cVar;
        }
        return cVar;
    }

    public V b(K k) {
        c a2 = a(k);
        if (a2 == null) {
            return null;
        }
        this.d--;
        if (!this.c.isEmpty()) {
            for (f a_ : this.c.keySet()) {
                a_.a_(a2);
            }
        }
        if (a2.d != null) {
            a2.d.c = a2.c;
        } else {
            this.a = a2.c;
        }
        if (a2.c != null) {
            a2.c.d = a2.d;
        } else {
            this.b = a2.d;
        }
        a2.c = null;
        a2.d = null;
        return a2.b;
    }

    public int a() {
        return this.d;
    }

    public Iterator<Entry<K, V>> iterator() {
        a aVar = new a(this.a, this.b);
        this.c.put(aVar, Boolean.valueOf(false));
        return aVar;
    }

    public Iterator<Entry<K, V>> b() {
        C0000b bVar = new C0000b(this.b, this.a);
        this.c.put(bVar, Boolean.valueOf(false));
        return bVar;
    }

    public d c() {
        d dVar = new d<>();
        this.c.put(dVar, Boolean.valueOf(false));
        return dVar;
    }

    public Entry<K, V> d() {
        return this.a;
    }

    public Entry<K, V> e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (a() != bVar.a()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = bVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Object next = it2.next();
            if (entry == null && next != null) {
                return false;
            }
            if (entry != null && !entry.equals(next)) {
                return false;
            }
        }
        return !it.hasNext() && !it2.hasNext();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
