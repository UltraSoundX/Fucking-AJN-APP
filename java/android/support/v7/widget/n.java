package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* compiled from: CardViewApi21Impl */
class n implements q {
    n() {
    }

    public void a(p pVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        pVar.a(new ah(colorStateList, f));
        View d = pVar.d();
        d.setClipToOutline(true);
        d.setElevation(f2);
        b(pVar, f3);
    }

    public void a(p pVar, float f) {
        j(pVar).a(f);
    }

    public void a() {
    }

    public void b(p pVar, float f) {
        j(pVar).a(f, pVar.a(), pVar.b());
        f(pVar);
    }

    public float a(p pVar) {
        return j(pVar).a();
    }

    public float b(p pVar) {
        return d(pVar) * 2.0f;
    }

    public float c(p pVar) {
        return d(pVar) * 2.0f;
    }

    public float d(p pVar) {
        return j(pVar).b();
    }

    public void c(p pVar, float f) {
        pVar.d().setElevation(f);
    }

    public float e(p pVar) {
        return pVar.d().getElevation();
    }

    public void f(p pVar) {
        if (!pVar.a()) {
            pVar.a(0, 0, 0, 0);
            return;
        }
        float a = a(pVar);
        float d = d(pVar);
        int ceil = (int) Math.ceil((double) ai.b(a, d, pVar.b()));
        int ceil2 = (int) Math.ceil((double) ai.a(a, d, pVar.b()));
        pVar.a(ceil, ceil2, ceil, ceil2);
    }

    public void g(p pVar) {
        b(pVar, a(pVar));
    }

    public void h(p pVar) {
        b(pVar, a(pVar));
    }

    public void a(p pVar, ColorStateList colorStateList) {
        j(pVar).a(colorStateList);
    }

    public ColorStateList i(p pVar) {
        return j(pVar).c();
    }

    private ah j(p pVar) {
        return (ah) pVar.c();
    }
}
