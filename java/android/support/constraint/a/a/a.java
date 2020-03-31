package android.support.constraint.a.a;

/* compiled from: Animator */
public class a {
    private static boolean h = false;
    private final c a;
    private C0003a b = new C0003a();
    private C0003a c = new C0003a();
    private C0003a d = new C0003a();
    private long e = 0;
    private long f = 350;
    private boolean g = false;

    /* renamed from: android.support.constraint.a.a.a$a reason: collision with other inner class name */
    /* compiled from: Animator */
    static class C0003a {
        int a;
        int b;
        int c;
        int d;

        C0003a() {
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, int i2, int i3, int i4) {
            this.a = i;
            this.c = i2;
            this.b = i3;
            this.d = i4;
        }
    }

    public static void a(boolean z) {
        h = z;
    }

    public static boolean a() {
        return h;
    }

    public a(c cVar) {
        this.a = cVar;
    }

    public boolean b() {
        return this.g;
    }

    public void c() {
        this.e = System.currentTimeMillis();
        this.g = true;
    }

    public static double a(double d2, double d3, double d4) {
        double d5 = (d4 - d3) / 2.0d;
        double d6 = d2 * 2.0d;
        if (d6 < 1.0d) {
            return (d5 * d6 * d6) + d3;
        }
        double d7 = d6 - 1.0d;
        return ((-d5) * ((d7 * (d7 - 2.0d)) - 1.0d)) + d3;
    }

    private static int a(float f2, float f3, float f4) {
        return (int) a((double) f2, (double) f3, (double) f4);
    }

    public void d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > this.e + this.f || currentTimeMillis < this.e) {
            this.d.a = this.b.a;
            this.d.c = this.b.c;
            this.d.b = this.b.b;
            this.d.d = this.b.d;
            this.g = false;
            return;
        }
        float f2 = ((float) (currentTimeMillis - this.e)) / ((float) this.f);
        this.d.a = a(f2, (float) this.c.a, (float) this.b.a);
        this.d.b = a(f2, (float) this.c.b, (float) this.b.b);
        this.d.c = a(f2, (float) this.c.c, (float) this.b.c);
        this.d.d = a(f2, (float) this.c.d, (float) this.b.d);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.d.a(i, i2, i3, i4);
        if (!b() && !(i == this.a.f() && i2 == this.a.g() && i3 == this.a.h() && i4 == this.a.i())) {
            this.c.a(this.a.f(), this.a.g(), this.a.h(), this.a.i());
            c();
        }
        if (b()) {
            this.b.a(i, i2, i3, i4);
            d();
        }
    }

    public int e() {
        return this.d.a;
    }

    public int f() {
        return this.d.c;
    }

    public int g() {
        return this.d.b;
    }

    public int h() {
        return this.d.d;
    }
}
