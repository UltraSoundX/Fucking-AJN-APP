package com.a.a.a;

import com.baidu.mobstat.Config;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    public boolean a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    private final String n;

    public a(String str) {
        this.n = str;
        n();
    }

    private void n() {
        try {
            if (this.n != null && this.n.length() != 0) {
                JSONObject jSONObject = new JSONObject(this.n);
                this.a = Boolean.parseBoolean(jSONObject.getString("t"));
                this.b = jSONObject.getString("e");
                this.c = jSONObject.getString("i");
                this.d = jSONObject.getString("d");
                this.e = jSONObject.getString("ks");
                this.f = jSONObject.getString(Config.STAT_SDK_CHANNEL);
                this.g = jSONObject.getString(Config.DEVICE_MAC_ID);
                this.h = jSONObject.getString("an");
                this.i = jSONObject.getString("av");
                this.j = jSONObject.getString("s");
                this.k = jSONObject.getString("sr");
                this.m = jSONObject.getString("v");
                this.l = jSONObject.getString("p");
            }
        } catch (JSONException e2) {
            this.m = "";
            this.l = "";
            e2.printStackTrace();
        }
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public String j() {
        return this.k;
    }

    public String k() {
        return this.j;
    }

    public String l() {
        return this.l;
    }

    public String m() {
        return this.m;
    }
}
