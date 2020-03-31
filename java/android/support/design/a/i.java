package android.support.design.a;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* compiled from: MotionTiming */
public class i {
    private long a = 0;
    private long b = 300;
    private TimeInterpolator c = null;
    private int d = 0;
    private int e = 1;

    public i(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public i(long j, long j2, TimeInterpolator timeInterpolator) {
        this.a = j;
        this.b = j2;
        this.c = timeInterpolator;
    }

    public void a(Animator animator) {
        animator.setStartDelay(a());
        animator.setDuration(b());
        animator.setInterpolator(c());
        if (animator instanceof ValueAnimator) {
            ((ValueAnimator) animator).setRepeatCount(d());
            ((ValueAnimator) animator).setRepeatMode(e());
        }
    }

    public long a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public TimeInterpolator c() {
        return this.c != null ? this.c : a.b;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    static i a(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), b(valueAnimator));
        iVar.d = valueAnimator.getRepeatCount();
        iVar.e = valueAnimator.getRepeatMode();
        return iVar;
    }

    private static TimeInterpolator b(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
            return a.b;
        }
        if (interpolator instanceof AccelerateInterpolator) {
            return a.c;
        }
        if (interpolator instanceof DecelerateInterpolator) {
            return a.d;
        }
        return interpolator;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        if (a() == iVar.a() && b() == iVar.b() && d() == iVar.d() && e() == iVar.e()) {
            return c().getClass().equals(iVar.c().getClass());
        }
        return false;
    }

    public int hashCode() {
        return (((((((((int) (a() ^ (a() >>> 32))) * 31) + ((int) (b() ^ (b() >>> 32)))) * 31) + c().getClass().hashCode()) * 31) + d()) * 31) + e();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(10);
        sb.append(getClass().getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" delay: ");
        sb.append(a());
        sb.append(" duration: ");
        sb.append(b());
        sb.append(" interpolator: ");
        sb.append(c().getClass());
        sb.append(" repeatCount: ");
        sb.append(d());
        sb.append(" repeatMode: ");
        sb.append(e());
        sb.append("}\n");
        return sb.toString();
    }
}
