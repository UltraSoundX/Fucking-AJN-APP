package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;

class ae extends as {
    private static final String a = "baidu_mtj_sdk_record";
    private static ae b = new ae();

    private ae() {
    }

    public static ae a() {
        return b;
    }

    public SharedPreferences a(Context context) {
        if (VERSION.SDK_INT >= 11) {
            return context.getSharedPreferences(a, 4);
        }
        return context.getSharedPreferences(a, 0);
    }

    public void a(Context context, long j) {
        b(context, "session_first_visit_time", j);
    }

    public Long b(Context context) {
        return Long.valueOf(a(context, "session_first_visit_time", 0));
    }

    public void b(Context context, long j) {
        b(context, "session_last_visit_time", j);
    }

    public Long c(Context context) {
        return Long.valueOf(a(context, "session_last_visit_time", 0));
    }

    public void c(Context context, long j) {
        b(context, "session_visit_interval", j);
    }

    public Long d(Context context) {
        return Long.valueOf(a(context, "session_visit_interval", 0));
    }

    public void a(Context context, String str) {
        b(context, "session_today_visit_count", str);
    }

    public String e(Context context) {
        return a(context, "session_today_visit_count", "");
    }

    public void b(Context context, String str) {
        b(context, "session_recent_visit", str);
    }

    public String f(Context context) {
        return a(context, "session_recent_visit", "");
    }
}
