package com.baidu.location.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.g.e;
import com.baidu.location.g.j;
import com.baidu.location.h;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class c {
    private static Class<?> i = null;
    String a = null;
    String b = null;
    b c = new b();
    private Context d = null;
    private TelephonyManager e = null;
    private com.baidu.location.e.a f = new com.baidu.location.e.a();
    /* access modifiers changed from: private */
    public WifiManager g = null;
    private C0024c h = null;
    private String j = null;
    /* access modifiers changed from: private */
    public h k;
    /* access modifiers changed from: private */
    public a l;
    private String m = null;
    /* access modifiers changed from: private */
    public String n = null;
    /* access modifiers changed from: private */
    public String o = null;
    private boolean p = false;

    /* renamed from: q reason: collision with root package name */
    private long f368q = 0;
    /* access modifiers changed from: private */
    public boolean r = false;

    public interface a {
        void a(BDLocation bDLocation);
    }

    class b extends e {
        String a;

        b() {
            this.a = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = j.d();
            if (!(c.this.n == null || c.this.o == null)) {
                this.a += String.format(Locale.CHINA, "&ki=%s&sn=%s", new Object[]{c.this.n, c.this.o});
            }
            String f = Jni.f(this.a);
            this.a = null;
            this.k.put("bloc", f);
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        public void a(String str) {
            this.a = str;
            b(j.f);
        }

        public void a(boolean z) {
            BDLocation bDLocation;
            if (z && this.j != null) {
                try {
                    try {
                        bDLocation = new BDLocation(this.j);
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.e(63);
                    }
                    if (bDLocation != null) {
                        if (bDLocation.o() == 161) {
                            bDLocation.d(c.this.k.a);
                            bDLocation.c(Jni.b(c.this.a + ";" + c.this.b + ";" + bDLocation.g()));
                            c.this.r = true;
                            c.this.l.a(bDLocation);
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    /* renamed from: com.baidu.location.a.c$c reason: collision with other inner class name */
    protected class C0024c {
        public List<ScanResult> a = null;
        private long c = 0;

        public C0024c(List<ScanResult> list) {
            this.a = list;
            this.c = System.currentTimeMillis();
            c();
        }

        private String b() {
            if (c.this.g == null) {
                return null;
            }
            WifiInfo connectionInfo = c.this.g.getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            try {
                String bssid = connectionInfo.getBSSID();
                String str = bssid != null ? bssid.replace(Config.TRACE_TODAY_VISIT_SPLIT, "") : null;
                if (str == null || str.length() == 12) {
                    return new String(str);
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }

        private void c() {
            boolean z;
            if (a() >= 1) {
                boolean z2 = true;
                for (int size = this.a.size() - 1; size >= 1 && z2; size--) {
                    int i = 0;
                    z2 = false;
                    while (i < size) {
                        if (((ScanResult) this.a.get(i)).level < ((ScanResult) this.a.get(i + 1)).level) {
                            ScanResult scanResult = (ScanResult) this.a.get(i + 1);
                            this.a.set(i + 1, this.a.get(i));
                            this.a.set(i, scanResult);
                            z = true;
                        } else {
                            z = z2;
                        }
                        i++;
                        z2 = z;
                    }
                }
            }
        }

        public int a() {
            if (this.a == null) {
                return 0;
            }
            return this.a.size();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e0, code lost:
            if (r10 > r12) goto L_0x00e2;
         */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00e8  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00f6  */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0106  */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x0196 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String a(int r23) {
            /*
                r22 = this;
                int r2 = r22.a()
                r3 = 2
                if (r2 >= r3) goto L_0x0009
                r2 = 0
            L_0x0008:
                return r2
            L_0x0009:
                java.util.ArrayList r15 = new java.util.ArrayList
                r15.<init>()
                r4 = 0
                r12 = 0
                r2 = 0
                int r3 = android.os.Build.VERSION.SDK_INT
                r6 = 19
                if (r3 < r6) goto L_0x0199
                long r4 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch:{ Error -> 0x005d }
                r6 = 1000(0x3e8, double:4.94E-321)
                long r4 = r4 / r6
            L_0x0020:
                r6 = 0
                int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r3 <= 0) goto L_0x0199
                r2 = 1
                r3 = r2
            L_0x0028:
                java.lang.StringBuffer r16 = new java.lang.StringBuffer
                r2 = 512(0x200, float:7.175E-43)
                r0 = r16
                r0.<init>(r2)
                r0 = r22
                java.util.List<android.net.wifi.ScanResult> r2 = r0.a
                int r17 = r2.size()
                r6 = 1
                r9 = 0
                java.lang.String r18 = r22.b()
                r8 = 0
                r7 = 0
                r2 = 0
                r14 = r2
            L_0x0043:
                r0 = r17
                if (r14 >= r0) goto L_0x0196
                r0 = r22
                java.util.List<android.net.wifi.ScanResult> r2 = r0.a
                java.lang.Object r2 = r2.get(r14)
                android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
                int r2 = r2.level
                if (r2 != 0) goto L_0x0061
                r2 = r9
                r10 = r12
            L_0x0057:
                int r9 = r14 + 1
                r14 = r9
                r12 = r10
                r9 = r2
                goto L_0x0043
            L_0x005d:
                r3 = move-exception
                r4 = 0
                goto L_0x0020
            L_0x0061:
                int r8 = r8 + 1
                if (r6 == 0) goto L_0x00f9
                r2 = 0
                java.lang.String r6 = "&wf="
                r0 = r16
                r0.append(r6)
                r6 = r2
            L_0x006e:
                r0 = r22
                java.util.List<android.net.wifi.ScanResult> r2 = r0.a
                java.lang.Object r2 = r2.get(r14)
                android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
                java.lang.String r2 = r2.BSSID
                java.lang.String r10 = ":"
                java.lang.String r11 = ""
                java.lang.String r2 = r2.replace(r10, r11)
                r0 = r16
                r0.append(r2)
                if (r18 == 0) goto L_0x0092
                r0 = r18
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x0092
                r7 = r8
            L_0x0092:
                r0 = r22
                java.util.List<android.net.wifi.ScanResult> r2 = r0.a
                java.lang.Object r2 = r2.get(r14)
                android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
                int r2 = r2.level
                if (r2 >= 0) goto L_0x00a1
                int r2 = -r2
            L_0x00a1:
                java.util.Locale r10 = java.util.Locale.CHINA
                java.lang.String r11 = ";%d;"
                r19 = 1
                r0 = r19
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r19 = r0
                r20 = 0
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                r19[r20] = r2
                r0 = r19
                java.lang.String r2 = java.lang.String.format(r10, r11, r0)
                r0 = r16
                r0.append(r2)
                int r9 = r9 + 1
                if (r3 == 0) goto L_0x0193
                r0 = r22
                java.util.List<android.net.wifi.ScanResult> r2 = r0.a     // Catch:{ Throwable -> 0x0102 }
                java.lang.Object r2 = r2.get(r14)     // Catch:{ Throwable -> 0x0102 }
                android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2     // Catch:{ Throwable -> 0x0102 }
                long r10 = r2.timestamp     // Catch:{ Throwable -> 0x0102 }
                long r10 = r4 - r10
                r20 = 1000000(0xf4240, double:4.940656E-318)
                long r10 = r10 / r20
            L_0x00d7:
                java.lang.Long r2 = java.lang.Long.valueOf(r10)
                r15.add(r2)
                int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r2 <= 0) goto L_0x0193
            L_0x00e2:
                r0 = r23
                if (r9 <= r0) goto L_0x0190
            L_0x00e6:
                if (r7 <= 0) goto L_0x00f4
                java.lang.String r2 = "&wf_n="
                r0 = r16
                r0.append(r2)
                r0 = r16
                r0.append(r7)
            L_0x00f4:
                if (r6 == 0) goto L_0x0106
                r2 = 0
                goto L_0x0008
            L_0x00f9:
                java.lang.String r2 = "|"
                r0 = r16
                r0.append(r2)
                goto L_0x006e
            L_0x0102:
                r2 = move-exception
                r10 = 0
                goto L_0x00d7
            L_0x0106:
                r2 = 10
                int r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
                if (r2 <= 0) goto L_0x018a
                int r2 = r15.size()
                if (r2 <= 0) goto L_0x018a
                r2 = 0
                java.lang.Object r2 = r15.get(r2)
                java.lang.Long r2 = (java.lang.Long) r2
                long r2 = r2.longValue()
                r4 = 0
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 <= 0) goto L_0x018a
                java.lang.StringBuffer r5 = new java.lang.StringBuffer
                r2 = 128(0x80, float:1.794E-43)
                r5.<init>(r2)
                java.lang.String r2 = "&wf_ut="
                r5.append(r2)
                r3 = 1
                r2 = 0
                java.lang.Object r2 = r15.get(r2)
                java.lang.Long r2 = (java.lang.Long) r2
                java.util.Iterator r6 = r15.iterator()
                r4 = r3
            L_0x013c:
                boolean r3 = r6.hasNext()
                if (r3 == 0) goto L_0x0181
                java.lang.Object r3 = r6.next()
                java.lang.Long r3 = (java.lang.Long) r3
                if (r4 == 0) goto L_0x015a
                r4 = 0
                long r8 = r3.longValue()
                r5.append(r8)
                r3 = r4
            L_0x0153:
                java.lang.String r4 = "|"
                r5.append(r4)
                r4 = r3
                goto L_0x013c
            L_0x015a:
                long r8 = r3.longValue()
                long r10 = r2.longValue()
                long r8 = r8 - r10
                r10 = 0
                int r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
                if (r3 == 0) goto L_0x017f
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r7 = ""
                java.lang.StringBuilder r3 = r3.append(r7)
                java.lang.StringBuilder r3 = r3.append(r8)
                java.lang.String r3 = r3.toString()
                r5.append(r3)
            L_0x017f:
                r3 = r4
                goto L_0x0153
            L_0x0181:
                java.lang.String r2 = r5.toString()
                r0 = r16
                r0.append(r2)
            L_0x018a:
                java.lang.String r2 = r16.toString()
                goto L_0x0008
            L_0x0190:
                r2 = r9
                goto L_0x0057
            L_0x0193:
                r10 = r12
                goto L_0x00e2
            L_0x0196:
                r10 = r12
                goto L_0x00e6
            L_0x0199:
                r3 = r2
                goto L_0x0028
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.c.C0024c.a(int):java.lang.String");
        }
    }

    public c(Context context, h hVar, a aVar) {
        String str;
        this.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.p = true;
        if (this.p) {
            this.k = new h(hVar);
            this.l = aVar;
            this.a = this.d.getPackageName();
            this.b = null;
            try {
                this.e = (TelephonyManager) this.d.getSystemService("phone");
                this.g = (WifiManager) StubApp.getOrigApplicationContext(this.d.getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
                str = this.e.getDeviceId();
            } catch (Exception e2) {
                str = null;
            }
            try {
                this.b = com.baidu.a.a.a.b.a.a(this.d);
            } catch (Throwable th) {
                this.b = null;
                this.e = null;
                this.g = null;
            }
            if (this.b != null) {
                j.o = "" + this.b;
                this.j = "&prod=" + this.k.f + Config.TRACE_TODAY_VISIT_SPLIT + this.a + "|&cu=" + this.b + "&coor=" + hVar.a();
            } else {
                this.j = "&prod=" + this.k.f + Config.TRACE_TODAY_VISIT_SPLIT + this.a + "|&im=" + str + "&coor=" + hVar.a();
            }
            StringBuffer stringBuffer = new StringBuffer(256);
            stringBuffer.append("&fw=");
            stringBuffer.append("7.62");
            stringBuffer.append("&sdk=");
            stringBuffer.append("7.62");
            stringBuffer.append("&lt=1");
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
            stringBuffer.append("&resid=");
            stringBuffer.append("12");
            if (hVar.b() != null) {
            }
            if (hVar.b() != null && hVar.b().equals("all")) {
                this.j += "&addr=allj";
            }
            if (hVar.t) {
                stringBuffer.append("&adtp=n2");
            }
            if (hVar.o || hVar.p) {
                this.j += "&sema=";
                if (hVar.o) {
                    this.j += "aptag|";
                }
                if (hVar.p) {
                    this.j += "aptagd|";
                }
                this.n = j.b(this.d);
                this.o = j.c(this.d);
            }
            stringBuffer.append("&first=1");
            stringBuffer.append("&os=A");
            stringBuffer.append(VERSION.SDK);
            this.j += stringBuffer.toString();
            String a2 = a();
            if (!TextUtils.isEmpty(a2)) {
                a2 = a2.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
            }
            if (!TextUtils.isEmpty(a2) && !a2.equals("020000000000")) {
                this.j += "&mac=" + a2;
            }
            b();
        }
    }

    private int a(int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return -1;
        }
        return i2;
    }

    @SuppressLint({"NewApi"})
    private com.baidu.location.e.a a(CellInfo cellInfo) {
        boolean z = false;
        int i2 = -1;
        int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
        if (intValue < 17) {
            return null;
        }
        com.baidu.location.e.a aVar = new com.baidu.location.e.a();
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            aVar.c = a(cellIdentity.getMcc());
            aVar.d = a(cellIdentity.getMnc());
            aVar.a = a(cellIdentity.getLac());
            aVar.b = a(cellIdentity.getCid());
            aVar.i = 'g';
            aVar.h = ((CellInfoGsm) cellInfo).getCellSignalStrength().getAsuLevel();
            z = true;
        } else if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            aVar.e = cellIdentity2.getLatitude();
            aVar.f = cellIdentity2.getLongitude();
            aVar.d = a(cellIdentity2.getSystemId());
            aVar.a = a(cellIdentity2.getNetworkId());
            aVar.b = a(cellIdentity2.getBasestationId());
            aVar.i = 'c';
            aVar.h = ((CellInfoCdma) cellInfo).getCellSignalStrength().getCdmaDbm();
            if (this.f == null || this.f.c <= 0) {
                try {
                    String networkOperator = this.e.getNetworkOperator();
                    if (networkOperator != null && networkOperator.length() > 0 && networkOperator.length() >= 3) {
                        int intValue2 = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue2 < 0) {
                            intValue2 = -1;
                        }
                        i2 = intValue2;
                    }
                } catch (Exception e2) {
                }
                if (i2 > 0) {
                    aVar.c = i2;
                }
            } else {
                aVar.c = this.f.c;
            }
            z = true;
        } else if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            aVar.c = a(cellIdentity3.getMcc());
            aVar.d = a(cellIdentity3.getMnc());
            aVar.a = a(cellIdentity3.getTac());
            aVar.b = a(cellIdentity3.getCi());
            aVar.i = 'g';
            aVar.h = ((CellInfoLte) cellInfo).getCellSignalStrength().getAsuLevel();
            z = true;
        }
        if (intValue >= 18 && !z) {
            try {
                if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    aVar.c = a(cellIdentity4.getMcc());
                    aVar.d = a(cellIdentity4.getMnc());
                    aVar.a = a(cellIdentity4.getLac());
                    aVar.b = a(cellIdentity4.getCid());
                    aVar.i = 'g';
                    aVar.h = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getAsuLevel();
                }
            } catch (Exception e3) {
            }
        }
        try {
            aVar.g = System.currentTimeMillis() - ((SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000);
        } catch (Error e4) {
            aVar.g = System.currentTimeMillis();
        }
        return aVar;
    }

    private void a(CellLocation cellLocation) {
        int i2 = 0;
        if (cellLocation != null && this.e != null) {
            com.baidu.location.e.a aVar = new com.baidu.location.e.a();
            String networkOperator = this.e.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.f.c;
                        }
                        aVar.c = intValue;
                    }
                    String substring = networkOperator.substring(3);
                    if (substring != null) {
                        char[] charArray = substring.toCharArray();
                        while (i2 < charArray.length && Character.isDigit(charArray[i2])) {
                            i2++;
                        }
                        int intValue2 = Integer.valueOf(substring.substring(0, i2)).intValue();
                        if (intValue2 < 0) {
                            intValue2 = this.f.d;
                        }
                        aVar.d = intValue2;
                    }
                } catch (Exception e2) {
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                aVar.a = ((GsmCellLocation) cellLocation).getLac();
                aVar.b = ((GsmCellLocation) cellLocation).getCid();
                aVar.i = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                aVar.i = 'c';
                if (i == null) {
                    try {
                        i = Class.forName("android.telephony.cdma.CdmaCellLocation");
                    } catch (Exception e3) {
                        i = null;
                        return;
                    }
                }
                if (i != null && i.isInstance(cellLocation)) {
                    try {
                        int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                        if (systemId < 0) {
                            systemId = -1;
                        }
                        aVar.d = systemId;
                        aVar.b = ((CdmaCellLocation) cellLocation).getBaseStationId();
                        aVar.a = ((CdmaCellLocation) cellLocation).getNetworkId();
                        int baseStationLatitude = ((CdmaCellLocation) cellLocation).getBaseStationLatitude();
                        if (baseStationLatitude < Integer.MAX_VALUE) {
                            aVar.e = baseStationLatitude;
                        }
                        int baseStationLongitude = ((CdmaCellLocation) cellLocation).getBaseStationLongitude();
                        if (baseStationLongitude < Integer.MAX_VALUE) {
                            aVar.f = baseStationLongitude;
                        }
                    } catch (Exception e4) {
                    }
                }
            }
            if (aVar.b()) {
                this.f = aVar;
            } else {
                this.f = null;
            }
        }
    }

    private String b(int i2) {
        String str;
        String str2;
        try {
            com.baidu.location.e.a d2 = d();
            if (d2 == null || !d2.b()) {
                a(this.e.getCellLocation());
            } else {
                this.f = d2;
            }
            str = (this.f == null || !this.f.b()) ? null : this.f.h();
            try {
                if (!(TextUtils.isEmpty(str) || this.f == null || this.f.j == null)) {
                    str = str + this.f.j;
                }
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            str = null;
        }
        try {
            this.h = null;
            this.h = new C0024c(this.g.getScanResults());
            str2 = this.h.a(i2);
        } catch (Exception e2) {
            str2 = null;
        }
        if (str == null && str2 == null) {
            this.m = null;
            return null;
        }
        if (str2 != null) {
            str = str == null ? str2 : str + str2;
        }
        if (str == null) {
            return null;
        }
        this.m = str;
        if (this.j != null) {
            this.m += this.j;
        }
        return str + this.j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        if (r1 == null) goto L_0x0049;
     */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.location.e.a d() {
        /*
            r6 = this;
            r2 = 0
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r0 = r0.intValue()
            r1 = 17
            if (r0 >= r1) goto L_0x0011
            r1 = r2
        L_0x0010:
            return r1
        L_0x0011:
            android.telephony.TelephonyManager r0 = r6.e     // Catch:{ Throwable -> 0x0056 }
            java.util.List r0 = r0.getAllCellInfo()     // Catch:{ Throwable -> 0x0056 }
            if (r0 == 0) goto L_0x005b
            int r1 = r0.size()     // Catch:{ Throwable -> 0x0056 }
            if (r1 <= 0) goto L_0x005b
            java.util.Iterator r4 = r0.iterator()     // Catch:{ Throwable -> 0x0056 }
            r1 = r2
        L_0x0024:
            boolean r0 = r4.hasNext()     // Catch:{ Throwable -> 0x0056 }
            if (r0 == 0) goto L_0x0010
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x0056 }
            android.telephony.CellInfo r0 = (android.telephony.CellInfo) r0     // Catch:{ Throwable -> 0x0056 }
            boolean r3 = r0.isRegistered()     // Catch:{ Throwable -> 0x0056 }
            if (r3 == 0) goto L_0x0059
            r3 = 0
            if (r1 == 0) goto L_0x003a
            r3 = 1
        L_0x003a:
            com.baidu.location.e.a r0 = r6.a(r0)     // Catch:{ Throwable -> 0x0056 }
            if (r0 == 0) goto L_0x0024
            boolean r5 = r0.b()     // Catch:{ Throwable -> 0x0056 }
            if (r5 != 0) goto L_0x004b
            r0 = r2
        L_0x0047:
            if (r1 != 0) goto L_0x0059
        L_0x0049:
            r1 = r0
            goto L_0x0024
        L_0x004b:
            if (r3 == 0) goto L_0x0047
            if (r1 == 0) goto L_0x0047
            java.lang.String r0 = r0.i()     // Catch:{ Throwable -> 0x0056 }
            r1.j = r0     // Catch:{ Throwable -> 0x0056 }
            goto L_0x0010
        L_0x0056:
            r0 = move-exception
            r1 = r2
            goto L_0x0010
        L_0x0059:
            r0 = r1
            goto L_0x0049
        L_0x005b:
            r1 = r2
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.c.d():com.baidu.location.e.a");
    }

    public String a() {
        try {
            WifiInfo connectionInfo = this.g.getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        } catch (Error | Exception e2) {
            return null;
        }
    }

    public String b() {
        try {
            return b(15);
        } catch (Exception e2) {
            return null;
        }
    }

    public void c() {
        BDLocation bDLocation;
        BDLocation bDLocation2 = null;
        if (this.m != null && this.p) {
            if (this.g != null && this.k.d >= 1000 && !this.k.b().equals("all") && !this.k.o && !this.k.p) {
                try {
                    String str = this.f != null ? this.f.g() : null;
                    if (this.g != null) {
                        bDLocation = com.baidu.location.d.a.a().a(str, this.g.getScanResults(), false);
                        if (bDLocation != null && bDLocation.o() == 66 && Math.abs(bDLocation.h()) < 0.10000000149011612d && Math.abs(bDLocation.i()) < 0.10000000149011612d) {
                            bDLocation.e(67);
                        }
                    } else {
                        bDLocation = null;
                    }
                    if (bDLocation == null || bDLocation.o() != 66) {
                    }
                    if (bDLocation == null || bDLocation.o() != 66) {
                    }
                    if (!this.k.a.equals("gcj02") && bDLocation != null && bDLocation.o() == 66) {
                        double i2 = bDLocation.i();
                        double h2 = bDLocation.h();
                        if (Math.abs(i2) > 0.10000000149011612d && Math.abs(h2) > 0.10000000149011612d) {
                            double[] a2 = Jni.a(i2, h2, this.k.a);
                            bDLocation.b(a2[0]);
                            bDLocation.a(a2[1]);
                            bDLocation.d(this.k.a);
                        }
                    }
                    if (bDLocation == null || bDLocation.o() != 66 || Math.abs(bDLocation.h()) <= 0.10000000149011612d || Math.abs(bDLocation.i()) <= 0.10000000149011612d) {
                        bDLocation = null;
                    } else if (!this.r) {
                        this.l.a(bDLocation);
                    }
                    bDLocation2 = bDLocation;
                } catch (Exception e2) {
                }
            }
            if (bDLocation2 == null) {
                this.c.a(this.m);
            }
        }
    }
}
