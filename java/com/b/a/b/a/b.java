package com.b.a.b.a;

import com.b.a.b.c;
import com.b.a.b.h;
import com.b.a.e;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: CollectionTypeAdapterFactory */
public final class b implements u {
    private final c a;

    /* compiled from: CollectionTypeAdapterFactory */
    private static final class a<E> extends t<Collection<E>> {
        private final t<E> a;
        private final h<? extends Collection<E>> b;

        public a(e eVar, Type type, t<E> tVar, h<? extends Collection<E>> hVar) {
            this.a = new m(eVar, tVar, type);
            this.b = hVar;
        }

        /* renamed from: a */
        public Collection<E> b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == com.b.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            Collection<E> collection = (Collection) this.b.a();
            aVar.a();
            while (aVar.e()) {
                collection.add(this.a.b(aVar));
            }
            aVar.b();
            return collection;
        }

        public void a(com.b.a.d.c cVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (E a2 : collection) {
                this.a.a(cVar, a2);
            }
            cVar.c();
        }
    }

    public b(c cVar) {
        this.a = cVar;
    }

    public <T> t<T> a(e eVar, com.b.a.c.a<T> aVar) {
        Type b = aVar.b();
        Class a2 = aVar.a();
        if (!Collection.class.isAssignableFrom(a2)) {
            return null;
        }
        Type a3 = com.b.a.b.b.a(b, a2);
        return new a(eVar, a3, eVar.a(com.b.a.c.a.a(a3)), this.a.a(aVar));
    }
}
