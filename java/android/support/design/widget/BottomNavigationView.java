package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.internal.c;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.g;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.l;
import android.support.v7.widget.at;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BottomNavigationView extends FrameLayout {
    private final MenuBuilder a;
    private final BottomNavigationMenuView b;
    private final BottomNavigationPresenter c;
    private MenuInflater d;
    /* access modifiers changed from: private */
    public b e;
    /* access modifiers changed from: private */
    public a f;

    static class SavedState extends AbsSavedState {
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
        Bundle a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            a(parcel, classLoader);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.a);
        }

        private void a(Parcel parcel, ClassLoader classLoader) {
            this.a = parcel.readBundle(classLoader);
        }
    }

    public interface a {
        void a(MenuItem menuItem);
    }

    public interface b {
        boolean a(MenuItem menuItem);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new BottomNavigationPresenter();
        this.a = new BottomNavigationMenu(context);
        this.b = new BottomNavigationMenuView(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.b.setLayoutParams(layoutParams);
        this.c.a(this.b);
        this.c.a(1);
        this.b.setPresenter(this.c);
        this.a.a((l) this.c);
        this.c.a(getContext(), this.a);
        at b2 = c.b(context, attributeSet, R.styleable.BottomNavigationView, i, R.style.Widget_Design_BottomNavigationView, R.styleable.BottomNavigationView_itemTextAppearanceInactive, R.styleable.BottomNavigationView_itemTextAppearanceActive);
        if (b2.g(R.styleable.BottomNavigationView_itemIconTint)) {
            this.b.setIconTintList(b2.e(R.styleable.BottomNavigationView_itemIconTint));
        } else {
            this.b.setIconTintList(this.b.a(16842808));
        }
        setItemIconSize(b2.e(R.styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_icon_size)));
        if (b2.g(R.styleable.BottomNavigationView_itemTextAppearanceInactive)) {
            setItemTextAppearanceInactive(b2.g(R.styleable.BottomNavigationView_itemTextAppearanceInactive, 0));
        }
        if (b2.g(R.styleable.BottomNavigationView_itemTextAppearanceActive)) {
            setItemTextAppearanceActive(b2.g(R.styleable.BottomNavigationView_itemTextAppearanceActive, 0));
        }
        if (b2.g(R.styleable.BottomNavigationView_itemTextColor)) {
            setItemTextColor(b2.e(R.styleable.BottomNavigationView_itemTextColor));
        }
        if (b2.g(R.styleable.BottomNavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) b2.e(R.styleable.BottomNavigationView_elevation, 0));
        }
        setLabelVisibilityMode(b2.c(R.styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(b2.a(R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        this.b.setItemBackgroundRes(b2.g(R.styleable.BottomNavigationView_itemBackground, 0));
        if (b2.g(R.styleable.BottomNavigationView_menu)) {
            a(b2.g(R.styleable.BottomNavigationView_menu, 0));
        }
        b2.a();
        addView(this.b, layoutParams);
        if (VERSION.SDK_INT < 21) {
            a(context);
        }
        this.a.a((android.support.v7.view.menu.MenuBuilder.a) new android.support.v7.view.menu.MenuBuilder.a() {
            public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
                if (BottomNavigationView.this.f != null && menuItem.getItemId() == BottomNavigationView.this.getSelectedItemId()) {
                    BottomNavigationView.this.f.a(menuItem);
                    return true;
                } else if (BottomNavigationView.this.e == null || BottomNavigationView.this.e.a(menuItem)) {
                    return false;
                } else {
                    return true;
                }
            }

            public void a(MenuBuilder menuBuilder) {
            }
        });
    }

    public void setOnNavigationItemSelectedListener(b bVar) {
        this.e = bVar;
    }

    public void setOnNavigationItemReselectedListener(a aVar) {
        this.f = aVar;
    }

    public Menu getMenu() {
        return this.a;
    }

    public void a(int i) {
        this.c.b(true);
        getMenuInflater().inflate(i, this.a);
        this.c.b(false);
        this.c.a(true);
    }

    public int getMaxItemCount() {
        return 5;
    }

    public ColorStateList getItemIconTintList() {
        return this.b.getIconTintList();
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.b.setIconTintList(colorStateList);
    }

    public void setItemIconSize(int i) {
        this.b.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public int getItemIconSize() {
        return this.b.getItemIconSize();
    }

    public ColorStateList getItemTextColor() {
        return this.b.getItemTextColor();
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.b.setItemTextColor(colorStateList);
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.b.getItemBackgroundRes();
    }

    public void setItemBackgroundResource(int i) {
        this.b.setItemBackgroundRes(i);
    }

    public Drawable getItemBackground() {
        return this.b.getItemBackground();
    }

    public void setItemBackground(Drawable drawable) {
        this.b.setItemBackground(drawable);
    }

    public int getSelectedItemId() {
        return this.b.getSelectedItemId();
    }

    public void setSelectedItemId(int i) {
        MenuItem findItem = this.a.findItem(i);
        if (findItem != null && !this.a.a(findItem, (l) this.c, 0)) {
            findItem.setChecked(true);
        }
    }

    public void setLabelVisibilityMode(int i) {
        if (this.b.getLabelVisibilityMode() != i) {
            this.b.setLabelVisibilityMode(i);
            this.c.a(false);
        }
    }

    public int getLabelVisibilityMode() {
        return this.b.getLabelVisibilityMode();
    }

    public void setItemTextAppearanceInactive(int i) {
        this.b.setItemTextAppearanceInactive(i);
    }

    public int getItemTextAppearanceInactive() {
        return this.b.getItemTextAppearanceInactive();
    }

    public void setItemTextAppearanceActive(int i) {
        this.b.setItemTextAppearanceActive(i);
    }

    public int getItemTextAppearanceActive() {
        return this.b.getItemTextAppearanceActive();
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.b.a() != z) {
            this.b.setItemHorizontalTranslationEnabled(z);
            this.c.a(false);
        }
    }

    private void a(Context context) {
        View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    private MenuInflater getMenuInflater() {
        if (this.d == null) {
            this.d = new g(getContext());
        }
        return this.d;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = new Bundle();
        this.a.a(savedState.a);
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
        this.a.b(savedState.a);
    }
}
