package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.b.a.c;

/* compiled from: ShadowDrawableWrapper */
public class l extends c {
    static final double a = Math.cos(Math.toRadians(45.0d));
    final Paint b;
    final Paint c;
    final RectF d;
    float e;
    Path f;
    float g;
    float h;
    float i;
    float j;
    private boolean k = true;
    private final int l;
    private final int m;
    private final int n;
    private boolean o = true;
    private float p;

    /* renamed from: q reason: collision with root package name */
    private boolean f330q = false;

    public l(Context context, Drawable drawable, float f2, float f3, float f4) {
        super(drawable);
        this.l = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.m = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.n = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        this.b = new Paint(5);
        this.b.setStyle(Style.FILL);
        this.e = (float) Math.round(f2);
        this.d = new RectF();
        this.c = new Paint(this.b);
        this.c.setAntiAlias(false);
        a(f3, f4);
    }

    private static int c(float f2) {
        int round = Math.round(f2);
        return round % 2 == 1 ? round - 1 : round;
    }

    public void a(boolean z) {
        this.o = z;
        invalidateSelf();
    }

    public void setAlpha(int i2) {
        super.setAlpha(i2);
        this.b.setAlpha(i2);
        this.c.setAlpha(i2);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.k = true;
    }

    public void a(float f2, float f3) {
        if (f2 < 0.0f || f3 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float c2 = (float) c(f2);
        float c3 = (float) c(f3);
        if (c2 > c3) {
            if (!this.f330q) {
                this.f330q = true;
            }
            c2 = c3;
        }
        if (this.j != c2 || this.h != c3) {
            this.j = c2;
            this.h = c3;
            this.i = (float) Math.round(c2 * 1.5f);
            this.g = c3;
            this.k = true;
            invalidateSelf();
        }
    }

    public void a(float f2) {
        a(f2, this.h);
    }

    public float a() {
        return this.j;
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) a(this.h, this.e, this.o));
        int ceil2 = (int) Math.ceil((double) b(this.h, this.e, this.o));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public static float a(float f2, float f3, boolean z) {
        if (z) {
            return (float) (((double) (1.5f * f2)) + ((1.0d - a) * ((double) f3)));
        }
        return 1.5f * f2;
    }

    public static float b(float f2, float f3, boolean z) {
        if (z) {
            return (float) (((double) f2) + ((1.0d - a) * ((double) f3)));
        }
        return f2;
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(Canvas canvas) {
        if (this.k) {
            a(getBounds());
            this.k = false;
        }
        a(canvas);
        super.draw(canvas);
    }

    public final void b(float f2) {
        if (this.p != f2) {
            this.p = f2;
            invalidateSelf();
        }
    }

    private void a(Canvas canvas) {
        int save = canvas.save();
        canvas.rotate(this.p, this.d.centerX(), this.d.centerY());
        float f2 = (-this.e) - this.i;
        float f3 = this.e;
        boolean z = this.d.width() - (2.0f * f3) > 0.0f;
        boolean z2 = this.d.height() - (2.0f * f3) > 0.0f;
        float f4 = f3 / ((this.j - (this.j * 0.5f)) + f3);
        float f5 = f3 / ((this.j - (this.j * 0.25f)) + f3);
        float f6 = f3 / (f3 + (this.j - (this.j * 1.0f)));
        int save2 = canvas.save();
        canvas.translate(this.d.left + f3, this.d.top + f3);
        canvas.scale(f4, f5);
        canvas.drawPath(this.f, this.b);
        if (z) {
            canvas.scale(1.0f / f4, 1.0f);
            canvas.drawRect(0.0f, f2, this.d.width() - (2.0f * f3), -this.e, this.c);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        canvas.translate(this.d.right - f3, this.d.bottom - f3);
        canvas.scale(f4, f6);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f, this.b);
        if (z) {
            canvas.scale(1.0f / f4, 1.0f);
            canvas.drawRect(0.0f, f2, this.d.width() - (2.0f * f3), this.i + (-this.e), this.c);
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        canvas.translate(this.d.left + f3, this.d.bottom - f3);
        canvas.scale(f4, f6);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f, this.b);
        if (z2) {
            canvas.scale(1.0f / f6, 1.0f);
            canvas.drawRect(0.0f, f2, this.d.height() - (2.0f * f3), -this.e, this.c);
        }
        canvas.restoreToCount(save4);
        int save5 = canvas.save();
        canvas.translate(this.d.right - f3, this.d.top + f3);
        canvas.scale(f4, f5);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f, this.b);
        if (z2) {
            canvas.scale(1.0f / f5, 1.0f);
            canvas.drawRect(0.0f, f2, this.d.height() - (2.0f * f3), -this.e, this.c);
        }
        canvas.restoreToCount(save5);
        canvas.restoreToCount(save);
    }

    private void c() {
        RectF rectF = new RectF(-this.e, -this.e, this.e, this.e);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.i, -this.i);
        if (this.f == null) {
            this.f = new Path();
        } else {
            this.f.reset();
        }
        this.f.setFillType(FillType.EVEN_ODD);
        this.f.moveTo(-this.e, 0.0f);
        this.f.rLineTo(-this.i, 0.0f);
        this.f.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f.arcTo(rectF, 270.0f, -90.0f, false);
        this.f.close();
        float f2 = -rectF2.top;
        if (f2 > 0.0f) {
            float f3 = this.e / f2;
            float f4 = f3 + ((1.0f - f3) / 2.0f);
            this.b.setShader(new RadialGradient(0.0f, 0.0f, f2, new int[]{0, this.l, this.m, this.n}, new float[]{0.0f, f3, f4, 1.0f}, TileMode.CLAMP));
        }
        this.c.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.l, this.m, this.n}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP));
        this.c.setAntiAlias(false);
    }

    private void a(Rect rect) {
        float f2 = this.h * 1.5f;
        this.d.set(((float) rect.left) + this.h, ((float) rect.top) + f2, ((float) rect.right) - this.h, ((float) rect.bottom) - f2);
        b().setBounds((int) this.d.left, (int) this.d.top, (int) this.d.right, (int) this.d.bottom);
        c();
    }
}
