package android.support.transition;

import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: GhostViewUtils */
class g {
    static f a(View view, ViewGroup viewGroup, Matrix matrix) {
        if (VERSION.SDK_INT >= 21) {
            return e.a(view, viewGroup, matrix);
        }
        return d.a(view, viewGroup);
    }

    static void a(View view) {
        if (VERSION.SDK_INT >= 21) {
            e.a(view);
        } else {
            d.a(view);
        }
    }
}
