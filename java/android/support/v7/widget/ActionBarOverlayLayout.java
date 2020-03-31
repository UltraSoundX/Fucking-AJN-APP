package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.Window.Callback;
import android.widget.OverScroller;

public class ActionBarOverlayLayout extends ViewGroup implements NestedScrollingParent, s {
    static final int[] e = {R.attr.actionBarSize, 16842841};
    private final Runnable A;
    private final NestedScrollingParentHelper B;
    ActionBarContainer a;
    boolean b;
    ViewPropertyAnimator c;
    final AnimatorListenerAdapter d;
    private int f;
    private int g;
    private ContentFrameLayout h;
    private t i;
    private Drawable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private final Rect f349q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;
    private final Rect w;
    private a x;
    private OverScroller y;
    private final Runnable z;

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public interface a {
        void b(int i);

        void g(boolean z);

        void j();

        void k();

        void l();

        void m();
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 0;
        this.f349q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        this.w = new Rect();
        this.d = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ActionBarOverlayLayout.this.c = null;
                ActionBarOverlayLayout.this.b = false;
            }

            public void onAnimationCancel(Animator animator) {
                ActionBarOverlayLayout.this.c = null;
                ActionBarOverlayLayout.this.b = false;
            }
        };
        this.z = new Runnable() {
            public void run() {
                ActionBarOverlayLayout.this.d();
                ActionBarOverlayLayout.this.c = ActionBarOverlayLayout.this.a.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.d);
            }
        };
        this.A = new Runnable() {
            public void run() {
                ActionBarOverlayLayout.this.d();
                ActionBarOverlayLayout.this.c = ActionBarOverlayLayout.this.a.animate().translationY((float) (-ActionBarOverlayLayout.this.a.getHeight())).setListener(ActionBarOverlayLayout.this.d);
            }
        };
        a(context);
        this.B = new NestedScrollingParentHelper(this);
    }

    private void a(Context context) {
        boolean z2 = true;
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(e);
        this.f = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.j = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.j == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion >= 19) {
            z2 = false;
        }
        this.k = z2;
        this.y = new OverScroller(context);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.x = aVar;
        if (getWindowToken() != null) {
            this.x.b(this.g);
            if (this.p != 0) {
                onWindowSystemUiVisibilityChanged(this.p);
                ViewCompat.requestApplyInsets(this);
            }
        }
    }

    public void setOverlayMode(boolean z2) {
        this.l = z2;
        this.k = z2 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public boolean a() {
        return this.l;
    }

    public void setHasNonEmbeddedTabs(boolean z2) {
        this.m = z2;
    }

    public void setShowingForActionMode(boolean z2) {
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    public void onWindowSystemUiVisibilityChanged(int i2) {
        boolean z2;
        boolean z3 = true;
        if (VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i2);
        }
        c();
        int i3 = this.p ^ i2;
        this.p = i2;
        boolean z4 = (i2 & 4) == 0;
        if ((i2 & 256) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.x != null) {
            a aVar = this.x;
            if (z2) {
                z3 = false;
            }
            aVar.g(z3);
            if (z4 || !z2) {
                this.x.j();
            } else {
                this.x.k();
            }
        }
        if ((i3 & 256) != 0 && this.x != null) {
            ViewCompat.requestApplyInsets(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.g = i2;
        if (this.x != null) {
            this.x.b(i2);
        }
    }

    private boolean a(View view, Rect rect, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (z2 && layoutParams.leftMargin != rect.left) {
            layoutParams.leftMargin = rect.left;
            z6 = true;
        }
        if (z3 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z6 = true;
        }
        if (z5 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z6 = true;
        }
        if (!z4 || layoutParams.bottomMargin == rect.bottom) {
            return z6;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        c();
        if ((ViewCompat.getWindowSystemUiVisibility(this) & 256) != 0) {
        }
        boolean a2 = a(this.a, rect, true, true, false, true);
        this.t.set(rect);
        bb.a(this, this.t, this.f349q);
        if (!this.u.equals(this.t)) {
            this.u.set(this.t);
            a2 = true;
        }
        if (!this.r.equals(this.f349q)) {
            this.r.set(this.f349q);
            a2 = true;
        }
        if (a2) {
            requestLayout();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        boolean z2;
        int i4;
        c();
        measureChildWithMargins(this.a, i2, 0, i3, 0);
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        int max = Math.max(0, this.a.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
        int max2 = Math.max(0, layoutParams.bottomMargin + this.a.getMeasuredHeight() + layoutParams.topMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.a.getMeasuredState());
        if ((ViewCompat.getWindowSystemUiVisibility(this) & 256) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            i4 = this.f;
            if (this.m && this.a.getTabContainer() != null) {
                i4 += this.f;
            }
        } else {
            i4 = this.a.getVisibility() != 8 ? this.a.getMeasuredHeight() : 0;
        }
        this.s.set(this.f349q);
        this.v.set(this.t);
        if (this.l || z2) {
            Rect rect = this.v;
            rect.top = i4 + rect.top;
            this.v.bottom += 0;
        } else {
            Rect rect2 = this.s;
            rect2.top = i4 + rect2.top;
            this.s.bottom += 0;
        }
        a(this.h, this.s, true, true, true, true);
        if (!this.w.equals(this.v)) {
            this.w.set(this.v);
            this.h.a(this.v);
        }
        measureChildWithMargins(this.h, i2, 0, i3, 0);
        LayoutParams layoutParams2 = (LayoutParams) this.h.getLayoutParams();
        int max3 = Math.max(max, this.h.getMeasuredWidth() + layoutParams2.leftMargin + layoutParams2.rightMargin);
        int max4 = Math.max(max2, layoutParams2.bottomMargin + this.h.getMeasuredHeight() + layoutParams2.topMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.h.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, combineMeasuredStates2 << 16));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i4 - i2) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = (i5 - i3) - getPaddingBottom();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i7 = layoutParams.leftMargin + paddingLeft;
                int i8 = layoutParams.topMargin + paddingTop;
                childAt.layout(i7, i8, childAt.getMeasuredWidth() + i7, childAt.getMeasuredHeight() + i8);
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.j != null && !this.k) {
            int i2 = this.a.getVisibility() == 0 ? (int) (((float) this.a.getBottom()) + this.a.getTranslationY() + 0.5f) : 0;
            this.j.setBounds(0, i2, getWidth(), this.j.getIntrinsicHeight() + i2);
            this.j.draw(canvas);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.a.getVisibility() != 0) {
            return false;
        }
        return this.n;
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.B.onNestedScrollAccepted(view, view2, i2);
        this.o = getActionBarHideOffset();
        d();
        if (this.x != null) {
            this.x.l();
        }
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        this.o += i3;
        setActionBarHideOffset(this.o);
    }

    public void onStopNestedScroll(View view) {
        if (this.n && !this.b) {
            if (this.o <= this.a.getHeight()) {
                l();
            } else {
                m();
            }
        }
        if (this.x != null) {
            this.x.m();
        }
    }

    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (!this.n || !z2) {
            return false;
        }
        if (a(f2, f3)) {
            o();
        } else {
            n();
        }
        this.b = true;
        return true;
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    public int getNestedScrollAxes() {
        return this.B.getNestedScrollAxes();
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.h == null) {
            this.h = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.a = (ActionBarContainer) findViewById(R.id.action_bar_container);
            this.i = a(findViewById(R.id.action_bar));
        }
    }

    private t a(View view) {
        if (view instanceof t) {
            return (t) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void setHideOnContentScrollEnabled(boolean z2) {
        if (z2 != this.n) {
            this.n = z2;
            if (!z2) {
                d();
                setActionBarHideOffset(0);
            }
        }
    }

    public int getActionBarHideOffset() {
        if (this.a != null) {
            return -((int) this.a.getTranslationY());
        }
        return 0;
    }

    public void setActionBarHideOffset(int i2) {
        d();
        this.a.setTranslationY((float) (-Math.max(0, Math.min(i2, this.a.getHeight()))));
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        removeCallbacks(this.z);
        removeCallbacks(this.A);
        if (this.c != null) {
            this.c.cancel();
        }
    }

    private void l() {
        d();
        postDelayed(this.z, 600);
    }

    private void m() {
        d();
        postDelayed(this.A, 600);
    }

    private void n() {
        d();
        this.z.run();
    }

    private void o() {
        d();
        this.A.run();
    }

    private boolean a(float f2, float f3) {
        this.y.fling(0, 0, 0, (int) f3, 0, 0, ExploreByTouchHelper.INVALID_ID, Integer.MAX_VALUE);
        if (this.y.getFinalY() > this.a.getHeight()) {
            return true;
        }
        return false;
    }

    public void setWindowCallback(Callback callback) {
        c();
        this.i.a(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        c();
        this.i.a(charSequence);
    }

    public CharSequence getTitle() {
        c();
        return this.i.e();
    }

    public void a(int i2) {
        c();
        switch (i2) {
            case 2:
                this.i.f();
                return;
            case 5:
                this.i.g();
                return;
            case 109:
                setOverlayMode(true);
                return;
            default:
                return;
        }
    }

    public void setUiOptions(int i2) {
    }

    public void setIcon(int i2) {
        c();
        this.i.a(i2);
    }

    public void setIcon(Drawable drawable) {
        c();
        this.i.a(drawable);
    }

    public void setLogo(int i2) {
        c();
        this.i.b(i2);
    }

    public boolean e() {
        c();
        return this.i.h();
    }

    public boolean f() {
        c();
        return this.i.i();
    }

    public boolean g() {
        c();
        return this.i.j();
    }

    public boolean h() {
        c();
        return this.i.k();
    }

    public boolean i() {
        c();
        return this.i.l();
    }

    public void j() {
        c();
        this.i.m();
    }

    public void a(Menu menu, android.support.v7.view.menu.l.a aVar) {
        c();
        this.i.a(menu, aVar);
    }

    public void k() {
        c();
        this.i.n();
    }
}
