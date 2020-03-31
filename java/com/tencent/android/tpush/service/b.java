package com.tencent.android.tpush.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.f;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.e.i;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class b {
    /* access modifiers changed from: private */
    public static Context a = null;
    private static String b = "";
    private static LocalServerSocket c = null;
    private static LocalServerSocket d = null;
    private static volatile boolean f = false;
    private static volatile boolean g = false;
    /* access modifiers changed from: private */
    public static volatile boolean h = false;
    /* access modifiers changed from: private */
    public static volatile boolean i = false;
    private Handler e;

    /* compiled from: ProGuard */
    public static class a {
        public static final b a = new b();
    }

    private b() {
        this.e = null;
        b = i.a(f());
    }

    public static b a() {
        return a.a;
    }

    public void b() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a2, code lost:
        r2 = com.tencent.android.tpush.service.cache.CacheManager.getRegisterInfos(f());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00aa, code lost:
        if (r2 == null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b0, code lost:
        if (r2.size() <= 1) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b2, code lost:
        r0 = (long) (((int) (java.lang.Math.random() * 1000.0d)) + com.tencent.smtt.sdk.TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_TIMED_TEXT_ERROR);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c4, code lost:
        if (r0 >= 1000) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c6, code lost:
        r0 = 1000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c8, code lost:
        r6.e.sendMessageDelayed(r6.e.obtainMessage(1), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Intent r7) {
        /*
            r6 = this;
            r4 = 1
            r0 = 0
            android.os.Handler r2 = r6.e
            if (r2 != 0) goto L_0x000a
            r6.o()
        L_0x000a:
            monitor-enter(r6)
            boolean r2 = f     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0071
            android.net.LocalServerSocket r2 = c     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0071
            if (r7 == 0) goto L_0x0043
            java.lang.String r2 = r7.getAction()     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0043
            java.lang.String r3 = "com.tencent.android.tpush.action.keepalive"
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0054
            android.os.Handler r2 = r6.e     // Catch:{ all -> 0x0051 }
            r3 = 2
            android.os.Message r2 = r2.obtainMessage(r3)     // Catch:{ all -> 0x0051 }
            java.lang.String r3 = "delay_time"
            r4 = 0
            long r4 = r7.getLongExtra(r3, r4)     // Catch:{ all -> 0x0051 }
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0045
            android.os.Handler r0 = r6.e     // Catch:{ all -> 0x0051 }
            r1 = 2
            r0.removeMessages(r1)     // Catch:{ all -> 0x0051 }
            android.os.Handler r0 = r6.e     // Catch:{ all -> 0x0051 }
            r4 = 100
            r0.sendMessageDelayed(r2, r4)     // Catch:{ all -> 0x0051 }
        L_0x0043:
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
        L_0x0044:
            return
        L_0x0045:
            android.os.Handler r0 = r6.e     // Catch:{ all -> 0x0051 }
            r1 = 2
            r0.removeMessages(r1)     // Catch:{ all -> 0x0051 }
            android.os.Handler r0 = r6.e     // Catch:{ all -> 0x0051 }
            r0.sendMessageDelayed(r2, r4)     // Catch:{ all -> 0x0051 }
            goto L_0x0043
        L_0x0051:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            throw r0
        L_0x0054:
            java.lang.String r0 = "com.tencent.android.tpush.action.stop_connect"
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0051 }
            if (r0 == 0) goto L_0x0043
            android.os.Handler r0 = r6.e     // Catch:{ all -> 0x0051 }
            r1 = 3
            android.os.Message r0 = r0.obtainMessage(r1)     // Catch:{ all -> 0x0051 }
            android.os.Handler r1 = r6.e     // Catch:{ all -> 0x0051 }
            r2 = 3
            r1.removeMessages(r2)     // Catch:{ all -> 0x0051 }
            android.os.Handler r1 = r6.e     // Catch:{ all -> 0x0051 }
            r2 = 100
            r1.sendMessageDelayed(r0, r2)     // Catch:{ all -> 0x0051 }
            goto L_0x0043
        L_0x0071:
            boolean r2 = g     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x00a1
            android.net.LocalServerSocket r2 = d     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x00a1
            android.content.Context r0 = f()     // Catch:{ all -> 0x0051 }
            boolean r0 = com.tencent.android.tpush.service.e.i.s(r0)     // Catch:{ all -> 0x0051 }
            if (r0 != 0) goto L_0x0091
            if (r7 == 0) goto L_0x009f
            java.lang.String r0 = "com.tencent.android.tpush.action.slave2main"
            java.lang.String r1 = r7.getAction()     // Catch:{ all -> 0x0051 }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0051 }
            if (r0 == 0) goto L_0x009f
        L_0x0091:
            android.os.Handler r0 = r6.e     // Catch:{ all -> 0x0051 }
            r1 = 4
            android.os.Message r0 = r0.obtainMessage(r1)     // Catch:{ all -> 0x0051 }
            android.os.Handler r1 = r6.e     // Catch:{ all -> 0x0051 }
            r2 = 0
            r1.sendMessageDelayed(r0, r2)     // Catch:{ all -> 0x0051 }
        L_0x009f:
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            goto L_0x0044
        L_0x00a1:
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            android.content.Context r2 = f()
            java.util.List r2 = com.tencent.android.tpush.service.cache.CacheManager.getRegisterInfos(r2)
            if (r2 == 0) goto L_0x00c8
            int r2 = r2.size()
            if (r2 <= r4) goto L_0x00c8
            double r0 = java.lang.Math.random()
            r2 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r0 = r0 * r2
            int r0 = (int) r0
            int r0 = r0 + 900
            long r0 = (long) r0
            r2 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x00c8
            r0 = 1000(0x3e8, double:4.94E-321)
        L_0x00c8:
            android.os.Handler r2 = r6.e
            android.os.Message r2 = r2.obtainMessage(r4)
            android.os.Handler r3 = r6.e
            r3.sendMessageDelayed(r2, r0)
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.b.a(android.content.Intent):void");
    }

    public static void a(Context context) {
        a(context, Constants.ACTION_KEEPALIVE, 0);
    }

    public static void b(Context context) {
        a(context, Constants.ACTION_START_SLVAE, 0);
    }

    public static void a(Context context, long j) {
        a(context, Constants.ACTION_KEEPALIVE, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0063 A[Catch:{ Throwable -> 0x0067 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r6, java.lang.String r7, long r8) {
        /*
            r2 = 0
            if (r6 == 0) goto L_0x0024
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Throwable -> 0x0099 }
            r1.<init>()     // Catch:{ Throwable -> 0x0099 }
            java.lang.Class<com.tencent.android.tpush.service.XGPushServiceV4> r0 = com.tencent.android.tpush.service.XGPushServiceV4.class
            r1.setClass(r6, r0)     // Catch:{ Throwable -> 0x0030 }
            r1.setAction(r7)     // Catch:{ Throwable -> 0x0030 }
            r2 = 0
            int r0 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x001b
            java.lang.String r0 = "delay_time"
            r1.putExtra(r0, r8)     // Catch:{ Throwable -> 0x0030 }
        L_0x001b:
            int r0 = com.tencent.android.tpush.common.l.a(r6)     // Catch:{ Throwable -> 0x0030 }
            if (r0 > 0) goto L_0x0025
            r6.startService(r1)     // Catch:{ Throwable -> 0x0030 }
        L_0x0024:
            return
        L_0x0025:
            java.lang.String r0 = "PushServiceManager"
            java.lang.String r2 = "startService failed, libtpnsSecurity.so not found."
            com.tencent.android.tpush.b.a.i(r0, r2)     // Catch:{ Throwable -> 0x0030 }
            r6.stopService(r1)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x0024
        L_0x0030:
            r0 = move-exception
        L_0x0031:
            java.lang.String r2 = "PushServiceManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "startService failed, intent:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r4 = ", ex:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r2, r0)
            android.content.Intent r2 = new android.content.Intent     // Catch:{ Throwable -> 0x0097 }
            r2.<init>()     // Catch:{ Throwable -> 0x0097 }
            java.lang.Class<com.tencent.android.tpush.service.XGPushServiceV4> r0 = com.tencent.android.tpush.service.XGPushServiceV4.class
            r2.setClass(r6, r0)     // Catch:{ Throwable -> 0x0067 }
            int r0 = com.tencent.android.tpush.common.l.a(r6)     // Catch:{ Throwable -> 0x0067 }
            if (r0 > 0) goto L_0x008c
            r6.startService(r2)     // Catch:{ Throwable -> 0x0067 }
            goto L_0x0024
        L_0x0067:
            r0 = move-exception
            r1 = r2
        L_0x0069:
            java.lang.String r2 = "PushServiceManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "222 startService failed, intent:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = ", ex:"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r2, r0)
            goto L_0x0024
        L_0x008c:
            java.lang.String r0 = "PushServiceManager"
            java.lang.String r1 = "startService failed, libtpnsSecurity.so not found."
            com.tencent.android.tpush.b.a.i(r0, r1)     // Catch:{ Throwable -> 0x0067 }
            r6.stopService(r2)     // Catch:{ Throwable -> 0x0067 }
            goto L_0x0024
        L_0x0097:
            r0 = move-exception
            goto L_0x0069
        L_0x0099:
            r0 = move-exception
            r1 = r2
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.b.a(android.content.Context, java.lang.String, long):void");
    }

    public static void c(Context context) {
        com.tencent.android.tpush.b.a.f("PushServiceManager", "Action -> stop Current Connect");
        a(context, Constants.ACTION_STOP_CONNECT, 0);
    }

    public void c() {
        com.tencent.android.tpush.b.a.c("PushServiceManager", "@@ serviceExit()");
        l.a();
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
            this.e = null;
        }
        if (c.a().b() != null) {
            c.a().b().removeCallbacksAndMessages(null);
        }
        a.a().c(a);
        d();
        i.u(f());
    }

    public void d() {
        synchronized (this) {
            if (c != null) {
                try {
                    c.close();
                    c = null;
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, ">> Destroy local socket exception", e2);
                }
            }
            f = Boolean.valueOf(false).booleanValue();
        }
    }

    public void e() {
        synchronized (this) {
            if (d != null) {
                try {
                    d.close();
                    d = null;
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, ">> Destroy local socket exception", e2);
                }
            }
            g = Boolean.valueOf(false).booleanValue();
        }
    }

    public static void d(Context context) {
        if (context != null) {
            a = context;
            b = context.getPackageName();
        }
    }

    public static Context f() {
        return a;
    }

    public static String g() {
        return b;
    }

    private boolean k() {
        float f2;
        float f3;
        try {
            if (i.a(a.getPackageName())) {
                return true;
            }
            Map registerEntityMap = CacheManager.getRegisterEntityMap();
            if (registerEntityMap != null && registerEntityMap.size() >= 2) {
                HashMap hashMap = new HashMap();
                for (Entry value : registerEntityMap.entrySet()) {
                    RegisterEntity registerEntity = (RegisterEntity) value.getValue();
                    if (registerEntity != null && !i.b(registerEntity.packageName) && registerEntity.isRegistered()) {
                        hashMap.put(registerEntity.packageName, Float.valueOf(registerEntity.xgSDKVersion));
                    }
                }
                float f4 = 0.0f;
                if (hashMap.get(b) != null) {
                    f2 = ((Float) hashMap.get(b)).floatValue();
                } else {
                    f2 = 4.03f;
                }
                List<RunningServiceInfo> runningServices = ((ActivityManager) a.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    String name = XGPushServiceV4.class.getName();
                    for (RunningServiceInfo runningServiceInfo : runningServices) {
                        if (name.equals(runningServiceInfo.service.getClassName())) {
                            String packageName = runningServiceInfo.service.getPackageName();
                            com.tencent.android.tpush.b.a.c("PushServiceManager", "isSurvive srvPkg :" + packageName);
                            if (com.tencent.android.tpush.stat.a.c.b(packageName) && hashMap.get(runningServiceInfo.service.getPackageName()) != null) {
                                f3 = ((Float) hashMap.get(packageName)).floatValue();
                                if (f3 > f4) {
                                    f4 = f3;
                                }
                            }
                        }
                        f3 = f4;
                        f4 = f3;
                    }
                }
                if (f4 > f2) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("PushServiceManager", "isSurvive", e2);
        }
    }

    public static void a(Service service) {
        com.tencent.android.tpush.b.a.i("showMockNotification", service.getClass().getSimpleName() + " showMockNotification");
        Notification notification = new Notification();
        notification.sound = null;
        notification.vibrate = null;
        service.startForeground(19981111, notification);
    }

    public static int e(Context context) {
        int i2;
        int i3 = 0;
        try {
            List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
            if (runningServices != null && runningServices.size() > 0) {
                String name = XGPushServiceV4.class.getName();
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    if (name.equals(runningServiceInfo.service.getClassName())) {
                        String packageName = runningServiceInfo.service.getPackageName();
                        if (packageName != null && !packageName.equals(context.getPackageName())) {
                            i2 = i3 + 1;
                            i3 = i2;
                        }
                    }
                    i2 = i3;
                    i3 = i2;
                }
            }
        } catch (Throwable th) {
        }
        return i3;
    }

    private void l() {
        if (VERSION.SDK_INT < 18) {
            XGPushServiceV4.b().startForeground(-1998, new Notification());
        }
    }

    /* access modifiers changed from: private */
    public boolean m() {
        boolean z;
        boolean k = k();
        synchronized (this) {
            if (k) {
                try {
                    String token = CacheManager.getToken(a);
                    com.tencent.android.tpush.b.a.a("PushServiceManager", "(ignore) running v4 service count " + e(a));
                    if (!l.c(token) && !"0".equals(token)) {
                        LocalServerSocket localServerSocket = new LocalServerSocket(i.b());
                        if (localServerSocket != null) {
                            try {
                                localServerSocket.close();
                            } catch (Throwable th) {
                            }
                        }
                    }
                    String d2 = i.d(a);
                    c = new LocalServerSocket(d2);
                    com.tencent.android.tpush.b.a.a("PushServiceManager", "tryToKeepServiceAlive socketFinalName:" + d2);
                    f = Boolean.valueOf(true).booleanValue();
                    l();
                    f.a(a).a();
                    z = k;
                } catch (Throwable th2) {
                    com.tencent.android.tpush.b.a.f("PushServiceManager", "isSurvive " + k + th2.getLocalizedMessage());
                    z = f;
                }
            } else {
                z = k;
            }
        }
        return z;
    }

    public boolean f(Context context) {
        return !i.v(context);
    }

    /* access modifiers changed from: private */
    public boolean n() {
        com.tencent.android.tpush.b.a.e("PushServiceManager", "tryToKeepSlaveServiceAlive");
        boolean k = k();
        synchronized (this) {
            if (k) {
                try {
                    d = new LocalServerSocket(i.c());
                    g = Boolean.valueOf(true).booleanValue();
                    f.a(a).a();
                } catch (Throwable th) {
                    k = g;
                }
            }
        }
        return k;
    }

    private void o() {
        this.e = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message != null) {
                    switch (message.what) {
                        case 1:
                            if (b.this.m()) {
                                com.tencent.android.tpush.b.a.e("PushServiceManager", "start as main service......");
                                if (!b.h) {
                                    if (XGPushConfig.enableDebug) {
                                        com.tencent.android.tpush.b.a.f("PushServiceManager", "Service's first running at " + b.a.getPackageName() + " version : " + 4.03f);
                                    }
                                    b.h = true;
                                    if (!f.a()) {
                                        com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "permission check failed, kill service!");
                                        b.this.d();
                                        i.u(b.f());
                                    }
                                    a.a().a(b.f());
                                }
                                com.tencent.android.tpush.service.channel.b.a().b();
                                b.this.e();
                                com.tencent.android.tpush.service.channel.b.a().g();
                                c.a().a(new Runnable() {
                                    public void run() {
                                        com.tencent.android.tpush.b.a.e("PushServiceManager", "main service pull up sloveSerice");
                                        i.h(b.a);
                                    }
                                }, 20000);
                                com.tencent.android.tpush.a.a.a(b.f()).a();
                                return;
                            } else if (b.this.n()) {
                                com.tencent.android.tpush.b.a.e("PushServiceManager", "start as slave service......");
                                if (!b.i) {
                                    if (XGPushConfig.enableDebug) {
                                        com.tencent.android.tpush.b.a.f("PushServiceManager", "Slave Service's first running at " + b.a.getPackageName() + " version : " + 4.03f);
                                    }
                                    b.i = true;
                                    com.tencent.android.tpush.service.channel.b.a().h();
                                }
                                a.a().c(b.f());
                                return;
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(b.a, XGPushServiceV4.class);
                                com.tencent.android.tpush.b.a.g("PushServiceManager", b.a.getPackageName() + " XGPushServiceV4 try to stop self.");
                                a.a().c(b.f());
                                b.a.stopService(intent);
                                return;
                            }
                        case 2:
                            com.tencent.android.tpush.service.channel.b.a().b();
                            return;
                        case 3:
                            com.tencent.android.tpush.service.channel.b.a().c();
                            return;
                        case 4:
                            com.tencent.android.tpush.b.a.e("PushServiceManager", "go to slave to main service ...");
                            if (b.this.m()) {
                                com.tencent.android.tpush.b.a.e("PushServiceManager", "swicth main service ...");
                                if (!b.h) {
                                    if (XGPushConfig.enableDebug) {
                                        com.tencent.android.tpush.b.a.f("PushServiceManager", "Service's first running at " + b.a.getPackageName() + " version : " + 4.03f);
                                    }
                                    b.h = true;
                                    if (!f.a()) {
                                        com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "permission check failed, kill service!");
                                        b.this.d();
                                        i.u(b.f());
                                    }
                                    a.a().b(b.a);
                                    a.a().a(b.f());
                                }
                                com.tencent.android.tpush.service.channel.b.a().b();
                                b.this.e();
                                com.tencent.android.tpush.service.channel.b.a().g();
                                c.a().a(new Runnable() {
                                    public void run() {
                                        com.tencent.android.tpush.b.a.e("PushServiceManager", "swicth main service then pull up sloveSerice");
                                        i.h(b.a);
                                    }
                                }, 20000);
                                return;
                            }
                            return;
                        default:
                            com.tencent.android.tpush.b.a.i("PushServiceManager", "unknown handler msg = " + message.what);
                            return;
                    }
                }
            }
        };
    }
}
