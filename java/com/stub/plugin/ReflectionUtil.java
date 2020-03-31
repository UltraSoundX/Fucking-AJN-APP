package com.stub.plugin;

import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    public static Class<?> getSuperClass(Class<?> cls) {
        if (cls != null) {
            return cls.getSuperclass();
        }
        return null;
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls != null && !TextUtils.isEmpty(str)) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
            }
        }
        return null;
    }

    public static Object invoke(Object obj, Method method, Object... objArr) {
        if (!(obj == null || method == null)) {
            try {
                return method.invoke(obj, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
        return null;
    }

    public static Object invokeStatic(Method method, Object... objArr) {
        Object obj = null;
        if (method == null) {
            return obj;
        }
        try {
            return method.invoke(null, objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return obj;
        }
    }
}
