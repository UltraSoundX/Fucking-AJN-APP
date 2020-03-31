package cn.qqtheme.framework.b;

import android.util.Log;
import com.baidu.mobstat.Config;

/* compiled from: LogUtils */
public final class b {
    private static boolean a = false;
    private static String b = "liyujiang";

    public static void a(String str) {
        a("", str);
    }

    public static void a(String str, String str2) {
        if (a) {
            Log.v(b + ((str == null || str.trim().length() == 0) ? "" : "-") + str, str2 + a());
        }
    }

    public static void b(String str) {
        b("", str);
    }

    public static void b(String str, String str2) {
        if (a) {
            Log.d(b + ((str == null || str.trim().length() == 0) ? "" : "-") + str, str2 + a());
        }
    }

    private static String a() {
        int i = 2;
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int a2 = a(stackTrace);
            if (2 + a2 > stackTrace.length) {
                i = (stackTrace.length - a2) - 1;
            }
            StringBuilder sb = new StringBuilder();
            int i2 = i;
            String str = "    ";
            for (int i3 = i2; i3 > 0; i3--) {
                int i4 = i3 + a2;
                if (i4 < stackTrace.length) {
                    sb.append("\n").append(str).append(c(stackTrace[i4].getClassName())).append(".").append(stackTrace[i4].getMethodName()).append(" ").append("(").append(stackTrace[i4].getFileName()).append(Config.TRACE_TODAY_VISIT_SPLIT).append(stackTrace[i4].getLineNumber()).append(")");
                    str = str + "    ";
                }
            }
            return sb.toString();
        } catch (Exception e) {
            Log.w(b, e);
            return "";
        }
    }

    private static int a(StackTraceElement[] stackTraceElementArr) {
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            if (!stackTraceElementArr[i].getClassName().equals(b.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }

    private static String c(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }
}
