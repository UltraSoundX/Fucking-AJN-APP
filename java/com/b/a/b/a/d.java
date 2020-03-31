package com.b.a.b.a;

import com.b.a.a.b;
import com.b.a.b.c;
import com.b.a.c.a;
import com.b.a.e;
import com.b.a.i;
import com.b.a.q;
import com.b.a.t;
import com.b.a.u;

/* compiled from: JsonAdapterAnnotationTypeAdapterFactory */
public final class d implements u {
    private final c a;

    public d(c cVar) {
        this.a = cVar;
    }

    public <T> t<T> a(e eVar, a<T> aVar) {
        b bVar = (b) aVar.a().getAnnotation(b.class);
        if (bVar == null) {
            return null;
        }
        return a(this.a, eVar, aVar, bVar);
    }

    /* access modifiers changed from: 0000 */
    public t<?> a(c cVar, e eVar, a<?> aVar, b bVar) {
        i iVar;
        t<?> lVar;
        Object a2 = cVar.a(a.b(bVar.a())).a();
        if (a2 instanceof t) {
            lVar = (t) a2;
        } else if (a2 instanceof u) {
            lVar = ((u) a2).a(eVar, aVar);
        } else if ((a2 instanceof q) || (a2 instanceof i)) {
            q qVar = a2 instanceof q ? (q) a2 : null;
            if (a2 instanceof i) {
                iVar = (i) a2;
            } else {
                iVar = null;
            }
            lVar = new l<>(qVar, iVar, eVar, aVar, null);
        } else {
            throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
        }
        if (lVar != null) {
            return lVar.a();
        }
        return lVar;
    }
}
