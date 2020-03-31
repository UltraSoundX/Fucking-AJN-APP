package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.mid.api.MidEntity;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class b {
    public static String g = "xgsdk";
    protected static String i = null;
    protected static long j = 0;
    protected String c = null;
    protected long d = 0;
    protected long e;
    protected int f;
    protected String h = null;
    protected long k = 0;
    protected Context l;

    public abstract boolean a(JSONObject jSONObject);

    public abstract EventType b();

    public b(Context context, int i2, long j2) {
        this.c = "Axg" + j2;
        a(context, i2, j2);
    }

    public b(Context context, String str) {
        this.c = str;
        a(context, 0, this.d);
    }

    private void a(Context context, int i2, long j2) {
        this.l = context;
        this.d = j2;
        this.e = System.currentTimeMillis() / 1000;
        this.f = i2;
        this.h = l.f(context);
        if (i == null || i.trim().length() < 40) {
            i = XGPushConfig.getToken(context);
            if (!c.b(i)) {
                i = "0";
            }
        }
        if (j == 0) {
            j = CacheManager.getGuid(c());
        }
    }

    public Context c() {
        return this.l;
    }

    public boolean b(JSONObject jSONObject) {
        try {
            f.a(jSONObject, "ky", this.c);
            jSONObject.put("et", b().GetIntValue());
            jSONObject.put("ui", f.a(this.l));
            f.a(jSONObject, Config.DEVICE_MAC_ID, CustomDeviceInfos.getMacAddress(this.l));
            jSONObject.put("ut", 1);
            if (b() != EventType.SESSION_ENV) {
                f.a(jSONObject, "av", this.h);
                f.a(jSONObject, "ch", g);
            }
            f.a(jSONObject, "mid", i);
            jSONObject.put("si", this.f);
            if (b() == EventType.CUSTOM) {
                jSONObject.put("cts", this.e);
                if (this.k != 0 || this.e == 0) {
                    jSONObject.put(MidEntity.TAG_TIMESTAMPS, this.k);
                } else {
                    jSONObject.put(MidEntity.TAG_TIMESTAMPS, this.e);
                }
            } else {
                jSONObject.put(MidEntity.TAG_TIMESTAMPS, this.e);
            }
            if ("0".equals(c.a(this.l, this.d))) {
                jSONObject.put("sv", c.a(this.l));
            } else {
                jSONObject.put("sv", c.a(this.l, this.d));
            }
            jSONObject.put("guid", j);
            jSONObject.put("dts", c.a(this.l, false));
            return a(jSONObject);
        } catch (Throwable th) {
            return false;
        }
    }

    public String d() {
        try {
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public String toString() {
        return d();
    }
}
