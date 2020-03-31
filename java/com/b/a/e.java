package com.b.a;

import com.b.a.b.a.b;
import com.b.a.b.a.g;
import com.b.a.b.a.h;
import com.b.a.b.a.i;
import com.b.a.b.a.j;
import com.b.a.b.a.k;
import com.b.a.b.a.n;
import com.b.a.b.c;
import com.b.a.b.d;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: Gson */
public final class e {
    private static final com.b.a.c.a<?> a = new com.b.a.c.a<Object>() {
    };
    private final ThreadLocal<Map<com.b.a.c.a<?>, a<?>>> b;
    private final Map<com.b.a.c.a<?>, t<?>> c;
    private final List<u> d;
    private final c e;
    private final d f;
    private final d g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final com.b.a.b.a.d m;

    /* compiled from: Gson */
    static class a<T> extends t<T> {
        private t<T> a;

        a() {
        }

        public void a(t<T> tVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = tVar;
        }

        public T b(com.b.a.d.a aVar) throws IOException {
            if (this.a != null) {
                return this.a.b(aVar);
            }
            throw new IllegalStateException();
        }

        public void a(com.b.a.d.c cVar, T t) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(cVar, t);
        }
    }

    public e() {
        this(d.a, c.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, s.DEFAULT, Collections.emptyList());
    }

    e(d dVar, d dVar2, Map<Type, f<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, s sVar, List<u> list) {
        this.b = new ThreadLocal<>();
        this.c = new ConcurrentHashMap();
        this.e = new c(map);
        this.f = dVar;
        this.g = dVar2;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(n.Y);
        arrayList.add(h.a);
        arrayList.add(dVar);
        arrayList.addAll(list);
        arrayList.add(n.D);
        arrayList.add(n.m);
        arrayList.add(n.g);
        arrayList.add(n.i);
        arrayList.add(n.k);
        t a2 = a(sVar);
        arrayList.add(n.a(Long.TYPE, Long.class, a2));
        arrayList.add(n.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(n.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(n.x);
        arrayList.add(n.o);
        arrayList.add(n.f366q);
        arrayList.add(n.a(AtomicLong.class, a(a2)));
        arrayList.add(n.a(AtomicLongArray.class, b(a2)));
        arrayList.add(n.s);
        arrayList.add(n.z);
        arrayList.add(n.F);
        arrayList.add(n.H);
        arrayList.add(n.a(BigDecimal.class, n.B));
        arrayList.add(n.a(BigInteger.class, n.C));
        arrayList.add(n.J);
        arrayList.add(n.L);
        arrayList.add(n.P);
        arrayList.add(n.R);
        arrayList.add(n.W);
        arrayList.add(n.N);
        arrayList.add(n.d);
        arrayList.add(com.b.a.b.a.c.a);
        arrayList.add(n.U);
        arrayList.add(k.a);
        arrayList.add(j.a);
        arrayList.add(n.S);
        arrayList.add(com.b.a.b.a.a.a);
        arrayList.add(n.b);
        arrayList.add(new b(this.e));
        arrayList.add(new g(this.e, z2));
        this.m = new com.b.a.b.a.d(this.e);
        arrayList.add(this.m);
        arrayList.add(n.Z);
        arrayList.add(new i(this.e, dVar2, dVar, this.m));
        this.d = Collections.unmodifiableList(arrayList);
    }

    private t<Number> a(boolean z) {
        if (z) {
            return n.v;
        }
        return new t<Number>() {
            /* renamed from: a */
            public Double b(com.b.a.d.a aVar) throws IOException {
                if (aVar.f() != com.b.a.d.b.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.b.a.d.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.f();
                    return;
                }
                e.a(number.doubleValue());
                cVar.a(number);
            }
        };
    }

    private t<Number> b(boolean z) {
        if (z) {
            return n.u;
        }
        return new t<Number>() {
            /* renamed from: a */
            public Float b(com.b.a.d.a aVar) throws IOException {
                if (aVar.f() != com.b.a.d.b.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.b.a.d.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.f();
                    return;
                }
                e.a((double) number.floatValue());
                cVar.a(number);
            }
        };
    }

    static void a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static t<Number> a(s sVar) {
        if (sVar == s.DEFAULT) {
            return n.t;
        }
        return new t<Number>() {
            /* renamed from: a */
            public Number b(com.b.a.d.a aVar) throws IOException {
                if (aVar.f() != com.b.a.d.b.NULL) {
                    return Long.valueOf(aVar.l());
                }
                aVar.j();
                return null;
            }

            public void a(com.b.a.d.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.f();
                } else {
                    cVar.b(number.toString());
                }
            }
        };
    }

    private static t<AtomicLong> a(final t<Number> tVar) {
        return new t<AtomicLong>() {
            public void a(com.b.a.d.c cVar, AtomicLong atomicLong) throws IOException {
                tVar.a(cVar, Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong b(com.b.a.d.a aVar) throws IOException {
                return new AtomicLong(((Number) tVar.b(aVar)).longValue());
            }
        }.a();
    }

    private static t<AtomicLongArray> b(final t<Number> tVar) {
        return new t<AtomicLongArray>() {
            public void a(com.b.a.d.c cVar, AtomicLongArray atomicLongArray) throws IOException {
                cVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    tVar.a(cVar, Long.valueOf(atomicLongArray.get(i)));
                }
                cVar.c();
            }

            /* renamed from: a */
            public AtomicLongArray b(com.b.a.d.a aVar) throws IOException {
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(Long.valueOf(((Number) tVar.b(aVar)).longValue()));
                }
                aVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.a();
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.b.a.c.a<T>, code=com.b.a.c.a, for r6v0, types: [com.b.a.c.a, com.b.a.c.a<T>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.b.a.t<T> a(com.b.a.c.a r6) {
        /*
            r5 = this;
            java.util.Map<com.b.a.c.a<?>, com.b.a.t<?>> r1 = r5.c
            if (r6 != 0) goto L_0x000f
            com.b.a.c.a<?> r0 = a
        L_0x0006:
            java.lang.Object r0 = r1.get(r0)
            com.b.a.t r0 = (com.b.a.t) r0
            if (r0 == 0) goto L_0x0011
        L_0x000e:
            return r0
        L_0x000f:
            r0 = r6
            goto L_0x0006
        L_0x0011:
            java.lang.ThreadLocal<java.util.Map<com.b.a.c.a<?>, com.b.a.e$a<?>>> r0 = r5.b
            java.lang.Object r0 = r0.get()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            if (r0 != 0) goto L_0x0089
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.ThreadLocal<java.util.Map<com.b.a.c.a<?>, com.b.a.e$a<?>>> r0 = r5.b
            r0.set(r1)
            r0 = 1
            r2 = r1
            r1 = r0
        L_0x0029:
            java.lang.Object r0 = r2.get(r6)
            com.b.a.e$a r0 = (com.b.a.e.a) r0
            if (r0 != 0) goto L_0x000e
            com.b.a.e$a r3 = new com.b.a.e$a     // Catch:{ all -> 0x007d }
            r3.<init>()     // Catch:{ all -> 0x007d }
            r2.put(r6, r3)     // Catch:{ all -> 0x007d }
            java.util.List<com.b.a.u> r0 = r5.d     // Catch:{ all -> 0x007d }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x007d }
        L_0x003f:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x007d }
            if (r0 == 0) goto L_0x0064
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x007d }
            com.b.a.u r0 = (com.b.a.u) r0     // Catch:{ all -> 0x007d }
            com.b.a.t r0 = r0.a(r5, r6)     // Catch:{ all -> 0x007d }
            if (r0 == 0) goto L_0x003f
            r3.a(r0)     // Catch:{ all -> 0x007d }
            java.util.Map<com.b.a.c.a<?>, com.b.a.t<?>> r3 = r5.c     // Catch:{ all -> 0x007d }
            r3.put(r6, r0)     // Catch:{ all -> 0x007d }
            r2.remove(r6)
            if (r1 == 0) goto L_0x000e
            java.lang.ThreadLocal<java.util.Map<com.b.a.c.a<?>, com.b.a.e$a<?>>> r1 = r5.b
            r1.remove()
            goto L_0x000e
        L_0x0064:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x007d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            r3.<init>()     // Catch:{ all -> 0x007d }
            java.lang.String r4 = "GSON cannot handle "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x007d }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x007d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x007d }
            r0.<init>(r3)     // Catch:{ all -> 0x007d }
            throw r0     // Catch:{ all -> 0x007d }
        L_0x007d:
            r0 = move-exception
            r2.remove(r6)
            if (r1 == 0) goto L_0x0088
            java.lang.ThreadLocal<java.util.Map<com.b.a.c.a<?>, com.b.a.e$a<?>>> r1 = r5.b
            r1.remove()
        L_0x0088:
            throw r0
        L_0x0089:
            r2 = r0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.e.a(com.b.a.c.a):com.b.a.t");
    }

    public <T> t<T> a(u uVar, com.b.a.c.a<T> aVar) {
        if (!this.d.contains(uVar)) {
            uVar = this.m;
        }
        boolean z = false;
        for (u uVar2 : this.d) {
            if (z) {
                t<T> a2 = uVar2.a(this, aVar);
                if (a2 != null) {
                    return a2;
                }
            } else if (uVar2 == uVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public <T> t<T> a(Class<T> cls) {
        return a(com.b.a.c.a.b(cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a((j) l.a);
        }
        return a(obj, (Type) obj.getClass());
    }

    public String a(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        a(obj, type, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) throws k {
        try {
            a(obj, type, a(com.b.a.b.j.a(appendable)));
        } catch (IOException e2) {
            throw new k((Throwable) e2);
        }
    }

    public void a(Object obj, Type type, com.b.a.d.c cVar) throws k {
        t a2 = a(com.b.a.c.a.a(type));
        boolean g2 = cVar.g();
        cVar.b(true);
        boolean h2 = cVar.h();
        cVar.c(this.i);
        boolean i2 = cVar.i();
        cVar.d(this.h);
        try {
            a2.a(cVar, obj);
            cVar.b(g2);
            cVar.c(h2);
            cVar.d(i2);
        } catch (IOException e2) {
            throw new k((Throwable) e2);
        } catch (Throwable th) {
            cVar.b(g2);
            cVar.c(h2);
            cVar.d(i2);
            throw th;
        }
    }

    public String a(j jVar) {
        StringWriter stringWriter = new StringWriter();
        a(jVar, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public void a(j jVar, Appendable appendable) throws k {
        try {
            a(jVar, a(com.b.a.b.j.a(appendable)));
        } catch (IOException e2) {
            throw new k((Throwable) e2);
        }
    }

    public com.b.a.d.c a(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        com.b.a.d.c cVar = new com.b.a.d.c(writer);
        if (this.k) {
            cVar.c("  ");
        }
        cVar.d(this.h);
        return cVar;
    }

    public com.b.a.d.a a(Reader reader) {
        com.b.a.d.a aVar = new com.b.a.d.a(reader);
        aVar.a(this.l);
        return aVar;
    }

    public void a(j jVar, com.b.a.d.c cVar) throws k {
        boolean g2 = cVar.g();
        cVar.b(true);
        boolean h2 = cVar.h();
        cVar.c(this.i);
        boolean i2 = cVar.i();
        cVar.d(this.h);
        try {
            com.b.a.b.j.a(jVar, cVar);
            cVar.b(g2);
            cVar.c(h2);
            cVar.d(i2);
        } catch (IOException e2) {
            throw new k((Throwable) e2);
        } catch (Throwable th) {
            cVar.b(g2);
            cVar.c(h2);
            cVar.d(i2);
            throw th;
        }
    }

    public <T> T a(String str, Class<T> cls) throws r {
        return com.b.a.b.i.a(cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) throws r {
        if (str == null) {
            return null;
        }
        return a((Reader) new StringReader(str), type);
    }

    public <T> T a(Reader reader, Type type) throws k, r {
        com.b.a.d.a a2 = a(reader);
        T a3 = a(a2, type);
        a((Object) a3, a2);
        return a3;
    }

    private static void a(Object obj, com.b.a.d.a aVar) {
        if (obj != null) {
            try {
                if (aVar.f() != com.b.a.d.b.END_DOCUMENT) {
                    throw new k("JSON document was not fully consumed.");
                }
            } catch (com.b.a.d.d e2) {
                throw new r((Throwable) e2);
            } catch (IOException e3) {
                throw new k((Throwable) e3);
            }
        }
    }

    public <T> T a(com.b.a.d.a aVar, Type type) throws k, r {
        boolean z = true;
        boolean q2 = aVar.q();
        aVar.a(true);
        try {
            aVar.f();
            z = false;
            T b2 = a(com.b.a.c.a.a(type)).b(aVar);
            aVar.a(q2);
            return b2;
        } catch (EOFException e2) {
            if (z) {
                aVar.a(q2);
                return null;
            }
            throw new r((Throwable) e2);
        } catch (IllegalStateException e3) {
            throw new r((Throwable) e3);
        } catch (IOException e4) {
            throw new r((Throwable) e4);
        } catch (Throwable th) {
            aVar.a(q2);
            throw th;
        }
    }

    public <T> T a(j jVar, Class<T> cls) throws r {
        return com.b.a.b.i.a(cls).cast(a(jVar, (Type) cls));
    }

    public <T> T a(j jVar, Type type) throws r {
        if (jVar == null) {
            return null;
        }
        return a((com.b.a.d.a) new com.b.a.b.a.e(jVar), type);
    }

    public String toString() {
        return "{serializeNulls:" + this.h + "factories:" + this.d + ",instanceCreators:" + this.e + "}";
    }
}
