package com.b.a.b;

import com.b.a.a;
import com.b.a.b;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* compiled from: Excluder */
public final class d implements u, Cloneable {
    public static final d a = new d();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<a> f = Collections.emptyList();
    private List<a> g = Collections.emptyList();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public d clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
        Class a2 = aVar.a();
        final boolean a3 = a(a2, true);
        final boolean a4 = a(a2, false);
        if (!a3 && !a4) {
            return null;
        }
        final e eVar2 = eVar;
        final com.b.a.c.a<T> aVar2 = aVar;
        return new t<T>() {
            private t<T> f;

            public T b(com.b.a.d.a aVar) throws IOException {
                if (!a4) {
                    return b().b(aVar);
                }
                aVar.n();
                return null;
            }

            public void a(c cVar, T t) throws IOException {
                if (a3) {
                    cVar.f();
                } else {
                    b().a(cVar, t);
                }
            }

            private t<T> b() {
                t<T> tVar = this.f;
                if (tVar != null) {
                    return tVar;
                }
                t<T> a2 = eVar2.a((u) d.this, aVar2);
                this.f = a2;
                return a2;
            }
        };
    }

    public boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.b != -1.0d && !a((com.b.a.a.d) field.getAnnotation(com.b.a.a.d.class), (com.b.a.a.e) field.getAnnotation(com.b.a.a.e.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            com.b.a.a.a aVar = (com.b.a.a.a) field.getAnnotation(com.b.a.a.a.class);
            if (aVar == null || (!z ? !aVar.b() : !aVar.a())) {
                return true;
            }
        }
        if (!this.d && b(field.getType())) {
            return true;
        }
        if (a(field.getType())) {
            return true;
        }
        List<a> list = z ? this.f : this.g;
        if (!list.isEmpty()) {
            b bVar = new b(field);
            for (a a2 : list) {
                if (a2.a(bVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((com.b.a.a.d) cls.getAnnotation(com.b.a.a.d.class), (com.b.a.a.e) cls.getAnnotation(com.b.a.a.e.class))) {
            return true;
        }
        if (!this.d && b(cls)) {
            return true;
        }
        if (a(cls)) {
            return true;
        }
        for (a a2 : z ? this.f : this.g) {
            if (a2.a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(com.b.a.a.d dVar, com.b.a.a.e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(com.b.a.a.d dVar) {
        if (dVar == null || dVar.a() <= this.b) {
            return true;
        }
        return false;
    }

    private boolean a(com.b.a.a.e eVar) {
        if (eVar == null || eVar.a() > this.b) {
            return true;
        }
        return false;
    }
}
