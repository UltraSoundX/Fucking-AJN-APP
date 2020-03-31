package com.tencent.android.tpush.service.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* compiled from: ProGuard */
public class h {
    static int a = 100;
    private static SharedPreferences b = null;

    public static String a(Context context, String str, String str2) {
        a(context);
        return b.getString(str, str2);
    }

    public static void b(Context context, String str, String str2) {
        try {
            Editor edit = a(context).edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Throwable th) {
        }
    }

    public static int a(Context context, String str, int i) {
        a(context);
        return b.getInt(str, i);
    }

    public static void b(Context context, String str, int i) {
        try {
            Editor edit = a(context).edit();
            edit.putInt(str, i);
            edit.commit();
        } catch (Throwable th) {
        }
    }

    public static long a(Context context, String str, long j) {
        a(context);
        return b.getLong(str, j);
    }

    public static void b(Context context, String str, long j) {
        try {
            Editor edit = a(context).edit();
            edit.putLong(str, j);
            edit.commit();
        } catch (Throwable th) {
        }
    }

    private static SharedPreferences a(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("tpush.shareprefs", 0);
        }
        return b;
    }
}
