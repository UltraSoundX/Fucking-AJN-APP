package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.av;
import android.support.v7.widget.z;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;

public class ActionMenuItemView extends AppCompatTextView implements android.support.v7.view.menu.m.a, android.support.v7.widget.ActionMenuView.a, OnClickListener {
    MenuItemImpl a;
    android.support.v7.view.menu.MenuBuilder.b b;
    b c;
    private CharSequence d;
    private Drawable e;
    private z f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;

    private class a extends z {
        public a() {
            super(ActionMenuItemView.this);
        }

        public p a() {
            if (ActionMenuItemView.this.c != null) {
                return ActionMenuItemView.this.c.a();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean b() {
            if (ActionMenuItemView.this.b == null || !ActionMenuItemView.this.b.a(ActionMenuItemView.this.a)) {
                return false;
            }
            p a2 = a();
            if (a2 == null || !a2.f()) {
                return false;
            }
            return true;
        }
    }

    public static abstract class b {
        public abstract p a();
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Resources resources = context.getResources();
        this.g = e();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionMenuItemView, i2, 0);
        this.i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.k = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.j = -1;
        setSaveEnabled(false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.g = e();
        f();
    }

    private boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        return i2 >= 480 || (i2 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public void setPadding(int i2, int i3, int i4, int i5) {
        this.j = i2;
        super.setPadding(i2, i3, i4, i5);
    }

    public MenuItemImpl getItemData() {
        return this.a;
    }

    public void a(MenuItemImpl menuItemImpl, int i2) {
        this.a = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.a((android.support.v7.view.menu.m.a) this));
        setId(menuItemImpl.getItemId());
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setEnabled(menuItemImpl.isEnabled());
        if (menuItemImpl.hasSubMenu() && this.f == null) {
            this.f = new a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.a.hasSubMenu() || this.f == null || !this.f.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void onClick(View view) {
        if (this.b != null) {
            this.b.a(this.a);
        }
    }

    public void setItemInvoker(android.support.v7.view.menu.MenuBuilder.b bVar) {
        this.b = bVar;
    }

    public void setPopupCallback(b bVar) {
        this.c = bVar;
    }

    public boolean a() {
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.h != z) {
            this.h = z;
            if (this.a != null) {
                this.a.g();
            }
        }
    }

    private void f() {
        CharSequence charSequence;
        boolean z = false;
        CharSequence charSequence2 = null;
        boolean z2 = !TextUtils.isEmpty(this.d);
        if (this.e == null || (this.a.l() && (this.g || this.h))) {
            z = true;
        }
        boolean z3 = z2 & z;
        if (z3) {
            charSequence = this.d;
        } else {
            charSequence = null;
        }
        setText(charSequence);
        CharSequence contentDescription = this.a.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z3 ? null : this.a.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.a.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (!z3) {
                charSequence2 = this.a.getTitle();
            }
            av.a(this, charSequence2);
            return;
        }
        av.a(this, tooltipText);
    }

    public void setIcon(Drawable drawable) {
        this.e = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > this.k) {
                float f2 = ((float) this.k) / ((float) intrinsicWidth);
                intrinsicWidth = this.k;
                intrinsicHeight = (int) (((float) intrinsicHeight) * f2);
            }
            if (intrinsicHeight > this.k) {
                float f3 = ((float) this.k) / ((float) intrinsicHeight);
                intrinsicHeight = this.k;
                intrinsicWidth = (int) (((float) intrinsicWidth) * f3);
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        f();
    }

    public boolean b() {
        return !TextUtils.isEmpty(getText());
    }

    public void setTitle(CharSequence charSequence) {
        this.d = charSequence;
        f();
    }

    public boolean c() {
        return b() && this.a.getIcon() == null;
    }

    public boolean d() {
        return b();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        boolean b2 = b();
        if (b2 && this.j >= 0) {
            super.setPadding(this.j, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i2, i3);
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int i4 = mode == Integer.MIN_VALUE ? Math.min(size, this.i) : this.i;
        if (mode != 1073741824 && this.i > 0 && measuredWidth < i4) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(i4, 1073741824), i3);
        }
        if (!b2 && this.e != null) {
            super.setPadding((getMeasuredWidth() - this.e.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }
}
