package com.tencent.android.tpush.c;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class a {
    protected JSONObject a = null;
    protected String b = null;
    protected String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;

    public abstract int c();

    /* access modifiers changed from: protected */
    public abstract void d();

    protected a(String str) {
        this.b = str;
    }

    public String a() {
        return this.c;
    }

    public void b() {
        try {
            this.a = new JSONObject(this.b);
        } catch (Exception e2) {
            try {
                this.a = new JSONObject(this.b.substring(this.b.indexOf("{"), this.b.lastIndexOf("}") + 1));
            } catch (Exception e3) {
                try {
                    this.a = new JSONObject(this.b.substring(1));
                } catch (Exception e4) {
                    try {
                        this.a = new JSONObject(this.b.substring(2));
                    } catch (Exception e5) {
                        try {
                            this.a = new JSONObject(this.b.substring(3));
                        } catch (Exception e6) {
                        }
                    }
                }
            }
        }
        try {
            if (!this.a.isNull("title")) {
                this.d = this.a.getString("title");
            }
            if (!this.a.isNull("content")) {
                this.e = this.a.getString("content");
            }
            if (!this.a.isNull("custom_content")) {
                String optString = this.a.optString("custom_content", "");
                if (optString != null && !optString.trim().equals("{}")) {
                    this.f = optString;
                }
            }
            if (!this.a.isNull(MessageKey.MSG_ACCEPT_TIME)) {
                this.g = this.a.optString(MessageKey.MSG_ACCEPT_TIME, "");
            }
        } catch (Throwable th) {
        }
        d();
        this.c = l.a(this.b).toUpperCase();
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BaseMessageHolder [msgJson=").append(this.a).append(", msgJsonStr=").append(this.b).append(", title=").append(this.d).append(", content=").append(this.e).append(", customContent=").append(this.f).append(", acceptTime=").append(this.g).append("]");
        return sb.toString();
    }
}
