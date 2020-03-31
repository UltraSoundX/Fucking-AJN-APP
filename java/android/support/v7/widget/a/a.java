package android.support.v7.widget.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.f;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.j;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.s;
import android.support.v7.widget.RecyclerView.v;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ItemTouchHelper */
public class a extends h implements j {
    private b A;
    private final l B = new l() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            a.this.p.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                a.this.g = motionEvent.getPointerId(0);
                a.this.c = motionEvent.getX();
                a.this.d = motionEvent.getY();
                a.this.c();
                if (a.this.b == null) {
                    c b = a.this.b(motionEvent);
                    if (b != null) {
                        a.this.c -= b.l;
                        a.this.d -= b.m;
                        a.this.a(b.h, true);
                        if (a.this.a.remove(b.h.itemView)) {
                            a.this.h.clearView(a.this.k, b.h);
                        }
                        a.this.a(b.h, b.i);
                        a.this.a(motionEvent, a.this.i, 0);
                    }
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                a.this.g = -1;
                a.this.a((v) null, 0);
            } else if (a.this.g != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(a.this.g);
                if (findPointerIndex >= 0) {
                    a.this.a(actionMasked, motionEvent, findPointerIndex);
                }
            }
            if (a.this.m != null) {
                a.this.m.addMovement(motionEvent);
            }
            if (a.this.b != null) {
                return true;
            }
            return false;
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int i = 0;
            a.this.p.onTouchEvent(motionEvent);
            if (a.this.m != null) {
                a.this.m.addMovement(motionEvent);
            }
            if (a.this.g != -1) {
                int actionMasked = motionEvent.getActionMasked();
                int findPointerIndex = motionEvent.findPointerIndex(a.this.g);
                if (findPointerIndex >= 0) {
                    a.this.a(actionMasked, motionEvent, findPointerIndex);
                }
                v vVar = a.this.b;
                if (vVar != null) {
                    switch (actionMasked) {
                        case 1:
                            break;
                        case 2:
                            if (findPointerIndex >= 0) {
                                a.this.a(motionEvent, a.this.i, findPointerIndex);
                                a.this.a(vVar);
                                a.this.k.removeCallbacks(a.this.l);
                                a.this.l.run();
                                a.this.k.invalidate();
                                return;
                            }
                            return;
                        case 3:
                            if (a.this.m != null) {
                                a.this.m.clear();
                                break;
                            }
                            break;
                        case 6:
                            int actionIndex = motionEvent.getActionIndex();
                            if (motionEvent.getPointerId(actionIndex) == a.this.g) {
                                if (actionIndex == 0) {
                                    i = 1;
                                }
                                a.this.g = motionEvent.getPointerId(i);
                                a.this.a(motionEvent, a.this.i, actionIndex);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                    a.this.a((v) null, 0);
                    a.this.g = -1;
                }
            }
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                a.this.a((v) null, 0);
            }
        }
    };
    private Rect C;
    private long D;
    final List<View> a = new ArrayList();
    v b = null;
    float c;
    float d;
    float e;
    float f;
    int g = -1;
    C0015a h;
    int i;
    List<c> j = new ArrayList();
    RecyclerView k;
    final Runnable l = new Runnable() {
        public void run() {
            if (a.this.b != null && a.this.b()) {
                if (a.this.b != null) {
                    a.this.a(a.this.b);
                }
                a.this.k.removeCallbacks(a.this.l);
                ViewCompat.postOnAnimation(a.this.k, this);
            }
        }
    };
    VelocityTracker m;
    View n = null;
    int o = -1;
    GestureDetectorCompat p;

    /* renamed from: q reason: collision with root package name */
    private final float[] f359q = new float[2];
    private float r;
    private float s;
    private float t;
    private float u;
    private int v = 0;
    private int w;
    private List<v> x;
    private List<Integer> y;
    private android.support.v7.widget.RecyclerView.d z = null;

    /* renamed from: android.support.v7.widget.a.a$a reason: collision with other inner class name */
    /* compiled from: ItemTouchHelper */
    public static abstract class C0015a {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator = new Interpolator() {
            public float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        };
        private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        };
        private int mCachedMaxScrollSpeed = -1;

        public abstract int getMovementFlags(RecyclerView recyclerView, v vVar);

        public abstract boolean onMove(RecyclerView recyclerView, v vVar, v vVar2);

        public abstract void onSwiped(v vVar, int i);

        public static b getDefaultUIUtil() {
            return c.a;
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int i3 = i & ABS_HORIZONTAL_DIR_FLAGS;
            if (i3 == 0) {
                return i;
            }
            int i4 = (i3 ^ -1) & i;
            if (i2 == 0) {
                return i4 | (i3 << 2);
            }
            return i4 | ((i3 << 1) & -789517) | (((i3 << 1) & ABS_HORIZONTAL_DIR_FLAGS) << 2);
        }

        public static int makeMovementFlags(int i, int i2) {
            return makeFlag(0, i2 | i) | makeFlag(1, i2) | makeFlag(2, i);
        }

        public static int makeFlag(int i, int i2) {
            return i2 << (i * 8);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            int i3 = i & RELATIVE_DIR_FLAGS;
            if (i3 == 0) {
                return i;
            }
            int i4 = (i3 ^ -1) & i;
            if (i2 == 0) {
                return i4 | (i3 >> 2);
            }
            return i4 | ((i3 >> 1) & -3158065) | (((i3 >> 1) & RELATIVE_DIR_FLAGS) >> 2);
        }

        /* access modifiers changed from: 0000 */
        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, v vVar) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, vVar), ViewCompat.getLayoutDirection(recyclerView));
        }

        /* access modifiers changed from: 0000 */
        public boolean hasDragFlag(RecyclerView recyclerView, v vVar) {
            return (getAbsoluteMovementFlags(recyclerView, vVar) & 16711680) != 0;
        }

        /* access modifiers changed from: 0000 */
        public boolean hasSwipeFlag(RecyclerView recyclerView, v vVar) {
            return (getAbsoluteMovementFlags(recyclerView, vVar) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) != 0;
        }

        public boolean canDropOver(RecyclerView recyclerView, v vVar, v vVar2) {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getSwipeThreshold(v vVar) {
            return 0.5f;
        }

        public float getMoveThreshold(v vVar) {
            return 0.5f;
        }

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0053  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0074  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0095  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.v7.widget.RecyclerView.v chooseDropTarget(android.support.v7.widget.RecyclerView.v r14, java.util.List<android.support.v7.widget.RecyclerView.v> r15, int r16, int r17) {
            /*
                r13 = this;
                android.view.View r0 = r14.itemView
                int r0 = r0.getWidth()
                int r5 = r16 + r0
                android.view.View r0 = r14.itemView
                int r0 = r0.getHeight()
                int r6 = r17 + r0
                r2 = 0
                r1 = -1
                android.view.View r0 = r14.itemView
                int r0 = r0.getLeft()
                int r7 = r16 - r0
                android.view.View r0 = r14.itemView
                int r0 = r0.getTop()
                int r8 = r17 - r0
                int r9 = r15.size()
                r0 = 0
                r4 = r0
            L_0x0028:
                if (r4 >= r9) goto L_0x00bc
                java.lang.Object r0 = r15.get(r4)
                android.support.v7.widget.RecyclerView$v r0 = (android.support.v7.widget.RecyclerView.v) r0
                if (r7 <= 0) goto L_0x00bd
                android.view.View r3 = r0.itemView
                int r3 = r3.getRight()
                int r3 = r3 - r5
                if (r3 >= 0) goto L_0x00bd
                android.view.View r10 = r0.itemView
                int r10 = r10.getRight()
                android.view.View r11 = r14.itemView
                int r11 = r11.getRight()
                if (r10 <= r11) goto L_0x00bd
                int r3 = java.lang.Math.abs(r3)
                if (r3 <= r1) goto L_0x00bd
                r2 = r3
                r3 = r0
            L_0x0051:
                if (r7 >= 0) goto L_0x00c5
                android.view.View r1 = r0.itemView
                int r1 = r1.getLeft()
                int r1 = r1 - r16
                if (r1 <= 0) goto L_0x00c5
                android.view.View r10 = r0.itemView
                int r10 = r10.getLeft()
                android.view.View r11 = r14.itemView
                int r11 = r11.getLeft()
                if (r10 >= r11) goto L_0x00c5
                int r1 = java.lang.Math.abs(r1)
                if (r1 <= r2) goto L_0x00c5
                r3 = r0
            L_0x0072:
                if (r8 >= 0) goto L_0x00c3
                android.view.View r2 = r0.itemView
                int r2 = r2.getTop()
                int r2 = r2 - r17
                if (r2 <= 0) goto L_0x00c3
                android.view.View r10 = r0.itemView
                int r10 = r10.getTop()
                android.view.View r11 = r14.itemView
                int r11 = r11.getTop()
                if (r10 >= r11) goto L_0x00c3
                int r2 = java.lang.Math.abs(r2)
                if (r2 <= r1) goto L_0x00c3
                r3 = r0
            L_0x0093:
                if (r8 <= 0) goto L_0x00c0
                android.view.View r1 = r0.itemView
                int r1 = r1.getBottom()
                int r1 = r1 - r6
                if (r1 >= 0) goto L_0x00c0
                android.view.View r10 = r0.itemView
                int r10 = r10.getBottom()
                android.view.View r11 = r14.itemView
                int r11 = r11.getBottom()
                if (r10 <= r11) goto L_0x00c0
                int r1 = java.lang.Math.abs(r1)
                if (r1 <= r2) goto L_0x00c0
                r12 = r1
                r1 = r0
                r0 = r12
            L_0x00b5:
                int r2 = r4 + 1
                r4 = r2
                r2 = r1
                r1 = r0
                goto L_0x0028
            L_0x00bc:
                return r2
            L_0x00bd:
                r3 = r2
                r2 = r1
                goto L_0x0051
            L_0x00c0:
                r0 = r2
                r1 = r3
                goto L_0x00b5
            L_0x00c3:
                r2 = r1
                goto L_0x0093
            L_0x00c5:
                r1 = r2
                goto L_0x0072
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.a.a.C0015a.chooseDropTarget(android.support.v7.widget.RecyclerView$v, java.util.List, int, int):android.support.v7.widget.RecyclerView$v");
        }

        public void onSelectedChanged(v vVar, int i) {
            if (vVar != null) {
                c.a.b(vVar.itemView);
            }
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public void onMoved(RecyclerView recyclerView, v vVar, int i, v vVar2, int i2, int i3, int i4) {
            i layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof d) {
                ((d) layoutManager).a(vVar.itemView, vVar2.itemView, i3, i4);
                return;
            }
            if (layoutManager.f()) {
                if (layoutManager.h(vVar2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.a(i2);
                }
                if (layoutManager.j(vVar2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.a(i2);
                }
            }
            if (layoutManager.g()) {
                if (layoutManager.i(vVar2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.a(i2);
                }
                if (layoutManager.k(vVar2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.a(i2);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void onDraw(Canvas canvas, RecyclerView recyclerView, v vVar, List<c> list, int i, float f, float f2) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                c cVar = (c) list.get(i2);
                cVar.c();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, cVar.h, cVar.l, cVar.m, cVar.i, false);
                canvas.restoreToCount(save);
            }
            if (vVar != null) {
                int save2 = canvas.save();
                onChildDraw(canvas, recyclerView, vVar, f, f2, i, true);
                canvas.restoreToCount(save2);
            }
        }

        /* access modifiers changed from: 0000 */
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, v vVar, List<c> list, int i, float f, float f2) {
            boolean z;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                c cVar = (c) list.get(i2);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, cVar.h, cVar.l, cVar.m, cVar.i, false);
                canvas.restoreToCount(save);
            }
            if (vVar != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, vVar, f, f2, i, true);
                canvas.restoreToCount(save2);
            }
            boolean z2 = false;
            int i3 = size - 1;
            while (i3 >= 0) {
                c cVar2 = (c) list.get(i3);
                if (cVar2.o && !cVar2.k) {
                    list.remove(i3);
                    z = z2;
                } else if (!cVar2.o) {
                    z = true;
                } else {
                    z = z2;
                }
                i3--;
                z2 = z;
            }
            if (z2) {
                recyclerView.invalidate();
            }
        }

        public void clearView(RecyclerView recyclerView, v vVar) {
            c.a.a(vVar.itemView);
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, v vVar, float f, float f2, int i, boolean z) {
            c.a.a(canvas, recyclerView, vVar.itemView, f, f2, i, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, v vVar, float f, float f2, int i, boolean z) {
            c.a.b(canvas, recyclerView, vVar.itemView, f, f2, i, z);
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            f itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                return i == 8 ? 200 : 250;
            }
            if (i == 8) {
                return itemAnimator.e();
            }
            return itemAnimator.g();
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            float f = 1.0f;
            int maxDragScroll = (int) (((float) (getMaxDragScroll(recyclerView) * ((int) Math.signum((float) i2)))) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (((float) Math.abs(i2)) * 1.0f) / ((float) i))));
            if (j <= DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS) {
                f = ((float) j) / 2000.0f;
            }
            int interpolation = (int) (sDragScrollInterpolator.getInterpolation(f) * ((float) maxDragScroll));
            if (interpolation == 0) {
                return i2 > 0 ? 1 : -1;
            }
            return interpolation;
        }
    }

    /* compiled from: ItemTouchHelper */
    private class b extends SimpleOnGestureListener {
        private boolean b = true;

        b() {
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.b = false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.b) {
                View a2 = a.this.a(motionEvent);
                if (a2 != null) {
                    v b2 = a.this.k.b(a2);
                    if (b2 != null && a.this.h.hasDragFlag(a.this.k, b2) && motionEvent.getPointerId(0) == a.this.g) {
                        int findPointerIndex = motionEvent.findPointerIndex(a.this.g);
                        float x = motionEvent.getX(findPointerIndex);
                        float y = motionEvent.getY(findPointerIndex);
                        a.this.c = x;
                        a.this.d = y;
                        a aVar = a.this;
                        a.this.f = 0.0f;
                        aVar.e = 0.0f;
                        if (a.this.h.isLongPressDragEnabled()) {
                            a.this.a(b2, 2);
                        }
                    }
                }
            }
        }
    }

    /* compiled from: ItemTouchHelper */
    private static class c implements AnimatorListener {
        private final ValueAnimator a;
        private float b;
        final float d;
        final float e;
        final float f;
        final float g;
        final v h;
        final int i;
        final int j;
        boolean k;
        float l;
        float m;
        boolean n = false;
        boolean o = false;

        c(v vVar, int i2, int i3, float f2, float f3, float f4, float f5) {
            this.i = i3;
            this.j = i2;
            this.h = vVar;
            this.d = f2;
            this.e = f3;
            this.f = f4;
            this.g = f5;
            this.a = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.a.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    c.this.a(valueAnimator.getAnimatedFraction());
                }
            });
            this.a.setTarget(vVar.itemView);
            this.a.addListener(this);
            a(0.0f);
        }

        public void a(long j2) {
            this.a.setDuration(j2);
        }

        public void a() {
            this.h.setIsRecyclable(false);
            this.a.start();
        }

        public void b() {
            this.a.cancel();
        }

        public void a(float f2) {
            this.b = f2;
        }

        public void c() {
            if (this.d == this.f) {
                this.l = this.h.itemView.getTranslationX();
            } else {
                this.l = this.d + (this.b * (this.f - this.d));
            }
            if (this.e == this.g) {
                this.m = this.h.itemView.getTranslationY();
            } else {
                this.m = this.e + (this.b * (this.g - this.e));
            }
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.o) {
                this.h.setIsRecyclable(true);
            }
            this.o = true;
        }

        public void onAnimationCancel(Animator animator) {
            a(1.0f);
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    /* compiled from: ItemTouchHelper */
    public interface d {
        void a(View view, View view2, int i, int i2);
    }

    public a(C0015a aVar) {
        this.h = aVar;
    }

    private static boolean a(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= ((float) view.getWidth()) + f4 && f3 >= f5 && f3 <= ((float) view.getHeight()) + f5;
    }

    public void a(RecyclerView recyclerView) {
        if (this.k != recyclerView) {
            if (this.k != null) {
                e();
            }
            this.k = recyclerView;
            if (recyclerView != null) {
                Resources resources = recyclerView.getResources();
                this.r = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
                this.s = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
                d();
            }
        }
    }

    private void d() {
        this.w = ViewConfiguration.get(this.k.getContext()).getScaledTouchSlop();
        this.k.a((h) this);
        this.k.a(this.B);
        this.k.a((j) this);
        f();
    }

    private void e() {
        this.k.b((h) this);
        this.k.b(this.B);
        this.k.b((j) this);
        for (int size = this.j.size() - 1; size >= 0; size--) {
            this.h.clearView(this.k, ((c) this.j.get(0)).h);
        }
        this.j.clear();
        this.n = null;
        this.o = -1;
        h();
        g();
    }

    private void f() {
        this.A = new b();
        this.p = new GestureDetectorCompat(this.k.getContext(), this.A);
    }

    private void g() {
        if (this.A != null) {
            this.A.a();
            this.A = null;
        }
        if (this.p != null) {
            this.p = null;
        }
    }

    private void a(float[] fArr) {
        if ((this.i & 12) != 0) {
            fArr[0] = (this.t + this.e) - ((float) this.b.itemView.getLeft());
        } else {
            fArr[0] = this.b.itemView.getTranslationX();
        }
        if ((this.i & 3) != 0) {
            fArr[1] = (this.u + this.f) - ((float) this.b.itemView.getTop());
        } else {
            fArr[1] = this.b.itemView.getTranslationY();
        }
    }

    public void a(Canvas canvas, RecyclerView recyclerView, s sVar) {
        float f2;
        float f3 = 0.0f;
        if (this.b != null) {
            a(this.f359q);
            f2 = this.f359q[0];
            f3 = this.f359q[1];
        } else {
            f2 = 0.0f;
        }
        this.h.onDrawOver(canvas, recyclerView, this.b, this.j, this.v, f2, f3);
    }

    public void b(Canvas canvas, RecyclerView recyclerView, s sVar) {
        float f2;
        float f3 = 0.0f;
        this.o = -1;
        if (this.b != null) {
            a(this.f359q);
            f2 = this.f359q[0];
            f3 = this.f359q[1];
        } else {
            f2 = 0.0f;
        }
        this.h.onDraw(canvas, recyclerView, this.b, this.j, this.v, f2, f3);
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar, int i2) {
        final int d2;
        float f2;
        float signum;
        int i3;
        if (vVar != this.b || i2 != this.v) {
            this.D = Long.MIN_VALUE;
            int i4 = this.v;
            a(vVar, true);
            this.v = i2;
            if (i2 == 2) {
                if (vVar == null) {
                    throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
                }
                this.n = vVar.itemView;
                i();
            }
            int i5 = (1 << ((i2 * 8) + 8)) - 1;
            boolean z2 = false;
            if (this.b != null) {
                v vVar2 = this.b;
                if (vVar2.itemView.getParent() != null) {
                    if (i4 == 2) {
                        d2 = 0;
                    } else {
                        d2 = d(vVar2);
                    }
                    h();
                    switch (d2) {
                        case 1:
                        case 2:
                            f2 = 0.0f;
                            signum = Math.signum(this.f) * ((float) this.k.getHeight());
                            break;
                        case 4:
                        case 8:
                        case 16:
                        case 32:
                            signum = 0.0f;
                            f2 = Math.signum(this.e) * ((float) this.k.getWidth());
                            break;
                        default:
                            f2 = 0.0f;
                            signum = 0.0f;
                            break;
                    }
                    if (i4 == 2) {
                        i3 = 8;
                    } else if (d2 > 0) {
                        i3 = 2;
                    } else {
                        i3 = 4;
                    }
                    a(this.f359q);
                    float f3 = this.f359q[0];
                    float f4 = this.f359q[1];
                    final v vVar3 = vVar2;
                    AnonymousClass3 r0 = new c(vVar2, i3, i4, f3, f4, f2, signum) {
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            if (!this.n) {
                                if (d2 <= 0) {
                                    a.this.h.clearView(a.this.k, vVar3);
                                } else {
                                    a.this.a.add(vVar3.itemView);
                                    this.k = true;
                                    if (d2 > 0) {
                                        a.this.a((c) this, d2);
                                    }
                                }
                                if (a.this.n == vVar3.itemView) {
                                    a.this.c(vVar3.itemView);
                                }
                            }
                        }
                    };
                    r0.a(this.h.getAnimationDuration(this.k, i3, f2 - f3, signum - f4));
                    this.j.add(r0);
                    r0.a();
                    z2 = true;
                } else {
                    c(vVar2.itemView);
                    this.h.clearView(this.k, vVar2);
                }
                this.b = null;
            }
            boolean z3 = z2;
            if (vVar != null) {
                this.i = (this.h.getAbsoluteMovementFlags(this.k, vVar) & i5) >> (this.v * 8);
                this.t = (float) vVar.itemView.getLeft();
                this.u = (float) vVar.itemView.getTop();
                this.b = vVar;
                if (i2 == 2) {
                    this.b.itemView.performHapticFeedback(0);
                }
            }
            ViewParent parent = this.k.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(this.b != null);
            }
            if (!z3) {
                this.k.getLayoutManager().L();
            }
            this.h.onSelectedChanged(this.b, this.v);
            this.k.invalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(final c cVar, final int i2) {
        this.k.post(new Runnable() {
            public void run() {
                if (a.this.k != null && a.this.k.isAttachedToWindow() && !cVar.n && cVar.h.getAdapterPosition() != -1) {
                    f itemAnimator = a.this.k.getItemAnimator();
                    if ((itemAnimator == null || !itemAnimator.a((android.support.v7.widget.RecyclerView.f.a) null)) && !a.this.a()) {
                        a.this.h.onSwiped(cVar.h, i2);
                    } else {
                        a.this.k.post(this);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((c) this.j.get(i2)).o) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e4, code lost:
        if (r4 <= 0) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x010c, code lost:
        if (r8 <= 0) goto L_0x010e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a9 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
            r14 = this;
            r12 = -9223372036854775808
            r0 = 0
            r5 = 0
            android.support.v7.widget.RecyclerView$v r1 = r14.b
            if (r1 != 0) goto L_0x000b
            r14.D = r12
        L_0x000a:
            return r0
        L_0x000b:
            long r10 = java.lang.System.currentTimeMillis()
            long r2 = r14.D
            int r1 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x00bb
            r6 = 0
        L_0x0017:
            android.support.v7.widget.RecyclerView r1 = r14.k
            android.support.v7.widget.RecyclerView$i r1 = r1.getLayoutManager()
            android.graphics.Rect r2 = r14.C
            if (r2 != 0) goto L_0x0028
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            r14.C = r2
        L_0x0028:
            android.support.v7.widget.RecyclerView$v r2 = r14.b
            android.view.View r2 = r2.itemView
            android.graphics.Rect r3 = r14.C
            r1.b(r2, r3)
            boolean r2 = r1.f()
            if (r2 == 0) goto L_0x00e6
            float r2 = r14.t
            float r3 = r14.e
            float r2 = r2 + r3
            int r2 = (int) r2
            android.graphics.Rect r3 = r14.C
            int r3 = r3.left
            int r3 = r2 - r3
            android.support.v7.widget.RecyclerView r4 = r14.k
            int r4 = r4.getPaddingLeft()
            int r4 = r3 - r4
            float r3 = r14.e
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x00c1
            if (r4 >= 0) goto L_0x00c1
        L_0x0053:
            boolean r1 = r1.g()
            if (r1 == 0) goto L_0x010e
            float r1 = r14.u
            float r2 = r14.f
            float r1 = r1 + r2
            int r1 = (int) r1
            android.graphics.Rect r2 = r14.C
            int r2 = r2.top
            int r2 = r1 - r2
            android.support.v7.widget.RecyclerView r3 = r14.k
            int r3 = r3.getPaddingTop()
            int r8 = r2 - r3
            float r2 = r14.f
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x00e9
            if (r8 >= 0) goto L_0x00e9
        L_0x0075:
            if (r4 == 0) goto L_0x0117
            android.support.v7.widget.a.a$a r1 = r14.h
            android.support.v7.widget.RecyclerView r2 = r14.k
            android.support.v7.widget.RecyclerView$v r3 = r14.b
            android.view.View r3 = r3.itemView
            int r3 = r3.getWidth()
            android.support.v7.widget.RecyclerView r5 = r14.k
            int r5 = r5.getWidth()
            int r4 = r1.interpolateOutOfBoundsScroll(r2, r3, r4, r5, r6)
            r9 = r4
        L_0x008e:
            if (r8 == 0) goto L_0x0115
            android.support.v7.widget.a.a$a r1 = r14.h
            android.support.v7.widget.RecyclerView r2 = r14.k
            android.support.v7.widget.RecyclerView$v r3 = r14.b
            android.view.View r3 = r3.itemView
            int r3 = r3.getHeight()
            android.support.v7.widget.RecyclerView r4 = r14.k
            int r5 = r4.getHeight()
            r4 = r8
            int r1 = r1.interpolateOutOfBoundsScroll(r2, r3, r4, r5, r6)
        L_0x00a7:
            if (r9 != 0) goto L_0x00ab
            if (r1 == 0) goto L_0x0111
        L_0x00ab:
            long r2 = r14.D
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 != 0) goto L_0x00b3
            r14.D = r10
        L_0x00b3:
            android.support.v7.widget.RecyclerView r0 = r14.k
            r0.scrollBy(r9, r1)
            r0 = 1
            goto L_0x000a
        L_0x00bb:
            long r2 = r14.D
            long r6 = r10 - r2
            goto L_0x0017
        L_0x00c1:
            float r3 = r14.e
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x00e6
            android.support.v7.widget.RecyclerView$v r3 = r14.b
            android.view.View r3 = r3.itemView
            int r3 = r3.getWidth()
            int r2 = r2 + r3
            android.graphics.Rect r3 = r14.C
            int r3 = r3.right
            int r2 = r2 + r3
            android.support.v7.widget.RecyclerView r3 = r14.k
            int r3 = r3.getWidth()
            android.support.v7.widget.RecyclerView r4 = r14.k
            int r4 = r4.getPaddingRight()
            int r3 = r3 - r4
            int r4 = r2 - r3
            if (r4 > 0) goto L_0x0053
        L_0x00e6:
            r4 = r0
            goto L_0x0053
        L_0x00e9:
            float r2 = r14.f
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x010e
            android.support.v7.widget.RecyclerView$v r2 = r14.b
            android.view.View r2 = r2.itemView
            int r2 = r2.getHeight()
            int r1 = r1 + r2
            android.graphics.Rect r2 = r14.C
            int r2 = r2.bottom
            int r1 = r1 + r2
            android.support.v7.widget.RecyclerView r2 = r14.k
            int r2 = r2.getHeight()
            android.support.v7.widget.RecyclerView r3 = r14.k
            int r3 = r3.getPaddingBottom()
            int r2 = r2 - r3
            int r8 = r1 - r2
            if (r8 > 0) goto L_0x0075
        L_0x010e:
            r8 = r0
            goto L_0x0075
        L_0x0111:
            r14.D = r12
            goto L_0x000a
        L_0x0115:
            r1 = r8
            goto L_0x00a7
        L_0x0117:
            r9 = r4
            goto L_0x008e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.a.a.b():boolean");
    }

    private List<v> c(v vVar) {
        if (this.x == null) {
            this.x = new ArrayList();
            this.y = new ArrayList();
        } else {
            this.x.clear();
            this.y.clear();
        }
        int boundingBoxMargin = this.h.getBoundingBoxMargin();
        int round = Math.round(this.t + this.e) - boundingBoxMargin;
        int round2 = Math.round(this.u + this.f) - boundingBoxMargin;
        int width = vVar.itemView.getWidth() + round + (boundingBoxMargin * 2);
        int height = vVar.itemView.getHeight() + round2 + (boundingBoxMargin * 2);
        int i2 = (round + width) / 2;
        int i3 = (round2 + height) / 2;
        i layoutManager = this.k.getLayoutManager();
        int y2 = layoutManager.y();
        for (int i4 = 0; i4 < y2; i4++) {
            View i5 = layoutManager.i(i4);
            if (i5 != vVar.itemView && i5.getBottom() >= round2 && i5.getTop() <= height && i5.getRight() >= round && i5.getLeft() <= width) {
                v b2 = this.k.b(i5);
                if (this.h.canDropOver(this.k, this.b, b2)) {
                    int abs = Math.abs(i2 - ((i5.getLeft() + i5.getRight()) / 2));
                    int abs2 = Math.abs(i3 - ((i5.getBottom() + i5.getTop()) / 2));
                    int i6 = (abs * abs) + (abs2 * abs2);
                    int size = this.x.size();
                    int i7 = 0;
                    int i8 = 0;
                    while (i8 < size && i6 > ((Integer) this.y.get(i8)).intValue()) {
                        i7++;
                        i8++;
                    }
                    this.x.add(i7, b2);
                    this.y.add(i7, Integer.valueOf(i6));
                }
            }
        }
        return this.x;
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar) {
        if (!this.k.isLayoutRequested() && this.v == 2) {
            float moveThreshold = this.h.getMoveThreshold(vVar);
            int i2 = (int) (this.t + this.e);
            int i3 = (int) (this.u + this.f);
            if (((float) Math.abs(i3 - vVar.itemView.getTop())) >= ((float) vVar.itemView.getHeight()) * moveThreshold || ((float) Math.abs(i2 - vVar.itemView.getLeft())) >= moveThreshold * ((float) vVar.itemView.getWidth())) {
                List c2 = c(vVar);
                if (c2.size() != 0) {
                    v chooseDropTarget = this.h.chooseDropTarget(vVar, c2, i2, i3);
                    if (chooseDropTarget == null) {
                        this.x.clear();
                        this.y.clear();
                        return;
                    }
                    int adapterPosition = chooseDropTarget.getAdapterPosition();
                    int adapterPosition2 = vVar.getAdapterPosition();
                    if (this.h.onMove(this.k, vVar, chooseDropTarget)) {
                        this.h.onMoved(this.k, vVar, adapterPosition2, chooseDropTarget, adapterPosition, i2, i3);
                    }
                }
            }
        }
    }

    public void a(View view) {
    }

    public void b(View view) {
        c(view);
        v b2 = this.k.b(view);
        if (b2 != null) {
            if (this.b == null || b2 != this.b) {
                a(b2, false);
                if (this.a.remove(b2.itemView)) {
                    this.h.clearView(this.k, b2);
                    return;
                }
                return;
            }
            a((v) null, 0);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar, boolean z2) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            c cVar = (c) this.j.get(size);
            if (cVar.h == vVar) {
                cVar.n |= z2;
                if (!cVar.o) {
                    cVar.b();
                }
                this.j.remove(size);
                return;
            }
        }
    }

    public void a(Rect rect, View view, RecyclerView recyclerView, s sVar) {
        rect.setEmpty();
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.m != null) {
            this.m.recycle();
        }
        this.m = VelocityTracker.obtain();
    }

    private void h() {
        if (this.m != null) {
            this.m.recycle();
            this.m = null;
        }
    }

    private v c(MotionEvent motionEvent) {
        i layoutManager = this.k.getLayoutManager();
        if (this.g == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(this.g);
        float x2 = motionEvent.getX(findPointerIndex) - this.c;
        float y2 = motionEvent.getY(findPointerIndex) - this.d;
        float abs = Math.abs(x2);
        float abs2 = Math.abs(y2);
        if (abs < ((float) this.w) && abs2 < ((float) this.w)) {
            return null;
        }
        if (abs > abs2 && layoutManager.f()) {
            return null;
        }
        if (abs2 > abs && layoutManager.g()) {
            return null;
        }
        View a2 = a(motionEvent);
        if (a2 != null) {
            return this.k.b(a2);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, MotionEvent motionEvent, int i3) {
        if (this.b == null && i2 == 2 && this.v != 2 && this.h.isItemViewSwipeEnabled() && this.k.getScrollState() != 1) {
            v c2 = c(motionEvent);
            if (c2 != null) {
                int absoluteMovementFlags = (this.h.getAbsoluteMovementFlags(this.k, c2) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                if (absoluteMovementFlags != 0) {
                    float x2 = motionEvent.getX(i3);
                    float f2 = x2 - this.c;
                    float y2 = motionEvent.getY(i3) - this.d;
                    float abs = Math.abs(f2);
                    float abs2 = Math.abs(y2);
                    if (abs >= ((float) this.w) || abs2 >= ((float) this.w)) {
                        if (abs > abs2) {
                            if (f2 < 0.0f && (absoluteMovementFlags & 4) == 0) {
                                return;
                            }
                            if (f2 > 0.0f && (absoluteMovementFlags & 8) == 0) {
                                return;
                            }
                        } else if (y2 < 0.0f && (absoluteMovementFlags & 1) == 0) {
                            return;
                        } else {
                            if (y2 > 0.0f && (absoluteMovementFlags & 2) == 0) {
                                return;
                            }
                        }
                        this.f = 0.0f;
                        this.e = 0.0f;
                        this.g = motionEvent.getPointerId(0);
                        a(c2, 1);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public View a(MotionEvent motionEvent) {
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        if (this.b != null) {
            View view = this.b.itemView;
            if (a(view, x2, y2, this.t + this.e, this.u + this.f)) {
                return view;
            }
        }
        for (int size = this.j.size() - 1; size >= 0; size--) {
            c cVar = (c) this.j.get(size);
            View view2 = cVar.h.itemView;
            if (a(view2, x2, y2, cVar.l, cVar.m)) {
                return view2;
            }
        }
        return this.k.a(x2, y2);
    }

    public void b(v vVar) {
        if (!this.h.hasDragFlag(this.k, vVar)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
        } else if (vVar.itemView.getParent() != this.k) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            c();
            this.f = 0.0f;
            this.e = 0.0f;
            a(vVar, 2);
        }
    }

    /* access modifiers changed from: 0000 */
    public c b(MotionEvent motionEvent) {
        if (this.j.isEmpty()) {
            return null;
        }
        View a2 = a(motionEvent);
        for (int size = this.j.size() - 1; size >= 0; size--) {
            c cVar = (c) this.j.get(size);
            if (cVar.h.itemView == a2) {
                return cVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(MotionEvent motionEvent, int i2, int i3) {
        float x2 = motionEvent.getX(i3);
        float y2 = motionEvent.getY(i3);
        this.e = x2 - this.c;
        this.f = y2 - this.d;
        if ((i2 & 4) == 0) {
            this.e = Math.max(0.0f, this.e);
        }
        if ((i2 & 8) == 0) {
            this.e = Math.min(0.0f, this.e);
        }
        if ((i2 & 1) == 0) {
            this.f = Math.max(0.0f, this.f);
        }
        if ((i2 & 2) == 0) {
            this.f = Math.min(0.0f, this.f);
        }
    }

    private int d(v vVar) {
        if (this.v == 2) {
            return 0;
        }
        int movementFlags = this.h.getMovementFlags(this.k, vVar);
        int convertToAbsoluteDirection = (this.h.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.k)) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i2 = (movementFlags & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (Math.abs(this.e) > Math.abs(this.f)) {
            int b2 = b(vVar, convertToAbsoluteDirection);
            if (b2 > 0) {
                return (i2 & b2) == 0 ? C0015a.convertToRelativeDirection(b2, ViewCompat.getLayoutDirection(this.k)) : b2;
            }
            int c2 = c(vVar, convertToAbsoluteDirection);
            if (c2 > 0) {
                return c2;
            }
            return 0;
        }
        int c3 = c(vVar, convertToAbsoluteDirection);
        if (c3 > 0) {
            return c3;
        }
        int b3 = b(vVar, convertToAbsoluteDirection);
        if (b3 > 0) {
            return (i2 & b3) == 0 ? C0015a.convertToRelativeDirection(b3, ViewCompat.getLayoutDirection(this.k)) : b3;
        }
        return 0;
    }

    private int b(v vVar, int i2) {
        int i3 = 8;
        if ((i2 & 12) != 0) {
            int i4 = this.e > 0.0f ? 8 : 4;
            if (this.m != null && this.g > -1) {
                this.m.computeCurrentVelocity(1000, this.h.getSwipeVelocityThreshold(this.s));
                float xVelocity = this.m.getXVelocity(this.g);
                float yVelocity = this.m.getYVelocity(this.g);
                if (xVelocity <= 0.0f) {
                    i3 = 4;
                }
                float abs = Math.abs(xVelocity);
                if ((i3 & i2) != 0 && i4 == i3 && abs >= this.h.getSwipeEscapeVelocity(this.r) && abs > Math.abs(yVelocity)) {
                    return i3;
                }
            }
            float width = ((float) this.k.getWidth()) * this.h.getSwipeThreshold(vVar);
            if ((i2 & i4) != 0 && Math.abs(this.e) > width) {
                return i4;
            }
        }
        return 0;
    }

    private int c(v vVar, int i2) {
        int i3 = 2;
        if ((i2 & 3) != 0) {
            int i4 = this.f > 0.0f ? 2 : 1;
            if (this.m != null && this.g > -1) {
                this.m.computeCurrentVelocity(1000, this.h.getSwipeVelocityThreshold(this.s));
                float xVelocity = this.m.getXVelocity(this.g);
                float yVelocity = this.m.getYVelocity(this.g);
                if (yVelocity <= 0.0f) {
                    i3 = 1;
                }
                float abs = Math.abs(yVelocity);
                if ((i3 & i2) != 0 && i3 == i4 && abs >= this.h.getSwipeEscapeVelocity(this.r) && abs > Math.abs(xVelocity)) {
                    return i3;
                }
            }
            float height = ((float) this.k.getHeight()) * this.h.getSwipeThreshold(vVar);
            if ((i2 & i4) != 0 && Math.abs(this.f) > height) {
                return i4;
            }
        }
        return 0;
    }

    private void i() {
        if (VERSION.SDK_INT < 21) {
            if (this.z == null) {
                this.z = new android.support.v7.widget.RecyclerView.d() {
                    public int a(int i, int i2) {
                        if (a.this.n == null) {
                            return i2;
                        }
                        int i3 = a.this.o;
                        if (i3 == -1) {
                            i3 = a.this.k.indexOfChild(a.this.n);
                            a.this.o = i3;
                        }
                        if (i2 == i - 1) {
                            return i3;
                        }
                        return i2 >= i3 ? i2 + 1 : i2;
                    }
                };
            }
            this.k.setChildDrawingOrderCallback(this.z);
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(View view) {
        if (view == this.n) {
            this.n = null;
            if (this.z != null) {
                this.k.setChildDrawingOrderCallback(null);
            }
        }
    }
}
