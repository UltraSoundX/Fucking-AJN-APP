package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.l.a;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow.OnDismissListener;

/* compiled from: MenuPopupHelper */
public class k {
    private final Context a;
    private final MenuBuilder b;
    private final boolean c;
    private final int d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private a i;
    private j j;
    private OnDismissListener k;
    private final OnDismissListener l;

    public k(Context context, MenuBuilder menuBuilder, View view, boolean z, int i2) {
        this(context, menuBuilder, view, z, i2, 0);
    }

    public k(Context context, MenuBuilder menuBuilder, View view, boolean z, int i2, int i3) {
        this.g = GravityCompat.START;
        this.l = new OnDismissListener() {
            public void onDismiss() {
                k.this.e();
            }
        };
        this.a = context;
        this.b = menuBuilder;
        this.f = view;
        this.c = z;
        this.d = i2;
        this.e = i3;
    }

    public void a(OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void a(View view) {
        this.f = view;
    }

    public void a(boolean z) {
        this.h = z;
        if (this.j != null) {
            this.j.b(z);
        }
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a() {
        if (!c()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public j b() {
        if (this.j == null) {
            this.j = g();
        }
        return this.j;
    }

    public boolean c() {
        if (f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public boolean a(int i2, int i3) {
        if (f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(i2, i3, true, true);
        return true;
    }

    private j g() {
        j qVar;
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        if (Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
            qVar = new d(this.a, this.f, this.d, this.e, this.c);
        } else {
            qVar = new q(this.a, this.b, this.f, this.d, this.e, this.c);
        }
        qVar.a(this.b);
        qVar.a(this.l);
        qVar.a(this.f);
        qVar.a(this.i);
        qVar.b(this.h);
        qVar.a(this.g);
        return qVar;
    }

    private void a(int i2, int i3, boolean z, boolean z2) {
        j b2 = b();
        b2.c(z2);
        if (z) {
            if ((GravityCompat.getAbsoluteGravity(this.g, ViewCompat.getLayoutDirection(this.f)) & 7) == 5) {
                i2 -= this.f.getWidth();
            }
            b2.b(i2);
            b2.c(i3);
            int i4 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            b2.a(new Rect(i2 - i4, i3 - i4, i2 + i4, i4 + i3));
        }
        b2.d();
    }

    public void d() {
        if (f()) {
            this.j.e();
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.j = null;
        if (this.k != null) {
            this.k.onDismiss();
        }
    }

    public boolean f() {
        return this.j != null && this.j.f();
    }

    public void a(a aVar) {
        this.i = aVar;
        if (this.j != null) {
            this.j.a(aVar);
        }
    }
}
