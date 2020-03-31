package com.tencent.bigdata.customdataacquisition.b;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    private static boolean a = false;

    public static void a(Object obj) {
        if (a) {
            Log.i("CustomDataAcq", "" + obj);
        }
    }

    public static void a(String str, Throwable th) {
        Log.e("CustomDataAcq", "" + str, th);
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            a("checkPermission error", th);
            return false;
        }
    }

    public static boolean a(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() <= 0;
    }

    public static boolean a(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() <= 0;
    }

    public static void b(Object obj) {
        if (a) {
            Log.d("CustomDataAcq", "" + obj);
        }
    }

    public static void c(Object obj) {
        if (a) {
            Log.w("CustomDataAcq", "" + obj);
        }
    }

    public static void d(Object obj) {
        Log.e("CustomDataAcq", "" + obj);
    }
}
