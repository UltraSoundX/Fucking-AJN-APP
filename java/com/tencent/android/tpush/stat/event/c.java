package com.tencent.android.tpush.stat.event;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c extends a {
    public c(Context context, String str, JSONObject jSONObject, String str2, boolean z) {
        super(context, str, jSONObject, str2, z);
    }

    public EventType b() {
        return EventType.LBS;
    }

    public boolean a(JSONObject jSONObject) {
        jSONObject.put("ei", this.a.a);
        if (this.b > 0) {
            jSONObject.put("du", this.b);
        }
        if (this.a.b == null) {
            jSONObject.put("lbs", this.a.c);
        } else {
            jSONObject.put("ar", this.a.b);
        }
        return true;
    }
}
