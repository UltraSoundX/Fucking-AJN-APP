package android.support.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

/* compiled from: ViewUtils */
class ah {
    static final Property<View, Float> a = new Property<View, Float>(Float.class, "translationAlpha") {
        /* renamed from: a */
        public Float get(View view) {
            return Float.valueOf(ah.c(view));
        }

        /* renamed from: a */
        public void set(View view, Float f) {
            ah.a(view, f.floatValue());
        }
    };
    static final Property<View, Rect> b = new Property<View, Rect>(Rect.class, "clipBounds") {
        /* renamed from: a */
        public Rect get(View view) {
            return ViewCompat.getClipBounds(view);
        }

        /* renamed from: a */
        public void set(View view, Rect rect) {
            ViewCompat.setClipBounds(view, rect);
        }
    };
    private static final al c;
    private static Field d;
    private static boolean e;

    static {
        if (VERSION.SDK_INT >= 22) {
            c = new ak();
        } else if (VERSION.SDK_INT >= 21) {
            c = new aj();
        } else if (VERSION.SDK_INT >= 19) {
            c = new ai();
        } else {
            c = new al();
        }
    }

    static ag a(View view) {
        if (VERSION.SDK_INT >= 18) {
            return new af(view);
        }
        return ae.d(view);
    }

    static ap b(View view) {
        if (VERSION.SDK_INT >= 18) {
            return new ao(view);
        }
        return new an(view.getWindowToken());
    }

    static void a(View view, float f) {
        c.a(view, f);
    }

    static float c(View view) {
        return c.a(view);
    }

    static void d(View view) {
        c.b(view);
    }

    static void e(View view) {
        c.c(view);
    }

    static void a(View view, int i) {
        a();
        if (d != null) {
            try {
                d.setInt(view, (d.getInt(view) & -13) | i);
            } catch (IllegalAccessException e2) {
            }
        }
    }

    static void a(View view, Matrix matrix) {
        c.a(view, matrix);
    }

    static void b(View view, Matrix matrix) {
        c.b(view, matrix);
    }

    static void c(View view, Matrix matrix) {
        c.c(view, matrix);
    }

    static void a(View view, int i, int i2, int i3, int i4) {
        c.a(view, i, i2, i3, i4);
    }

    private static void a() {
        if (!e) {
            try {
                d = View.class.getDeclaredField("mViewFlags");
                d.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.i("ViewUtils", "fetchViewFlagsField: ");
            }
            e = true;
        }
    }
}
