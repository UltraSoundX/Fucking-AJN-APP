package com.baidu.location;

import com.baidu.mobstat.Config;

public class Jni {
    private static int a = 0;
    private static int b = 1;
    private static int c = 2;
    private static int d = 11;
    private static int e = 12;
    private static int f = 13;
    private static int g = 14;
    private static int h = 15;
    private static int i = 1024;
    private static boolean j;

    static {
        j = false;
        try {
            System.loadLibrary("locSDK7b");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            j = true;
        }
    }

    public static double a(float f2, double d2, double d3) {
        double d4 = 0.0d;
        if (j) {
            return d4;
        }
        try {
            return gsr(f2, d2, d3);
        } catch (UnsatisfiedLinkError e2) {
            return d4;
        }
    }

    public static String a(String str) {
        return j ? "err!" : b(str) + "|tp=3";
    }

    private static native String a(byte[] bArr, int i2);

    public static double[] a(double d2, double d3, String str) {
        double[] dArr = {0.0d, 0.0d};
        if (j) {
            return dArr;
        }
        int i2 = -1;
        if (str.equals("bd09")) {
            i2 = a;
        } else if (str.equals("bd09ll")) {
            i2 = b;
        } else if (str.equals("gcj02")) {
            i2 = c;
        } else if (str.equals("gps2gcj")) {
            i2 = d;
        } else if (str.equals("bd092gcj")) {
            i2 = e;
        } else if (str.equals("bd09ll2gcj")) {
            i2 = f;
        } else if (str.equals("wgs842mc")) {
            i2 = h;
        }
        try {
            String[] split = b(d2, d3, str.equals("gcj2wgs") ? 16 : i2, 132456).split(Config.TRACE_TODAY_VISIT_SPLIT);
            dArr[0] = Double.parseDouble(split[0]);
            dArr[1] = Double.parseDouble(split[1]);
        } catch (UnsatisfiedLinkError e2) {
        }
        return dArr;
    }

    private static native String b(double d2, double d3, int i2, int i3);

    public static String b(String str) {
        int i2 = 740;
        if (j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[i];
        int length = bytes.length;
        if (length <= 740) {
            i2 = length;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (bytes[i4] != 0) {
                bArr[i3] = bytes[i4];
                i3++;
            }
        }
        String str2 = "err!";
        try {
            return a(bArr, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "err!";
        }
    }

    public static String c(String str) {
        if (j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        String str2 = "err!";
        try {
            return c(str.getBytes(), 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "err!";
        }
    }

    private static native String c(byte[] bArr, int i2);

    public static Long d(String str) {
        String str2;
        Long l = null;
        if (j) {
            return l;
        }
        String str3 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e2) {
            str2 = str3;
        }
        try {
            return Long.valueOf(murmur(str2));
        } catch (UnsatisfiedLinkError e3) {
            e3.printStackTrace();
            return l;
        }
    }

    public static String e(String str) {
        String str2;
        String str3;
        if (j) {
            return "err!";
        }
        String str4 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e2) {
            str2 = str4;
        }
        String str5 = "err!";
        try {
            str3 = encodeNotLimit(str2, 132456);
        } catch (UnsatisfiedLinkError e3) {
            e3.printStackTrace();
            str3 = "err!";
        }
        return str3 + "|tp=3";
    }

    private static native String ee(String str, int i2);

    private static native String encodeNotLimit(String str, int i2);

    public static String f(String str) {
        String str2;
        String str3;
        if (j) {
            return "err!";
        }
        String str4 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e2) {
            str2 = str4;
        }
        String str5 = "err!";
        try {
            str3 = ee(str2, 132456);
        } catch (UnsatisfiedLinkError e3) {
            e3.printStackTrace();
            str3 = "err!";
        }
        return str3 + "|tp=4";
    }

    private static native double gsr(float f2, double d2, double d3);

    private static native long murmur(String str);
}
