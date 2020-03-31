package com.tencent.android.tpush.d;

import android.content.Context;
import com.tencent.android.tpush.common.Constants;
import java.lang.reflect.InvocationTargetException;

/* compiled from: ProGuard */
public class a {
    private static final String[] a = {"com.tencent.android.tpush.otherpush.mipush.impl.OtherPushImpl", "com.tencent.android.tpush.otherpush.fcm.impl.OtherPushImpl"};
    private static int b = -2;
    private static String c = null;

    public static boolean a(Context context) {
        boolean z = false;
        if (b == -2) {
            int i = 0;
            while (i < a.length) {
                try {
                    Class.forName(a[i]);
                    if (a(context, a[i])) {
                        b = i;
                        c = a[i];
                        return true;
                    }
                    i++;
                } catch (ClassNotFoundException e) {
                }
            }
            b = -1;
        }
        if (b > -1) {
            z = true;
        }
        return z;
    }

    public static boolean a(Context context, String str) {
        try {
            Class cls = Class.forName(str);
            return ((Boolean) cls.getMethod("checkDevice", new Class[]{Context.class}).invoke(cls, new Object[]{context})).booleanValue();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "checkDevice Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
            return false;
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "checkDevice Error, are you import otherpush package? " + e2);
            return false;
        }
    }

    public static void b(Context context, String str) {
        a(context, str, "com.tencent.android.tpush.otherpush.fcm.impl.OtherPushImpl");
    }

    private static void a(Context context, String str, String str2) {
        try {
            Class cls = Class.forName(str2);
            cls.getMethod("setAppid", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{context, str});
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.h(Constants.OTHER_PUSH_TAG, "setAppid Error, are you import otherpush package? " + e);
        }
    }
}
