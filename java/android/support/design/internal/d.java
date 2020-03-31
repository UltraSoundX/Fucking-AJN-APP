package android.support.design.internal;

import android.graphics.PorterDuff.Mode;
import android.support.v4.view.ViewCompat;
import android.view.View;

/* compiled from: ViewUtils */
public class d {
    public static Mode a(int i, Mode mode) {
        switch (i) {
            case 3:
                return Mode.SRC_OVER;
            case 5:
                return Mode.SRC_IN;
            case 9:
                return Mode.SRC_ATOP;
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            case 16:
                return Mode.ADD;
            default:
                return mode;
        }
    }

    public static boolean a(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }
}
