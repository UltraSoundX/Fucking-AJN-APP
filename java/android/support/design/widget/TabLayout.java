package android.support.design.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.DecorView;
import android.support.v4.view.ViewPager.OnAdapterChangeListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.av;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@DecorView
public class TabLayout extends HorizontalScrollView {
    private static final Pool<f> w = new SynchronizedPool(16);
    private final e A;
    private final int B;
    private final int C;
    private final int D;
    private int E;
    private b F;
    private final ArrayList<b> G;
    private b H;
    private ValueAnimator I;
    private PagerAdapter J;
    private DataSetObserver K;
    private TabLayoutOnPageChangeListener L;
    private a M;
    private boolean N;
    private final Pool<g> O;
    int a;
    int b;
    int c;
    int d;
    int e;
    ColorStateList f;
    ColorStateList g;
    ColorStateList h;
    Drawable i;
    Mode j;
    float k;
    float l;
    final int m;
    int n;
    int o;
    int p;

    /* renamed from: q reason: collision with root package name */
    int f325q;
    int r;
    boolean s;
    boolean t;
    boolean u;
    ViewPager v;
    private final ArrayList<f> x;
    private f y;
    /* access modifiers changed from: private */
    public final RectF z;

    public static class TabLayoutOnPageChangeListener implements OnPageChangeListener {
        private final WeakReference<TabLayout> a;
        private int b;
        private int c;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.a = new WeakReference<>(tabLayout);
        }

        public void onPageScrollStateChanged(int i) {
            this.b = this.c;
            this.c = i;
        }

        public void onPageScrolled(int i, float f, int i2) {
            boolean z = false;
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null) {
                boolean z2 = this.c != 2 || this.b == 1;
                if (!(this.c == 2 && this.b == 0)) {
                    z = true;
                }
                tabLayout.a(i, f, z2, z);
            }
        }

        public void onPageSelected(int i) {
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i && i < tabLayout.getTabCount()) {
                tabLayout.b(tabLayout.a(i), this.c == 0 || (this.c == 2 && this.b == 0));
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.c = 0;
            this.b = 0;
        }
    }

    private class a implements OnAdapterChangeListener {
        private boolean b;

        a() {
        }

        public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            if (TabLayout.this.v == viewPager) {
                TabLayout.this.a(pagerAdapter2, this.b);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(boolean z) {
            this.b = z;
        }
    }

    public interface b<T extends f> {
        void a(T t);

        void b(T t);

        void c(T t);
    }

    public interface c extends b {
    }

    private class d extends DataSetObserver {
        d() {
        }

        public void onChanged() {
            TabLayout.this.d();
        }

        public void onInvalidated() {
            TabLayout.this.d();
        }
    }

    private class e extends LinearLayout {
        int a = -1;
        float b;
        private int d;
        private final Paint e;
        private final GradientDrawable f;
        private int g = -1;
        private int h = -1;
        private int i = -1;
        private ValueAnimator j;

        e(Context context) {
            super(context);
            setWillNotDraw(false);
            this.e = new Paint();
            this.f = new GradientDrawable();
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2) {
            if (this.e.getColor() != i2) {
                this.e.setColor(i2);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(int i2) {
            if (this.d != i2) {
                this.d = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2, float f2) {
            if (this.j != null && this.j.isRunning()) {
                this.j.cancel();
            }
            this.a = i2;
            this.b = f2;
            b();
        }

        public void onRtlPropertiesChanged(int i2) {
            super.onRtlPropertiesChanged(i2);
            if (VERSION.SDK_INT < 23 && this.g != i2) {
                requestLayout();
                this.g = i2;
            }
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i2, int i3) {
            boolean z;
            int i4;
            boolean z2 = false;
            super.onMeasure(i2, i3);
            if (MeasureSpec.getMode(i2) == 1073741824 && TabLayout.this.r == 1 && TabLayout.this.o == 1) {
                int childCount = getChildCount();
                int i5 = 0;
                int i6 = 0;
                while (i5 < childCount) {
                    View childAt = getChildAt(i5);
                    if (childAt.getVisibility() == 0) {
                        i4 = Math.max(i6, childAt.getMeasuredWidth());
                    } else {
                        i4 = i6;
                    }
                    i5++;
                    i6 = i4;
                }
                if (i6 > 0) {
                    if (i6 * childCount <= getMeasuredWidth() - (TabLayout.this.b(16) * 2)) {
                        int i7 = 0;
                        while (i7 < childCount) {
                            LayoutParams layoutParams = (LayoutParams) getChildAt(i7).getLayoutParams();
                            if (layoutParams.width == i6 && layoutParams.weight == 0.0f) {
                                z = z2;
                            } else {
                                layoutParams.width = i6;
                                layoutParams.weight = 0.0f;
                                z = true;
                            }
                            i7++;
                            z2 = z;
                        }
                    } else {
                        TabLayout.this.o = 0;
                        TabLayout.this.a(false);
                        z2 = true;
                    }
                    if (z2) {
                        super.onMeasure(i2, i3);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            if (this.j == null || !this.j.isRunning()) {
                b();
                return;
            }
            this.j.cancel();
            b(this.a, Math.round(((float) this.j.getDuration()) * (1.0f - this.j.getAnimatedFraction())));
        }

        private void b() {
            int i2;
            int i3;
            int i4;
            int i5;
            View childAt = getChildAt(this.a);
            if (childAt == null || childAt.getWidth() <= 0) {
                i2 = -1;
                i3 = -1;
            } else {
                i3 = childAt.getLeft();
                i2 = childAt.getRight();
                if (!TabLayout.this.t && (childAt instanceof g)) {
                    a((g) childAt, TabLayout.this.z);
                    i3 = (int) TabLayout.this.z.left;
                    i2 = (int) TabLayout.this.z.right;
                }
                if (this.b > 0.0f && this.a < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.a + 1);
                    int left = childAt2.getLeft();
                    int right = childAt2.getRight();
                    if (TabLayout.this.t || !(childAt2 instanceof g)) {
                        i4 = right;
                        i5 = left;
                    } else {
                        a((g) childAt2, TabLayout.this.z);
                        i5 = (int) TabLayout.this.z.left;
                        i4 = (int) TabLayout.this.z.right;
                    }
                    i3 = (int) ((((float) i3) * (1.0f - this.b)) + (((float) i5) * this.b));
                    i2 = (int) ((((float) i4) * this.b) + (((float) i2) * (1.0f - this.b)));
                }
            }
            a(i3, i2);
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2, int i3) {
            if (i2 != this.h || i3 != this.i) {
                this.h = i2;
                this.i = i3;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(final int i2, int i3) {
            if (this.j != null && this.j.isRunning()) {
                this.j.cancel();
            }
            View childAt = getChildAt(i2);
            if (childAt == null) {
                b();
                return;
            }
            final int left = childAt.getLeft();
            final int right = childAt.getRight();
            if (!TabLayout.this.t && (childAt instanceof g)) {
                a((g) childAt, TabLayout.this.z);
                left = (int) TabLayout.this.z.left;
                right = (int) TabLayout.this.z.right;
            }
            final int i4 = this.h;
            final int i5 = this.i;
            if (i4 != left || i5 != right) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.j = valueAnimator;
                valueAnimator.setInterpolator(android.support.design.a.a.b);
                valueAnimator.setDuration((long) i3);
                valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
                valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        e.this.a(android.support.design.a.a.a(i4, left, animatedFraction), android.support.design.a.a.a(i5, right, animatedFraction));
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        e.this.a = i2;
                        e.this.b = 0.0f;
                    }
                });
                valueAnimator.start();
            }
        }

        private void a(g gVar, RectF rectF) {
            int a2 = gVar.d();
            if (a2 < TabLayout.this.b(24)) {
                a2 = TabLayout.this.b(24);
            }
            int left = (gVar.getLeft() + gVar.getRight()) / 2;
            rectF.set((float) (left - (a2 / 2)), 0.0f, (float) ((a2 / 2) + left), 0.0f);
        }

        public void draw(Canvas canvas) {
            int i2;
            int i3 = 0;
            if (TabLayout.this.i != null) {
                i2 = TabLayout.this.i.getIntrinsicHeight();
            } else {
                i2 = 0;
            }
            if (this.d >= 0) {
                i2 = this.d;
            }
            switch (TabLayout.this.f325q) {
                case 0:
                    i3 = getHeight() - i2;
                    i2 = getHeight();
                    break;
                case 1:
                    i3 = (getHeight() - i2) / 2;
                    i2 = (i2 + getHeight()) / 2;
                    break;
                case 2:
                    break;
                case 3:
                    i2 = getHeight();
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (this.h >= 0 && this.i > this.h) {
                Drawable wrap = DrawableCompat.wrap(TabLayout.this.i != null ? TabLayout.this.i : this.f);
                wrap.setBounds(this.h, i3, this.i, i2);
                if (this.e != null) {
                    if (VERSION.SDK_INT == 21) {
                        wrap.setColorFilter(this.e.getColor(), Mode.SRC_IN);
                    } else {
                        DrawableCompat.setTint(wrap, this.e.getColor());
                    }
                }
                wrap.draw(canvas);
            }
            super.draw(canvas);
        }
    }

    public static class f {
        public TabLayout a;
        public g b;
        private Object c;
        private Drawable d;
        /* access modifiers changed from: private */
        public CharSequence e;
        /* access modifiers changed from: private */
        public CharSequence f;
        private int g = -1;
        private View h;

        public View a() {
            return this.h;
        }

        public f a(View view) {
            this.h = view;
            g();
            return this;
        }

        public f a(int i) {
            return a(LayoutInflater.from(this.b.getContext()).inflate(i, this.b, false));
        }

        public Drawable b() {
            return this.d;
        }

        public int c() {
            return this.g;
        }

        /* access modifiers changed from: 0000 */
        public void b(int i) {
            this.g = i;
        }

        public CharSequence d() {
            return this.e;
        }

        public f a(Drawable drawable) {
            this.d = drawable;
            g();
            return this;
        }

        public f a(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f) && !TextUtils.isEmpty(charSequence)) {
                this.b.setContentDescription(charSequence);
            }
            this.e = charSequence;
            g();
            return this;
        }

        public void e() {
            if (this.a == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            this.a.c(this);
        }

        public boolean f() {
            if (this.a != null) {
                return this.a.getSelectedTabPosition() == this.g;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public f b(CharSequence charSequence) {
            this.f = charSequence;
            g();
            return this;
        }

        /* access modifiers changed from: 0000 */
        public void g() {
            if (this.b != null) {
                this.b.b();
            }
        }

        /* access modifiers changed from: 0000 */
        public void h() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = -1;
            this.h = null;
        }
    }

    class g extends LinearLayout {
        private f b;
        private TextView c;
        private ImageView d;
        private View e;
        private TextView f;
        private ImageView g;
        private Drawable h;
        private int i = 2;

        public g(Context context) {
            int i2;
            super(context);
            a(context);
            ViewCompat.setPaddingRelative(this, TabLayout.this.a, TabLayout.this.b, TabLayout.this.c, TabLayout.this.d);
            setGravity(17);
            if (TabLayout.this.s) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            setOrientation(i2);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), PointerIconCompat.TYPE_HAND));
        }

        /* access modifiers changed from: private */
        public void a(Context context) {
            Drawable drawable;
            GradientDrawable gradientDrawable = null;
            if (TabLayout.this.m != 0) {
                this.h = android.support.v7.a.a.a.b(context, TabLayout.this.m);
                if (this.h != null && this.h.isStateful()) {
                    this.h.setState(getDrawableState());
                }
            } else {
                this.h = null;
            }
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setColor(0);
            if (TabLayout.this.h != null) {
                GradientDrawable gradientDrawable3 = new GradientDrawable();
                gradientDrawable3.setCornerRadius(1.0E-5f);
                gradientDrawable3.setColor(-1);
                ColorStateList a2 = android.support.design.f.a.a(TabLayout.this.h);
                if (VERSION.SDK_INT >= 21) {
                    if (TabLayout.this.u) {
                        gradientDrawable2 = null;
                    }
                    if (!TabLayout.this.u) {
                        gradientDrawable = gradientDrawable3;
                    }
                    drawable = new RippleDrawable(a2, gradientDrawable2, gradientDrawable);
                } else {
                    Drawable wrap = DrawableCompat.wrap(gradientDrawable3);
                    DrawableCompat.setTintList(wrap, a2);
                    drawable = new LayerDrawable(new Drawable[]{gradientDrawable2, wrap});
                }
            } else {
                drawable = gradientDrawable2;
            }
            ViewCompat.setBackground(this, drawable);
            TabLayout.this.invalidate();
        }

        /* access modifiers changed from: private */
        public void a(Canvas canvas) {
            if (this.h != null) {
                this.h.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.h.draw(canvas);
            }
        }

        /* access modifiers changed from: protected */
        public void drawableStateChanged() {
            super.drawableStateChanged();
            boolean z = false;
            int[] drawableState = getDrawableState();
            if (this.h != null && this.h.isStateful()) {
                z = false | this.h.setState(drawableState);
            }
            if (z) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.b == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            this.b.e();
            return true;
        }

        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            if (this.c != null) {
                this.c.setSelected(z);
            }
            if (this.d != null) {
                this.d.setSelected(z);
            }
            if (this.e != null) {
                this.e.setSelected(z);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        public void onMeasure(int i2, int i3) {
            boolean z = true;
            int size = MeasureSpec.getSize(i2);
            int mode = MeasureSpec.getMode(i2);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i2 = MeasureSpec.makeMeasureSpec(TabLayout.this.n, ExploreByTouchHelper.INVALID_ID);
            }
            super.onMeasure(i2, i3);
            if (this.c != null) {
                float f2 = TabLayout.this.k;
                int i4 = this.i;
                if (this.d != null && this.d.getVisibility() == 0) {
                    i4 = 1;
                } else if (this.c != null && this.c.getLineCount() > 1) {
                    f2 = TabLayout.this.l;
                }
                float textSize = this.c.getTextSize();
                int lineCount = this.c.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.c);
                if (f2 != textSize || (maxLines >= 0 && i4 != maxLines)) {
                    if (TabLayout.this.r == 1 && f2 > textSize && lineCount == 1) {
                        Layout layout = this.c.getLayout();
                        if (layout == null || a(layout, 0, f2) > ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) {
                            z = false;
                        }
                    }
                    if (z) {
                        this.c.setTextSize(0, f2);
                        this.c.setMaxLines(i4);
                        super.onMeasure(i2, i3);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(f fVar) {
            if (fVar != this.b) {
                this.b = fVar;
                b();
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            a((f) null);
            setSelected(false);
        }

        /* access modifiers changed from: 0000 */
        public final void b() {
            boolean z;
            f fVar = this.b;
            View view = fVar != null ? fVar.a() : null;
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(view);
                    }
                    addView(view);
                }
                this.e = view;
                if (this.c != null) {
                    this.c.setVisibility(8);
                }
                if (this.d != null) {
                    this.d.setVisibility(8);
                    this.d.setImageDrawable(null);
                }
                this.f = (TextView) view.findViewById(16908308);
                if (this.f != null) {
                    this.i = TextViewCompat.getMaxLines(this.f);
                }
                this.g = (ImageView) view.findViewById(16908294);
            } else {
                if (this.e != null) {
                    removeView(this.e);
                    this.e = null;
                }
                this.f = null;
                this.g = null;
            }
            if (this.e == null) {
                if (this.d == null) {
                    ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, this, false);
                    addView(imageView, 0);
                    this.d = imageView;
                }
                Drawable drawable = (fVar == null || fVar.b() == null) ? null : DrawableCompat.wrap(fVar.b()).mutate();
                if (drawable != null) {
                    DrawableCompat.setTintList(drawable, TabLayout.this.g);
                    if (TabLayout.this.j != null) {
                        DrawableCompat.setTintMode(drawable, TabLayout.this.j);
                    }
                }
                if (this.c == null) {
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, this, false);
                    addView(textView);
                    this.c = textView;
                    this.i = TextViewCompat.getMaxLines(this.c);
                }
                TextViewCompat.setTextAppearance(this.c, TabLayout.this.e);
                if (TabLayout.this.f != null) {
                    this.c.setTextColor(TabLayout.this.f);
                }
                a(this.c, this.d);
            } else if (!(this.f == null && this.g == null)) {
                a(this.f, this.g);
            }
            if (fVar != null && !TextUtils.isEmpty(fVar.f)) {
                setContentDescription(fVar.f);
            }
            if (fVar == null || !fVar.f()) {
                z = false;
            } else {
                z = true;
            }
            setSelected(z);
        }

        /* access modifiers changed from: 0000 */
        public final void c() {
            setOrientation(TabLayout.this.s ? 0 : 1);
            if (this.f == null && this.g == null) {
                a(this.c, this.d);
            } else {
                a(this.f, this.g);
            }
        }

        private void a(TextView textView, ImageView imageView) {
            CharSequence charSequence;
            boolean z;
            CharSequence charSequence2;
            int i2;
            CharSequence charSequence3 = null;
            Drawable drawable = (this.b == null || this.b.b() == null) ? null : DrawableCompat.wrap(this.b.b()).mutate();
            if (this.b != null) {
                charSequence = this.b.d();
            } else {
                charSequence = null;
            }
            if (imageView != null) {
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            if (!TextUtils.isEmpty(charSequence)) {
                z = true;
            } else {
                z = false;
            }
            if (textView != null) {
                if (z) {
                    textView.setText(charSequence);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText(null);
                }
            }
            if (imageView != null) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) imageView.getLayoutParams();
                if (!z || imageView.getVisibility() != 0) {
                    i2 = 0;
                } else {
                    i2 = TabLayout.this.b(8);
                }
                if (TabLayout.this.s) {
                    if (i2 != MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) {
                        MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, i2);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (i2 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = i2;
                    MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            if (this.b != null) {
                charSequence2 = this.b.f;
            } else {
                charSequence2 = null;
            }
            if (!z) {
                charSequence3 = charSequence2;
            }
            av.a(this, charSequence3);
        }

        /* access modifiers changed from: private */
        public int d() {
            int i2 = 0;
            View[] viewArr = {this.c, this.d, this.e};
            int length = viewArr.length;
            int i3 = 0;
            boolean z = false;
            for (int i4 = 0; i4 < length; i4++) {
                View view = viewArr[i4];
                if (view != null && view.getVisibility() == 0) {
                    i3 = z ? Math.min(i3, view.getLeft()) : view.getLeft();
                    i2 = z ? Math.max(i2, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return i2 - i3;
        }

        private float a(Layout layout, int i2, float f2) {
            return layout.getLineWidth(i2) * (f2 / layout.getPaint().getTextSize());
        }
    }

    public static class h implements c {
        private final ViewPager a;

        public h(ViewPager viewPager) {
            this.a = viewPager;
        }

        public void a(f fVar) {
            this.a.setCurrentItem(fVar.c());
        }

        public void b(f fVar) {
        }

        public void c(f fVar) {
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.tabStyle);
    }

    /* JADX INFO: finally extract failed */
    public TabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.x = new ArrayList<>();
        this.z = new RectF();
        this.n = Integer.MAX_VALUE;
        this.G = new ArrayList<>();
        this.O = new SimplePool(12);
        setHorizontalScrollBarEnabled(false);
        this.A = new e(context);
        super.addView(this.A, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray a2 = android.support.design.internal.c.a(context, attributeSet, R.styleable.TabLayout, i2, R.style.Widget_Design_TabLayout, R.styleable.TabLayout_tabTextAppearance);
        this.A.b(a2.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, -1));
        this.A.a(a2.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(android.support.design.e.a.b(context, a2, R.styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(a2.getInt(R.styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(a2.getBoolean(R.styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = a2.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
        this.d = dimensionPixelSize;
        this.c = dimensionPixelSize;
        this.b = dimensionPixelSize;
        this.a = dimensionPixelSize;
        this.a = a2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, this.a);
        this.b = a2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.b);
        this.c = a2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.c);
        this.d = a2.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.d);
        this.e = a2.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.e, android.support.v7.appcompat.R.styleable.TextAppearance);
        try {
            this.k = (float) obtainStyledAttributes.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize, 0);
            this.f = android.support.design.e.a.a(context, obtainStyledAttributes, android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
            obtainStyledAttributes.recycle();
            if (a2.hasValue(R.styleable.TabLayout_tabTextColor)) {
                this.f = android.support.design.e.a.a(context, a2, R.styleable.TabLayout_tabTextColor);
            }
            if (a2.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
                this.f = a(this.f.getDefaultColor(), a2.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0));
            }
            this.g = android.support.design.e.a.a(context, a2, R.styleable.TabLayout_tabIconTint);
            this.j = android.support.design.internal.d.a(a2.getInt(R.styleable.TabLayout_tabIconTintMode, -1), null);
            this.h = android.support.design.e.a.a(context, a2, R.styleable.TabLayout_tabRippleColor);
            this.p = a2.getInt(R.styleable.TabLayout_tabIndicatorAnimationDuration, ErrorCode.ERROR_CODE_LOAD_BASE);
            this.B = a2.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
            this.C = a2.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
            this.m = a2.getResourceId(R.styleable.TabLayout_tabBackground, 0);
            this.E = a2.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
            this.r = a2.getInt(R.styleable.TabLayout_tabMode, 1);
            this.o = a2.getInt(R.styleable.TabLayout_tabGravity, 0);
            this.s = a2.getBoolean(R.styleable.TabLayout_tabInlineLabel, false);
            this.u = a2.getBoolean(R.styleable.TabLayout_tabUnboundedRipple, false);
            a2.recycle();
            Resources resources = getResources();
            this.l = (float) resources.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
            this.D = resources.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
            h();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.A.a(i2);
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.A.b(i2);
    }

    public void a(int i2, float f2, boolean z2) {
        a(i2, f2, z2, true);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, float f2, boolean z2, boolean z3) {
        int round = Math.round(((float) i2) + f2);
        if (round >= 0 && round < this.A.getChildCount()) {
            if (z3) {
                this.A.a(i2, f2);
            }
            if (this.I != null && this.I.isRunning()) {
                this.I.cancel();
            }
            scrollTo(a(i2, f2), 0);
            if (z2) {
                setSelectedTabView(round);
            }
        }
    }

    public void a(f fVar) {
        a(fVar, this.x.isEmpty());
    }

    public void a(f fVar, boolean z2) {
        a(fVar, this.x.size(), z2);
    }

    public void a(f fVar, int i2, boolean z2) {
        if (fVar.a != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        a(fVar, i2);
        e(fVar);
        if (z2) {
            fVar.e();
        }
    }

    private void a(TabItem tabItem) {
        f a2 = a();
        if (tabItem.a != null) {
            a2.a(tabItem.a);
        }
        if (tabItem.b != null) {
            a2.a(tabItem.b);
        }
        if (tabItem.c != 0) {
            a2.a(tabItem.c);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            a2.b(tabItem.getContentDescription());
        }
        a(a2);
    }

    @Deprecated
    public void setOnTabSelectedListener(b bVar) {
        if (this.F != null) {
            b(this.F);
        }
        this.F = bVar;
        if (bVar != null) {
            a(bVar);
        }
    }

    public void a(b bVar) {
        if (!this.G.contains(bVar)) {
            this.G.add(bVar);
        }
    }

    public void b(b bVar) {
        this.G.remove(bVar);
    }

    public f a() {
        f b2 = b();
        b2.a = this;
        b2.b = d(b2);
        return b2;
    }

    /* access modifiers changed from: protected */
    public f b() {
        f fVar = (f) w.acquire();
        if (fVar == null) {
            return new f();
        }
        return fVar;
    }

    /* access modifiers changed from: protected */
    public boolean b(f fVar) {
        return w.release(fVar);
    }

    public int getTabCount() {
        return this.x.size();
    }

    public f a(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return (f) this.x.get(i2);
    }

    public int getSelectedTabPosition() {
        if (this.y != null) {
            return this.y.c();
        }
        return -1;
    }

    public void c() {
        for (int childCount = this.A.getChildCount() - 1; childCount >= 0; childCount--) {
            c(childCount);
        }
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            f fVar = (f) it.next();
            it.remove();
            fVar.h();
            b(fVar);
        }
        this.y = null;
    }

    public void setTabMode(int i2) {
        if (i2 != this.r) {
            this.r = i2;
            h();
        }
    }

    public int getTabMode() {
        return this.r;
    }

    public void setTabGravity(int i2) {
        if (this.o != i2) {
            this.o = i2;
            h();
        }
    }

    public int getTabGravity() {
        return this.o;
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.f325q != i2) {
            this.f325q = i2;
            ViewCompat.postInvalidateOnAnimation(this.A);
        }
    }

    public int getTabIndicatorGravity() {
        return this.f325q;
    }

    public void setTabIndicatorFullWidth(boolean z2) {
        this.t = z2;
        ViewCompat.postInvalidateOnAnimation(this.A);
    }

    public void setInlineLabel(boolean z2) {
        if (this.s != z2) {
            this.s = z2;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < this.A.getChildCount()) {
                    View childAt = this.A.getChildAt(i3);
                    if (childAt instanceof g) {
                        ((g) childAt).c();
                    }
                    i2 = i3 + 1;
                } else {
                    h();
                    return;
                }
            }
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    public void setUnboundedRipple(boolean z2) {
        if (this.u != z2) {
            this.u = z2;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < this.A.getChildCount()) {
                    View childAt = this.A.getChildAt(i3);
                    if (childAt instanceof g) {
                        ((g) childAt).a(getContext());
                    }
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.f != colorStateList) {
            this.f = colorStateList;
            e();
        }
    }

    public ColorStateList getTabTextColors() {
        return this.f;
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.g != colorStateList) {
            this.g = colorStateList;
            e();
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(android.support.v7.a.a.a.a(getContext(), i2));
    }

    public ColorStateList getTabIconTint() {
        return this.g;
    }

    public ColorStateList getTabRippleColor() {
        return this.h;
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.h != colorStateList) {
            this.h = colorStateList;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < this.A.getChildCount()) {
                    View childAt = this.A.getChildAt(i3);
                    if (childAt instanceof g) {
                        ((g) childAt).a(getContext());
                    }
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(android.support.v7.a.a.a.a(getContext(), i2));
    }

    public Drawable getTabSelectedIndicator() {
        return this.i;
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.i != drawable) {
            this.i = drawable;
            ViewCompat.postInvalidateOnAnimation(this.A);
        }
    }

    public void setSelectedTabIndicator(int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(android.support.v7.a.a.a.b(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setupWithViewPager(ViewPager viewPager) {
        a(viewPager, true);
    }

    public void a(ViewPager viewPager, boolean z2) {
        a(viewPager, z2, false);
    }

    private void a(ViewPager viewPager, boolean z2, boolean z3) {
        if (this.v != null) {
            if (this.L != null) {
                this.v.removeOnPageChangeListener(this.L);
            }
            if (this.M != null) {
                this.v.removeOnAdapterChangeListener(this.M);
            }
        }
        if (this.H != null) {
            b(this.H);
            this.H = null;
        }
        if (viewPager != null) {
            this.v = viewPager;
            if (this.L == null) {
                this.L = new TabLayoutOnPageChangeListener(this);
            }
            this.L.a();
            viewPager.addOnPageChangeListener(this.L);
            this.H = new h(viewPager);
            a(this.H);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                a(adapter, z2);
            }
            if (this.M == null) {
                this.M = new a();
            }
            this.M.a(z2);
            viewPager.addOnAdapterChangeListener(this.M);
            a(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.v = null;
            a((PagerAdapter) null, false);
        }
        this.N = z3;
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter) {
        a(pagerAdapter, false);
    }

    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.v == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                a((ViewPager) parent, true, true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.N) {
            setupWithViewPager(null);
            this.N = false;
        }
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.A.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    /* access modifiers changed from: 0000 */
    public void a(PagerAdapter pagerAdapter, boolean z2) {
        if (!(this.J == null || this.K == null)) {
            this.J.unregisterDataSetObserver(this.K);
        }
        this.J = pagerAdapter;
        if (z2 && pagerAdapter != null) {
            if (this.K == null) {
                this.K = new d();
            }
            pagerAdapter.registerDataSetObserver(this.K);
        }
        d();
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        c();
        if (this.J != null) {
            int count = this.J.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                a(a().a(this.J.getPageTitle(i2)), false);
            }
            if (this.v != null && count > 0) {
                int currentItem = this.v.getCurrentItem();
                if (currentItem != getSelectedTabPosition() && currentItem < getTabCount()) {
                    c(a(currentItem));
                }
            }
        }
    }

    private void e() {
        int size = this.x.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((f) this.x.get(i2)).g();
        }
    }

    private g d(f fVar) {
        g gVar = this.O != null ? (g) this.O.acquire() : null;
        if (gVar == null) {
            gVar = new g(getContext());
        }
        gVar.a(fVar);
        gVar.setFocusable(true);
        gVar.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(fVar.f)) {
            gVar.setContentDescription(fVar.e);
        } else {
            gVar.setContentDescription(fVar.f);
        }
        return gVar;
    }

    private void a(f fVar, int i2) {
        fVar.b(i2);
        this.x.add(i2, fVar);
        int size = this.x.size();
        for (int i3 = i2 + 1; i3 < size; i3++) {
            ((f) this.x.get(i3)).b(i3);
        }
    }

    private void e(f fVar) {
        this.A.addView(fVar.b, fVar.c(), f());
    }

    public void addView(View view) {
        a(view);
    }

    public void addView(View view, int i2) {
        a(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    private void a(View view) {
        if (view instanceof TabItem) {
            a((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private LayoutParams f() {
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        a(layoutParams);
        return layoutParams;
    }

    private void a(LayoutParams layoutParams) {
        if (this.r == 1 && this.o == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    /* access modifiers changed from: 0000 */
    public int b(int i2) {
        return Math.round(getResources().getDisplayMetrics().density * ((float) i2));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.A.getChildCount()) {
                View childAt = this.A.getChildAt(i3);
                if (childAt instanceof g) {
                    ((g) childAt).a(canvas);
                }
                i2 = i3 + 1;
            } else {
                super.onDraw(canvas);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        boolean z2;
        int b2;
        boolean z3 = true;
        int b3 = b(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        switch (MeasureSpec.getMode(i3)) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                i3 = MeasureSpec.makeMeasureSpec(Math.min(b3, MeasureSpec.getSize(i3)), 1073741824);
                break;
            case 0:
                i3 = MeasureSpec.makeMeasureSpec(b3, 1073741824);
                break;
        }
        int size = MeasureSpec.getSize(i2);
        if (MeasureSpec.getMode(i2) != 0) {
            if (this.C > 0) {
                b2 = this.C;
            } else {
                b2 = size - b(56);
            }
            this.n = b2;
        }
        super.onMeasure(i2, i3);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            switch (this.r) {
                case 0:
                    if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                        z2 = false;
                        break;
                    } else {
                        z2 = true;
                        break;
                    }
                case 1:
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        z3 = false;
                    }
                    z2 = z3;
                    break;
                default:
                    z2 = false;
                    break;
            }
            if (z2) {
                childAt.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
            }
        }
    }

    private void c(int i2) {
        g gVar = (g) this.A.getChildAt(i2);
        this.A.removeViewAt(i2);
        if (gVar != null) {
            gVar.a();
            this.O.release(gVar);
        }
        requestLayout();
    }

    private void d(int i2) {
        if (i2 != -1) {
            if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.A.a()) {
                a(i2, 0.0f, true);
                return;
            }
            int scrollX = getScrollX();
            int a2 = a(i2, 0.0f);
            if (scrollX != a2) {
                g();
                this.I.setIntValues(new int[]{scrollX, a2});
                this.I.start();
            }
            this.A.b(i2, this.p);
        }
    }

    private void g() {
        if (this.I == null) {
            this.I = new ValueAnimator();
            this.I.setInterpolator(android.support.design.a.a.b);
            this.I.setDuration((long) this.p);
            this.I.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void setScrollAnimatorListener(AnimatorListener animatorListener) {
        g();
        this.I.addListener(animatorListener);
    }

    private void setSelectedTabView(int i2) {
        boolean z2;
        boolean z3;
        int childCount = this.A.getChildCount();
        if (i2 < childCount) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = this.A.getChildAt(i3);
                if (i3 == i2) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                childAt.setSelected(z2);
                if (i3 == i2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                childAt.setActivated(z3);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(f fVar) {
        b(fVar, true);
    }

    /* access modifiers changed from: 0000 */
    public void b(f fVar, boolean z2) {
        f fVar2 = this.y;
        if (fVar2 != fVar) {
            int i2 = fVar != null ? fVar.c() : -1;
            if (z2) {
                if ((fVar2 == null || fVar2.c() == -1) && i2 != -1) {
                    a(i2, 0.0f, true);
                } else {
                    d(i2);
                }
                if (i2 != -1) {
                    setSelectedTabView(i2);
                }
            }
            this.y = fVar;
            if (fVar2 != null) {
                g(fVar2);
            }
            if (fVar != null) {
                f(fVar);
            }
        } else if (fVar2 != null) {
            h(fVar);
            d(fVar.c());
        }
    }

    private void f(f fVar) {
        for (int size = this.G.size() - 1; size >= 0; size--) {
            ((b) this.G.get(size)).a(fVar);
        }
    }

    private void g(f fVar) {
        for (int size = this.G.size() - 1; size >= 0; size--) {
            ((b) this.G.get(size)).b(fVar);
        }
    }

    private void h(f fVar) {
        for (int size = this.G.size() - 1; size >= 0; size--) {
            ((b) this.G.get(size)).c(fVar);
        }
    }

    private int a(int i2, float f2) {
        int i3;
        int i4 = 0;
        if (this.r != 0) {
            return 0;
        }
        View childAt = this.A.getChildAt(i2);
        View view = i2 + 1 < this.A.getChildCount() ? this.A.getChildAt(i2 + 1) : null;
        if (childAt != null) {
            i3 = childAt.getWidth();
        } else {
            i3 = 0;
        }
        if (view != null) {
            i4 = view.getWidth();
        }
        int left = (childAt.getLeft() + (i3 / 2)) - (getWidth() / 2);
        int i5 = (int) (((float) (i4 + i3)) * 0.5f * f2);
        return ViewCompat.getLayoutDirection(this) == 0 ? i5 + left : left - i5;
    }

    private void h() {
        int i2;
        if (this.r == 0) {
            i2 = Math.max(0, this.E - this.a);
        } else {
            i2 = 0;
        }
        ViewCompat.setPaddingRelative(this.A, i2, 0, 0, 0);
        switch (this.r) {
            case 0:
                this.A.setGravity(GravityCompat.START);
                break;
            case 1:
                this.A.setGravity(1);
                break;
        }
        a(true);
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.A.getChildCount()) {
                View childAt = this.A.getChildAt(i3);
                childAt.setMinimumWidth(getTabMinWidth());
                a((LayoutParams) childAt.getLayoutParams());
                if (z2) {
                    childAt.requestLayout();
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    private static ColorStateList a(int i2, int i3) {
        return new ColorStateList(new int[][]{SELECTED_STATE_SET, EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private int getDefaultHeight() {
        boolean z2;
        int size = this.x.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z2 = false;
                break;
            }
            f fVar = (f) this.x.get(i2);
            if (fVar != null && fVar.b() != null && !TextUtils.isEmpty(fVar.d())) {
                z2 = true;
                break;
            }
            i2++;
        }
        if (!z2 || this.s) {
            return 48;
        }
        return 72;
    }

    private int getTabMinWidth() {
        if (this.B != -1) {
            return this.B;
        }
        if (this.r == 0) {
            return this.D;
        }
        return 0;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    /* access modifiers changed from: 0000 */
    public int getTabMaxWidth() {
        return this.n;
    }
}
