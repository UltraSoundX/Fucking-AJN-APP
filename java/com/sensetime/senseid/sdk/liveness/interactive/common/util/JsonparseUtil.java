package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonparseUtil {
    public static boolean getBoolean(String str, String str2) {
        return new JSONObject(str).getBoolean(str2);
    }

    public static double getDouble(String str, String str2) {
        return new JSONObject(str).getDouble(str2);
    }

    public static int getInt(String str, String str2) {
        return new JSONObject(str).getInt(str2);
    }

    public static long getLong(String str, String str2) {
        return new JSONObject(str).getLong(str2);
    }

    public static String getString(String str, String str2) {
        return new JSONObject(str).getString(str2);
    }

    public static boolean optBoolean(String str, String str2) {
        return optBoolean(str, str2, false);
    }

    public static boolean optBoolean(String str, String str2, boolean z) {
        try {
            return new JSONObject(str).optBoolean(str2, z);
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static double optDouble(String str, String str2) {
        return optDouble(str, str2, 0.0d);
    }

    public static double optDouble(String str, String str2, double d) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return d;
        }
        try {
            return new JSONObject(str).optDouble(str2, d);
        } catch (JSONException e) {
            e.printStackTrace();
            return d;
        }
    }

    public static int optInt(String str, String str2) {
        return optInt(str, str2, 0);
    }

    public static int optInt(String str, String str2, int i) {
        try {
            return new JSONObject(str).optInt(str2, i);
        } catch (JSONException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static long optLong(String str, String str2) {
        return optLong(str, str2, 0);
    }

    public static long optLong(String str, String str2, long j) {
        try {
            return new JSONObject(str).optLong(str2, j);
        } catch (JSONException e) {
            e.printStackTrace();
            return j;
        }
    }

    public static String optString(String str, String str2) {
        return optString(str, str2, "");
    }

    public static String optString(String str, String str2, String str3) {
        try {
            return new JSONObject(str).optString(str2, str3);
        } catch (JSONException e) {
            e.printStackTrace();
            return str3;
        }
    }
}
