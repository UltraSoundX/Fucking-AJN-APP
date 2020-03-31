package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.transition.Transition.c;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Fade extends Visibility {

    private static class a extends AnimatorListenerAdapter {
        private final View a;
        private boolean b = false;

        a(View view) {
            this.a = view;
        }

        public void onAnimationStart(Animator animator) {
            if (ViewCompat.hasOverlappingRendering(this.a) && this.a.getLayerType() == 0) {
                this.b = true;
                this.a.setLayerType(2, null);
            }
        }

        public void onAnimationEnd(Animator animator) {
            ah.a(this.a, 1.0f);
            if (this.b) {
                this.a.setLayerType(0, null);
            }
        }
    }

    public Fade(int i) {
        b(i);
    }

    public Fade() {
    }

    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.f);
        b(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, r()));
        obtainStyledAttributes.recycle();
    }

    public void a(v vVar) {
        super.a(vVar);
        vVar.a.put("android:fade:transitionAlpha", Float.valueOf(ah.c(vVar.b)));
    }

    private Animator a(final View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        ah.a(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ah.a, new float[]{f2});
        ofFloat.addListener(new a(view));
        a((c) new q() {
            public void a(Transition transition) {
                ah.a(view, 1.0f);
                ah.e(view);
                transition.b((c) this);
            }
        });
        return ofFloat;
    }

    public Animator a(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        float f = 0.0f;
        float a2 = a(vVar, 0.0f);
        if (a2 != 1.0f) {
            f = a2;
        }
        return a(view, f, 1.0f);
    }

    public Animator b(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        ah.d(view);
        return a(view, a(vVar, 1.0f), 0.0f);
    }

    private static float a(v vVar, float f) {
        if (vVar == null) {
            return f;
        }
        Float f2 = (Float) vVar.a.get("android:fade:transitionAlpha");
        if (f2 != null) {
            return f2.floatValue();
        }
        return f;
    }
}
