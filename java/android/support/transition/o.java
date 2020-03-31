package android.support.transition;

import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: SidePropagation */
public class o extends am {
    private float a = 3.0f;
    private int b = 80;

    public void a(int i) {
        this.b = i;
    }

    public long a(ViewGroup viewGroup, Transition transition, v vVar, v vVar2) {
        int i;
        int i2;
        int i3;
        if (vVar == null && vVar2 == null) {
            return 0;
        }
        Rect n = transition.n();
        if (vVar2 == null || b(vVar) == 0) {
            vVar2 = vVar;
            i = -1;
        } else {
            i = 1;
        }
        int c = c(vVar2);
        int d = d(vVar2);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(viewGroup.getTranslationX());
        int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        int width = round + viewGroup.getWidth();
        int height = round2 + viewGroup.getHeight();
        if (n != null) {
            i2 = n.centerX();
            i3 = n.centerY();
        } else {
            i2 = (round + width) / 2;
            i3 = (round2 + height) / 2;
        }
        float a2 = ((float) a(viewGroup, c, d, i2, i3, round, round2, width, height)) / ((float) a(viewGroup));
        long b2 = transition.b();
        if (b2 < 0) {
            b2 = 300;
        }
        return (long) Math.round((((float) (b2 * ((long) i))) / this.a) * a2);
    }

    private int a(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = 5;
        int i10 = 3;
        boolean z = true;
        if (this.b == 8388611) {
            if (ViewCompat.getLayoutDirection(view) != 1) {
                z = false;
            }
            if (!z) {
                i9 = 3;
            }
        } else if (this.b == 8388613) {
            if (ViewCompat.getLayoutDirection(view) != 1) {
                z = false;
            }
            if (!z) {
                i10 = 5;
            }
            i9 = i10;
        } else {
            i9 = this.b;
        }
        switch (i9) {
            case 3:
                return (i7 - i) + Math.abs(i4 - i2);
            case 5:
                return (i - i5) + Math.abs(i4 - i2);
            case 48:
                return (i8 - i2) + Math.abs(i3 - i);
            case 80:
                return (i2 - i6) + Math.abs(i3 - i);
            default:
                return 0;
        }
    }

    private int a(ViewGroup viewGroup) {
        switch (this.b) {
            case 3:
            case 5:
            case GravityCompat.START /*8388611*/:
            case GravityCompat.END /*8388613*/:
                return viewGroup.getWidth();
            default:
                return viewGroup.getHeight();
        }
    }
}
