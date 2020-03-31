package android.support.design.b;

import android.graphics.Canvas;
import android.os.Build.VERSION;

/* compiled from: CanvasCompat */
public class a {
    public static int a(Canvas canvas, float f, float f2, float f3, float f4, int i) {
        if (VERSION.SDK_INT > 21) {
            return canvas.saveLayerAlpha(f, f2, f3, f4, i);
        }
        return canvas.saveLayerAlpha(f, f2, f3, f4, i, 31);
    }
}
