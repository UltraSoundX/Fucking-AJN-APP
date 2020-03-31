package com.tencent.android.tpush.common;

import android.content.Context;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.d.d;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class i {
    private static i a = null;
    private Context b = null;
    private String c = null;
    private String d = null;

    private i(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.c = l.f(context);
        this.d = String.valueOf(4.03f);
    }

    public static synchronized i a(Context context) {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new i(context);
            }
            iVar = a;
        }
        return iVar;
    }

    public String a() {
        int i;
        boolean z;
        int i2;
        boolean z2 = false;
        JSONObject jSONObject = new JSONObject();
        try {
            b.a(jSONObject, "appVer", (Object) this.c);
            b.a(jSONObject, "appSdkVer", (Object) this.d);
            b.a(jSONObject, "ch", (Object) XGPushConfig.getInstallChannel(this.b));
            b.a(jSONObject, "gs", (Object) XGPushConfig.getGameServer(this.b));
            if (k.a(this.b).c() || (XGPushConfig.isUsedOtherPush(this.b) && d.a(this.b).a())) {
                String f = d.a(this.b).f();
                String d2 = d.a(this.b).d();
                a.f(Constants.OTHER_PUSH_TAG, "Reservert info: other push token is : " + d2 + "  other push type: " + f);
                if (l.c(f) || l.c(d2)) {
                    z = false;
                } else {
                    b.a(jSONObject, f, (Object) d2);
                    z = true;
                }
                if (k.a(this.b).c()) {
                    i2 = 2;
                } else {
                    i2 = -1;
                }
                if (XGPushConfig.isUsedOtherPush(this.b)) {
                    i2 = 1;
                }
                i = i2;
                z2 = z;
            } else {
                i = 0;
            }
            if (!z2) {
                a.f(Constants.OTHER_PUSH_TAG, "Reservert info: use normal xg token register");
                b.a(jSONObject, "fcm", (Object) "");
                b.a(jSONObject, "miid", (Object) "");
            }
            int a2 = h.a(this.b, ".firstregister", 1);
            int a3 = h.a(this.b, ".usertype", 0);
            long a4 = h.a(this.b, ".installtime", 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (a4 == 0) {
                h.b(this.b, ".installtime", currentTimeMillis);
            } else {
                if (a3 == 0 && a2 != 1) {
                    if (!l.a(a4).equals(l.a(System.currentTimeMillis()))) {
                        h.b(this.b, ".usertype", 1);
                        currentTimeMillis = a4;
                        a3 = 1;
                    }
                }
                currentTimeMillis = a4;
            }
            jSONObject.put("ut", a3);
            if (a2 == 1) {
                jSONObject.put("freg", 1);
            }
            jSONObject.put("it", (int) (currentTimeMillis / 1000));
            if (l.b(this.b)) {
                jSONObject.put("aidl", 1);
            }
            jSONObject.put("push_type", i);
        } catch (Exception e) {
            a.d("RegisterReservedInfo", "toSting", e);
        }
        return jSONObject.toString();
    }
}
