package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.a.a.a;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class AppCompatRadioButton extends RadioButton implements TintableCompoundButton {
    private final e a;
    private final k b;

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(aq.a(context), attributeSet, i);
        this.a = new e(this);
        this.a.a(attributeSet, i);
        this.b = new k(this);
        this.b.a(attributeSet, i);
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (this.a != null) {
            this.a.c();
        }
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(a.b(getContext(), i));
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        return this.a != null ? this.a.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        if (this.a != null) {
            this.a.a(colorStateList);
        }
    }

    public ColorStateList getSupportButtonTintList() {
        if (this.a != null) {
            return this.a.a();
        }
        return null;
    }

    public void setSupportButtonTintMode(Mode mode) {
        if (this.a != null) {
            this.a.a(mode);
        }
    }

    public Mode getSupportButtonTintMode() {
        if (this.a != null) {
            return this.a.b();
        }
        return null;
    }
}
