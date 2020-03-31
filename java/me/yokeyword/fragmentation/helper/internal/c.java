package me.yokeyword.fragmentation.helper.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentationMagician;
import java.util.List;
import me.yokeyword.fragmentation.d;

/* compiled from: VisibleDelegate */
public class c {
    private boolean a;
    private boolean b = true;
    private boolean c;
    private boolean d = true;
    private boolean e = true;
    private Handler f;
    private Bundle g;
    private d h;
    private Fragment i;

    public c(d dVar) {
        this.h = dVar;
        this.i = (Fragment) dVar;
    }

    public void a(Bundle bundle) {
        if (bundle != null) {
            this.g = bundle;
            this.c = bundle.getBoolean("fragmentation_invisible_when_leave");
            this.e = bundle.getBoolean("fragmentation_compat_replace");
        }
    }

    public void b(Bundle bundle) {
        bundle.putBoolean("fragmentation_invisible_when_leave", this.c);
        bundle.putBoolean("fragmentation_compat_replace", this.e);
    }

    public void c(Bundle bundle) {
        if (this.e || this.i.getTag() == null || !this.i.getTag().startsWith("android:switcher:")) {
            if (this.e) {
                this.e = false;
            }
            if (!this.c && !this.i.isHidden() && this.i.getUserVisibleHint()) {
                if ((this.i.getParentFragment() != null && a(this.i.getParentFragment())) || this.i.getParentFragment() == null) {
                    this.b = false;
                    c(true);
                }
            }
        }
    }

    public void a() {
        if (!this.d && !this.a && !this.c && a(this.i)) {
            this.b = false;
            d(true);
        }
    }

    public void b() {
        if (!this.a || !a(this.i)) {
            this.c = true;
            return;
        }
        this.b = false;
        this.c = false;
        d(false);
    }

    public void a(boolean z) {
        if (!z && !this.i.isResumed()) {
            this.c = false;
        } else if (z) {
            c(false);
        } else {
            e();
        }
    }

    public void c() {
        this.d = true;
    }

    public void b(boolean z) {
        if (!this.i.isResumed() && (this.i.isAdded() || !z)) {
            return;
        }
        if (!this.a && z) {
            c(true);
        } else if (this.a && !z) {
            d(false);
        }
    }

    private void c(boolean z) {
        if (!this.d) {
            d(z);
        } else if (z) {
            e();
        }
    }

    private void e() {
        h().post(new Runnable() {
            public void run() {
                c.this.d(true);
            }
        });
    }

    /* access modifiers changed from: private */
    public void d(boolean z) {
        if (z && f()) {
            return;
        }
        if (this.a == z) {
            this.b = true;
            return;
        }
        this.a = z;
        if (!z) {
            e(false);
            this.h.d();
        } else if (!g()) {
            this.h.c();
            if (this.d) {
                this.d = false;
                this.h.b(this.g);
            }
            e(true);
        }
    }

    private void e(boolean z) {
        if (!this.b) {
            this.b = true;
        } else if (!g()) {
            List<Fragment> activeFragments = FragmentationMagician.getActiveFragments(this.i.getChildFragmentManager());
            if (activeFragments != null) {
                for (Fragment fragment : activeFragments) {
                    if ((fragment instanceof d) && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                        ((d) fragment).b().m().d(z);
                    }
                }
            }
        }
    }

    private boolean f() {
        d dVar = (d) this.i.getParentFragment();
        return dVar != null && !dVar.e();
    }

    private boolean g() {
        boolean z = false;
        if (this.i.isAdded()) {
            return false;
        }
        if (!this.a) {
            z = true;
        }
        this.a = z;
        return true;
    }

    private boolean a(Fragment fragment) {
        return !fragment.isHidden() && fragment.getUserVisibleHint();
    }

    public boolean d() {
        return this.a;
    }

    private Handler h() {
        if (this.f == null) {
            this.f = new Handler(Looper.getMainLooper());
        }
        return this.f;
    }
}
