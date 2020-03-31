package com.tencent.android.tpush.cloudctr.a;

import android.content.Context;
import android.os.Environment;
import com.tencent.android.tpush.b.a;
import com.tencent.mid.core.Constants;
import java.io.File;

/* compiled from: ProGuard */
public class b {
    private static boolean a(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            a.d("cc public storage", "checkPermission error", th);
            return false;
        }
    }

    private static boolean a(Context context) {
        return a(context, Constants.PERMISSION_WRITE_EXTERNAL_STORAGE) && "mounted".equals(Environment.getExternalStorageState());
    }

    private static boolean b(Context context) {
        return a(context, "android.permission.READ_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState());
    }

    private static File c(Context context) {
        if (!a(context) || !b(context)) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory(), ".mtacc");
        boolean exists = file.exists();
        if (exists && !file.isDirectory()) {
            file.delete();
            exists = false;
        }
        if (exists || file.mkdir()) {
        }
        return file;
    }

    public static boolean a(Context context, File file) {
        if (c(context) == null) {
            return false;
        }
        if (com.tencent.android.tpush.cloudctr.b.a.a(file, new File(Environment.getExternalStorageDirectory(), ".mtacc/" + file.getName()))) {
            return true;
        }
        a.i("cc public storage", "cache File: " + file.getName() + " error");
        return false;
    }

    public static String a(Context context, String str, String str2) {
        if (c(context) == null) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory(), ".mtacc/" + str);
        if (!file.exists()) {
            return null;
        }
        if (file.isDirectory()) {
            file.delete();
            return null;
        } else if (com.tencent.android.tpush.cloudctr.b.b.a(str2, file)) {
            return file.getAbsolutePath();
        } else {
            file.delete();
            return null;
        }
    }
}
