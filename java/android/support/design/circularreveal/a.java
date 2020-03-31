package android.support.design.circularreveal;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build.VERSION;
import android.support.design.circularreveal.c.b;
import android.support.design.circularreveal.c.d;
import android.view.View;
import android.view.ViewAnimationUtils;

/* compiled from: CircularRevealCompat */
public final class a {
    public static Animator a(c cVar, float f, float f2, float f3) {
        ObjectAnimator ofObject = ObjectAnimator.ofObject(cVar, b.a, android.support.design.circularreveal.c.a.a, new d[]{new d(f, f2, f3)});
        if (VERSION.SDK_INT < 21) {
            return ofObject;
        }
        d revealInfo = cVar.getRevealInfo();
        if (revealInfo == null) {
            throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
        }
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal((View) cVar, (int) f, (int) f2, revealInfo.c, f3);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofObject, createCircularReveal});
        return animatorSet;
    }

    public static AnimatorListener a(final c cVar) {
        return new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                cVar.a();
            }

            public void onAnimationEnd(Animator animator) {
                cVar.b();
            }
        };
    }
}
