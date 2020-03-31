package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.design.R;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import java.util.List;

public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final Handler a = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    ((BaseTransientBottomBar) message.obj).c();
                    return true;
                case 1:
                    ((BaseTransientBottomBar) message.obj).b(message.arg1);
                    return true;
                default:
                    return false;
            }
        }
    });
    /* access modifiers changed from: private */
    public static final boolean d = (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19);
    private static final int[] e = {R.attr.snackbarStyle};
    protected final e b;
    final a c;
    private final ViewGroup f;
    /* access modifiers changed from: private */
    public final android.support.design.g.a g;
    private List<a<B>> h;
    private Behavior i;
    private final AccessibilityManager j;

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final b g = new b(this);

        /* access modifiers changed from: private */
        public void a(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.g.a(baseTransientBottomBar);
        }

        public boolean a(View view) {
            return this.g.a(view);
        }

        public boolean b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.g.a(coordinatorLayout, view, motionEvent);
            return super.b(coordinatorLayout, view, motionEvent);
        }
    }

    public static abstract class a<B> {
        public void a(B b, int i) {
        }

        public void a(B b) {
        }
    }

    public static class b {
        private a a;

        public b(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.a(0.1f);
            swipeDismissBehavior.b(0.6f);
            swipeDismissBehavior.a(0);
        }

        public void a(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.a = baseTransientBottomBar.c;
        }

        public boolean a(View view) {
            return view instanceof e;
        }

        public void a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    if (coordinatorLayout.a(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                        n.a().c(this.a);
                        return;
                    }
                    return;
                case 1:
                case 3:
                    n.a().d(this.a);
                    return;
                default:
                    return;
            }
        }
    }

    protected interface c {
        void a(View view);

        void b(View view);
    }

    protected interface d {
        void a(View view, int i, int i2, int i3, int i4);
    }

    protected static class e extends FrameLayout {
        private final AccessibilityManager a;
        private final TouchExplorationStateChangeListener b;
        private d c;
        private c d;

        protected e(Context context) {
            this(context, null);
        }

        protected e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            obtainStyledAttributes.recycle();
            this.a = (AccessibilityManager) context.getSystemService("accessibility");
            this.b = new TouchExplorationStateChangeListener() {
                public void onTouchExplorationStateChanged(boolean z) {
                    e.this.setClickableOrFocusableBasedOnAccessibility(z);
                }
            };
            AccessibilityManagerCompat.addTouchExplorationStateChangeListener(this.a, this.b);
            setClickableOrFocusableBasedOnAccessibility(this.a.isTouchExplorationEnabled());
        }

        /* access modifiers changed from: private */
        public void setClickableOrFocusableBasedOnAccessibility(boolean z) {
            setClickable(!z);
            setFocusable(z);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.c != null) {
                this.c.a(this, i, i2, i3, i4);
            }
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.d != null) {
                this.d.a(this);
            }
            ViewCompat.requestApplyInsets(this);
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.d != null) {
                this.d.b(this);
            }
            AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(this.a, this.b);
        }

        /* access modifiers changed from: 0000 */
        public void setOnLayoutChangeListener(d dVar) {
            this.c = dVar;
        }

        /* access modifiers changed from: 0000 */
        public void setOnAttachStateChangeListener(c cVar) {
            this.d = cVar;
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2) {
        n.a().a(this.c, i2);
    }

    public boolean a() {
        return n.a().e(this.c);
    }

    /* access modifiers changed from: protected */
    public SwipeDismissBehavior<? extends View> b() {
        return new Behavior();
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        if (this.b.getParent() == null) {
            LayoutParams layoutParams = this.b.getLayoutParams();
            if (layoutParams instanceof android.support.design.widget.CoordinatorLayout.d) {
                android.support.design.widget.CoordinatorLayout.d dVar = (android.support.design.widget.CoordinatorLayout.d) layoutParams;
                SwipeDismissBehavior swipeDismissBehavior = this.i == null ? b() : this.i;
                if (swipeDismissBehavior instanceof Behavior) {
                    ((Behavior) swipeDismissBehavior).a(this);
                }
                swipeDismissBehavior.a((android.support.design.widget.SwipeDismissBehavior.a) new android.support.design.widget.SwipeDismissBehavior.a() {
                    public void a(View view) {
                        view.setVisibility(8);
                        BaseTransientBottomBar.this.a(0);
                    }

                    public void a(int i) {
                        switch (i) {
                            case 0:
                                n.a().d(BaseTransientBottomBar.this.c);
                                return;
                            case 1:
                            case 2:
                                n.a().c(BaseTransientBottomBar.this.c);
                                return;
                            default:
                                return;
                        }
                    }
                });
                dVar.a((android.support.design.widget.CoordinatorLayout.Behavior) swipeDismissBehavior);
                dVar.g = 80;
            }
            this.f.addView(this.b);
        }
        this.b.setOnAttachStateChangeListener(new c() {
            public void a(View view) {
            }

            public void b(View view) {
                if (BaseTransientBottomBar.this.a()) {
                    BaseTransientBottomBar.a.post(new Runnable() {
                        public void run() {
                            BaseTransientBottomBar.this.c(3);
                        }
                    });
                }
            }
        });
        if (!ViewCompat.isLaidOut(this.b)) {
            this.b.setOnLayoutChangeListener(new d() {
                public void a(View view, int i, int i2, int i3, int i4) {
                    BaseTransientBottomBar.this.b.setOnLayoutChangeListener(null);
                    if (BaseTransientBottomBar.this.f()) {
                        BaseTransientBottomBar.this.d();
                    } else {
                        BaseTransientBottomBar.this.e();
                    }
                }
            });
        } else if (f()) {
            d();
        } else {
            e();
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        final int h2 = h();
        if (d) {
            ViewCompat.offsetTopAndBottom(this.b, h2);
        } else {
            this.b.setTranslationY((float) h2);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(new int[]{h2, 0});
        valueAnimator.setInterpolator(android.support.design.a.a.b);
        valueAnimator.setDuration(250);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.g.a(70, 180);
            }

            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.e();
            }
        });
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            private int c = h2;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.d) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.b, intValue - this.c);
                } else {
                    BaseTransientBottomBar.this.b.setTranslationY((float) intValue);
                }
                this.c = intValue;
            }
        });
        valueAnimator.start();
    }

    private void d(final int i2) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(new int[]{0, h()});
        valueAnimator.setInterpolator(android.support.design.a.a.b);
        valueAnimator.setDuration(250);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.g.b(0, 180);
            }

            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.c(i2);
            }
        });
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            private int b = 0;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.d) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.b, intValue - this.b);
                } else {
                    BaseTransientBottomBar.this.b.setTranslationY((float) intValue);
                }
                this.b = intValue;
            }
        });
        valueAnimator.start();
    }

    private int h() {
        int height = this.b.getHeight();
        LayoutParams layoutParams = this.b.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            return ((MarginLayoutParams) layoutParams).bottomMargin + height;
        }
        return height;
    }

    /* access modifiers changed from: 0000 */
    public final void b(int i2) {
        if (!f() || this.b.getVisibility() != 0) {
            c(i2);
        } else {
            d(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        n.a().b(this.c);
        if (this.h != null) {
            for (int size = this.h.size() - 1; size >= 0; size--) {
                ((a) this.h.get(size)).a(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2) {
        n.a().a(this.c);
        if (this.h != null) {
            for (int size = this.h.size() - 1; size >= 0; size--) {
                ((a) this.h.get(size)).a(this, i2);
            }
        }
        ViewParent parent = this.b.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.b);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean f() {
        List enabledAccessibilityServiceList = this.j.getEnabledAccessibilityServiceList(1);
        if (enabledAccessibilityServiceList == null || !enabledAccessibilityServiceList.isEmpty()) {
            return false;
        }
        return true;
    }
}
