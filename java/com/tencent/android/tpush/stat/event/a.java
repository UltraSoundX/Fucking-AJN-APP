package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a extends b {
    protected C0072a a = new C0072a();
    protected long b = -1;

    /* renamed from: com.tencent.android.tpush.stat.event.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public static class C0072a {
        public String a;
        public JSONArray b;
        public JSONObject c = null;
        public boolean d = false;

        public C0072a(String str, String[] strArr, Properties properties) {
            this.a = str;
            if (properties != null) {
                this.c = new JSONObject(properties);
            } else if (strArr != null) {
                this.b = new JSONArray();
                for (String put : strArr) {
                    this.b.put(put);
                }
            } else {
                this.c = new JSONObject();
            }
        }

        public C0072a() {
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.a).append(StorageInterface.KEY_SPLITER);
            if (this.b != null) {
                sb.append(this.b.toString());
            }
            if (this.c != null) {
                sb.append(this.c.toString());
            }
            return sb.toString();
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0072a)) {
                return false;
            }
            return toString().equals(((C0072a) obj).toString());
        }
    }

    public C0072a a() {
        return this.a;
    }

    public a(Context context, String str, JSONObject jSONObject, String str2, boolean z) {
        super(context, str2);
        this.a.a = str;
        this.a.c = jSONObject;
        this.a.d = z;
    }

    public a(Context context, int i, String str, long j, long j2) {
        super(context, i, j);
        this.a.a = str;
        this.k = j2;
    }

    public EventType b() {
        return EventType.CUSTOM;
    }

    public boolean a(JSONObject jSONObject) {
        jSONObject.put("ei", this.a.a);
        if (this.b > 0) {
            jSONObject.put("du", this.b);
        }
        if (this.a.b != null) {
            jSONObject.put("ar", this.a.b);
        } else if (this.a.d) {
            jSONObject.put("kv2", this.a.c);
        } else {
            jSONObject.put("kv", this.a.c);
        }
        return true;
    }
}
