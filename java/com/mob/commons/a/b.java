package com.mob.commons.a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.d;
import com.mob.commons.g;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ReflectHelper;
import com.tencent.android.tpush.common.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: ArtClt */
public class b extends d {
    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.artc_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.A() > 0;
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(1);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                long A = a.A();
                if (A <= 0 || !a(A)) {
                    f();
                    return;
                } else {
                    a(1, A);
                    return;
                }
            default:
                return;
        }
    }

    private boolean a(long j) {
        List list;
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        try {
            Object systemServiceSafe = DeviceHelper.getInstance(MobSDK.getContext()).getSystemServiceSafe("usagestats");
            if (systemServiceSafe == null) {
                return false;
            }
            ReflectHelper.importClass("android.app.usage.UsageStatsManager");
            List list2 = null;
            if (VERSION.SDK_INT >= 21) {
                list2 = (List) ReflectHelper.invokeInstanceMethod(systemServiceSafe, "queryUsageStats", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(System.currentTimeMillis()));
            }
            if (VERSION.SDK_INT < 28 && (list2 == null || list2.isEmpty())) {
                Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(ReflectHelper.getInstanceField(systemServiceSafe, "mService"), "queryUsageStats", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(System.currentTimeMillis()), "com.android.settings");
                if (invokeInstanceMethod != null) {
                    list = (List) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, "getList", new Object[0]);
                    if (list != null || list.isEmpty()) {
                        return false;
                    }
                    long e = g.e();
                    long a = a.a();
                    if (e > 0 && a < e) {
                        return true;
                    }
                    HashMap hashMap4 = null;
                    HashMap hashMap5 = null;
                    int size = list.size();
                    ReflectHelper.importClass("android.app.usage.UsageStats");
                    PackageManager packageManager = MobSDK.getContext().getPackageManager();
                    int i = size - 1;
                    while (i >= 0) {
                        Object obj = list.get(i);
                        if (VERSION.SDK_INT >= 21) {
                            long longValue = ((Long) ReflectHelper.invokeInstanceMethod(obj, "getLastTimeUsed", new Object[0])).longValue();
                            if (longValue > 0) {
                                String str = (String) ReflectHelper.invokeInstanceMethod(obj, "getPackageName", new Object[0]);
                                if (a(packageManager, str)) {
                                    hashMap = hashMap5;
                                    hashMap2 = hashMap4;
                                } else {
                                    if (hashMap5 == null) {
                                        hashMap3 = new HashMap();
                                    } else {
                                        hashMap3 = hashMap5;
                                    }
                                    Long l = (Long) hashMap3.get(str);
                                    if (l == null || l.longValue() <= longValue) {
                                        hashMap3.put(str, Long.valueOf(longValue));
                                        Object invokeInstanceMethod2 = ReflectHelper.invokeInstanceMethod(obj, "getFirstTimeStamp", new Object[0]);
                                        Object invokeInstanceMethod3 = ReflectHelper.invokeInstanceMethod(obj, "getLastTimeStamp", new Object[0]);
                                        Object invokeInstanceMethod4 = ReflectHelper.invokeInstanceMethod(obj, "getTotalTimeInForeground", new Object[0]);
                                        Object obj2 = null;
                                        Object obj3 = null;
                                        if (VERSION.SDK_INT < 28) {
                                            obj3 = ReflectHelper.getInstanceField(obj, "mLaunchCount");
                                            obj2 = ReflectHelper.getInstanceField(obj, "mLastEvent");
                                        }
                                        HashMap hashMap6 = new HashMap();
                                        hashMap6.put(Constants.FLAG_PACKAGE_NAME, str);
                                        hashMap6.put("firstTimeStamp", invokeInstanceMethod2);
                                        hashMap6.put("lastTimeStamp", invokeInstanceMethod3);
                                        hashMap6.put("lastTimeUsed", Long.valueOf(longValue));
                                        hashMap6.put("totalTimeInForeground", invokeInstanceMethod4);
                                        if (obj3 != null) {
                                            hashMap6.put("launchCount", obj3);
                                        }
                                        if (obj2 != null) {
                                            hashMap6.put("lastEvent", obj2);
                                        }
                                        if (hashMap4 == null) {
                                            hashMap2 = new HashMap();
                                        } else {
                                            hashMap2 = hashMap4;
                                        }
                                        hashMap2.put(str, hashMap6);
                                        hashMap = hashMap3;
                                    } else {
                                        hashMap = hashMap3;
                                        hashMap2 = hashMap4;
                                    }
                                }
                                i--;
                                hashMap4 = hashMap2;
                                hashMap5 = hashMap;
                            }
                        }
                        hashMap = hashMap5;
                        hashMap2 = hashMap4;
                        i--;
                        hashMap4 = hashMap2;
                        hashMap5 = hashMap;
                    }
                    if (hashMap4 == null || hashMap4.isEmpty()) {
                        return false;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Entry value : hashMap4.entrySet()) {
                        arrayList.add(value.getValue());
                    }
                    if (arrayList.size() > 0) {
                        HashMap hashMap7 = new HashMap();
                        hashMap7.put("type", "XM_APP_RUNTIMES");
                        hashMap7.put("list", arrayList);
                        hashMap7.put("datetime", Long.valueOf(a.a()));
                        com.mob.commons.b.a().a(a.a(), hashMap7);
                        g.c(a.a() + j);
                        return true;
                    }
                    return false;
                }
            }
            list = list2;
            if (list != null) {
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private boolean a(PackageManager packageManager, String str) {
        boolean z;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            boolean z2 = (packageInfo.applicationInfo.flags & 1) == 1;
            if ((packageInfo.applicationInfo.flags & 128) == 1) {
                z = true;
            } else {
                z = false;
            }
            if (z2 || z) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return true;
        }
    }
}
