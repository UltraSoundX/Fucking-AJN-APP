package com.baidu.location.indoor;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.a.n;
import com.baidu.location.g.j;
import com.baidu.location.indoor.a.C0031a;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.zhy.http.okhttp.OkHttpUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class g {
    private static g h;
    /* access modifiers changed from: private */
    public String A;
    private String B;
    /* access modifiers changed from: private */
    public int C;
    private int D;
    /* access modifiers changed from: private */
    public c<String> E;
    private int F;
    private c<String> G;
    /* access modifiers changed from: private */
    public double H;
    /* access modifiers changed from: private */
    public double I;
    /* access modifiers changed from: private */
    public double J;
    /* access modifiers changed from: private */
    public boolean K;
    private boolean L;
    /* access modifiers changed from: private */
    public List<h> M;
    /* access modifiers changed from: private */
    public int N;
    /* access modifiers changed from: private */
    public int O;
    /* access modifiers changed from: private */
    public int P;
    /* access modifiers changed from: private */
    public a Q;
    /* access modifiers changed from: private */
    public String R;
    /* access modifiers changed from: private */
    public d S;
    /* access modifiers changed from: private */
    public boolean T;
    /* access modifiers changed from: private */
    public q U;
    private com.baidu.location.indoor.q.a V;
    private com.baidu.location.indoor.mapversion.a.a W;
    /* access modifiers changed from: private */
    public int X;
    /* access modifiers changed from: private */
    public BDLocation Y;
    private boolean Z;
    public d a;
    private boolean aa;
    /* access modifiers changed from: private */
    public boolean ab;
    /* access modifiers changed from: private */
    public boolean ac;
    private c ad;
    /* access modifiers changed from: private */
    public e ae;
    /* access modifiers changed from: private */
    public f af;
    private b ag;
    public SimpleDateFormat b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public int e;
    private boolean f;
    private com.baidu.location.c g;
    /* access modifiers changed from: private */
    public long i;
    /* access modifiers changed from: private */
    public volatile boolean j;
    /* access modifiers changed from: private */
    public n k;
    private C0032g l;
    private i m;
    private long n;
    /* access modifiers changed from: private */
    public boolean o;
    private boolean p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public long f382q;
    /* access modifiers changed from: private */
    public int r;
    private String s;
    private com.baidu.location.indoor.n.a t;
    /* access modifiers changed from: private */
    public int u;
    private int v;
    /* access modifiers changed from: private */
    public String w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public m y;
    /* access modifiers changed from: private */
    public String z;

    class a {
    }

    class b {
        public String a;
        public int b;
        public String c;
        private ArrayList<Double> e;
        private ArrayList<String> f;
        private Map<String, Double> g;
        private int h;
        private Map<String, Integer> i;

        public b() {
            this.a = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = 0;
            this.b = 0;
            this.c = null;
            this.i = null;
            this.e = new ArrayList<>();
            this.f = new ArrayList<>();
            this.i = new HashMap();
        }

        /* access modifiers changed from: private */
        public int a(BDLocation bDLocation) {
            String str;
            if (!bDLocation.D().equals(this.c)) {
                this.c = bDLocation.D();
                a();
            }
            if (b(bDLocation.l("p_floor")) != 0) {
                this.b = 0;
                return 1;
            }
            try {
                if (this.f.size() == 0) {
                    for (Entry entry : this.g.entrySet()) {
                        this.f.add(entry.getKey());
                        this.e.add(entry.getValue());
                    }
                } else {
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        arrayList.add((String) it.next());
                        arrayList2.add(Double.valueOf(0.0d));
                    }
                    HashMap hashMap = new HashMap();
                    for (Entry entry2 : this.g.entrySet()) {
                        String str2 = (String) entry2.getKey();
                        Double d2 = (Double) entry2.getValue();
                        hashMap.put(str2, d2);
                        if (!this.f.contains(str2)) {
                            arrayList.add(str2);
                            arrayList2.add(d2);
                        }
                    }
                    double d3 = 0.0d;
                    for (Double doubleValue : this.g.values()) {
                        d3 = doubleValue.doubleValue() + d3;
                    }
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (hashMap.containsKey(arrayList.get(i2))) {
                            arrayList2.set(i2, hashMap.get(arrayList.get(i2)));
                        } else {
                            arrayList2.set(i2, Double.valueOf((1.0d - d3) / ((double) (this.h - hashMap.size()))));
                        }
                    }
                    ArrayList arrayList3 = new ArrayList();
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        arrayList3.add(Double.valueOf(0.0d));
                    }
                    for (int i4 = 0; i4 < this.f.size(); i4++) {
                        Double d4 = (Double) this.e.get(i4);
                        ArrayList a2 = a(arrayList, (String) this.f.get(i4));
                        for (int i5 = 0; i5 < arrayList.size(); i5++) {
                            arrayList3.set(i5, Double.valueOf(((Double) arrayList3.get(i5)).doubleValue() + (d4.doubleValue() * ((Double) a2.get(i5)).doubleValue() * ((Double) arrayList2.get(i5)).doubleValue())));
                        }
                    }
                    this.f = arrayList;
                    this.e = a(arrayList3);
                }
                double d5 = 0.0d;
                String str3 = null;
                int i6 = 0;
                while (i6 < this.f.size()) {
                    if (((Double) this.e.get(i6)).doubleValue() > d5) {
                        d5 = ((Double) this.e.get(i6)).doubleValue();
                        str = (String) this.f.get(i6);
                    } else {
                        str = str3;
                    }
                    i6++;
                    str3 = str;
                }
                this.a = str3;
            } catch (Exception e2) {
                this.b = 0;
            }
            this.b = 1;
            return 0;
        }

        private int a(String str) {
            int i2 = 1000;
            if (this.i.containsKey(str)) {
                return ((Integer) this.i.get(str)).intValue();
            }
            try {
                if (str.startsWith("F") || str.startsWith("f")) {
                    i2 = Integer.parseInt(str.substring(1)) - 1;
                    this.i.put(str, Integer.valueOf(i2));
                    return i2;
                }
                if (str.startsWith("B") || str.startsWith("b")) {
                    i2 = -Integer.parseInt(str.substring(1));
                }
                this.i.put(str, Integer.valueOf(i2));
                return i2;
            } catch (Exception e2) {
            }
        }

        private ArrayList<Double> a(ArrayList<Double> arrayList) {
            Double d2;
            ArrayList<Double> arrayList2 = new ArrayList<>();
            Double valueOf = Double.valueOf(0.0d);
            Iterator it = arrayList.iterator();
            while (true) {
                d2 = valueOf;
                if (!it.hasNext()) {
                    break;
                }
                Double d3 = (Double) it.next();
                valueOf = Double.valueOf(d3.doubleValue() + d2.doubleValue());
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Double.valueOf(((Double) it2.next()).doubleValue() / d2.doubleValue()));
            }
            return arrayList2;
        }

        private ArrayList<Double> a(ArrayList<String> arrayList, String str) {
            ArrayList arrayList2 = new ArrayList();
            double[] dArr = {180.0d, 10.0d, 1.0d};
            int a2 = a(str);
            if (a2 == 1000) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).equals(str)) {
                        arrayList2.add(Double.valueOf(dArr[0]));
                    } else {
                        arrayList2.add(Double.valueOf(dArr[2]));
                    }
                }
                return arrayList2;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                int a3 = a((String) it2.next());
                int i2 = a3 == 1000 ? 2 : a2 > a3 ? a2 - a3 : a3 - a2;
                if (i2 > 2) {
                    i2 = 2;
                }
                arrayList2.add(Double.valueOf(dArr[i2]));
            }
            return arrayList2;
        }

        private void a() {
            this.e.clear();
            this.f.clear();
            this.i.clear();
        }

        private int b(String str) {
            try {
                String[] split = str.split(";");
                if (split.length <= 1) {
                    return 1;
                }
                this.h = Integer.parseInt(split[0]);
                this.g = new HashMap();
                for (int i2 = 1; i2 < split.length; i2++) {
                    String[] split2 = split[i2].split(Config.TRACE_TODAY_VISIT_SPLIT);
                    this.g.put(split2[0], Double.valueOf(Double.parseDouble(split2[1])));
                }
                return 0;
            } catch (Exception e2) {
                return 1;
            }
        }

        /* access modifiers changed from: private */
        public String b() {
            return this.a;
        }
    }

    class c {
        public ArrayList<ArrayList<Float>> a = null;
        public double[] b = null;
        private float d = -0.18181887f;
        private float e = -0.90904963f;
        private float f = -0.55321634f;
        private float g = -0.05259979f;
        private float h = 24.0f;
        private float i = 8.61f;
        private float j = 4.25f;
        private float k = 60.39f;
        private float l = 15.6f;
        private float m = 68.07f;
        private float n = 11.61f;

        public c() {
        }

        public double a(double d2, double d3, double d4, double d5) {
            double[] a2 = a(d3, d4);
            double abs = Math.abs(d5 - a2[0]);
            return abs > a2[1] * 2.0d ? d2 + abs : d2;
        }

        public boolean a(int i2, double d2, double d3) {
            return (((double) (this.d + (((((float) i2) - this.i) / this.j) * this.e))) + (((d2 - ((double) this.k)) / ((double) this.l)) * ((double) this.f))) + (((d3 - ((double) this.m)) / ((double) this.n)) * ((double) this.g)) > 0.0d;
        }

        public boolean a(ArrayList<ArrayList<Float>> arrayList) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (this.a == null) {
                    return false;
                }
                boolean z = false;
                for (int i3 = 0; i3 < this.a.size(); i3++) {
                    ArrayList arrayList2 = (ArrayList) this.a.get(i3);
                    if (arrayList2.size() > 0 && arrayList2.equals(arrayList.get(i2))) {
                        z = true;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            return true;
        }

        public double[] a(double d2, double d3) {
            return com.baidu.location.b.a.a().a(d2, d3);
        }

        public boolean b(ArrayList<ArrayList<Float>> arrayList) {
            boolean z;
            double d2;
            double d3 = 10000.0d;
            int size = arrayList.size();
            List<ScanResult> list = com.baidu.location.e.i.a().q().a;
            if (list != null) {
                if (list.size() >= 3) {
                    d2 = (double) (-((ScanResult) list.get(0)).level);
                    d3 = ((double) (((-((ScanResult) list.get(0)).level) - ((ScanResult) list.get(1)).level) - ((ScanResult) list.get(2)).level)) / 3.0d;
                } else {
                    d2 = 10000.0d;
                }
                z = a(size, d2, d3);
            } else {
                z = false;
            }
            boolean a2 = a(arrayList);
            this.a = arrayList;
            return !z && !a2;
        }
    }

    public class d extends Handler {
        public d() {
        }

        public void handleMessage(Message message) {
            if (com.baidu.location.f.f) {
                switch (message.what) {
                    case 21:
                        g.this.a(message);
                        return;
                    case 41:
                        g.this.k();
                        return;
                    case 801:
                        g.this.a((BDLocation) message.obj);
                        return;
                    default:
                        super.dispatchMessage(message);
                        return;
                }
            }
        }
    }

    class e {
        /* access modifiers changed from: private */
        public double b = -1.0d;
        /* access modifiers changed from: private */
        public long c = 0;
        private long d = 0;
        /* access modifiers changed from: private */
        public long e = 0;
        private long f = 0;
        private long g = 0;
        private long h = 0;
        private long i = 0;
        private long j = 0;
        private long k = 0;
        private double l = 0.0d;
        private double m = 0.0d;
        private double n = 0.0d;
        private double o = 0.0d;
        private int p = 0;

        /* renamed from: q reason: collision with root package name */
        private int f383q = 0;
        private com.baidu.location.e.h r = null;
        private long s = 0;
        private int t = 0;
        private int u = 0;

        public e() {
        }

        /* access modifiers changed from: private */
        public void a() {
            this.b = -1.0d;
            this.c = 0;
            this.d = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.l = 0.0d;
            this.m = 0.0d;
            this.p = 0;
            this.f383q = 0;
            this.r = null;
            this.s = 0;
            this.t = 0;
            this.u = 0;
            this.e = 0;
        }

        /* access modifiers changed from: private */
        public void a(double d2, double d3, double d4, long j2) {
            this.j = j2;
            this.u = 0;
        }

        /* access modifiers changed from: private */
        public void a(Location location, boolean z) {
            this.k = System.currentTimeMillis();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            if (this.l != 0.0d) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.m, this.l, latitude, longitude, fArr);
                if (fArr[0] < 20.0f) {
                    this.p++;
                } else {
                    this.p = 0;
                }
                if (fArr[0] < 5.0f) {
                    this.f383q++;
                } else {
                    this.f383q = 0;
                }
            }
            this.l = longitude;
            this.m = latitude;
            if (location.hasSpeed() && location.getSpeed() > 3.0f) {
                this.h = System.currentTimeMillis();
            }
            if (location.getAccuracy() >= 10.0f || z) {
                this.t = 0;
            } else {
                this.t++;
            }
        }

        /* access modifiers changed from: private */
        public boolean a(double d2, double d3, double d4) {
            if (!g.this.ae.c()) {
                return true;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f != 0 && currentTimeMillis - this.f > OkHttpUtils.DEFAULT_MILLISECONDS) {
                return true;
            }
            if (this.f383q >= 5 && d4 < 15.0d && currentTimeMillis - this.c > 20000) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.o, this.n, d3, d2, fArr);
                if (fArr[0] > 30.0f) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public boolean a(BDLocation bDLocation, double d2, String str) {
            long currentTimeMillis = System.currentTimeMillis();
            this.i = currentTimeMillis;
            this.b = d2;
            this.n = bDLocation.i();
            this.o = bDLocation.h();
            if (str.equals(NetworkUtil.NETWORK_WIFI)) {
                this.c = currentTimeMillis;
            }
            if (str.equals("gps")) {
                this.e = currentTimeMillis;
            }
            if (e()) {
                this.f = currentTimeMillis;
            }
            g.this.d = g.this.a(bDLocation.i(), bDLocation.h());
            if (g.this.d || g.this.c == 1) {
                this.g = currentTimeMillis;
            }
            if (this.s != 0 && currentTimeMillis - this.s > 30000 && currentTimeMillis - this.j < OkHttpUtils.DEFAULT_MILLISECONDS && currentTimeMillis - this.k < OkHttpUtils.DEFAULT_MILLISECONDS) {
                return false;
            }
            if (this.t > 10 && currentTimeMillis - this.c > 30000) {
                return false;
            }
            if (currentTimeMillis - this.g <= OkHttpUtils.DEFAULT_MILLISECONDS || currentTimeMillis - this.c <= 30000) {
                return this.f == 0 || currentTimeMillis - this.f <= 60000;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public boolean b() {
            System.currentTimeMillis();
            if (g.this.o || this.p < 3 || (!com.baidu.location.e.i.a().h().contains("&wifio") && g.this.c != 1)) {
                return false;
            }
            this.u = 1;
            return true;
        }

        private boolean c() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.h >= OkHttpUtils.DEFAULT_MILLISECONDS || currentTimeMillis - this.c <= 30000) {
                return currentTimeMillis - this.k >= OkHttpUtils.DEFAULT_MILLISECONDS || this.j == 0 || currentTimeMillis - this.j <= 16000 || currentTimeMillis - this.c <= 30000;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void d() {
            com.baidu.location.e.h q2 = com.baidu.location.e.i.a().q();
            if (q2.a != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.r == null || !q2.b(this.r)) {
                    if (currentTimeMillis - this.s < OkHttpUtils.DEFAULT_MILLISECONDS) {
                        this.d = currentTimeMillis;
                    }
                    this.s = currentTimeMillis;
                    this.r = q2;
                }
            }
        }

        /* access modifiers changed from: private */
        public boolean e() {
            if (this.u != 1 && c() && this.b <= 25.0d) {
                return this.i == 0 || System.currentTimeMillis() - this.i <= 30000;
            }
            return false;
        }
    }

    private class f {
        public int a = 10;
        /* access modifiers changed from: private */
        public List<a> c = Collections.synchronizedList(new ArrayList());

        private class a {
            public double a;
            public double b;
            public double c;

            public a(double d2, double d3, double d4) {
                this.a = d2;
                this.b = d3;
                this.c = d4;
            }
        }

        public f() {
        }

        public void a(BDLocation bDLocation) {
            this.c.add(new a(bDLocation.i(), bDLocation.h(), g.this.ae.b));
        }

        public String toString() {
            if (this.c.size() == 0) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            double d = ((a) this.c.get(0)).a;
            double d2 = ((a) this.c.get(0)).b;
            stringBuffer.append(String.format("%.6f:%.6f:%.1f", new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(((a) this.c.get(0)).c)}));
            int i = 0;
            if (this.c.size() > this.a) {
                i = this.c.size() - this.a;
            }
            int i2 = i + 1;
            while (true) {
                int i3 = i2;
                if (i3 >= this.c.size()) {
                    return stringBuffer.toString();
                }
                stringBuffer.append(String.format(";%.0f:%.0f:%.1f", new Object[]{Double.valueOf((((a) this.c.get(i3)).a - d) * 1000000.0d), Double.valueOf((((a) this.c.get(i3)).b - d2) * 1000000.0d), Double.valueOf(((a) this.c.get(i3)).c)}));
                i2 = i3 + 1;
            }
        }
    }

    /* renamed from: com.baidu.location.indoor.g$g reason: collision with other inner class name */
    class C0032g extends Thread {
        /* access modifiers changed from: private */
        public volatile boolean b = true;
        private long c = 0;
        private long d = 0;

        C0032g() {
        }

        public void run() {
            while (this.b) {
                if (g.this.c != 1 || g.this.d) {
                    g.this.i = 3000;
                } else {
                    g.this.i = Config.BPLUS_DELAY_TIME;
                }
                if (g.this.k.c() == 1) {
                    this.d = System.currentTimeMillis();
                }
                boolean z = System.currentTimeMillis() - this.c > 17500;
                if (System.currentTimeMillis() - this.d < Config.BPLUS_DELAY_TIME) {
                    if (System.currentTimeMillis() - this.c > OkHttpUtils.DEFAULT_MILLISECONDS) {
                        z = true;
                    }
                    if (System.currentTimeMillis() - this.c > g.this.i) {
                        z = true;
                    }
                }
                if (z) {
                    com.baidu.location.e.i.a().i();
                    g.this.k.f();
                    this.c = System.currentTimeMillis();
                    g.this.j = false;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    this.b = false;
                    return;
                }
            }
        }
    }

    private class h {
        public int a;
        public double b;
        public double c;
        public int d = 1;
        public double e;

        public h(int i, double d2, double d3, double d4) {
            this.a = i;
            this.b = d2;
            this.c = d3;
            this.e = d4;
        }

        public String toString() {
            if (this.c == this.e) {
                return String.format("%d:%.1f:%.2f", new Object[]{Integer.valueOf(this.d), Double.valueOf(this.c), Double.valueOf(this.b)});
            }
            return String.format("%d:%.1f:%.2f:%.1f", new Object[]{Integer.valueOf(this.d), Double.valueOf(this.c), Double.valueOf(this.b), Double.valueOf(this.e)});
        }
    }

    class i extends com.baidu.location.g.e {
        private boolean b;
        private boolean c;
        private String d;
        private String e;
        private long f;

        /* renamed from: q reason: collision with root package name */
        private a f384q;
        private long r;
        private long s;

        public i() {
            this.b = false;
            this.c = false;
            this.d = null;
            this.e = null;
            this.f = 0;
            this.f384q = null;
            this.r = 0;
            this.s = 0;
            this.k = new HashMap();
        }

        public void a() {
            this.h = j.d();
            if (g.this.x == null || g.this.y == null || !g.this.x.equals(g.this.y.a())) {
                this.d = "&nd_idf=1&indoor_polygon=1" + this.d;
            }
            this.i = 1;
            String f2 = Jni.f(this.d);
            this.d = null;
            this.k.put("bloc", f2);
            this.r = System.currentTimeMillis();
        }

        public void a(boolean z) {
            String str = null;
            if (!z || this.j == null) {
                g.this.r = g.this.r + 1;
                g.this.X = 0;
                this.b = false;
                if (g.this.r > 40) {
                    g.this.d();
                } else {
                    return;
                }
            } else {
                try {
                    String str2 = this.j;
                    if (!g.this.o) {
                        this.b = false;
                        return;
                    }
                    BDLocation bDLocation = new BDLocation(str2);
                    if (!(bDLocation == null || bDLocation.o() != 161 || bDLocation.D() == null)) {
                        g.this.Y = new BDLocation(bDLocation);
                    }
                    if (bDLocation != null) {
                        str = bDLocation.c();
                    }
                    if (str != null && !g.this.Q.a(str)) {
                        g.this.Q.a(str, (C0031a) null);
                    }
                    if (g.this.S != null) {
                        g.this.S.a((com.baidu.location.indoor.d.b) new l(this));
                    }
                    n.a().b(true);
                    if (!(bDLocation == null || bDLocation.E() == null)) {
                        g.this.A = bDLocation.E();
                    }
                    if (!(bDLocation == null || bDLocation.C() == null)) {
                        g.this.f382q = System.currentTimeMillis();
                        this.s = System.currentTimeMillis();
                        int i = (int) (this.s - this.r);
                        if (i > 10000) {
                            g.this.X = 0;
                        } else if (i < 3000) {
                            g.this.X = 2;
                        } else {
                            g.this.X = 1;
                        }
                        if (bDLocation.C().contains("-a")) {
                            g.this.K = true;
                            bDLocation.h(bDLocation.C().split("-")[0]);
                        } else {
                            g.this.K = false;
                        }
                        g.this.E.add(bDLocation.C());
                    }
                    Message obtainMessage = g.this.a.obtainMessage(21);
                    obtainMessage.obj = bDLocation;
                    obtainMessage.sendToTarget();
                } catch (Exception e2) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
            this.b = false;
        }

        public void b() {
            if (this.b) {
                this.c = true;
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(1024);
            String h = com.baidu.location.e.b.a().f().h();
            String f2 = com.baidu.location.e.e.a().f();
            g.this.J = 0.5d;
            com.baidu.location.e.h q2 = com.baidu.location.e.i.a().q();
            String a2 = g.this.a(q2);
            if (a2 == null) {
                a2 = q2.a(g.this.e, true, false);
            }
            if (a2 != null && a2.length() >= 10) {
                if (this.e == null || !this.e.equals(a2)) {
                    this.e = a2;
                    this.b = true;
                    stringBuffer.append(h);
                    if (f2 != null) {
                        stringBuffer.append(f2);
                    }
                    stringBuffer.append("&coor=gcj02");
                    stringBuffer.append("&lt=1");
                    stringBuffer.append(a2);
                    if (!(g.this.k == null || g.this.O > 2 || g.this.k.h() == null)) {
                        stringBuffer.append("&idsl=" + g.this.k.h());
                    }
                    int size = g.this.M.size();
                    stringBuffer.append(g.this.a(size));
                    g.this.N = size;
                    g.this.O = g.this.O + 1;
                    stringBuffer.append("&drsi=" + g.this.O);
                    stringBuffer.append("&drc=" + g.this.u);
                    if (!(g.this.H == 0.0d || g.this.I == 0.0d)) {
                        stringBuffer.append("&lst_idl=" + String.format(Locale.CHINA, "%.5f:%.5f", new Object[]{Double.valueOf(g.this.H), Double.valueOf(g.this.I)}));
                    }
                    g.this.u = 0;
                    stringBuffer.append("&idpfv=1");
                    stringBuffer.append("&iflxy=" + g.this.af.toString());
                    g.this.af.c.clear();
                    if (g.this.k != null && g.this.k.g()) {
                        stringBuffer.append("&pdr2=1");
                    }
                    if (!(g.this.S == null || g.this.S.e() == null || !g.this.S.g())) {
                        stringBuffer.append("&bleand=").append(g.this.S.e());
                        stringBuffer.append("&bleand_et=").append(g.this.S.f());
                    }
                    g.this.P = g.this.P + 1;
                    if (g.this.R != null) {
                        stringBuffer.append(g.this.R);
                        g.this.R = null;
                    }
                    String d2 = com.baidu.location.a.a.a().d();
                    if (d2 != null) {
                        stringBuffer.append(d2);
                    }
                    stringBuffer.append(com.baidu.location.g.b.a().a(true));
                    this.d = stringBuffer.toString();
                    if (g.this.c != 1 || g.this.d || System.currentTimeMillis() - this.f >= 30000) {
                        b(j.f);
                        this.f = System.currentTimeMillis();
                    }
                }
            }
        }

        public synchronized void c() {
            if (!this.b) {
                if (this.c) {
                    this.c = false;
                    b();
                }
            }
        }
    }

    private g() {
        this.c = 0;
        this.d = false;
        this.e = 32;
        this.i = 3000;
        this.j = true;
        this.a = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.p = false;
        this.f382q = 0;
        this.r = 0;
        this.s = null;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = 0;
        this.D = 3;
        this.E = null;
        this.F = 20;
        this.G = null;
        this.H = 0.0d;
        this.I = 0.0d;
        this.J = 0.4d;
        this.K = false;
        this.L = true;
        this.M = Collections.synchronizedList(new ArrayList());
        this.N = -1;
        this.O = 0;
        this.P = 0;
        this.R = null;
        this.S = null;
        this.T = false;
        this.b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.X = 2;
        this.Y = null;
        this.Z = false;
        this.aa = false;
        this.ab = false;
        this.ac = false;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.f = false;
        this.g = new h(this);
        this.a = new d();
        try {
            com.baidu.location.indoor.mapversion.c.a.a(com.baidu.location.f.c());
        } catch (Exception e2) {
        }
        try {
            com.baidu.location.indoor.mapversion.c.c.a(com.baidu.location.f.c());
        } catch (Exception e3) {
        }
        this.U = new q();
        this.U.a(1000);
        this.V = new i(this);
        this.t = new j(this);
        this.k = new n(com.baidu.location.f.c(), this.t);
        this.m = new i();
        this.E = new c<>(this.D);
        this.G = new c<>(this.F);
        this.Q = new a(com.baidu.location.f.c());
        this.ad = new c();
        i();
        this.ae = new e();
        this.af = new f();
        this.ag = new b();
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (h == null) {
                h = new g();
            }
            gVar = h;
        }
        return gVar;
    }

    /* access modifiers changed from: private */
    public String a(int i2) {
        if (this.M.size() == 0) {
            return "&dr=0:0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&dr=");
        ((h) this.M.get(0)).d = 1;
        sb.append(((h) this.M.get(0)).toString());
        int i3 = 1;
        int i4 = ((h) this.M.get(0)).a;
        while (i3 < this.M.size() && i3 <= i2) {
            ((h) this.M.get(i3)).d = ((h) this.M.get(i3)).a - i4;
            sb.append(";");
            sb.append(((h) this.M.get(i3)).toString());
            int i5 = ((h) this.M.get(i3)).a;
            i3++;
            i4 = i5;
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public String a(com.baidu.location.e.h hVar) {
        int a2 = hVar.a();
        if (a2 <= this.e) {
            return hVar.a(this.e, true, true) + "&aprk=0";
        }
        String str = "";
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < a2; i2++) {
            String lowerCase = ((ScanResult) hVar.a.get(i2)).BSSID.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "").toLowerCase();
            if (this.Q == null || !this.Q.c(lowerCase)) {
                arrayList2.add(hVar.a.get(i2));
            } else {
                arrayList.add(hVar.a.get(i2));
            }
        }
        String str2 = arrayList.size() > 0 ? "&aprk=3" : str;
        if (str2.equals("")) {
            str2 = (this.Q == null || !this.Q.b()) ? "&aprk=1" : "&aprk=2";
        }
        arrayList.addAll(arrayList2);
        hVar.a = arrayList;
        return hVar.a(this.e, true, true) + str2;
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        if (this.o) {
            this.p = false;
            BDLocation bDLocation = (BDLocation) message.obj;
            if (bDLocation.o() == 161) {
                m();
                if (!(bDLocation.f() == null || bDLocation.d() == null || (this.y != null && this.y.a().equals(bDLocation.D())))) {
                    String[] split = bDLocation.f().split("\\|");
                    Location[] locationArr = new Location[split.length];
                    for (int i2 = 0; i2 < split.length; i2++) {
                        String[] split2 = split[i2].split(StorageInterface.KEY_SPLITER);
                        Location location = new Location("gps");
                        location.setLatitude(Double.valueOf(split2[1]).doubleValue());
                        location.setLongitude(Double.valueOf(split2[0]).doubleValue());
                        locationArr[i2] = location;
                    }
                    this.y = new m(bDLocation.d(), locationArr);
                }
                if (this.L && this.S != null) {
                    if ((((bDLocation.e() >> 2) & 1) == 1) && this.S.a()) {
                        this.L = false;
                        this.S.b();
                    }
                }
                this.r = 0;
                if (bDLocation.D() != null) {
                    this.p = true;
                    bDLocation.a(true);
                    if (bDLocation.l("tp") == null || !bDLocation.l("tp").equalsIgnoreCase("ble")) {
                        this.T = false;
                    } else {
                        bDLocation.b(8.0f);
                        bDLocation.k("ble");
                        this.T = true;
                    }
                    String l2 = bDLocation.l("pdr2");
                    if (!(l2 == null || !l2.equals("1") || this.k == null)) {
                        this.k.a(true);
                    }
                    this.x = bDLocation.D();
                    this.z = bDLocation.E();
                    this.B = bDLocation.H();
                    this.C = bDLocation.F();
                    this.ag.a(bDLocation);
                    if (bDLocation.C().equals(l())) {
                        if (this.w == null) {
                            this.w = bDLocation.C();
                        }
                        com.baidu.location.indoor.mapversion.c.a.a().a(bDLocation.i(), bDLocation.h());
                        a(bDLocation.E(), bDLocation.C());
                        if (bDLocation.C().equals(l())) {
                            if (!bDLocation.C().equalsIgnoreCase(this.w) && this.ab) {
                                this.ae.a();
                                com.baidu.location.indoor.mapversion.b.a.c();
                                this.ac = com.baidu.location.indoor.mapversion.b.a.a(bDLocation.C());
                            }
                            this.w = bDLocation.C();
                            if (this.k != null && this.k.e() >= 0.0d && bDLocation.r() <= 0.0f) {
                                bDLocation.c((float) this.k.e());
                            }
                            double[] a2 = com.baidu.location.indoor.mapversion.b.a.a(bDLocation);
                            if (!(a2 == null || a2[0] == -1.0d || a2[0] != 0.0d)) {
                                bDLocation.b(a2[1]);
                                bDLocation.a(a2[2]);
                                bDLocation.a("res", a2);
                                bDLocation.b((float) a2[5]);
                                bDLocation.c((float) a2[6]);
                                bDLocation.a((float) a2[8]);
                                if (!this.ae.a(bDLocation, a2[5], NetworkUtil.NETWORK_WIFI)) {
                                    d();
                                    return;
                                }
                            }
                            this.I = bDLocation.h();
                            this.H = bDLocation.i();
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else if (bDLocation.o() == 63) {
                this.r++;
                if (this.r > 10) {
                    d();
                } else {
                    return;
                }
            } else {
                this.r = 0;
            }
            if (this.p) {
                if (bDLocation.g() == null) {
                    bDLocation.b(this.b.format(new Date()));
                }
                BDLocation bDLocation2 = new BDLocation(bDLocation);
                bDLocation2.k(bDLocation2.H() + "2");
                if (this.U == null || !this.U.c()) {
                    a(bDLocation2, 21);
                } else {
                    this.U.a(bDLocation2);
                }
            }
            this.m.c();
        }
    }

    /* access modifiers changed from: private */
    public void a(BDLocation bDLocation) {
    }

    /* access modifiers changed from: private */
    public void a(BDLocation bDLocation, int i2) {
        if (bDLocation != null) {
            if (bDLocation.H().startsWith("vps")) {
                if (bDLocation.i() == -1.0d && bDLocation.h() == -1.0d) {
                    bDLocation.a(-1);
                } else {
                    bDLocation.a(1);
                }
                bDLocation.c(this.X);
                com.baidu.location.a.a.a().a(bDLocation);
                return;
            }
            if (this.Y != null) {
                if (bDLocation.u() == null && this.Y.u() != null) {
                    bDLocation.a(this.Y.t());
                    bDLocation.e(this.Y.u());
                }
                if (bDLocation.a() == null && this.Y.a() != null) {
                    bDLocation.a(this.Y.a());
                }
                if (bDLocation.B() == null && this.Y.B() != null) {
                    bDLocation.g(this.Y.B());
                }
            }
            if (bDLocation != null) {
                bDLocation.b(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis())));
                if (bDLocation.H().contains("2")) {
                    String H2 = bDLocation.H();
                    bDLocation.k(H2.substring(0, H2.length() - 1));
                    bDLocation.a(1);
                    bDLocation.c(this.X);
                    com.baidu.location.a.a.a().a(bDLocation);
                    BDLocation bDLocation2 = new BDLocation(bDLocation);
                    if (this.T) {
                        bDLocation2.b(8.0f);
                    } else {
                        bDLocation2.b(15.0f);
                    }
                    Message obtainMessage = this.a.obtainMessage(801);
                    obtainMessage.obj = bDLocation2;
                    obtainMessage.sendToTarget();
                }
            }
        }
    }

    private void a(String str, String str2) {
        if (this.z == null || !this.z.equals(str) || !this.ab) {
            com.baidu.location.indoor.mapversion.c.a a2 = com.baidu.location.indoor.mapversion.c.a.a();
            a2.a("gcj02");
            a2.a(str, (com.baidu.location.indoor.mapversion.c.a.c) new k(this, str, str2));
        }
    }

    private void i() {
    }

    private void j() {
        this.E.clear();
        this.G.clear();
        this.f382q = 0;
        this.r = 0;
        this.C = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.L = true;
        this.J = 0.4d;
        this.T = false;
        this.H = 0.0d;
        this.I = 0.0d;
        this.K = false;
        this.O = 0;
        this.u = 0;
        this.s = null;
        this.ae.a();
        com.baidu.location.indoor.mapversion.b.a.c();
        if (this.ab) {
            com.baidu.location.indoor.mapversion.c.a.a().b();
        }
        this.ac = false;
        this.ab = false;
        n.a().b(false);
        if (this.S != null) {
            this.S.c();
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.o) {
            this.j = true;
            this.ae.d();
            this.m.b();
            this.n = System.currentTimeMillis();
        }
    }

    private String l() {
        int i2;
        String str;
        if (this.ag.b == 1 && this.ag.a != null) {
            return this.ag.b();
        }
        HashMap hashMap = new HashMap();
        int size = this.E.size();
        String str2 = null;
        int i3 = -1;
        int i4 = 0;
        String str3 = "";
        while (i4 < size) {
            try {
                String str4 = (String) this.E.get(i4);
                str3 = str3 + str4 + "|";
                if (hashMap.containsKey(str4)) {
                    hashMap.put(str4, Integer.valueOf(((Integer) hashMap.get(str4)).intValue() + 1));
                } else {
                    hashMap.put(str4, Integer.valueOf(1));
                }
                i4++;
            } catch (Exception e2) {
                return this.w;
            }
        }
        for (String str5 : hashMap.keySet()) {
            if (((Integer) hashMap.get(str5)).intValue() > i3) {
                str = str5;
                i2 = ((Integer) hashMap.get(str5)).intValue();
            } else {
                i2 = i3;
                str = str2;
            }
            i3 = i2;
            str2 = str;
        }
        return str2;
    }

    private void m() {
        for (int i2 = this.N; i2 >= 0 && this.M.size() > 0; i2--) {
            this.M.remove(0);
        }
        this.N = -1;
    }

    public boolean a(double d2, double d3) {
        String str;
        com.baidu.location.indoor.mapversion.c.c a2 = com.baidu.location.indoor.mapversion.c.c.a();
        if (!a2.c() || !a2.b()) {
            return false;
        }
        Map d4 = a2.d();
        if (d4 == null) {
            return false;
        }
        Iterator it = d4.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            com.baidu.location.indoor.mapversion.c.c.b bVar = (com.baidu.location.indoor.mapversion.c.c.b) d4.get((String) it.next());
            if (d2 > bVar.e && d2 < bVar.c && d3 > bVar.f && d3 < bVar.d) {
                String str2 = bVar.b;
                String str3 = bVar.a;
                String str4 = bVar.g;
                str = str3;
                break;
            }
        }
        return str != null;
    }

    public boolean a(Location location, ArrayList<ArrayList<Float>> arrayList) {
        if (arrayList.size() == 0 || !com.baidu.location.e.e.a().j()) {
            return false;
        }
        if (!this.o && location.getSpeed() > 3.0f) {
            return false;
        }
        double[] a2 = Jni.a(location.getLongitude(), location.getLatitude(), "gps2gcj");
        double d2 = a2[0];
        double d3 = a2[1];
        double accuracy = (double) location.getAccuracy();
        double bearing = (double) location.getBearing();
        double altitude = location.getAltitude();
        double speed = (double) location.getSpeed();
        boolean z2 = a(d2, d3) || this.c == 1;
        if (!this.o && !z2) {
            return false;
        }
        try {
            if (!this.ad.b(arrayList)) {
                return false;
            }
            this.ae.a(location, z2);
            if (this.ae.b()) {
                c();
                return true;
            } else if (!e()) {
                return false;
            } else {
                if (this.ae.a(d2, d3, accuracy)) {
                    com.baidu.location.indoor.mapversion.b.a.c();
                }
                double[] a3 = com.baidu.location.indoor.mapversion.b.a.a(d2, d3, this.ad.a(accuracy, d2, d3, altitude), bearing, speed);
                if (a3 == null || a3[0] == -1.0d || a3[0] != 0.0d) {
                    return false;
                }
                BDLocation bDLocation = new BDLocation();
                bDLocation.c(altitude);
                bDLocation.a(a3[2]);
                bDLocation.b(a3[1]);
                if (this.T) {
                    bDLocation.b(8.0f);
                } else {
                    bDLocation.b(15.0f);
                }
                bDLocation.c((float) bearing);
                bDLocation.a((float) speed);
                bDLocation.e((int) ErrorCode.STARTDOWNLOAD_2);
                bDLocation.k("gps");
                if (System.currentTimeMillis() - this.ae.c < 20000) {
                    bDLocation.h(this.w);
                    bDLocation.j(this.z);
                    bDLocation.i(this.x);
                } else {
                    bDLocation.h((String) null);
                    bDLocation.j((String) null);
                    bDLocation.i((String) null);
                }
                bDLocation.a(true);
                this.I = bDLocation.h();
                this.H = bDLocation.i();
                bDLocation.a("res", a3);
                bDLocation.b((float) a3[5]);
                bDLocation.c((float) a3[6]);
                bDLocation.a((float) a3[8]);
                bDLocation.b(this.b.format(new Date()));
                BDLocation bDLocation2 = new BDLocation(bDLocation);
                bDLocation2.k(bDLocation2.H() + "2");
                if (this.U == null || !this.U.c()) {
                    a(bDLocation2, 21);
                } else {
                    this.U.a(bDLocation2);
                }
                if (!this.ae.a(bDLocation, a3[5], "gps")) {
                    d();
                }
                return true;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public boolean a(Bundle bundle) {
        if (bundle == null || this.W == null) {
            return false;
        }
        com.baidu.location.indoor.mapversion.a.a.d dVar = new com.baidu.location.indoor.mapversion.a.a.d();
        dVar.b(bundle.getString("bid")).c(bundle.getString("code"));
        dVar.a(bundle.getDouble("fov")).a(bundle.getFloatArray("gravity"));
        dVar.a(bundle.getString("image"));
        dVar.a(bundle.getBoolean("force_online"));
        this.W.a(dVar);
        return true;
    }

    public synchronized void b() {
        if (this.o) {
            this.E.clear();
        }
    }

    public boolean b(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.c = bundle.getInt("mode");
        return true;
    }

    public synchronized void c() {
        if (!this.o) {
            com.baidu.location.indoor.mapversion.b.a.b();
            this.f382q = System.currentTimeMillis();
            this.k.a();
            this.l = new C0032g();
            this.l.start();
            this.p = false;
            this.o = true;
            this.S = d.a(com.baidu.location.f.c());
            this.O = 0;
            this.u = 0;
            n.a().b(true);
        }
    }

    public synchronized void d() {
        if (this.o) {
            this.o = false;
            this.k.b();
            if (this.U != null && this.U.c()) {
                this.U.a();
            }
            if (this.Q != null) {
                this.Q.c();
            }
            if (this.S != null) {
                this.S.d();
            }
            if (this.l != null) {
                this.l.b = false;
                this.l.interrupt();
                this.l = null;
            }
            j();
            this.p = false;
            com.baidu.location.a.a.a().c();
        }
    }

    public boolean e() {
        return this.o;
    }

    public boolean f() {
        return this.o && this.ae.e();
    }

    public String g() {
        return this.w;
    }

    public String h() {
        return this.x;
    }
}
