package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.recyclerview.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements NestedScrollingChild2, ScrollingView {
    static final Interpolator L = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private static final int[] M = {16843830};
    private static final int[] N = {16842987};
    private static final boolean O;
    private static final boolean P;
    private static final Class<?>[] Q = {Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
    static final boolean a = (VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20);
    static final boolean b;
    static final boolean c;
    static final boolean d;
    final u A;
    aa B;
    a C;
    final s D;
    boolean E;
    boolean F;
    boolean G;
    RecyclerViewAccessibilityDelegate H;
    final int[] I;
    final int[] J;
    final List<v> K;
    private final q R;
    private SavedState S;
    private final Rect T;
    private final ArrayList<l> U;
    private l V;
    private int W;
    private List<m> aA;
    private b aB;
    private d aC;
    private final int[] aD;
    private NestedScrollingChildHelper aE;
    private final int[] aF;
    private final int[] aG;
    private Runnable aH;
    private final b aI;
    private boolean aa;
    private int ab;
    private final AccessibilityManager ac;
    private List<j> ad;
    private int ae;
    private int af;
    private e ag;
    private EdgeEffect ah;
    private EdgeEffect ai;
    private EdgeEffect aj;
    private EdgeEffect ak;
    private int al;
    private int am;
    private VelocityTracker an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private k at;
    private final int au;
    private final int av;
    private float aw;
    private float ax;
    private boolean ay;
    private m az;
    final o e;
    c f;
    r g;
    final ba h;
    boolean i;
    final Runnable j;
    final Rect k;
    final RectF l;
    a m;
    i n;
    p o;
    final ArrayList<h> p;

    /* renamed from: q reason: collision with root package name */
    boolean f353q;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    f z;

    public static class LayoutParams extends MarginLayoutParams {
        v c;
        final Rect d = new Rect();
        boolean e = true;
        boolean f = false;

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

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public boolean c() {
            return this.c.isInvalid();
        }

        public boolean d() {
            return this.c.isRemoved();
        }

        public boolean e() {
            return this.c.isUpdated();
        }

        public int f() {
            return this.c.getLayoutPosition();
        }
    }

    public static class SavedState extends AbsSavedState {
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
        Parcelable a;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                classLoader = i.class.getClassLoader();
            }
            this.a = parcel.readParcelable(classLoader);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.a, 0);
        }

        /* access modifiers changed from: 0000 */
        public void a(SavedState savedState) {
            this.a = savedState.a;
        }
    }

    public static abstract class a<VH extends v> {
        private boolean mHasStableIds = false;
        private final b mObservable = new b();

        public abstract int getItemCount();

        public abstract void onBindViewHolder(VH vh, int i);

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        public void onBindViewHolder(VH vh, int i, List<Object> list) {
            onBindViewHolder(vh, i);
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i) {
            try {
                TraceCompat.beginSection("RV CreateView");
                VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
                if (onCreateViewHolder.itemView.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                onCreateViewHolder.mItemViewType = i;
                return onCreateViewHolder;
            } finally {
                TraceCompat.endSection();
            }
        }

        public final void bindViewHolder(VH vh, int i) {
            vh.mPosition = i;
            if (hasStableIds()) {
                vh.mItemId = getItemId(i);
            }
            vh.setFlags(1, 519);
            TraceCompat.beginSection("RV OnBindView");
            onBindViewHolder(vh, i, vh.getUnmodifiedPayloads());
            vh.clearPayload();
            android.view.ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).e = true;
            }
            TraceCompat.endSection();
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public void setHasStableIds(boolean z) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z;
        }

        public long getItemId(int i) {
            return -1;
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public void onViewRecycled(VH vh) {
        }

        public boolean onFailedToRecycleView(VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public final boolean hasObservers() {
            return this.mObservable.a();
        }

        public void registerAdapterDataObserver(c cVar) {
            this.mObservable.registerObserver(cVar);
        }

        public void unregisterAdapterDataObserver(c cVar) {
            this.mObservable.unregisterObserver(cVar);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public final void notifyDataSetChanged() {
            this.mObservable.b();
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.a(i, 1);
        }

        public final void notifyItemChanged(int i, Object obj) {
            this.mObservable.a(i, 1, obj);
        }

        public final void notifyItemRangeChanged(int i, int i2) {
            this.mObservable.a(i, i2);
        }

        public final void notifyItemRangeChanged(int i, int i2, Object obj) {
            this.mObservable.a(i, i2, obj);
        }

        public final void notifyItemInserted(int i) {
            this.mObservable.b(i, 1);
        }

        public final void notifyItemMoved(int i, int i2) {
            this.mObservable.d(i, i2);
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            this.mObservable.b(i, i2);
        }

        public final void notifyItemRemoved(int i) {
            this.mObservable.c(i, 1);
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            this.mObservable.c(i, i2);
        }
    }

    static class b extends Observable<c> {
        b() {
        }

        public boolean a() {
            return !this.mObservers.isEmpty();
        }

        public void b() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a();
            }
        }

        public void a(int i, int i2) {
            a(i, i2, null);
        }

        public void a(int i, int i2, Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a(i, i2, obj);
            }
        }

        public void b(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).b(i, i2);
            }
        }

        public void c(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).c(i, i2);
            }
        }

        public void d(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a(i, i2, 1);
            }
        }
    }

    public static abstract class c {
        public void a() {
        }

        public void a(int i, int i2) {
        }

        public void a(int i, int i2, Object obj) {
            a(i, i2);
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2) {
        }

        public void a(int i, int i2, int i3) {
        }
    }

    public interface d {
        int a(int i, int i2);
    }

    public static class e {
        /* access modifiers changed from: protected */
        public EdgeEffect a(RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class f {
        private b a = null;
        private ArrayList<a> b = new ArrayList<>();
        private long c = 120;
        private long d = 120;
        private long e = 250;
        private long f = 250;

        public interface a {
            void a();
        }

        interface b {
            void a(v vVar);
        }

        public static class c {
            public int a;
            public int b;
            public int c;
            public int d;

            public c a(v vVar) {
                return a(vVar, 0);
            }

            public c a(v vVar, int i) {
                View view = vVar.itemView;
                this.a = view.getLeft();
                this.b = view.getTop();
                this.c = view.getRight();
                this.d = view.getBottom();
                return this;
            }
        }

        public abstract void a();

        public abstract boolean a(v vVar, c cVar, c cVar2);

        public abstract boolean a(v vVar, v vVar2, c cVar, c cVar2);

        public abstract boolean b();

        public abstract boolean b(v vVar, c cVar, c cVar2);

        public abstract boolean c(v vVar, c cVar, c cVar2);

        public abstract void d();

        public abstract void d(v vVar);

        public long e() {
            return this.e;
        }

        public long f() {
            return this.c;
        }

        public long g() {
            return this.d;
        }

        public long h() {
            return this.f;
        }

        /* access modifiers changed from: 0000 */
        public void a(b bVar) {
            this.a = bVar;
        }

        public c a(s sVar, v vVar, int i, List<Object> list) {
            return j().a(vVar);
        }

        public c a(s sVar, v vVar) {
            return j().a(vVar);
        }

        static int e(v vVar) {
            int i = vVar.mFlags & 14;
            if (vVar.isInvalid()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int oldPosition = vVar.getOldPosition();
            int adapterPosition = vVar.getAdapterPosition();
            if (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) {
                return i;
            }
            return i | 2048;
        }

        public final void f(v vVar) {
            g(vVar);
            if (this.a != null) {
                this.a.a(vVar);
            }
        }

        public void g(v vVar) {
        }

        public final boolean a(a aVar) {
            boolean b2 = b();
            if (aVar != null) {
                if (!b2) {
                    aVar.a();
                } else {
                    this.b.add(aVar);
                }
            }
            return b2;
        }

        public boolean h(v vVar) {
            return true;
        }

        public boolean a(v vVar, List<Object> list) {
            return h(vVar);
        }

        public final void i() {
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((a) this.b.get(i)).a();
            }
            this.b.clear();
        }

        public c j() {
            return new c();
        }
    }

    private class g implements b {
        g() {
        }

        public void a(v vVar) {
            vVar.setIsRecyclable(true);
            if (vVar.mShadowedHolder != null && vVar.mShadowingHolder == null) {
                vVar.mShadowedHolder = null;
            }
            vVar.mShadowingHolder = null;
            if (!vVar.shouldBeKeptAsChild() && !RecyclerView.this.a(vVar.itemView) && vVar.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(vVar.itemView, false);
            }
        }
    }

    public static abstract class h {
        public void b(Canvas canvas, RecyclerView recyclerView, s sVar) {
            a(canvas, recyclerView);
        }

        @Deprecated
        public void a(Canvas canvas, RecyclerView recyclerView) {
        }

        public void a(Canvas canvas, RecyclerView recyclerView, s sVar) {
            b(canvas, recyclerView);
        }

        @Deprecated
        public void b(Canvas canvas, RecyclerView recyclerView) {
        }

        @Deprecated
        public void a(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void a(Rect rect, View view, RecyclerView recyclerView, s sVar) {
            a(rect, ((LayoutParams) view.getLayoutParams()).f(), recyclerView);
        }
    }

    public static abstract class i {
        private final b a = new b() {
            public View a(int i) {
                return i.this.i(i);
            }

            public int a() {
                return i.this.D();
            }

            public int b() {
                return i.this.B() - i.this.F();
            }

            public int a(View view) {
                return i.this.h(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
            }

            public int b(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.rightMargin + i.this.j(view);
            }
        };
        private final b b = new b() {
            public View a(int i) {
                return i.this.i(i);
            }

            public int a() {
                return i.this.E();
            }

            public int b() {
                return i.this.C() - i.this.G();
            }

            public int a(View view) {
                return i.this.i(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
            }

            public int b(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return layoutParams.bottomMargin + i.this.k(view);
            }
        };
        private boolean c = true;
        private boolean d = true;
        private int e;
        private int f;
        private int g;
        private int h;
        r p;

        /* renamed from: q reason: collision with root package name */
        RecyclerView f354q;
        az r = new az(this.a);
        az s = new az(this.b);
        r t;
        boolean u = false;
        boolean v = false;
        boolean w = false;
        int x;
        boolean y;

        public interface a {
            void b(int i, int i2);
        }

        public static class b {
            public int a;
            public int b;
            public boolean c;
            public boolean d;
        }

        public abstract LayoutParams a();

        /* access modifiers changed from: 0000 */
        public void b(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f354q = null;
                this.p = null;
                this.g = 0;
                this.h = 0;
            } else {
                this.f354q = recyclerView;
                this.p = recyclerView.g;
                this.g = recyclerView.getWidth();
                this.h = recyclerView.getHeight();
            }
            this.e = 1073741824;
            this.f = 1073741824;
        }

        /* access modifiers changed from: 0000 */
        public void d(int i, int i2) {
            this.g = MeasureSpec.getSize(i);
            this.e = MeasureSpec.getMode(i);
            if (this.e == 0 && !RecyclerView.b) {
                this.g = 0;
            }
            this.h = MeasureSpec.getSize(i2);
            this.f = MeasureSpec.getMode(i2);
            if (this.f == 0 && !RecyclerView.b) {
                this.h = 0;
            }
        }

        /* access modifiers changed from: 0000 */
        public void e(int i, int i2) {
            int i3 = Integer.MAX_VALUE;
            int i4 = ExploreByTouchHelper.INVALID_ID;
            int y2 = y();
            if (y2 == 0) {
                this.f354q.e(i, i2);
                return;
            }
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < y2; i7++) {
                View i8 = i(i7);
                Rect rect = this.f354q.k;
                a(i8, rect);
                if (rect.left < i6) {
                    i6 = rect.left;
                }
                if (rect.right > i5) {
                    i5 = rect.right;
                }
                if (rect.top < i3) {
                    i3 = rect.top;
                }
                if (rect.bottom > i4) {
                    i4 = rect.bottom;
                }
            }
            this.f354q.k.set(i6, i3, i5, i4);
            a(this.f354q.k, i, i2);
        }

        public void a(Rect rect, int i, int i2) {
            g(a(i, rect.width() + D() + F(), I()), a(i2, rect.height() + E() + G(), J()));
        }

        public void r() {
            if (this.f354q != null) {
                this.f354q.requestLayout();
            }
        }

        public static int a(int i, int i2, int i3) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            switch (mode) {
                case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                    return Math.min(size, Math.max(i2, i3));
                case 1073741824:
                    return size;
                default:
                    return Math.max(i2, i3);
            }
        }

        public void a(String str) {
            if (this.f354q != null) {
                this.f354q.a(str);
            }
        }

        public boolean d() {
            return this.w;
        }

        public boolean c() {
            return false;
        }

        public final boolean s() {
            return this.d;
        }

        public void a(int i, int i2, s sVar, a aVar) {
        }

        public void a(int i, a aVar) {
        }

        /* access modifiers changed from: 0000 */
        public void c(RecyclerView recyclerView) {
            this.v = true;
            d(recyclerView);
        }

        /* access modifiers changed from: 0000 */
        public void b(RecyclerView recyclerView, o oVar) {
            this.v = false;
            a(recyclerView, oVar);
        }

        public boolean t() {
            return this.v;
        }

        public boolean a(Runnable runnable) {
            if (this.f354q != null) {
                return this.f354q.removeCallbacks(runnable);
            }
            return false;
        }

        public void d(RecyclerView recyclerView) {
        }

        @Deprecated
        public void e(RecyclerView recyclerView) {
        }

        public void a(RecyclerView recyclerView, o oVar) {
            e(recyclerView);
        }

        public boolean u() {
            return this.f354q != null && this.f354q.i;
        }

        public void c(o oVar, s sVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void a(s sVar) {
        }

        public boolean a(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof MarginLayoutParams) {
                return new LayoutParams((MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public LayoutParams a(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public int a(int i, o oVar, s sVar) {
            return 0;
        }

        public int b(int i, o oVar, s sVar) {
            return 0;
        }

        public boolean f() {
            return false;
        }

        public boolean g() {
            return false;
        }

        public void e(int i) {
        }

        public boolean v() {
            return this.t != null && this.t.d();
        }

        public int w() {
            return ViewCompat.getLayoutDirection(this.f354q);
        }

        public void a(View view) {
            a(view, -1);
        }

        public void a(View view, int i) {
            a(view, i, true);
        }

        public void b(View view) {
            b(view, -1);
        }

        public void b(View view, int i) {
            a(view, i, false);
        }

        private void a(View view, int i, boolean z) {
            v e2 = RecyclerView.e(view);
            if (z || e2.isRemoved()) {
                this.f354q.h.e(e2);
            } else {
                this.f354q.h.f(e2);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (e2.wasReturnedFromScrap() || e2.isScrap()) {
                if (e2.isScrap()) {
                    e2.unScrap();
                } else {
                    e2.clearReturnedFromScrapFlag();
                }
                this.p.a(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f354q) {
                int b2 = this.p.b(view);
                if (i == -1) {
                    i = this.p.b();
                }
                if (b2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f354q.indexOfChild(view) + this.f354q.a());
                } else if (b2 != i) {
                    this.f354q.n.f(b2, i);
                }
            } else {
                this.p.a(view, i, false);
                layoutParams.e = true;
                if (this.t != null && this.t.d()) {
                    this.t.b(view);
                }
            }
            if (layoutParams.f) {
                e2.itemView.invalidate();
                layoutParams.f = false;
            }
        }

        public void c(View view) {
            this.p.a(view);
        }

        public void g(int i) {
            if (i(i) != null) {
                this.p.a(i);
            }
        }

        public int x() {
            return -1;
        }

        public int d(View view) {
            return ((LayoutParams) view.getLayoutParams()).f();
        }

        public View e(View view) {
            if (this.f354q == null) {
                return null;
            }
            View c2 = this.f354q.c(view);
            if (c2 == null || this.p.c(c2)) {
                return null;
            }
            return c2;
        }

        public View c(int i) {
            int y2 = y();
            for (int i2 = 0; i2 < y2; i2++) {
                View i3 = i(i2);
                v e2 = RecyclerView.e(i3);
                if (e2 != null && e2.getLayoutPosition() == i && !e2.shouldIgnore() && (this.f354q.D.a() || !e2.isRemoved())) {
                    return i3;
                }
            }
            return null;
        }

        public void h(int i) {
            a(i, i(i));
        }

        private void a(int i, View view) {
            this.p.e(i);
        }

        public void a(View view, int i, LayoutParams layoutParams) {
            v e2 = RecyclerView.e(view);
            if (e2.isRemoved()) {
                this.f354q.h.e(e2);
            } else {
                this.f354q.h.f(e2);
            }
            this.p.a(view, i, layoutParams, e2.isRemoved());
        }

        public void c(View view, int i) {
            a(view, i, (LayoutParams) view.getLayoutParams());
        }

        public void f(int i, int i2) {
            View i3 = i(i);
            if (i3 == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.f354q.toString());
            }
            h(i);
            c(i3, i2);
        }

        public void a(View view, o oVar) {
            c(view);
            oVar.a(view);
        }

        public void a(int i, o oVar) {
            View i2 = i(i);
            g(i);
            oVar.a(i2);
        }

        public int y() {
            if (this.p != null) {
                return this.p.b();
            }
            return 0;
        }

        public View i(int i) {
            if (this.p != null) {
                return this.p.b(i);
            }
            return null;
        }

        public int z() {
            return this.e;
        }

        public int A() {
            return this.f;
        }

        public int B() {
            return this.g;
        }

        public int C() {
            return this.h;
        }

        public int D() {
            if (this.f354q != null) {
                return this.f354q.getPaddingLeft();
            }
            return 0;
        }

        public int E() {
            if (this.f354q != null) {
                return this.f354q.getPaddingTop();
            }
            return 0;
        }

        public int F() {
            if (this.f354q != null) {
                return this.f354q.getPaddingRight();
            }
            return 0;
        }

        public int G() {
            if (this.f354q != null) {
                return this.f354q.getPaddingBottom();
            }
            return 0;
        }

        public View H() {
            if (this.f354q == null) {
                return null;
            }
            View focusedChild = this.f354q.getFocusedChild();
            if (focusedChild == null || this.p.c(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void j(int i) {
            if (this.f354q != null) {
                this.f354q.f(i);
            }
        }

        public void k(int i) {
            if (this.f354q != null) {
                this.f354q.e(i);
            }
        }

        public void a(o oVar) {
            for (int y2 = y() - 1; y2 >= 0; y2--) {
                a(oVar, y2, i(y2));
            }
        }

        private void a(o oVar, int i, View view) {
            v e2 = RecyclerView.e(view);
            if (!e2.shouldIgnore()) {
                if (!e2.isInvalid() || e2.isRemoved() || this.f354q.m.hasStableIds()) {
                    h(i);
                    oVar.c(view);
                    this.f354q.h.h(e2);
                    return;
                }
                g(i);
                oVar.b(e2);
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(o oVar) {
            int e2 = oVar.e();
            for (int i = e2 - 1; i >= 0; i--) {
                View e3 = oVar.e(i);
                v e4 = RecyclerView.e(e3);
                if (!e4.shouldIgnore()) {
                    e4.setIsRecyclable(false);
                    if (e4.isTmpDetached()) {
                        this.f354q.removeDetachedView(e3, false);
                    }
                    if (this.f354q.z != null) {
                        this.f354q.z.d(e4);
                    }
                    e4.setIsRecyclable(true);
                    oVar.b(e3);
                }
            }
            oVar.f();
            if (e2 > 0) {
                this.f354q.invalidate();
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a(View view, int i, int i2, LayoutParams layoutParams) {
            return !this.c || !b(view.getMeasuredWidth(), i, layoutParams.width) || !b(view.getMeasuredHeight(), i2, layoutParams.height);
        }

        /* access modifiers changed from: 0000 */
        public boolean b(View view, int i, int i2, LayoutParams layoutParams) {
            return view.isLayoutRequested() || !this.c || !b(view.getWidth(), i, layoutParams.width) || !b(view.getHeight(), i2, layoutParams.height);
        }

        private static boolean b(int i, int i2, int i3) {
            int mode = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            switch (mode) {
                case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                    if (size < i) {
                        return false;
                    }
                    return true;
                case 0:
                    return true;
                case 1073741824:
                    if (size != i) {
                        return false;
                    }
                    return true;
                default:
                    return false;
            }
        }

        public void a(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect j = this.f354q.j(view);
            int i3 = j.left + j.right + i;
            int i4 = j.bottom + j.top + i2;
            int a2 = a(B(), z(), i3 + D() + F() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width, f());
            int a3 = a(C(), A(), i4 + E() + G() + layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height, g());
            if (b(view, a2, a3, layoutParams)) {
                view.measure(a2, a3);
            }
        }

        public static int a(int i, int i2, int i3, int i4, boolean z) {
            int i5 = 0;
            int max = Math.max(0, i - i3);
            if (z) {
                if (i4 >= 0) {
                    i5 = 1073741824;
                    max = i4;
                } else if (i4 == -1) {
                    switch (i2) {
                        case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                        case 1073741824:
                            i5 = max;
                            break;
                        case 0:
                            i2 = 0;
                            break;
                        default:
                            i2 = 0;
                            break;
                    }
                    max = i5;
                    i5 = i2;
                } else {
                    if (i4 == -2) {
                        max = 0;
                    }
                    max = 0;
                }
            } else if (i4 >= 0) {
                i5 = 1073741824;
                max = i4;
            } else if (i4 == -1) {
                i5 = i2;
            } else {
                if (i4 == -2) {
                    if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                        i5 = Integer.MIN_VALUE;
                    }
                }
                max = 0;
            }
            return MeasureSpec.makeMeasureSpec(max, i5);
        }

        public int f(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            return rect.right + view.getMeasuredWidth() + rect.left;
        }

        public int g(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            return rect.bottom + view.getMeasuredHeight() + rect.top;
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.d;
            view.layout(rect.left + i + layoutParams.leftMargin, rect.top + i2 + layoutParams.topMargin, (i3 - rect.right) - layoutParams.rightMargin, (i4 - rect.bottom) - layoutParams.bottomMargin);
        }

        public void a(View view, boolean z, Rect rect) {
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).d;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, rect2.bottom + view.getHeight());
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f354q != null) {
                Matrix matrix = view.getMatrix();
                if (matrix != null && !matrix.isIdentity()) {
                    RectF rectF = this.f354q.l;
                    rectF.set(rect);
                    matrix.mapRect(rectF);
                    rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
                }
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void a(View view, Rect rect) {
            RecyclerView.a(view, rect);
        }

        public int h(View view) {
            return view.getLeft() - n(view);
        }

        public int i(View view) {
            return view.getTop() - l(view);
        }

        public int j(View view) {
            return view.getRight() + o(view);
        }

        public int k(View view) {
            return view.getBottom() + m(view);
        }

        public void b(View view, Rect rect) {
            if (this.f354q == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(this.f354q.j(view));
            }
        }

        public int l(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.top;
        }

        public int m(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.bottom;
        }

        public int n(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.left;
        }

        public int o(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.right;
        }

        public View a(View view, int i, o oVar, s sVar) {
            return null;
        }

        public View d(View view, int i) {
            return null;
        }

        private int[] b(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            int min;
            int i;
            int min2;
            int[] iArr = new int[2];
            int D = D();
            int E = E();
            int B = B() - F();
            int C = C() - G();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width = left + rect.width();
            int height = top + rect.height();
            int min3 = Math.min(0, left - D);
            int min4 = Math.min(0, top - E);
            int max = Math.max(0, width - B);
            int max2 = Math.max(0, height - C);
            if (w() == 1) {
                if (max == 0) {
                    max = Math.max(min3, width - B);
                }
                i = max;
            } else {
                if (min3 != 0) {
                    min = min3;
                } else {
                    min = Math.min(left - D, max);
                }
                i = min;
            }
            if (min4 != 0) {
                min2 = min4;
            } else {
                min2 = Math.min(top - E, max2);
            }
            iArr[0] = i;
            iArr[1] = min2;
            return iArr;
        }

        public boolean a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return a(recyclerView, view, rect, z, false);
        }

        public boolean a(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] b2 = b(recyclerView, view, rect, z);
            int i = b2[0];
            int i2 = b2[1];
            if (z2 && !d(recyclerView, i, i2)) {
                return false;
            }
            if (i == 0 && i2 == 0) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i, i2);
            } else {
                recyclerView.a(i, i2);
            }
            return true;
        }

        public boolean a(View view, boolean z, boolean z2) {
            boolean z3;
            if (!this.r.a(view, 24579) || !this.s.a(view, 24579)) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z) {
                return z3;
            }
            if (z3) {
                return false;
            }
            return true;
        }

        private boolean d(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int D = D();
            int E = E();
            int B = B() - F();
            int C = C() - G();
            Rect rect = this.f354q.k;
            a(focusedChild, rect);
            if (rect.left - i >= B || rect.right - i <= D || rect.top - i2 >= C || rect.bottom - i2 <= E) {
                return false;
            }
            return true;
        }

        @Deprecated
        public boolean a(RecyclerView recyclerView, View view, View view2) {
            return v() || recyclerView.o();
        }

        public boolean a(RecyclerView recyclerView, s sVar, View view, View view2) {
            return a(recyclerView, view, view2);
        }

        public void a(a aVar, a aVar2) {
        }

        public boolean a(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        public void a(RecyclerView recyclerView) {
        }

        public void a(RecyclerView recyclerView, int i, int i2) {
        }

        public void b(RecyclerView recyclerView, int i, int i2) {
        }

        public void c(RecyclerView recyclerView, int i, int i2) {
        }

        public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
            c(recyclerView, i, i2);
        }

        public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int e(s sVar) {
            return 0;
        }

        public int c(s sVar) {
            return 0;
        }

        public int g(s sVar) {
            return 0;
        }

        public int f(s sVar) {
            return 0;
        }

        public int d(s sVar) {
            return 0;
        }

        public int h(s sVar) {
            return 0;
        }

        public void a(o oVar, s sVar, int i, int i2) {
            this.f354q.e(i, i2);
        }

        public void g(int i, int i2) {
            this.f354q.setMeasuredDimension(i, i2);
        }

        public int I() {
            return ViewCompat.getMinimumWidth(this.f354q);
        }

        public int J() {
            return ViewCompat.getMinimumHeight(this.f354q);
        }

        public Parcelable e() {
            return null;
        }

        public void a(Parcelable parcelable) {
        }

        /* access modifiers changed from: 0000 */
        public void K() {
            if (this.t != null) {
                this.t.b();
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(r rVar) {
            if (this.t == rVar) {
                this.t = null;
            }
        }

        public void l(int i) {
        }

        public void c(o oVar) {
            for (int y2 = y() - 1; y2 >= 0; y2--) {
                if (!RecyclerView.e(i(y2)).shouldIgnore()) {
                    a(y2, oVar);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            a(this.f354q.e, this.f354q.D, accessibilityNodeInfoCompat);
        }

        public void a(o oVar, s sVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.f354q.canScrollVertically(-1) || this.f354q.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.f354q.canScrollVertically(1) || this.f354q.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(CollectionInfoCompat.obtain(a(oVar, sVar), b(oVar, sVar), e(oVar, sVar), d(oVar, sVar)));
        }

        public void a(AccessibilityEvent accessibilityEvent) {
            a(this.f354q.e, this.f354q.D, accessibilityEvent);
        }

        public void a(o oVar, s sVar, AccessibilityEvent accessibilityEvent) {
            boolean z = true;
            if (this.f354q != null && accessibilityEvent != null) {
                if (!this.f354q.canScrollVertically(1) && !this.f354q.canScrollVertically(-1) && !this.f354q.canScrollHorizontally(-1) && !this.f354q.canScrollHorizontally(1)) {
                    z = false;
                }
                accessibilityEvent.setScrollable(z);
                if (this.f354q.m != null) {
                    accessibilityEvent.setItemCount(this.f354q.m.getItemCount());
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            v e2 = RecyclerView.e(view);
            if (e2 != null && !e2.isRemoved() && !this.p.c(e2.itemView)) {
                a(this.f354q.e, this.f354q.D, view, accessibilityNodeInfoCompat);
            }
        }

        public void a(o oVar, s sVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i;
            int i2 = g() ? d(view) : 0;
            if (f()) {
                i = d(view);
            } else {
                i = 0;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(i2, 1, i, 1, false, false));
        }

        public void L() {
            this.u = true;
        }

        public int d(o oVar, s sVar) {
            return 0;
        }

        public int a(o oVar, s sVar) {
            if (this.f354q == null || this.f354q.m == null || !g()) {
                return 1;
            }
            return this.f354q.m.getItemCount();
        }

        public int b(o oVar, s sVar) {
            if (this.f354q == null || this.f354q.m == null || !f()) {
                return 1;
            }
            return this.f354q.m.getItemCount();
        }

        public boolean e(o oVar, s sVar) {
            return false;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(int i, Bundle bundle) {
            return a(this.f354q.e, this.f354q.D, i, bundle);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x007a, code lost:
            r3 = r0;
            r0 = 0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(android.support.v7.widget.RecyclerView.o r7, android.support.v7.widget.RecyclerView.s r8, int r9, android.os.Bundle r10) {
            /*
                r6 = this;
                r4 = -1
                r2 = 1
                r1 = 0
                android.support.v7.widget.RecyclerView r0 = r6.f354q
                if (r0 != 0) goto L_0x0008
            L_0x0007:
                return r1
            L_0x0008:
                switch(r9) {
                    case 4096: goto L_0x004a;
                    case 8192: goto L_0x0018;
                    default: goto L_0x000b;
                }
            L_0x000b:
                r0 = r1
                r3 = r1
            L_0x000d:
                if (r3 != 0) goto L_0x0011
                if (r0 == 0) goto L_0x0007
            L_0x0011:
                android.support.v7.widget.RecyclerView r1 = r6.f354q
                r1.a(r0, r3)
                r1 = r2
                goto L_0x0007
            L_0x0018:
                android.support.v7.widget.RecyclerView r0 = r6.f354q
                boolean r0 = r0.canScrollVertically(r4)
                if (r0 == 0) goto L_0x007f
                int r0 = r6.C()
                int r3 = r6.E()
                int r0 = r0 - r3
                int r3 = r6.G()
                int r0 = r0 - r3
                int r0 = -r0
            L_0x002f:
                android.support.v7.widget.RecyclerView r3 = r6.f354q
                boolean r3 = r3.canScrollHorizontally(r4)
                if (r3 == 0) goto L_0x007a
                int r3 = r6.B()
                int r4 = r6.D()
                int r3 = r3 - r4
                int r4 = r6.F()
                int r3 = r3 - r4
                int r3 = -r3
                r5 = r3
                r3 = r0
                r0 = r5
                goto L_0x000d
            L_0x004a:
                android.support.v7.widget.RecyclerView r0 = r6.f354q
                boolean r0 = r0.canScrollVertically(r2)
                if (r0 == 0) goto L_0x007d
                int r0 = r6.C()
                int r3 = r6.E()
                int r0 = r0 - r3
                int r3 = r6.G()
                int r0 = r0 - r3
            L_0x0060:
                android.support.v7.widget.RecyclerView r3 = r6.f354q
                boolean r3 = r3.canScrollHorizontally(r2)
                if (r3 == 0) goto L_0x007a
                int r3 = r6.B()
                int r4 = r6.D()
                int r3 = r3 - r4
                int r4 = r6.F()
                int r3 = r3 - r4
                r5 = r3
                r3 = r0
                r0 = r5
                goto L_0x000d
            L_0x007a:
                r3 = r0
                r0 = r1
                goto L_0x000d
            L_0x007d:
                r0 = r1
                goto L_0x0060
            L_0x007f:
                r0 = r1
                goto L_0x002f
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.i.a(android.support.v7.widget.RecyclerView$o, android.support.v7.widget.RecyclerView$s, int, android.os.Bundle):boolean");
        }

        /* access modifiers changed from: 0000 */
        public boolean a(View view, int i, Bundle bundle) {
            return a(this.f354q.e, this.f354q.D, view, i, bundle);
        }

        public boolean a(o oVar, s sVar, View view, int i, Bundle bundle) {
            return false;
        }

        public static b a(Context context, AttributeSet attributeSet, int i, int i2) {
            b bVar = new b();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, i2);
            bVar.a = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 1);
            bVar.b = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, 1);
            bVar.c = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
            bVar.d = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return bVar;
        }

        /* access modifiers changed from: 0000 */
        public void f(RecyclerView recyclerView) {
            d(MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        /* access modifiers changed from: 0000 */
        public boolean m() {
            return false;
        }

        /* access modifiers changed from: 0000 */
        public boolean M() {
            int y2 = y();
            for (int i = 0; i < y2; i++) {
                android.view.ViewGroup.LayoutParams layoutParams = i(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public interface j {
        void a(View view);

        void b(View view);
    }

    public static abstract class k {
        public abstract boolean a(int i, int i2);
    }

    public interface l {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class m {
        public void a(RecyclerView recyclerView, int i) {
        }

        public void a(RecyclerView recyclerView, int i, int i2) {
        }
    }

    public static class n {
        SparseArray<a> a = new SparseArray<>();
        private int b = 0;

        static class a {
            final ArrayList<v> a = new ArrayList<>();
            int b = 5;
            long c = 0;
            long d = 0;

            a() {
            }
        }

        public void a() {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.a.size()) {
                    ((a) this.a.valueAt(i2)).a.clear();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }

        public v a(int i) {
            a aVar = (a) this.a.get(i);
            if (aVar == null || aVar.a.isEmpty()) {
                return null;
            }
            ArrayList<v> arrayList = aVar.a;
            return (v) arrayList.remove(arrayList.size() - 1);
        }

        public void a(v vVar) {
            int itemViewType = vVar.getItemViewType();
            ArrayList<v> arrayList = b(itemViewType).a;
            if (((a) this.a.get(itemViewType)).b > arrayList.size()) {
                vVar.resetInternal();
                arrayList.add(vVar);
            }
        }

        /* access modifiers changed from: 0000 */
        public long a(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, long j) {
            a b2 = b(i);
            b2.c = a(b2.c, j);
        }

        /* access modifiers changed from: 0000 */
        public void b(int i, long j) {
            a b2 = b(i);
            b2.d = a(b2.d, j);
        }

        /* access modifiers changed from: 0000 */
        public boolean a(int i, long j, long j2) {
            long j3 = b(i).c;
            return j3 == 0 || j3 + j < j2;
        }

        /* access modifiers changed from: 0000 */
        public boolean b(int i, long j, long j2) {
            long j3 = b(i).d;
            return j3 == 0 || j3 + j < j2;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.b++;
        }

        /* access modifiers changed from: 0000 */
        public void c() {
            this.b--;
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar, a aVar2, boolean z) {
            if (aVar != null) {
                c();
            }
            if (!z && this.b == 0) {
                a();
            }
            if (aVar2 != null) {
                b();
            }
        }

        private a b(int i) {
            a aVar = (a) this.a.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.a.put(i, aVar2);
            return aVar2;
        }
    }

    public final class o {
        final ArrayList<v> a = new ArrayList<>();
        ArrayList<v> b = null;
        final ArrayList<v> c = new ArrayList<>();
        int d = 2;
        n e;
        private final List<v> g = Collections.unmodifiableList(this.a);
        private int h = 2;
        private t i;

        public o() {
        }

        public void a() {
            this.a.clear();
            d();
        }

        public void a(int i2) {
            this.h = i2;
            b();
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.d = (RecyclerView.this.n != null ? RecyclerView.this.n.x : 0) + this.h;
            for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.d; size--) {
                d(size);
            }
        }

        public List<v> c() {
            return this.g;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(v vVar) {
            if (vVar.isRemoved()) {
                return RecyclerView.this.D.a();
            }
            if (vVar.mPosition < 0 || vVar.mPosition >= RecyclerView.this.m.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + vVar + RecyclerView.this.a());
            } else if (!RecyclerView.this.D.a() && RecyclerView.this.m.getItemViewType(vVar.mPosition) != vVar.getItemViewType()) {
                return false;
            } else {
                if (!RecyclerView.this.m.hasStableIds() || vVar.getItemId() == RecyclerView.this.m.getItemId(vVar.mPosition)) {
                    return true;
                }
                return false;
            }
        }

        private boolean a(v vVar, int i2, int i3, long j) {
            vVar.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = vVar.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != Long.MAX_VALUE && !this.e.b(itemViewType, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.m.bindViewHolder(vVar, i2);
            this.e.b(vVar.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
            e(vVar);
            if (RecyclerView.this.D.a()) {
                vVar.mPreLayoutPosition = i3;
            }
            return true;
        }

        public int b(int i2) {
            if (i2 >= 0 && i2 < RecyclerView.this.D.e()) {
                return !RecyclerView.this.D.a() ? i2 : RecyclerView.this.f.b(i2);
            }
            throw new IndexOutOfBoundsException("invalid position " + i2 + ". State " + "item count is " + RecyclerView.this.D.e() + RecyclerView.this.a());
        }

        public View c(int i2) {
            return a(i2, false);
        }

        /* access modifiers changed from: 0000 */
        public View a(int i2, boolean z) {
            return a(i2, z, Long.MAX_VALUE).itemView;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0126  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0174  */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x018a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.v7.widget.RecyclerView.v a(int r12, boolean r13, long r14) {
            /*
                r11 = this;
                r10 = 8192(0x2000, float:1.14794E-41)
                r8 = 0
                r6 = 1
                r7 = 0
                if (r12 < 0) goto L_0x0011
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r0 = r0.D
                int r0 = r0.e()
                if (r12 < r0) goto L_0x0050
            L_0x0011:
                java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Invalid item position "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r12)
                java.lang.String r2 = "("
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r12)
                java.lang.String r2 = "). Item count:"
                java.lang.StringBuilder r1 = r1.append(r2)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r2 = r2.D
                int r2 = r2.e()
                java.lang.StringBuilder r1 = r1.append(r2)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                java.lang.String r2 = r2.a()
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0050:
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r0 = r0.D
                boolean r0 = r0.a()
                if (r0 == 0) goto L_0x0281
                android.support.v7.widget.RecyclerView$v r1 = r11.f(r12)
                if (r1 == 0) goto L_0x00e6
                r0 = r6
            L_0x0061:
                r2 = r0
                r0 = r1
            L_0x0063:
                if (r0 != 0) goto L_0x008b
                android.support.v7.widget.RecyclerView$v r0 = r11.b(r12, r13)
                if (r0 == 0) goto L_0x008b
                boolean r1 = r11.a(r0)
                if (r1 != 0) goto L_0x00f3
                if (r13 != 0) goto L_0x008a
                r1 = 4
                r0.addFlags(r1)
                boolean r1 = r0.isScrap()
                if (r1 == 0) goto L_0x00e9
                android.support.v7.widget.RecyclerView r1 = android.support.v7.widget.RecyclerView.this
                android.view.View r3 = r0.itemView
                r1.removeDetachedView(r3, r7)
                r0.unScrap()
            L_0x0087:
                r11.b(r0)
            L_0x008a:
                r0 = r8
            L_0x008b:
                if (r0 != 0) goto L_0x027d
                android.support.v7.widget.RecyclerView r1 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.c r1 = r1.f
                int r3 = r1.b(r12)
                if (r3 < 0) goto L_0x00a1
                android.support.v7.widget.RecyclerView r1 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$a r1 = r1.m
                int r1 = r1.getItemCount()
                if (r3 < r1) goto L_0x00f5
            L_0x00a1:
                java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Inconsistency detected. Invalid item position "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r12)
                java.lang.String r2 = "(offset:"
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r2 = ")."
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r2 = "state:"
                java.lang.StringBuilder r1 = r1.append(r2)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r2 = r2.D
                int r2 = r2.e()
                java.lang.StringBuilder r1 = r1.append(r2)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                java.lang.String r2 = r2.a()
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x00e6:
                r0 = r7
                goto L_0x0061
            L_0x00e9:
                boolean r1 = r0.wasReturnedFromScrap()
                if (r1 == 0) goto L_0x0087
                r0.clearReturnedFromScrapFlag()
                goto L_0x0087
            L_0x00f3:
                r2 = r6
                goto L_0x008b
            L_0x00f5:
                android.support.v7.widget.RecyclerView r1 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$a r1 = r1.m
                int r1 = r1.getItemViewType(r3)
                android.support.v7.widget.RecyclerView r4 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$a r4 = r4.m
                boolean r4 = r4.hasStableIds()
                if (r4 == 0) goto L_0x027a
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$a r0 = r0.m
                long r4 = r0.getItemId(r3)
                android.support.v7.widget.RecyclerView$v r0 = r11.a(r4, r1, r13)
                if (r0 == 0) goto L_0x027a
                r0.mPosition = r3
                r9 = r6
            L_0x0118:
                if (r0 != 0) goto L_0x0172
                android.support.v7.widget.RecyclerView$t r2 = r11.i
                if (r2 == 0) goto L_0x0172
                android.support.v7.widget.RecyclerView$t r2 = r11.i
                android.view.View r2 = r2.a(r11, r12, r1)
                if (r2 == 0) goto L_0x0172
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$v r0 = r0.b(r2)
                if (r0 != 0) goto L_0x014d
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "getViewForPositionAndType returned a view which does not have a ViewHolder"
                java.lang.StringBuilder r1 = r1.append(r2)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                java.lang.String r2 = r2.a()
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x014d:
                boolean r2 = r0.shouldIgnore()
                if (r2 == 0) goto L_0x0172
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view."
                java.lang.StringBuilder r1 = r1.append(r2)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                java.lang.String r2 = r2.a()
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0172:
                if (r0 != 0) goto L_0x0188
                android.support.v7.widget.RecyclerView$n r0 = r11.g()
                android.support.v7.widget.RecyclerView$v r0 = r0.a(r1)
                if (r0 == 0) goto L_0x0188
                r0.resetInternal()
                boolean r2 = android.support.v7.widget.RecyclerView.a
                if (r2 == 0) goto L_0x0188
                r11.f(r0)
            L_0x0188:
                if (r0 != 0) goto L_0x01ce
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                long r2 = r0.getNanoTime()
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r0 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
                if (r0 == 0) goto L_0x01a4
                android.support.v7.widget.RecyclerView$n r0 = r11.e
                r4 = r14
                boolean r0 = r0.a(r1, r2, r4)
                if (r0 != 0) goto L_0x01a4
                r1 = r8
            L_0x01a3:
                return r1
            L_0x01a4:
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$a r0 = r0.m
                android.support.v7.widget.RecyclerView r4 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$v r0 = r0.createViewHolder(r4, r1)
                boolean r4 = android.support.v7.widget.RecyclerView.d
                if (r4 == 0) goto L_0x01c1
                android.view.View r4 = r0.itemView
                android.support.v7.widget.RecyclerView r4 = android.support.v7.widget.RecyclerView.k(r4)
                if (r4 == 0) goto L_0x01c1
                java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference
                r5.<init>(r4)
                r0.mNestedRecyclerView = r5
            L_0x01c1:
                android.support.v7.widget.RecyclerView r4 = android.support.v7.widget.RecyclerView.this
                long r4 = r4.getNanoTime()
                android.support.v7.widget.RecyclerView$n r8 = r11.e
                long r2 = r4 - r2
                r8.a(r1, r2)
            L_0x01ce:
                r1 = r0
                r8 = r9
            L_0x01d0:
                if (r8 == 0) goto L_0x0208
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r0 = r0.D
                boolean r0 = r0.a()
                if (r0 != 0) goto L_0x0208
                boolean r0 = r1.hasAnyOfTheFlags(r10)
                if (r0 == 0) goto L_0x0208
                r1.setFlags(r7, r10)
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r0 = r0.D
                boolean r0 = r0.j
                if (r0 == 0) goto L_0x0208
                int r0 = android.support.v7.widget.RecyclerView.f.e(r1)
                r0 = r0 | 4096(0x1000, float:5.74E-42)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$f r2 = r2.z
                android.support.v7.widget.RecyclerView r3 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r3 = r3.D
                java.util.List r4 = r1.getUnmodifiedPayloads()
                android.support.v7.widget.RecyclerView$f$c r0 = r2.a(r3, r1, r0, r4)
                android.support.v7.widget.RecyclerView r2 = android.support.v7.widget.RecyclerView.this
                r2.a(r1, r0)
            L_0x0208:
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$s r0 = r0.D
                boolean r0 = r0.a()
                if (r0 == 0) goto L_0x023a
                boolean r0 = r1.isBound()
                if (r0 == 0) goto L_0x023a
                r1.mPreLayoutPosition = r12
                r2 = r7
            L_0x021b:
                android.view.View r0 = r1.itemView
                android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
                if (r0 != 0) goto L_0x025d
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r0 = r0.generateDefaultLayoutParams()
                android.support.v7.widget.RecyclerView$LayoutParams r0 = (android.support.v7.widget.RecyclerView.LayoutParams) r0
                android.view.View r3 = r1.itemView
                r3.setLayoutParams(r0)
            L_0x0230:
                r0.c = r1
                if (r8 == 0) goto L_0x0276
                if (r2 == 0) goto L_0x0276
            L_0x0236:
                r0.f = r6
                goto L_0x01a3
            L_0x023a:
                boolean r0 = r1.isBound()
                if (r0 == 0) goto L_0x024c
                boolean r0 = r1.needsUpdate()
                if (r0 != 0) goto L_0x024c
                boolean r0 = r1.isInvalid()
                if (r0 == 0) goto L_0x0278
            L_0x024c:
                android.support.v7.widget.RecyclerView r0 = android.support.v7.widget.RecyclerView.this
                android.support.v7.widget.c r0 = r0.f
                int r2 = r0.b(r12)
                r0 = r11
                r3 = r12
                r4 = r14
                boolean r0 = r0.a(r1, r2, r3, r4)
                r2 = r0
                goto L_0x021b
            L_0x025d:
                android.support.v7.widget.RecyclerView r3 = android.support.v7.widget.RecyclerView.this
                boolean r3 = r3.checkLayoutParams(r0)
                if (r3 != 0) goto L_0x0273
                android.support.v7.widget.RecyclerView r3 = android.support.v7.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r0 = r3.generateLayoutParams(r0)
                android.support.v7.widget.RecyclerView$LayoutParams r0 = (android.support.v7.widget.RecyclerView.LayoutParams) r0
                android.view.View r3 = r1.itemView
                r3.setLayoutParams(r0)
                goto L_0x0230
            L_0x0273:
                android.support.v7.widget.RecyclerView$LayoutParams r0 = (android.support.v7.widget.RecyclerView.LayoutParams) r0
                goto L_0x0230
            L_0x0276:
                r6 = r7
                goto L_0x0236
            L_0x0278:
                r2 = r7
                goto L_0x021b
            L_0x027a:
                r9 = r2
                goto L_0x0118
            L_0x027d:
                r1 = r0
                r8 = r2
                goto L_0x01d0
            L_0x0281:
                r0 = r8
                r2 = r7
                goto L_0x0063
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.o.a(int, boolean, long):android.support.v7.widget.RecyclerView$v");
        }

        private void e(v vVar) {
            if (RecyclerView.this.n()) {
                View view = vVar.itemView;
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                if (!ViewCompat.hasAccessibilityDelegate(view)) {
                    vVar.addFlags(16384);
                    ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.H.b());
                }
            }
        }

        private void f(v vVar) {
            if (vVar.itemView instanceof ViewGroup) {
                a((ViewGroup) vVar.itemView, false);
            }
        }

        private void a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                    return;
                }
                int visibility = viewGroup.getVisibility();
                viewGroup.setVisibility(4);
                viewGroup.setVisibility(visibility);
            }
        }

        public void a(View view) {
            v e2 = RecyclerView.e(view);
            if (e2.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (e2.isScrap()) {
                e2.unScrap();
            } else if (e2.wasReturnedFromScrap()) {
                e2.clearReturnedFromScrapFlag();
            }
            b(e2);
        }

        /* access modifiers changed from: 0000 */
        public void d() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                d(size);
            }
            this.c.clear();
            if (RecyclerView.d) {
                RecyclerView.this.C.a();
            }
        }

        /* access modifiers changed from: 0000 */
        public void d(int i2) {
            a((v) this.c.get(i2), true);
            this.c.remove(i2);
        }

        /* access modifiers changed from: 0000 */
        public void b(v vVar) {
            boolean z;
            boolean z2 = false;
            if (vVar.isScrap() || vVar.itemView.getParent() != null) {
                throw new IllegalArgumentException("Scrapped or attached views may not be recycled. isScrap:" + vVar.isScrap() + " isAttached:" + (vVar.itemView.getParent() != null) + RecyclerView.this.a());
            } else if (vVar.isTmpDetached()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + vVar + RecyclerView.this.a());
            } else if (vVar.shouldIgnore()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.a());
            } else {
                boolean doesTransientStatePreventRecycling = vVar.doesTransientStatePreventRecycling();
                if ((RecyclerView.this.m != null && doesTransientStatePreventRecycling && RecyclerView.this.m.onFailedToRecycleView(vVar)) || vVar.isRecyclable()) {
                    if (this.d <= 0 || vVar.hasAnyOfTheFlags(526)) {
                        z = false;
                    } else {
                        int size = this.c.size();
                        if (size >= this.d && size > 0) {
                            d(0);
                            size--;
                        }
                        if (RecyclerView.d && size > 0 && !RecyclerView.this.C.a(vVar.mPosition)) {
                            int i2 = size - 1;
                            while (i2 >= 0) {
                                if (!RecyclerView.this.C.a(((v) this.c.get(i2)).mPosition)) {
                                    break;
                                }
                                i2--;
                            }
                            size = i2 + 1;
                        }
                        this.c.add(size, vVar);
                        z = true;
                    }
                    if (!z) {
                        a(vVar, true);
                        z2 = true;
                    }
                } else {
                    z = false;
                }
                RecyclerView.this.h.g(vVar);
                if (!z && !z2 && doesTransientStatePreventRecycling) {
                    vVar.mOwnerRecyclerView = null;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(v vVar, boolean z) {
            RecyclerView.c(vVar);
            if (vVar.hasAnyOfTheFlags(16384)) {
                vVar.setFlags(0, 16384);
                ViewCompat.setAccessibilityDelegate(vVar.itemView, null);
            }
            if (z) {
                d(vVar);
            }
            vVar.mOwnerRecyclerView = null;
            g().a(vVar);
        }

        /* access modifiers changed from: 0000 */
        public void b(View view) {
            v e2 = RecyclerView.e(view);
            e2.mScrapContainer = null;
            e2.mInChangeScrap = false;
            e2.clearReturnedFromScrapFlag();
            b(e2);
        }

        /* access modifiers changed from: 0000 */
        public void c(View view) {
            v e2 = RecyclerView.e(view);
            if (!e2.hasAnyOfTheFlags(12) && e2.isUpdated() && !RecyclerView.this.b(e2)) {
                if (this.b == null) {
                    this.b = new ArrayList<>();
                }
                e2.setScrapContainer(this, true);
                this.b.add(e2);
            } else if (!e2.isInvalid() || e2.isRemoved() || RecyclerView.this.m.hasStableIds()) {
                e2.setScrapContainer(this, false);
                this.a.add(e2);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.a());
            }
        }

        /* access modifiers changed from: 0000 */
        public void c(v vVar) {
            if (vVar.mInChangeScrap) {
                this.b.remove(vVar);
            } else {
                this.a.remove(vVar);
            }
            vVar.mScrapContainer = null;
            vVar.mInChangeScrap = false;
            vVar.clearReturnedFromScrapFlag();
        }

        /* access modifiers changed from: 0000 */
        public int e() {
            return this.a.size();
        }

        /* access modifiers changed from: 0000 */
        public View e(int i2) {
            return ((v) this.a.get(i2)).itemView;
        }

        /* access modifiers changed from: 0000 */
        public void f() {
            this.a.clear();
            if (this.b != null) {
                this.b.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public v f(int i2) {
            int i3 = 0;
            if (this.b != null) {
                int size = this.b.size();
                if (size != 0) {
                    int i4 = 0;
                    while (i4 < size) {
                        v vVar = (v) this.b.get(i4);
                        if (vVar.wasReturnedFromScrap() || vVar.getLayoutPosition() != i2) {
                            i4++;
                        } else {
                            vVar.addFlags(32);
                            return vVar;
                        }
                    }
                    if (RecyclerView.this.m.hasStableIds()) {
                        int b2 = RecyclerView.this.f.b(i2);
                        if (b2 > 0 && b2 < RecyclerView.this.m.getItemCount()) {
                            long itemId = RecyclerView.this.m.getItemId(b2);
                            while (i3 < size) {
                                v vVar2 = (v) this.b.get(i3);
                                if (vVar2.wasReturnedFromScrap() || vVar2.getItemId() != itemId) {
                                    i3++;
                                } else {
                                    vVar2.addFlags(32);
                                    return vVar2;
                                }
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public v b(int i2, boolean z) {
            int i3 = 0;
            int size = this.a.size();
            int i4 = 0;
            while (i4 < size) {
                v vVar = (v) this.a.get(i4);
                if (vVar.wasReturnedFromScrap() || vVar.getLayoutPosition() != i2 || vVar.isInvalid() || (!RecyclerView.this.D.g && vVar.isRemoved())) {
                    i4++;
                } else {
                    vVar.addFlags(32);
                    return vVar;
                }
            }
            if (!z) {
                View c2 = RecyclerView.this.g.c(i2);
                if (c2 != null) {
                    v e2 = RecyclerView.e(c2);
                    RecyclerView.this.g.e(c2);
                    int b2 = RecyclerView.this.g.b(c2);
                    if (b2 == -1) {
                        throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + e2 + RecyclerView.this.a());
                    }
                    RecyclerView.this.g.e(b2);
                    c(c2);
                    e2.addFlags(8224);
                    return e2;
                }
            }
            int size2 = this.c.size();
            while (i3 < size2) {
                v vVar2 = (v) this.c.get(i3);
                if (vVar2.isInvalid() || vVar2.getLayoutPosition() != i2) {
                    i3++;
                } else if (z) {
                    return vVar2;
                } else {
                    this.c.remove(i3);
                    return vVar2;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public v a(long j, int i2, boolean z) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                v vVar = (v) this.a.get(size);
                if (vVar.getItemId() == j && !vVar.wasReturnedFromScrap()) {
                    if (i2 == vVar.getItemViewType()) {
                        vVar.addFlags(32);
                        if (!vVar.isRemoved() || RecyclerView.this.D.a()) {
                            return vVar;
                        }
                        vVar.setFlags(2, 14);
                        return vVar;
                    } else if (!z) {
                        this.a.remove(size);
                        RecyclerView.this.removeDetachedView(vVar.itemView, false);
                        b(vVar.itemView);
                    }
                }
            }
            for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
                v vVar2 = (v) this.c.get(size2);
                if (vVar2.getItemId() == j) {
                    if (i2 == vVar2.getItemViewType()) {
                        if (z) {
                            return vVar2;
                        }
                        this.c.remove(size2);
                        return vVar2;
                    } else if (!z) {
                        d(size2);
                        return null;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public void d(v vVar) {
            if (RecyclerView.this.o != null) {
                RecyclerView.this.o.a(vVar);
            }
            if (RecyclerView.this.m != null) {
                RecyclerView.this.m.onViewRecycled(vVar);
            }
            if (RecyclerView.this.D != null) {
                RecyclerView.this.h.g(vVar);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar, a aVar2, boolean z) {
            a();
            g().a(aVar, aVar2, z);
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            if (i2 < i3) {
                i4 = -1;
                i5 = i3;
                i6 = i2;
            } else {
                i4 = 1;
                i5 = i2;
                i6 = i3;
            }
            int size = this.c.size();
            for (int i7 = 0; i7 < size; i7++) {
                v vVar = (v) this.c.get(i7);
                if (vVar != null && vVar.mPosition >= i6 && vVar.mPosition <= i5) {
                    if (vVar.mPosition == i2) {
                        vVar.offsetPosition(i3 - i2, false);
                    } else {
                        vVar.offsetPosition(i4, false);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(int i2, int i3) {
            int size = this.c.size();
            for (int i4 = 0; i4 < size; i4++) {
                v vVar = (v) this.c.get(i4);
                if (vVar != null && vVar.mPosition >= i2) {
                    vVar.offsetPosition(i3, true);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2, int i3, boolean z) {
            int i4 = i2 + i3;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                v vVar = (v) this.c.get(size);
                if (vVar != null) {
                    if (vVar.mPosition >= i4) {
                        vVar.offsetPosition(-i3, z);
                    } else if (vVar.mPosition >= i2) {
                        vVar.addFlags(8);
                        d(size);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(t tVar) {
            this.i = tVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(n nVar) {
            if (this.e != null) {
                this.e.c();
            }
            this.e = nVar;
            if (this.e != null && RecyclerView.this.getAdapter() != null) {
                this.e.b();
            }
        }

        /* access modifiers changed from: 0000 */
        public n g() {
            if (this.e == null) {
                this.e = new n();
            }
            return this.e;
        }

        /* access modifiers changed from: 0000 */
        public void c(int i2, int i3) {
            int i4 = i2 + i3;
            for (int size = this.c.size() - 1; size >= 0; size--) {
                v vVar = (v) this.c.get(size);
                if (vVar != null) {
                    int i5 = vVar.mPosition;
                    if (i5 >= i2 && i5 < i4) {
                        vVar.addFlags(2);
                        d(size);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void h() {
            int size = this.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                v vVar = (v) this.c.get(i2);
                if (vVar != null) {
                    vVar.addFlags(6);
                    vVar.addChangePayload(null);
                }
            }
            if (RecyclerView.this.m == null || !RecyclerView.this.m.hasStableIds()) {
                d();
            }
        }

        /* access modifiers changed from: 0000 */
        public void i() {
            int size = this.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((v) this.c.get(i2)).clearOldPosition();
            }
            int size2 = this.a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((v) this.a.get(i3)).clearOldPosition();
            }
            if (this.b != null) {
                int size3 = this.b.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    ((v) this.b.get(i4)).clearOldPosition();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void j() {
            int size = this.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                LayoutParams layoutParams = (LayoutParams) ((v) this.c.get(i2)).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.e = true;
                }
            }
        }
    }

    public interface p {
        void a(v vVar);
    }

    private class q extends c {
        q() {
        }

        public void a() {
            RecyclerView.this.a((String) null);
            RecyclerView.this.D.f = true;
            RecyclerView.this.c(true);
            if (!RecyclerView.this.f.d()) {
                RecyclerView.this.requestLayout();
            }
        }

        public void a(int i, int i2, Object obj) {
            RecyclerView.this.a((String) null);
            if (RecyclerView.this.f.a(i, i2, obj)) {
                b();
            }
        }

        public void b(int i, int i2) {
            RecyclerView.this.a((String) null);
            if (RecyclerView.this.f.b(i, i2)) {
                b();
            }
        }

        public void c(int i, int i2) {
            RecyclerView.this.a((String) null);
            if (RecyclerView.this.f.c(i, i2)) {
                b();
            }
        }

        public void a(int i, int i2, int i3) {
            RecyclerView.this.a((String) null);
            if (RecyclerView.this.f.a(i, i2, i3)) {
                b();
            }
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            if (!RecyclerView.c || !RecyclerView.this.r || !RecyclerView.this.f353q) {
                RecyclerView.this.w = true;
                RecyclerView.this.requestLayout();
                return;
            }
            ViewCompat.postOnAnimation(RecyclerView.this, RecyclerView.this.j);
        }
    }

    public static abstract class r {
        private int a;
        private RecyclerView b;
        private i c;
        private boolean d;
        private boolean e;
        private View f;
        private final a g;

        public static class a {
            private int a;
            private int b;
            private int c;
            private int d;
            private Interpolator e;
            private boolean f;
            private int g;

            /* access modifiers changed from: 0000 */
            public boolean a() {
                return this.d >= 0;
            }

            /* access modifiers changed from: 0000 */
            public void a(RecyclerView recyclerView) {
                if (this.d >= 0) {
                    int i = this.d;
                    this.d = -1;
                    recyclerView.b(i);
                    this.f = false;
                } else if (this.f) {
                    b();
                    if (this.e != null) {
                        recyclerView.A.a(this.a, this.b, this.c, this.e);
                    } else if (this.c == Integer.MIN_VALUE) {
                        recyclerView.A.b(this.a, this.b);
                    } else {
                        recyclerView.A.a(this.a, this.b, this.c);
                    }
                    this.g++;
                    if (this.g > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                } else {
                    this.g = 0;
                }
            }

            private void b() {
                if (this.e != null && this.c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }
        }

        public interface b {
            PointF d(int i);
        }

        /* access modifiers changed from: protected */
        public abstract void a(int i, int i2, s sVar, a aVar);

        /* access modifiers changed from: protected */
        public abstract void a(View view, s sVar, a aVar);

        /* access modifiers changed from: protected */
        public abstract void f();

        public void a(int i) {
            this.a = i;
        }

        public PointF b(int i) {
            i a2 = a();
            if (a2 instanceof b) {
                return ((b) a2).d(i);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        public i a() {
            return this.c;
        }

        /* access modifiers changed from: protected */
        public final void b() {
            if (this.e) {
                this.e = false;
                f();
                this.b.D.a = -1;
                this.f = null;
                this.a = -1;
                this.d = false;
                this.c.a(this);
                this.c = null;
                this.b = null;
            }
        }

        public boolean c() {
            return this.d;
        }

        public boolean d() {
            return this.e;
        }

        public int e() {
            return this.a;
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, int i2) {
            RecyclerView recyclerView = this.b;
            if (!this.e || this.a == -1 || recyclerView == null) {
                b();
            }
            if (this.d && this.f == null && this.c != null) {
                PointF b2 = b(this.a);
                if (!(b2 == null || (b2.x == 0.0f && b2.y == 0.0f))) {
                    recyclerView.a((int) Math.signum(b2.x), (int) Math.signum(b2.y), (int[]) null);
                }
            }
            this.d = false;
            if (this.f != null) {
                if (a(this.f) == this.a) {
                    a(this.f, recyclerView.D, this.g);
                    this.g.a(recyclerView);
                    b();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.e) {
                a(i, i2, recyclerView.D, this.g);
                boolean a2 = this.g.a();
                this.g.a(recyclerView);
                if (!a2) {
                    return;
                }
                if (this.e) {
                    this.d = true;
                    recyclerView.A.a();
                    return;
                }
                b();
            }
        }

        public int a(View view) {
            return this.b.g(view);
        }

        /* access modifiers changed from: protected */
        public void b(View view) {
            if (a(view) == e()) {
                this.f = view;
            }
        }
    }

    public static class s {
        int a = -1;
        int b = 0;
        int c = 0;
        int d = 1;
        int e = 0;
        boolean f = false;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        int l;
        long m;
        int n;
        int o;
        int p;

        /* renamed from: q reason: collision with root package name */
        private SparseArray<Object> f355q;

        /* access modifiers changed from: 0000 */
        public void a(int i2) {
            if ((this.d & i2) == 0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.d));
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar) {
            this.d = 1;
            this.e = aVar.getItemCount();
            this.g = false;
            this.h = false;
            this.i = false;
        }

        public boolean a() {
            return this.g;
        }

        public boolean b() {
            return this.k;
        }

        public int c() {
            return this.a;
        }

        public boolean d() {
            return this.a != -1;
        }

        public int e() {
            return this.g ? this.b - this.c : this.e;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.a + ", mData=" + this.f355q + ", mItemCount=" + this.e + ", mIsMeasuring=" + this.i + ", mPreviousLayoutItemCount=" + this.b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.c + ", mStructureChanged=" + this.f + ", mInPreLayout=" + this.g + ", mRunSimpleAnimations=" + this.j + ", mRunPredictiveAnimations=" + this.k + '}';
        }
    }

    public static abstract class t {
        public abstract View a(o oVar, int i, int i2);
    }

    class u implements Runnable {
        OverScroller a;
        Interpolator b = RecyclerView.L;
        private int d;
        private int e;
        private boolean f = false;
        private boolean g = false;

        u() {
            this.a = new OverScroller(RecyclerView.this.getContext(), RecyclerView.L);
        }

        public void run() {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            if (RecyclerView.this.n == null) {
                b();
                return;
            }
            c();
            RecyclerView.this.d();
            OverScroller overScroller = this.a;
            r rVar = RecyclerView.this.n.t;
            if (overScroller.computeScrollOffset()) {
                int[] iArr = RecyclerView.this.I;
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i8 = currX - this.d;
                int i9 = currY - this.e;
                this.d = currX;
                this.e = currY;
                if (RecyclerView.this.dispatchNestedPreScroll(i8, i9, iArr, null, 1)) {
                    i = i9 - iArr[1];
                    i2 = i8 - iArr[0];
                } else {
                    i = i9;
                    i2 = i8;
                }
                if (RecyclerView.this.m != null) {
                    RecyclerView.this.a(i2, i, RecyclerView.this.J);
                    i6 = RecyclerView.this.J[0];
                    i5 = RecyclerView.this.J[1];
                    i4 = i2 - i6;
                    i3 = i - i5;
                    if (rVar != null && !rVar.c() && rVar.d()) {
                        int e2 = RecyclerView.this.D.e();
                        if (e2 == 0) {
                            rVar.b();
                        } else if (rVar.e() >= e2) {
                            rVar.a(e2 - 1);
                            rVar.a(i2 - i4, i - i3);
                        } else {
                            rVar.a(i2 - i4, i - i3);
                        }
                    }
                } else {
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                }
                if (!RecyclerView.this.p.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.c(i2, i);
                }
                if (!RecyclerView.this.dispatchNestedScroll(i6, i5, i4, i3, null, 1) && !(i4 == 0 && i3 == 0)) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    if (i4 != currX) {
                        int i10 = i4 < 0 ? -currVelocity : i4 > 0 ? currVelocity : 0;
                        i7 = i10;
                    } else {
                        i7 = 0;
                    }
                    if (i3 == currY) {
                        currVelocity = 0;
                    } else if (i3 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i3 <= 0) {
                        currVelocity = 0;
                    }
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        RecyclerView.this.d(i7, currVelocity);
                    }
                    if ((i7 != 0 || i4 == currX || overScroller.getFinalX() == 0) && (currVelocity != 0 || i3 == currY || overScroller.getFinalY() == 0)) {
                        overScroller.abortAnimation();
                    }
                }
                if (!(i6 == 0 && i5 == 0)) {
                    RecyclerView.this.i(i6, i5);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = (i2 == 0 && i == 0) || (i2 != 0 && RecyclerView.this.n.f() && i6 == i2) || (i != 0 && RecyclerView.this.n.g() && i5 == i);
                if (overScroller.isFinished() || (!z && !RecyclerView.this.hasNestedScrollingParent(1))) {
                    RecyclerView.this.setScrollState(0);
                    if (RecyclerView.d) {
                        RecyclerView.this.C.a();
                    }
                    RecyclerView.this.stopNestedScroll(1);
                } else {
                    a();
                    if (RecyclerView.this.B != null) {
                        RecyclerView.this.B.a(RecyclerView.this, i2, i);
                    }
                }
            }
            if (rVar != null) {
                if (rVar.c()) {
                    rVar.a(0, 0);
                }
                if (!this.g) {
                    rVar.b();
                }
            }
            d();
        }

        private void c() {
            this.g = false;
            this.f = true;
        }

        private void d() {
            this.f = false;
            if (this.g) {
                a();
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            if (this.f) {
                this.g = true;
                return;
            }
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        public void a(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.e = 0;
            this.d = 0;
            this.a.fling(0, 0, i, i2, ExploreByTouchHelper.INVALID_ID, Integer.MAX_VALUE, ExploreByTouchHelper.INVALID_ID, Integer.MAX_VALUE);
            a();
        }

        public void b(int i, int i2) {
            a(i, i2, 0, 0);
        }

        public void a(int i, int i2, int i3, int i4) {
            a(i, i2, b(i, i2, i3, i4));
        }

        private float a(float f2) {
            return (float) Math.sin((double) ((f2 - 0.5f) * 0.47123894f));
        }

        private int b(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((double) ((i3 * i3) + (i4 * i4)));
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            int height = z ? RecyclerView.this.getWidth() : RecyclerView.this.getHeight();
            int i7 = height / 2;
            float a2 = (a(Math.min(1.0f, (((float) sqrt2) * 1.0f) / ((float) height))) * ((float) i7)) + ((float) i7);
            if (sqrt > 0) {
                i6 = Math.round(1000.0f * Math.abs(a2 / ((float) sqrt))) * 4;
            } else {
                if (z) {
                    i5 = abs;
                } else {
                    i5 = abs2;
                }
                i6 = (int) (((((float) i5) / ((float) height)) + 1.0f) * 300.0f);
            }
            return Math.min(i6, 2000);
        }

        public void a(int i, int i2, int i3) {
            a(i, i2, i3, RecyclerView.L);
        }

        public void a(int i, int i2, Interpolator interpolator) {
            int b2 = b(i, i2, 0, 0);
            if (interpolator == null) {
                interpolator = RecyclerView.L;
            }
            a(i, i2, b2, interpolator);
        }

        public void a(int i, int i2, int i3, Interpolator interpolator) {
            if (this.b != interpolator) {
                this.b = interpolator;
                this.a = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.e = 0;
            this.d = 0;
            this.a.startScroll(0, 0, i, i2, i3);
            if (VERSION.SDK_INT < 23) {
                this.a.computeScrollOffset();
            }
            a();
        }

        public void b() {
            RecyclerView.this.removeCallbacks(this);
            this.a.abortAnimation();
        }
    }

    public static abstract class v {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        int mFlags;
        boolean mInChangeScrap = false;
        private int mIsRecyclableCount = 0;
        long mItemId = -1;
        int mItemViewType = -1;
        WeakReference<RecyclerView> mNestedRecyclerView;
        int mOldPosition = -1;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads = null;
        int mPendingAccessibilityState = -1;
        int mPosition = -1;
        int mPreLayoutPosition = -1;
        o mScrapContainer = null;
        v mShadowedHolder = null;
        v mShadowingHolder = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mWasImportantForAccessibilityBeforeHidden = 0;

        public v(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        /* access modifiers changed from: 0000 */
        public void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
            addFlags(8);
            offsetPosition(i2, z);
            this.mPosition = i;
        }

        /* access modifiers changed from: 0000 */
        public void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).e = true;
            }
        }

        /* access modifiers changed from: 0000 */
        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        /* access modifiers changed from: 0000 */
        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        @Deprecated
        public final int getPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getLayoutPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getAdapterPosition() {
            if (this.mOwnerRecyclerView == null) {
                return -1;
            }
            return this.mOwnerRecyclerView.d(this);
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        /* access modifiers changed from: 0000 */
        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        /* access modifiers changed from: 0000 */
        public void unScrap() {
            this.mScrapContainer.c(this);
        }

        /* access modifiers changed from: 0000 */
        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }

        /* access modifiers changed from: 0000 */
        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        /* access modifiers changed from: 0000 */
        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        /* access modifiers changed from: 0000 */
        public void stopIgnoring() {
            this.mFlags &= -129;
        }

        /* access modifiers changed from: 0000 */
        public void setScrapContainer(o oVar, boolean z) {
            this.mScrapContainer = oVar;
            this.mInChangeScrap = z;
        }

        /* access modifiers changed from: 0000 */
        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean hasAnyOfTheFlags(int i) {
            return (this.mFlags & i) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        /* access modifiers changed from: 0000 */
        public void setFlags(int i, int i2) {
            this.mFlags = (this.mFlags & (i2 ^ -1)) | (i & i2);
        }

        /* access modifiers changed from: 0000 */
        public void addFlags(int i) {
            this.mFlags |= i;
        }

        /* access modifiers changed from: 0000 */
        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((this.mFlags & 1024) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList();
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }
        }

        /* access modifiers changed from: 0000 */
        public void clearPayload() {
            if (this.mPayloads != null) {
                this.mPayloads.clear();
            }
            this.mFlags &= -1025;
        }

        /* access modifiers changed from: 0000 */
        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            if (this.mPayloads == null || this.mPayloads.size() == 0) {
                return FULLUPDATE_PAYLOADS;
            }
            return this.mUnmodifiedPayloads;
        }

        /* access modifiers changed from: 0000 */
        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.c(this);
        }

        /* access modifiers changed from: 0000 */
        public void onEnteredHiddenState(RecyclerView recyclerView) {
            if (this.mPendingAccessibilityState != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = this.mPendingAccessibilityState;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            }
            recyclerView.a(this, 4);
        }

        /* access modifiers changed from: 0000 */
        public void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.a(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ").append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final void setIsRecyclable(boolean z) {
            this.mIsRecyclableCount = z ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
            if (this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && this.mIsRecyclableCount == 1) {
                this.mFlags |= 16;
            } else if (z && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        /* access modifiers changed from: 0000 */
        public boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        /* access modifiers changed from: 0000 */
        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }
    }

    static {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        if (VERSION.SDK_INT >= 23) {
            z2 = true;
        } else {
            z2 = false;
        }
        b = z2;
        if (VERSION.SDK_INT >= 16) {
            z3 = true;
        } else {
            z3 = false;
        }
        c = z3;
        if (VERSION.SDK_INT >= 21) {
            z4 = true;
        } else {
            z4 = false;
        }
        d = z4;
        if (VERSION.SDK_INT <= 15) {
            z5 = true;
        } else {
            z5 = false;
        }
        O = z5;
        if (VERSION.SDK_INT <= 15) {
            z6 = true;
        } else {
            z6 = false;
        }
        P = z6;
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        boolean z2 = true;
        super(context, attributeSet, i2);
        this.R = new q();
        this.e = new o();
        this.h = new ba();
        this.j = new Runnable() {
            public void run() {
                if (RecyclerView.this.t && !RecyclerView.this.isLayoutRequested()) {
                    if (!RecyclerView.this.f353q) {
                        RecyclerView.this.requestLayout();
                    } else if (RecyclerView.this.v) {
                        RecyclerView.this.u = true;
                    } else {
                        RecyclerView.this.d();
                    }
                }
            }
        };
        this.k = new Rect();
        this.T = new Rect();
        this.l = new RectF();
        this.p = new ArrayList<>();
        this.U = new ArrayList<>();
        this.W = 0;
        this.x = false;
        this.y = false;
        this.ae = 0;
        this.af = 0;
        this.ag = new e();
        this.z = new u();
        this.al = 0;
        this.am = -1;
        this.aw = Float.MIN_VALUE;
        this.ax = Float.MIN_VALUE;
        this.ay = true;
        this.A = new u();
        this.C = d ? new a() : null;
        this.D = new s();
        this.E = false;
        this.F = false;
        this.aB = new g();
        this.G = false;
        this.aD = new int[2];
        this.aF = new int[2];
        this.I = new int[2];
        this.aG = new int[2];
        this.J = new int[2];
        this.K = new ArrayList();
        this.aH = new Runnable() {
            public void run() {
                if (RecyclerView.this.z != null) {
                    RecyclerView.this.z.a();
                }
                RecyclerView.this.G = false;
            }
        };
        this.aI = new b() {
            public void a(v vVar, c cVar, c cVar2) {
                RecyclerView.this.e.c(vVar);
                RecyclerView.this.b(vVar, cVar, cVar2);
            }

            public void b(v vVar, c cVar, c cVar2) {
                RecyclerView.this.a(vVar, cVar, cVar2);
            }

            public void c(v vVar, c cVar, c cVar2) {
                vVar.setIsRecyclable(false);
                if (RecyclerView.this.x) {
                    if (RecyclerView.this.z.a(vVar, vVar, cVar, cVar2)) {
                        RecyclerView.this.p();
                    }
                } else if (RecyclerView.this.z.c(vVar, cVar, cVar2)) {
                    RecyclerView.this.p();
                }
            }

            public void a(v vVar) {
                RecyclerView.this.n.a(vVar.itemView, RecyclerView.this.e);
            }
        };
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, N, i2, 0);
            this.i = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        } else {
            this.i = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.as = viewConfiguration.getScaledTouchSlop();
        this.aw = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.ax = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.au = viewConfiguration.getScaledMinimumFlingVelocity();
        this.av = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.z.a(this.aB);
        b();
        z();
        y();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.ac = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i2, 0);
            String string = obtainStyledAttributes2.getString(R.styleable.RecyclerView_layoutManager);
            if (obtainStyledAttributes2.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(262144);
            }
            this.s = obtainStyledAttributes2.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
            if (this.s) {
                a((StateListDrawable) obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes2.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            obtainStyledAttributes2.recycle();
            a(context, string, attributeSet, i2, 0);
            if (VERSION.SDK_INT >= 21) {
                TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, M, i2, 0);
                z2 = obtainStyledAttributes3.getBoolean(0, true);
                obtainStyledAttributes3.recycle();
            }
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z2);
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return " " + super.toString() + ", adapter:" + this.m + ", layout:" + this.n + ", context:" + getContext();
    }

    @SuppressLint({"InlinedApi"})
    private void y() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.H;
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.H = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, this.H);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r9, java.lang.String r10, android.util.AttributeSet r11, int r12, int r13) {
        /*
            r8 = this;
            if (r10 == 0) goto L_0x0054
            java.lang.String r0 = r10.trim()
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0054
            java.lang.String r3 = r8.a(r9, r0)
            boolean r0 = r8.isInEditMode()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            if (r0 == 0) goto L_0x0055
            java.lang.Class r0 = r8.getClass()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
        L_0x001e:
            java.lang.Class r0 = r0.loadClass(r3)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.Class<android.support.v7.widget.RecyclerView$i> r1 = android.support.v7.widget.RecyclerView.i.class
            java.lang.Class r4 = r0.asSubclass(r1)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            r1 = 0
            java.lang.Class<?>[] r0 = Q     // Catch:{ NoSuchMethodException -> 0x005a }
            java.lang.reflect.Constructor r2 = r4.getConstructor(r0)     // Catch:{ NoSuchMethodException -> 0x005a }
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x005a }
            r5 = 0
            r0[r5] = r9     // Catch:{ NoSuchMethodException -> 0x005a }
            r5 = 1
            r0[r5] = r11     // Catch:{ NoSuchMethodException -> 0x005a }
            r5 = 2
            java.lang.Integer r6 = java.lang.Integer.valueOf(r12)     // Catch:{ NoSuchMethodException -> 0x005a }
            r0[r5] = r6     // Catch:{ NoSuchMethodException -> 0x005a }
            r5 = 3
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)     // Catch:{ NoSuchMethodException -> 0x005a }
            r0[r5] = r6     // Catch:{ NoSuchMethodException -> 0x005a }
            r1 = r2
        L_0x0047:
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.Object r0 = r1.newInstance(r0)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            android.support.v7.widget.RecyclerView$i r0 = (android.support.v7.widget.RecyclerView.i) r0     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            r8.setLayoutManager(r0)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
        L_0x0054:
            return
        L_0x0055:
            java.lang.ClassLoader r0 = r9.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            goto L_0x001e
        L_0x005a:
            r0 = move-exception
            r2 = 0
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ NoSuchMethodException -> 0x0066 }
            java.lang.reflect.Constructor r0 = r4.getConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x0066 }
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x0047
        L_0x0066:
            r1 = move-exception
            r1.initCause(r0)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            r2.<init>()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.String r4 = r11.getPositionDescription()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.String r4 = ": Error creating LayoutManager "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            java.lang.String r2 = r2.toString()     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            r0.<init>(r2, r1)     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
            throw r0     // Catch:{ ClassNotFoundException -> 0x008b, InvocationTargetException -> 0x00ad, InstantiationException -> 0x00cf, IllegalAccessException -> 0x00f1, ClassCastException -> 0x0113 }
        L_0x008b:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r11.getPositionDescription()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = ": Unable to find LayoutManager "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x00ad:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r11.getPositionDescription()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = ": Could not instantiate the LayoutManager: "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x00cf:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r11.getPositionDescription()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = ": Could not instantiate the LayoutManager: "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x00f1:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r11.getPositionDescription()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = ": Cannot access non-public constructor "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0113:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r11.getPositionDescription()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = ": Class is not a LayoutManager "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.a(android.content.Context, java.lang.String, android.util.AttributeSet, int, int):void");
    }

    private String a(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        return !str.contains(".") ? RecyclerView.class.getPackage().getName() + '.' + str : str;
    }

    private void z() {
        this.g = new r(new b() {
            public int a() {
                return RecyclerView.this.getChildCount();
            }

            public void a(View view, int i) {
                RecyclerView.this.addView(view, i);
                RecyclerView.this.m(view);
            }

            public int a(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            public void a(int i) {
                View childAt = RecyclerView.this.getChildAt(i);
                if (childAt != null) {
                    RecyclerView.this.l(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i);
            }

            public View b(int i) {
                return RecyclerView.this.getChildAt(i);
            }

            public void b() {
                int a2 = a();
                for (int i = 0; i < a2; i++) {
                    View b = b(i);
                    RecyclerView.this.l(b);
                    b.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            public v b(View view) {
                return RecyclerView.e(view);
            }

            public void a(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
                v e = RecyclerView.e(view);
                if (e != null) {
                    if (e.isTmpDetached() || e.shouldIgnore()) {
                        e.clearTmpDetachFlag();
                    } else {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + e + RecyclerView.this.a());
                    }
                }
                RecyclerView.this.attachViewToParent(view, i, layoutParams);
            }

            public void c(int i) {
                View b = b(i);
                if (b != null) {
                    v e = RecyclerView.e(b);
                    if (e != null) {
                        if (!e.isTmpDetached() || e.shouldIgnore()) {
                            e.addFlags(256);
                        } else {
                            throw new IllegalArgumentException("called detach on an already detached child " + e + RecyclerView.this.a());
                        }
                    }
                }
                RecyclerView.this.detachViewFromParent(i);
            }

            public void c(View view) {
                v e = RecyclerView.e(view);
                if (e != null) {
                    e.onEnteredHiddenState(RecyclerView.this);
                }
            }

            public void d(View view) {
                v e = RecyclerView.e(view);
                if (e != null) {
                    e.onLeftHiddenState(RecyclerView.this);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.f = new c(new a() {
            public v a(int i) {
                v a2 = RecyclerView.this.a(i, true);
                if (a2 != null && !RecyclerView.this.g.c(a2.itemView)) {
                    return a2;
                }
                return null;
            }

            public void a(int i, int i2) {
                RecyclerView.this.a(i, i2, true);
                RecyclerView.this.E = true;
                RecyclerView.this.D.c += i2;
            }

            public void b(int i, int i2) {
                RecyclerView.this.a(i, i2, false);
                RecyclerView.this.E = true;
            }

            public void a(int i, int i2, Object obj) {
                RecyclerView.this.a(i, i2, obj);
                RecyclerView.this.F = true;
            }

            public void a(b bVar) {
                c(bVar);
            }

            /* access modifiers changed from: 0000 */
            public void c(b bVar) {
                switch (bVar.a) {
                    case 1:
                        RecyclerView.this.n.a(RecyclerView.this, bVar.b, bVar.d);
                        return;
                    case 2:
                        RecyclerView.this.n.b(RecyclerView.this, bVar.b, bVar.d);
                        return;
                    case 4:
                        RecyclerView.this.n.a(RecyclerView.this, bVar.b, bVar.d, bVar.c);
                        return;
                    case 8:
                        RecyclerView.this.n.a(RecyclerView.this, bVar.b, bVar.d, 1);
                        return;
                    default:
                        return;
                }
            }

            public void b(b bVar) {
                c(bVar);
            }

            public void c(int i, int i2) {
                RecyclerView.this.g(i, i2);
                RecyclerView.this.E = true;
            }

            public void d(int i, int i2) {
                RecyclerView.this.f(i, i2);
                RecyclerView.this.E = true;
            }
        });
    }

    public void setHasFixedSize(boolean z2) {
        this.r = z2;
    }

    public void setClipToPadding(boolean z2) {
        if (z2 != this.i) {
            k();
        }
        this.i = z2;
        super.setClipToPadding(z2);
        if (this.t) {
            requestLayout();
        }
    }

    public boolean getClipToPadding() {
        return this.i;
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        switch (i2) {
            case 0:
                break;
            case 1:
                this.as = viewConfiguration.getScaledPagingTouchSlop();
                return;
            default:
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
                break;
        }
        this.as = viewConfiguration.getScaledTouchSlop();
    }

    public void setAdapter(a aVar) {
        setLayoutFrozen(false);
        a(aVar, false, true);
        c(false);
        requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.z != null) {
            this.z.d();
        }
        if (this.n != null) {
            this.n.c(this.e);
            this.n.b(this.e);
        }
        this.e.a();
    }

    private void a(a aVar, boolean z2, boolean z3) {
        if (this.m != null) {
            this.m.unregisterAdapterDataObserver(this.R);
            this.m.onDetachedFromRecyclerView(this);
        }
        if (!z2 || z3) {
            c();
        }
        this.f.a();
        a aVar2 = this.m;
        this.m = aVar;
        if (aVar != null) {
            aVar.registerAdapterDataObserver(this.R);
            aVar.onAttachedToRecyclerView(this);
        }
        if (this.n != null) {
            this.n.a(aVar2, this.m);
        }
        this.e.a(aVar2, this.m, z2);
        this.D.f = true;
    }

    public a getAdapter() {
        return this.m;
    }

    public void setRecyclerListener(p pVar) {
        this.o = pVar;
    }

    public int getBaseline() {
        if (this.n != null) {
            return this.n.x();
        }
        return super.getBaseline();
    }

    public void a(j jVar) {
        if (this.ad == null) {
            this.ad = new ArrayList();
        }
        this.ad.add(jVar);
    }

    public void b(j jVar) {
        if (this.ad != null) {
            this.ad.remove(jVar);
        }
    }

    public void setLayoutManager(i iVar) {
        if (iVar != this.n) {
            f();
            if (this.n != null) {
                if (this.z != null) {
                    this.z.d();
                }
                this.n.c(this.e);
                this.n.b(this.e);
                this.e.a();
                if (this.f353q) {
                    this.n.b(this, this.e);
                }
                this.n.b((RecyclerView) null);
                this.n = null;
            } else {
                this.e.a();
            }
            this.g.a();
            this.n = iVar;
            if (iVar != null) {
                if (iVar.f354q != null) {
                    throw new IllegalArgumentException("LayoutManager " + iVar + " is already attached to a RecyclerView:" + iVar.f354q.a());
                }
                this.n.b(this);
                if (this.f353q) {
                    this.n.c(this);
                }
            }
            this.e.b();
            requestLayout();
        }
    }

    public void setOnFlingListener(k kVar) {
        this.at = kVar;
    }

    public k getOnFlingListener() {
        return this.at;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.S != null) {
            savedState.a(this.S);
        } else if (this.n != null) {
            savedState.a = this.n.e();
        } else {
            savedState.a = null;
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.S = (SavedState) parcelable;
        super.onRestoreInstanceState(this.S.getSuperState());
        if (this.n != null && this.S.a != null) {
            this.n.a(this.S.a);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void e(v vVar) {
        View view = vVar.itemView;
        boolean z2 = view.getParent() == this;
        this.e.c(b(view));
        if (vVar.isTmpDetached()) {
            this.g.a(view, -1, view.getLayoutParams(), true);
        } else if (!z2) {
            this.g.a(view, true);
        } else {
            this.g.d(view);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(View view) {
        e();
        boolean f2 = this.g.f(view);
        if (f2) {
            v e2 = e(view);
            this.e.c(e2);
            this.e.b(e2);
        }
        a(!f2);
        return f2;
    }

    public i getLayoutManager() {
        return this.n;
    }

    public n getRecycledViewPool() {
        return this.e.g();
    }

    public void setRecycledViewPool(n nVar) {
        this.e.a(nVar);
    }

    public void setViewCacheExtension(t tVar) {
        this.e.a(tVar);
    }

    public void setItemViewCacheSize(int i2) {
        this.e.a(i2);
    }

    public int getScrollState() {
        return this.al;
    }

    /* access modifiers changed from: 0000 */
    public void setScrollState(int i2) {
        if (i2 != this.al) {
            this.al = i2;
            if (i2 != 2) {
                B();
            }
            h(i2);
        }
    }

    public void a(h hVar, int i2) {
        if (this.n != null) {
            this.n.a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.p.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.p.add(hVar);
        } else {
            this.p.add(i2, hVar);
        }
        r();
        requestLayout();
    }

    public void a(h hVar) {
        a(hVar, -1);
    }

    public int getItemDecorationCount() {
        return this.p.size();
    }

    public void b(h hVar) {
        if (this.n != null) {
            this.n.a("Cannot remove item decoration during a scroll  or layout");
        }
        this.p.remove(hVar);
        if (this.p.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        r();
        requestLayout();
    }

    public void setChildDrawingOrderCallback(d dVar) {
        if (dVar != this.aC) {
            this.aC = dVar;
            setChildrenDrawingOrderEnabled(this.aC != null);
        }
    }

    @Deprecated
    public void setOnScrollListener(m mVar) {
        this.az = mVar;
    }

    public void a(m mVar) {
        if (this.aA == null) {
            this.aA = new ArrayList();
        }
        this.aA.add(mVar);
    }

    public void b(m mVar) {
        if (this.aA != null) {
            this.aA.remove(mVar);
        }
    }

    public void a(int i2) {
        if (!this.v) {
            f();
            if (this.n == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            this.n.e(i2);
            awakenScrollBars();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        if (this.n != null) {
            this.n.e(i2);
            awakenScrollBars();
        }
    }

    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollBy(int i2, int i3) {
        if (this.n == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.v) {
            boolean f2 = this.n.f();
            boolean g2 = this.n.g();
            if (f2 || g2) {
                if (!f2) {
                    i2 = 0;
                }
                if (!g2) {
                    i3 = 0;
                }
                a(i2, i3, (MotionEvent) null);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        e();
        l();
        TraceCompat.beginSection("RV Scroll");
        a(this.D);
        if (i2 != 0) {
            i4 = this.n.a(i2, this.e, this.D);
        } else {
            i4 = 0;
        }
        if (i3 != 0) {
            i5 = this.n.b(i3, this.e, this.D);
        } else {
            i5 = 0;
        }
        TraceCompat.endSection();
        w();
        m();
        a(false);
        if (iArr != null) {
            iArr[0] = i4;
            iArr[1] = i5;
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        if (!this.t || this.x) {
            TraceCompat.beginSection("RV FullInvalidate");
            q();
            TraceCompat.endSection();
        } else if (!this.f.d()) {
        } else {
            if (this.f.a(4) && !this.f.a(11)) {
                TraceCompat.beginSection("RV PartialInvalidate");
                e();
                l();
                this.f.b();
                if (!this.u) {
                    if (A()) {
                        q();
                    } else {
                        this.f.c();
                    }
                }
                a(true);
                m();
                TraceCompat.endSection();
            } else if (this.f.d()) {
                TraceCompat.beginSection("RV FullInvalidate");
                q();
                TraceCompat.endSection();
            }
        }
    }

    private boolean A() {
        int b2 = this.g.b();
        for (int i2 = 0; i2 < b2; i2++) {
            v e2 = e(this.g.b(i2));
            if (e2 != null && !e2.shouldIgnore() && e2.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        d();
        if (this.m != null) {
            a(i2, i3, this.J);
            i5 = this.J[0];
            i4 = this.J[1];
            i7 = i2 - i5;
            i6 = i3 - i4;
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
        }
        if (!this.p.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(i5, i4, i7, i6, this.aF, 0)) {
            this.aq -= this.aF[0];
            this.ar -= this.aF[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float) this.aF[0], (float) this.aF[1]);
            }
            int[] iArr = this.aG;
            iArr[0] = iArr[0] + this.aF[0];
            int[] iArr2 = this.aG;
            iArr2[1] = iArr2[1] + this.aF[1];
        } else if (getOverScrollMode() != 2) {
            if (motionEvent != null && !MotionEventCompat.isFromSource(motionEvent, 8194)) {
                a(motionEvent.getX(), (float) i7, motionEvent.getY(), (float) i6);
            }
            c(i2, i3);
        }
        if (!(i5 == 0 && i4 == 0)) {
            i(i5, i4);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        if (i5 == 0 && i4 == 0) {
            return false;
        }
        return true;
    }

    public int computeHorizontalScrollOffset() {
        if (this.n != null && this.n.f()) {
            return this.n.c(this.D);
        }
        return 0;
    }

    public int computeHorizontalScrollExtent() {
        if (this.n != null && this.n.f()) {
            return this.n.e(this.D);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        if (this.n != null && this.n.f()) {
            return this.n.g(this.D);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        if (this.n != null && this.n.g()) {
            return this.n.d(this.D);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        if (this.n != null && this.n.g()) {
            return this.n.f(this.D);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        if (this.n != null && this.n.g()) {
            return this.n.h(this.D);
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        this.W++;
        if (this.W == 1 && !this.v) {
            this.u = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z2) {
        if (this.W < 1) {
            this.W = 1;
        }
        if (!z2 && !this.v) {
            this.u = false;
        }
        if (this.W == 1) {
            if (z2 && this.u && !this.v && this.n != null && this.m != null) {
                q();
            }
            if (!this.v) {
                this.u = false;
            }
        }
        this.W--;
    }

    public void setLayoutFrozen(boolean z2) {
        if (z2 != this.v) {
            a("Do not setLayoutFrozen in layout or scroll");
            if (!z2) {
                this.v = false;
                if (!(!this.u || this.n == null || this.m == null)) {
                    requestLayout();
                }
                this.u = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.v = true;
            this.aa = true;
            f();
        }
    }

    public void a(int i2, int i3) {
        a(i2, i3, (Interpolator) null);
    }

    public void a(int i2, int i3, Interpolator interpolator) {
        int i4 = 0;
        if (this.n == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.v) {
            if (!this.n.f()) {
                i2 = 0;
            }
            if (this.n.g()) {
                i4 = i3;
            }
            if (i2 != 0 || i4 != 0) {
                this.A.a(i2, i4, interpolator);
            }
        }
    }

    public boolean b(int i2, int i3) {
        boolean z2;
        int i4;
        if (this.n == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.v) {
            return false;
        } else {
            boolean f2 = this.n.f();
            boolean g2 = this.n.g();
            if (!f2 || Math.abs(i2) < this.au) {
                i2 = 0;
            }
            if (!g2 || Math.abs(i3) < this.au) {
                i3 = 0;
            }
            if ((i2 == 0 && i3 == 0) || dispatchNestedPreFling((float) i2, (float) i3)) {
                return false;
            }
            if (f2 || g2) {
                z2 = true;
            } else {
                z2 = false;
            }
            dispatchNestedFling((float) i2, (float) i3, z2);
            if (this.at != null && this.at.a(i2, i3)) {
                return true;
            }
            if (!z2) {
                return false;
            }
            if (f2) {
                i4 = 1;
            } else {
                i4 = 0;
            }
            if (g2) {
                i4 |= 2;
            }
            startNestedScroll(i4, 1);
            this.A.a(Math.max(-this.av, Math.min(i2, this.av)), Math.max(-this.av, Math.min(i3, this.av)));
            return true;
        }
    }

    public void f() {
        setScrollState(0);
        B();
    }

    private void B() {
        this.A.b();
        if (this.n != null) {
            this.n.K();
        }
    }

    public int getMinFlingVelocity() {
        return this.au;
    }

    public int getMaxFlingVelocity() {
        return this.av;
    }

    private void a(float f2, float f3, float f4, float f5) {
        boolean z2 = true;
        boolean z3 = false;
        if (f3 < 0.0f) {
            g();
            EdgeEffectCompat.onPull(this.ah, (-f3) / ((float) getWidth()), 1.0f - (f4 / ((float) getHeight())));
            z3 = true;
        } else if (f3 > 0.0f) {
            h();
            EdgeEffectCompat.onPull(this.aj, f3 / ((float) getWidth()), f4 / ((float) getHeight()));
            z3 = true;
        }
        if (f5 < 0.0f) {
            i();
            EdgeEffectCompat.onPull(this.ai, (-f5) / ((float) getHeight()), f2 / ((float) getWidth()));
        } else if (f5 > 0.0f) {
            j();
            EdgeEffectCompat.onPull(this.ak, f5 / ((float) getHeight()), 1.0f - (f2 / ((float) getWidth())));
        } else {
            z2 = z3;
        }
        if (z2 || f3 != 0.0f || f5 != 0.0f) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void C() {
        boolean z2 = false;
        if (this.ah != null) {
            this.ah.onRelease();
            z2 = this.ah.isFinished();
        }
        if (this.ai != null) {
            this.ai.onRelease();
            z2 |= this.ai.isFinished();
        }
        if (this.aj != null) {
            this.aj.onRelease();
            z2 |= this.aj.isFinished();
        }
        if (this.ak != null) {
            this.ak.onRelease();
            z2 |= this.ak.isFinished();
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2, int i3) {
        boolean z2 = false;
        if (this.ah != null && !this.ah.isFinished() && i2 > 0) {
            this.ah.onRelease();
            z2 = this.ah.isFinished();
        }
        if (this.aj != null && !this.aj.isFinished() && i2 < 0) {
            this.aj.onRelease();
            z2 |= this.aj.isFinished();
        }
        if (this.ai != null && !this.ai.isFinished() && i3 > 0) {
            this.ai.onRelease();
            z2 |= this.ai.isFinished();
        }
        if (this.ak != null && !this.ak.isFinished() && i3 < 0) {
            this.ak.onRelease();
            z2 |= this.ak.isFinished();
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void d(int i2, int i3) {
        if (i2 < 0) {
            g();
            this.ah.onAbsorb(-i2);
        } else if (i2 > 0) {
            h();
            this.aj.onAbsorb(i2);
        }
        if (i3 < 0) {
            i();
            this.ai.onAbsorb(-i3);
        } else if (i3 > 0) {
            j();
            this.ak.onAbsorb(i3);
        }
        if (i2 != 0 || i3 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        if (this.ah == null) {
            this.ah = this.ag.a(this, 0);
            if (this.i) {
                this.ah.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.ah.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void h() {
        if (this.aj == null) {
            this.aj = this.ag.a(this, 2);
            if (this.i) {
                this.aj.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.aj.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        if (this.ai == null) {
            this.ai = this.ag.a(this, 1);
            if (this.i) {
                this.ai.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.ai.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void j() {
        if (this.ak == null) {
            this.ak = this.ag.a(this, 3);
            if (this.i) {
                this.ak.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.ak.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        this.ak = null;
        this.ai = null;
        this.aj = null;
        this.ah = null;
    }

    public void setEdgeEffectFactory(e eVar) {
        Preconditions.checkNotNull(eVar);
        this.ag = eVar;
        k();
    }

    public e getEdgeEffectFactory() {
        return this.ag;
    }

    public View focusSearch(View view, int i2) {
        View view2;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6 = true;
        View d2 = this.n.d(view, i2);
        if (d2 != null) {
            return d2;
        }
        boolean z7 = this.m != null && this.n != null && !o() && !this.v;
        FocusFinder instance = FocusFinder.getInstance();
        if (!z7 || !(i2 == 2 || i2 == 1)) {
            View findNextFocus = instance.findNextFocus(this, view, i2);
            if (findNextFocus != null || !z7) {
                view2 = findNextFocus;
            } else {
                d();
                if (c(view) == null) {
                    return null;
                }
                e();
                view2 = this.n.a(view, i2, this.e, this.D);
                a(false);
            }
        } else {
            if (this.n.g()) {
                int i3 = i2 == 2 ? 130 : 33;
                if (instance.findNextFocus(this, view, i3) == null) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (O) {
                    i2 = i3;
                    z2 = z5;
                } else {
                    z2 = z5;
                }
            } else {
                z2 = false;
            }
            if (z2 || !this.n.f()) {
                z6 = z2;
            } else {
                if (this.n.w() == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (i2 == 2) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                int i4 = z4 ^ z3 ? 66 : 17;
                if (instance.findNextFocus(this, view, i4) != null) {
                    z6 = false;
                }
                if (O) {
                    i2 = i4;
                }
            }
            if (z6) {
                d();
                if (c(view) == null) {
                    return null;
                }
                e();
                this.n.a(view, i2, this.e, this.D);
                a(false);
            }
            view2 = instance.findNextFocus(this, view, i2);
        }
        if (view2 == null || view2.hasFocusable()) {
            if (!a(view, view2, i2)) {
                view2 = super.focusSearch(view, i2);
            }
            return view2;
        } else if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        } else {
            a(view2, (View) null);
            return view;
        }
    }

    private boolean a(View view, View view2, int i2) {
        int i3;
        char c2 = 65535;
        boolean z2 = false;
        if (view2 == null || view2 == this) {
            return false;
        }
        if (c(view2) == null) {
            return false;
        }
        if (view == null || c(view) == null) {
            return true;
        }
        this.k.set(0, 0, view.getWidth(), view.getHeight());
        this.T.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.k);
        offsetDescendantRectToMyCoords(view2, this.T);
        int i4 = this.n.w() == 1 ? -1 : 1;
        if ((this.k.left < this.T.left || this.k.right <= this.T.left) && this.k.right < this.T.right) {
            i3 = 1;
        } else if ((this.k.right > this.T.right || this.k.left >= this.T.right) && this.k.left > this.T.left) {
            i3 = -1;
        } else {
            i3 = 0;
        }
        if ((this.k.top < this.T.top || this.k.bottom <= this.T.top) && this.k.bottom < this.T.bottom) {
            c2 = 1;
        } else if ((this.k.bottom <= this.T.bottom && this.k.top < this.T.bottom) || this.k.top <= this.T.top) {
            c2 = 0;
        }
        switch (i2) {
            case 1:
                if (c2 < 0 || (c2 == 0 && i4 * i3 <= 0)) {
                    z2 = true;
                }
                return z2;
            case 2:
                if (c2 > 0 || (c2 == 0 && i4 * i3 >= 0)) {
                    z2 = true;
                }
                return z2;
            case 17:
                if (i3 >= 0) {
                    return false;
                }
                return true;
            case 33:
                if (c2 >= 0) {
                    return false;
                }
                return true;
            case 66:
                if (i3 <= 0) {
                    return false;
                }
                return true;
            case 130:
                if (c2 <= 0) {
                    return false;
                }
                return true;
            default:
                throw new IllegalArgumentException("Invalid direction: " + i2 + a());
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.n.a(this, this.D, view, view2) && view2 != null) {
            a(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    private void a(View view, View view2) {
        boolean z2 = true;
        View view3 = view2 != null ? view2 : view;
        this.k.set(0, 0, view3.getWidth(), view3.getHeight());
        android.view.ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.e) {
                Rect rect = layoutParams2.d;
                this.k.left -= rect.left;
                this.k.right += rect.right;
                this.k.top -= rect.top;
                Rect rect2 = this.k;
                rect2.bottom = rect.bottom + rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.k);
            offsetRectIntoDescendantCoords(view, this.k);
        }
        i iVar = this.n;
        Rect rect3 = this.k;
        boolean z3 = !this.t;
        if (view2 != null) {
            z2 = false;
        }
        iVar.a(this, view, rect3, z3, z2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.n.a(this, view, rect, z2);
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        if (this.n == null || !this.n.a(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (o()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        if (r0 >= 30.0f) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttachedToWindow() {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            super.onAttachedToWindow()
            r4.ae = r1
            r4.f353q = r0
            boolean r2 = r4.t
            if (r2 == 0) goto L_0x0068
            boolean r2 = r4.isLayoutRequested()
            if (r2 != 0) goto L_0x0068
        L_0x0013:
            r4.t = r0
            android.support.v7.widget.RecyclerView$i r0 = r4.n
            if (r0 == 0) goto L_0x001e
            android.support.v7.widget.RecyclerView$i r0 = r4.n
            r0.c(r4)
        L_0x001e:
            r4.G = r1
            boolean r0 = d
            if (r0 == 0) goto L_0x0067
            java.lang.ThreadLocal<android.support.v7.widget.aa> r0 = android.support.v7.widget.aa.a
            java.lang.Object r0 = r0.get()
            android.support.v7.widget.aa r0 = (android.support.v7.widget.aa) r0
            r4.B = r0
            android.support.v7.widget.aa r0 = r4.B
            if (r0 != 0) goto L_0x0062
            android.support.v7.widget.aa r0 = new android.support.v7.widget.aa
            r0.<init>()
            r4.B = r0
            android.view.Display r0 = android.support.v4.view.ViewCompat.getDisplay(r4)
            r1 = 1114636288(0x42700000, float:60.0)
            boolean r2 = r4.isInEditMode()
            if (r2 != 0) goto L_0x006a
            if (r0 == 0) goto L_0x006a
            float r0 = r0.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x006a
        L_0x0051:
            android.support.v7.widget.aa r1 = r4.B
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r0 = r2 / r0
            long r2 = (long) r0
            r1.d = r2
            java.lang.ThreadLocal<android.support.v7.widget.aa> r0 = android.support.v7.widget.aa.a
            android.support.v7.widget.aa r1 = r4.B
            r0.set(r1)
        L_0x0062:
            android.support.v7.widget.aa r0 = r4.B
            r0.a(r4)
        L_0x0067:
            return
        L_0x0068:
            r0 = r1
            goto L_0x0013
        L_0x006a:
            r0 = r1
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onAttachedToWindow():void");
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.z != null) {
            this.z.d();
        }
        f();
        this.f353q = false;
        if (this.n != null) {
            this.n.b(this, this.e);
        }
        this.K.clear();
        removeCallbacks(this.aH);
        this.h.b();
        if (d && this.B != null) {
            this.B.b(this);
            this.B = null;
        }
    }

    public boolean isAttachedToWindow() {
        return this.f353q;
    }

    /* access modifiers changed from: 0000 */
    public void a(String str) {
        if (o()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + a());
            }
            throw new IllegalStateException(str);
        } else if (this.af > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + a()));
        }
    }

    public void a(l lVar) {
        this.U.add(lVar);
    }

    public void b(l lVar) {
        this.U.remove(lVar);
        if (this.V == lVar) {
            this.V = null;
        }
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.V = null;
        }
        int size = this.U.size();
        int i2 = 0;
        while (i2 < size) {
            l lVar = (l) this.U.get(i2);
            if (!lVar.onInterceptTouchEvent(this, motionEvent) || action == 3) {
                i2++;
            } else {
                this.V = lVar;
                return true;
            }
        }
        return false;
    }

    private boolean b(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.V != null) {
            if (action == 0) {
                this.V = null;
            } else {
                this.V.onTouchEvent(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.V = null;
                }
                return true;
            }
        }
        if (action != 0) {
            int size = this.U.size();
            for (int i2 = 0; i2 < size; i2++) {
                l lVar = (l) this.U.get(i2);
                if (lVar.onInterceptTouchEvent(this, motionEvent)) {
                    this.V = lVar;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        int i2;
        boolean z3;
        if (this.v) {
            return false;
        }
        if (a(motionEvent)) {
            E();
            return true;
        } else if (this.n == null) {
            return false;
        } else {
            boolean f2 = this.n.f();
            boolean g2 = this.n.g();
            if (this.an == null) {
                this.an = VelocityTracker.obtain();
            }
            this.an.addMovement(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            int actionIndex = motionEvent.getActionIndex();
            switch (actionMasked) {
                case 0:
                    if (this.aa) {
                        this.aa = false;
                    }
                    this.am = motionEvent.getPointerId(0);
                    int x2 = (int) (motionEvent.getX() + 0.5f);
                    this.aq = x2;
                    this.ao = x2;
                    int y2 = (int) (motionEvent.getY() + 0.5f);
                    this.ar = y2;
                    this.ap = y2;
                    if (this.al == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                    }
                    int[] iArr = this.aG;
                    this.aG[1] = 0;
                    iArr[0] = 0;
                    if (f2) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    if (g2) {
                        i2 |= 2;
                    }
                    startNestedScroll(i2, 0);
                    break;
                case 1:
                    this.an.clear();
                    stopNestedScroll(0);
                    break;
                case 2:
                    int findPointerIndex = motionEvent.findPointerIndex(this.am);
                    if (findPointerIndex >= 0) {
                        int x3 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
                        int y3 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
                        if (this.al != 1) {
                            int i3 = x3 - this.ao;
                            int i4 = y3 - this.ap;
                            if (!f2 || Math.abs(i3) <= this.as) {
                                z2 = false;
                            } else {
                                this.aq = x3;
                                z2 = true;
                            }
                            if (g2 && Math.abs(i4) > this.as) {
                                this.ar = y3;
                                z2 = true;
                            }
                            if (z2) {
                                setScrollState(1);
                                break;
                            }
                        }
                    } else {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.am + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    break;
                case 3:
                    E();
                    break;
                case 5:
                    this.am = motionEvent.getPointerId(actionIndex);
                    int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.aq = x4;
                    this.ao = x4;
                    int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.ar = y4;
                    this.ap = y4;
                    break;
                case 6:
                    c(motionEvent);
                    break;
            }
            if (this.al == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            return z3;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.U.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((l) this.U.get(i2)).onRequestDisallowInterceptTouchEvent(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f2;
        int i2;
        boolean z2;
        int i3;
        int i4;
        int i5;
        boolean z3 = false;
        if (this.v || this.aa) {
            return false;
        }
        if (b(motionEvent)) {
            E();
            return true;
        } else if (this.n == null) {
            return false;
        } else {
            boolean f3 = this.n.f();
            boolean g2 = this.n.g();
            if (this.an == null) {
                this.an = VelocityTracker.obtain();
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            int actionIndex = motionEvent.getActionIndex();
            if (actionMasked == 0) {
                int[] iArr = this.aG;
                this.aG[1] = 0;
                iArr[0] = 0;
            }
            obtain.offsetLocation((float) this.aG[0], (float) this.aG[1]);
            switch (actionMasked) {
                case 0:
                    this.am = motionEvent.getPointerId(0);
                    int x2 = (int) (motionEvent.getX() + 0.5f);
                    this.aq = x2;
                    this.ao = x2;
                    int y2 = (int) (motionEvent.getY() + 0.5f);
                    this.ar = y2;
                    this.ap = y2;
                    if (f3) {
                        i5 = 1;
                    } else {
                        i5 = 0;
                    }
                    if (g2) {
                        i5 |= 2;
                    }
                    startNestedScroll(i5, 0);
                    break;
                case 1:
                    this.an.addMovement(obtain);
                    this.an.computeCurrentVelocity(1000, (float) this.av);
                    float f4 = f3 ? -this.an.getXVelocity(this.am) : 0.0f;
                    if (g2) {
                        f2 = -this.an.getYVelocity(this.am);
                    } else {
                        f2 = 0.0f;
                    }
                    if ((f4 == 0.0f && f2 == 0.0f) || !b((int) f4, (int) f2)) {
                        setScrollState(0);
                    }
                    D();
                    z3 = true;
                    break;
                case 2:
                    int findPointerIndex = motionEvent.findPointerIndex(this.am);
                    if (findPointerIndex >= 0) {
                        int x3 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
                        int y3 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
                        int i6 = this.aq - x3;
                        int i7 = this.ar - y3;
                        if (dispatchNestedPreScroll(i6, i7, this.I, this.aF, 0)) {
                            i6 -= this.I[0];
                            i7 -= this.I[1];
                            obtain.offsetLocation((float) this.aF[0], (float) this.aF[1]);
                            int[] iArr2 = this.aG;
                            iArr2[0] = iArr2[0] + this.aF[0];
                            int[] iArr3 = this.aG;
                            iArr3[1] = iArr3[1] + this.aF[1];
                        }
                        if (this.al != 1) {
                            if (!f3 || Math.abs(i6) <= this.as) {
                                z2 = false;
                            } else {
                                if (i6 > 0) {
                                    i4 = i6 - this.as;
                                } else {
                                    i4 = this.as + i6;
                                }
                                i6 = i4;
                                z2 = true;
                            }
                            if (g2 && Math.abs(i7) > this.as) {
                                if (i7 > 0) {
                                    i3 = i7 - this.as;
                                } else {
                                    i3 = this.as + i7;
                                }
                                i7 = i3;
                                z2 = true;
                            }
                            if (z2) {
                                setScrollState(1);
                            }
                        }
                        if (this.al == 1) {
                            this.aq = x3 - this.aF[0];
                            this.ar = y3 - this.aF[1];
                            int i8 = f3 ? i6 : 0;
                            if (g2) {
                                i2 = i7;
                            } else {
                                i2 = 0;
                            }
                            if (a(i8, i2, obtain)) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            if (!(this.B == null || (i6 == 0 && i7 == 0))) {
                                this.B.a(this, i6, i7);
                                break;
                            }
                        }
                    } else {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.am + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    break;
                case 3:
                    E();
                    break;
                case 5:
                    this.am = motionEvent.getPointerId(actionIndex);
                    int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.aq = x4;
                    this.ao = x4;
                    int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.ar = y4;
                    this.ap = y4;
                    break;
                case 6:
                    c(motionEvent);
                    break;
            }
            if (!z3) {
                this.an.addMovement(obtain);
            }
            obtain.recycle();
            return true;
        }
    }

    private void D() {
        if (this.an != null) {
            this.an.clear();
        }
        stopNestedScroll(0);
        C();
    }

    private void E() {
        D();
        setScrollState(0);
    }

    private void c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.am) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.am = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.aq = x2;
            this.ao = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.ar = y2;
            this.ap = y2;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f2;
        float f3;
        float f4;
        if (this.n != null && !this.v && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                if (this.n.g()) {
                    f4 = -motionEvent.getAxisValue(9);
                } else {
                    f4 = 0.0f;
                }
                if (this.n.f()) {
                    f3 = f4;
                    f2 = motionEvent.getAxisValue(10);
                } else {
                    f3 = f4;
                    f2 = 0.0f;
                }
            } else if ((motionEvent.getSource() & 4194304) != 0) {
                f2 = motionEvent.getAxisValue(26);
                if (this.n.g()) {
                    f3 = -f2;
                    f2 = 0.0f;
                } else if (this.n.f()) {
                    f3 = 0.0f;
                } else {
                    f2 = 0.0f;
                    f3 = 0.0f;
                }
            } else {
                f2 = 0.0f;
                f3 = 0.0f;
            }
            if (!(f3 == 0.0f && f2 == 0.0f)) {
                a((int) (f2 * this.aw), (int) (this.ax * f3), motionEvent);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        boolean z2 = false;
        if (this.n == null) {
            e(i2, i3);
        } else if (this.n.d()) {
            int mode = MeasureSpec.getMode(i2);
            int mode2 = MeasureSpec.getMode(i3);
            this.n.a(this.e, this.D, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (!z2 && this.m != null) {
                if (this.D.d == 1) {
                    M();
                }
                this.n.d(i2, i3);
                this.D.i = true;
                N();
                this.n.e(i2, i3);
                if (this.n.m()) {
                    this.n.d(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.D.i = true;
                    N();
                    this.n.e(i2, i3);
                }
            }
        } else if (this.r) {
            this.n.a(this.e, this.D, i2, i3);
        } else {
            if (this.w) {
                e();
                l();
                H();
                m();
                if (this.D.k) {
                    this.D.g = true;
                } else {
                    this.f.e();
                    this.D.g = false;
                }
                this.w = false;
                a(false);
            } else if (this.D.k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            if (this.m != null) {
                this.D.e = this.m.getItemCount();
            } else {
                this.D.e = 0;
            }
            e();
            this.n.a(this.e, this.D, i2, i3);
            a(false);
            this.D.g = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void e(int i2, int i3) {
        setMeasuredDimension(i.a(i2, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), i.a(i3, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4 || i3 != i5) {
            k();
        }
    }

    public void setItemAnimator(f fVar) {
        if (this.z != null) {
            this.z.d();
            this.z.a((b) null);
        }
        this.z = fVar;
        if (this.z != null) {
            this.z.a(this.aB);
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        this.ae++;
    }

    /* access modifiers changed from: 0000 */
    public void m() {
        b(true);
    }

    /* access modifiers changed from: 0000 */
    public void b(boolean z2) {
        this.ae--;
        if (this.ae < 1) {
            this.ae = 0;
            if (z2) {
                F();
                x();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean n() {
        return this.ac != null && this.ac.isEnabled();
    }

    private void F() {
        int i2 = this.ab;
        this.ab = 0;
        if (i2 != 0 && n()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, i2);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    public boolean o() {
        return this.ae > 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(AccessibilityEvent accessibilityEvent) {
        int i2;
        int i3 = 0;
        if (!o()) {
            return false;
        }
        if (accessibilityEvent != null) {
            i2 = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            i3 = i2;
        }
        this.ab = i3 | this.ab;
        return true;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!a(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public f getItemAnimator() {
        return this.z;
    }

    /* access modifiers changed from: 0000 */
    public void p() {
        if (!this.G && this.f353q) {
            ViewCompat.postOnAnimation(this, this.aH);
            this.G = true;
        }
    }

    private boolean G() {
        return this.z != null && this.n.c();
    }

    private void H() {
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (this.x) {
            this.f.a();
            if (this.y) {
                this.n.a(this);
            }
        }
        if (G()) {
            this.f.b();
        } else {
            this.f.e();
        }
        if (this.E || this.F) {
            z2 = true;
        } else {
            z2 = false;
        }
        s sVar = this.D;
        if (!this.t || this.z == null || ((!this.x && !z2 && !this.n.u) || (this.x && !this.m.hasStableIds()))) {
            z3 = false;
        } else {
            z3 = true;
        }
        sVar.j = z3;
        s sVar2 = this.D;
        if (!this.D.j || !z2 || this.x || !G()) {
            z4 = false;
        }
        sVar2.k = z4;
    }

    /* access modifiers changed from: 0000 */
    public void q() {
        if (this.m == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        } else if (this.n == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {
            this.D.i = false;
            if (this.D.d == 1) {
                M();
                this.n.f(this);
                N();
            } else if (!this.f.f() && this.n.B() == getWidth() && this.n.C() == getHeight()) {
                this.n.f(this);
            } else {
                this.n.f(this);
                N();
            }
            O();
        }
    }

    private void I() {
        View view;
        int adapterPosition;
        if (!this.ay || !hasFocus() || this.m == null) {
            view = null;
        } else {
            view = getFocusedChild();
        }
        v d2 = view == null ? null : d(view);
        if (d2 == null) {
            J();
            return;
        }
        this.D.m = this.m.hasStableIds() ? d2.getItemId() : -1;
        s sVar = this.D;
        if (this.x) {
            adapterPosition = -1;
        } else if (d2.isRemoved()) {
            adapterPosition = d2.mOldPosition;
        } else {
            adapterPosition = d2.getAdapterPosition();
        }
        sVar.l = adapterPosition;
        this.D.n = n(d2.itemView);
    }

    private void J() {
        this.D.m = -1;
        this.D.l = -1;
        this.D.n = -1;
    }

    private View K() {
        int i2 = this.D.l != -1 ? this.D.l : 0;
        int e2 = this.D.e();
        int i3 = i2;
        while (i3 < e2) {
            v d2 = d(i3);
            if (d2 == null) {
                break;
            } else if (d2.itemView.hasFocusable()) {
                return d2.itemView;
            } else {
                i3++;
            }
        }
        for (int min = Math.min(e2, i2) - 1; min >= 0; min--) {
            v d3 = d(min);
            if (d3 == null) {
                return null;
            }
            if (d3.itemView.hasFocusable()) {
                return d3.itemView;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a7, code lost:
        if (r0.isFocusable() != false) goto L_0x00a9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void L() {
        /*
            r6 = this;
            r4 = -1
            r1 = 0
            boolean r0 = r6.ay
            if (r0 == 0) goto L_0x0027
            android.support.v7.widget.RecyclerView$a r0 = r6.m
            if (r0 == 0) goto L_0x0027
            boolean r0 = r6.hasFocus()
            if (r0 == 0) goto L_0x0027
            int r0 = r6.getDescendantFocusability()
            r2 = 393216(0x60000, float:5.51013E-40)
            if (r0 == r2) goto L_0x0027
            int r0 = r6.getDescendantFocusability()
            r2 = 131072(0x20000, float:1.83671E-40)
            if (r0 != r2) goto L_0x0028
            boolean r0 = r6.isFocused()
            if (r0 == 0) goto L_0x0028
        L_0x0027:
            return
        L_0x0028:
            boolean r0 = r6.isFocused()
            if (r0 != 0) goto L_0x0056
            android.view.View r0 = r6.getFocusedChild()
            boolean r2 = P
            if (r2 == 0) goto L_0x004e
            android.view.ViewParent r2 = r0.getParent()
            if (r2 == 0) goto L_0x0042
            boolean r2 = r0.hasFocus()
            if (r2 != 0) goto L_0x004e
        L_0x0042:
            android.support.v7.widget.r r0 = r6.g
            int r0 = r0.b()
            if (r0 != 0) goto L_0x0056
            r6.requestFocus()
            goto L_0x0027
        L_0x004e:
            android.support.v7.widget.r r2 = r6.g
            boolean r0 = r2.c(r0)
            if (r0 == 0) goto L_0x0027
        L_0x0056:
            android.support.v7.widget.RecyclerView$s r0 = r6.D
            long r2 = r0.m
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00b3
            android.support.v7.widget.RecyclerView$a r0 = r6.m
            boolean r0 = r0.hasStableIds()
            if (r0 == 0) goto L_0x00b3
            android.support.v7.widget.RecyclerView$s r0 = r6.D
            long r2 = r0.m
            android.support.v7.widget.RecyclerView$v r0 = r6.a(r2)
        L_0x006e:
            if (r0 == 0) goto L_0x0082
            android.support.v7.widget.r r2 = r6.g
            android.view.View r3 = r0.itemView
            boolean r2 = r2.c(r3)
            if (r2 != 0) goto L_0x0082
            android.view.View r2 = r0.itemView
            boolean r2 = r2.hasFocusable()
            if (r2 != 0) goto L_0x00ae
        L_0x0082:
            android.support.v7.widget.r r0 = r6.g
            int r0 = r0.b()
            if (r0 <= 0) goto L_0x008e
            android.view.View r1 = r6.K()
        L_0x008e:
            if (r1 == 0) goto L_0x0027
            android.support.v7.widget.RecyclerView$s r0 = r6.D
            int r0 = r0.n
            long r2 = (long) r0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00b1
            android.support.v7.widget.RecyclerView$s r0 = r6.D
            int r0 = r0.n
            android.view.View r0 = r1.findViewById(r0)
            if (r0 == 0) goto L_0x00b1
            boolean r2 = r0.isFocusable()
            if (r2 == 0) goto L_0x00b1
        L_0x00a9:
            r0.requestFocus()
            goto L_0x0027
        L_0x00ae:
            android.view.View r1 = r0.itemView
            goto L_0x008e
        L_0x00b1:
            r0 = r1
            goto L_0x00a9
        L_0x00b3:
            r0 = r1
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.L():void");
    }

    private int n(View view) {
        int i2;
        int id = view.getId();
        while (true) {
            i2 = id;
            View view2 = view;
            if (view2.isFocused() || !(view2 instanceof ViewGroup) || !view2.hasFocus()) {
                return i2;
            }
            view = ((ViewGroup) view2).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            } else {
                id = i2;
            }
        }
        return i2;
    }

    /* access modifiers changed from: 0000 */
    public final void a(s sVar) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.A.a;
            sVar.o = overScroller.getFinalX() - overScroller.getCurrX();
            sVar.p = overScroller.getFinalY() - overScroller.getCurrY();
            return;
        }
        sVar.o = 0;
        sVar.p = 0;
    }

    private void M() {
        boolean z2 = true;
        this.D.a(1);
        a(this.D);
        this.D.i = false;
        e();
        this.h.a();
        l();
        H();
        I();
        s sVar = this.D;
        if (!this.D.j || !this.F) {
            z2 = false;
        }
        sVar.h = z2;
        this.F = false;
        this.E = false;
        this.D.g = this.D.k;
        this.D.e = this.m.getItemCount();
        a(this.aD);
        if (this.D.j) {
            int b2 = this.g.b();
            for (int i2 = 0; i2 < b2; i2++) {
                v e2 = e(this.g.b(i2));
                if (!e2.shouldIgnore() && (!e2.isInvalid() || this.m.hasStableIds())) {
                    this.h.a(e2, this.z.a(this.D, e2, f.e(e2), e2.getUnmodifiedPayloads()));
                    if (this.D.h && e2.isUpdated() && !e2.isRemoved() && !e2.shouldIgnore() && !e2.isInvalid()) {
                        this.h.a(a(e2), e2);
                    }
                }
            }
        }
        if (this.D.k) {
            s();
            boolean z3 = this.D.f;
            this.D.f = false;
            this.n.c(this.e, this.D);
            this.D.f = z3;
            for (int i3 = 0; i3 < this.g.b(); i3++) {
                v e3 = e(this.g.b(i3));
                if (!e3.shouldIgnore() && !this.h.d(e3)) {
                    int e4 = f.e(e3);
                    boolean hasAnyOfTheFlags = e3.hasAnyOfTheFlags(8192);
                    if (!hasAnyOfTheFlags) {
                        e4 |= 4096;
                    }
                    c a2 = this.z.a(this.D, e3, e4, e3.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        a(e3, a2);
                    } else {
                        this.h.b(e3, a2);
                    }
                }
            }
            t();
        } else {
            t();
        }
        m();
        a(false);
        this.D.d = 2;
    }

    private void N() {
        boolean z2;
        e();
        l();
        this.D.a(6);
        this.f.e();
        this.D.e = this.m.getItemCount();
        this.D.c = 0;
        this.D.g = false;
        this.n.c(this.e, this.D);
        this.D.f = false;
        this.S = null;
        s sVar = this.D;
        if (!this.D.j || this.z == null) {
            z2 = false;
        } else {
            z2 = true;
        }
        sVar.j = z2;
        this.D.d = 4;
        m();
        a(false);
    }

    private void O() {
        this.D.a(4);
        e();
        l();
        this.D.d = 1;
        if (this.D.j) {
            for (int b2 = this.g.b() - 1; b2 >= 0; b2--) {
                v e2 = e(this.g.b(b2));
                if (!e2.shouldIgnore()) {
                    long a2 = a(e2);
                    c a3 = this.z.a(this.D, e2);
                    v a4 = this.h.a(a2);
                    if (a4 == null || a4.shouldIgnore()) {
                        this.h.c(e2, a3);
                    } else {
                        boolean a5 = this.h.a(a4);
                        boolean a6 = this.h.a(e2);
                        if (!a5 || a4 != e2) {
                            c b3 = this.h.b(a4);
                            this.h.c(e2, a3);
                            c c2 = this.h.c(e2);
                            if (b3 == null) {
                                a(a2, e2, a4);
                            } else {
                                a(a4, e2, b3, c2, a5, a6);
                            }
                        } else {
                            this.h.c(e2, a3);
                        }
                    }
                }
            }
            this.h.a(this.aI);
        }
        this.n.b(this.e);
        this.D.b = this.D.e;
        this.x = false;
        this.y = false;
        this.D.j = false;
        this.D.k = false;
        this.n.u = false;
        if (this.e.b != null) {
            this.e.b.clear();
        }
        if (this.n.y) {
            this.n.x = 0;
            this.n.y = false;
            this.e.b();
        }
        this.n.a(this.D);
        m();
        a(false);
        this.h.a();
        if (j(this.aD[0], this.aD[1])) {
            i(0, 0);
        }
        L();
        J();
    }

    private void a(long j2, v vVar, v vVar2) {
        int b2 = this.g.b();
        int i2 = 0;
        while (i2 < b2) {
            v e2 = e(this.g.b(i2));
            if (e2 == vVar || a(e2) != j2) {
                i2++;
            } else if (this.m == null || !this.m.hasStableIds()) {
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + e2 + " \n View Holder 2:" + vVar + a());
            } else {
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + e2 + " \n View Holder 2:" + vVar + a());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + vVar2 + " cannot be found but it is necessary for " + vVar + a());
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar, c cVar) {
        vVar.setFlags(0, 8192);
        if (this.D.h && vVar.isUpdated() && !vVar.isRemoved() && !vVar.shouldIgnore()) {
            this.h.a(a(vVar), vVar);
        }
        this.h.a(vVar, cVar);
    }

    private void a(int[] iArr) {
        int b2 = this.g.b();
        if (b2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = ExploreByTouchHelper.INVALID_ID;
        int i4 = 0;
        while (i4 < b2) {
            v e2 = e(this.g.b(i4));
            if (!e2.shouldIgnore()) {
                int layoutPosition = e2.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i3) {
                    i3 = layoutPosition;
                }
            }
            i4++;
            i2 = i2;
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    private boolean j(int i2, int i3) {
        a(this.aD);
        if (this.aD[0] == i2 && this.aD[1] == i3) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View view, boolean z2) {
        v e2 = e(view);
        if (e2 != null) {
            if (e2.isTmpDetached()) {
                e2.clearTmpDetachFlag();
            } else if (!e2.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + e2 + a());
            }
        }
        view.clearAnimation();
        l(view);
        super.removeDetachedView(view, z2);
    }

    /* access modifiers changed from: 0000 */
    public long a(v vVar) {
        return this.m.hasStableIds() ? vVar.getItemId() : (long) vVar.mPosition;
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar, c cVar, c cVar2) {
        vVar.setIsRecyclable(false);
        if (this.z.b(vVar, cVar, cVar2)) {
            p();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(v vVar, c cVar, c cVar2) {
        e(vVar);
        vVar.setIsRecyclable(false);
        if (this.z.a(vVar, cVar, cVar2)) {
            p();
        }
    }

    private void a(v vVar, v vVar2, c cVar, c cVar2, boolean z2, boolean z3) {
        vVar.setIsRecyclable(false);
        if (z2) {
            e(vVar);
        }
        if (vVar != vVar2) {
            if (z3) {
                e(vVar2);
            }
            vVar.mShadowedHolder = vVar2;
            e(vVar);
            this.e.c(vVar);
            vVar2.setIsRecyclable(false);
            vVar2.mShadowingHolder = vVar;
        }
        if (this.z.a(vVar, vVar2, cVar, cVar2)) {
            p();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        TraceCompat.beginSection("RV OnLayout");
        q();
        TraceCompat.endSection();
        this.t = true;
    }

    public void requestLayout() {
        if (this.W != 0 || this.v) {
            this.u = true;
        } else {
            super.requestLayout();
        }
    }

    /* access modifiers changed from: 0000 */
    public void r() {
        int c2 = this.g.c();
        for (int i2 = 0; i2 < c2; i2++) {
            ((LayoutParams) this.g.d(i2).getLayoutParams()).e = true;
        }
        this.e.j();
    }

    public void draw(Canvas canvas) {
        boolean z2;
        int i2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        boolean z6 = false;
        super.draw(canvas);
        int size = this.p.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((h) this.p.get(i3)).a(canvas, this, this.D);
        }
        if (this.ah == null || this.ah.isFinished()) {
            z2 = false;
        } else {
            int save = canvas.save();
            int i4 = this.i ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) (i4 + (-getHeight())), 0.0f);
            if (this.ah == null || !this.ah.draw(canvas)) {
                z2 = false;
            } else {
                z2 = true;
            }
            canvas.restoreToCount(save);
        }
        if (this.ai != null && !this.ai.isFinished()) {
            int save2 = canvas.save();
            if (this.i) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            if (this.ai == null || !this.ai.draw(canvas)) {
                z4 = false;
            } else {
                z4 = true;
            }
            z2 |= z4;
            canvas.restoreToCount(save2);
        }
        if (this.aj != null && !this.aj.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            if (this.i) {
                i2 = getPaddingTop();
            } else {
                i2 = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float) (-i2), (float) (-width));
            if (this.aj == null || !this.aj.draw(canvas)) {
                z3 = false;
            } else {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(save3);
        }
        if (this.ak != null && !this.ak.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.i) {
                canvas.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            if (this.ak != null && this.ak.draw(canvas)) {
                z6 = true;
            }
            z2 |= z6;
            canvas.restoreToCount(save4);
        }
        if (z2 || this.z == null || this.p.size() <= 0 || !this.z.b()) {
            z5 = z2;
        }
        if (z5) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((h) this.p.get(i2)).b(canvas, this, this.D);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.n.a((LayoutParams) layoutParams);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.n != null) {
            return this.n.a();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        if (this.n != null) {
            return this.n.a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (this.n != null) {
            return this.n.a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    /* access modifiers changed from: 0000 */
    public void s() {
        int c2 = this.g.c();
        for (int i2 = 0; i2 < c2; i2++) {
            v e2 = e(this.g.d(i2));
            if (!e2.shouldIgnore()) {
                e2.saveOldPosition();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void t() {
        int c2 = this.g.c();
        for (int i2 = 0; i2 < c2; i2++) {
            v e2 = e(this.g.d(i2));
            if (!e2.shouldIgnore()) {
                e2.clearOldPosition();
            }
        }
        this.e.i();
    }

    /* access modifiers changed from: 0000 */
    public void f(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int c2 = this.g.c();
        if (i2 < i3) {
            i4 = -1;
            i5 = i3;
            i6 = i2;
        } else {
            i4 = 1;
            i5 = i2;
            i6 = i3;
        }
        for (int i7 = 0; i7 < c2; i7++) {
            v e2 = e(this.g.d(i7));
            if (e2 != null && e2.mPosition >= i6 && e2.mPosition <= i5) {
                if (e2.mPosition == i2) {
                    e2.offsetPosition(i3 - i2, false);
                } else {
                    e2.offsetPosition(i4, false);
                }
                this.D.f = true;
            }
        }
        this.e.a(i2, i3);
        requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public void g(int i2, int i3) {
        int c2 = this.g.c();
        for (int i4 = 0; i4 < c2; i4++) {
            v e2 = e(this.g.d(i4));
            if (e2 != null && !e2.shouldIgnore() && e2.mPosition >= i2) {
                e2.offsetPosition(i3, false);
                this.D.f = true;
            }
        }
        this.e.b(i2, i3);
        requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int c2 = this.g.c();
        for (int i5 = 0; i5 < c2; i5++) {
            v e2 = e(this.g.d(i5));
            if (e2 != null && !e2.shouldIgnore()) {
                if (e2.mPosition >= i4) {
                    e2.offsetPosition(-i3, z2);
                    this.D.f = true;
                } else if (e2.mPosition >= i2) {
                    e2.flagRemovedAndOffsetPosition(i2 - 1, -i3, z2);
                    this.D.f = true;
                }
            }
        }
        this.e.a(i2, i3, z2);
        requestLayout();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, Object obj) {
        int c2 = this.g.c();
        int i4 = i2 + i3;
        for (int i5 = 0; i5 < c2; i5++) {
            View d2 = this.g.d(i5);
            v e2 = e(d2);
            if (e2 != null && !e2.shouldIgnore() && e2.mPosition >= i2 && e2.mPosition < i4) {
                e2.addFlags(2);
                e2.addChangePayload(obj);
                ((LayoutParams) d2.getLayoutParams()).e = true;
            }
        }
        this.e.c(i2, i3);
    }

    /* access modifiers changed from: 0000 */
    public boolean b(v vVar) {
        return this.z == null || this.z.a(vVar, vVar.getUnmodifiedPayloads());
    }

    /* access modifiers changed from: 0000 */
    public void c(boolean z2) {
        this.y |= z2;
        this.x = true;
        u();
    }

    /* access modifiers changed from: 0000 */
    public void u() {
        int c2 = this.g.c();
        for (int i2 = 0; i2 < c2; i2++) {
            v e2 = e(this.g.d(i2));
            if (e2 != null && !e2.shouldIgnore()) {
                e2.addFlags(6);
            }
        }
        r();
        this.e.h();
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.ay;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.ay = z2;
    }

    public v b(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return e(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public View c(View view) {
        ViewParent parent = view.getParent();
        View view2 = view;
        while (parent != null && parent != this && (parent instanceof View)) {
            View view3 = (View) parent;
            view2 = view3;
            parent = view3.getParent();
        }
        if (parent == this) {
            return view2;
        }
        return null;
    }

    public v d(View view) {
        View c2 = c(view);
        if (c2 == null) {
            return null;
        }
        return b(c2);
    }

    static v e(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).c;
    }

    public int f(View view) {
        v e2 = e(view);
        if (e2 != null) {
            return e2.getAdapterPosition();
        }
        return -1;
    }

    public int g(View view) {
        v e2 = e(view);
        if (e2 != null) {
            return e2.getLayoutPosition();
        }
        return -1;
    }

    public v c(int i2) {
        return a(i2, false);
    }

    public v d(int i2) {
        if (this.x) {
            return null;
        }
        int c2 = this.g.c();
        int i3 = 0;
        v vVar = null;
        while (i3 < c2) {
            v e2 = e(this.g.d(i3));
            if (e2 == null || e2.isRemoved() || d(e2) != i2) {
                e2 = vVar;
            } else if (!this.g.c(e2.itemView)) {
                return e2;
            }
            i3++;
            vVar = e2;
        }
        return vVar;
    }

    /* access modifiers changed from: 0000 */
    public v a(int i2, boolean z2) {
        int c2 = this.g.c();
        v vVar = null;
        for (int i3 = 0; i3 < c2; i3++) {
            v e2 = e(this.g.d(i3));
            if (e2 != null && !e2.isRemoved()) {
                if (z2) {
                    if (e2.mPosition != i2) {
                        continue;
                    }
                } else if (e2.getLayoutPosition() != i2) {
                    continue;
                }
                if (!this.g.c(e2.itemView)) {
                    return e2;
                }
                vVar = e2;
            }
        }
        return vVar;
    }

    public v a(long j2) {
        if (this.m == null || !this.m.hasStableIds()) {
            return null;
        }
        int c2 = this.g.c();
        int i2 = 0;
        v vVar = null;
        while (i2 < c2) {
            v e2 = e(this.g.d(i2));
            if (e2 == null || e2.isRemoved() || e2.getItemId() != j2) {
                e2 = vVar;
            } else if (!this.g.c(e2.itemView)) {
                return e2;
            }
            i2++;
            vVar = e2;
        }
        return vVar;
    }

    public View a(float f2, float f3) {
        for (int b2 = this.g.b() - 1; b2 >= 0; b2--) {
            View b3 = this.g.b(b2);
            float translationX = b3.getTranslationX();
            float translationY = b3.getTranslationY();
            if (f2 >= ((float) b3.getLeft()) + translationX && f2 <= translationX + ((float) b3.getRight()) && f3 >= ((float) b3.getTop()) + translationY && f3 <= ((float) b3.getBottom()) + translationY) {
                return b3;
            }
        }
        return null;
    }

    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public void e(int i2) {
        int b2 = this.g.b();
        for (int i3 = 0; i3 < b2; i3++) {
            this.g.b(i3).offsetTopAndBottom(i2);
        }
    }

    public void h(View view) {
    }

    public void i(View view) {
    }

    public void f(int i2) {
        int b2 = this.g.b();
        for (int i3 = 0; i3 < b2; i3++) {
            this.g.b(i3).offsetLeftAndRight(i2);
        }
    }

    static void a(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.d;
        rect.set((view.getLeft() - rect2.left) - layoutParams.leftMargin, (view.getTop() - rect2.top) - layoutParams.topMargin, view.getRight() + rect2.right + layoutParams.rightMargin, layoutParams.bottomMargin + rect2.bottom + view.getBottom());
    }

    /* access modifiers changed from: 0000 */
    public Rect j(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.e) {
            return layoutParams.d;
        }
        if (this.D.a() && (layoutParams.e() || layoutParams.c())) {
            return layoutParams.d;
        }
        Rect rect = layoutParams.d;
        rect.set(0, 0, 0, 0);
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.k.set(0, 0, 0, 0);
            ((h) this.p.get(i2)).a(this.k, view, this, this.D);
            rect.left += this.k.left;
            rect.top += this.k.top;
            rect.right += this.k.right;
            rect.bottom += this.k.bottom;
        }
        layoutParams.e = false;
        return rect;
    }

    public void h(int i2, int i3) {
    }

    /* access modifiers changed from: 0000 */
    public void i(int i2, int i3) {
        this.af++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        h(i2, i3);
        if (this.az != null) {
            this.az.a(this, i2, i3);
        }
        if (this.aA != null) {
            for (int size = this.aA.size() - 1; size >= 0; size--) {
                ((m) this.aA.get(size)).a(this, i2, i3);
            }
        }
        this.af--;
    }

    public void g(int i2) {
    }

    /* access modifiers changed from: 0000 */
    public void h(int i2) {
        if (this.n != null) {
            this.n.l(i2);
        }
        g(i2);
        if (this.az != null) {
            this.az.a(this, i2);
        }
        if (this.aA != null) {
            for (int size = this.aA.size() - 1; size >= 0; size--) {
                ((m) this.aA.get(size)).a(this, i2);
            }
        }
    }

    public boolean v() {
        return !this.t || this.x || this.f.d();
    }

    /* access modifiers changed from: 0000 */
    public void w() {
        int b2 = this.g.b();
        for (int i2 = 0; i2 < b2; i2++) {
            View b3 = this.g.b(i2);
            v b4 = b(b3);
            if (!(b4 == null || b4.mShadowingHolder == null)) {
                View view = b4.mShadowingHolder.itemView;
                int left = b3.getLeft();
                int top = b3.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    static RecyclerView k(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView k2 = k(viewGroup.getChildAt(i2));
            if (k2 != null) {
                return k2;
            }
        }
        return null;
    }

    static void c(v vVar) {
        if (vVar.mNestedRecyclerView != null) {
            View view = (View) vVar.mNestedRecyclerView.get();
            while (view != null) {
                if (view != vVar.itemView) {
                    ViewParent parent = view.getParent();
                    view = parent instanceof View ? (View) parent : null;
                } else {
                    return;
                }
            }
            vVar.mNestedRecyclerView = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public long getNanoTime() {
        if (d) {
            return System.nanoTime();
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public void l(View view) {
        v e2 = e(view);
        i(view);
        if (!(this.m == null || e2 == null)) {
            this.m.onViewDetachedFromWindow(e2);
        }
        if (this.ad != null) {
            for (int size = this.ad.size() - 1; size >= 0; size--) {
                ((j) this.ad.get(size)).b(view);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void m(View view) {
        v e2 = e(view);
        h(view);
        if (!(this.m == null || e2 == null)) {
            this.m.onViewAttachedToWindow(e2);
        }
        if (this.ad != null) {
            for (int size = this.ad.size() - 1; size >= 0; size--) {
                ((j) this.ad.get(size)).a(view);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(v vVar, int i2) {
        if (o()) {
            vVar.mPendingAccessibilityState = i2;
            this.K.add(vVar);
            return false;
        }
        ViewCompat.setImportantForAccessibility(vVar.itemView, i2);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void x() {
        for (int size = this.K.size() - 1; size >= 0; size--) {
            v vVar = (v) this.K.get(size);
            if (vVar.itemView.getParent() == this && !vVar.shouldIgnore()) {
                int i2 = vVar.mPendingAccessibilityState;
                if (i2 != -1) {
                    ViewCompat.setImportantForAccessibility(vVar.itemView, i2);
                    vVar.mPendingAccessibilityState = -1;
                }
            }
        }
        this.K.clear();
    }

    /* access modifiers changed from: 0000 */
    public int d(v vVar) {
        if (vVar.hasAnyOfTheFlags(524) || !vVar.isBound()) {
            return -1;
        }
        return this.f.c(vVar.mPosition);
    }

    /* access modifiers changed from: 0000 */
    public void a(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + a());
        }
        Resources resources = getContext().getResources();
        new x(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }

    public void setNestedScrollingEnabled(boolean z2) {
        getScrollingChildHelper().setNestedScrollingEnabled(z2);
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().startNestedScroll(i2);
    }

    public boolean startNestedScroll(int i2, int i3) {
        return getScrollingChildHelper().startNestedScroll(i2, i3);
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopNestedScroll(int i2) {
        getScrollingChildHelper().stopNestedScroll(i2);
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int i2) {
        return getScrollingChildHelper().hasNestedScrollingParent(i2);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return getScrollingChildHelper().dispatchNestedFling(f2, f3, z2);
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().dispatchNestedPreFling(f2, f3);
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.aC == null) {
            return super.getChildDrawingOrder(i2, i3);
        }
        return this.aC.a(i2, i3);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.aE == null) {
            this.aE = new NestedScrollingChildHelper(this);
        }
        return this.aE;
    }
}
