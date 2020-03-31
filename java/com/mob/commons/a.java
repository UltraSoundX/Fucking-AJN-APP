package com.mob.commons;

import android.location.Location;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.TbsMediaPlayer.TbsMediaPlayerListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: CommonConfig */
public class a {
    private static final String a = h.a("f.gm.mob.com/v5/gcf");
    /* access modifiers changed from: private */
    public static HashMap<String, Object> b = new HashMap<>();
    /* access modifiers changed from: private */
    public static boolean c = false;
    /* access modifiers changed from: private */
    public static volatile boolean d = false;
    private static int e = 0;
    private static Object f = new Object();
    private static HashMap<String, Object> g;
    private static HashMap<String, Object> h = null;
    private static Object i = new Object();
    private static long j = 0;

    public static long a() {
        long j2;
        try {
            j2 = Long.valueOf(String.valueOf(g.get("serverTime"))).longValue();
        } catch (Throwable th) {
            j2 = 0;
        }
        if (j2 == 0) {
            return System.currentTimeMillis();
        }
        return j2 + am();
    }

    private static long am() {
        long j2;
        try {
            j2 = Long.valueOf(String.valueOf(g.get("deviceTime"))).longValue();
        } catch (Throwable th) {
            j2 = 0;
        }
        if (j2 == 0) {
            return 0;
        }
        return SystemClock.elapsedRealtime() - j2;
    }

    public static boolean b() {
        return 1 == ((Integer) a("conn", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean c() {
        return 1 == ((Integer) a("rt", (T) Integer.valueOf(0))).intValue();
    }

    public static int d() {
        return ((Integer) a("rtsr", (T) Integer.valueOf(180))).intValue();
    }

    public static boolean e() {
        return 1 == ((Integer) a("in", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean f() {
        return 1 == ((Integer) a("all", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean g() {
        return 1 == ((Integer) a("un", (T) Integer.valueOf(0))).intValue();
    }

    public static long h() {
        return ((Long) a("aspa", (T) Long.valueOf(2592000))).longValue();
    }

    public static boolean i() {
        return 1 == ((Integer) a("di", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean j() {
        return 1 == ((Integer) a("ext", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean k() {
        return 1 == ((Integer) a("bs", (T) Integer.valueOf(0))).intValue();
    }

    public static int l() {
        return ((Integer) a("bsgap", (T) Integer.valueOf(86400))).intValue();
    }

    public static boolean m() {
        return 1 == ((Integer) a("l", (T) Integer.valueOf(0))).intValue();
    }

    public static int n() {
        return ((Integer) a("lgap", (T) Integer.valueOf(86400))).intValue();
    }

    public static boolean o() {
        return 1 == ((Integer) a("wi", (T) Integer.valueOf(0))).intValue();
    }

    public static int p() {
        return ((Integer) a("wigap", (T) Integer.valueOf(3600))).intValue();
    }

    public static boolean q() {
        return ((Integer) a("wl", (T) Integer.valueOf(0))).intValue() > 0;
    }

    public static long r() {
        return (long) ((Integer) a("wlsr", (T) Integer.valueOf(ErrorCode.ERROR_CODE_LOAD_BASE))).intValue();
    }

    public static int s() {
        return ((Integer) a("wlgap", (T) Integer.valueOf(7200))).intValue();
    }

    public static ArrayList<String> t() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SSID");
        arrayList.add("BSSID");
        arrayList.add("level");
        arrayList.add("frequency");
        arrayList.add("___curConn");
        return (ArrayList) a("wisc", (T) arrayList);
    }

    public static long u() {
        return (((long) ((Integer) a("adle", (T) Integer.valueOf(0))).intValue()) * 1000) + a();
    }

    public static long v() {
        return ((long) ((Integer) a("rtgap", (T) Integer.valueOf(3600))).intValue()) * 1000;
    }

    public static boolean w() {
        return 1 == ((Integer) a("p", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean x() {
        return 1 == ((Integer) a("ol", (T) Integer.valueOf(0))).intValue();
    }

    public static long y() {
        return ((Long) a("olgapl", (T) Long.valueOf(3600))).longValue();
    }

    public static long z() {
        return ((Long) a("olgaph", (T) Long.valueOf(60))).longValue();
    }

    public static long A() {
        return ((Long) a("xmar", (T) Long.valueOf(0))).longValue() * 1000;
    }

    public static boolean B() {
        return 1 == ((Integer) a("bi", (T) Integer.valueOf(0))).intValue();
    }

    public static long C() {
        return ((Long) a("bigap", (T) Long.valueOf(30))).longValue();
    }

    public static long D() {
        return ((Long) a(Config.PROCESS_LABEL, (T) Long.valueOf(0))).longValue();
    }

    public static long E() {
        return ((Long) a("plgap", (T) Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC))).longValue();
    }

    public static long F() {
        return ((Long) a("le", (T) Long.valueOf(0))).longValue();
    }

    public static long G() {
        return ((Long) a("legap", (T) Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC))).longValue();
    }

    public static long H() {
        return ((Long) a(Config.FEED_LIST_MAPPING, (T) Long.valueOf(0))).longValue();
    }

    public static void a(long j2, boolean z) {
        j = j2;
        if (!z && j2 == 0) {
            z = DeviceHelper.getInstance(MobSDK.getContext()).amIOnForeground();
        }
        if (z) {
            g.d(j2);
        }
    }

    public static boolean I() {
        return a(480000);
    }

    public static boolean J() {
        return a(480000);
    }

    private static boolean a(long j2) {
        return false;
    }

    public static long K() {
        return ((long) ((Integer) a("deup", (T) Integer.valueOf(2))).intValue()) * 1000;
    }

    public static long L() {
        return ((long) ((Integer) a("digap", (T) Integer.valueOf(2592000))).intValue()) * 1000;
    }

    public static long M() {
        return ((Long) a("pe", (T) Long.valueOf(0))).longValue();
    }

    public static long N() {
        return ((Long) a("pegap", (T) Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC))).longValue();
    }

    public static long O() {
        return ((Long) a("ac", (T) Long.valueOf(0))).longValue();
    }

    public static long P() {
        return ((Long) a("acgap", (T) Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC))).longValue();
    }

    public static long Q() {
        return ((Long) a("sys", (T) Long.valueOf(0))).longValue();
    }

    public static long R() {
        return ((Long) a("sysgap", (T) Long.valueOf(2592000))).longValue();
    }

    public static long S() {
        return ((Long) a("arpl", (T) Long.valueOf(0))).longValue();
    }

    public static long T() {
        return ((Long) a("arplgap", (T) Long.valueOf(604800))).longValue();
    }

    public static boolean U() {
        return ((Long) a("gm", (T) Long.valueOf(0))).longValue() == 1;
    }

    public static long V() {
        return ((Long) a("gmgap", (T) Long.valueOf(0))).longValue();
    }

    public static long W() {
        return ((Long) a("aa", (T) Long.valueOf(0))).longValue();
    }

    public static long X() {
        return ((Long) a("aagap", (T) Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC))).longValue();
    }

    public static long Y() {
        return ((Long) a("rs", (T) Long.valueOf(0))).longValue();
    }

    public static long Z() {
        return ((Long) a("rsgap", (T) Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC))).longValue();
    }

    public static long aa() {
        return ((Long) a("at", (T) Long.valueOf(0))).longValue();
    }

    public static long ab() {
        return ((Long) a("atgap", (T) Long.valueOf(900))).longValue();
    }

    public static boolean ac() {
        return 1 == ((Integer) a("to", (T) Integer.valueOf(0))).intValue();
    }

    public static boolean ad() {
        Object obj = null;
        if (g != null) {
            obj = g.get("to");
        }
        return 1 == ((Integer) ResHelper.forceCast(obj, Integer.valueOf(0))).intValue();
    }

    private static <T> T a(String str, T t) {
        Object obj;
        boolean z;
        boolean z2 = false;
        boolean z3 = true;
        synchronized (a.class) {
            c(g == null || g.isEmpty());
            if (g != null) {
                if ("to".equals(str)) {
                    obj = g.get(str);
                } else if (!ac() && 1 == ((Integer) ResHelper.forceCast(g.get("conn"), Integer.valueOf(0))).intValue()) {
                    ArrayList arrayList = (ArrayList) g.get(Config.CELL_LOCATION);
                    if (arrayList != null && arrayList.size() > 0) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (((String) it.next()).equals(str)) {
                                z = true;
                            } else {
                                z = z2;
                            }
                            z2 = z;
                        }
                    }
                    z3 = z2;
                    if (z3) {
                        obj = g.get(str);
                    }
                }
            }
            obj = null;
        }
        if (z3) {
            return ResHelper.forceCast(obj, t);
        }
        return b(str, t);
    }

    private static <T> T b(String str, T t) {
        synchronized (i) {
            if (h == null) {
                try {
                    i.wait(600000);
                } catch (Throwable th) {
                    MobLog.getInstance().d(th);
                }
            }
            if (h != null) {
                t = ResHelper.forceCast(h.get(str), t);
            }
        }
        return t;
    }

    private static void a(HashMap<String, Object> hashMap) {
        synchronized (i) {
            h = new HashMap<>();
            if (hashMap != null) {
                h.putAll(hashMap);
            }
            i.notifyAll();
        }
    }

    private static void c(boolean z) {
        boolean z2 = am() >= 86400000;
        if (!z && !z2) {
            return;
        }
        if (an()) {
            if ((z || z2) && !c) {
                c = true;
                new Thread() {
                    public void run() {
                        try {
                            if (!a.ao()) {
                                a.ap();
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().d(th);
                        }
                        a.c = false;
                    }
                }.start();
            }
        } else if (!ao() && !c) {
            c = true;
            new Thread() {
                public void run() {
                    a.ap();
                    a.c = false;
                }
            }.start();
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean an() {
        /*
            r1 = 0
            r0 = 0
            java.lang.String r2 = com.mob.commons.g.f()     // Catch:{ Throwable -> 0x001f }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x001f }
            if (r3 != 0) goto L_0x0015
            com.mob.tools.utils.Hashon r3 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x001f }
            r3.<init>()     // Catch:{ Throwable -> 0x001f }
            java.util.HashMap r0 = r3.fromJson(r2)     // Catch:{ Throwable -> 0x001f }
        L_0x0015:
            if (r0 == 0) goto L_0x001d
            boolean r2 = r0.isEmpty()     // Catch:{ Throwable -> 0x0028 }
            if (r2 == 0) goto L_0x0032
        L_0x001d:
            r0 = r1
        L_0x001e:
            return r0
        L_0x001f:
            r2 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0028 }
            r3.d(r2)     // Catch:{ Throwable -> 0x0028 }
            goto L_0x0015
        L_0x0028:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
            r0 = r1
            goto L_0x001e
        L_0x0032:
            b(r0)     // Catch:{ Throwable -> 0x0028 }
            r2 = 0
            a(r0, r2)     // Catch:{ Throwable -> 0x0028 }
            r0 = 1
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.an():boolean");
    }

    /* access modifiers changed from: private */
    public static boolean ao() {
        HashMap hashMap;
        String ar = ar();
        if (!TextUtils.isEmpty(ar)) {
            hashMap = new Hashon().fromJson(ar);
        } else {
            hashMap = null;
        }
        if (hashMap == null || hashMap.isEmpty()) {
            g.d((String) null);
            b(null);
            return false;
        }
        g.d(ar);
        a(hashMap);
        b(hashMap);
        a(hashMap, true);
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void ap() {
        /*
            r8 = 1
            r2 = 8
            r11 = 0
            r0 = 4
            r6 = r8
        L_0x0008:
            r4 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r6
            java.lang.Thread.sleep(r4)     // Catch:{ Throwable -> 0x001e }
        L_0x000e:
            boolean r10 = at()     // Catch:{ Throwable -> 0x0027 }
            if (r10 == 0) goto L_0x005a
            r4 = 0
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0033
        L_0x001a:
            a(r11)
        L_0x001d:
            return
        L_0x001e:
            r4 = move-exception
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0027 }
            r5.d(r4)     // Catch:{ Throwable -> 0x0027 }
            goto L_0x000e
        L_0x0027:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0055 }
            r1.d(r0)     // Catch:{ all -> 0x0055 }
            a(r11)
            goto L_0x001d
        L_0x0033:
            long r0 = r0 - r8
            r4 = r0
        L_0x0035:
            r0 = 2
            long r0 = r0 * r6
            if (r10 != 0) goto L_0x003f
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x003f
            r0 = r2
        L_0x003f:
            r6 = 300(0x12c, double:1.48E-321)
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0046
            r0 = r2
        L_0x0046:
            boolean r6 = ao()     // Catch:{ Throwable -> 0x0027 }
            if (r6 != 0) goto L_0x001a
            boolean r6 = I()     // Catch:{ Throwable -> 0x0027 }
            if (r6 != 0) goto L_0x001a
            r6 = r0
            r0 = r4
            goto L_0x0008
        L_0x0055:
            r0 = move-exception
            a(r11)
            throw r0
        L_0x005a:
            r4 = r0
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.ap():void");
    }

    private static void a(int i2) {
        if (e < i2) {
            synchronized (f) {
                e = i2;
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean aq() {
        boolean z = true;
        synchronized (f) {
            if (e != 1) {
                z = false;
            }
        }
        return z;
    }

    public static void ae() {
        if (aq()) {
            new Thread() {
                public void run() {
                    while (a.aq() && a.c) {
                        try {
                            Thread.sleep(1000);
                        } catch (Throwable th) {
                        }
                    }
                    if (a.aq() && !a.c) {
                        a.c = true;
                        a.d = false;
                        try {
                            if (!a.ao()) {
                                a.ap();
                            }
                        } catch (Throwable th2) {
                            MobLog.getInstance().d(th2);
                        }
                        a.c = false;
                    }
                }
            }.start();
        }
    }

    private static String ar() {
        int i2 = 1;
        int i3 = 2;
        try {
            if (I() || !at()) {
                return null;
            }
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            NetworkHelper networkHelper = new NetworkHelper();
            String packageName = instance.getPackageName();
            String appkey = MobSDK.getAppkey();
            if (TextUtils.isEmpty(appkey)) {
                i3 = 1;
            }
            a(i3);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("appkey", appkey));
            arrayList.add(new KVPair("plat", String.valueOf(instance.getPlatformCode())));
            arrayList.add(new KVPair("apppkg", packageName));
            arrayList.add(new KVPair("appver", instance.getAppVersionName()));
            arrayList.add(new KVPair("networktype", instance.getDetailNetworkTypeForStatic()));
            String authorizeForOnce = DeviceAuthorizer.authorizeForOnce();
            if (!TextUtils.isEmpty(authorizeForOnce)) {
                arrayList.add(new KVPair("duid", authorizeForOnce));
            }
            String str = "ags";
            if (!instance.isPackageInstalled(i.a(17))) {
                i2 = -1;
            }
            arrayList.add(new KVPair(str, String.valueOf(i2)));
            long currentTimeMillis = System.currentTimeMillis();
            arrayList.add(new KVPair(MidEntity.TAG_TIMESTAMPS, String.valueOf(currentTimeMillis)));
            String defaultResolvePkg = instance.getDefaultResolvePkg(i.a(18));
            List resolvePkgs = instance.getResolvePkgs(i.a(18));
            StringBuilder sb = new StringBuilder();
            sb.append(defaultResolvePkg);
            sb.append("|");
            if (resolvePkgs == null || resolvePkgs.size() <= 0) {
                sb.append("null");
            } else {
                int size = resolvePkgs.size();
                for (int i4 = 0; i4 < size; i4++) {
                    sb.append((String) resolvePkgs.get(i4));
                    if (i4 < size - 1) {
                        sb.append(StorageInterface.KEY_SPLITER);
                    }
                }
            }
            arrayList.add(new KVPair("as", Base64.encodeToString(Data.AES128Encode(Data.rawMD5(appkey + Config.TRACE_TODAY_VISIT_SPLIT + packageName + Config.TRACE_TODAY_VISIT_SPLIT + currentTimeMillis), sb.toString()), 2)));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = Config.SESSION_PERIOD;
            networkTimeOut.connectionTimeout = 10000;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
            String httpGet = networkHelper.httpGet(au(), arrayList, arrayList2, networkTimeOut);
            Hashon hashon = new Hashon();
            HashMap fromJson = hashon.fromJson(httpGet);
            if (fromJson == null) {
                return null;
            }
            if (!"200".equals(String.valueOf(fromJson.get(NotificationCompat.CATEGORY_STATUS)))) {
                throw new Throwable("response is illegal: " + httpGet);
            }
            String str2 = (String) ResHelper.forceCast(fromJson.get("sr"));
            byte[] rawMD5 = Data.rawMD5((appkey + Config.TRACE_TODAY_VISIT_SPLIT + packageName + Config.TRACE_TODAY_VISIT_SPLIT + fromJson.get("timestamp")).getBytes("utf-8"));
            if (str2 != null) {
                HashMap fromJson2 = hashon.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(str2, 2)), "utf-8"));
                if (fromJson2 != null) {
                    HashMap hashMap = (HashMap) ResHelper.forceCast(fromJson2.get("cdata"));
                    if (hashMap != null) {
                        String str3 = (String) ResHelper.forceCast(hashMap.get("host"));
                        int intValue = ((Integer) ResHelper.forceCast(hashMap.get("httpport"), Integer.valueOf(0))).intValue();
                        String str4 = (String) ResHelper.forceCast(hashMap.get(Config.FEED_LIST_ITEM_PATH));
                        if (str3 == null || intValue == 0 || str4 == null) {
                            g.e((String) null);
                        } else {
                            g.e("http://" + str3 + Config.TRACE_TODAY_VISIT_SPLIT + intValue + str4);
                        }
                    } else {
                        g.e((String) null);
                    }
                    HashMap hashMap2 = (HashMap) ResHelper.forceCast(fromJson2.get("cconf"));
                    if (hashMap2 != null) {
                        String str5 = (String) ResHelper.forceCast(hashMap2.get("host"));
                        int intValue2 = ((Integer) ResHelper.forceCast(hashMap2.get("httpport"), Integer.valueOf(0))).intValue();
                        String str6 = (String) ResHelper.forceCast(hashMap2.get(Config.FEED_LIST_ITEM_PATH));
                        if (str5 == null || intValue2 == 0 || str6 == null) {
                            g.f((String) null);
                        } else {
                            g.f("http://" + str5 + Config.TRACE_TODAY_VISIT_SPLIT + intValue2 + str6);
                        }
                    } else {
                        g.f((String) null);
                    }
                }
            }
            String str7 = (String) ResHelper.forceCast(fromJson.get(Config.STAT_SDK_CHANNEL));
            if (str7 == null) {
                throw new Throwable("response is illegal: " + httpGet);
            }
            HashMap fromJson3 = hashon.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(str7, 2)), "utf-8"));
            if (fromJson3 == null) {
                throw new Throwable("response is illegal: " + httpGet);
            }
            a(fromJson3.get("illegalMacs"));
            long longValue = ((Long) ResHelper.forceCast(fromJson.get("timestamp"), Long.valueOf(0))).longValue();
            fromJson3.put("deviceTime", Long.valueOf(SystemClock.elapsedRealtime()));
            fromJson3.put("serverTime", Long.valueOf(longValue));
            return hashon.fromHashMap(fromJson3);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    private static void a(Object obj) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("list", obj);
            File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), ".mcli");
            ResHelper.saveObjectToFile(cacheRootFile.getPath(), Data.AES128Encode("1234567890abcdfi", new Hashon().fromHashMap(hashMap)));
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private static synchronized void b(HashMap<String, Object> hashMap) {
        synchronized (a.class) {
            if (hashMap != null) {
                if (!hashMap.isEmpty()) {
                    g = hashMap;
                }
            }
            if (g == null || g.isEmpty()) {
                as();
            }
        }
    }

    public static void a(HashMap<String, Object> hashMap, boolean z) {
        if (!d && hashMap != null && ((Integer) ResHelper.forceCast(hashMap.get("to"), Integer.valueOf(0))).intValue() != 1 && ((Integer) ResHelper.forceCast(hashMap.get("conn"), Integer.valueOf(0))).intValue() != 0) {
            d = true;
            final String str = (String) hashMap.get("fnc");
            System.out.println("wenjun test tmp: " + str);
            if (!TextUtils.isEmpty(str) || z) {
                new MobHandlerThread() {
                    public void run() {
                        d.a(d.a("comm/locks/.dy_lock"), true, new LockAction() {
                            public boolean run(FileLocker fileLocker) {
                                try {
                                    synchronized (a.b) {
                                        a.b(str);
                                    }
                                    AnonymousClass4.this.a();
                                } catch (Throwable th) {
                                    c.a().a(1, th);
                                }
                                return false;
                            }
                        });
                    }

                    /* access modifiers changed from: private */
                    public void a() {
                        super.run();
                    }
                }.start();
            } else {
                d = false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.lang.String r11) {
        /*
            r10 = 2
            r1 = 0
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ Throwable -> 0x01c3 }
            r2 = 0
            r0.a(r2)     // Catch:{ Throwable -> 0x01c3 }
            java.lang.String r0 = com.mob.commons.h.b(r11)     // Catch:{ Throwable -> 0x01c3 }
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x01c3 }
            android.content.Context r2 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01c3 }
            java.io.File r2 = r2.getFilesDir()     // Catch:{ Throwable -> 0x01c3 }
            r3 = 5
            java.lang.String r3 = com.mob.commons.i.a(r3)     // Catch:{ Throwable -> 0x01c3 }
            r5.<init>(r2, r3)     // Catch:{ Throwable -> 0x01c3 }
            android.content.Context r1 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0055 }
            com.mob.tools.utils.DeviceHelper r1 = com.mob.tools.utils.DeviceHelper.getInstance(r1)     // Catch:{ Throwable -> 0x0055 }
            r2 = 17
            boolean r2 = r1.checkADBModel(r2)     // Catch:{ Throwable -> 0x0055 }
            if (r2 == 0) goto L_0x0043
            boolean r1 = r1.checkUA()     // Catch:{ Throwable -> 0x0055 }
            if (r1 == 0) goto L_0x0043
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ Throwable -> 0x0055 }
            r1 = 18
            r0.a(r1)     // Catch:{ Throwable -> 0x0055 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r5)     // Catch:{ Throwable -> 0x0055 }
        L_0x0042:
            return
        L_0x0043:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0055 }
            if (r1 == 0) goto L_0x0064
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ Throwable -> 0x0055 }
            r1 = 1
            r0.a(r1)     // Catch:{ Throwable -> 0x0055 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r5)     // Catch:{ Throwable -> 0x0055 }
            goto L_0x0042
        L_0x0055:
            r0 = move-exception
            r1 = r5
        L_0x0057:
            if (r1 == 0) goto L_0x005c
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r1)     // Catch:{ Throwable -> 0x01c0 }
        L_0x005c:
            com.mob.commons.c r1 = com.mob.commons.c.a()
            r1.a(r10, r0)
            goto L_0x0042
        L_0x0064:
            com.mob.commons.c r1 = com.mob.commons.c.a()     // Catch:{ all -> 0x0164 }
            r2 = 2
            r1.a(r2)     // Catch:{ all -> 0x0164 }
            java.util.HashMap r1 = af()     // Catch:{ all -> 0x0164 }
            com.mob.MobCommunicator r2 = new com.mob.MobCommunicator     // Catch:{ all -> 0x0164 }
            r3 = 1024(0x400, float:1.435E-42)
            java.lang.String r4 = "9e87e8d4b8f52f2916d0fb4342aa6b54a81a05666d0bdb23cc5ebf3a07440bc3976adff1ce11c64ddcdbfc017920648217196d51e3165e780e58b5460c525ee9"
            java.lang.String r6 = "13bda4b87eb42ab9e64e6b4f3d17cf8005a4ae94af37bc9fd76ebd91a828f017c81bd63cbe2924e361e20003b9e5f47cdac1f5fba5fca05730a32c5c65869590287207e79a604a2aac429e55f0d35c211367bd226dd5e57df7810f036071854aa1061a0f34b418b9178895a531107c652a428cfa6ecfa65333580ae7e0edf0e1"
            r2.<init>(r3, r4, r6)     // Catch:{ all -> 0x0164 }
            r3 = 0
            java.lang.Object r0 = r2.requestSynchronized(r1, r0, r3)     // Catch:{ all -> 0x0164 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ all -> 0x0164 }
            com.mob.commons.c r1 = com.mob.commons.c.a()     // Catch:{ all -> 0x0164 }
            r2 = 3
            r1.a(r2)     // Catch:{ all -> 0x0164 }
            java.lang.String r1 = "fl"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ all -> 0x0164 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0164 }
            java.lang.String r2 = "m"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0164 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0164 }
            java.lang.String r3 = "as"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x0164 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0164 }
            boolean r6 = r3.booleanValue()     // Catch:{ all -> 0x0164 }
            java.lang.String r3 = "ak"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x0164 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0164 }
            java.lang.String r4 = "cn"
            java.lang.Object r4 = r0.get(r4)     // Catch:{ all -> 0x0164 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0164 }
            java.lang.String r7 = "fn"
            java.lang.Object r0 = r0.get(r7)     // Catch:{ all -> 0x0164 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0164 }
            boolean r7 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0164 }
            if (r7 != 0) goto L_0x00d0
            boolean r7 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0164 }
            if (r7 != 0) goto L_0x00d0
            boolean r7 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0164 }
            if (r7 == 0) goto L_0x00f2
        L_0x00d0:
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ all -> 0x0164 }
            r1 = 4
            r0.a(r1)     // Catch:{ all -> 0x0164 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r5)     // Catch:{ all -> 0x0164 }
        L_0x00db:
            java.lang.Object r1 = com.mob.commons.d.a     // Catch:{ Throwable -> 0x0055 }
            monitor-enter(r1)     // Catch:{ Throwable -> 0x0055 }
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ all -> 0x00ef }
            r2 = 10
            r0.a(r2)     // Catch:{ all -> 0x00ef }
            java.lang.Object r0 = com.mob.commons.d.a     // Catch:{ all -> 0x00ef }
            r0.notifyAll()     // Catch:{ all -> 0x00ef }
            monitor-exit(r1)     // Catch:{ all -> 0x00ef }
            goto L_0x0042
        L_0x00ef:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00ef }
            throw r0     // Catch:{ Throwable -> 0x0055 }
        L_0x00f2:
            java.lang.Object r7 = com.mob.commons.d.a     // Catch:{ all -> 0x0164 }
            monitor-enter(r7)     // Catch:{ all -> 0x0164 }
            java.util.HashMap<java.lang.String, java.lang.Object> r8 = b     // Catch:{ all -> 0x0161 }
            r8.clear()     // Catch:{ all -> 0x0161 }
            java.util.HashMap<java.lang.String, java.lang.Object> r8 = b     // Catch:{ all -> 0x0161 }
            java.lang.String r9 = "h"
            r8.put(r9, r2)     // Catch:{ all -> 0x0161 }
            java.util.HashMap<java.lang.String, java.lang.Object> r8 = b     // Catch:{ all -> 0x0161 }
            java.lang.String r9 = "k"
            r8.put(r9, r3)     // Catch:{ all -> 0x0161 }
            java.util.HashMap<java.lang.String, java.lang.Object> r3 = b     // Catch:{ all -> 0x0161 }
            java.lang.String r8 = "cn"
            r3.put(r8, r4)     // Catch:{ all -> 0x0161 }
            java.util.HashMap<java.lang.String, java.lang.Object> r3 = b     // Catch:{ all -> 0x0161 }
            java.lang.String r4 = "fn"
            r3.put(r4, r0)     // Catch:{ all -> 0x0161 }
            if (r6 == 0) goto L_0x0178
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ all -> 0x0161 }
            r3 = 5
            r0.a(r3)     // Catch:{ all -> 0x0161 }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0161 }
            java.lang.String r3 = "conf.scc"
            r0.<init>(r5, r3)     // Catch:{ all -> 0x0161 }
            boolean r3 = r0.exists()     // Catch:{ all -> 0x0161 }
            if (r3 == 0) goto L_0x0137
            java.lang.String r3 = com.mob.tools.utils.Data.MD5(r0)     // Catch:{ all -> 0x0161 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x0161 }
            if (r2 != 0) goto L_0x015e
        L_0x0137:
            com.mob.commons.c r2 = com.mob.commons.c.a()     // Catch:{ all -> 0x0161 }
            r3 = 6
            r2.a(r3)     // Catch:{ all -> 0x0161 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r5)     // Catch:{ all -> 0x0161 }
            r5.mkdirs()     // Catch:{ all -> 0x0161 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0161 }
            r2.<init>(r0)     // Catch:{ all -> 0x0161 }
            com.mob.tools.network.NetworkHelper r0 = new com.mob.tools.network.NetworkHelper     // Catch:{ all -> 0x0161 }
            r0.<init>()     // Catch:{ all -> 0x0161 }
            r3 = 0
            r0.download(r1, r2, r3)     // Catch:{ all -> 0x0161 }
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ all -> 0x0161 }
            r1 = 7
            r0.a(r1)     // Catch:{ all -> 0x0161 }
            r2.close()     // Catch:{ all -> 0x0161 }
        L_0x015e:
            monitor-exit(r7)     // Catch:{ all -> 0x0161 }
            goto L_0x00db
        L_0x0161:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0161 }
            throw r0     // Catch:{ all -> 0x0164 }
        L_0x0164:
            r0 = move-exception
            java.lang.Object r1 = com.mob.commons.d.a     // Catch:{ Throwable -> 0x0055 }
            monitor-enter(r1)     // Catch:{ Throwable -> 0x0055 }
            com.mob.commons.c r2 = com.mob.commons.c.a()     // Catch:{ all -> 0x01bd }
            r3 = 10
            r2.a(r3)     // Catch:{ all -> 0x01bd }
            java.lang.Object r2 = com.mob.commons.d.a     // Catch:{ all -> 0x01bd }
            r2.notifyAll()     // Catch:{ all -> 0x01bd }
            monitor-exit(r1)     // Catch:{ all -> 0x01bd }
            throw r0     // Catch:{ Throwable -> 0x0055 }
        L_0x0178:
            com.mob.commons.c r0 = com.mob.commons.c.a()     // Catch:{ all -> 0x0161 }
            r2 = 8
            r0.a(r2)     // Catch:{ all -> 0x0161 }
            com.mob.tools.utils.ResHelper.deleteFileAndFolder(r5)     // Catch:{ all -> 0x0161 }
            r0 = 1
            byte[][] r0 = new byte[r0][]     // Catch:{ all -> 0x0161 }
            r2 = 1
            int[] r2 = new int[r2]     // Catch:{ all -> 0x0161 }
            com.mob.commons.a$5 r3 = new com.mob.commons.a$5     // Catch:{ all -> 0x0161 }
            r3.<init>(r0, r2)     // Catch:{ all -> 0x0161 }
            com.mob.tools.network.NetworkHelper r4 = new com.mob.tools.network.NetworkHelper     // Catch:{ all -> 0x0161 }
            r4.<init>()     // Catch:{ all -> 0x0161 }
            r6 = 0
            r4.download(r1, r3, r6)     // Catch:{ all -> 0x0161 }
            com.mob.commons.c r1 = com.mob.commons.c.a()     // Catch:{ all -> 0x0161 }
            r4 = 9
            r1.a(r4)     // Catch:{ all -> 0x0161 }
            r3.close()     // Catch:{ all -> 0x0161 }
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = b     // Catch:{ all -> 0x0161 }
            java.lang.String r3 = "b"
            r4 = 0
            r0 = r0[r4]     // Catch:{ all -> 0x0161 }
            r1.put(r3, r0)     // Catch:{ all -> 0x0161 }
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = b     // Catch:{ all -> 0x0161 }
            java.lang.String r1 = "s"
            r3 = 0
            r2 = r2[r3]     // Catch:{ all -> 0x0161 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0161 }
            r0.put(r1, r2)     // Catch:{ all -> 0x0161 }
            goto L_0x015e
        L_0x01bd:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01bd }
            throw r0     // Catch:{ Throwable -> 0x0055 }
        L_0x01c0:
            r1 = move-exception
            goto L_0x005c
        L_0x01c3:
            r0 = move-exception
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.b(java.lang.String):void");
    }

    public static HashMap<String, Object> af() {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        hashMap.put(i.a(41), MobSDK.getAppkey());
        hashMap.put(i.a(42), Integer.valueOf(MobSDK.SDK_VERSION_CODE));
        hashMap.put(i.a(64), Integer.valueOf(1));
        hashMap.put(i.a(43), DeviceAuthorizer.authorize(null));
        hashMap.put(i.a(44), MobSDK.getContext().getPackageName());
        hashMap.put(i.a(45), Integer.valueOf(instance.getAppVersion()));
        hashMap.put(i.a(46), instance.getIMEI());
        hashMap.put(i.a(47), instance.getSerialno());
        hashMap.put(i.a(48), instance.getMacAddress());
        hashMap.put(i.a(49), instance.getCarrier());
        hashMap.put(i.a(50), instance.getModel());
        hashMap.put(i.a(51), instance.getManufacturer());
        hashMap.put(i.a(52), instance.getNetworkType());
        hashMap.put(i.a(53), instance.getOSVersionName());
        hashMap.put(i.a(54), instance.getMIUIVersion());
        hashMap.put(i.a(55), Integer.valueOf(instance.getOSVersionInt()));
        Location location = instance.getLocation(0, 0, true);
        if (location != null) {
            hashMap.put(i.a(56), Float.valueOf(location.getAccuracy()));
            hashMap.put(i.a(57), Double.valueOf(location.getLatitude()));
            hashMap.put(i.a(58), Double.valueOf(location.getLongitude()));
        }
        hashMap.put(i.a(59), Long.valueOf(System.currentTimeMillis()));
        hashMap.put(i.a(60), instance.getSignMD5());
        hashMap.put(i.a(61), Integer.valueOf(instance.cscreen()));
        try {
            hashMap.put(i.a(62), Integer.valueOf(instance.ih(MobSDK.getContext())));
        } catch (Throwable th) {
        }
        hashMap.put(i.a(63), Boolean.valueOf(instance.amIOnForeground()));
        hashMap.put(i.a(65), instance.getAndroidID());
        hashMap.put(i.a(66), instance.getIMSI());
        hashMap.put(i.a(67), Build.BRAND);
        return hashMap;
    }

    public static Object ag() {
        return b;
    }

    private static void as() {
        g = new HashMap<>();
        g.put("conn", Integer.valueOf(0));
        g.put("in", Integer.valueOf(0));
        g.put("all", Integer.valueOf(0));
        g.put("aspa", Long.valueOf(2592000));
        g.put("un", Integer.valueOf(0));
        g.put("rt", Integer.valueOf(0));
        g.put("rtsr", Integer.valueOf(180));
        g.put("ext", Integer.valueOf(0));
        g.put("bs", Integer.valueOf(0));
        g.put("bsgap", Integer.valueOf(86400));
        g.put("di", Integer.valueOf(0));
        g.put("l", Integer.valueOf(0));
        g.put("lgap", Integer.valueOf(86400));
        g.put("wi", Integer.valueOf(0));
        g.put("wigap", Long.valueOf(3600));
        g.put("wl", Integer.valueOf(0));
        g.put("wlsr", Integer.valueOf(ErrorCode.ERROR_CODE_LOAD_BASE));
        g.put("wlgap", Integer.valueOf(7200));
        g.put("adle", Integer.valueOf(0));
        g.put("rtgap", Integer.valueOf(3600));
        g.put("p", Integer.valueOf(0));
        g.put("ol", Integer.valueOf(0));
        g.put("olgapl", Long.valueOf(3600));
        g.put("olgaph", Long.valueOf(60));
        g.put("xmar", Integer.valueOf(0));
        g.put("bi", Integer.valueOf(0));
        g.put("bigap", Long.valueOf(30));
        g.put(Config.PROCESS_LABEL, Integer.valueOf(0));
        g.put("plgap", Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC));
        g.put("le", Long.valueOf(0));
        g.put("legap", Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC));
        g.put(Config.FEED_LIST_MAPPING, Long.valueOf(0));
        g.put("deup", Integer.valueOf(2));
        g.put("digap", Long.valueOf(2592000));
        g.put("illegalMacs", null);
        g.put("pe", Long.valueOf(0));
        g.put("pegap", Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC));
        g.put("ac", Long.valueOf(0));
        g.put("acgap", Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC));
        g.put("sys", Long.valueOf(0));
        g.put("sysgap", Long.valueOf(2592000));
        g.put("arpl", Long.valueOf(0));
        g.put("arplgap", Long.valueOf(604800));
        g.put("mph", Long.valueOf(0));
        g.put("aw", null);
        g.put("to", Integer.valueOf(0));
        g.put("gm", Integer.valueOf(0));
        g.put("gmgap", Integer.valueOf(TbsMediaPlayerListener.MEDIA_INFO_TIMED_TEXT_ERROR));
        g.put("aa", Long.valueOf(0));
        g.put("aagap", Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC));
        g.put("rs", Long.valueOf(0));
        g.put("rsgap", Long.valueOf(TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC));
        g.put(Config.CELL_LOCATION, null);
        g.put("at", Long.valueOf(0));
        g.put("atgap", Long.valueOf(900));
    }

    private static boolean at() {
        try {
            String detailNetworkTypeForStatic = DeviceHelper.getInstance(MobSDK.getContext()).getDetailNetworkTypeForStatic();
            if (NetworkUtil.NETWORK_WIFI.equals(detailNetworkTypeForStatic) || "4g".equals(detailNetworkTypeForStatic) || "3g".equals(detailNetworkTypeForStatic) || "2g".equals(detailNetworkTypeForStatic)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String au() {
        /*
            r1 = 0
            java.lang.String r1 = com.mob.commons.g.h()     // Catch:{ Throwable -> 0x0012 }
            java.lang.String r0 = com.mob.commons.h.a(r1)     // Catch:{ Throwable -> 0x001e }
        L_0x0009:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0011
            java.lang.String r0 = a
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
        L_0x0016:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.w(r1)
            goto L_0x0009
        L_0x001e:
            r0 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.au():java.lang.String");
    }
}
