package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.stub.StubApp;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class aq {
    private static String a;
    private WeakReference<WebView> b;
    private WeakReference<Activity> c;
    private JSONObject d;
    private boolean e;

    @JavascriptInterface
    public void setViewportTreeToNative(String str) {
        if (an.c().b()) {
            an.c().a("setViewportTreeToNative " + str);
        }
        a = str;
    }

    @JavascriptInterface
    public void setEventToNative(String str) {
        if (an.c().b() && this.e) {
            an.c().a("setEventToNative: " + str);
        }
        if (ao.c().b()) {
            ao.c().a("setEventToNative: " + str);
        }
        if (this.c != null) {
            Activity activity = (Activity) this.c.get();
            if (activity != null && this.b != null) {
                WebView webView = (WebView) this.b.get();
                if (webView != null) {
                    a(str, activity, webView);
                }
            }
        }
    }

    private void a(String str, Activity activity, WebView webView) {
        String str2;
        JSONArray jSONArray;
        boolean z;
        String str3;
        JSONArray jSONArray2 = null;
        String str4 = null;
        String str5 = null;
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            jSONArray2 = jSONObject2.optJSONArray(Config.EVENT_H5_VIEW_HIERARCHY);
            str4 = jSONObject2.optString(Config.EVENT_H5_PAGE);
            str5 = jSONObject2.optString("l");
            jSONObject = jSONObject2.optJSONObject(Config.EVENT_HEAT_POINT);
            str2 = str5;
            jSONArray = jSONArray2;
            z = true;
            str3 = str4;
        } catch (Exception e2) {
            str2 = str5;
            jSONArray = jSONArray2;
            z = false;
            str3 = str4;
        }
        if (z) {
            if (TextUtils.isEmpty(str3)) {
                str3 = "/";
            }
            JSONArray a2 = ap.a(activity, (View) webView);
            String name = activity.getClass().getName();
            String a3 = ap.a(a2);
            String b2 = ap.b(jSONArray);
            String d2 = ap.d((View) webView);
            Map e3 = ap.e(webView);
            Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
            String str6 = "";
            long currentTimeMillis = System.currentTimeMillis();
            if (a(this.d, activity.getClass().getName(), str3, a3, b2)) {
                BDStatCore.instance().onEvent(origApplicationContext, str6, str2, 1, System.currentTimeMillis(), a2, jSONArray, name, str3, d2, e3, true);
            } else if (an.c().b() && this.e) {
                an.c().a("setEventToNative: not circle event, will not take effect");
            }
            ai.a().a(origApplicationContext, str6, str2, 1, currentTimeMillis, name, a2, str3, jSONArray, d2, e3, true, jSONObject, "");
        }
    }

    private boolean a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        boolean z;
        boolean z2 = false;
        if (jSONObject == null || jSONObject.toString().equals(new JSONObject().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return false;
        }
        try {
            if (((JSONObject) jSONObject.get("meta")).getInt("matchAll") != 0) {
                return true;
            }
        } catch (Exception e2) {
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("data");
            int i = 0;
            while (i < jSONArray.length()) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                String optString = jSONObject2.optString("page");
                String optString2 = jSONObject2.optString("layout");
                String str5 = (String) jSONObject2.opt("url");
                String str6 = (String) jSONObject2.opt("webLayout");
                if (!str.equals(optString) || !str2.equals(str5) || !str3.equals(optString2) || !str4.equals(str6)) {
                    z = z2;
                } else {
                    z = true;
                }
                i++;
                z2 = z;
            }
        } catch (Exception e3) {
        }
        return z2;
    }
}
