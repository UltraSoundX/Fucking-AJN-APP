package com.baidu.location;

public final class h {
    public String a = "gcj02";
    public String b = "detail";
    public boolean c = false;
    public int d = 0;
    public int e = 12000;
    public String f = "SDK6.0";
    public int g = 1;
    public boolean h = false;
    public boolean i = true;
    public boolean j = false;
    public String k = "com.baidu.location.service_v2.9";
    public boolean l = true;
    public boolean m = true;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;

    /* renamed from: q reason: collision with root package name */
    public boolean f380q = false;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    protected a u;
    public int v = 0;
    public float w = 0.5f;
    public int x = 0;
    public int y = 0;
    public int z = Integer.MAX_VALUE;

    public enum a {
        Hight_Accuracy,
        Battery_Saving,
        Device_Sensors
    }

    public h() {
    }

    public h(h hVar) {
        this.a = hVar.a;
        this.b = hVar.b;
        this.c = hVar.c;
        this.d = hVar.d;
        this.e = hVar.e;
        this.f = hVar.f;
        this.g = hVar.g;
        this.h = hVar.h;
        this.k = hVar.k;
        this.i = hVar.i;
        this.l = hVar.l;
        this.m = hVar.m;
        this.j = hVar.j;
        this.u = hVar.u;
        this.o = hVar.o;
        this.p = hVar.p;
        this.f380q = hVar.f380q;
        this.r = hVar.r;
        this.n = hVar.n;
        this.s = hVar.s;
        this.v = hVar.v;
        this.w = hVar.w;
        this.x = hVar.x;
        this.y = hVar.y;
        this.z = hVar.z;
        this.t = hVar.t;
    }

    public String a() {
        return this.a;
    }

    public void a(int i2) {
        this.d = i2;
    }

    public void a(boolean z2) {
        if (z2) {
            this.b = "all";
        } else {
            this.b = "noaddr";
        }
    }

    public boolean a(h hVar) {
        return this.a.equals(hVar.a) && this.b.equals(hVar.b) && this.c == hVar.c && this.d == hVar.d && this.e == hVar.e && this.f.equals(hVar.f) && this.h == hVar.h && this.g == hVar.g && this.i == hVar.i && this.l == hVar.l && this.t == hVar.t && this.m == hVar.m && this.o == hVar.o && this.p == hVar.p && this.f380q == hVar.f380q && this.r == hVar.r && this.n == hVar.n && this.v == hVar.v && this.w == hVar.w && this.x == hVar.x && this.y == hVar.y && this.z == hVar.z && this.s == hVar.s && this.u == hVar.u;
    }

    public String b() {
        return this.b;
    }

    public void b(boolean z2) {
        this.h = z2;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.v;
    }

    public void c(boolean z2) {
        this.o = z2;
    }

    public int d() {
        return this.x;
    }

    public void d(boolean z2) {
        this.p = z2;
    }

    public int e() {
        return this.y;
    }

    /* access modifiers changed from: 0000 */
    public float f() {
        return this.w;
    }

    public boolean g() {
        return this.i;
    }
}
