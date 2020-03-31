package com.tencent.bigdata.dataacquisition.b;

import android.content.Context;
import android.util.Log;

public class a {
    private static boolean a = false;

    public static void a(Object obj) {
        if (a) {
            Log.w("DataAcq", "" + obj);
        }
    }

    public static void a(String str, Throwable th) {
        Log.e("DataAcq", "" + str, th);
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            a("checkPermission error", th);
            return false;
        }
    }

    public static void b(Object obj) {
        Log.e("DataAcq", "" + obj);
    }
}
