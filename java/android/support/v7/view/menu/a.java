package android.support.v7.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: BaseMenuPresenter */
public abstract class a implements l {
    protected Context a;
    protected Context b;
    protected MenuBuilder c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    protected m f;
    private android.support.v7.view.menu.l.a g;
    private int h;
    private int i;
    private int j;

    public abstract void a(MenuItemImpl menuItemImpl, android.support.v7.view.menu.m.a aVar);

    public a(Context context, int i2, int i3) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.h = i2;
        this.i = i3;
    }

    public void a(Context context, MenuBuilder menuBuilder) {
        this.b = context;
        this.e = LayoutInflater.from(this.b);
        this.c = menuBuilder;
    }

    public m a(ViewGroup viewGroup) {
        if (this.f == null) {
            this.f = (m) this.d.inflate(this.h, viewGroup, false);
            this.f.a(this.c);
            a(true);
        }
        return this.f;
    }

    public void a(boolean z) {
        int i2;
        int i3;
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup != null) {
            if (this.c != null) {
                this.c.k();
                ArrayList j2 = this.c.j();
                int size = j2.size();
                int i4 = 0;
                i2 = 0;
                while (i4 < size) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) j2.get(i4);
                    if (a(i2, menuItemImpl)) {
                        View childAt = viewGroup.getChildAt(i2);
                        MenuItemImpl menuItemImpl2 = childAt instanceof android.support.v7.view.menu.m.a ? ((android.support.v7.view.menu.m.a) childAt).getItemData() : null;
                        View a2 = a(menuItemImpl, childAt, viewGroup);
                        if (menuItemImpl != menuItemImpl2) {
                            a2.setPressed(false);
                            a2.jumpDrawablesToCurrentState();
                        }
                        if (a2 != childAt) {
                            a(a2, i2);
                        }
                        i3 = i2 + 1;
                    } else {
                        i3 = i2;
                    }
                    i4++;
                    i2 = i3;
                }
            } else {
                i2 = 0;
            }
            while (i2 < viewGroup.getChildCount()) {
                if (!a(viewGroup, i2)) {
                    i2++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f).addView(view, i2);
    }

    /* access modifiers changed from: protected */
    public boolean a(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    public void a(android.support.v7.view.menu.l.a aVar) {
        this.g = aVar;
    }

    public android.support.v7.view.menu.l.a d() {
        return this.g;
    }

    public android.support.v7.view.menu.m.a b(ViewGroup viewGroup) {
        return (android.support.v7.view.menu.m.a) this.d.inflate(this.i, viewGroup, false);
    }

    public View a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        android.support.v7.view.menu.m.a b2;
        if (view instanceof android.support.v7.view.menu.m.a) {
            b2 = (android.support.v7.view.menu.m.a) view;
        } else {
            b2 = b(viewGroup);
        }
        a(menuItemImpl, b2);
        return (View) b2;
    }

    public boolean a(int i2, MenuItemImpl menuItemImpl) {
        return true;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.g != null) {
            this.g.a(menuBuilder, z);
        }
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        if (this.g != null) {
            return this.g.a(subMenuBuilder);
        }
        return false;
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
        return this.j;
    }

    public void a(int i2) {
        this.j = i2;
    }
}
