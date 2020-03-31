package com.tencent.android.tpush.stat.b;

import android.text.TextUtils;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.api.MidEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class d {
    private String a = null;
    private String b = null;
    private String c = null;
    private String d = "0";
    private long e = 0;
    private int f = 0;
    private long g = 0;

    public long a() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }

    public long b() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public boolean c() {
        return c.a(this.d);
    }

    public static d a(String str) {
        d dVar = new d();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(MidEntity.TAG_IMEI)) {
                    dVar.c(jSONObject.getString(MidEntity.TAG_IMEI));
                }
                if (!jSONObject.isNull(MidEntity.TAG_IMSI)) {
                    dVar.d(jSONObject.getString(MidEntity.TAG_IMSI));
                }
                if (!jSONObject.isNull(MidEntity.TAG_MAC)) {
                    dVar.e(jSONObject.getString(MidEntity.TAG_MAC));
                }
                if (!jSONObject.isNull("mid")) {
                    dVar.b(jSONObject.getString("mid"));
                }
                if (!jSONObject.isNull(MidEntity.TAG_TIMESTAMPS)) {
                    dVar.b(jSONObject.getLong(MidEntity.TAG_TIMESTAMPS));
                }
                if (!jSONObject.isNull(MidEntity.TAG_VER)) {
                    dVar.f = jSONObject.optInt(MidEntity.TAG_VER, 0);
                }
                if (!jSONObject.isNull("guid")) {
                    dVar.g = jSONObject.optLong("guid", 0);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return dVar;
    }

    public String toString() {
        return d().toString();
    }

    private void a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject != null && !i.b(str) && !i.b(str2)) {
            try {
                jSONObject.put(str, str2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            a(jSONObject, MidEntity.TAG_IMEI, this.a);
            a(jSONObject, MidEntity.TAG_IMSI, this.b);
            a(jSONObject, MidEntity.TAG_MAC, this.c);
            a(jSONObject, "mid", this.d);
            try {
                jSONObject.put("guid", this.g);
            } catch (Throwable th) {
            }
            jSONObject.put(MidEntity.TAG_TIMESTAMPS, this.e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String e() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.a = str;
    }

    public void d(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.c = str;
    }
}
