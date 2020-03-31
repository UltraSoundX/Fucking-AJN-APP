package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.RecyclerView.s;
import android.support.v7.widget.RecyclerView.v;
import android.support.v7.widget.a.a.d;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class LinearLayoutManager extends i implements android.support.v7.widget.RecyclerView.r.b, d {
    private c a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private final b g;
    private int h;
    int i;
    af j;
    boolean k;
    int l;
    int m;
    SavedState n;
    final a o;

    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        int b;
        boolean c;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            boolean z = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.c = z;
        }

        public SavedState(SavedState savedState) {
            this.a = savedState.a;
            this.b = savedState.b;
            this.c = savedState.c;
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            return this.a >= 0;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.a = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }
    }

    static class a {
        af a;
        int b;
        int c;
        boolean d;
        boolean e;

        a() {
            a();
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.b = -1;
            this.c = ExploreByTouchHelper.INVALID_ID;
            this.d = false;
            this.e = false;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            int c2;
            if (this.d) {
                c2 = this.a.d();
            } else {
                c2 = this.a.c();
            }
            this.c = c2;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
        }

        /* access modifiers changed from: 0000 */
        public boolean a(View view, s sVar) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return !layoutParams.d() && layoutParams.f() >= 0 && layoutParams.f() < sVar.e();
        }

        public void a(View view, int i) {
            int b2 = this.a.b();
            if (b2 >= 0) {
                b(view, i);
                return;
            }
            this.b = i;
            if (this.d) {
                int d2 = (this.a.d() - b2) - this.a.b(view);
                this.c = this.a.d() - d2;
                if (d2 > 0) {
                    int e2 = this.c - this.a.e(view);
                    int c2 = this.a.c();
                    int min = e2 - (c2 + Math.min(this.a.a(view) - c2, 0));
                    if (min < 0) {
                        this.c = Math.min(d2, -min) + this.c;
                        return;
                    }
                    return;
                }
                return;
            }
            int a2 = this.a.a(view);
            int c3 = a2 - this.a.c();
            this.c = a2;
            if (c3 > 0) {
                int d3 = (this.a.d() - Math.min(0, (this.a.d() - b2) - this.a.b(view))) - (a2 + this.a.e(view));
                if (d3 < 0) {
                    this.c -= Math.min(c3, -d3);
                }
            }
        }

        public void b(View view, int i) {
            if (this.d) {
                this.c = this.a.b(view) + this.a.b();
            } else {
                this.c = this.a.a(view);
            }
            this.b = i;
        }
    }

    protected static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        protected b() {
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a = 0;
            this.b = false;
            this.c = false;
            this.d = false;
        }
    }

    static class c {
        boolean a = true;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h = 0;
        boolean i = false;
        int j;
        List<v> k = null;
        boolean l;

        c() {
        }

        /* access modifiers changed from: 0000 */
        public boolean a(s sVar) {
            return this.d >= 0 && this.d < sVar.e();
        }

        /* access modifiers changed from: 0000 */
        public View a(o oVar) {
            if (this.k != null) {
                return b();
            }
            View c2 = oVar.c(this.d);
            this.d += this.e;
            return c2;
        }

        private View b() {
            int size = this.k.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = ((v) this.k.get(i2)).itemView;
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (!layoutParams.d() && this.d == layoutParams.f()) {
                    a(view);
                    return view;
                }
            }
            return null;
        }

        public void a() {
            a((View) null);
        }

        public void a(View view) {
            View b2 = b(view);
            if (b2 == null) {
                this.d = -1;
            } else {
                this.d = ((LayoutParams) b2.getLayoutParams()).f();
            }
        }

        public View b(View view) {
            int i2;
            View view2;
            int size = this.k.size();
            View view3 = null;
            int i3 = Integer.MAX_VALUE;
            int i4 = 0;
            while (i4 < size) {
                View view4 = ((v) this.k.get(i4)).itemView;
                LayoutParams layoutParams = (LayoutParams) view4.getLayoutParams();
                if (view4 != view) {
                    if (layoutParams.d()) {
                        i2 = i3;
                        view2 = view3;
                    } else {
                        i2 = (layoutParams.f() - this.d) * this.e;
                        if (i2 < 0) {
                            i2 = i3;
                            view2 = view3;
                        } else if (i2 < i3) {
                            if (i2 == 0) {
                                return view4;
                            }
                            view2 = view4;
                        }
                    }
                    i4++;
                    view3 = view2;
                    i3 = i2;
                }
                i2 = i3;
                view2 = view3;
                i4++;
                view3 = view2;
                i3 = i2;
            }
            return view3;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i2, boolean z) {
        this.i = 1;
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = ExploreByTouchHelper.INVALID_ID;
        this.n = null;
        this.o = new a();
        this.g = new b();
        this.h = 2;
        b(i2);
        b(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.i = 1;
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = ExploreByTouchHelper.INVALID_ID;
        this.n = null;
        this.o = new a();
        this.g = new b();
        this.h = 2;
        android.support.v7.widget.RecyclerView.i.b a2 = a(context, attributeSet, i2, i3);
        b(a2.a);
        b(a2.c);
        a(a2.d);
    }

    public boolean d() {
        return true;
    }

    public LayoutParams a() {
        return new LayoutParams(-2, -2);
    }

    public void a(RecyclerView recyclerView, o oVar) {
        super.a(recyclerView, oVar);
        if (this.f) {
            c(oVar);
            oVar.a();
        }
    }

    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (y() > 0) {
            accessibilityEvent.setFromIndex(n());
            accessibilityEvent.setToIndex(p());
        }
    }

    public Parcelable e() {
        if (this.n != null) {
            return new SavedState(this.n);
        }
        SavedState savedState = new SavedState();
        if (y() > 0) {
            j();
            boolean z = this.b ^ this.k;
            savedState.c = z;
            if (z) {
                View O = O();
                savedState.b = this.j.d() - this.j.b(O);
                savedState.a = d(O);
                return savedState;
            }
            View N = N();
            savedState.a = d(N);
            savedState.b = this.j.a(N) - this.j.c();
            return savedState;
        }
        savedState.b();
        return savedState;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.n = (SavedState) parcelable;
            r();
        }
    }

    public boolean f() {
        return this.i == 0;
    }

    public boolean g() {
        return this.i == 1;
    }

    public void a(boolean z) {
        a((String) null);
        if (this.d != z) {
            this.d = z;
            r();
        }
    }

    public int h() {
        return this.i;
    }

    public void b(int i2) {
        if (i2 == 0 || i2 == 1) {
            a((String) null);
            if (i2 != this.i || this.j == null) {
                this.j = af.a(this, i2);
                this.o.a = this.j;
                this.i = i2;
                r();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i2);
    }

    private void b() {
        boolean z = true;
        if (this.i == 1 || !i()) {
            this.k = this.c;
            return;
        }
        if (this.c) {
            z = false;
        }
        this.k = z;
    }

    public void b(boolean z) {
        a((String) null);
        if (z != this.c) {
            this.c = z;
            r();
        }
    }

    public View c(int i2) {
        int y = y();
        if (y == 0) {
            return null;
        }
        int d2 = i2 - d(i(0));
        if (d2 >= 0 && d2 < y) {
            View i3 = i(d2);
            if (d(i3) == i2) {
                return i3;
            }
        }
        return super.c(i2);
    }

    /* access modifiers changed from: protected */
    public int b(s sVar) {
        if (sVar.d()) {
            return this.j.f();
        }
        return 0;
    }

    public PointF d(int i2) {
        int i3 = 1;
        boolean z = false;
        if (y() == 0) {
            return null;
        }
        if (i2 < d(i(0))) {
            z = true;
        }
        if (z != this.k) {
            i3 = -1;
        }
        if (this.i == 0) {
            return new PointF((float) i3, 0.0f);
        }
        return new PointF(0.0f, (float) i3);
    }

    public void c(o oVar, s sVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int a2;
        int i6 = -1;
        if (!(this.n == null && this.l == -1) && sVar.e() == 0) {
            c(oVar);
            return;
        }
        if (this.n != null && this.n.a()) {
            this.l = this.n.a;
        }
        j();
        this.a.a = false;
        b();
        View H = H();
        if (!this.o.e || this.l != -1 || this.n != null) {
            this.o.a();
            this.o.d = this.k ^ this.d;
            a(oVar, sVar, this.o);
            this.o.e = true;
        } else if (H != null && (this.j.a(H) >= this.j.d() || this.j.b(H) <= this.j.c())) {
            this.o.a(H, d(H));
        }
        int b2 = b(sVar);
        if (this.a.j >= 0) {
            i2 = 0;
        } else {
            i2 = b2;
            b2 = 0;
        }
        int c2 = i2 + this.j.c();
        int g2 = b2 + this.j.g();
        if (!(!sVar.a() || this.l == -1 || this.m == Integer.MIN_VALUE)) {
            View c3 = c(this.l);
            if (c3 != null) {
                if (this.k) {
                    a2 = (this.j.d() - this.j.b(c3)) - this.m;
                } else {
                    a2 = this.m - (this.j.a(c3) - this.j.c());
                }
                if (a2 > 0) {
                    c2 += a2;
                } else {
                    g2 -= a2;
                }
            }
        }
        if (this.o.d) {
            if (this.k) {
                i6 = 1;
            }
        } else if (!this.k) {
            i6 = 1;
        }
        a(oVar, sVar, this.o, i6);
        a(oVar);
        this.a.l = l();
        this.a.i = sVar.a();
        if (this.o.d) {
            b(this.o);
            this.a.h = c2;
            a(oVar, this.a, sVar, false);
            int i7 = this.a.b;
            int i8 = this.a.d;
            if (this.a.c > 0) {
                g2 += this.a.c;
            }
            a(this.o);
            this.a.h = g2;
            this.a.d += this.a.e;
            a(oVar, this.a, sVar, false);
            int i9 = this.a.b;
            if (this.a.c > 0) {
                int i10 = this.a.c;
                h(i8, i7);
                this.a.h = i10;
                a(oVar, this.a, sVar, false);
                i5 = this.a.b;
            } else {
                i5 = i7;
            }
            i4 = i5;
            i3 = i9;
        } else {
            a(this.o);
            this.a.h = g2;
            a(oVar, this.a, sVar, false);
            i3 = this.a.b;
            int i11 = this.a.d;
            if (this.a.c > 0) {
                c2 += this.a.c;
            }
            b(this.o);
            this.a.h = c2;
            this.a.d += this.a.e;
            a(oVar, this.a, sVar, false);
            i4 = this.a.b;
            if (this.a.c > 0) {
                int i12 = this.a.c;
                a(i11, i3);
                this.a.h = i12;
                a(oVar, this.a, sVar, false);
                i3 = this.a.b;
            }
        }
        if (y() > 0) {
            if (this.k ^ this.d) {
                int a3 = a(i3, oVar, sVar, true);
                int i13 = i4 + a3;
                int i14 = i3 + a3;
                int b3 = b(i13, oVar, sVar, false);
                i4 = i13 + b3;
                i3 = i14 + b3;
            } else {
                int b4 = b(i4, oVar, sVar, true);
                int i15 = i4 + b4;
                int i16 = i3 + b4;
                int a4 = a(i16, oVar, sVar, false);
                i4 = i15 + a4;
                i3 = i16 + a4;
            }
        }
        b(oVar, sVar, i4, i3);
        if (!sVar.a()) {
            this.j.a();
        } else {
            this.o.a();
        }
        this.b = this.d;
    }

    public void a(s sVar) {
        super.a(sVar);
        this.n = null;
        this.l = -1;
        this.m = ExploreByTouchHelper.INVALID_ID;
        this.o.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(o oVar, s sVar, a aVar, int i2) {
    }

    private void b(o oVar, s sVar, int i2, int i3) {
        int e2;
        int i4;
        if (sVar.b() && y() != 0 && !sVar.a() && c()) {
            int i5 = 0;
            int i6 = 0;
            List<v> c2 = oVar.c();
            int size = c2.size();
            int d2 = d(i(0));
            int i7 = 0;
            while (i7 < size) {
                v vVar = (v) c2.get(i7);
                if (vVar.isRemoved()) {
                    e2 = i6;
                    i4 = i5;
                } else {
                    if (((vVar.getLayoutPosition() < d2) != this.k ? (char) 65535 : 1) == 65535) {
                        i4 = this.j.e(vVar.itemView) + i5;
                        e2 = i6;
                    } else {
                        e2 = this.j.e(vVar.itemView) + i6;
                        i4 = i5;
                    }
                }
                i7++;
                i5 = i4;
                i6 = e2;
            }
            this.a.k = c2;
            if (i5 > 0) {
                h(d(N()), i2);
                this.a.h = i5;
                this.a.c = 0;
                this.a.a();
                a(oVar, this.a, sVar, false);
            }
            if (i6 > 0) {
                a(d(O()), i3);
                this.a.h = i6;
                this.a.c = 0;
                this.a.a();
                a(oVar, this.a, sVar, false);
            }
            this.a.k = null;
        }
    }

    private void a(o oVar, s sVar, a aVar) {
        if (!a(sVar, aVar) && !b(oVar, sVar, aVar)) {
            aVar.b();
            aVar.b = this.d ? sVar.e() - 1 : 0;
        }
    }

    private boolean b(o oVar, s sVar, a aVar) {
        View g2;
        int c2;
        boolean z = false;
        if (y() == 0) {
            return false;
        }
        View H = H();
        if (H != null && aVar.a(H, sVar)) {
            aVar.a(H, d(H));
            return true;
        } else if (this.b != this.d) {
            return false;
        } else {
            if (aVar.d) {
                g2 = f(oVar, sVar);
            } else {
                g2 = g(oVar, sVar);
            }
            if (g2 == null) {
                return false;
            }
            aVar.b(g2, d(g2));
            if (!sVar.a() && c()) {
                if (this.j.a(g2) >= this.j.d() || this.j.b(g2) < this.j.c()) {
                    z = true;
                }
                if (z) {
                    if (aVar.d) {
                        c2 = this.j.d();
                    } else {
                        c2 = this.j.c();
                    }
                    aVar.c = c2;
                }
            }
            return true;
        }
    }

    private boolean a(s sVar, a aVar) {
        boolean z;
        int a2;
        boolean z2 = false;
        if (sVar.a() || this.l == -1) {
            return false;
        }
        if (this.l < 0 || this.l >= sVar.e()) {
            this.l = -1;
            this.m = ExploreByTouchHelper.INVALID_ID;
            return false;
        }
        aVar.b = this.l;
        if (this.n != null && this.n.a()) {
            aVar.d = this.n.c;
            if (aVar.d) {
                aVar.c = this.j.d() - this.n.b;
                return true;
            }
            aVar.c = this.j.c() + this.n.b;
            return true;
        } else if (this.m == Integer.MIN_VALUE) {
            View c2 = c(this.l);
            if (c2 == null) {
                if (y() > 0) {
                    if (this.l < d(i(0))) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z == this.k) {
                        z2 = true;
                    }
                    aVar.d = z2;
                }
                aVar.b();
                return true;
            } else if (this.j.e(c2) > this.j.f()) {
                aVar.b();
                return true;
            } else if (this.j.a(c2) - this.j.c() < 0) {
                aVar.c = this.j.c();
                aVar.d = false;
                return true;
            } else if (this.j.d() - this.j.b(c2) < 0) {
                aVar.c = this.j.d();
                aVar.d = true;
                return true;
            } else {
                if (aVar.d) {
                    a2 = this.j.b(c2) + this.j.b();
                } else {
                    a2 = this.j.a(c2);
                }
                aVar.c = a2;
                return true;
            }
        } else {
            aVar.d = this.k;
            if (this.k) {
                aVar.c = this.j.d() - this.m;
                return true;
            }
            aVar.c = this.j.c() + this.m;
            return true;
        }
    }

    private int a(int i2, o oVar, s sVar, boolean z) {
        int d2 = this.j.d() - i2;
        if (d2 <= 0) {
            return 0;
        }
        int i3 = -c(-d2, oVar, sVar);
        int i4 = i2 + i3;
        if (!z) {
            return i3;
        }
        int d3 = this.j.d() - i4;
        if (d3 <= 0) {
            return i3;
        }
        this.j.a(d3);
        return i3 + d3;
    }

    private int b(int i2, o oVar, s sVar, boolean z) {
        int c2 = i2 - this.j.c();
        if (c2 <= 0) {
            return 0;
        }
        int i3 = -c(c2, oVar, sVar);
        int i4 = i2 + i3;
        if (!z) {
            return i3;
        }
        int c3 = i4 - this.j.c();
        if (c3 <= 0) {
            return i3;
        }
        this.j.a(-c3);
        return i3 - c3;
    }

    private void a(a aVar) {
        a(aVar.b, aVar.c);
    }

    private void a(int i2, int i3) {
        this.a.c = this.j.d() - i3;
        this.a.e = this.k ? -1 : 1;
        this.a.d = i2;
        this.a.f = 1;
        this.a.b = i3;
        this.a.g = ExploreByTouchHelper.INVALID_ID;
    }

    private void b(a aVar) {
        h(aVar.b, aVar.c);
    }

    private void h(int i2, int i3) {
        this.a.c = i3 - this.j.c();
        this.a.d = i2;
        this.a.e = this.k ? 1 : -1;
        this.a.f = -1;
        this.a.b = i3;
        this.a.g = ExploreByTouchHelper.INVALID_ID;
    }

    /* access modifiers changed from: protected */
    public boolean i() {
        return w() == 1;
    }

    /* access modifiers changed from: 0000 */
    public void j() {
        if (this.a == null) {
            this.a = k();
        }
    }

    /* access modifiers changed from: 0000 */
    public c k() {
        return new c();
    }

    public void e(int i2) {
        this.l = i2;
        this.m = ExploreByTouchHelper.INVALID_ID;
        if (this.n != null) {
            this.n.b();
        }
        r();
    }

    public void b(int i2, int i3) {
        this.l = i2;
        this.m = i3;
        if (this.n != null) {
            this.n.b();
        }
        r();
    }

    public int a(int i2, o oVar, s sVar) {
        if (this.i == 1) {
            return 0;
        }
        return c(i2, oVar, sVar);
    }

    public int b(int i2, o oVar, s sVar) {
        if (this.i == 0) {
            return 0;
        }
        return c(i2, oVar, sVar);
    }

    public int c(s sVar) {
        return i(sVar);
    }

    public int d(s sVar) {
        return i(sVar);
    }

    public int e(s sVar) {
        return j(sVar);
    }

    public int f(s sVar) {
        return j(sVar);
    }

    public int g(s sVar) {
        return k(sVar);
    }

    public int h(s sVar) {
        return k(sVar);
    }

    private int i(s sVar) {
        boolean z = false;
        if (y() == 0) {
            return 0;
        }
        j();
        af afVar = this.j;
        View a2 = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return ak.a(sVar, afVar, a2, b(z, true), this, this.e, this.k);
    }

    private int j(s sVar) {
        boolean z = false;
        if (y() == 0) {
            return 0;
        }
        j();
        af afVar = this.j;
        View a2 = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return ak.a(sVar, afVar, a2, b(z, true), this, this.e);
    }

    private int k(s sVar) {
        boolean z = false;
        if (y() == 0) {
            return 0;
        }
        j();
        af afVar = this.j;
        View a2 = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return ak.b(sVar, afVar, a2, b(z, true), this, this.e);
    }

    private void a(int i2, int i3, boolean z, s sVar) {
        int c2;
        int i4 = -1;
        int i5 = 1;
        this.a.l = l();
        this.a.h = b(sVar);
        this.a.f = i2;
        if (i2 == 1) {
            this.a.h += this.j.g();
            View O = O();
            c cVar = this.a;
            if (!this.k) {
                i4 = 1;
            }
            cVar.e = i4;
            this.a.d = d(O) + this.a.e;
            this.a.b = this.j.b(O);
            c2 = this.j.b(O) - this.j.d();
        } else {
            View N = N();
            this.a.h += this.j.c();
            c cVar2 = this.a;
            if (!this.k) {
                i5 = -1;
            }
            cVar2.e = i5;
            this.a.d = d(N) + this.a.e;
            this.a.b = this.j.a(N);
            c2 = (-this.j.a(N)) + this.j.c();
        }
        this.a.c = i3;
        if (z) {
            this.a.c -= c2;
        }
        this.a.g = c2;
    }

    /* access modifiers changed from: 0000 */
    public boolean l() {
        return this.j.h() == 0 && this.j.e() == 0;
    }

    /* access modifiers changed from: 0000 */
    public void a(s sVar, c cVar, android.support.v7.widget.RecyclerView.i.a aVar) {
        int i2 = cVar.d;
        if (i2 >= 0 && i2 < sVar.e()) {
            aVar.b(i2, Math.max(0, cVar.g));
        }
    }

    public void a(int i2, android.support.v7.widget.RecyclerView.i.a aVar) {
        int i3;
        boolean z;
        if (this.n == null || !this.n.a()) {
            b();
            boolean z2 = this.k;
            if (this.l == -1) {
                i3 = z2 ? i2 - 1 : 0;
                z = z2;
            } else {
                i3 = this.l;
                z = z2;
            }
        } else {
            z = this.n.c;
            i3 = this.n.a;
        }
        int i4 = z ? -1 : 1;
        for (int i5 = 0; i5 < this.h && i3 >= 0 && i3 < i2; i5++) {
            aVar.b(i3, 0);
            i3 += i4;
        }
    }

    public void a(int i2, int i3, s sVar, android.support.v7.widget.RecyclerView.i.a aVar) {
        if (this.i != 0) {
            i2 = i3;
        }
        if (y() != 0 && i2 != 0) {
            j();
            a(i2 > 0 ? 1 : -1, Math.abs(i2), true, sVar);
            a(sVar, this.a, aVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public int c(int i2, o oVar, s sVar) {
        if (y() == 0 || i2 == 0) {
            return 0;
        }
        this.a.a = true;
        j();
        int i3 = i2 > 0 ? 1 : -1;
        int abs = Math.abs(i2);
        a(i3, abs, true, sVar);
        int a2 = this.a.g + a(oVar, this.a, sVar, false);
        if (a2 < 0) {
            return 0;
        }
        if (abs > a2) {
            i2 = i3 * a2;
        }
        this.j.a(-i2);
        this.a.j = i2;
        return i2;
    }

    public void a(String str) {
        if (this.n == null) {
            super.a(str);
        }
    }

    private void a(o oVar, int i2, int i3) {
        if (i2 != i3) {
            if (i3 > i2) {
                for (int i4 = i3 - 1; i4 >= i2; i4--) {
                    a(i4, oVar);
                }
                return;
            }
            while (i2 > i3) {
                a(i2, oVar);
                i2--;
            }
        }
    }

    private void a(o oVar, int i2) {
        if (i2 >= 0) {
            int y = y();
            if (this.k) {
                for (int i3 = y - 1; i3 >= 0; i3--) {
                    View i4 = i(i3);
                    if (this.j.b(i4) > i2 || this.j.c(i4) > i2) {
                        a(oVar, y - 1, i3);
                        return;
                    }
                }
                return;
            }
            for (int i5 = 0; i5 < y; i5++) {
                View i6 = i(i5);
                if (this.j.b(i6) > i2 || this.j.c(i6) > i2) {
                    a(oVar, 0, i5);
                    return;
                }
            }
        }
    }

    private void b(o oVar, int i2) {
        int y = y();
        if (i2 >= 0) {
            int e2 = this.j.e() - i2;
            if (this.k) {
                for (int i3 = 0; i3 < y; i3++) {
                    View i4 = i(i3);
                    if (this.j.a(i4) < e2 || this.j.d(i4) < e2) {
                        a(oVar, 0, i3);
                        return;
                    }
                }
                return;
            }
            for (int i5 = y - 1; i5 >= 0; i5--) {
                View i6 = i(i5);
                if (this.j.a(i6) < e2 || this.j.d(i6) < e2) {
                    a(oVar, y - 1, i5);
                    return;
                }
            }
        }
    }

    private void a(o oVar, c cVar) {
        if (cVar.a && !cVar.l) {
            if (cVar.f == -1) {
                b(oVar, cVar.g);
            } else {
                a(oVar, cVar.g);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int a(o oVar, c cVar, s sVar, boolean z) {
        int i2 = cVar.c;
        if (cVar.g != Integer.MIN_VALUE) {
            if (cVar.c < 0) {
                cVar.g += cVar.c;
            }
            a(oVar, cVar);
        }
        int i3 = cVar.c + cVar.h;
        b bVar = this.g;
        while (true) {
            if ((!cVar.l && i3 <= 0) || !cVar.a(sVar)) {
                break;
            }
            bVar.a();
            a(oVar, sVar, cVar, bVar);
            if (!bVar.b) {
                cVar.b += bVar.a * cVar.f;
                if (!bVar.c || this.a.k != null || !sVar.a()) {
                    cVar.c -= bVar.a;
                    i3 -= bVar.a;
                }
                if (cVar.g != Integer.MIN_VALUE) {
                    cVar.g += bVar.a;
                    if (cVar.c < 0) {
                        cVar.g += cVar.c;
                    }
                    a(oVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - cVar.c;
    }

    /* access modifiers changed from: 0000 */
    public void a(o oVar, s sVar, c cVar, b bVar) {
        boolean z;
        int E;
        int f2;
        int i2;
        int i3;
        int f3;
        View a2 = cVar.a(oVar);
        if (a2 == null) {
            bVar.b = true;
            return;
        }
        LayoutParams layoutParams = (LayoutParams) a2.getLayoutParams();
        if (cVar.k == null) {
            if (this.k == (cVar.f == -1)) {
                b(a2);
            } else {
                b(a2, 0);
            }
        } else {
            boolean z2 = this.k;
            if (cVar.f == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z2 == z) {
                a(a2);
            } else {
                a(a2, 0);
            }
        }
        a(a2, 0, 0);
        bVar.a = this.j.e(a2);
        if (this.i == 1) {
            if (i()) {
                f3 = B() - F();
                i2 = f3 - this.j.f(a2);
            } else {
                i2 = D();
                f3 = this.j.f(a2) + i2;
            }
            if (cVar.f == -1) {
                f2 = cVar.b;
                E = cVar.b - bVar.a;
                i3 = f3;
            } else {
                E = cVar.b;
                f2 = bVar.a + cVar.b;
                i3 = f3;
            }
        } else {
            E = E();
            f2 = E + this.j.f(a2);
            if (cVar.f == -1) {
                i2 = cVar.b - bVar.a;
                i3 = cVar.b;
            } else {
                i2 = cVar.b;
                i3 = cVar.b + bVar.a;
            }
        }
        a(a2, i2, E, i3, f2);
        if (layoutParams.d() || layoutParams.e()) {
            bVar.c = true;
        }
        bVar.d = a2.hasFocusable();
    }

    /* access modifiers changed from: 0000 */
    public boolean m() {
        return (A() == 1073741824 || z() == 1073741824 || !M()) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public int f(int i2) {
        int i3 = ExploreByTouchHelper.INVALID_ID;
        int i4 = 1;
        switch (i2) {
            case 1:
                if (this.i == 1 || !i()) {
                    return -1;
                }
                return 1;
            case 2:
                if (this.i == 1) {
                    return 1;
                }
                if (!i()) {
                    return 1;
                }
                return -1;
            case 17:
                if (this.i != 0) {
                    return ExploreByTouchHelper.INVALID_ID;
                }
                return -1;
            case 33:
                if (this.i != 1) {
                    return ExploreByTouchHelper.INVALID_ID;
                }
                return -1;
            case 66:
                if (this.i != 0) {
                    i4 = Integer.MIN_VALUE;
                }
                return i4;
            case 130:
                if (this.i == 1) {
                    i3 = 1;
                }
                return i3;
            default:
                return ExploreByTouchHelper.INVALID_ID;
        }
    }

    private View N() {
        return i(this.k ? y() - 1 : 0);
    }

    private View O() {
        return i(this.k ? 0 : y() - 1);
    }

    private View a(boolean z, boolean z2) {
        if (this.k) {
            return a(y() - 1, -1, z, z2);
        }
        return a(0, y(), z, z2);
    }

    private View b(boolean z, boolean z2) {
        if (this.k) {
            return a(0, y(), z, z2);
        }
        return a(y() - 1, -1, z, z2);
    }

    private View f(o oVar, s sVar) {
        if (this.k) {
            return h(oVar, sVar);
        }
        return i(oVar, sVar);
    }

    private View g(o oVar, s sVar) {
        if (this.k) {
            return i(oVar, sVar);
        }
        return h(oVar, sVar);
    }

    private View h(o oVar, s sVar) {
        return a(oVar, sVar, 0, y(), sVar.e());
    }

    private View i(o oVar, s sVar) {
        return a(oVar, sVar, y() - 1, -1, sVar.e());
    }

    /* access modifiers changed from: 0000 */
    public View a(o oVar, s sVar, int i2, int i3, int i4) {
        View view;
        View view2 = null;
        j();
        int c2 = this.j.c();
        int d2 = this.j.d();
        int i5 = i3 > i2 ? 1 : -1;
        View view3 = null;
        while (i2 != i3) {
            View i6 = i(i2);
            int d3 = d(i6);
            if (d3 >= 0 && d3 < i4) {
                if (((LayoutParams) i6.getLayoutParams()).d()) {
                    if (view3 == null) {
                        view = view2;
                        i2 += i5;
                        view2 = view;
                        view3 = i6;
                    }
                } else if (this.j.a(i6) < d2 && this.j.b(i6) >= c2) {
                    return i6;
                } else {
                    if (view2 == null) {
                        view = i6;
                        i6 = view3;
                        i2 += i5;
                        view2 = view;
                        view3 = i6;
                    }
                }
            }
            view = view2;
            i6 = view3;
            i2 += i5;
            view2 = view;
            view3 = i6;
        }
        if (view2 == null) {
            view2 = view3;
        }
        return view2;
    }

    private View j(o oVar, s sVar) {
        if (this.k) {
            return l(oVar, sVar);
        }
        return m(oVar, sVar);
    }

    private View k(o oVar, s sVar) {
        if (this.k) {
            return m(oVar, sVar);
        }
        return l(oVar, sVar);
    }

    private View l(o oVar, s sVar) {
        return c(0, y());
    }

    private View m(o oVar, s sVar) {
        return c(y() - 1, -1);
    }

    public int n() {
        View a2 = a(0, y(), false, true);
        if (a2 == null) {
            return -1;
        }
        return d(a2);
    }

    public int o() {
        View a2 = a(0, y(), true, false);
        if (a2 == null) {
            return -1;
        }
        return d(a2);
    }

    public int p() {
        View a2 = a(y() - 1, -1, false, true);
        if (a2 == null) {
            return -1;
        }
        return d(a2);
    }

    public int q() {
        View a2 = a(y() - 1, -1, true, false);
        if (a2 == null) {
            return -1;
        }
        return d(a2);
    }

    /* access modifiers changed from: 0000 */
    public View a(int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5 = 320;
        j();
        if (z) {
            i4 = 24579;
        } else {
            i4 = 320;
        }
        if (!z2) {
            i5 = 0;
        }
        if (this.i == 0) {
            return this.r.a(i2, i3, i4, i5);
        }
        return this.s.a(i2, i3, i4, i5);
    }

    /* access modifiers changed from: 0000 */
    public View c(int i2, int i3) {
        int i4;
        int i5;
        j();
        char c2 = i3 > i2 ? 1 : i3 < i2 ? (char) 65535 : 0;
        if (c2 == 0) {
            return i(i2);
        }
        if (this.j.a(i(i2)) < this.j.c()) {
            i4 = 16644;
            i5 = 16388;
        } else {
            i4 = 4161;
            i5 = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        if (this.i == 0) {
            return this.r.a(i2, i3, i4, i5);
        }
        return this.s.a(i2, i3, i4, i5);
    }

    public View a(View view, int i2, o oVar, s sVar) {
        View j2;
        View O;
        b();
        if (y() == 0) {
            return null;
        }
        int f2 = f(i2);
        if (f2 == Integer.MIN_VALUE) {
            return null;
        }
        j();
        j();
        a(f2, (int) (0.33333334f * ((float) this.j.f())), false, sVar);
        this.a.g = ExploreByTouchHelper.INVALID_ID;
        this.a.a = false;
        a(oVar, this.a, sVar, true);
        if (f2 == -1) {
            j2 = k(oVar, sVar);
        } else {
            j2 = j(oVar, sVar);
        }
        if (f2 == -1) {
            O = N();
        } else {
            O = O();
        }
        if (!O.hasFocusable()) {
            return j2;
        }
        if (j2 == null) {
            return null;
        }
        return O;
    }

    public boolean c() {
        return this.n == null && this.b == this.d;
    }

    public void a(View view, View view2, int i2, int i3) {
        a("Cannot drop a view during a scroll or layout calculation");
        j();
        b();
        int d2 = d(view);
        int d3 = d(view2);
        boolean z = d2 < d3 ? true : true;
        if (this.k) {
            if (z) {
                b(d3, this.j.d() - (this.j.a(view2) + this.j.e(view)));
            } else {
                b(d3, this.j.d() - this.j.b(view2));
            }
        } else if (z) {
            b(d3, this.j.a(view2));
        } else {
            b(d3, this.j.b(view2) - this.j.e(view));
        }
    }
}
