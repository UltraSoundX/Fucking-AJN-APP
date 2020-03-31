package com.tencent.android.tpush.cloudctr.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
public class a {
    private static String a = "CloudControlCacheMgr";
    private Map<String, String> b = new ConcurrentHashMap();

    private static String a(Context context) {
        return context.getPackageName() + ".mta.cloudctr";
    }

    private static String a(String str) {
        return str + ".ver";
    }

    private static String b(String str) {
        return str + ".downloadJson";
    }

    private static String b(String str, long j) {
        return String.valueOf(j) + str;
    }

    private static void c(String str) {
        File file = new File(str);
        boolean exists = file.exists();
        if (exists && !file.isDirectory()) {
            file.delete();
            exists = false;
        }
        if (!exists) {
            file.mkdir();
        }
    }

    public void a(String str, String str2) {
        if (this.b.containsKey(str)) {
            com.tencent.android.tpush.b.a.i(a, str + " 业务已有 cacheDir, 这次设置无效");
        } else if (str2 == null) {
            com.tencent.android.tpush.b.a.i(a, str + " 业务 cacheDir 为空");
        } else {
            c(str2.endsWith(File.separator) ? str2 + str : str2 + File.separator + str);
            this.b.put(str, str2);
        }
    }

    public long a(Context context, String str) {
        return context.getSharedPreferences(a(context), 0).getLong(a(str), 0);
    }

    public void a(Context context, String str, long j, boolean z) {
        if (((String) this.b.get(str)) != null) {
            long a2 = a(context, str);
            if (a2 != j) {
                Editor edit = context.getSharedPreferences(a(context), 0).edit();
                edit.putLong(a(str), j);
                edit.commit();
                if (z) {
                    String a3 = a(str, a2);
                    if (a3 != null) {
                        new File(a3).delete();
                    }
                }
            }
        }
    }

    public void a(Context context, String str, String str2) {
        String b2 = b(str);
        Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.putString(b2, str2);
        edit.commit();
    }

    public void b(Context context, String str) {
        String b2 = b(str);
        Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.remove(b2);
        edit.commit();
    }

    public String c(Context context, String str) {
        return context.getSharedPreferences(a(context), 0).getString(b(str), null);
    }

    public String d(Context context, String str) {
        return a(str, a(context, str));
    }

    public String a(String str, long j) {
        String str2 = (String) this.b.get(str);
        if (str2 == null) {
            return null;
        }
        String str3 = str2 + File.separator + str + File.separator + String.valueOf(j) + File.separator;
        File file = new File(str3);
        boolean exists = file.exists();
        if (exists && !file.isDirectory()) {
            file.delete();
            exists = false;
        }
        if (exists) {
            return str3;
        }
        file.mkdir();
        return str3;
    }

    public Date a(Context context, String str, long j) {
        long j2 = context.getSharedPreferences(a(context), 0).getLong(b(str, j), 0);
        if (j2 == 0) {
            return null;
        }
        return new Date(j2);
    }

    public void a(Context context, String str, long j, Date date) {
        long time = date.getTime();
        Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.putLong(b(str, j), time);
        edit.commit();
    }
}
