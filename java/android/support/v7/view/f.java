package android.support.v7.view;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.view.menu.n;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

/* compiled from: SupportActionModeWrapper */
public class f extends ActionMode {
    final Context a;
    final b b;

    /* compiled from: SupportActionModeWrapper */
    public static class a implements android.support.v7.view.b.a {
        final Callback a;
        final Context b;
        final ArrayList<f> c = new ArrayList<>();
        final SimpleArrayMap<Menu, Menu> d = new SimpleArrayMap<>();

        public a(Context context, Callback callback) {
            this.b = context;
            this.a = callback;
        }

        public boolean a(b bVar, Menu menu) {
            return this.a.onCreateActionMode(b(bVar), a(menu));
        }

        public boolean b(b bVar, Menu menu) {
            return this.a.onPrepareActionMode(b(bVar), a(menu));
        }

        public boolean a(b bVar, MenuItem menuItem) {
            return this.a.onActionItemClicked(b(bVar), n.a(this.b, (SupportMenuItem) menuItem));
        }

        public void a(b bVar) {
            this.a.onDestroyActionMode(b(bVar));
        }

        private Menu a(Menu menu) {
            Menu menu2 = (Menu) this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            Menu a2 = n.a(this.b, (SupportMenu) menu);
            this.d.put(menu, a2);
            return a2;
        }

        public ActionMode b(b bVar) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                f fVar = (f) this.c.get(i);
                if (fVar != null && fVar.b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.b, bVar);
            this.c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.a = context;
        this.b = bVar;
    }

    public Object getTag() {
        return this.b.j();
    }

    public void setTag(Object obj) {
        this.b.a(obj);
    }

    public void setTitle(CharSequence charSequence) {
        this.b.b(charSequence);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.b.a(charSequence);
    }

    public void invalidate() {
        this.b.d();
    }

    public void finish() {
        this.b.c();
    }

    public Menu getMenu() {
        return n.a(this.a, (SupportMenu) this.b.b());
    }

    public CharSequence getTitle() {
        return this.b.f();
    }

    public void setTitle(int i) {
        this.b.a(i);
    }

    public CharSequence getSubtitle() {
        return this.b.g();
    }

    public void setSubtitle(int i) {
        this.b.b(i);
    }

    public View getCustomView() {
        return this.b.i();
    }

    public void setCustomView(View view) {
        this.b.a(view);
    }

    public MenuInflater getMenuInflater() {
        return this.b.a();
    }

    public boolean getTitleOptionalHint() {
        return this.b.k();
    }

    public void setTitleOptionalHint(boolean z) {
        this.b.a(z);
    }

    public boolean isTitleOptional() {
        return this.b.h();
    }
}
