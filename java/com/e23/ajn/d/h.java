package com.e23.ajn.d;

import android.os.Environment;
import com.e23.ajn.app.App;
import com.stub.StubApp;
import java.io.File;

/* compiled from: FileCache */
public class h {
    static {
        a(b());
        a(c());
        a(d());
        a(f());
        a(e());
    }

    public static void a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String a() {
        App instance = App.getInstance();
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return instance.getCacheDir() + "";
        }
        return Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + instance.getPackageName() + "/cache/");
    }

    public static String b() {
        return a() + "data/";
    }

    public static String c() {
        return b() + "image_cache/";
    }

    public static String d() {
        return b() + "image_download/";
    }

    public static String e() {
        return a() + "collect_cache";
    }

    public static String f() {
        return a() + "images/";
    }

    public static String g() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public static double h() {
        File databasePath = StubApp.getOrigApplicationContext(App.getInstance().getApplicationContext()).getDatabasePath("love_ji_nan.db");
        if (databasePath == null) {
            return 0.0d;
        }
        new StringBuilder(6);
        return (double) databasePath.length();
    }

    public static String i() {
        return i.a(h() + ((double) j.a().d(StubApp.getOrigApplicationContext(App.getInstance().getApplicationContext()))));
    }
}
