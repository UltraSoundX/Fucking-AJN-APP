package com.b.a.b.a;

import com.b.a.d.a;
import com.b.a.d.c;
import com.b.a.e;
import com.b.a.t;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: TypeAdapterRuntimeTypeWrapper */
final class m<T> extends t<T> {
    private final e a;
    private final t<T> b;
    private final Type c;

    m(e eVar, t<T> tVar, Type type) {
        this.a = eVar;
        this.b = tVar;
        this.c = type;
    }

    public T b(a aVar) throws IOException {
        return this.b.b(aVar);
    }

    public void a(c cVar, T t) throws IOException {
        t<T> tVar = this.b;
        Type a2 = a(this.c, (Object) t);
        if (a2 != this.c) {
            tVar = this.a.a(com.b.a.c.a.a(a2));
            if ((tVar instanceof i.a) && !(this.b instanceof i.a)) {
                tVar = this.b;
            }
        }
        tVar.a(cVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
