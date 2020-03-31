package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.baidu.mobstat.ay.b;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    static d a = new d();

    static class a {
        private String a;
        private String b;
        private String c;
        private String d;

        public a(String str, String str2, String str3, String str4) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            if (str3 == null) {
                str3 = "";
            }
            if (str4 == null) {
                str4 = "";
            }
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", this.a);
                jSONObject.put("v", this.b);
                jSONObject.put("c", this.c);
                jSONObject.put(Config.APP_VERSION_CODE, this.d);
                return jSONObject;
            } catch (JSONException e) {
                al.c().b((Throwable) e);
                return null;
            }
        }
    }

    public synchronized void a(Context context) {
        b(context);
    }

    private void b(Context context) {
        a(context, c(context));
    }

    private ArrayList<a> c(Context context) {
        String str;
        ArrayList<a> arrayList = new ArrayList<>();
        Iterator it = d(context).iterator();
        while (it.hasNext()) {
            PackageInfo packageInfo = (PackageInfo) it.next();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null) {
                String str2 = packageInfo.packageName;
                String str3 = packageInfo.versionName;
                String str4 = "";
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr == null || signatureArr.length == 0) {
                    str = str4;
                } else {
                    str = signatureArr[0].toChars().toString();
                }
                String a2 = b.a(str.getBytes());
                String str5 = "";
                String str6 = applicationInfo.sourceDir;
                if (!TextUtils.isEmpty(str6)) {
                    str5 = b.a(new File(str6));
                }
                arrayList.add(new a(str2, str3, a2, str5));
            }
        }
        return arrayList;
    }

    private ArrayList<PackageInfo> d(Context context) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        List<PackageInfo> arrayList2 = new ArrayList<>(1);
        try {
            arrayList2 = packageManager.getInstalledPackages(64);
        } catch (Exception e) {
            al.c().b((Throwable) e);
        }
        for (PackageInfo packageInfo : arrayList2) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    private void a(Context context, ArrayList<a> arrayList) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        String str2 = "";
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject a2 = ((a) it.next()).a();
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_apk", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            str = com.baidu.mobstat.ar.a.a(jSONObject.toString().getBytes());
        } catch (Exception e) {
            al.c().b((Throwable) e);
            str = str2;
        }
        if (!TextUtils.isEmpty(str)) {
            k.APP_APK.a(System.currentTimeMillis(), str);
        }
    }
}
