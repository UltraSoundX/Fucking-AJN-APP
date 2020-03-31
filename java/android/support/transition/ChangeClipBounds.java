package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeClipBounds extends Transition {
    private static final String[] a = {"android:clipBounds:clip"};

    public String[] a() {
        return a;
    }

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void d(v vVar) {
        View view = vVar.b;
        if (view.getVisibility() != 8) {
            Rect clipBounds = ViewCompat.getClipBounds(view);
            vVar.a.put("android:clipBounds:clip", clipBounds);
            if (clipBounds == null) {
                vVar.a.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void a(v vVar) {
        d(vVar);
    }

    public void b(v vVar) {
        d(vVar);
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        boolean z;
        if (vVar == null || vVar2 == null || !vVar.a.containsKey("android:clipBounds:clip") || !vVar2.a.containsKey("android:clipBounds:clip")) {
            return null;
        }
        Rect rect = (Rect) vVar.a.get("android:clipBounds:clip");
        Rect rect2 = (Rect) vVar2.a.get("android:clipBounds:clip");
        if (rect2 == null) {
            z = true;
        } else {
            z = false;
        }
        if (rect == null && rect2 == null) {
            return null;
        }
        if (rect == null) {
            rect = (Rect) vVar.a.get("android:clipBounds:bounds");
        } else if (rect2 == null) {
            rect2 = (Rect) vVar2.a.get("android:clipBounds:bounds");
        }
        if (rect.equals(rect2)) {
            return null;
        }
        ViewCompat.setClipBounds(vVar2.b, rect);
        m mVar = new m(new Rect());
        ObjectAnimator ofObject = ObjectAnimator.ofObject(vVar2.b, ah.b, mVar, new Rect[]{rect, rect2});
        if (!z) {
            return ofObject;
        }
        final View view = vVar2.b;
        ofObject.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ViewCompat.setClipBounds(view, null);
            }
        });
        return ofObject;
    }
}
