package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

public class LaunchInfo {
    private String a;
    private String b;
    private String c;

    public String getPushLandingPage() {
        String str = "";
        if (!TextUtils.isEmpty(this.a)) {
            return this.a;
        }
        return str;
    }

    public String getPushContent() {
        String str = "";
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        return str;
    }

    public String getRefererPkgName() {
        String str = "";
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        }
        return str;
    }

    public void setPushInfo(String str, String str2) {
        this.a = str;
        this.b = bc.a(str2, 1024);
    }

    public void setRefererPkgName(String str) {
        this.c = str;
    }

    public int getLaunchType(Context context) {
        if (!TextUtils.isEmpty(this.a)) {
            return 2;
        }
        String str = "";
        if (context != null) {
            str = context.getPackageName();
        }
        if (!TextUtils.isEmpty(this.c) && !this.c.equals(str)) {
            String a2 = ap.a(context);
            if (!TextUtils.isEmpty(a2)) {
                if (!this.c.equals(a2)) {
                    return 1;
                }
            } else if (!ap.a(context, this.c)) {
                return 1;
            }
        }
        return 0;
    }

    public static JSONObject getConvertedJson(int i, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", String.valueOf(i));
                String str3 = Config.LAUNCH_REFERER;
                if (str == null) {
                    str = "";
                }
                jSONObject.put(str3, str);
                String str4 = Config.LAUNCH_INFO;
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str4, str2);
                jSONObject.put("content", "");
                return jSONObject;
            } catch (Exception e) {
                return jSONObject;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static String getLauncherHomePkgName(Context context) {
        String str = "";
        String a2 = ap.a(context);
        return !TextUtils.isEmpty(a2) ? a2 : str;
    }
}
