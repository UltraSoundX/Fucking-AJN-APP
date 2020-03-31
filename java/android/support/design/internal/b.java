package android.support.design.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.transition.Transition;
import android.support.transition.v;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

/* compiled from: TextScale */
public class b extends Transition {
    public void a(v vVar) {
        d(vVar);
    }

    public void b(v vVar) {
        d(vVar);
    }

    private void d(v vVar) {
        if (vVar.b instanceof TextView) {
            vVar.a.put("android:textscale:scale", Float.valueOf(((TextView) vVar.b).getScaleX()));
        }
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        float f;
        float f2 = 1.0f;
        if (vVar == null || vVar2 == null || !(vVar.b instanceof TextView) || !(vVar2.b instanceof TextView)) {
            return null;
        }
        final TextView textView = (TextView) vVar2.b;
        Map<String, Object> map = vVar.a;
        Map<String, Object> map2 = vVar2.a;
        if (map.get("android:textscale:scale") != null) {
            f = ((Float) map.get("android:textscale:scale")).floatValue();
        } else {
            f = 1.0f;
        }
        if (map2.get("android:textscale:scale") != null) {
            f2 = ((Float) map2.get("android:textscale:scale")).floatValue();
        }
        if (f == f2) {
            return null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                textView.setScaleX(floatValue);
                textView.setScaleY(floatValue);
            }
        });
        return ofFloat;
    }
}
