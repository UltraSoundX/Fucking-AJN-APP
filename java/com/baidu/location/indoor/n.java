package com.baidu.location.indoor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public class n {
    private int A;
    private long B;
    private int C;
    private int D;
    private double E;
    private double F;
    private double G;
    private double H;
    private double I;
    private double J;
    private double K;
    private int L;
    private float M;
    /* access modifiers changed from: private */
    public int N;
    /* access modifiers changed from: private */
    public int O;
    /* access modifiers changed from: private */
    public double[] P;
    /* access modifiers changed from: private */
    public boolean Q;
    private double R;
    private String S;
    Timer a;
    public SensorEventListener b;
    private a c;
    /* access modifiers changed from: private */
    public SensorManager d;
    private boolean e;
    private int f;
    private Sensor g;
    /* access modifiers changed from: private */
    public Sensor h;
    private Sensor i;
    private final long j;
    /* access modifiers changed from: private */
    public boolean k;
    private boolean l;
    private boolean m;
    /* access modifiers changed from: private */
    public volatile int n;
    private int o;
    private float[] p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public float[] f385q;
    /* access modifiers changed from: private */
    public double[] r;
    private int s;
    private double[] t;
    private int u;
    private int v;
    private int w;
    private double[] x;
    private int y;
    private double z;

    public interface a {
        void a(double d, double d2, double d3, long j);
    }

    private n(Context context, int i2) {
        this.j = 30;
        this.k = true;
        this.l = false;
        this.m = false;
        this.n = 1;
        this.o = 1;
        this.p = new float[3];
        this.f385q = new float[]{0.0f, 0.0f, 0.0f};
        this.r = new double[]{0.0d, 0.0d, 0.0d};
        this.s = 31;
        this.t = new double[this.s];
        this.u = 0;
        this.x = new double[6];
        this.y = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0.0d;
        this.F = 0.0d;
        this.G = 100.0d;
        this.H = 0.5d;
        this.I = this.H;
        this.J = 0.85d;
        this.K = 0.42d;
        this.L = -1;
        this.M = 0.0f;
        this.N = 20;
        this.O = 0;
        this.P = new double[this.N];
        this.Q = false;
        this.R = -1.0d;
        this.S = null;
        this.b = new o(this);
        this.z = 1.6d;
        this.A = 440;
        try {
            this.d = (SensorManager) context.getSystemService("sensor");
            this.f = i2;
            this.g = this.d.getDefaultSensor(1);
            this.h = this.d.getDefaultSensor(3);
            if (com.baidu.location.indoor.mapversion.a.b()) {
                this.i = this.d.getDefaultSensor(4);
            }
            j();
        } catch (Exception e2) {
        }
    }

    public n(Context context, a aVar) {
        this(context, 1);
        this.c = aVar;
    }

    /* access modifiers changed from: private */
    public double a(double d2, double d3, double d4) {
        double d5 = d3 - d2;
        if (d5 < -180.0d) {
            d5 += 360.0d;
        } else if (d5 > 180.0d) {
            d5 -= 360.0d;
        }
        return (d5 * d4) + d2;
    }

    private double a(double[] dArr) {
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (double d4 : dArr) {
            d3 += d4;
        }
        double d5 = d3 / ((double) r6);
        for (int i2 = 0; i2 < r6; i2++) {
            d2 += (dArr[i2] - d5) * (dArr[i2] - d5);
        }
        return d2 / ((double) (r6 - 1));
    }

    private void a(double d2) {
        this.x[this.y % 6] = d2;
        this.y++;
        this.y %= 6;
    }

    private synchronized void a(int i2) {
        this.o |= i2;
    }

    /* access modifiers changed from: private */
    public float[] a(float f2, float f3, float f4) {
        this.p[0] = (this.p[0] * 0.8f) + (0.19999999f * f2);
        this.p[1] = (this.p[1] * 0.8f) + (0.19999999f * f3);
        this.p[2] = (this.p[2] * 0.8f) + (0.19999999f * f4);
        return new float[]{f2 - this.p[0], f3 - this.p[1], f4 - this.p[2]};
    }

    static /* synthetic */ int b(n nVar) {
        int i2 = nVar.v + 1;
        nVar.v = i2;
        return i2;
    }

    private boolean b(double d2) {
        for (int i2 = 1; i2 <= 5; i2++) {
            if (this.x[((((this.y - 1) - i2) + 6) + 6) % 6] - this.x[((this.y - 1) + 6) % 6] > d2) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ int h(n nVar) {
        int i2 = nVar.w + 1;
        nVar.w = i2;
        return i2;
    }

    /* access modifiers changed from: private */
    public boolean i() {
        for (int i2 = 0; i2 < this.N; i2++) {
            if (this.P[i2] > 1.0E-7d) {
                return true;
            }
        }
        return false;
    }

    private void j() {
        try {
            List<Sensor> sensorList = this.d.getSensorList(-1);
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(1), Integer.valueOf(0));
            hashMap.put(Integer.valueOf(10), Integer.valueOf(1));
            hashMap.put(Integer.valueOf(9), Integer.valueOf(2));
            hashMap.put(Integer.valueOf(4), Integer.valueOf(3));
            hashMap.put(Integer.valueOf(2), Integer.valueOf(4));
            hashMap.put(Integer.valueOf(11), Integer.valueOf(5));
            hashMap.put(Integer.valueOf(6), Integer.valueOf(6));
            if (VERSION.SDK_INT >= 18) {
                hashMap.put(Integer.valueOf(14), Integer.valueOf(7));
                hashMap.put(Integer.valueOf(16), Integer.valueOf(8));
            }
            int size = hashMap.size();
            char[] cArr = new char[size];
            for (int i2 = 0; i2 < size; i2++) {
                cArr[i2] = '0';
            }
            for (Sensor type : sensorList) {
                int type2 = type.getType();
                if (hashMap.get(Integer.valueOf(type2)) != null) {
                    int intValue = ((Integer) hashMap.get(Integer.valueOf(type2))).intValue();
                    if (intValue < size) {
                        cArr[intValue] = '1';
                    }
                }
            }
            this.S = new String(cArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void k() {
        this.k = false;
    }

    /* access modifiers changed from: private */
    public void l() {
        if (this.v >= 20 && this.e) {
            long currentTimeMillis = System.currentTimeMillis();
            float[] fArr = new float[3];
            System.arraycopy(this.f385q, 0, fArr, 0, 3);
            double[] dArr = new double[3];
            System.arraycopy(this.r, 0, dArr, 0, 3);
            double sqrt = Math.sqrt((double) ((fArr[2] * fArr[2]) + (fArr[0] * fArr[0]) + (fArr[1] * fArr[1])));
            this.t[this.u] = sqrt;
            a(sqrt);
            this.D++;
            if (sqrt > this.F) {
                this.F = sqrt;
            } else if (sqrt < this.G) {
                this.G = sqrt;
            }
            this.u++;
            if (this.u == this.s) {
                this.u = 0;
                double a2 = a(this.t);
                if (this.n != 0 || a2 >= 0.3d) {
                    a(1);
                    this.n = 1;
                } else {
                    a(0);
                    this.n = 0;
                }
            }
            if (currentTimeMillis - this.B > ((long) this.A) && b(this.z)) {
                this.C++;
                this.B = currentTimeMillis;
                double d2 = dArr[0];
                double d3 = dArr[0];
                boolean z2 = false;
                if (this.k && this.l && com.baidu.location.indoor.mapversion.a.b()) {
                    d2 = (double) com.baidu.location.indoor.mapversion.a.c()[0];
                    z2 = true;
                    if (Double.isNaN(d2) || d2 < 0.0d) {
                        d2 = dArr[0];
                    }
                }
                if (this.D >= 40 || this.D <= 0) {
                    this.I = this.H;
                } else {
                    this.I = Math.sqrt(Math.sqrt(this.F - this.G)) * this.K;
                    if (this.I > this.J) {
                        this.I = this.J;
                    } else if (this.I < this.H) {
                        this.I = this.H;
                    }
                }
                double d4 = ((double) this.M) + d2;
                if (d4 > 360.0d) {
                    d4 -= 360.0d;
                }
                if (d4 < 0.0d) {
                    d4 += 360.0d;
                }
                this.D = 1;
                this.F = sqrt;
                this.G = sqrt;
                this.R = d4;
                if (this.Q || z2) {
                    this.c.a(this.I, d3, d4, currentTimeMillis);
                }
            }
        }
    }

    public void a() {
        if (!this.e) {
            if (this.g != null) {
                try {
                    this.d.registerListener(this.b, this.g, this.f);
                } catch (Exception e2) {
                    this.k = false;
                }
                this.a = new Timer("UpdateData", false);
                this.a.schedule(new p(this), 500, 30);
                this.e = true;
            }
            if (this.h != null) {
                try {
                    this.d.registerListener(this.b, this.h, this.f);
                } catch (Exception e3) {
                    this.k = false;
                }
            }
        }
    }

    public void a(boolean z2) {
        this.l = z2;
        if (z2 && !this.m) {
            k();
            this.m = true;
        }
    }

    public void b() {
        if (this.e) {
            this.e = false;
            try {
                this.d.unregisterListener(this.b);
            } catch (Exception e2) {
            }
            this.a.cancel();
            this.a.purge();
            this.a = null;
            this.m = false;
            if (com.baidu.location.indoor.mapversion.a.b()) {
                com.baidu.location.indoor.mapversion.a.a();
            }
        }
    }

    public synchronized int c() {
        return this.v < 20 ? 1 : this.o;
    }

    public synchronized int d() {
        return this.v < 20 ? -1 : this.C;
    }

    public double e() {
        return this.R;
    }

    public synchronized void f() {
        this.o = 0;
    }

    public boolean g() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public String h() {
        return this.S;
    }
}
