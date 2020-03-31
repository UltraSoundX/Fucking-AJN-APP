package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.app.a.C0012a;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.l.a;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

/* compiled from: MenuDialogHelper */
class g implements OnClickListener, OnDismissListener, OnKeyListener, a {
    e a;
    private MenuBuilder b;
    private android.support.v7.app.a c;
    private a d;

    public g(MenuBuilder menuBuilder) {
        this.b = menuBuilder;
    }

    public void a(IBinder iBinder) {
        MenuBuilder menuBuilder = this.b;
        C0012a aVar = new C0012a(menuBuilder.f());
        this.a = new e(aVar.a(), R.layout.abc_list_menu_item_layout);
        this.a.a((a) this);
        this.b.a((l) this.a);
        aVar.a(this.a.d(), (OnClickListener) this);
        View p = menuBuilder.p();
        if (p != null) {
            aVar.a(p);
        } else {
            aVar.a(menuBuilder.o()).a(menuBuilder.n());
        }
        aVar.a((OnKeyListener) this);
        this.c = aVar.b();
        this.c.setOnDismissListener(this);
        LayoutParams attributes = this.c.getWindow().getAttributes();
        attributes.type = PointerIconCompat.TYPE_HELP;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.c.show();
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window = this.c.getWindow();
                if (window != null) {
                    View decorView = window.getDecorView();
                    if (decorView != null) {
                        DispatcherState keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null) {
                            keyDispatcherState.startTracking(keyEvent, this);
                            return true;
                        }
                    }
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                Window window2 = this.c.getWindow();
                if (window2 != null) {
                    View decorView2 = window2.getDecorView();
                    if (decorView2 != null) {
                        DispatcherState keyDispatcherState2 = decorView2.getKeyDispatcherState();
                        if (keyDispatcherState2 != null && keyDispatcherState2.isTracking(keyEvent)) {
                            this.b.b(true);
                            dialogInterface.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.b.performShortcut(i, keyEvent, 0);
    }

    public void a() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a(this.b, true);
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (z || menuBuilder == this.b) {
            a();
        }
        if (this.d != null) {
            this.d.a(menuBuilder, z);
        }
    }

    public boolean a(MenuBuilder menuBuilder) {
        if (this.d != null) {
            return this.d.a(menuBuilder);
        }
        return false;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a((MenuItem) (MenuItemImpl) this.a.d().getItem(i), 0);
    }
}
