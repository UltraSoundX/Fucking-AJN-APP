package com.baidu.location.a;

import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.e.d;
import com.baidu.location.e.e;
import com.baidu.location.e.h;
import com.baidu.location.e.i;
import com.baidu.location.f;
import com.baidu.location.g.j;
import com.baidu.location.indoor.g;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.List;

public class k extends i {
    public static boolean g = false;
    private static k h = null;
    private boolean A;
    private long B;
    private long C;
    private a D;
    /* access modifiers changed from: private */
    public boolean E;
    /* access modifiers changed from: private */
    public boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private b J;
    /* access modifiers changed from: private */
    public boolean K;
    private int L;
    private long M;
    private boolean N;
    private boolean O;
    public b e;
    public final Handler f;
    private boolean i;
    private String j;
    private BDLocation k;
    private BDLocation l;
    private h m;
    private com.baidu.location.e.a n;
    private h o;
    private com.baidu.location.e.a p;

    /* renamed from: q reason: collision with root package name */
    private boolean f370q;
    private volatile boolean r;
    /* access modifiers changed from: private */
    public boolean s;
    private long t;
    private long u;
    private com.baidu.location.a v;
    private String w;
    private List<Poi> x;
    private double y;
    private double z;

    private class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(k kVar, l lVar) {
            this();
        }

        public void run() {
            if (k.this.E) {
                k.this.E = false;
                if (!k.this.F && !e.a().j()) {
                    k.this.a(false, false);
                }
            }
        }
    }

    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(k kVar, l lVar) {
            this();
        }

        public void run() {
            if (k.this.K) {
                k.this.K = false;
            }
            if (k.this.s) {
                k.this.s = false;
                k.this.h(null);
            }
        }
    }

    private k() {
        this.i = true;
        this.e = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.f370q = true;
        this.r = false;
        this.s = false;
        this.t = 0;
        this.u = 0;
        this.v = null;
        this.w = null;
        this.x = null;
        this.A = false;
        this.B = 0;
        this.C = 0;
        this.D = null;
        this.E = false;
        this.F = false;
        this.G = true;
        this.f = new com.baidu.location.a.i.a();
        this.H = false;
        this.I = false;
        this.J = null;
        this.K = false;
        this.L = 0;
        this.M = 0;
        this.N = false;
        this.O = true;
        this.e = new b();
    }

    private boolean a(com.baidu.location.e.a aVar) {
        this.b = com.baidu.location.e.b.a().f();
        if (this.b == aVar) {
            return false;
        }
        return this.b == null || aVar == null || !aVar.a(this.b);
    }

    private boolean a(h hVar) {
        this.a = i.a().p();
        if (hVar == this.a) {
            return false;
        }
        return this.a == null || hVar == null || !hVar.c(this.a);
    }

    private boolean b(com.baidu.location.e.a aVar) {
        if (aVar == null) {
            return false;
        }
        return this.p == null || !aVar.a(this.p);
    }

    public static synchronized k c() {
        k kVar;
        synchronized (k.class) {
            if (h == null) {
                h = new k();
            }
            kVar = h;
        }
        return kVar;
    }

    private void c(Message message) {
        boolean z2 = message.getData().getBoolean("isWaitingLocTag", false);
        if (z2) {
            g = true;
        }
        if (z2) {
        }
        if (!g.a().f()) {
            int d = a.a().d(message);
            switch (d) {
                case 1:
                    d(message);
                    return;
                case 2:
                    g(message);
                    return;
                case 3:
                    if (e.a().j()) {
                        e(message);
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException(String.format("this type %d is illegal", new Object[]{Integer.valueOf(d)}));
            }
        }
    }

    private void d(Message message) {
        if (e.a().j()) {
            e(message);
            n.a().c();
            return;
        }
        g(message);
        n.a().b();
    }

    private void e(Message message) {
        BDLocation bDLocation = new BDLocation(e.a().g());
        if (j.g.equals("all") || j.h || j.j) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.z, this.y, bDLocation.h(), bDLocation.i(), fArr);
            if (fArr[0] < 100.0f) {
                if (this.v != null) {
                    bDLocation.a(this.v);
                }
                if (this.w != null) {
                    bDLocation.g(this.w);
                }
                if (this.x != null) {
                    bDLocation.a(this.x);
                }
            } else {
                this.A = true;
                g(null);
            }
        }
        this.k = bDLocation;
        this.l = null;
        a.a().a(bDLocation);
    }

    private void f(Message message) {
        if (i.a().g()) {
            this.s = true;
            if (this.J == null) {
                this.J = new b(this, null);
            }
            if (this.K && this.J != null) {
                this.f.removeCallbacks(this.J);
            }
            this.f.postDelayed(this.J, 3500);
            this.K = true;
            return;
        }
        h(message);
    }

    /* access modifiers changed from: private */
    public void g(Message message) {
        this.L = 0;
        if (this.f370q) {
            this.L = 1;
            this.C = SystemClock.uptimeMillis();
            if (i.a().k()) {
                f(message);
            } else {
                h(message);
            }
        } else {
            f(message);
            this.C = SystemClock.uptimeMillis();
        }
    }

    /* access modifiers changed from: private */
    public void h(Message message) {
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis() - this.t;
        if (this.r && currentTimeMillis <= 12000) {
            return;
        }
        if (System.currentTimeMillis() - this.t <= 0 || System.currentTimeMillis() - this.t >= 1000) {
            this.r = true;
            this.i = a(this.n);
            if (!a(this.m) && !this.i && this.k != null && !this.A) {
                if (this.l != null && System.currentTimeMillis() - this.u > 30000) {
                    this.k = this.l;
                    this.l = null;
                }
                if (n.a().d()) {
                    this.k.c(n.a().e());
                }
                if (this.k.o() == 62) {
                    long currentTimeMillis2 = System.currentTimeMillis() - this.M;
                    if (currentTimeMillis2 > 0) {
                        j2 = currentTimeMillis2;
                    }
                }
                if (this.k.o() == 61 || this.k.o() == 161 || (this.k.o() == 62 && j2 < 15000)) {
                    a.a().a(this.k);
                    m();
                    return;
                }
            }
            this.t = System.currentTimeMillis();
            String a2 = a((String) null);
            this.I = false;
            if (a2 == null) {
                this.I = true;
                this.M = System.currentTimeMillis();
                String[] l2 = l();
                long currentTimeMillis3 = System.currentTimeMillis();
                if (currentTimeMillis3 - this.B > 60000) {
                    this.B = currentTimeMillis3;
                }
                String m2 = i.a().m();
                a2 = m2 != null ? m2 + b() + l2[0] : "" + b() + l2[0];
                if (!(this.b == null || this.b.h() == null)) {
                    a2 = this.b.h() + a2;
                }
                String a3 = com.baidu.location.g.b.a().a(true);
                if (a3 != null) {
                    a2 = a2 + a3;
                }
            }
            if (this.j != null) {
                a2 = a2 + this.j;
                this.j = null;
            }
            this.e.a(a2);
            this.n = this.b;
            this.m = this.a;
            if (!e.a().j()) {
                k();
            }
            if (com.baidu.location.d.g.a().h()) {
                if (this.D == null) {
                    this.D = new a(this, null);
                }
                this.f.postDelayed(this.D, com.baidu.location.d.g.a().a(d.a(com.baidu.location.e.b.a().e())));
                this.E = true;
            }
            if (this.f370q) {
                this.f370q = false;
                if (i.j() && message != null && a.a().e(message) < 1000 && com.baidu.location.d.g.a().d()) {
                    com.baidu.location.d.g.a().i();
                }
                com.baidu.location.b.b.a().b();
            }
            if (this.L > 0) {
                if (this.L == 2) {
                    i.a().g();
                }
                this.L = 0;
                return;
            }
            return;
        }
        if (this.k != null) {
            a.a().a(this.k);
        }
        m();
    }

    private boolean k() {
        boolean z2;
        BDLocation bDLocation = null;
        double random = Math.random();
        SystemClock.uptimeMillis();
        com.baidu.location.e.a f2 = com.baidu.location.e.b.a().f();
        h o2 = i.a().o();
        long j2 = (o2 == null || o2.a() <= 0) ? 0 : o2.f();
        boolean z3 = f2 != null && f2.e() && (o2 == null || o2.a() == 0);
        if (com.baidu.location.d.g.a().d() && com.baidu.location.d.g.a().f() && j2 < 60 && (z3 || (0.0d < random && random < com.baidu.location.d.g.a().o()))) {
            BDLocation a2 = com.baidu.location.d.g.a().a(com.baidu.location.e.b.a().f(), i.a().o(), null, com.baidu.location.d.g.b.IS_MIX_MODE, com.baidu.location.d.g.a.NEED_TO_LOG);
            if (a2 == null) {
                z2 = false;
            } else {
                z2 = !j.g.equals("all") || a2.u() != null;
                if (j.h && a2.B() == null) {
                    z2 = false;
                }
                if (j.j && a2.a() == null) {
                    z2 = false;
                }
            }
            if (z2) {
                bDLocation = a2;
            }
        }
        if (bDLocation == null || bDLocation.o() != 66 || !this.r) {
            return false;
        }
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        bDLocation2.e((int) ErrorCode.STARTDOWNLOAD_2);
        if (!this.r) {
            return false;
        }
        this.F = true;
        a.a().a(bDLocation2);
        this.k = bDLocation2;
        return true;
    }

    private String[] l() {
        boolean z2;
        String[] strArr = {"", "Location failed beacuse we can not get any loc information!"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&apl=");
        int b2 = j.b(f.c());
        if (b2 == 1) {
            strArr[1] = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        }
        stringBuffer.append(b2);
        String d = j.d(f.c());
        if (d.contains("0|0|")) {
            strArr[1] = "Location failed beacuse we can not get any loc information without any location permission!";
        }
        stringBuffer.append(d);
        if (VERSION.SDK_INT >= 23) {
            stringBuffer.append("&loc=");
            int c = j.c(f.c());
            if (c == 0) {
                strArr[1] = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
                z2 = true;
            } else {
                z2 = false;
            }
            stringBuffer.append(c);
        } else {
            z2 = false;
        }
        if (VERSION.SDK_INT >= 19) {
            stringBuffer.append("&lmd=");
            int c2 = j.c(f.c());
            if (c2 >= 0) {
                stringBuffer.append(c2);
            }
        }
        String g2 = com.baidu.location.e.b.a().g();
        String h2 = i.a().h();
        stringBuffer.append(h2);
        stringBuffer.append(g2);
        stringBuffer.append(j.e(f.c()));
        if (b2 == 1) {
            b.a().a(62, 7, "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!");
        } else if (d.contains("0|0|")) {
            b.a().a(62, 4, "Location failed beacuse we can not get any loc information without any location permission!");
        } else if (z2) {
            b.a().a(62, 5, "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!");
        } else if (g2 == null || h2 == null || !g2.equals("&sim=1") || h2.equals("&wifio=1")) {
            b.a().a(62, 9, "Location failed beacuse we can not get any loc information!");
        } else {
            b.a().a(62, 6, "Location failed beacuse we can not get any loc information , you can insert a sim card or open wifi and try again!");
        }
        strArr[0] = stringBuffer.toString();
        return strArr;
    }

    private void m() {
        this.r = false;
        this.F = false;
        this.G = false;
        this.A = false;
        n();
        if (this.O) {
            this.O = false;
        }
    }

    private void n() {
        if (this.k != null && i.j()) {
            w.a().d();
        }
    }

    public com.baidu.location.a a(BDLocation bDLocation) {
        if (j.g.equals("all") || j.h || j.j) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.z, this.y, bDLocation.h(), bDLocation.i(), fArr);
            if (fArr[0] >= 100.0f) {
                this.w = null;
                this.x = null;
                this.A = true;
                this.f.post(new l(this));
            } else if (this.v != null) {
                return this.v;
            }
        }
        return null;
    }

    public void a() {
        BDLocation bDLocation;
        BDLocation bDLocation2;
        if (this.D != null && this.E) {
            this.E = false;
            this.f.removeCallbacks(this.D);
        }
        if (e.a().j()) {
            BDLocation bDLocation3 = new BDLocation(e.a().g());
            if (j.g.equals("all") || j.h || j.j) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.z, this.y, bDLocation3.h(), bDLocation3.i(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.v != null) {
                        bDLocation3.a(this.v);
                    }
                    if (this.w != null) {
                        bDLocation3.g(this.w);
                    }
                    if (this.x != null) {
                        bDLocation3.a(this.x);
                    }
                }
            }
            a.a().a(bDLocation3);
            m();
        } else if (this.F) {
            m();
        } else {
            if (!com.baidu.location.d.g.a().d() || !com.baidu.location.d.g.a().e()) {
                bDLocation = null;
            } else {
                bDLocation = com.baidu.location.d.g.a().a(com.baidu.location.e.b.a().f(), i.a().o(), null, com.baidu.location.d.g.b.IS_NOT_MIX_MODE, com.baidu.location.d.g.a.NEED_TO_LOG);
                if (bDLocation != null && bDLocation.o() == 66) {
                    a.a().a(bDLocation);
                }
            }
            if (bDLocation == null || bDLocation.o() == 67) {
                if (this.i || this.k == null) {
                    if (com.baidu.location.d.a.a().a) {
                        bDLocation2 = com.baidu.location.d.a.a().a(false);
                    } else if (bDLocation == null) {
                        BDLocation bDLocation4 = new BDLocation();
                        bDLocation4.e(67);
                        bDLocation2 = bDLocation4;
                    } else {
                        bDLocation2 = bDLocation;
                    }
                    if (bDLocation2 != null) {
                        a.a().a(bDLocation2);
                        if (bDLocation2.o() == 67 && !this.I) {
                            b.a().a(67, 3, "Offline location failed, please check the net (wifi/cell)!");
                        }
                        boolean z2 = true;
                        if (j.g.equals("all") && bDLocation2.u() == null) {
                            z2 = false;
                        }
                        if (j.h && bDLocation2.B() == null) {
                            z2 = false;
                        }
                        if (j.j && bDLocation2.a() == null) {
                            z2 = false;
                        }
                        if (!z2) {
                            bDLocation2.e(67);
                        }
                    }
                } else {
                    a.a().a(this.k);
                }
            }
            this.l = null;
            m();
        }
    }

    public void a(Message message) {
        if (this.D != null && this.E) {
            this.E = false;
            this.f.removeCallbacks(this.D);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        if (bDLocation != null && bDLocation.o() == 167 && this.I) {
            bDLocation.e(62);
        }
        b(bDLocation);
    }

    public void a(boolean z2, boolean z3) {
        BDLocation bDLocation = null;
        if (com.baidu.location.d.g.a().d() && com.baidu.location.d.g.a().g()) {
            bDLocation = com.baidu.location.d.g.a().a(com.baidu.location.e.b.a().f(), i.a().o(), null, com.baidu.location.d.g.b.IS_NOT_MIX_MODE, com.baidu.location.d.g.a.NEED_TO_LOG);
            if ((bDLocation == null || bDLocation.o() == 67) && z2 && com.baidu.location.d.a.a().a) {
                bDLocation = com.baidu.location.d.a.a().a(false);
            }
        } else if (z2 && com.baidu.location.d.a.a().a) {
            bDLocation = com.baidu.location.d.a.a().a(false);
        }
        if (bDLocation != null && bDLocation.o() == 66) {
            boolean z4 = true;
            if (j.g.equals("all") && bDLocation.u() == null) {
                z4 = false;
            }
            if (j.h && bDLocation.B() == null) {
                z4 = false;
            }
            if (j.j && bDLocation.a() == null) {
                z4 = false;
            }
            if (z4 || z3) {
                a.a().a(bDLocation);
            }
        }
    }

    public void b(Message message) {
        if (this.H) {
            c(message);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0240  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.baidu.location.BDLocation r12) {
        /*
            r11 = this;
            r6 = 2
            r0 = 1
            r2 = 0
            r9 = 0
            r8 = 161(0xa1, float:2.26E-43)
            com.baidu.location.BDLocation r3 = new com.baidu.location.BDLocation
            r3.<init>(r12)
            boolean r1 = r12.s()
            if (r1 == 0) goto L_0x0023
            com.baidu.location.a r1 = r12.t()
            r11.v = r1
            double r4 = r12.i()
            r11.y = r4
            double r4 = r12.h()
            r11.z = r4
        L_0x0023:
            java.lang.String r1 = r12.B()
            if (r1 == 0) goto L_0x003b
            java.lang.String r1 = r12.B()
            r11.w = r1
            double r4 = r12.i()
            r11.y = r4
            double r4 = r12.h()
            r11.z = r4
        L_0x003b:
            java.util.List r1 = r12.a()
            if (r1 == 0) goto L_0x0053
            java.util.List r1 = r12.a()
            r11.x = r1
            double r4 = r12.i()
            r11.y = r4
            double r4 = r12.h()
            r11.z = r4
        L_0x0053:
            com.baidu.location.e.e r1 = com.baidu.location.e.e.a()
            boolean r1 = r1.j()
            if (r1 == 0) goto L_0x0295
            r1 = r0
        L_0x005e:
            if (r1 == 0) goto L_0x00be
            com.baidu.location.e.e r0 = com.baidu.location.e.e.a()
            java.lang.String r0 = r0.g()
            com.baidu.location.BDLocation r10 = new com.baidu.location.BDLocation
            r10.<init>(r0)
            java.lang.String r0 = com.baidu.location.g.j.g
            java.lang.String r1 = "all"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x007f
            boolean r0 = com.baidu.location.g.j.h
            if (r0 != 0) goto L_0x007f
            boolean r0 = com.baidu.location.g.j.j
            if (r0 == 0) goto L_0x00b3
        L_0x007f:
            float[] r8 = new float[r6]
            double r0 = r11.z
            double r2 = r11.y
            double r4 = r10.h()
            double r6 = r10.i()
            android.location.Location.distanceBetween(r0, r2, r4, r6, r8)
            r0 = r8[r9]
            r1 = 1120403456(0x42c80000, float:100.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x00b3
            com.baidu.location.a r0 = r11.v
            if (r0 == 0) goto L_0x00a1
            com.baidu.location.a r0 = r11.v
            r10.a(r0)
        L_0x00a1:
            java.lang.String r0 = r11.w
            if (r0 == 0) goto L_0x00aa
            java.lang.String r0 = r11.w
            r10.g(r0)
        L_0x00aa:
            java.util.List<com.baidu.location.Poi> r0 = r11.x
            if (r0 == 0) goto L_0x00b3
            java.util.List<com.baidu.location.Poi> r0 = r11.x
            r10.a(r0)
        L_0x00b3:
            com.baidu.location.a.a r0 = com.baidu.location.a.a.a()
            r0.a(r10)
            r11.m()
        L_0x00bd:
            return
        L_0x00be:
            boolean r1 = r11.F
            if (r1 == 0) goto L_0x010b
            float[] r8 = new float[r6]
            com.baidu.location.BDLocation r0 = r11.k
            if (r0 == 0) goto L_0x00df
            com.baidu.location.BDLocation r0 = r11.k
            double r0 = r0.h()
            com.baidu.location.BDLocation r2 = r11.k
            double r2 = r2.i()
            double r4 = r12.h()
            double r6 = r12.i()
            android.location.Location.distanceBetween(r0, r2, r4, r6, r8)
        L_0x00df:
            r0 = r8[r9]
            r1 = 1092616192(0x41200000, float:10.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x00fa
            r11.k = r12
            boolean r0 = r11.G
            if (r0 != 0) goto L_0x00f6
            r11.G = r9
            com.baidu.location.a.a r0 = com.baidu.location.a.a.a()
            r0.a(r12)
        L_0x00f6:
            r11.m()
            goto L_0x00bd
        L_0x00fa:
            int r0 = r12.b()
            r1 = -1
            if (r0 <= r1) goto L_0x00f6
            r11.k = r12
            com.baidu.location.a.a r0 = com.baidu.location.a.a.a()
            r0.a(r12)
            goto L_0x00f6
        L_0x010b:
            int r1 = r12.o()
            r4 = 167(0xa7, float:2.34E-43)
            if (r1 != r4) goto L_0x021a
            com.baidu.location.a.b r1 = com.baidu.location.a.b.a()
            r4 = 167(0xa7, float:2.34E-43)
            r5 = 8
            java.lang.String r6 = "NetWork location failed because baidu location service can not caculate the location!"
            r1.a(r4, r5, r6)
        L_0x0120:
            r11.l = r2
            int r1 = r12.o()
            if (r1 != r8) goto L_0x0290
            java.lang.String r1 = "cl"
            java.lang.String r4 = r12.H()
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0290
            com.baidu.location.BDLocation r1 = r11.k
            if (r1 == 0) goto L_0x0290
            com.baidu.location.BDLocation r1 = r11.k
            int r1 = r1.o()
            if (r1 != r8) goto L_0x0290
            java.lang.String r1 = "wf"
            com.baidu.location.BDLocation r4 = r11.k
            java.lang.String r4 = r4.H()
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0290
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r11.u
            long r4 = r4 - r6
            r6 = 30000(0x7530, double:1.4822E-319)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0290
            r11.l = r12
        L_0x015d:
            if (r0 == 0) goto L_0x0279
            com.baidu.location.a.a r1 = com.baidu.location.a.a.a()
            com.baidu.location.BDLocation r4 = r11.k
            r1.a(r4)
        L_0x0168:
            boolean r1 = com.baidu.location.g.j.a(r12)
            if (r1 == 0) goto L_0x0288
            if (r0 != 0) goto L_0x0172
            r11.k = r12
        L_0x0172:
            java.lang.String r0 = c
            java.lang.String r1 = "ssid\":\""
            java.lang.String r4 = "\""
            int r0 = com.baidu.location.g.j.a(r0, r1, r4)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r1) goto L_0x028c
            com.baidu.location.e.h r1 = r11.m
            if (r1 == 0) goto L_0x028c
            com.baidu.location.e.h r1 = r11.m
            java.lang.String r0 = r1.d(r0)
            r11.j = r0
        L_0x018c:
            com.baidu.location.d.g r0 = com.baidu.location.d.g.a()
            boolean r0 = r0.d()
            if (r0 == 0) goto L_0x01c1
            int r0 = r12.o()
            if (r0 != r8) goto L_0x01c1
            java.lang.String r0 = "cl"
            java.lang.String r1 = r12.H()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01c1
            com.baidu.location.e.a r0 = r11.n
            boolean r0 = r11.b(r0)
            if (r0 == 0) goto L_0x01c1
            com.baidu.location.d.g r0 = com.baidu.location.d.g.a()
            com.baidu.location.e.a r1 = r11.n
            com.baidu.location.d.g$b r4 = com.baidu.location.d.g.b.IS_NOT_MIX_MODE
            com.baidu.location.d.g$a r5 = com.baidu.location.d.g.a.NO_NEED_TO_LOG
            r0.a(r1, r2, r3, r4, r5)
            com.baidu.location.e.a r0 = r11.n
            r11.p = r0
        L_0x01c1:
            com.baidu.location.d.g r0 = com.baidu.location.d.g.a()
            boolean r0 = r0.d()
            if (r0 == 0) goto L_0x01f0
            int r0 = r12.o()
            if (r0 != r8) goto L_0x01f0
            java.lang.String r0 = "wf"
            java.lang.String r1 = r12.H()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01f0
            com.baidu.location.d.g r4 = com.baidu.location.d.g.a()
            com.baidu.location.e.h r6 = r11.m
            com.baidu.location.d.g$b r8 = com.baidu.location.d.g.b.IS_NOT_MIX_MODE
            com.baidu.location.d.g$a r9 = com.baidu.location.d.g.a.NO_NEED_TO_LOG
            r5 = r2
            r7 = r3
            r4.a(r5, r6, r7, r8, r9)
            com.baidu.location.e.h r0 = r11.m
            r11.o = r0
        L_0x01f0:
            com.baidu.location.e.a r0 = r11.n
            if (r0 == 0) goto L_0x0201
            com.baidu.location.d.a r0 = com.baidu.location.d.a.a()
            java.lang.String r1 = c
            com.baidu.location.e.a r2 = r11.n
            com.baidu.location.e.h r4 = r11.m
            r0.a(r1, r2, r4, r3)
        L_0x0201:
            boolean r0 = com.baidu.location.e.i.j()
            if (r0 == 0) goto L_0x0215
            com.baidu.location.d.g r0 = com.baidu.location.d.g.a()
            r0.i()
            com.baidu.location.d.g r0 = com.baidu.location.d.g.a()
            r0.m()
        L_0x0215:
            r11.m()
            goto L_0x00bd
        L_0x021a:
            int r1 = r12.o()
            if (r1 != r8) goto L_0x0120
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 19
            if (r1 < r4) goto L_0x0293
            android.content.Context r1 = com.baidu.location.f.c()
            int r1 = com.baidu.location.g.j.c(r1)
            if (r1 == 0) goto L_0x0232
            if (r1 != r6) goto L_0x0293
        L_0x0232:
            r1 = r0
        L_0x0233:
            if (r1 == 0) goto L_0x0240
            com.baidu.location.a.b r1 = com.baidu.location.a.b.a()
            java.lang.String r4 = "NetWork location successful, open gps will be better!"
            r1.a(r8, r0, r4)
            goto L_0x0120
        L_0x0240:
            float r1 = r12.l()
            r4 = 1120403456(0x42c80000, float:100.0)
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 < 0) goto L_0x0120
            java.lang.String r1 = r12.H()
            if (r1 == 0) goto L_0x0120
            java.lang.String r1 = r12.H()
            java.lang.String r4 = "cl"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0120
            com.baidu.location.e.i r1 = com.baidu.location.e.i.a()
            java.lang.String r1 = r1.h()
            if (r1 == 0) goto L_0x0120
            java.lang.String r4 = "&wifio=1"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0120
            com.baidu.location.a.b r1 = com.baidu.location.a.b.a()
            java.lang.String r4 = "NetWork location successful, open wifi will be better!"
            r1.a(r8, r6, r4)
            goto L_0x0120
        L_0x0279:
            com.baidu.location.a.a r1 = com.baidu.location.a.a.a()
            r1.a(r12)
            long r4 = java.lang.System.currentTimeMillis()
            r11.u = r4
            goto L_0x0168
        L_0x0288:
            r11.k = r2
            goto L_0x0172
        L_0x028c:
            r11.j = r2
            goto L_0x018c
        L_0x0290:
            r0 = r9
            goto L_0x015d
        L_0x0293:
            r1 = r9
            goto L_0x0233
        L_0x0295:
            r1 = r9
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.k.b(com.baidu.location.BDLocation):void");
    }

    public void c(BDLocation bDLocation) {
        this.k = new BDLocation(bDLocation);
    }

    public void d() {
        this.f370q = true;
        this.r = false;
        this.H = true;
    }

    public void e() {
        this.r = false;
        this.s = false;
        this.F = false;
        this.G = true;
        j();
        this.H = false;
    }

    public String f() {
        return this.w;
    }

    public List<Poi> g() {
        return this.x;
    }

    public boolean h() {
        return this.i;
    }

    public void i() {
        if (this.s) {
            h(null);
            this.s = false;
            return;
        }
        com.baidu.location.b.b.a().d();
    }

    public void j() {
        this.k = null;
    }
}
