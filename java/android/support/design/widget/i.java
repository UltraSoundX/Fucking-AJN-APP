package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import java.util.ArrayList;

/* compiled from: FloatingActionButtonImplLollipop */
class i extends h {
    private InsetDrawable x;

    /* compiled from: FloatingActionButtonImplLollipop */
    static class a extends GradientDrawable {
        a() {
        }

        public boolean isStateful() {
            return true;
        }
    }

    i(VisibilityAwareImageButton visibilityAwareImageButton, m mVar) {
        super(visibilityAwareImageButton, mVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList, Mode mode, ColorStateList colorStateList2, int i) {
        Drawable drawable;
        this.g = DrawableCompat.wrap(p());
        DrawableCompat.setTintList(this.g, colorStateList);
        if (mode != null) {
            DrawableCompat.setTintMode(this.g, mode);
        }
        if (i > 0) {
            this.i = a(i, colorStateList);
            drawable = new LayerDrawable(new Drawable[]{this.i, this.g});
        } else {
            this.i = null;
            drawable = this.g;
        }
        this.h = new RippleDrawable(android.support.design.f.a.a(colorStateList2), drawable, null);
        this.j = this.h;
        this.w.a(this.h);
    }

    /* access modifiers changed from: 0000 */
    public void b(ColorStateList colorStateList) {
        if (this.h instanceof RippleDrawable) {
            ((RippleDrawable) this.h).setColor(android.support.design.f.a.a(colorStateList));
        } else {
            super.b(colorStateList);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(float f, float f2, float f3) {
        if (VERSION.SDK_INT == 21) {
            this.v.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(p, a(f, f3));
            stateListAnimator.addState(f328q, a(f, f2));
            stateListAnimator.addState(r, a(f, f2));
            stateListAnimator.addState(s, a(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.v, "elevation", new float[]{f}).setDuration(0));
            if (VERSION.SDK_INT >= 22 && VERSION.SDK_INT <= 24) {
                arrayList.add(ObjectAnimator.ofFloat(this.v, View.TRANSLATION_Z, new float[]{this.v.getTranslationZ()}).setDuration(100));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.v, View.TRANSLATION_Z, new float[]{0.0f}).setDuration(100));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(a);
            stateListAnimator.addState(t, animatorSet);
            stateListAnimator.addState(u, a(0.0f, 0.0f));
            this.v.setStateListAnimator(stateListAnimator);
        }
        if (this.w.b()) {
            j();
        }
    }

    private Animator a(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.v, "elevation", new float[]{f}).setDuration(0)).with(ObjectAnimator.ofFloat(this.v, View.TRANSLATION_Z, new float[]{f2}).setDuration(100));
        animatorSet.setInterpolator(a);
        return animatorSet;
    }

    public float a() {
        return this.v.getElevation();
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        j();
    }

    /* access modifiers changed from: 0000 */
    public void b(Rect rect) {
        if (this.w.b()) {
            this.x = new InsetDrawable(this.h, rect.left, rect.top, rect.right, rect.bottom);
            this.w.a(this.x);
            return;
        }
        this.w.a(this.h);
    }

    /* access modifiers changed from: 0000 */
    public void a(int[] iArr) {
        if (VERSION.SDK_INT != 21) {
            return;
        }
        if (this.v.isEnabled()) {
            this.v.setElevation(this.k);
            if (this.v.isPressed()) {
                this.v.setTranslationZ(this.m);
            } else if (this.v.isFocused() || this.v.isHovered()) {
                this.v.setTranslationZ(this.l);
            } else {
                this.v.setTranslationZ(0.0f);
            }
        } else {
            this.v.setElevation(0.0f);
            this.v.setTranslationZ(0.0f);
        }
    }

    /* access modifiers changed from: 0000 */
    public void g() {
    }

    /* access modifiers changed from: 0000 */
    public boolean m() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public b n() {
        return new c();
    }

    /* access modifiers changed from: 0000 */
    public GradientDrawable q() {
        return new a();
    }

    /* access modifiers changed from: 0000 */
    public void a(Rect rect) {
        if (this.w.b()) {
            float a2 = this.w.a();
            float a3 = a() + this.m;
            int ceil = (int) Math.ceil((double) l.b(a3, a2, false));
            int ceil2 = (int) Math.ceil((double) l.a(a3, a2, false));
            rect.set(ceil, ceil2, ceil, ceil2);
            return;
        }
        rect.set(0, 0, 0, 0);
    }
}
