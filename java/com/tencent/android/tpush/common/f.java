package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.service.b;
import com.tencent.mid.core.Constants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public class f {
    private static final String[] a = {Constants.PERMISSION_INTERNET, Constants.PERMISSION_ACCESS_WIFI_STATE, Constants.PERMISSION_ACCESS_NETWORK_STATE};
    private static Map<String, Boolean> b = new HashMap(8);

    private static Context b() {
        if (XGPushManager.getContext() != null) {
            return XGPushManager.getContext();
        }
        return b.f();
    }

    public static boolean a(String str) {
        boolean z;
        Throwable th;
        try {
            if (b.containsKey(str)) {
                return ((Boolean) b.get(str)).booleanValue();
            }
            Context b2 = b();
            if (b2.getPackageManager().checkPermission(str, b2.getPackageName()) == 0) {
                z = true;
            } else {
                z = false;
            }
            try {
                b.put(str, Boolean.valueOf(z));
                return z;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            z = false;
            th = th4;
            Log.e("XgStat", "checkPermission error", th);
            return z;
        }
    }

    public static boolean a() {
        String[] strArr;
        Context b2 = b();
        if (b2 == null) {
            throw new IllegalArgumentException("The context parameter can not be null!");
        }
        try {
            PackageManager packageManager = b2.getPackageManager();
            if (packageManager != null) {
                String[] strArr2 = packageManager.getPackageInfo(b2.getPackageName(), 4096).requestedPermissions;
                if (strArr2 == null) {
                    return false;
                }
                for (String str : a) {
                    boolean a2 = a(strArr2, str);
                    b.put(str, Boolean.valueOf(a2));
                    if (!a2) {
                        a.j(Constants.LogTag, "The required permission of <" + str + "> does not found!");
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            a.d(Constants.LogTag, "check required permissins exception.", e);
            return false;
        }
    }

    private static boolean a(String[] strArr, String str) {
        for (String equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
