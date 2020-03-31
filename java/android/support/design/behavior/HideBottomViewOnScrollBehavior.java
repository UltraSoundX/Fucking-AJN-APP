package android.support.design.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.design.a.a;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class HideBottomViewOnScrollBehavior<V extends View> extends Behavior<V> {
    private int a = 0;
    private int b = 2;
    /* access modifiers changed from: private */
    public ViewPropertyAnimator c;

    public HideBottomViewOnScrollBehavior() {
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.a = v.getMeasuredHeight();
        return super.a(coordinatorLayout, v, i);
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        return i == 2;
    }

    public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4) {
        if (this.b != 1 && i2 > 0) {
            b(v);
        } else if (this.b != 2 && i2 < 0) {
            a(v);
        }
    }

    /* access modifiers changed from: protected */
    public void a(V v) {
        if (this.c != null) {
            this.c.cancel();
            v.clearAnimation();
        }
        this.b = 2;
        a(v, 0, 225, a.d);
    }

    /* access modifiers changed from: protected */
    public void b(V v) {
        if (this.c != null) {
            this.c.cancel();
            v.clearAnimation();
        }
        this.b = 1;
        a(v, this.a, 175, a.c);
    }

    private void a(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.c = v.animate().translationY((float) i).setInterpolator(timeInterpolator).setDuration(j).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                HideBottomViewOnScrollBehavior.this.c = null;
            }
        });
    }
}
