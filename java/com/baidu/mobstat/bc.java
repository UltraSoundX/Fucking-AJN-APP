package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.a.b.b.a;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public final class bc {
    public static String a(Context context) {
        String str = "";
        try {
            str = a.a(context);
        } catch (Throwable th) {
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static HashMap<String, String> a(Map<String, String> map) {
        if (map != null) {
            return new HashMap<>(map);
        }
        return null;
    }

    public static String a(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return simpleDateFormat.format(date);
    }

    public static boolean a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String methodName = stackTraceElement.getMethodName();
            if (!TextUtils.isEmpty(methodName) && cls != null && methodName.equals(str)) {
                String className = stackTraceElement.getClassName();
                Class cls2 = null;
                try {
                    cls2 = Class.forName(className);
                } catch (Throwable th) {
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > i) {
            return str.substring(0, i);
        }
        return str;
    }
}
