package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.tencent.android.tpush.common.h;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    private String a = null;
    private Context b = null;

    public a(Context context, String str) {
        this.b = context;
        this.a = "com.tencent.xg.tpush.httpdns.cache." + str;
    }

    public void a(JSONObject jSONObject) {
        h.b(this.b, this.a, jSONObject.toString());
    }

    public String a() {
        return h.a(this.b, this.a, (String) null);
    }

    public String b() {
        c cVar;
        String a2 = a();
        if (a2 == null || a2.length() <= 7) {
            cVar = null;
        } else {
            cVar = c.a(a2);
        }
        if (cVar == null) {
            return null;
        }
        long b2 = cVar.b() - System.currentTimeMillis();
        com.tencent.android.tpush.b.a.c("httpDns", "cacheResult:" + cVar + ",diff:" + b2);
        if (b2 > 0) {
            return cVar.a();
        }
        com.tencent.android.tpush.b.a.g("httpDns", "cacheResult Exp.");
        return null;
    }
}
