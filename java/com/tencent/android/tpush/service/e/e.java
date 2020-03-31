package com.tencent.android.tpush.service.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.stub.StubApp;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public class e {
    private static volatile e a = null;
    private Context b = null;
    private Map<String, String> c = new HashMap(10);
    private Map<Long, String> d = new HashMap(10);

    private e(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.d.put(Long.valueOf(-1), "");
    }

    public static e a(Context context) {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e(context);
                }
            }
        }
        return a;
    }

    public String a(long j) {
        if (this.d.containsKey(Long.valueOf(j))) {
            return (String) this.d.get(Long.valueOf(j));
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(this.b);
        if (registerInfos != null) {
            for (String str : registerInfos) {
                RegisterEntity registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                if (registerInfoByPkgName != null) {
                    this.d.put(Long.valueOf(registerInfoByPkgName.accessId), a(str));
                }
            }
        }
        if (this.d.get(Long.valueOf(j)) == null) {
            return "";
        }
        return (String) this.d.get(Long.valueOf(j));
    }

    public String a(String str) {
        if (str == null) {
            return "";
        }
        if (this.c.containsKey(str)) {
            return (String) this.c.get(str);
        }
        List<PackageInfo> installedPackages = CustomDeviceInfos.getInstalledPackages(this.b);
        if (installedPackages != null) {
            for (PackageInfo packageInfo : installedPackages) {
                if (str.equals(packageInfo.packageName)) {
                    this.c.put(str, packageInfo.versionName);
                    return packageInfo.versionName;
                }
            }
        }
        return "";
    }
}
