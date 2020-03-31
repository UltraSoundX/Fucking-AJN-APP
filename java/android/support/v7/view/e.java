package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.a;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

/* compiled from: StandaloneActionMode */
public class e extends b implements a {
    private Context a;
    private ActionBarContextView b;
    private b.a c;
    private WeakReference<View> d;
    private boolean e;
    private boolean f;
    private MenuBuilder g;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.a = context;
        this.b = actionBarContextView;
        this.c = aVar;
        this.g = new MenuBuilder(actionBarContextView.getContext()).a(1);
        this.g.a((a) this);
        this.f = z;
    }

    public void b(CharSequence charSequence) {
        this.b.setTitle(charSequence);
    }

    public void a(CharSequence charSequence) {
        this.b.setSubtitle(charSequence);
    }

    public void a(int i) {
        b((CharSequence) this.a.getString(i));
    }

    public void b(int i) {
        a((CharSequence) this.a.getString(i));
    }

    public void a(boolean z) {
        super.a(z);
        this.b.setTitleOptional(z);
    }

    public boolean h() {
        return this.b.d();
    }

    public void a(View view) {
        this.b.setCustomView(view);
        this.d = view != null ? new WeakReference<>(view) : null;
    }

    public void d() {
        this.c.b(this, this.g);
    }

    public void c() {
        if (!this.e) {
            this.e = true;
            this.b.sendAccessibilityEvent(32);
            this.c.a(this);
        }
    }

    public Menu b() {
        return this.g;
    }

    public CharSequence f() {
        return this.b.getTitle();
    }

    public CharSequence g() {
        return this.b.getSubtitle();
    }

    public View i() {
        if (this.d != null) {
            return (View) this.d.get();
        }
        return null;
    }

    public MenuInflater a() {
        return new g(this.b.getContext());
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.c.a((b) this, menuItem);
    }

    public void a(MenuBuilder menuBuilder) {
        d();
        this.b.a();
    }
}
