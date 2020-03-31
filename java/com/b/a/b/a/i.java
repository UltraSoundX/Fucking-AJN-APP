package com.b.a.b.a;

import com.b.a.b.c;
import com.b.a.b.h;
import com.b.a.d;
import com.b.a.e;
import com.b.a.r;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ReflectiveTypeAdapterFactory */
public final class i implements u {
    private final c a;
    private final d b;
    private final com.b.a.b.d c;
    private final d d;

    /* compiled from: ReflectiveTypeAdapterFactory */
    public static final class a<T> extends t<T> {
        private final h<T> a;
        private final Map<String, b> b;

        a(h<T> hVar, Map<String, b> map) {
            this.a = hVar;
            this.b = map;
        }

        public T b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == com.b.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            Object a2 = this.a.a();
            try {
                aVar.c();
                while (aVar.e()) {
                    b bVar = (b) this.b.get(aVar.g());
                    if (bVar == null || !bVar.j) {
                        aVar.n();
                    } else {
                        bVar.a(aVar, a2);
                    }
                }
                aVar.d();
                return a2;
            } catch (IllegalStateException e) {
                throw new r((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void a(com.b.a.d.c cVar, T t) throws IOException {
            if (t == null) {
                cVar.f();
                return;
            }
            cVar.d();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.a(t)) {
                        cVar.a(bVar.h);
                        bVar.a(cVar, (Object) t);
                    }
                }
                cVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory */
    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        /* access modifiers changed from: 0000 */
        public abstract void a(com.b.a.d.a aVar, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: 0000 */
        public abstract void a(com.b.a.d.c cVar, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: 0000 */
        public abstract boolean a(Object obj) throws IOException, IllegalAccessException;

        protected b(String str, boolean z, boolean z2) {
            this.h = str;
            this.i = z;
            this.j = z2;
        }
    }

    public i(c cVar, d dVar, com.b.a.b.d dVar2, d dVar3) {
        this.a = cVar;
        this.b = dVar;
        this.c = dVar2;
        this.d = dVar3;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, com.b.a.b.d dVar) {
        return !dVar.a(field.getType(), z) && !dVar.a(field, z);
    }

    private List<String> a(Field field) {
        com.b.a.a.c cVar = (com.b.a.a.c) field.getAnnotation(com.b.a.a.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.b.a(field));
        }
        String a2 = cVar.a();
        String[] b2 = cVar.b();
        if (b2.length == 0) {
            return Collections.singletonList(a2);
        }
        ArrayList arrayList = new ArrayList(b2.length + 1);
        arrayList.add(a2);
        for (String add : b2) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
        Class a2 = aVar.a();
        if (!Object.class.isAssignableFrom(a2)) {
            return null;
        }
        return new a(this.a.a(aVar), a(eVar, aVar, a2));
    }

    private b a(e eVar, Field field, String str, com.b.a.c.a<?> aVar, boolean z, boolean z2) {
        final boolean a2 = com.b.a.b.i.a((Type) aVar.a());
        com.b.a.a.b bVar = (com.b.a.a.b) field.getAnnotation(com.b.a.a.b.class);
        final t tVar = null;
        if (bVar != null) {
            tVar = this.d.a(this.a, eVar, aVar, bVar);
        }
        final boolean z3 = tVar != null;
        if (tVar == null) {
            tVar = eVar.a(aVar);
        }
        final Field field2 = field;
        final e eVar2 = eVar;
        final com.b.a.c.a<?> aVar2 = aVar;
        return new b(str, z, z2) {
            /* access modifiers changed from: 0000 */
            public void a(com.b.a.d.c cVar, Object obj) throws IOException, IllegalAccessException {
                t mVar;
                Object obj2 = field2.get(obj);
                if (z3) {
                    mVar = tVar;
                } else {
                    mVar = new m(eVar2, tVar, aVar2.b());
                }
                mVar.a(cVar, obj2);
            }

            /* access modifiers changed from: 0000 */
            public void a(com.b.a.d.a aVar, Object obj) throws IOException, IllegalAccessException {
                Object b2 = tVar.b(aVar);
                if (b2 != null || !a2) {
                    field2.set(obj, b2);
                }
            }

            public boolean a(Object obj) throws IOException, IllegalAccessException {
                if (this.i && field2.get(obj) != obj) {
                    return true;
                }
                return false;
            }
        };
    }

    private Map<String, b> a(e eVar, com.b.a.c.a<?> aVar, Class<?> cls) {
        Field[] declaredFields;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b2 = aVar.b();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean a2 = a(field, true);
                boolean a3 = a(field, false);
                if (a2 || a3) {
                    field.setAccessible(true);
                    Type a4 = com.b.a.b.b.a(aVar.b(), cls, field.getGenericType());
                    List a5 = a(field);
                    b bVar = null;
                    int i = 0;
                    while (i < a5.size()) {
                        String str = (String) a5.get(i);
                        if (i != 0) {
                            a2 = false;
                        }
                        b bVar2 = (b) linkedHashMap.put(str, a(eVar, field, str, com.b.a.c.a.a(a4), a2, a3));
                        if (bVar != null) {
                            bVar2 = bVar;
                        }
                        i++;
                        bVar = bVar2;
                    }
                    if (bVar != null) {
                        throw new IllegalArgumentException(b2 + " declares multiple JSON fields named " + bVar.h);
                    }
                }
            }
            aVar = com.b.a.c.a.a(com.b.a.b.b.a(aVar.b(), cls, cls.getGenericSuperclass()));
            cls = aVar.a();
        }
        return linkedHashMap;
    }
}
