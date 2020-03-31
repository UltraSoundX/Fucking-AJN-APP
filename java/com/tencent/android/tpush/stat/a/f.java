package com.tencent.android.tpush.stat.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class f {
    public static boolean a(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e("XgStat", "checkPermission error", th);
            return false;
        }
    }

    public static String a(Context context) {
        String a = e.a(context, "xg.mta.ui", "");
        if (TextUtils.isEmpty(a)) {
            a = CustomDeviceInfos.getDeviceId(context);
        }
        if (TextUtils.isEmpty(a)) {
            a = Integer.toString(new Random().nextInt(Integer.MAX_VALUE));
        }
        e.b(context, "xg.mta.ui", a);
        return a;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(b.b(Base64.decode(str.getBytes("UTF-8"), 0)), "UTF-8");
        } catch (Throwable th) {
            Log.e("XgStat", "decode error", th);
            return str;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(b.a(str.getBytes("UTF-8")), 0), "UTF-8");
        } catch (Throwable th) {
            Log.e("XgStat", "encode error", th);
            return str;
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
                Log.e("XgStat", "jsonPut error", th);
            }
        }
    }
}
