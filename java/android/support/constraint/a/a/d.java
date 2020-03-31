package android.support.constraint.a.a;

import android.support.constraint.a.a.c.a;
import android.support.constraint.a.b;
import android.support.constraint.a.e;
import android.support.constraint.a.g;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: ConstraintWidgetContainer */
public class d extends i {
    static boolean V = true;
    protected e T = new e();
    protected e U = null;
    int W;
    int X;
    int Y;
    int Z;
    int aa;
    int ab;
    private h ad;
    private int ae = 0;
    private int af = 0;
    private c[] ag = new c[4];
    private c[] ah = new c[4];
    private c[] ai = new c[4];
    private int aj = 2;
    private boolean[] ak = new boolean[2];

    public void k(int i) {
        this.aj = i;
    }

    public void a() {
        this.T.a();
        this.Y = 0;
        this.aa = 0;
        this.Z = 0;
        this.ab = 0;
        super.a();
    }

    public boolean c(e eVar, int i) {
        a(eVar, i);
        int size = this.ac.size();
        if ((this.aj == 2 || this.aj == 4) && a(eVar)) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = (c) this.ac.get(i2);
            if (cVar instanceof d) {
                a aVar = cVar.C;
                a aVar2 = cVar.D;
                if (aVar == a.WRAP_CONTENT) {
                    cVar.a(a.FIXED);
                }
                if (aVar2 == a.WRAP_CONTENT) {
                    cVar.b(a.FIXED);
                }
                cVar.a(eVar, i);
                if (aVar == a.WRAP_CONTENT) {
                    cVar.a(aVar);
                }
                if (aVar2 == a.WRAP_CONTENT) {
                    cVar.b(aVar2);
                }
            } else {
                cVar.a(eVar, i);
            }
        }
        if (this.ae > 0) {
            b(eVar);
        }
        if (this.af > 0) {
            c(eVar);
        }
        return true;
    }

    private boolean a(e eVar) {
        int i;
        boolean z;
        int i2;
        int size = this.ac.size();
        for (int i3 = 0; i3 < size; i3++) {
            c cVar = (c) this.ac.get(i3);
            cVar.a = -1;
            cVar.b = -1;
            if (cVar.C == a.MATCH_CONSTRAINT || cVar.D == a.MATCH_CONSTRAINT) {
                cVar.a = 1;
                cVar.b = 1;
            }
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
        while (!z2) {
            int i7 = i4 + 1;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i8 < size) {
                c cVar2 = (c) this.ac.get(i8);
                if (cVar2.a == -1) {
                    if (this.C == a.WRAP_CONTENT) {
                        cVar2.a = 1;
                    } else {
                        f.a(this, eVar, cVar2);
                    }
                }
                if (cVar2.b == -1) {
                    if (this.D == a.WRAP_CONTENT) {
                        cVar2.b = 1;
                    } else {
                        f.b(this, eVar, cVar2);
                    }
                }
                if (cVar2.b == -1) {
                    i10++;
                }
                if (cVar2.a == -1) {
                    i2 = i9 + 1;
                } else {
                    i2 = i9;
                }
                i8++;
                i9 = i2;
            }
            if (i10 == 0 && i9 == 0) {
                z = true;
            } else if (i6 == i10 && i5 == i9) {
                z = true;
            } else {
                z = z2;
            }
            i5 = i9;
            i6 = i10;
            z2 = z;
            i4 = i7;
        }
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i11 < size) {
            c cVar3 = (c) this.ac.get(i11);
            if (cVar3.a == 1 || cVar3.a == -1) {
                i13++;
            }
            if (cVar3.b == 1 || cVar3.b == -1) {
                i = i12 + 1;
            } else {
                i = i12;
            }
            i11++;
            i12 = i;
        }
        if (i13 == 0 && i12 == 0) {
            return true;
        }
        return false;
    }

    private void b(e eVar) {
        c cVar;
        boolean z;
        c cVar2;
        c cVar3;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.ae) {
                c cVar4 = this.ai[i2];
                int a = a(this.ai[i2], 0, this.ak);
                if (this.ak[1]) {
                    int n = cVar4.n();
                    while (cVar4 != null) {
                        eVar.a(cVar4.i.f, n);
                        c cVar5 = cVar4.k.c != null ? cVar4.k.c.a : null;
                        if (cVar5 == null || cVar5.i.c == null || cVar5.i.c.a != cVar4) {
                            cVar5 = null;
                        }
                        n += cVar4.i.d() + cVar4.l() + cVar4.k.d();
                        cVar4 = cVar5;
                    }
                    return;
                }
                boolean z2 = cVar4.N == 0;
                boolean z3 = cVar4.N == 2;
                boolean z4 = this.C == a.WRAP_CONTENT;
                if ((this.aj != 2 && this.aj != 8) || !this.ak[0] || !cVar4.P || z3 || z4 || cVar4.N != 0) {
                    if (a != 0 && !z3) {
                        float f = 0.0f;
                        c cVar6 = null;
                        while (true) {
                            if (cVar6 == null || (cVar4.i.c != null && cVar4.i.c.a == cVar6)) {
                                if (cVar4.C != a.MATCH_CONSTRAINT) {
                                    int d = cVar4.i.d();
                                    if (cVar6 != null) {
                                        d += cVar6.k.d();
                                    }
                                    int i3 = 3;
                                    if (cVar4.i.c.a.C == a.MATCH_CONSTRAINT) {
                                        i3 = 2;
                                    }
                                    eVar.a(cVar4.i.f, cVar4.i.c.f, d, i3);
                                    int d2 = cVar4.k.d();
                                    if (cVar4.k.c.a.i.c != null && cVar4.k.c.a.i.c.a == cVar4) {
                                        d2 += cVar4.k.c.a.i.d();
                                    }
                                    int i4 = 3;
                                    if (cVar4.k.c.a.C == a.MATCH_CONSTRAINT) {
                                        i4 = 2;
                                    }
                                    eVar.b(cVar4.k.f, cVar4.k.c.f, -d2, i4);
                                } else {
                                    f += cVar4.R;
                                    eVar.a(cVar4.k.f, cVar4.i.f, 0, 1);
                                    eVar.b(cVar4.k.f, cVar4.k.c.f, 0, 1);
                                }
                                cVar6 = cVar4;
                                cVar4 = cVar4.k.c.a;
                            }
                        }
                        if (a != 1) {
                            int i5 = 0;
                            while (true) {
                                int i6 = i5;
                                if (i6 >= a - 1) {
                                    break;
                                }
                                c cVar7 = this.ag[i6];
                                c cVar8 = this.ag[i6 + 1];
                                g gVar = cVar7.i.f;
                                g gVar2 = cVar7.k.f;
                                g gVar3 = cVar8.i.f;
                                g gVar4 = cVar8.k.f;
                                int d3 = cVar7.i.d();
                                if (!(cVar7.i.c == null || cVar7.i.c.a.k.c == null || cVar7.i.c.a.k.c.a != cVar7)) {
                                    d3 += cVar7.i.c.a.k.d();
                                }
                                eVar.a(gVar, cVar7.i.c.f, d3, 2);
                                int d4 = cVar7.k.d();
                                if (!(cVar7.k.c == null || cVar7.k.c.a.i.c == null || cVar7.k.c.a.i.c.a != cVar7)) {
                                    d4 += cVar7.k.c.a.i.d();
                                }
                                eVar.b(gVar2, cVar7.k.c.f, -d4, 2);
                                if (i6 + 1 == a - 1) {
                                    int d5 = cVar8.i.d();
                                    if (!(cVar8.i.c == null || cVar8.i.c.a.k.c == null || cVar8.i.c.a.k.c.a != cVar8)) {
                                        d5 += cVar8.i.c.a.k.d();
                                    }
                                    eVar.a(gVar3, cVar8.i.c.f, d5, 2);
                                    int d6 = cVar8.k.d();
                                    if (!(cVar8.k.c == null || cVar8.k.c.a.i.c == null || cVar8.k.c.a.i.c.a != cVar8)) {
                                        d6 += cVar8.k.c.a.i.d();
                                    }
                                    eVar.b(gVar4, cVar8.k.c.f, -d6, 2);
                                }
                                if (cVar4.f > 0) {
                                    eVar.b(gVar2, gVar, cVar4.f, 2);
                                }
                                b b = eVar.b();
                                b.a(cVar7.R, f, cVar8.R, gVar, cVar7.i.d(), gVar2, cVar7.k.d(), gVar3, cVar8.i.d(), gVar4, cVar8.k.d());
                                eVar.a(b);
                                i5 = i6 + 1;
                            }
                        } else {
                            c cVar9 = this.ag[0];
                            int d7 = cVar9.i.d();
                            if (cVar9.i.c != null) {
                                d7 += cVar9.i.c.d();
                            }
                            int d8 = cVar9.k.d();
                            if (cVar9.k.c != null) {
                                d8 += cVar9.k.c.d();
                            }
                            eVar.c(cVar9.i.f, cVar9.i.c.f, d7, 1);
                            eVar.c(cVar9.k.f, cVar9.k.c.f, -d8, 1);
                        }
                    } else {
                        c cVar10 = null;
                        c cVar11 = cVar4;
                        while (cVar11 != null && cVar11.d() == 8) {
                            eVar.c(cVar11.i.f, cVar11.i.c.f, 0, 5);
                            eVar.c(cVar11.k.f, cVar11.i.c.f, 0, 5);
                            cVar11 = cVar11.k.c != null ? cVar11.k.c.a : null;
                        }
                        boolean z5 = false;
                        c cVar12 = null;
                        c cVar13 = null;
                        c cVar14 = cVar11;
                        while (cVar14 != null) {
                            c cVar15 = cVar14.k.c != null ? cVar14.k.c.a : null;
                            if (cVar15 == null || cVar15.i.c == null || cVar15.i.c.a != cVar14) {
                                cVar12 = cVar15;
                                z = true;
                                cVar = cVar14;
                            } else {
                                while (!z5 && cVar15 != null && cVar15.d() == 8) {
                                    eVar.c(cVar15.i.f, cVar15.i.c.f, 0, 5);
                                    eVar.c(cVar15.k.f, cVar15.i.c.f, 0, 5);
                                    if (cVar15.k.c != null) {
                                        cVar3 = cVar15.k.c.a;
                                    } else {
                                        cVar3 = null;
                                    }
                                    if (cVar3 == null || cVar3.i.c == null || cVar3.i.c.a != cVar15) {
                                        z5 = true;
                                        cVar10 = cVar15;
                                    }
                                    cVar15 = cVar3;
                                }
                                cVar12 = cVar15;
                                z = z5;
                                cVar = cVar10;
                            }
                            if (z3) {
                                b bVar = cVar14.i;
                                int d9 = bVar.d();
                                if (cVar13 != null) {
                                    d9 += cVar13.k.d();
                                }
                                eVar.a(bVar.f, bVar.c.f, d9, 1);
                                if (cVar14.C == a.MATCH_CONSTRAINT) {
                                    eVar.c(cVar14.k.f, bVar.f, cVar14.e, 2);
                                }
                            } else if (z2 || !z || cVar13 == null) {
                                if (z2 || z || cVar13 != null) {
                                    b bVar2 = cVar14.i;
                                    b bVar3 = cVar14.k;
                                    int d10 = bVar2.d();
                                    int d11 = bVar3.d();
                                    eVar.a(bVar2.f, bVar2.c.f, d10, 1);
                                    eVar.b(bVar3.f, bVar3.c.f, -d11, 1);
                                    g gVar5 = bVar2.c != null ? bVar2.c.f : null;
                                    if (cVar13 == null) {
                                        gVar5 = cVar4.i.c != null ? cVar4.i.c.f : null;
                                    }
                                    g gVar6 = cVar14.r == cVar12 ? cVar12.k.f : cVar12.i.f;
                                    if (!(gVar5 == null || gVar6 == null)) {
                                        eVar.a(bVar2.f, gVar5, d10, 0.5f, gVar6, bVar3.f, d11, 4);
                                    }
                                } else if (cVar14.i.c == null) {
                                    eVar.a(cVar14.i.f, cVar14.n());
                                } else {
                                    eVar.c(cVar14.i.f, cVar4.i.c.f, cVar14.i.d(), 5);
                                }
                            } else if (cVar14.k.c == null) {
                                eVar.a(cVar14.k.f, cVar14.q());
                            } else {
                                eVar.c(cVar14.k.f, cVar.k.c.f, -cVar14.k.d(), 5);
                            }
                            if (z) {
                                cVar2 = null;
                            } else {
                                cVar2 = cVar12;
                            }
                            cVar10 = cVar;
                            cVar13 = cVar14;
                            cVar14 = cVar2;
                            z5 = z;
                        }
                        if (z3) {
                            b bVar4 = cVar11.i;
                            b bVar5 = cVar10.k;
                            int d12 = bVar4.d();
                            int d13 = bVar5.d();
                            g gVar7 = cVar4.i.c != null ? cVar4.i.c.f : null;
                            g gVar8 = cVar10.r == cVar12 ? cVar12.k.f : cVar12.i.f;
                            if (!(gVar7 == null || gVar8 == null)) {
                                eVar.a(bVar4.f, gVar7, d12, cVar4.A, gVar8, bVar5.f, d13, 3);
                            }
                        }
                    }
                } else {
                    f.a(this, eVar, a, cVar4);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private void c(e eVar) {
        c cVar;
        boolean z;
        c cVar2;
        g gVar;
        int i;
        g gVar2;
        c cVar3;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.af) {
                c cVar4 = this.ah[i3];
                int a = a(this.ah[i3], 1, this.ak);
                if (this.ak[1]) {
                    int o = cVar4.o();
                    while (cVar4 != null) {
                        eVar.a(cVar4.j.f, o);
                        c cVar5 = cVar4.l.c != null ? cVar4.l.c.a : null;
                        if (cVar5 == null || cVar5.j.c == null || cVar5.j.c.a != cVar4) {
                            cVar5 = null;
                        }
                        o += cVar4.j.d() + cVar4.m() + cVar4.l.d();
                        cVar4 = cVar5;
                    }
                    return;
                }
                boolean z2 = cVar4.O == 0;
                boolean z3 = cVar4.O == 2;
                boolean z4 = this.D == a.WRAP_CONTENT;
                if ((this.aj != 2 && this.aj != 8) || !this.ak[0] || !cVar4.Q || z3 || z4 || cVar4.O != 0) {
                    if (a != 0 && !z3) {
                        float f = 0.0f;
                        c cVar6 = null;
                        c cVar7 = cVar4;
                        while (true) {
                            if (cVar6 == null || (cVar7.j.c != null && cVar7.j.c.a == cVar6)) {
                                if (cVar7.D != a.MATCH_CONSTRAINT) {
                                    int d = cVar7.j.d();
                                    if (cVar6 != null) {
                                        d += cVar6.l.d();
                                    }
                                    int i4 = 3;
                                    if (cVar7.j.c.a.D == a.MATCH_CONSTRAINT) {
                                        i4 = 2;
                                    }
                                    eVar.a(cVar7.j.f, cVar7.j.c.f, d, i4);
                                    int d2 = cVar7.l.d();
                                    if (cVar7.l.c.a.j.c != null && cVar7.l.c.a.j.c.a == cVar7) {
                                        d2 += cVar7.l.c.a.j.d();
                                    }
                                    int i5 = 3;
                                    if (cVar7.l.c.a.D == a.MATCH_CONSTRAINT) {
                                        i5 = 2;
                                    }
                                    eVar.b(cVar7.l.f, cVar7.l.c.f, -d2, i5);
                                } else {
                                    f += cVar7.S;
                                    eVar.a(cVar7.l.f, cVar7.j.f, 0, 1);
                                    eVar.b(cVar7.l.f, cVar7.l.c.f, 0, 1);
                                }
                                cVar6 = cVar7;
                                cVar7 = cVar7.l.c.a;
                            }
                        }
                        if (a != 1) {
                            int i6 = 0;
                            while (true) {
                                int i7 = i6;
                                if (i7 >= a - 1) {
                                    break;
                                }
                                c cVar8 = this.ag[i7];
                                c cVar9 = this.ag[i7 + 1];
                                g gVar3 = cVar8.j.f;
                                g gVar4 = cVar8.l.f;
                                g gVar5 = cVar9.j.f;
                                g gVar6 = cVar9.l.f;
                                int d3 = cVar8.j.d();
                                if (!(cVar8.j.c == null || cVar8.j.c.a.l.c == null || cVar8.j.c.a.l.c.a != cVar8)) {
                                    d3 += cVar8.j.c.a.l.d();
                                }
                                eVar.a(gVar3, cVar8.j.c.f, d3, 2);
                                int d4 = cVar8.l.d();
                                if (!(cVar8.l.c == null || cVar8.l.c.a.j.c == null || cVar8.l.c.a.j.c.a != cVar8)) {
                                    d4 += cVar8.l.c.a.j.d();
                                }
                                eVar.b(gVar4, cVar8.l.c.f, -d4, 2);
                                if (i7 + 1 == a - 1) {
                                    int d5 = cVar9.j.d();
                                    if (!(cVar9.j.c == null || cVar9.j.c.a.l.c == null || cVar9.j.c.a.l.c.a != cVar9)) {
                                        d5 += cVar9.j.c.a.l.d();
                                    }
                                    eVar.a(gVar5, cVar9.j.c.f, d5, 2);
                                    int d6 = cVar9.l.d();
                                    if (!(cVar9.l.c == null || cVar9.l.c.a.j.c == null || cVar9.l.c.a.j.c.a != cVar9)) {
                                        d6 += cVar9.l.c.a.j.d();
                                    }
                                    eVar.b(gVar6, cVar9.l.c.f, -d6, 2);
                                }
                                b b = eVar.b();
                                b.a(cVar8.S, f, cVar9.S, gVar3, cVar8.j.d(), gVar4, cVar8.l.d(), gVar5, cVar9.j.d(), gVar6, cVar9.l.d());
                                eVar.a(b);
                                i6 = i7 + 1;
                            }
                        } else {
                            c cVar10 = this.ag[0];
                            int d7 = cVar10.j.d();
                            if (cVar10.j.c != null) {
                                d7 += cVar10.j.c.d();
                            }
                            eVar.c(cVar10.j.f, cVar10.j.c.f, d7, 1);
                            int d8 = cVar10.l.d();
                            if (cVar10.l.c != null) {
                                d8 += cVar10.l.c.d();
                            }
                            eVar.c(cVar10.l.f, cVar10.l.c.f, -d8, 1);
                        }
                    } else {
                        c cVar11 = null;
                        c cVar12 = cVar4;
                        while (cVar12 != null && cVar12.d() == 8) {
                            eVar.c(cVar12.j.f, cVar12.j.c.f, 0, 5);
                            eVar.c(cVar12.l.f, cVar12.j.c.f, 0, 5);
                            cVar12 = cVar12.l.c != null ? cVar12.l.c.a : null;
                        }
                        boolean z5 = false;
                        c cVar13 = null;
                        c cVar14 = null;
                        c cVar15 = cVar12;
                        while (cVar15 != null) {
                            c cVar16 = cVar15.l.c != null ? cVar15.l.c.a : null;
                            if (cVar16 == null || cVar16.j.c == null || cVar16.j.c.a != cVar15) {
                                cVar13 = cVar16;
                                z = true;
                                cVar = cVar15;
                            } else {
                                while (!z5 && cVar16 != null && cVar16.d() == 8) {
                                    eVar.c(cVar16.j.f, cVar16.j.c.f, 0, 5);
                                    eVar.c(cVar16.l.f, cVar16.j.c.f, 0, 5);
                                    if (cVar16.l.c != null) {
                                        cVar3 = cVar16.l.c.a;
                                    } else {
                                        cVar3 = null;
                                    }
                                    if (cVar3 == null || cVar3.j.c == null || cVar3.j.c.a != cVar16) {
                                        z5 = true;
                                        cVar11 = cVar16;
                                    }
                                    cVar16 = cVar3;
                                }
                                cVar13 = cVar16;
                                z = z5;
                                cVar = cVar11;
                            }
                            if (z3) {
                                b bVar = cVar15.j;
                                int d9 = bVar.d();
                                if (cVar14 != null) {
                                    d9 += cVar14.l.d();
                                }
                                if (bVar.c != null) {
                                    gVar = bVar.f;
                                    i = d9;
                                    gVar2 = bVar.c.f;
                                } else if (cVar15.m.c != null) {
                                    gVar = cVar15.m.f;
                                    i = d9 - bVar.d();
                                    gVar2 = cVar15.m.c.f;
                                } else {
                                    gVar = null;
                                    i = d9;
                                    gVar2 = null;
                                }
                                if (!(gVar == null || gVar2 == null)) {
                                    eVar.a(gVar, gVar2, i, 1);
                                }
                                if (cVar15.D == a.MATCH_CONSTRAINT) {
                                    eVar.c(cVar15.l.f, gVar, cVar15.g, 2);
                                }
                            } else if (z2 || !z || cVar14 == null) {
                                if (z2 || z || cVar14 != null) {
                                    b bVar2 = cVar15.j;
                                    b bVar3 = cVar15.l;
                                    int d10 = bVar2.d();
                                    int d11 = bVar3.d();
                                    eVar.a(bVar2.f, bVar2.c.f, d10, 1);
                                    eVar.b(bVar3.f, bVar3.c.f, -d11, 1);
                                    g gVar7 = bVar2.c != null ? bVar2.c.f : null;
                                    if (cVar14 == null) {
                                        gVar7 = cVar4.j.c != null ? cVar4.j.c.f : null;
                                    }
                                    g gVar8 = cVar15.r == cVar13 ? cVar13.l.f : cVar13.j.f;
                                    if (!(gVar7 == null || gVar8 == null)) {
                                        eVar.a(bVar2.f, gVar7, d10, 0.5f, gVar8, bVar3.f, d11, 3);
                                    }
                                } else if (cVar15.j.c == null) {
                                    eVar.a(cVar15.j.f, cVar15.o());
                                } else {
                                    eVar.c(cVar15.j.f, cVar4.j.c.f, cVar15.j.d(), 5);
                                }
                            } else if (cVar15.l.c == null) {
                                eVar.a(cVar15.l.f, cVar15.p());
                            } else {
                                eVar.c(cVar15.l.f, cVar.l.c.f, -cVar15.l.d(), 5);
                            }
                            if (z) {
                                cVar2 = null;
                            } else {
                                cVar2 = cVar13;
                            }
                            cVar11 = cVar;
                            cVar14 = cVar15;
                            cVar15 = cVar2;
                            z5 = z;
                        }
                        if (z3) {
                            b bVar4 = cVar12.j;
                            b bVar5 = cVar11.l;
                            int d12 = bVar4.d();
                            int d13 = bVar5.d();
                            g gVar9 = cVar4.j.c != null ? cVar4.j.c.f : null;
                            g gVar10 = cVar11.r == cVar13 ? cVar13.l.f : cVar13.j.f;
                            if (!(gVar9 == null || gVar10 == null)) {
                                eVar.a(bVar4.f, gVar9, d12, cVar4.B, gVar10, bVar5.f, d13, 3);
                            }
                        }
                    }
                } else {
                    f.b(this, eVar, a, cVar4);
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    public void d(e eVar, int i) {
        b(eVar, i);
        int size = this.ac.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((c) this.ac.get(i2)).b(eVar, i);
        }
    }

    public void D() {
        boolean z;
        boolean z2 = true;
        int i = this.u;
        int i2 = this.v;
        int l = l();
        int m = m();
        if (this.r != null) {
            if (this.ad == null) {
                this.ad = new h(this);
            }
            this.ad.a(this);
            b(this.Y);
            c(this.Z);
            A();
            a(this.T.f());
        } else {
            this.u = 0;
            this.v = 0;
        }
        a aVar = this.D;
        a aVar2 = this.C;
        if (this.D == a.WRAP_CONTENT || this.C == a.WRAP_CONTENT) {
            a(this.ac);
            if (this.C == a.WRAP_CONTENT) {
                this.C = a.FIXED;
                d(l > 0 ? Math.min(l, this.W) : this.W);
            }
            if (this.D == a.WRAP_CONTENT) {
                this.D = a.FIXED;
                e(m > 0 ? Math.min(m, this.X) : this.X);
            }
            z = true;
        } else {
            z = false;
        }
        H();
        int size = this.ac.size();
        for (int i3 = 0; i3 < size; i3++) {
            c cVar = (c) this.ac.get(i3);
            if (cVar instanceof i) {
                ((i) cVar).D();
            }
        }
        try {
            this.T.a();
            z2 = c(this.T, Integer.MAX_VALUE);
            if (z2) {
                this.T.e();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (z2) {
            d(this.T, Integer.MAX_VALUE);
        } else {
            b(this.T, Integer.MAX_VALUE);
        }
        if (this.r != null) {
            int l2 = l();
            int m2 = m();
            this.ad.b(this);
            d(l2 + this.Y + this.aa);
            e(this.Z + m2 + this.ab);
        } else {
            this.u = i;
            this.v = i2;
        }
        if (z) {
            this.C = aVar2;
            this.D = aVar;
        }
        a(this.T.f());
        if (this == F()) {
            z();
        }
    }

    /* JADX WARNING: type inference failed for: r3v16, types: [int] */
    /* JADX WARNING: type inference failed for: r11v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.support.constraint.a.a.c r13) {
        /*
            r12 = this;
            r6 = 0
            r4 = 0
            r10 = -1
            r8 = 1
            r1 = 0
            int r0 = r13.l()
            int r3 = r13.m()
            android.support.constraint.a.a.c$a r2 = r13.C
            android.support.constraint.a.a.c$a r5 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r2 != r5) goto L_0x003b
            int r2 = r13.c
            if (r2 != r8) goto L_0x00a9
            int r2 = r13.e
            int r0 = java.lang.Math.max(r2, r0)
        L_0x001d:
            int r2 = r13.f
            if (r2 <= 0) goto L_0x0027
            int r2 = r13.f
            if (r2 >= r0) goto L_0x0027
            int r0 = r13.f
        L_0x0027:
            android.support.constraint.a.a.c$a r2 = r13.D
            android.support.constraint.a.a.c$a r5 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r2 == r5) goto L_0x003b
            float r2 = r13.s
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x003b
            float r0 = r13.s
            float r2 = (float) r3
            float r0 = r0 * r2
            int r0 = (int) r0
            r13.d(r0)
        L_0x003b:
            r2 = r0
            android.support.constraint.a.a.c$a r0 = r13.D
            android.support.constraint.a.a.c$a r5 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r0 != r5) goto L_0x006b
            int r0 = r13.d
            if (r0 != r8) goto L_0x00b7
            int r0 = r13.g
            int r0 = java.lang.Math.max(r0, r3)
        L_0x004c:
            int r3 = r13.h
            if (r3 <= 0) goto L_0x0056
            int r3 = r13.h
            if (r3 >= r0) goto L_0x0056
            int r0 = r13.h
        L_0x0056:
            android.support.constraint.a.a.c$a r3 = r13.C
            android.support.constraint.a.a.c$a r5 = android.support.constraint.a.a.c.a.MATCH_CONSTRAINT
            if (r3 == r5) goto L_0x006a
            float r3 = r13.s
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x006a
            float r0 = (float) r2
            float r3 = r13.s
            float r0 = r0 / r3
            int r0 = (int) r0
            r13.e(r0)
        L_0x006a:
            r3 = r0
        L_0x006b:
            r13.M = r8
            boolean r0 = r13 instanceof android.support.constraint.a.a.e
            if (r0 == 0) goto L_0x00d0
            r0 = r13
            android.support.constraint.a.a.e r0 = (android.support.constraint.a.a.e) r0
            int r5 = r0.D()
            if (r5 != r8) goto L_0x0372
            int r2 = r0.F()
            if (r2 == r10) goto L_0x00c3
            int r2 = r0.F()
            r0 = r1
        L_0x0085:
            r6 = r2
            r5 = r0
        L_0x0087:
            r13.F = r6
            r13.G = r5
            boolean r0 = r13 instanceof android.support.constraint.a.a.e
            if (r0 == 0) goto L_0x01f4
            r0 = r13
            android.support.constraint.a.a.e r0 = (android.support.constraint.a.a.e) r0
            int r2 = r0.D()
            if (r2 != 0) goto L_0x0363
            int r2 = r0.F()
            if (r2 == r10) goto L_0x01e5
            int r3 = r0.F()
        L_0x00a2:
            r2 = r3
            r3 = r1
        L_0x00a4:
            r13.E = r2
            r13.H = r3
        L_0x00a8:
            return
        L_0x00a9:
            int r0 = r13.e
            if (r0 <= 0) goto L_0x00b4
            int r0 = r13.e
            r13.d(r0)
            goto L_0x001d
        L_0x00b4:
            r0 = r1
            goto L_0x001d
        L_0x00b7:
            int r0 = r13.g
            if (r0 <= 0) goto L_0x00c1
            int r0 = r13.g
            r13.e(r0)
            goto L_0x004c
        L_0x00c1:
            r0 = r1
            goto L_0x004c
        L_0x00c3:
            int r2 = r0.G()
            if (r2 == r10) goto L_0x036e
            int r2 = r0.G()
            r0 = r2
            r2 = r1
            goto L_0x0085
        L_0x00d0:
            android.support.constraint.a.a.b r0 = r13.k
            boolean r0 = r0.j()
            if (r0 != 0) goto L_0x00e8
            android.support.constraint.a.a.b r0 = r13.i
            boolean r0 = r0.j()
            if (r0 != 0) goto L_0x00e8
            int r0 = r13.j()
            int r6 = r2 + r0
            r5 = r2
            goto L_0x0087
        L_0x00e8:
            android.support.constraint.a.a.b r0 = r13.k
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x036a
            android.support.constraint.a.a.b r0 = r13.k
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.c r0 = r0.a
            android.support.constraint.a.a.b r5 = r13.k
            int r5 = r5.d()
            int r5 = r5 + r2
            boolean r6 = r0.b()
            if (r6 != 0) goto L_0x0108
            boolean r6 = r0.M
            if (r6 != 0) goto L_0x0108
            r12.b(r0)
        L_0x0108:
            android.support.constraint.a.a.b r6 = r13.i
            android.support.constraint.a.a.b r6 = r6.c
            if (r6 == 0) goto L_0x0366
            android.support.constraint.a.a.b r6 = r13.i
            android.support.constraint.a.a.b r6 = r6.c
            android.support.constraint.a.a.c r6 = r6.a
            android.support.constraint.a.a.b r7 = r13.i
            int r7 = r7.d()
            int r2 = r2 + r7
            boolean r7 = r6.b()
            if (r7 != 0) goto L_0x0128
            boolean r7 = r6.M
            if (r7 != 0) goto L_0x0128
            r12.b(r6)
        L_0x0128:
            r11 = r6
            r6 = r2
            r2 = r11
        L_0x012b:
            android.support.constraint.a.a.b r7 = r13.k
            android.support.constraint.a.a.b r7 = r7.c
            if (r7 == 0) goto L_0x016b
            boolean r7 = r0.b()
            if (r7 != 0) goto L_0x016b
            android.support.constraint.a.a.b r7 = r13.k
            android.support.constraint.a.a.b r7 = r7.c
            android.support.constraint.a.a.b$c r7 = r7.b
            android.support.constraint.a.a.b$c r9 = android.support.constraint.a.a.b.c.RIGHT
            if (r7 != r9) goto L_0x01af
            int r7 = r0.G
            int r9 = r0.l()
            int r7 = r7 - r9
            int r5 = r5 + r7
        L_0x0149:
            boolean r7 = r0.J
            if (r7 != 0) goto L_0x0159
            android.support.constraint.a.a.b r7 = r0.i
            android.support.constraint.a.a.b r7 = r7.c
            if (r7 == 0) goto L_0x01bf
            android.support.constraint.a.a.b r7 = r0.k
            android.support.constraint.a.a.b r7 = r7.c
            if (r7 == 0) goto L_0x01bf
        L_0x0159:
            r7 = r8
        L_0x015a:
            r13.J = r7
            boolean r7 = r13.J
            if (r7 == 0) goto L_0x016b
            android.support.constraint.a.a.b r7 = r0.i
            android.support.constraint.a.a.b r7 = r7.c
            if (r7 != 0) goto L_0x01c1
        L_0x0166:
            int r0 = r0.G
            int r0 = r5 - r0
            int r5 = r5 + r0
        L_0x016b:
            android.support.constraint.a.a.b r0 = r13.i
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x0087
            boolean r0 = r2.b()
            if (r0 != 0) goto L_0x0087
            android.support.constraint.a.a.b r0 = r13.i
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.b$c r0 = r0.c()
            android.support.constraint.a.a.b$c r7 = android.support.constraint.a.a.b.c.LEFT
            if (r0 != r7) goto L_0x01ca
            int r0 = r2.F
            int r7 = r2.l()
            int r0 = r0 - r7
            int r6 = r6 + r0
        L_0x018b:
            boolean r0 = r2.I
            if (r0 != 0) goto L_0x019b
            android.support.constraint.a.a.b r0 = r2.i
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x01da
            android.support.constraint.a.a.b r0 = r2.k
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x01da
        L_0x019b:
            r0 = r8
        L_0x019c:
            r13.I = r0
            boolean r0 = r13.I
            if (r0 == 0) goto L_0x0087
            android.support.constraint.a.a.b r0 = r2.k
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 != 0) goto L_0x01dc
        L_0x01a8:
            int r0 = r2.F
            int r0 = r6 - r0
            int r6 = r6 + r0
            goto L_0x0087
        L_0x01af:
            android.support.constraint.a.a.b r7 = r13.k
            android.support.constraint.a.a.b r7 = r7.c
            android.support.constraint.a.a.b$c r7 = r7.c()
            android.support.constraint.a.a.b$c r9 = android.support.constraint.a.a.b.c.LEFT
            if (r7 != r9) goto L_0x0149
            int r7 = r0.G
            int r5 = r5 + r7
            goto L_0x0149
        L_0x01bf:
            r7 = r1
            goto L_0x015a
        L_0x01c1:
            android.support.constraint.a.a.b r7 = r0.i
            android.support.constraint.a.a.b r7 = r7.c
            android.support.constraint.a.a.c r7 = r7.a
            if (r7 == r13) goto L_0x016b
            goto L_0x0166
        L_0x01ca:
            android.support.constraint.a.a.b r0 = r13.i
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.b$c r0 = r0.c()
            android.support.constraint.a.a.b$c r7 = android.support.constraint.a.a.b.c.RIGHT
            if (r0 != r7) goto L_0x018b
            int r0 = r2.F
            int r6 = r6 + r0
            goto L_0x018b
        L_0x01da:
            r0 = r1
            goto L_0x019c
        L_0x01dc:
            android.support.constraint.a.a.b r0 = r2.k
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.c r0 = r0.a
            if (r0 == r13) goto L_0x0087
            goto L_0x01a8
        L_0x01e5:
            int r2 = r0.G()
            if (r2 == r10) goto L_0x0360
            int r3 = r0.G()
            r11 = r3
            r3 = r1
            r1 = r11
            goto L_0x00a2
        L_0x01f4:
            android.support.constraint.a.a.b r0 = r13.m
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 != 0) goto L_0x020e
            android.support.constraint.a.a.b r0 = r13.j
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 != 0) goto L_0x020e
            android.support.constraint.a.a.b r0 = r13.l
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 != 0) goto L_0x020e
            int r0 = r13.k()
            int r2 = r3 + r0
            goto L_0x00a4
        L_0x020e:
            android.support.constraint.a.a.b r0 = r13.m
            boolean r0 = r0.j()
            if (r0 == 0) goto L_0x0257
            android.support.constraint.a.a.b r0 = r13.m
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.c r2 = r0.b()
            boolean r0 = r2.M
            if (r0 != 0) goto L_0x0225
            r12.b(r2)
        L_0x0225:
            int r0 = r2.H
            if (r0 <= r3) goto L_0x035d
            int r0 = r2.H
        L_0x022b:
            int r4 = r2.E
            if (r4 <= r3) goto L_0x0231
            int r3 = r2.E
        L_0x0231:
            boolean r4 = r2.K
            if (r4 != 0) goto L_0x0245
            boolean r4 = r2.L
            if (r4 != 0) goto L_0x0245
            android.support.constraint.a.a.b r4 = r2.j
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x0246
            android.support.constraint.a.a.b r4 = r2.l
            android.support.constraint.a.a.b r4 = r4.c
            if (r4 == 0) goto L_0x0246
        L_0x0245:
            r1 = r8
        L_0x0246:
            r13.K = r1
            boolean r1 = r13.K
            if (r1 == 0) goto L_0x0251
            int r1 = r2.E
            int r1 = r3 - r1
            int r3 = r3 + r1
        L_0x0251:
            r13.E = r3
            r13.H = r0
            goto L_0x00a8
        L_0x0257:
            android.support.constraint.a.a.b r0 = r13.j
            boolean r0 = r0.j()
            if (r0 == 0) goto L_0x0359
            android.support.constraint.a.a.b r0 = r13.j
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.c r0 = r0.b()
            android.support.constraint.a.a.b r2 = r13.j
            int r2 = r2.d()
            int r2 = r2 + r3
            boolean r5 = r0.b()
            if (r5 != 0) goto L_0x027b
            boolean r5 = r0.M
            if (r5 != 0) goto L_0x027b
            r12.b(r0)
        L_0x027b:
            android.support.constraint.a.a.b r5 = r13.l
            boolean r5 = r5.j()
            if (r5 == 0) goto L_0x029f
            android.support.constraint.a.a.b r4 = r13.l
            android.support.constraint.a.a.b r4 = r4.c
            android.support.constraint.a.a.c r4 = r4.b()
            android.support.constraint.a.a.b r5 = r13.l
            int r5 = r5.d()
            int r3 = r3 + r5
            boolean r5 = r4.b()
            if (r5 != 0) goto L_0x029f
            boolean r5 = r4.M
            if (r5 != 0) goto L_0x029f
            r12.b(r4)
        L_0x029f:
            android.support.constraint.a.a.b r5 = r13.j
            android.support.constraint.a.a.b r5 = r5.c
            if (r5 == 0) goto L_0x02e1
            boolean r5 = r0.b()
            if (r5 != 0) goto L_0x02e1
            android.support.constraint.a.a.b r5 = r13.j
            android.support.constraint.a.a.b r5 = r5.c
            android.support.constraint.a.a.b$c r5 = r5.c()
            android.support.constraint.a.a.b$c r6 = android.support.constraint.a.a.b.c.TOP
            if (r5 != r6) goto L_0x0325
            int r5 = r0.E
            int r6 = r0.m()
            int r5 = r5 - r6
            int r2 = r2 + r5
        L_0x02bf:
            boolean r5 = r0.K
            if (r5 != 0) goto L_0x02cf
            android.support.constraint.a.a.b r5 = r0.j
            android.support.constraint.a.a.b r5 = r5.c
            if (r5 == 0) goto L_0x0335
            android.support.constraint.a.a.b r5 = r0.l
            android.support.constraint.a.a.b r5 = r5.c
            if (r5 == 0) goto L_0x0335
        L_0x02cf:
            r5 = r8
        L_0x02d0:
            r13.K = r5
            boolean r5 = r13.K
            if (r5 == 0) goto L_0x02e1
            android.support.constraint.a.a.b r5 = r0.l
            android.support.constraint.a.a.b r5 = r5.c
            if (r5 != 0) goto L_0x0337
        L_0x02dc:
            int r0 = r0.E
            int r0 = r2 - r0
            int r2 = r2 + r0
        L_0x02e1:
            android.support.constraint.a.a.b r0 = r13.l
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x00a4
            boolean r0 = r4.b()
            if (r0 != 0) goto L_0x00a4
            android.support.constraint.a.a.b r0 = r13.l
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.b$c r0 = r0.c()
            android.support.constraint.a.a.b$c r5 = android.support.constraint.a.a.b.c.BOTTOM
            if (r0 != r5) goto L_0x0340
            int r0 = r4.H
            int r5 = r4.m()
            int r0 = r0 - r5
            int r3 = r3 + r0
        L_0x0301:
            boolean r0 = r4.L
            if (r0 != 0) goto L_0x0311
            android.support.constraint.a.a.b r0 = r4.j
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x0312
            android.support.constraint.a.a.b r0 = r4.l
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 == 0) goto L_0x0312
        L_0x0311:
            r1 = r8
        L_0x0312:
            r13.L = r1
            boolean r0 = r13.L
            if (r0 == 0) goto L_0x00a4
            android.support.constraint.a.a.b r0 = r4.j
            android.support.constraint.a.a.b r0 = r0.c
            if (r0 != 0) goto L_0x0350
        L_0x031e:
            int r0 = r4.H
            int r0 = r3 - r0
            int r3 = r3 + r0
            goto L_0x00a4
        L_0x0325:
            android.support.constraint.a.a.b r5 = r13.j
            android.support.constraint.a.a.b r5 = r5.c
            android.support.constraint.a.a.b$c r5 = r5.c()
            android.support.constraint.a.a.b$c r6 = android.support.constraint.a.a.b.c.BOTTOM
            if (r5 != r6) goto L_0x02bf
            int r5 = r0.E
            int r2 = r2 + r5
            goto L_0x02bf
        L_0x0335:
            r5 = r1
            goto L_0x02d0
        L_0x0337:
            android.support.constraint.a.a.b r5 = r0.l
            android.support.constraint.a.a.b r5 = r5.c
            android.support.constraint.a.a.c r5 = r5.a
            if (r5 == r13) goto L_0x02e1
            goto L_0x02dc
        L_0x0340:
            android.support.constraint.a.a.b r0 = r13.l
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.b$c r0 = r0.c()
            android.support.constraint.a.a.b$c r5 = android.support.constraint.a.a.b.c.TOP
            if (r0 != r5) goto L_0x0301
            int r0 = r4.H
            int r3 = r3 + r0
            goto L_0x0301
        L_0x0350:
            android.support.constraint.a.a.b r0 = r4.j
            android.support.constraint.a.a.b r0 = r0.c
            android.support.constraint.a.a.c r0 = r0.a
            if (r0 == r13) goto L_0x00a4
            goto L_0x031e
        L_0x0359:
            r0 = r4
            r2 = r3
            goto L_0x027b
        L_0x035d:
            r0 = r3
            goto L_0x022b
        L_0x0360:
            r3 = r1
            goto L_0x00a2
        L_0x0363:
            r1 = r3
            goto L_0x00a2
        L_0x0366:
            r6 = r2
            r2 = r4
            goto L_0x012b
        L_0x036a:
            r0 = r4
            r5 = r2
            goto L_0x0108
        L_0x036e:
            r2 = r1
            r0 = r1
            goto L_0x0085
        L_0x0372:
            r0 = r2
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.a.a.d.b(android.support.constraint.a.a.c):void");
    }

    public void a(ArrayList<c> arrayList) {
        int max;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i < size) {
            c cVar = (c) arrayList.get(i);
            if (cVar.b()) {
                max = i2;
            } else {
                if (!cVar.M) {
                    b(cVar);
                }
                int l = (cVar.F + cVar.G) - cVar.l();
                int m = (cVar.E + cVar.H) - cVar.m();
                i6 = Math.max(i6, cVar.F);
                i5 = Math.max(i5, cVar.G);
                i4 = Math.max(i4, cVar.H);
                i7 = Math.max(i7, cVar.E);
                i3 = Math.max(i3, l);
                max = Math.max(i2, m);
            }
            int i8 = i3;
            int i9 = i4;
            int i10 = i5;
            int i11 = i6;
            i++;
            i7 = i7;
            i6 = i11;
            i5 = i10;
            i4 = i9;
            i3 = i8;
            i2 = max;
        }
        this.W = Math.max(Math.max(i6, i5), i3);
        this.X = Math.max(Math.max(i7, i4), i2);
        for (int i12 = 0; i12 < size; i12++) {
            ((c) arrayList.get(i12)).M = false;
            ((c) arrayList.get(i12)).I = false;
            ((c) arrayList.get(i12)).J = false;
            ((c) arrayList.get(i12)).K = false;
            ((c) arrayList.get(i12)).L = false;
        }
    }

    public boolean E() {
        return false;
    }

    private void H() {
        this.ae = 0;
        this.af = 0;
    }

    /* access modifiers changed from: 0000 */
    public void a(c cVar, int i) {
        if (i == 0) {
            while (cVar.i.c != null && cVar.i.c.a.k.c != null && cVar.i.c.a.k.c == cVar.i && cVar.i.c.a != cVar) {
                cVar = cVar.i.c.a;
            }
            e(cVar);
        } else if (i == 1) {
            while (cVar.j.c != null && cVar.j.c.a.l.c != null && cVar.j.c.a.l.c == cVar.j && cVar.j.c.a != cVar) {
                cVar = cVar.j.c.a;
            }
            f(cVar);
        }
    }

    private void e(c cVar) {
        int i = 0;
        while (i < this.ae) {
            if (this.ai[i] != cVar) {
                i++;
            } else {
                return;
            }
        }
        if (this.ae + 1 >= this.ai.length) {
            this.ai = (c[]) Arrays.copyOf(this.ai, this.ai.length * 2);
        }
        this.ai[this.ae] = cVar;
        this.ae++;
    }

    private void f(c cVar) {
        int i = 0;
        while (i < this.af) {
            if (this.ah[i] != cVar) {
                i++;
            } else {
                return;
            }
        }
        if (this.af + 1 >= this.ah.length) {
            this.ah = (c[]) Arrays.copyOf(this.ah, this.ah.length * 2);
        }
        this.ah[this.af] = cVar;
        this.af++;
    }

    private int a(c cVar, int i, boolean[] zArr) {
        c cVar2;
        int i2;
        boolean z;
        c cVar3;
        int i3;
        c cVar4;
        boolean z2;
        c cVar5;
        zArr[0] = true;
        zArr[1] = false;
        if (i == 0) {
            if (cVar.i.c == null || cVar.i.c.a == this) {
                cVar5 = null;
                z2 = true;
                i3 = 0;
                cVar4 = cVar;
            } else {
                cVar5 = null;
                z2 = false;
                i3 = 0;
                cVar4 = cVar;
            }
            while (cVar4.k.c != null) {
                if (cVar4.d() != 8 && cVar4.C == a.MATCH_CONSTRAINT) {
                    if (cVar4.D == a.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (cVar4.s <= 0.0f) {
                        zArr[0] = false;
                        if (i3 + 1 >= this.ag.length) {
                            this.ag = (c[]) Arrays.copyOf(this.ag, this.ag.length * 2);
                        }
                        int i4 = i3 + 1;
                        this.ag[i3] = cVar4;
                        i3 = i4;
                    }
                }
                if (cVar4.k.c.a.i.c == null || cVar4.k.c.a.i.c.a != cVar4 || cVar4.k.c.a == cVar4) {
                    break;
                }
                c cVar6 = cVar4.k.c.a;
                cVar5 = cVar6;
                cVar4 = cVar6;
            }
            if (!(cVar4.k.c == null || cVar4.k.c.a == this)) {
                z2 = false;
            }
            if (cVar.i.c == null || cVar5.k.c == null) {
                zArr[1] = true;
            }
            cVar.P = z2;
        } else {
            if (cVar.j.c == null || cVar.j.c.a == this) {
                cVar3 = null;
                z = true;
                i2 = 0;
                cVar2 = cVar;
            } else {
                cVar3 = null;
                z = false;
                i2 = 0;
                cVar2 = cVar;
            }
            while (cVar2.l.c != null) {
                if (cVar2.d() != 8 && cVar2.D == a.MATCH_CONSTRAINT) {
                    if (cVar2.C == a.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (cVar2.s <= 0.0f) {
                        zArr[0] = false;
                        if (i2 + 1 >= this.ag.length) {
                            this.ag = (c[]) Arrays.copyOf(this.ag, this.ag.length * 2);
                        }
                        int i5 = i2 + 1;
                        this.ag[i2] = cVar2;
                        i2 = i5;
                    }
                }
                if (cVar2.l.c.a.j.c == null || cVar2.l.c.a.j.c.a != cVar2 || cVar2.l.c.a == cVar2) {
                    break;
                }
                c cVar7 = cVar2.l.c.a;
                cVar3 = cVar7;
                cVar2 = cVar7;
            }
            if (!(cVar2.l.c == null || cVar2.l.c.a == this)) {
                z = false;
            }
            if (cVar.j.c == null || cVar3.l.c == null) {
                zArr[1] = true;
            }
            cVar.Q = z;
        }
        return i3;
    }
}
