package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.RecyclerView.s;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    boolean a = false;
    int b = -1;
    int[] c;
    View[] d;
    final SparseIntArray e = new SparseIntArray();
    final SparseIntArray f = new SparseIntArray();
    b g = new a();
    final Rect h = new Rect();

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        int a = -1;
        int b = 0;

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

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    public static final class a extends b {
        public int getSpanSize(int i) {
            return 1;
        }

        public int getSpanIndex(int i, int i2) {
            return i % i2;
        }
    }

    public static abstract class b {
        private boolean mCacheSpanIndices = false;
        final SparseIntArray mSpanIndexCache = new SparseIntArray();

        public abstract int getSpanSize(int i);

        public void setSpanIndexCacheEnabled(boolean z) {
            this.mCacheSpanIndices = z;
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        /* access modifiers changed from: 0000 */
        public int getCachedSpanIndex(int i, int i2) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i, i2);
            }
            int i3 = this.mSpanIndexCache.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int spanIndex = getSpanIndex(i, i2);
            this.mSpanIndexCache.put(i, spanIndex);
            return spanIndex;
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x002a  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x003f  */
        /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSpanIndex(int r6, int r7) {
            /*
                r5 = this;
                r1 = 0
                int r4 = r5.getSpanSize(r6)
                if (r4 != r7) goto L_0x0008
            L_0x0007:
                return r1
            L_0x0008:
                boolean r0 = r5.mCacheSpanIndices
                if (r0 == 0) goto L_0x0041
                android.util.SparseIntArray r0 = r5.mSpanIndexCache
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x0041
                int r0 = r5.findReferenceIndexFromCache(r6)
                if (r0 < 0) goto L_0x0041
                android.util.SparseIntArray r2 = r5.mSpanIndexCache
                int r2 = r2.get(r0)
                int r3 = r5.getSpanSize(r0)
                int r2 = r2 + r3
                int r0 = r0 + 1
            L_0x0027:
                r3 = r0
            L_0x0028:
                if (r3 >= r6) goto L_0x003b
                int r0 = r5.getSpanSize(r3)
                int r2 = r2 + r0
                if (r2 != r7) goto L_0x0037
                r0 = r1
            L_0x0032:
                int r2 = r3 + 1
                r3 = r2
                r2 = r0
                goto L_0x0028
            L_0x0037:
                if (r2 > r7) goto L_0x0032
                r0 = r2
                goto L_0x0032
            L_0x003b:
                int r0 = r2 + r4
                if (r0 > r7) goto L_0x0007
                r1 = r2
                goto L_0x0007
            L_0x0041:
                r0 = r1
                r2 = r1
                goto L_0x0027
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.b.getSpanIndex(int, int):int");
        }

        /* access modifiers changed from: 0000 */
        public int findReferenceIndexFromCache(int i) {
            int i2 = 0;
            int size = this.mSpanIndexCache.size() - 1;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (this.mSpanIndexCache.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            int i4 = i2 - 1;
            if (i4 < 0 || i4 >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(i4);
        }

        public int getSpanGroupIndex(int i, int i2) {
            int spanSize = getSpanSize(i);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < i) {
                int spanSize2 = getSpanSize(i3);
                int i6 = i5 + spanSize2;
                if (i6 == i2) {
                    i4++;
                    spanSize2 = 0;
                } else if (i6 > i2) {
                    i4++;
                } else {
                    spanSize2 = i6;
                }
                i3++;
                i5 = spanSize2;
            }
            if (i5 + spanSize > i2) {
                return i4 + 1;
            }
            return i4;
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(a(context, attributeSet, i, i2).b);
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        a(i);
    }

    public void a(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.a(false);
    }

    public int a(o oVar, s sVar) {
        if (this.i == 0) {
            return this.b;
        }
        if (sVar.e() < 1) {
            return 0;
        }
        return a(oVar, sVar, sVar.e() - 1) + 1;
    }

    public int b(o oVar, s sVar) {
        if (this.i == 1) {
            return this.b;
        }
        if (sVar.e() < 1) {
            return 0;
        }
        return a(oVar, sVar, sVar.e() - 1) + 1;
    }

    public void a(o oVar, s sVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.a(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int a2 = a(oVar, sVar, layoutParams2.f());
        if (this.i == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(layoutParams2.a(), layoutParams2.b(), a2, 1, this.b > 1 && layoutParams2.b() == this.b, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(a2, 1, layoutParams2.a(), layoutParams2.b(), this.b > 1 && layoutParams2.b() == this.b, false));
        }
    }

    public void c(o oVar, s sVar) {
        if (sVar.a()) {
            O();
        }
        super.c(oVar, sVar);
        N();
    }

    public void a(s sVar) {
        super.a(sVar);
        this.a = false;
    }

    private void N() {
        this.e.clear();
        this.f.clear();
    }

    private void O() {
        int y = y();
        for (int i = 0; i < y; i++) {
            LayoutParams layoutParams = (LayoutParams) i(i).getLayoutParams();
            int f2 = layoutParams.f();
            this.e.put(f2, layoutParams.b());
            this.f.put(f2, layoutParams.a());
        }
    }

    public void a(RecyclerView recyclerView, int i, int i2) {
        this.g.invalidateSpanIndexCache();
    }

    public void a(RecyclerView recyclerView) {
        this.g.invalidateSpanIndexCache();
    }

    public void b(RecyclerView recyclerView, int i, int i2) {
        this.g.invalidateSpanIndexCache();
    }

    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.g.invalidateSpanIndexCache();
    }

    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        this.g.invalidateSpanIndexCache();
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a() {
        if (this.i == 0) {
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

    public void a(b bVar) {
        this.g = bVar;
    }

    private void P() {
        int C;
        if (h() == 1) {
            C = (B() - F()) - D();
        } else {
            C = (C() - G()) - E();
        }
        m(C);
    }

    public void a(Rect rect, int i, int i2) {
        int a2;
        int a3;
        if (this.c == null) {
            super.a(rect, i, i2);
        }
        int F = F() + D();
        int E = E() + G();
        if (this.i == 1) {
            a3 = a(i2, E + rect.height(), J());
            a2 = a(i, F + this.c[this.c.length - 1], I());
        } else {
            a2 = a(i, F + rect.width(), I());
            a3 = a(i2, E + this.c[this.c.length - 1], J());
        }
        g(a2, a3);
    }

    private void m(int i) {
        this.c = a(this.c, this.b, i);
    }

    static int[] a(int[] iArr, int i, int i2) {
        int i3;
        int i4 = 0;
        if (!(iArr != null && iArr.length == i + 1 && iArr[iArr.length - 1] == i2)) {
            iArr = new int[(i + 1)];
        }
        iArr[0] = 0;
        int i5 = i2 / i;
        int i6 = i2 % i;
        int i7 = 0;
        for (int i8 = 1; i8 <= i; i8++) {
            i4 += i6;
            if (i4 <= 0 || i - i4 >= i6) {
                i3 = i5;
            } else {
                i3 = i5 + 1;
                i4 -= i;
            }
            i7 += i3;
            iArr[i8] = i7;
        }
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    public int a(int i, int i2) {
        if (this.i != 1 || !i()) {
            return this.c[i + i2] - this.c[i];
        }
        return this.c[this.b - i] - this.c[(this.b - i) - i2];
    }

    /* access modifiers changed from: 0000 */
    public void a(o oVar, s sVar, a aVar, int i) {
        super.a(oVar, sVar, aVar, i);
        P();
        if (sVar.e() > 0 && !sVar.a()) {
            b(oVar, sVar, aVar, i);
        }
        Q();
    }

    private void Q() {
        if (this.d == null || this.d.length != this.b) {
            this.d = new View[this.b];
        }
    }

    public int a(int i, o oVar, s sVar) {
        P();
        Q();
        return super.a(i, oVar, sVar);
    }

    public int b(int i, o oVar, s sVar) {
        P();
        Q();
        return super.b(i, oVar, sVar);
    }

    private void b(o oVar, s sVar, a aVar, int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        int b2 = b(oVar, sVar, aVar.b);
        if (z) {
            while (b2 > 0 && aVar.b > 0) {
                aVar.b--;
                b2 = b(oVar, sVar, aVar.b);
            }
            return;
        }
        int e2 = sVar.e() - 1;
        int i2 = aVar.b;
        int i3 = b2;
        while (i2 < e2) {
            int b3 = b(oVar, sVar, i2 + 1);
            if (b3 <= i3) {
                break;
            }
            i2++;
            i3 = b3;
        }
        aVar.b = i2;
    }

    /* access modifiers changed from: 0000 */
    public View a(o oVar, s sVar, int i, int i2, int i3) {
        View view;
        View view2 = null;
        j();
        int c2 = this.j.c();
        int d2 = this.j.d();
        int i4 = i2 > i ? 1 : -1;
        View view3 = null;
        while (i != i2) {
            View i5 = i(i);
            int d3 = d(i5);
            if (d3 >= 0 && d3 < i3) {
                if (b(oVar, sVar, d3) != 0) {
                    view = view2;
                    i5 = view3;
                } else if (((android.support.v7.widget.RecyclerView.LayoutParams) i5.getLayoutParams()).d()) {
                    if (view3 == null) {
                        view = view2;
                    }
                } else if (this.j.a(i5) < d2 && this.j.b(i5) >= c2) {
                    return i5;
                } else {
                    if (view2 == null) {
                        view = i5;
                        i5 = view3;
                    }
                }
                i += i4;
                view2 = view;
                view3 = i5;
            }
            view = view2;
            i5 = view3;
            i += i4;
            view2 = view;
            view3 = i5;
        }
        if (view2 == null) {
            view2 = view3;
        }
        return view2;
    }

    private int a(o oVar, s sVar, int i) {
        if (!sVar.a()) {
            return this.g.getSpanGroupIndex(i, this.b);
        }
        int b2 = oVar.b(i);
        if (b2 != -1) {
            return this.g.getSpanGroupIndex(b2, this.b);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int b(o oVar, s sVar, int i) {
        if (!sVar.a()) {
            return this.g.getCachedSpanIndex(i, this.b);
        }
        int i2 = this.f.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int b2 = oVar.b(i);
        if (b2 != -1) {
            return this.g.getCachedSpanIndex(b2, this.b);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int c(o oVar, s sVar, int i) {
        if (!sVar.a()) {
            return this.g.getSpanSize(i);
        }
        int i2 = this.e.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int b2 = oVar.b(i);
        if (b2 != -1) {
            return this.g.getSpanSize(b2);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    /* access modifiers changed from: 0000 */
    public void a(s sVar, c cVar, android.support.v7.widget.RecyclerView.i.a aVar) {
        int i = this.b;
        for (int i2 = 0; i2 < this.b && cVar.a(sVar) && i > 0; i2++) {
            int i3 = cVar.d;
            aVar.b(i3, Math.max(0, cVar.g));
            i -= this.g.getSpanSize(i3);
            cVar.d += cVar.e;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(o oVar, s sVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int makeMeasureSpec;
        int a2;
        int i4 = this.j.i();
        boolean z = i4 != 1073741824;
        int i5 = y() > 0 ? this.c[this.b] : 0;
        if (z) {
            P();
        }
        boolean z2 = cVar.e == 1;
        int i6 = 0;
        int i7 = 0;
        int i8 = this.b;
        if (!z2) {
            i8 = b(oVar, sVar, cVar.d) + c(oVar, sVar, cVar.d);
        }
        while (i6 < this.b && cVar.a(sVar) && i8 > 0) {
            int i9 = cVar.d;
            int c2 = c(oVar, sVar, i9);
            if (c2 <= this.b) {
                i8 -= c2;
                if (i8 >= 0) {
                    View a3 = cVar.a(oVar);
                    if (a3 == null) {
                        break;
                    }
                    i7 += c2;
                    this.d[i6] = a3;
                    i6++;
                } else {
                    break;
                }
            } else {
                throw new IllegalArgumentException("Item at position " + i9 + " requires " + c2 + " spans but GridLayoutManager has only " + this.b + " spans.");
            }
        }
        if (i6 == 0) {
            bVar.b = true;
            return;
        }
        a(oVar, sVar, i6, i7, z2);
        int i10 = 0;
        float f2 = 0.0f;
        int i11 = 0;
        while (i10 < i6) {
            View view = this.d[i10];
            if (cVar.k == null) {
                if (z2) {
                    b(view);
                } else {
                    b(view, 0);
                }
            } else if (z2) {
                a(view);
            } else {
                a(view, 0);
            }
            b(view, this.h);
            a(view, i4, false);
            int e2 = this.j.e(view);
            if (e2 > i11) {
                i11 = e2;
            }
            float f3 = (((float) this.j.f(view)) * 1.0f) / ((float) ((LayoutParams) view.getLayoutParams()).b);
            if (f3 <= f2) {
                f3 = f2;
            }
            i10++;
            f2 = f3;
        }
        if (z) {
            a(f2, i5);
            i11 = 0;
            int i12 = 0;
            while (i12 < i6) {
                View view2 = this.d[i12];
                a(view2, 1073741824, true);
                int e3 = this.j.e(view2);
                if (e3 <= i11) {
                    e3 = i11;
                }
                i12++;
                i11 = e3;
            }
        }
        for (int i13 = 0; i13 < i6; i13++) {
            View view3 = this.d[i13];
            if (this.j.e(view3) != i11) {
                LayoutParams layoutParams = (LayoutParams) view3.getLayoutParams();
                Rect rect = layoutParams.d;
                int i14 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
                int i15 = rect.right + rect.left + layoutParams.leftMargin + layoutParams.rightMargin;
                int a4 = a(layoutParams.a, layoutParams.b);
                if (this.i == 1) {
                    makeMeasureSpec = a(a4, 1073741824, i15, layoutParams.width, false);
                    a2 = MeasureSpec.makeMeasureSpec(i11 - i14, 1073741824);
                } else {
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(i11 - i15, 1073741824);
                    a2 = a(a4, 1073741824, i14, layoutParams.height, false);
                }
                a(view3, makeMeasureSpec, a2, true);
            }
        }
        bVar.a = i11;
        int i16 = 0;
        if (this.i == 1) {
            if (cVar.f == -1) {
                i16 = cVar.b;
                i3 = i16 - i11;
                i2 = 0;
                i = 0;
            } else {
                int i17 = cVar.b;
                i16 = i17 + i11;
                i3 = i17;
                i2 = 0;
                i = 0;
            }
        } else if (cVar.f == -1) {
            int i18 = cVar.b;
            i2 = i18;
            i = i18 - i11;
            i3 = 0;
        } else {
            i = cVar.b;
            i2 = i11 + i;
            i3 = 0;
        }
        int i19 = i16;
        int i20 = i3;
        int i21 = i2;
        int i22 = i;
        for (int i23 = 0; i23 < i6; i23++) {
            View view4 = this.d[i23];
            LayoutParams layoutParams2 = (LayoutParams) view4.getLayoutParams();
            if (this.i != 1) {
                i20 = E() + this.c[layoutParams2.a];
                i19 = i20 + this.j.f(view4);
            } else if (i()) {
                i21 = D() + this.c[this.b - layoutParams2.a];
                i22 = i21 - this.j.f(view4);
            } else {
                i22 = D() + this.c[layoutParams2.a];
                i21 = i22 + this.j.f(view4);
            }
            a(view4, i22, i20, i21, i19);
            if (layoutParams2.d() || layoutParams2.e()) {
                bVar.c = true;
            }
            bVar.d |= view4.hasFocusable();
        }
        Arrays.fill(this.d, null);
    }

    private void a(View view, int i, boolean z) {
        int a2;
        int i2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.d;
        int i3 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        int i4 = layoutParams.rightMargin + rect.right + rect.left + layoutParams.leftMargin;
        int a3 = a(layoutParams.a, layoutParams.b);
        if (this.i == 1) {
            a2 = a(a3, i, i4, layoutParams.width, false);
            i2 = a(this.j.f(), A(), i3, layoutParams.height, true);
        } else {
            int a4 = a(a3, i, i3, layoutParams.height, false);
            a2 = a(this.j.f(), z(), i4, layoutParams.width, true);
            i2 = a4;
        }
        a(view, a2, i2, z);
    }

    private void a(float f2, int i) {
        m(Math.max(Math.round(((float) this.b) * f2), i));
    }

    private void a(View view, int i, int i2, boolean z) {
        boolean b2;
        android.support.v7.widget.RecyclerView.LayoutParams layoutParams = (android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            b2 = a(view, i, i2, layoutParams);
        } else {
            b2 = b(view, i, i2, layoutParams);
        }
        if (b2) {
            view.measure(i, i2);
        }
    }

    private void a(o oVar, s sVar, int i, int i2, boolean z) {
        int i3;
        int i4;
        if (z) {
            i4 = 1;
            i3 = 0;
        } else {
            int i5 = i - 1;
            i = -1;
            i3 = i5;
            i4 = -1;
        }
        int i6 = 0;
        for (int i7 = i3; i7 != i; i7 += i4) {
            View view = this.d[i7];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.b = c(oVar, sVar, d(view));
            layoutParams.a = i6;
            i6 += layoutParams.b;
        }
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        if (i != this.b) {
            this.a = true;
            if (i < 1) {
                throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
            }
            this.b = i;
            this.g.invalidateSpanIndexCache();
            r();
        }
    }

    public View a(View view, int i, o oVar, s sVar) {
        int i2;
        int i3;
        int y;
        int i4;
        int min;
        View view2;
        int i5;
        int i6;
        View view3;
        View e2 = e(view);
        if (e2 == null) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) e2.getLayoutParams();
        int i7 = layoutParams.a;
        int i8 = layoutParams.a + layoutParams.b;
        if (super.a(view, i, oVar, sVar) == null) {
            return null;
        }
        if ((f(i) == 1) != this.k) {
            i2 = y() - 1;
            i3 = -1;
            y = -1;
        } else {
            i2 = 0;
            i3 = 1;
            y = y();
        }
        boolean z = this.i == 1 && i();
        View view4 = null;
        int i9 = -1;
        int i10 = 0;
        View view5 = null;
        int i11 = -1;
        int i12 = 0;
        int a2 = a(oVar, sVar, i2);
        int i13 = i2;
        while (i13 != y) {
            int a3 = a(oVar, sVar, i13);
            View i14 = i(i13);
            if (i14 == e2) {
                break;
            }
            if (i14.hasFocusable() && a3 != a2) {
                if (view4 != null) {
                    break;
                }
            } else {
                LayoutParams layoutParams2 = (LayoutParams) i14.getLayoutParams();
                int i15 = layoutParams2.a;
                int i16 = layoutParams2.a + layoutParams2.b;
                if (i14.hasFocusable() && i15 == i7 && i16 == i8) {
                    return i14;
                }
                boolean z2 = false;
                if ((!i14.hasFocusable() || view4 != null) && (i14.hasFocusable() || view5 != null)) {
                    int min2 = Math.min(i16, i8) - Math.max(i15, i7);
                    if (i14.hasFocusable()) {
                        if (min2 > i10) {
                            z2 = true;
                        } else if (min2 == i10) {
                            if (z == (i15 > i9)) {
                                z2 = true;
                            }
                        }
                    } else if (view4 == null && a(i14, false, true)) {
                        if (min2 > i12) {
                            z2 = true;
                        } else if (min2 == i12) {
                            if (z == (i15 > i11)) {
                                z2 = true;
                            }
                        }
                    }
                } else {
                    z2 = true;
                }
                if (z2) {
                    if (i14.hasFocusable()) {
                        int i17 = layoutParams2.a;
                        int i18 = i12;
                        i4 = i11;
                        view2 = view5;
                        i5 = Math.min(i16, i8) - Math.max(i15, i7);
                        min = i18;
                        int i19 = i17;
                        view3 = i14;
                        i6 = i19;
                    } else {
                        i4 = layoutParams2.a;
                        min = Math.min(i16, i8) - Math.max(i15, i7);
                        view2 = i14;
                        i5 = i10;
                        i6 = i9;
                        view3 = view4;
                    }
                    i13 += i3;
                    view4 = view3;
                    i10 = i5;
                    i9 = i6;
                    view5 = view2;
                    i11 = i4;
                    i12 = min;
                }
            }
            min = i12;
            i6 = i9;
            i4 = i11;
            view2 = view5;
            i5 = i10;
            view3 = view4;
            i13 += i3;
            view4 = view3;
            i10 = i5;
            i9 = i6;
            view5 = view2;
            i11 = i4;
            i12 = min;
        }
        if (view4 == null) {
            view4 = view5;
        }
        return view4;
    }

    public boolean c() {
        return this.n == null && !this.a;
    }
}
