package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.R;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.m;
import android.support.v7.widget.RecyclerView.v;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

/* compiled from: NavigationMenuPresenter */
public class a implements l {
    LinearLayout a;
    MenuBuilder b;
    b c;
    LayoutInflater d;
    int e;
    boolean f;
    ColorStateList g;
    ColorStateList h;
    Drawable i;
    int j;
    int k;
    int l;
    final OnClickListener m = new OnClickListener() {
        public void onClick(View view) {
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
            a.this.b(true);
            MenuItemImpl itemData = navigationMenuItemView.getItemData();
            boolean a2 = a.this.b.a((MenuItem) itemData, (l) a.this, 0);
            if (itemData != null && itemData.isCheckable() && a2) {
                a.this.c.a(itemData);
            }
            a.this.b(false);
            a.this.a(false);
        }
    };
    private NavigationMenuView n;
    private android.support.v7.view.menu.l.a o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private int f319q;

    /* renamed from: android.support.design.internal.a$a reason: collision with other inner class name */
    /* compiled from: NavigationMenuPresenter */
    private static class C0006a extends j {
        public C0006a(View view) {
            super(view);
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private class b extends android.support.v7.widget.RecyclerView.a<j> {
        private final ArrayList<d> b = new ArrayList<>();
        private MenuItemImpl c;
        private boolean d;

        b() {
            d();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemCount() {
            return this.b.size();
        }

        public int getItemViewType(int i) {
            d dVar = (d) this.b.get(i);
            if (dVar instanceof e) {
                return 2;
            }
            if (dVar instanceof c) {
                return 3;
            }
            if (!(dVar instanceof f)) {
                throw new RuntimeException("Unknown item type.");
            } else if (((f) dVar).a().hasSubMenu()) {
                return 1;
            } else {
                return 0;
            }
        }

        /* renamed from: a */
        public j onCreateViewHolder(ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                    return new g(a.this.d, viewGroup, a.this.m);
                case 1:
                    return new i(a.this.d, viewGroup);
                case 2:
                    return new h(a.this.d, viewGroup);
                case 3:
                    return new C0006a(a.this.a);
                default:
                    return null;
            }
        }

        /* renamed from: a */
        public void onBindViewHolder(j jVar, int i) {
            switch (getItemViewType(i)) {
                case 0:
                    NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) jVar.itemView;
                    navigationMenuItemView.setIconTintList(a.this.h);
                    if (a.this.f) {
                        navigationMenuItemView.setTextAppearance(a.this.e);
                    }
                    if (a.this.g != null) {
                        navigationMenuItemView.setTextColor(a.this.g);
                    }
                    ViewCompat.setBackground(navigationMenuItemView, a.this.i != null ? a.this.i.getConstantState().newDrawable() : null);
                    f fVar = (f) this.b.get(i);
                    navigationMenuItemView.setNeedsEmptyIcon(fVar.a);
                    navigationMenuItemView.setHorizontalPadding(a.this.j);
                    navigationMenuItemView.setIconPadding(a.this.k);
                    navigationMenuItemView.a(fVar.a(), 0);
                    return;
                case 1:
                    ((TextView) jVar.itemView).setText(((f) this.b.get(i)).a().getTitle());
                    return;
                case 2:
                    e eVar = (e) this.b.get(i);
                    jVar.itemView.setPadding(0, eVar.a(), 0, eVar.b());
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        public void onViewRecycled(j jVar) {
            if (jVar instanceof g) {
                ((NavigationMenuItemView) jVar.itemView).b();
            }
        }

        public void a() {
            d();
            notifyDataSetChanged();
        }

        private void d() {
            boolean z;
            int i;
            int i2;
            if (!this.d) {
                this.d = true;
                this.b.clear();
                this.b.add(new c());
                int i3 = -1;
                int i4 = 0;
                boolean z2 = false;
                int size = a.this.b.j().size();
                int i5 = 0;
                while (i5 < size) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) a.this.b.j().get(i5);
                    if (menuItemImpl.isChecked()) {
                        a(menuItemImpl);
                    }
                    if (menuItemImpl.isCheckable()) {
                        menuItemImpl.a(false);
                    }
                    if (menuItemImpl.hasSubMenu()) {
                        SubMenu subMenu = menuItemImpl.getSubMenu();
                        if (subMenu.hasVisibleItems()) {
                            if (i5 != 0) {
                                this.b.add(new e(a.this.l, 0));
                            }
                            this.b.add(new f(menuItemImpl));
                            boolean z3 = false;
                            int size2 = this.b.size();
                            int size3 = subMenu.size();
                            for (int i6 = 0; i6 < size3; i6++) {
                                MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenu.getItem(i6);
                                if (menuItemImpl2.isVisible()) {
                                    if (!z3 && menuItemImpl2.getIcon() != null) {
                                        z3 = true;
                                    }
                                    if (menuItemImpl2.isCheckable()) {
                                        menuItemImpl2.a(false);
                                    }
                                    if (menuItemImpl.isChecked()) {
                                        a(menuItemImpl);
                                    }
                                    this.b.add(new f(menuItemImpl2));
                                }
                            }
                            if (z3) {
                                a(size2, this.b.size());
                            }
                        }
                        i2 = i3;
                    } else {
                        int groupId = menuItemImpl.getGroupId();
                        if (groupId != i3) {
                            i = this.b.size();
                            z = menuItemImpl.getIcon() != null;
                            if (i5 != 0) {
                                i++;
                                this.b.add(new e(a.this.l, a.this.l));
                            }
                        } else if (z2 || menuItemImpl.getIcon() == null) {
                            z = z2;
                            i = i4;
                        } else {
                            z = true;
                            a(i4, this.b.size());
                            i = i4;
                        }
                        f fVar = new f(menuItemImpl);
                        fVar.a = z;
                        this.b.add(fVar);
                        z2 = z;
                        i4 = i;
                        i2 = groupId;
                    }
                    i5++;
                    i3 = i2;
                }
                this.d = false;
            }
        }

        private void a(int i, int i2) {
            while (i < i2) {
                ((f) this.b.get(i)).a = true;
                i++;
            }
        }

        public void a(MenuItemImpl menuItemImpl) {
            if (this.c != menuItemImpl && menuItemImpl.isCheckable()) {
                if (this.c != null) {
                    this.c.setChecked(false);
                }
                this.c = menuItemImpl;
                menuItemImpl.setChecked(true);
            }
        }

        public MenuItemImpl b() {
            return this.c;
        }

        public Bundle c() {
            Bundle bundle = new Bundle();
            if (this.c != null) {
                bundle.putInt("android:menu:checked", this.c.getItemId());
            }
            SparseArray sparseArray = new SparseArray();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                d dVar = (d) this.b.get(i);
                if (dVar instanceof f) {
                    MenuItemImpl a2 = ((f) dVar).a();
                    View view = a2 != null ? a2.getActionView() : null;
                    if (view != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        view.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(a2.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public void a(Bundle bundle) {
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.d = true;
                int size = this.b.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    d dVar = (d) this.b.get(i2);
                    if (dVar instanceof f) {
                        MenuItemImpl a2 = ((f) dVar).a();
                        if (a2 != null && a2.getItemId() == i) {
                            a(a2);
                            break;
                        }
                    }
                    i2++;
                }
                this.d = false;
                d();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.b.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    d dVar2 = (d) this.b.get(i3);
                    if (dVar2 instanceof f) {
                        MenuItemImpl a3 = ((f) dVar2).a();
                        if (a3 != null) {
                            View actionView = a3.getActionView();
                            if (actionView != null) {
                                ParcelableSparseArray parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(a3.getItemId());
                                if (parcelableSparseArray != null) {
                                    actionView.restoreHierarchyState(parcelableSparseArray);
                                }
                            }
                        }
                    }
                }
            }
        }

        public void a(boolean z) {
            this.d = z;
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private static class c implements d {
        c() {
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private interface d {
    }

    /* compiled from: NavigationMenuPresenter */
    private static class e implements d {
        private final int a;
        private final int b;

        public e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private static class f implements d {
        boolean a;
        private final MenuItemImpl b;

        f(MenuItemImpl menuItemImpl) {
            this.b = menuItemImpl;
        }

        public MenuItemImpl a() {
            return this.b;
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private static class g extends j {
        public g(LayoutInflater layoutInflater, ViewGroup viewGroup, OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private static class h extends j {
        public h(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private static class i extends j {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* compiled from: NavigationMenuPresenter */
    private static abstract class j extends v {
        public j(View view) {
            super(view);
        }
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        this.d = LayoutInflater.from(context);
        this.b = menuBuilder;
        this.l = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public m a(ViewGroup viewGroup) {
        if (this.n == null) {
            this.n = (NavigationMenuView) this.d.inflate(R.layout.design_navigation_menu, viewGroup, false);
            if (this.c == null) {
                this.c = new b();
            }
            this.a = (LinearLayout) this.d.inflate(R.layout.design_navigation_item_header, this.n, false);
            this.n.setAdapter(this.c);
        }
        return this.n;
    }

    public void a(boolean z) {
        if (this.c != null) {
            this.c.a();
        }
    }

    public void a(android.support.v7.view.menu.l.a aVar) {
        this.o = aVar;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.o != null) {
            this.o.a(menuBuilder, z);
        }
    }

    public boolean a() {
        return false;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public int b() {
        return this.p;
    }

    public void a(int i2) {
        this.p = i2;
    }

    public Parcelable c() {
        Bundle bundle = new Bundle();
        if (this.n != null) {
            SparseArray sparseArray = new SparseArray();
            this.n.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        if (this.c != null) {
            bundle.putBundle("android:menu:adapter", this.c.c());
        }
        if (this.a != null) {
            SparseArray sparseArray2 = new SparseArray();
            this.a.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.n.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.c.a(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.a.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    public void a(MenuItemImpl menuItemImpl) {
        this.c.a(menuItemImpl);
    }

    public MenuItemImpl d() {
        return this.c.b();
    }

    public View b(int i2) {
        View inflate = this.d.inflate(i2, this.a, false);
        a(inflate);
        return inflate;
    }

    public void a(View view) {
        this.a.addView(view);
        this.n.setPadding(0, 0, 0, this.n.getPaddingBottom());
    }

    public int e() {
        return this.a.getChildCount();
    }

    public ColorStateList f() {
        return this.h;
    }

    public void a(ColorStateList colorStateList) {
        this.h = colorStateList;
        a(false);
    }

    public ColorStateList g() {
        return this.g;
    }

    public void b(ColorStateList colorStateList) {
        this.g = colorStateList;
        a(false);
    }

    public void c(int i2) {
        this.e = i2;
        this.f = true;
        a(false);
    }

    public Drawable h() {
        return this.i;
    }

    public void a(Drawable drawable) {
        this.i = drawable;
        a(false);
    }

    public int i() {
        return this.j;
    }

    public void d(int i2) {
        this.j = i2;
        a(false);
    }

    public int j() {
        return this.k;
    }

    public void e(int i2) {
        this.k = i2;
        a(false);
    }

    public void b(boolean z) {
        if (this.c != null) {
            this.c.a(z);
        }
    }

    public void a(WindowInsetsCompat windowInsetsCompat) {
        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        if (this.f319q != systemWindowInsetTop) {
            this.f319q = systemWindowInsetTop;
            if (this.a.getChildCount() == 0) {
                this.n.setPadding(0, this.f319q, 0, this.n.getPaddingBottom());
            }
        }
        ViewCompat.dispatchApplyWindowInsets(this.a, windowInsetsCompat);
    }
}
