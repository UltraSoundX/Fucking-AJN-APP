package com.tencent.android.tpush.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class d {
    private static d a = new d();
    private static AlarmManager b = null;

    private d() {
    }

    public static d a() {
        if (b == null) {
            b();
        }
        return a;
    }

    public void a(int i, long j, PendingIntent pendingIntent) {
        if (b != null) {
            if (VERSION.SDK_INT >= 23) {
                try {
                    Method declaredMethod = b.getClass().getDeclaredMethod("setAndAllowWhileIdle", new Class[]{Integer.class, Long.class, PendingIntent.class});
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(b, new Object[]{Integer.valueOf(i), Long.valueOf(j), pendingIntent});
                    return;
                } catch (Throwable th) {
                    a.g(Constants.LogTag, th.getMessage());
                }
            }
            b.set(i, j, pendingIntent);
        }
    }

    private static synchronized void b() {
        synchronized (d.class) {
            if (b == null && b.f() != null) {
                b = (AlarmManager) b.f().getSystemService(NotificationCompat.CATEGORY_ALARM);
            }
        }
    }
}
