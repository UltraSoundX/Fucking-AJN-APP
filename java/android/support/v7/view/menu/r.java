package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: SubMenuWrapperICS */
class r extends o implements SubMenu {
    r(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    public SupportSubMenu b() {
        return (SupportSubMenu) this.b;
    }

    public SubMenu setHeaderTitle(int i) {
        b().setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        b().setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderIcon(int i) {
        b().setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        b().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        b().setHeaderView(view);
        return this;
    }

    public void clearHeader() {
        b().clearHeader();
    }

    public SubMenu setIcon(int i) {
        b().setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        b().setIcon(drawable);
        return this;
    }

    public MenuItem getItem() {
        return a(b().getItem());
    }
}
