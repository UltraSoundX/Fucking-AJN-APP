package com.b.a.b.a;

import com.b.a.d.c;
import com.b.a.e;
import com.b.a.h;
import com.b.a.i;
import com.b.a.j;
import com.b.a.p;
import com.b.a.q;
import com.b.a.t;
import com.b.a.u;
import java.io.IOException;

/* compiled from: TreeTypeAdapter */
public final class l<T> extends t<T> {
    private final q<T> a;
    private final i<T> b;
    private final e c;
    private final com.b.a.c.a<T> d;
    private final u e;
    private final a f = new a<>();
    private t<T> g;

    /* compiled from: TreeTypeAdapter */
    private final class a implements h, p {
        private a() {
        }
    }

    public l(q<T> qVar, i<T> iVar, e eVar, com.b.a.c.a<T> aVar, u uVar) {
        this.a = qVar;
        this.b = iVar;
        this.c = eVar;
        this.d = aVar;
        this.e = uVar;
    }

    public T b(com.b.a.d.a aVar) throws IOException {
        if (this.b == null) {
            return b().b(aVar);
        }
        j a2 = com.b.a.b.j.a(aVar);
        if (a2.j()) {
            return null;
        }
        return this.b.a(a2, this.d.b(), this.f);
    }

    public void a(c cVar, T t) throws IOException {
        if (this.a == null) {
            b().a(cVar, t);
        } else if (t == null) {
            cVar.f();
        } else {
            com.b.a.b.j.a(this.a.a(t, this.d.b(), this.f), cVar);
        }
    }

    private t<T> b() {
        t<T> tVar = this.g;
        if (tVar != null) {
            return tVar;
        }
        t<T> a2 = this.c.a(this.e, this.d);
        this.g = a2;
        return a2;
    }
}
