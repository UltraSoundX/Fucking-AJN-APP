package android.support.design.widget;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.design.a.a;
import android.support.v4.math.MathUtils;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.at;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;

/* compiled from: CollapsingTextHelper */
public final class d {
    private static final boolean a = (VERSION.SDK_INT < 18);
    private static final Paint b = null;
    private boolean A;
    private Bitmap B;
    private Paint C;
    private float D;
    private float E;
    private float F;
    private float G;
    private int[] H;
    private boolean I;
    private final TextPaint J;
    private final TextPaint K;
    private TimeInterpolator L;
    private TimeInterpolator M;
    private float N;
    private float O;
    private float P;
    private int Q;
    private float R;
    private float S;
    private float T;
    private int U;
    private final View c;
    private boolean d;
    private float e;
    private final Rect f;
    private final Rect g;
    private final RectF h;
    private int i = 16;
    private int j = 16;
    private float k = 15.0f;
    private float l = 15.0f;
    private ColorStateList m;
    private ColorStateList n;
    private float o;
    private float p;

    /* renamed from: q reason: collision with root package name */
    private float f327q;
    private float r;
    private float s;
    private float t;
    private Typeface u;
    private Typeface v;
    private Typeface w;
    private CharSequence x;
    private CharSequence y;
    private boolean z;

    static {
        if (b != null) {
            b.setAntiAlias(true);
            b.setColor(-65281);
        }
    }

    public d(View view) {
        this.c = view;
        this.J = new TextPaint(129);
        this.K = new TextPaint(this.J);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    public void a(TimeInterpolator timeInterpolator) {
        this.M = timeInterpolator;
        k();
    }

    public void b(TimeInterpolator timeInterpolator) {
        this.L = timeInterpolator;
        k();
    }

    public void a(float f2) {
        if (this.k != f2) {
            this.k = f2;
            k();
        }
    }

    public void a(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            k();
        }
    }

    public void b(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            k();
        }
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (!a(this.f, i2, i3, i4, i5)) {
            this.f.set(i2, i3, i4, i5);
            this.I = true;
            c();
        }
    }

    public void b(int i2, int i3, int i4, int i5) {
        if (!a(this.g, i2, i3, i4, i5)) {
            this.g.set(i2, i3, i4, i5);
            this.I = true;
            c();
        }
    }

    public float a() {
        if (this.x == null) {
            return 0.0f;
        }
        a(this.K);
        return this.K.measureText(this.x, 0, this.x.length());
    }

    public float b() {
        a(this.K);
        return -this.K.ascent();
    }

    public void a(RectF rectF) {
        float a2;
        boolean b2 = b(this.x);
        if (!b2) {
            a2 = (float) this.g.left;
        } else {
            a2 = ((float) this.g.right) - a();
        }
        rectF.left = a2;
        rectF.top = (float) this.g.top;
        rectF.right = !b2 ? rectF.left + a() : (float) this.g.right;
        rectF.bottom = ((float) this.g.top) + b();
    }

    private void a(TextPaint textPaint) {
        textPaint.setTextSize(this.l);
        textPaint.setTypeface(this.u);
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.d = this.g.width() > 0 && this.g.height() > 0 && this.f.width() > 0 && this.f.height() > 0;
    }

    public void a(int i2) {
        if (this.i != i2) {
            this.i = i2;
            k();
        }
    }

    public int d() {
        return this.i;
    }

    public void b(int i2) {
        if (this.j != i2) {
            this.j = i2;
            k();
        }
    }

    public int e() {
        return this.j;
    }

    public void c(int i2) {
        at a2 = at.a(this.c.getContext(), i2, R.styleable.TextAppearance);
        if (a2.g(R.styleable.TextAppearance_android_textColor)) {
            this.n = a2.e(R.styleable.TextAppearance_android_textColor);
        }
        if (a2.g(R.styleable.TextAppearance_android_textSize)) {
            this.l = (float) a2.e(R.styleable.TextAppearance_android_textSize, (int) this.l);
        }
        this.Q = a2.a(R.styleable.TextAppearance_android_shadowColor, 0);
        this.O = a2.a(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.P = a2.a(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.N = a2.a(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a2.a();
        if (VERSION.SDK_INT >= 16) {
            this.u = e(i2);
        }
        k();
    }

    public void d(int i2) {
        at a2 = at.a(this.c.getContext(), i2, R.styleable.TextAppearance);
        if (a2.g(R.styleable.TextAppearance_android_textColor)) {
            this.m = a2.e(R.styleable.TextAppearance_android_textColor);
        }
        if (a2.g(R.styleable.TextAppearance_android_textSize)) {
            this.k = (float) a2.e(R.styleable.TextAppearance_android_textSize, (int) this.k);
        }
        this.U = a2.a(R.styleable.TextAppearance_android_shadowColor, 0);
        this.S = a2.a(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.T = a2.a(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.R = a2.a(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a2.a();
        if (VERSION.SDK_INT >= 16) {
            this.v = e(i2);
        }
        k();
    }

    private Typeface e(int i2) {
        TypedArray obtainStyledAttributes = this.c.getContext().obtainStyledAttributes(i2, new int[]{16843692});
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                return Typeface.create(string, 0);
            }
            obtainStyledAttributes.recycle();
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void a(Typeface typeface) {
        if (this.u != typeface) {
            this.u = typeface;
            k();
        }
    }

    public void b(Typeface typeface) {
        if (this.v != typeface) {
            this.v = typeface;
            k();
        }
    }

    public void c(Typeface typeface) {
        this.v = typeface;
        this.u = typeface;
        k();
    }

    public Typeface f() {
        return this.u != null ? this.u : Typeface.DEFAULT;
    }

    public Typeface g() {
        return this.v != null ? this.v : Typeface.DEFAULT;
    }

    public void b(float f2) {
        float clamp = MathUtils.clamp(f2, 0.0f, 1.0f);
        if (clamp != this.e) {
            this.e = clamp;
            n();
        }
    }

    public final boolean a(int[] iArr) {
        this.H = iArr;
        if (!h()) {
            return false;
        }
        k();
        return true;
    }

    public final boolean h() {
        return (this.n != null && this.n.isStateful()) || (this.m != null && this.m.isStateful());
    }

    public float i() {
        return this.e;
    }

    private void n() {
        c(this.e);
    }

    private void c(float f2) {
        d(f2);
        this.s = a(this.f327q, this.r, f2, this.L);
        this.t = a(this.o, this.p, f2, this.L);
        e(a(this.k, this.l, f2, this.M));
        if (this.n != this.m) {
            this.J.setColor(a(o(), j(), f2));
        } else {
            this.J.setColor(j());
        }
        this.J.setShadowLayer(a(this.R, this.N, f2, (TimeInterpolator) null), a(this.S, this.O, f2, (TimeInterpolator) null), a(this.T, this.P, f2, (TimeInterpolator) null), a(this.U, this.Q, f2));
        ViewCompat.postInvalidateOnAnimation(this.c);
    }

    private int o() {
        if (this.H != null) {
            return this.m.getColorForState(this.H, 0);
        }
        return this.m.getDefaultColor();
    }

    public int j() {
        if (this.H != null) {
            return this.n.getColorForState(this.H, 0);
        }
        return this.n.getDefaultColor();
    }

    private void p() {
        int i2;
        int i3 = 1;
        float f2 = 0.0f;
        float f3 = this.G;
        f(this.l);
        float f4 = this.y != null ? this.J.measureText(this.y, 0, this.y.length()) : 0.0f;
        int i4 = this.j;
        if (this.z) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i4, i2);
        switch (absoluteGravity & 112) {
            case 48:
                this.p = ((float) this.g.top) - this.J.ascent();
                break;
            case 80:
                this.p = (float) this.g.bottom;
                break;
            default:
                this.p = (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent()) + ((float) this.g.centerY());
                break;
        }
        switch (absoluteGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.r = ((float) this.g.centerX()) - (f4 / 2.0f);
                break;
            case 5:
                this.r = ((float) this.g.right) - f4;
                break;
            default:
                this.r = (float) this.g.left;
                break;
        }
        f(this.k);
        if (this.y != null) {
            f2 = this.J.measureText(this.y, 0, this.y.length());
        }
        int i5 = this.i;
        if (!this.z) {
            i3 = 0;
        }
        int absoluteGravity2 = GravityCompat.getAbsoluteGravity(i5, i3);
        switch (absoluteGravity2 & 112) {
            case 48:
                this.o = ((float) this.f.top) - this.J.ascent();
                break;
            case 80:
                this.o = (float) this.f.bottom;
                break;
            default:
                this.o = (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent()) + ((float) this.f.centerY());
                break;
        }
        switch (absoluteGravity2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.f327q = ((float) this.f.centerX()) - (f2 / 2.0f);
                break;
            case 5:
                this.f327q = ((float) this.f.right) - f2;
                break;
            default:
                this.f327q = (float) this.f.left;
                break;
        }
        r();
        e(f3);
    }

    private void d(float f2) {
        this.h.left = a((float) this.f.left, (float) this.g.left, f2, this.L);
        this.h.top = a(this.o, this.p, f2, this.L);
        this.h.right = a((float) this.f.right, (float) this.g.right, f2, this.L);
        this.h.bottom = a((float) this.f.bottom, (float) this.g.bottom, f2, this.L);
    }

    public void a(Canvas canvas) {
        float ascent;
        int save = canvas.save();
        if (this.y != null && this.d) {
            float f2 = this.s;
            float f3 = this.t;
            boolean z2 = this.A && this.B != null;
            if (z2) {
                ascent = this.D * this.F;
                float f4 = this.E * this.F;
            } else {
                ascent = this.J.ascent() * this.F;
                float descent = this.J.descent() * this.F;
            }
            if (z2) {
                f3 += ascent;
            }
            if (this.F != 1.0f) {
                canvas.scale(this.F, this.F, f2, f3);
            }
            if (z2) {
                canvas.drawBitmap(this.B, f2, f3, this.C);
            } else {
                canvas.drawText(this.y, 0, this.y.length(), f2, f3, this.J);
            }
        }
        canvas.restoreToCount(save);
    }

    private boolean b(CharSequence charSequence) {
        boolean z2 = true;
        if (ViewCompat.getLayoutDirection(this.c) != 1) {
            z2 = false;
        }
        return (z2 ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(charSequence, 0, charSequence.length());
    }

    private void e(float f2) {
        f(f2);
        this.A = a && this.F != 1.0f;
        if (this.A) {
            q();
        }
        ViewCompat.postInvalidateOnAnimation(this.c);
    }

    private void f(float f2) {
        float f3;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (this.x != null) {
            float width = (float) this.g.width();
            float width2 = (float) this.f.width();
            if (a(f2, this.l)) {
                f3 = this.l;
                this.F = 1.0f;
                if (this.w != this.u) {
                    this.w = this.u;
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                f3 = this.k;
                if (this.w != this.v) {
                    this.w = this.v;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (a(f2, this.k)) {
                    this.F = 1.0f;
                } else {
                    this.F = f2 / this.k;
                }
                float f4 = this.l / this.k;
                width = width2 * f4 > width ? Math.min(width / f4, width2) : width2;
            }
            if (width > 0.0f) {
                if (this.G != f3 || this.I || z3) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.G = f3;
                this.I = false;
            }
            if (this.y == null || z3) {
                this.J.setTextSize(this.G);
                this.J.setTypeface(this.w);
                TextPaint textPaint = this.J;
                if (this.F == 1.0f) {
                    z4 = false;
                }
                textPaint.setLinearText(z4);
                CharSequence ellipsize = TextUtils.ellipsize(this.x, this.J, width, TruncateAt.END);
                if (!TextUtils.equals(ellipsize, this.y)) {
                    this.y = ellipsize;
                    this.z = b(this.y);
                }
            }
        }
    }

    private void q() {
        if (this.B == null && !this.f.isEmpty() && !TextUtils.isEmpty(this.y)) {
            c(0.0f);
            this.D = this.J.ascent();
            this.E = this.J.descent();
            int round = Math.round(this.J.measureText(this.y, 0, this.y.length()));
            int round2 = Math.round(this.E - this.D);
            if (round > 0 && round2 > 0) {
                this.B = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                new Canvas(this.B).drawText(this.y, 0, this.y.length(), 0.0f, ((float) round2) - this.J.descent(), this.J);
                if (this.C == null) {
                    this.C = new Paint(3);
                }
            }
        }
    }

    public void k() {
        if (this.c.getHeight() > 0 && this.c.getWidth() > 0) {
            p();
            n();
        }
    }

    public void a(CharSequence charSequence) {
        if (charSequence == null || !charSequence.equals(this.x)) {
            this.x = charSequence;
            this.y = null;
            r();
            k();
        }
    }

    public CharSequence l() {
        return this.x;
    }

    private void r() {
        if (this.B != null) {
            this.B.recycle();
            this.B = null;
        }
    }

    private static boolean a(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.001f;
    }

    public ColorStateList m() {
        return this.n;
    }

    private static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((((float) Color.alpha(i2)) * f3) + (((float) Color.alpha(i3)) * f2)), (int) ((((float) Color.red(i2)) * f3) + (((float) Color.red(i3)) * f2)), (int) ((((float) Color.green(i2)) * f3) + (((float) Color.green(i3)) * f2)), (int) ((f3 * ((float) Color.blue(i2))) + (((float) Color.blue(i3)) * f2)));
    }

    private static float a(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        return a.a(f2, f3, f4);
    }

    private static boolean a(Rect rect, int i2, int i3, int i4, int i5) {
        return rect.left == i2 && rect.top == i3 && rect.right == i4 && rect.bottom == i5;
    }
}
