package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
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
import android.support.v7.cardview.R;

/* compiled from: RoundRectDrawableWithShadow */
class ai extends Drawable {
    static a a;
    private static final double b = Math.cos(Math.toRadians(45.0d));
    private final int c;
    private Paint d;
    private Paint e;
    private Paint f;
    private final RectF g;
    private float h;
    private Path i;
    private float j;
    private float k;
    private float l;
    private ColorStateList m;
    private boolean n = true;
    private final int o;
    private final int p;

    /* renamed from: q reason: collision with root package name */
    private boolean f360q = true;
    private boolean r = false;

    /* compiled from: RoundRectDrawableWithShadow */
    interface a {
        void a(Canvas canvas, RectF rectF, float f, Paint paint);
    }

    ai(Resources resources, ColorStateList colorStateList, float f2, float f3, float f4) {
        this.o = resources.getColor(R.color.cardview_shadow_start_color);
        this.p = resources.getColor(R.color.cardview_shadow_end_color);
        this.c = resources.getDimensionPixelSize(R.dimen.cardview_compat_inset_shadow);
        this.d = new Paint(5);
        b(colorStateList);
        this.e = new Paint(5);
        this.e.setStyle(Style.FILL);
        this.h = (float) ((int) (0.5f + f2));
        this.g = new RectF();
        this.f = new Paint(this.e);
        this.f.setAntiAlias(false);
        a(f3, f4);
    }

    private void b(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.m = colorStateList;
        this.d.setColor(this.m.getColorForState(getState(), this.m.getDefaultColor()));
    }

    private int d(float f2) {
        int i2 = (int) (0.5f + f2);
        if (i2 % 2 == 1) {
            return i2 - 1;
        }
        return i2;
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.f360q = z;
        invalidateSelf();
    }

    public void setAlpha(int i2) {
        this.d.setAlpha(i2);
        this.e.setAlpha(i2);
        this.f.setAlpha(i2);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.n = true;
    }

    private void a(float f2, float f3) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f2 + ". Must be >= 0");
        } else if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f3 + ". Must be >= 0");
        } else {
            float d2 = (float) d(f2);
            float d3 = (float) d(f3);
            if (d2 > d3) {
                if (!this.r) {
                    this.r = true;
                }
                d2 = d3;
            }
            if (this.l != d2 || this.j != d3) {
                this.l = d2;
                this.j = d3;
                this.k = (float) ((int) ((d2 * 1.5f) + ((float) this.c) + 0.5f));
                this.n = true;
                invalidateSelf();
            }
        }
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) a(this.j, this.h, this.f360q));
        int ceil2 = (int) Math.ceil((double) b(this.j, this.h, this.f360q));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    static float a(float f2, float f3, boolean z) {
        if (z) {
            return (float) (((double) (1.5f * f2)) + ((1.0d - b) * ((double) f3)));
        }
        return 1.5f * f2;
    }

    static float b(float f2, float f3, boolean z) {
        if (z) {
            return (float) (((double) f2) + ((1.0d - b) * ((double) f3)));
        }
        return f2;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int colorForState = this.m.getColorForState(iArr, this.m.getDefaultColor());
        if (this.d.getColor() == colorForState) {
            return false;
        }
        this.d.setColor(colorForState);
        this.n = true;
        invalidateSelf();
        return true;
    }

    public boolean isStateful() {
        return (this.m != null && this.m.isStateful()) || super.isStateful();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    /* access modifiers changed from: 0000 */
    public void a(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f2 + ". Must be >= 0");
        }
        float f3 = (float) ((int) (0.5f + f2));
        if (this.h != f3) {
            this.h = f3;
            this.n = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.n) {
            b(getBounds());
            this.n = false;
        }
        canvas.translate(0.0f, this.l / 2.0f);
        a(canvas);
        canvas.translate(0.0f, (-this.l) / 2.0f);
        a.a(canvas, this.g, this.h, this.d);
    }

    private void a(Canvas canvas) {
        boolean z;
        float f2 = (-this.h) - this.k;
        float f3 = this.h + ((float) this.c) + (this.l / 2.0f);
        boolean z2 = this.g.width() - (2.0f * f3) > 0.0f;
        if (this.g.height() - (2.0f * f3) > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        int save = canvas.save();
        canvas.translate(this.g.left + f3, this.g.top + f3);
        canvas.drawPath(this.i, this.e);
        if (z2) {
            canvas.drawRect(0.0f, f2, this.g.width() - (2.0f * f3), -this.h, this.f);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        canvas.translate(this.g.right - f3, this.g.bottom - f3);
        canvas.rotate(180.0f);
        canvas.drawPath(this.i, this.e);
        if (z2) {
            canvas.drawRect(0.0f, f2, this.g.width() - (2.0f * f3), this.k + (-this.h), this.f);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        canvas.translate(this.g.left + f3, this.g.bottom - f3);
        canvas.rotate(270.0f);
        canvas.drawPath(this.i, this.e);
        if (z) {
            canvas.drawRect(0.0f, f2, this.g.height() - (2.0f * f3), -this.h, this.f);
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        canvas.translate(this.g.right - f3, this.g.top + f3);
        canvas.rotate(90.0f);
        canvas.drawPath(this.i, this.e);
        if (z) {
            canvas.drawRect(0.0f, f2, this.g.height() - (2.0f * f3), -this.h, this.f);
        }
        canvas.restoreToCount(save4);
    }

    private void g() {
        RectF rectF = new RectF(-this.h, -this.h, this.h, this.h);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.k, -this.k);
        if (this.i == null) {
            this.i = new Path();
        } else {
            this.i.reset();
        }
        this.i.setFillType(FillType.EVEN_ODD);
        this.i.moveTo(-this.h, 0.0f);
        this.i.rLineTo(-this.k, 0.0f);
        this.i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.i.arcTo(rectF, 270.0f, -90.0f, false);
        this.i.close();
        float f2 = this.h / (this.h + this.k);
        this.e.setShader(new RadialGradient(0.0f, 0.0f, this.h + this.k, new int[]{this.o, this.o, this.p}, new float[]{0.0f, f2, 1.0f}, TileMode.CLAMP));
        this.f.setShader(new LinearGradient(0.0f, (-this.h) + this.k, 0.0f, (-this.h) - this.k, new int[]{this.o, this.o, this.p}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP));
        this.f.setAntiAlias(false);
    }

    private void b(Rect rect) {
        float f2 = this.j * 1.5f;
        this.g.set(((float) rect.left) + this.j, ((float) rect.top) + f2, ((float) rect.right) - this.j, ((float) rect.bottom) - f2);
        g();
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public void a(Rect rect) {
        getPadding(rect);
    }

    /* access modifiers changed from: 0000 */
    public void b(float f2) {
        a(f2, this.j);
    }

    /* access modifiers changed from: 0000 */
    public void c(float f2) {
        a(this.l, f2);
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public float c() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public float d() {
        return (Math.max(this.j, this.h + ((float) this.c) + (this.j / 2.0f)) * 2.0f) + ((this.j + ((float) this.c)) * 2.0f);
    }

    /* access modifiers changed from: 0000 */
    public float e() {
        return (Math.max(this.j, this.h + ((float) this.c) + ((this.j * 1.5f) / 2.0f)) * 2.0f) + (((this.j * 1.5f) + ((float) this.c)) * 2.0f);
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        b(colorStateList);
        invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList f() {
        return this.m;
    }
}
