package com.tencent.android.tpush.stat;

import android.content.Context;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.android.tpush.stat.a.d;
import com.tencent.android.tpush.stat.a.e;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    static a a = new a(2);
    static a b = new a(1);
    static String c = "__HIBERNATE__";
    static volatile String d = "pingma.qq.com:80";
    private static d e = c.b();
    private static StatReportStrategy f = StatReportStrategy.APP_LAUNCH;
    private static boolean g = false;
    private static boolean h = true;
    private static volatile String i = "http://pingma.qq.com:80/mstat/report";
    private static boolean j = false;
    private static short k = 6;
    private static int l = 1024;
    private static int m = Config.SESSION_PERIOD;
    private static int n = 0;
    private static int o = 20;

    /* compiled from: ProGuard */
    static class a {
        int a;
        JSONObject b = new JSONObject();
        String c = "";
        int d = 0;

        public a(int i) {
            this.a = i;
        }
    }

    public static StatReportStrategy a() {
        return f;
    }

    public static void a(StatReportStrategy statReportStrategy) {
        f = statReportStrategy;
        if (b()) {
            e.h("Change to statSendStrategy: " + statReportStrategy);
        }
    }

    public static boolean b() {
        return g;
    }

    public static boolean c() {
        Context context = XGPushManager.getContext();
        if (context == null) {
            context = XGPushServiceV4.b();
        }
        if (context == null) {
            return h;
        }
        if (!h || com.tencent.android.tpush.service.a.a.a(StubApp.getOrigApplicationContext(context.getApplicationContext())).B != 1) {
            return false;
        }
        return true;
    }

    public static void a(boolean z) {
        h = z;
        if (!z) {
            e.c("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    static void a(Context context, JSONObject jSONObject) {
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(Integer.toString(b.a))) {
                    a(context, b, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase(Integer.toString(a.a))) {
                    a(context, a, jSONObject.getJSONObject(str));
                }
            }
        } catch (JSONException e2) {
            e.b((Throwable) e2);
        }
    }

    static void a(Context context, a aVar, JSONObject jSONObject) {
        boolean z;
        boolean z2 = false;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase("v")) {
                    int i2 = jSONObject.getInt(str);
                    if (aVar.d != i2) {
                        z = true;
                    } else {
                        z = z2;
                    }
                    aVar.d = i2;
                } else if (str.equalsIgnoreCase("c")) {
                    String string = jSONObject.getString("c");
                    if (string.length() > 0) {
                        aVar.b = new JSONObject(string);
                    }
                    z = z2;
                } else {
                    if (str.equalsIgnoreCase(Config.MODEL)) {
                        aVar.c = jSONObject.getString(Config.MODEL);
                    }
                    z = z2;
                }
                z2 = z;
            }
            if (z2 && aVar.a == b.a) {
                a(aVar.b);
                b(aVar.b);
            }
            a(context, aVar);
        } catch (JSONException e2) {
            e.b((Throwable) e2);
        } catch (Throwable th) {
            e.b(th);
        }
    }

    static void a(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                a(statReportStrategy);
            }
        } catch (JSONException e2) {
            if (b()) {
                e.b((Object) "rs not found.");
            }
        }
    }

    static void a(Context context, a aVar) {
        if (aVar.a == b.a) {
            b = aVar;
            a(b.b);
        } else if (aVar.a == a.a) {
            a = aVar;
        }
    }

    static void b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                String string = jSONObject.getString(c);
                if (b()) {
                    e.h("hibernateVer:" + string + ", current version:" + "2.0.6");
                }
                long a2 = c.a(string);
                if (c.a("2.0.6") <= a2) {
                    a(a2);
                }
            } catch (JSONException e2) {
                e.h("__HIBERNATE__ not found.");
            }
        }
    }

    static void a(long j2) {
        e.b(d.a(), c, j2);
        a(false);
        e.c("MTA is disable for current SDK version");
    }

    public static String d() {
        return i;
    }

    public static boolean e() {
        return j;
    }

    public static void b(boolean z) {
        j = z;
    }

    public static int f() {
        return l;
    }
}
