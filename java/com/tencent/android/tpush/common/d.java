package com.tencent.android.tpush.common;

import android.os.Build;
import android.text.TextUtils;

/* compiled from: ProGuard */
public class d {
    public static boolean a() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "xiaomi".equals(str.trim().toLowerCase());
    }
}
