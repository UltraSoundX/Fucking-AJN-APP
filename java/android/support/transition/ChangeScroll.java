package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeScroll extends Transition {
    private static final String[] a = {"android:changeScroll:x", "android:changeScroll:y"};

    public ChangeScroll() {
    }

    public ChangeScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(v vVar) {
        d(vVar);
    }

    public void b(v vVar) {
        d(vVar);
    }

    public String[] a() {
        return a;
    }

    private void d(v vVar) {
        vVar.a.put("android:changeScroll:x", Integer.valueOf(vVar.b.getScrollX()));
        vVar.a.put("android:changeScroll:y", Integer.valueOf(vVar.b.getScrollY()));
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        Animator animator;
        ObjectAnimator objectAnimator;
        if (vVar == null || vVar2 == null) {
            return null;
        }
        View view = vVar2.b;
        int intValue = ((Integer) vVar.a.get("android:changeScroll:x")).intValue();
        int intValue2 = ((Integer) vVar2.a.get("android:changeScroll:x")).intValue();
        int intValue3 = ((Integer) vVar.a.get("android:changeScroll:y")).intValue();
        int intValue4 = ((Integer) vVar2.a.get("android:changeScroll:y")).intValue();
        if (intValue != intValue2) {
            view.setScrollX(intValue);
            animator = ObjectAnimator.ofInt(view, "scrollX", new int[]{intValue, intValue2});
        } else {
            animator = null;
        }
        if (intValue3 != intValue4) {
            view.setScrollY(intValue3);
            objectAnimator = ObjectAnimator.ofInt(view, "scrollY", new int[]{intValue3, intValue4});
        } else {
            objectAnimator = null;
        }
        return u.a(animator, objectAnimator);
    }
}
