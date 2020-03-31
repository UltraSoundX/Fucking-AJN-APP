package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.k;
import android.support.v7.view.menu.m;
import android.support.v7.view.menu.p;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

class ActionMenuPresenter extends android.support.v7.view.menu.a implements SubUiVisibilityListener {
    private b A;
    d g;
    e h;
    a i;
    c j;
    final f k = new f();
    int l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private int f350q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y = new SparseBooleanArray();
    private View z;

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int a;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
        }
    }

    private class a extends k {
        public a(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false, R.attr.actionOverflowMenuStyle);
            if (!((MenuItemImpl) subMenuBuilder.getItem()).i()) {
                a(ActionMenuPresenter.this.g == null ? (View) ActionMenuPresenter.this.f : ActionMenuPresenter.this.g);
            }
            a((android.support.v7.view.menu.l.a) ActionMenuPresenter.this.k);
        }

        /* access modifiers changed from: protected */
        public void e() {
            ActionMenuPresenter.this.i = null;
            ActionMenuPresenter.this.l = 0;
            super.e();
        }
    }

    private class b extends android.support.v7.view.menu.ActionMenuItemView.b {
        b() {
        }

        public p a() {
            if (ActionMenuPresenter.this.i != null) {
                return ActionMenuPresenter.this.i.b();
            }
            return null;
        }
    }

    private class c implements Runnable {
        private e b;

        public c(e eVar) {
            this.b = eVar;
        }

        public void run() {
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.g();
            }
            View view = (View) ActionMenuPresenter.this.f;
            if (!(view == null || view.getWindowToken() == null || !this.b.c())) {
                ActionMenuPresenter.this.h = this.b;
            }
            ActionMenuPresenter.this.j = null;
        }
    }

    private class d extends AppCompatImageView implements android.support.v7.widget.ActionMenuView.a {
        private final float[] b = new float[2];

        public d(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            av.a(this, getContentDescription());
            setOnTouchListener(new z(this, ActionMenuPresenter.this) {
                public p a() {
                    if (ActionMenuPresenter.this.h == null) {
                        return null;
                    }
                    return ActionMenuPresenter.this.h.b();
                }

                public boolean b() {
                    ActionMenuPresenter.this.f();
                    return true;
                }

                public boolean c() {
                    if (ActionMenuPresenter.this.j != null) {
                        return false;
                    }
                    ActionMenuPresenter.this.g();
                    return true;
                }
            });
        }

        public boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                ActionMenuPresenter.this.f();
            }
            return true;
        }

        public boolean c() {
            return false;
        }

        public boolean d() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    private class e extends k {
        public e(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z, R.attr.actionOverflowMenuStyle);
            a((int) GravityCompat.END);
            a((android.support.v7.view.menu.l.a) ActionMenuPresenter.this.k);
        }

        /* access modifiers changed from: protected */
        public void e() {
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.close();
            }
            ActionMenuPresenter.this.h = null;
            super.e();
        }
    }

    private class f implements android.support.v7.view.menu.l.a {
        f() {
        }

        public boolean a(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.l = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            android.support.v7.view.menu.l.a d = ActionMenuPresenter.this.d();
            return d != null ? d.a(menuBuilder) : false;
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.q().b(false);
            }
            android.support.v7.view.menu.l.a d = ActionMenuPresenter.this.d();
            if (d != null) {
                d.a(menuBuilder, z);
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        super.a(context, menuBuilder);
        Resources resources = context.getResources();
        android.support.v7.view.a a2 = android.support.v7.view.a.a(context);
        if (!this.p) {
            this.o = a2.b();
        }
        if (!this.v) {
            this.f350q = a2.c();
        }
        if (!this.t) {
            this.s = a2.a();
        }
        int i2 = this.f350q;
        if (this.o) {
            if (this.g == null) {
                this.g = new d(this.a);
                if (this.n) {
                    this.g.setImageDrawable(this.m);
                    this.m = null;
                    this.n = false;
                }
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i2 -= this.g.getMeasuredWidth();
        } else {
            this.g = null;
        }
        this.r = i2;
        this.x = (int) (56.0f * resources.getDisplayMetrics().density);
        this.z = null;
    }

    public void a(Configuration configuration) {
        if (!this.t) {
            this.s = android.support.v7.view.a.a(this.b).a();
        }
        if (this.c != null) {
            this.c.a(true);
        }
    }

    public void b(boolean z2) {
        this.o = z2;
        this.p = true;
    }

    public void c(boolean z2) {
        this.w = z2;
    }

    public void a(Drawable drawable) {
        if (this.g != null) {
            this.g.setImageDrawable(drawable);
            return;
        }
        this.n = true;
        this.m = drawable;
    }

    public Drawable e() {
        if (this.g != null) {
            return this.g.getDrawable();
        }
        if (this.n) {
            return this.m;
        }
        return null;
    }

    public m a(ViewGroup viewGroup) {
        m mVar = this.f;
        m a2 = super.a(viewGroup);
        if (mVar != a2) {
            ((ActionMenuView) a2).setPresenter(this);
        }
        return a2;
    }

    public View a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.m()) {
            actionView = super.a(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public void a(MenuItemImpl menuItemImpl, android.support.v7.view.menu.m.a aVar) {
        aVar.a(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f);
        if (this.A == null) {
            this.A = new b();
        }
        actionMenuItemView.setPopupCallback(this.A);
    }

    public boolean a(int i2, MenuItemImpl menuItemImpl) {
        return menuItemImpl.i();
    }

    public void a(boolean z2) {
        boolean z3;
        boolean z4 = true;
        boolean z5 = false;
        super.a(z2);
        ((View) this.f).requestLayout();
        if (this.c != null) {
            ArrayList l2 = this.c.l();
            int size = l2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ActionProvider supportActionProvider = ((MenuItemImpl) l2.get(i2)).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList arrayList = this.c != null ? this.c.m() : null;
        if (this.o && arrayList != null) {
            int size2 = arrayList.size();
            if (size2 == 1) {
                if (!((MenuItemImpl) arrayList.get(0)).isActionViewExpanded()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z5 = z3;
            } else {
                if (size2 <= 0) {
                    z4 = false;
                }
                z5 = z4;
            }
        }
        if (z5) {
            if (this.g == null) {
                this.g = new d(this.a);
            }
            ViewGroup viewGroup = (ViewGroup) this.g.getParent();
            if (viewGroup != this.f) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.g);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f;
                actionMenuView.addView(this.g, actionMenuView.c());
            }
        } else if (this.g != null && this.g.getParent() == this.f) {
            ((ViewGroup) this.f).removeView(this.g);
        }
        ((ActionMenuView) this.f).setOverflowReserved(this.o);
    }

    public boolean a(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.g) {
            return false;
        }
        return super.a(viewGroup, i2);
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        boolean z2;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.t() != this.c) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.t();
        }
        View a2 = a(subMenuBuilder2.getItem());
        if (a2 == null) {
            return false;
        }
        this.l = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z2 = false;
                break;
            }
            MenuItem item = subMenuBuilder.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z2 = true;
                break;
            }
            i2++;
        }
        this.i = new a(this.b, subMenuBuilder, a2);
        this.i.a(z2);
        this.i.a();
        super.a(subMenuBuilder);
        return true;
    }

    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof android.support.v7.view.menu.m.a) && ((android.support.v7.view.menu.m.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean f() {
        if (!this.o || j() || this.c == null || this.f == null || this.j != null || this.c.m().isEmpty()) {
            return false;
        }
        this.j = new c(new e(this.b, this.c, this.g, true));
        ((View) this.f).post(this.j);
        super.a((SubMenuBuilder) null);
        return true;
    }

    public boolean g() {
        if (this.j == null || this.f == null) {
            e eVar = this.h;
            if (eVar == null) {
                return false;
            }
            eVar.d();
            return true;
        }
        ((View) this.f).removeCallbacks(this.j);
        this.j = null;
        return true;
    }

    public boolean h() {
        return g() | i();
    }

    public boolean i() {
        if (this.i == null) {
            return false;
        }
        this.i.d();
        return true;
    }

    public boolean j() {
        return this.h != null && this.h.f();
    }

    public boolean k() {
        return this.j != null || j();
    }

    public boolean a() {
        int i2;
        ArrayList arrayList;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z3;
        int i11;
        if (this.c != null) {
            ArrayList j2 = this.c.j();
            i2 = j2.size();
            arrayList = j2;
        } else {
            i2 = 0;
            arrayList = null;
        }
        int i12 = this.s;
        int i13 = this.r;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f;
        int i14 = 0;
        int i15 = 0;
        boolean z4 = false;
        int i16 = 0;
        while (i16 < i2) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i16);
            if (menuItemImpl.k()) {
                i14++;
            } else if (menuItemImpl.j()) {
                i15++;
            } else {
                z4 = true;
            }
            if (!this.w || !menuItemImpl.isActionViewExpanded()) {
                i11 = i12;
            } else {
                i11 = 0;
            }
            i16++;
            i12 = i11;
        }
        if (this.o && (z4 || i14 + i15 > i12)) {
            i12--;
        }
        int i17 = i12 - i14;
        SparseBooleanArray sparseBooleanArray = this.y;
        sparseBooleanArray.clear();
        int i18 = 0;
        if (this.u) {
            i18 = i13 / this.x;
            i3 = ((i13 % this.x) / i18) + this.x;
        } else {
            i3 = 0;
        }
        int i19 = 0;
        int i20 = 0;
        int i21 = i18;
        while (i19 < i2) {
            MenuItemImpl menuItemImpl2 = (MenuItemImpl) arrayList.get(i19);
            if (menuItemImpl2.k()) {
                View a2 = a(menuItemImpl2, this.z, viewGroup);
                if (this.z == null) {
                    this.z = a2;
                }
                if (this.u) {
                    i21 -= ActionMenuView.a(a2, i3, i21, makeMeasureSpec, 0);
                } else {
                    a2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                i4 = a2.getMeasuredWidth();
                int i22 = i13 - i4;
                if (i20 != 0) {
                    i4 = i20;
                }
                int groupId = menuItemImpl2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                menuItemImpl2.d(true);
                i5 = i22;
                i6 = i17;
            } else if (menuItemImpl2.j()) {
                int groupId2 = menuItemImpl2.getGroupId();
                boolean z5 = sparseBooleanArray.get(groupId2);
                boolean z6 = (i17 > 0 || z5) && i13 > 0 && (!this.u || i21 > 0);
                if (z6) {
                    View a3 = a(menuItemImpl2, this.z, viewGroup);
                    if (this.z == null) {
                        this.z = a3;
                    }
                    if (this.u) {
                        int a4 = ActionMenuView.a(a3, i3, i21, makeMeasureSpec, 0);
                        int i23 = i21 - a4;
                        if (a4 == 0) {
                            z3 = false;
                        } else {
                            z3 = z6;
                        }
                        i10 = i23;
                    } else {
                        a3.measure(makeMeasureSpec, makeMeasureSpec);
                        boolean z7 = z6;
                        i10 = i21;
                        z3 = z7;
                    }
                    int measuredWidth = a3.getMeasuredWidth();
                    i13 -= measuredWidth;
                    if (i20 == 0) {
                        i20 = measuredWidth;
                    }
                    if (this.u) {
                        z2 = z3 & (i13 >= 0);
                        i7 = i20;
                        i8 = i10;
                    } else {
                        z2 = z3 & (i13 + i20 > 0);
                        i7 = i20;
                        i8 = i10;
                    }
                } else {
                    z2 = z6;
                    i7 = i20;
                    i8 = i21;
                }
                if (z2 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                    i9 = i17;
                } else if (z5) {
                    sparseBooleanArray.put(groupId2, false);
                    int i24 = i17;
                    for (int i25 = 0; i25 < i19; i25++) {
                        MenuItemImpl menuItemImpl3 = (MenuItemImpl) arrayList.get(i25);
                        if (menuItemImpl3.getGroupId() == groupId2) {
                            if (menuItemImpl3.i()) {
                                i24++;
                            }
                            menuItemImpl3.d(false);
                        }
                    }
                    i9 = i24;
                } else {
                    i9 = i17;
                }
                if (z2) {
                    i9--;
                }
                menuItemImpl2.d(z2);
                i4 = i7;
                i5 = i13;
                int i26 = i8;
                i6 = i9;
                i21 = i26;
            } else {
                menuItemImpl2.d(false);
                i4 = i20;
                i5 = i13;
                i6 = i17;
            }
            i19++;
            i13 = i5;
            i17 = i6;
            i20 = i4;
        }
        return true;
    }

    public void a(MenuBuilder menuBuilder, boolean z2) {
        h();
        super.a(menuBuilder, z2);
    }

    public Parcelable c() {
        SavedState savedState = new SavedState();
        savedState.a = this.l;
        return savedState;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            if (savedState.a > 0) {
                MenuItem findItem = this.c.findItem(savedState.a);
                if (findItem != null) {
                    a((SubMenuBuilder) findItem.getSubMenu());
                }
            }
        }
    }

    public void onSubUiVisibilityChanged(boolean z2) {
        if (z2) {
            super.a((SubMenuBuilder) null);
        } else if (this.c != null) {
            this.c.b(false);
        }
    }

    public void a(ActionMenuView actionMenuView) {
        this.f = actionMenuView;
        actionMenuView.a(this.c);
    }
}
