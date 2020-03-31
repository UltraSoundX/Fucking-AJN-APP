package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.a.a.a;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.l;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;

/* compiled from: ToolbarWidgetWrapper */
public class au implements t {
    Toolbar a;
    CharSequence b;
    Callback c;
    boolean d;
    private int e;
    private View f;
    private View g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private CharSequence l;
    private CharSequence m;
    private ActionMenuPresenter n;
    private int o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private Drawable f361q;

    public au(Toolbar toolbar, boolean z) {
        this(toolbar, z, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_material);
    }

    public au(Toolbar toolbar, boolean z, int i2, int i3) {
        this.o = 0;
        this.p = 0;
        this.a = toolbar;
        this.b = toolbar.getTitle();
        this.l = toolbar.getSubtitle();
        this.k = this.b != null;
        this.j = toolbar.getNavigationIcon();
        at a2 = at.a(toolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        this.f361q = a2.a(R.styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence c2 = a2.c(R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(c2)) {
                b(c2);
            }
            CharSequence c3 = a2.c(R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(c3)) {
                c(c3);
            }
            Drawable a3 = a2.a(R.styleable.ActionBar_logo);
            if (a3 != null) {
                b(a3);
            }
            Drawable a4 = a2.a(R.styleable.ActionBar_icon);
            if (a4 != null) {
                a(a4);
            }
            if (this.j == null && this.f361q != null) {
                c(this.f361q);
            }
            c(a2.a(R.styleable.ActionBar_displayOptions, 0));
            int g2 = a2.g(R.styleable.ActionBar_customNavigationLayout, 0);
            if (g2 != 0) {
                a(LayoutInflater.from(this.a.getContext()).inflate(g2, this.a, false));
                c(this.e | 16);
            }
            int f2 = a2.f(R.styleable.ActionBar_height, 0);
            if (f2 > 0) {
                LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = f2;
                this.a.setLayoutParams(layoutParams);
            }
            int d2 = a2.d(R.styleable.ActionBar_contentInsetStart, -1);
            int d3 = a2.d(R.styleable.ActionBar_contentInsetEnd, -1);
            if (d2 >= 0 || d3 >= 0) {
                this.a.a(Math.max(d2, 0), Math.max(d3, 0));
            }
            int g3 = a2.g(R.styleable.ActionBar_titleTextStyle, 0);
            if (g3 != 0) {
                this.a.a(this.a.getContext(), g3);
            }
            int g4 = a2.g(R.styleable.ActionBar_subtitleTextStyle, 0);
            if (g4 != 0) {
                this.a.b(this.a.getContext(), g4);
            }
            int g5 = a2.g(R.styleable.ActionBar_popupTheme, 0);
            if (g5 != 0) {
                this.a.setPopupTheme(g5);
            }
        } else {
            this.e = r();
        }
        a2.a();
        f(i2);
        this.m = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new OnClickListener() {
            final ActionMenuItem a = new ActionMenuItem(au.this.a.getContext(), 0, 16908332, 0, 0, au.this.b);

            public void onClick(View view) {
                if (au.this.c != null && au.this.d) {
                    au.this.c.onMenuItemSelected(0, this.a);
                }
            }
        });
    }

    public void f(int i2) {
        if (i2 != this.p) {
            this.p = i2;
            if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
                d(this.p);
            }
        }
    }

    private int r() {
        if (this.a.getNavigationIcon() == null) {
            return 11;
        }
        this.f361q = this.a.getNavigationIcon();
        return 15;
    }

    public ViewGroup a() {
        return this.a;
    }

    public Context b() {
        return this.a.getContext();
    }

    public boolean c() {
        return this.a.g();
    }

    public void d() {
        this.a.h();
    }

    public void a(Callback callback) {
        this.c = callback;
    }

    public void a(CharSequence charSequence) {
        if (!this.k) {
            e(charSequence);
        }
    }

    public CharSequence e() {
        return this.a.getTitle();
    }

    public void b(CharSequence charSequence) {
        this.k = true;
        e(charSequence);
    }

    private void e(CharSequence charSequence) {
        this.b = charSequence;
        if ((this.e & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    public void c(CharSequence charSequence) {
        this.l = charSequence;
        if ((this.e & 8) != 0) {
            this.a.setSubtitle(charSequence);
        }
    }

    public void f() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void g() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void a(int i2) {
        a(i2 != 0 ? a.b(b(), i2) : null);
    }

    public void a(Drawable drawable) {
        this.h = drawable;
        s();
    }

    public void b(int i2) {
        b(i2 != 0 ? a.b(b(), i2) : null);
    }

    public void b(Drawable drawable) {
        this.i = drawable;
        s();
    }

    private void s() {
        Drawable drawable = null;
        if ((this.e & 2) != 0) {
            drawable = (this.e & 1) != 0 ? this.i != null ? this.i : this.h : this.h;
        }
        this.a.setLogo(drawable);
    }

    public boolean h() {
        return this.a.a();
    }

    public boolean i() {
        return this.a.b();
    }

    public boolean j() {
        return this.a.c();
    }

    public boolean k() {
        return this.a.d();
    }

    public boolean l() {
        return this.a.e();
    }

    public void m() {
        this.d = true;
    }

    public void a(Menu menu, l.a aVar) {
        if (this.n == null) {
            this.n = new ActionMenuPresenter(this.a.getContext());
            this.n.a(R.id.action_menu_presenter);
        }
        this.n.a(aVar);
        this.a.a((MenuBuilder) menu, this.n);
    }

    public void n() {
        this.a.f();
    }

    public int o() {
        return this.e;
    }

    public void c(int i2) {
        int i3 = this.e ^ i2;
        this.e = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    u();
                }
                t();
            }
            if ((i3 & 3) != 0) {
                s();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.a.setTitle(this.b);
                    this.a.setSubtitle(this.l);
                } else {
                    this.a.setTitle((CharSequence) null);
                    this.a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) != 0 && this.g != null) {
                if ((i2 & 16) != 0) {
                    this.a.addView(this.g);
                } else {
                    this.a.removeView(this.g);
                }
            }
        }
    }

    public void a(al alVar) {
        if (this.f != null && this.f.getParent() == this.a) {
            this.a.removeView(this.f);
        }
        this.f = alVar;
        if (alVar != null && this.o == 2) {
            this.a.addView(this.f, 0);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.f.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.a = 8388691;
            alVar.setAllowCollapse(true);
        }
    }

    public void a(boolean z) {
        this.a.setCollapsible(z);
    }

    public void b(boolean z) {
    }

    public int p() {
        return this.o;
    }

    public void a(View view) {
        if (!(this.g == null || (this.e & 16) == 0)) {
            this.a.removeView(this.g);
        }
        this.g = view;
        if (view != null && (this.e & 16) != 0) {
            this.a.addView(this.g);
        }
    }

    public ViewPropertyAnimatorCompat a(final int i2, long j2) {
        return ViewCompat.animate(this.a).alpha(i2 == 0 ? 1.0f : 0.0f).setDuration(j2).setListener(new ViewPropertyAnimatorListenerAdapter() {
            private boolean c = false;

            public void onAnimationStart(View view) {
                au.this.a.setVisibility(0);
            }

            public void onAnimationEnd(View view) {
                if (!this.c) {
                    au.this.a.setVisibility(i2);
                }
            }

            public void onAnimationCancel(View view) {
                this.c = true;
            }
        });
    }

    public void c(Drawable drawable) {
        this.j = drawable;
        t();
    }

    private void t() {
        if ((this.e & 4) != 0) {
            this.a.setNavigationIcon(this.j != null ? this.j : this.f361q);
        } else {
            this.a.setNavigationIcon((Drawable) null);
        }
    }

    public void d(CharSequence charSequence) {
        this.m = charSequence;
        u();
    }

    public void d(int i2) {
        d((CharSequence) i2 == 0 ? null : b().getString(i2));
    }

    private void u() {
        if ((this.e & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.m)) {
            this.a.setNavigationContentDescription(this.p);
        } else {
            this.a.setNavigationContentDescription(this.m);
        }
    }

    public void e(int i2) {
        this.a.setVisibility(i2);
    }

    public void a(l.a aVar, MenuBuilder.a aVar2) {
        this.a.a(aVar, aVar2);
    }

    public Menu q() {
        return this.a.getMenu();
    }
}
