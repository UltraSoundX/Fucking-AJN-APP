package me.yokeyword.fragmentation;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentationMagician;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.stub.StubApp;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.internal.b;
import me.yokeyword.fragmentation.helper.internal.c;

/* compiled from: SupportFragmentDelegate */
public class f {
    FragmentAnimator a;
    me.yokeyword.fragmentation.helper.internal.a b;
    boolean c;
    int d;
    b e;
    Bundle f;
    protected FragmentActivity g;
    boolean h = true;
    a i;
    private int j = 0;
    private boolean k;
    private int l = ExploreByTouchHelper.INVALID_ID;
    private int m = ExploreByTouchHelper.INVALID_ID;
    private int n = ExploreByTouchHelper.INVALID_ID;
    /* access modifiers changed from: private */
    public Handler o;
    private boolean p = true;

    /* renamed from: q reason: collision with root package name */
    private boolean f441q;
    private boolean r = true;
    private h s;
    private c t;
    /* access modifiers changed from: private */
    public Bundle u;
    /* access modifiers changed from: private */
    public d v;
    /* access modifiers changed from: private */
    public Fragment w;
    /* access modifiers changed from: private */
    public c x;
    /* access modifiers changed from: private */
    public boolean y;
    private Runnable z = new Runnable() {
        public void run() {
            if (f.this.w != null) {
                f.this.v.a(f.this.u);
                if (!f.this.y) {
                    final View view = f.this.w.getView();
                    if (view != null) {
                        d a2 = g.a(f.this.w);
                        if (a2 != null) {
                            f.this.o.postDelayed(new Runnable() {
                                public void run() {
                                    view.setClickable(false);
                                }
                            }, a2.b().x() - f.this.w());
                        }
                    }
                }
            }
        }
    };

    /* compiled from: SupportFragmentDelegate */
    interface a {
        void a();
    }

    public f(d dVar) {
        if (!(dVar instanceof Fragment)) {
            throw new RuntimeException("Must extends Fragment");
        }
        this.v = dVar;
        this.w = (Fragment) dVar;
    }

    public void a(Activity activity) {
        if (activity instanceof c) {
            this.x = (c) activity;
            this.g = (FragmentActivity) activity;
            this.s = this.x.getSupportDelegate().b();
            return;
        }
        throw new RuntimeException(activity.getClass().getSimpleName() + " must impl ISupportActivity!");
    }

    public void a(Bundle bundle) {
        m().a(bundle);
        Bundle arguments = this.w.getArguments();
        if (arguments != null) {
            this.j = arguments.getInt("fragmentation_arg_root_status", 0);
            this.k = arguments.getBoolean("fragmentation_arg_is_shared_element", false);
            this.d = arguments.getInt("fragmentation_arg_container");
            this.f441q = arguments.getBoolean("fragmentation_arg_replace", false);
            this.l = arguments.getInt("fragmentation_arg_custom_enter_anim", ExploreByTouchHelper.INVALID_ID);
            this.m = arguments.getInt("fragmentation_arg_custom_exit_anim", ExploreByTouchHelper.INVALID_ID);
            this.n = arguments.getInt("fragmentation_arg_custom_pop_exit_anim", ExploreByTouchHelper.INVALID_ID);
        }
        if (bundle == null) {
            i();
        } else {
            this.u = bundle;
            this.a = (FragmentAnimator) bundle.getParcelable("fragmentation_state_save_animator");
            this.r = bundle.getBoolean("fragmentation_state_save_status");
            this.d = bundle.getInt("fragmentation_arg_container");
            if (this.j != 0) {
                FragmentationMagician.reorderIndices(this.w.getFragmentManager());
            }
        }
        g(bundle);
        this.b = new me.yokeyword.fragmentation.helper.internal.a(StubApp.getOrigApplicationContext(this.g.getApplicationContext()), this.a);
        final Animation v2 = v();
        if (v2 != null) {
            v().setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    f.this.x.getSupportDelegate().b = false;
                    f.this.o.postDelayed(new Runnable() {
                        public void run() {
                            f.this.x.getSupportDelegate().b = true;
                        }
                    }, v2.getDuration());
                }

                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    public Animation a(int i2, boolean z2, int i3) {
        if (this.x.getSupportDelegate().a || this.c) {
            if (i2 != 8194 || !z2) {
                return this.b.a();
            }
            return this.b.b();
        } else if (i2 == 4097) {
            if (!z2) {
                return this.b.d;
            }
            if (this.j == 1) {
                return this.b.a();
            }
            Animation animation = this.b.a;
            a(animation);
            return animation;
        } else if (i2 == 8194) {
            return z2 ? this.b.c : this.b.b;
        } else {
            if (this.k && z2) {
                r();
            }
            if (!z2) {
                return this.b.a(this.w);
            }
            return null;
        }
    }

    public void b(Bundle bundle) {
        m().b(bundle);
        bundle.putParcelable("fragmentation_state_save_animator", this.a);
        bundle.putBoolean("fragmentation_state_save_status", this.w.isHidden());
        bundle.putInt("fragmentation_arg_container", this.d);
    }

    public void c(Bundle bundle) {
        m().c(bundle);
        View view = this.w.getView();
        if (view != null) {
            this.y = view.isClickable();
            view.setClickable(true);
            a(view);
        }
        if (bundle != null || this.j == 1 || ((this.w.getTag() != null && this.w.getTag().startsWith("android:switcher:")) || (this.f441q && !this.p))) {
            t();
        }
        if (this.p) {
            this.p = false;
        }
    }

    public void a() {
        m().a();
    }

    public void b() {
        m().b();
    }

    public void c() {
        this.x.getSupportDelegate().b = true;
        m().c();
        u().removeCallbacks(this.z);
    }

    public void d() {
        this.s.a(this.w);
    }

    public void a(boolean z2) {
        m().a(z2);
    }

    public void b(boolean z2) {
        m().b(z2);
    }

    public void d(Bundle bundle) {
    }

    public void e(Bundle bundle) {
    }

    public void e() {
    }

    public void f() {
    }

    public final boolean g() {
        return m().d();
    }

    public FragmentAnimator h() {
        return this.x.getFragmentAnimator();
    }

    public FragmentAnimator i() {
        if (this.x == null) {
            throw new RuntimeException("Fragment has not been attached to Activity!");
        }
        if (this.a == null) {
            this.a = this.v.f();
            if (this.a == null) {
                this.a = this.x.getFragmentAnimator();
            }
        }
        return this.a;
    }

    public void a(int i2, int i3, Bundle bundle) {
    }

    public void f(Bundle bundle) {
    }

    public boolean j() {
        return false;
    }

    public void a(int i2, d dVar) {
        a(i2, dVar, true, false);
    }

    public void a(int i2, d dVar, boolean z2, boolean z3) {
        this.s.a(q(), i2, dVar, z2, z3);
    }

    public void a(int i2, int i3, d... dVarArr) {
        this.s.a(q(), i2, i3, dVarArr);
    }

    public void a(d dVar, d dVar2) {
        this.s.a(q(), dVar, dVar2);
    }

    public void a(d dVar) {
        a(dVar, 0);
    }

    public void a(d dVar, int i2) {
        this.s.a(this.w.getFragmentManager(), this.v, dVar, 0, i2, 0);
    }

    public void k() {
        this.s.a(q());
    }

    public void a(Class<?> cls, boolean z2) {
        a(cls, z2, (Runnable) null);
    }

    public void a(Class<?> cls, boolean z2, Runnable runnable) {
        a(cls, z2, runnable, Integer.MAX_VALUE);
    }

    public void a(Class<?> cls, boolean z2, Runnable runnable, int i2) {
        this.s.a(cls.getName(), z2, runnable, q(), i2);
    }

    public void l() {
        this.s.b(this.w.getFragmentManager());
    }

    private FragmentManager q() {
        return this.w.getChildFragmentManager();
    }

    private void g(Bundle bundle) {
        if (bundle != null) {
            FragmentTransaction beginTransaction = this.w.getFragmentManager().beginTransaction();
            if (this.r) {
                beginTransaction.hide(this.w);
            } else {
                beginTransaction.show(this.w);
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void a(Animation animation) {
        u().postDelayed(this.z, animation.getDuration());
        this.x.getSupportDelegate().b = true;
        if (this.i != null) {
            u().post(new Runnable() {
                public void run() {
                    f.this.i.a();
                    f.this.i = null;
                }
            });
        }
    }

    private void r() {
        t();
    }

    public void a(View view) {
        if ((this.w.getTag() == null || !this.w.getTag().startsWith("android:switcher:")) && this.j == 0 && view.getBackground() == null) {
            int e2 = this.x.getSupportDelegate().e();
            if (e2 == 0) {
                view.setBackgroundResource(s());
            } else {
                view.setBackgroundResource(e2);
            }
        }
    }

    private int s() {
        TypedArray obtainStyledAttributes = this.g.getTheme().obtainStyledAttributes(new int[]{16842836});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private void t() {
        u().post(this.z);
        this.x.getSupportDelegate().b = true;
    }

    private Handler u() {
        if (this.o == null) {
            this.o = new Handler(Looper.getMainLooper());
        }
        return this.o;
    }

    public c m() {
        if (this.t == null) {
            this.t = new c(this.v);
        }
        return this.t;
    }

    public FragmentActivity n() {
        return this.g;
    }

    private Animation v() {
        if (this.l != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this.g, this.l);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (!(this.b == null || this.b.a == null)) {
            return this.b.a;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public long w() {
        Animation v2 = v();
        if (v2 != null) {
            return v2.getDuration();
        }
        return 300;
    }

    public long o() {
        if (this.m != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this.g, this.m).getDuration();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (!(this.b == null || this.b.b == null)) {
            return this.b.b.getDuration();
        }
        return 300;
    }

    /* access modifiers changed from: private */
    public long x() {
        if (this.n != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this.g, this.n).getDuration();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (!(this.b == null || this.b.d == null)) {
            return this.b.d.getDuration();
        }
        return 300;
    }

    /* access modifiers changed from: 0000 */
    public Animation p() {
        if (this.m != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this.g, this.m);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (!(this.b == null || this.b.b == null)) {
            return this.b.b;
        }
        return null;
    }
}
