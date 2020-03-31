package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h {
    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", VERSION.SDK_INT);
            jSONObject.put("sv", VERSION.RELEASE);
            jSONObject.put(Config.CUID_SEC, bb.a(2, context));
            jSONObject.put(Config.DEVICE_WIDTH, bb.c(context));
            jSONObject.put("h", bb.d(context));
            jSONObject.put("ly", ab.c);
            jSONObject.put("pv", "33");
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                jSONObject.put(Config.PACKAGE_NAME, bb.h(2, context));
                jSONObject.put(Config.APP_VERSION_CODE, packageInfo.versionCode);
                jSONObject.put("n", packageInfo.versionName);
            } catch (Exception e) {
                al.c().a((Throwable) e);
            }
            jSONObject.put(Config.DEVICE_MAC_ID, bb.b(2, context));
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, bb.f(2, context));
            jSONObject.put(Config.MODEL, Build.MODEL);
            jSONObject.put(Config.DEVICE_NAME, bb.a(context, 2));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            jSONObject2.put("send_index", 0);
            String b = bb.b();
            String str = Config.ROM;
            if (b == null) {
                b = "";
            }
            jSONObject2.put(str, b);
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (JSONException e2) {
            al.c().b((Throwable) e2);
        }
        return jSONObject;
    }

    public static JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("payload");
            if (jSONArray == null || jSONArray.length() <= 0) {
                jSONObject3 = null;
            } else {
                jSONObject3 = (JSONObject) jSONArray.get(0);
            }
            jSONObject2 = jSONObject3 != null ? jSONObject3.getJSONObject(Config.HEADER_PART) : null;
        } catch (Exception e) {
            jSONObject2 = null;
        }
        return jSONObject2;
    }

    public static void b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
        } catch (Exception e) {
        }
    }

    public static void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put("send_index", jSONObject2.getLong("send_index") + 1);
        } catch (Exception e) {
        }
    }
}
