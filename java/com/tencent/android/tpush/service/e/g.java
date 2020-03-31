package com.tencent.android.tpush.service.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

/* compiled from: ProGuard */
public class g {
    private static SharedPreferences a = null;

    static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (g.class) {
            if (a == null) {
                a = context.getSharedPreferences(".tpns.service.xml", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public static void b(Context context, String str, long j) {
        Editor edit = a(context).edit();
        edit.putLong(str, j);
        a(edit);
    }

    public static int a(Context context, String str, int i) {
        return a(context).getInt(str, i);
    }

    public static void b(Context context, String str, int i) {
        Editor edit = a(context).edit();
        edit.putInt(str, i);
        a(edit);
    }

    public static String a(Context context, String str, String str2) {
        if (!a(context).contains(str)) {
            return str2;
        }
        return a(context).getString(str, str2);
    }

    public static void b(Context context, String str, String str2) {
        Editor edit = a(context).edit();
        edit.putString(str, str2);
        a(edit);
    }

    @SuppressLint({"NewApi"})
    private static void a(Editor editor) {
        if (VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
