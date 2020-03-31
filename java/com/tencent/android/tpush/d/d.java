package com.tencent.android.tpush.d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.d.a.a;
import com.tencent.android.tpush.d.a.b;
import com.tencent.android.tpush.d.a.c;

/* compiled from: ProGuard */
public class d {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static Context e = null;
    public static boolean f = false;
    private static volatile d g = null;
    private static volatile c h = null;
    private int i = -1;

    public boolean a() {
        if (h == null) {
            return false;
        }
        return h.d(e);
    }

    private d(Context context) {
        e = context;
        if (h != null) {
            return;
        }
        if (XGPushConfig.isUsedFcmPush(context)) {
            h = new a();
            return;
        }
        String e2 = e();
        if ("xiaomi".equals(e2)) {
            h = new com.tencent.android.tpush.d.a.d();
        } else if ("huawei".equals(e2)) {
            h = new b();
        } else if ("meizu".equals(e2)) {
            h = new c();
        }
    }

    public static d a(Context context) {
        if (g == null) {
            synchronized (d.class) {
                if (g == null) {
                    g = new d(context);
                }
            }
        }
        return g;
    }

    public void b() {
        if (h != null && e != null && h.d(e)) {
            h.a(e);
        }
    }

    public void c() {
        if (h != null && e != null && h.d(e)) {
            h.b(e);
        }
    }

    public String d() {
        if (h == null || h == null || !h.d(e)) {
            return null;
        }
        return h.c(e);
    }

    public static String e() {
        String str = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(str)) {
            return str.trim().toLowerCase();
        }
        return str;
    }

    public static void a(Context context, String str) {
        a = str;
    }

    public static void b(Context context, String str) {
        b = str;
    }

    public static void c(Context context, String str) {
        c = str;
    }

    public static void d(Context context, String str) {
        d = str;
    }

    public String f() {
        if (h == null || h == null) {
            return null;
        }
        return h.a();
    }

    public boolean g() {
        if (h == null || h == null) {
            return false;
        }
        return h.d(e);
    }
}
