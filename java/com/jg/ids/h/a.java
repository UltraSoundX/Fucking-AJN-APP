package com.jg.ids.h;

import android.content.Context;
import com.jg.ids.k;
import java.lang.reflect.Method;

public final class a implements k {
    private static Object a;
    private static Class b;
    private static Method c;
    private static Method d;
    private static Method e;

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        try {
            Class cls = Class.forName("com.android.id.impl.IdProviderImpl");
            b = cls;
            a = cls.newInstance();
        } catch (Throwable th) {
            a = null;
        }
        try {
            b.getMethod("getDefaultUDID", new Class[]{Context.class});
        } catch (Throwable th2) {
        }
        try {
            c = b.getMethod("getOAID", new Class[]{Context.class});
        } catch (Throwable th3) {
            c = null;
        }
        try {
            d = b.getMethod("getVAID", new Class[]{Context.class});
        } catch (Throwable th4) {
            d = null;
        }
        try {
            e = b.getMethod("getAAID", new Class[]{Context.class});
        } catch (Throwable th5) {
            e = null;
        }
    }

    private static String a(Context context, Method method) {
        String str = "";
        if (!(a == null || method == null)) {
            try {
                return (String) method.invoke(a, new Object[]{context});
            } catch (Throwable th) {
            }
        }
        return str;
    }

    public final String a(Context context) {
        return a(context, d);
    }

    public final String b(Context context) {
        return a(context, c);
    }

    public final String c(Context context) {
        return a(context, e);
    }
}
