package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class d extends b {
    Long a = null;
    String b;
    String m;
    public long n = 0;
    public long o = 0;

    public d(Context context, String str, String str2, int i, Long l, long j) {
        super(context, i, j);
        this.m = str;
        this.b = str2;
        this.a = l;
    }

    public EventType b() {
        return EventType.PAGE_VIEW;
    }

    public boolean a(JSONObject jSONObject) {
        l.a(jSONObject, "pi", this.b);
        l.a(jSONObject, "rf", this.m);
        if (this.a != null) {
            jSONObject.put("du", this.a);
        }
        if (this.n > 0) {
            l.a(jSONObject, MessageKey.MSG_ID, this.n);
        }
        if (this.o > 0) {
            l.a(jSONObject, MessageKey.MSG_BUSI_MSG_ID, this.o);
        }
        return true;
    }
}
