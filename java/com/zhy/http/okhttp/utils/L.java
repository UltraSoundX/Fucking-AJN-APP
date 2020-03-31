package com.zhy.http.okhttp.utils;

import android.util.Log;

public class L {
    private static boolean debug = false;

    public static void e(String str) {
        if (debug) {
            Log.e("OkHttp", str);
        }
    }
}
