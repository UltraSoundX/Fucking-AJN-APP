package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.i;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.au;
import android.support.v7.widget.t;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window.Callback;
import java.util.ArrayList;

/* compiled from: ToolbarActionBar */
class f extends ActionBar {
    t a;
    boolean b;
    Callback c;
    private boolean d;
    private boolean e;
    private ArrayList<android.support.v7.app.ActionBar.a> f = new ArrayList<>();
    private final Runnable g = new Runnable() {
        public void run() {
            f.this.i();
        }
    };
    private final android.support.v7.widget.Toolbar.b h = new android.support.v7.widget.Toolbar.b() {
        public boolean a(MenuItem menuItem) {
            return f.this.c.onMenuItemSelected(0, menuItem);
        }
    };

    /* compiled from: ToolbarActionBar */
    private final class a implements android.support.v7.view.menu.l.a {
        private boolean b;

        a() {
        }

        public boolean a(MenuBuilder menuBuilder) {
            if (f.this.c == null) {
                return false;
            }
            f.this.c.onMenuOpened(108, menuBuilder);
            return true;
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
            if (!this.b) {
                this.b = true;
                f.this.a.n();
                if (f.this.c != null) {
                    f.this.c.onPanelClosed(108, menuBuilder);
                }
                this.b = false;
            }
        }
    }

    /* compiled from: ToolbarActionBar */
    private final class b implements android.support.v7.view.menu.MenuBuilder.a {
        b() {
        }

        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        public void a(MenuBuilder menuBuilder) {
            if (f.this.c == null) {
                return;
            }
            if (f.this.a.i()) {
                f.this.c.onPanelClosed(108, menuBuilder);
            } else if (f.this.c.onPreparePanel(0, null, menuBuilder)) {
                f.this.c.onMenuOpened(108, menuBuilder);
            }
        }
    }

    /* compiled from: ToolbarActionBar */
    private class c extends i {
        public c(Callback callback) {
            super(callback);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel && !f.this.b) {
                f.this.a.m();
                f.this.b = true;
            }
            return onPreparePanel;
        }

        public View onCreatePanelView(int i) {
            if (i == 0) {
                return new View(f.this.a.b());
            }
            return super.onCreatePanelView(i);
        }
    }

    f(Toolbar toolbar, CharSequence charSequence, Callback callback) {
        this.a = new au(toolbar, false);
        this.c = new c(callback);
        this.a.a(this.c);
        toolbar.setOnMenuItemClickListener(this.h);
        this.a.a(charSequence);
    }

    public Callback h() {
        return this.c;
    }

    public void a(boolean z) {
    }

    public void a(float f2) {
        ViewCompat.setElevation(this.a.a(), f2);
    }

    public Context b() {
        return this.a.b();
    }

    public void c(boolean z) {
    }

    public void a(int i) {
        this.a.d(i);
    }

    public void d(boolean z) {
    }

    public void a(Configuration configuration) {
        super.a(configuration);
    }

    public void a(CharSequence charSequence) {
        this.a.a(charSequence);
    }

    public int a() {
        return this.a.o();
    }

    public boolean c() {
        return this.a.k();
    }

    public boolean d() {
        return this.a.l();
    }

    public boolean e() {
        this.a.a().removeCallbacks(this.g);
        ViewCompat.postOnAnimation(this.a.a(), this.g);
        return true;
    }

    public boolean f() {
        if (!this.a.c()) {
            return false;
        }
        this.a.d();
        return true;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public void i() {
        Menu j = j();
        MenuBuilder menuBuilder = j instanceof MenuBuilder ? (MenuBuilder) j : null;
        if (menuBuilder != null) {
            menuBuilder.h();
        }
        try {
            j.clear();
            if (!this.c.onCreatePanelMenu(0, j) || !this.c.onPreparePanel(0, null, j)) {
                j.clear();
            }
            if (menuBuilder != null) {
                menuBuilder.i();
            }
        } catch (Throwable th) {
            if (menuBuilder != null) {
                menuBuilder.i();
            }
            throw th;
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            c();
        }
        return true;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        boolean z;
        Menu j = j();
        if (j == null) {
            return false;
        }
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
            z = true;
        } else {
            z = false;
        }
        j.setQwertyMode(z);
        return j.performShortcut(i, keyEvent, 0);
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        this.a.a().removeCallbacks(this.g);
    }

    public void e(boolean z) {
        if (z != this.e) {
            this.e = z;
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                ((android.support.v7.app.ActionBar.a) this.f.get(i)).a(z);
            }
        }
    }

    private Menu j() {
        if (!this.d) {
            this.a.a((android.support.v7.view.menu.l.a) new a(), (android.support.v7.view.menu.MenuBuilder.a) new b());
            this.d = true;
        }
        return this.a.q();
    }
}
