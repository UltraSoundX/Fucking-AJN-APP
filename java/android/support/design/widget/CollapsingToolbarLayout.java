package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.design.internal.c;
import android.support.design.widget.AppBarLayout.b;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;

public class CollapsingToolbarLayout extends FrameLayout {
    final d a;
    Drawable b;
    int c;
    WindowInsetsCompat d;
    private boolean e;
    private int f;
    private Toolbar g;
    private View h;
    private View i;
    private int j;
    private int k;
    private int l;
    private int m;
    private final Rect n;
    private boolean o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private Drawable f322q;
    private int r;
    private boolean s;
    private ValueAnimator t;
    private long u;
    private int v;
    private b w;

    public static class LayoutParams extends android.widget.FrameLayout.LayoutParams {
        int a = 0;
        float b = 0.5f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingToolbarLayout_Layout);
            this.a = obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(obtainStyledAttributes.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public void a(int i) {
            this.a = i;
        }

        public void a(float f) {
            this.b = f;
        }
    }

    private class a implements b {
        a() {
        }

        public void a(AppBarLayout appBarLayout, int i) {
            int i2;
            CollapsingToolbarLayout.this.c = i;
            if (CollapsingToolbarLayout.this.d != null) {
                i2 = CollapsingToolbarLayout.this.d.getSystemWindowInsetTop();
            } else {
                i2 = 0;
            }
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                p a2 = CollapsingToolbarLayout.a(childAt);
                switch (layoutParams.a) {
                    case 1:
                        a2.a(MathUtils.clamp(-i, 0, CollapsingToolbarLayout.this.b(childAt)));
                        break;
                    case 2:
                        a2.a(Math.round(layoutParams.b * ((float) (-i))));
                        break;
                }
            }
            CollapsingToolbarLayout.this.b();
            if (CollapsingToolbarLayout.this.b != null && i2 > 0) {
                ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
            }
            CollapsingToolbarLayout.this.a.b(((float) Math.abs(i)) / ((float) ((CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this)) - i2)));
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = true;
        this.n = new Rect();
        this.v = -1;
        this.a = new d(this);
        this.a.a(android.support.design.a.a.e);
        TypedArray a2 = c.a(context, attributeSet, R.styleable.CollapsingToolbarLayout, i2, R.style.Widget_Design_CollapsingToolbar, new int[0]);
        this.a.a(a2.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        this.a.b(a2.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = a2.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.m = dimensionPixelSize;
        this.l = dimensionPixelSize;
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        if (a2.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.j = a2.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (a2.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.l = a2.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (a2.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.k = a2.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (a2.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.m = a2.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.o = a2.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(a2.getText(R.styleable.CollapsingToolbarLayout_title));
        this.a.d(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        this.a.c(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (a2.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            this.a.d(a2.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (a2.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            this.a.c(a2.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        this.v = a2.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        this.u = (long) a2.getInt(R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(a2.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(a2.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
        this.f = a2.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        a2.recycle();
        setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return CollapsingToolbarLayout.this.a(windowInsetsCompat);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows((View) parent));
            if (this.w == null) {
                this.w = new a();
            }
            ((AppBarLayout) parent).a(this.w);
            ViewCompat.requestApplyInsets(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        if (this.w != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).b(this.w);
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: 0000 */
    public WindowInsetsCompat a(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = null;
        if (ViewCompat.getFitsSystemWindows(this)) {
            windowInsetsCompat2 = windowInsetsCompat;
        }
        if (!ObjectsCompat.equals(this.d, windowInsetsCompat2)) {
            this.d = windowInsetsCompat2;
            requestLayout();
        }
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        c();
        if (this.g == null && this.f322q != null && this.r > 0) {
            this.f322q.mutate().setAlpha(this.r);
            this.f322q.draw(canvas);
        }
        if (this.o && this.p) {
            this.a.a(canvas);
        }
        if (this.b != null && this.r > 0) {
            int i2 = this.d != null ? this.d.getSystemWindowInsetTop() : 0;
            if (i2 > 0) {
                this.b.setBounds(0, -this.c, getWidth(), i2 - this.c);
                this.b.mutate().setAlpha(this.r);
                this.b.draw(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        boolean z;
        if (this.f322q == null || this.r <= 0 || !c(view)) {
            z = false;
        } else {
            this.f322q.mutate().setAlpha(this.r);
            this.f322q.draw(canvas);
            z = true;
        }
        if (super.drawChild(canvas, view, j2) || z) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.f322q != null) {
            this.f322q.setBounds(0, 0, i2, i3);
        }
    }

    private void c() {
        Toolbar toolbar;
        if (this.e) {
            this.g = null;
            this.h = null;
            if (this.f != -1) {
                this.g = (Toolbar) findViewById(this.f);
                if (this.g != null) {
                    this.h = d(this.g);
                }
            }
            if (this.g == null) {
                int childCount = getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        toolbar = null;
                        break;
                    }
                    View childAt = getChildAt(i2);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                    i2++;
                }
                this.g = toolbar;
            }
            d();
            this.e = false;
        }
    }

    private boolean c(View view) {
        return (this.h == null || this.h == this) ? view == this.g : view == this.h;
    }

    private View d(View view) {
        ViewParent parent = view.getParent();
        View view2 = view;
        while (parent != this && parent != null) {
            if (parent instanceof View) {
                view2 = (View) parent;
            }
            parent = parent.getParent();
        }
        return view2;
    }

    private void d() {
        if (!this.o && this.i != null) {
            ViewParent parent = this.i.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.i);
            }
        }
        if (this.o && this.g != null) {
            if (this.i == null) {
                this.i = new View(getContext());
            }
            if (this.i.getParent() == null) {
                this.g.addView(this.i, -1, -1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        c();
        super.onMeasure(i2, i3);
        int mode = MeasureSpec.getMode(i3);
        int i4 = this.d != null ? this.d.getSystemWindowInsetTop() : 0;
        if (mode == 0 && i4 > 0) {
            super.onMeasure(i2, MeasureSpec.makeMeasureSpec(i4 + getMeasuredHeight(), 1073741824));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        boolean z2 = true;
        super.onLayout(z, i2, i3, i4, i5);
        if (this.d != null) {
            int systemWindowInsetTop = this.d.getSystemWindowInsetTop();
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                if (!ViewCompat.getFitsSystemWindows(childAt) && childAt.getTop() < systemWindowInsetTop) {
                    ViewCompat.offsetTopAndBottom(childAt, systemWindowInsetTop);
                }
            }
        }
        if (this.o && this.i != null) {
            this.p = ViewCompat.isAttachedToWindow(this.i) && this.i.getVisibility() == 0;
            if (this.p) {
                if (ViewCompat.getLayoutDirection(this) != 1) {
                    z2 = false;
                }
                int b2 = b(this.h != null ? this.h : this.g);
                f.b(this, this.i, this.n);
                this.a.b(this.n.left + (z2 ? this.g.getTitleMarginEnd() : this.g.getTitleMarginStart()), this.g.getTitleMarginTop() + this.n.top + b2, (z2 ? this.g.getTitleMarginStart() : this.g.getTitleMarginEnd()) + this.n.right, (b2 + this.n.bottom) - this.g.getTitleMarginBottom());
                d dVar = this.a;
                int i8 = z2 ? this.l : this.j;
                int i9 = this.n.top + this.k;
                int i10 = i4 - i2;
                if (z2) {
                    i6 = this.j;
                } else {
                    i6 = this.l;
                }
                dVar.a(i8, i9, i10 - i6, (i5 - i3) - this.m);
                this.a.k();
            }
        }
        int childCount2 = getChildCount();
        for (int i11 = 0; i11 < childCount2; i11++) {
            a(getChildAt(i11)).a();
        }
        if (this.g != null) {
            if (this.o && TextUtils.isEmpty(this.a.l())) {
                setTitle(this.g.getTitle());
            }
            if (this.h == null || this.h == this) {
                setMinimumHeight(e(this.g));
            } else {
                setMinimumHeight(e(this.h));
            }
        }
        b();
    }

    private static int e(View view) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            return view.getHeight();
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        return marginLayoutParams.bottomMargin + view.getHeight() + marginLayoutParams.topMargin;
    }

    static p a(View view) {
        p pVar = (p) view.getTag(R.id.view_offset_helper);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p(view);
        view.setTag(R.id.view_offset_helper, pVar2);
        return pVar2;
    }

    public void setTitle(CharSequence charSequence) {
        this.a.a(charSequence);
        e();
    }

    public CharSequence getTitle() {
        if (this.o) {
            return this.a.l();
        }
        return null;
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.o) {
            this.o = z;
            e();
            d();
            requestLayout();
        }
    }

    public void setScrimsShown(boolean z) {
        a(z, ViewCompat.isLaidOut(this) && !isInEditMode());
    }

    public void a(boolean z, boolean z2) {
        int i2 = 255;
        if (this.s != z) {
            if (z2) {
                if (!z) {
                    i2 = 0;
                }
                a(i2);
            } else {
                if (!z) {
                    i2 = 0;
                }
                setScrimAlpha(i2);
            }
            this.s = z;
        }
    }

    private void a(int i2) {
        c();
        if (this.t == null) {
            this.t = new ValueAnimator();
            this.t.setDuration(this.u);
            this.t.setInterpolator(i2 > this.r ? android.support.design.a.a.c : android.support.design.a.a.d);
            this.t.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
        } else if (this.t.isRunning()) {
            this.t.cancel();
        }
        this.t.setIntValues(new int[]{this.r, i2});
        this.t.start();
    }

    /* access modifiers changed from: 0000 */
    public void setScrimAlpha(int i2) {
        if (i2 != this.r) {
            if (!(this.f322q == null || this.g == null)) {
                ViewCompat.postInvalidateOnAnimation(this.g);
            }
            this.r = i2;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getScrimAlpha() {
        return this.r;
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = null;
        if (this.f322q != drawable) {
            if (this.f322q != null) {
                this.f322q.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.f322q = drawable2;
            if (this.f322q != null) {
                this.f322q.setBounds(0, 0, getWidth(), getHeight());
                this.f322q.setCallback(this);
                this.f322q.setAlpha(this.r);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(int i2) {
        setContentScrim(new ColorDrawable(i2));
    }

    public void setContentScrimResource(int i2) {
        setContentScrim(ContextCompat.getDrawable(getContext(), i2));
    }

    public Drawable getContentScrim() {
        return this.f322q;
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = null;
        if (this.b != drawable) {
            if (this.b != null) {
                this.b.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.b = drawable2;
            if (this.b != null) {
                if (this.b.isStateful()) {
                    this.b.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.b, ViewCompat.getLayoutDirection(this));
                this.b.setVisible(getVisibility() == 0, false);
                this.b.setCallback(this);
                this.b.setAlpha(this.r);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        boolean z = false;
        Drawable drawable = this.b;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f322q;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (this.a != null) {
            z |= this.a.a(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f322q || drawable == this.b;
    }

    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        if (!(this.b == null || this.b.isVisible() == z)) {
            this.b.setVisible(z, false);
        }
        if (this.f322q != null && this.f322q.isVisible() != z) {
            this.f322q.setVisible(z, false);
        }
    }

    public void setStatusBarScrimColor(int i2) {
        setStatusBarScrim(new ColorDrawable(i2));
    }

    public void setStatusBarScrimResource(int i2) {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), i2));
    }

    public Drawable getStatusBarScrim() {
        return this.b;
    }

    public void setCollapsedTitleTextAppearance(int i2) {
        this.a.c(i2);
    }

    public void setCollapsedTitleTextColor(int i2) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        this.a.a(colorStateList);
    }

    public void setCollapsedTitleGravity(int i2) {
        this.a.b(i2);
    }

    public int getCollapsedTitleGravity() {
        return this.a.e();
    }

    public void setExpandedTitleTextAppearance(int i2) {
        this.a.d(i2);
    }

    public void setExpandedTitleColor(int i2) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        this.a.b(colorStateList);
    }

    public void setExpandedTitleGravity(int i2) {
        this.a.a(i2);
    }

    public int getExpandedTitleGravity() {
        return this.a.d();
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        this.a.a(typeface);
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.a.f();
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        this.a.b(typeface);
    }

    public Typeface getExpandedTitleTypeface() {
        return this.a.g();
    }

    public int getExpandedTitleMarginStart() {
        return this.j;
    }

    public void setExpandedTitleMarginStart(int i2) {
        this.j = i2;
        requestLayout();
    }

    public int getExpandedTitleMarginTop() {
        return this.k;
    }

    public void setExpandedTitleMarginTop(int i2) {
        this.k = i2;
        requestLayout();
    }

    public int getExpandedTitleMarginEnd() {
        return this.l;
    }

    public void setExpandedTitleMarginEnd(int i2) {
        this.l = i2;
        requestLayout();
    }

    public int getExpandedTitleMarginBottom() {
        return this.m;
    }

    public void setExpandedTitleMarginBottom(int i2) {
        this.m = i2;
        requestLayout();
    }

    public void setScrimVisibleHeightTrigger(int i2) {
        if (this.v != i2) {
            this.v = i2;
            b();
        }
    }

    public int getScrimVisibleHeightTrigger() {
        if (this.v >= 0) {
            return this.v;
        }
        int i2 = this.d != null ? this.d.getSystemWindowInsetTop() : 0;
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight > 0) {
            return Math.min(i2 + (minimumHeight * 2), getHeight());
        }
        return getHeight() / 3;
    }

    public void setScrimAnimationDuration(long j2) {
        this.u = j2;
    }

    public long getScrimAnimationDuration() {
        return this.u;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public android.widget.FrameLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: 0000 */
    public final void b() {
        if (this.f322q != null || this.b != null) {
            setScrimsShown(getHeight() + this.c < getScrimVisibleHeightTrigger());
        }
    }

    /* access modifiers changed from: 0000 */
    public final int b(View view) {
        return ((getHeight() - a(view).c()) - view.getHeight()) - ((LayoutParams) view.getLayoutParams()).bottomMargin;
    }

    private void e() {
        setContentDescription(getTitle());
    }
}
