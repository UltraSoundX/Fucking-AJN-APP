package com.tencent.android.tpush.service.e;

import android.content.Context;
import com.tencent.android.tpush.encrypt.a;

/* compiled from: ProGuard */
public class f {
    private static String a(String str) {
        return a.a(str);
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            g.b(context, a(str), str2);
            return true;
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("ServicePushInfoMd5Pref", "putString", e);
            return false;
        }
    }

    public static String a(Context context, String str) {
        boolean z = false;
        try {
            return g.a(context, a(str), (String) null);
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("ServicePushInfoMd5Pref", "getString", e);
            return z;
        }
    }

    public static boolean a(Context context, String str, long j) {
        try {
            g.b(context, a(str), j);
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("ServicePushInfoMd5Pref", "putLong", e);
        }
        return false;
    }

    public static long b(Context context, String str, long j) {
        try {
            return g.a(context, a(str), j);
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("ServicePushInfoMd5Pref", "getLong", e);
            return 0;
        }
    }

    public static boolean a(Context context, String str, int i) {
        try {
            g.b(context, a(str), i);
            return true;
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("ServicePushInfoMd5Pref", "putInt", e);
            return false;
        }
    }

    public static int b(Context context, String str, int i) {
        try {
            return g.a(context, a(str), i);
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("ServicePushInfoMd5Pref", "getInt", e);
            return 0;
        }
    }
}
