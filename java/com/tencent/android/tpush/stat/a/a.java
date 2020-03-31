package com.tencent.android.tpush.stat.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.stat.event.b;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    static C0071a a;
    private static d d = c.b();
    private static JSONObject e = new JSONObject();
    Integer b = null;
    String c = null;

    /* renamed from: com.tencent.android.tpush.stat.a.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    static class C0071a {
        String a;
        String b;
        DisplayMetrics c;
        int d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        int k;
        String l;
        Context m;
        long n;
        private String o;
        private String p;

        private C0071a(Context context, long j2) {
            this.b = "2.0.6";
            this.d = VERSION.SDK_INT;
            this.e = Build.MODEL;
            this.f = Build.MANUFACTURER;
            this.g = Locale.getDefault().getLanguage();
            this.k = 0;
            this.l = null;
            this.m = null;
            this.o = null;
            this.p = null;
            this.n = 0;
            this.m = StubApp.getOrigApplicationContext(context.getApplicationContext());
            this.c = DeviceInfos.getDisplayMetrics(this.m);
            this.a = c.b(this.m, j2);
            this.h = CustomDeviceInfos.getSimOperator(this.m);
            this.i = TimeZone.getDefault().getID();
            this.j = DeviceInfos.getExternalStorageInfo(this.m);
            this.l = this.m.getPackageName();
            this.o = DeviceInfos.getSystemMemory(this.m);
            this.p = DeviceInfos.getRomMemory();
            this.n = j2;
        }

        /* access modifiers changed from: 0000 */
        public void a(JSONObject jSONObject, Thread thread) {
            if (thread == null) {
                if (this.c != null) {
                    jSONObject.put("sr", this.c.widthPixels + "*" + this.c.heightPixels);
                    jSONObject.put("dpi", this.c.xdpi + "*" + this.c.ydpi);
                }
                if (com.tencent.android.tpush.stat.a.a(this.m).b()) {
                    JSONObject jSONObject2 = new JSONObject();
                    f.a(jSONObject2, "bs", CustomDeviceInfos.getWiFiBBSID(this.m));
                    f.a(jSONObject2, "ss", CustomDeviceInfos.getWiFiSSID(this.m));
                    if (jSONObject2.length() > 0) {
                        f.a(jSONObject, "wf", jSONObject2.toString());
                    }
                }
                JSONArray wifiTopN = CustomDeviceInfos.getWifiTopN(this.m, 10);
                if (wifiTopN != null && wifiTopN.length() > 0) {
                    f.a(jSONObject, "wflist", wifiTopN.toString());
                }
            } else {
                f.a(jSONObject, "thn", thread.getName());
                if (c.b(this.o) && this.o.split("/").length == 2) {
                    f.a(jSONObject, "fram", this.o.split("/")[0]);
                }
                if (c.b(this.p) && this.p.split("/").length == 2) {
                    f.a(jSONObject, "from", this.p.split("/")[0]);
                }
                jSONObject.put("ui", CustomDeviceInfos.getDeviceId(this.m));
                f.a(jSONObject, "mid", XGPushConfig.getToken(this.m));
            }
            f.a(jSONObject, "pcn", c.c(this.m));
            f.a(jSONObject, "osn", VERSION.RELEASE);
            f.a(jSONObject, "av", this.a);
            f.a(jSONObject, "ch", b.g);
            f.a(jSONObject, "mf", this.f);
            if (this.n > 0) {
                f.a(jSONObject, "sv", c.a(this.m, this.n));
            }
            f.a(jSONObject, "osd", Build.DISPLAY);
            f.a(jSONObject, "prod", Build.PRODUCT);
            f.a(jSONObject, "tags", Build.TAGS);
            f.a(jSONObject, Config.FEED_LIST_ITEM_CUSTOM_ID, Build.ID);
            f.a(jSONObject, "fng", Build.FINGERPRINT);
            f.a(jSONObject, "ov", Integer.toString(this.d));
            jSONObject.put("os", 1);
            f.a(jSONObject, Config.OPERATOR, this.h);
            f.a(jSONObject, "lg", this.g);
            f.a(jSONObject, "md", this.e);
            f.a(jSONObject, "tz", this.i);
            if (this.k != 0) {
                jSONObject.put("jb", this.k);
            }
            f.a(jSONObject, Config.FEED_LIST_MAPPING, this.j);
            f.a(jSONObject, "abi", Build.CPU_ABI);
            f.a(jSONObject, "ram", this.o);
            f.a(jSONObject, Config.ROM, this.p);
        }
    }

    static synchronized C0071a a(Context context, long j) {
        C0071a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new C0071a(StubApp.getOrigApplicationContext(context.getApplicationContext()), j);
            }
            aVar = a;
        }
        return aVar;
    }

    public a(Context context, long j) {
        try {
            a(context, j);
            this.b = DeviceInfos.getTelephonyNetworkType(StubApp.getOrigApplicationContext(context.getApplicationContext()));
            this.c = com.tencent.android.tpush.stat.a.a(context).a();
        } catch (Throwable th) {
            d.b(th);
        }
    }

    public void a(JSONObject jSONObject, Thread thread) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (a != null) {
                a.a(jSONObject2, thread);
            }
            f.a(jSONObject2, "cn", this.c);
            if (this.b != null) {
                jSONObject2.put("tn", this.b);
            }
            if (thread == null) {
                jSONObject.put(Config.EVENT_PART, jSONObject2);
            } else {
                jSONObject.put("errkv", jSONObject2.toString());
            }
            if (e != null && e.length() > 0) {
                jSONObject.put("eva", e);
            }
        } catch (Throwable th) {
            d.b(th);
        }
    }
}
