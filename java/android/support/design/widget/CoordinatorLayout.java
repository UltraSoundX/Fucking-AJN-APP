package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.coordinatorlayout.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DirectedAcyclicGraph;
import android.support.v4.widget.ViewGroupUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2 {
    static final String a;
    static final Class<?>[] b = {Context.class, AttributeSet.class};
    static final ThreadLocal<Map<String, Constructor<Behavior>>> c = new ThreadLocal<>();
    static final Comparator<View> d;
    private static final Pool<Rect> f = new SynchronizedPool(12);
    OnHierarchyChangeListener e;
    private final List<View> g;
    private final DirectedAcyclicGraph<View> h;
    private final List<View> i;
    private final List<View> j;
    private final int[] k;
    private Paint l;
    private boolean m;
    private boolean n;
    private int[] o;
    private View p;

    /* renamed from: q reason: collision with root package name */
    private View f323q;
    private e r;
    private boolean s;
    private WindowInsetsCompat t;
    private boolean u;
    private Drawable v;
    private OnApplyWindowInsetsListener w;
    private final NestedScrollingParentHelper x;

    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }

        public void a(d dVar) {
        }

        public void c() {
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public int c(CoordinatorLayout coordinatorLayout, V v) {
            return -16777216;
        }

        public float d(CoordinatorLayout coordinatorLayout, V v) {
            return 0.0f;
        }

        public boolean e(CoordinatorLayout coordinatorLayout, V v) {
            return d(coordinatorLayout, v) > 0.0f;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public void c(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
            return false;
        }

        @Deprecated
        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                return a(coordinatorLayout, v, view, view2, i);
            }
            return false;
        }

        @Deprecated
        public void b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        }

        public void b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                b(coordinatorLayout, v, view, view2, i);
            }
        }

        @Deprecated
        public void d(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
            if (i == 0) {
                d(coordinatorLayout, v, view);
            }
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5) {
            if (i5 == 0) {
                a(coordinatorLayout, v, view, i, i2, i3, i4);
            }
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
            if (i3 == 0) {
                a(coordinatorLayout, v, view, i, i2, iArr);
            }
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2, boolean z) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
            return false;
        }

        public WindowInsetsCompat a(CoordinatorLayout coordinatorLayout, V v, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, Rect rect, boolean z) {
            return false;
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        }

        public Parcelable b(CoordinatorLayout coordinatorLayout, V v) {
            return BaseSavedState.EMPTY_STATE;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, Rect rect) {
            return false;
        }
    }

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
        SparseArray<Parcelable> a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.a = new SparseArray<>(readInt);
            for (int i = 0; i < readInt; i++) {
                this.a.append(iArr[i], readParcelableArray[i]);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            int i2 = this.a != null ? this.a.size() : 0;
            parcel.writeInt(i2);
            int[] iArr = new int[i2];
            Parcelable[] parcelableArr = new Parcelable[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                iArr[i3] = this.a.keyAt(i3);
                parcelableArr[i3] = (Parcelable) this.a.valueAt(i3);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }
    }

    public interface a {
        Behavior getBehavior();
    }

    @Deprecated
    @Retention(RetentionPolicy.RUNTIME)
    public @interface b {
        Class<? extends Behavior> a();
    }

    private class c implements OnHierarchyChangeListener {
        c() {
        }

        public void onChildViewAdded(View view, View view2) {
            if (CoordinatorLayout.this.e != null) {
                CoordinatorLayout.this.e.onChildViewAdded(view, view2);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.a(2);
            if (CoordinatorLayout.this.e != null) {
                CoordinatorLayout.this.e.onChildViewRemoved(view, view2);
            }
        }
    }

    public static class d extends MarginLayoutParams {
        Behavior a;
        boolean b = false;
        public int c = 0;
        public int d = 0;
        public int e = -1;
        int f = -1;
        public int g = 0;
        public int h = 0;
        int i;
        int j;
        View k;
        View l;
        final Rect m = new Rect();
        Object n;
        private boolean o;
        private boolean p;

        /* renamed from: q reason: collision with root package name */
        private boolean f324q;
        private boolean r;

        public d(int i2, int i3) {
            super(i2, i3);
        }

        d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout_Layout);
            this.c = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.d = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.e = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = obtainStyledAttributes.getInt(R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = obtainStyledAttributes.getInt(R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.b = obtainStyledAttributes.hasValue(R.styleable.CoordinatorLayout_Layout_layout_behavior);
            if (this.b) {
                this.a = CoordinatorLayout.a(context, attributeSet, obtainStyledAttributes.getString(R.styleable.CoordinatorLayout_Layout_layout_behavior));
            }
            obtainStyledAttributes.recycle();
            if (this.a != null) {
                this.a.a(this);
            }
        }

        public d(d dVar) {
            super(dVar);
        }

        public d(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public d(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public int a() {
            return this.f;
        }

        public Behavior b() {
            return this.a;
        }

        public void a(Behavior behavior) {
            if (this.a != behavior) {
                if (this.a != null) {
                    this.a.c();
                }
                this.a = behavior;
                this.n = null;
                this.b = true;
                if (behavior != null) {
                    behavior.a(this);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect) {
            this.m.set(rect);
        }

        /* access modifiers changed from: 0000 */
        public Rect c() {
            return this.m;
        }

        /* access modifiers changed from: 0000 */
        public boolean d() {
            return this.k == null && this.f != -1;
        }

        /* access modifiers changed from: 0000 */
        public boolean e() {
            if (this.a == null) {
                this.o = false;
            }
            return this.o;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(CoordinatorLayout coordinatorLayout, View view) {
            if (this.o) {
                return true;
            }
            boolean e2 = (this.a != null ? this.a.e(coordinatorLayout, view) : false) | this.o;
            this.o = e2;
            return e2;
        }

        /* access modifiers changed from: 0000 */
        public void f() {
            this.o = false;
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2) {
            a(i2, false);
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2, boolean z) {
            switch (i2) {
                case 0:
                    this.p = z;
                    return;
                case 1:
                    this.f324q = z;
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean b(int i2) {
            switch (i2) {
                case 0:
                    return this.p;
                case 1:
                    return this.f324q;
                default:
                    return false;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean g() {
            return this.r;
        }

        /* access modifiers changed from: 0000 */
        public void a(boolean z) {
            this.r = z;
        }

        /* access modifiers changed from: 0000 */
        public void h() {
            this.r = false;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 == this.l || a(view2, ViewCompat.getLayoutDirection(coordinatorLayout)) || (this.a != null && this.a.a(coordinatorLayout, view, view2));
        }

        /* access modifiers changed from: 0000 */
        public View b(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f == -1) {
                this.l = null;
                this.k = null;
                return null;
            }
            if (this.k == null || !b(view, coordinatorLayout)) {
                a(view, coordinatorLayout);
            }
            return this.k;
        }

        private void a(View view, CoordinatorLayout coordinatorLayout) {
            this.k = coordinatorLayout.findViewById(this.f);
            if (this.k != null) {
                if (this.k != coordinatorLayout) {
                    View view2 = this.k;
                    ViewParent parent = this.k.getParent();
                    while (parent != coordinatorLayout && parent != null) {
                        if (parent != view) {
                            if (parent instanceof View) {
                                view2 = (View) parent;
                            }
                            parent = parent.getParent();
                        } else if (coordinatorLayout.isInEditMode()) {
                            this.l = null;
                            this.k = null;
                            return;
                        } else {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                    }
                    this.l = view2;
                } else if (coordinatorLayout.isInEditMode()) {
                    this.l = null;
                    this.k = null;
                } else {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
            } else if (coordinatorLayout.isInEditMode()) {
                this.l = null;
                this.k = null;
            } else {
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f) + " to anchor view " + view);
            }
        }

        private boolean b(View view, CoordinatorLayout coordinatorLayout) {
            if (this.k.getId() != this.f) {
                return false;
            }
            View view2 = this.k;
            for (ViewParent parent = this.k.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.l = null;
                    this.k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = (View) parent;
                }
            }
            this.l = view2;
            return true;
        }

        private boolean a(View view, int i2) {
            int absoluteGravity = GravityCompat.getAbsoluteGravity(((d) view.getLayoutParams()).g, i2);
            return absoluteGravity != 0 && (GravityCompat.getAbsoluteGravity(this.h, i2) & absoluteGravity) == absoluteGravity;
        }
    }

    class e implements OnPreDrawListener {
        e() {
        }

        public boolean onPreDraw() {
            CoordinatorLayout.this.a(0);
            return true;
        }
    }

    static class f implements Comparator<View> {
        f() {
        }

        /* renamed from: a */
        public int compare(View view, View view2) {
            float z = ViewCompat.getZ(view);
            float z2 = ViewCompat.getZ(view2);
            if (z > z2) {
                return -1;
            }
            if (z < z2) {
                return 1;
            }
            return 0;
        }
    }

    static {
        Package packageR = CoordinatorLayout.class.getPackage();
        a = packageR != null ? packageR.getName() : null;
        if (VERSION.SDK_INT >= 21) {
            d = new f();
        } else {
            d = null;
        }
    }

    private static Rect a() {
        Rect rect = (Rect) f.acquire();
        if (rect == null) {
            return new Rect();
        }
        return rect;
    }

    private static void a(Rect rect) {
        rect.setEmpty();
        f.release(rect);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes;
        super(context, attributeSet, i2);
        this.g = new ArrayList();
        this.h = new DirectedAcyclicGraph<>();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new int[2];
        this.x = new NestedScrollingParentHelper(this);
        if (i2 == 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout, 0, R.style.Widget_Support_CoordinatorLayout);
        } else {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorLayout, i2, 0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.o = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.o.length;
            for (int i3 = 0; i3 < length; i3++) {
                this.o[i3] = (int) (((float) this.o[i3]) * f2);
            }
        }
        this.v = obtainStyledAttributes.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        c();
        super.setOnHierarchyChangeListener(new c());
    }

    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
        this.e = onHierarchyChangeListener;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(false);
        if (this.s) {
            if (this.r == null) {
                this.r = new e();
            }
            getViewTreeObserver().addOnPreDrawListener(this.r);
        }
        if (this.t == null && ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.requestApplyInsets(this);
        }
        this.n = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a(false);
        if (this.s && this.r != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.r);
        }
        if (this.f323q != null) {
            onStopNestedScroll(this.f323q);
        }
        this.n = false;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = null;
        if (this.v != drawable) {
            if (this.v != null) {
                this.v.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.v = drawable2;
            if (this.v != null) {
                if (this.v.isStateful()) {
                    this.v.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.v, ViewCompat.getLayoutDirection(this));
                this.v.setVisible(getVisibility() == 0, false);
                this.v.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public Drawable getStatusBarBackground() {
        return this.v;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        boolean z = false;
        Drawable drawable = this.v;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.v;
    }

    public void setVisibility(int i2) {
        boolean z;
        super.setVisibility(i2);
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.v != null && this.v.isVisible() != z) {
            this.v.setVisible(z, false);
        }
    }

    public void setStatusBarBackgroundResource(int i2) {
        setStatusBarBackground(i2 != 0 ? ContextCompat.getDrawable(getContext(), i2) : null);
    }

    public void setStatusBarBackgroundColor(int i2) {
        setStatusBarBackground(new ColorDrawable(i2));
    }

    /* access modifiers changed from: 0000 */
    public final WindowInsetsCompat a(WindowInsetsCompat windowInsetsCompat) {
        boolean z = true;
        if (ObjectsCompat.equals(this.t, windowInsetsCompat)) {
            return windowInsetsCompat;
        }
        this.t = windowInsetsCompat;
        this.u = windowInsetsCompat != null && windowInsetsCompat.getSystemWindowInsetTop() > 0;
        if (this.u || getBackground() != null) {
            z = false;
        }
        setWillNotDraw(z);
        WindowInsetsCompat b2 = b(windowInsetsCompat);
        requestLayout();
        return b2;
    }

    public final WindowInsetsCompat getLastWindowInsets() {
        return this.t;
    }

    private void a(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            Behavior b2 = ((d) childAt.getLayoutParams()).b();
            if (b2 != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    b2.b(this, childAt, obtain);
                } else {
                    b2.a(this, childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            ((d) getChildAt(i3).getLayoutParams()).f();
        }
        this.p = null;
        this.m = false;
    }

    private void a(List<View> list) {
        int i2;
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            if (isChildrenDrawingOrderEnabled) {
                i2 = getChildDrawingOrder(childCount, i3);
            } else {
                i2 = i3;
            }
            list.add(getChildAt(i2));
        }
        if (d != null) {
            Collections.sort(list, d);
        }
    }

    private boolean a(MotionEvent motionEvent, int i2) {
        boolean z;
        boolean z2;
        MotionEvent motionEvent2;
        boolean z3 = false;
        boolean z4 = false;
        MotionEvent motionEvent3 = null;
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.i;
        a(list);
        int size = list.size();
        int i3 = 0;
        while (true) {
            if (i3 < size) {
                View view = (View) list.get(i3);
                d dVar = (d) view.getLayoutParams();
                Behavior b2 = dVar.b();
                if ((!z3 && !z4) || actionMasked == 0) {
                    if (!z3 && b2 != null) {
                        switch (i2) {
                            case 0:
                                z3 = b2.b(this, view, motionEvent);
                                break;
                            case 1:
                                z3 = b2.a(this, view, motionEvent);
                                break;
                        }
                        if (z3) {
                            this.p = view;
                        }
                    }
                    z = z3;
                    boolean e2 = dVar.e();
                    boolean a2 = dVar.a(this, view);
                    boolean z5 = a2 && !e2;
                    if (!a2 || z5) {
                        MotionEvent motionEvent4 = motionEvent3;
                        z2 = z5;
                        motionEvent2 = motionEvent4;
                    }
                } else if (b2 != null) {
                    if (motionEvent3 == null) {
                        long uptimeMillis = SystemClock.uptimeMillis();
                        motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    } else {
                        motionEvent2 = motionEvent3;
                    }
                    switch (i2) {
                        case 0:
                            b2.b(this, view, motionEvent2);
                            break;
                        case 1:
                            b2.a(this, view, motionEvent2);
                            break;
                    }
                    z2 = z4;
                    z = z3;
                } else {
                    motionEvent2 = motionEvent3;
                    z = z3;
                    z2 = z4;
                }
                i3++;
                z4 = z2;
                z3 = z;
                motionEvent3 = motionEvent2;
            } else {
                z = z3;
            }
        }
        list.clear();
        return z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            a(true);
        }
        boolean a2 = a(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            a(true);
        }
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r12) {
        /*
            r11 = this;
            r4 = 3
            r10 = 1
            r5 = 0
            r7 = 0
            r2 = 0
            int r9 = r12.getActionMasked()
            android.view.View r0 = r11.p
            if (r0 != 0) goto L_0x005d
            boolean r0 = r11.a(r12, r10)
            if (r0 == 0) goto L_0x005a
            r1 = r0
        L_0x0014:
            android.view.View r0 = r11.p
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.support.design.widget.CoordinatorLayout$d r0 = (android.support.design.widget.CoordinatorLayout.d) r0
            android.support.design.widget.CoordinatorLayout$Behavior r0 = r0.b()
            if (r0 == 0) goto L_0x0058
            android.view.View r3 = r11.p
            boolean r0 = r0.a(r11, r3, r12)
            r8 = r0
        L_0x0029:
            android.view.View r0 = r11.p
            if (r0 != 0) goto L_0x0043
            boolean r0 = super.onTouchEvent(r12)
            r8 = r8 | r0
        L_0x0032:
            if (r8 != 0) goto L_0x0036
            if (r9 != 0) goto L_0x0036
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.recycle()
        L_0x003b:
            if (r9 == r10) goto L_0x003f
            if (r9 != r4) goto L_0x0042
        L_0x003f:
            r11.a(r7)
        L_0x0042:
            return r8
        L_0x0043:
            if (r1 == 0) goto L_0x0032
            if (r2 != 0) goto L_0x0056
            long r0 = android.os.SystemClock.uptimeMillis()
            r2 = r0
            r6 = r5
            android.view.MotionEvent r0 = android.view.MotionEvent.obtain(r0, r2, r4, r5, r6, r7)
        L_0x0051:
            super.onTouchEvent(r0)
            r2 = r0
            goto L_0x0032
        L_0x0056:
            r0 = r2
            goto L_0x0051
        L_0x0058:
            r8 = r7
            goto L_0x0029
        L_0x005a:
            r1 = r0
            r8 = r7
            goto L_0x0029
        L_0x005d:
            r1 = r7
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.m) {
            a(false);
            this.m = true;
        }
    }

    private int b(int i2) {
        if (this.o == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i2);
            return 0;
        } else if (i2 >= 0 && i2 < this.o.length) {
            return this.o[i2];
        } else {
            Log.e("CoordinatorLayout", "Keyline index " + i2 + " out of range for " + this);
            return 0;
        }
    }

    static Behavior a(Context context, AttributeSet attributeSet, String str) {
        Map map;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0 && !TextUtils.isEmpty(a)) {
            str = a + '.' + str;
        }
        try {
            Map map2 = (Map) c.get();
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                c.set(hashMap);
                map = hashMap;
            } else {
                map = map2;
            }
            Constructor constructor = (Constructor) map.get(str);
            if (constructor == null) {
                constructor = context.getClassLoader().loadClass(str).getConstructor(b);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (Behavior) constructor.newInstance(new Object[]{context, attributeSet});
        } catch (Exception e2) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e2);
        }
    }

    /* access modifiers changed from: 0000 */
    public d a(View view) {
        d dVar = (d) view.getLayoutParams();
        if (!dVar.b) {
            if (view instanceof a) {
                Behavior behavior = ((a) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                dVar.a(behavior);
                dVar.b = true;
            } else {
                b bVar = null;
                for (Class cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    bVar = (b) cls.getAnnotation(b.class);
                    if (bVar != null) {
                        break;
                    }
                }
                b bVar2 = bVar;
                if (bVar2 != null) {
                    try {
                        dVar.a((Behavior) bVar2.a().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e2) {
                        Log.e("CoordinatorLayout", "Default behavior class " + bVar2.a().getName() + " could not be instantiated. Did you forget" + " a default constructor?", e2);
                    }
                }
                dVar.b = true;
            }
        }
        return dVar;
    }

    private void b() {
        this.g.clear();
        this.h.clear();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            d a2 = a(childAt);
            a2.b(this, childAt);
            this.h.addNode(childAt);
            for (int i3 = 0; i3 < childCount; i3++) {
                if (i3 != i2) {
                    View childAt2 = getChildAt(i3);
                    if (a2.a(this, childAt, childAt2)) {
                        if (!this.h.contains(childAt2)) {
                            this.h.addNode(childAt2);
                        }
                        this.h.addEdge(childAt2, childAt);
                    }
                }
            }
        }
        this.g.addAll(this.h.getSortedList());
        Collections.reverse(this.g);
    }

    /* access modifiers changed from: 0000 */
    public void a(View view, Rect rect) {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    public void a(View view, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        boolean z;
        int i4;
        int i5;
        int max;
        int combineMeasuredStates;
        int i6;
        b();
        d();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 1) {
            z = true;
        } else {
            z = false;
        }
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        int i7 = paddingLeft + paddingRight;
        int i8 = paddingTop + paddingBottom;
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int i9 = 0;
        boolean z2 = this.t != null && ViewCompat.getFitsSystemWindows(this);
        int size3 = this.g.size();
        int i10 = 0;
        while (i10 < size3) {
            View view = (View) this.g.get(i10);
            if (view.getVisibility() == 8) {
                combineMeasuredStates = i9;
                max = suggestedMinimumHeight;
                i6 = suggestedMinimumWidth;
            } else {
                d dVar = (d) view.getLayoutParams();
                int i11 = 0;
                if (dVar.e >= 0 && mode != 0) {
                    int b2 = b(dVar.e);
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(d(dVar.c), layoutDirection) & 7;
                    if ((absoluteGravity == 3 && !z) || (absoluteGravity == 5 && z)) {
                        i11 = Math.max(0, (size - paddingRight) - b2);
                    } else if ((absoluteGravity == 5 && !z) || (absoluteGravity == 3 && z)) {
                        i11 = Math.max(0, b2 - paddingLeft);
                    }
                }
                if (!z2 || ViewCompat.getFitsSystemWindows(view)) {
                    i4 = i3;
                    i5 = i2;
                } else {
                    int systemWindowInsetTop = this.t.getSystemWindowInsetTop() + this.t.getSystemWindowInsetBottom();
                    i5 = MeasureSpec.makeMeasureSpec(size - (this.t.getSystemWindowInsetLeft() + this.t.getSystemWindowInsetRight()), mode);
                    i4 = MeasureSpec.makeMeasureSpec(size2 - systemWindowInsetTop, mode2);
                }
                Behavior b3 = dVar.b();
                if (b3 == null || !b3.a(this, view, i5, i11, i4, 0)) {
                    a(view, i5, i11, i4, 0);
                }
                int max2 = Math.max(suggestedMinimumWidth, view.getMeasuredWidth() + i7 + dVar.leftMargin + dVar.rightMargin);
                max = Math.max(suggestedMinimumHeight, view.getMeasuredHeight() + i8 + dVar.topMargin + dVar.bottomMargin);
                combineMeasuredStates = View.combineMeasuredStates(i9, view.getMeasuredState());
                i6 = max2;
            }
            i10++;
            i9 = combineMeasuredStates;
            suggestedMinimumHeight = max;
            suggestedMinimumWidth = i6;
        }
        setMeasuredDimension(View.resolveSizeAndState(suggestedMinimumWidth, i2, -16777216 & i9), View.resolveSizeAndState(suggestedMinimumHeight, i3, i9 << 16));
    }

    private WindowInsetsCompat b(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2;
        if (windowInsetsCompat.isConsumed()) {
            return windowInsetsCompat;
        }
        int childCount = getChildCount();
        int i2 = 0;
        WindowInsetsCompat windowInsetsCompat3 = windowInsetsCompat;
        while (true) {
            if (i2 >= childCount) {
                windowInsetsCompat2 = windowInsetsCompat3;
                break;
            }
            View childAt = getChildAt(i2);
            if (ViewCompat.getFitsSystemWindows(childAt)) {
                Behavior b2 = ((d) childAt.getLayoutParams()).b();
                if (b2 != null) {
                    windowInsetsCompat2 = b2.a(this, childAt, windowInsetsCompat3);
                    if (windowInsetsCompat2.isConsumed()) {
                        break;
                    }
                    i2++;
                    windowInsetsCompat3 = windowInsetsCompat2;
                }
            }
            windowInsetsCompat2 = windowInsetsCompat3;
            i2++;
            windowInsetsCompat3 = windowInsetsCompat2;
        }
        return windowInsetsCompat2;
    }

    public void a(View view, int i2) {
        d dVar = (d) view.getLayoutParams();
        if (dVar.d()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (dVar.k != null) {
            a(view, dVar.k, i2);
        } else if (dVar.e >= 0) {
            b(view, dVar.e, i2);
        } else {
            c(view, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int size = this.g.size();
        for (int i6 = 0; i6 < size; i6++) {
            View view = (View) this.g.get(i6);
            if (view.getVisibility() != 8) {
                Behavior b2 = ((d) view.getLayoutParams()).b();
                if (b2 == null || !b2.a(this, view, layoutDirection)) {
                    a(view, layoutDirection);
                }
            }
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.u && this.v != null) {
            int i2 = this.t != null ? this.t.getSystemWindowInsetTop() : 0;
            if (i2 > 0) {
                this.v.setBounds(0, 0, getWidth(), i2);
                this.v.draw(canvas);
            }
        }
    }

    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        c();
    }

    /* access modifiers changed from: 0000 */
    public void b(View view, Rect rect) {
        ((d) view.getLayoutParams()).a(rect);
    }

    /* access modifiers changed from: 0000 */
    public void c(View view, Rect rect) {
        rect.set(((d) view.getLayoutParams()).c());
    }

    /* access modifiers changed from: 0000 */
    public void a(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            a(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    private void a(View view, int i2, Rect rect, Rect rect2, d dVar, int i3, int i4) {
        int width;
        int height;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(e(dVar.c), i2);
        int absoluteGravity2 = GravityCompat.getAbsoluteGravity(c(dVar.d), i2);
        int i5 = absoluteGravity & 7;
        int i6 = absoluteGravity & 112;
        int i7 = absoluteGravity2 & 112;
        switch (absoluteGravity2 & 7) {
            case 1:
                width = (rect.width() / 2) + rect.left;
                break;
            case 5:
                width = rect.right;
                break;
            default:
                width = rect.left;
                break;
        }
        switch (i7) {
            case 16:
                height = rect.top + (rect.height() / 2);
                break;
            case 80:
                height = rect.bottom;
                break;
            default:
                height = rect.top;
                break;
        }
        switch (i5) {
            case 1:
                width -= i3 / 2;
                break;
            case 5:
                break;
            default:
                width -= i3;
                break;
        }
        switch (i6) {
            case 16:
                height -= i4 / 2;
                break;
            case 80:
                break;
            default:
                height -= i4;
                break;
        }
        rect2.set(width, height, width + i3, height + i4);
    }

    private void a(d dVar, Rect rect, int i2, int i3) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + dVar.leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i2) - dVar.rightMargin));
        int max2 = Math.max(getPaddingTop() + dVar.topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i3) - dVar.bottomMargin));
        rect.set(max, max2, max + i2, max2 + i3);
    }

    /* access modifiers changed from: 0000 */
    public void a(View view, int i2, Rect rect, Rect rect2) {
        d dVar = (d) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        a(view, i2, rect, rect2, dVar, measuredWidth, measuredHeight);
        a(dVar, rect2, measuredWidth, measuredHeight);
    }

    private void a(View view, View view2, int i2) {
        Rect a2 = a();
        Rect a3 = a();
        try {
            a(view2, a2);
            a(view, i2, a2, a3);
            view.layout(a3.left, a3.top, a3.right, a3.bottom);
        } finally {
            a(a2);
            a(a3);
        }
    }

    private void b(View view, int i2, int i3) {
        d dVar = (d) view.getLayoutParams();
        int absoluteGravity = GravityCompat.getAbsoluteGravity(d(dVar.c), i3);
        int i4 = absoluteGravity & 7;
        int i5 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i3 == 1) {
            i2 = width - i2;
        }
        int b2 = b(i2) - measuredWidth;
        int i6 = 0;
        switch (i4) {
            case 1:
                b2 += measuredWidth / 2;
                break;
            case 5:
                b2 += measuredWidth;
                break;
        }
        switch (i5) {
            case 16:
                i6 = 0 + (measuredHeight / 2);
                break;
            case 80:
                i6 = 0 + measuredHeight;
                break;
        }
        int max = Math.max(getPaddingLeft() + dVar.leftMargin, Math.min(b2, ((width - getPaddingRight()) - measuredWidth) - dVar.rightMargin));
        int max2 = Math.max(getPaddingTop() + dVar.topMargin, Math.min(i6, ((height - getPaddingBottom()) - measuredHeight) - dVar.bottomMargin));
        view.layout(max, max2, max + measuredWidth, max2 + measuredHeight);
    }

    private void c(View view, int i2) {
        d dVar = (d) view.getLayoutParams();
        Rect a2 = a();
        a2.set(getPaddingLeft() + dVar.leftMargin, getPaddingTop() + dVar.topMargin, (getWidth() - getPaddingRight()) - dVar.rightMargin, (getHeight() - getPaddingBottom()) - dVar.bottomMargin);
        if (this.t != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(view)) {
            a2.left += this.t.getSystemWindowInsetLeft();
            a2.top += this.t.getSystemWindowInsetTop();
            a2.right -= this.t.getSystemWindowInsetRight();
            a2.bottom -= this.t.getSystemWindowInsetBottom();
        }
        Rect a3 = a();
        GravityCompat.apply(c(dVar.c), view.getMeasuredWidth(), view.getMeasuredHeight(), a2, a3, i2);
        view.layout(a3.left, a3.top, a3.right, a3.bottom);
        a(a2);
        a(a3);
    }

    private static int c(int i2) {
        int i3;
        if ((i2 & 7) == 0) {
            i3 = 8388611 | i2;
        } else {
            i3 = i2;
        }
        if ((i3 & 112) == 0) {
            return i3 | 48;
        }
        return i3;
    }

    private static int d(int i2) {
        if (i2 == 0) {
            return 8388661;
        }
        return i2;
    }

    private static int e(int i2) {
        if (i2 == 0) {
            return 17;
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        d dVar = (d) view.getLayoutParams();
        if (dVar.a != null) {
            float d2 = dVar.a.d(this, view);
            if (d2 > 0.0f) {
                if (this.l == null) {
                    this.l = new Paint();
                }
                this.l.setColor(dVar.a.c(this, view));
                this.l.setAlpha(a(Math.round(d2 * 255.0f), 0, 255));
                int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) view.getBottom(), Op.DIFFERENCE);
                }
                canvas.drawRect((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()), this.l);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, j2);
    }

    private static int a(int i2, int i3, int i4) {
        if (i2 < i3) {
            return i3;
        }
        if (i2 > i4) {
            return i4;
        }
        return i2;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2) {
        boolean z;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int size = this.g.size();
        Rect a2 = a();
        Rect a3 = a();
        Rect a4 = a();
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) this.g.get(i3);
            d dVar = (d) view.getLayoutParams();
            if (i2 != 0 || view.getVisibility() != 8) {
                for (int i4 = 0; i4 < i3; i4++) {
                    if (dVar.l == ((View) this.g.get(i4))) {
                        b(view, layoutDirection);
                    }
                }
                a(view, true, a3);
                if (dVar.g != 0 && !a3.isEmpty()) {
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(dVar.g, layoutDirection);
                    switch (absoluteGravity & 112) {
                        case 48:
                            a2.top = Math.max(a2.top, a3.bottom);
                            break;
                        case 80:
                            a2.bottom = Math.max(a2.bottom, getHeight() - a3.top);
                            break;
                    }
                    switch (absoluteGravity & 7) {
                        case 3:
                            a2.left = Math.max(a2.left, a3.right);
                            break;
                        case 5:
                            a2.right = Math.max(a2.right, getWidth() - a3.left);
                            break;
                    }
                }
                if (dVar.h != 0 && view.getVisibility() == 0) {
                    a(view, a2, layoutDirection);
                }
                if (i2 != 2) {
                    c(view, a4);
                    if (!a4.equals(a3)) {
                        b(view, a3);
                    }
                }
                for (int i5 = i3 + 1; i5 < size; i5++) {
                    View view2 = (View) this.g.get(i5);
                    d dVar2 = (d) view2.getLayoutParams();
                    Behavior b2 = dVar2.b();
                    if (b2 != null && b2.a(this, view2, view)) {
                        if (i2 != 0 || !dVar2.g()) {
                            switch (i2) {
                                case 2:
                                    b2.c(this, view2, view);
                                    z = true;
                                    break;
                                default:
                                    z = b2.b(this, view2, view);
                                    break;
                            }
                            if (i2 == 1) {
                                dVar2.a(z);
                            }
                        } else {
                            dVar2.h();
                        }
                    }
                }
            }
        }
        a(a2);
        a(a3);
        a(a4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.view.View r11, android.graphics.Rect r12, int r13) {
        /*
            r10 = this;
            r1 = 1
            r3 = 0
            boolean r0 = android.support.v4.view.ViewCompat.isLaidOut(r11)
            if (r0 != 0) goto L_0x0009
        L_0x0008:
            return
        L_0x0009:
            int r0 = r11.getWidth()
            if (r0 <= 0) goto L_0x0008
            int r0 = r11.getHeight()
            if (r0 <= 0) goto L_0x0008
            android.view.ViewGroup$LayoutParams r0 = r11.getLayoutParams()
            android.support.design.widget.CoordinatorLayout$d r0 = (android.support.design.widget.CoordinatorLayout.d) r0
            android.support.design.widget.CoordinatorLayout$Behavior r2 = r0.b()
            android.graphics.Rect r4 = a()
            android.graphics.Rect r5 = a()
            int r6 = r11.getLeft()
            int r7 = r11.getTop()
            int r8 = r11.getRight()
            int r9 = r11.getBottom()
            r5.set(r6, r7, r8, r9)
            if (r2 == 0) goto L_0x0073
            boolean r2 = r2.a(r10, r11, r4)
            if (r2 == 0) goto L_0x0073
            boolean r2 = r5.contains(r4)
            if (r2 != 0) goto L_0x0076
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Rect should be within the child's bounds. Rect:"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r4.toShortString()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " | Bounds:"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r5.toShortString()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0073:
            r4.set(r5)
        L_0x0076:
            a(r5)
            boolean r2 = r4.isEmpty()
            if (r2 == 0) goto L_0x0083
            a(r4)
            goto L_0x0008
        L_0x0083:
            int r2 = r0.h
            int r5 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r2, r13)
            r2 = r5 & 48
            r6 = 48
            if (r2 != r6) goto L_0x010b
            int r2 = r4.top
            int r6 = r0.topMargin
            int r2 = r2 - r6
            int r6 = r0.j
            int r2 = r2 - r6
            int r6 = r12.top
            if (r2 >= r6) goto L_0x010b
            int r6 = r12.top
            int r2 = r6 - r2
            r10.e(r11, r2)
            r2 = r1
        L_0x00a3:
            r6 = r5 & 80
            r7 = 80
            if (r6 != r7) goto L_0x00c2
            int r6 = r10.getHeight()
            int r7 = r4.bottom
            int r6 = r6 - r7
            int r7 = r0.bottomMargin
            int r6 = r6 - r7
            int r7 = r0.j
            int r6 = r6 + r7
            int r7 = r12.bottom
            if (r6 >= r7) goto L_0x00c2
            int r2 = r12.bottom
            int r2 = r6 - r2
            r10.e(r11, r2)
            r2 = r1
        L_0x00c2:
            if (r2 != 0) goto L_0x00c7
            r10.e(r11, r3)
        L_0x00c7:
            r2 = r5 & 3
            r6 = 3
            if (r2 != r6) goto L_0x0109
            int r2 = r4.left
            int r6 = r0.leftMargin
            int r2 = r2 - r6
            int r6 = r0.i
            int r2 = r2 - r6
            int r6 = r12.left
            if (r2 >= r6) goto L_0x0109
            int r6 = r12.left
            int r2 = r6 - r2
            r10.d(r11, r2)
            r2 = r1
        L_0x00e0:
            r5 = r5 & 5
            r6 = 5
            if (r5 != r6) goto L_0x0107
            int r5 = r10.getWidth()
            int r6 = r4.right
            int r5 = r5 - r6
            int r6 = r0.rightMargin
            int r5 = r5 - r6
            int r0 = r0.i
            int r0 = r0 + r5
            int r5 = r12.right
            if (r0 >= r5) goto L_0x0107
            int r2 = r12.right
            int r0 = r0 - r2
            r10.d(r11, r0)
            r0 = r1
        L_0x00fd:
            if (r0 != 0) goto L_0x0102
            r10.d(r11, r3)
        L_0x0102:
            a(r4)
            goto L_0x0008
        L_0x0107:
            r0 = r2
            goto L_0x00fd
        L_0x0109:
            r2 = r3
            goto L_0x00e0
        L_0x010b:
            r2 = r3
            goto L_0x00a3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.a(android.view.View, android.graphics.Rect, int):void");
    }

    private void d(View view, int i2) {
        d dVar = (d) view.getLayoutParams();
        if (dVar.i != i2) {
            ViewCompat.offsetLeftAndRight(view, i2 - dVar.i);
            dVar.i = i2;
        }
    }

    private void e(View view, int i2) {
        d dVar = (d) view.getLayoutParams();
        if (dVar.j != i2) {
            ViewCompat.offsetTopAndBottom(view, i2 - dVar.j);
            dVar.j = i2;
        }
    }

    public void b(View view) {
        List incomingEdges = this.h.getIncomingEdges(view);
        if (incomingEdges != null && !incomingEdges.isEmpty()) {
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < incomingEdges.size()) {
                    View view2 = (View) incomingEdges.get(i3);
                    Behavior b2 = ((d) view2.getLayoutParams()).b();
                    if (b2 != null) {
                        b2.b(this, view2, view);
                    }
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public List<View> c(View view) {
        List outgoingEdges = this.h.getOutgoingEdges(view);
        this.j.clear();
        if (outgoingEdges != null) {
            this.j.addAll(outgoingEdges);
        }
        return this.j;
    }

    public List<View> d(View view) {
        List incomingEdges = this.h.getIncomingEdges(view);
        this.j.clear();
        if (incomingEdges != null) {
            this.j.addAll(incomingEdges);
        }
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public final List<View> getDependencySortedChildren() {
        b();
        return Collections.unmodifiableList(this.g);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        boolean z = false;
        int childCount = getChildCount();
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            } else if (e(getChildAt(i2))) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z == this.s) {
            return;
        }
        if (z) {
            e();
        } else {
            f();
        }
    }

    private boolean e(View view) {
        return this.h.hasOutgoingEdges(view);
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        if (this.n) {
            if (this.r == null) {
                this.r = new e();
            }
            getViewTreeObserver().addOnPreDrawListener(this.r);
        }
        this.s = true;
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        if (this.n && this.r != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.r);
        }
        this.s = false;
    }

    /* access modifiers changed from: 0000 */
    public void b(View view, int i2) {
        d dVar = (d) view.getLayoutParams();
        if (dVar.k != null) {
            Rect a2 = a();
            Rect a3 = a();
            Rect a4 = a();
            a(dVar.k, a2);
            a(view, false, a3);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            a(view, i2, a2, a4, dVar, measuredWidth, measuredHeight);
            boolean z = (a4.left == a3.left && a4.top == a3.top) ? false : true;
            a(dVar, a4, measuredWidth, measuredHeight);
            int i3 = a4.left - a3.left;
            int i4 = a4.top - a3.top;
            if (i3 != 0) {
                ViewCompat.offsetLeftAndRight(view, i3);
            }
            if (i4 != 0) {
                ViewCompat.offsetTopAndBottom(view, i4);
            }
            if (z) {
                Behavior b2 = dVar.b();
                if (b2 != null) {
                    b2.b(this, view, dVar.k);
                }
            }
            a(a2);
            a(a3);
            a(a4);
        }
    }

    public boolean a(View view, int i2, int i3) {
        Rect a2 = a();
        a(view, a2);
        try {
            return a2.contains(i2, i3);
        } finally {
            a(a2);
        }
    }

    /* renamed from: a */
    public d generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public d generateLayoutParams(LayoutParams layoutParams) {
        if (layoutParams instanceof d) {
            return new d((d) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new d((MarginLayoutParams) layoutParams);
        }
        return new d(layoutParams);
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public d generateDefaultLayoutParams() {
        return new d(-2, -2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return onStartNestedScroll(view, view2, i2, 0);
    }

    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        boolean z;
        boolean z2 = false;
        int childCount = getChildCount();
        int i4 = 0;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() == 8) {
                z = z2;
            } else {
                d dVar = (d) childAt.getLayoutParams();
                Behavior b2 = dVar.b();
                if (b2 != null) {
                    boolean a2 = b2.a(this, childAt, view, view2, i2, i3);
                    z = z2 | a2;
                    dVar.a(i3, a2);
                } else {
                    dVar.a(i3, false);
                    z = z2;
                }
            }
            i4++;
            z2 = z;
        }
        return z2;
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        onNestedScrollAccepted(view, view2, i2, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
        this.x.onNestedScrollAccepted(view, view2, i2, i3);
        this.f323q = view2;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.b(i3)) {
                Behavior b2 = dVar.b();
                if (b2 != null) {
                    b2.b(this, childAt, view, view2, i2, i3);
                }
            }
        }
    }

    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    public void onStopNestedScroll(View view, int i2) {
        this.x.onStopNestedScroll(view, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            d dVar = (d) childAt.getLayoutParams();
            if (dVar.b(i2)) {
                Behavior b2 = dVar.b();
                if (b2 != null) {
                    b2.a(this, childAt, view, i2);
                }
                dVar.a(i2);
                dVar.h();
            }
        }
        this.f323q = null;
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        onNestedScroll(view, i2, i3, i4, i5, 0);
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6) {
        boolean z;
        int childCount = getChildCount();
        boolean z2 = false;
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 8) {
                z = z2;
            } else {
                d dVar = (d) childAt.getLayoutParams();
                if (!dVar.b(i6)) {
                    z = z2;
                } else {
                    Behavior b2 = dVar.b();
                    if (b2 != null) {
                        b2.a(this, childAt, view, i2, i3, i4, i5, i6);
                        z = true;
                    } else {
                        z = z2;
                    }
                }
            }
            i7++;
            z2 = z;
        }
        if (z2) {
            a(1);
        }
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        onNestedPreScroll(view, i2, i3, iArr, 0);
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4) {
        boolean z;
        int i5;
        int i6;
        int min;
        int i7 = 0;
        int i8 = 0;
        boolean z2 = false;
        int childCount = getChildCount();
        int i9 = 0;
        while (i9 < childCount) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() == 8) {
                z = z2;
                i5 = i7;
                i6 = i8;
            } else {
                d dVar = (d) childAt.getLayoutParams();
                if (!dVar.b(i4)) {
                    z = z2;
                    i5 = i7;
                    i6 = i8;
                } else {
                    Behavior b2 = dVar.b();
                    if (b2 != null) {
                        int[] iArr2 = this.k;
                        this.k[1] = 0;
                        iArr2[0] = 0;
                        b2.a(this, childAt, view, i2, i3, this.k, i4);
                        if (i2 > 0) {
                            i5 = Math.max(i7, this.k[0]);
                        } else {
                            i5 = Math.min(i7, this.k[0]);
                        }
                        if (i3 > 0) {
                            min = Math.max(i8, this.k[1]);
                        } else {
                            min = Math.min(i8, this.k[1]);
                        }
                        i6 = min;
                        z = true;
                    } else {
                        z = z2;
                        i5 = i7;
                        i6 = i8;
                    }
                }
            }
            i9++;
            i8 = i6;
            i7 = i5;
            z2 = z;
        }
        iArr[0] = i7;
        iArr[1] = i8;
        if (z2) {
            a(1);
        }
    }

    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        boolean z2;
        int childCount = getChildCount();
        int i2 = 0;
        boolean z3 = false;
        while (i2 < childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 8) {
                z2 = z3;
            } else {
                d dVar = (d) childAt.getLayoutParams();
                if (!dVar.b(0)) {
                    z2 = z3;
                } else {
                    Behavior b2 = dVar.b();
                    if (b2 != null) {
                        z2 = b2.a(this, childAt, view, f2, f3, z) | z3;
                    } else {
                        z2 = z3;
                    }
                }
            }
            i2++;
            z3 = z2;
        }
        if (z3) {
            a(1);
        }
        return z3;
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        boolean z;
        int childCount = getChildCount();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 8) {
                z = z2;
            } else {
                d dVar = (d) childAt.getLayoutParams();
                if (!dVar.b(0)) {
                    z = z2;
                } else {
                    Behavior b2 = dVar.b();
                    if (b2 != null) {
                        z = b2.a(this, childAt, view, f2, f3) | z2;
                    } else {
                        z = z2;
                    }
                }
            }
            i2++;
            z2 = z;
        }
        return z2;
    }

    public int getNestedScrollAxes() {
        return this.x.getNestedScrollAxes();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        SparseArray<Parcelable> sparseArray = savedState.a;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            Behavior b2 = a(childAt).b();
            if (!(id == -1 || b2 == null)) {
                Parcelable parcelable2 = (Parcelable) sparseArray.get(id);
                if (parcelable2 != null) {
                    b2.a(this, childAt, parcelable2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            Behavior b2 = ((d) childAt.getLayoutParams()).b();
            if (!(id == -1 || b2 == null)) {
                Parcelable b3 = b2.b(this, childAt);
                if (b3 != null) {
                    sparseArray.append(id, b3);
                }
            }
        }
        savedState.a = sparseArray;
        return savedState;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Behavior b2 = ((d) view.getLayoutParams()).b();
        if (b2 == null || !b2.a(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    private void c() {
        if (VERSION.SDK_INT >= 21) {
            if (ViewCompat.getFitsSystemWindows(this)) {
                if (this.w == null) {
                    this.w = new OnApplyWindowInsetsListener() {
                        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                            return CoordinatorLayout.this.a(windowInsetsCompat);
                        }
                    };
                }
                ViewCompat.setOnApplyWindowInsetsListener(this, this.w);
                setSystemUiVisibility(1280);
                return;
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, null);
        }
    }
}
