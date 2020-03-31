package me.yokeyword.fragmentation;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentationMagician;
import android.view.MotionEvent;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.b.a;
import me.yokeyword.fragmentation.debug.b;

/* compiled from: SupportActivityDelegate */
public class e {
    boolean a = false;
    boolean b = true;
    /* access modifiers changed from: private */
    public c c;
    private FragmentActivity d;
    /* access modifiers changed from: private */
    public h e;
    private FragmentAnimator f;
    private int g = 0;
    private b h;

    public e(c cVar) {
        if (!(cVar instanceof FragmentActivity)) {
            throw new RuntimeException("Must extends FragmentActivity/AppCompatActivity");
        }
        this.c = cVar;
        this.d = (FragmentActivity) cVar;
    }

    public a a() {
        return new C0085a((FragmentActivity) this.c, k(), b(), true);
    }

    public void a(Bundle bundle) {
        this.e = b();
        this.h = new b(this.d);
        this.f = this.c.onCreateFragmentAnimator();
        this.h.a(b.a().c());
    }

    public h b() {
        if (this.e == null) {
            this.e = new h(this.c);
        }
        return this.e;
    }

    public void b(Bundle bundle) {
        this.h.b(b.a().c());
    }

    public FragmentAnimator c() {
        return this.f.a();
    }

    public void a(FragmentAnimator fragmentAnimator) {
        this.f = fragmentAnimator;
        for (Fragment fragment : FragmentationMagician.getActiveFragments(j())) {
            if (fragment instanceof d) {
                f b2 = ((d) fragment).b();
                if (b2.h) {
                    b2.a = fragmentAnimator.a();
                    if (b2.b != null) {
                        b2.b.a(b2.a);
                    }
                }
            }
        }
    }

    public FragmentAnimator d() {
        return new DefaultVerticalAnimator();
    }

    public void a(int i) {
        this.g = i;
    }

    public int e() {
        return this.g;
    }

    public void a(Runnable runnable) {
        this.e.a(runnable);
    }

    public void f() {
        this.e.a.a((a) new a(3) {
            public void a() {
                if (!e.this.b) {
                    e.this.b = true;
                }
                if (!e.this.e.a(g.b(e.this.j()))) {
                    e.this.c.onBackPressedSupport();
                }
            }
        });
    }

    public void g() {
        if (j().getBackStackEntryCount() > 1) {
            i();
        } else {
            ActivityCompat.finishAfterTransition(this.d);
        }
    }

    public void h() {
        this.h.a();
    }

    public boolean a(MotionEvent motionEvent) {
        return !this.b;
    }

    public void a(int i, d dVar) {
        a(i, dVar, true, false);
    }

    public void a(int i, d dVar, boolean z, boolean z2) {
        this.e.a(j(), i, dVar, z, z2);
    }

    public void a(int i, int i2, d... dVarArr) {
        this.e.a(j(), i, i2, dVarArr);
    }

    public void a(d dVar) {
        a(dVar, (d) null);
    }

    public void a(d dVar, d dVar2) {
        this.e.a(j(), dVar, dVar2);
    }

    public void b(d dVar) {
        a(dVar, 0);
    }

    public void a(d dVar, int i) {
        this.e.a(j(), k(), dVar, 0, i, 0);
    }

    public void b(d dVar, int i) {
        this.e.a(j(), k(), dVar, i, 0, 1);
    }

    public void c(d dVar) {
        this.e.b(j(), k(), dVar);
    }

    public void a(d dVar, Class<?> cls, boolean z) {
        this.e.a(j(), k(), dVar, cls.getName(), z);
    }

    public void a(d dVar, boolean z) {
        this.e.a(j(), k(), dVar, 0, 0, z ? 10 : 11);
    }

    public void i() {
        this.e.a(j());
    }

    public void a(Class<?> cls, boolean z) {
        a(cls, z, (Runnable) null);
    }

    public void a(Class<?> cls, boolean z, Runnable runnable) {
        a(cls, z, runnable, Integer.MAX_VALUE);
    }

    public void a(Class<?> cls, boolean z, Runnable runnable, int i) {
        this.e.a(cls.getName(), z, runnable, j(), i);
    }

    /* access modifiers changed from: private */
    public FragmentManager j() {
        return this.d.getSupportFragmentManager();
    }

    private d k() {
        return g.a(j());
    }
}
