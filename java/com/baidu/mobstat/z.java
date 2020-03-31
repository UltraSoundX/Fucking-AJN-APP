package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import org.json.JSONObject;

public class z {
    public static final z a = new z();

    public void a(Context context, JSONObject jSONObject) {
        al.c().a("startDataAnynalyzed start");
        a(jSONObject);
        y a2 = y.a(context);
        boolean a3 = a2.a();
        al.c().a("is data collect closed:" + a3);
        if (!a3) {
            if (!k.AP_LIST.b(10000)) {
                c(context);
            }
            String str = Build.MANUFACTURER;
            int i = VERSION.SDK_INT;
            boolean z = false;
            if (!TextUtils.isEmpty(str) && "huawei".equals(str.trim().toLowerCase()) && i >= 28) {
                z = true;
            }
            if (!k.APP_LIST.b(10000) && !z) {
                d(context);
            }
            if (!k.APP_TRACE.b(10000) && !z) {
                e(context);
            }
            if (ab.e && !k.APP_APK.b(10000) && !z) {
                f(context);
            }
            boolean p = bb.p(context);
            if (p && a2.l()) {
                al.c().a("sendLog");
                g(context);
            } else if (!p) {
                al.c().a("isWifiAvailable = false, will not sendLog");
            } else {
                al.c().a("can not sendLog due to time stratergy");
            }
        }
        al.c().a("startDataAnynalyzed finished");
    }

    private void a(JSONObject jSONObject) {
        ac acVar = new ac(jSONObject);
        ab.b = acVar.a;
        ab.c = acVar.b;
        ab.d = acVar.c;
    }

    private void c(Context context) {
        al.c().a("collectAPWithStretegy 1");
        y a2 = y.a(context);
        long a3 = a2.a(g.AP_LIST);
        long currentTimeMillis = System.currentTimeMillis();
        long e = a2.e();
        al.c().a("now time: " + currentTimeMillis + ": last time: " + a3 + "; time interval: " + e);
        if (a3 == 0 || currentTimeMillis - a3 > e) {
            al.c().a("collectAPWithStretegy 2");
            c.a(context);
        }
    }

    private void d(Context context) {
        al.c().a("collectAPPListWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        y a2 = y.a(context);
        long a3 = a2.a(g.APP_USER_LIST);
        long f = a2.f();
        al.c().a("now time: " + currentTimeMillis + ": last time: " + a3 + "; userInterval : " + f);
        if (a3 == 0 || currentTimeMillis - a3 > f || !a2.a(a3)) {
            al.c().a("collectUserAPPListWithStretegy 2");
            c.a(context, false);
        }
        long a4 = a2.a(g.APP_SYS_LIST);
        long g = a2.g();
        al.c().a("now time: " + currentTimeMillis + ": last time: " + a4 + "; sysInterval : " + g);
        if (a4 == 0 || currentTimeMillis - a4 > g) {
            al.c().a("collectSysAPPListWithStretegy 2");
            c.a(context, true);
        }
    }

    private void e(Context context) {
        al.c().a("collectAPPTraceWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        y a2 = y.a(context);
        long a3 = a2.a(g.APP_TRACE_HIS);
        long i = a2.i();
        al.c().a("now time: " + currentTimeMillis + ": last time: " + a3 + "; time interval: " + i);
        if (a3 == 0 || currentTimeMillis - a3 > i) {
            al.c().a("collectAPPTraceWithStretegy 2");
            c.b(context, false);
        }
    }

    private void f(Context context) {
        al.c().a("collectAPKWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        y a2 = y.a(context);
        long a3 = a2.a(g.APP_APK);
        long h = a2.h();
        al.c().a("now time: " + currentTimeMillis + ": last time: " + a3 + "; interval : " + h);
        if (a3 == 0 || currentTimeMillis - a3 > h) {
            al.c().a("collectAPKWithStretegy 2");
            c.b(context);
        }
    }

    public void a(Context context, String str) {
        y.a(context).a(str);
    }

    public void b(Context context, String str) {
        y.a(context).b(str);
    }

    public void a(Context context, long j) {
        y.a(context).a(g.LAST_UPDATE, j);
    }

    private void g(Context context) {
        y.a(context).a(g.LAST_SEND, System.currentTimeMillis());
        JSONObject a2 = h.a(context);
        al.c().a("header: " + a2);
        int i = 0;
        while (a()) {
            int i2 = i + 1;
            if (i > 0) {
                h.c(a2);
            }
            b(context, a2);
            i = i2;
        }
    }

    private boolean a() {
        if (k.AP_LIST.b() && k.APP_LIST.b() && k.APP_TRACE.b() && k.APP_CHANGE.b() && k.APP_APK.b()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006f A[LOOP:0: B:13:0x0069->B:15:0x006f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c7 A[LOOP:1: B:28:0x00c1->B:30:0x00c7, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0115 A[LOOP:2: B:40:0x010f->B:42:0x0115, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0164 A[LOOP:3: B:52:0x015e->B:54:0x0164, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01b4 A[LOOP:4: B:64:0x01ae->B:66:0x01b4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(android.content.Context r7, org.json.JSONObject r8) {
        /*
            r6 = this;
            r0 = 0
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r1 = "he"
            r2.put(r1, r8)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r1 = r8.toString()     // Catch:{ JSONException -> 0x0080 }
            int r1 = r1.length()     // Catch:{ JSONException -> 0x0080 }
            int r0 = r0 + r1
        L_0x0014:
            com.baidu.mobstat.al r1 = com.baidu.mobstat.al.c()
            java.lang.String r3 = "APP_MEM"
            r1.a(r3)
            com.baidu.mobstat.y r1 = com.baidu.mobstat.y.a(r7)
            boolean r1 = r1.b()
            if (r1 != 0) goto L_0x0091
            java.lang.String r1 = com.baidu.mobstat.bb.v(r7)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            com.baidu.mobstat.al r4 = com.baidu.mobstat.al.c()
            r4.a(r1)
            r3.put(r1)
            int r1 = r3.length()
            if (r1 <= 0) goto L_0x0091
            java.lang.String r1 = "app_mem3"
            r2.put(r1, r3)     // Catch:{ JSONException -> 0x0089 }
            java.lang.String r1 = r3.toString()     // Catch:{ JSONException -> 0x0089 }
            int r1 = r1.length()     // Catch:{ JSONException -> 0x0089 }
            int r0 = r0 + r1
            r1 = r0
        L_0x004f:
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()
            java.lang.String r3 = "APP_APK"
            r0.a(r3)
            com.baidu.mobstat.k r0 = com.baidu.mobstat.k.APP_APK
            r3 = 20480(0x5000, float:2.8699E-41)
            java.util.List r0 = r0.a(r3)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x0069:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0093
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            com.baidu.mobstat.al r5 = com.baidu.mobstat.al.c()
            r5.a(r0)
            r3.put(r0)
            goto L_0x0069
        L_0x0080:
            r1 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r1)
            goto L_0x0014
        L_0x0089:
            r1 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r1)
        L_0x0091:
            r1 = r0
            goto L_0x004f
        L_0x0093:
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x00a7
            java.lang.String r0 = "app_apk3"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x00d8 }
            int r0 = r0.length()     // Catch:{ JSONException -> 0x00d8 }
            int r1 = r1 + r0
        L_0x00a7:
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()
            java.lang.String r3 = "APP_CHANGE"
            r0.a(r3)
            com.baidu.mobstat.k r0 = com.baidu.mobstat.k.APP_CHANGE
            r3 = 10240(0x2800, float:1.4349E-41)
            java.util.List r0 = r0.a(r3)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x00c1:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x00e1
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            com.baidu.mobstat.al r5 = com.baidu.mobstat.al.c()
            r5.a(r0)
            r3.put(r0)
            goto L_0x00c1
        L_0x00d8:
            r0 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r0)
            goto L_0x00a7
        L_0x00e1:
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x00f5
            java.lang.String r0 = "app_change3"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x0126 }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x0126 }
            int r0 = r0.length()     // Catch:{ JSONException -> 0x0126 }
            int r1 = r1 + r0
        L_0x00f5:
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()
            java.lang.String r3 = "APP_TRACE"
            r0.a(r3)
            com.baidu.mobstat.k r0 = com.baidu.mobstat.k.APP_TRACE
            r3 = 15360(0x3c00, float:2.1524E-41)
            java.util.List r0 = r0.a(r3)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x010f:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x012f
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            com.baidu.mobstat.al r5 = com.baidu.mobstat.al.c()
            r5.a(r0)
            r3.put(r0)
            goto L_0x010f
        L_0x0126:
            r0 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r0)
            goto L_0x00f5
        L_0x012f:
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x0143
            java.lang.String r0 = "app_trace3"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x0175 }
            int r0 = r0.length()     // Catch:{ JSONException -> 0x0175 }
            int r1 = r1 + r0
        L_0x0143:
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()
            java.lang.String r3 = "APP_LIST"
            r0.a(r3)
            com.baidu.mobstat.k r0 = com.baidu.mobstat.k.APP_LIST
            r3 = 46080(0xb400, float:6.4572E-41)
            java.util.List r0 = r0.a(r3)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x015e:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x017e
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            com.baidu.mobstat.al r5 = com.baidu.mobstat.al.c()
            r5.a(r0)
            r3.put(r0)
            goto L_0x015e
        L_0x0175:
            r0 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r0)
            goto L_0x0143
        L_0x017e:
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x0192
            java.lang.String r0 = "app_list3"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x01c5 }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x01c5 }
            int r0 = r0.length()     // Catch:{ JSONException -> 0x01c5 }
            int r1 = r1 + r0
        L_0x0192:
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()
            java.lang.String r3 = "AP_LIST"
            r0.a(r3)
            r0 = 184320(0x2d000, float:2.58287E-40)
            int r0 = r0 - r1
            com.baidu.mobstat.k r3 = com.baidu.mobstat.k.AP_LIST
            java.util.List r0 = r3.a(r0)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x01ae:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x01ce
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            com.baidu.mobstat.al r5 = com.baidu.mobstat.al.c()
            r5.a(r0)
            r3.put(r0)
            goto L_0x01ae
        L_0x01c5:
            r0 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r0)
            goto L_0x0192
        L_0x01ce:
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x01e2
            java.lang.String r0 = "ap_list3"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x021a }
            java.lang.String r0 = r3.toString()     // Catch:{ JSONException -> 0x021a }
            int r0 = r0.length()     // Catch:{ JSONException -> 0x021a }
            int r1 = r1 + r0
        L_0x01e2:
            com.baidu.mobstat.al r0 = com.baidu.mobstat.al.c()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "log in bytes is almost :"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r0.a(r1)
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            r0.put(r2)
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "payload"
            r1.put(r2, r0)     // Catch:{ Exception -> 0x0223 }
            com.baidu.mobstat.s r0 = com.baidu.mobstat.s.a()     // Catch:{ Exception -> 0x0223 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0223 }
            r0.a(r7, r1)     // Catch:{ Exception -> 0x0223 }
        L_0x0219:
            return
        L_0x021a:
            r0 = move-exception
            com.baidu.mobstat.al r3 = com.baidu.mobstat.al.c()
            r3.a(r0)
            goto L_0x01e2
        L_0x0223:
            r0 = move-exception
            com.baidu.mobstat.al r1 = com.baidu.mobstat.al.c()
            r1.a(r0)
            goto L_0x0219
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.z.b(android.content.Context, org.json.JSONObject):void");
    }

    public boolean a(Context context) {
        if (!bb.c().booleanValue()) {
            return false;
        }
        y a2 = y.a(context);
        long a3 = a2.a(g.LAST_UPDATE);
        long c = a2.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a3 > c) {
            al.c().a("need to update, checkWithLastUpdateTime lastUpdateTime =" + a3 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
            return true;
        }
        al.c().a("no need to update, checkWithLastUpdateTime lastUpdateTime =" + a3 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
        return false;
    }

    public boolean b(Context context) {
        return !y.a(context).a() || a(context);
    }
}
