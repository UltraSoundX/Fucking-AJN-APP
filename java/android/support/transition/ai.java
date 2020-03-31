package android.support.transition;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: ViewUtilsApi19 */
class ai extends al {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;

    ai() {
    }

    public void a(View view, float f) {
        a();
        if (a != null) {
            try {
                a.invoke(view, new Object[]{Float.valueOf(f)});
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        } else {
            view.setAlpha(f);
        }
    }

    public float a(View view) {
        b();
        if (c != null) {
            try {
                return ((Float) c.invoke(view, new Object[0])).floatValue();
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        return super.a(view);
    }

    public void b(View view) {
    }

    public void c(View view) {
    }

    private void a() {
        if (!b) {
            try {
                a = View.class.getDeclaredMethod("setTransitionAlpha", new Class[]{Float.TYPE});
                a.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", e);
            }
            b = true;
        }
    }

    private void b() {
        if (!d) {
            try {
                c = View.class.getDeclaredMethod("getTransitionAlpha", new Class[0]);
                c.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", e);
            }
            d = true;
        }
    }
}
