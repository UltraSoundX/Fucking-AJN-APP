package android.support.v7.view.menu;

import android.content.Context;
import android.os.Parcelable;

/* compiled from: MenuPresenter */
public interface l {

    /* compiled from: MenuPresenter */
    public interface a {
        void a(MenuBuilder menuBuilder, boolean z);

        boolean a(MenuBuilder menuBuilder);
    }

    void a(Context context, MenuBuilder menuBuilder);

    void a(Parcelable parcelable);

    void a(MenuBuilder menuBuilder, boolean z);

    void a(a aVar);

    void a(boolean z);

    boolean a();

    boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    boolean a(SubMenuBuilder subMenuBuilder);

    int b();

    boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    Parcelable c();
}
