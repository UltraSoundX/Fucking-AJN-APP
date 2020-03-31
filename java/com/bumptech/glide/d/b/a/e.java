package com.bumptech.glide.d.b.a;

import com.bumptech.glide.d.b.a.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: GroupedLinkedMap */
class e<K extends h, V> {
    private final a<K, V> a = new a<>();
    private final Map<K, a<K, V>> b = new HashMap();

    /* compiled from: GroupedLinkedMap */
    private static class a<K, V> {
        a<K, V> a;
        a<K, V> b;
        /* access modifiers changed from: private */
        public final K c;
        private List<V> d;

        public a() {
            this(null);
        }

        public a(K k) {
            this.b = this;
            this.a = this;
            this.c = k;
        }

        public V a() {
            int b2 = b();
            if (b2 > 0) {
                return this.d.remove(b2 - 1);
            }
            return null;
        }

        public int b() {
            if (this.d != null) {
                return this.d.size();
            }
            return 0;
        }

        public void a(V v) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(v);
        }
    }

    e() {
    }

    public void a(K k, V v) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            b(aVar);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a(v);
    }

    public V a(K k) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        a(aVar);
        return aVar.a();
    }

    public V a() {
        a<K, V> aVar = this.a.b;
        while (true) {
            a<K, V> aVar2 = aVar;
            if (aVar2.equals(this.a)) {
                return null;
            }
            V a2 = aVar2.a();
            if (a2 != null) {
                return a2;
            }
            d(aVar2);
            this.b.remove(aVar2.c);
            ((h) aVar2.c).a();
            aVar = aVar2.b;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (a<K, V> aVar = this.a.a; !aVar.equals(this.a); aVar = aVar.a) {
            z = true;
            sb.append('{').append(aVar.c).append(':').append(aVar.b()).append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append(" )").toString();
    }

    private void a(a<K, V> aVar) {
        d(aVar);
        aVar.b = this.a;
        aVar.a = this.a.a;
        c(aVar);
    }

    private void b(a<K, V> aVar) {
        d(aVar);
        aVar.b = this.a.b;
        aVar.a = this.a;
        c(aVar);
    }

    private static <K, V> void c(a<K, V> aVar) {
        aVar.a.b = aVar;
        aVar.b.a = aVar;
    }

    private static <K, V> void d(a<K, V> aVar) {
        aVar.b.a = aVar.a;
        aVar.a.b = aVar.b;
    }
}
