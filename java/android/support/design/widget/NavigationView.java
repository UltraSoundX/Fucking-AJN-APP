package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.internal.c;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.view.g;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.l;
import android.support.v7.widget.at;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] d = {16842912};
    private static final int[] e = {-16842910};
    a c;
    private final NavigationMenu f;
    private final android.support.design.internal.a g;
    private final int h;
    private MenuInflater i;

    public static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public Bundle a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readBundle(classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.a);
        }
    }

    public interface a {
        boolean a(MenuItem menuItem);
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.navigationViewStyle);
    }

    public NavigationView(Context context, AttributeSet attributeSet, int i2) {
        ColorStateList c2;
        int i3;
        boolean z;
        super(context, attributeSet, i2);
        this.g = new android.support.design.internal.a();
        this.f = new NavigationMenu(context);
        at b = c.b(context, attributeSet, R.styleable.NavigationView, i2, R.style.Widget_Design_NavigationView, new int[0]);
        ViewCompat.setBackground(this, b.a(R.styleable.NavigationView_android_background));
        if (b.g(R.styleable.NavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) b.e(R.styleable.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows(this, b.a(R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.h = b.e(R.styleable.NavigationView_android_maxWidth, 0);
        if (b.g(R.styleable.NavigationView_itemIconTint)) {
            c2 = b.e(R.styleable.NavigationView_itemIconTint);
        } else {
            c2 = c(16842808);
        }
        if (b.g(R.styleable.NavigationView_itemTextAppearance)) {
            i3 = b.g(R.styleable.NavigationView_itemTextAppearance, 0);
            z = true;
        } else {
            i3 = 0;
            z = false;
        }
        ColorStateList colorStateList = null;
        if (b.g(R.styleable.NavigationView_itemTextColor)) {
            colorStateList = b.e(R.styleable.NavigationView_itemTextColor);
        }
        if (!z && colorStateList == null) {
            colorStateList = c(16842806);
        }
        Drawable a2 = b.a(R.styleable.NavigationView_itemBackground);
        if (b.g(R.styleable.NavigationView_itemHorizontalPadding)) {
            this.g.d(b.e(R.styleable.NavigationView_itemHorizontalPadding, 0));
        }
        int e2 = b.e(R.styleable.NavigationView_itemIconPadding, 0);
        this.f.a((android.support.v7.view.menu.MenuBuilder.a) new android.support.v7.view.menu.MenuBuilder.a() {
            public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
                return NavigationView.this.c != null && NavigationView.this.c.a(menuItem);
            }

            public void a(MenuBuilder menuBuilder) {
            }
        });
        this.g.a(1);
        this.g.a(context, (MenuBuilder) this.f);
        this.g.a(c2);
        if (z) {
            this.g.c(i3);
        }
        this.g.b(colorStateList);
        this.g.a(a2);
        this.g.e(e2);
        this.f.a((l) this.g);
        addView((View) this.g.a((ViewGroup) this));
        if (b.g(R.styleable.NavigationView_menu)) {
            a(b.g(R.styleable.NavigationView_menu, 0));
        }
        if (b.g(R.styleable.NavigationView_headerLayout)) {
            b(b.g(R.styleable.NavigationView_headerLayout, 0));
        }
        b.a();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = new Bundle();
        this.f.a(savedState.a);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f.b(savedState.a);
    }

    public void setNavigationItemSelectedListener(a aVar) {
        this.c = aVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        switch (MeasureSpec.getMode(i2)) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                i2 = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(i2), this.h), 1073741824);
                break;
            case 0:
                i2 = MeasureSpec.makeMeasureSpec(this.h, 1073741824);
                break;
        }
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void a(WindowInsetsCompat windowInsetsCompat) {
        this.g.a(windowInsetsCompat);
    }

    public void a(int i2) {
        this.g.b(true);
        getMenuInflater().inflate(i2, this.f);
        this.g.b(false);
        this.g.a(false);
    }

    public Menu getMenu() {
        return this.f;
    }

    public View b(int i2) {
        return this.g.b(i2);
    }

    public int getHeaderCount() {
        return this.g.e();
    }

    public ColorStateList getItemIconTintList() {
        return this.g.f();
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.g.a(colorStateList);
    }

    public ColorStateList getItemTextColor() {
        return this.g.g();
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.g.b(colorStateList);
    }

    public Drawable getItemBackground() {
        return this.g.h();
    }

    public void setItemBackgroundResource(int i2) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i2));
    }

    public void setItemBackground(Drawable drawable) {
        this.g.a(drawable);
    }

    public int getItemHorizontalPadding() {
        return this.g.i();
    }

    public void setItemHorizontalPadding(int i2) {
        this.g.d(i2);
    }

    public void setItemHorizontalPaddingResource(int i2) {
        this.g.d(getResources().getDimensionPixelSize(i2));
    }

    public int getItemIconPadding() {
        return this.g.j();
    }

    public void setItemIconPadding(int i2) {
        this.g.e(i2);
    }

    public void setItemIconPaddingResource(int i2) {
        this.g.e(getResources().getDimensionPixelSize(i2));
    }

    public void setCheckedItem(int i2) {
        MenuItem findItem = this.f.findItem(i2);
        if (findItem != null) {
            this.g.a((MenuItemImpl) findItem);
        }
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem findItem = this.f.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.g.a((MenuItemImpl) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public MenuItem getCheckedItem() {
        return this.g.d();
    }

    public void setItemTextAppearance(int i2) {
        this.g.c(i2);
    }

    private MenuInflater getMenuInflater() {
        if (this.i == null) {
            this.i = new g(getContext());
        }
        return this.i;
    }

    private ColorStateList c(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList a2 = android.support.v7.a.a.a.a(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = a2.getDefaultColor();
        return new ColorStateList(new int[][]{e, d, EMPTY_STATE_SET}, new int[]{a2.getColorForState(e, defaultColor), i3, defaultColor});
    }
}
