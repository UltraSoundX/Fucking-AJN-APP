package com.mob.commons.a;

import android.content.pm.PackageInfo;
import android.os.Message;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.h;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SharePrefrenceHelper;
import com.tencent.mid.api.MidEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: VplClt */
public class p extends d {
    private SharePrefrenceHelper a;

    /* access modifiers changed from: protected */
    public File a() {
        return i();
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.D() > 0;
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(0);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 0) {
            long D = a.D();
            if (D > 0) {
                try {
                    Thread.sleep(D * 1000);
                    HashMap l = l();
                    if (!(l == null || l.isEmpty() || b(l) == null)) {
                        a(null);
                    }
                } catch (Throwable th) {
                }
                n();
                this.a = null;
                a(0, a.E() * 1000);
            }
        }
    }

    private synchronized void m() {
        if (this.a == null) {
            this.a = new SharePrefrenceHelper(MobSDK.getContext());
            this.a.open("vpl_cache");
        }
    }

    private void n() {
        boolean z;
        boolean z2;
        try {
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            HashMap hashMap = new HashMap();
            String appkey = MobSDK.getAppkey();
            String authorize = DeviceAuthorizer.authorize(null);
            hashMap.put("appkey", appkey);
            hashMap.put("apppkg", instance.getPackageName());
            hashMap.put("appver", Integer.valueOf(instance.getAppVersion()));
            hashMap.put("duid", authorize);
            hashMap.put("plat", Integer.valueOf(instance.getPlatformCode()));
            hashMap.put("networktype", instance.getNetworkType());
            hashMap.put("lastVplAt", Long.valueOf(j()));
            String encodeToString = Base64.encodeToString((appkey + Config.TRACE_TODAY_VISIT_SPLIT + authorize).getBytes("utf-8"), 2);
            hashMap.put("lastVplId", encodeToString);
            HashMap hashMap2 = (HashMap) a(hashMap, h.a("v.data.mob.com/vpl"));
            if (hashMap2 != null && hashMap2.size() != 0) {
                k();
                List<String> list = (List) hashMap2.get("pkgs");
                if (list != null && list.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : list) {
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("apppkg", str);
                        try {
                            PackageInfo packageInfo = MobSDK.getContext().getPackageManager().getPackageInfo(str, 0);
                            hashMap3.put("appver", packageInfo.versionName);
                            boolean z3 = (packageInfo.applicationInfo.flags & 1) == 1;
                            if ((packageInfo.applicationInfo.flags & 128) == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                            String str2 = "issys";
                            if (z3 || z) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            hashMap3.put(str2, Boolean.valueOf(z2));
                        } catch (Throwable th) {
                        }
                        arrayList.add(hashMap3);
                    }
                    hashMap.remove("networktype");
                    hashMap.remove("lastVplAt");
                    hashMap.remove("lastVplId");
                    hashMap.put(MidEntity.TAG_MAC, instance.getMacAddress());
                    hashMap.put("model", instance.getModel());
                    hashMap.put(MidEntity.TAG_IMEI, instance.getIMEI());
                    hashMap.put("serialno", instance.getSerialno());
                    hashMap.put("datetime", Long.valueOf(a.a()));
                    hashMap.put(Config.FEED_LIST_ITEM_CUSTOM_ID, encodeToString);
                    hashMap.put("pkgs", arrayList);
                    Object b = b(hashMap);
                    if (b == null) {
                        b = b(hashMap);
                    }
                    if (b == null) {
                        a(hashMap);
                    }
                }
            }
        } catch (Throwable th2) {
            MobLog.getInstance().d(th2);
        }
    }

    private Object b(HashMap<String, Object> hashMap) {
        try {
            hashMap.put("cplAt", Long.valueOf(a.a()));
            return a(hashMap, h.a("v.data.mob.com/cpl"));
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private static Object a(HashMap<String, Object> hashMap, String str) throws Throwable {
        if (a.I()) {
            return null;
        }
        return new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1").requestSynchronized(hashMap, str, false);
    }

    public File i() {
        File file = new File(ResHelper.getDataCache(MobSDK.getContext()), ".vpl_lock");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public synchronized long j() {
        m();
        return this.a.getLong("LAST_UPLOAD_TIME");
    }

    public synchronized void k() {
        m();
        this.a.putLong("LAST_UPLOAD_TIME", Long.valueOf(a.a()));
    }

    public synchronized HashMap<String, Object> l() {
        m();
        return (HashMap) this.a.get("LAST_FAILED_DATA");
    }

    public synchronized void a(HashMap<String, Object> hashMap) {
        m();
        if (hashMap == null) {
            this.a.remove("LAST_FAILED_DATA");
        } else {
            this.a.put("LAST_FAILED_DATA", hashMap);
        }
    }
}
