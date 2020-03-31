package android.support.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* compiled from: CircularPropagation */
public class b extends am {
    private float a = 3.0f;

    public long a(ViewGroup viewGroup, Transition transition, v vVar, v vVar2) {
        int i;
        int round;
        int round2;
        if (vVar == null && vVar2 == null) {
            return 0;
        }
        if (vVar2 == null || b(vVar) == 0) {
            i = -1;
            vVar2 = vVar;
        } else {
            i = 1;
        }
        int c = c(vVar2);
        int d = d(vVar2);
        Rect n = transition.n();
        if (n != null) {
            round = n.centerX();
            round2 = n.centerY();
        } else {
            int[] iArr = new int[2];
            viewGroup.getLocationOnScreen(iArr);
            round = Math.round(((float) (iArr[0] + (viewGroup.getWidth() / 2))) + viewGroup.getTranslationX());
            round2 = Math.round(((float) (iArr[1] + (viewGroup.getHeight() / 2))) + viewGroup.getTranslationY());
        }
        float a2 = a((float) c, (float) d, (float) round, (float) round2) / a(0.0f, 0.0f, (float) viewGroup.getWidth(), (float) viewGroup.getHeight());
        long b = transition.b();
        if (b < 0) {
            b = 300;
        }
        return (long) Math.round((((float) (b * ((long) i))) / this.a) * a2);
    }

    private static float a(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }
}
