package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/* compiled from: CardViewBaseImpl */
class o implements q {
    final RectF a = new RectF();

    o() {
    }

    public void a() {
        ai.a = new a() {
            public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
                float f2 = 2.0f * f;
                float width = (rectF.width() - f2) - 1.0f;
                float height = (rectF.height() - f2) - 1.0f;
                if (f >= 1.0f) {
                    float f3 = f + 0.5f;
                    o.this.a.set(-f3, -f3, f3, f3);
                    int save = canvas.save();
                    canvas.translate(rectF.left + f3, rectF.top + f3);
                    canvas.drawArc(o.this.a, 180.0f, 90.0f, true, paint);
                    canvas.translate(width, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(o.this.a, 180.0f, 90.0f, true, paint);
                    canvas.translate(height, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(o.this.a, 180.0f, 90.0f, true, paint);
                    canvas.translate(width, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(o.this.a, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(save);
                    canvas.drawRect((rectF.left + f3) - 1.0f, rectF.top, 1.0f + (rectF.right - f3), rectF.top + f3, paint);
                    canvas.drawRect((rectF.left + f3) - 1.0f, rectF.bottom - f3, 1.0f + (rectF.right - f3), rectF.bottom, paint);
                }
                canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
            }
        };
    }

    public void a(p pVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        ai a2 = a(context, colorStateList, f, f2, f3);
        a2.a(pVar.b());
        pVar.a(a2);
        f(pVar);
    }

    private ai a(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new ai(context.getResources(), colorStateList, f, f2, f3);
    }

    public void f(p pVar) {
        Rect rect = new Rect();
        j(pVar).a(rect);
        pVar.a((int) Math.ceil((double) b(pVar)), (int) Math.ceil((double) c(pVar)));
        pVar.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void g(p pVar) {
    }

    public void h(p pVar) {
        j(pVar).a(pVar.b());
        f(pVar);
    }

    public void a(p pVar, ColorStateList colorStateList) {
        j(pVar).a(colorStateList);
    }

    public ColorStateList i(p pVar) {
        return j(pVar).f();
    }

    public void a(p pVar, float f) {
        j(pVar).a(f);
        f(pVar);
    }

    public float d(p pVar) {
        return j(pVar).a();
    }

    public void c(p pVar, float f) {
        j(pVar).b(f);
    }

    public float e(p pVar) {
        return j(pVar).b();
    }

    public void b(p pVar, float f) {
        j(pVar).c(f);
        f(pVar);
    }

    public float a(p pVar) {
        return j(pVar).c();
    }

    public float b(p pVar) {
        return j(pVar).d();
    }

    public float c(p pVar) {
        return j(pVar).e();
    }

    private ai j(p pVar) {
        return (ai) pVar.c();
    }
}
