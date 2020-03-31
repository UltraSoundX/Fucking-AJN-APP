package android.support.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: ViewUtilsApi21 */
class aj extends ai {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;
    private static Method e;
    private static boolean f;

    aj() {
    }

    public void a(View view, Matrix matrix) {
        a();
        if (a != null) {
            try {
                a.invoke(view, new Object[]{matrix});
            } catch (IllegalAccessException e2) {
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
    }

    public void b(View view, Matrix matrix) {
        b();
        if (c != null) {
            try {
                c.invoke(view, new Object[]{matrix});
            } catch (IllegalAccessException e2) {
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
    }

    public void c(View view, Matrix matrix) {
        c();
        if (e != null) {
            try {
                e.invoke(view, new Object[]{matrix});
            } catch (InvocationTargetException e2) {
            } catch (IllegalAccessException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
    }

    private void a() {
        if (!b) {
            try {
                a = View.class.getDeclaredMethod("transformMatrixToGlobal", new Class[]{Matrix.class});
                a.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", e2);
            }
            b = true;
        }
    }

    private void b() {
        if (!d) {
            try {
                c = View.class.getDeclaredMethod("transformMatrixToLocal", new Class[]{Matrix.class});
                c.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", e2);
            }
            d = true;
        }
    }

    private void c() {
        if (!f) {
            try {
                e = View.class.getDeclaredMethod("setAnimationMatrix", new Class[]{Matrix.class});
                e.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("ViewUtilsApi21", "Failed to retrieve setAnimationMatrix method", e2);
            }
            f = true;
        }
    }
}
