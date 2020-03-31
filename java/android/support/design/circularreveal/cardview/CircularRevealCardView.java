package android.support.design.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.design.circularreveal.b;
import android.support.design.circularreveal.c;
import android.support.design.circularreveal.c.d;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class CircularRevealCardView extends CardView implements c {
    private final b e = new b(this);

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.e.a();
    }

    public void b() {
        this.e.b();
    }

    public void setRevealInfo(d dVar) {
        this.e.a(dVar);
    }

    public d getRevealInfo() {
        return this.e.c();
    }

    public void setCircularRevealScrimColor(int i) {
        this.e.a(i);
    }

    public int getCircularRevealScrimColor() {
        return this.e.d();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.e.e();
    }

    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.e.a(drawable);
    }

    public void draw(Canvas canvas) {
        if (this.e != null) {
            this.e.a(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean isOpaque() {
        if (this.e != null) {
            return this.e.f();
        }
        return super.isOpaque();
    }

    public boolean c() {
        return super.isOpaque();
    }
}
