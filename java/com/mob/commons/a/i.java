package com.mob.commons.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.commons.g;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import org.json.JSONObject;

/* compiled from: DvcClt */
public class i extends d {
    private Hashon a;
    private Random b;
    private BroadcastReceiver c;
    private BroadcastReceiver d;
    private long e = 0;
    private DeviceHelper f = DeviceHelper.getInstance(MobSDK.getContext());

    i() {
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.dic_lock");
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(1);
        b(2);
        Message obtain = Message.obtain();
        obtain.arg1 = -1;
        obtain.what = 6;
        b(obtain);
        b(3);
        b(5);
        b(7);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        if (n() != false) goto L_0x0069;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.os.Message r8) {
        /*
            r7 = this;
            r6 = 120(0x78, float:1.68E-43)
            r5 = 6
            r4 = 4
            int r0 = r8.what
            switch(r0) {
                case 1: goto L_0x000a;
                case 2: goto L_0x0014;
                case 3: goto L_0x0025;
                case 4: goto L_0x0051;
                case 5: goto L_0x008f;
                case 6: goto L_0x00c7;
                case 7: goto L_0x00f1;
                case 8: goto L_0x011a;
                case 9: goto L_0x011f;
                default: goto L_0x0009;
            }
        L_0x0009:
            return
        L_0x000a:
            boolean r0 = com.mob.commons.a.j()
            if (r0 == 0) goto L_0x0009
            r7.i()
            goto L_0x0009
        L_0x0014:
            boolean r0 = com.mob.commons.a.o()
            if (r0 == 0) goto L_0x0021
            r7.j()
            r7.k()
            goto L_0x0009
        L_0x0021:
            r7.l()
            goto L_0x0009
        L_0x0025:
            boolean r0 = com.mob.commons.a.k()
            if (r0 == 0) goto L_0x0009
            r7.m()     // Catch:{ Throwable -> 0x0048 }
        L_0x002e:
            java.util.Random r0 = r7.b
            if (r0 != 0) goto L_0x0039
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            r7.b = r0
        L_0x0039:
            java.util.Random r0 = r7.b
            int r0 = r0.nextInt(r6)
            int r0 = r0 + 180
            int r0 = r0 * 1000
            long r0 = (long) r0
            r7.a(r4, r0)
            goto L_0x0009
        L_0x0048:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            goto L_0x002e
        L_0x0051:
            boolean r0 = com.mob.commons.a.k()
            if (r0 == 0) goto L_0x0009
            long r0 = com.mob.commons.g.d()
            long r2 = com.mob.commons.a.a()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x0069
            boolean r0 = r7.n()     // Catch:{ Throwable -> 0x0086 }
            if (r0 == 0) goto L_0x006c
        L_0x0069:
            r7.m()     // Catch:{ Throwable -> 0x0086 }
        L_0x006c:
            java.util.Random r0 = r7.b
            if (r0 != 0) goto L_0x0077
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            r7.b = r0
        L_0x0077:
            java.util.Random r0 = r7.b
            int r0 = r0.nextInt(r6)
            int r0 = r0 + 180
            int r0 = r0 * 1000
            long r0 = (long) r0
            r7.a(r4, r0)
            goto L_0x0009
        L_0x0086:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            goto L_0x006c
        L_0x008f:
            boolean r0 = com.mob.commons.a.m()
            if (r0 == 0) goto L_0x00b1
            com.mob.tools.utils.DeviceHelper r0 = r7.f     // Catch:{ Throwable -> 0x00be }
            r1 = 30
            r2 = 0
            r3 = 1
            android.location.Location r0 = r0.getLocation(r1, r2, r3)     // Catch:{ Throwable -> 0x00be }
            r1 = 1
            r7.a(r0, r1)     // Catch:{ Throwable -> 0x00be }
            com.mob.tools.utils.DeviceHelper r0 = r7.f     // Catch:{ Throwable -> 0x00be }
            r1 = 0
            r2 = 15
            r3 = 1
            android.location.Location r0 = r0.getLocation(r1, r2, r3)     // Catch:{ Throwable -> 0x00be }
            r1 = 2
            r7.a(r0, r1)     // Catch:{ Throwable -> 0x00be }
        L_0x00b1:
            r0 = 5
            int r1 = com.mob.commons.a.n()
            int r1 = r1 * 1000
            long r2 = (long) r1
            r7.a(r0, r2)
            goto L_0x0009
        L_0x00be:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            goto L_0x00b1
        L_0x00c7:
            boolean r0 = com.mob.commons.a.q()
            long r2 = com.mob.commons.a.r()
            int r1 = (int) r2
            if (r0 == 0) goto L_0x00e2
            r7.c(r1)
            com.mob.tools.utils.DeviceHelper r0 = r7.f
            r0.scanWifiList()
            int r0 = r1 * 1000
            long r0 = (long) r0
            r7.a(r5, r0)
            goto L_0x0009
        L_0x00e2:
            r7.p()
            int r0 = com.mob.commons.a.s()
            int r0 = r0 * 1000
            long r0 = (long) r0
            r7.a(r5, r0)
            goto L_0x0009
        L_0x00f1:
            boolean r0 = com.mob.commons.a.x()
            if (r0 == 0) goto L_0x0104
            com.mob.tools.utils.DeviceHelper r0 = r7.f     // Catch:{ Throwable -> 0x0111 }
            r1 = 0
            r2 = 0
            r3 = 1
            android.location.Location r0 = r0.getLocation(r1, r2, r3)     // Catch:{ Throwable -> 0x0111 }
            r1 = 0
            r7.a(r0, r1)     // Catch:{ Throwable -> 0x0111 }
        L_0x0104:
            r0 = 7
            long r2 = com.mob.commons.a.z()
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            r7.a(r0, r2)
            goto L_0x0009
        L_0x0111:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            goto L_0x0104
        L_0x011a:
            r7.o()
            goto L_0x0009
        L_0x011f:
            java.lang.Object r0 = r8.obj
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            r7.a(r0)
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.i.a(android.os.Message):void");
    }

    /* access modifiers changed from: protected */
    public void b() {
        l();
        p();
    }

    private void i() {
        boolean z = true;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("phonename", this.f.getBluetoothName());
            hashMap.put("signmd5", this.f.getSignMD5());
            hashMap.put("boardname", Build.BOARD);
            hashMap.put("devicename", Build.DEVICE);
            hashMap.put("displayid", Build.DISPLAY);
            hashMap.put("fingerprint", Build.FINGERPRINT);
            if (VERSION.SDK_INT >= 14) {
                hashMap.put("radiover", Build.getRadioVersion());
            } else {
                hashMap.put("radiover", null);
            }
            hashMap.put("density", Float.valueOf(ResHelper.getDensity(MobSDK.getContext())));
            hashMap.put("densitydpi", Integer.valueOf(ResHelper.getDensityDpi(MobSDK.getContext())));
            hashMap.put("btm", this.f.getBTMac());
            hashMap.put("btmp", this.f.getBTMacFromProvider());
            hashMap.put("bt", Integer.valueOf(this.f.isBT() ? 1 : 0));
            String MD5 = Data.MD5(q().fromHashMap(hashMap));
            String a2 = g.a();
            if (a.a() < g.o()) {
                z = false;
            }
            if (a2 == null || !a2.equals(MD5) || z) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("type", "DEVEXT");
                hashMap2.put("data", hashMap);
                hashMap2.put("datetime", Long.valueOf(a.a()));
                b.a().a(a.a(), hashMap2);
                g.a(MD5);
                g.e(a.a() - 1702967296);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private void j() {
        synchronized (this) {
            HashMap hashMap = new HashMap();
            try {
                String bssid = this.f.getBssid();
                if (!TextUtils.isEmpty(bssid)) {
                    ArrayList availableWifiList = this.f.getAvailableWifiList();
                    if (availableWifiList != null && !availableWifiList.isEmpty()) {
                        ArrayList t = a.t();
                        if (t != null && !t.isEmpty()) {
                            Iterator it = availableWifiList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                HashMap hashMap2 = (HashMap) it.next();
                                Object obj = hashMap2.get("BSSID");
                                if (obj != null && String.valueOf(obj).equals(bssid)) {
                                    hashMap.putAll(hashMap2);
                                    break;
                                }
                            }
                            hashMap.remove("BSSID");
                            hashMap.remove("SSID");
                        }
                    }
                }
                HashMap currentWifiInfo = this.f.getCurrentWifiInfo();
                if (currentWifiInfo != null) {
                    hashMap.putAll(currentWifiInfo);
                }
                String ssid = this.f.getSSID();
                hashMap.put("ssid", ssid);
                hashMap.put("bssid", bssid);
                HashMap hashMap3 = new HashMap();
                hashMap3.put("type", "WIFI_INFO");
                hashMap3.put("data", hashMap);
                hashMap3.put(Config.CELL_LOCATION, g());
                long a2 = a.a();
                hashMap3.put("datetime", Long.valueOf(a2));
                b.a().a(a.a(), hashMap3);
                g.a(a2);
                TreeMap treeMap = new TreeMap();
                treeMap.put("ssid", ssid);
                treeMap.put("bssid", bssid);
                g.b(Data.MD5(new JSONObject(treeMap).toString()));
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
            a(2, (long) (a.p() * 1000));
        }
    }

    private void k() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && "android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                        try {
                            Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
                            if (parcelableExtra != null) {
                                Message message = new Message();
                                message.what = 9;
                                message.obj = parcelableExtra;
                                i.this.b(message);
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().w(th);
                        }
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            try {
                MobSDK.getContext().registerReceiver(this.c, intentFilter);
            } catch (Throwable th) {
            }
        }
    }

    private void a(Parcelable parcelable) {
        try {
            if (((NetworkInfo) parcelable).isConnected()) {
                TreeMap treeMap = new TreeMap();
                treeMap.put("ssid", this.f.getSSID());
                treeMap.put("bssid", this.f.getBssid());
                String MD5 = Data.MD5(new JSONObject(treeMap).toString());
                String b2 = g.b();
                if ((b2 == null || !b2.equals(MD5)) && a.o()) {
                    j();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private void l() {
        if (this.c != null) {
            try {
                MobSDK.getContext().unregisterReceiver(this.c);
            } catch (Throwable th) {
            }
            this.c = null;
        }
    }

    private void m() throws Throwable {
        int i;
        try {
            i = Integer.parseInt(this.f.getCarrier());
        } catch (Throwable th) {
            i = -1;
        }
        int cellLac = this.f.getCellLac();
        int cellId = this.f.getCellId();
        int psc = this.f.getPsc();
        HashMap hashMap = null;
        if (!(i == -1 || cellLac == -1 || cellId == -1)) {
            hashMap = new HashMap();
            hashMap.put("lac", Integer.valueOf(cellLac));
            hashMap.put("cell", Integer.valueOf(cellId));
            if (psc != -1) {
                hashMap.put("psc", Integer.valueOf(psc));
            }
        }
        int cdmaBid = this.f.getCdmaBid();
        int cdmaSid = this.f.getCdmaSid();
        int cdmaNid = this.f.getCdmaNid();
        int cdmaLat = this.f.getCdmaLat();
        int cdmaLon = this.f.getCdmaLon();
        if (!(i == -1 || cdmaBid == -1 || cdmaSid == -1 || cdmaNid == -1)) {
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            hashMap.put("bid", Integer.valueOf(cdmaBid));
            hashMap.put("sid", Integer.valueOf(cdmaSid));
            hashMap.put("nid", Integer.valueOf(cdmaNid));
            if (cdmaLat != -1) {
                hashMap.put("lat", Integer.valueOf(cdmaLat));
            }
            if (cdmaLon != -1) {
                hashMap.put("lon", Integer.valueOf(cdmaLon));
            }
        }
        if (hashMap != null) {
            hashMap.put("carrier", Integer.valueOf(i));
            hashMap.put("simopname", this.f.getCarrierName());
            ArrayList neighboringCellInfo = this.f.getNeighboringCellInfo();
            if (neighboringCellInfo != null && neighboringCellInfo.size() > 0) {
                hashMap.put("nearby", neighboringCellInfo);
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", "BSINFO");
            hashMap2.put(Config.CELL_LOCATION, g());
            hashMap2.put("data", hashMap);
            hashMap2.put("datetime", Long.valueOf(a.a()));
            b.a().a(a.a(), hashMap2);
            g.c(Data.MD5(q().fromHashMap(hashMap)));
        }
        g.b(a.a() + (((long) a.l()) * 1000));
    }

    private boolean n() throws Throwable {
        int i;
        try {
            i = Integer.parseInt(this.f.getCarrier());
        } catch (Throwable th) {
            i = -1;
        }
        int cellLac = this.f.getCellLac();
        int cellId = this.f.getCellId();
        if (i == -1 || cellLac == -1 || cellId == -1) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("carrier", Integer.valueOf(i));
        hashMap.put("simopname", this.f.getCarrierName());
        hashMap.put("lac", Integer.valueOf(cellLac));
        hashMap.put("cell", Integer.valueOf(cellId));
        String MD5 = Data.MD5(q().fromHashMap(hashMap));
        String c2 = g.c();
        return c2 == null || !c2.equals(MD5);
    }

    private void a(Location location, int i) {
        if (location != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("accuracy", Float.valueOf(location.getAccuracy()));
            hashMap.put("latitude", Double.valueOf(location.getLatitude()));
            hashMap.put("longitude", Double.valueOf(location.getLongitude()));
            hashMap.put("ltime", Long.valueOf(location.getTime()));
            hashMap.put("provider", location.getProvider());
            hashMap.put("altitude", Double.valueOf(location.getAltitude()));
            hashMap.put("bearing", Float.valueOf(location.getBearing()));
            hashMap.put("speed", Float.valueOf(location.getSpeed()));
            HashMap hashMap2 = new HashMap();
            if (i == 0) {
                hashMap2.put("type", "O_LOCATION");
            } else {
                hashMap2.put("type", "LOCATION");
                hashMap.put("location_type", Integer.valueOf(i));
            }
            String ssid = this.f.getSSID();
            String bssid = this.f.getBssid();
            if (!TextUtils.isEmpty(bssid)) {
                hashMap.put("cur_bssid", bssid);
            }
            if (!TextUtils.isEmpty(ssid)) {
                hashMap.put("cur_ssid", ssid);
            }
            hashMap2.put("data", hashMap);
            hashMap2.put("datetime", Long.valueOf(a.a()));
            if (i == 0) {
                TreeMap treeMap = new TreeMap();
                treeMap.put("latitude", Double.valueOf(location.getLatitude()));
                treeMap.put("longitude", Double.valueOf(location.getLongitude()));
                JSONObject jSONObject = new JSONObject(treeMap);
                String MD5 = Data.MD5(jSONObject.toString());
                String q2 = g.q();
                long r = g.r();
                long a2 = a.a();
                MobLog.getInstance().d("o_location: " + jSONObject.toString(), new Object[0]);
                if (TextUtils.isEmpty(q2) || !q2.equals(MD5) || a2 >= r) {
                    MobLog.getInstance().d("o_location: yes", new Object[0]);
                    b.a().a(a.a(), hashMap2);
                    g.j(MD5);
                    g.g((a.y() * 1000) + a2);
                    return;
                }
                MobLog.getInstance().d("o_location: no", new Object[0]);
                return;
            }
            b.a().a(a.a(), hashMap2);
        }
    }

    private void c(int i) {
        if (this.d == null) {
            this.d = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if ("android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                        i.this.b(8);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            try {
                MobSDK.getContext().registerReceiver(this.d, intentFilter);
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f A[Catch:{ Throwable -> 0x007f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void o() {
        /*
            r10 = this;
            com.mob.tools.utils.DeviceHelper r0 = r10.f     // Catch:{ Throwable -> 0x007f }
            java.util.ArrayList r0 = r0.getAvailableWifiList()     // Catch:{ Throwable -> 0x007f }
            if (r0 == 0) goto L_0x001a
            boolean r1 = r0.isEmpty()     // Catch:{ Throwable -> 0x007f }
            if (r1 != 0) goto L_0x001a
            java.util.ArrayList r3 = com.mob.commons.a.t()     // Catch:{ Throwable -> 0x007f }
            if (r3 == 0) goto L_0x001a
            boolean r1 = r3.isEmpty()     // Catch:{ Throwable -> 0x007f }
            if (r1 == 0) goto L_0x001b
        L_0x001a:
            return
        L_0x001b:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Throwable -> 0x007f }
            r4.<init>()     // Catch:{ Throwable -> 0x007f }
            com.mob.tools.utils.DeviceHelper r1 = r10.f     // Catch:{ Throwable -> 0x007f }
            java.lang.String r1 = r1.getBssid()     // Catch:{ Throwable -> 0x007f }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x007f }
            r5.<init>()     // Catch:{ Throwable -> 0x007f }
            java.util.Iterator r6 = r0.iterator()     // Catch:{ Throwable -> 0x007f }
        L_0x002f:
            boolean r0 = r6.hasNext()     // Catch:{ Throwable -> 0x007f }
            if (r0 == 0) goto L_0x008d
            java.lang.Object r0 = r6.next()     // Catch:{ Throwable -> 0x007f }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x007f }
            java.lang.String r2 = "BSSID"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x007f }
            if (r2 == 0) goto L_0x0103
            java.lang.String r7 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x007f }
            r5.add(r7)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x007f }
            boolean r2 = r2.equals(r1)     // Catch:{ Throwable -> 0x007f }
            if (r2 == 0) goto L_0x0103
            java.lang.String r1 = "___curConn"
            r2 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Throwable -> 0x007f }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x007f }
            r1 = 0
            r2 = r1
        L_0x0060:
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x007f }
            r7.<init>()     // Catch:{ Throwable -> 0x007f }
            java.util.Iterator r8 = r3.iterator()     // Catch:{ Throwable -> 0x007f }
        L_0x0069:
            boolean r1 = r8.hasNext()     // Catch:{ Throwable -> 0x007f }
            if (r1 == 0) goto L_0x0088
            java.lang.Object r1 = r8.next()     // Catch:{ Throwable -> 0x007f }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x007f }
            java.lang.Object r9 = r0.get(r1)     // Catch:{ Throwable -> 0x007f }
            if (r9 == 0) goto L_0x0069
            r7.put(r1, r9)     // Catch:{ Throwable -> 0x007f }
            goto L_0x0069
        L_0x007f:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            goto L_0x001a
        L_0x0088:
            r4.add(r7)     // Catch:{ Throwable -> 0x007f }
            r1 = r2
            goto L_0x002f
        L_0x008d:
            java.util.Collections.sort(r5)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r0 = ""
            java.lang.String r0 = android.text.TextUtils.join(r0, r5)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r0 = com.mob.tools.utils.Data.MD5(r0)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r1 = com.mob.commons.g.i()     // Catch:{ Throwable -> 0x007f }
            long r2 = com.mob.commons.g.p()     // Catch:{ Throwable -> 0x007f }
            long r6 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x007f }
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x007f }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x007f }
            r8.<init>()     // Catch:{ Throwable -> 0x007f }
            java.lang.String r9 = "wiHashLast: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x007f }
            java.lang.StringBuilder r8 = r8.append(r1)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x007f }
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x007f }
            r5.d(r8, r9)     // Catch:{ Throwable -> 0x007f }
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x007f }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x007f }
            r8.<init>()     // Catch:{ Throwable -> 0x007f }
            java.lang.String r9 = "wiHash: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x007f }
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x007f }
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x007f }
            r5.d(r8, r9)     // Catch:{ Throwable -> 0x007f }
            if (r1 == 0) goto L_0x00ec
            boolean r1 = r1.equals(r0)     // Catch:{ Throwable -> 0x007f }
            if (r1 == 0) goto L_0x00ec
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x001a
        L_0x00ec:
            r10.a(r4)     // Catch:{ Throwable -> 0x007f }
            com.mob.commons.g.g(r0)     // Catch:{ Throwable -> 0x007f }
            long r0 = com.mob.commons.a.a()     // Catch:{ Throwable -> 0x007f }
            int r2 = com.mob.commons.a.s()     // Catch:{ Throwable -> 0x007f }
            int r2 = r2 * 1000
            long r2 = (long) r2     // Catch:{ Throwable -> 0x007f }
            long r0 = r0 + r2
            com.mob.commons.g.f(r0)     // Catch:{ Throwable -> 0x007f }
            goto L_0x001a
        L_0x0103:
            r2 = r1
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.i.o():void");
    }

    private void a(ArrayList<HashMap<String, Object>> arrayList) {
        synchronized (this) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "WIFI_SCAN_LIST");
                hashMap.put("list", arrayList);
                hashMap.put(Config.CELL_LOCATION, g());
                hashMap.put("datetime", Long.valueOf(a.a()));
                b.a().a(a.a(), hashMap);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
    }

    private void p() {
        if (this.d != null) {
            try {
                MobSDK.getContext().unregisterReceiver(this.d);
            } catch (Throwable th) {
            }
            this.d = null;
        }
    }

    private Hashon q() {
        if (this.a == null) {
            this.a = new Hashon();
        }
        return this.a;
    }
}
