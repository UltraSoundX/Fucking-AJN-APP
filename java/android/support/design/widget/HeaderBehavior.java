package android.support.design.widget;

import android.content.Context;
import android.support.v4.math.MathUtils;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    OverScroller a;
    private Runnable b;
    private boolean c;
    private int d = -1;
    private int e;
    private int f = -1;
    private VelocityTracker g;

    private class a implements Runnable {
        private final CoordinatorLayout b;
        private final V c;

        a(CoordinatorLayout coordinatorLayout, V v) {
            this.b = coordinatorLayout;
            this.c = v;
        }

        public void run() {
            if (this.c != null && HeaderBehavior.this.a != null) {
                if (HeaderBehavior.this.a.computeScrollOffset()) {
                    HeaderBehavior.this.a_(this.b, this.c, HeaderBehavior.this.a.getCurrY());
                    ViewCompat.postOnAnimation(this.c, this);
                    return;
                }
                HeaderBehavior.this.a(this.b, this.c);
            }
        }
    }

    public HeaderBehavior() {
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.f < 0) {
            this.f = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.c) {
            return true;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.c = false;
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (c(v) && coordinatorLayout.a((View) v, x, y)) {
                    this.e = y;
                    this.d = motionEvent.getPointerId(0);
                    d();
                    break;
                }
            case 1:
            case 3:
                this.c = false;
                this.d = -1;
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                    break;
                }
                break;
            case 2:
                int i = this.d;
                if (i != -1) {
                    int findPointerIndex = motionEvent.findPointerIndex(i);
                    if (findPointerIndex != -1) {
                        int y2 = (int) motionEvent.getY(findPointerIndex);
                        if (Math.abs(y2 - this.e) > this.f) {
                            this.c = true;
                            this.e = y2;
                            break;
                        }
                    }
                }
                break;
        }
        if (this.g != null) {
            this.g.addMovement(motionEvent);
        }
        return this.c;
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.f < 0) {
            this.f = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                int y = (int) motionEvent.getY();
                if (coordinatorLayout.a((View) v, (int) motionEvent.getX(), y) && c(v)) {
                    this.e = y;
                    this.d = motionEvent.getPointerId(0);
                    d();
                    break;
                } else {
                    return false;
                }
                break;
            case 1:
                if (this.g != null) {
                    this.g.addMovement(motionEvent);
                    this.g.computeCurrentVelocity(1000);
                    a(coordinatorLayout, v, -a(v), 0, this.g.getYVelocity(this.d));
                    break;
                }
                break;
            case 2:
                int findPointerIndex = motionEvent.findPointerIndex(this.d);
                if (findPointerIndex != -1) {
                    int y2 = (int) motionEvent.getY(findPointerIndex);
                    int i = this.e - y2;
                    if (!this.c && Math.abs(i) > this.f) {
                        this.c = true;
                        i = i > 0 ? i - this.f : i + this.f;
                    }
                    if (this.c) {
                        this.e = y2;
                        b(coordinatorLayout, v, i, b(v), 0);
                        break;
                    }
                } else {
                    return false;
                }
                break;
            case 3:
                break;
        }
        this.c = false;
        this.d = -1;
        if (this.g != null) {
            this.g.recycle();
            this.g = null;
        }
        if (this.g != null) {
            this.g.addMovement(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public int a_(CoordinatorLayout coordinatorLayout, V v, int i) {
        return a(coordinatorLayout, v, i, (int) ExploreByTouchHelper.INVALID_ID, Integer.MAX_VALUE);
    }

    /* access modifiers changed from: 0000 */
    public int a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        int b2 = b();
        if (i2 == 0 || b2 < i2 || b2 > i3) {
            return 0;
        }
        int clamp = MathUtils.clamp(i, i2, i3);
        if (b2 == clamp) {
            return 0;
        }
        a(clamp);
        return b2 - clamp;
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return b();
    }

    /* access modifiers changed from: 0000 */
    public final int b(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return a(coordinatorLayout, v, a() - i, i2, i3);
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, float f2) {
        if (this.b != null) {
            v.removeCallbacks(this.b);
            this.b = null;
        }
        if (this.a == null) {
            this.a = new OverScroller(v.getContext());
        }
        this.a.fling(0, b(), 0, Math.round(f2), 0, 0, i, i2);
        if (this.a.computeScrollOffset()) {
            this.b = new a(coordinatorLayout, v);
            ViewCompat.postOnAnimation(v, this.b);
            return true;
        }
        a(coordinatorLayout, v);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(CoordinatorLayout coordinatorLayout, V v) {
    }

    /* access modifiers changed from: 0000 */
    public boolean c(V v) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public int b(V v) {
        return -v.getHeight();
    }

    /* access modifiers changed from: 0000 */
    public int a(V v) {
        return v.getHeight();
    }

    private void d() {
        if (this.g == null) {
            this.g = VelocityTracker.obtain();
        }
    }
}
