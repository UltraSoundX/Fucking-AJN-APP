package com.tencent.android.tpush.d.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.d.c;
import com.tencent.android.tpush.service.e.i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: ProGuard */
public class d extends c {
    public String a() {
        return "xiaomi";
    }

    public void a(Context context) {
        boolean z;
        boolean z2 = false;
        if (i.b(com.tencent.android.tpush.d.d.a)) {
            a.j("OtherPushMiImpl", "registerPush Error for xiaomi null appid");
        } else if (i.b(com.tencent.android.tpush.d.d.b)) {
            a.j("OtherPushMiImpl", "registerPush Error for xiaomi null miAppkey");
        } else {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            String packageName = context.getPackageName();
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid != myPid || !packageName.equals(runningAppProcessInfo.processName)) {
                    z = z2;
                } else {
                    z = true;
                }
                z2 = z;
            }
            if (z2) {
                a.e("OtherPushMiImpl", "begin Mipush register!" + com.tencent.android.tpush.d.d.a + " " + com.tencent.android.tpush.d.d.b);
                try {
                    Class cls = Class.forName("com.xiaomi.mipush.sdk.MiPushClient");
                    Method method = cls.getMethod("registerPush", new Class[]{Context.class, String.class, String.class});
                    a.e("OtherPushMiImpl", "begin Mipush register!" + com.tencent.android.tpush.d.d.a + " " + com.tencent.android.tpush.d.d.b);
                    method.invoke(cls, new Object[]{context, com.tencent.android.tpush.d.d.a, com.tencent.android.tpush.d.d.b});
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    a.j("OtherPushMiImpl", "registerPush Error for InvocationTargetException: " + cause.getMessage());
                    cause.printStackTrace();
                } catch (Exception e2) {
                    a.e("OtherPushMiImpl", "registerPush Error ", e2);
                }
            }
        }
    }

    public void b(Context context) {
        try {
            Class cls = Class.forName("com.xiaomi.mipush.sdk.MiPushClient");
            cls.getMethod("unregisterPush", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (InvocationTargetException e) {
            a.j("OtherPushMiImpl", "unregisterPush Error for InvocationTargetException: " + e.getCause().getMessage());
        } catch (Exception e2) {
            a.j("OtherPushMiImpl", "unregisterPush Error, are you import otherpush package? " + e2);
        }
    }

    public String c(Context context) {
        try {
            Class cls = Class.forName("com.xiaomi.mipush.sdk.MiPushClient");
            Object invoke = cls.getMethod("getRegId", new Class[]{Context.class}).invoke(cls, new Object[]{context});
            if (invoke != null) {
                return invoke.toString();
            }
        } catch (InvocationTargetException e) {
            a.j("OtherPushMiImpl", "getToken Error for InvocationTargetException: " + e.getCause().getMessage());
        } catch (Exception e2) {
            a.e("OtherPushMiImpl", "getToken Error", e2);
        }
        return null;
    }

    public boolean d(Context context) {
        if (i.b(com.tencent.android.tpush.d.d.a) || i.b(com.tencent.android.tpush.d.d.b)) {
            l.n(context);
        }
        if (!i.b(com.tencent.android.tpush.d.d.a) && !i.b(com.tencent.android.tpush.d.d.b)) {
            return true;
        }
        return false;
    }
}
