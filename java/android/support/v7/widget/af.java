package android.support.v7.widget;

import android.graphics.Rect;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.i;
import android.view.View;

/* compiled from: OrientationHelper */
public abstract class af {
    protected final i a;
    final Rect b;
    private int c;

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int e(View view);

    public abstract int f();

    public abstract int f(View view);

    public abstract int g();

    public abstract int h();

    public abstract int i();

    private af(i iVar) {
        this.c = ExploreByTouchHelper.INVALID_ID;
        this.b = new Rect();
        this.a = iVar;
    }

    public void a() {
        this.c = f();
    }

    public int b() {
        if (Integer.MIN_VALUE == this.c) {
            return 0;
        }
        return f() - this.c;
    }

    public static af a(i iVar, int i) {
        switch (i) {
            case 0:
                return a(iVar);
            case 1:
                return b(iVar);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }

    public static af a(i iVar) {
        return new af(iVar) {
            public int d() {
                return this.a.B() - this.a.F();
            }

            public int e() {
                return this.a.B();
            }

            public void a(int i) {
                this.a.j(i);
            }

            public int c() {
                return this.a.D();
            }

            public int e(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + this.a.f(view) + layoutParams.leftMargin;
            }

            public int f(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + this.a.g(view) + layoutParams.topMargin;
            }

            public int b(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + this.a.j(view);
            }

            public int a(View view) {
                return this.a.h(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
            }

            public int c(View view) {
                this.a.a(view, true, this.b);
                return this.b.right;
            }

            public int d(View view) {
                this.a.a(view, true, this.b);
                return this.b.left;
            }

            public int f() {
                return (this.a.B() - this.a.D()) - this.a.F();
            }

            public int g() {
                return this.a.F();
            }

            public int h() {
                return this.a.z();
            }

            public int i() {
                return this.a.A();
            }
        };
    }

    public static af b(i iVar) {
        return new af(iVar) {
            public int d() {
                return this.a.C() - this.a.G();
            }

            public int e() {
                return this.a.C();
            }

            public void a(int i) {
                this.a.k(i);
            }

            public int c() {
                return this.a.E();
            }

            public int e(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + this.a.g(view) + layoutParams.topMargin;
            }

            public int f(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + this.a.f(view) + layoutParams.leftMargin;
            }

            public int b(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + this.a.k(view);
            }

            public int a(View view) {
                return this.a.i(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
            }

            public int c(View view) {
                this.a.a(view, true, this.b);
                return this.b.bottom;
            }

            public int d(View view) {
                this.a.a(view, true, this.b);
                return this.b.top;
            }

            public int f() {
                return (this.a.C() - this.a.E()) - this.a.G();
            }

            public int g() {
                return this.a.G();
            }

            public int h() {
                return this.a.A();
            }

            public int i() {
                return this.a.z();
            }
        };
    }
}
