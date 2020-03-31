package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.appcompat.R;
import android.support.v7.view.b;
import android.support.v7.view.g;
import android.support.v7.view.h;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.al;
import android.support.v7.widget.t;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: WindowDecorActionBar */
public class i extends ActionBar implements android.support.v7.widget.ActionBarOverlayLayout.a {
    static final /* synthetic */ boolean s = (!i.class.desiredAssertionStatus());
    private static final Interpolator t = new AccelerateInterpolator();
    private static final Interpolator u = new DecelerateInterpolator();
    private boolean A;
    private boolean B;
    private ArrayList<android.support.v7.app.ActionBar.a> C = new ArrayList<>();
    private boolean D;
    private int E = 0;
    private boolean F;
    private boolean G = true;
    private boolean H;
    Context a;
    ActionBarOverlayLayout b;
    ActionBarContainer c;
    t d;
    ActionBarContextView e;
    View f;
    al g;
    a h;
    b i;
    android.support.v7.view.b.a j;
    boolean k = true;
    boolean l;
    boolean m;
    h n;
    boolean o;
    final ViewPropertyAnimatorListener p = new ViewPropertyAnimatorListenerAdapter() {
        public void onAnimationEnd(View view) {
            if (i.this.k && i.this.f != null) {
                i.this.f.setTranslationY(0.0f);
                i.this.c.setTranslationY(0.0f);
            }
            i.this.c.setVisibility(8);
            i.this.c.setTransitioning(false);
            i.this.n = null;
            i.this.h();
            if (i.this.b != null) {
                ViewCompat.requestApplyInsets(i.this.b);
            }
        }
    };

    /* renamed from: q reason: collision with root package name */
    final ViewPropertyAnimatorListener f339q = new ViewPropertyAnimatorListenerAdapter() {
        public void onAnimationEnd(View view) {
            i.this.n = null;
            i.this.c.requestLayout();
        }
    };
    final ViewPropertyAnimatorUpdateListener r = new ViewPropertyAnimatorUpdateListener() {
        public void onAnimationUpdate(View view) {
            ((View) i.this.c.getParent()).invalidate();
        }
    };
    private Context v;
    private Activity w;
    private Dialog x;
    private ArrayList<Object> y = new ArrayList<>();
    private int z = -1;

    /* compiled from: WindowDecorActionBar */
    public class a extends b implements android.support.v7.view.menu.MenuBuilder.a {
        private final Context b;
        private final MenuBuilder c;
        private android.support.v7.view.b.a d;
        private WeakReference<View> e;

        public a(Context context, android.support.v7.view.b.a aVar) {
            this.b = context;
            this.d = aVar;
            this.c = new MenuBuilder(context).a(1);
            this.c.a((android.support.v7.view.menu.MenuBuilder.a) this);
        }

        public MenuInflater a() {
            return new g(this.b);
        }

        public Menu b() {
            return this.c;
        }

        public void c() {
            if (i.this.h == this) {
                if (!i.a(i.this.l, i.this.m, false)) {
                    i.this.i = this;
                    i.this.j = this.d;
                } else {
                    this.d.a(this);
                }
                this.d = null;
                i.this.j(false);
                i.this.e.b();
                i.this.d.a().sendAccessibilityEvent(32);
                i.this.b.setHideOnContentScrollEnabled(i.this.o);
                i.this.h = null;
            }
        }

        public void d() {
            if (i.this.h == this) {
                this.c.h();
                try {
                    this.d.b(this, this.c);
                } finally {
                    this.c.i();
                }
            }
        }

        public boolean e() {
            this.c.h();
            try {
                return this.d.a((b) this, (Menu) this.c);
            } finally {
                this.c.i();
            }
        }

        public void a(View view) {
            i.this.e.setCustomView(view);
            this.e = new WeakReference<>(view);
        }

        public void a(CharSequence charSequence) {
            i.this.e.setSubtitle(charSequence);
        }

        public void b(CharSequence charSequence) {
            i.this.e.setTitle(charSequence);
        }

        public void a(int i) {
            b((CharSequence) i.this.a.getResources().getString(i));
        }

        public void b(int i) {
            a((CharSequence) i.this.a.getResources().getString(i));
        }

        public CharSequence f() {
            return i.this.e.getTitle();
        }

        public CharSequence g() {
            return i.this.e.getSubtitle();
        }

        public void a(boolean z) {
            super.a(z);
            i.this.e.setTitleOptional(z);
        }

        public boolean h() {
            return i.this.e.d();
        }

        public View i() {
            if (this.e != null) {
                return (View) this.e.get();
            }
            return null;
        }

        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (this.d != null) {
                return this.d.a((b) this, menuItem);
            }
            return false;
        }

        public void a(MenuBuilder menuBuilder) {
            if (this.d != null) {
                d();
                i.this.e.a();
            }
        }
    }

    public i(Activity activity, boolean z2) {
        this.w = activity;
        View decorView = activity.getWindow().getDecorView();
        a(decorView);
        if (!z2) {
            this.f = decorView.findViewById(16908290);
        }
    }

    public i(Dialog dialog) {
        this.x = dialog;
        a(dialog.getWindow().getDecorView());
    }

    private void a(View view) {
        boolean z2;
        this.b = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        if (this.b != null) {
            this.b.setActionBarVisibilityCallback(this);
        }
        this.d = b(view.findViewById(R.id.action_bar));
        this.e = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        this.c = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        if (this.d == null || this.e == null || this.c == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.a = this.d.b();
        boolean z3 = (this.d.o() & 4) != 0;
        if (z3) {
            this.A = true;
        }
        android.support.v7.view.a a2 = android.support.v7.view.a.a(this.a);
        if (a2.f() || z3) {
            z2 = true;
        } else {
            z2 = false;
        }
        a(z2);
        k(a2.d());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
            b(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private t b(View view) {
        if (view instanceof t) {
            return (t) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + (view != null ? view.getClass().getSimpleName() : "null"));
    }

    public void a(float f2) {
        ViewCompat.setElevation(this.c, f2);
    }

    public void a(Configuration configuration) {
        k(android.support.v7.view.a.a(this.a).d());
    }

    private void k(boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5 = true;
        this.D = z2;
        if (!this.D) {
            this.d.a((al) null);
            this.c.setTabContainer(this.g);
        } else {
            this.c.setTabContainer(null);
            this.d.a(this.g);
        }
        if (i() == 2) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (this.g != null) {
            if (z3) {
                this.g.setVisibility(0);
                if (this.b != null) {
                    ViewCompat.requestApplyInsets(this.b);
                }
            } else {
                this.g.setVisibility(8);
            }
        }
        t tVar = this.d;
        if (this.D || !z3) {
            z4 = false;
        } else {
            z4 = true;
        }
        tVar.a(z4);
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (this.D || !z3) {
            z5 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z5);
    }

    /* access modifiers changed from: 0000 */
    public void h() {
        if (this.j != null) {
            this.j.a(this.i);
            this.i = null;
            this.j = null;
        }
    }

    public void b(int i2) {
        this.E = i2;
    }

    public void d(boolean z2) {
        this.H = z2;
        if (!z2 && this.n != null) {
            this.n.c();
        }
    }

    public void e(boolean z2) {
        if (z2 != this.B) {
            this.B = z2;
            int size = this.C.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((android.support.v7.app.ActionBar.a) this.C.get(i2)).a(z2);
            }
        }
    }

    public void f(boolean z2) {
        a(z2 ? 4 : 0, 4);
    }

    public void a(boolean z2) {
        this.d.b(z2);
    }

    public void a(CharSequence charSequence) {
        this.d.a(charSequence);
    }

    public void a(int i2, int i3) {
        int o2 = this.d.o();
        if ((i3 & 4) != 0) {
            this.A = true;
        }
        this.d.c((o2 & (i3 ^ -1)) | (i2 & i3));
    }

    public int i() {
        return this.d.p();
    }

    public int a() {
        return this.d.o();
    }

    public b a(android.support.v7.view.b.a aVar) {
        if (this.h != null) {
            this.h.c();
        }
        this.b.setHideOnContentScrollEnabled(false);
        this.e.c();
        a aVar2 = new a(this.e.getContext(), aVar);
        if (!aVar2.e()) {
            return null;
        }
        this.h = aVar2;
        aVar2.d();
        this.e.a(aVar2);
        j(true);
        this.e.sendAccessibilityEvent(32);
        return aVar2;
    }

    public void g(boolean z2) {
        this.k = z2;
    }

    private void n() {
        if (!this.F) {
            this.F = true;
            if (this.b != null) {
                this.b.setShowingForActionMode(true);
            }
            l(false);
        }
    }

    public void j() {
        if (this.m) {
            this.m = false;
            l(true);
        }
    }

    private void o() {
        if (this.F) {
            this.F = false;
            if (this.b != null) {
                this.b.setShowingForActionMode(false);
            }
            l(false);
        }
    }

    public void k() {
        if (!this.m) {
            this.m = true;
            l(true);
        }
    }

    public void b(boolean z2) {
        if (!z2 || this.b.a()) {
            this.o = z2;
            this.b.setHideOnContentScrollEnabled(z2);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    static boolean a(boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return true;
        }
        if (z2 || z3) {
            return false;
        }
        return true;
    }

    private void l(boolean z2) {
        if (a(this.l, this.m, this.F)) {
            if (!this.G) {
                this.G = true;
                h(z2);
            }
        } else if (this.G) {
            this.G = false;
            i(z2);
        }
    }

    public void h(boolean z2) {
        if (this.n != null) {
            this.n.c();
        }
        this.c.setVisibility(0);
        if (this.E != 0 || (!this.H && !z2)) {
            this.c.setAlpha(1.0f);
            this.c.setTranslationY(0.0f);
            if (this.k && this.f != null) {
                this.f.setTranslationY(0.0f);
            }
            this.f339q.onAnimationEnd(null);
        } else {
            this.c.setTranslationY(0.0f);
            float f2 = (float) (-this.c.getHeight());
            if (z2) {
                int[] iArr = {0, 0};
                this.c.getLocationInWindow(iArr);
                f2 -= (float) iArr[1];
            }
            this.c.setTranslationY(f2);
            h hVar = new h();
            ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.c).translationY(0.0f);
            translationY.setUpdateListener(this.r);
            hVar.a(translationY);
            if (this.k && this.f != null) {
                this.f.setTranslationY(f2);
                hVar.a(ViewCompat.animate(this.f).translationY(0.0f));
            }
            hVar.a(u);
            hVar.a(250);
            hVar.a(this.f339q);
            this.n = hVar;
            hVar.a();
        }
        if (this.b != null) {
            ViewCompat.requestApplyInsets(this.b);
        }
    }

    public void i(boolean z2) {
        if (this.n != null) {
            this.n.c();
        }
        if (this.E != 0 || (!this.H && !z2)) {
            this.p.onAnimationEnd(null);
            return;
        }
        this.c.setAlpha(1.0f);
        this.c.setTransitioning(true);
        h hVar = new h();
        float f2 = (float) (-this.c.getHeight());
        if (z2) {
            int[] iArr = {0, 0};
            this.c.getLocationInWindow(iArr);
            f2 -= (float) iArr[1];
        }
        ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.c).translationY(f2);
        translationY.setUpdateListener(this.r);
        hVar.a(translationY);
        if (this.k && this.f != null) {
            hVar.a(ViewCompat.animate(this.f).translationY(f2));
        }
        hVar.a(t);
        hVar.a(250);
        hVar.a(this.p);
        this.n = hVar;
        hVar.a();
    }

    public void j(boolean z2) {
        ViewPropertyAnimatorCompat a2;
        ViewPropertyAnimatorCompat a3;
        if (z2) {
            n();
        } else {
            o();
        }
        if (p()) {
            if (z2) {
                a3 = this.d.a(4, 100);
                a2 = this.e.a(0, 200);
            } else {
                a2 = this.d.a(0, 200);
                a3 = this.e.a(8, 100);
            }
            h hVar = new h();
            hVar.a(a3, a2);
            hVar.a();
        } else if (z2) {
            this.d.e(4);
            this.e.setVisibility(0);
        } else {
            this.d.e(0);
            this.e.setVisibility(8);
        }
    }

    private boolean p() {
        return ViewCompat.isLaidOut(this.c);
    }

    public Context b() {
        if (this.v == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.v = new ContextThemeWrapper(this.a, i2);
            } else {
                this.v = this.a;
            }
        }
        return this.v;
    }

    public void a(int i2) {
        this.d.d(i2);
    }

    public void l() {
        if (this.n != null) {
            this.n.c();
            this.n = null;
        }
    }

    public void m() {
    }

    public boolean f() {
        if (this.d == null || !this.d.c()) {
            return false;
        }
        this.d.d();
        return true;
    }

    public void c(boolean z2) {
        if (!this.A) {
            f(z2);
        }
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        boolean z2;
        if (this.h == null) {
            return false;
        }
        Menu b2 = this.h.b();
        if (b2 == null) {
            return false;
        }
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        b2.setQwertyMode(z2);
        return b2.performShortcut(i2, keyEvent, 0);
    }
}
