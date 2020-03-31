package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Session {
    private volatile long a = 0;
    private volatile long b = 0;
    private volatile long c = 0;
    private volatile long d = 0;
    private volatile long e = 0;
    private volatile int f = 0;
    private List<a> g = new ArrayList();
    private volatile JSONObject h = null;

    static class a {
        /* access modifiers changed from: private */
        public String a;
        private String b;
        private String c;
        private long d;
        private long e;
        /* access modifiers changed from: private */
        public boolean f;
        private JSONObject g;
        /* access modifiers changed from: private */
        public boolean h;

        public a(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2) {
            this.b = str;
            this.c = str2;
            this.a = str3;
            this.d = j;
            this.e = j2;
            this.f = z;
            JSONObject jSONObject = new JSONObject();
            if (extraInfo != null) {
                jSONObject = extraInfo.dumpToJson();
            }
            this.g = jSONObject;
            this.h = z2;
        }

        public String a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }

        public long c() {
            return this.d;
        }

        public long d() {
            return this.e;
        }

        public JSONObject e() {
            return this.g;
        }

        public boolean f() {
            return this.f;
        }

        public void a(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
            this.f = aVar.f;
            this.g = aVar.g;
            this.h = aVar.h;
        }
    }

    public void reset() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.f = 0;
        this.g.clear();
    }

    public void setTrackStartTime(long j) {
        if (this.c <= 0) {
            this.c = j;
        }
    }

    public void setTrackEndTime(long j) {
        this.d = j;
    }

    public void setInvokeType(int i) {
        this.f = i;
    }

    public void addPageView(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2) {
        a(this.g, new a(str, str2, str3, j, j2, z, extraInfo, z2));
    }

    public void addPageView(a aVar) {
        a(this.g, aVar);
    }

    private void a(List<a> list, a aVar) {
        if (list != null && aVar != null) {
            int size = list.size();
            if (size == 0) {
                list.add(aVar);
                return;
            }
            a aVar2 = (a) list.get(size - 1);
            if (TextUtils.isEmpty(aVar2.a) || TextUtils.isEmpty(aVar.a)) {
                list.add(aVar);
            } else if (!aVar2.a.equals(aVar.a) || aVar2.f == aVar.f) {
                list.add(aVar);
            } else if (aVar2.f) {
                aVar2.a(aVar);
            }
        }
    }

    public void setStartTime(long j) {
        if (this.a <= 0) {
            this.a = j;
            this.e = j;
        }
    }

    public long getStartTime() {
        return this.a;
    }

    public boolean hasStart() {
        return this.a > 0;
    }

    public boolean hasEnd() {
        return this.b > 0;
    }

    public void setEndTime(long j) {
        this.b = j;
    }

    public void setLaunchInfo(JSONObject jSONObject) {
        this.h = jSONObject;
    }

    public JSONObject constructJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.a);
            jSONObject.put("e", this.b);
            jSONObject.put("i", this.e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.c == 0 ? this.a : this.c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.d == 0 ? this.b : this.d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f);
            if (!(this.h == null || this.h.length() == 0)) {
                jSONObject.put(Config.LAUNCH, this.h);
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.size(); i++) {
                jSONArray.put(getPVJson((a) this.g.get(i), this.a));
            }
            jSONObject.put("p", jSONArray);
        } catch (JSONException e2) {
        }
        return jSONObject;
    }

    public JSONObject getPageSessionHead() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.a);
            jSONObject.put("e", this.b);
            jSONObject.put("i", this.e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.c == 0 ? this.a : this.c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.d == 0 ? this.b : this.d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f);
        } catch (Exception e2) {
        }
        return jSONObject;
    }

    public String toString() {
        return constructJSONObject().toString();
    }

    public static JSONObject getPVJson(a aVar, long j) {
        int i;
        long j2 = 0;
        int i2 = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", aVar.a());
            jSONObject.put("d", aVar.c());
            long d2 = aVar.d() - j;
            String str = "ps";
            if (d2 >= 0) {
                j2 = d2;
            }
            jSONObject.put(str, j2);
            jSONObject.put("t", aVar.b());
            String str2 = "at";
            if (aVar.f()) {
                i = 1;
            } else {
                i = 0;
            }
            jSONObject.put(str2, i);
            JSONObject e2 = aVar.e();
            if (!(e2 == null || e2.length() == 0)) {
                jSONObject.put("ext", e2);
            }
            String str3 = "h5";
            if (!aVar.h) {
                i2 = 0;
            }
            jSONObject.put(str3, i2);
        } catch (JSONException e3) {
        }
        return jSONObject;
    }
}
