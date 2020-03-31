package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.R;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* compiled from: AppCompatTextHelper */
class k {
    private final TextView a;
    private ar b;
    private ar c;
    private ar d;
    private ar e;
    private ar f;
    private ar g;
    private final l h;
    private int i = 0;
    private Typeface j;
    private boolean k;

    k(TextView textView) {
        this.a = textView;
        this.h = new l(this.a);
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"NewApi"})
    public void a(AttributeSet attributeSet, int i2) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        boolean z;
        boolean z2;
        ColorStateList colorStateList3 = null;
        Context context = this.a.getContext();
        f a2 = f.a();
        at a3 = at.a(context, attributeSet, R.styleable.AppCompatTextHelper, i2, 0);
        int g2 = a3.g(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (a3.g(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.b = a(context, a2, a3.g(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a3.g(R.styleable.AppCompatTextHelper_android_drawableTop)) {
            this.c = a(context, a2, a3.g(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a3.g(R.styleable.AppCompatTextHelper_android_drawableRight)) {
            this.d = a(context, a2, a3.g(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a3.g(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.e = a(context, a2, a3.g(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (VERSION.SDK_INT >= 17) {
            if (a3.g(R.styleable.AppCompatTextHelper_android_drawableStart)) {
                this.f = a(context, a2, a3.g(R.styleable.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (a3.g(R.styleable.AppCompatTextHelper_android_drawableEnd)) {
                this.g = a(context, a2, a3.g(R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        a3.a();
        boolean z3 = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (g2 != -1) {
            at a4 = at.a(context, g2, R.styleable.TextAppearance);
            if (z3 || !a4.g(R.styleable.TextAppearance_textAllCaps)) {
                z = false;
                z2 = false;
            } else {
                z2 = a4.a(R.styleable.TextAppearance_textAllCaps, false);
                z = true;
            }
            a(context, a4);
            if (VERSION.SDK_INT < 23) {
                if (a4.g(R.styleable.TextAppearance_android_textColor)) {
                    colorStateList2 = a4.e(R.styleable.TextAppearance_android_textColor);
                } else {
                    colorStateList2 = null;
                }
                if (a4.g(R.styleable.TextAppearance_android_textColorHint)) {
                    colorStateList = a4.e(R.styleable.TextAppearance_android_textColorHint);
                } else {
                    colorStateList = null;
                }
                if (a4.g(R.styleable.TextAppearance_android_textColorLink)) {
                    colorStateList3 = a4.e(R.styleable.TextAppearance_android_textColorLink);
                }
            } else {
                colorStateList = null;
                colorStateList2 = null;
            }
            a4.a();
        } else {
            colorStateList = null;
            colorStateList2 = null;
            z = false;
            z2 = false;
        }
        at a5 = at.a(context, attributeSet, R.styleable.TextAppearance, i2, 0);
        if (!z3 && a5.g(R.styleable.TextAppearance_textAllCaps)) {
            z2 = a5.a(R.styleable.TextAppearance_textAllCaps, false);
            z = true;
        }
        if (VERSION.SDK_INT < 23) {
            if (a5.g(R.styleable.TextAppearance_android_textColor)) {
                colorStateList2 = a5.e(R.styleable.TextAppearance_android_textColor);
            }
            if (a5.g(R.styleable.TextAppearance_android_textColorHint)) {
                colorStateList = a5.e(R.styleable.TextAppearance_android_textColorHint);
            }
            if (a5.g(R.styleable.TextAppearance_android_textColorLink)) {
                colorStateList3 = a5.e(R.styleable.TextAppearance_android_textColorLink);
            }
        }
        if (VERSION.SDK_INT >= 28 && a5.g(R.styleable.TextAppearance_android_textSize) && a5.e(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        a(context, a5);
        a5.a();
        if (colorStateList2 != null) {
            this.a.setTextColor(colorStateList2);
        }
        if (colorStateList != null) {
            this.a.setHintTextColor(colorStateList);
        }
        if (colorStateList3 != null) {
            this.a.setLinkTextColor(colorStateList3);
        }
        if (!z3 && z) {
            a(z2);
        }
        if (this.j != null) {
            this.a.setTypeface(this.j, this.i);
        }
        this.h.a(attributeSet, i2);
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && this.h.a() != 0) {
            int[] e2 = this.h.e();
            if (e2.length > 0) {
                if (((float) this.a.getAutoSizeStepGranularity()) != -1.0f) {
                    this.a.setAutoSizeTextTypeUniformWithConfiguration(this.h.c(), this.h.d(), this.h.b(), 0);
                } else {
                    this.a.setAutoSizeTextTypeUniformWithPresetSizes(e2, 0);
                }
            }
        }
        at a6 = at.a(context, attributeSet, R.styleable.AppCompatTextView);
        int e3 = a6.e(R.styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
        int e4 = a6.e(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int e5 = a6.e(R.styleable.AppCompatTextView_lineHeight, -1);
        a6.a();
        if (e3 != -1) {
            TextViewCompat.setFirstBaselineToTopHeight(this.a, e3);
        }
        if (e4 != -1) {
            TextViewCompat.setLastBaselineToBottomHeight(this.a, e4);
        }
        if (e5 != -1) {
            TextViewCompat.setLineHeight(this.a, e5);
        }
    }

    private void a(Context context, at atVar) {
        boolean z = true;
        this.i = atVar.a(R.styleable.TextAppearance_android_textStyle, this.i);
        if (atVar.g(R.styleable.TextAppearance_android_fontFamily) || atVar.g(R.styleable.TextAppearance_fontFamily)) {
            this.j = null;
            int i2 = atVar.g(R.styleable.TextAppearance_fontFamily) ? R.styleable.TextAppearance_fontFamily : R.styleable.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.a);
                try {
                    this.j = atVar.a(i2, this.i, (FontCallback) new FontCallback() {
                        public void onFontRetrieved(Typeface typeface) {
                            k.this.a(weakReference, typeface);
                        }

                        public void onFontRetrievalFailed(int i) {
                        }
                    });
                    if (this.j != null) {
                        z = false;
                    }
                    this.k = z;
                } catch (NotFoundException | UnsupportedOperationException e2) {
                }
            }
            if (this.j == null) {
                String d2 = atVar.d(i2);
                if (d2 != null) {
                    this.j = Typeface.create(d2, this.i);
                }
            }
        } else if (atVar.g(R.styleable.TextAppearance_android_typeface)) {
            this.k = false;
            switch (atVar.a(R.styleable.TextAppearance_android_typeface, 1)) {
                case 1:
                    this.j = Typeface.SANS_SERIF;
                    return;
                case 2:
                    this.j = Typeface.SERIF;
                    return;
                case 3:
                    this.j = Typeface.MONOSPACE;
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.k) {
            this.j = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.i);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, int i2) {
        at a2 = at.a(context, i2, R.styleable.TextAppearance);
        if (a2.g(R.styleable.TextAppearance_textAllCaps)) {
            a(a2.a(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (VERSION.SDK_INT < 23 && a2.g(R.styleable.TextAppearance_android_textColor)) {
            ColorStateList e2 = a2.e(R.styleable.TextAppearance_android_textColor);
            if (e2 != null) {
                this.a.setTextColor(e2);
            }
        }
        if (a2.g(R.styleable.TextAppearance_android_textSize) && a2.e(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        a(context, a2);
        a2.a();
        if (this.j != null) {
            this.a.setTypeface(this.j, this.i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.a.setAllCaps(z);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        if (!(this.b == null && this.c == null && this.d == null && this.e == null)) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (VERSION.SDK_INT < 17) {
            return;
        }
        if (this.f != null || this.g != null) {
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    private void a(Drawable drawable, ar arVar) {
        if (drawable != null && arVar != null) {
            f.a(drawable, arVar, this.a.getDrawableState());
        }
    }

    private static ar a(Context context, f fVar, int i2) {
        ColorStateList b2 = fVar.b(context, i2);
        if (b2 == null) {
            return null;
        }
        ar arVar = new ar();
        arVar.d = true;
        arVar.a = b2;
        return arVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z, int i2, int i3, int i4, int i5) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
            b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, float f2) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !c()) {
            b(i2, f2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.h.f();
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.h.g();
    }

    private void b(int i2, float f2) {
        this.h.a(i2, f2);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        this.h.a(i2);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        this.h.a(i2, i3, i4, i5);
    }

    /* access modifiers changed from: 0000 */
    public void a(int[] iArr, int i2) throws IllegalArgumentException {
        this.h.a(iArr, i2);
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.h.a();
    }

    /* access modifiers changed from: 0000 */
    public int e() {
        return this.h.b();
    }

    /* access modifiers changed from: 0000 */
    public int f() {
        return this.h.c();
    }

    /* access modifiers changed from: 0000 */
    public int g() {
        return this.h.d();
    }

    /* access modifiers changed from: 0000 */
    public int[] h() {
        return this.h.e();
    }
}
