package com.baidu.b.a;

import android.util.Log;

class d {
    public static boolean a = false;
    private static String b = "BaiduApiAuth";

    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    public static void a(String str) {
        if (a && Thread.currentThread().getStackTrace().length != 0) {
            Log.d(b, a() + ";" + str);
        }
    }

    public static void b(String str) {
        if (Thread.currentThread().getStackTrace().length != 0) {
            Log.i(b, str);
        }
    }

    public static void c(String str) {
        if (a && Thread.currentThread().getStackTrace().length != 0) {
            Log.e(b, a() + ";" + str);
        }
    }
}
