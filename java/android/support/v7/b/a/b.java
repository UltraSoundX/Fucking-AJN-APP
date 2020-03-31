package android.support.v7.b.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.SparseArray;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: DrawableContainer */
class b extends Drawable implements Callback {
    private C0014b a;
    private Rect b;
    private Drawable c;
    private Drawable d;
    private int e = 255;
    private boolean f;
    private int g = -1;
    private int h = -1;
    private boolean i;
    private Runnable j;
    private long k;
    private long l;
    private a m;

    /* compiled from: DrawableContainer */
    static class a implements Callback {
        private Callback a;

        a() {
        }

        public a a(Callback callback) {
            this.a = callback;
            return this;
        }

        public Callback a() {
            Callback callback = this.a;
            this.a = null;
            return callback;
        }

        public void invalidateDrawable(Drawable drawable) {
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            if (this.a != null) {
                this.a.scheduleDrawable(drawable, runnable, j);
            }
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            if (this.a != null) {
                this.a.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    /* renamed from: android.support.v7.b.a.b$b reason: collision with other inner class name */
    /* compiled from: DrawableContainer */
    static abstract class C0014b extends ConstantState {
        boolean A;
        int B;
        int C = 0;
        int D = 0;
        boolean E;
        ColorFilter F;
        boolean G;
        ColorStateList H;
        Mode I;
        boolean J;
        boolean K;
        final b c;
        Resources d;
        int e = ErrorCode.STARTDOWNLOAD_1;
        int f;
        int g;
        SparseArray<ConstantState> h;
        Drawable[] i;
        int j;
        boolean k = false;
        boolean l;
        Rect m;
        boolean n = false;
        boolean o;
        int p;

        /* renamed from: q reason: collision with root package name */
        int f340q;
        int r;
        int s;
        boolean t;
        int u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        boolean z = true;

        C0014b(C0014b bVar, b bVar2, Resources resources) {
            int i2;
            this.c = bVar2;
            Resources resources2 = resources != null ? resources : bVar != null ? bVar.d : null;
            this.d = resources2;
            if (bVar != null) {
                i2 = bVar.e;
            } else {
                i2 = 0;
            }
            this.e = b.a(resources, i2);
            if (bVar != null) {
                this.f = bVar.f;
                this.g = bVar.g;
                this.x = true;
                this.y = true;
                this.k = bVar.k;
                this.n = bVar.n;
                this.z = bVar.z;
                this.A = bVar.A;
                this.B = bVar.B;
                this.C = bVar.C;
                this.D = bVar.D;
                this.E = bVar.E;
                this.F = bVar.F;
                this.G = bVar.G;
                this.H = bVar.H;
                this.I = bVar.I;
                this.J = bVar.J;
                this.K = bVar.K;
                if (bVar.e == this.e) {
                    if (bVar.l) {
                        this.m = new Rect(bVar.m);
                        this.l = true;
                    }
                    if (bVar.o) {
                        this.p = bVar.p;
                        this.f340q = bVar.f340q;
                        this.r = bVar.r;
                        this.s = bVar.s;
                        this.o = true;
                    }
                }
                if (bVar.t) {
                    this.u = bVar.u;
                    this.t = true;
                }
                if (bVar.v) {
                    this.w = bVar.w;
                    this.v = true;
                }
                Drawable[] drawableArr = bVar.i;
                this.i = new Drawable[drawableArr.length];
                this.j = bVar.j;
                SparseArray<ConstantState> sparseArray = bVar.h;
                if (sparseArray != null) {
                    this.h = sparseArray.clone();
                } else {
                    this.h = new SparseArray<>(this.j);
                }
                int i3 = this.j;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (drawableArr[i4] != null) {
                        ConstantState constantState = drawableArr[i4].getConstantState();
                        if (constantState != null) {
                            this.h.put(i4, constantState);
                        } else {
                            this.i[i4] = drawableArr[i4];
                        }
                    }
                }
                return;
            }
            this.i = new Drawable[10];
            this.j = 0;
        }

        public int getChangingConfigurations() {
            return this.f | this.g;
        }

        public final int a(Drawable drawable) {
            int i2 = this.j;
            if (i2 >= this.i.length) {
                e(i2, i2 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.c);
            this.i[i2] = drawable;
            this.j++;
            this.g |= drawable.getChangingConfigurations();
            b();
            this.m = null;
            this.l = false;
            this.o = false;
            this.x = false;
            return i2;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.t = false;
            this.v = false;
        }

        /* access modifiers changed from: 0000 */
        public final int c() {
            return this.i.length;
        }

        private void o() {
            if (this.h != null) {
                int size = this.h.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.i[this.h.keyAt(i2)] = b(((ConstantState) this.h.valueAt(i2)).newDrawable(this.d));
                }
                this.h = null;
            }
        }

        private Drawable b(Drawable drawable) {
            if (VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.B);
            }
            Drawable mutate = drawable.mutate();
            mutate.setCallback(this.c);
            return mutate;
        }

        public final int d() {
            return this.j;
        }

        public final Drawable b(int i2) {
            Drawable drawable = this.i[i2];
            if (drawable != null) {
                return drawable;
            }
            if (this.h != null) {
                int indexOfKey = this.h.indexOfKey(i2);
                if (indexOfKey >= 0) {
                    Drawable b = b(((ConstantState) this.h.valueAt(indexOfKey)).newDrawable(this.d));
                    this.i[i2] = b;
                    this.h.removeAt(indexOfKey);
                    if (this.h.size() != 0) {
                        return b;
                    }
                    this.h = null;
                    return b;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public final boolean d(int i2, int i3) {
            boolean z2;
            int i4 = this.j;
            Drawable[] drawableArr = this.i;
            int i5 = 0;
            boolean z3 = false;
            while (i5 < i4) {
                if (drawableArr[i5] != null) {
                    if (VERSION.SDK_INT >= 23) {
                        z2 = drawableArr[i5].setLayoutDirection(i2);
                    } else {
                        z2 = false;
                    }
                    if (i5 == i3) {
                        i5++;
                        z3 = z2;
                    }
                }
                z2 = z3;
                i5++;
                z3 = z2;
            }
            this.B = i2;
            return z3;
        }

        /* access modifiers changed from: 0000 */
        public final void a(Resources resources) {
            if (resources != null) {
                this.d = resources;
                int a = b.a(resources, this.e);
                int i2 = this.e;
                this.e = a;
                if (i2 != a) {
                    this.o = false;
                    this.l = false;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public final void a(Theme theme) {
            if (theme != null) {
                o();
                int i2 = this.j;
                Drawable[] drawableArr = this.i;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3] != null && drawableArr[i3].canApplyTheme()) {
                        drawableArr[i3].applyTheme(theme);
                        this.g |= drawableArr[i3].getChangingConfigurations();
                    }
                }
                a(theme.getResources());
            }
        }

        public boolean canApplyTheme() {
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable == null) {
                    ConstantState constantState = (ConstantState) this.h.get(i3);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3] != null) {
                    drawableArr[i3].mutate();
                }
            }
            this.A = true;
        }

        public final void a(boolean z2) {
            this.k = z2;
        }

        public final Rect e() {
            Rect rect = null;
            if (this.k) {
                return null;
            }
            if (this.m != null || this.l) {
                return this.m;
            }
            o();
            Rect rect2 = new Rect();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getPadding(rect2)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    if (rect2.left > rect.left) {
                        rect.left = rect2.left;
                    }
                    if (rect2.top > rect.top) {
                        rect.top = rect2.top;
                    }
                    if (rect2.right > rect.right) {
                        rect.right = rect2.right;
                    }
                    if (rect2.bottom > rect.bottom) {
                        rect.bottom = rect2.bottom;
                    }
                }
            }
            this.l = true;
            this.m = rect;
            return rect;
        }

        public final void b(boolean z2) {
            this.n = z2;
        }

        public final boolean f() {
            return this.n;
        }

        public final int g() {
            if (!this.o) {
                k();
            }
            return this.p;
        }

        public final int h() {
            if (!this.o) {
                k();
            }
            return this.f340q;
        }

        public final int i() {
            if (!this.o) {
                k();
            }
            return this.r;
        }

        public final int j() {
            if (!this.o) {
                k();
            }
            return this.s;
        }

        /* access modifiers changed from: protected */
        public void k() {
            this.o = true;
            o();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            this.f340q = -1;
            this.p = -1;
            this.s = 0;
            this.r = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.p) {
                    this.p = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.f340q) {
                    this.f340q = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.r) {
                    this.r = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.s) {
                    this.s = minimumHeight;
                }
            }
        }

        public final void c(int i2) {
            this.C = i2;
        }

        public final void d(int i2) {
            this.D = i2;
        }

        public final int l() {
            if (this.t) {
                return this.u;
            }
            o();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            int i3 = i2 > 0 ? drawableArr[0].getOpacity() : -2;
            int i4 = 1;
            while (i4 < i2) {
                int resolveOpacity = Drawable.resolveOpacity(i3, drawableArr[i4].getOpacity());
                i4++;
                i3 = resolveOpacity;
            }
            this.u = i3;
            this.t = true;
            return i3;
        }

        public final boolean m() {
            boolean z2 = false;
            if (this.v) {
                return this.w;
            }
            o();
            int i2 = this.j;
            Drawable[] drawableArr = this.i;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                } else if (drawableArr[i3].isStateful()) {
                    z2 = true;
                    break;
                } else {
                    i3++;
                }
            }
            this.w = z2;
            this.v = true;
            return z2;
        }

        public void e(int i2, int i3) {
            Drawable[] drawableArr = new Drawable[i3];
            System.arraycopy(this.i, 0, drawableArr, 0, i2);
            this.i = drawableArr;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            r6.y = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x002d, code lost:
            r0 = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean n() {
            /*
                r6 = this;
                r1 = 1
                r0 = 0
                monitor-enter(r6)
                boolean r2 = r6.x     // Catch:{ all -> 0x0024 }
                if (r2 == 0) goto L_0x000b
                boolean r0 = r6.y     // Catch:{ all -> 0x0024 }
            L_0x0009:
                monitor-exit(r6)
                return r0
            L_0x000b:
                r6.o()     // Catch:{ all -> 0x0024 }
                r2 = 1
                r6.x = r2     // Catch:{ all -> 0x0024 }
                int r3 = r6.j     // Catch:{ all -> 0x0024 }
                android.graphics.drawable.Drawable[] r4 = r6.i     // Catch:{ all -> 0x0024 }
                r2 = r0
            L_0x0016:
                if (r2 >= r3) goto L_0x002a
                r5 = r4[r2]     // Catch:{ all -> 0x0024 }
                android.graphics.drawable.Drawable$ConstantState r5 = r5.getConstantState()     // Catch:{ all -> 0x0024 }
                if (r5 != 0) goto L_0x0027
                r1 = 0
                r6.y = r1     // Catch:{ all -> 0x0024 }
                goto L_0x0009
            L_0x0024:
                r0 = move-exception
                monitor-exit(r6)
                throw r0
            L_0x0027:
                int r2 = r2 + 1
                goto L_0x0016
            L_0x002a:
                r0 = 1
                r6.y = r0     // Catch:{ all -> 0x0024 }
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.b.a.b.C0014b.n():boolean");
        }
    }

    b() {
    }

    public void draw(Canvas canvas) {
        if (this.c != null) {
            this.c.draw(canvas);
        }
        if (this.d != null) {
            this.d.draw(canvas);
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.a.getChangingConfigurations();
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(23)
    private boolean a() {
        return isAutoMirrored() && getLayoutDirection() == 1;
    }

    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect e2 = this.a.e();
        if (e2 != null) {
            rect.set(e2);
            padding = (e2.right | ((e2.left | e2.top) | e2.bottom)) != 0;
        } else {
            padding = this.c != null ? this.c.getPadding(rect) : super.getPadding(rect);
        }
        if (a()) {
            int i2 = rect.left;
            rect.left = rect.right;
            rect.right = i2;
        }
        return padding;
    }

    public void getOutline(Outline outline) {
        if (this.c != null) {
            this.c.getOutline(outline);
        }
    }

    public void setAlpha(int i2) {
        if (!this.f || this.e != i2) {
            this.f = true;
            this.e = i2;
            if (this.c == null) {
                return;
            }
            if (this.k == 0) {
                this.c.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    public int getAlpha() {
        return this.e;
    }

    public void setDither(boolean z) {
        if (this.a.z != z) {
            this.a.z = z;
            if (this.c != null) {
                this.c.setDither(this.a.z);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.G = true;
        if (this.a.F != colorFilter) {
            this.a.F = colorFilter;
            if (this.c != null) {
                this.c.setColorFilter(colorFilter);
            }
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        this.a.J = true;
        if (this.a.H != colorStateList) {
            this.a.H = colorStateList;
            DrawableCompat.setTintList(this.c, colorStateList);
        }
    }

    public void setTintMode(Mode mode) {
        this.a.K = true;
        if (this.a.I != mode) {
            this.a.I = mode;
            DrawableCompat.setTintMode(this.c, mode);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (this.d != null) {
            this.d.setBounds(rect);
        }
        if (this.c != null) {
            this.c.setBounds(rect);
        }
    }

    public boolean isStateful() {
        return this.a.m();
    }

    public void setAutoMirrored(boolean z) {
        if (this.a.E != z) {
            this.a.E = z;
            if (this.c != null) {
                DrawableCompat.setAutoMirrored(this.c, this.a.E);
            }
        }
    }

    public boolean isAutoMirrored() {
        return this.a.E;
    }

    public void jumpToCurrentState() {
        boolean z = true;
        boolean z2 = false;
        if (this.d != null) {
            this.d.jumpToCurrentState();
            this.d = null;
            this.h = -1;
            z2 = true;
        }
        if (this.c != null) {
            this.c.jumpToCurrentState();
            if (this.f) {
                this.c.setAlpha(this.e);
            }
        }
        if (this.l != 0) {
            this.l = 0;
            z2 = true;
        }
        if (this.k != 0) {
            this.k = 0;
        } else {
            z = z2;
        }
        if (z) {
            invalidateSelf();
        }
    }

    public void setHotspot(float f2, float f3) {
        if (this.c != null) {
            DrawableCompat.setHotspot(this.c, f2, f3);
        }
    }

    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        if (this.b == null) {
            this.b = new Rect(i2, i3, i4, i5);
        } else {
            this.b.set(i2, i3, i4, i5);
        }
        if (this.c != null) {
            DrawableCompat.setHotspotBounds(this.c, i2, i3, i4, i5);
        }
    }

    public void getHotspotBounds(Rect rect) {
        if (this.b != null) {
            rect.set(this.b);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.d != null) {
            return this.d.setState(iArr);
        }
        if (this.c != null) {
            return this.c.setState(iArr);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i2) {
        if (this.d != null) {
            return this.d.setLevel(i2);
        }
        if (this.c != null) {
            return this.c.setLevel(i2);
        }
        return false;
    }

    public boolean onLayoutDirectionChanged(int i2) {
        return this.a.d(i2, d());
    }

    public int getIntrinsicWidth() {
        if (this.a.f()) {
            return this.a.g();
        }
        if (this.c != null) {
            return this.c.getIntrinsicWidth();
        }
        return -1;
    }

    public int getIntrinsicHeight() {
        if (this.a.f()) {
            return this.a.h();
        }
        if (this.c != null) {
            return this.c.getIntrinsicHeight();
        }
        return -1;
    }

    public int getMinimumWidth() {
        if (this.a.f()) {
            return this.a.i();
        }
        if (this.c != null) {
            return this.c.getMinimumWidth();
        }
        return 0;
    }

    public int getMinimumHeight() {
        if (this.a.f()) {
            return this.a.j();
        }
        if (this.c != null) {
            return this.c.getMinimumHeight();
        }
        return 0;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.a != null) {
            this.a.b();
        }
        if (drawable == this.c && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        if (drawable == this.c && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j2);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.c && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.d != null) {
            this.d.setVisible(z, z2);
        }
        if (this.c != null) {
            this.c.setVisible(z, z2);
        }
        return visible;
    }

    public int getOpacity() {
        if (this.c == null || !this.c.isVisible()) {
            return -2;
        }
        return this.a.l();
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2) {
        if (i2 == this.g) {
            return false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.a.D > 0) {
            if (this.d != null) {
                this.d.setVisible(false, false);
            }
            if (this.c != null) {
                this.d = this.c;
                this.h = this.g;
                this.l = ((long) this.a.D) + uptimeMillis;
            } else {
                this.d = null;
                this.h = -1;
                this.l = 0;
            }
        } else if (this.c != null) {
            this.c.setVisible(false, false);
        }
        if (i2 < 0 || i2 >= this.a.j) {
            this.c = null;
            this.g = -1;
        } else {
            Drawable b2 = this.a.b(i2);
            this.c = b2;
            this.g = i2;
            if (b2 != null) {
                if (this.a.C > 0) {
                    this.k = uptimeMillis + ((long) this.a.C);
                }
                a(b2);
            }
        }
        if (!(this.k == 0 && this.l == 0)) {
            if (this.j == null) {
                this.j = new Runnable() {
                    public void run() {
                        b.this.a(true);
                        b.this.invalidateSelf();
                    }
                };
            } else {
                unscheduleSelf(this.j);
            }
            a(true);
        }
        invalidateSelf();
        return true;
    }

    private void a(Drawable drawable) {
        if (this.m == null) {
            this.m = new a();
        }
        drawable.setCallback(this.m.a(drawable.getCallback()));
        try {
            if (this.a.C <= 0 && this.f) {
                drawable.setAlpha(this.e);
            }
            if (this.a.G) {
                drawable.setColorFilter(this.a.F);
            } else {
                if (this.a.J) {
                    DrawableCompat.setTintList(drawable, this.a.H);
                }
                if (this.a.K) {
                    DrawableCompat.setTintMode(drawable, this.a.I);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.a.z);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.a.E);
            }
            Rect rect = this.b;
            if (VERSION.SDK_INT >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.m.a());
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        boolean z2;
        this.f = true;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.c != null) {
            if (this.k != 0) {
                if (this.k <= uptimeMillis) {
                    this.c.setAlpha(this.e);
                    this.k = 0;
                    z2 = false;
                } else {
                    this.c.setAlpha(((255 - (((int) ((this.k - uptimeMillis) * 255)) / this.a.C)) * this.e) / 255);
                    z2 = true;
                }
            }
            z2 = false;
        } else {
            this.k = 0;
            z2 = false;
        }
        if (this.d == null) {
            this.l = 0;
        } else if (this.l != 0) {
            if (this.l <= uptimeMillis) {
                this.d.setVisible(false, false);
                this.d = null;
                this.h = -1;
                this.l = 0;
            } else {
                this.d.setAlpha(((((int) ((this.l - uptimeMillis) * 255)) / this.a.D) * this.e) / 255);
                z2 = true;
            }
        }
        if (z && z2) {
            scheduleSelf(this.j, 16 + uptimeMillis);
        }
    }

    public Drawable getCurrent() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final void a(Resources resources) {
        this.a.a(resources);
    }

    public void applyTheme(Theme theme) {
        this.a.a(theme);
    }

    public boolean canApplyTheme() {
        return this.a.canApplyTheme();
    }

    public final ConstantState getConstantState() {
        if (!this.a.n()) {
            return null;
        }
        this.a.f = getChangingConfigurations();
        return this.a;
    }

    public Drawable mutate() {
        if (!this.i && super.mutate() == this) {
            C0014b c2 = c();
            c2.a();
            a(c2);
            this.i = true;
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public C0014b c() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void a(C0014b bVar) {
        this.a = bVar;
        if (this.g >= 0) {
            this.c = bVar.b(this.g);
            if (this.c != null) {
                a(this.c);
            }
        }
        this.h = -1;
        this.d = null;
    }

    static int a(Resources resources, int i2) {
        int i3 = resources == null ? i2 : resources.getDisplayMetrics().densityDpi;
        return i3 == 0 ? ErrorCode.STARTDOWNLOAD_1 : i3;
    }
}
