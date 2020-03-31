package android.support.design.internal;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.transition.s;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.a.a.a;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.m;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class BottomNavigationMenuView extends ViewGroup implements m {
    private static final int[] a = {16842912};
    private static final int[] b = {-16842910};
    private final TransitionSet c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final OnClickListener i;
    private final Pool<BottomNavigationItemView> j;
    private boolean k;
    private int l;
    private BottomNavigationItemView[] m;
    private int n;
    private int o;
    private ColorStateList p;

    /* renamed from: q reason: collision with root package name */
    private int f318q;
    private ColorStateList r;
    private final ColorStateList s;
    private int t;
    private int u;
    private Drawable v;
    private int w;
    private int[] x;
    /* access modifiers changed from: private */
    public BottomNavigationPresenter y;
    /* access modifiers changed from: private */
    public MenuBuilder z;

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new SynchronizedPool(5);
        this.n = 0;
        this.o = 0;
        Resources resources = getResources();
        this.d = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.e = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.f = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.g = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
        this.h = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
        this.s = a(16842808);
        this.c = new AutoTransition();
        this.c.a(0);
        this.c.a(115);
        this.c.a((TimeInterpolator) new FastOutSlowInInterpolator());
        this.c.a((Transition) new b());
        this.i = new OnClickListener() {
            public void onClick(View view) {
                MenuItemImpl itemData = ((BottomNavigationItemView) view).getItemData();
                if (!BottomNavigationMenuView.this.z.a((MenuItem) itemData, (l) BottomNavigationMenuView.this.y, 0)) {
                    itemData.setChecked(true);
                }
            }
        };
        this.x = new int[5];
    }

    public void a(MenuBuilder menuBuilder) {
        this.z = menuBuilder;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6 = 1;
        int size = MeasureSpec.getSize(i2);
        int size2 = this.z.j().size();
        int childCount = getChildCount();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.h, 1073741824);
        if (!a(this.l, size2) || !this.k) {
            if (size2 != 0) {
                i6 = size2;
            }
            int min = Math.min(size / i6, this.f);
            int i7 = size - (min * size2);
            for (int i8 = 0; i8 < childCount; i8++) {
                if (getChildAt(i8).getVisibility() != 8) {
                    this.x[i8] = min;
                    if (i7 > 0) {
                        int[] iArr = this.x;
                        iArr[i8] = iArr[i8] + 1;
                        i7--;
                    }
                } else {
                    this.x[i8] = 0;
                }
            }
        } else {
            View childAt = getChildAt(this.o);
            int i9 = this.g;
            if (childAt.getVisibility() != 8) {
                childAt.measure(MeasureSpec.makeMeasureSpec(this.f, ExploreByTouchHelper.INVALID_ID), makeMeasureSpec);
                i9 = Math.max(i9, childAt.getMeasuredWidth());
            }
            int i10 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int min2 = Math.min(size - (this.e * i10), Math.min(i9, this.f));
            int i11 = size - min2;
            if (i10 != 0) {
                i6 = i10;
            }
            int min3 = Math.min(i11 / i6, this.d);
            int i12 = (size - min2) - (i10 * min3);
            int i13 = 0;
            while (i13 < childCount) {
                if (getChildAt(i13).getVisibility() != 8) {
                    int[] iArr2 = this.x;
                    if (i13 == this.o) {
                        i5 = min2;
                    } else {
                        i5 = min3;
                    }
                    iArr2[i13] = i5;
                    if (i12 > 0) {
                        int[] iArr3 = this.x;
                        iArr3[i13] = iArr3[i13] + 1;
                        i4 = i12 - 1;
                    }
                    i4 = i12;
                } else {
                    this.x[i13] = 0;
                    i4 = i12;
                }
                i13++;
                i12 = i4;
            }
        }
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt2 = getChildAt(i15);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(MeasureSpec.makeMeasureSpec(this.x[i15], 1073741824), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i14 += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(i14, MeasureSpec.makeMeasureSpec(i14, 1073741824), 0), View.resolveSizeAndState(this.h, makeMeasureSpec, 0));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    childAt.layout((i6 - i8) - childAt.getMeasuredWidth(), 0, i6 - i8, i7);
                } else {
                    childAt.layout(i8, 0, childAt.getMeasuredWidth() + i8, i7);
                }
                i8 += childAt.getMeasuredWidth();
            }
        }
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.p = colorStateList;
        if (this.m != null) {
            for (BottomNavigationItemView iconTintList : this.m) {
                iconTintList.setIconTintList(colorStateList);
            }
        }
    }

    public ColorStateList getIconTintList() {
        return this.p;
    }

    public void setItemIconSize(int i2) {
        this.f318q = i2;
        if (this.m != null) {
            for (BottomNavigationItemView iconSize : this.m) {
                iconSize.setIconSize(i2);
            }
        }
    }

    public int getItemIconSize() {
        return this.f318q;
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.r = colorStateList;
        if (this.m != null) {
            for (BottomNavigationItemView textColor : this.m) {
                textColor.setTextColor(colorStateList);
            }
        }
    }

    public ColorStateList getItemTextColor() {
        return this.r;
    }

    public void setItemTextAppearanceInactive(int i2) {
        BottomNavigationItemView[] bottomNavigationItemViewArr;
        this.t = i2;
        if (this.m != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.m) {
                bottomNavigationItemView.setTextAppearanceInactive(i2);
                if (this.r != null) {
                    bottomNavigationItemView.setTextColor(this.r);
                }
            }
        }
    }

    public int getItemTextAppearanceInactive() {
        return this.t;
    }

    public void setItemTextAppearanceActive(int i2) {
        BottomNavigationItemView[] bottomNavigationItemViewArr;
        this.u = i2;
        if (this.m != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.m) {
                bottomNavigationItemView.setTextAppearanceActive(i2);
                if (this.r != null) {
                    bottomNavigationItemView.setTextColor(this.r);
                }
            }
        }
    }

    public int getItemTextAppearanceActive() {
        return this.u;
    }

    public void setItemBackgroundRes(int i2) {
        this.w = i2;
        if (this.m != null) {
            for (BottomNavigationItemView itemBackground : this.m) {
                itemBackground.setItemBackground(i2);
            }
        }
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.w;
    }

    public void setItemBackground(Drawable drawable) {
        this.v = drawable;
        if (this.m != null) {
            for (BottomNavigationItemView itemBackground : this.m) {
                itemBackground.setItemBackground(drawable);
            }
        }
    }

    public Drawable getItemBackground() {
        if (this.m == null || this.m.length <= 0) {
            return this.v;
        }
        return this.m[0].getBackground();
    }

    public void setLabelVisibilityMode(int i2) {
        this.l = i2;
    }

    public int getLabelVisibilityMode() {
        return this.l;
    }

    public void setItemHorizontalTranslationEnabled(boolean z2) {
        this.k = z2;
    }

    public boolean a() {
        return this.k;
    }

    public ColorStateList a(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList a2 = a.a(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = a2.getDefaultColor();
        return new ColorStateList(new int[][]{b, a, EMPTY_STATE_SET}, new int[]{a2.getColorForState(b, defaultColor), i3, defaultColor});
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.y = bottomNavigationPresenter;
    }

    public void b() {
        BottomNavigationItemView[] bottomNavigationItemViewArr;
        removeAllViews();
        if (this.m != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.m) {
                if (bottomNavigationItemView != null) {
                    this.j.release(bottomNavigationItemView);
                }
            }
        }
        if (this.z.size() == 0) {
            this.n = 0;
            this.o = 0;
            this.m = null;
            return;
        }
        this.m = new BottomNavigationItemView[this.z.size()];
        boolean a2 = a(this.l, this.z.j().size());
        for (int i2 = 0; i2 < this.z.size(); i2++) {
            this.y.b(true);
            this.z.getItem(i2).setCheckable(true);
            this.y.b(false);
            BottomNavigationItemView newItem = getNewItem();
            this.m[i2] = newItem;
            newItem.setIconTintList(this.p);
            newItem.setIconSize(this.f318q);
            newItem.setTextColor(this.s);
            newItem.setTextAppearanceInactive(this.t);
            newItem.setTextAppearanceActive(this.u);
            newItem.setTextColor(this.r);
            if (this.v != null) {
                newItem.setItemBackground(this.v);
            } else {
                newItem.setItemBackground(this.w);
            }
            newItem.setShifting(a2);
            newItem.setLabelVisibilityMode(this.l);
            newItem.a((MenuItemImpl) this.z.getItem(i2), 0);
            newItem.setItemPosition(i2);
            newItem.setOnClickListener(this.i);
            addView(newItem);
        }
        this.o = Math.min(this.z.size() - 1, this.o);
        this.z.getItem(this.o).setChecked(true);
    }

    public void c() {
        if (this.z != null && this.m != null) {
            int size = this.z.size();
            if (size != this.m.length) {
                b();
                return;
            }
            int i2 = this.n;
            for (int i3 = 0; i3 < size; i3++) {
                MenuItem item = this.z.getItem(i3);
                if (item.isChecked()) {
                    this.n = item.getItemId();
                    this.o = i3;
                }
            }
            if (i2 != this.n) {
                s.a(this, this.c);
            }
            boolean a2 = a(this.l, this.z.j().size());
            for (int i4 = 0; i4 < size; i4++) {
                this.y.b(true);
                this.m[i4].setLabelVisibilityMode(this.l);
                this.m[i4].setShifting(a2);
                this.m[i4].a((MenuItemImpl) this.z.getItem(i4), 0);
                this.y.b(false);
            }
        }
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) this.j.acquire();
        if (bottomNavigationItemView == null) {
            return new BottomNavigationItemView(getContext());
        }
        return bottomNavigationItemView;
    }

    public int getSelectedItemId() {
        return this.n;
    }

    private boolean a(int i2, int i3) {
        return i2 == -1 ? i3 > 3 : i2 == 0;
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        int size = this.z.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.z.getItem(i3);
            if (i2 == item.getItemId()) {
                this.n = i2;
                this.o = i3;
                item.setChecked(true);
                return;
            }
        }
    }
}
