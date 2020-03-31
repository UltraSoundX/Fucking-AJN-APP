package android.support.design.button;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.design.e.a;
import android.support.design.internal.d;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;

/* compiled from: MaterialButtonHelper */
class b {
    private static final boolean a = (VERSION.SDK_INT >= 21);
    private final MaterialButton b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private Mode i;
    private ColorStateList j;
    private ColorStateList k;
    private ColorStateList l;
    private final Paint m = new Paint(1);
    private final Rect n = new Rect();
    private final RectF o = new RectF();
    private GradientDrawable p;

    /* renamed from: q reason: collision with root package name */
    private Drawable f316q;
    private GradientDrawable r;
    private Drawable s;
    private GradientDrawable t;
    private GradientDrawable u;
    private GradientDrawable v;
    private boolean w = false;

    public b(MaterialButton materialButton) {
        this.b = materialButton;
    }

    public void a(TypedArray typedArray) {
        int i2 = 0;
        this.c = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.d = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.e = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.f = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        this.g = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, 0);
        this.h = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.i = d.a(typedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), Mode.SRC_IN);
        this.j = a.a(this.b.getContext(), typedArray, R.styleable.MaterialButton_backgroundTint);
        this.k = a.a(this.b.getContext(), typedArray, R.styleable.MaterialButton_strokeColor);
        this.l = a.a(this.b.getContext(), typedArray, R.styleable.MaterialButton_rippleColor);
        this.m.setStyle(Style.STROKE);
        this.m.setStrokeWidth((float) this.h);
        Paint paint = this.m;
        if (this.k != null) {
            i2 = this.k.getColorForState(this.b.getDrawableState(), 0);
        }
        paint.setColor(i2);
        int paddingStart = ViewCompat.getPaddingStart(this.b);
        int paddingTop = this.b.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.b);
        int paddingBottom = this.b.getPaddingBottom();
        this.b.setInternalBackground(a ? k() : i());
        ViewCompat.setPaddingRelative(this.b, paddingStart + this.c, paddingTop + this.e, paddingEnd + this.d, paddingBottom + this.f);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.w = true;
        this.b.setSupportBackgroundTintList(this.j);
        this.b.setSupportBackgroundTintMode(this.i);
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        return this.w;
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas) {
        if (canvas != null && this.k != null && this.h > 0) {
            this.n.set(this.b.getBackground().getBounds());
            this.o.set(((float) this.n.left) + (((float) this.h) / 2.0f) + ((float) this.c), ((float) this.n.top) + (((float) this.h) / 2.0f) + ((float) this.e), (((float) this.n.right) - (((float) this.h) / 2.0f)) - ((float) this.d), (((float) this.n.bottom) - (((float) this.h) / 2.0f)) - ((float) this.f));
            float f2 = ((float) this.g) - (((float) this.h) / 2.0f);
            canvas.drawRoundRect(this.o, f2, f2, this.m);
        }
    }

    private Drawable i() {
        this.p = new GradientDrawable();
        this.p.setCornerRadius(((float) this.g) + 1.0E-5f);
        this.p.setColor(-1);
        this.f316q = DrawableCompat.wrap(this.p);
        DrawableCompat.setTintList(this.f316q, this.j);
        if (this.i != null) {
            DrawableCompat.setTintMode(this.f316q, this.i);
        }
        this.r = new GradientDrawable();
        this.r.setCornerRadius(((float) this.g) + 1.0E-5f);
        this.r.setColor(-1);
        this.s = DrawableCompat.wrap(this.r);
        DrawableCompat.setTintList(this.s, this.l);
        return a((Drawable) new LayerDrawable(new Drawable[]{this.f316q, this.s}));
    }

    private InsetDrawable a(Drawable drawable) {
        return new InsetDrawable(drawable, this.c, this.e, this.d, this.f);
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            if (a) {
                j();
            } else if (this.f316q != null) {
                DrawableCompat.setTintList(this.f316q, this.j);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList c() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public void a(Mode mode) {
        if (this.i != mode) {
            this.i = mode;
            if (a) {
                j();
            } else if (this.f316q != null && this.i != null) {
                DrawableCompat.setTintMode(this.f316q, this.i);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public Mode d() {
        return this.i;
    }

    private void j() {
        if (this.t != null) {
            DrawableCompat.setTintList(this.t, this.j);
            if (this.i != null) {
                DrawableCompat.setTintMode(this.t, this.i);
            }
        }
    }

    @TargetApi(21)
    private Drawable k() {
        this.t = new GradientDrawable();
        this.t.setCornerRadius(((float) this.g) + 1.0E-5f);
        this.t.setColor(-1);
        j();
        this.u = new GradientDrawable();
        this.u.setCornerRadius(((float) this.g) + 1.0E-5f);
        this.u.setColor(0);
        this.u.setStroke(this.h, this.k);
        InsetDrawable a2 = a((Drawable) new LayerDrawable(new Drawable[]{this.t, this.u}));
        this.v = new GradientDrawable();
        this.v.setCornerRadius(((float) this.g) + 1.0E-5f);
        this.v.setColor(-1);
        return new a(android.support.design.f.a.a(this.l), a2, this.v);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        if (this.v != null) {
            this.v.setBounds(this.c, this.e, i3 - this.d, i2 - this.f);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        if (a && this.t != null) {
            this.t.setColor(i2);
        } else if (!a && this.p != null) {
            this.p.setColor(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            if (a && (this.b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.b.getBackground()).setColor(colorStateList);
            } else if (!a && this.s != null) {
                DrawableCompat.setTintList(this.s, colorStateList);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList e() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public void c(ColorStateList colorStateList) {
        int i2 = 0;
        if (this.k != colorStateList) {
            this.k = colorStateList;
            Paint paint = this.m;
            if (colorStateList != null) {
                i2 = colorStateList.getColorForState(this.b.getDrawableState(), 0);
            }
            paint.setColor(i2);
            l();
        }
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList f() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        if (this.h != i2) {
            this.h = i2;
            this.m.setStrokeWidth((float) i2);
            l();
        }
    }

    /* access modifiers changed from: 0000 */
    public int g() {
        return this.h;
    }

    private void l() {
        if (a && this.u != null) {
            this.b.setInternalBackground(k());
        } else if (!a) {
            this.b.invalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2) {
        if (this.g != i2) {
            this.g = i2;
            if (a && this.t != null && this.u != null && this.v != null) {
                if (VERSION.SDK_INT == 21) {
                    n().setCornerRadius(((float) i2) + 1.0E-5f);
                    m().setCornerRadius(((float) i2) + 1.0E-5f);
                }
                this.t.setCornerRadius(((float) i2) + 1.0E-5f);
                this.u.setCornerRadius(((float) i2) + 1.0E-5f);
                this.v.setCornerRadius(((float) i2) + 1.0E-5f);
            } else if (!a && this.p != null && this.r != null) {
                this.p.setCornerRadius(((float) i2) + 1.0E-5f);
                this.r.setCornerRadius(((float) i2) + 1.0E-5f);
                this.b.invalidate();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int h() {
        return this.g;
    }

    private GradientDrawable m() {
        if (!a || this.b.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.b.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }

    private GradientDrawable n() {
        if (!a || this.b.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.b.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }
}
