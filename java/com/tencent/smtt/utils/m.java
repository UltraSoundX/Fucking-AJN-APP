package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileFilter;

/* compiled from: TbsCheckUtils */
public class m {
    private static File c(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_share");
        if (file == null) {
            return null;
        }
        if (!file.isDirectory() || !file.exists()) {
            return null;
        }
        return file;
    }

    public static boolean a(Context context) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return b(context);
        }
        return true;
    }

    public static boolean b(Context context) {
        File[] listFiles;
        try {
            if (VERSION.SDK_INT < 21 || VERSION.SDK_INT > 25) {
                return true;
            }
            File c = c(context);
            if (c == null) {
                return true;
            }
            for (File file : c.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    String name = file.getName();
                    return !TextUtils.isEmpty(name) && name.endsWith(".dex");
                }
            })) {
                if (file.isFile() && file.exists()) {
                    if (a(file)) {
                        TbsLog.w("TbsCheckUtils", "" + file + " is invalid --> check failed!");
                        file.delete();
                        return false;
                    }
                    TbsLog.i("TbsCheckUtils", "" + file + " #4 check success!");
                }
            }
            TbsLog.i("TbsCheckUtils", "checkTbsValidity -->#5 check ok!");
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean a(File file) {
        try {
            if (!e.b(file)) {
                return true;
            }
        } catch (Throwable th) {
            Log.e("TbsCheckUtils", "isOatFileBroken exception: " + th);
        }
        return false;
    }
}
