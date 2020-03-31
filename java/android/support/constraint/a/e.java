package android.support.constraint.a;

import android.support.constraint.a.a.b;
import android.support.constraint.a.g.a;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: LinearSystem */
public class e {
    private static int d = 1000;
    int a;
    int b;
    final c c;
    private HashMap<String, g> e;
    private d f;
    private int g;
    private int h;
    private b[] i;
    private boolean[] j;
    private int k;
    private int l;
    private g[] m;
    private int n;
    private b[] o;

    public e() {
        this.a = 0;
        this.e = null;
        this.f = new d();
        this.g = 32;
        this.h = this.g;
        this.i = null;
        this.j = new boolean[this.g];
        this.b = 1;
        this.k = 0;
        this.l = this.g;
        this.m = new g[d];
        this.n = 0;
        this.o = new b[this.g];
        this.i = new b[this.g];
        h();
        this.c = new c();
    }

    private void g() {
        this.g *= 2;
        this.i = (b[]) Arrays.copyOf(this.i, this.g);
        this.c.c = (g[]) Arrays.copyOf(this.c.c, this.g);
        this.j = new boolean[this.g];
        this.h = this.g;
        this.l = this.g;
        this.f.a.clear();
    }

    private void h() {
        for (int i2 = 0; i2 < this.i.length; i2++) {
            b bVar = this.i[i2];
            if (bVar != null) {
                this.c.a.a(bVar);
            }
            this.i[i2] = null;
        }
    }

    public void a() {
        for (g gVar : this.c.c) {
            if (gVar != null) {
                gVar.c();
            }
        }
        this.c.b.a(this.m, this.n);
        this.n = 0;
        Arrays.fill(this.c.c, null);
        if (this.e != null) {
            this.e.clear();
        }
        this.a = 0;
        this.f.a.clear();
        this.b = 1;
        for (int i2 = 0; i2 < this.k; i2++) {
            this.i[i2].c = false;
        }
        h();
        this.k = 0;
    }

    public g a(Object obj) {
        g gVar = null;
        if (obj != null) {
            if (this.b + 1 >= this.h) {
                g();
            }
            if (obj instanceof b) {
                gVar = ((b) obj).a();
                if (gVar == null) {
                    ((b) obj).a(this.c);
                    gVar = ((b) obj).a();
                }
                if (gVar.a == -1 || gVar.a > this.a || this.c.c[gVar.a] == null) {
                    if (gVar.a != -1) {
                        gVar.c();
                    }
                    this.a++;
                    this.b++;
                    gVar.a = this.a;
                    gVar.f = a.UNRESTRICTED;
                    this.c.c[this.a] = gVar;
                }
            }
        }
        return gVar;
    }

    public b b() {
        b bVar = (b) this.c.a.a();
        if (bVar == null) {
            return new b(this.c);
        }
        bVar.d();
        return bVar;
    }

    public g c() {
        if (this.b + 1 >= this.h) {
            g();
        }
        g a2 = a(a.SLACK);
        this.a++;
        this.b++;
        a2.a = this.a;
        this.c.c[this.a] = a2;
        return a2;
    }

    private void b(b bVar) {
        bVar.a(d(), d());
    }

    private void a(b bVar, int i2) {
        bVar.c(d(), i2);
    }

    public g d() {
        if (this.b + 1 >= this.h) {
            g();
        }
        g a2 = a(a.ERROR);
        this.a++;
        this.b++;
        a2.a = this.a;
        this.c.c[this.a] = a2;
        return a2;
    }

    private g a(a aVar) {
        g gVar;
        g gVar2 = (g) this.c.b.a();
        if (gVar2 == null) {
            gVar = new g(aVar);
        } else {
            gVar2.c();
            gVar2.a(aVar);
            gVar = gVar2;
        }
        if (this.n >= d) {
            d *= 2;
            this.m = (g[]) Arrays.copyOf(this.m, d);
        }
        g[] gVarArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        gVarArr[i2] = gVar;
        return gVar;
    }

    /* access modifiers changed from: 0000 */
    public b a(int i2) {
        return this.i[i2];
    }

    public int b(Object obj) {
        g a2 = ((b) obj).a();
        if (a2 != null) {
            return (int) (a2.d + 0.5f);
        }
        return 0;
    }

    public void e() throws Exception {
        a(this.f);
    }

    /* access modifiers changed from: 0000 */
    public void a(d dVar) throws Exception {
        dVar.a(this);
        c(dVar);
        b(dVar);
        i();
    }

    private void c(b bVar) {
        if (this.k > 0) {
            bVar.d.a(bVar, this.i);
            if (bVar.d.a == 0) {
                bVar.e = true;
            }
        }
    }

    public void a(b bVar) {
        if (bVar != null) {
            if (this.k + 1 >= this.l || this.b + 1 >= this.h) {
                g();
            }
            if (!bVar.e) {
                c(bVar);
                bVar.e();
                bVar.f();
                if (!bVar.b()) {
                    return;
                }
            }
            if (this.i[this.k] != null) {
                this.c.a.a(this.i[this.k]);
            }
            if (!bVar.e) {
                bVar.a();
            }
            this.i[this.k] = bVar;
            bVar.a.b = this.k;
            this.k++;
            int i2 = bVar.a.h;
            if (i2 > 0) {
                while (this.o.length < i2) {
                    this.o = new b[(this.o.length * 2)];
                }
                b[] bVarArr = this.o;
                for (int i3 = 0; i3 < i2; i3++) {
                    bVarArr[i3] = bVar.a.g[i3];
                }
                for (int i4 = 0; i4 < i2; i4++) {
                    b bVar2 = bVarArr[i4];
                    if (bVar2 != bVar) {
                        bVar2.d.a(bVar2, bVar);
                        bVar2.a();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(android.support.constraint.a.d r14) {
        /*
            r13 = this;
            r3 = -1
            r7 = 1
            r1 = 0
            r0 = r1
        L_0x0004:
            int r2 = r13.b
            if (r0 >= r2) goto L_0x000f
            boolean[] r2 = r13.j
            r2[r0] = r1
            int r0 = r0 + 1
            goto L_0x0004
        L_0x000f:
            r2 = r1
            r0 = r1
            r6 = r1
        L_0x0012:
            if (r6 != 0) goto L_0x00a3
            int r10 = r0 + 1
            android.support.constraint.a.g r0 = r14.a()
            if (r0 == 0) goto L_0x00a4
            boolean[] r4 = r13.j
            int r5 = r0.a
            boolean r4 = r4[r5]
            if (r4 == 0) goto L_0x0042
            r0 = 0
            r8 = r0
            r9 = r2
        L_0x0027:
            if (r8 == 0) goto L_0x00a1
            r0 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r2 = r3
            r4 = r0
            r0 = r1
        L_0x002f:
            int r5 = r13.k
            if (r0 >= r5) goto L_0x006e
            android.support.constraint.a.b[] r5 = r13.i
            r5 = r5[r0]
            android.support.constraint.a.g r11 = r5.a
            android.support.constraint.a.g$a r11 = r11.f
            android.support.constraint.a.g$a r12 = android.support.constraint.a.g.a.UNRESTRICTED
            if (r11 != r12) goto L_0x0052
        L_0x003f:
            int r0 = r0 + 1
            goto L_0x002f
        L_0x0042:
            boolean[] r4 = r13.j
            int r5 = r0.a
            r4[r5] = r7
            int r2 = r2 + 1
            int r4 = r13.b
            if (r2 < r4) goto L_0x00a4
            r8 = r0
            r9 = r2
            r6 = r7
            goto L_0x0027
        L_0x0052:
            boolean r11 = r5.a(r8)
            if (r11 == 0) goto L_0x003f
            android.support.constraint.a.a r11 = r5.d
            float r11 = r11.c(r8)
            r12 = 0
            int r12 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x003f
            float r5 = r5.b
            float r5 = -r5
            float r5 = r5 / r11
            int r11 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r11 >= 0) goto L_0x003f
            r2 = r0
            r4 = r5
            goto L_0x003f
        L_0x006e:
            if (r2 <= r3) goto L_0x009f
            android.support.constraint.a.b[] r0 = r13.i
            r4 = r0[r2]
            android.support.constraint.a.g r0 = r4.a
            r0.b = r3
            r4.b(r8)
            android.support.constraint.a.g r0 = r4.a
            r0.b = r2
            r0 = r1
        L_0x0080:
            int r2 = r13.k
            if (r0 >= r2) goto L_0x008e
            android.support.constraint.a.b[] r2 = r13.i
            r2 = r2[r0]
            r2.a(r4)
            int r0 = r0 + 1
            goto L_0x0080
        L_0x008e:
            r14.a(r13)
            r13.c(r14)     // Catch:{ Exception -> 0x009a }
        L_0x0094:
            r0 = r6
        L_0x0095:
            r2 = r9
            r6 = r0
            r0 = r10
            goto L_0x0012
        L_0x009a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0094
        L_0x009f:
            r0 = r7
            goto L_0x0095
        L_0x00a1:
            r0 = r7
            goto L_0x0095
        L_0x00a3:
            return r0
        L_0x00a4:
            r8 = r0
            r9 = r2
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.a.e.b(android.support.constraint.a.d):int");
    }

    private int c(d dVar) throws Exception {
        boolean z;
        int i2;
        boolean z2;
        int i3 = 0;
        while (true) {
            if (i3 >= this.k) {
                z = false;
                break;
            } else if (this.i[i3].a.f != a.UNRESTRICTED && this.i[i3].b < 0.0f) {
                z = true;
                break;
            } else {
                i3++;
            }
        }
        if (z) {
            boolean z3 = false;
            i2 = 0;
            while (!z3) {
                int i4 = i2 + 1;
                float f2 = Float.MAX_VALUE;
                int i5 = 0;
                int i6 = -1;
                int i7 = -1;
                for (int i8 = 0; i8 < this.k; i8++) {
                    b bVar = this.i[i8];
                    if (bVar.a.f != a.UNRESTRICTED && bVar.b < 0.0f) {
                        float f3 = f2;
                        int i9 = i5;
                        int i10 = i6;
                        int i11 = i7;
                        for (int i12 = 1; i12 < this.b; i12++) {
                            g gVar = this.c.c[i12];
                            float c2 = bVar.d.c(gVar);
                            if (c2 > 0.0f) {
                                float f4 = f3;
                                int i13 = 0;
                                while (i13 < 6) {
                                    float f5 = gVar.e[i13] / c2;
                                    if ((f5 >= f4 || i13 != i9) && i13 <= i9) {
                                        f5 = f4;
                                    } else {
                                        i11 = i12;
                                        i10 = i8;
                                        i9 = i13;
                                    }
                                    i13++;
                                    f4 = f5;
                                }
                                f3 = f4;
                            }
                        }
                        i7 = i11;
                        i6 = i10;
                        i5 = i9;
                        f2 = f3;
                    }
                }
                if (i6 != -1) {
                    b bVar2 = this.i[i6];
                    bVar2.a.b = -1;
                    bVar2.b(this.c.c[i7]);
                    bVar2.a.b = i6;
                    for (int i14 = 0; i14 < this.k; i14++) {
                        this.i[i14].a(bVar2);
                    }
                    dVar.a(this);
                    z2 = z3;
                } else {
                    z2 = true;
                }
                z3 = z2;
                i2 = i4;
            }
        } else {
            i2 = 0;
        }
        int i15 = 0;
        while (i15 < this.k && (this.i[i15].a.f == a.UNRESTRICTED || this.i[i15].b >= 0.0f)) {
            i15++;
        }
        return i2;
    }

    private void i() {
        for (int i2 = 0; i2 < this.k; i2++) {
            b bVar = this.i[i2];
            bVar.a.d = bVar.b;
        }
    }

    public c f() {
        return this.c;
    }

    public void a(g gVar, g gVar2, int i2, int i3) {
        b b2 = b();
        g c2 = c();
        c2.c = i3;
        b2.a(gVar, gVar2, c2, i2);
        a(b2);
    }

    public void b(g gVar, g gVar2, int i2, int i3) {
        b b2 = b();
        g c2 = c();
        c2.c = i3;
        b2.b(gVar, gVar2, c2, i2);
        a(b2);
    }

    public void a(g gVar, g gVar2, int i2, float f2, g gVar3, g gVar4, int i3, int i4) {
        b b2 = b();
        b2.a(gVar, gVar2, i2, f2, gVar3, gVar4, i3);
        g d2 = d();
        g d3 = d();
        d2.c = i4;
        d3.c = i4;
        b2.a(d2, d3);
        a(b2);
    }

    public b c(g gVar, g gVar2, int i2, int i3) {
        b b2 = b();
        b2.a(gVar, gVar2, i2);
        g d2 = d();
        g d3 = d();
        d2.c = i3;
        d3.c = i3;
        b2.a(d2, d3);
        a(b2);
        return b2;
    }

    public void a(g gVar, int i2) {
        int i3 = gVar.b;
        if (gVar.b != -1) {
            b bVar = this.i[i3];
            if (bVar.e) {
                bVar.b = (float) i2;
                return;
            }
            b b2 = b();
            b2.b(gVar, i2);
            a(b2);
            return;
        }
        b b3 = b();
        b3.a(gVar, i2);
        a(b3);
    }

    public static b a(e eVar, g gVar, g gVar2, int i2, boolean z) {
        b b2 = eVar.b();
        b2.a(gVar, gVar2, i2);
        if (z) {
            eVar.a(b2, 1);
        }
        return b2;
    }

    public static b a(e eVar, g gVar, g gVar2, g gVar3, float f2, boolean z) {
        b b2 = eVar.b();
        if (z) {
            eVar.b(b2);
        }
        return b2.a(gVar, gVar2, gVar3, f2);
    }

    public static b b(e eVar, g gVar, g gVar2, int i2, boolean z) {
        g c2 = eVar.c();
        b b2 = eVar.b();
        b2.a(gVar, gVar2, c2, i2);
        if (z) {
            eVar.a(b2, (int) (b2.d.c(c2) * -1.0f));
        }
        return b2;
    }

    public static b c(e eVar, g gVar, g gVar2, int i2, boolean z) {
        g c2 = eVar.c();
        b b2 = eVar.b();
        b2.b(gVar, gVar2, c2, i2);
        if (z) {
            eVar.a(b2, (int) (b2.d.c(c2) * -1.0f));
        }
        return b2;
    }

    public static b a(e eVar, g gVar, g gVar2, int i2, float f2, g gVar3, g gVar4, int i3, boolean z) {
        b b2 = eVar.b();
        b2.a(gVar, gVar2, i2, f2, gVar3, gVar4, i3);
        if (z) {
            g d2 = eVar.d();
            g d3 = eVar.d();
            d2.c = 4;
            d3.c = 4;
            b2.a(d2, d3);
        }
        return b2;
    }
}
