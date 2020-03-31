package android.support.v7.view.menu;

import android.support.v7.view.menu.m.a;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* compiled from: MenuAdapter */
public class f extends BaseAdapter {
    MenuBuilder a;
    private int b = -1;
    private boolean c;
    private final boolean d;
    private final LayoutInflater e;
    private final int f;

    public f(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i) {
        this.d = z;
        this.e = layoutInflater;
        this.a = menuBuilder;
        this.f = i;
        b();
    }

    public void a(boolean z) {
        this.c = z;
    }

    public int getCount() {
        ArrayList j = this.d ? this.a.m() : this.a.j();
        if (this.b < 0) {
            return j.size();
        }
        return j.size() - 1;
    }

    public MenuBuilder a() {
        return this.a;
    }

    /* renamed from: a */
    public MenuItemImpl getItem(int i) {
        ArrayList j = this.d ? this.a.m() : this.a.j();
        if (this.b >= 0 && i >= this.b) {
            i++;
        }
        return (MenuItemImpl) j.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        boolean z;
        if (view == null) {
            view2 = this.e.inflate(this.f, viewGroup, false);
        } else {
            view2 = view;
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i + -1 >= 0 ? getItem(i - 1).getGroupId() : groupId;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view2;
        if (!this.a.b() || groupId == i2) {
            z = false;
        } else {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        a aVar = (a) view2;
        if (this.c) {
            ((ListMenuItemView) view2).setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view2;
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        MenuItemImpl s = this.a.s();
        if (s != null) {
            ArrayList m = this.a.m();
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
        b();
        super.notifyDataSetChanged();
    }
}
