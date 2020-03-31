package android.support.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: GhostViewApi21 */
class e implements f {
    private static Class<?> a;
    private static boolean b;
    private static Method c;
    private static boolean d;
    private static Method e;
    private static boolean f;
    private final View g;

    static f a(View view, ViewGroup viewGroup, Matrix matrix) {
        b();
        if (c != null) {
            try {
                return new e((View) c.invoke(null, new Object[]{view, viewGroup, matrix}));
            } catch (IllegalAccessException e2) {
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
        return null;
    }

    static void a(View view) {
        c();
        if (e != null) {
            try {
                e.invoke(null, new Object[]{view});
            } catch (IllegalAccessException e2) {
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
    }

    private e(View view) {
        this.g = view;
    }

    public void setVisibility(int i) {
        this.g.setVisibility(i);
    }

    public void a(ViewGroup viewGroup, View view) {
    }

    private static void a() {
        if (!b) {
            try {
                a = Class.forName("android.view.GhostView");
            } catch (ClassNotFoundException e2) {
                Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e2);
            }
            b = true;
        }
    }

    private static void b() {
        if (!d) {
            try {
                a();
                c = a.getDeclaredMethod("addGhost", new Class[]{View.class, ViewGroup.class, Matrix.class});
                c.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e2);
            }
            d = true;
        }
    }

    private static void c() {
        if (!f) {
            try {
                a();
                e = a.getDeclaredMethod("removeGhost", new Class[]{View.class});
                e.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e2);
            }
            f = true;
        }
    }
}
