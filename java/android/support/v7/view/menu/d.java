package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.ac;
import android.support.v7.widget.ad;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CascadingMenuPopup */
final class d extends j implements l, OnKeyListener, OnDismissListener {
    private static final int g = R.layout.abc_cascading_menu_item_layout;
    private OnDismissListener A;
    final Handler a;
    final List<a> b = new ArrayList();
    final OnGlobalLayoutListener c = new OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            if (d.this.f() && d.this.b.size() > 0 && !((a) d.this.b.get(0)).a.c()) {
                View view = d.this.d;
                if (view == null || !view.isShown()) {
                    d.this.e();
                    return;
                }
                for (a aVar : d.this.b) {
                    aVar.a.d();
                }
            }
        }
    };
    View d;
    ViewTreeObserver e;
    boolean f;
    private final Context h;
    private final int i;
    private final int j;
    private final int k;
    private final boolean l;
    private final List<MenuBuilder> m = new ArrayList();
    private final OnAttachStateChangeListener n = new OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            if (d.this.e != null) {
                if (!d.this.e.isAlive()) {
                    d.this.e = view.getViewTreeObserver();
                }
                d.this.e.removeGlobalOnLayoutListener(d.this.c);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private final ac o = new ac() {
        public void a(MenuBuilder menuBuilder, MenuItem menuItem) {
            d.this.a.removeCallbacksAndMessages(menuBuilder);
        }

        public void b(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            int i;
            final a aVar;
            d.this.a.removeCallbacksAndMessages(null);
            int i2 = 0;
            int size = d.this.b.size();
            while (true) {
                if (i2 >= size) {
                    i = -1;
                    break;
                } else if (menuBuilder == ((a) d.this.b.get(i2)).b) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i != -1) {
                int i3 = i + 1;
                if (i3 < d.this.b.size()) {
                    aVar = (a) d.this.b.get(i3);
                } else {
                    aVar = null;
                }
                d.this.a.postAtTime(new Runnable() {
                    public void run() {
                        if (aVar != null) {
                            d.this.f = true;
                            aVar.b.b(false);
                            d.this.f = false;
                        }
                        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                            menuBuilder.a(menuItem, 4);
                        }
                    }
                }, menuBuilder, SystemClock.uptimeMillis() + 200);
            }
        }
    };
    private int p = 0;

    /* renamed from: q reason: collision with root package name */
    private int f346q = 0;
    private View r;
    private int s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private android.support.v7.view.menu.l.a z;

    /* compiled from: CascadingMenuPopup */
    private static class a {
        public final ad a;
        public final MenuBuilder b;
        public final int c;

        public a(ad adVar, MenuBuilder menuBuilder, int i) {
            this.a = adVar;
            this.b = menuBuilder;
            this.c = i;
        }

        public ListView a() {
            return this.a.g();
        }
    }

    public d(Context context, View view, int i2, int i3, boolean z2) {
        this.h = context;
        this.r = view;
        this.j = i2;
        this.k = i3;
        this.l = z2;
        this.x = false;
        this.s = k();
        Resources resources = context.getResources();
        this.i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.a = new Handler();
    }

    public void b(boolean z2) {
        this.x = z2;
    }

    private ad j() {
        ad adVar = new ad(this.h, null, this.j, this.k);
        adVar.a(this.o);
        adVar.a((OnItemClickListener) this);
        adVar.a((OnDismissListener) this);
        adVar.b(this.r);
        adVar.e(this.f346q);
        adVar.a(true);
        adVar.h(2);
        return adVar;
    }

    public void d() {
        if (!f()) {
            for (MenuBuilder c2 : this.m) {
                c(c2);
            }
            this.m.clear();
            this.d = this.r;
            if (this.d != null) {
                boolean z2 = this.e == null;
                this.e = this.d.getViewTreeObserver();
                if (z2) {
                    this.e.addOnGlobalLayoutListener(this.c);
                }
                this.d.addOnAttachStateChangeListener(this.n);
            }
        }
    }

    public void e() {
        int size = this.b.size();
        if (size > 0) {
            a[] aVarArr = (a[]) this.b.toArray(new a[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                a aVar = aVarArr[i2];
                if (aVar.a.f()) {
                    aVar.a.e();
                }
            }
        }
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        e();
        return true;
    }

    private int k() {
        if (ViewCompat.getLayoutDirection(this.r) == 1) {
            return 0;
        }
        return 1;
    }

    private int d(int i2) {
        ListView a2 = ((a) this.b.get(this.b.size() - 1)).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.d.getWindowVisibleDisplayFrame(rect);
        if (this.s == 1) {
            if (a2.getWidth() + iArr[0] + i2 > rect.right) {
                return 0;
            }
            return 1;
        } else if (iArr[0] - i2 < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void a(MenuBuilder menuBuilder) {
        menuBuilder.a((l) this, this.h);
        if (f()) {
            c(menuBuilder);
        } else {
            this.m.add(menuBuilder);
        }
    }

    private void c(MenuBuilder menuBuilder) {
        View view;
        a aVar;
        boolean z2;
        int i2;
        int i3;
        int i4;
        LayoutInflater from = LayoutInflater.from(this.h);
        f fVar = new f(menuBuilder, from, this.l, g);
        if (!f() && this.x) {
            fVar.a(true);
        } else if (f()) {
            fVar.a(j.b(menuBuilder));
        }
        int a2 = a(fVar, null, this.h, this.i);
        ad j2 = j();
        j2.a((ListAdapter) fVar);
        j2.g(a2);
        j2.e(this.f346q);
        if (this.b.size() > 0) {
            a aVar2 = (a) this.b.get(this.b.size() - 1);
            view = a(aVar2, menuBuilder);
            aVar = aVar2;
        } else {
            view = null;
            aVar = null;
        }
        if (view != null) {
            j2.c(false);
            j2.a((Object) null);
            int d2 = d(a2);
            if (d2 == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.s = d2;
            if (VERSION.SDK_INT >= 26) {
                j2.b(view);
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr = new int[2];
                this.r.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.f346q & 7) == 5) {
                    iArr[0] = iArr[0] + this.r.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i2 = iArr2[0] - iArr[0];
                i3 = iArr2[1] - iArr[1];
            }
            if ((this.f346q & 5) == 5) {
                if (z2) {
                    i4 = i2 + a2;
                } else {
                    i4 = i2 - view.getWidth();
                }
            } else if (z2) {
                i4 = view.getWidth() + i2;
            } else {
                i4 = i2 - a2;
            }
            j2.c(i4);
            j2.b(true);
            j2.d(i3);
        } else {
            if (this.t) {
                j2.c(this.v);
            }
            if (this.u) {
                j2.d(this.w);
            }
            j2.a(i());
        }
        this.b.add(new a(j2, menuBuilder, this.s));
        j2.d();
        ListView g2 = j2.g();
        g2.setOnKeyListener(this);
        if (aVar == null && this.y && menuBuilder.n() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, g2, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            frameLayout.setEnabled(false);
            textView.setText(menuBuilder.n());
            g2.addHeaderView(frameLayout, null, false);
            j2.d();
        }
    }

    private MenuItem a(MenuBuilder menuBuilder, MenuBuilder menuBuilder2) {
        int size = menuBuilder.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menuBuilder.getItem(i2);
            if (item.hasSubMenu() && menuBuilder2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View a(a aVar, MenuBuilder menuBuilder) {
        f fVar;
        int i2;
        int i3;
        int i4 = 0;
        MenuItem a2 = a(aVar.b, menuBuilder);
        if (a2 == null) {
            return null;
        }
        ListView a3 = aVar.a();
        ListAdapter adapter = a3.getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i2 = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            i2 = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i4 >= count) {
                i3 = -1;
                break;
            } else if (a2 == fVar.getItem(i4)) {
                i3 = i4;
                break;
            } else {
                i4++;
            }
        }
        if (i3 == -1) {
            return null;
        }
        int firstVisiblePosition = (i3 + i2) - a3.getFirstVisiblePosition();
        if (firstVisiblePosition < 0 || firstVisiblePosition >= a3.getChildCount()) {
            return null;
        }
        return a3.getChildAt(firstVisiblePosition);
    }

    public boolean f() {
        return this.b.size() > 0 && ((a) this.b.get(0)).a.f();
    }

    public void onDismiss() {
        a aVar;
        int size = this.b.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                aVar = null;
                break;
            }
            aVar = (a) this.b.get(i2);
            if (!aVar.a.f()) {
                break;
            }
            i2++;
        }
        if (aVar != null) {
            aVar.b.b(false);
        }
    }

    public void a(boolean z2) {
        for (a a2 : this.b) {
            a(a2.a().getAdapter()).notifyDataSetChanged();
        }
    }

    public void a(android.support.v7.view.menu.l.a aVar) {
        this.z = aVar;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        for (a aVar : this.b) {
            if (subMenuBuilder == aVar.b) {
                aVar.a().requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        a((MenuBuilder) subMenuBuilder);
        if (this.z != null) {
            this.z.a(subMenuBuilder);
        }
        return true;
    }

    private int d(MenuBuilder menuBuilder) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (menuBuilder == ((a) this.b.get(i2)).b) {
                return i2;
            }
        }
        return -1;
    }

    public void a(MenuBuilder menuBuilder, boolean z2) {
        int d2 = d(menuBuilder);
        if (d2 >= 0) {
            int i2 = d2 + 1;
            if (i2 < this.b.size()) {
                ((a) this.b.get(i2)).b.b(false);
            }
            a aVar = (a) this.b.remove(d2);
            aVar.b.b((l) this);
            if (this.f) {
                aVar.a.b(null);
                aVar.a.b(0);
            }
            aVar.a.e();
            int size = this.b.size();
            if (size > 0) {
                this.s = ((a) this.b.get(size - 1)).c;
            } else {
                this.s = k();
            }
            if (size == 0) {
                e();
                if (this.z != null) {
                    this.z.a(menuBuilder, true);
                }
                if (this.e != null) {
                    if (this.e.isAlive()) {
                        this.e.removeGlobalOnLayoutListener(this.c);
                    }
                    this.e = null;
                }
                this.d.removeOnAttachStateChangeListener(this.n);
                this.A.onDismiss();
            } else if (z2) {
                ((a) this.b.get(0)).b.b(false);
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

    public void a(int i2) {
        if (this.p != i2) {
            this.p = i2;
            this.f346q = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this.r));
        }
    }

    public void a(View view) {
        if (this.r != view) {
            this.r = view;
            this.f346q = GravityCompat.getAbsoluteGravity(this.p, ViewCompat.getLayoutDirection(this.r));
        }
    }

    public void a(OnDismissListener onDismissListener) {
        this.A = onDismissListener;
    }

    public ListView g() {
        if (this.b.isEmpty()) {
            return null;
        }
        return ((a) this.b.get(this.b.size() - 1)).a();
    }

    public void b(int i2) {
        this.t = true;
        this.v = i2;
    }

    public void c(int i2) {
        this.u = true;
        this.w = i2;
    }

    public void c(boolean z2) {
        this.y = z2;
    }

    /* access modifiers changed from: protected */
    public boolean h() {
        return false;
    }
}
