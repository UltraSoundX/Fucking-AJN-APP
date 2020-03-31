package android.support.constraint.a.a;

import android.support.constraint.a.a.b.C0004b;
import android.support.constraint.a.b;
import android.support.constraint.a.e;
import android.support.constraint.a.g;
import java.util.ArrayList;

/* compiled from: ConstraintWidget */
public class c {
    public static float z = 0.5f;
    float A = z;
    float B = z;
    a C = a.FIXED;
    a D = a.FIXED;
    int E;
    int F;
    int G;
    int H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    boolean M;
    int N = 0;
    int O = 0;
    boolean P;
    boolean Q;
    float R = 0.0f;
    float S = 0.0f;
    private a T = new a(this);
    private int U = 0;
    private int V = 0;
    private int W = 0;
    private int X = 0;
    private int Y = 0;
    private int Z = 0;
    public int a = -1;
    private int aa = 0;
    private int ab = 0;
    private int ac = 0;
    private int ad = 0;
    private int ae;
    private int af;
    private int ag;
    private int ah;
    private Object ai;
    private int aj = 0;
    private int ak = 0;
    private String al = null;
    private String am = null;
    public int b = -1;
    int c = 0;
    int d = 0;
    int e = 0;
    int f = 0;
    int g = 0;
    int h = 0;
    b i = new b(this, android.support.constraint.a.a.b.c.LEFT);
    b j = new b(this, android.support.constraint.a.a.b.c.TOP);
    b k = new b(this, android.support.constraint.a.a.b.c.RIGHT);
    b l = new b(this, android.support.constraint.a.a.b.c.BOTTOM);
    b m = new b(this, android.support.constraint.a.a.b.c.BASELINE);
    b n = new b(this, android.support.constraint.a.a.b.c.CENTER_X);
    b o = new b(this, android.support.constraint.a.a.b.c.CENTER_Y);
    b p = new b(this, android.support.constraint.a.a.b.c.CENTER);

    /* renamed from: q reason: collision with root package name */
    protected ArrayList<b> f315q = new ArrayList<>();
    c r = null;
    protected float s = 0.0f;
    protected int t = -1;
    protected int u = 0;
    protected int v = 0;
    protected int w = 0;
    protected int x = 0;
    int y = 0;

    /* compiled from: ConstraintWidget */
    public enum a {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT
    }

    public void a() {
        this.i.i();
        this.j.i();
        this.k.i();
        this.l.i();
        this.m.i();
        this.n.i();
        this.o.i();
        this.p.i();
        this.r = null;
        this.U = 0;
        this.V = 0;
        this.s = 0.0f;
        this.t = -1;
        this.u = 0;
        this.v = 0;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = 0;
        this.ah = 0;
        this.A = z;
        this.B = z;
        this.C = a.FIXED;
        this.D = a.FIXED;
        this.ai = null;
        this.aj = 0;
        this.ak = 0;
        this.al = null;
        this.am = null;
        this.M = false;
        this.N = 0;
        this.O = 0;
        this.P = false;
        this.Q = false;
        this.R = 0.0f;
        this.S = 0.0f;
        this.a = -1;
        this.b = -1;
    }

    public c() {
        D();
    }

    public void a(android.support.constraint.a.c cVar) {
        this.i.a(cVar);
        this.j.a(cVar);
        this.k.a(cVar);
        this.l.a(cVar);
        this.m.a(cVar);
        this.p.a(cVar);
        this.n.a(cVar);
        this.o.a(cVar);
    }

    private void D() {
        this.f315q.add(this.i);
        this.f315q.add(this.j);
        this.f315q.add(this.k);
        this.f315q.add(this.l);
        this.f315q.add(this.n);
        this.f315q.add(this.o);
        this.f315q.add(this.m);
    }

    public boolean b() {
        return this.r == null;
    }

    public c c() {
        return this.r;
    }

    public void a(c cVar) {
        this.r = cVar;
    }

    public void a(int i2) {
        this.ak = i2;
    }

    public int d() {
        return this.ak;
    }

    public String e() {
        return this.al;
    }

    public String toString() {
        return (this.am != null ? "type: " + this.am + " " : "") + (this.al != null ? "id: " + this.al + " " : "") + "(" + this.u + ", " + this.v + ") - (" + this.U + " x " + this.V + ")" + " wrap: (" + this.ag + " x " + this.ah + ")";
    }

    /* access modifiers changed from: 0000 */
    public int f() {
        return this.aa;
    }

    /* access modifiers changed from: 0000 */
    public int g() {
        return this.ab;
    }

    public int h() {
        return this.aa + this.ac;
    }

    public int i() {
        return this.ab + this.ad;
    }

    public int j() {
        return this.u;
    }

    public int k() {
        return this.v;
    }

    public int l() {
        if (this.ak == 8) {
            return 0;
        }
        return this.U;
    }

    public int m() {
        if (this.ak == 8) {
            return 0;
        }
        return this.V;
    }

    public int n() {
        return this.aa + this.w;
    }

    public int o() {
        return this.ab + this.x;
    }

    public int p() {
        return o() + this.ad;
    }

    public int q() {
        return n() + this.ac;
    }

    /* access modifiers changed from: protected */
    public int r() {
        return this.u + this.w;
    }

    /* access modifiers changed from: protected */
    public int s() {
        return this.v + this.x;
    }

    public int t() {
        return j() + this.U;
    }

    public int u() {
        return k() + this.V;
    }

    public boolean v() {
        return this.y > 0;
    }

    public int w() {
        return this.y;
    }

    public Object x() {
        return this.ai;
    }

    public ArrayList<b> y() {
        return this.f315q;
    }

    public void b(int i2) {
        this.u = i2;
    }

    public void c(int i2) {
        this.v = i2;
    }

    public void a(int i2, int i3) {
        this.u = i2;
        this.v = i3;
    }

    public void b(int i2, int i3) {
        this.w = i2;
        this.x = i3;
    }

    public void z() {
        int i2 = this.u;
        int i3 = this.v;
        int i4 = this.U + this.u;
        int i5 = this.v + this.V;
        if (a.a()) {
            this.T.a(i2, i3, i4, i5);
            i2 = this.T.e();
            i3 = this.T.f();
            i4 = this.T.g();
            i5 = this.T.h();
        }
        this.aa = i2;
        this.ab = i3;
        this.ac = i4 - i2;
        this.ad = i5 - i3;
    }

    public void d(int i2) {
        this.U = i2;
        if (this.U < this.ae) {
            this.U = this.ae;
        }
    }

    public void e(int i2) {
        this.V = i2;
        if (this.V < this.af) {
            this.V = this.af;
        }
    }

    public void a(int i2, int i3, int i4) {
        this.c = i2;
        this.e = i3;
        this.f = i4;
    }

    public void b(int i2, int i3, int i4) {
        this.d = i2;
        this.g = i3;
        this.h = i4;
    }

    public void a(String str) {
        float f2;
        int i2 = 0;
        if (str == null || str.length() == 0) {
            this.s = 0.0f;
            return;
        }
        int i3 = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (!substring.equalsIgnoreCase("W")) {
                if (substring.equalsIgnoreCase("H")) {
                    i2 = 1;
                } else {
                    i2 = -1;
                }
            }
            i3 = i2;
            i2 = indexOf + 1;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 < 0 || indexOf2 >= length - 1) {
            String substring2 = str.substring(i2);
            if (substring2.length() > 0) {
                try {
                    f2 = Float.parseFloat(substring2);
                } catch (NumberFormatException e2) {
                    f2 = 0.0f;
                }
            }
            f2 = 0.0f;
        } else {
            String substring3 = str.substring(i2, indexOf2);
            String substring4 = str.substring(indexOf2 + 1);
            if (substring3.length() > 0 && substring4.length() > 0) {
                try {
                    float parseFloat = Float.parseFloat(substring3);
                    float parseFloat2 = Float.parseFloat(substring4);
                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                        f2 = i3 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                    }
                } catch (NumberFormatException e3) {
                    f2 = 0.0f;
                }
            }
            f2 = 0.0f;
        }
        if (f2 > 0.0f) {
            this.s = f2;
            this.t = i3;
        }
    }

    public void a(float f2) {
        this.A = f2;
    }

    public void b(float f2) {
        this.B = f2;
    }

    public void f(int i2) {
        this.ae = i2;
    }

    public void g(int i2) {
        this.af = i2;
    }

    public void a(int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        this.u = i2;
        this.v = i3;
        if (this.ak == 8) {
            this.U = 0;
            this.V = 0;
            return;
        }
        if (this.C == a.FIXED && i6 < this.U) {
            i6 = this.U;
        }
        if (this.D == a.FIXED && i7 < this.V) {
            i7 = this.V;
        }
        this.U = i6;
        this.V = i7;
        if (this.V < this.af) {
            this.V = this.af;
        }
        if (this.U < this.ae) {
            this.U = this.ae;
        }
    }

    public void c(int i2, int i3) {
        this.u = i2;
        this.U = i3 - i2;
        if (this.U < this.ae) {
            this.U = this.ae;
        }
    }

    public void d(int i2, int i3) {
        this.v = i2;
        this.V = i3 - i2;
        if (this.V < this.af) {
            this.V = this.af;
        }
    }

    public void h(int i2) {
        this.y = i2;
    }

    public void a(Object obj) {
        this.ai = obj;
    }

    public void c(float f2) {
        this.R = f2;
    }

    public void d(float f2) {
        this.S = f2;
    }

    public void i(int i2) {
        this.N = i2;
    }

    public void j(int i2) {
        this.O = i2;
    }

    public void a(android.support.constraint.a.a.b.c cVar, c cVar2, android.support.constraint.a.a.b.c cVar3, int i2, int i3) {
        a(cVar).a(cVar2.a(cVar3), i2, i3, C0004b.STRONG, 0, true);
    }

    public void A() {
        c c2 = c();
        if (c2 == null || !(c2 instanceof d) || !((d) c()).E()) {
            int size = this.f315q.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((b) this.f315q.get(i2)).i();
            }
        }
    }

    public b a(android.support.constraint.a.a.b.c cVar) {
        switch (cVar) {
            case LEFT:
                return this.i;
            case TOP:
                return this.j;
            case RIGHT:
                return this.k;
            case BOTTOM:
                return this.l;
            case BASELINE:
                return this.m;
            case CENTER_X:
                return this.n;
            case CENTER_Y:
                return this.o;
            case CENTER:
                return this.p;
            default:
                return null;
        }
    }

    public a B() {
        return this.C;
    }

    public a C() {
        return this.D;
    }

    public void a(a aVar) {
        this.C = aVar;
        if (this.C == a.WRAP_CONTENT) {
            d(this.ag);
        }
    }

    public void b(a aVar) {
        this.D = aVar;
        if (this.D == a.WRAP_CONTENT) {
            e(this.ah);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:124:0x02cc A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0307  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x04b7  */
    /* JADX WARNING: Removed duplicated region for block: B:272:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.support.constraint.a.e r32, int r33) {
        /*
            r31 = this;
            r8 = 0
            r7 = 0
            r6 = 0
            r5 = 0
            r4 = 0
            r9 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r9) goto L_0x0016
            r0 = r31
            android.support.constraint.a.a.b r9 = r0.i
            int r9 = r9.g
            r0 = r33
            if (r9 != r0) goto L_0x0770
        L_0x0016:
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.i
            r0 = r32
            android.support.constraint.a.g r8 = r0.a(r8)
            r30 = r8
        L_0x0022:
            r8 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r8) goto L_0x0033
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.k
            int r8 = r8.g
            r0 = r33
            if (r8 != r0) goto L_0x076c
        L_0x0033:
            r0 = r31
            android.support.constraint.a.a.b r7 = r0.k
            r0 = r32
            android.support.constraint.a.g r7 = r0.a(r7)
            r29 = r7
        L_0x003f:
            r7 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r7) goto L_0x0050
            r0 = r31
            android.support.constraint.a.a.b r7 = r0.j
            int r7 = r7.g
            r0 = r33
            if (r7 != r0) goto L_0x0768
        L_0x0050:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r6)
            r28 = r6
        L_0x005c:
            r6 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r6) goto L_0x006d
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            int r6 = r6.g
            r0 = r33
            if (r6 != r0) goto L_0x0764
        L_0x006d:
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.l
            r0 = r32
            android.support.constraint.a.g r5 = r0.a(r5)
            r27 = r5
        L_0x0079:
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r5) goto L_0x008a
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.m
            int r5 = r5.g
            r0 = r33
            if (r5 != r0) goto L_0x0760
        L_0x008a:
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.m
            r0 = r32
            android.support.constraint.a.g r4 = r0.a(r4)
            r20 = r4
        L_0x0096:
            r4 = 0
            r6 = 0
            r0 = r31
            android.support.constraint.a.a.c r5 = r0.r
            if (r5 == 0) goto L_0x075a
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.i
            android.support.constraint.a.a.b r5 = r5.c
            if (r5 == 0) goto L_0x00b4
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.i
            android.support.constraint.a.a.b r5 = r5.c
            android.support.constraint.a.a.b r5 = r5.c
            r0 = r31
            android.support.constraint.a.a.b r7 = r0.i
            if (r5 == r7) goto L_0x00ca
        L_0x00b4:
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.k
            android.support.constraint.a.a.b r5 = r5.c
            if (r5 == 0) goto L_0x0757
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.k
            android.support.constraint.a.a.b r5 = r5.c
            android.support.constraint.a.a.b r5 = r5.c
            r0 = r31
            android.support.constraint.a.a.b r7 = r0.k
            if (r5 != r7) goto L_0x0757
        L_0x00ca:
            r0 = r31
            android.support.constraint.a.a.c r4 = r0.r
            android.support.constraint.a.a.d r4 = (android.support.constraint.a.a.d) r4
            r5 = 0
            r0 = r31
            r4.a(r0, r5)
            r4 = 1
            r5 = r4
        L_0x00d8:
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x00ee
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            android.support.constraint.a.a.b r4 = r4.c
            android.support.constraint.a.a.b r4 = r4.c
            r0 = r31
            android.support.constraint.a.a.b r7 = r0.j
            if (r4 == r7) goto L_0x0104
        L_0x00ee:
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x0754
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            android.support.constraint.a.a.b r4 = r4.c
            android.support.constraint.a.a.b r4 = r4.c
            r0 = r31
            android.support.constraint.a.a.b r7 = r0.l
            if (r4 != r7) goto L_0x0754
        L_0x0104:
            r0 = r31
            android.support.constraint.a.a.c r4 = r0.r
            android.support.constraint.a.a.d r4 = (android.support.constraint.a.a.d) r4
            r6 = 1
            r0 = r31
            r4.a(r0, r6)
            r4 = 1
        L_0x0111:
            r0 = r31
            android.support.constraint.a.a.c r6 = r0.r
            android.support.constraint.a.a.c$a r6 = r6.B()
            android.support.constraint.a.a.c$a r7 = android.support.constraint.a.a.c.a.WRAP_CONTENT
            if (r6 != r7) goto L_0x0189
            if (r5 != 0) goto L_0x0189
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.i
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x0135
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.i
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 == r7) goto L_0x0389
        L_0x0135:
            r0 = r31
            android.support.constraint.a.a.c r6 = r0.r
            android.support.constraint.a.a.b r6 = r6.i
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r6)
            android.support.constraint.a.b r7 = r32.b()
            android.support.constraint.a.g r8 = r32.c()
            r9 = 0
            r0 = r30
            r7.a(r0, r6, r8, r9)
            r0 = r32
            r0.a(r7)
        L_0x0154:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.k
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x016a
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.k
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 == r7) goto L_0x03aa
        L_0x016a:
            r0 = r31
            android.support.constraint.a.a.c r6 = r0.r
            android.support.constraint.a.a.b r6 = r6.k
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r6)
            android.support.constraint.a.b r7 = r32.b()
            android.support.constraint.a.g r8 = r32.c()
            r9 = 0
            r0 = r29
            r7.a(r6, r0, r8, r9)
            r0 = r32
            r0.a(r7)
        L_0x0189:
            r0 = r31
            android.support.constraint.a.a.c r6 = r0.r
            android.support.constraint.a.a.c$a r6 = r6.C()
            android.support.constraint.a.a.c$a r7 = android.support.constraint.a.a.c.a.WRAP_CONTENT
            if (r6 != r7) goto L_0x040b
            if (r4 != 0) goto L_0x040b
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x01ad
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 == r7) goto L_0x03cb
        L_0x01ad:
            r0 = r31
            android.support.constraint.a.a.c r6 = r0.r
            android.support.constraint.a.a.b r6 = r6.j
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r6)
            android.support.constraint.a.b r7 = r32.b()
            android.support.constraint.a.g r8 = r32.c()
            r9 = 0
            r0 = r28
            r7.a(r0, r6, r8, r9)
            r0 = r32
            r0.a(r7)
        L_0x01cc:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x01e2
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 == r7) goto L_0x03ec
        L_0x01e2:
            r0 = r31
            android.support.constraint.a.a.c r6 = r0.r
            android.support.constraint.a.a.b r6 = r6.l
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r6)
            android.support.constraint.a.b r7 = r32.b()
            android.support.constraint.a.g r8 = r32.c()
            r9 = 0
            r0 = r27
            r7.a(r6, r0, r8, r9)
            r0 = r32
            r0.a(r7)
            r21 = r4
            r16 = r5
        L_0x0205:
            r0 = r31
            int r4 = r0.U
            r0 = r31
            int r5 = r0.ae
            if (r4 >= r5) goto L_0x0213
            r0 = r31
            int r4 = r0.ae
        L_0x0213:
            r0 = r31
            int r5 = r0.V
            r0 = r31
            int r6 = r0.af
            if (r5 >= r6) goto L_0x0221
            r0 = r31
            int r5 = r0.af
        L_0x0221:
            r0 = r31
            android.support.constraint.a.a.c$a r6 = r0.C
            android.support.constraint.a.a.c$a r7 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r6 == r7) goto L_0x0411
            r6 = 1
        L_0x022a:
            r0 = r31
            android.support.constraint.a.a.c$a r7 = r0.D
            android.support.constraint.a.a.c$a r8 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r7 == r8) goto L_0x0414
            r7 = 1
        L_0x0233:
            if (r6 != 0) goto L_0x0751
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.i
            if (r8 == 0) goto L_0x0751
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.k
            if (r8 == 0) goto L_0x0751
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.i
            android.support.constraint.a.a.b r8 = r8.c
            if (r8 == 0) goto L_0x0251
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.k
            android.support.constraint.a.a.b r8 = r8.c
            if (r8 != 0) goto L_0x0751
        L_0x0251:
            r6 = 1
            r10 = r6
        L_0x0253:
            if (r7 != 0) goto L_0x074e
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            if (r6 == 0) goto L_0x074e
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            if (r6 == 0) goto L_0x074e
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x0271
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 != 0) goto L_0x074e
        L_0x0271:
            r0 = r31
            int r6 = r0.y
            if (r6 == 0) goto L_0x028d
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.m
            if (r6 == 0) goto L_0x074e
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x028d
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.m
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 != 0) goto L_0x074e
        L_0x028d:
            r6 = 1
        L_0x028e:
            r9 = 0
            r0 = r31
            int r8 = r0.t
            r0 = r31
            float r7 = r0.s
            r0 = r31
            float r11 = r0.s
            r12 = 0
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 <= 0) goto L_0x0740
            r0 = r31
            int r11 = r0.ak
            r12 = 8
            if (r11 == r12) goto L_0x0740
            r0 = r31
            android.support.constraint.a.a.c$a r11 = r0.C
            android.support.constraint.a.a.c$a r12 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0435
            r0 = r31
            android.support.constraint.a.a.c$a r11 = r0.D
            android.support.constraint.a.a.c$a r12 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0435
            r9 = 1
            if (r10 == 0) goto L_0x0417
            if (r6 != 0) goto L_0x0417
            r8 = 0
            r23 = r7
            r24 = r8
            r25 = r9
            r26 = r6
            r22 = r5
            r12 = r4
            r7 = r10
        L_0x02ca:
            if (r25 == 0) goto L_0x047c
            if (r24 == 0) goto L_0x02d3
            r4 = -1
            r0 = r24
            if (r0 != r4) goto L_0x047c
        L_0x02d3:
            r15 = 1
        L_0x02d4:
            r0 = r31
            android.support.constraint.a.a.c$a r4 = r0.C
            android.support.constraint.a.a.c$a r5 = android.support.constraint.a.a.c.a.WRAP_CONTENT
            if (r4 != r5) goto L_0x047f
            r0 = r31
            boolean r4 = r0 instanceof android.support.constraint.a.a.d
            if (r4 == 0) goto L_0x047f
            r6 = 1
        L_0x02e3:
            r0 = r31
            int r4 = r0.a
            r5 = 2
            if (r4 == r5) goto L_0x0381
            r4 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r4) goto L_0x0305
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.i
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x0381
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.k
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x0381
        L_0x0305:
            if (r15 == 0) goto L_0x0482
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.i
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x0482
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.k
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x0482
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.i
            r0 = r32
            android.support.constraint.a.g r5 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.k
            r0 = r32
            android.support.constraint.a.g r10 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.i
            android.support.constraint.a.a.b r4 = r4.f()
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.k
            android.support.constraint.a.a.b r4 = r4.f()
            r0 = r32
            android.support.constraint.a.g r9 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.i
            int r4 = r4.d()
            r7 = 3
            r0 = r32
            r0.a(r5, r6, r4, r7)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.k
            int r4 = r4.d()
            int r4 = r4 * -1
            r7 = 3
            r0 = r32
            r0.b(r10, r9, r4, r7)
            if (r16 != 0) goto L_0x0381
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.i
            int r7 = r4.d()
            r0 = r31
            float r8 = r0.A
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.k
            int r11 = r4.d()
            r12 = 4
            r4 = r32
            r4.a(r5, r6, r7, r8, r9, r10, r11, r12)
        L_0x0381:
            r0 = r31
            int r4 = r0.b
            r5 = 2
            if (r4 != r5) goto L_0x04b7
        L_0x0388:
            return
        L_0x0389:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.i
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x0154
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.i
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 != r7) goto L_0x0154
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.i
            android.support.constraint.a.a.b$a r7 = android.support.constraint.a.a.b.a.STRICT
            r6.a(r7)
            goto L_0x0154
        L_0x03aa:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.k
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x0189
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.k
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 != r7) goto L_0x0189
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.k
            android.support.constraint.a.a.b$a r7 = android.support.constraint.a.a.b.a.STRICT
            r6.a(r7)
            goto L_0x0189
        L_0x03cb:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x01cc
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 != r7) goto L_0x01cc
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.j
            android.support.constraint.a.a.b$a r7 = android.support.constraint.a.a.b.a.STRICT
            r6.a(r7)
            goto L_0x01cc
        L_0x03ec:
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x040b
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            r0 = r31
            android.support.constraint.a.a.c r7 = r0.r
            if (r6 != r7) goto L_0x040b
            r0 = r31
            android.support.constraint.a.a.b r6 = r0.l
            android.support.constraint.a.a.b$a r7 = android.support.constraint.a.a.b.a.STRICT
            r6.a(r7)
        L_0x040b:
            r21 = r4
            r16 = r5
            goto L_0x0205
        L_0x0411:
            r6 = 0
            goto L_0x022a
        L_0x0414:
            r7 = 0
            goto L_0x0233
        L_0x0417:
            if (r10 != 0) goto L_0x0740
            if (r6 == 0) goto L_0x0740
            r8 = 1
            r0 = r31
            int r11 = r0.t
            r12 = -1
            if (r11 != r12) goto L_0x0740
            r11 = 1065353216(0x3f800000, float:1.0)
            float r7 = r11 / r7
            r23 = r7
            r24 = r8
            r25 = r9
            r26 = r6
            r22 = r5
            r12 = r4
            r7 = r10
            goto L_0x02ca
        L_0x0435:
            r0 = r31
            android.support.constraint.a.a.c$a r11 = r0.C
            android.support.constraint.a.a.c$a r12 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0453
            r4 = 0
            r0 = r31
            int r8 = r0.V
            float r8 = (float) r8
            float r8 = r8 * r7
            int r12 = (int) r8
            r8 = 1
            r23 = r7
            r24 = r4
            r25 = r9
            r26 = r6
            r22 = r5
            r7 = r8
            goto L_0x02ca
        L_0x0453:
            r0 = r31
            android.support.constraint.a.a.c$a r11 = r0.D
            android.support.constraint.a.a.c$a r12 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0740
            r6 = 1
            r0 = r31
            int r5 = r0.t
            r8 = -1
            if (r5 != r8) goto L_0x073d
            r5 = 1065353216(0x3f800000, float:1.0)
            float r5 = r5 / r7
        L_0x0466:
            r0 = r31
            int r7 = r0.U
            float r7 = (float) r7
            float r7 = r7 * r5
            int r0 = (int) r7
            r22 = r0
            r7 = 1
            r23 = r5
            r24 = r6
            r25 = r9
            r26 = r7
            r12 = r4
            r7 = r10
            goto L_0x02ca
        L_0x047c:
            r15 = 0
            goto L_0x02d4
        L_0x047f:
            r6 = 0
            goto L_0x02e3
        L_0x0482:
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.i
            r0 = r31
            android.support.constraint.a.a.b r9 = r0.k
            r0 = r31
            int r10 = r0.u
            r0 = r31
            int r4 = r0.u
            int r11 = r4 + r12
            r0 = r31
            int r13 = r0.ae
            r0 = r31
            float r14 = r0.A
            r0 = r31
            int r0 = r0.c
            r17 = r0
            r0 = r31
            int r0 = r0.e
            r18 = r0
            r0 = r31
            int r0 = r0.f
            r19 = r0
            r4 = r31
            r5 = r32
            r4.a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            goto L_0x0381
        L_0x04b7:
            r0 = r31
            android.support.constraint.a.a.c$a r4 = r0.D
            android.support.constraint.a.a.c$a r5 = android.support.constraint.a.a.c.a.WRAP_CONTENT
            if (r4 != r5) goto L_0x05e1
            r0 = r31
            boolean r4 = r0 instanceof android.support.constraint.a.a.d
            if (r4 == 0) goto L_0x05e1
            r6 = 1
        L_0x04c6:
            if (r25 == 0) goto L_0x05e4
            r4 = 1
            r0 = r24
            if (r0 == r4) goto L_0x04d2
            r4 = -1
            r0 = r24
            if (r0 != r4) goto L_0x05e4
        L_0x04d2:
            r15 = 1
        L_0x04d3:
            r0 = r31
            int r4 = r0.y
            if (r4 <= 0) goto L_0x0627
            r0 = r31
            android.support.constraint.a.a.b r9 = r0.l
            r4 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r4) goto L_0x04f8
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x0506
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.m
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x0506
        L_0x04f8:
            int r4 = r31.w()
            r5 = 5
            r0 = r32
            r1 = r20
            r2 = r28
            r0.c(r1, r2, r4, r5)
        L_0x0506:
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.m
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x0739
            r0 = r31
            int r12 = r0.y
            r0 = r31
            android.support.constraint.a.a.b r9 = r0.m
        L_0x0516:
            r4 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r4) goto L_0x052d
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x05a9
            int r4 = r9.g
            r0 = r33
            if (r4 != r0) goto L_0x05a9
        L_0x052d:
            if (r15 == 0) goto L_0x05e7
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x05e7
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x05e7
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            r0 = r32
            android.support.constraint.a.g r5 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            r0 = r32
            android.support.constraint.a.g r10 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            android.support.constraint.a.a.b r4 = r4.f()
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            android.support.constraint.a.a.b r4 = r4.f()
            r0 = r32
            android.support.constraint.a.g r9 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            int r4 = r4.d()
            r7 = 3
            r0 = r32
            r0.a(r5, r6, r4, r7)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            int r4 = r4.d()
            int r4 = r4 * -1
            r7 = 3
            r0 = r32
            r0.b(r10, r9, r4, r7)
            if (r21 != 0) goto L_0x05a9
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            int r7 = r4.d()
            r0 = r31
            float r8 = r0.B
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            int r11 = r4.d()
            r12 = 4
            r4 = r32
            r4.a(r5, r6, r7, r8, r9, r10, r11, r12)
        L_0x05a9:
            if (r25 == 0) goto L_0x0388
            android.support.constraint.a.b r4 = r32.b()
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r5) goto L_0x05ca
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.i
            int r5 = r5.g
            r0 = r33
            if (r5 != r0) goto L_0x0388
            r0 = r31
            android.support.constraint.a.a.b r5 = r0.k
            int r5 = r5.g
            r0 = r33
            if (r5 != r0) goto L_0x0388
        L_0x05ca:
            if (r24 != 0) goto L_0x06fb
            r5 = r29
            r6 = r30
            r7 = r27
            r8 = r28
            r9 = r23
            android.support.constraint.a.b r4 = r4.a(r5, r6, r7, r8, r9)
            r0 = r32
            r0.a(r4)
            goto L_0x0388
        L_0x05e1:
            r6 = 0
            goto L_0x04c6
        L_0x05e4:
            r15 = 0
            goto L_0x04d3
        L_0x05e7:
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.j
            r0 = r31
            int r10 = r0.v
            r0 = r31
            int r4 = r0.v
            int r11 = r4 + r12
            r0 = r31
            int r13 = r0.af
            r0 = r31
            float r14 = r0.B
            r0 = r31
            int r0 = r0.d
            r17 = r0
            r0 = r31
            int r0 = r0.g
            r18 = r0
            r0 = r31
            int r0 = r0.h
            r19 = r0
            r4 = r31
            r5 = r32
            r7 = r26
            r16 = r21
            r4.a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            r4 = 5
            r0 = r32
            r1 = r27
            r2 = r28
            r3 = r22
            r0.c(r1, r2, r3, r4)
            goto L_0x05a9
        L_0x0627:
            r4 = 2147483647(0x7fffffff, float:NaN)
            r0 = r33
            if (r0 == r4) goto L_0x0642
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x05a9
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            int r4 = r4.g
            r0 = r33
            if (r4 != r0) goto L_0x05a9
        L_0x0642:
            if (r15 == 0) goto L_0x06c0
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x06c0
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x06c0
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            r0 = r32
            android.support.constraint.a.g r5 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            r0 = r32
            android.support.constraint.a.g r10 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            android.support.constraint.a.a.b r4 = r4.f()
            r0 = r32
            android.support.constraint.a.g r6 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            android.support.constraint.a.a.b r4 = r4.f()
            r0 = r32
            android.support.constraint.a.g r9 = r0.a(r4)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            int r4 = r4.d()
            r7 = 3
            r0 = r32
            r0.a(r5, r6, r4, r7)
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            int r4 = r4.d()
            int r4 = r4 * -1
            r7 = 3
            r0 = r32
            r0.b(r10, r9, r4, r7)
            if (r21 != 0) goto L_0x05a9
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.j
            int r7 = r4.d()
            r0 = r31
            float r8 = r0.B
            r0 = r31
            android.support.constraint.a.a.b r4 = r0.l
            int r11 = r4.d()
            r12 = 4
            r4 = r32
            r4.a(r5, r6, r7, r8, r9, r10, r11, r12)
            goto L_0x05a9
        L_0x06c0:
            r0 = r31
            android.support.constraint.a.a.b r8 = r0.j
            r0 = r31
            android.support.constraint.a.a.b r9 = r0.l
            r0 = r31
            int r10 = r0.v
            r0 = r31
            int r4 = r0.v
            int r11 = r4 + r22
            r0 = r31
            int r13 = r0.af
            r0 = r31
            float r14 = r0.B
            r0 = r31
            int r0 = r0.d
            r17 = r0
            r0 = r31
            int r0 = r0.g
            r18 = r0
            r0 = r31
            int r0 = r0.h
            r19 = r0
            r4 = r31
            r5 = r32
            r7 = r26
            r12 = r22
            r16 = r21
            r4.a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            goto L_0x05a9
        L_0x06fb:
            r5 = 1
            r0 = r24
            if (r0 != r5) goto L_0x0715
            r5 = r27
            r6 = r28
            r7 = r29
            r8 = r30
            r9 = r23
            android.support.constraint.a.b r4 = r4.a(r5, r6, r7, r8, r9)
            r0 = r32
            r0.a(r4)
            goto L_0x0388
        L_0x0715:
            r10 = 4
            r5 = r29
            r6 = r30
            r7 = r27
            r8 = r28
            r9 = r23
            r4.a(r5, r6, r7, r8, r9)
            android.support.constraint.a.g r5 = r32.d()
            android.support.constraint.a.g r6 = r32.d()
            r5.c = r10
            r6.c = r10
            r4.a(r5, r6)
            r0 = r32
            r0.a(r4)
            goto L_0x0388
        L_0x0739:
            r12 = r22
            goto L_0x0516
        L_0x073d:
            r5 = r7
            goto L_0x0466
        L_0x0740:
            r23 = r7
            r24 = r8
            r25 = r9
            r26 = r6
            r22 = r5
            r12 = r4
            r7 = r10
            goto L_0x02ca
        L_0x074e:
            r6 = r7
            goto L_0x028e
        L_0x0751:
            r10 = r6
            goto L_0x0253
        L_0x0754:
            r4 = r6
            goto L_0x0111
        L_0x0757:
            r5 = r4
            goto L_0x00d8
        L_0x075a:
            r21 = r6
            r16 = r4
            goto L_0x0205
        L_0x0760:
            r20 = r4
            goto L_0x0096
        L_0x0764:
            r27 = r5
            goto L_0x0079
        L_0x0768:
            r28 = r6
            goto L_0x005c
        L_0x076c:
            r29 = r7
            goto L_0x003f
        L_0x0770:
            r30 = r8
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.a.a.c.a(android.support.constraint.a.e, int):void");
    }

    private void a(e eVar, boolean z2, boolean z3, b bVar, b bVar2, int i2, int i3, int i4, int i5, float f2, boolean z4, boolean z5, int i6, int i7, int i8) {
        int i9;
        g a2 = eVar.a((Object) bVar);
        g a3 = eVar.a((Object) bVar2);
        g a4 = eVar.a((Object) bVar.f());
        g a5 = eVar.a((Object) bVar2.f());
        int d2 = bVar.d();
        int d3 = bVar2.d();
        if (this.ak == 8) {
            i9 = 0;
            z3 = true;
        } else {
            i9 = i4;
        }
        if (a4 == null && a5 == null) {
            eVar.a(eVar.b().b(a2, i2));
            if (z4) {
                return;
            }
            if (z2) {
                eVar.a(e.a(eVar, a3, a2, i5, true));
            } else if (z3) {
                eVar.a(e.a(eVar, a3, a2, i9, false));
            } else {
                eVar.a(eVar.b().b(a3, i3));
            }
        } else if (a4 != null && a5 == null) {
            eVar.a(eVar.b().a(a2, a4, d2));
            if (z2) {
                eVar.a(e.a(eVar, a3, a2, i5, true));
            } else if (z4) {
            } else {
                if (z3) {
                    eVar.a(eVar.b().a(a3, a2, i9));
                } else {
                    eVar.a(eVar.b().b(a3, i3));
                }
            }
        } else if (a4 == null && a5 != null) {
            eVar.a(eVar.b().a(a3, a5, d3 * -1));
            if (z2) {
                eVar.a(e.a(eVar, a3, a2, i5, true));
            } else if (z4) {
            } else {
                if (z3) {
                    eVar.a(eVar.b().a(a3, a2, i9));
                } else {
                    eVar.a(eVar.b().b(a2, i2));
                }
            }
        } else if (z3) {
            if (z2) {
                eVar.a(e.a(eVar, a3, a2, i5, true));
            } else {
                eVar.a(eVar.b().a(a3, a2, i9));
            }
            if (bVar.e() != bVar2.e()) {
                if (bVar.e() == C0004b.STRONG) {
                    eVar.a(eVar.b().a(a2, a4, d2));
                    g c2 = eVar.c();
                    b b2 = eVar.b();
                    b2.b(a3, a5, c2, d3 * -1);
                    eVar.a(b2);
                    return;
                }
                g c3 = eVar.c();
                b b3 = eVar.b();
                b3.a(a2, a4, c3, d2);
                eVar.a(b3);
                eVar.a(eVar.b().a(a3, a5, d3 * -1));
            } else if (a4 == a5) {
                eVar.a(e.a(eVar, a2, a4, 0, 0.5f, a5, a3, 0, true));
            } else if (!z5) {
                eVar.a(e.b(eVar, a2, a4, d2, bVar.g() != android.support.constraint.a.a.b.a.STRICT));
                eVar.a(e.c(eVar, a3, a5, d3 * -1, bVar2.g() != android.support.constraint.a.a.b.a.STRICT));
                eVar.a(e.a(eVar, a2, a4, d2, f2, a5, a3, d3, false));
            }
        } else if (z4) {
            eVar.a(a2, a4, d2, 3);
            eVar.b(a3, a5, d3 * -1, 3);
            eVar.a(e.a(eVar, a2, a4, d2, f2, a5, a3, d3, true));
        } else if (z5) {
        } else {
            if (i6 == 1) {
                if (i7 > i9) {
                    i9 = i7;
                }
                if (i8 > 0) {
                    if (i8 >= i9) {
                        eVar.b(a3, a2, i8, 4);
                    }
                    eVar.c(a3, a2, i8, 3);
                    eVar.a(a2, a4, d2, 2);
                    eVar.b(a3, a5, -d3, 2);
                    eVar.a(a2, a4, d2, f2, a5, a3, d3, 4);
                }
                i8 = i9;
                eVar.c(a3, a2, i8, 3);
                eVar.a(a2, a4, d2, 2);
                eVar.b(a3, a5, -d3, 2);
                eVar.a(a2, a4, d2, f2, a5, a3, d3, 4);
            } else if (i7 == 0 && i8 == 0) {
                eVar.a(eVar.b().a(a2, a4, d2));
                eVar.a(eVar.b().a(a3, a5, d3 * -1));
            } else {
                if (i7 <= i9) {
                    i7 = i9;
                }
                if (i8 > 0 && i8 >= i7) {
                    eVar.b(a3, a2, i8, 4);
                }
                eVar.a(a2, a4, d2, 2);
                eVar.b(a3, a5, -d3, 2);
                eVar.a(a2, a4, d2, f2, a5, a3, d3, 4);
            }
        }
    }

    public void b(e eVar, int i2) {
        if (i2 == Integer.MAX_VALUE) {
            a(eVar.b((Object) this.i), eVar.b((Object) this.j), eVar.b((Object) this.k), eVar.b((Object) this.l));
        } else if (i2 == -2) {
            a(this.W, this.X, this.Y, this.Z);
        } else {
            if (this.i.g == i2) {
                this.W = eVar.b((Object) this.i);
            }
            if (this.j.g == i2) {
                this.X = eVar.b((Object) this.j);
            }
            if (this.k.g == i2) {
                this.Y = eVar.b((Object) this.k);
            }
            if (this.l.g == i2) {
                this.Z = eVar.b((Object) this.l);
            }
        }
    }
}
