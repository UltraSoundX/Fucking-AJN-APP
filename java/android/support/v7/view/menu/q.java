package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.l.a;
import android.support.v7.widget.ad;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/* compiled from: StandardMenuPopup */
final class q extends j implements l, OnKeyListener, OnItemClickListener, OnDismissListener {
    private static final int e = R.layout.abc_popup_menu_item_layout;
    final ad a;
    final OnGlobalLayoutListener b = new OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            if (q.this.f() && !q.this.a.c()) {
                View view = q.this.c;
                if (view == null || !view.isShown()) {
                    q.this.e();
                } else {
                    q.this.a.d();
                }
            }
        }
    };
    View c;
    ViewTreeObserver d;
    private final Context f;
    private final MenuBuilder g;
    private final f h;
    private final boolean i;
    private final int j;
    private final int k;
    private final int l;
    private final OnAttachStateChangeListener m = new OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            if (q.this.d != null) {
                if (!q.this.d.isAlive()) {
                    q.this.d = view.getViewTreeObserver();
                }
                q.this.d.removeGlobalOnLayoutListener(q.this.b);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private OnDismissListener n;
    private View o;
    private a p;

    /* renamed from: q reason: collision with root package name */
    private boolean f347q;
    private boolean r;
    private int s;
    private int t = 0;
    private boolean u;

    public q(Context context, MenuBuilder menuBuilder, View view, int i2, int i3, boolean z) {
        this.f = context;
        this.g = menuBuilder;
        this.i = z;
        this.h = new f(menuBuilder, LayoutInflater.from(context), this.i, e);
        this.k = i2;
        this.l = i3;
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.o = view;
        this.a = new ad(this.f, null, this.k, this.l);
        menuBuilder.a((l) this, context);
    }

    public void b(boolean z) {
        this.h.a(z);
    }

    public void a(int i2) {
        this.t = i2;
    }

    private boolean j() {
        if (f()) {
            return true;
        }
        if (this.f347q || this.o == null) {
            return false;
        }
        this.c = this.o;
        this.a.a((OnDismissListener) this);
        this.a.a((OnItemClickListener) this);
        this.a.a(true);
        View view = this.c;
        boolean z = this.d == null;
        this.d = view.getViewTreeObserver();
        if (z) {
            this.d.addOnGlobalLayoutListener(this.b);
        }
        view.addOnAttachStateChangeListener(this.m);
        this.a.b(view);
        this.a.e(this.t);
        if (!this.r) {
            this.s = a(this.h, null, this.f, this.j);
            this.r = true;
        }
        this.a.g(this.s);
        this.a.h(2);
        this.a.a(i());
        this.a.d();
        ListView g2 = this.a.g();
        g2.setOnKeyListener(this);
        if (this.u && this.g.n() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f).inflate(R.layout.abc_popup_menu_header_item_layout, g2, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.g.n());
            }
            frameLayout.setEnabled(false);
            g2.addHeaderView(frameLayout, null, false);
        }
        this.a.a((ListAdapter) this.h);
        this.a.d();
        return true;
    }

    public void d() {
        if (!j()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    public void e() {
        if (f()) {
            this.a.e();
        }
    }

    public void a(MenuBuilder menuBuilder) {
    }

    public boolean f() {
        return !this.f347q && this.a.f();
    }

    public void onDismiss() {
        this.f347q = true;
        this.g.close();
        if (this.d != null) {
            if (!this.d.isAlive()) {
                this.d = this.c.getViewTreeObserver();
            }
            this.d.removeGlobalOnLayoutListener(this.b);
            this.d = null;
        }
        this.c.removeOnAttachStateChangeListener(this.m);
        if (this.n != null) {
            this.n.onDismiss();
        }
    }

    public void a(boolean z) {
        this.r = false;
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public void a(a aVar) {
        this.p = aVar;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            k kVar = new k(this.f, subMenuBuilder, this.c, this.i, this.k, this.l);
            kVar.a(this.p);
            kVar.a(j.b((MenuBuilder) subMenuBuilder));
            kVar.a(this.n);
            this.n = null;
            this.g.b(false);
            int j2 = this.a.j();
            int k2 = this.a.k();
            if ((Gravity.getAbsoluteGravity(this.t, ViewCompat.getLayoutDirection(this.o)) & 7) == 5) {
                j2 += this.o.getWidth();
            }
            if (kVar.a(j2, k2)) {
                if (this.p != null) {
                    this.p.a(subMenuBuilder);
                }
                return true;
            }
        }
        return false;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.g) {
            e();
            if (this.p != null) {
                this.p.a(menuBuilder, z);
            }
        }
    }

    public boolean a() {
        return false;
    }

    public Parcelable c() {
        return null;
    }

    public void a(Parcelable parcelable) {
    }

    public void a(View view) {
        this.o = view;
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        e();
        return true;
    }

    public void a(OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    public ListView g() {
        return this.a.g();
    }

    public void b(int i2) {
        this.a.c(i2);
    }

    public void c(int i2) {
        this.a.d(i2);
    }

    public void c(boolean z) {
        this.u = z;
    }
}
