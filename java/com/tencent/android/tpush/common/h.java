package com.tencent.android.tpush.common;

import android.content.Context;
import com.tencent.android.tpush.common.j.a;

/* compiled from: ProGuard */
public class h {
    private static j a = null;

    static synchronized j a(Context context) {
        j jVar;
        synchronized (h.class) {
            if (a == null) {
                a = j.a(context);
            }
            jVar = a;
        }
        return jVar;
    }

    public static long a(Context context, String str, long j) {
        return a(context).a(str, j);
    }

    public static void b(Context context, String str, long j) {
        a a2 = a(context).a();
        a2.a(str, j);
        a2.b();
    }

    public static int a(Context context, String str, int i) {
        return a(context).a(str, i);
    }

    public static void b(Context context, String str, int i) {
        a a2 = a(context).a();
        a2.a(str, i);
        a2.b();
    }

    public static String a(Context context, String str, String str2) {
        return a(context).a(str, str2);
    }

    public static void b(Context context, String str, String str2) {
        a a2 = a(context).a();
        a2.a(str, str2);
        a2.b();
    }

    public static void a(Context context, String str) {
        if (a(context) != null) {
            a a2 = a(context).a();
            a2.a(str);
            a2.b();
        }
    }
}
