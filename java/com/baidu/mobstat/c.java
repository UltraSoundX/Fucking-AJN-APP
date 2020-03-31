package com.baidu.mobstat;

import android.content.Context;

public class c {
    public static void a(Context context) {
        b.a.a(context);
        y.a(context).a(g.AP_LIST, System.currentTimeMillis());
    }

    public static void a(Context context, boolean z) {
        e.a.a(context, z);
        y.a(context).a(z ? g.APP_SYS_LIST : g.APP_USER_LIST, System.currentTimeMillis());
    }

    public static void b(Context context, boolean z) {
        f.a.a(context, z);
        y.a(context).a(z ? g.APP_TRACE_CURRENT : g.APP_TRACE_HIS, System.currentTimeMillis());
    }

    public static void b(Context context) {
        d.a.a(context);
        y.a(context).a(g.APP_APK, System.currentTimeMillis());
    }
}
