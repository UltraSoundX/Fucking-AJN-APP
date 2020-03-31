package com.baidu.location.e;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.GnssStatus;
import android.location.GnssStatus.Callback;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.baidu.location.Jni;
import com.baidu.location.a.t;
import com.baidu.location.a.v;
import com.baidu.location.a.w;
import com.baidu.location.f;
import com.baidu.location.g.j;
import com.baidu.location.indoor.g;
import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class e {
    private static String C = null;
    /* access modifiers changed from: private */
    public static double G = 100.0d;
    public static String a = "";
    public static String b = "";
    private static e c = null;
    /* access modifiers changed from: private */
    public static int p = 0;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public static int f376q = 0;
    /* access modifiers changed from: private */
    public static int r = 0;
    /* access modifiers changed from: private */
    public static long s = 0;
    private double A = 0.0d;
    private double B = 0.0d;
    /* access modifiers changed from: private */
    public Handler D = null;
    /* access modifiers changed from: private */
    public int E;
    /* access modifiers changed from: private */
    public int F;
    private long H = 0;
    /* access modifiers changed from: private */
    public long I = 0;
    /* access modifiers changed from: private */
    public ArrayList<ArrayList<Float>> J = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<ArrayList<Float>> K = new ArrayList<>();
    private Context d;
    /* access modifiers changed from: private */
    public LocationManager e = null;
    private Location f;
    private c g = null;
    private C0029e h = null;
    /* access modifiers changed from: private */
    public GpsStatus i;
    private a j;
    private boolean k = false;
    private b l = null;
    private boolean m = false;
    private d n = null;
    private OnNmeaMessageListener o = null;
    /* access modifiers changed from: private */
    public long t = 0;
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public boolean v = false;
    private String w = null;
    private boolean x = false;
    /* access modifiers changed from: private */
    public long y = 0;
    private double z = -1.0d;

    @TargetApi(24)
    private class a extends Callback {
        private a() {
        }

        /* synthetic */ a(e eVar, f fVar) {
            this();
        }

        public void onFirstFix(int i) {
        }

        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            int i = 0;
            if (e.this.e != null) {
                e.this.I = System.currentTimeMillis();
                int satelliteCount = gnssStatus.getSatelliteCount();
                e.this.J.clear();
                e.this.K.clear();
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < satelliteCount; i4++) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    i2++;
                    if (gnssStatus.usedInFix(i4)) {
                        i3++;
                        if (gnssStatus.getConstellationType(i4) == 1) {
                            i++;
                            arrayList.add(Float.valueOf(gnssStatus.getCn0DbHz(i4)));
                            arrayList.add(Float.valueOf(0.0f));
                            arrayList.add(Float.valueOf(gnssStatus.getAzimuthDegrees(i4)));
                            arrayList.add(Float.valueOf(gnssStatus.getElevationDegrees(i4)));
                            arrayList.add(Float.valueOf(1.0f));
                            arrayList.add(Float.valueOf((float) gnssStatus.getSvid(i4)));
                            e.this.J.add(arrayList);
                            e.this.K.add(arrayList);
                        }
                    } else if (gnssStatus.getConstellationType(i4) == 1) {
                        arrayList2.add(Float.valueOf(gnssStatus.getCn0DbHz(i4)));
                        arrayList2.add(Float.valueOf(0.0f));
                        arrayList2.add(Float.valueOf(gnssStatus.getAzimuthDegrees(i4)));
                        arrayList2.add(Float.valueOf(gnssStatus.getElevationDegrees(i4)));
                        arrayList2.add(Float.valueOf(0.0f));
                        arrayList2.add(Float.valueOf((float) gnssStatus.getSvid(i4)));
                        e.this.K.add(arrayList2);
                    }
                }
                e.a = e.this.l();
                e.b = e.this.m();
                e.p = i3;
                e.f376q = i;
                e.r = i2;
                e.s = System.currentTimeMillis();
            }
        }

        public void onStarted() {
        }

        public void onStopped() {
            e.this.d((Location) null);
            e.this.b(false);
            e.p = 0;
            e.f376q = 0;
        }
    }

    private class b implements Listener {
        private long b;

        private b() {
            this.b = 0;
        }

        /* synthetic */ b(e eVar, f fVar) {
            this();
        }

        public void onGpsStatusChanged(int i) {
            if (e.this.e != null) {
                switch (i) {
                    case 2:
                        e.this.d((Location) null);
                        e.this.b(false);
                        e.p = 0;
                        e.f376q = 0;
                        return;
                    case 4:
                        if (e.this.v) {
                            try {
                                if (e.this.i == null) {
                                    e.this.i = e.this.e.getGpsStatus(null);
                                } else {
                                    e.this.e.getGpsStatus(e.this.i);
                                }
                                e.this.E = 0;
                                e.this.F = 0;
                                double d = 0.0d;
                                e.this.J.clear();
                                e.this.K.clear();
                                e.this.I = System.currentTimeMillis();
                                int i2 = 0;
                                int i3 = 0;
                                int i4 = 0;
                                for (GpsSatellite gpsSatellite : e.this.i.getSatellites()) {
                                    ArrayList arrayList = new ArrayList();
                                    ArrayList arrayList2 = new ArrayList();
                                    i2++;
                                    if (gpsSatellite.usedInFix()) {
                                        i4++;
                                        if (gpsSatellite.getPrn() <= 65) {
                                            i3++;
                                            d += (double) gpsSatellite.getSnr();
                                            arrayList.add(Float.valueOf(0.0f));
                                            arrayList.add(Float.valueOf(gpsSatellite.getSnr()));
                                            arrayList.add(Float.valueOf(gpsSatellite.getAzimuth()));
                                            arrayList.add(Float.valueOf(gpsSatellite.getElevation()));
                                            arrayList.add(Float.valueOf(1.0f));
                                            arrayList.add(Float.valueOf((float) gpsSatellite.getPrn()));
                                            e.this.J.add(arrayList);
                                            e.this.K.add(arrayList);
                                        }
                                    } else if (gpsSatellite.getPrn() <= 65) {
                                        arrayList2.add(Float.valueOf(0.0f));
                                        arrayList2.add(Float.valueOf(gpsSatellite.getSnr()));
                                        arrayList2.add(Float.valueOf(gpsSatellite.getAzimuth()));
                                        arrayList2.add(Float.valueOf(gpsSatellite.getElevation()));
                                        arrayList2.add(Float.valueOf(0.0f));
                                        arrayList2.add(Float.valueOf((float) gpsSatellite.getPrn()));
                                        e.this.K.add(arrayList2);
                                    }
                                    if (gpsSatellite.getSnr() >= ((float) j.H)) {
                                        e.this.F = e.this.F + 1;
                                    }
                                }
                                e.a = e.this.l();
                                e.b = e.this.m();
                                if (i3 > 0) {
                                    e.f376q = i3;
                                    e.G = d / ((double) i3);
                                }
                                if (i4 > 0) {
                                    this.b = System.currentTimeMillis();
                                    e.p = i4;
                                } else if (System.currentTimeMillis() - this.b > 100) {
                                    this.b = System.currentTimeMillis();
                                    e.p = i4;
                                }
                                e.s = System.currentTimeMillis();
                                return;
                            } catch (Exception e) {
                                return;
                            }
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    private class c implements LocationListener {
        private c() {
        }

        /* synthetic */ c(e eVar, f fVar) {
            this();
        }

        public void onLocationChanged(Location location) {
            e.this.y = System.currentTimeMillis();
            e.this.b(true);
            e.this.d(location);
            e.this.u = false;
        }

        public void onProviderDisabled(String str) {
            e.this.d((Location) null);
            e.this.b(false);
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
                case 0:
                    e.this.d((Location) null);
                    e.this.b(false);
                    return;
                case 1:
                    e.this.t = System.currentTimeMillis();
                    e.this.u = true;
                    e.this.b(false);
                    return;
                case 2:
                    e.this.u = false;
                    return;
                default:
                    return;
            }
        }
    }

    private class d implements NmeaListener {
        private d() {
        }

        /* synthetic */ d(e eVar, f fVar) {
            this();
        }

        public void onNmeaReceived(long j, String str) {
            if (e.this.b(str)) {
                e.this.a(str);
            }
        }
    }

    /* renamed from: com.baidu.location.e.e$e reason: collision with other inner class name */
    private class C0029e implements LocationListener {
        private long b;

        private C0029e() {
            this.b = 0;
        }

        /* synthetic */ C0029e(e eVar, f fVar) {
            this();
        }

        public void onLocationChanged(Location location) {
            if (!e.this.v && location != null && location.getProvider() == "gps" && System.currentTimeMillis() - this.b >= OkHttpUtils.DEFAULT_MILLISECONDS && v.a(location, false)) {
                this.b = System.currentTimeMillis();
                e.this.D.sendMessage(e.this.D.obtainMessage(4, location));
            }
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    private e() {
        if (VERSION.SDK_INT >= 24) {
            try {
                Class.forName("android.location.GnssStatus");
                this.k = true;
            } catch (ClassNotFoundException e2) {
                this.k = false;
            }
        }
        this.m = false;
    }

    public static int a(String str, String str2) {
        char charAt = str2.charAt(0);
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (Character.valueOf(charAt).equals(Character.valueOf(str.charAt(i3)))) {
                i2++;
            }
        }
        return i2;
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = new e();
            }
            eVar = c;
        }
        return eVar;
    }

    public static String a(Location location) {
        float f2 = -1.0f;
        if (location == null) {
            return null;
        }
        float speed = (float) (((double) location.getSpeed()) * 3.6d);
        if (!location.hasSpeed()) {
            speed = -1.0f;
        }
        int accuracy = (int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f);
        double d2 = location.hasAltitude() ? location.getAltitude() : 555.0d;
        if (location.hasBearing()) {
            f2 = location.getBearing();
        }
        return String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d&ll_sn=%d|%d&ll_snr=%.1f", new Object[]{Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(f2), Integer.valueOf(accuracy), Integer.valueOf(p), Double.valueOf(d2), Long.valueOf(location.getTime() / 1000), Integer.valueOf(p), Integer.valueOf(f376q), Double.valueOf(G)});
    }

    /* access modifiers changed from: private */
    public void a(String str, Location location) {
        if (location != null) {
            String str2 = str + com.baidu.location.a.a.a().d();
            boolean f2 = i.a().f();
            t.a(new a(b.a().f()));
            t.a(System.currentTimeMillis());
            t.a(new Location(location));
            t.a(str2);
            if (!f2) {
                v.a(t.c(), null, t.d(), str2);
            }
        }
    }

    public static boolean a(Location location, Location location2, boolean z2) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (z2 && ((j.v == 3 || !com.baidu.location.g.d.a().a(location2.getLongitude(), location2.getLatitude())) && speed < 5.0f)) {
            return true;
        }
        float distanceTo = location2.distanceTo(location);
        return speed > j.L ? distanceTo > j.N : speed > j.K ? distanceTo > j.M : distanceTo > 5.0f;
    }

    public static String b(Location location) {
        String a2 = a(location);
        return a2 != null ? a2 + "&g_tp=0" : a2;
    }

    /* access modifiers changed from: private */
    public void b(boolean z2) {
        this.x = z2;
        if (!z2 || !j()) {
        }
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        if (str.indexOf("*") == -1) {
            return false;
        }
        if (str.indexOf("$") == -1) {
            return false;
        }
        if (str.indexOf("$") > str.indexOf("*")) {
            return false;
        }
        if (str.length() < str.indexOf("*")) {
            return false;
        }
        byte[] bytes = str.substring(0, str.indexOf("*")).getBytes();
        byte b2 = bytes[1];
        for (int i2 = 2; i2 < bytes.length; i2++) {
            b2 ^= bytes[i2];
        }
        String format = String.format("%02x", new Object[]{Integer.valueOf(b2)});
        int indexOf = str.indexOf("*");
        if (indexOf == -1) {
            return false;
        }
        if (str.length() >= indexOf + 3) {
            return format.equalsIgnoreCase(str.substring(indexOf + 1, indexOf + 3));
        }
        return false;
    }

    public static String c(Location location) {
        String a2 = a(location);
        return a2 != null ? a2 + C : a2;
    }

    /* access modifiers changed from: private */
    public void d(Location location) {
        this.D.sendMessage(this.D.obtainMessage(1, location));
    }

    /* access modifiers changed from: private */
    public void e(Location location) {
        if (location != null) {
            int i2 = p;
            if (i2 == 0) {
                try {
                    i2 = location.getExtras().getInt("satellites");
                } catch (Exception e2) {
                }
            }
            if (i2 != 0 || j.m) {
                if (this.m && ((double) location.getSpeed()) == 0.0d && this.A != 0.0d && ((double) System.currentTimeMillis()) - this.B < 2000.0d) {
                    location.setSpeed((float) this.A);
                }
                this.f = location;
                Location location2 = new Location(this.f);
                int i3 = p;
                if (this.f == null) {
                    this.w = null;
                } else {
                    long currentTimeMillis = System.currentTimeMillis();
                    this.f.setTime(currentTimeMillis);
                    float speed = (float) (((double) this.f.getSpeed()) * 3.6d);
                    if (!this.f.hasSpeed()) {
                        speed = -1.0f;
                    }
                    if (i3 == 0) {
                        try {
                            i3 = this.f.getExtras().getInt("satellites");
                        } catch (Exception e3) {
                        }
                    }
                    this.w = String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", new Object[]{Double.valueOf(this.f.getLongitude()), Double.valueOf(this.f.getLatitude()), Float.valueOf(speed), Float.valueOf(this.f.getBearing()), Integer.valueOf(i3), Long.valueOf(currentTimeMillis)});
                }
                if (j() && this.f != null) {
                    if (g.a().e()) {
                        g.a().a(this.f, this.J);
                    }
                    if (!g.a().f()) {
                        if (!g.a().f()) {
                            com.baidu.location.a.a.a().a(g());
                        } else {
                            com.baidu.location.a.a.a().a(g());
                        }
                    }
                    if (p > 2 && v.a(this.f, true)) {
                        boolean f2 = i.a().f();
                        t.a(new a(b.a().f()));
                        t.a(System.currentTimeMillis());
                        t.a(new Location(this.f));
                        t.a(com.baidu.location.a.a.a().d());
                        if (!f2) {
                            w.a().b();
                        }
                    }
                }
                w.a().a(location2, p);
                return;
            }
            return;
        }
        this.f = null;
    }

    public static String k() {
        long currentTimeMillis = System.currentTimeMillis() - s;
        if (currentTimeMillis < 0 || currentTimeMillis >= 3000) {
            return null;
        }
        return String.format(Locale.US, "&gsvn=%d&gsfn=%d", new Object[]{Integer.valueOf(r), Integer.valueOf(p)});
    }

    /* access modifiers changed from: private */
    public String l() {
        StringBuilder sb = new StringBuilder();
        if (this.J.size() > 32 || this.J.size() == 0) {
            return sb.toString();
        }
        Iterator it = this.J.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            ArrayList arrayList = (ArrayList) it.next();
            if (arrayList.size() == 6) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append("|");
                }
                sb.append(String.format("%.1f;", new Object[]{arrayList.get(0)}));
                sb.append(String.format("%.1f;", new Object[]{arrayList.get(1)}));
                sb.append(String.format("%.0f;", new Object[]{arrayList.get(2)}));
                sb.append(String.format("%.0f;", new Object[]{arrayList.get(3)}));
                sb.append(String.format("%.0f", new Object[]{arrayList.get(4)}));
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public String m() {
        StringBuilder sb = new StringBuilder();
        if (this.K.size() > 32 || this.K.size() == 0) {
            return sb.toString();
        }
        Iterator it = this.K.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            ArrayList arrayList = (ArrayList) it.next();
            if (arrayList.size() == 6) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append("|");
                }
                sb.append(String.format("%.1f;", new Object[]{arrayList.get(0)}));
                sb.append(String.format("%.1f;", new Object[]{arrayList.get(1)}));
                sb.append(String.format("%.0f;", new Object[]{arrayList.get(2)}));
                sb.append(String.format("%.0f;", new Object[]{arrayList.get(3)}));
                sb.append(String.format("%.0f", new Object[]{arrayList.get(4)}));
            }
        }
        return sb.toString();
    }

    public void a(String str) {
        if (str.length() == 0 || !b(str)) {
            return;
        }
        if (str.startsWith("$GPPWR,") || str.startsWith("$GNGST,") || str.startsWith("$GPGST,") || str.startsWith("$GLGSV,") || str.startsWith("$GNGSV,") || str.startsWith("$BDGSV,") || str.startsWith("$GPZDA,") || str.startsWith("$GPGSA,") || str.startsWith("$GNVTG,") || str.startsWith("$GPVTG,") || str.startsWith("$GNGSA,") || str.startsWith("$GPNTR,") || str.startsWith("$GNGGA,") || str.startsWith("$GPGGA,") || str.startsWith("$GPRMC,") || str.startsWith("$GPGSV,") || str.startsWith("$BDGSA,")) {
            String[] split = str.split(StorageInterface.KEY_SPLITER);
            a(str, StorageInterface.KEY_SPLITER);
            if (split != null && split.length > 0) {
                if ((split[0].equalsIgnoreCase("$GPRMC") || split[0].equalsIgnoreCase("$GNRMC") || split[0].equalsIgnoreCase("$GLRMC") || split[0].equalsIgnoreCase("$BDRMC")) && split.length > 7 && split[7].trim().length() > 0) {
                    this.A = ((Double.valueOf(split[7]).doubleValue() * 1.852d) / 3600.0d) * 1000.0d;
                    this.B = (double) System.currentTimeMillis();
                }
            }
        }
    }

    public void a(boolean z2) {
        if (z2) {
            c();
        } else {
            d();
        }
    }

    public synchronized void b() {
        if (f.f) {
            this.d = f.c();
            try {
                this.e = (LocationManager) this.d.getSystemService("location");
                if (!this.k) {
                    this.l = new b(this, null);
                    this.e.addGpsStatusListener(this.l);
                } else {
                    this.j = new a(this, null);
                    this.e.registerGnssStatusCallback(this.j);
                }
                if (this.m) {
                    if (VERSION.SDK_INT >= 24) {
                        this.o = new f(this);
                        this.e.addNmeaListener(this.o);
                    } else {
                        this.n = new d(this, null);
                        this.e.addNmeaListener(this.n);
                    }
                }
                this.h = new C0029e(this, null);
                this.e.requestLocationUpdates("passive", 9000, 0.0f, this.h);
            } catch (Exception e2) {
            }
            this.D = new g(this);
        }
    }

    public void c() {
        Log.d(com.baidu.location.g.a.a, "start gps...");
        if (!this.v) {
            try {
                this.g = new c(this, null);
                try {
                    this.e.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
                } catch (Exception e2) {
                }
                this.e.requestLocationUpdates("gps", 1000, 0.0f, this.g);
                this.H = System.currentTimeMillis();
                this.v = true;
            } catch (Exception e3) {
            }
        }
    }

    public void d() {
        if (this.v) {
            if (this.e != null) {
                try {
                    if (this.g != null) {
                        this.e.removeUpdates(this.g);
                    }
                } catch (Exception e2) {
                }
            }
            j.d = 0;
            j.v = 0;
            this.g = null;
            this.v = false;
            b(false);
        }
    }

    public synchronized void e() {
        d();
        if (this.e != null) {
            try {
                if (this.l != null) {
                    this.e.removeGpsStatusListener(this.l);
                }
                if (this.k && this.j != null) {
                    this.e.unregisterGnssStatusCallback(this.j);
                }
                if (this.m && this.n != null) {
                    this.e.removeNmeaListener(this.n);
                }
                this.e.removeUpdates(this.h);
            } catch (Exception e2) {
            }
            this.l = null;
            this.e = null;
        }
    }

    public String f() {
        if (!j() || this.f == null) {
            return null;
        }
        return String.format("%s&idgps_tp=%s", new Object[]{a(this.f).replaceAll("ll", "idll").replaceAll("&d=", "&idd=").replaceAll("&s", "&ids="), this.f.getProvider()});
    }

    public String g() {
        double[] dArr;
        boolean z2;
        if (this.f == null) {
            return null;
        }
        String str = "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"";
        int accuracy = (int) (this.f.hasAccuracy() ? this.f.getAccuracy() : 10.0f);
        float speed = (float) (((double) this.f.getSpeed()) * 3.6d);
        if (!this.f.hasSpeed()) {
            speed = -1.0f;
        }
        double[] dArr2 = new double[2];
        if (com.baidu.location.g.d.a().a(this.f.getLongitude(), this.f.getLatitude())) {
            double[] a2 = Jni.a(this.f.getLongitude(), this.f.getLatitude(), "gps2gcj");
            if (a2[0] > 0.0d || a2[1] > 0.0d) {
                dArr = a2;
                z2 = true;
            } else {
                a2[0] = this.f.getLongitude();
                a2[1] = this.f.getLatitude();
                dArr = a2;
                z2 = true;
            }
        } else {
            dArr2[0] = this.f.getLongitude();
            dArr2[1] = this.f.getLatitude();
            dArr = dArr2;
            z2 = false;
        }
        String format = String.format(Locale.CHINA, str, new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(accuracy), Float.valueOf(this.f.getBearing()), Float.valueOf(speed), Integer.valueOf(p)});
        if (!z2) {
            format = format + ",\"in_cn\":\"0\"";
        }
        if (!this.f.hasAltitude()) {
            return format + "}}";
        }
        return format + String.format(Locale.CHINA, ",\"h\":%.2f}}", new Object[]{Double.valueOf(this.f.getAltitude())});
    }

    public Location h() {
        if (this.f != null && Math.abs(System.currentTimeMillis() - this.f.getTime()) <= 60000) {
            return this.f;
        }
        return null;
    }

    public boolean i() {
        try {
            return !(this.f == null || this.f.getLatitude() == 0.0d || this.f.getLongitude() == 0.0d || (p <= 2 && this.f.getExtras().getInt("satellites", 3) <= 2)) || Math.abs(System.currentTimeMillis() - this.I) < Config.BPLUS_DELAY_TIME;
        } catch (Exception e2) {
            return (this.f == null || this.f.getLatitude() == 0.0d || this.f.getLongitude() == 0.0d) ? false : true;
        }
    }

    public boolean j() {
        if (!i() || System.currentTimeMillis() - this.y > OkHttpUtils.DEFAULT_MILLISECONDS) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.u || currentTimeMillis - this.t >= 3000) {
            return this.x;
        }
        return true;
    }
}
