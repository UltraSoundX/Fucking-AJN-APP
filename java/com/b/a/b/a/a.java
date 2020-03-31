package com.b.a.b.a;

import com.b.a.b.b;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* compiled from: ArrayTypeAdapter */
public final class a<E> extends t<Object> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
            Type b = aVar.b();
            if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
                return null;
            }
            Type g = b.g(b);
            return new a(eVar, eVar.a(com.b.a.c.a.a(g)), b.e(g));
        }
    };
    private final Class<E> b;
    private final t<E> c;

    public a(e eVar, t<E> tVar, Class<E> cls) {
        this.c = new m(eVar, tVar, cls);
        this.b = cls;
    }

    public Object b(com.b.a.d.a aVar) throws IOException {
        if (aVar.f() == com.b.a.d.b.NULL) {
            aVar.j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.e()) {
            arrayList.add(this.c.b(aVar));
        }
        aVar.b();
        Object newInstance = Array.newInstance(this.b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void a(c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.f();
            return;
        }
        cVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.a(cVar, Array.get(obj, i));
        }
        cVar.c();
    }
}
