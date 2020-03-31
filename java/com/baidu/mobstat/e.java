package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.baidu.mobstat.ar.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class e {
    static final e a = new e();

    e() {
    }

    public synchronized void a(Context context, boolean z) {
        b(context, z);
    }

    private void b(Context context, boolean z) {
        int i = 1;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            List<PackageInfo> arrayList = new ArrayList<>(1);
            try {
                arrayList = packageManager.getInstalledPackages(0);
            } catch (Exception e) {
                al.c().b((Throwable) e);
            }
            JSONArray jSONArray = new JSONArray();
            for (PackageInfo packageInfo : arrayList) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo != null) {
                    boolean z2 = (applicationInfo.flags & 1) != 0;
                    String charSequence = applicationInfo.loadLabel(packageManager).toString();
                    String str = applicationInfo.sourceDir;
                    if (z == z2) {
                        a(z, charSequence, str, packageInfo, jSONArray);
                    }
                }
            }
            if (jSONArray.length() != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis() + "|");
                if (!z) {
                    i = 0;
                }
                sb.append(i);
                String str2 = "";
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("app_list", jSONArray);
                    jSONObject.put("meta-data", sb.toString());
                    str2 = a.a(jSONObject.toString().getBytes());
                } catch (Exception e2) {
                }
                if (!TextUtils.isEmpty(str2)) {
                    k.APP_LIST.a(System.currentTimeMillis(), str2);
                }
            }
        }
    }

    private void a(boolean z, String str, String str2, PackageInfo packageInfo, JSONArray jSONArray) {
        long j;
        long j2 = 0;
        if (!z || !packageInfo.packageName.startsWith("com.android.")) {
            try {
                j = packageInfo.firstInstallTime;
            } catch (Throwable th) {
                al.c().b(th);
                j = j2;
            }
            try {
                j2 = packageInfo.lastUpdateTime;
            } catch (Throwable th2) {
                al.c().b(th2);
            }
            long a2 = a(str2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", packageInfo.packageName);
                jSONObject.put(Config.APP_VERSION_CODE, str);
                jSONObject.put("v", String.valueOf(packageInfo.versionName));
                jSONObject.put("f", j);
                jSONObject.put("l", j2);
                jSONObject.put(Config.MODEL, a2);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                al.c().b((Throwable) e);
            }
        }
    }

    private long a(String str) {
        if (str == null) {
            return 0;
        }
        File file = new File(str);
        if (file == null || !file.exists()) {
            return 0;
        }
        return file.lastModified();
    }
}
