package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.m.a;
import android.support.v7.widget.av;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class BottomNavigationItemView extends FrameLayout implements a {
    private static final int[] a = {16842912};
    private final int b;
    private float c;
    private float d;
    private float e;
    private int f;
    private boolean g;
    private ImageView h;
    private final TextView i;
    private final TextView j;
    private int k;
    private MenuItemImpl l;
    private ColorStateList m;

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = -1;
        Resources resources = getResources();
        LayoutInflater.from(context).inflate(R.layout.design_bottom_navigation_item, this, true);
        setBackgroundResource(R.drawable.design_bottom_navigation_item_background);
        this.b = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_margin);
        this.h = (ImageView) findViewById(R.id.icon);
        this.i = (TextView) findViewById(R.id.smallLabel);
        this.j = (TextView) findViewById(R.id.largeLabel);
        ViewCompat.setImportantForAccessibility(this.i, 2);
        ViewCompat.setImportantForAccessibility(this.j, 2);
        setFocusable(true);
        a(this.i.getTextSize(), this.j.getTextSize());
    }

    public void a(MenuItemImpl menuItemImpl, int i2) {
        this.l = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitle());
        setId(menuItemImpl.getItemId());
        if (!TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(menuItemImpl.getContentDescription());
        }
        av.a(this, menuItemImpl.getTooltipText());
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
    }

    public void setItemPosition(int i2) {
        this.k = i2;
    }

    public int getItemPosition() {
        return this.k;
    }

    public void setShifting(boolean z) {
        if (this.g != z) {
            this.g = z;
            if (this.l != null) {
                setChecked(this.l.isChecked());
            }
        }
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.f != i2) {
            this.f = i2;
            if (this.l != null) {
                setChecked(this.l.isChecked());
            }
        }
    }

    public MenuItemImpl getItemData() {
        return this.l;
    }

    public void setTitle(CharSequence charSequence) {
        this.i.setText(charSequence);
        this.j.setText(charSequence);
        if (this.l == null || TextUtils.isEmpty(this.l.getContentDescription())) {
            setContentDescription(charSequence);
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        this.j.setPivotX((float) (this.j.getWidth() / 2));
        this.j.setPivotY((float) this.j.getBaseline());
        this.i.setPivotX((float) (this.i.getWidth() / 2));
        this.i.setPivotY((float) this.i.getBaseline());
        switch (this.f) {
            case -1:
                if (!this.g) {
                    if (!z) {
                        a(this.h, this.b, 49);
                        a(this.j, this.e, this.e, 4);
                        a(this.i, 1.0f, 1.0f, 0);
                        break;
                    } else {
                        a(this.h, (int) (((float) this.b) + this.c), 49);
                        a(this.j, 1.0f, 1.0f, 0);
                        a(this.i, this.d, this.d, 4);
                        break;
                    }
                } else {
                    if (z) {
                        a(this.h, this.b, 49);
                        a(this.j, 1.0f, 1.0f, 0);
                    } else {
                        a(this.h, this.b, 17);
                        a(this.j, 0.5f, 0.5f, 4);
                    }
                    this.i.setVisibility(4);
                    break;
                }
            case 0:
                if (z) {
                    a(this.h, this.b, 49);
                    a(this.j, 1.0f, 1.0f, 0);
                } else {
                    a(this.h, this.b, 17);
                    a(this.j, 0.5f, 0.5f, 4);
                }
                this.i.setVisibility(4);
                break;
            case 1:
                if (!z) {
                    a(this.h, this.b, 49);
                    a(this.j, this.e, this.e, 4);
                    a(this.i, 1.0f, 1.0f, 0);
                    break;
                } else {
                    a(this.h, (int) (((float) this.b) + this.c), 49);
                    a(this.j, 1.0f, 1.0f, 0);
                    a(this.i, this.d, this.d, 4);
                    break;
                }
            case 2:
                a(this.h, this.b, 17);
                this.j.setVisibility(8);
                this.i.setVisibility(8);
                break;
        }
        refreshDrawableState();
        setSelected(z);
    }

    private void a(View view, int i2, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.gravity = i3;
        view.setLayoutParams(layoutParams);
    }

    private void a(View view, float f2, float f3, int i2) {
        view.setScaleX(f2);
        view.setScaleY(f3);
        view.setVisibility(i2);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.i.setEnabled(z);
        this.j.setEnabled(z);
        this.h.setEnabled(z);
        if (z) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), PointerIconCompat.TYPE_HAND));
        } else {
            ViewCompat.setPointerIcon(this, null);
        }
    }

    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (this.l != null && this.l.isCheckable() && this.l.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, a);
        }
        return onCreateDrawableState;
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTintList(drawable, this.m);
        }
        this.h.setImageDrawable(drawable);
    }

    public boolean a() {
        return false;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.m = colorStateList;
        if (this.l != null) {
            setIcon(this.l.getIcon());
        }
    }

    public void setIconSize(int i2) {
        LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.h.setLayoutParams(layoutParams);
    }

    public void setTextAppearanceInactive(int i2) {
        TextViewCompat.setTextAppearance(this.i, i2);
        a(this.i.getTextSize(), this.j.getTextSize());
    }

    public void setTextAppearanceActive(int i2) {
        TextViewCompat.setTextAppearance(this.j, i2);
        a(this.i.getTextSize(), this.j.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.i.setTextColor(colorStateList);
            this.j.setTextColor(colorStateList);
        }
    }

    private void a(float f2, float f3) {
        this.c = f2 - f3;
        this.d = (1.0f * f3) / f2;
        this.e = (1.0f * f2) / f3;
    }

    public void setItemBackground(int i2) {
        Drawable drawable;
        if (i2 == 0) {
            drawable = null;
        } else {
            drawable = ContextCompat.getDrawable(getContext(), i2);
        }
        setItemBackground(drawable);
    }

    public void setItemBackground(Drawable drawable) {
        ViewCompat.setBackground(this, drawable);
    }
}
