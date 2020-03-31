package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.v4.graphics.ColorUtils;

/* compiled from: CircularBorderDrawable */
public class b extends Drawable {
    final Paint a = new Paint(1);
    final Rect b = new Rect();
    final RectF c = new RectF();
    final a d = new a();
    float e;
    private int f;
    private int g;
    private int h;
    private int i;
    private ColorStateList j;
    private int k;
    private boolean l = true;
    private float m;

    /* compiled from: CircularBorderDrawable */
    private class a extends ConstantState {
        private a() {
        }

        public Drawable newDrawable() {
            return b.this;
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }

    public b() {
        this.a.setStyle(Style.STROKE);
    }

    public ConstantState getConstantState() {
        return this.d;
    }

    public void a(int i2, int i3, int i4, int i5) {
        this.f = i2;
        this.g = i3;
        this.h = i4;
        this.i = i5;
    }

    public void a(float f2) {
        if (this.e != f2) {
            this.e = f2;
            this.a.setStrokeWidth(1.3333f * f2);
            this.l = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.l) {
            this.a.setShader(a());
            this.l = false;
        }
        float strokeWidth = this.a.getStrokeWidth() / 2.0f;
        RectF rectF = this.c;
        copyBounds(this.b);
        rectF.set(this.b);
        rectF.left += strokeWidth;
        rectF.top += strokeWidth;
        rectF.right -= strokeWidth;
        rectF.bottom -= strokeWidth;
        canvas.save();
        canvas.rotate(this.m, rectF.centerX(), rectF.centerY());
        canvas.drawOval(rectF, this.a);
        canvas.restore();
    }

    public boolean getPadding(Rect rect) {
        int round = Math.round(this.e);
        rect.set(round, round, round, round);
        return true;
    }

    public void setAlpha(int i2) {
        this.a.setAlpha(i2);
        invalidateSelf();
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.k = colorStateList.getColorForState(getState(), this.k);
        }
        this.j = colorStateList;
        this.l = true;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return this.e > 0.0f ? -3 : -2;
    }

    public final void b(float f2) {
        if (f2 != this.m) {
            this.m = f2;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.l = true;
    }

    public boolean isStateful() {
        return (this.j != null && this.j.isStateful()) || super.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.j != null) {
            int colorForState = this.j.getColorForState(iArr, this.k);
            if (colorForState != this.k) {
                this.l = true;
                this.k = colorForState;
            }
        }
        if (this.l) {
            invalidateSelf();
        }
        return this.l;
    }

    private Shader a() {
        Rect rect = this.b;
        copyBounds(rect);
        float height = this.e / ((float) rect.height());
        return new LinearGradient(0.0f, (float) rect.top, 0.0f, (float) rect.bottom, new int[]{ColorUtils.compositeColors(this.f, this.k), ColorUtils.compositeColors(this.g, this.k), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.g, 0), this.k), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.i, 0), this.k), ColorUtils.compositeColors(this.i, this.k), ColorUtils.compositeColors(this.h, this.k)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, TileMode.CLAMP);
    }
}
