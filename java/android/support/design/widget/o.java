package android.support.design.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* compiled from: StateListAnimator */
public final class o {
    ValueAnimator a = null;
    private final ArrayList<a> b = new ArrayList<>();
    private a c = null;
    private final AnimatorListener d = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            if (o.this.a == animator) {
                o.this.a = null;
            }
        }
    };

    /* compiled from: StateListAnimator */
    static class a {
        final int[] a;
        final ValueAnimator b;

        a(int[] iArr, ValueAnimator valueAnimator) {
            this.a = iArr;
            this.b = valueAnimator;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        a aVar = new a(iArr, valueAnimator);
        valueAnimator.addListener(this.d);
        this.b.add(aVar);
    }

    public void a(int[] iArr) {
        a aVar;
        int size = this.b.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                aVar = null;
                break;
            }
            aVar = (a) this.b.get(i);
            if (StateSet.stateSetMatches(aVar.a, iArr)) {
                break;
            }
            i++;
        }
        if (aVar != this.c) {
            if (this.c != null) {
                b();
            }
            this.c = aVar;
            if (aVar != null) {
                a(aVar);
            }
        }
    }

    private void a(a aVar) {
        this.a = aVar.b;
        this.a.start();
    }

    private void b() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.end();
            this.a = null;
        }
    }
}
