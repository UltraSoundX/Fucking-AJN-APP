package com.c.a.e;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;

/* compiled from: LogUtils */
public class c {
    public static String a = "";
    public static boolean b = true;
    public static boolean c = true;
    public static boolean d = true;
    public static boolean e = true;
    public static boolean f = true;
    public static boolean g = true;
    public static a h;

    /* compiled from: LogUtils */
    public interface a {
        void a(String str, String str2);

        void a(String str, String str2, Throwable th);

        void a(String str, Throwable th);

        void b(String str, String str2);

        void c(String str, String str2);
    }

    private static String a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String format = String.format("%s.%s(L:%d)", new Object[]{className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
        if (TextUtils.isEmpty(a)) {
            return format;
        }
        return a + Config.TRACE_TODAY_VISIT_SPLIT + format;
    }

    public static void a(String str) {
        if (b) {
            String a2 = a(d.a());
            if (h != null) {
                h.a(a2, str);
            } else {
                Log.d(a2, str);
            }
        }
    }

    public static void b(String str) {
        if (c) {
            String a2 = a(d.a());
            if (h != null) {
                h.b(a2, str);
            } else {
                Log.e(a2, str);
            }
        }
    }

    public static void a(String str, Throwable th) {
        if (c) {
            String a2 = a(d.a());
            if (h != null) {
                h.a(a2, str, th);
            } else {
                Log.e(a2, str, th);
            }
        }
    }

    public static void c(String str) {
        if (d) {
            String a2 = a(d.a());
            if (h != null) {
                h.c(a2, str);
            } else {
                Log.i(a2, str);
            }
        }
    }

    public static void a(Throwable th) {
        if (f) {
            String a2 = a(d.a());
            if (h != null) {
                h.a(a2, th);
            } else {
                Log.w(a2, th);
            }
        }
    }
}
