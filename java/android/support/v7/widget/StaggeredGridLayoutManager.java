package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.RecyclerView.s;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends i implements android.support.v7.widget.RecyclerView.r.b {
    private SavedState A;
    private int B;
    private final Rect C = new Rect();
    private final a D = new a();
    private boolean E = false;
    private boolean F = true;
    private int[] G;
    private final Runnable H = new Runnable() {
        public void run() {
            StaggeredGridLayoutManager.this.b();
        }
    };
    b[] a;
    af b;
    af c;
    boolean d = false;
    boolean e = false;
    int f = -1;
    int g = ExploreByTouchHelper.INVALID_ID;
    LazySpanLookup h = new LazySpanLookup();
    private int i = -1;
    private int j;
    private int k;
    private final ab l;
    private BitSet m;
    private int n = 2;
    private boolean o;
    private boolean z;

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        b a;
        boolean b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public void a(boolean z) {
            this.b = z;
        }

        public boolean a() {
            return this.b;
        }

        public final int b() {
            if (this.a == null) {
                return -1;
            }
            return this.a.e;
        }
    }

    static class LazySpanLookup {
        int[] a;
        List<FullSpanItem> b;

        static class FullSpanItem implements Parcelable {
            public static final Creator<FullSpanItem> CREATOR = new Creator<FullSpanItem>() {
                /* renamed from: a */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                /* renamed from: a */
                public FullSpanItem[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            };
            int a;
            int b;
            int[] c;
            boolean d;

            FullSpanItem(Parcel parcel) {
                boolean z = true;
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                this.d = z;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.c = new int[readInt];
                    parcel.readIntArray(this.c);
                }
            }

            FullSpanItem() {
            }

            /* access modifiers changed from: 0000 */
            public int a(int i) {
                if (this.c == null) {
                    return 0;
                }
                return this.c[i];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                if (this.c == null || this.c.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(this.c.length);
                parcel.writeIntArray(this.c);
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }
        }

        LazySpanLookup() {
        }

        /* access modifiers changed from: 0000 */
        public int a(int i) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    if (((FullSpanItem) this.b.get(size)).a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return b(i);
        }

        /* access modifiers changed from: 0000 */
        public int b(int i) {
            if (this.a == null || i >= this.a.length) {
                return -1;
            }
            int g = g(i);
            if (g == -1) {
                Arrays.fill(this.a, i, this.a.length, -1);
                return this.a.length;
            }
            Arrays.fill(this.a, i, g + 1, -1);
            return g + 1;
        }

        /* access modifiers changed from: 0000 */
        public int c(int i) {
            if (this.a == null || i >= this.a.length) {
                return -1;
            }
            return this.a[i];
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, b bVar) {
            e(i);
            this.a[i] = bVar.e;
        }

        /* access modifiers changed from: 0000 */
        public int d(int i) {
            int length = this.a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        /* access modifiers changed from: 0000 */
        public void e(int i) {
            if (this.a == null) {
                this.a = new int[(Math.max(i, 10) + 1)];
                Arrays.fill(this.a, -1);
            } else if (i >= this.a.length) {
                int[] iArr = this.a;
                this.a = new int[d(i)];
                System.arraycopy(iArr, 0, this.a, 0, iArr.length);
                Arrays.fill(this.a, iArr.length, this.a.length, -1);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            if (this.a != null) {
                Arrays.fill(this.a, -1);
            }
            this.b = null;
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                e(i + i2);
                System.arraycopy(this.a, i + i2, this.a, i, (this.a.length - i) - i2);
                Arrays.fill(this.a, this.a.length - i2, this.a.length, -1);
                c(i, i2);
            }
        }

        private void c(int i, int i2) {
            if (this.b != null) {
                int i3 = i + i2;
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                    if (fullSpanItem.a >= i) {
                        if (fullSpanItem.a < i3) {
                            this.b.remove(size);
                        } else {
                            fullSpanItem.a -= i2;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                e(i + i2);
                System.arraycopy(this.a, i, this.a, i + i2, (this.a.length - i) - i2);
                Arrays.fill(this.a, i, i + i2, -1);
                d(i, i2);
            }
        }

        private void d(int i, int i2) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                    if (fullSpanItem.a >= i) {
                        fullSpanItem.a += i2;
                    }
                }
            }
        }

        private int g(int i) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem f = f(i);
            if (f != null) {
                this.b.remove(f);
            }
            int size = this.b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (((FullSpanItem) this.b.get(i2)).a >= i) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(i2);
            this.b.remove(i2);
            return fullSpanItem.a;
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = (FullSpanItem) this.b.get(i);
                if (fullSpanItem2.a == fullSpanItem.a) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.a >= fullSpanItem.a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public FullSpanItem f(int i) {
            if (this.b == null) {
                return null;
            }
            for (int size = this.b.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                if (fullSpanItem.a == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem a(int i, int i2, int i3, boolean z) {
            if (this.b == null) {
                return null;
            }
            int size = this.b.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(i4);
                if (fullSpanItem.a >= i2) {
                    return null;
                }
                if (fullSpanItem.a >= i) {
                    if (i3 == 0 || fullSpanItem.b == i3) {
                        return fullSpanItem;
                    }
                    if (z && fullSpanItem.d) {
                        return fullSpanItem;
                    }
                }
            }
            return null;
        }
    }

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
        int c;
        int[] d;
        int e;
        int[] f;
        List<FullSpanItem> g;
        boolean h;
        boolean i;
        boolean j;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            if (this.c > 0) {
                this.d = new int[this.c];
                parcel.readIntArray(this.d);
            }
            this.e = parcel.readInt();
            if (this.e > 0) {
                this.f = new int[this.e];
                parcel.readIntArray(this.f);
            }
            this.h = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.i = z;
            if (parcel.readInt() != 1) {
                z2 = false;
            }
            this.j = z2;
            this.g = parcel.readArrayList(FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.c = savedState.c;
            this.a = savedState.a;
            this.b = savedState.b;
            this.d = savedState.d;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.g = savedState.g;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.d = null;
            this.c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.d = null;
            this.c = 0;
            this.a = -1;
            this.b = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            int i3;
            int i4;
            int i5 = 1;
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            if (this.h) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            parcel.writeInt(i3);
            if (this.i) {
                i4 = 1;
            } else {
                i4 = 0;
            }
            parcel.writeInt(i4);
            if (!this.j) {
                i5 = 0;
            }
            parcel.writeInt(i5);
            parcel.writeList(this.g);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        boolean d;
        boolean e;
        int[] f;

        a() {
            a();
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a = -1;
            this.b = ExploreByTouchHelper.INVALID_ID;
            this.c = false;
            this.d = false;
            this.e = false;
            if (this.f != null) {
                Arrays.fill(this.f, -1);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(b[] bVarArr) {
            int length = bVarArr.length;
            if (this.f == null || this.f.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.a.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = bVarArr[i].a((int) ExploreByTouchHelper.INVALID_ID);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            int c2;
            if (this.c) {
                c2 = StaggeredGridLayoutManager.this.b.d();
            } else {
                c2 = StaggeredGridLayoutManager.this.b.c();
            }
            this.b = c2;
        }

        /* access modifiers changed from: 0000 */
        public void a(int i) {
            if (this.c) {
                this.b = StaggeredGridLayoutManager.this.b.d() - i;
            } else {
                this.b = StaggeredGridLayoutManager.this.b.c() + i;
            }
        }
    }

    class b {
        ArrayList<View> a = new ArrayList<>();
        int b = ExploreByTouchHelper.INVALID_ID;
        int c = ExploreByTouchHelper.INVALID_ID;
        int d = 0;
        final int e;

        b(int i) {
            this.e = i;
        }

        /* access modifiers changed from: 0000 */
        public int a(int i) {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            if (this.a.size() == 0) {
                return i;
            }
            a();
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            View view = (View) this.a.get(0);
            LayoutParams c2 = c(view);
            this.b = StaggeredGridLayoutManager.this.b.a(view);
            if (c2.b) {
                FullSpanItem f2 = StaggeredGridLayoutManager.this.h.f(c2.f());
                if (f2 != null && f2.b == -1) {
                    this.b -= f2.a(this.e);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public int b() {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            a();
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i) {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            if (this.a.size() == 0) {
                return i;
            }
            c();
            return this.c;
        }

        /* access modifiers changed from: 0000 */
        public void c() {
            View view = (View) this.a.get(this.a.size() - 1);
            LayoutParams c2 = c(view);
            this.c = StaggeredGridLayoutManager.this.b.b(view);
            if (c2.b) {
                FullSpanItem f2 = StaggeredGridLayoutManager.this.h.f(c2.f());
                if (f2 != null && f2.b == 1) {
                    this.c = f2.a(this.e) + this.c;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public int d() {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            c();
            return this.c;
        }

        /* access modifiers changed from: 0000 */
        public void a(View view) {
            LayoutParams c2 = c(view);
            c2.a = this;
            this.a.add(0, view);
            this.b = ExploreByTouchHelper.INVALID_ID;
            if (this.a.size() == 1) {
                this.c = ExploreByTouchHelper.INVALID_ID;
            }
            if (c2.d() || c2.e()) {
                this.d += StaggeredGridLayoutManager.this.b.e(view);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(View view) {
            LayoutParams c2 = c(view);
            c2.a = this;
            this.a.add(view);
            this.c = ExploreByTouchHelper.INVALID_ID;
            if (this.a.size() == 1) {
                this.b = ExploreByTouchHelper.INVALID_ID;
            }
            if (c2.d() || c2.e()) {
                this.d += StaggeredGridLayoutManager.this.b.e(view);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(boolean z, int i) {
            int a2;
            if (z) {
                a2 = b((int) ExploreByTouchHelper.INVALID_ID);
            } else {
                a2 = a((int) ExploreByTouchHelper.INVALID_ID);
            }
            e();
            if (a2 != Integer.MIN_VALUE) {
                if (z && a2 < StaggeredGridLayoutManager.this.b.d()) {
                    return;
                }
                if (z || a2 <= StaggeredGridLayoutManager.this.b.c()) {
                    if (i != Integer.MIN_VALUE) {
                        a2 += i;
                    }
                    this.c = a2;
                    this.b = a2;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void e() {
            this.a.clear();
            f();
            this.d = 0;
        }

        /* access modifiers changed from: 0000 */
        public void f() {
            this.b = ExploreByTouchHelper.INVALID_ID;
            this.c = ExploreByTouchHelper.INVALID_ID;
        }

        /* access modifiers changed from: 0000 */
        public void c(int i) {
            this.b = i;
            this.c = i;
        }

        /* access modifiers changed from: 0000 */
        public void g() {
            int size = this.a.size();
            View view = (View) this.a.remove(size - 1);
            LayoutParams c2 = c(view);
            c2.a = null;
            if (c2.d() || c2.e()) {
                this.d -= StaggeredGridLayoutManager.this.b.e(view);
            }
            if (size == 1) {
                this.b = ExploreByTouchHelper.INVALID_ID;
            }
            this.c = ExploreByTouchHelper.INVALID_ID;
        }

        /* access modifiers changed from: 0000 */
        public void h() {
            View view = (View) this.a.remove(0);
            LayoutParams c2 = c(view);
            c2.a = null;
            if (this.a.size() == 0) {
                this.c = ExploreByTouchHelper.INVALID_ID;
            }
            if (c2.d() || c2.e()) {
                this.d -= StaggeredGridLayoutManager.this.b.e(view);
            }
            this.b = ExploreByTouchHelper.INVALID_ID;
        }

        public int i() {
            return this.d;
        }

        /* access modifiers changed from: 0000 */
        public LayoutParams c(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        /* access modifiers changed from: 0000 */
        public void d(int i) {
            if (this.b != Integer.MIN_VALUE) {
                this.b += i;
            }
            if (this.c != Integer.MIN_VALUE) {
                this.c += i;
            }
        }

        public int j() {
            if (StaggeredGridLayoutManager.this.d) {
                return b(this.a.size() - 1, -1, true);
            }
            return b(0, this.a.size(), true);
        }

        public int k() {
            if (StaggeredGridLayoutManager.this.d) {
                return b(0, this.a.size(), true);
            }
            return b(this.a.size() - 1, -1, true);
        }

        public int l() {
            if (StaggeredGridLayoutManager.this.d) {
                return a(0, this.a.size(), true);
            }
            return a(this.a.size() - 1, -1, true);
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, int i2, boolean z, boolean z2, boolean z3) {
            int c2 = StaggeredGridLayoutManager.this.b.c();
            int d2 = StaggeredGridLayoutManager.this.b.d();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = (View) this.a.get(i);
                int a2 = StaggeredGridLayoutManager.this.b.a(view);
                int b2 = StaggeredGridLayoutManager.this.b.b(view);
                boolean z4 = z3 ? a2 <= d2 : a2 < d2;
                boolean z5 = z3 ? b2 >= c2 : b2 > c2;
                if (z4 && z5) {
                    if (!z || !z2) {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.d(view);
                        }
                        if (a2 < c2 || b2 > d2) {
                            return StaggeredGridLayoutManager.this.d(view);
                        }
                    } else if (a2 >= c2 && b2 <= d2) {
                        return StaggeredGridLayoutManager.this.d(view);
                    }
                }
                i += i3;
            }
            return -1;
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, int i2, boolean z) {
            return a(i, i2, z, true, false);
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, int i2, boolean z) {
            return a(i, i2, false, false, z);
        }

        public View a(int i, int i2) {
            View view = null;
            if (i2 == -1) {
                int size = this.a.size();
                int i3 = 0;
                while (i3 < size) {
                    View view2 = (View) this.a.get(i3);
                    if ((StaggeredGridLayoutManager.this.d && StaggeredGridLayoutManager.this.d(view2) <= i) || ((!StaggeredGridLayoutManager.this.d && StaggeredGridLayoutManager.this.d(view2) >= i) || !view2.hasFocusable())) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
                return view;
            }
            int size2 = this.a.size() - 1;
            while (size2 >= 0) {
                View view3 = (View) this.a.get(size2);
                if (StaggeredGridLayoutManager.this.d && StaggeredGridLayoutManager.this.d(view3) >= i) {
                    break;
                } else if (StaggeredGridLayoutManager.this.d || StaggeredGridLayoutManager.this.d(view3) > i) {
                    if (!view3.hasFocusable()) {
                        break;
                    }
                    size2--;
                    view = view3;
                } else {
                    return view;
                }
            }
            return view;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        android.support.v7.widget.RecyclerView.i.b a2 = a(context, attributeSet, i2, i3);
        b(a2.a);
        a(a2.b);
        a(a2.c);
        this.l = new ab();
        N();
    }

    public boolean d() {
        return this.n != 0;
    }

    private void N() {
        this.b = af.a(this, this.j);
        this.c = af.a(this, 1 - this.j);
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        int q2;
        int p;
        if (y() == 0 || this.n == 0 || !t()) {
            return false;
        }
        if (this.e) {
            q2 = p();
            p = q();
        } else {
            q2 = q();
            p = p();
        }
        if (q2 == 0 && h() != null) {
            this.h.a();
            L();
            r();
            return true;
        } else if (!this.E) {
            return false;
        } else {
            int i2 = this.e ? -1 : 1;
            FullSpanItem a2 = this.h.a(q2, p + 1, i2, true);
            if (a2 == null) {
                this.E = false;
                this.h.a(p + 1);
                return false;
            }
            FullSpanItem a3 = this.h.a(q2, a2.a, i2 * -1, true);
            if (a3 == null) {
                this.h.a(a2.a);
            } else {
                this.h.a(a3.a + 1);
            }
            L();
            r();
            return true;
        }
    }

    public void l(int i2) {
        if (i2 == 0) {
            b();
        }
    }

    public void a(RecyclerView recyclerView, o oVar) {
        super.a(recyclerView, oVar);
        a(this.H);
        for (int i2 = 0; i2 < this.i; i2++) {
            this.a[i2].e();
        }
        recyclerView.requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public View h() {
        int i2;
        int i3;
        boolean z2;
        boolean z3;
        int y = y() - 1;
        BitSet bitSet = new BitSet(this.i);
        bitSet.set(0, this.i, true);
        char c2 = (this.j != 1 || !k()) ? (char) 65535 : 1;
        if (this.e) {
            i2 = -1;
        } else {
            i2 = y + 1;
            y = 0;
        }
        if (y < i2) {
            i3 = 1;
        } else {
            i3 = -1;
        }
        for (int i4 = y; i4 != i2; i4 += i3) {
            View i5 = i(i4);
            LayoutParams layoutParams = (LayoutParams) i5.getLayoutParams();
            if (bitSet.get(layoutParams.a.e)) {
                if (a(layoutParams.a)) {
                    return i5;
                }
                bitSet.clear(layoutParams.a.e);
            }
            if (!layoutParams.b && i4 + i3 != i2) {
                View i6 = i(i4 + i3);
                if (this.e) {
                    int b2 = this.b.b(i5);
                    int b3 = this.b.b(i6);
                    if (b2 < b3) {
                        return i5;
                    }
                    if (b2 == b3) {
                        z2 = true;
                    }
                    z2 = false;
                } else {
                    int a2 = this.b.a(i5);
                    int a3 = this.b.a(i6);
                    if (a2 > a3) {
                        return i5;
                    }
                    if (a2 == a3) {
                        z2 = true;
                    }
                    z2 = false;
                }
                if (z2) {
                    if (layoutParams.a.e - ((LayoutParams) i6.getLayoutParams()).a.e < 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3 != (c2 < 0)) {
                        return i5;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private boolean a(b bVar) {
        boolean z2 = true;
        if (this.e) {
            if (bVar.d() < this.b.d()) {
                return !bVar.c((View) bVar.a.get(bVar.a.size() + -1)).b;
            }
        } else if (bVar.b() > this.b.c()) {
            if (bVar.c((View) bVar.a.get(0)).b) {
                z2 = false;
            }
            return z2;
        }
        return false;
    }

    public void a(int i2) {
        a((String) null);
        if (i2 != this.i) {
            j();
            this.i = i2;
            this.m = new BitSet(this.i);
            this.a = new b[this.i];
            for (int i3 = 0; i3 < this.i; i3++) {
                this.a[i3] = new b(i3);
            }
            r();
        }
    }

    public void b(int i2) {
        if (i2 == 0 || i2 == 1) {
            a((String) null);
            if (i2 != this.j) {
                this.j = i2;
                af afVar = this.b;
                this.b = this.c;
                this.c = afVar;
                r();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public void a(boolean z2) {
        a((String) null);
        if (!(this.A == null || this.A.h == z2)) {
            this.A.h = z2;
        }
        this.d = z2;
        r();
    }

    public void a(String str) {
        if (this.A == null) {
            super.a(str);
        }
    }

    public int i() {
        return this.i;
    }

    public void j() {
        this.h.a();
        r();
    }

    private void O() {
        boolean z2 = true;
        if (this.j == 1 || !k()) {
            this.e = this.d;
            return;
        }
        if (this.d) {
            z2 = false;
        }
        this.e = z2;
    }

    /* access modifiers changed from: 0000 */
    public boolean k() {
        return w() == 1;
    }

    public void a(Rect rect, int i2, int i3) {
        int a2;
        int a3;
        int F2 = F() + D();
        int E2 = E() + G();
        if (this.j == 1) {
            a3 = a(i3, E2 + rect.height(), J());
            a2 = a(i2, F2 + (this.k * this.i), I());
        } else {
            a2 = a(i2, F2 + rect.width(), I());
            a3 = a(i3, E2 + (this.k * this.i), J());
        }
        g(a2, a3);
    }

    public void c(o oVar, s sVar) {
        a(oVar, sVar, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.support.v7.widget.RecyclerView.o r9, android.support.v7.widget.RecyclerView.s r10, boolean r11) {
        /*
            r8 = this;
            r7 = -1
            r2 = 1
            r1 = 0
            android.support.v7.widget.StaggeredGridLayoutManager$a r3 = r8.D
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r0 = r8.A
            if (r0 != 0) goto L_0x000d
            int r0 = r8.f
            if (r0 == r7) goto L_0x001a
        L_0x000d:
            int r0 = r10.e()
            if (r0 != 0) goto L_0x001a
            r8.c(r9)
            r3.a()
        L_0x0019:
            return
        L_0x001a:
            boolean r0 = r3.e
            if (r0 == 0) goto L_0x0026
            int r0 = r8.f
            if (r0 != r7) goto L_0x0026
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r0 = r8.A
            if (r0 == 0) goto L_0x0087
        L_0x0026:
            r0 = r2
        L_0x0027:
            if (r0 == 0) goto L_0x0038
            r3.a()
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r4 = r8.A
            if (r4 == 0) goto L_0x0089
            r8.a(r3)
        L_0x0033:
            r8.a(r10, r3)
            r3.e = r2
        L_0x0038:
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r4 = r8.A
            if (r4 != 0) goto L_0x0055
            int r4 = r8.f
            if (r4 != r7) goto L_0x0055
            boolean r4 = r3.c
            boolean r5 = r8.o
            if (r4 != r5) goto L_0x004e
            boolean r4 = r8.k()
            boolean r5 = r8.z
            if (r4 == r5) goto L_0x0055
        L_0x004e:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r8.h
            r4.a()
            r3.d = r2
        L_0x0055:
            int r4 = r8.y()
            if (r4 <= 0) goto L_0x00b3
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r4 = r8.A
            if (r4 == 0) goto L_0x0065
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r4 = r8.A
            int r4 = r4.c
            if (r4 >= r2) goto L_0x00b3
        L_0x0065:
            boolean r4 = r3.d
            if (r4 == 0) goto L_0x0091
            r0 = r1
        L_0x006a:
            int r4 = r8.i
            if (r0 >= r4) goto L_0x00b3
            android.support.v7.widget.StaggeredGridLayoutManager$b[] r4 = r8.a
            r4 = r4[r0]
            r4.e()
            int r4 = r3.b
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 == r5) goto L_0x0084
            android.support.v7.widget.StaggeredGridLayoutManager$b[] r4 = r8.a
            r4 = r4[r0]
            int r5 = r3.b
            r4.c(r5)
        L_0x0084:
            int r0 = r0 + 1
            goto L_0x006a
        L_0x0087:
            r0 = r1
            goto L_0x0027
        L_0x0089:
            r8.O()
            boolean r4 = r8.e
            r3.c = r4
            goto L_0x0033
        L_0x0091:
            if (r0 != 0) goto L_0x0099
            android.support.v7.widget.StaggeredGridLayoutManager$a r0 = r8.D
            int[] r0 = r0.f
            if (r0 != 0) goto L_0x0148
        L_0x0099:
            r0 = r1
        L_0x009a:
            int r4 = r8.i
            if (r0 >= r4) goto L_0x00ac
            android.support.v7.widget.StaggeredGridLayoutManager$b[] r4 = r8.a
            r4 = r4[r0]
            boolean r5 = r8.e
            int r6 = r3.b
            r4.a(r5, r6)
            int r0 = r0 + 1
            goto L_0x009a
        L_0x00ac:
            android.support.v7.widget.StaggeredGridLayoutManager$a r0 = r8.D
            android.support.v7.widget.StaggeredGridLayoutManager$b[] r4 = r8.a
            r0.a(r4)
        L_0x00b3:
            r8.a(r9)
            android.support.v7.widget.ab r0 = r8.l
            r0.a = r1
            r8.E = r1
            android.support.v7.widget.af r0 = r8.c
            int r0 = r0.f()
            r8.f(r0)
            int r0 = r3.a
            r8.b(r0, r10)
            boolean r0 = r3.c
            if (r0 == 0) goto L_0x0160
            r8.m(r7)
            android.support.v7.widget.ab r0 = r8.l
            r8.a(r9, r0, r10)
            r8.m(r2)
            android.support.v7.widget.ab r0 = r8.l
            int r4 = r3.a
            android.support.v7.widget.ab r5 = r8.l
            int r5 = r5.d
            int r4 = r4 + r5
            r0.c = r4
            android.support.v7.widget.ab r0 = r8.l
            r8.a(r9, r0, r10)
        L_0x00e9:
            r8.P()
            int r0 = r8.y()
            if (r0 <= 0) goto L_0x00fc
            boolean r0 = r8.e
            if (r0 == 0) goto L_0x017d
            r8.b(r9, r10, r2)
            r8.c(r9, r10, r1)
        L_0x00fc:
            if (r11 == 0) goto L_0x0187
            boolean r0 = r10.a()
            if (r0 != 0) goto L_0x0187
            int r0 = r8.n
            if (r0 == 0) goto L_0x0185
            int r0 = r8.y()
            if (r0 <= 0) goto L_0x0185
            boolean r0 = r8.E
            if (r0 != 0) goto L_0x0118
            android.view.View r0 = r8.h()
            if (r0 == 0) goto L_0x0185
        L_0x0118:
            r0 = r2
        L_0x0119:
            if (r0 == 0) goto L_0x0187
            java.lang.Runnable r0 = r8.H
            r8.a(r0)
            boolean r0 = r8.b()
            if (r0 == 0) goto L_0x0187
            r0 = r2
        L_0x0127:
            boolean r2 = r10.a()
            if (r2 == 0) goto L_0x0132
            android.support.v7.widget.StaggeredGridLayoutManager$a r2 = r8.D
            r2.a()
        L_0x0132:
            boolean r2 = r3.c
            r8.o = r2
            boolean r2 = r8.k()
            r8.z = r2
            if (r0 == 0) goto L_0x0019
            android.support.v7.widget.StaggeredGridLayoutManager$a r0 = r8.D
            r0.a()
            r8.a(r9, r10, r1)
            goto L_0x0019
        L_0x0148:
            r0 = r1
        L_0x0149:
            int r4 = r8.i
            if (r0 >= r4) goto L_0x00b3
            android.support.v7.widget.StaggeredGridLayoutManager$b[] r4 = r8.a
            r4 = r4[r0]
            r4.e()
            android.support.v7.widget.StaggeredGridLayoutManager$a r5 = r8.D
            int[] r5 = r5.f
            r5 = r5[r0]
            r4.c(r5)
            int r0 = r0 + 1
            goto L_0x0149
        L_0x0160:
            r8.m(r2)
            android.support.v7.widget.ab r0 = r8.l
            r8.a(r9, r0, r10)
            r8.m(r7)
            android.support.v7.widget.ab r0 = r8.l
            int r4 = r3.a
            android.support.v7.widget.ab r5 = r8.l
            int r5 = r5.d
            int r4 = r4 + r5
            r0.c = r4
            android.support.v7.widget.ab r0 = r8.l
            r8.a(r9, r0, r10)
            goto L_0x00e9
        L_0x017d:
            r8.c(r9, r10, r2)
            r8.b(r9, r10, r1)
            goto L_0x00fc
        L_0x0185:
            r0 = r1
            goto L_0x0119
        L_0x0187:
            r0 = r1
            goto L_0x0127
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.a(android.support.v7.widget.RecyclerView$o, android.support.v7.widget.RecyclerView$s, boolean):void");
    }

    public void a(s sVar) {
        super.a(sVar);
        this.f = -1;
        this.g = ExploreByTouchHelper.INVALID_ID;
        this.A = null;
        this.D.a();
    }

    private void P() {
        float f2;
        float max;
        if (this.c.h() != 1073741824) {
            float f3 = 0.0f;
            int y = y();
            int i2 = 0;
            while (i2 < y) {
                View i3 = i(i2);
                float e2 = (float) this.c.e(i3);
                if (e2 < f3) {
                    max = f3;
                } else {
                    if (((LayoutParams) i3.getLayoutParams()).a()) {
                        f2 = (1.0f * e2) / ((float) this.i);
                    } else {
                        f2 = e2;
                    }
                    max = Math.max(f3, f2);
                }
                i2++;
                f3 = max;
            }
            int i4 = this.k;
            int round = Math.round(((float) this.i) * f3);
            if (this.c.h() == Integer.MIN_VALUE) {
                round = Math.min(round, this.c.f());
            }
            f(round);
            if (this.k != i4) {
                for (int i5 = 0; i5 < y; i5++) {
                    View i6 = i(i5);
                    LayoutParams layoutParams = (LayoutParams) i6.getLayoutParams();
                    if (!layoutParams.b) {
                        if (!k() || this.j != 1) {
                            int i7 = layoutParams.a.e * this.k;
                            int i8 = layoutParams.a.e * i4;
                            if (this.j == 1) {
                                i6.offsetLeftAndRight(i7 - i8);
                            } else {
                                i6.offsetTopAndBottom(i7 - i8);
                            }
                        } else {
                            i6.offsetLeftAndRight(((-((this.i - 1) - layoutParams.a.e)) * this.k) - ((-((this.i - 1) - layoutParams.a.e)) * i4));
                        }
                    }
                }
            }
        }
    }

    private void a(a aVar) {
        if (this.A.c > 0) {
            if (this.A.c == this.i) {
                for (int i2 = 0; i2 < this.i; i2++) {
                    this.a[i2].e();
                    int i3 = this.A.d[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        if (this.A.i) {
                            i3 += this.b.d();
                        } else {
                            i3 += this.b.c();
                        }
                    }
                    this.a[i2].c(i3);
                }
            } else {
                this.A.a();
                this.A.a = this.A.b;
            }
        }
        this.z = this.A.j;
        a(this.A.h);
        O();
        if (this.A.a != -1) {
            this.f = this.A.a;
            aVar.c = this.A.i;
        } else {
            aVar.c = this.e;
        }
        if (this.A.e > 1) {
            this.h.a = this.A.f;
            this.h.b = this.A.g;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(s sVar, a aVar) {
        if (!b(sVar, aVar) && !c(sVar, aVar)) {
            aVar.b();
            aVar.a = 0;
        }
    }

    private boolean c(s sVar, a aVar) {
        int v;
        if (this.o) {
            v = w(sVar.e());
        } else {
            v = v(sVar.e());
        }
        aVar.a = v;
        aVar.b = ExploreByTouchHelper.INVALID_ID;
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(s sVar, a aVar) {
        int q2;
        int c2;
        boolean z2 = false;
        if (sVar.a() || this.f == -1) {
            return false;
        }
        if (this.f < 0 || this.f >= sVar.e()) {
            this.f = -1;
            this.g = ExploreByTouchHelper.INVALID_ID;
            return false;
        } else if (this.A == null || this.A.a == -1 || this.A.c < 1) {
            View c3 = c(this.f);
            if (c3 != null) {
                if (this.e) {
                    q2 = p();
                } else {
                    q2 = q();
                }
                aVar.a = q2;
                if (this.g != Integer.MIN_VALUE) {
                    if (aVar.c) {
                        aVar.b = (this.b.d() - this.g) - this.b.b(c3);
                        return true;
                    }
                    aVar.b = (this.b.c() + this.g) - this.b.a(c3);
                    return true;
                } else if (this.b.e(c3) > this.b.f()) {
                    if (aVar.c) {
                        c2 = this.b.d();
                    } else {
                        c2 = this.b.c();
                    }
                    aVar.b = c2;
                    return true;
                } else {
                    int a2 = this.b.a(c3) - this.b.c();
                    if (a2 < 0) {
                        aVar.b = -a2;
                        return true;
                    }
                    int d2 = this.b.d() - this.b.b(c3);
                    if (d2 < 0) {
                        aVar.b = d2;
                        return true;
                    }
                    aVar.b = ExploreByTouchHelper.INVALID_ID;
                    return true;
                }
            } else {
                aVar.a = this.f;
                if (this.g == Integer.MIN_VALUE) {
                    if (u(aVar.a) == 1) {
                        z2 = true;
                    }
                    aVar.c = z2;
                    aVar.b();
                } else {
                    aVar.a(this.g);
                }
                aVar.d = true;
                return true;
            }
        } else {
            aVar.b = ExploreByTouchHelper.INVALID_ID;
            aVar.a = this.f;
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void f(int i2) {
        this.k = i2 / this.i;
        this.B = MeasureSpec.makeMeasureSpec(i2, this.c.h());
    }

    public boolean c() {
        return this.A == null;
    }

    public int[] a(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.i];
        } else if (iArr.length < this.i) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.i + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.i; i2++) {
            iArr[i2] = this.a[i2].l();
        }
        return iArr;
    }

    public int c(s sVar) {
        return b(sVar);
    }

    private int b(s sVar) {
        boolean z2 = true;
        if (y() == 0) {
            return 0;
        }
        af afVar = this.b;
        View b2 = b(!this.F);
        if (this.F) {
            z2 = false;
        }
        return ak.a(sVar, afVar, b2, c(z2), this, this.F, this.e);
    }

    public int d(s sVar) {
        return b(sVar);
    }

    public int e(s sVar) {
        return i(sVar);
    }

    private int i(s sVar) {
        boolean z2 = true;
        if (y() == 0) {
            return 0;
        }
        af afVar = this.b;
        View b2 = b(!this.F);
        if (this.F) {
            z2 = false;
        }
        return ak.a(sVar, afVar, b2, c(z2), this, this.F);
    }

    public int f(s sVar) {
        return i(sVar);
    }

    public int g(s sVar) {
        return j(sVar);
    }

    private int j(s sVar) {
        boolean z2 = true;
        if (y() == 0) {
            return 0;
        }
        af afVar = this.b;
        View b2 = b(!this.F);
        if (this.F) {
            z2 = false;
        }
        return ak.b(sVar, afVar, b2, c(z2), this, this.F);
    }

    public int h(s sVar) {
        return j(sVar);
    }

    private void a(View view, LayoutParams layoutParams, boolean z2) {
        if (layoutParams.b) {
            if (this.j == 1) {
                a(view, this.B, a(C(), A(), E() + G(), layoutParams.height, true), z2);
            } else {
                a(view, a(B(), z(), D() + F(), layoutParams.width, true), this.B, z2);
            }
        } else if (this.j == 1) {
            a(view, a(this.k, z(), 0, layoutParams.width, false), a(C(), A(), E() + G(), layoutParams.height, true), z2);
        } else {
            a(view, a(B(), z(), D() + F(), layoutParams.width, true), a(this.k, A(), 0, layoutParams.height, false), z2);
        }
    }

    private void a(View view, int i2, int i3, boolean z2) {
        boolean b2;
        b(view, this.C);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int b3 = b(i2, layoutParams.leftMargin + this.C.left, layoutParams.rightMargin + this.C.right);
        int b4 = b(i3, layoutParams.topMargin + this.C.top, layoutParams.bottomMargin + this.C.bottom);
        if (z2) {
            b2 = a(view, b3, b4, (android.support.v7.widget.RecyclerView.LayoutParams) layoutParams);
        } else {
            b2 = b(view, b3, b4, (android.support.v7.widget.RecyclerView.LayoutParams) layoutParams);
        }
        if (b2) {
            view.measure(b3, b4);
        }
    }

    private int b(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return MeasureSpec.makeMeasureSpec(Math.max(0, (MeasureSpec.getSize(i2) - i3) - i4), mode);
        }
        return i2;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.A = (SavedState) parcelable;
            r();
        }
    }

    public Parcelable e() {
        int q2;
        int a2;
        if (this.A != null) {
            return new SavedState(this.A);
        }
        SavedState savedState = new SavedState();
        savedState.h = this.d;
        savedState.i = this.o;
        savedState.j = this.z;
        if (this.h == null || this.h.a == null) {
            savedState.e = 0;
        } else {
            savedState.f = this.h.a;
            savedState.e = savedState.f.length;
            savedState.g = this.h.b;
        }
        if (y() > 0) {
            if (this.o) {
                q2 = p();
            } else {
                q2 = q();
            }
            savedState.a = q2;
            savedState.b = l();
            savedState.c = this.i;
            savedState.d = new int[this.i];
            for (int i2 = 0; i2 < this.i; i2++) {
                if (this.o) {
                    a2 = this.a[i2].b((int) ExploreByTouchHelper.INVALID_ID);
                    if (a2 != Integer.MIN_VALUE) {
                        a2 -= this.b.d();
                    }
                } else {
                    a2 = this.a[i2].a((int) ExploreByTouchHelper.INVALID_ID);
                    if (a2 != Integer.MIN_VALUE) {
                        a2 -= this.b.c();
                    }
                }
                savedState.d[i2] = a2;
            }
        } else {
            savedState.a = -1;
            savedState.b = -1;
            savedState.c = 0;
        }
        return savedState;
    }

    public void a(o oVar, s sVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.a(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.j == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(layoutParams2.b(), layoutParams2.b ? this.i : 1, -1, -1, layoutParams2.b, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(-1, -1, layoutParams2.b(), layoutParams2.b ? this.i : 1, layoutParams2.b, false));
        }
    }

    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (y() > 0) {
            View b2 = b(false);
            View c2 = c(false);
            if (b2 != null && c2 != null) {
                int d2 = d(b2);
                int d3 = d(c2);
                if (d2 < d3) {
                    accessibilityEvent.setFromIndex(d2);
                    accessibilityEvent.setToIndex(d3);
                    return;
                }
                accessibilityEvent.setFromIndex(d3);
                accessibilityEvent.setToIndex(d2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int l() {
        View b2;
        if (this.e) {
            b2 = c(true);
        } else {
            b2 = b(true);
        }
        if (b2 == null) {
            return -1;
        }
        return d(b2);
    }

    public int a(o oVar, s sVar) {
        if (this.j == 0) {
            return this.i;
        }
        return super.a(oVar, sVar);
    }

    public int b(o oVar, s sVar) {
        if (this.j == 1) {
            return this.i;
        }
        return super.b(oVar, sVar);
    }

    /* access modifiers changed from: 0000 */
    public View b(boolean z2) {
        int c2 = this.b.c();
        int d2 = this.b.d();
        int y = y();
        View view = null;
        for (int i2 = 0; i2 < y; i2++) {
            View i3 = i(i2);
            int a2 = this.b.a(i3);
            if (this.b.b(i3) > c2 && a2 < d2) {
                if (a2 >= c2 || !z2) {
                    return i3;
                }
                if (view == null) {
                    view = i3;
                }
            }
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    public View c(boolean z2) {
        int c2 = this.b.c();
        int d2 = this.b.d();
        View view = null;
        for (int y = y() - 1; y >= 0; y--) {
            View i2 = i(y);
            int a2 = this.b.a(i2);
            int b2 = this.b.b(i2);
            if (b2 > c2 && a2 < d2) {
                if (b2 <= d2 || !z2) {
                    return i2;
                }
                if (view == null) {
                    view = i2;
                }
            }
        }
        return view;
    }

    private void b(o oVar, s sVar, boolean z2) {
        int r = r(ExploreByTouchHelper.INVALID_ID);
        if (r != Integer.MIN_VALUE) {
            int d2 = this.b.d() - r;
            if (d2 > 0) {
                int i2 = d2 - (-c(-d2, oVar, sVar));
                if (z2 && i2 > 0) {
                    this.b.a(i2);
                }
            }
        }
    }

    private void c(o oVar, s sVar, boolean z2) {
        int q2 = q(Integer.MAX_VALUE);
        if (q2 != Integer.MAX_VALUE) {
            int c2 = q2 - this.b.c();
            if (c2 > 0) {
                int c3 = c2 - c(c2, oVar, sVar);
                if (z2 && c3 > 0) {
                    this.b.a(-c3);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(int r7, android.support.v7.widget.RecyclerView.s r8) {
        /*
            r6 = this;
            r3 = 1
            r1 = 0
            android.support.v7.widget.ab r0 = r6.l
            r0.b = r1
            android.support.v7.widget.ab r0 = r6.l
            r0.c = r7
            boolean r0 = r6.v()
            if (r0 == 0) goto L_0x007c
            int r0 = r8.c()
            r2 = -1
            if (r0 == r2) goto L_0x007c
            boolean r2 = r6.e
            if (r0 >= r7) goto L_0x0060
            r0 = r3
        L_0x001c:
            if (r2 != r0) goto L_0x0062
            android.support.v7.widget.af r0 = r6.b
            int r0 = r0.f()
            r2 = r1
        L_0x0025:
            boolean r4 = r6.u()
            if (r4 == 0) goto L_0x006b
            android.support.v7.widget.ab r4 = r6.l
            android.support.v7.widget.af r5 = r6.b
            int r5 = r5.c()
            int r2 = r5 - r2
            r4.f = r2
            android.support.v7.widget.ab r2 = r6.l
            android.support.v7.widget.af r4 = r6.b
            int r4 = r4.d()
            int r0 = r0 + r4
            r2.g = r0
        L_0x0042:
            android.support.v7.widget.ab r0 = r6.l
            r0.h = r1
            android.support.v7.widget.ab r0 = r6.l
            r0.a = r3
            android.support.v7.widget.ab r0 = r6.l
            android.support.v7.widget.af r2 = r6.b
            int r2 = r2.h()
            if (r2 != 0) goto L_0x005d
            android.support.v7.widget.af r2 = r6.b
            int r2 = r2.e()
            if (r2 != 0) goto L_0x005d
            r1 = r3
        L_0x005d:
            r0.i = r1
            return
        L_0x0060:
            r0 = r1
            goto L_0x001c
        L_0x0062:
            android.support.v7.widget.af r0 = r6.b
            int r0 = r0.f()
            r2 = r0
            r0 = r1
            goto L_0x0025
        L_0x006b:
            android.support.v7.widget.ab r4 = r6.l
            android.support.v7.widget.af r5 = r6.b
            int r5 = r5.e()
            int r0 = r0 + r5
            r4.g = r0
            android.support.v7.widget.ab r0 = r6.l
            int r2 = -r2
            r0.f = r2
            goto L_0x0042
        L_0x007c:
            r0 = r1
            r2 = r1
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.b(int, android.support.v7.widget.RecyclerView$s):void");
    }

    private void m(int i2) {
        int i3 = 1;
        this.l.e = i2;
        ab abVar = this.l;
        if (this.e != (i2 == -1)) {
            i3 = -1;
        }
        abVar.d = i3;
    }

    public void j(int i2) {
        super.j(i2);
        for (int i3 = 0; i3 < this.i; i3++) {
            this.a[i3].d(i2);
        }
    }

    public void k(int i2) {
        super.k(i2);
        for (int i3 = 0; i3 < this.i; i3++) {
            this.a[i3].d(i2);
        }
    }

    public void b(RecyclerView recyclerView, int i2, int i3) {
        c(i2, i3, 2);
    }

    public void a(RecyclerView recyclerView, int i2, int i3) {
        c(i2, i3, 1);
    }

    public void a(RecyclerView recyclerView) {
        this.h.a();
        r();
    }

    public void a(RecyclerView recyclerView, int i2, int i3, int i4) {
        c(i2, i3, 8);
    }

    public void a(RecyclerView recyclerView, int i2, int i3, Object obj) {
        c(i2, i3, 4);
    }

    private void c(int i2, int i3, int i4) {
        int i5;
        int i6;
        int q2 = this.e ? p() : q();
        if (i4 != 8) {
            i5 = i2 + i3;
            i6 = i2;
        } else if (i2 < i3) {
            i5 = i3 + 1;
            i6 = i2;
        } else {
            i5 = i2 + 1;
            i6 = i3;
        }
        this.h.b(i6);
        switch (i4) {
            case 1:
                this.h.b(i2, i3);
                break;
            case 2:
                this.h.a(i2, i3);
                break;
            case 8:
                this.h.a(i2, 1);
                this.h.b(i3, 1);
                break;
        }
        if (i5 > q2) {
            if (i6 <= (this.e ? q() : p())) {
                r();
            }
        }
    }

    private int a(o oVar, ab abVar, s sVar) {
        int i2;
        int c2;
        int r;
        b bVar;
        int a2;
        int e2;
        int i3;
        int c3;
        int e3;
        int i4;
        int d2;
        int b2;
        this.m.set(0, this.i, true);
        if (this.l.i) {
            if (abVar.e == 1) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = Integer.MIN_VALUE;
            }
        } else if (abVar.e == 1) {
            i2 = abVar.g + abVar.b;
        } else {
            i2 = abVar.f - abVar.b;
        }
        a(abVar.e, i2);
        if (this.e) {
            c2 = this.b.d();
        } else {
            c2 = this.b.c();
        }
        boolean z2 = false;
        while (abVar.a(sVar) && (this.l.i || !this.m.isEmpty())) {
            View a3 = abVar.a(oVar);
            LayoutParams layoutParams = (LayoutParams) a3.getLayoutParams();
            int f2 = layoutParams.f();
            int c4 = this.h.c(f2);
            boolean z3 = c4 == -1;
            if (z3) {
                b a4 = layoutParams.b ? this.a[0] : a(abVar);
                this.h.a(f2, a4);
                bVar = a4;
            } else {
                bVar = this.a[c4];
            }
            layoutParams.a = bVar;
            if (abVar.e == 1) {
                b(a3);
            } else {
                b(a3, 0);
            }
            a(a3, layoutParams, false);
            if (abVar.e == 1) {
                if (layoutParams.b) {
                    b2 = r(c2);
                } else {
                    b2 = bVar.b(c2);
                }
                i3 = b2 + this.b.e(a3);
                if (!z3 || !layoutParams.b) {
                    e2 = b2;
                } else {
                    FullSpanItem n2 = n(b2);
                    n2.b = -1;
                    n2.a = f2;
                    this.h.a(n2);
                    e2 = b2;
                }
            } else {
                if (layoutParams.b) {
                    a2 = q(c2);
                } else {
                    a2 = bVar.a(c2);
                }
                e2 = a2 - this.b.e(a3);
                if (z3 && layoutParams.b) {
                    FullSpanItem o2 = o(a2);
                    o2.b = 1;
                    o2.a = f2;
                    this.h.a(o2);
                }
                i3 = a2;
            }
            if (layoutParams.b && abVar.d == -1) {
                if (z3) {
                    this.E = true;
                } else {
                    boolean z4 = abVar.e == 1 ? !n() : !o();
                    if (z4) {
                        FullSpanItem f3 = this.h.f(f2);
                        if (f3 != null) {
                            f3.d = true;
                        }
                        this.E = true;
                    }
                }
            }
            a(a3, layoutParams, abVar);
            if (!k() || this.j != 1) {
                if (layoutParams.b) {
                    c3 = this.c.c();
                } else {
                    c3 = (bVar.e * this.k) + this.c.c();
                }
                e3 = c3 + this.c.e(a3);
                i4 = c3;
            } else {
                if (layoutParams.b) {
                    d2 = this.c.d();
                } else {
                    d2 = this.c.d() - (((this.i - 1) - bVar.e) * this.k);
                }
                i4 = d2 - this.c.e(a3);
                e3 = d2;
            }
            if (this.j == 1) {
                a(a3, i4, e2, e3, i3);
            } else {
                a(a3, e2, i4, i3, e3);
            }
            if (layoutParams.b) {
                a(this.l.e, i2);
            } else {
                a(bVar, this.l.e, i2);
            }
            a(oVar, this.l);
            if (this.l.h && a3.hasFocusable()) {
                if (layoutParams.b) {
                    this.m.clear();
                } else {
                    this.m.set(bVar.e, false);
                }
            }
            z2 = true;
        }
        if (!z2) {
            a(oVar, this.l);
        }
        if (this.l.e == -1) {
            r = this.b.c() - q(this.b.c());
        } else {
            r = r(this.b.d()) - this.b.d();
        }
        if (r > 0) {
            return Math.min(abVar.b, r);
        }
        return 0;
    }

    private FullSpanItem n(int i2) {
        FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.c = new int[this.i];
        for (int i3 = 0; i3 < this.i; i3++) {
            fullSpanItem.c[i3] = i2 - this.a[i3].b(i2);
        }
        return fullSpanItem;
    }

    private FullSpanItem o(int i2) {
        FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.c = new int[this.i];
        for (int i3 = 0; i3 < this.i; i3++) {
            fullSpanItem.c[i3] = this.a[i3].a(i2) - i2;
        }
        return fullSpanItem;
    }

    private void a(View view, LayoutParams layoutParams, ab abVar) {
        if (abVar.e == 1) {
            if (layoutParams.b) {
                p(view);
            } else {
                layoutParams.a.b(view);
            }
        } else if (layoutParams.b) {
            q(view);
        } else {
            layoutParams.a.a(view);
        }
    }

    private void a(o oVar, ab abVar) {
        int min;
        int min2;
        if (abVar.a && !abVar.i) {
            if (abVar.b == 0) {
                if (abVar.e == -1) {
                    b(oVar, abVar.g);
                } else {
                    a(oVar, abVar.f);
                }
            } else if (abVar.e == -1) {
                int p = abVar.f - p(abVar.f);
                if (p < 0) {
                    min2 = abVar.g;
                } else {
                    min2 = abVar.g - Math.min(p, abVar.b);
                }
                b(oVar, min2);
            } else {
                int s = s(abVar.g) - abVar.g;
                if (s < 0) {
                    min = abVar.f;
                } else {
                    min = Math.min(s, abVar.b) + abVar.f;
                }
                a(oVar, min);
            }
        }
    }

    private void p(View view) {
        for (int i2 = this.i - 1; i2 >= 0; i2--) {
            this.a[i2].b(view);
        }
    }

    private void q(View view) {
        for (int i2 = this.i - 1; i2 >= 0; i2--) {
            this.a[i2].a(view);
        }
    }

    private void a(int i2, int i3) {
        for (int i4 = 0; i4 < this.i; i4++) {
            if (!this.a[i4].a.isEmpty()) {
                a(this.a[i4], i2, i3);
            }
        }
    }

    private void a(b bVar, int i2, int i3) {
        int i4 = bVar.i();
        if (i2 == -1) {
            if (i4 + bVar.b() <= i3) {
                this.m.set(bVar.e, false);
            }
        } else if (bVar.d() - i4 >= i3) {
            this.m.set(bVar.e, false);
        }
    }

    private int p(int i2) {
        int a2 = this.a[0].a(i2);
        for (int i3 = 1; i3 < this.i; i3++) {
            int a3 = this.a[i3].a(i2);
            if (a3 > a2) {
                a2 = a3;
            }
        }
        return a2;
    }

    private int q(int i2) {
        int a2 = this.a[0].a(i2);
        for (int i3 = 1; i3 < this.i; i3++) {
            int a3 = this.a[i3].a(i2);
            if (a3 < a2) {
                a2 = a3;
            }
        }
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public boolean n() {
        int b2 = this.a[0].b((int) ExploreByTouchHelper.INVALID_ID);
        for (int i2 = 1; i2 < this.i; i2++) {
            if (this.a[i2].b((int) ExploreByTouchHelper.INVALID_ID) != b2) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean o() {
        int a2 = this.a[0].a((int) ExploreByTouchHelper.INVALID_ID);
        for (int i2 = 1; i2 < this.i; i2++) {
            if (this.a[i2].a((int) ExploreByTouchHelper.INVALID_ID) != a2) {
                return false;
            }
        }
        return true;
    }

    private int r(int i2) {
        int b2 = this.a[0].b(i2);
        for (int i3 = 1; i3 < this.i; i3++) {
            int b3 = this.a[i3].b(i2);
            if (b3 > b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    private int s(int i2) {
        int b2 = this.a[0].b(i2);
        for (int i3 = 1; i3 < this.i; i3++) {
            int b3 = this.a[i3].b(i2);
            if (b3 < b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    private void a(o oVar, int i2) {
        while (y() > 0) {
            View i3 = i(0);
            if (this.b.b(i3) <= i2 && this.b.c(i3) <= i2) {
                LayoutParams layoutParams = (LayoutParams) i3.getLayoutParams();
                if (layoutParams.b) {
                    int i4 = 0;
                    while (i4 < this.i) {
                        if (this.a[i4].a.size() != 1) {
                            i4++;
                        } else {
                            return;
                        }
                    }
                    for (int i5 = 0; i5 < this.i; i5++) {
                        this.a[i5].h();
                    }
                } else if (layoutParams.a.a.size() != 1) {
                    layoutParams.a.h();
                } else {
                    return;
                }
                a(i3, oVar);
            } else {
                return;
            }
        }
    }

    private void b(o oVar, int i2) {
        int y = y() - 1;
        while (y >= 0) {
            View i3 = i(y);
            if (this.b.a(i3) >= i2 && this.b.d(i3) >= i2) {
                LayoutParams layoutParams = (LayoutParams) i3.getLayoutParams();
                if (layoutParams.b) {
                    int i4 = 0;
                    while (i4 < this.i) {
                        if (this.a[i4].a.size() != 1) {
                            i4++;
                        } else {
                            return;
                        }
                    }
                    for (int i5 = 0; i5 < this.i; i5++) {
                        this.a[i5].g();
                    }
                } else if (layoutParams.a.a.size() != 1) {
                    layoutParams.a.g();
                } else {
                    return;
                }
                a(i3, oVar);
                y--;
            } else {
                return;
            }
        }
    }

    private boolean t(int i2) {
        boolean z2;
        if (this.j == 0) {
            if (i2 == -1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 != this.e) {
                return true;
            }
            return false;
        }
        if (((i2 == -1) == this.e) != k()) {
            return false;
        }
        return true;
    }

    private b a(ab abVar) {
        int i2;
        int i3;
        b bVar;
        b bVar2;
        b bVar3 = null;
        int i4 = -1;
        if (t(abVar.e)) {
            i2 = this.i - 1;
            i3 = -1;
        } else {
            i2 = 0;
            i3 = this.i;
            i4 = 1;
        }
        if (abVar.e == 1) {
            int c2 = this.b.c();
            int i5 = i2;
            int i6 = Integer.MAX_VALUE;
            while (i5 != i3) {
                b bVar4 = this.a[i5];
                int b2 = bVar4.b(c2);
                if (b2 < i6) {
                    bVar2 = bVar4;
                } else {
                    b2 = i6;
                    bVar2 = bVar3;
                }
                i5 += i4;
                bVar3 = bVar2;
                i6 = b2;
            }
        } else {
            int d2 = this.b.d();
            int i7 = i2;
            int i8 = Integer.MIN_VALUE;
            while (i7 != i3) {
                b bVar5 = this.a[i7];
                int a2 = bVar5.a(d2);
                if (a2 > i8) {
                    bVar = bVar5;
                } else {
                    a2 = i8;
                    bVar = bVar3;
                }
                i7 += i4;
                bVar3 = bVar;
                i8 = a2;
            }
        }
        return bVar3;
    }

    public boolean g() {
        return this.j == 1;
    }

    public boolean f() {
        return this.j == 0;
    }

    public int a(int i2, o oVar, s sVar) {
        return c(i2, oVar, sVar);
    }

    public int b(int i2, o oVar, s sVar) {
        return c(i2, oVar, sVar);
    }

    private int u(int i2) {
        int i3 = -1;
        if (y() != 0) {
            if ((i2 < q()) == this.e) {
                i3 = 1;
            }
            return i3;
        } else if (this.e) {
            return 1;
        } else {
            return -1;
        }
    }

    public PointF d(int i2) {
        int u = u(i2);
        PointF pointF = new PointF();
        if (u == 0) {
            return null;
        }
        if (this.j == 0) {
            pointF.x = (float) u;
            pointF.y = 0.0f;
            return pointF;
        }
        pointF.x = 0.0f;
        pointF.y = (float) u;
        return pointF;
    }

    public void e(int i2) {
        if (!(this.A == null || this.A.a == i2)) {
            this.A.b();
        }
        this.f = i2;
        this.g = ExploreByTouchHelper.INVALID_ID;
        r();
    }

    public void a(int i2, int i3, s sVar, android.support.v7.widget.RecyclerView.i.a aVar) {
        int b2;
        if (this.j != 0) {
            i2 = i3;
        }
        if (y() != 0 && i2 != 0) {
            a(i2, sVar);
            if (this.G == null || this.G.length < this.i) {
                this.G = new int[this.i];
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.i; i5++) {
                if (this.l.d == -1) {
                    b2 = this.l.f - this.a[i5].a(this.l.f);
                } else {
                    b2 = this.a[i5].b(this.l.g) - this.l.g;
                }
                if (b2 >= 0) {
                    this.G[i4] = b2;
                    i4++;
                }
            }
            Arrays.sort(this.G, 0, i4);
            for (int i6 = 0; i6 < i4 && this.l.a(sVar); i6++) {
                aVar.b(this.l.c, this.G[i6]);
                this.l.c += this.l.d;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, s sVar) {
        int i3;
        int q2;
        if (i2 > 0) {
            q2 = p();
            i3 = 1;
        } else {
            i3 = -1;
            q2 = q();
        }
        this.l.a = true;
        b(q2, sVar);
        m(i3);
        this.l.c = this.l.d + q2;
        this.l.b = Math.abs(i2);
    }

    /* access modifiers changed from: 0000 */
    public int c(int i2, o oVar, s sVar) {
        if (y() == 0 || i2 == 0) {
            return 0;
        }
        a(i2, sVar);
        int a2 = a(oVar, this.l, sVar);
        if (this.l.b >= a2) {
            i2 = i2 < 0 ? -a2 : a2;
        }
        this.b.a(-i2);
        this.o = this.e;
        this.l.b = 0;
        a(oVar, this.l);
        return i2;
    }

    /* access modifiers changed from: 0000 */
    public int p() {
        int y = y();
        if (y == 0) {
            return 0;
        }
        return d(i(y - 1));
    }

    /* access modifiers changed from: 0000 */
    public int q() {
        if (y() == 0) {
            return 0;
        }
        return d(i(0));
    }

    private int v(int i2) {
        int y = y();
        for (int i3 = 0; i3 < y; i3++) {
            int d2 = d(i(i3));
            if (d2 >= 0 && d2 < i2) {
                return d2;
            }
        }
        return 0;
    }

    private int w(int i2) {
        for (int y = y() - 1; y >= 0; y--) {
            int d2 = d(i(y));
            if (d2 >= 0 && d2 < i2) {
                return d2;
            }
        }
        return 0;
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a() {
        if (this.j == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public boolean a(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public View a(View view, int i2, o oVar, s sVar) {
        int q2;
        int k2;
        int k3;
        int k4;
        if (y() == 0) {
            return null;
        }
        View e2 = e(view);
        if (e2 == null) {
            return null;
        }
        O();
        int x = x(i2);
        if (x == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) e2.getLayoutParams();
        boolean z2 = layoutParams.b;
        b bVar = layoutParams.a;
        if (x == 1) {
            q2 = p();
        } else {
            q2 = q();
        }
        b(q2, sVar);
        m(x);
        this.l.c = this.l.d + q2;
        this.l.b = (int) (0.33333334f * ((float) this.b.f()));
        this.l.h = true;
        this.l.a = false;
        a(oVar, this.l, sVar);
        this.o = this.e;
        if (!z2) {
            View a2 = bVar.a(q2, x);
            if (!(a2 == null || a2 == e2)) {
                return a2;
            }
        }
        if (t(x)) {
            for (int i3 = this.i - 1; i3 >= 0; i3--) {
                View a3 = this.a[i3].a(q2, x);
                if (a3 != null && a3 != e2) {
                    return a3;
                }
            }
        } else {
            for (int i4 = 0; i4 < this.i; i4++) {
                View a4 = this.a[i4].a(q2, x);
                if (a4 != null && a4 != e2) {
                    return a4;
                }
            }
        }
        boolean z3 = (!this.d) == (x == -1);
        if (!z2) {
            if (z3) {
                k4 = bVar.j();
            } else {
                k4 = bVar.k();
            }
            View c2 = c(k4);
            if (!(c2 == null || c2 == e2)) {
                return c2;
            }
        }
        if (t(x)) {
            for (int i5 = this.i - 1; i5 >= 0; i5--) {
                if (i5 != bVar.e) {
                    if (z3) {
                        k3 = this.a[i5].j();
                    } else {
                        k3 = this.a[i5].k();
                    }
                    View c3 = c(k3);
                    if (!(c3 == null || c3 == e2)) {
                        return c3;
                    }
                }
            }
        } else {
            for (int i6 = 0; i6 < this.i; i6++) {
                if (z3) {
                    k2 = this.a[i6].j();
                } else {
                    k2 = this.a[i6].k();
                }
                View c4 = c(k2);
                if (c4 != null && c4 != e2) {
                    return c4;
                }
            }
        }
        return null;
    }

    private int x(int i2) {
        int i3 = ExploreByTouchHelper.INVALID_ID;
        int i4 = 1;
        switch (i2) {
            case 1:
                if (this.j == 1 || !k()) {
                    return -1;
                }
                return 1;
            case 2:
                if (this.j == 1) {
                    return 1;
                }
                if (!k()) {
                    return 1;
                }
                return -1;
            case 17:
                if (this.j != 0) {
                    return ExploreByTouchHelper.INVALID_ID;
                }
                return -1;
            case 33:
                if (this.j != 1) {
                    return ExploreByTouchHelper.INVALID_ID;
                }
                return -1;
            case 66:
                if (this.j != 0) {
                    i4 = Integer.MIN_VALUE;
                }
                return i4;
            case 130:
                if (this.j == 1) {
                    i3 = 1;
                }
                return i3;
            default:
                return ExploreByTouchHelper.INVALID_ID;
        }
    }
}
