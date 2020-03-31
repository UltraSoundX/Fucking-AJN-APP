package android.support.design.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.design.e.a;
import android.support.design.internal.c;
import android.support.design.internal.d;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;

public class MaterialButton extends AppCompatButton {
    private final b a;
    private int b;
    private Mode c;
    private ColorStateList d;
    private Drawable e;
    private int f;
    private int g;
    private int h;

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialButtonStyle);
    }

    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray a2 = c.a(context, attributeSet, R.styleable.MaterialButton, i, R.style.Widget_MaterialComponents_Button, new int[0]);
        this.b = a2.getDimensionPixelSize(R.styleable.MaterialButton_iconPadding, 0);
        this.c = d.a(a2.getInt(R.styleable.MaterialButton_iconTintMode, -1), Mode.SRC_IN);
        this.d = a.a(getContext(), a2, R.styleable.MaterialButton_iconTint);
        this.e = a.b(getContext(), a2, R.styleable.MaterialButton_icon);
        this.h = a2.getInteger(R.styleable.MaterialButton_iconGravity, 1);
        this.f = a2.getDimensionPixelSize(R.styleable.MaterialButton_iconSize, 0);
        this.a = new b(this);
        this.a.a(a2);
        a2.recycle();
        setCompoundDrawablePadding(this.b);
        b();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (VERSION.SDK_INT < 21 && c()) {
            this.a.a(canvas);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (c()) {
            this.a.a(colorStateList);
        } else if (this.a != null) {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        if (c()) {
            return this.a.c();
        }
        return super.getSupportBackgroundTintList();
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (c()) {
            this.a.a(mode);
        } else if (this.a != null) {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        if (c()) {
            return this.a.d();
        }
        return super.getSupportBackgroundTintMode();
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    public void setBackgroundTintMode(Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public void setBackgroundColor(int i) {
        if (c()) {
            this.a.a(i);
        } else {
            super.setBackgroundColor(i);
        }
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(int i) {
        Drawable drawable = null;
        if (i != 0) {
            drawable = android.support.v7.a.a.a.b(getContext(), i);
        }
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (!c()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.i("MaterialButton", "Setting a custom background is not supported.");
            this.a.a();
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (VERSION.SDK_INT == 21 && this.a != null) {
            this.a.a(i4 - i2, i3 - i);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.e != null && this.h == 2) {
            int measuredWidth = (((((getMeasuredWidth() - ((int) getPaint().measureText(getText().toString()))) - ViewCompat.getPaddingEnd(this)) - (this.f == 0 ? this.e.getIntrinsicWidth() : this.f)) - this.b) - ViewCompat.getPaddingStart(this)) / 2;
            if (a()) {
                measuredWidth = -measuredWidth;
            }
            if (this.g != measuredWidth) {
                this.g = measuredWidth;
                b();
            }
        }
    }

    private boolean a() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    /* access modifiers changed from: 0000 */
    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setIconPadding(int i) {
        if (this.b != i) {
            this.b = i;
            setCompoundDrawablePadding(i);
        }
    }

    public int getIconPadding() {
        return this.b;
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        } else if (this.f != i) {
            this.f = i;
            b();
        }
    }

    public int getIconSize() {
        return this.f;
    }

    public void setIcon(Drawable drawable) {
        if (this.e != drawable) {
            this.e = drawable;
            b();
        }
    }

    public void setIconResource(int i) {
        Drawable drawable = null;
        if (i != 0) {
            drawable = android.support.v7.a.a.a.b(getContext(), i);
        }
        setIcon(drawable);
    }

    public Drawable getIcon() {
        return this.e;
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.d != colorStateList) {
            this.d = colorStateList;
            b();
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(android.support.v7.a.a.a.a(getContext(), i));
    }

    public ColorStateList getIconTint() {
        return this.d;
    }

    public void setIconTintMode(Mode mode) {
        if (this.c != mode) {
            this.c = mode;
            b();
        }
    }

    public Mode getIconTintMode() {
        return this.c;
    }

    private void b() {
        if (this.e != null) {
            this.e = this.e.mutate();
            DrawableCompat.setTintList(this.e, this.d);
            if (this.c != null) {
                DrawableCompat.setTintMode(this.e, this.c);
            }
            this.e.setBounds(this.g, 0, (this.f != 0 ? this.f : this.e.getIntrinsicWidth()) + this.g, this.f != 0 ? this.f : this.e.getIntrinsicHeight());
        }
        TextViewCompat.setCompoundDrawablesRelative(this, this.e, null, null, null);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (c()) {
            this.a.b(colorStateList);
        }
    }

    public void setRippleColorResource(int i) {
        if (c()) {
            setRippleColor(android.support.v7.a.a.a.a(getContext(), i));
        }
    }

    public ColorStateList getRippleColor() {
        if (c()) {
            return this.a.e();
        }
        return null;
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (c()) {
            this.a.c(colorStateList);
        }
    }

    public void setStrokeColorResource(int i) {
        if (c()) {
            setStrokeColor(android.support.v7.a.a.a.a(getContext(), i));
        }
    }

    public ColorStateList getStrokeColor() {
        if (c()) {
            return this.a.f();
        }
        return null;
    }

    public void setStrokeWidth(int i) {
        if (c()) {
            this.a.b(i);
        }
    }

    public void setStrokeWidthResource(int i) {
        if (c()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    public int getStrokeWidth() {
        if (c()) {
            return this.a.g();
        }
        return 0;
    }

    public void setCornerRadius(int i) {
        if (c()) {
            this.a.c(i);
        }
    }

    public void setCornerRadiusResource(int i) {
        if (c()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    public int getCornerRadius() {
        if (c()) {
            return this.a.h();
        }
        return 0;
    }

    public int getIconGravity() {
        return this.h;
    }

    public void setIconGravity(int i) {
        this.h = i;
    }

    private boolean c() {
        return this.a != null && !this.a.b();
    }
}
