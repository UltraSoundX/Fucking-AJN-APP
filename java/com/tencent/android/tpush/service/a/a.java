package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    private static volatile a L = null;
    public int A = 60000;
    public int B = 1;
    public int C = 1;
    public int D = 1;
    public int E = -1;
    public int F = -1;
    public int G = -1;
    public int H = -1;
    public String I = "xiaomi";
    public JSONArray J = null;
    public Map<String, String> K;
    private Context M = null;
    public long a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;

    /* renamed from: q reason: collision with root package name */
    public int f430q;
    public int r;
    public int s;
    public int t;
    public String u;
    public int v;
    public int w;
    public String x = null;
    public int y = 1;
    public int z = 1;

    private a(Context context) {
        this.M = StubApp.getOrigApplicationContext(context.getApplicationContext());
        a();
    }

    public static a a(Context context) {
        if (L == null) {
            synchronized (a.class) {
                if (L == null) {
                    L = new a(context);
                }
            }
        }
        return L;
    }

    public String toString() {
        return "ConfigurationManager [context=" + this.M + ", configurationVersion=" + this.a + ", receiveTimeout=" + this.b + ", heartbeatInterval=" + this.c + ", httpHeartbeatInterval=" + this.d + ", speedTestInterval=" + this.e + ", channelMessageExpires=" + this.f + ", freqencySuccess=" + this.g + ", freqencyFailed=" + this.h + ", reportInterval=" + this.i + ", reportMaxCount=" + this.j + ", httpRetryCount=" + this.k + ", ackMaxCount=" + this.l + ", ackDuration=" + this.m + ", loadIpInerval=" + this.n + ", redirectConnectTimeOut=" + this.o + ", redirectSoTimeOut=" + this.p + ", strategyExpiredTime=" + this.f430q + ", logLevel=" + this.r + ", logFileSizeLimit=" + this.s + ", errCount=" + this.t + ", logUploadDomain=" + this.u + ", rptLive=" + this.v + ", rptLiveIntvl=" + this.w + ", disableXG=" + this.x + ", enableNewWd=" + this.y + ", enableMonitor=" + this.z + ", monitorFreg=" + this.A + ", enableReport=" + this.B + ", abTestVersion=" + this.C + ", isHttpDNSEnable=" + this.D + ", isLBSEnable=" + this.E + ", isAPPListEnable=" + this.F + ", isNotificatiobStatusEnable=" + this.G + ", isQgameEnable=" + this.H + ", pullup_Arr_ProviderAndActivty=" + this.J + ", pullup_packges_map=" + this.K + ", wakeupCtrl=" + this.I + "]";
    }

    public void a() {
        if (this.a == 0) {
            this.a = b();
            this.b = a("recTo", (int) Config.SESSION_PERIOD);
            this.c = a("hbIntvl", 299980);
            this.d = a("httpHbIntvl", 600000);
            this.e = a("stIntvl", 54000000);
            this.f = a("cnMsgExp", 60000);
            this.g = a("fqcSuc", 10);
            this.h = a("fqcFal", 100);
            this.i = a("rptIntvl", 1200);
            this.j = a("rptMaxCnt", 5);
            this.k = a("httpRtCnt", 3);
            this.l = a("ackMaxCnt", 3);
            this.m = a("ackDuration", 180000);
            this.n = a("loadIpIntvl", 72000000);
            this.F = a("conf_applist", -1);
            this.E = a("conf_lbs", -1);
            this.G = a("conf_nt_status", -1);
            this.o = a("redirectConnectTime", (int) Config.SESSION_PERIOD);
            this.p = a("redirectSoTime", 20000);
            this.f430q = a("strategyExpiredTime", 1440);
            this.v = a("rptLive", 0);
            this.w = a("rptLiveIntvl", 3600);
            this.s = a("logFileSizeLimit", 262144);
            this.t = a("errCount", 5);
            this.u = a("logUploadDomain", "183.61.46.193");
            this.x = a("stopXG", "");
            String a2 = a("pullup_packges", "");
            if (!l.c(a2)) {
                String decrypt = Rijndael.decrypt(a2);
                if (!l.c(decrypt)) {
                    this.K = b(decrypt);
                }
            }
            this.y = a("enableNewWd", 1);
            this.B = a("report", 1);
            this.C = a("ABT", 1);
            this.z = a("enable.monitor", 1);
            this.A = a("m.freq", 60000);
            this.D = a("httpdns", 1);
            this.H = a("conf_qgame", -1);
            this.I = a("wakeupCtr", "xiaomi");
            try {
                String a3 = a("conf_pull_arr", "");
                if (!l.c(a3)) {
                    String decrypt2 = Rijndael.decrypt(a3);
                    if (!l.c(decrypt2)) {
                        this.J = new JSONArray(decrypt2);
                    }
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "pullup_Arr_ProviderAndActivty.", th);
            }
        }
    }

    private a() {
    }

    public void a(String str) {
        int i2 = 3600;
        int i3 = 5;
        int i4 = 3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = (long) a("confVer", jSONObject);
            this.a = this.a == 0 ? 1 : this.a;
            this.b = a("recTo", jSONObject) * 1000;
            this.b = this.b == 0 ? Config.SESSION_PERIOD : this.b;
            this.c = a("hbIntvl", jSONObject) * 60 * 1000;
            this.c = this.c == 0 ? 299980 : this.c;
            this.d = a("httpHbIntvl", jSONObject) * 60 * 1000;
            this.d = this.d == 0 ? 600000 : this.d;
            this.e = a("stIntvl", jSONObject) * 60 * 1000;
            this.e = this.e == 0 ? 54000000 : this.e;
            this.f = a("cnMsgExp", jSONObject) * 1000;
            this.f = this.f == 0 ? 60000 : this.f;
            this.g = a("fqcSuc", jSONObject);
            this.g = this.g == 0 ? 10 : this.g;
            this.h = a("fqcFal", jSONObject);
            this.h = this.h == 0 ? 100 : this.h;
            this.i = a("rptIntvl", jSONObject);
            this.i = this.i == 0 ? 1200 : this.i;
            this.j = a("rptMaxCnt", jSONObject);
            this.j = this.j == 0 ? 5 : this.j;
            this.k = a("httpRtCnt", jSONObject);
            this.k = this.k == 0 ? 3 : this.k;
            this.l = a("ackMaxCnt", jSONObject);
            if (this.l != 0) {
                i4 = this.l;
            }
            this.l = i4;
            this.m = a("ackDuration", jSONObject) * 1000;
            this.m = this.m == 0 ? 180000 : this.m;
            this.n = a("loadIpIntvl", jSONObject) * 60 * 60 * 1000;
            this.n = this.n == 0 ? 72000000 : this.n;
            this.o = a("redirectConnectTime", jSONObject);
            this.o = this.o == 0 ? Config.SESSION_PERIOD : this.o;
            this.p = a("redirectSoTime", jSONObject);
            this.p = this.p == 0 ? 20000 : this.p;
            this.f430q = a("strategyExpiredTime", jSONObject);
            this.f430q = this.f430q == 0 ? 1440 : this.f430q;
            this.v = a("rptLive", jSONObject);
            this.v = this.v == 0 ? 0 : this.v;
            this.w = a("rptLiveIntvl", jSONObject);
            if (this.w != 3600) {
                i2 = this.w;
            }
            this.w = i2;
            this.r = a("logLevel", jSONObject);
            this.s = a("logFileSizeLimit", jSONObject) * 1024;
            this.s = this.s == 0 ? 262144 : this.s;
            this.t = a("errCount", jSONObject);
            if (this.t != 0) {
                i3 = this.t;
            }
            this.t = i3;
            this.u = b("logUploadDomain", jSONObject);
            this.u = TextUtils.isEmpty(this.u) ? "183.61.46.193" : this.u;
            this.y = jSONObject.optInt("enableNewWd", 1);
            this.B = jSONObject.optInt("report", 1);
            this.x = jSONObject.optString("stopXG", null);
            this.C = jSONObject.optInt("ABT", 1);
            this.z = jSONObject.optInt("enable.monitor", 1);
            this.A = jSONObject.optInt("m.freq", 60000);
            this.D = jSONObject.optInt("httpdns", 1);
            this.F = jSONObject.optInt("conf_applist", -1);
            this.E = jSONObject.optInt("conf_lbs", -1);
            this.G = jSONObject.optInt("conf_nt_status", -1);
            this.H = jSONObject.optInt("conf_qgame", -1);
            this.I = jSONObject.optString("wakeupCtr", "xiaomi");
            String optString = jSONObject.optString("st.kv", "");
            String optString2 = jSONObject.optString("sp.kv", "");
            String optString3 = jSONObject.optString("pullup_packges", "");
            if (!l.c(optString3)) {
                this.K = b(optString3);
                h.b(c(), c("pullup_packges"), Rijndael.encrypt(optString3));
            }
            h.b(c(), c("confVer"), this.a);
            h.b(c(), c("recTo"), this.b);
            h.b(c(), c("hbIntvl"), this.c);
            h.b(c(), c("httpHbIntvl"), this.d);
            h.b(c(), c("stIntvl"), this.e);
            h.b(c(), c("cnMsgExp"), this.f);
            h.b(c(), c("fqcSuc"), this.g);
            h.b(c(), c("fqcFal"), this.h);
            h.b(c(), c("rptIntvl"), this.i);
            h.b(c(), c("rptMaxCnt"), this.j);
            h.b(c(), c("httpRtCnt"), this.k);
            h.b(c(), c("ackMaxCnt"), this.l);
            h.b(c(), c("ackDuration"), this.m);
            h.b(c(), c("loadIpIntvl"), this.n);
            h.b(c(), c("redirectConnectTime"), this.o);
            h.b(c(), c("redirectSoTime"), this.p);
            h.b(c(), c("strategyExpiredTime"), this.f430q);
            h.b(c(), c("rptLive"), this.v);
            h.b(c(), c("rptLiveIntvl"), this.w);
            h.b(c(), c("logLevel"), this.r);
            h.b(c(), c("logFileSizeLimit"), this.s);
            h.b(c(), c("errCount"), this.t);
            h.b(c(), c("conf_applist"), this.F);
            h.b(c(), c("conf_lbs"), this.E);
            h.b(c(), c("conf_nt_status"), this.G);
            h.b(c(), c("conf_qgame"), this.H);
            if (!i.b(this.x)) {
                h.b(c(), c("stopXG"), Rijndael.encrypt(this.x));
            }
            h.b(c(), c("enableNewWd"), this.y);
            h.b(c(), c("report"), this.B);
            h.b(c(), c("enable.monitor"), this.z);
            h.b(c(), c("m.freq"), this.A);
            h.b(c(), c("httpdns"), this.D);
            h.b(c(), c("wakeupCtr"), this.I);
            if (!TextUtils.isEmpty(optString)) {
                b.b(c(), optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                b.a(c(), optString2);
            }
            this.J = jSONObject.optJSONArray("conf_pull_arr");
            if (this.J != null && this.J.length() > 0) {
                h.b(c(), c("conf_pull_arr"), Rijndael.encrypt(this.J.toString()));
            } else if (this.J != null && this.J.length() == 0) {
                System.err.println("pullup_Arr_ProviderAndActivty length is 0 remove the old arr");
                this.J = null;
                h.a(c(), c("conf_pull_arr"));
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "parseValue failed.", th);
        }
    }

    public long b() {
        if (this.M != null) {
            return h.a(this.M, c("confVer"), 1);
        }
        return 1;
    }

    public void a(long j2) {
        if (this.M != null && b() != j2) {
            h.b(this.M, c("confVer"), j2);
        }
    }

    public Map<String, String> b(String str) {
        try {
            if (l.c(str)) {
                return null;
            }
            String[] split = str.split(StorageInterface.KEY_SPLITER);
            if (split == null || split.length <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (String split2 : split) {
                String[] split3 = split2.split("/");
                if (split3 != null && split3.length >= 2) {
                    hashMap.put(split3[0], split3[1]);
                }
            }
            return hashMap;
        } catch (Throwable th) {
            return null;
        }
    }

    private Context c() {
        if (this.M != null) {
            return this.M;
        }
        if (b.f() != null) {
            this.M = b.f();
            return this.M;
        }
        if (this.M == null && XGPushManager.getContext() != null) {
            this.M = XGPushManager.getContext();
        }
        return this.M;
    }

    public int a(String str, int i2) {
        return h.a(c(), c(str), i2);
    }

    public String a(String str, String str2) {
        String a2 = h.a(c(), c(str), str2);
        return TextUtils.isEmpty(a2) ? str2 : a2;
    }

    public int a(String str, JSONObject jSONObject) {
        if (jSONObject != null && !i.b(str)) {
            try {
                return jSONObject.getInt(str);
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getJsonInt", e2);
            }
        }
        return 0;
    }

    public String b(String str, JSONObject jSONObject) {
        if (jSONObject != null && !i.b(str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "getJsonStr", e2);
            }
        }
        return "";
    }

    public String c(String str) {
        return "com.tencent.tpus." + str;
    }
}
