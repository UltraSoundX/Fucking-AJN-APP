package com.baidu.location.indoor;

import android.location.Location;
import android.os.Handler;
import com.baidu.location.BDLocation;

public class q {
    /* access modifiers changed from: private */
    public a a;
    /* access modifiers changed from: private */
    public long b = 450;
    /* access modifiers changed from: private */
    public BDLocation c;
    private b d = null;
    /* access modifiers changed from: private */
    public b e = null;
    private b f = new b();
    private b g = new b();
    private b h = new b();
    private b i = new b();
    /* access modifiers changed from: private */
    public BDLocation j = null;
    /* access modifiers changed from: private */
    public long k = -1;
    private boolean l = false;
    /* access modifiers changed from: private */
    public Handler m = new Handler();
    private Runnable n = new r(this);
    /* access modifiers changed from: private */
    public Runnable o = new s(this);

    public interface a {
        void a(BDLocation bDLocation);
    }

    private class b {
        public double a;
        public double b;

        public b() {
            this.a = 0.0d;
            this.b = 0.0d;
        }

        public b(double d, double d2) {
            this.a = d;
            this.b = d2;
        }

        public b(b bVar) {
            this.a = bVar.a;
            this.b = bVar.b;
        }

        public b a(double d) {
            return new b(this.a * d, this.b * d);
        }

        public b a(b bVar) {
            return new b(this.a - bVar.a, this.b - bVar.b);
        }

        public b b(b bVar) {
            return new b(this.a + bVar.a, this.b + bVar.b);
        }

        public boolean b(double d) {
            double abs = Math.abs(this.a);
            double abs2 = Math.abs(this.b);
            return abs > 0.0d && abs < d && abs2 > 0.0d && abs2 < d;
        }
    }

    /* access modifiers changed from: private */
    public b a(b bVar) {
        if (this.d == null || bVar == null) {
            return null;
        }
        b a2 = this.d.a(bVar);
        this.i = this.i.b(a2);
        b a3 = this.h.a(this.f);
        this.f = new b(this.h);
        this.h = new b(a2);
        b a4 = a2.a(0.2d);
        b a5 = this.i.a(0.01d);
        return a4.b(a5).b(a3.a(-0.02d));
    }

    public void a() {
        if (this.l) {
            this.l = false;
            this.m.removeCallbacks(this.o);
            b();
        }
    }

    public void a(long j2) {
        this.b = j2;
    }

    public synchronized void a(BDLocation bDLocation) {
        double h2 = bDLocation.h();
        double i2 = bDLocation.i();
        this.c = bDLocation;
        this.d = new b(h2, i2);
        if (this.e == null) {
            this.e = new b(h2, i2);
        }
        if (this.j == null) {
            this.j = new BDLocation(bDLocation);
        } else {
            double h3 = this.j.h();
            double i3 = this.j.i();
            double h4 = bDLocation.h();
            double i4 = bDLocation.i();
            float[] fArr = new float[2];
            Location.distanceBetween(h3, i3, h4, i4, fArr);
            if (fArr[0] > 10.0f) {
                this.j.a(h4);
                this.j.b(i4);
            } else {
                this.j.a((h3 + h4) / 2.0d);
                this.j.b((i3 + i4) / 2.0d);
            }
        }
    }

    public void b() {
        this.k = -1;
        this.e = null;
        this.d = null;
        this.f = new b();
        this.g = new b();
        this.h = new b();
        this.i = new b();
        this.j = null;
    }

    public boolean c() {
        return this.l;
    }
}
