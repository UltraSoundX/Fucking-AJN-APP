package com.tencent.android.otherPush.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* compiled from: ProGuard */
public class b {
    static int a = 100;
    private static SharedPreferences b = null;

    public static String a(Context context, String str, String str2) {
        a(context);
        return b.getString(str, str2);
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

    private static SharedPreferences a(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("tpush.shareprefs", 0);
        }
        return b;
    }
}
