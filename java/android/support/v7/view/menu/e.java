package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

/* compiled from: ListMenuPresenter */
public class e implements l, OnItemClickListener {
    Context a;
    LayoutInflater b;
    MenuBuilder c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    a h;
    private android.support.v7.view.menu.l.a i;
    private int j;

    /* compiled from: ListMenuPresenter */
    private class a extends BaseAdapter {
        private int b = -1;

        public a() {
            a();
        }

        public int getCount() {
            int size = e.this.c.m().size() - e.this.e;
            return this.b < 0 ? size : size - 1;
        }

        /* renamed from: a */
        public MenuItemImpl getItem(int i) {
            ArrayList m = e.this.c.m();
            int i2 = e.this.e + i;
            if (this.b >= 0 && i2 >= this.b) {
                i2++;
            }
            return (MenuItemImpl) m.get(i2);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            if (view == null) {
                view2 = e.this.b.inflate(e.this.g, viewGroup, false);
            } else {
                view2 = view;
            }
            ((android.support.v7.view.menu.m.a) view2).a(getItem(i), 0);
            return view2;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            MenuItemImpl s = e.this.c.s();
            if (s != null) {
                ArrayList m = e.this.c.m();
                int size = m.size();
                for (int i = 0; i < size; i++) {
                    if (((MenuItemImpl) m.get(i)) == s) {
                        this.b = i;
                        return;
                    }
                }
            }
            this.b = -1;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(Context context, int i2) {
        this(i2, 0);
        this.a = context;
        this.b = LayoutInflater.from(this.a);
    }

    public e(int i2, int i3) {
        this.g = i2;
        this.f = i3;
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        if (this.f != 0) {
            this.a = new ContextThemeWrapper(context, this.f);
            this.b = LayoutInflater.from(this.a);
        } else if (this.a != null) {
            this.a = context;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.a);
            }
        }
        this.c = menuBuilder;
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public m a(ViewGroup viewGroup) {
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.h == null) {
                this.h = new a();
            }
            this.d.setAdapter(this.h);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    public ListAdapter d() {
        if (this.h == null) {
            this.h = new a();
        }
        return this.h;
    }

    public void a(boolean z) {
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public void a(android.support.v7.view.menu.l.a aVar) {
        this.i = aVar;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new g(subMenuBuilder).a((IBinder) null);
        if (this.i != null) {
            this.i.a(subMenuBuilder);
        }
        return true;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.i != null) {
            this.i.a(menuBuilder, z);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.c.a((MenuItem) this.h.getItem(i2), (l) this, 0);
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

    public void a(Bundle bundle) {
        SparseArray sparseArray = new SparseArray();
        if (this.d != null) {
            this.d.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    public void b(Bundle bundle) {
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public int b() {
        return this.j;
    }

    public Parcelable c() {
        if (this.d == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        a(bundle);
        return bundle;
    }

    public void a(Parcelable parcelable) {
        b((Bundle) parcelable);
    }
}
