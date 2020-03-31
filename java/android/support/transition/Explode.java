package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Explode extends Visibility {
    private static final TimeInterpolator a = new DecelerateInterpolator();
    private static final TimeInterpolator i = new AccelerateInterpolator();
    private int[] j = new int[2];

    public Explode() {
        a((t) new b());
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a((t) new b());
    }

    private void d(v vVar) {
        View view = vVar.b;
        view.getLocationOnScreen(this.j);
        int i2 = this.j[0];
        int i3 = this.j[1];
        vVar.a.put("android:explode:screenBounds", new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3));
    }

    public void a(v vVar) {
        super.a(vVar);
        d(vVar);
    }

    public void b(v vVar) {
        super.b(vVar);
        d(vVar);
    }

    public Animator a(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        if (vVar2 == null) {
            return null;
        }
        Rect rect = (Rect) vVar2.a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        a((View) viewGroup, rect, this.j);
        return x.a(view, vVar2, rect.left, rect.top, translationX + ((float) this.j[0]), translationY + ((float) this.j[1]), translationX, translationY, a);
    }

    public Animator b(ViewGroup viewGroup, View view, v vVar, v vVar2) {
        float f;
        float f2;
        if (vVar == null) {
            return null;
        }
        Rect rect = (Rect) vVar.a.get("android:explode:screenBounds");
        int i2 = rect.left;
        int i3 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) vVar.b.getTag(R.id.transition_position);
        if (iArr != null) {
            float f3 = translationX + ((float) (iArr[0] - rect.left));
            float f4 = ((float) (iArr[1] - rect.top)) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
            f = f4;
            f2 = f3;
        } else {
            f = translationY;
            f2 = translationX;
        }
        a((View) viewGroup, rect, this.j);
        return x.a(view, vVar, i2, i3, translationX, translationY, f2 + ((float) this.j[0]), f + ((float) this.j[1]), i);
    }

    private void a(View view, Rect rect, int[] iArr) {
        int centerX;
        int centerY;
        view.getLocationOnScreen(this.j);
        int i2 = this.j[0];
        int i3 = this.j[1];
        Rect n = n();
        if (n == null) {
            centerX = Math.round(view.getTranslationX()) + (view.getWidth() / 2) + i2;
            centerY = (view.getHeight() / 2) + i3 + Math.round(view.getTranslationY());
        } else {
            centerX = n.centerX();
            centerY = n.centerY();
        }
        float centerX2 = (float) (rect.centerX() - centerX);
        float centerY2 = (float) (rect.centerY() - centerY);
        if (centerX2 == 0.0f && centerY2 == 0.0f) {
            centerX2 = ((float) (Math.random() * 2.0d)) - 1.0f;
            centerY2 = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float a2 = a(centerX2, centerY2);
        float f = centerX2 / a2;
        float f2 = centerY2 / a2;
        float a3 = a(view, centerX - i2, centerY - i3);
        iArr[0] = Math.round(f * a3);
        iArr[1] = Math.round(a3 * f2);
    }

    private static float a(View view, int i2, int i3) {
        return a((float) Math.max(i2, view.getWidth() - i2), (float) Math.max(i3, view.getHeight() - i3));
    }

    private static float a(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }
}
