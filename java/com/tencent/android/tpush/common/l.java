package com.tencent.android.tpush.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushActivity;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushProvider;
import com.tencent.android.tpush.XGPushReceiver;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.e.i;
import com.tencent.android.tpush.stat.b.d;
import com.tencent.android.tpush.stat.e;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidProvider;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.security.auth.x500.X500Principal;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class l {
    public static String a = null;
    private static AtomicBoolean b = new AtomicBoolean(false);
    private static boolean c = false;
    private static final X500Principal d = new X500Principal("CN=Android Debug,O=Android,C=US");

    public static String a(String str) {
        if (str == null) {
            return "0";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                byte b3 = b2 & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
                if (b3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(b3));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return "0";
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
            }
        }
    }

    public static void a(JSONObject jSONObject, String str, long j) {
        if (str != null && j > 0) {
            try {
                jSONObject.put(str, j);
            } catch (Throwable th) {
            }
        }
    }

    public static int a(Context context) {
        if (b.get()) {
            return 0;
        }
        try {
            if (XGPushManager.getContext() == null) {
                XGPushManager.setContext(context);
            }
            if (b.f() == null) {
                b.d(context);
            }
            e.b(context);
            if (!h(context)) {
                a.j("Util", "XG is disable");
                return ReturnCode.CODE_SERVICE_DISABLED.getType();
            } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                a.j("Util", "can not load library from so file");
                return ReturnCode.CODE_SO_ERROR.getType();
            } else if (!f.a()) {
                return ReturnCode.CODE_PERMISSIONS_ERROR.getType();
            } else {
                if (!a(context, XGPushProvider.class.getName(), XGPushProvider.AUTH_PRIX)) {
                    a.h("Util", "Maybe have not contentprovider: " + XGPushProvider.class.getName());
                }
                if (!a(context, SettingsContentProvider.class.getName(), ".TPUSH_PROVIDER")) {
                    a.h("Util", "Maybe have not contentprovider: " + SettingsContentProvider.class.getName());
                }
                if (!a(context, MidProvider.class.getName(), MidConstants.PROVIDER_AUTH_SUFFIX)) {
                    a.h("Util", "Maybe have not contentprovider: " + MidProvider.class.getName());
                }
                if (!b("com.qq.taf.jce.JceStruct")) {
                    a.j("Util", "please add wup-1.0.0.E-SNAPSHOT.jar in your libs");
                    return ReturnCode.CODE_JCE_ERROR.getType();
                }
                i.q(context);
                b.set(true);
                return 0;
            }
        } catch (Throwable th) {
            a.d("Util", "Util -> initGlobal", th);
            return -1;
        }
    }

    public static boolean b(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, 0, 0)) {
                if (providerInfo.name.equals(str) && providerInfo.authority.equals(context.getPackageName() + str2)) {
                    return true;
                }
            }
            a.g("Util", "Util -> initGlobal can not find provider " + str + " with authority " + context.getPackageName() + str2);
            return false;
        } catch (Throwable th) {
            throw new RuntimeException("Package manager has died", th);
        }
    }

    public static boolean b(Context context) {
        try {
            List e = i.e(context, context.getPackageName() + Constants.RPC_SUFFIX);
            if (e != null && e.size() > 0) {
                return true;
            }
        } catch (Throwable th) {
            a.d("Util", "Util -> isAIDLConfiged", th);
        }
        return false;
    }

    public static boolean c(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static int c(Context context) {
        if (context != null) {
            try {
                List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    String name = XGPushServiceV4.class.getName();
                    for (RunningServiceInfo runningServiceInfo : runningServices) {
                        String className = runningServiceInfo.service.getClassName();
                        if (!name.equals(className)) {
                            if ("com.tencent.android.tpush.service.XGPushServiceV4".equals(className)) {
                            }
                        }
                        return runningServiceInfo.pid != 0 ? 1 : 2;
                    }
                }
            } catch (Throwable th) {
                a.d("Util", "getServiceStatus", th);
            }
        }
        return 0;
    }

    public static void d(Context context) {
        a.c(Constants.LogTag, "startCurrentAppService " + context.getPackageName());
        context.startService(new Intent(context, XGPushServiceV4.class));
    }

    public static void e(final Context context) {
        if (context != null) {
            try {
                if (i.a(context.getPackageName())) {
                    d(context);
                    return;
                }
                b.d(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                b.a(context);
                a.a("Util", "Action -> start Local Service()");
                c.a().a(new Runnable() {
                    public void run() {
                        if (l.c(context) != 1) {
                            try {
                                b.a(context);
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    }
                }, 1500);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean a(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
            return true;
        } catch (Exception e) {
            a.d("Util", "safeUnregisterReceiver error", e);
            return false;
        }
    }

    public static String f(Context context) {
        String str = "";
        try {
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 == null) {
                return "";
            }
            return str2;
        } catch (Throwable th) {
            Throwable th2 = th;
            String str3 = str;
            a.d("Util", "get app version error", th2);
            return str3;
        }
    }

    private static void b(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ComponentName componentName = new ComponentName(context.getPackageName(), str);
                if (packageManager.getComponentEnabledSetting(componentName) != 1) {
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                }
            }
        }
    }

    public static void g(Context context) {
        if (context != null && !c) {
            try {
                b(context, XGPushServiceV4.class.getName());
                b(context, XGPushActivity.class.getName());
                b(context, XGPushProvider.class.getName());
                b(context, SettingsContentProvider.class.getName());
                b(context, MidProvider.class.getName());
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
                int length = activityInfoArr.length;
                int i = 0;
                while (i < length) {
                    String str = activityInfoArr[i].name;
                    try {
                        Class loadClass = context.getClassLoader().loadClass(str);
                        if (XGPushBaseReceiver.class.isAssignableFrom(loadClass) || loadClass.getName().equals(XGPushReceiver.class.getName())) {
                            b(context, str);
                            i++;
                        } else {
                            i++;
                        }
                    } catch (ClassNotFoundException e) {
                    }
                }
            } catch (Exception e2) {
                a.d("Util", "enableComponents", e2);
            }
            c = true;
        }
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(j));
        } catch (Exception e) {
            a.d("Util", "getDateString", e);
            return "20141111";
        }
    }

    public static boolean h(Context context) {
        if (context == null) {
            return true;
        }
        XGPushManager.enableService = g.b(context, context.getPackageName() + XGPushManager.ENABLE_SERVICE_SUFFIX, XGPushManager.enableService);
        if (XGPushManager.enableService == -1) {
            XGPushManager.enableService = g.b(context, context.getPackageName() + XGPushManager.ENABLE_SERVICE_SUFFIX, 2);
        }
        if (XGPushManager.enableService == 2 && TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            String str = com.tencent.android.tpush.service.a.a.a(context).x;
            if (!c(str)) {
                String[] split = Rijndael.decrypt(str).split(StorageInterface.KEY_SPLITER);
                HashMap hashMap = new HashMap();
                for (String valueOf : split) {
                    try {
                        hashMap.put(Long.valueOf(valueOf), Long.valueOf(0));
                    } catch (NumberFormatException e) {
                    }
                }
                if (hashMap.size() > 0) {
                    if (XGPushConfig.getAccessId(context) > 0 && hashMap.containsKey(Long.valueOf(XGPushConfig.getAccessId(context)))) {
                        XGPushManager.enableService(context, false);
                        return false;
                    } else if (XGPush4Msdk.getQQAccessId(context) > 0 && hashMap.containsKey(Long.valueOf(XGPush4Msdk.getQQAccessId(context)))) {
                        XGPushManager.enableService(context, false);
                        return false;
                    }
                }
            }
        }
        if (XGPushManager.enableService != 0) {
            return true;
        }
        return false;
    }

    public static boolean i(Context context) {
        try {
            return ((PowerManager) context.getSystemService("power")).isScreenOn();
        } catch (Exception e) {
            a.d("Util", "Util -> isScreenOn", e);
            return false;
        }
    }

    public static int j(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
            if (!(intExtra == 2 || intExtra == 5)) {
                return -1;
            }
            return registerReceiver.getIntExtra("plugged", -1);
        } catch (Exception e) {
            a.d("Util", "Util -> getChangedStatus", e);
            return -1;
        }
    }

    public static void a() {
        try {
            WakeLock b2 = com.tencent.android.tpush.service.e.a().b();
            if (b2 != null) {
                if (b2.isHeld()) {
                    b2.release();
                }
                a.c("Util", "stop WakeLock CPU");
            }
        } catch (Throwable th) {
            a.d("Util", "stopWakeLock", th);
        }
    }

    public static boolean a(d dVar) {
        if (dVar == null || !dVar.c()) {
            return false;
        }
        return true;
    }

    public static void a(String str, Context context) {
        if (XGPushConfig.isHuaweiDebug()) {
            d(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0044 A[SYNTHETIC, Splitter:B:11:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d A[SYNTHETIC, Splitter:B:16:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d(java.lang.String r5) {
        /*
            r1 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "/google.txt"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = r0.toString()
            java.io.FileWriter r0 = new java.io.FileWriter     // Catch:{ Throwable -> 0x0040, all -> 0x004a }
            r3 = 1
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0040, all -> 0x004a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            r1.<init>()     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            java.lang.String r2 = "\r\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            r0.write(r1)     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            r0.flush()     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            r0.close()     // Catch:{ Throwable -> 0x005a, all -> 0x0055 }
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ Exception -> 0x0051 }
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            r0 = r1
        L_0x0042:
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ Exception -> 0x0048 }
            goto L_0x003f
        L_0x0048:
            r0 = move-exception
            goto L_0x003f
        L_0x004a:
            r0 = move-exception
        L_0x004b:
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ Exception -> 0x0053 }
        L_0x0050:
            throw r0
        L_0x0051:
            r0 = move-exception
            goto L_0x003f
        L_0x0053:
            r1 = move-exception
            goto L_0x0050
        L_0x0055:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x004b
        L_0x005a:
            r1 = move-exception
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.common.l.d(java.lang.String):void");
    }

    public static void k(Context context) {
        try {
            if ("oppo".equals(b())) {
                Intent intent = new Intent("oppo.safecenter.intent.action.CHANGE_NOTIFICATION_STATE");
                intent.putExtra("package_name", context.getPackageName());
                intent.putExtra("allow_notify", true);
                context.sendBroadcast(intent);
            }
        } catch (Throwable th) {
            a.b("openNotification", "openNotification", th);
        }
    }

    public static void l(Context context) {
        String b2 = b();
        if (!"meizu".equals(b2) && "oppo".equals(b2)) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.coloros.notificationmanager", "com.coloros.notificationmanager.AppDetailPreferenceActivity");
                intent.setAction("com.coloros.notificationmanager.app.detail");
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                intent.putExtra("pkg_name", context.getPackageName());
                intent.putExtra("app_name", m(context));
                intent.putExtra("class_name", context.getPackageName());
                context.startActivity(intent);
            } catch (Throwable th) {
                a.b("Util", "openNotificationSettings", th);
            }
        }
    }

    public static String m(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
        } catch (Throwable th) {
            a.d("Util", "", th);
            return null;
        }
    }

    public static String b() {
        String str = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(str)) {
            return str.trim().toLowerCase();
        }
        return str;
    }

    public static void n(Context context) {
        String a2 = a(context, "otherpush_config.json");
        if (!c(a2)) {
            try {
                JSONObject jSONObject = new JSONObject(a2);
                try {
                    JSONObject optJSONObject = jSONObject.optJSONObject("xiaomi");
                    if (optJSONObject != null) {
                        com.tencent.android.tpush.d.d.a = optJSONObject.optString("appid", null);
                        com.tencent.android.tpush.d.d.b = optJSONObject.optString("appkey", null);
                    }
                } catch (Throwable th) {
                }
                try {
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("meizu");
                    if (optJSONObject2 != null) {
                        com.tencent.android.tpush.d.d.c = optJSONObject2.optString("appid", null);
                        com.tencent.android.tpush.d.d.d = optJSONObject2.optString("appkey", null);
                    }
                } catch (Throwable th2) {
                }
            } catch (Throwable th3) {
                a.d("Util", "", th3);
            }
        }
    }

    public static String a(Context context, String str) {
        if (!c(a)) {
            return a;
        }
        try {
            InputStream open = context.getResources().getAssets().open(str);
            if (open == null) {
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            String str2 = "";
            String str3 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    str3 = str3 + readLine;
                } else {
                    a = str3;
                    return a;
                }
            }
        } catch (Throwable th) {
            a.c("Util", "assets is null");
            return null;
        }
    }
}
