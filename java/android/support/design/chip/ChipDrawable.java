package android.support.design.chip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.design.R;
import android.support.design.a.h;
import android.support.design.e.b;
import android.support.design.internal.c;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.text.BidiFormatter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class ChipDrawable extends Drawable implements Callback, TintAwareDrawable {
    private static final int[] a = {16842910};
    private float A;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G;
    private final Context H;
    private final TextPaint I = new TextPaint(1);
    private final Paint J = new Paint(1);
    private final Paint K;
    private final FontMetrics L = new FontMetrics();
    private final RectF M = new RectF();
    private final PointF N = new PointF();
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean S;
    private int T;
    private int U = 255;
    private ColorFilter V;
    private PorterDuffColorFilter W;
    private ColorStateList X;
    private Mode Y = Mode.SRC_IN;
    private int[] Z;
    private boolean aa;
    private ColorStateList ab;
    private WeakReference<a> ac = new WeakReference<>(null);
    /* access modifiers changed from: private */
    public boolean ad = true;
    private float ae;
    private TruncateAt af;
    private boolean ag;
    private int ah;
    private ColorStateList b;
    private float c;
    private float d;
    private ColorStateList e;
    private float f;
    private ColorStateList g;
    private CharSequence h;
    private CharSequence i;
    private b j;
    private final FontCallback k = new FontCallback() {
        public void onFontRetrieved(Typeface typeface) {
            ChipDrawable.this.ad = true;
            ChipDrawable.this.a();
            ChipDrawable.this.invalidateSelf();
        }

        public void onFontRetrievalFailed(int i) {
        }
    };
    private boolean l;
    private Drawable m;
    private ColorStateList n;
    private float o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private Drawable f317q;
    private ColorStateList r;
    private float s;
    private CharSequence t;
    private boolean u;
    private boolean v;
    private Drawable w;
    private h x;
    private h y;
    private float z;

    public interface a {
        void a();
    }

    public static ChipDrawable a(Context context, AttributeSet attributeSet, int i2, int i3) {
        ChipDrawable chipDrawable = new ChipDrawable(context);
        chipDrawable.a(attributeSet, i2, i3);
        return chipDrawable;
    }

    private ChipDrawable(Context context) {
        this.H = context;
        this.h = "";
        this.I.density = context.getResources().getDisplayMetrics().density;
        this.K = null;
        if (this.K != null) {
            this.K.setStyle(Style.STROKE);
        }
        setState(a);
        a(a);
        this.ag = true;
    }

    private void a(AttributeSet attributeSet, int i2, int i3) {
        TypedArray a2 = c.a(this.H, attributeSet, R.styleable.Chip, i2, i3, new int[0]);
        a(android.support.design.e.a.a(this.H, a2, R.styleable.Chip_chipBackgroundColor));
        a(a2.getDimension(R.styleable.Chip_chipMinHeight, 0.0f));
        b(a2.getDimension(R.styleable.Chip_chipCornerRadius, 0.0f));
        b(android.support.design.e.a.a(this.H, a2, R.styleable.Chip_chipStrokeColor));
        c(a2.getDimension(R.styleable.Chip_chipStrokeWidth, 0.0f));
        c(android.support.design.e.a.a(this.H, a2, R.styleable.Chip_rippleColor));
        a(a2.getText(R.styleable.Chip_android_text));
        a(android.support.design.e.a.c(this.H, a2, R.styleable.Chip_android_textAppearance));
        switch (a2.getInt(R.styleable.Chip_android_ellipsize, 0)) {
            case 1:
                a(TruncateAt.START);
                break;
            case 2:
                a(TruncateAt.MIDDLE);
                break;
            case 3:
                a(TruncateAt.END);
                break;
        }
        b(a2.getBoolean(R.styleable.Chip_chipIconVisible, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") != null)) {
            b(a2.getBoolean(R.styleable.Chip_chipIconEnabled, false));
        }
        a(android.support.design.e.a.b(this.H, a2, R.styleable.Chip_chipIcon));
        d(android.support.design.e.a.a(this.H, a2, R.styleable.Chip_chipIconTint));
        d(a2.getDimension(R.styleable.Chip_chipIconSize, 0.0f));
        c(a2.getBoolean(R.styleable.Chip_closeIconVisible, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") != null)) {
            c(a2.getBoolean(R.styleable.Chip_closeIconEnabled, false));
        }
        b(android.support.design.e.a.b(this.H, a2, R.styleable.Chip_closeIcon));
        e(android.support.design.e.a.a(this.H, a2, R.styleable.Chip_closeIconTint));
        e(a2.getDimension(R.styleable.Chip_closeIconSize, 0.0f));
        d(a2.getBoolean(R.styleable.Chip_android_checkable, false));
        e(a2.getBoolean(R.styleable.Chip_checkedIconVisible, false));
        if (!(attributeSet == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") == null || attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") != null)) {
            e(a2.getBoolean(R.styleable.Chip_checkedIconEnabled, false));
        }
        c(android.support.design.e.a.b(this.H, a2, R.styleable.Chip_checkedIcon));
        a(h.a(this.H, a2, R.styleable.Chip_showMotionSpec));
        b(h.a(this.H, a2, R.styleable.Chip_hideMotionSpec));
        f(a2.getDimension(R.styleable.Chip_chipStartPadding, 0.0f));
        g(a2.getDimension(R.styleable.Chip_iconStartPadding, 0.0f));
        h(a2.getDimension(R.styleable.Chip_iconEndPadding, 0.0f));
        i(a2.getDimension(R.styleable.Chip_textStartPadding, 0.0f));
        j(a2.getDimension(R.styleable.Chip_textEndPadding, 0.0f));
        k(a2.getDimension(R.styleable.Chip_closeIconStartPadding, 0.0f));
        l(a2.getDimension(R.styleable.Chip_closeIconEndPadding, 0.0f));
        m(a2.getDimension(R.styleable.Chip_chipEndPadding, 0.0f));
        C(a2.getDimensionPixelSize(R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        a2.recycle();
    }

    public void a(boolean z2) {
        if (this.aa != z2) {
            this.aa = z2;
            S();
            onStateChange(getState());
        }
    }

    public void a(a aVar) {
        this.ac = new WeakReference<>(aVar);
    }

    /* access modifiers changed from: protected */
    public void a() {
        a aVar = (a) this.ac.get();
        if (aVar != null) {
            aVar.a();
        }
    }

    public void a(RectF rectF) {
        e(getBounds(), rectF);
    }

    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.z + b() + this.C + O() + this.D + P() + this.G), this.ah);
    }

    public int getIntrinsicHeight() {
        return (int) this.c;
    }

    private boolean K() {
        return this.l && this.m != null;
    }

    private boolean L() {
        return this.v && this.w != null && this.S;
    }

    private boolean M() {
        return this.p && this.f317q != null;
    }

    private boolean N() {
        return this.v && this.w != null && this.u;
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        if (K() || L()) {
            return this.A + this.o + this.B;
        }
        return 0.0f;
    }

    private float O() {
        if (!this.ad) {
            return this.ae;
        }
        this.ae = c(this.i);
        this.ad = false;
        return this.ae;
    }

    private float c(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.I.measureText(charSequence, 0, charSequence.length());
    }

    private float P() {
        if (M()) {
            return this.E + this.s + this.F;
        }
        return 0.0f;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && getAlpha() != 0) {
            int i2 = 0;
            if (this.U < 255) {
                i2 = android.support.design.b.a.a(canvas, (float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.U);
            }
            a(canvas, bounds);
            b(canvas, bounds);
            c(canvas, bounds);
            d(canvas, bounds);
            e(canvas, bounds);
            if (this.ag) {
                f(canvas, bounds);
            }
            g(canvas, bounds);
            h(canvas, bounds);
            if (this.U < 255) {
                canvas.restoreToCount(i2);
            }
        }
    }

    private void a(Canvas canvas, Rect rect) {
        this.J.setColor(this.O);
        this.J.setStyle(Style.FILL);
        this.J.setColorFilter(R());
        this.M.set(rect);
        canvas.drawRoundRect(this.M, this.d, this.d, this.J);
    }

    private void b(Canvas canvas, Rect rect) {
        if (this.f > 0.0f) {
            this.J.setColor(this.P);
            this.J.setStyle(Style.STROKE);
            this.J.setColorFilter(R());
            this.M.set(((float) rect.left) + (this.f / 2.0f), ((float) rect.top) + (this.f / 2.0f), ((float) rect.right) - (this.f / 2.0f), ((float) rect.bottom) - (this.f / 2.0f));
            float f2 = this.d - (this.f / 2.0f);
            canvas.drawRoundRect(this.M, f2, f2, this.J);
        }
    }

    private void c(Canvas canvas, Rect rect) {
        this.J.setColor(this.Q);
        this.J.setStyle(Style.FILL);
        this.M.set(rect);
        canvas.drawRoundRect(this.M, this.d, this.d, this.J);
    }

    private void d(Canvas canvas, Rect rect) {
        if (K()) {
            a(rect, this.M);
            float f2 = this.M.left;
            float f3 = this.M.top;
            canvas.translate(f2, f3);
            this.m.setBounds(0, 0, (int) this.M.width(), (int) this.M.height());
            this.m.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private void e(Canvas canvas, Rect rect) {
        if (L()) {
            a(rect, this.M);
            float f2 = this.M.left;
            float f3 = this.M.top;
            canvas.translate(f2, f3);
            this.w.setBounds(0, 0, (int) this.M.width(), (int) this.M.height());
            this.w.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private void f(Canvas canvas, Rect rect) {
        int i2;
        if (this.i != null) {
            Align a2 = a(rect, this.N);
            b(rect, this.M);
            if (this.j != null) {
                this.I.drawableState = getState();
                this.j.b(this.H, this.I, this.k);
            }
            this.I.setTextAlign(a2);
            boolean z2 = Math.round(O()) > Math.round(this.M.width());
            if (z2) {
                int save = canvas.save();
                canvas.clipRect(this.M);
                i2 = save;
            } else {
                i2 = 0;
            }
            CharSequence charSequence = this.i;
            if (z2 && this.af != null) {
                charSequence = TextUtils.ellipsize(this.i, this.I, this.M.width(), this.af);
            }
            canvas.drawText(charSequence, 0, charSequence.length(), this.N.x, this.N.y, this.I);
            if (z2) {
                canvas.restoreToCount(i2);
            }
        }
    }

    private void g(Canvas canvas, Rect rect) {
        if (M()) {
            c(rect, this.M);
            float f2 = this.M.left;
            float f3 = this.M.top;
            canvas.translate(f2, f3);
            this.f317q.setBounds(0, 0, (int) this.M.width(), (int) this.M.height());
            this.f317q.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private void h(Canvas canvas, Rect rect) {
        if (this.K != null) {
            this.K.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
            canvas.drawRect(rect, this.K);
            if (K() || L()) {
                a(rect, this.M);
                canvas.drawRect(this.M, this.K);
            }
            if (this.i != null) {
                canvas.drawLine((float) rect.left, rect.exactCenterY(), (float) rect.right, rect.exactCenterY(), this.K);
            }
            if (M()) {
                c(rect, this.M);
                canvas.drawRect(this.M, this.K);
            }
            this.K.setColor(ColorUtils.setAlphaComponent(SupportMenu.CATEGORY_MASK, 127));
            d(rect, this.M);
            canvas.drawRect(this.M, this.K);
            this.K.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            e(rect, this.M);
            canvas.drawRect(this.M, this.K);
        }
    }

    private void a(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (K() || L()) {
            float f2 = this.z + this.A;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = f2 + ((float) rect.left);
                rectF.right = rectF.left + this.o;
            } else {
                rectF.right = ((float) rect.right) - f2;
                rectF.left = rectF.right - this.o;
            }
            rectF.top = rect.exactCenterY() - (this.o / 2.0f);
            rectF.bottom = rectF.top + this.o;
        }
    }

    /* access modifiers changed from: 0000 */
    public Align a(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Align align = Align.LEFT;
        if (this.i != null) {
            float b2 = this.z + b() + this.C;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF.x = b2 + ((float) rect.left);
                align = Align.LEFT;
            } else {
                pointF.x = ((float) rect.right) - b2;
                align = Align.RIGHT;
            }
            pointF.y = ((float) rect.centerY()) - Q();
        }
        return align;
    }

    private float Q() {
        this.I.getFontMetrics(this.L);
        return (this.L.descent + this.L.ascent) / 2.0f;
    }

    private void b(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.i != null) {
            float b2 = this.z + b() + this.C;
            float P2 = this.G + P() + this.D;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = b2 + ((float) rect.left);
                rectF.right = ((float) rect.right) - P2;
            } else {
                rectF.left = P2 + ((float) rect.left);
                rectF.right = ((float) rect.right) - b2;
            }
            rectF.top = (float) rect.top;
            rectF.bottom = (float) rect.bottom;
        }
    }

    private void c(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (M()) {
            float f2 = this.G + this.F;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = ((float) rect.right) - f2;
                rectF.left = rectF.right - this.s;
            } else {
                rectF.left = f2 + ((float) rect.left);
                rectF.right = rectF.left + this.s;
            }
            rectF.top = rect.exactCenterY() - (this.s / 2.0f);
            rectF.bottom = rectF.top + this.s;
        }
    }

    private void d(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (M()) {
            float f2 = this.G + this.F + this.s + this.E + this.D;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = ((float) rect.right) - f2;
            } else {
                rectF.left = f2 + ((float) rect.left);
            }
        }
    }

    private void e(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (M()) {
            float f2 = this.G + this.F + this.s + this.E + this.D;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = (float) rect.right;
                rectF.left = rectF.right - f2;
            } else {
                rectF.left = (float) rect.left;
                rectF.right = f2 + ((float) rect.left);
            }
            rectF.top = (float) rect.top;
            rectF.bottom = (float) rect.bottom;
        }
    }

    public boolean isStateful() {
        if (f(this.b) || f(this.e) || ((this.aa && f(this.ab)) || b(this.j) || N() || d(this.m) || d(this.w) || f(this.X))) {
            return true;
        }
        return false;
    }

    public boolean c() {
        return d(this.f317q);
    }

    public boolean a(int[] iArr) {
        if (!Arrays.equals(this.Z, iArr)) {
            this.Z = iArr;
            if (M()) {
                return a(getState(), iArr);
            }
        }
        return false;
    }

    public int[] d() {
        return this.Z;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        return a(iArr, d());
    }

    private boolean a(int[] iArr, int[] iArr2) {
        boolean z2;
        int i2;
        int i3;
        boolean z3;
        boolean z4;
        boolean z5;
        int i4 = 0;
        boolean z6 = true;
        boolean onStateChange = super.onStateChange(iArr);
        int i5 = this.b != null ? this.b.getColorForState(iArr, this.O) : 0;
        if (this.O != i5) {
            this.O = i5;
            z2 = true;
        } else {
            z2 = onStateChange;
        }
        if (this.e != null) {
            i2 = this.e.getColorForState(iArr, this.P);
        } else {
            i2 = 0;
        }
        if (this.P != i2) {
            this.P = i2;
            z2 = true;
        }
        if (this.ab != null) {
            i3 = this.ab.getColorForState(iArr, this.Q);
        } else {
            i3 = 0;
        }
        if (this.Q != i3) {
            this.Q = i3;
            if (this.aa) {
                z2 = true;
            }
        }
        int i6 = (this.j == null || this.j.b == null) ? 0 : this.j.b.getColorForState(iArr, this.R);
        if (this.R != i6) {
            this.R = i6;
            z2 = true;
        }
        if (!a(getState(), 16842912) || !this.u) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (this.S == z3 || this.w == null) {
            z4 = z2;
            z5 = false;
        } else {
            float b2 = b();
            this.S = z3;
            if (b2 != b()) {
                z5 = true;
                z4 = true;
            } else {
                z5 = false;
                z4 = true;
            }
        }
        if (this.X != null) {
            i4 = this.X.getColorForState(iArr, this.T);
        }
        if (this.T != i4) {
            this.T = i4;
            this.W = android.support.design.c.a.a(this, this.X, this.Y);
        } else {
            z6 = z4;
        }
        if (d(this.m)) {
            z6 |= this.m.setState(iArr);
        }
        if (d(this.w)) {
            z6 |= this.w.setState(iArr);
        }
        if (d(this.f317q)) {
            z6 |= this.f317q.setState(iArr2);
        }
        if (z6) {
            invalidateSelf();
        }
        if (z5) {
            a();
        }
        return z6;
    }

    private static boolean f(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private static boolean d(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private static boolean b(b bVar) {
        return (bVar == null || bVar.b == null || !bVar.b.isStateful()) ? false : true;
    }

    @TargetApi(23)
    public boolean onLayoutDirectionChanged(int i2) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i2);
        if (K()) {
            onLayoutDirectionChanged |= this.m.setLayoutDirection(i2);
        }
        if (L()) {
            onLayoutDirectionChanged |= this.w.setLayoutDirection(i2);
        }
        if (M()) {
            onLayoutDirectionChanged |= this.f317q.setLayoutDirection(i2);
        }
        if (onLayoutDirectionChanged) {
            invalidateSelf();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i2) {
        boolean onLevelChange = super.onLevelChange(i2);
        if (K()) {
            onLevelChange |= this.m.setLevel(i2);
        }
        if (L()) {
            onLevelChange |= this.w.setLevel(i2);
        }
        if (M()) {
            onLevelChange |= this.f317q.setLevel(i2);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    public boolean setVisible(boolean z2, boolean z3) {
        boolean visible = super.setVisible(z2, z3);
        if (K()) {
            visible |= this.m.setVisible(z2, z3);
        }
        if (L()) {
            visible |= this.w.setVisible(z2, z3);
        }
        if (M()) {
            visible |= this.f317q.setVisible(z2, z3);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public void setAlpha(int i2) {
        if (this.U != i2) {
            this.U = i2;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.U;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.V != colorFilter) {
            this.V = colorFilter;
            invalidateSelf();
        }
    }

    public ColorFilter getColorFilter() {
        return this.V;
    }

    public void setTintList(ColorStateList colorStateList) {
        if (this.X != colorStateList) {
            this.X = colorStateList;
            onStateChange(getState());
        }
    }

    public void setTintMode(Mode mode) {
        if (this.Y != mode) {
            this.Y = mode;
            this.W = android.support.design.c.a.a(this, this.X, mode);
            invalidateSelf();
        }
    }

    public int getOpacity() {
        return -3;
    }

    @TargetApi(21)
    public void getOutline(Outline outline) {
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.d);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.d);
        }
        outline.setAlpha(((float) getAlpha()) / 255.0f);
    }

    public void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    private void e(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void f(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.f317q) {
                if (drawable.isStateful()) {
                    drawable.setState(d());
                }
                DrawableCompat.setTintList(drawable, this.r);
            } else if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    private ColorFilter R() {
        return this.V != null ? this.V : this.W;
    }

    private void S() {
        this.ab = this.aa ? android.support.design.f.a.a(this.g) : null;
    }

    private static boolean a(int[] iArr, int i2) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public ColorStateList e() {
        return this.b;
    }

    public void a(int i2) {
        a(android.support.v7.a.a.a.a(this.H, i2));
    }

    public void a(ColorStateList colorStateList) {
        if (this.b != colorStateList) {
            this.b = colorStateList;
            onStateChange(getState());
        }
    }

    public float f() {
        return this.c;
    }

    public void b(int i2) {
        a(this.H.getResources().getDimension(i2));
    }

    public void a(float f2) {
        if (this.c != f2) {
            this.c = f2;
            invalidateSelf();
            a();
        }
    }

    public float g() {
        return this.d;
    }

    public void c(int i2) {
        b(this.H.getResources().getDimension(i2));
    }

    public void b(float f2) {
        if (this.d != f2) {
            this.d = f2;
            invalidateSelf();
        }
    }

    public ColorStateList h() {
        return this.e;
    }

    public void d(int i2) {
        b(android.support.v7.a.a.a.a(this.H, i2));
    }

    public void b(ColorStateList colorStateList) {
        if (this.e != colorStateList) {
            this.e = colorStateList;
            onStateChange(getState());
        }
    }

    public float i() {
        return this.f;
    }

    public void e(int i2) {
        c(this.H.getResources().getDimension(i2));
    }

    public void c(float f2) {
        if (this.f != f2) {
            this.f = f2;
            this.J.setStrokeWidth(f2);
            invalidateSelf();
        }
    }

    public ColorStateList j() {
        return this.g;
    }

    public void f(int i2) {
        c(android.support.v7.a.a.a.a(this.H, i2));
    }

    public void c(ColorStateList colorStateList) {
        if (this.g != colorStateList) {
            this.g = colorStateList;
            S();
            onStateChange(getState());
        }
    }

    public CharSequence k() {
        return this.h;
    }

    public void a(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (this.h != charSequence) {
            this.h = charSequence;
            this.i = BidiFormatter.getInstance().unicodeWrap(charSequence);
            this.ad = true;
            invalidateSelf();
            a();
        }
    }

    public b l() {
        return this.j;
    }

    public void g(int i2) {
        a(new b(this.H, i2));
    }

    public void a(b bVar) {
        if (this.j != bVar) {
            this.j = bVar;
            if (bVar != null) {
                bVar.c(this.H, this.I, this.k);
                this.ad = true;
            }
            onStateChange(getState());
            a();
        }
    }

    public TruncateAt m() {
        return this.af;
    }

    public void a(TruncateAt truncateAt) {
        this.af = truncateAt;
    }

    public boolean n() {
        return this.l;
    }

    public void h(int i2) {
        b(this.H.getResources().getBoolean(i2));
    }

    public void b(boolean z2) {
        if (this.l != z2) {
            boolean K2 = K();
            this.l = z2;
            boolean K3 = K();
            if (K2 != K3) {
                if (K3) {
                    f(this.m);
                } else {
                    e(this.m);
                }
                invalidateSelf();
                a();
            }
        }
    }

    public Drawable o() {
        if (this.m != null) {
            return DrawableCompat.unwrap(this.m);
        }
        return null;
    }

    public void i(int i2) {
        a(android.support.v7.a.a.a.b(this.H, i2));
    }

    public void a(Drawable drawable) {
        Drawable o2 = o();
        if (o2 != drawable) {
            float b2 = b();
            this.m = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float b3 = b();
            e(o2);
            if (K()) {
                f(this.m);
            }
            invalidateSelf();
            if (b2 != b3) {
                a();
            }
        }
    }

    public ColorStateList p() {
        return this.n;
    }

    public void j(int i2) {
        d(android.support.v7.a.a.a.a(this.H, i2));
    }

    public void d(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            if (K()) {
                DrawableCompat.setTintList(this.m, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float q() {
        return this.o;
    }

    public void k(int i2) {
        d(this.H.getResources().getDimension(i2));
    }

    public void d(float f2) {
        if (this.o != f2) {
            float b2 = b();
            this.o = f2;
            float b3 = b();
            invalidateSelf();
            if (b2 != b3) {
                a();
            }
        }
    }

    public boolean r() {
        return this.p;
    }

    public void l(int i2) {
        c(this.H.getResources().getBoolean(i2));
    }

    public void c(boolean z2) {
        if (this.p != z2) {
            boolean M2 = M();
            this.p = z2;
            boolean M3 = M();
            if (M2 != M3) {
                if (M3) {
                    f(this.f317q);
                } else {
                    e(this.f317q);
                }
                invalidateSelf();
                a();
            }
        }
    }

    public Drawable s() {
        if (this.f317q != null) {
            return DrawableCompat.unwrap(this.f317q);
        }
        return null;
    }

    public void m(int i2) {
        b(android.support.v7.a.a.a.b(this.H, i2));
    }

    public void b(Drawable drawable) {
        Drawable s2 = s();
        if (s2 != drawable) {
            float P2 = P();
            this.f317q = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float P3 = P();
            e(s2);
            if (M()) {
                f(this.f317q);
            }
            invalidateSelf();
            if (P2 != P3) {
                a();
            }
        }
    }

    public ColorStateList t() {
        return this.r;
    }

    public void n(int i2) {
        e(android.support.v7.a.a.a.a(this.H, i2));
    }

    public void e(ColorStateList colorStateList) {
        if (this.r != colorStateList) {
            this.r = colorStateList;
            if (M()) {
                DrawableCompat.setTintList(this.f317q, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float u() {
        return this.s;
    }

    public void o(int i2) {
        e(this.H.getResources().getDimension(i2));
    }

    public void e(float f2) {
        if (this.s != f2) {
            this.s = f2;
            invalidateSelf();
            if (M()) {
                a();
            }
        }
    }

    public void b(CharSequence charSequence) {
        if (this.t != charSequence) {
            this.t = BidiFormatter.getInstance().unicodeWrap(charSequence);
            invalidateSelf();
        }
    }

    public CharSequence v() {
        return this.t;
    }

    public boolean w() {
        return this.u;
    }

    public void p(int i2) {
        d(this.H.getResources().getBoolean(i2));
    }

    public void d(boolean z2) {
        if (this.u != z2) {
            this.u = z2;
            float b2 = b();
            if (!z2 && this.S) {
                this.S = false;
            }
            float b3 = b();
            invalidateSelf();
            if (b2 != b3) {
                a();
            }
        }
    }

    public boolean x() {
        return this.v;
    }

    public void q(int i2) {
        e(this.H.getResources().getBoolean(i2));
    }

    public void e(boolean z2) {
        if (this.v != z2) {
            boolean L2 = L();
            this.v = z2;
            boolean L3 = L();
            if (L2 != L3) {
                if (L3) {
                    f(this.w);
                } else {
                    e(this.w);
                }
                invalidateSelf();
                a();
            }
        }
    }

    public Drawable y() {
        return this.w;
    }

    public void r(int i2) {
        c(android.support.v7.a.a.a.b(this.H, i2));
    }

    public void c(Drawable drawable) {
        if (this.w != drawable) {
            float b2 = b();
            this.w = drawable;
            float b3 = b();
            e(this.w);
            f(this.w);
            invalidateSelf();
            if (b2 != b3) {
                a();
            }
        }
    }

    public h z() {
        return this.x;
    }

    public void s(int i2) {
        a(h.a(this.H, i2));
    }

    public void a(h hVar) {
        this.x = hVar;
    }

    public h A() {
        return this.y;
    }

    public void t(int i2) {
        b(h.a(this.H, i2));
    }

    public void b(h hVar) {
        this.y = hVar;
    }

    public float B() {
        return this.z;
    }

    public void u(int i2) {
        f(this.H.getResources().getDimension(i2));
    }

    public void f(float f2) {
        if (this.z != f2) {
            this.z = f2;
            invalidateSelf();
            a();
        }
    }

    public float C() {
        return this.A;
    }

    public void v(int i2) {
        g(this.H.getResources().getDimension(i2));
    }

    public void g(float f2) {
        if (this.A != f2) {
            float b2 = b();
            this.A = f2;
            float b3 = b();
            invalidateSelf();
            if (b2 != b3) {
                a();
            }
        }
    }

    public float D() {
        return this.B;
    }

    public void w(int i2) {
        h(this.H.getResources().getDimension(i2));
    }

    public void h(float f2) {
        if (this.B != f2) {
            float b2 = b();
            this.B = f2;
            float b3 = b();
            invalidateSelf();
            if (b2 != b3) {
                a();
            }
        }
    }

    public float E() {
        return this.C;
    }

    public void x(int i2) {
        i(this.H.getResources().getDimension(i2));
    }

    public void i(float f2) {
        if (this.C != f2) {
            this.C = f2;
            invalidateSelf();
            a();
        }
    }

    public float F() {
        return this.D;
    }

    public void y(int i2) {
        j(this.H.getResources().getDimension(i2));
    }

    public void j(float f2) {
        if (this.D != f2) {
            this.D = f2;
            invalidateSelf();
            a();
        }
    }

    public float G() {
        return this.E;
    }

    public void z(int i2) {
        k(this.H.getResources().getDimension(i2));
    }

    public void k(float f2) {
        if (this.E != f2) {
            this.E = f2;
            invalidateSelf();
            if (M()) {
                a();
            }
        }
    }

    public float H() {
        return this.F;
    }

    public void A(int i2) {
        l(this.H.getResources().getDimension(i2));
    }

    public void l(float f2) {
        if (this.F != f2) {
            this.F = f2;
            invalidateSelf();
            if (M()) {
                a();
            }
        }
    }

    public float I() {
        return this.G;
    }

    public void B(int i2) {
        m(this.H.getResources().getDimension(i2));
    }

    public void m(float f2) {
        if (this.G != f2) {
            this.G = f2;
            invalidateSelf();
            a();
        }
    }

    public void C(int i2) {
        this.ah = i2;
    }

    /* access modifiers changed from: 0000 */
    public boolean J() {
        return this.ag;
    }

    /* access modifiers changed from: 0000 */
    public void f(boolean z2) {
        this.ag = z2;
    }
}
