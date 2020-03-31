package com.tencent.android.tpush.service.e;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;

/* compiled from: ProGuard */
public class d {
    public static boolean a(Context context) {
        if (VERSION.SDK_INT >= 27) {
            a.g("NotificationsUtils", "OS >= 27, return true");
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName();
        int i = applicationInfo.uid;
        try {
            Class cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
        } catch (Throwable th) {
            a.i("NotificationsUtils", th.getMessage());
            return true;
        }
    }
}
