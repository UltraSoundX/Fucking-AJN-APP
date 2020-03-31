package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ViewPropertyAnimatorCompatSet */
public class h {
    final ArrayList<ViewPropertyAnimatorCompat> a = new ArrayList<>();
    ViewPropertyAnimatorListener b;
    private long c = -1;
    private Interpolator d;
    private boolean e;
    private final ViewPropertyAnimatorListenerAdapter f = new ViewPropertyAnimatorListenerAdapter() {
        private boolean b = false;
        private int c = 0;

        public void onAnimationStart(View view) {
            if (!this.b) {
                this.b = true;
                if (h.this.b != null) {
                    h.this.b.onAnimationStart(null);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.c = 0;
            this.b = false;
            h.this.b();
        }

        public void onAnimationEnd(View view) {
            int i = this.c + 1;
            this.c = i;
            if (i == h.this.a.size()) {
                if (h.this.b != null) {
                    h.this.b.onAnimationEnd(null);
                }
                a();
            }
        }
    };

    public h a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.e) {
            this.a.add(viewPropertyAnimatorCompat);
        }
        return this;
    }

    public h a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        this.a.add(viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompat2.setStartDelay(viewPropertyAnimatorCompat.getDuration());
        this.a.add(viewPropertyAnimatorCompat2);
        return this;
    }

    public void a() {
        if (!this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) it.next();
                if (this.c >= 0) {
                    viewPropertyAnimatorCompat.setDuration(this.c);
                }
                if (this.d != null) {
                    viewPropertyAnimatorCompat.setInterpolator(this.d);
                }
                if (this.b != null) {
                    viewPropertyAnimatorCompat.setListener(this.f);
                }
                viewPropertyAnimatorCompat.start();
            }
            this.e = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.e = false;
    }

    public void c() {
        if (this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((ViewPropertyAnimatorCompat) it.next()).cancel();
            }
            this.e = false;
        }
    }

    public h a(long j) {
        if (!this.e) {
            this.c = j;
        }
        return this;
    }

    public h a(Interpolator interpolator) {
        if (!this.e) {
            this.d = interpolator;
        }
        return this;
    }

    public h a(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.e) {
            this.b = viewPropertyAnimatorListener;
        }
        return this;
    }
}
