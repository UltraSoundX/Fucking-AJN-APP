package com.baidu.location.indoor.mapversion.a;

import com.baidu.mobstat.Config;
import org.json.JSONObject;

class c {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private double h;
    private double i;

    public c() {
    }

    public c(JSONObject jSONObject) {
        this.a = jSONObject.optString("bldg");
        this.b = jSONObject.optString("guid");
        this.c = jSONObject.optString("building_bid");
        this.d = jSONObject.optString("poi_guid");
        this.e = jSONObject.optString("poi_bid");
        this.f = jSONObject.optString("name");
        this.g = jSONObject.optString("floor");
        this.h = jSONObject.optDouble(Config.EVENT_HEAT_X);
        this.i = jSONObject.optDouble("y");
    }

    public static String a(String str) {
        return str.toLowerCase().replaceAll("[^a-zA-Z0-9]+", "");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public double e() {
        return this.h;
    }

    public double f() {
        return this.i;
    }
}
