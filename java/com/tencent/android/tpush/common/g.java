package com.tencent.android.tpush.common;

import android.content.Context;
import com.tencent.android.tpush.encrypt.a;

/* compiled from: ProGuard */
public class g {
    private static String a(String str) {
        return a.a(str);
    }

    public static boolean a(Context context, String str, String str2, boolean z) {
        if (z) {
            try {
                String str3 = (String) com.tencent.android.tpush.service.cache.a.a(str);
                if (str3 != null && str2 != null && str3.equals(str2)) {
                    return true;
                }
                com.tencent.android.tpush.service.cache.a.a(str, str2);
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d("PushMd5Pref", "putString", e);
                return false;
            }
        }
        h.b(context, a(str), str2);
        return true;
    }

    public static String a(Context context, String str, boolean z) {
        if (!z) {
            return h.a(context, a(str), (String) null);
        }
        try {
            String str2 = (String) com.tencent.android.tpush.service.cache.a.a(str);
            if (str2 != null) {
                return str2;
            }
            String a = h.a(context, a(str), (String) null);
            com.tencent.android.tpush.service.cache.a.a(str, a);
            return a;
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("PushMd5Pref", "getString", e);
            return "";
        }
    }

    public static boolean a(Context context, String str, int i) {
        try {
            h.b(context, a(str), i);
            return true;
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("PushMd5Pref", "putInt", e);
            return false;
        }
    }

    public static int b(Context context, String str, int i) {
        try {
            return h.a(context, a(str), i);
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("PushMd5Pref", "getInt", e);
            return 0;
        }
    }
}
