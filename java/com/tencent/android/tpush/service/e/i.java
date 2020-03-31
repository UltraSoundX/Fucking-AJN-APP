package com.tencent.android.tpush.service.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.TypeStr;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushProvider;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.MobileType;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.a;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class i {
    static List<String> a = new ArrayList();
    private static long b = 0;
    private static long c = 0;
    private static int d = -1;
    private static String e = null;

    public static String a(Context context) {
        if (context != null) {
            return context.getPackageName();
        }
        return "";
    }

    public static void b(Context context) {
        if (b <= 0) {
            b = h.a(context, "last_reportAppList_time", -1);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b > 259200000) {
            b = currentTimeMillis;
            JSONArray o = o(context);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ap_ls", o);
                a.a(context, "app_list", jSONObject);
            } catch (JSONException e2) {
            }
            h.b(context, "last_reportAppList_time", b);
        }
    }

    public static void c(Context context) {
        try {
            int i = d.a(context) ? 1 : 0;
            if (d < 0) {
                d = f.b(context, "notification_st", -1);
            }
            if (c <= 0) {
                c = h.a(context, "last_reportNotification_time", -1);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (i != d || currentTimeMillis - c <= 259200000) {
                d = i;
                c = currentTimeMillis;
                h.b(context, "last_reportNotification_time", c);
                f.a(context, "notification_st", i);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("nf_st", d);
                a.a(context, "notification_st", jSONObject);
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "reportNotificationStatus", th);
        }
    }

    public static String a() {
        try {
            return TpnsSecurity.generateLocalSocketServieName(b.f());
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getSocketName", e2);
            return null;
        }
    }

    public static String b() {
        return com.tencent.android.tpush.encrypt.a.a(a() + "V4");
    }

    public static String c() {
        return com.tencent.android.tpush.encrypt.a.a(a() + "V4.Slave");
    }

    public static String d(Context context) {
        String b2 = b();
        String token = CacheManager.getToken(context);
        if (l.c(token) || "0".equals(token)) {
            return b2;
        }
        return b2 + token;
    }

    public static List<ResolveInfo> e(Context context) {
        if (context != null) {
            try {
                HashMap hashMap = new HashMap();
                PackageManager packageManager = context.getPackageManager();
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(new Intent("android.intent.action"), 32);
                queryIntentActivities.addAll(packageManager.queryIntentActivities(new Intent(""), 32));
                queryIntentActivities.addAll(packageManager.queryBroadcastReceivers(new Intent(Constants.ACTION_SDK_INSTALL), 512));
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    hashMap.put(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo);
                }
                return new ArrayList(hashMap.values());
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getLocalPushAppsInfo", e2);
            }
        }
        return null;
    }

    public static boolean a(Context context, Long l) {
        if (l.longValue() <= 0 || l.longValue() != XGPushConfig.getChannelId(context)) {
            return false;
        }
        return true;
    }

    public static List<String> a(Context context, List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return arrayList;
            }
            if (i(context, (String) list.get(i2))) {
                arrayList.clear();
                arrayList.add(list.get(i2));
                return arrayList;
            }
            if (a(context, (String) list.get(i2))) {
                arrayList.add(list.get(i2));
            }
            i = i2 + 1;
        }
    }

    public static boolean a(Context context, String str) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> b(Context context, Long l) {
        ArrayList arrayList = new ArrayList();
        try {
            for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(new Intent(Constants.ACTION_SDK_INSTALL), 512)) {
                String str = resolveInfo.activityInfo.applicationInfo.packageName;
                try {
                    Object a2 = a(context.createPackageContext(str, 0), XGPushConfig.TPUSH_ACCESS_CHANNAL, (Object) null);
                    if (a2 != null && l.toString().equals(a2.toString())) {
                        arrayList.add(str);
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Throwable th) {
        }
        return arrayList;
    }

    public static List<String> b(Context context, List<Long> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Long c2 : list) {
            List c3 = c(context, c2);
            c3.removeAll(arrayList);
            arrayList.addAll(c3);
        }
        return arrayList;
    }

    public static List<String> c(Context context, Long l) {
        ArrayList arrayList = new ArrayList();
        try {
            for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(new Intent(Constants.ACTION_SDK_INSTALL), 512)) {
                String str = resolveInfo.activityInfo.applicationInfo.packageName;
                try {
                    Object a2 = a(context.createPackageContext(str, 0), XGPushConfig.TPUSH_ACCESS_ID, (Object) null);
                    if (a2 != null && l.toString().equals(a2.toString())) {
                        arrayList.add(str);
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Throwable th) {
        }
        return arrayList;
    }

    private static Object a(Context context, String str, Object obj) {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        if (applicationInfo == null) {
            return obj;
        }
        Object obj2 = applicationInfo.metaData.get(str);
        if (obj2 != null) {
            return obj2;
        }
        return obj;
    }

    public static List<String> d() {
        if (a.isEmpty()) {
            a.add("com.jingdong.app.mall");
            a.add("com.ifeng.news2");
        }
        return a;
    }

    public static boolean a(String str) {
        return d().contains(str);
    }

    public static void f(Context context) {
        int i;
        Process process;
        String str;
        try {
            Map<String, String> map = com.tencent.android.tpush.service.a.a.a(context).K;
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    String str2 = "am startservice -n " + ((String) entry.getKey()) + "/" + ((String) entry.getValue());
                    Process exec = Runtime.getRuntime().exec(str2);
                    int waitFor = exec.waitFor();
                    if (waitFor != 0) {
                        str = "am startservice --user 0 -n " + ((String) entry.getKey()) + "/" + ((String) entry.getValue());
                        process = Runtime.getRuntime().exec(str);
                        i = process.waitFor();
                    } else {
                        i = waitFor;
                        process = exec;
                        str = str2;
                    }
                    if (i != 0) {
                        com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "pullUpServerConfigPkgs error exec cmd:" + str + ",exitValud:" + process.exitValue());
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    static boolean b(Context context, String str) {
        if (str == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo != null && runningAppProcessInfo.processName != null && runningAppProcessInfo.processName.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    public static void g(Context context) {
        try {
            JSONArray jSONArray = com.tencent.android.tpush.service.a.a.a(context).J;
            if (jSONArray == null || jSONArray.length() == 0) {
                com.tencent.android.tpush.b.a.g("Util", "pullupOtherServiceByProviderAndActivity no running");
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                a(context, jSONArray.optJSONObject(i));
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "pullupOtherServiceByProviderAndActivity" + th);
        }
    }

    public static void a(final Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            final String optString = jSONObject.optString("name", "");
            if (!b(optString) && !b(context, optString)) {
                com.tencent.android.tpush.b.a.g("Util", "pullUpOtherServiceByProviderAndActivityJSONOject " + optString);
                String optString2 = jSONObject.optString("intent", "");
                if (!b(optString2)) {
                    try {
                        Intent intent = new Intent(optString2);
                        intent.setFlags(268435456);
                        context.startActivity(intent);
                    } catch (Throwable th) {
                    }
                }
                final String optString3 = jSONObject.optString("url", "");
                if (!b(optString3)) {
                    c.a().a(new Runnable() {
                        public void run() {
                            try {
                                if (!i.b(context, optString)) {
                                    com.tencent.android.tpush.a.b.a(context, Uri.parse("content://" + optString3));
                                }
                            } catch (Throwable th) {
                            }
                        }
                    }, 2000);
                }
            }
        }
    }

    public static void h(Context context) {
        com.tencent.android.tpush.a.b(context);
        if (a(context.getPackageName())) {
            com.tencent.android.tpush.b.a.g("Util", context.getPackageName() + " ingore.");
            return;
        }
        try {
            if (i(context) >= 2) {
                com.tencent.android.tpush.b.a.g("Util", "more than two XGV3 service running");
            } else {
                w(context);
                x(context);
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "pullUpXGServiceByRemoteService" + th);
        }
        g(context);
        f(context);
    }

    private static void w(Context context) {
        List<ResolveInfo> e2 = e(context);
        if (i(context) < 2) {
            if (e2 != null) {
                int i = 0;
                for (ResolveInfo resolveInfo : e2) {
                    i++;
                    if ("oppo".equals(l.b())) {
                        if (i > 2) {
                            return;
                        }
                    } else if (i > 4) {
                        return;
                    }
                    String str = resolveInfo.activityInfo.applicationInfo.packageName;
                    if (!b(str) && !context.getPackageName().equals(str) && !d(context, str)) {
                        if (i(context) < 2) {
                            try {
                                String str2 = "am startservice -n " + str + "/" + XGPushServiceV4.class.getName();
                                Process exec = Runtime.getRuntime().exec(str2);
                                com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, "pull up pullUpXGServiceByRemoteService " + str);
                                int waitFor = exec.waitFor();
                                if (waitFor != 0) {
                                    str2 = "am startservice --user 0 -n " + str + "/" + XGPushServiceV4.class.getName();
                                    exec = Runtime.getRuntime().exec(str2);
                                    waitFor = exec.waitFor();
                                }
                                if (waitFor != 0) {
                                    com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "pull up error exec cmd:" + str2 + ",exitValud:" + exec.exitValue());
                                }
                            } catch (Throwable th) {
                                com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "pull up error exec cmd:" + th);
                            }
                        } else {
                            return;
                        }
                    }
                }
                return;
            }
            com.tencent.android.tpush.b.a.f(Constants.ServiceLogTag, "pullupXGServices  with null content");
        }
    }

    private static void x(final Context context) {
        if (i(context) < 2) {
            c.a().a(new Runnable() {
                public void run() {
                    if (i.i(context) < 2) {
                        List<ResolveInfo> e = i.e(context);
                        if (e != null) {
                            int i = 0;
                            for (ResolveInfo resolveInfo : e) {
                                i++;
                                if ("oppo".equals(l.b())) {
                                    if (i > 2) {
                                        return;
                                    }
                                } else if (i > 4) {
                                    return;
                                }
                                String str = resolveInfo.activityInfo.applicationInfo.packageName;
                                if (!i.b(str) && !context.getPackageName().equals(str) && !i.d(context, str) && i.c(context, str)) {
                                    try {
                                        if (i.i(context) < 2) {
                                            com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, "pull up by provider " + str);
                                            com.tencent.android.tpush.a.b.a(context, Uri.parse("content://" + str + XGPushProvider.AUTH_PRIX + "/" + TypeStr.pullupxg.getStr()));
                                            Thread.sleep(200);
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable th) {
                                        com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "pull up by provider error" + th);
                                    }
                                }
                            }
                            return;
                        }
                        com.tencent.android.tpush.b.a.f(Constants.ServiceLogTag, "pullupXGServices  with null content");
                    }
                }
            }, 2000);
        }
    }

    public static int i(Context context) {
        int i;
        int i2 = 0;
        try {
            List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
            if (runningServices != null && runningServices.size() > 0) {
                String name = XGPushServiceV4.class.getName();
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    if (name.equals(runningServiceInfo.service.getClassName())) {
                        i = i2 + 1;
                    } else {
                        i = i2;
                    }
                    i2 = i;
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "checkXGServiceV3IsRunningByPkgName", th);
        }
        return i2;
    }

    public static boolean c(Context context, String str) {
        try {
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(context.createPackageContext(str, 0), XGPushServiceV4.class), 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                return false;
            }
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (resolveInfo.serviceInfo.processName.contains("xg_service")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
        }
    }

    public static boolean d(Context context, String str) {
        try {
            List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
            if (runningServices != null && runningServices.size() > 0) {
                String name = XGPushServiceV4.class.getName();
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    if (name.equals(runningServiceInfo.service.getClassName())) {
                        String packageName = runningServiceInfo.service.getPackageName();
                        if (com.tencent.android.tpush.stat.a.c.b(packageName) && packageName.equals(str)) {
                            com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, "isSurvive srvPkg :" + packageName);
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "checkXGServiceV3IsRunningByPkgName", th);
        }
        return false;
    }

    public static List<ResolveInfo> e(Context context, String str) {
        List<ResolveInfo> list = null;
        if (context != null) {
            try {
                return context.getPackageManager().queryIntentServices(new Intent(str), 512);
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getLocalPushServicesInfo", e2);
                return list;
            }
        } else {
            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "getLocalPushServicesInfo the context == null");
            return list;
        }
    }

    public static boolean f(Context context, String str) {
        if (l.c(str)) {
            return false;
        }
        if (context != null) {
            try {
                List<ResolveInfo> e2 = e(context);
                if (e2 != null) {
                    for (ResolveInfo resolveInfo : e2) {
                        if (str.equals(resolveInfo.activityInfo.packageName)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e3) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "isLocalApp", e3);
            }
        }
        return false;
    }

    public static boolean g(Context context, String str) {
        if (l.c(str) || context == null) {
            return false;
        }
        try {
            List e2 = e(context, str + Constants.RPC_SUFFIX);
            if (e2 == null || e2.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e3) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "isPkgHasRemoteService", e3);
            return false;
        }
    }

    public static boolean a(Context context, String str, long j) {
        return a(context, str, j, false);
    }

    private static boolean a(Context context, String str, long j, boolean z) {
        boolean z2;
        boolean z3 = false;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(str, 0);
            z3 = true;
        } catch (Exception e2) {
            if (f(context, str) || g(context, str)) {
                return true;
            }
            if (z) {
                try {
                    List registerInfo = CacheManager.getRegisterInfo(context);
                    if (registerInfo != null) {
                        Iterator it = registerInfo.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            RegisterEntity registerEntity = (RegisterEntity) it.next();
                            if (registerEntity.accessId == j) {
                                try {
                                    packageManager.getPackageInfo(registerEntity.packageName, 0);
                                    z2 = true;
                                    break;
                                } catch (Exception e3) {
                                }
                            }
                        }
                    }
                    z2 = false;
                    z3 = z2;
                } catch (Exception e4) {
                    com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "isAppInstalled", e2);
                }
            }
        }
        return z3;
    }

    public static boolean h(Context context, String str) {
        if (context != null) {
            List<String> registerInfos = CacheManager.getRegisterInfos(context);
            if (registerInfos != null) {
                for (String equals : registerInfos) {
                    if (equals.equals(str) && !context.getPackageName().equals(str)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean b(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static int e() {
        return VERSION.SDK_INT;
    }

    public static boolean j(Context context) {
        List registerInfos = CacheManager.getRegisterInfos(context);
        return registerInfos != null && registerInfos.size() > 0;
    }

    public static byte k(Context context) {
        byte type = MobileType.UNKNOWN.getType();
        if (context == null) {
            return type;
        }
        try {
            String simOperator = CustomDeviceInfos.getSimOperator(context);
            if (simOperator == null) {
                return type;
            }
            if (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007") || simOperator.equals("46020")) {
                return MobileType.CHINAMOBILE.getType();
            }
            if (simOperator.equals("46001") || simOperator.equals("46006")) {
                return MobileType.UNICOM.getType();
            }
            if (simOperator.equals("46003") || simOperator.equals("46005")) {
                return MobileType.TELCOM.getType();
            }
            return type;
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getIsp", e2);
            return type;
        }
    }

    public static String l(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                    return "" + k(context) + DeviceInfos.getNetworkType(context);
                }
                return m(context);
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getKey", e2);
            }
        }
        return "";
    }

    public static String m(Context context) {
        String wiFiBBSID = CustomDeviceInfos.getWiFiBBSID(context);
        if (wiFiBBSID != null && wiFiBBSID.length() != 0) {
            return wiFiBBSID;
        }
        String ip = CustomDeviceInfos.getIp(context);
        if (ip == null || ip.length() == 0) {
            return "0";
        }
        return ip;
    }

    public static long c(String str) {
        if (str == null || str.equals("0")) {
            return 0;
        }
        String trim = str.trim();
        long[] jArr = new long[4];
        int indexOf = trim.indexOf(".");
        int indexOf2 = trim.indexOf(".", indexOf + 1);
        int indexOf3 = trim.indexOf(".", indexOf2 + 1);
        try {
            jArr[3] = Long.parseLong(trim.substring(0, indexOf));
            jArr[2] = Long.parseLong(trim.substring(indexOf + 1, indexOf2));
            jArr[1] = Long.parseLong(trim.substring(indexOf2 + 1, indexOf3));
            jArr[0] = Long.parseLong(trim.substring(indexOf3 + 1));
        } catch (Throwable th) {
            Throwable th2 = th;
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = 0;
            }
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "service Util@@parseIpAddress(" + trim + ")", th2);
        }
        return (jArr[0] << 24) + (jArr[1] << 16) + (jArr[2] << 8) + jArr[3];
    }

    public static String a(long j) {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append(String.valueOf(255 & j));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf((65535 & j) >>> 8));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf((16777215 & j) >>> 16));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf(j >>> 24));
        return stringBuffer.toString();
    }

    public static String d(String str) {
        if (b.f() != null) {
            try {
                return TpnsSecurity.getEncryptAPKSignature(b.f().createPackageContext(str, 0));
            } catch (NameNotFoundException e2) {
                com.tencent.android.tpush.b.a.d(Constants.LogTag, "+++ getAppCert exception.", e2);
            }
        }
        return "";
    }

    public static Intent a(int i, String str, int i2) {
        Intent intent = new Intent(Constants.ACTION_FEEDBACK);
        if (!(str == null || str.length() == 0)) {
            intent.setPackage(str);
        }
        intent.putExtra(Constants.FEEDBACK_TAG, i2);
        intent.putExtra(Constants.FEEDBACK_ERROR_CODE, i);
        return intent;
    }

    public static boolean a(Intent intent) {
        try {
            JSONObject jSONObject = new JSONObject(Rijndael.decrypt(intent.getStringExtra("content")));
            if (jSONObject.isNull(MessageKey.MSG_ACCEPT_TIME)) {
                return true;
            }
            String string = jSONObject.getString(MessageKey.MSG_ACCEPT_TIME);
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() == 0) {
                return true;
            }
            Calendar instance = Calendar.getInstance();
            long longExtra = intent.getLongExtra(MessageKey.MSG_SERVER_TIME, 0);
            long longExtra2 = intent.getLongExtra(MessageKey.MSG_TIME_GAP, 0);
            if (!(longExtra == 0 || longExtra2 == 0 || longExtra != 0)) {
                instance.setTimeInMillis(System.currentTimeMillis() - longExtra2);
            }
            int i = (instance.get(11) * 60) + instance.get(12);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = new JSONObject(jSONArray.getString(i2));
                JSONObject jSONObject3 = new JSONObject(jSONObject2.getString(MessageKey.MSG_ACCEPT_TIME_START));
                int intValue = Integer.valueOf(jSONObject3.getString(MessageKey.MSG_ACCEPT_TIME_MIN)).intValue() + (Integer.valueOf(jSONObject3.getString(MessageKey.MSG_ACCEPT_TIME_HOUR)).intValue() * 60);
                JSONObject jSONObject4 = new JSONObject(jSONObject2.getString(MessageKey.MSG_ACCEPT_TIME_END));
                int intValue2 = (Integer.valueOf(jSONObject4.getString(MessageKey.MSG_ACCEPT_TIME_HOUR)).intValue() * 60) + Integer.valueOf(jSONObject4.getString(MessageKey.MSG_ACCEPT_TIME_MIN)).intValue();
                if (intValue <= i && i <= intValue2) {
                    return true;
                }
            }
            com.tencent.android.tpush.b.a.i("Utils", " discurd the msg due to time not accepted! acceptTime = " + string + " , curTime= " + i);
            return false;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "checkAcceptTime", th);
            return true;
        }
    }

    public static long b(Intent intent) {
        int i = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String stringExtra = intent.getStringExtra(MessageKey.MSG_DATE);
            if (b(stringExtra)) {
                stringExtra = simpleDateFormat.format(new Date());
            }
            long time = simpleDateFormat.parse(stringExtra).getTime();
            JSONObject jSONObject = new JSONObject(Rijndael.decrypt(intent.getStringExtra("content")));
            if (jSONObject.isNull(MessageKey.MSG_ACCEPT_TIME)) {
                return time;
            }
            String string = jSONObject.getString(MessageKey.MSG_ACCEPT_TIME);
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() == 0) {
                return time;
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = new JSONObject(new JSONObject(jSONArray.getString(i2)).getString(MessageKey.MSG_ACCEPT_TIME_START));
                int intValue = (Integer.valueOf(jSONObject2.getString(MessageKey.MSG_ACCEPT_TIME_HOUR)).intValue() * 60) + Integer.valueOf(jSONObject2.getString(MessageKey.MSG_ACCEPT_TIME_MIN)).intValue();
                if (intValue < i || i == 0) {
                    i = intValue;
                }
            }
            long j = (((long) (i * 60)) * 1000) + time;
            long longExtra = intent.getLongExtra(MessageKey.MSG_SERVER_TIME, 0);
            long longExtra2 = intent.getLongExtra(MessageKey.MSG_TIME_GAP, 0);
            if (!(longExtra == 0 || longExtra2 == 0 || longExtra != 0)) {
                j += longExtra2;
            }
            com.tencent.android.tpush.b.a.e("Utils", "get acceptTime = " + string + " , acceptBeginTime= " + j);
            return j;
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getAcceptBeginTime", th);
            return 0;
        }
    }

    public static String n(Context context) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null || resolveActivity.activityInfo == null || resolveActivity.activityInfo.packageName.equals("android")) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }

    private static boolean e(String str) {
        if (b(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.contains(".lbe.")) {
            return true;
        }
        if (lowerCase.contains(".qihoo360.")) {
            return true;
        }
        if (lowerCase.contains("jinshan.")) {
            return true;
        }
        if (lowerCase.contains(".qqpimsecure")) {
            return true;
        }
        if (lowerCase.contains(".phonoalbumshoushou")) {
            return true;
        }
        if (lowerCase.contains(".netqin.")) {
            return true;
        }
        if (lowerCase.contains(".kms.")) {
            return true;
        }
        if (lowerCase.contains(".avg.")) {
            return true;
        }
        if (lowerCase.contains(".am321.")) {
            return true;
        }
        if (lowerCase.contains("safe")) {
            return true;
        }
        if (lowerCase.contains("security")) {
            return true;
        }
        if (lowerCase.contains("clean")) {
            return true;
        }
        return false;
    }

    public static JSONArray o(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            Map recentTasks = CustomDeviceInfos.getRecentTasks(context);
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                Map runningAppProces = CustomDeviceInfos.getRunningAppProces(context);
                List<ResolveInfo> e2 = e(context);
                HashMap hashMap = new HashMap();
                if (e2 != null && e2.size() > 0) {
                    for (ResolveInfo resolveInfo : e2) {
                        if (resolveInfo.activityInfo != null) {
                            hashMap.put(resolveInfo.activityInfo.packageName, Integer.valueOf(1));
                        }
                    }
                }
                for (PackageInfo packageInfo : CustomDeviceInfos.getInstalledPackages(context)) {
                    JSONObject jSONObject = new JSONObject();
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if ((packageInfo.applicationInfo.flags & 1) != 0) {
                        if (e(applicationInfo.packageName)) {
                            jSONObject.put("s", "1");
                        }
                    }
                    String charSequence = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                    if (charSequence != null) {
                        jSONObject.put("n", charSequence);
                    }
                    if (applicationInfo.packageName != null) {
                        jSONObject.put(Config.PACKAGE_NAME, applicationInfo.packageName);
                    }
                    if (packageInfo.versionName != null) {
                        jSONObject.put("av", packageInfo.versionName);
                    }
                    if (runningAppProces.containsKey(applicationInfo.packageName)) {
                        jSONObject.put(Config.EVENT_VIEW_RES_NAME, "1");
                    }
                    if (hashMap.containsKey(applicationInfo.packageName)) {
                        jSONObject.put("xg", "1");
                    }
                    jSONObject.put("fit", packageInfo.firstInstallTime / 1000);
                    jSONObject.put("lut", packageInfo.lastUpdateTime / 1000);
                    jSONObject.put("fg", packageInfo.applicationInfo.flags);
                    if (recentTasks.containsKey(applicationInfo.packageName)) {
                        jSONObject.put("rt", 1);
                    }
                    jSONArray.put(jSONObject);
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "failed to get app.", th);
        }
        return jSONArray;
    }

    public static JSONArray p(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                Map runningAppProces = CustomDeviceInfos.getRunningAppProces(context);
                List<ResolveInfo> e2 = e(context);
                HashMap hashMap = new HashMap();
                if (e2 != null && e2.size() > 0) {
                    for (ResolveInfo resolveInfo : e2) {
                        if (resolveInfo.activityInfo != null) {
                            hashMap.put(resolveInfo.activityInfo.packageName, Integer.valueOf(1));
                        }
                    }
                }
                for (PackageInfo packageInfo : CustomDeviceInfos.getInstalledPackages(context)) {
                    JSONObject jSONObject = new JSONObject();
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if (runningAppProces.containsKey(applicationInfo.packageName) || hashMap.containsKey(applicationInfo.packageName)) {
                        if ((packageInfo.applicationInfo.flags & 1) != 0) {
                            if (e(applicationInfo.packageName)) {
                                jSONObject.put("s", "1");
                            }
                        }
                        String charSequence = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                        if (charSequence != null) {
                            jSONObject.put("n", charSequence);
                        }
                        if (applicationInfo.packageName != null) {
                            jSONObject.put("p", applicationInfo.packageName);
                        }
                        if (packageInfo.versionName != null) {
                            jSONObject.put("v", packageInfo.versionName);
                        }
                        if (runningAppProces.containsKey(applicationInfo.packageName)) {
                            jSONObject.put("r", "1");
                        }
                        if (hashMap.containsKey(applicationInfo.packageName)) {
                            jSONObject.put("xg", "1");
                        }
                        jSONArray.put(jSONObject);
                    }
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "failed to get app.", th);
        }
        return jSONArray;
    }

    public static String a(String str, int i) {
        int length = str.length();
        if (length < i) {
            for (int i2 = 0; i2 < i - length; i2++) {
                str = str + " ";
            }
        }
        return str;
    }

    public static boolean q(Context context) {
        try {
            ApplicationInfo r = r(context);
            if (r == null) {
                com.tencent.android.tpush.b.a.j(Constants.LogTag, "Failed to init due to null ApplicationInfo.");
                return false;
            } else if (r.icon != 0) {
                return true;
            } else {
                com.tencent.android.tpush.b.a.j(Constants.LogTag, "Failed to get Application icon in AndroidManifest.xml, You App maybe can not show notification, Please add Application icon in AndroidManifest.xml");
                return false;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    public static ApplicationInfo r(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.e(Constants.LogTag, "Failed to get Application info", e2);
            return null;
        }
    }

    public static boolean s(Context context) {
        return i(context, context.getPackageName());
    }

    public static boolean i(Context context, String str) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(str) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(Context context, String str, long j) {
        return a(context, str, j, false);
    }

    public static String t(Context context) {
        if (TextUtils.isEmpty(e)) {
            int myPid = Process.myPid();
            Iterator it = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                if (myPid == runningAppProcessInfo.pid) {
                    e = runningAppProcessInfo.processName;
                    break;
                }
            }
        }
        return e;
    }

    public static void u(Context context) {
        try {
            String t = t(context);
            if (!t.contains(":xg_service_v")) {
                return;
            }
            if (!"huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
                Process.killProcess(Process.myPid());
                return;
            }
            com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, "serviceSafeExit @ " + t);
            XGPushServiceV4.b().stopSelf();
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e A[SYNTHETIC, Splitter:B:28:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0073 A[SYNTHETIC, Splitter:B:31:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0090 A[SYNTHETIC, Splitter:B:40:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0095 A[SYNTHETIC, Splitter:B:43:0x0095] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean v(android.content.Context r7) {
        /*
            r0 = 0
            java.lang.String r1 = b()     // Catch:{ Throwable -> 0x0061, all -> 0x0089 }
            android.net.LocalServerSocket r2 = new android.net.LocalServerSocket     // Catch:{ Throwable -> 0x0061, all -> 0x0089 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0061, all -> 0x0089 }
            java.lang.String r3 = "TPush"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            r4.<init>()     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            java.lang.String r5 = "MainService : socketName: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            com.tencent.android.tpush.b.a.b(r3, r1)     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            java.lang.String r3 = d(r7)     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            android.net.LocalServerSocket r1 = new android.net.LocalServerSocket     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00ab }
            java.lang.String r0 = "TPush"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b5 }
            r4.<init>()     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r5 = "MainService not Alive: socketFinalName: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ Throwable -> 0x00b5 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00b5 }
            com.tencent.android.tpush.b.a.b(r0, r3)     // Catch:{ Throwable -> 0x00b5 }
            r0 = 0
            if (r2 == 0) goto L_0x0049
            r2.close()     // Catch:{ Throwable -> 0x004f }
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch:{ Throwable -> 0x0058 }
        L_0x004e:
            return r0
        L_0x004f:
            r2 = move-exception
            java.lang.String r3 = "TPush"
            java.lang.String r4 = "MainService localSocket.close()"
            com.tencent.android.tpush.b.a.d(r3, r4, r2)
            goto L_0x0049
        L_0x0058:
            r1 = move-exception
            java.lang.String r2 = "TPush"
            java.lang.String r3 = "MainService finallocalSocket.close()"
            com.tencent.android.tpush.b.a.d(r2, r3, r1)
            goto L_0x004e
        L_0x0061:
            r1 = move-exception
            r1 = r0
            r2 = r0
        L_0x0064:
            java.lang.String r0 = "TPush"
            java.lang.String r3 = "MainService is Alive"
            com.tencent.android.tpush.b.a.a(r0, r3)     // Catch:{ all -> 0x00b0 }
            r0 = 1
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ Throwable -> 0x0080 }
        L_0x0071:
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch:{ Throwable -> 0x0077 }
            goto L_0x004e
        L_0x0077:
            r1 = move-exception
            java.lang.String r2 = "TPush"
            java.lang.String r3 = "MainService finallocalSocket.close()"
            com.tencent.android.tpush.b.a.d(r2, r3, r1)
            goto L_0x004e
        L_0x0080:
            r2 = move-exception
            java.lang.String r3 = "TPush"
            java.lang.String r4 = "MainService localSocket.close()"
            com.tencent.android.tpush.b.a.d(r3, r4, r2)
            goto L_0x0071
        L_0x0089:
            r1 = move-exception
            r2 = r0
            r6 = r0
            r0 = r1
            r1 = r6
        L_0x008e:
            if (r2 == 0) goto L_0x0093
            r2.close()     // Catch:{ Throwable -> 0x0099 }
        L_0x0093:
            if (r1 == 0) goto L_0x0098
            r1.close()     // Catch:{ Throwable -> 0x00a2 }
        L_0x0098:
            throw r0
        L_0x0099:
            r2 = move-exception
            java.lang.String r3 = "TPush"
            java.lang.String r4 = "MainService localSocket.close()"
            com.tencent.android.tpush.b.a.d(r3, r4, r2)
            goto L_0x0093
        L_0x00a2:
            r1 = move-exception
            java.lang.String r2 = "TPush"
            java.lang.String r3 = "MainService finallocalSocket.close()"
            com.tencent.android.tpush.b.a.d(r2, r3, r1)
            goto L_0x0098
        L_0x00ab:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x008e
        L_0x00b0:
            r0 = move-exception
            goto L_0x008e
        L_0x00b2:
            r1 = move-exception
            r1 = r0
            goto L_0x0064
        L_0x00b5:
            r0 = move-exception
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.e.i.v(android.content.Context):boolean");
    }
}
