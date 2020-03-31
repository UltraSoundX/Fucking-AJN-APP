package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* compiled from: MenuWrapperFactory */
public final class n {
    public static Menu a(Context context, SupportMenu supportMenu) {
        return new o(context, supportMenu);
    }

    public static MenuItem a(Context context, SupportMenuItem supportMenuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new i(context, supportMenuItem);
        }
        return new h(context, supportMenuItem);
    }

    public static SubMenu a(Context context, SupportSubMenu supportSubMenu) {
        return new r(context, supportSubMenu);
    }
}
