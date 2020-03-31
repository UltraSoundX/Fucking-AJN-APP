package com.tencent.android.tpush.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.tencent.android.tpush.XGPushProvider;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.mid.api.MidProvider;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class a {
    private static volatile a a = null;
    private Context b = null;
    private PackageManager c = null;
    private HashMap<String, c> d = new HashMap<>();
    private Map<String, Long> e = new HashMap();

    private a(Context context) {
        this.b = context;
        this.c = context.getPackageManager();
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public void a() {
        try {
            b();
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "init", th);
        }
    }

    public void b() {
        List<ProviderInfo> queryContentProviders = this.c.queryContentProviders(null, 0, 0);
        List c2 = c();
        if (queryContentProviders != null && c2 != null) {
            for (ProviderInfo providerInfo : queryContentProviders) {
                if (c2.contains(providerInfo.packageName) && providerInfo.exported) {
                    c cVar = (c) this.d.get(providerInfo.packageName);
                    if (cVar == null) {
                        cVar = new c();
                    }
                    cVar.a(providerInfo.packageName);
                    String str = providerInfo.name;
                    if (str.equals(XGPushProvider.class.getName())) {
                        cVar.a(providerInfo);
                    } else if (str.equals(MidProvider.class.getName())) {
                        cVar.b(providerInfo);
                    } else {
                        cVar.c(providerInfo);
                    }
                    this.d.put(providerInfo.packageName, cVar);
                }
            }
        }
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> queryBroadcastReceivers = this.c.queryBroadcastReceivers(new Intent(Constants.ACTION_SDK_INSTALL), 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                arrayList.add(resolveInfo.resolvePackageName);
            }
        }
        Map a2 = com.tencent.android.tpush.a.a(this.b);
        if (a2 != null) {
            for (Entry entry : a2.entrySet()) {
                if (!arrayList.contains(entry.getKey())) {
                    arrayList.add(entry.getKey());
                }
            }
        }
        return arrayList;
    }

    public void a(String str) {
        try {
            if (d() && !this.b.getPackageName().equals(str)) {
                com.tencent.android.tpush.b.a.c(Constants.LogTag, "tryWakeUpApp packageName:" + str);
                c(str);
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "tryWakeUpApp", th);
        }
    }

    private boolean d() {
        String str = com.tencent.android.tpush.service.a.a.a(this.b).I;
        String str2 = Build.MANUFACTURER;
        com.tencent.android.tpush.b.a.c(Constants.LogTag, "wakeCtr:" + str + ",mf:" + str2);
        if (l.c(str) && l.c(str2)) {
            return false;
        }
        for (String lowerCase : str.split(StorageInterface.KEY_SPLITER)) {
            if (lowerCase.toLowerCase().equals(str2.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private void c(String str) {
        Long l = (Long) this.e.get(str);
        if (l == null || System.currentTimeMillis() <= l.longValue()) {
            c b2 = b(str);
            com.tencent.android.tpush.b.a.c(Constants.LogTag, "tryWakeUpApp ipcAppInfo:" + b2);
            if (b2 != null) {
                ArrayList<ProviderInfo> a2 = b2.a();
                com.tencent.android.tpush.b.a.c(Constants.LogTag, "tryWakeUpApp providerInfoList:" + a2);
                if (a2 != null) {
                    for (ProviderInfo providerInfo : a2) {
                        if (providerInfo.exported) {
                            Uri parse = Uri.parse("content://" + providerInfo.authority);
                            com.tencent.android.tpush.b.a.c(Constants.LogTag, "tryWakeUpApp uri:" + parse);
                            this.b.getContentResolver().getType(parse);
                        }
                    }
                }
            }
            this.e.put(str, Long.valueOf(System.currentTimeMillis() + 3600000));
        }
    }

    public c b(String str) {
        if (!this.d.containsKey(str)) {
            a();
        }
        return (c) this.d.get(str);
    }
}
