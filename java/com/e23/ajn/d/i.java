package com.e23.ajn.d;

import android.os.Environment;
import com.e23.ajn.app.App;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/* compiled from: FileUtil */
public class i {
    static {
        a(c());
    }

    public static void a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String a() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return b();
    }

    public static String b() {
        App instance = App.getInstance();
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return instance.getCacheDir() + "";
        }
        return Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + instance.getPackageName() + "/cache/");
    }

    public static String c() {
        return b() + "data/";
    }

    public static File b(String str) {
        File file = new File(a());
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file + File.separator + str + ".apk");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file2;
    }

    public static String a(double d) {
        double d2 = d / 1024.0d;
        if (d2 == 0.0d) {
            return "0byte";
        }
        if (d2 < 1.0d) {
            return d + "Byte";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "KB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "MB";
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            return new BigDecimal(Double.toString(d4)).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d5).setScale(2, 4).toPlainString() + "TB";
    }

    public static String d() {
        return "ajn";
    }

    public static File e() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), d());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String f() {
        e();
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + d() + "/";
    }
}
