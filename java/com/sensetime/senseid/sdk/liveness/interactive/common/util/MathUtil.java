package com.sensetime.senseid.sdk.liveness.interactive.common.util;

public final class MathUtil {
    public static boolean equalsNearly(double d, double d2, double d3) {
        return Math.abs(d - d2) < Math.abs(d3);
    }

    public static boolean equalsNearly(float f, float f2, float f3) {
        return Math.abs(f - f2) < Math.abs(f3);
    }
}
