package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.tencent.android.tpush.stat.a.a;
import com.tencent.android.tpush.stat.a.c;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class e extends b {
    private a a;
    private JSONObject b = null;

    public e(Context context, int i, JSONObject jSONObject, long j) {
        super(context, i, j);
        this.a = new a(context, j);
        this.b = jSONObject;
    }

    public EventType b() {
        return EventType.SESSION_ENV;
    }

    public boolean a(JSONObject jSONObject) {
        jSONObject.put("ut", 1);
        if (this.b != null) {
            jSONObject.put("cfg", this.b);
        }
        if (c.d(this.l)) {
            jSONObject.put("ncts", 1);
        }
        this.a.a(jSONObject, (Thread) null);
        return true;
    }
}
