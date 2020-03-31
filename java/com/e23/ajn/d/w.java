package com.e23.ajn.d;

import android.widget.Toast;
import com.e23.ajn.app.App;

/* compiled from: ToastUtil */
public class w {
    protected static Toast a = null;
    private static long b = 0;
    private static long c = 0;

    public static void a(String str) {
        Toast.makeText(App.softApplication, str, 0).show();
    }

    public static void b(String str) {
        Toast.makeText(App.softApplication, str, 1).show();
    }
}
