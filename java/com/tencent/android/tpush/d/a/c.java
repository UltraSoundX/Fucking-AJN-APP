package com.tencent.android.tpush.d.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.d.d;
import com.tencent.android.tpush.service.e.i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: ProGuard */
public class c extends com.tencent.android.tpush.d.c {
    public String a() {
        return "meizu";
    }

    public void a(Context context) {
        boolean z;
        boolean z2 = false;
        if (i.b(d.c)) {
            a.j("OtherPushMZImpl", "registerPush Error for mz null appid");
        } else if (i.b(d.d)) {
            a.j("OtherPushMZImpl", "registerPush Error for mz null mzAppkey");
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
                a.e("OtherPushMZImpl", "begin Mzpush register!" + d.c + " " + d.d);
                try {
                    Class cls = Class.forName("com.meizu.cloud.pushsdk.PushManager");
                    new Class[1][0] = Context.class;
                    Method method = cls.getMethod("register", new Class[]{Context.class, String.class, String.class});
                    a.e("OtherPushMZImpl", "begin Mzpush register!" + d.c + " " + d.d);
                    method.invoke(cls, new Object[]{context, d.c, d.d});
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    a.j("OtherPushMZImpl", "registerPush Error for InvocationTargetException: " + cause.getMessage());
                    cause.printStackTrace();
                } catch (Exception e2) {
                    a.e("OtherPushMZImpl", "registerPush Error ", e2);
                }
            }
        }
    }

    public void b(Context context) {
        try {
            Class cls = Class.forName("com.meizu.cloud.pushsdk.PushManager");
            new Class[1][0] = Context.class;
            Method method = cls.getMethod("unRegister", new Class[]{Context.class, String.class, String.class});
            a.e("OtherPushMZImpl", "begin Mzpush unregister!" + d.c + " " + d.d);
            method.invoke(cls, new Object[]{context, d.c, d.d});
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            a.j("OtherPushMZImpl", "unregisterPush Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
        } catch (Exception e2) {
            a.j("OtherPushMZImpl", "unregisterPush Error, are you import otherpush package? " + e2);
        }
    }

    public String c(Context context) {
        try {
            Class cls = Class.forName("com.meizu.cloud.pushsdk.PushManager");
            Object invoke = cls.getMethod("getPushId", new Class[]{Context.class}).invoke(cls, new Object[]{context});
            if (invoke != null) {
                return invoke.toString();
            }
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            a.j("OtherPushMZImpl", "getToken Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
        } catch (Exception e2) {
            a.e("OtherPushMZImpl", "getToken Error", e2);
        }
        return null;
    }

    public boolean d(Context context) {
        if (i.b(d.c) || i.b(d.d)) {
            l.n(context);
        }
        if (!i.b(d.c) && !i.b(d.d)) {
            return true;
        }
        return false;
    }
}
