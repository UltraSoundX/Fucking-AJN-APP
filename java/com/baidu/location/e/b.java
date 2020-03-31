package com.baidu.location.e;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Handler;
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
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.baidu.location.f;
import com.baidu.location.g.j;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@SuppressLint({"NewApi"})
public class b {
    public static int a = 0;
    public static int b = 0;
    private static b c = null;
    private static Class<?> k = null;
    private TelephonyManager d = null;
    /* access modifiers changed from: private */
    public a e = new a();
    private a f = null;
    private List<a> g = null;
    private a h = null;
    private boolean i = false;
    private boolean j = false;
    /* access modifiers changed from: private */
    public Handler l = new Handler();

    private class a extends PhoneStateListener {
        public a() {
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            if (cellLocation != null) {
                b.this.l.post(new c(this));
            }
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (b.this.e == null) {
                return;
            }
            if (b.this.e.i == 'g') {
                b.this.e.h = signalStrength.getGsmSignalStrength();
            } else if (b.this.e.i == 'c') {
                b.this.e.h = signalStrength.getCdmaDbm();
            }
        }
    }

    private b() {
    }

    private int a(int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return -1;
        }
        return i2;
    }

    @SuppressLint({"NewApi"})
    private a a(CellInfo cellInfo) {
        boolean z = false;
        int i2 = -1;
        int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
        if (intValue < 17) {
            return null;
        }
        a aVar = new a();
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
            if (this.e == null || this.e.c <= 0) {
                try {
                    String networkOperator = this.d.getNetworkOperator();
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
                aVar.c = this.e.c;
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

    private a a(CellLocation cellLocation) {
        return a(cellLocation, false);
    }

    private a a(CellLocation cellLocation, boolean z) {
        if (cellLocation == null || this.d == null) {
            return null;
        }
        a aVar = new a();
        if (z) {
            aVar.f();
        }
        aVar.g = System.currentTimeMillis();
        try {
            String networkOperator = this.d.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                int i2 = -1;
                if (networkOperator.length() >= 3) {
                    i2 = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    aVar.c = i2 < 0 ? this.e.c : i2;
                }
                String substring = networkOperator.substring(3);
                if (substring != null) {
                    char[] charArray = substring.toCharArray();
                    int i3 = 0;
                    while (i3 < charArray.length && Character.isDigit(charArray[i3])) {
                        i3++;
                    }
                    i2 = Integer.valueOf(substring.substring(0, i3)).intValue();
                }
                if (i2 < 0) {
                    i2 = this.e.d;
                }
                aVar.d = i2;
            }
            a = this.d.getSimState();
        } catch (Exception e2) {
            b = 1;
        }
        if (cellLocation instanceof GsmCellLocation) {
            aVar.a = ((GsmCellLocation) cellLocation).getLac();
            aVar.b = ((GsmCellLocation) cellLocation).getCid();
            aVar.i = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            aVar.i = 'c';
            if (k == null) {
                try {
                    k = Class.forName("android.telephony.cdma.CdmaCellLocation");
                } catch (Exception e3) {
                    k = null;
                    return aVar;
                }
            }
            if (k != null && k.isInstance(cellLocation)) {
                try {
                    int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                    if (systemId < 0) {
                        systemId = this.e.d;
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
                    b = 3;
                    return aVar;
                }
            }
        }
        c(aVar);
        return aVar;
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
            }
            bVar = c;
        }
        return bVar;
    }

    private void c(a aVar) {
        if (!aVar.b()) {
            return;
        }
        if (this.e == null || !this.e.a(aVar)) {
            this.e = aVar;
            if (aVar.b()) {
                int size = this.g.size();
                a aVar2 = size == 0 ? null : (a) this.g.get(size - 1);
                if (aVar2 == null || aVar2.b != this.e.b || aVar2.a != this.e.a) {
                    this.g.add(this.e);
                    if (this.g.size() > 3) {
                        this.g.remove(0);
                    }
                    j();
                    this.j = false;
                }
            } else if (this.g != null) {
                this.g.clear();
            }
        }
    }

    @SuppressLint({"NewApi"})
    private String d(a aVar) {
        StringBuilder sb = new StringBuilder();
        if (Integer.valueOf(VERSION.SDK_INT).intValue() >= 17) {
            try {
                List<CellInfo> allCellInfo = this.d.getAllCellInfo();
                if (allCellInfo != null && allCellInfo.size() > 0) {
                    sb.append("&nc=");
                    for (CellInfo cellInfo : allCellInfo) {
                        if (!cellInfo.isRegistered()) {
                            a a2 = a(cellInfo);
                            if (!(a2 == null || a2.a == -1 || a2.b == -1)) {
                                if (aVar.a != a2.a) {
                                    sb.append(a2.a + "|" + a2.b + "|" + a2.h + ";");
                                } else {
                                    sb.append("|" + a2.b + "|" + a2.h + ";");
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return sb.toString();
    }

    private void i() {
        String h2 = j.h();
        if (h2 != null) {
            File file = new File(h2 + File.separator + "lcvif.dat");
            if (file.exists()) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    if (System.currentTimeMillis() - randomAccessFile.readLong() > 60000) {
                        randomAccessFile.close();
                        file.delete();
                        return;
                    }
                    randomAccessFile.readInt();
                    for (int i2 = 0; i2 < 3; i2++) {
                        long readLong = randomAccessFile.readLong();
                        int readInt = randomAccessFile.readInt();
                        int readInt2 = randomAccessFile.readInt();
                        int readInt3 = randomAccessFile.readInt();
                        int readInt4 = randomAccessFile.readInt();
                        int readInt5 = randomAccessFile.readInt();
                        char c2 = 0;
                        if (readInt5 == 1) {
                            c2 = 'g';
                        }
                        if (readInt5 == 2) {
                            c2 = 'c';
                        }
                        if (readLong != 0) {
                            a aVar = new a(readInt3, readInt4, readInt, readInt2, 0, c2);
                            aVar.g = readLong;
                            if (aVar.b()) {
                                this.j = true;
                                this.g.add(aVar);
                            }
                        }
                    }
                    randomAccessFile.close();
                } catch (Exception e2) {
                    file.delete();
                }
            }
        }
    }

    private void j() {
        if (this.g != null || this.f != null) {
            if (this.g == null && this.f != null) {
                this.g = new LinkedList();
                this.g.add(this.f);
            }
            String h2 = j.h();
            if (h2 != null && this.g != null) {
                File file = new File(h2 + File.separator + "lcvif.dat");
                int size = this.g.size();
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeLong(((a) this.g.get(size - 1)).g);
                    randomAccessFile.writeInt(size);
                    for (int i2 = 0; i2 < 3 - size; i2++) {
                        randomAccessFile.writeLong(0);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(2);
                    }
                    for (int i3 = 0; i3 < size; i3++) {
                        randomAccessFile.writeLong(((a) this.g.get(i3)).g);
                        randomAccessFile.writeInt(((a) this.g.get(i3)).c);
                        randomAccessFile.writeInt(((a) this.g.get(i3)).d);
                        randomAccessFile.writeInt(((a) this.g.get(i3)).a);
                        randomAccessFile.writeInt(((a) this.g.get(i3)).b);
                        if (((a) this.g.get(i3)).i == 'g') {
                            randomAccessFile.writeInt(1);
                        } else if (((a) this.g.get(i3)).i == 'c') {
                            randomAccessFile.writeInt(2);
                        } else {
                            randomAccessFile.writeInt(3);
                        }
                    }
                    randomAccessFile.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        CellLocation cellLocation;
        a l2 = l();
        if (l2 != null) {
            c(l2);
        }
        if (l2 == null || !l2.b()) {
            try {
                cellLocation = this.d.getCellLocation();
            } catch (Throwable th) {
                cellLocation = null;
            }
            if (cellLocation != null) {
                a(cellLocation);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        if (r1 == null) goto L_0x0051;
     */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.location.e.a l() {
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
            android.telephony.TelephonyManager r0 = r6.d     // Catch:{ Throwable -> 0x005e }
            int r0 = r0.getSimState()     // Catch:{ Throwable -> 0x005e }
            a = r0     // Catch:{ Throwable -> 0x005e }
            android.telephony.TelephonyManager r0 = r6.d     // Catch:{ Throwable -> 0x005e }
            java.util.List r0 = r0.getAllCellInfo()     // Catch:{ Throwable -> 0x005e }
            if (r0 == 0) goto L_0x0063
            int r1 = r0.size()     // Catch:{ Throwable -> 0x005e }
            if (r1 <= 0) goto L_0x0063
            java.util.Iterator r4 = r0.iterator()     // Catch:{ Throwable -> 0x005e }
            r1 = r2
        L_0x002c:
            boolean r0 = r4.hasNext()     // Catch:{ Throwable -> 0x005e }
            if (r0 == 0) goto L_0x0010
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x005e }
            android.telephony.CellInfo r0 = (android.telephony.CellInfo) r0     // Catch:{ Throwable -> 0x005e }
            boolean r3 = r0.isRegistered()     // Catch:{ Throwable -> 0x005e }
            if (r3 == 0) goto L_0x0061
            r3 = 0
            if (r1 == 0) goto L_0x0042
            r3 = 1
        L_0x0042:
            com.baidu.location.e.a r0 = r6.a(r0)     // Catch:{ Throwable -> 0x005e }
            if (r0 == 0) goto L_0x002c
            boolean r5 = r0.b()     // Catch:{ Throwable -> 0x005e }
            if (r5 != 0) goto L_0x0053
            r0 = r2
        L_0x004f:
            if (r1 != 0) goto L_0x0061
        L_0x0051:
            r1 = r0
            goto L_0x002c
        L_0x0053:
            if (r3 == 0) goto L_0x004f
            if (r1 == 0) goto L_0x004f
            java.lang.String r0 = r0.i()     // Catch:{ Throwable -> 0x005e }
            r1.j = r0     // Catch:{ Throwable -> 0x005e }
            goto L_0x0010
        L_0x005e:
            r0 = move-exception
            r1 = r2
            goto L_0x0010
        L_0x0061:
            r0 = r1
            goto L_0x0051
        L_0x0063:
            r1 = r2
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.b.l():com.baidu.location.e.a");
    }

    public String a(a aVar) {
        String str;
        String str2 = "";
        try {
            str = d(aVar);
            int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
            if ((str != null && !str.equals("") && !str.equals("&nc=")) || intValue >= 17) {
                return str;
            }
            List neighboringCellInfo = this.d.getNeighboringCellInfo();
            if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                String str3 = "&nc=";
                Iterator it = neighboringCellInfo.iterator();
                int i2 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        str = str3;
                        break;
                    }
                    NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) it.next();
                    int lac = neighboringCellInfo2.getLac();
                    str = (lac == -1 || neighboringCellInfo2.getCid() == -1) ? str3 : aVar.a != lac ? str3 + lac + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() + ";" : str3 + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() + ";";
                    int i3 = i2 + 1;
                    if (i3 >= 8) {
                        break;
                    }
                    i2 = i3;
                    str3 = str;
                }
            }
            if (str == null || !str.equals("&nc=")) {
                return str;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            str = "";
        }
    }

    public String b(a aVar) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(aVar.i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", new Object[]{Integer.valueOf(aVar.c), Integer.valueOf(aVar.d), Integer.valueOf(aVar.a), Integer.valueOf(aVar.b), Integer.valueOf(aVar.h)}));
        if (aVar.e < Integer.MAX_VALUE && aVar.f < Integer.MAX_VALUE) {
            stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", new Object[]{Double.valueOf(((double) aVar.f) / 14400.0d), Double.valueOf(((double) aVar.e) / 14400.0d)}));
        }
        stringBuffer.append("&cl_t=");
        stringBuffer.append(aVar.g);
        try {
            if (this.g != null && this.g.size() > 0) {
                int size = this.g.size();
                stringBuffer.append("&clt=");
                for (int i2 = 0; i2 < size; i2++) {
                    a aVar2 = (a) this.g.get(i2);
                    if (aVar2 != null) {
                        if (aVar2.c != aVar.c) {
                            stringBuffer.append(aVar2.c);
                        }
                        stringBuffer.append("|");
                        if (aVar2.d != aVar.d) {
                            stringBuffer.append(aVar2.d);
                        }
                        stringBuffer.append("|");
                        if (aVar2.a != aVar.a) {
                            stringBuffer.append(aVar2.a);
                        }
                        stringBuffer.append("|");
                        if (aVar2.b != aVar.b) {
                            stringBuffer.append(aVar2.b);
                        }
                        stringBuffer.append("|");
                        stringBuffer.append((System.currentTimeMillis() - aVar2.g) / 1000);
                        stringBuffer.append(";");
                    }
                }
            }
        } catch (Exception e2) {
        }
        if (a > 100) {
            a = 0;
        }
        stringBuffer.append("&cs=" + ((b << 8) + a));
        if (aVar.j != null) {
            stringBuffer.append(aVar.j);
        }
        return stringBuffer.toString();
    }

    public synchronized void b() {
        if (!this.i) {
            if (f.f) {
                this.d = (TelephonyManager) f.c().getSystemService("phone");
                this.g = new LinkedList();
                this.h = new a();
                i();
                if (!(this.d == null || this.h == null)) {
                    try {
                        this.d.listen(this.h, 272);
                    } catch (Exception e2) {
                    }
                    this.i = true;
                }
            }
        }
    }

    public synchronized void c() {
        if (this.i) {
            if (!(this.h == null || this.d == null)) {
                this.d.listen(this.h, 0);
            }
            this.h = null;
            this.d = null;
            this.g.clear();
            this.g = null;
            j();
            this.i = false;
        }
    }

    public boolean d() {
        return this.j;
    }

    public int e() {
        int i2 = 0;
        if (this.d == null) {
            return i2;
        }
        try {
            return this.d.getNetworkType();
        } catch (Exception e2) {
            return i2;
        }
    }

    public a f() {
        if ((this.e == null || !this.e.a() || !this.e.b()) && this.d != null) {
            try {
                k();
            } catch (Exception e2) {
            }
        }
        if (this.e != null && this.e.e()) {
            this.f = null;
            this.f = new a(this.e.a, this.e.b, this.e.c, this.e.d, this.e.h, this.e.i);
        }
        if (this.e != null && this.e.d() && this.f != null && this.e.i == 'g') {
            this.e.d = this.f.d;
            this.e.c = this.f.c;
        }
        return this.e;
    }

    public String g() {
        int i2 = -1;
        try {
            if (this.d != null) {
                i2 = this.d.getSimState();
            }
        } catch (Exception e2) {
        }
        return "&sim=" + i2;
    }

    public int h() {
        String str;
        try {
            str = this.d.getSubscriberId();
        } catch (Exception e2) {
            str = null;
        }
        if (str != null) {
            if (str.startsWith("46000") || str.startsWith("46002") || str.startsWith("46007")) {
                return 1;
            }
            if (str.startsWith("46001")) {
                return 2;
            }
            if (str.startsWith("46003")) {
                return 3;
            }
        }
        return 0;
    }
}
