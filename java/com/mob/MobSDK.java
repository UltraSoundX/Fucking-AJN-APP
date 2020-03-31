package com.mob;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mob.MobUser.OnUserGotListener;
import com.mob.MobUser.UserWatcher;
import com.mob.commons.InternationalDomain;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.a;
import com.mob.commons.a.b;
import com.mob.commons.a.c;
import com.mob.commons.a.d;
import com.mob.commons.a.e;
import com.mob.commons.a.f;
import com.mob.commons.a.g;
import com.mob.commons.a.i;
import com.mob.commons.a.k;
import com.mob.commons.a.l;
import com.mob.commons.a.m;
import com.mob.commons.a.n;
import com.mob.commons.a.o;
import com.mob.commons.a.p;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.h;
import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper;
import com.stub.StubApp;
import java.util.HashMap;

public class MobSDK implements PublicMemberKeeper {
    public static final int CHANNEL_APICLOUD = 5;
    public static final int CHANNEL_COCOS = 1;
    public static final int CHANNEL_FLUTTER = 4;
    public static final int CHANNEL_JS = 3;
    public static final int CHANNEL_NATIVE = 0;
    public static final int CHANNEL_QUICKSDK = 6;
    public static final int CHANNEL_UNITY = 2;
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME;
    private static Context a;
    private static String b;
    private static String c;
    private static volatile boolean d = false;
    private static InternationalDomain e;
    private static volatile boolean f = false;

    static {
        String str = "1.0.0";
        int i = 1;
        try {
            str = "2019-09-18".replace("-", ".");
            i = Integer.parseInt("2019-09-18".replace("-", ""));
        } catch (Throwable th) {
        }
        SDK_VERSION_CODE = i;
        SDK_VERSION_NAME = str;
    }

    public static synchronized void init(Context context) {
        synchronized (MobSDK.class) {
            init(context, null, null);
        }
    }

    public static synchronized void init(Context context, String str) {
        synchronized (MobSDK.class) {
            init(context, str, null);
        }
    }

    public static synchronized void init(Context context, String str, String str2) {
        synchronized (MobSDK.class) {
            if (a == null) {
                a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                a(str, str2);
                c();
                a();
                b();
                d();
            } else if (!TextUtils.isEmpty(str)) {
                boolean isEmpty = TextUtils.isEmpty(b);
                b = str;
                c = str2;
                if (isEmpty) {
                    a.ae();
                }
            }
        }
    }

    private static void a() {
        ((DefaultLogsCollector) NLog.setDefaultCollector(DefaultLogsCollector.get())).addSDK("MOBSDK", SDK_VERSION_CODE);
        try {
            NLog instance = NLog.getInstance("MOBSDK");
            instance.d("===============================", new Object[0]);
            instance.d("MobCommons name: " + SDK_VERSION_NAME + ", code: " + SDK_VERSION_CODE, new Object[0]);
            instance.d("===============================", new Object[0]);
        } catch (Throwable th) {
        }
    }

    private static boolean b() {
        boolean z = true;
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                int length = stackTrace.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    String className = stackTraceElement.getClassName();
                    if (!"android.app.Instrumentation".equals(className)) {
                        if ("android.app.ActivityThread".equals(className) && "handleBindApplication".equals(stackTraceElement.getMethodName())) {
                            break;
                        }
                    } else if ("callApplicationOnCreate".equals(stackTraceElement.getMethodName())) {
                        break;
                    }
                    i++;
                }
            }
            z = false;
        } catch (Throwable th) {
            z = false;
        }
        if (!z) {
            Log.e("MobSDK", "Please invoke MobSDK.init(context) method in your application onCreate()");
        }
        return z;
    }

    private static void a(String str, String str2) {
        if (str == null || str2 == null) {
            Bundle bundle = null;
            try {
                bundle = a.getPackageManager().getPackageInfo(a.getPackageName(), 128).applicationInfo.metaData;
            } catch (Throwable th) {
            }
            if (str == null && bundle != null) {
                str = bundle.getString("Mob-AppKey");
            }
            if (str2 == null && bundle != null) {
                str2 = bundle.getString("Mob-AppSecret");
            }
            if (str2 == null && bundle != null) {
                str2 = bundle.getString("Mob-AppSeret");
            }
        }
        b = str;
        c = str2;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c() {
        /*
            r1 = 0
            android.content.Context r0 = a
            if (r0 != 0) goto L_0x000d
            java.lang.String r0 = "MobSDK"
            java.lang.String r1 = "Please invoke MobSDK.init(context) method firstly."
            android.util.Log.e(r0, r1)
        L_0x000c:
            return
        L_0x000d:
            boolean r0 = d
            if (r0 != 0) goto L_0x000c
            r0 = 1
            d = r0
            android.content.Context r0 = a     // Catch:{ Throwable -> 0x0052 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ Throwable -> 0x0052 }
            android.content.Context r2 = a     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ Throwable -> 0x0052 }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch:{ Throwable -> 0x0052 }
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch:{ Throwable -> 0x0052 }
            android.os.Bundle r0 = r0.metaData     // Catch:{ Throwable -> 0x0052 }
        L_0x002a:
            com.mob.commons.InternationalDomain r2 = e
            if (r2 != 0) goto L_0x003c
            if (r0 == 0) goto L_0x005b
            java.lang.String r2 = "Domain"
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Throwable -> 0x0055 }
            com.mob.commons.InternationalDomain r2 = com.mob.commons.InternationalDomain.domainOf(r2)     // Catch:{ Throwable -> 0x0055 }
            e = r2     // Catch:{ Throwable -> 0x0055 }
        L_0x003c:
            if (r0 == 0) goto L_0x004e
            java.lang.String r2 = "Mob-Https"
            java.lang.String r1 = r0.getString(r2)     // Catch:{ Throwable -> 0x006b }
        L_0x0044:
            if (r1 != 0) goto L_0x0060
            java.lang.String r1 = "Mob-Https"
            boolean r0 = r0.getBoolean(r1)     // Catch:{ Throwable -> 0x0069 }
            f = r0     // Catch:{ Throwable -> 0x0069 }
        L_0x004e:
            com.mob.commons.g.v()
            goto L_0x000c
        L_0x0052:
            r0 = move-exception
            r0 = r1
            goto L_0x002a
        L_0x0055:
            r2 = move-exception
            com.mob.commons.InternationalDomain r2 = com.mob.commons.InternationalDomain.DEFAULT
            e = r2
            goto L_0x003c
        L_0x005b:
            com.mob.commons.InternationalDomain r2 = com.mob.commons.InternationalDomain.DEFAULT
            e = r2
            goto L_0x003c
        L_0x0060:
            java.lang.String r0 = "yes"
            boolean r0 = r0.equalsIgnoreCase(r1)
            f = r0
            goto L_0x004e
        L_0x0069:
            r0 = move-exception
            goto L_0x004e
        L_0x006b:
            r2 = move-exception
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.MobSDK.c():void");
    }

    public static InternationalDomain getDomain() {
        if (e == null) {
            c();
        }
        return e == null ? InternationalDomain.DEFAULT : e;
    }

    @Deprecated
    public static void setDomain(InternationalDomain internationalDomain) {
        e = internationalDomain;
    }

    public static boolean checkForceHttps() {
        c();
        return f;
    }

    public static String checkRequestUrl(String str) {
        return h.a(str);
    }

    private static void d() {
        MobProductCollector.syncInit();
        try {
            new Thread() {
                public void run() {
                    try {
                        MobProductCollector.collect();
                        DeviceAuthorizer.authorize(null);
                        d.a((Class<? extends d>[]) new Class[]{com.mob.commons.a.a.class, i.class, l.class, n.class, b.class, p.class, f.class, o.class, m.class, k.class, com.mob.commons.a.h.class, g.class, e.class, c.class});
                    } catch (Throwable th) {
                        MobLog.getInstance().w(th);
                    }
                }
            }.start();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    public static Context getContext() {
        if (a == null) {
            try {
                Object currentActivityThread = DeviceHelper.currentActivityThread();
                if (currentActivityThread != null) {
                    Context context = (Context) ReflectHelper.invokeInstanceMethod(currentActivityThread, "getApplication", new Object[0]);
                    if (context != null) {
                        init(context);
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
        return a;
    }

    public static final boolean isMob() {
        return a.b();
    }

    public static final boolean isForb() {
        return a.ac();
    }

    public static String getAppkey() {
        return b;
    }

    public static String getAppSecret() {
        return c;
    }

    public static void setChannel(MobProduct mobProduct, int i) {
        com.mob.commons.e.a().a(mobProduct, i);
    }

    public static synchronized void setUser(String str, String str2, String str3, HashMap<String, Object> hashMap) {
        synchronized (MobSDK.class) {
            setUser(str, str2, str3, hashMap, null);
        }
    }

    public static synchronized void setUser(String str, String str2, String str3, HashMap<String, Object> hashMap, String str4) {
        synchronized (MobSDK.class) {
            MobUser.a(str, str2, str3, hashMap, str4);
        }
    }

    public static synchronized void clearUser() {
        synchronized (MobSDK.class) {
            MobUser.a();
        }
    }

    public static synchronized void addUserWatcher(UserWatcher userWatcher) {
        synchronized (MobSDK.class) {
            if (userWatcher != null) {
                MobUser.a(userWatcher);
            }
        }
    }

    public static synchronized void removeUserWatcher(UserWatcher userWatcher) {
        synchronized (MobSDK.class) {
            if (userWatcher != null) {
                MobUser.b(userWatcher);
            }
        }
    }

    public static synchronized void getUser(final OnUserGotListener onUserGotListener) {
        synchronized (MobSDK.class) {
            MobUser.a((OnUserGotListener) new OnUserGotListener() {
                public void onUserGot(MobUser mobUser) {
                    if (onUserGotListener != null) {
                        OnUserGotListener onUserGotListener = onUserGotListener;
                        if (mobUser.getMobUserId() == null) {
                            mobUser = null;
                        }
                        onUserGotListener.onUserGot(mobUser);
                    }
                }
            });
        }
    }

    public static HashMap<String, String> exchangeIds(String[] strArr) {
        return MobUser.a(strArr);
    }
}
