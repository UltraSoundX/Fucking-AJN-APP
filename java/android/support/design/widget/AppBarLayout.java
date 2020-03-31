package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.internal.c;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@android.support.design.widget.CoordinatorLayout.b(a = Behavior.class)
public class AppBarLayout extends LinearLayout {
    private int a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private WindowInsetsCompat f;
    private List<a> g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int[] l;

    protected static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        /* access modifiers changed from: private */
        public int b;
        private int c;
        private ValueAnimator d;
        private int e = -1;
        private boolean f;
        private float g;
        private WeakReference<View> h;
        private a i;

        protected static class SavedState extends AbsSavedState {
            public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return new SavedState(parcel, classLoader);
                }

                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel) {
                    return new SavedState(parcel, null);
                }

                /* renamed from: a */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            };
            int a;
            float b;
            boolean c;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.a = parcel.readInt();
                this.b = parcel.readFloat();
                this.c = parcel.readByte() != 0;
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }

            public void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.a);
                parcel.writeFloat(this.b);
                parcel.writeByte((byte) (this.c ? 1 : 0));
            }
        }

        public static abstract class a<T extends AppBarLayout> {
            public abstract boolean a(T t);
        }

        public BaseBehavior() {
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public boolean a(CoordinatorLayout coordinatorLayout, T t, View view, View view2, int i2, int i3) {
            boolean z = (i2 & 2) != 0 && (t.d() || a(coordinatorLayout, t, view));
            if (z && this.d != null) {
                this.d.cancel();
            }
            this.h = null;
            this.c = i3;
            return z;
        }

        private boolean a(CoordinatorLayout coordinatorLayout, T t, View view) {
            return t.c() && coordinatorLayout.getHeight() - view.getHeight() <= t.getHeight();
        }

        public void a(CoordinatorLayout coordinatorLayout, T t, View view, int i2, int i3, int[] iArr, int i4) {
            int i5;
            int i6;
            if (i3 != 0) {
                if (i3 < 0) {
                    i5 = -t.getTotalScrollRange();
                    i6 = i5 + t.getDownNestedPreScrollRange();
                } else {
                    i5 = -t.getUpNestedPreScrollRange();
                    i6 = 0;
                }
                if (i5 != i6) {
                    iArr[1] = b(coordinatorLayout, t, i3, i5, i6);
                    a(i3, t, view, i4);
                }
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, T t, View view, int i2, int i3, int i4, int i5, int i6) {
            boolean z = false;
            if (i5 < 0) {
                b(coordinatorLayout, t, i5, -t.getDownNestedScrollRange(), 0);
                a(i5, t, view, i6);
            }
            if (t.d()) {
                if (view.getScrollY() > 0) {
                    z = true;
                }
                t.a(z);
            }
        }

        private void a(int i2, T t, View view, int i3) {
            if (i3 == 1) {
                int a2 = a();
                if ((i2 < 0 && a2 == 0) || (i2 > 0 && a2 == (-t.getDownNestedScrollRange()))) {
                    ViewCompat.stopNestedScroll(view, 1);
                }
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, T t, View view, int i2) {
            if (this.c == 0 || i2 == 1) {
                c(coordinatorLayout, t);
            }
            this.h = new WeakReference<>(view);
        }

        private void a(CoordinatorLayout coordinatorLayout, T t, int i2, float f2) {
            int height;
            int abs = Math.abs(a() - i2);
            float abs2 = Math.abs(f2);
            if (abs2 > 0.0f) {
                height = Math.round((((float) abs) / abs2) * 1000.0f) * 3;
            } else {
                height = (int) (((((float) abs) / ((float) t.getHeight())) + 1.0f) * 150.0f);
            }
            a(coordinatorLayout, t, i2, height);
        }

        private void a(final CoordinatorLayout coordinatorLayout, final T t, int i2, int i3) {
            int a2 = a();
            if (a2 != i2) {
                if (this.d == null) {
                    this.d = new ValueAnimator();
                    this.d.setInterpolator(android.support.design.a.a.e);
                    this.d.addUpdateListener(new AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            BaseBehavior.this.a_(coordinatorLayout, t, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                        }
                    });
                } else {
                    this.d.cancel();
                }
                this.d.setDuration((long) Math.min(i3, 600));
                this.d.setIntValues(new int[]{a2, i2});
                this.d.start();
            } else if (this.d != null && this.d.isRunning()) {
                this.d.cancel();
            }
        }

        private int a(T t, int i2) {
            int i3;
            int i4;
            int childCount = t.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = t.getChildAt(i5);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (a(layoutParams.a(), 32)) {
                    int i6 = top - layoutParams.topMargin;
                    i3 = layoutParams.bottomMargin + bottom;
                    i4 = i6;
                } else {
                    i3 = bottom;
                    i4 = top;
                }
                if (i4 <= (-i2) && i3 >= (-i2)) {
                    return i5;
                }
            }
            return -1;
        }

        private void c(CoordinatorLayout coordinatorLayout, T t) {
            int i2;
            int a2 = a();
            int a3 = a(t, a2);
            if (a3 >= 0) {
                View childAt = t.getChildAt(a3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int a4 = layoutParams.a();
                if ((a4 & 17) == 17) {
                    int i3 = -childAt.getTop();
                    int i4 = -childAt.getBottom();
                    if (a3 == t.getChildCount() - 1) {
                        i4 += t.getTopInset();
                    }
                    if (a(a4, 2)) {
                        i4 += ViewCompat.getMinimumHeight(childAt);
                        i2 = i3;
                    } else if (a(a4, 5)) {
                        i2 = ViewCompat.getMinimumHeight(childAt) + i4;
                        if (a2 >= i2) {
                            i4 = i2;
                            i2 = i3;
                        }
                    } else {
                        i2 = i3;
                    }
                    if (a(a4, 32)) {
                        i2 += layoutParams.topMargin;
                        i4 -= layoutParams.bottomMargin;
                    }
                    if (a2 >= (i4 + i2) / 2) {
                        i4 = i2;
                    }
                    a(coordinatorLayout, t, MathUtils.clamp(i4, -t.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private static boolean a(int i2, int i3) {
            return (i2 & i3) == i3;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, T t, int i2, int i3, int i4, int i5) {
            if (((d) t.getLayoutParams()).height != -2) {
                return super.a(coordinatorLayout, t, i2, i3, i4, i5);
            }
            coordinatorLayout.a(t, i2, i3, MeasureSpec.makeMeasureSpec(0, 0), i5);
            return true;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, T t, int i2) {
            boolean z;
            int round;
            boolean a2 = super.a(coordinatorLayout, t, i2);
            int pendingAction = t.getPendingAction();
            if (this.e >= 0 && (pendingAction & 8) == 0) {
                View childAt = t.getChildAt(this.e);
                int i3 = -childAt.getBottom();
                if (this.f) {
                    round = ViewCompat.getMinimumHeight(childAt) + t.getTopInset() + i3;
                } else {
                    round = Math.round(((float) childAt.getHeight()) * this.g) + i3;
                }
                a_(coordinatorLayout, t, round);
            } else if (pendingAction != 0) {
                if ((pendingAction & 4) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                if ((pendingAction & 2) != 0) {
                    int i4 = -t.getUpNestedPreScrollRange();
                    if (z) {
                        a(coordinatorLayout, t, i4, 0.0f);
                    } else {
                        a_(coordinatorLayout, t, i4);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z) {
                        a(coordinatorLayout, t, 0, 0.0f);
                    } else {
                        a_(coordinatorLayout, t, 0);
                    }
                }
            }
            t.e();
            this.e = -1;
            a(MathUtils.clamp(b(), -t.getTotalScrollRange(), 0));
            a(coordinatorLayout, t, b(), 0, true);
            t.a(b());
            return a2;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public boolean c(T t) {
            if (this.i != null) {
                return this.i.a(t);
            }
            if (this.h == null) {
                return true;
            }
            View view = (View) this.h.get();
            return view != null && view.isShown() && !view.canScrollVertically(-1);
        }

        /* access modifiers changed from: 0000 */
        public void a(CoordinatorLayout coordinatorLayout, T t) {
            c(coordinatorLayout, t);
        }

        /* access modifiers changed from: 0000 */
        public int b(T t) {
            return -t.getDownNestedScrollRange();
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: c */
        public int a(T t) {
            return t.getTotalScrollRange();
        }

        /* access modifiers changed from: 0000 */
        public int a(CoordinatorLayout coordinatorLayout, T t, int i2, int i3, int i4) {
            int i5;
            int a2 = a();
            if (i3 == 0 || a2 < i3 || a2 > i4) {
                this.b = 0;
                return 0;
            }
            int clamp = MathUtils.clamp(i2, i3, i4);
            if (a2 == clamp) {
                return 0;
            }
            int i6 = t.b() ? b(t, clamp) : clamp;
            boolean a3 = a(i6);
            int i7 = a2 - clamp;
            this.b = clamp - i6;
            if (!a3 && t.b()) {
                coordinatorLayout.b((View) t);
            }
            t.a(b());
            if (clamp < a2) {
                i5 = -1;
            } else {
                i5 = 1;
            }
            a(coordinatorLayout, t, clamp, i5, false);
            return i7;
        }

        private int b(T t, int i2) {
            int i3;
            int abs = Math.abs(i2);
            int childCount = t.getChildCount();
            int i4 = 0;
            while (i4 < childCount) {
                View childAt = t.getChildAt(i4);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Interpolator b2 = layoutParams.b();
                if (abs < childAt.getTop() || abs > childAt.getBottom()) {
                    i4++;
                } else if (b2 == null) {
                    return i2;
                } else {
                    int a2 = layoutParams.a();
                    if ((a2 & 1) != 0) {
                        i3 = layoutParams.bottomMargin + childAt.getHeight() + layoutParams.topMargin + 0;
                        if ((a2 & 2) != 0) {
                            i3 -= ViewCompat.getMinimumHeight(childAt);
                        }
                    } else {
                        i3 = 0;
                    }
                    if (ViewCompat.getFitsSystemWindows(childAt)) {
                        i3 -= t.getTopInset();
                    }
                    if (i3 <= 0) {
                        return i2;
                    }
                    return Integer.signum(i2) * (Math.round(b2.getInterpolation(((float) (abs - childAt.getTop())) / ((float) i3)) * ((float) i3)) + childAt.getTop());
                }
            }
            return i2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0034  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
        /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(android.support.design.widget.CoordinatorLayout r7, T r8, int r9, int r10, boolean r11) {
            /*
                r6 = this;
                r1 = 1
                r2 = 0
                android.view.View r3 = c(r8, r9)
                if (r3 == 0) goto L_0x0058
                android.view.ViewGroup$LayoutParams r0 = r3.getLayoutParams()
                android.support.design.widget.AppBarLayout$LayoutParams r0 = (android.support.design.widget.AppBarLayout.LayoutParams) r0
                int r0 = r0.a()
                r4 = r0 & 1
                if (r4 == 0) goto L_0x0072
                int r4 = android.support.v4.view.ViewCompat.getMinimumHeight(r3)
                if (r10 <= 0) goto L_0x005b
                r5 = r0 & 12
                if (r5 == 0) goto L_0x005b
                int r0 = -r9
                int r3 = r3.getBottom()
                int r3 = r3 - r4
                int r4 = r8.getTopInset()
                int r3 = r3 - r4
                if (r0 < r3) goto L_0x0059
                r0 = r1
            L_0x002e:
                boolean r3 = r8.d()
                if (r3 == 0) goto L_0x0041
                android.view.View r3 = r6.a(r7)
                if (r3 == 0) goto L_0x0041
                int r0 = r3.getScrollY()
                if (r0 <= 0) goto L_0x0070
            L_0x0040:
                r0 = r1
            L_0x0041:
                boolean r0 = r8.a(r0)
                int r1 = android.os.Build.VERSION.SDK_INT
                r2 = 11
                if (r1 < r2) goto L_0x0058
                if (r11 != 0) goto L_0x0055
                if (r0 == 0) goto L_0x0058
                boolean r0 = r6.d(r7, r8)
                if (r0 == 0) goto L_0x0058
            L_0x0055:
                r8.jumpDrawablesToCurrentState()
            L_0x0058:
                return
            L_0x0059:
                r0 = r2
                goto L_0x002e
            L_0x005b:
                r0 = r0 & 2
                if (r0 == 0) goto L_0x0072
                int r0 = -r9
                int r3 = r3.getBottom()
                int r3 = r3 - r4
                int r4 = r8.getTopInset()
                int r3 = r3 - r4
                if (r0 < r3) goto L_0x006e
                r0 = r1
                goto L_0x002e
            L_0x006e:
                r0 = r2
                goto L_0x002e
            L_0x0070:
                r1 = r2
                goto L_0x0040
            L_0x0072:
                r0 = r2
                goto L_0x002e
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.BaseBehavior.a(android.support.design.widget.CoordinatorLayout, android.support.design.widget.AppBarLayout, int, int, boolean):void");
        }

        private boolean d(CoordinatorLayout coordinatorLayout, T t) {
            List d2 = coordinatorLayout.d((View) t);
            int size = d2.size();
            int i2 = 0;
            while (i2 < size) {
                android.support.design.widget.CoordinatorLayout.Behavior b2 = ((d) ((View) d2.get(i2)).getLayoutParams()).b();
                if (!(b2 instanceof ScrollingViewBehavior)) {
                    i2++;
                } else if (((ScrollingViewBehavior) b2).d() != 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        private static View c(AppBarLayout appBarLayout, int i2) {
            int abs = Math.abs(i2);
            int childCount = appBarLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = appBarLayout.getChildAt(i3);
                if (abs >= childAt.getTop() && abs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        private View a(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (childAt instanceof NestedScrollingChild) {
                    return childAt;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return b() + this.b;
        }

        public Parcelable b(CoordinatorLayout coordinatorLayout, T t) {
            boolean z = false;
            Parcelable b2 = super.b(coordinatorLayout, t);
            int b3 = b();
            int childCount = t.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = t.getChildAt(i2);
                int bottom = childAt.getBottom() + b3;
                if (childAt.getTop() + b3 > 0 || bottom < 0) {
                    i2++;
                } else {
                    SavedState savedState = new SavedState(b2);
                    savedState.a = i2;
                    if (bottom == ViewCompat.getMinimumHeight(childAt) + t.getTopInset()) {
                        z = true;
                    }
                    savedState.c = z;
                    savedState.b = ((float) bottom) / ((float) childAt.getHeight());
                    return savedState;
                }
            }
            return b2;
        }

        public void a(CoordinatorLayout coordinatorLayout, T t, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                SavedState savedState = (SavedState) parcelable;
                super.a(coordinatorLayout, t, savedState.getSuperState());
                this.e = savedState.a;
                this.g = savedState.b;
                this.f = savedState.c;
                return;
            }
            super.a(coordinatorLayout, t, parcelable);
            this.e = -1;
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public /* bridge */ /* synthetic */ void a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.a(coordinatorLayout, appBarLayout, parcelable);
        }

        public /* bridge */ /* synthetic */ void a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
            super.a(coordinatorLayout, appBarLayout, view, i);
        }

        public /* bridge */ /* synthetic */ void a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int i3, int i4, int i5) {
            super.a(coordinatorLayout, appBarLayout, view, i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ void a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
            super.a(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
        }

        public /* bridge */ /* synthetic */ boolean a(int i) {
            return super.a(i);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            return super.a(coordinatorLayout, appBarLayout, i);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3, int i4) {
            return super.a(coordinatorLayout, appBarLayout, i, i2, i3, i4);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
            return super.a(coordinatorLayout, appBarLayout, view, view2, i, i2);
        }

        public /* bridge */ /* synthetic */ int b() {
            return super.b();
        }

        public /* bridge */ /* synthetic */ Parcelable b(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.b(coordinatorLayout, appBarLayout);
        }

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
        int a = 1;
        Interpolator b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarLayout_Layout);
            this.a = obtainStyledAttributes.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.b = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.widget.LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public int a() {
            return this.a;
        }

        public Interpolator b() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public boolean c() {
            return (this.a & 1) == 1 && (this.a & 10) != 0;
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public /* bridge */ /* synthetic */ boolean a(int i) {
            return super.a(i);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
            return super.a(coordinatorLayout, view, i);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return super.a(coordinatorLayout, view, i, i2, i3, i4);
        }

        public /* bridge */ /* synthetic */ int b() {
            return super.b();
        }

        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingViewBehavior_Layout);
            b(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            obtainStyledAttributes.recycle();
        }

        public boolean a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            a(view, view2);
            b(view, view2);
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            boolean z2;
            AppBarLayout a = b(coordinatorLayout.c(view));
            if (a != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.a;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    if (!z) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    a.a(false, z2);
                    return true;
                }
            }
            return false;
        }

        private void a(View view, View view2) {
            android.support.design.widget.CoordinatorLayout.Behavior b = ((d) view2.getLayoutParams()).b();
            if (b instanceof BaseBehavior) {
                int bottom = view2.getBottom() - view.getTop();
                ViewCompat.offsetTopAndBottom(view, ((((BaseBehavior) b).b + bottom) + a()) - c(view2));
            }
        }

        /* access modifiers changed from: 0000 */
        public float a(View view) {
            if (!(view instanceof AppBarLayout)) {
                return 0.0f;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
            int a = a(appBarLayout);
            if (downNestedPreScrollRange != 0 && totalScrollRange + a <= downNestedPreScrollRange) {
                return 0.0f;
            }
            int i = totalScrollRange - downNestedPreScrollRange;
            if (i != 0) {
                return 1.0f + (((float) a) / ((float) i));
            }
            return 0.0f;
        }

        private static int a(AppBarLayout appBarLayout) {
            android.support.design.widget.CoordinatorLayout.Behavior b = ((d) appBarLayout.getLayoutParams()).b();
            if (b instanceof BaseBehavior) {
                return ((BaseBehavior) b).a();
            }
            return 0;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public AppBarLayout b(List<View> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public int b(View view) {
            if (view instanceof AppBarLayout) {
                return ((AppBarLayout) view).getTotalScrollRange();
            }
            return super.b(view);
        }

        private void b(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.d()) {
                    appBarLayout.a(view.getScrollY() > 0);
                }
            }
        }
    }

    public interface a<T extends AppBarLayout> {
        void a(T t, int i);
    }

    public interface b extends a<AppBarLayout> {
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.e = 0;
        setOrientation(1);
        if (VERSION.SDK_INT >= 21) {
            q.a(this);
            q.a(this, attributeSet, 0, R.style.Widget_Design_AppBarLayout);
        }
        TypedArray a2 = c.a(context, attributeSet, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout, new int[0]);
        ViewCompat.setBackground(this, a2.getDrawable(R.styleable.AppBarLayout_android_background));
        if (a2.hasValue(R.styleable.AppBarLayout_expanded)) {
            a(a2.getBoolean(R.styleable.AppBarLayout_expanded, false), false, false);
        }
        if (VERSION.SDK_INT >= 21 && a2.hasValue(R.styleable.AppBarLayout_elevation)) {
            q.a(this, (float) a2.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0));
        }
        if (VERSION.SDK_INT >= 26) {
            if (a2.hasValue(R.styleable.AppBarLayout_android_keyboardNavigationCluster)) {
                setKeyboardNavigationCluster(a2.getBoolean(R.styleable.AppBarLayout_android_keyboardNavigationCluster, false));
            }
            if (a2.hasValue(R.styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
                setTouchscreenBlocksFocus(a2.getBoolean(R.styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
            }
        }
        this.k = a2.getBoolean(R.styleable.AppBarLayout_liftOnScroll, false);
        a2.recycle();
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return AppBarLayout.this.a(windowInsetsCompat);
            }
        });
    }

    public void a(a aVar) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (aVar != null && !this.g.contains(aVar)) {
            this.g.add(aVar);
        }
    }

    public void a(b bVar) {
        a((a) bVar);
    }

    public void b(a aVar) {
        if (this.g != null && aVar != null) {
            this.g.remove(aVar);
        }
    }

    public void b(b bVar) {
        b((a) bVar);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        g();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        g();
        this.d = false;
        int childCount = getChildCount();
        int i6 = 0;
        while (true) {
            if (i6 >= childCount) {
                break;
            } else if (((LayoutParams) getChildAt(i6).getLayoutParams()).b() != null) {
                this.d = true;
                break;
            } else {
                i6++;
            }
        }
        if (!this.h) {
            b(this.k || f());
        }
    }

    private boolean f() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).c()) {
                return true;
            }
        }
        return false;
    }

    private void g() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
    }

    public void setOrientation(int i2) {
        if (i2 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i2);
    }

    public void setExpanded(boolean z) {
        a(z, ViewCompat.isLaidOut(this));
    }

    public void a(boolean z, boolean z2) {
        a(z, z2, true);
    }

    private void a(boolean z, boolean z2, boolean z3) {
        int i2 = 0;
        int i3 = (z2 ? 4 : 0) | (z ? 1 : 2);
        if (z3) {
            i2 = 8;
        }
        this.e = i2 | i3;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (VERSION.SDK_INT >= 19 && (layoutParams instanceof android.widget.LinearLayout.LayoutParams)) {
            return new LayoutParams((android.widget.LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        return this.d;
    }

    public final int getTotalScrollRange() {
        int i2;
        if (this.a != -1) {
            return this.a;
        }
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = layoutParams.a;
            if ((i5 & 1) == 0) {
                break;
            }
            i4 += layoutParams.bottomMargin + measuredHeight + layoutParams.topMargin;
            if ((i5 & 2) != 0) {
                i2 = i4 - ViewCompat.getMinimumHeight(childAt);
                break;
            }
            i3++;
        }
        i2 = i4;
        int max = Math.max(0, i2 - getTopInset());
        this.a = max;
        return max;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return getTotalScrollRange() != 0;
    }

    /* access modifiers changed from: 0000 */
    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    /* access modifiers changed from: 0000 */
    public int getDownNestedPreScrollRange() {
        int i2;
        if (this.b != -1) {
            return this.b;
        }
        int childCount = getChildCount() - 1;
        int i3 = 0;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = layoutParams.a;
            if ((i4 & 5) == 5) {
                int i5 = layoutParams.bottomMargin + layoutParams.topMargin + i3;
                if ((i4 & 8) != 0) {
                    i2 = i5 + ViewCompat.getMinimumHeight(childAt);
                } else if ((i4 & 2) != 0) {
                    i2 = i5 + (measuredHeight - ViewCompat.getMinimumHeight(childAt));
                } else {
                    i2 = i5 + (measuredHeight - getTopInset());
                }
            } else if (i3 > 0) {
                break;
            } else {
                i2 = i3;
            }
            childCount--;
            i3 = i2;
        }
        int max = Math.max(0, i3);
        this.b = max;
        return max;
    }

    /* access modifiers changed from: 0000 */
    public int getDownNestedScrollRange() {
        int i2;
        if (this.c != -1) {
            return this.c;
        }
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int i5 = layoutParams.a;
            if ((i5 & 1) == 0) {
                break;
            }
            i4 += measuredHeight;
            if ((i5 & 2) != 0) {
                i2 = i4 - (ViewCompat.getMinimumHeight(childAt) + getTopInset());
                break;
            }
            i3++;
        }
        i2 = i4;
        int max = Math.max(0, i2);
        this.c = max;
        return max;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        if (this.g != null) {
            int size = this.g.size();
            for (int i3 = 0; i3 < size; i3++) {
                a aVar = (a) this.g.get(i3);
                if (aVar != null) {
                    aVar.a(this, i2);
                }
            }
        }
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight != 0) {
            return (minimumHeight * 2) + topInset;
        }
        int childCount = getChildCount();
        int i2 = childCount >= 1 ? ViewCompat.getMinimumHeight(getChildAt(childCount - 1)) : 0;
        if (i2 != 0) {
            return (i2 * 2) + topInset;
        }
        return getHeight() / 3;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i2) {
        if (this.l == null) {
            this.l = new int[4];
        }
        int[] iArr = this.l;
        int[] onCreateDrawableState = super.onCreateDrawableState(iArr.length + i2);
        iArr[0] = this.i ? R.attr.state_liftable : -R.attr.state_liftable;
        iArr[1] = (!this.i || !this.j) ? -R.attr.state_lifted : R.attr.state_lifted;
        iArr[2] = this.i ? R.attr.state_collapsible : -R.attr.state_collapsible;
        iArr[3] = (!this.i || !this.j) ? -R.attr.state_collapsed : R.attr.state_collapsed;
        return mergeDrawableStates(onCreateDrawableState, iArr);
    }

    private boolean b(boolean z) {
        if (this.i == z) {
            return false;
        }
        this.i = z;
        refreshDrawableState();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(boolean z) {
        if (this.j == z) {
            return false;
        }
        this.j = z;
        refreshDrawableState();
        return true;
    }

    public void setLiftOnScroll(boolean z) {
        this.k = z;
    }

    public boolean d() {
        return this.k;
    }

    @Deprecated
    public void setTargetElevation(float f2) {
        if (VERSION.SDK_INT >= 21) {
            q.a(this, f2);
        }
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    /* access modifiers changed from: 0000 */
    public int getPendingAction() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        this.e = 0;
    }

    /* access modifiers changed from: 0000 */
    public final int getTopInset() {
        if (this.f != null) {
            return this.f.getSystemWindowInsetTop();
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public WindowInsetsCompat a(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = null;
        if (ViewCompat.getFitsSystemWindows(this)) {
            windowInsetsCompat2 = windowInsetsCompat;
        }
        if (!ObjectsCompat.equals(this.f, windowInsetsCompat2)) {
            this.f = windowInsetsCompat2;
            g();
        }
        return windowInsetsCompat;
    }
}
