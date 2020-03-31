package com.b.a.b.a;

import com.b.a.b.g;
import com.b.a.c.a;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: ObjectTypeAdapter */
public final class h extends t<Object> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Object.class) {
                return new h(eVar);
            }
            return null;
        }
    };
    private final e b;

    h(e eVar) {
        this.b = eVar;
    }

    public Object b(com.b.a.d.a aVar) throws IOException {
        switch (aVar.f()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(b(aVar));
                }
                aVar.b();
                return arrayList;
            case BEGIN_OBJECT:
                g gVar = new g();
                aVar.c();
                while (aVar.e()) {
                    gVar.put(aVar.g(), b(aVar));
                }
                aVar.d();
                return gVar;
            case STRING:
                return aVar.h();
            case NUMBER:
                return Double.valueOf(aVar.k());
            case BOOLEAN:
                return Boolean.valueOf(aVar.i());
            case NULL:
                aVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void a(c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.f();
            return;
        }
        t a2 = this.b.a(obj.getClass());
        if (a2 instanceof h) {
            cVar.d();
            cVar.e();
            return;
        }
        a2.a(cVar, obj);
    }
}
