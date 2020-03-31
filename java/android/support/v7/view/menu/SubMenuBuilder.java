package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuBuilder.a;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.baidu.mobstat.Config;

public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder d;
    private MenuItemImpl e;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.d = menuBuilder;
        this.e = menuItemImpl;
    }

    public void setQwertyMode(boolean z) {
        this.d.setQwertyMode(z);
    }

    public boolean c() {
        return this.d.c();
    }

    public boolean d() {
        return this.d.d();
    }

    public Menu t() {
        return this.d;
    }

    public MenuItem getItem() {
        return this.e;
    }

    public void a(a aVar) {
        this.d.a(aVar);
    }

    public MenuBuilder q() {
        return this.d.q();
    }

    /* access modifiers changed from: 0000 */
    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return super.a(menuBuilder, menuItem) || this.d.a(menuBuilder, menuItem);
    }

    public SubMenu setIcon(Drawable drawable) {
        this.e.setIcon(drawable);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.e.setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.a(drawable);
    }

    public SubMenu setHeaderIcon(int i) {
        return (SubMenu) super.e(i);
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.a(charSequence);
    }

    public SubMenu setHeaderTitle(int i) {
        return (SubMenu) super.d(i);
    }

    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.a(view);
    }

    public boolean c(MenuItemImpl menuItemImpl) {
        return this.d.c(menuItemImpl);
    }

    public boolean d(MenuItemImpl menuItemImpl) {
        return this.d.d(menuItemImpl);
    }

    public String a() {
        int i = this.e != null ? this.e.getItemId() : 0;
        if (i == 0) {
            return null;
        }
        return super.a() + Config.TRACE_TODAY_VISIT_SPLIT + i;
    }

    public void setGroupDividerEnabled(boolean z) {
        this.d.setGroupDividerEnabled(z);
    }

    public boolean b() {
        return this.d.b();
    }
}
