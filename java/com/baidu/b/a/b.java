package com.baidu.b.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.baidu.a.a.a.b.a;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.Constants;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    /* access modifiers changed from: private */
    public static Context a;
    /* access modifiers changed from: private */
    public static p d = null;
    private static int e = 0;
    /* access modifiers changed from: private */
    public static Hashtable<String, c> f = new Hashtable<>();
    private static b g;
    private f b = null;
    private h c = null;
    private final Handler h = new l(this, Looper.getMainLooper());

    private b(Context context) {
        a = context;
        if (d != null && !d.isAlive()) {
            d = null;
        }
        d.b("BaiduApiAuth SDK Version:1.0.22");
        e();
    }

    private int a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
            if (jSONObject.has("current") && i == 0) {
                long j = jSONObject.getLong("current");
                long currentTimeMillis = System.currentTimeMillis();
                if (((double) (currentTimeMillis - j)) / 3600000.0d >= 24.0d) {
                    i = 601;
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (!simpleDateFormat.format(Long.valueOf(currentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j)))) {
                        i = 601;
                    }
                }
            }
            if (jSONObject.has("current") && i == 602) {
                if (((double) ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000)) > 180.0d) {
                    return 601;
                }
            }
            return i;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static b a(Context context) {
        if (g == null) {
            synchronized (b.class) {
                if (g == null) {
                    g = new b(context);
                }
            }
        } else if (context != null) {
            a = context;
        } else if (d.a) {
            d.c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return g;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r2v20 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: type inference failed for: r1v24 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007c  */
    /* JADX WARNING: Unknown variable types count: 10 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.lang.String r3 = "/proc/"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.lang.String r3 = "/cmdline"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.lang.String r2 = r2.toString()     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            r3.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x0056, all -> 0x006a }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008d, all -> 0x0080 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x008d, all -> 0x0080 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x0091, all -> 0x0086 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x009a, IOException -> 0x0091, all -> 0x0086 }
            java.lang.String r0 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x009d, IOException -> 0x0094, all -> 0x008b }
            if (r1 == 0) goto L_0x0037
            r1.close()
        L_0x0037:
            if (r2 == 0) goto L_0x003c
            r2.close()
        L_0x003c:
            if (r3 == 0) goto L_0x0041
            r3.close()
        L_0x0041:
            return r0
        L_0x0042:
            r1 = move-exception
            r1 = r0
            r2 = r0
            r3 = r0
        L_0x0046:
            if (r1 == 0) goto L_0x004b
            r1.close()
        L_0x004b:
            if (r2 == 0) goto L_0x0050
            r2.close()
        L_0x0050:
            if (r3 == 0) goto L_0x0041
            r3.close()
            goto L_0x0041
        L_0x0056:
            r1 = move-exception
            r1 = r0
            r2 = r0
            r3 = r0
        L_0x005a:
            if (r1 == 0) goto L_0x005f
            r1.close()
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.close()
        L_0x0064:
            if (r3 == 0) goto L_0x0041
            r3.close()
            goto L_0x0041
        L_0x006a:
            r1 = move-exception
            r2 = r0
            r3 = r0
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.close()
        L_0x0075:
            if (r2 == 0) goto L_0x007a
            r2.close()
        L_0x007a:
            if (r3 == 0) goto L_0x007f
            r3.close()
        L_0x007f:
            throw r0
        L_0x0080:
            r1 = move-exception
            r2 = r0
            r5 = r0
            r0 = r1
            r1 = r5
            goto L_0x0070
        L_0x0086:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0070
        L_0x008b:
            r0 = move-exception
            goto L_0x0070
        L_0x008d:
            r1 = move-exception
            r1 = r0
            r2 = r0
            goto L_0x005a
        L_0x0091:
            r1 = move-exception
            r1 = r0
            goto L_0x005a
        L_0x0094:
            r4 = move-exception
            goto L_0x005a
        L_0x0096:
            r1 = move-exception
            r1 = r0
            r2 = r0
            goto L_0x0046
        L_0x009a:
            r1 = move-exception
            r1 = r0
            goto L_0x0046
        L_0x009d:
            r4 = move-exception
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.b.a.b.a(int):java.lang.String");
    }

    private String a(Context context, String str) {
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                c cVar = (c) f.get(str);
                if (cVar != null) {
                    cVar.a(101, a.a(101, "AndroidManifest.xml的application中没有meta-data标签"));
                }
                return str2;
            }
            str2 = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
            if (str2 == null || str2.equals("")) {
                c cVar2 = (c) f.get(str);
                if (cVar2 != null) {
                    cVar2.a(101, a.a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
                }
            }
            return str2;
        } catch (NameNotFoundException e2) {
            c cVar3 = (c) f.get(str);
            if (cVar3 != null) {
                cVar3.a(101, a.a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(String str, String str2) {
        if (str == null) {
            str = f();
        }
        Message obtainMessage = this.h.obtainMessage();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
            if (!jSONObject.has("current")) {
                jSONObject.put("current", System.currentTimeMillis());
            }
            c(jSONObject.toString());
            if (jSONObject.has("current")) {
                jSONObject.remove("current");
            }
            obtainMessage.what = jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
            obtainMessage.obj = jSONObject.toString();
            Bundle bundle = new Bundle();
            bundle.putString("listenerKey", str2);
            obtainMessage.setData(bundle);
            this.h.sendMessage(obtainMessage);
        } catch (JSONException e2) {
            e2.printStackTrace();
            obtainMessage.what = -1;
            obtainMessage.obj = new JSONObject();
            Bundle bundle2 = new Bundle();
            bundle2.putString("listenerKey", str2);
            obtainMessage.setData(bundle2);
            this.h.sendMessage(obtainMessage);
        }
        if (d != null) {
            d.c();
        }
        e--;
        if (d.a) {
            d.a("httpRequest called mAuthCounter-- = " + e);
        }
        if (e == 0 && d != null) {
            d.a();
            d = null;
        }
        return;
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str, Hashtable<String, String> hashtable, String str2) {
        String a2 = a(a, str2);
        if (a2 != null && !a2.equals("")) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
            d.a("url:https://api.map.baidu.com/sdkcs/verify");
            hashMap.put("output", "json");
            hashMap.put("ak", a2);
            d.a("ak:" + a2);
            hashMap.put("mcode", e.a(a));
            hashMap.put("from", "lbs_yunsdk");
            if (hashtable != null && hashtable.size() > 0) {
                for (Entry entry : hashtable.entrySet()) {
                    String str3 = (String) entry.getKey();
                    String str4 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                        hashMap.put(str3, str4);
                    }
                }
            }
            String str5 = "";
            try {
                str5 = a.a(a);
            } catch (Exception e2) {
            }
            d.a("cuid:" + str5);
            if (!TextUtils.isEmpty(str5)) {
                hashMap.put("cuid", str5);
            } else {
                hashMap.put("cuid", "");
            }
            hashMap.put("pcn", a.getPackageName());
            hashMap.put(Config.INPUT_DEF_VERSION, "1.0.22");
            String str6 = "";
            try {
                str6 = e.c(a);
            } catch (Exception e3) {
            }
            if (!TextUtils.isEmpty(str6)) {
                hashMap.put("macaddr", str6);
            } else {
                hashMap.put("macaddr", "");
            }
            String str7 = "";
            try {
                str7 = e.a();
            } catch (Exception e4) {
            }
            if (!TextUtils.isEmpty(str7)) {
                hashMap.put("language", str7);
            } else {
                hashMap.put("language", "");
            }
            if (z) {
                hashMap.put("force", z ? "1" : "0");
            }
            if (str == null) {
                hashMap.put("from_service", "");
            } else {
                hashMap.put("from_service", str);
            }
            this.b = new f(a);
            this.b.a(hashMap, (a<String>) new n<String>(this, str2));
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2) {
        String a2 = a(a, str2);
        if (a2 != null && !a2.equals("")) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
            hashMap.put("output", "json");
            hashMap.put("ak", a2);
            hashMap.put("from", "lbs_yunsdk");
            if (hashtable != null && hashtable.size() > 0) {
                for (Entry entry : hashtable.entrySet()) {
                    String str3 = (String) entry.getKey();
                    String str4 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                        hashMap.put(str3, str4);
                    }
                }
            }
            String str5 = "";
            try {
                str5 = a.a(a);
            } catch (Exception e2) {
            }
            if (!TextUtils.isEmpty(str5)) {
                hashMap.put("cuid", str5);
            } else {
                hashMap.put("cuid", "");
            }
            hashMap.put("pcn", a.getPackageName());
            hashMap.put(Config.INPUT_DEF_VERSION, "1.0.22");
            String str6 = "";
            try {
                str6 = e.c(a);
            } catch (Exception e3) {
            }
            if (!TextUtils.isEmpty(str6)) {
                hashMap.put("macaddr", str6);
            } else {
                hashMap.put("macaddr", "");
            }
            String str7 = "";
            try {
                str7 = e.a();
            } catch (Exception e4) {
            }
            if (!TextUtils.isEmpty(str7)) {
                hashMap.put("language", str7);
            } else {
                hashMap.put("language", "");
            }
            if (z) {
                hashMap.put("force", z ? "1" : "0");
            }
            if (str == null) {
                hashMap.put("from_service", "");
            } else {
                hashMap.put("from_service", str);
            }
            this.c = new h(a);
            this.c.a(hashMap, strArr, new o(this, str2));
        }
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        String str2;
        String a2 = a(a, str);
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(f());
            if (!jSONObject.has("ak")) {
                return true;
            }
            str2 = jSONObject.getString("ak");
            return (a2 == null || str2 == null || a2.equals(str2)) ? false : true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            str2 = str3;
        }
    }

    private String c(Context context) {
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        String str = null;
        try {
            str = a(myPid);
        } catch (IOException e2) {
        }
        return str == null ? a.getPackageName() : str;
    }

    private void c(String str) {
        a.getSharedPreferences("authStatus_" + c(a), 0).edit().putString(NotificationCompat.CATEGORY_STATUS, str).commit();
    }

    private void e() {
        synchronized (b.class) {
            if (d == null) {
                d = new p("auth");
                d.start();
                while (d.a == null) {
                    try {
                        if (d.a) {
                            d.a("wait for create auth thread.");
                        }
                        Thread.sleep(3);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private String f() {
        return a.getSharedPreferences("authStatus_" + c(a), 0).getString(NotificationCompat.CATEGORY_STATUS, "{\"status\":601}");
    }

    public int a(boolean z, String str, Hashtable<String, String> hashtable, c cVar) {
        int i;
        synchronized (b.class) {
            String str2 = System.currentTimeMillis() + "";
            if (cVar != null) {
                f.put(str2, cVar);
            }
            String a2 = a(a, str2);
            if (a2 == null || a2.equals("")) {
                i = 101;
            } else {
                e++;
                if (d.a) {
                    d.a(" mAuthCounter  ++ = " + e);
                }
                String f2 = f();
                if (d.a) {
                    d.a("getAuthMessage from cache:" + f2);
                }
                i = a(f2);
                if (i == 601) {
                    try {
                        c(new JSONObject().put(NotificationCompat.CATEGORY_STATUS, 602).toString());
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                e();
                if (d == null || d.a == null) {
                    i = -1;
                } else {
                    if (d.a) {
                        d.a("mThreadLooper.mHandler = " + d.a);
                    }
                    d.a.post(new m(this, i, z, str2, str, hashtable));
                }
            }
        }
        return i;
    }

    public String a() {
        return a == null ? "" : e.a(a);
    }

    public String b(Context context) throws NameNotFoundException {
        String str = "";
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
    }
}
