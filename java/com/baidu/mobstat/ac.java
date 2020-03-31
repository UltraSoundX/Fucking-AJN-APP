package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

public class ac {
    public boolean a = false;
    public String b = "";
    public boolean c = false;

    public ac() {
    }

    public ac(JSONObject jSONObject) {
        try {
            this.a = jSONObject.getBoolean("SDK_BPLUS_SERVICE");
        } catch (Exception e) {
            al.c().b((Throwable) e);
        }
        try {
            this.b = jSONObject.getString("SDK_PRODUCT_LY");
        } catch (Exception e2) {
            al.c().b((Throwable) e2);
        }
        try {
            this.c = jSONObject.getBoolean("SDK_LOCAL_SERVER");
        } catch (Exception e3) {
            al.c().b((Throwable) e3);
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SDK_BPLUS_SERVICE", this.a);
        } catch (JSONException e) {
            al.c().b((Throwable) e);
        }
        try {
            jSONObject.put("SDK_PRODUCT_LY", this.b);
        } catch (JSONException e2) {
            al.c().b((Throwable) e2);
        }
        try {
            jSONObject.put("SDK_LOCAL_SERVER", this.c);
        } catch (JSONException e3) {
            al.c().b((Throwable) e3);
        }
        return jSONObject;
    }
}
