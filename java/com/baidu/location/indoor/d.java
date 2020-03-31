package com.baidu.location.indoor;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.mobstat.Config;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class d {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static d b = null;
    private Context c;
    private boolean d = false;
    private boolean e = false;
    /* access modifiers changed from: private */
    public BluetoothAdapter f;
    private boolean g = false;
    private b h;
    private boolean i = false;
    private String j = null;
    private long k = -1;
    /* access modifiers changed from: private */
    public HashMap<String, ScanResult> l = new HashMap<>();
    private Handler m = new Handler();
    private Runnable n = new e(this);
    private Object o = null;

    public static class a implements Comparable<a> {
        public String a;
        public int b;
        public long c;

        public a(String str, int i, long j) {
            this.a = str;
            this.b = i;
            this.c = j / 1000000;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            return Math.abs(this.b) > Math.abs(aVar.b) ? 1 : 0;
        }

        public String toString() {
            return this.a.toUpperCase() + ";" + this.b + ";" + this.c;
        }
    }

    public interface b {
        void a(boolean z, String str, String str2, String str3);
    }

    @TargetApi(18)
    private d(Context context) {
        this.c = context;
        if (this.f == null) {
            try {
                if (VERSION.SDK_INT > 18) {
                    this.f = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
                    this.g = this.c.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
                    return;
                }
                this.f = BluetoothAdapter.getDefaultAdapter();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static d a(Context context) {
        if (b == null) {
            b = new d(context);
        }
        return b;
    }

    private String a(List<a> list, int i2) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        sb.append(((a) list.get(0)).toString());
        int i3 = 1;
        while (true) {
            int i4 = i3;
            if (i4 < list.size() && i4 < i2) {
                sb.append("|").append(((a) list.get(i4)).toString());
                i3 = i4 + 1;
            }
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
            cArr[i2 * 2] = a[b2 >>> 4];
            cArr[(i2 * 2) + 1] = a[b2 & 15];
        }
        return new String(cArr);
    }

    /* access modifiers changed from: private */
    @TargetApi(21)
    public void a(HashMap<String, ScanResult> hashMap) {
        int i2;
        ArrayList<ScanResult> arrayList = new ArrayList<>(hashMap.values());
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        for (ScanResult scanResult : arrayList) {
            arrayList3.add(new a(scanResult.getDevice().getAddress().replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, ""), scanResult.getRssi(), scanResult.getTimestampNanos()));
            if (this.d) {
                scanResult.getScanRecord().getAdvertiseFlags();
                byte[] bytes = scanResult.getScanRecord().getBytes();
                if (bytes.length >= 26) {
                    String a2 = a(Arrays.copyOfRange(bytes, 9, 25));
                    arrayList2.add(a2);
                    hashMap2.put(a2, scanResult.getDevice().getName());
                    hashMap3.put(a2, a(Arrays.copyOfRange(bytes, 0, 9)));
                    if (hashMap4.get(a2) == null) {
                        hashMap4.put(a2, Integer.valueOf(0));
                    }
                    hashMap4.put(a2, Integer.valueOf(((Integer) hashMap4.get(a2)).intValue() + 1));
                }
            }
        }
        int i3 = 0;
        String str = null;
        for (String str2 : hashMap4.keySet()) {
            if (((Integer) hashMap4.get(str2)).intValue() > i3) {
                i2 = ((Integer) hashMap4.get(str2)).intValue();
            } else {
                str2 = str;
                i2 = i3;
            }
            str = str2;
            i3 = i2;
        }
        boolean z = i3 > 3;
        if (this.h != null && this.d) {
            this.h.a(z, str, (String) hashMap2.get(str), (String) hashMap3.get(str));
            this.d = false;
        }
        if (arrayList3.size() > 3) {
            this.j = a((List<a>) arrayList3, 32);
            this.k = System.currentTimeMillis();
        }
        if (this.i) {
            a(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean h() {
        /*
            r6 = this;
            r0 = 0
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r6.c
            java.io.File r2 = r2.getCacheDir()
            java.lang.String r3 = "ibct"
            r1.<init>(r2, r3)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x0015
        L_0x0014:
            return r0
        L_0x0015:
            r2 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0062 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x0062 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0062 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0062 }
            java.lang.String r3 = ""
        L_0x0022:
            java.lang.String r2 = r4.readLine()     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x003a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0062 }
            r1.<init>()     // Catch:{ Exception -> 0x0062 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x0062 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0062 }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x0062 }
            goto L_0x0022
        L_0x003a:
            r4.close()     // Catch:{ Exception -> 0x006a }
        L_0x003d:
            if (r3 == 0) goto L_0x0014
            java.lang.String r1 = r3.trim()
            java.lang.String r2 = ""
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0014
            java.lang.Long r1 = java.lang.Long.valueOf(r3)     // Catch:{ Exception -> 0x0068 }
            long r2 = r1.longValue()     // Catch:{ Exception -> 0x0068 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0068 }
            long r2 = r4 - r2
            r4 = 259200(0x3f480, double:1.28062E-318)
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 >= 0) goto L_0x0014
            r0 = 1
            goto L_0x0014
        L_0x0062:
            r1 = move-exception
        L_0x0063:
            r1.printStackTrace()
            r3 = r2
            goto L_0x003d
        L_0x0068:
            r1 = move-exception
            goto L_0x0014
        L_0x006a:
            r1 = move-exception
            r2 = r3
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.d.h():boolean");
    }

    private void i() {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.c.getCacheDir(), "ibct"));
            fileWriter.write(System.currentTimeMillis() + "");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e2) {
        }
    }

    @TargetApi(21)
    public void a(boolean z) {
        if (this.f != null) {
            try {
                if (VERSION.SDK_INT < 21) {
                    return;
                }
                if (z) {
                    this.o = new f(this);
                    this.f.getBluetoothLeScanner().startScan((ScanCallback) this.o);
                    this.m.postDelayed(this.n, 3000);
                    this.d = true;
                    return;
                }
                if (this.h != null) {
                    this.f.getBluetoothLeScanner().stopScan((ScanCallback) this.o);
                }
                this.d = false;
            } catch (Exception e2) {
            }
        }
    }

    public boolean a() {
        boolean z = false;
        if (this.f == null || !this.g) {
            return z;
        }
        try {
            return this.f.isEnabled();
        } catch (Exception e2) {
            e2.printStackTrace();
            return z;
        }
    }

    public boolean a(b bVar) {
        if (this.d || this.e) {
            return false;
        }
        this.e = true;
        if (!a() || h()) {
            return false;
        }
        i();
        this.h = bVar;
        a(true);
        return true;
    }

    public boolean b() {
        if (!a()) {
            return false;
        }
        a(true);
        this.i = true;
        return true;
    }

    public void c() {
        this.e = false;
        this.d = false;
    }

    public void d() {
        this.i = false;
    }

    public String e() {
        return this.j;
    }

    public long f() {
        return this.k;
    }

    public boolean g() {
        return System.currentTimeMillis() - this.k <= 20000;
    }
}
