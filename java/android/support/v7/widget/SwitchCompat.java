package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.c.a;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;

public class SwitchCompat extends CompoundButton {
    private static final int[] N = {16842912};
    private static final Property<SwitchCompat, Float> c = new Property<SwitchCompat, Float>(Float.class, "thumbPos") {
        /* renamed from: a */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.a);
        }

        /* renamed from: a */
        public void set(SwitchCompat switchCompat, Float f) {
            switchCompat.setThumbPosition(f.floatValue());
        }
    };
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final TextPaint H;
    private ColorStateList I;
    private Layout J;
    private Layout K;
    private TransformationMethod L;
    private final Rect M;
    float a;
    ObjectAnimator b;
    private Drawable d;
    private ColorStateList e;
    private Mode f;
    private boolean g;
    private boolean h;
    private Drawable i;
    private ColorStateList j;
    private Mode k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private boolean f357q;
    private CharSequence r;
    private CharSequence s;
    private boolean t;
    private int u;
    private int v;
    private float w;
    private float x;
    private VelocityTracker y;
    private int z;

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = false;
        this.y = VelocityTracker.obtain();
        this.M = new Rect();
        this.H = new TextPaint(1);
        Resources resources = getResources();
        this.H.density = resources.getDisplayMetrics().density;
        at a2 = at.a(context, attributeSet, R.styleable.SwitchCompat, i2, 0);
        this.d = a2.a(R.styleable.SwitchCompat_android_thumb);
        if (this.d != null) {
            this.d.setCallback(this);
        }
        this.i = a2.a(R.styleable.SwitchCompat_track);
        if (this.i != null) {
            this.i.setCallback(this);
        }
        this.r = a2.c(R.styleable.SwitchCompat_android_textOn);
        this.s = a2.c(R.styleable.SwitchCompat_android_textOff);
        this.t = a2.a(R.styleable.SwitchCompat_showText, true);
        this.n = a2.e(R.styleable.SwitchCompat_thumbTextPadding, 0);
        this.o = a2.e(R.styleable.SwitchCompat_switchMinWidth, 0);
        this.p = a2.e(R.styleable.SwitchCompat_switchPadding, 0);
        this.f357q = a2.a(R.styleable.SwitchCompat_splitTrack, false);
        ColorStateList e2 = a2.e(R.styleable.SwitchCompat_thumbTint);
        if (e2 != null) {
            this.e = e2;
            this.g = true;
        }
        Mode a3 = v.a(a2.a(R.styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.f != a3) {
            this.f = a3;
            this.h = true;
        }
        if (this.g || this.h) {
            b();
        }
        ColorStateList e3 = a2.e(R.styleable.SwitchCompat_trackTint);
        if (e3 != null) {
            this.j = e3;
            this.l = true;
        }
        Mode a4 = v.a(a2.a(R.styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.k != a4) {
            this.k = a4;
            this.m = true;
        }
        if (this.l || this.m) {
            a();
        }
        int g2 = a2.g(R.styleable.SwitchCompat_switchTextAppearance, 0);
        if (g2 != 0) {
            a(context, g2);
        }
        a2.a();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.v = viewConfiguration.getScaledTouchSlop();
        this.z = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void a(Context context, int i2) {
        at a2 = at.a(context, i2, R.styleable.TextAppearance);
        ColorStateList e2 = a2.e(R.styleable.TextAppearance_android_textColor);
        if (e2 != null) {
            this.I = e2;
        } else {
            this.I = getTextColors();
        }
        int e3 = a2.e(R.styleable.TextAppearance_android_textSize, 0);
        if (!(e3 == 0 || ((float) e3) == this.H.getTextSize())) {
            this.H.setTextSize((float) e3);
            requestLayout();
        }
        a(a2.a(R.styleable.TextAppearance_android_typeface, -1), a2.a(R.styleable.TextAppearance_android_textStyle, -1));
        if (a2.a(R.styleable.TextAppearance_textAllCaps, false)) {
            this.L = new a(getContext());
        } else {
            this.L = null;
        }
        a2.a();
    }

    private void a(int i2, int i3) {
        Typeface typeface = null;
        switch (i2) {
            case 1:
                typeface = Typeface.SANS_SERIF;
                break;
            case 2:
                typeface = Typeface.SERIF;
                break;
            case 3:
                typeface = Typeface.MONOSPACE;
                break;
        }
        a(typeface, i3);
    }

    public void a(Typeface typeface, int i2) {
        Typeface create;
        int i3;
        float f2;
        boolean z2 = false;
        if (i2 > 0) {
            if (typeface == null) {
                create = Typeface.defaultFromStyle(i2);
            } else {
                create = Typeface.create(typeface, i2);
            }
            setSwitchTypeface(create);
            if (create != null) {
                i3 = create.getStyle();
            } else {
                i3 = 0;
            }
            int i4 = (i3 ^ -1) & i2;
            TextPaint textPaint = this.H;
            if ((i4 & 1) != 0) {
                z2 = true;
            }
            textPaint.setFakeBoldText(z2);
            TextPaint textPaint2 = this.H;
            if ((i4 & 2) != 0) {
                f2 = -0.25f;
            } else {
                f2 = 0.0f;
            }
            textPaint2.setTextSkewX(f2);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int i2) {
        this.p = i2;
        requestLayout();
    }

    public int getSwitchPadding() {
        return this.p;
    }

    public void setSwitchMinWidth(int i2) {
        this.o = i2;
        requestLayout();
    }

    public int getSwitchMinWidth() {
        return this.o;
    }

    public void setThumbTextPadding(int i2) {
        this.n = i2;
        requestLayout();
    }

    public int getThumbTextPadding() {
        return this.n;
    }

    public void setTrackDrawable(Drawable drawable) {
        if (this.i != null) {
            this.i.setCallback(null);
        }
        this.i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(android.support.v7.a.a.a.b(getContext(), i2));
    }

    public Drawable getTrackDrawable() {
        return this.i;
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.l = true;
        a();
    }

    public ColorStateList getTrackTintList() {
        return this.j;
    }

    public void setTrackTintMode(Mode mode) {
        this.k = mode;
        this.m = true;
        a();
    }

    public Mode getTrackTintMode() {
        return this.k;
    }

    private void a() {
        if (this.i == null) {
            return;
        }
        if (this.l || this.m) {
            this.i = this.i.mutate();
            if (this.l) {
                DrawableCompat.setTintList(this.i, this.j);
            }
            if (this.m) {
                DrawableCompat.setTintMode(this.i, this.k);
            }
            if (this.i.isStateful()) {
                this.i.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        if (this.d != null) {
            this.d.setCallback(null);
        }
        this.d = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(android.support.v7.a.a.a.b(getContext(), i2));
    }

    public Drawable getThumbDrawable() {
        return this.d;
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.e = colorStateList;
        this.g = true;
        b();
    }

    public ColorStateList getThumbTintList() {
        return this.e;
    }

    public void setThumbTintMode(Mode mode) {
        this.f = mode;
        this.h = true;
        b();
    }

    public Mode getThumbTintMode() {
        return this.f;
    }

    private void b() {
        if (this.d == null) {
            return;
        }
        if (this.g || this.h) {
            this.d = this.d.mutate();
            if (this.g) {
                DrawableCompat.setTintList(this.d, this.e);
            }
            if (this.h) {
                DrawableCompat.setTintMode(this.d, this.f);
            }
            if (this.d.isStateful()) {
                this.d.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean z2) {
        this.f357q = z2;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.f357q;
    }

    public CharSequence getTextOn() {
        return this.r;
    }

    public void setTextOn(CharSequence charSequence) {
        this.r = charSequence;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.s;
    }

    public void setTextOff(CharSequence charSequence) {
        this.s = charSequence;
        requestLayout();
    }

    public void setShowText(boolean z2) {
        if (this.t != z2) {
            this.t = z2;
            requestLayout();
        }
    }

    public boolean getShowText() {
        return this.t;
    }

    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (this.t) {
            if (this.J == null) {
                this.J = a(this.r);
            }
            if (this.K == null) {
                this.K = a(this.s);
            }
        }
        Rect rect = this.M;
        if (this.d != null) {
            this.d.getPadding(rect);
            i5 = (this.d.getIntrinsicWidth() - rect.left) - rect.right;
            i4 = this.d.getIntrinsicHeight();
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (this.t) {
            i6 = Math.max(this.J.getWidth(), this.K.getWidth()) + (this.n * 2);
        } else {
            i6 = 0;
        }
        this.C = Math.max(i6, i5);
        if (this.i != null) {
            this.i.getPadding(rect);
            i7 = this.i.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i8 = rect.left;
        int i9 = rect.right;
        if (this.d != null) {
            Rect a2 = v.a(this.d);
            i8 = Math.max(i8, a2.left);
            i9 = Math.max(i9, a2.right);
        }
        int max = Math.max(this.o, i9 + i8 + (this.C * 2));
        int max2 = Math.max(i7, i4);
        this.A = max;
        this.B = max2;
        super.onMeasure(i2, i3);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.r : this.s;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    private Layout a(CharSequence charSequence) {
        int i2;
        CharSequence charSequence2 = this.L != null ? this.L.getTransformation(charSequence, this) : charSequence;
        TextPaint textPaint = this.H;
        if (charSequence2 != null) {
            i2 = (int) Math.ceil((double) Layout.getDesiredWidth(charSequence2, this.H));
        } else {
            i2 = 0;
        }
        return new StaticLayout(charSequence2, textPaint, i2, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private boolean a(float f2, float f3) {
        if (this.d == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.d.getPadding(this.M);
        int i2 = this.E - this.v;
        int i3 = (thumbOffset + this.D) - this.v;
        int i4 = this.C + i3 + this.M.left + this.M.right + this.v;
        int i5 = this.G + this.v;
        if (f2 <= ((float) i3) || f2 >= ((float) i4) || f3 <= ((float) i2) || f3 >= ((float) i5)) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.y.addMovement(motionEvent);
        switch (motionEvent.getActionMasked()) {
            case 0:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                if (isEnabled() && a(x2, y2)) {
                    this.u = 1;
                    this.w = x2;
                    this.x = y2;
                    break;
                }
            case 1:
            case 3:
                if (this.u != 2) {
                    this.u = 0;
                    this.y.clear();
                    break;
                } else {
                    b(motionEvent);
                    super.onTouchEvent(motionEvent);
                    return true;
                }
            case 2:
                switch (this.u) {
                    case 1:
                        float x3 = motionEvent.getX();
                        float y3 = motionEvent.getY();
                        if (Math.abs(x3 - this.w) > ((float) this.v) || Math.abs(y3 - this.x) > ((float) this.v)) {
                            this.u = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.w = x3;
                            this.x = y3;
                            return true;
                        }
                    case 2:
                        float x4 = motionEvent.getX();
                        int thumbScrollRange = getThumbScrollRange();
                        float f2 = x4 - this.w;
                        float f3 = thumbScrollRange != 0 ? f2 / ((float) thumbScrollRange) : f2 > 0.0f ? 1.0f : -1.0f;
                        if (bb.a(this)) {
                            f3 = -f3;
                        }
                        float a2 = a(f3 + this.a, 0.0f, 1.0f);
                        if (a2 != this.a) {
                            this.w = x4;
                            setThumbPosition(a2);
                        }
                        return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void b(MotionEvent motionEvent) {
        boolean z2 = true;
        this.u = 0;
        boolean z3 = motionEvent.getAction() == 1 && isEnabled();
        boolean isChecked = isChecked();
        if (z3) {
            this.y.computeCurrentVelocity(1000);
            float xVelocity = this.y.getXVelocity();
            if (Math.abs(xVelocity) <= ((float) this.z)) {
                z2 = getTargetCheckedState();
            } else if (bb.a(this)) {
                if (xVelocity >= 0.0f) {
                    z2 = false;
                }
            } else if (xVelocity <= 0.0f) {
                z2 = false;
            }
        } else {
            z2 = isChecked;
        }
        if (z2 != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z2);
        a(motionEvent);
    }

    private void a(boolean z2) {
        this.b = ObjectAnimator.ofFloat(this, c, new float[]{z2 ? 1.0f : 0.0f});
        this.b.setDuration(250);
        if (VERSION.SDK_INT >= 18) {
            this.b.setAutoCancel(true);
        }
        this.b.start();
    }

    private void c() {
        if (this.b != null) {
            this.b.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.a > 0.5f;
    }

    /* access modifiers changed from: 0000 */
    public void setThumbPosition(float f2) {
        this.a = f2;
        invalidate();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public void setChecked(boolean z2) {
        super.setChecked(z2);
        boolean isChecked = isChecked();
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this)) {
            c();
            setThumbPosition(isChecked ? 1.0f : 0.0f);
            return;
        }
        a(isChecked);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int height;
        int i9;
        int i10 = 0;
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.d != null) {
            Rect rect = this.M;
            if (this.i != null) {
                this.i.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect a2 = v.a(this.d);
            i6 = Math.max(0, a2.left - rect.left);
            i10 = Math.max(0, a2.right - rect.right);
        } else {
            i6 = 0;
        }
        if (bb.a(this)) {
            int paddingLeft = getPaddingLeft() + i6;
            i8 = ((this.A + paddingLeft) - i6) - i10;
            i7 = paddingLeft;
        } else {
            int width = (getWidth() - getPaddingRight()) - i10;
            i7 = i10 + i6 + (width - this.A);
            i8 = width;
        }
        switch (getGravity() & 112) {
            case 16:
                i9 = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.B / 2);
                height = this.B + i9;
                break;
            case 80:
                height = getHeight() - getPaddingBottom();
                i9 = height - this.B;
                break;
            default:
                i9 = getPaddingTop();
                height = this.B + i9;
                break;
        }
        this.D = i7;
        this.E = i9;
        this.G = height;
        this.F = i8;
    }

    public void draw(Canvas canvas) {
        Rect rect;
        int i2;
        int i3;
        int i4;
        Rect rect2 = this.M;
        int i5 = this.D;
        int i6 = this.E;
        int i7 = this.F;
        int i8 = this.G;
        int thumbOffset = i5 + getThumbOffset();
        if (this.d != null) {
            rect = v.a(this.d);
        } else {
            rect = v.a;
        }
        if (this.i != null) {
            this.i.getPadding(rect2);
            int i9 = rect2.left + thumbOffset;
            if (rect != null) {
                if (rect.left > rect2.left) {
                    i5 += rect.left - rect2.left;
                }
                if (rect.top > rect2.top) {
                    i4 = (rect.top - rect2.top) + i6;
                } else {
                    i4 = i6;
                }
                if (rect.right > rect2.right) {
                    i7 -= rect.right - rect2.right;
                }
                i3 = rect.bottom > rect2.bottom ? i8 - (rect.bottom - rect2.bottom) : i8;
            } else {
                i3 = i8;
                i4 = i6;
            }
            this.i.setBounds(i5, i4, i7, i3);
            i2 = i9;
        } else {
            i2 = thumbOffset;
        }
        if (this.d != null) {
            this.d.getPadding(rect2);
            int i10 = i2 - rect2.left;
            int i11 = i2 + this.C + rect2.right;
            this.d.setBounds(i10, i6, i11, i8);
            Drawable background = getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, i10, i6, i11, i8);
            }
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.M;
        Drawable drawable = this.i;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.E + rect.top;
        int i3 = this.G - rect.bottom;
        Drawable drawable2 = this.d;
        if (drawable != null) {
            if (!this.f357q || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect a2 = v.a(drawable2);
                drawable2.copyBounds(rect);
                rect.left += a2.left;
                rect.right -= a2.right;
                int save = canvas.save();
                canvas.clipRect(rect, Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.J : this.K;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            if (this.I != null) {
                this.H.setColor(this.I.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.right + bounds.left;
            } else {
                width = getWidth();
            }
            canvas.translate((float) ((width / 2) - (layout.getWidth() / 2)), (float) (((i2 + i3) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public int getCompoundPaddingLeft() {
        if (!bb.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        if (!TextUtils.isEmpty(getText())) {
            return compoundPaddingLeft + this.p;
        }
        return compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (bb.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        if (!TextUtils.isEmpty(getText())) {
            return compoundPaddingRight + this.p;
        }
        return compoundPaddingRight;
    }

    private int getThumbOffset() {
        float f2;
        if (bb.a(this)) {
            f2 = 1.0f - this.a;
        } else {
            f2 = this.a;
        }
        return (int) ((f2 * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        Rect rect;
        if (this.i == null) {
            return 0;
        }
        Rect rect2 = this.M;
        this.i.getPadding(rect2);
        if (this.d != null) {
            rect = v.a(this.d);
        } else {
            rect = v.a;
        }
        return ((((this.A - this.C) - rect2.left) - rect2.right) - rect.left) - rect.right;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, N);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        boolean z2 = false;
        Drawable drawable = this.d;
        if (drawable != null && drawable.isStateful()) {
            z2 = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.i;
        if (drawable2 != null && drawable2.isStateful()) {
            z2 |= drawable2.setState(drawableState);
        }
        if (z2) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float f2, float f3) {
        if (VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        if (this.d != null) {
            DrawableCompat.setHotspot(this.d, f2, f3);
        }
        if (this.i != null) {
            DrawableCompat.setHotspot(this.i, f2, f3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.d || drawable == this.i;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.d != null) {
            this.d.jumpToCurrentState();
        }
        if (this.i != null) {
            this.i.jumpToCurrentState();
        }
        if (this.b != null && this.b.isStarted()) {
            this.b.end();
            this.b = null;
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.r : this.s;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text).append(' ').append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    private static float a(float f2, float f3, float f4) {
        if (f2 < f3) {
            return f3;
        }
        return f2 > f4 ? f4 : f2;
    }
}
