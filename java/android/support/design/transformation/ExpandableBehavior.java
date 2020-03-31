package android.support.design.transformation;

import android.content.Context;
import android.support.design.d.b;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.List;

public abstract class ExpandableBehavior extends Behavior<View> {
    /* access modifiers changed from: private */
    public int a = 0;

    public abstract boolean a(CoordinatorLayout coordinatorLayout, View view, View view2);

    /* access modifiers changed from: protected */
    public abstract boolean a(View view, View view2, boolean z, boolean z2);

    public ExpandableBehavior() {
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean a(CoordinatorLayout coordinatorLayout, final View view, int i) {
        if (!ViewCompat.isLaidOut(view)) {
            final b a2 = a(coordinatorLayout, view);
            if (a2 != null && a(a2.a())) {
                this.a = a2.a() ? 1 : 2;
                final int i2 = this.a;
                view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                        if (ExpandableBehavior.this.a == i2) {
                            ExpandableBehavior.this.a((View) a2, view, a2.a(), false);
                        }
                        return false;
                    }
                });
            }
        }
        return false;
    }

    public boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
        b bVar = (b) view2;
        if (!a(bVar.a())) {
            return false;
        }
        this.a = bVar.a() ? 1 : 2;
        return a((View) bVar, view, bVar.a(), true);
    }

    /* access modifiers changed from: protected */
    public b a(CoordinatorLayout coordinatorLayout, View view) {
        List c = coordinatorLayout.c(view);
        int size = c.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) c.get(i);
            if (a(coordinatorLayout, view, view2)) {
                return (b) view2;
            }
        }
        return null;
    }

    private boolean a(boolean z) {
        boolean z2 = true;
        if (!z) {
            if (this.a != 1) {
                z2 = false;
            }
            return z2;
        } else if (this.a == 0 || this.a == 2) {
            return true;
        } else {
            return false;
        }
    }
}
