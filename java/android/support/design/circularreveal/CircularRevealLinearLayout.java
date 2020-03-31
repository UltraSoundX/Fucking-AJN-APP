package android.support.design.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.design.circularreveal.c.d;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CircularRevealLinearLayout extends LinearLayout implements c {
    private final b a = new b(this);

    public CircularRevealLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.a.a();
    }

    public void b() {
        this.a.b();
    }

    public d getRevealInfo() {
        return this.a.c();
    }

    public void setRevealInfo(d dVar) {
        this.a.a(dVar);
    }

    public int getCircularRevealScrimColor() {
        return this.a.d();
    }

    public void setCircularRevealScrimColor(int i) {
        this.a.a(i);
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.a.e();
    }

    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.a.a(drawable);
    }

    public void draw(Canvas canvas) {
        if (this.a != null) {
            this.a.a(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean isOpaque() {
        if (this.a != null) {
            return this.a.f();
        }
        return super.isOpaque();
    }

    public boolean c() {
        return super.isOpaque();
    }
}
