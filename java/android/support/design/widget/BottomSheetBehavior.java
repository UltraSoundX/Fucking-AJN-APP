package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior<V extends View> extends Behavior<V> {
    int a;
    int b;
    int c;
    boolean d;
    int e = 4;
    ViewDragHelper f;
    int g;
    WeakReference<V> h;
    WeakReference<View> i;
    int j;
    boolean k;
    /* access modifiers changed from: private */
    public boolean l = true;
    private float m;
    private int n;
    private boolean o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private int f321q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private a v;
    private VelocityTracker w;
    private int x;
    private Map<View, Integer> y;
    private final Callback z = new Callback() {
        public boolean tryCaptureView(View view, int i) {
            if (BottomSheetBehavior.this.e == 1 || BottomSheetBehavior.this.k) {
                return false;
            }
            if (BottomSheetBehavior.this.e == 3 && BottomSheetBehavior.this.j == i) {
                View view2 = (View) BottomSheetBehavior.this.i.get();
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            return BottomSheetBehavior.this.h != null && BottomSheetBehavior.this.h.get() == view;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.d(i2);
        }

        public void onViewDragStateChanged(int i) {
            if (i == 1) {
                BottomSheetBehavior.this.c(1);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            int i2 = 0;
            int i3 = 6;
            int i4 = 3;
            if (f2 < 0.0f) {
                if (BottomSheetBehavior.this.l) {
                    i2 = BottomSheetBehavior.this.a;
                } else {
                    if (view.getTop() > BottomSheetBehavior.this.b) {
                        i = BottomSheetBehavior.this.b;
                    } else {
                        i3 = 3;
                        i = 0;
                    }
                    i2 = i;
                    i4 = i3;
                }
            } else if (BottomSheetBehavior.this.d && BottomSheetBehavior.this.a(view, f2) && (view.getTop() > BottomSheetBehavior.this.c || Math.abs(f) < Math.abs(f2))) {
                i2 = BottomSheetBehavior.this.g;
                i4 = 5;
            } else if (f2 == 0.0f || Math.abs(f) > Math.abs(f2)) {
                int top = view.getTop();
                if (BottomSheetBehavior.this.l) {
                    if (Math.abs(top - BottomSheetBehavior.this.a) < Math.abs(top - BottomSheetBehavior.this.c)) {
                        i2 = BottomSheetBehavior.this.a;
                    } else {
                        i2 = BottomSheetBehavior.this.c;
                        i4 = 4;
                    }
                } else if (top < BottomSheetBehavior.this.b) {
                    if (top >= Math.abs(top - BottomSheetBehavior.this.c)) {
                        i2 = BottomSheetBehavior.this.b;
                        i4 = 6;
                    }
                } else if (Math.abs(top - BottomSheetBehavior.this.b) < Math.abs(top - BottomSheetBehavior.this.c)) {
                    i2 = BottomSheetBehavior.this.b;
                    i4 = 6;
                } else {
                    i2 = BottomSheetBehavior.this.c;
                    i4 = 4;
                }
            } else {
                i2 = BottomSheetBehavior.this.c;
                i4 = 4;
            }
            if (BottomSheetBehavior.this.f.settleCapturedViewAt(view.getLeft(), i2)) {
                BottomSheetBehavior.this.c(2);
                ViewCompat.postOnAnimation(view, new b(view, i4));
                return;
            }
            BottomSheetBehavior.this.c(i4);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return MathUtils.clamp(i, BottomSheetBehavior.this.f(), BottomSheetBehavior.this.d ? BottomSheetBehavior.this.g : BottomSheetBehavior.this.c);
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return view.getLeft();
        }

        public int getViewVerticalDragRange(View view) {
            if (BottomSheetBehavior.this.d) {
                return BottomSheetBehavior.this.g;
            }
            return BottomSheetBehavior.this.c;
        }
    };

    protected static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final int a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.a = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public static abstract class a {
        public abstract void a(View view, float f);

        public abstract void a(View view, int i);
    }

    private class b implements Runnable {
        private final View b;
        private final int c;

        b(View view, int i) {
            this.b = view;
            this.c = i;
        }

        public void run() {
            if (BottomSheetBehavior.this.f == null || !BottomSheetBehavior.this.f.continueSettling(true)) {
                BottomSheetBehavior.this.c(this.c);
            } else {
                ViewCompat.postOnAnimation(this.b, this);
            }
        }
    }

    public BottomSheetBehavior() {
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        TypedValue peekValue = obtainStyledAttributes.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue == null || peekValue.data != -1) {
            a(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            a(peekValue.data);
        }
        b(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        a(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        c(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        obtainStyledAttributes.recycle();
        this.m = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public Parcelable b(CoordinatorLayout coordinatorLayout, V v2) {
        return new SavedState(super.b(coordinatorLayout, v2), this.e);
    }

    public void a(CoordinatorLayout coordinatorLayout, V v2, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.a(coordinatorLayout, v2, savedState.getSuperState());
        if (savedState.a == 1 || savedState.a == 2) {
            this.e = 4;
        } else {
            this.e = savedState.a;
        }
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v2, int i2) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v2)) {
            v2.setFitsSystemWindows(true);
        }
        int top = v2.getTop();
        coordinatorLayout.a((View) v2, i2);
        this.g = coordinatorLayout.getHeight();
        if (this.o) {
            if (this.p == 0) {
                this.p = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            }
            this.f321q = Math.max(this.p, this.g - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            this.f321q = this.n;
        }
        this.a = Math.max(0, this.g - v2.getHeight());
        this.b = this.g / 2;
        b();
        if (this.e == 3) {
            ViewCompat.offsetTopAndBottom(v2, f());
        } else if (this.e == 6) {
            ViewCompat.offsetTopAndBottom(v2, this.b);
        } else if (this.d && this.e == 5) {
            ViewCompat.offsetTopAndBottom(v2, this.g);
        } else if (this.e == 4) {
            ViewCompat.offsetTopAndBottom(v2, this.c);
        } else if (this.e == 1 || this.e == 2) {
            ViewCompat.offsetTopAndBottom(v2, top - v2.getTop());
        }
        if (this.f == null) {
            this.f = ViewDragHelper.create(coordinatorLayout, this.z);
        }
        this.h = new WeakReference<>(v2);
        this.i = new WeakReference<>(a((View) v2));
        return true;
    }

    public boolean b(CoordinatorLayout coordinatorLayout, V v2, MotionEvent motionEvent) {
        View view;
        boolean z2 = true;
        if (!v2.isShown()) {
            this.s = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            d();
        }
        if (this.w == null) {
            this.w = VelocityTracker.obtain();
        }
        this.w.addMovement(motionEvent);
        switch (actionMasked) {
            case 0:
                int x2 = (int) motionEvent.getX();
                this.x = (int) motionEvent.getY();
                View view2 = this.i != null ? (View) this.i.get() : null;
                if (view2 != null && coordinatorLayout.a(view2, x2, this.x)) {
                    this.j = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.k = true;
                }
                this.s = this.j == -1 && !coordinatorLayout.a((View) v2, x2, this.x);
                break;
            case 1:
            case 3:
                this.k = false;
                this.j = -1;
                if (this.s) {
                    this.s = false;
                    return false;
                }
                break;
        }
        if (!this.s && this.f != null && this.f.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        if (this.i != null) {
            view = (View) this.i.get();
        } else {
            view = null;
        }
        if (actionMasked != 2 || view == null || this.s || this.e == 1 || coordinatorLayout.a(view, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.f == null || Math.abs(((float) this.x) - motionEvent.getY()) <= ((float) this.f.getTouchSlop())) {
            z2 = false;
        }
        return z2;
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v2, MotionEvent motionEvent) {
        if (!v2.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.e == 1 && actionMasked == 0) {
            return true;
        }
        if (this.f != null) {
            this.f.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            d();
        }
        if (this.w == null) {
            this.w = VelocityTracker.obtain();
        }
        this.w.addMovement(motionEvent);
        if (actionMasked == 2 && !this.s && Math.abs(((float) this.x) - motionEvent.getY()) > ((float) this.f.getTouchSlop())) {
            this.f.captureChildView(v2, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        if (this.s) {
            return false;
        }
        return true;
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v2, View view, View view2, int i2, int i3) {
        this.t = 0;
        this.u = false;
        if ((i2 & 2) != 0) {
            return true;
        }
        return false;
    }

    public void a(CoordinatorLayout coordinatorLayout, V v2, View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 != 1 && view == ((View) this.i.get())) {
            int top = v2.getTop();
            int i5 = top - i3;
            if (i3 > 0) {
                if (i5 < f()) {
                    iArr[1] = top - f();
                    ViewCompat.offsetTopAndBottom(v2, -iArr[1]);
                    c(3);
                } else {
                    iArr[1] = i3;
                    ViewCompat.offsetTopAndBottom(v2, -i3);
                    c(1);
                }
            } else if (i3 < 0 && !view.canScrollVertically(-1)) {
                if (i5 <= this.c || this.d) {
                    iArr[1] = i3;
                    ViewCompat.offsetTopAndBottom(v2, -i3);
                    c(1);
                } else {
                    iArr[1] = top - this.c;
                    ViewCompat.offsetTopAndBottom(v2, -iArr[1]);
                    c(4);
                }
            }
            d(v2.getTop());
            this.t = i3;
            this.u = true;
        }
    }

    public void a(CoordinatorLayout coordinatorLayout, V v2, View view, int i2) {
        int i3;
        int i4 = 3;
        if (v2.getTop() == f()) {
            c(3);
        } else if (view == this.i.get() && this.u) {
            if (this.t > 0) {
                i3 = f();
            } else if (this.d && a((View) v2, e())) {
                i3 = this.g;
                i4 = 5;
            } else if (this.t == 0) {
                int top = v2.getTop();
                if (this.l) {
                    if (Math.abs(top - this.a) < Math.abs(top - this.c)) {
                        i3 = this.a;
                    } else {
                        i3 = this.c;
                        i4 = 4;
                    }
                } else if (top < this.b) {
                    if (top < Math.abs(top - this.c)) {
                        i3 = 0;
                    } else {
                        i3 = this.b;
                        i4 = 6;
                    }
                } else if (Math.abs(top - this.b) < Math.abs(top - this.c)) {
                    i3 = this.b;
                    i4 = 6;
                } else {
                    i3 = this.c;
                    i4 = 4;
                }
            } else {
                i3 = this.c;
                i4 = 4;
            }
            if (this.f.smoothSlideViewTo(v2, v2.getLeft(), i3)) {
                c(2);
                ViewCompat.postOnAnimation(v2, new b(v2, i4));
            } else {
                c(i4);
            }
            this.u = false;
        }
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v2, View view, float f2, float f3) {
        return view == this.i.get() && (this.e != 3 || super.a(coordinatorLayout, v2, view, f2, f3));
    }

    public void a(boolean z2) {
        if (this.l != z2) {
            this.l = z2;
            if (this.h != null) {
                b();
            }
            c((!this.l || this.e != 6) ? this.e : 3);
        }
    }

    public final void a(int i2) {
        boolean z2 = true;
        if (i2 == -1) {
            if (!this.o) {
                this.o = true;
            }
            z2 = false;
        } else {
            if (this.o || this.n != i2) {
                this.o = false;
                this.n = Math.max(0, i2);
                this.c = this.g - i2;
            }
            z2 = false;
        }
        if (z2 && this.e == 4 && this.h != null) {
            View view = (View) this.h.get();
            if (view != null) {
                view.requestLayout();
            }
        }
    }

    public void b(boolean z2) {
        this.d = z2;
    }

    public void c(boolean z2) {
        this.r = z2;
    }

    public void a(a aVar) {
        this.v = aVar;
    }

    public final void b(final int i2) {
        if (i2 != this.e) {
            if (this.h != null) {
                final View view = (View) this.h.get();
                if (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view)) {
                        a(view, i2);
                    } else {
                        view.post(new Runnable() {
                            public void run() {
                                BottomSheetBehavior.this.a(view, i2);
                            }
                        });
                    }
                }
            } else if (i2 == 4 || i2 == 3 || i2 == 6 || (this.d && i2 == 5)) {
                this.e = i2;
            }
        }
    }

    public final int a() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2) {
        if (this.e != i2) {
            this.e = i2;
            if (i2 == 6 || i2 == 3) {
                d(true);
            } else if (i2 == 5 || i2 == 4) {
                d(false);
            }
            View view = (View) this.h.get();
            if (view != null && this.v != null) {
                this.v.a(view, i2);
            }
        }
    }

    private void b() {
        if (this.l) {
            this.c = Math.max(this.g - this.f321q, this.a);
        } else {
            this.c = this.g - this.f321q;
        }
    }

    private void d() {
        this.j = -1;
        if (this.w != null) {
            this.w.recycle();
            this.w = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(View view, float f2) {
        if (this.r) {
            return true;
        }
        if (view.getTop() < this.c) {
            return false;
        }
        if (Math.abs((((float) view.getTop()) + (0.1f * f2)) - ((float) this.c)) / ((float) this.n) <= 0.5f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public View a(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View a2 = a(viewGroup.getChildAt(i2));
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    private float e() {
        if (this.w == null) {
            return 0.0f;
        }
        this.w.computeCurrentVelocity(1000, this.m);
        return this.w.getYVelocity(this.j);
    }

    /* access modifiers changed from: private */
    public int f() {
        if (this.l) {
            return this.a;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public void a(View view, int i2) {
        int i3;
        if (i2 == 4) {
            i3 = this.c;
        } else if (i2 == 6) {
            i3 = this.b;
            if (this.l && i3 <= this.a) {
                i3 = this.a;
                i2 = 3;
            }
        } else if (i2 == 3) {
            i3 = f();
        } else if (!this.d || i2 != 5) {
            throw new IllegalArgumentException("Illegal state argument: " + i2);
        } else {
            i3 = this.g;
        }
        if (this.f.smoothSlideViewTo(view, view.getLeft(), i3)) {
            c(2);
            ViewCompat.postOnAnimation(view, new b(view, i2));
            return;
        }
        c(i2);
    }

    /* access modifiers changed from: 0000 */
    public void d(int i2) {
        View view = (View) this.h.get();
        if (view != null && this.v != null) {
            if (i2 > this.c) {
                this.v.a(view, ((float) (this.c - i2)) / ((float) (this.g - this.c)));
            } else {
                this.v.a(view, ((float) (this.c - i2)) / ((float) (this.c - f())));
            }
        }
    }

    public static <V extends View> BottomSheetBehavior<V> b(V v2) {
        LayoutParams layoutParams = v2.getLayoutParams();
        if (!(layoutParams instanceof d)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        Behavior b2 = ((d) layoutParams).b();
        if (b2 instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) b2;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    private void d(boolean z2) {
        if (this.h != null) {
            ViewParent parent = ((View) this.h.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (VERSION.SDK_INT >= 16 && z2) {
                    if (this.y == null) {
                        this.y = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = coordinatorLayout.getChildAt(i2);
                    if (childAt != this.h.get()) {
                        if (z2) {
                            if (VERSION.SDK_INT >= 16) {
                                this.y.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                            }
                            ViewCompat.setImportantForAccessibility(childAt, 4);
                        } else if (this.y != null && this.y.containsKey(childAt)) {
                            ViewCompat.setImportantForAccessibility(childAt, ((Integer) this.y.get(childAt)).intValue());
                        }
                    }
                }
                if (!z2) {
                    this.y = null;
                }
            }
        }
    }
}
