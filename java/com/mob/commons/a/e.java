package com.mob.commons.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Message;
import android.os.SystemClock;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.common.Constants;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: BsClt */
public class e extends d {
    /* access modifiers changed from: protected */
    public boolean b_() {
        boolean z = true;
        if (VERSION.SDK_INT >= 26) {
            MobLog.getInstance().d("[%s] %s", "BsClt", "API Level: " + VERSION.SDK_INT);
            return false;
        }
        if (a.Y() <= 0) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.bs_lock");
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (a.a() >= i()) {
            a(1, a.Y() * 1000);
            return;
        }
        MobLog.getInstance().d("[%s] %s", "BsClt", "Gap not reached");
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                c(150);
                a(a.a() + (a.Z() * 1000));
                return;
            default:
                return;
        }
    }

    private void c(int i) {
        try {
            Context context = MobSDK.getContext();
            if (VERSION.SDK_INT >= 26 || context == null) {
                MobLog.getInstance().d("[%s] %s", "BsClt", "API Level: " + VERSION.SDK_INT);
                return;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
            if (i <= 0) {
                i = 100;
            }
            List<RunningServiceInfo> runningServices = activityManager.getRunningServices(i);
            if (runningServices != null && !runningServices.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    String str = runningServiceInfo.process;
                    long j = runningServiceInfo.activeSince;
                    ComponentName componentName = runningServiceInfo.service;
                    String shortClassName = componentName.getShortClassName();
                    String packageName = componentName.getPackageName();
                    PackageManager packageManager = context.getPackageManager();
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                    if (!a(packageInfo)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("appLabel", applicationInfo.loadLabel(packageManager) + "");
                        hashMap.put("serviceName", shortClassName);
                        hashMap.put("pkgName", packageName);
                        hashMap.put("processName", str);
                        hashMap.put("versionName", packageInfo.versionName);
                        hashMap.put("versionCode", Integer.valueOf(packageInfo.versionCode));
                        hashMap.put("activeSince", Long.valueOf(j));
                        arrayList.add(hashMap);
                    }
                }
                MobLog.getInstance().d("[%s] %s", "BsClt", "to srv: " + runningServices.size() + ", ap srv: " + arrayList.size());
                HashMap hashMap2 = new HashMap();
                hashMap2.put("list", arrayList);
                hashMap2.put("elapsedRealtime", Long.valueOf(SystemClock.elapsedRealtime()));
                a(hashMap2);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "BsClt", th.getMessage() + "");
        }
    }

    private void a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.bscd")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "BsClt", th.getMessage());
        }
    }

    private long i() {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.bscd");
        if (cacheRootFile.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(cacheRootFile));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            } catch (Throwable th) {
                MobLog.getInstance().d(th, "[%s] %s", "BsClt", th.getMessage());
            }
        }
        return 0;
    }

    private boolean a(PackageInfo packageInfo) {
        boolean z;
        boolean z2;
        if ((packageInfo.applicationInfo.flags & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        if ((packageInfo.applicationInfo.flags & 128) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    private void a(HashMap<String, Object> hashMap) {
        long a = a.a();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", "RUN_SERVICE_LIST");
        hashMap2.put("data", hashMap);
        hashMap2.put("datetime", Long.valueOf(a));
        b.a().a(a, hashMap2);
    }
}
