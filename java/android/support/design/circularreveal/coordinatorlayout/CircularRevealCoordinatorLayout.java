package android.support.design.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.design.circularreveal.b;
import android.support.design.circularreveal.c;
import android.support.design.circularreveal.c.d;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements c {
    private final b f = new b(this);

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.f.a();
    }

    public void b() {
        this.f.b();
    }

    public void setRevealInfo(d dVar) {
        this.f.a(dVar);
    }

    public d getRevealInfo() {
        return this.f.c();
    }

    public void setCircularRevealScrimColor(int i) {
        this.f.a(i);
    }

    public int getCircularRevealScrimColor() {
        return this.f.d();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f.e();
    }

    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f.a(drawable);
    }

    public void draw(Canvas canvas) {
        if (this.f != null) {
            this.f.a(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean isOpaque() {
        if (this.f != null) {
            return this.f.f();
        }
        return super.isOpaque();
    }

    public boolean c() {
        return super.isOpaque();
    }
}
