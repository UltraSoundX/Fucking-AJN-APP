package me.yokeyword.fragmentation.helper.internal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import me.yokeyword.fragmentation.R;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/* compiled from: AnimatorHelper */
public final class a {
    public Animation a;
    public Animation b;
    public Animation c;
    public Animation d;
    private Animation e;
    private Animation f;
    private Context g;
    private FragmentAnimator h;

    public a(Context context, FragmentAnimator fragmentAnimator) {
        this.g = context;
        a(fragmentAnimator);
    }

    public void a(FragmentAnimator fragmentAnimator) {
        this.h = fragmentAnimator;
        c();
        d();
        e();
        f();
    }

    public Animation a() {
        if (this.e == null) {
            this.e = AnimationUtils.loadAnimation(this.g, R.anim.no_anim);
        }
        return this.e;
    }

    public Animation b() {
        if (this.f == null) {
            this.f = new Animation() {
            };
        }
        return this.f;
    }

    public Animation a(Fragment fragment) {
        if ((fragment.getTag() == null || !fragment.getTag().startsWith("android:switcher:") || !fragment.getUserVisibleHint()) && (fragment.getParentFragment() == null || !fragment.getParentFragment().isRemoving() || fragment.isHidden())) {
            return null;
        }
        AnonymousClass2 r0 = new Animation() {
        };
        r0.setDuration(this.b.getDuration());
        return r0;
    }

    private Animation c() {
        if (this.h.b() == 0) {
            this.a = AnimationUtils.loadAnimation(this.g, R.anim.no_anim);
        } else {
            this.a = AnimationUtils.loadAnimation(this.g, this.h.b());
        }
        return this.a;
    }

    private Animation d() {
        if (this.h.c() == 0) {
            this.b = AnimationUtils.loadAnimation(this.g, R.anim.no_anim);
        } else {
            this.b = AnimationUtils.loadAnimation(this.g, this.h.c());
        }
        return this.b;
    }

    private Animation e() {
        if (this.h.d() == 0) {
            this.c = AnimationUtils.loadAnimation(this.g, R.anim.no_anim);
        } else {
            this.c = AnimationUtils.loadAnimation(this.g, this.h.d());
        }
        return this.c;
    }

    private Animation f() {
        if (this.h.e() == 0) {
            this.d = AnimationUtils.loadAnimation(this.g, R.anim.no_anim);
        } else {
            this.d = AnimationUtils.loadAnimation(this.g, this.h.e());
        }
        return this.d;
    }
}
