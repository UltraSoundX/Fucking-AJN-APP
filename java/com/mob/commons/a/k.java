package com.mob.commons.a;

import android.os.Message;
import android.os.SystemClock;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

/* compiled from: IMcClt */
public class k extends d {
    private Random a;
    private DeviceHelper b;
    private HashMap<String, Object> c;

    /* compiled from: IMcClt */
    private static class a implements Comparator<HashMap<String, Object>> {
        private a() {
        }

        /* renamed from: a */
        public int compare(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
            if ((hashMap == null || hashMap.isEmpty()) && (hashMap2 == null || hashMap2.isEmpty())) {
                return 0;
            }
            if ((hashMap == null || hashMap.isEmpty()) && hashMap2 != null && !hashMap2.isEmpty()) {
                return -1;
            }
            if (hashMap != null && !hashMap.isEmpty() && (hashMap2 == null || hashMap2.isEmpty())) {
                return 1;
            }
            try {
                String str = (String) hashMap.get("ip");
                String str2 = (String) hashMap2.get("ip");
                if (str == null || "".equals(str) || str2 == null || "".equals(str2)) {
                    return 0;
                }
                String[] split = str.split("\\.");
                String[] split2 = str2.split("\\.");
                if (split == null || split.length != 4 || split2 == null || split2.length != 4) {
                    return 0;
                }
                int intValue = Integer.valueOf(split[2]).intValue();
                int intValue2 = Integer.valueOf(split2[2]).intValue();
                if (intValue < intValue2) {
                    return -1;
                }
                if (intValue != intValue2) {
                    return 1;
                }
                int intValue3 = Integer.valueOf(split[3]).intValue();
                int intValue4 = Integer.valueOf(split2[3]).intValue();
                if (intValue3 < intValue4) {
                    return -1;
                }
                if (intValue3 == intValue4) {
                    return 0;
                }
                return 1;
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
                return 0;
            }
        }
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.im_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return com.mob.commons.a.S() > 0;
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (com.mob.commons.a.S() > 0) {
            a(1, (long) l());
            return;
        }
        MobLog.getInstance().d("[%s] %s", "IMcClt", "Config no process");
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (this.b == null) {
            this.b = DeviceHelper.getInstance(MobSDK.getContext());
        }
        switch (message.what) {
            case 1:
                i();
                return;
            default:
                return;
        }
    }

    private void i() {
        MobLog.getInstance().d("[%s] %s", "IMcClt", ">>> Pre-obtain");
        if (NetworkUtil.NETWORK_WIFI.equals(this.b.getNetworkType())) {
            if (this.c == null || this.c.isEmpty()) {
                this.c = a("comm/dbs/.imcd");
            }
            if (this.c == null || this.c.isEmpty()) {
                j();
            } else {
                String bssid = this.b.getBssid();
                if (this.c.containsKey(bssid)) {
                    if (com.mob.commons.a.a() >= ((Long) this.c.get(bssid)).longValue()) {
                        j();
                    } else {
                        MobLog.getInstance().d("[%s] %s", "IMcClt", "Interval not reached");
                    }
                } else {
                    j();
                }
            }
            this.c = null;
            this.a = null;
            this.b = null;
            return;
        }
        MobLog.getInstance().d("[%s] %s", "IMcClt", "No wifi");
        f();
    }

    private void j() {
        try {
            MobLog.getInstance().d("[%s] %s", "IMcClt", ">>> Obtain");
            if (NetworkUtil.NETWORK_WIFI.equals(this.b.getNetworkType())) {
                MobLog.getInstance().d("[%s] %s", "IMcClt", "Communicating");
                k();
                MobLog.getInstance().d("[%s] %s", "IMcClt", "Waiting for update");
                SystemClock.sleep(Config.BPLUS_DELAY_TIME);
                MobLog.getInstance().d("[%s] %s", "IMcClt", "Obtaining");
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (arrayList.isEmpty() && i < 8) {
                    ArrayList arpList = this.b.getArpList();
                    SystemClock.sleep(arpList.size() > 0 ? 0 : 1000);
                    i++;
                    arrayList = arpList;
                }
                Collections.sort(arrayList, new a());
                HashMap hashMap = new HashMap();
                hashMap.put("bssid", this.b.getBssid());
                hashMap.put("ssid", this.b.getSSID());
                hashMap.put("curip", this.b.getIPAddress());
                hashMap.put("list", arrayList);
                b(hashMap, "WIFI_IP_MAC");
                return;
            }
            MobLog.getInstance().d("[%s] %s", "IMcClt", "No wifi");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private void k() {
        int i = 2;
        String[] split = this.b.getIPAddress().split("\\.");
        if (split.length > 2) {
            String str = split[0] + "." + split[1] + "." + split[2] + ".";
            try {
                DatagramPacket datagramPacket = new DatagramPacket(new byte[0], 0, 0);
                DatagramSocket datagramSocket = new DatagramSocket();
                while (i < 255) {
                    datagramPacket.setAddress(InetAddress.getByName(str + String.valueOf(i)));
                    datagramSocket.send(datagramPacket);
                    i++;
                    if (i == 125) {
                        datagramSocket.close();
                        datagramSocket = new DatagramSocket();
                    }
                }
                datagramSocket.close();
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> a(java.lang.String r5) {
        /*
            r4 = this;
            r2 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r5)     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            if (r1 != 0) goto L_0x0018
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            r0.<init>()     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            r4.a(r2)
        L_0x0017:
            return r0
        L_0x0018:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0033, all -> 0x0041 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ Throwable -> 0x0049 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0049 }
            r4.a(r1)
        L_0x002b:
            if (r0 != 0) goto L_0x0017
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            goto L_0x0017
        L_0x0033:
            r0 = move-exception
            r1 = r2
        L_0x0035:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0046 }
            r3.d(r0)     // Catch:{ all -> 0x0046 }
            r4.a(r1)
            r0 = r2
            goto L_0x002b
        L_0x0041:
            r0 = move-exception
        L_0x0042:
            r4.a(r2)
            throw r0
        L_0x0046:
            r0 = move-exception
            r2 = r1
            goto L_0x0042
        L_0x0049:
            r0 = move-exception
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.k.a(java.lang.String):java.util.HashMap");
    }

    private void a(HashMap<String, Object> hashMap, String str) {
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), str);
            if (hashMap == null || hashMap.isEmpty()) {
                cacheRootFile.delete();
                a((Closeable) null);
                return;
            }
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheRootFile));
            try {
                objectOutputStream.writeObject(hashMap);
                a((Closeable) objectOutputStream);
            } catch (Throwable th) {
                th = th;
                try {
                    MobLog.getInstance().w(th);
                    a((Closeable) objectOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream;
                    a((Closeable) objectOutputStream2);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            a((Closeable) objectOutputStream2);
            throw th;
        }
    }

    private void b(HashMap<String, Object> hashMap, String str) {
        MobLog.getInstance().d("[%s] %s", "IMcClt", "Write into queue");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", str);
        hashMap2.put("data", hashMap);
        hashMap2.put(Config.CELL_LOCATION, g());
        long a2 = com.mob.commons.a.a();
        hashMap2.put("datetime", Long.valueOf(a2));
        b.a().a(a2, hashMap2);
        if (this.c == null) {
            this.c = new HashMap<>();
        }
        if (this.c.size() >= 500) {
            this.c.clear();
        }
        this.c.put((String) hashMap.get("bssid"), Long.valueOf(a2 + (com.mob.commons.a.T() * 1000)));
        a(this.c, "comm/dbs/.imcd");
    }

    private int l() {
        int i = 0;
        if (com.mob.commons.a.S() > 0) {
            if (this.a == null) {
                this.a = new Random();
            }
            i = this.a.nextInt((int) com.mob.commons.a.S());
        }
        return i * 1000;
    }
}
