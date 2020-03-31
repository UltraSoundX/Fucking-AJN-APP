package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.a.a.a;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* compiled from: AppCompatCompoundButtonHelper */
class e {
    private final CompoundButton a;
    private ColorStateList b = null;
    private Mode c = null;
    private boolean d = false;
    private boolean e = false;
    private boolean f;

    e(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    /* access modifiers changed from: 0000 */
    public void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, R.styleable.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(R.styleable.CompoundButton_android_button)) {
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CompoundButton_android_button, 0);
                if (resourceId != 0) {
                    this.a.setButtonDrawable(a.b(this.a.getContext(), resourceId));
                }
            }
            if (obtainStyledAttributes.hasValue(R.styleable.CompoundButton_buttonTint)) {
                CompoundButtonCompat.setButtonTintList(this.a, obtainStyledAttributes.getColorStateList(R.styleable.CompoundButton_buttonTint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.CompoundButton_buttonTintMode)) {
                CompoundButtonCompat.setButtonTintMode(this.a, v.a(obtainStyledAttributes.getInt(R.styleable.CompoundButton_buttonTintMode, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.d = true;
        d();
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public void a(Mode mode) {
        this.c = mode;
        this.e = true;
        d();
    }

    /* access modifiers changed from: 0000 */
    public Mode b() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.f) {
            this.f = false;
            return;
        }
        this.f = true;
        d();
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.a);
        if (buttonDrawable == null) {
            return;
        }
        if (this.d || this.e) {
            Drawable mutate = DrawableCompat.wrap(buttonDrawable).mutate();
            if (this.d) {
                DrawableCompat.setTintList(mutate, this.b);
            }
            if (this.e) {
                DrawableCompat.setTintMode(mutate, this.c);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.a.getDrawableState());
            }
            this.a.setButtonDrawable(mutate);
        }
    }

    /* access modifiers changed from: 0000 */
    public int a(int i) {
        if (VERSION.SDK_INT >= 17) {
            return i;
        }
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.a);
        if (buttonDrawable != null) {
            return i + buttonDrawable.getIntrinsicWidth();
        }
        return i;
    }
}
