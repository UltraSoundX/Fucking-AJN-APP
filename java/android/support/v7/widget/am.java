package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.f;
import android.support.v7.widget.RecyclerView.f.c;
import android.support.v7.widget.RecyclerView.v;
import android.view.View;

/* compiled from: SimpleItemAnimator */
public abstract class am extends f {
    boolean h = true;

    public abstract boolean a(v vVar);

    public abstract boolean a(v vVar, int i, int i2, int i3, int i4);

    public abstract boolean a(v vVar, v vVar2, int i, int i2, int i3, int i4);

    public abstract boolean b(v vVar);

    public boolean h(v vVar) {
        return !this.h || vVar.isInvalid();
    }

    public boolean a(v vVar, c cVar, c cVar2) {
        int i = cVar.a;
        int i2 = cVar.b;
        View view = vVar.itemView;
        int i3 = cVar2 == null ? view.getLeft() : cVar2.a;
        int i4 = cVar2 == null ? view.getTop() : cVar2.b;
        if (vVar.isRemoved() || (i == i3 && i2 == i4)) {
            return a(vVar);
        }
        view.layout(i3, i4, view.getWidth() + i3, view.getHeight() + i4);
        return a(vVar, i, i2, i3, i4);
    }

    public boolean b(v vVar, c cVar, c cVar2) {
        if (cVar == null || (cVar.a == cVar2.a && cVar.b == cVar2.b)) {
            return b(vVar);
        }
        return a(vVar, cVar.a, cVar.b, cVar2.a, cVar2.b);
    }

    public boolean c(v vVar, c cVar, c cVar2) {
        if (cVar.a == cVar2.a && cVar.b == cVar2.b) {
            j(vVar);
            return false;
        }
        return a(vVar, cVar.a, cVar.b, cVar2.a, cVar2.b);
    }

    public boolean a(v vVar, v vVar2, c cVar, c cVar2) {
        int i;
        int i2;
        int i3 = cVar.a;
        int i4 = cVar.b;
        if (vVar2.shouldIgnore()) {
            i = cVar.a;
            i2 = cVar.b;
        } else {
            i = cVar2.a;
            i2 = cVar2.b;
        }
        return a(vVar, vVar2, i3, i4, i, i2);
    }

    public final void i(v vVar) {
        p(vVar);
        f(vVar);
    }

    public final void j(v vVar) {
        t(vVar);
        f(vVar);
    }

    public final void k(v vVar) {
        r(vVar);
        f(vVar);
    }

    public final void a(v vVar, boolean z) {
        d(vVar, z);
        f(vVar);
    }

    public final void l(v vVar) {
        o(vVar);
    }

    public final void m(v vVar) {
        s(vVar);
    }

    public final void n(v vVar) {
        q(vVar);
    }

    public final void b(v vVar, boolean z) {
        c(vVar, z);
    }

    public void o(v vVar) {
    }

    public void p(v vVar) {
    }

    public void q(v vVar) {
    }

    public void r(v vVar) {
    }

    public void s(v vVar) {
    }

    public void t(v vVar) {
    }

    public void c(v vVar, boolean z) {
    }

    public void d(v vVar, boolean z) {
    }
}
