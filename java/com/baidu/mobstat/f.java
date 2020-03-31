package com.baidu.mobstat;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class f {
    static f a = new f();
    private String b = "";

    static class a {
        private String a;
        private String b;
        private String c;

        public a(String str, String str2, String str3) {
            if (str == null) {
                str = "";
            }
            this.a = str;
            if (str2 == null) {
                str2 = "";
            }
            this.b = str2;
            if (str3 == null) {
                str3 = "";
            }
            this.c = str3;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", this.a);
                jSONObject.put("v", this.b);
                jSONObject.put(Config.DEVICE_WIDTH, this.c);
                return jSONObject;
            } catch (JSONException e) {
                al.c().b((Throwable) e);
                return null;
            }
        }

        public String b() {
            return this.a;
        }
    }

    f() {
    }

    public synchronized void a(Context context, boolean z) {
        int i = 1;
        if (!z) {
            i = 20;
        }
        a(context, z, i);
    }

    private void a(Context context, boolean z, int i) {
        ArrayList a2 = a(context, i);
        if (a2 != null && a2.size() != 0) {
            if (z) {
                String b2 = ((a) a2.get(0)).b();
                if (a(b2, this.b)) {
                    this.b = b2;
                }
            }
            a(context, a2, z);
        }
    }

    private ArrayList<a> a(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return c(context, i);
        }
        return b(context, i);
    }

    private ArrayList<a> b(Context context, int i) {
        List<RunningTaskInfo> list;
        try {
            list = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(50);
        } catch (Exception e) {
            al.c().b((Throwable) e);
            list = null;
        }
        if (list == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (RunningTaskInfo runningTaskInfo : list) {
            if (linkedHashMap.size() > i) {
                break;
            }
            ComponentName componentName = runningTaskInfo.topActivity;
            if (componentName != null) {
                String packageName = componentName.getPackageName();
                if (!TextUtils.isEmpty(packageName) && !b(context, packageName) && !linkedHashMap.containsKey(packageName)) {
                    linkedHashMap.put(packageName, new a(packageName, a(context, packageName), ""));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    private ArrayList<a> c(Context context, int i) {
        List runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < runningAppProcesses.size() && linkedHashMap.size() <= i; i2++) {
            RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) runningAppProcesses.get(i2);
            if (a(runningAppProcessInfo.importance)) {
                String[] strArr = runningAppProcessInfo.pkgList;
                if (!(strArr == null || strArr.length == 0)) {
                    String str = runningAppProcessInfo.pkgList[0];
                    if (!TextUtils.isEmpty(str) && !b(context, str) && !linkedHashMap.containsKey(str)) {
                        linkedHashMap.put(str, new a(str, a(context, str), String.valueOf(runningAppProcessInfo.importance)));
                    }
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    private boolean a(int i) {
        if (i == 100 || i == 200 || i == 130) {
            return true;
        }
        return false;
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || str.equals(this.b)) {
            return false;
        }
        return true;
    }

    private String a(Context context, String str) {
        String str2 = "";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str2;
        }
        try {
            str2 = packageManager.getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            al.c().b((Throwable) e);
        }
        return str2 == null ? "" : str2;
    }

    private boolean b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo == null || (applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            al.c().b((Throwable) e);
            return false;
        }
    }

    private void a(Context context, ArrayList<a> arrayList, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() + "|");
        sb.append(z ? 1 : 0);
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
            jSONObject.put("app_trace", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            str = com.baidu.mobstat.ar.a.a(jSONObject.toString().getBytes());
        } catch (Exception e) {
            al.c().b((Throwable) e);
            str = str2;
        }
        if (!TextUtils.isEmpty(str)) {
            k.APP_TRACE.a(System.currentTimeMillis(), str);
        }
    }
}
