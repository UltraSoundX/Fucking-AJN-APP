package com.tencent.smtt.utils;

import android.os.Build.VERSION;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/* compiled from: ReflectionUtils */
public class k {
    public static Object a(String str, String str2) {
        boolean z = false;
        try {
            return Class.forName(str).getMethod(str2, new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            return z;
        }
    }

    public static Object a(Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) {
        boolean z = false;
        try {
            Method method = cls.getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            th.printStackTrace();
            return z;
        }
    }

    public static Object a(Object obj, String str) {
        return a(obj, str, (Class<?>[]) null, new Object[0]);
    }

    public static Object a(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        Method declaredMethod;
        if (obj == null) {
            return null;
        }
        try {
            Class cls = obj.getClass();
            if (VERSION.SDK_INT > 10) {
                declaredMethod = cls.getMethod(str, clsArr);
            } else {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            }
            declaredMethod.setAccessible(true);
            if (objArr.length == 0) {
                objArr = null;
            }
            return declaredMethod.invoke(obj, objArr);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            if (th.getCause() != null && th.getCause().toString().contains("AuthenticationFail")) {
                return new String("AuthenticationFail");
            }
            if (str != null && (str.equalsIgnoreCase("canLoadX5Core") || str.equalsIgnoreCase("initTesRuntimeEnvironment"))) {
                return null;
            }
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            Log.e("ReflectionUtils", "invokeInstance -- exceptions:" + stringWriter.toString());
            return null;
        }
    }

    public static Method a(Object obj, String str, Class<?>... clsArr) {
        Class<Object> cls = obj.getClass();
        while (cls != Object.class) {
            if (cls == null) {
                return null;
            }
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception e) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }
}
