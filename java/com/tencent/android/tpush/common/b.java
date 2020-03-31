package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.tencent.android.tpush.b.a;
import java.io.Closeable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    public static boolean a(JSONObject jSONObject, String str, Object obj) {
        if (obj != null) {
            try {
                jSONObject.put(str, obj);
                return true;
            } catch (JSONException e) {
            }
        }
        return false;
    }

    public static Object b(JSONObject jSONObject, String str, Object obj) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.get(str);
            }
            return obj;
        } catch (JSONException e) {
            return obj;
        }
    }

    public static Object a(Context context, String str, Object obj) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return obj;
            }
            Object obj2 = applicationInfo.metaData.get(str);
            if (obj2 != null) {
                return obj2;
            }
            return obj;
        } catch (Throwable th) {
            a.d(Constants.LogTag, "", th);
            return obj;
        }
    }

    public static boolean a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }
}
