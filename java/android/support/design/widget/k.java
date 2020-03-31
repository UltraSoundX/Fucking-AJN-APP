package android.support.design.widget;

/* compiled from: MathUtils */
public final class k {
    public static float a(float f, float f2, float f3, float f4) {
        return (float) Math.hypot((double) (f3 - f), (double) (f4 - f2));
    }

    public static float a(float f, float f2, float f3) {
        return ((1.0f - f3) * f) + (f3 * f2);
    }

    public static boolean b(float f, float f2, float f3) {
        return f + f3 >= f2;
    }

    public static float a(float f, float f2, float f3, float f4, float f5, float f6) {
        return b(a(f, f2, f3, f4), a(f, f2, f5, f4), a(f, f2, f5, f6), a(f, f2, f3, f6));
    }

    private static float b(float f, float f2, float f3, float f4) {
        if (f > f2 && f > f3 && f > f4) {
            return f;
        }
        if (f2 <= f3 || f2 <= f4) {
            return f3 > f4 ? f3 : f4;
        }
        return f2;
    }
}
