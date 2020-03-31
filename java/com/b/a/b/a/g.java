package com.b.a.b.a;

import com.b.a.b.c;
import com.b.a.b.h;
import com.b.a.d.b;
import com.b.a.e;
import com.b.a.j;
import com.b.a.o;
import com.b.a.r;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: MapTypeAdapterFactory */
public final class g implements u {
    final boolean a;
    private final c b;

    /* compiled from: MapTypeAdapterFactory */
    private final class a<K, V> extends t<Map<K, V>> {
        private final t<K> b;
        private final t<V> c;
        private final h<? extends Map<K, V>> d;

        public a(e eVar, Type type, t<K> tVar, Type type2, t<V> tVar2, h<? extends Map<K, V>> hVar) {
            this.b = new m(eVar, tVar, type);
            this.c = new m(eVar, tVar2, type2);
            this.d = hVar;
        }

        /* renamed from: a */
        public Map<K, V> b(com.b.a.d.a aVar) throws IOException {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            if (f == b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    Object b2 = this.b.b(aVar);
                    if (map.put(b2, this.c.b(aVar)) != null) {
                        throw new r("duplicate key: " + b2);
                    }
                    aVar.b();
                }
                aVar.b();
                return map;
            }
            aVar.c();
            while (aVar.e()) {
                com.b.a.b.e.a.a(aVar);
                Object b3 = this.b.b(aVar);
                if (map.put(b3, this.c.b(aVar)) != null) {
                    throw new r("duplicate key: " + b3);
                }
            }
            aVar.d();
            return map;
        }

        public void a(com.b.a.d.c cVar, Map<K, V> map) throws IOException {
            boolean z;
            int i = 0;
            if (map == null) {
                cVar.f();
            } else if (!g.this.a) {
                cVar.d();
                for (Entry entry : map.entrySet()) {
                    cVar.a(String.valueOf(entry.getKey()));
                    this.c.a(cVar, entry.getValue());
                }
                cVar.e();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                boolean z2 = false;
                for (Entry entry2 : map.entrySet()) {
                    j a2 = this.b.a(entry2.getKey());
                    arrayList.add(a2);
                    arrayList2.add(entry2.getValue());
                    if (a2.g() || a2.h()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = z | z2;
                }
                if (z2) {
                    cVar.b();
                    while (i < arrayList.size()) {
                        cVar.b();
                        com.b.a.b.j.a((j) arrayList.get(i), cVar);
                        this.c.a(cVar, arrayList2.get(i));
                        cVar.c();
                        i++;
                    }
                    cVar.c();
                    return;
                }
                cVar.d();
                while (i < arrayList.size()) {
                    cVar.a(a((j) arrayList.get(i)));
                    this.c.a(cVar, arrayList2.get(i));
                    i++;
                }
                cVar.e();
            }
        }

        private String a(j jVar) {
            if (jVar.i()) {
                o m = jVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (jVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public g(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
        Type b2 = aVar.b();
        if (!Map.class.isAssignableFrom(aVar.a())) {
            return null;
        }
        Type[] b3 = com.b.a.b.b.b(b2, com.b.a.b.b.e(b2));
        t a2 = a(eVar, b3[0]);
        t a3 = eVar.a(com.b.a.c.a.a(b3[1]));
        h a4 = this.b.a(aVar);
        return new a(eVar, b3[0], a2, b3[1], a3, a4);
    }

    private t<?> a(e eVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return n.f;
        }
        return eVar.a(com.b.a.c.a.a(type));
    }
}
