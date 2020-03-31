package com.e23.ajn.ccb.d;

import com.b.a.e;
import com.b.a.r;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JsonUtils */
public class h {
    private static boolean a = true;

    public static int a(JSONObject jSONObject, String str, Integer num) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return num.intValue();
        }
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e) {
            if (a) {
                e.printStackTrace();
            }
            return num.intValue();
        }
    }

    public static int a(String str, String str2, Integer num) {
        if (str == null || "".equals(str)) {
            return num.intValue();
        }
        try {
            return a(new JSONObject(str), str2, num);
        } catch (JSONException e) {
            if (a) {
                e.printStackTrace();
            }
            return num.intValue();
        }
    }

    public static String a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return str2;
        }
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            if (!a) {
                return str2;
            }
            e.printStackTrace();
            return str2;
        }
    }

    public static String a(String str, String str2, String str3) {
        if (str == null || "".equals(str)) {
            return str3;
        }
        try {
            return a(new JSONObject(str), str2, str3);
        } catch (JSONException e) {
            if (!a) {
                return str3;
            }
            e.printStackTrace();
            return str3;
        }
    }

    public static boolean a(JSONObject jSONObject, String str, Boolean bool) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return bool.booleanValue();
        }
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException e) {
            if (a) {
                e.printStackTrace();
            }
            return bool.booleanValue();
        }
    }

    public static boolean a(String str, String str2, Boolean bool) {
        if (str == null || "".equals(str)) {
            return bool.booleanValue();
        }
        try {
            return a(new JSONObject(str), str2, bool);
        } catch (JSONException e) {
            if (a) {
                e.printStackTrace();
            }
            return bool.booleanValue();
        }
    }

    public static <T> T a(String str, Class<T> cls) {
        T t = null;
        if (str == null || "".equals(str)) {
            return t;
        }
        try {
            return new e().a(str, cls);
        } catch (r e) {
            if (!a) {
                return t;
            }
            e.printStackTrace();
            return t;
        }
    }

    public static String a(Object obj) {
        return new e().a(obj);
    }
}
