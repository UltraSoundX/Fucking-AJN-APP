package android.support.constraint.a.a;

import android.support.constraint.a.a.c.a;
import android.support.constraint.a.e;
import android.support.constraint.a.g;

/* compiled from: Optimizer */
public class f {
    static void a(d dVar, e eVar, int i, c cVar) {
        float f;
        float f2;
        float l;
        int i2;
        int i3 = 0;
        int i4 = 0;
        float f3 = 0.0f;
        c cVar2 = null;
        c cVar3 = cVar;
        while (cVar3 != null) {
            if (!(cVar3.d() == 8)) {
                i2 = i4 + 1;
                if (cVar3.C != a.MATCH_CONSTRAINT) {
                    i3 = (cVar3.k.c != null ? cVar3.k.d() : 0) + i3 + cVar3.l() + (cVar3.i.c != null ? cVar3.i.d() : 0);
                } else {
                    f3 = cVar3.R + f3;
                }
            } else {
                i2 = i4;
            }
            c cVar4 = cVar3.k.c != null ? cVar3.k.c.a : null;
            if (cVar4 != null && (cVar4.i.c == null || !(cVar4.i.c == null || cVar4.i.c.a == cVar3))) {
                cVar4 = null;
            }
            cVar2 = cVar3;
            cVar3 = cVar4;
            i4 = i2;
        }
        int i5 = 0;
        if (cVar2 != null) {
            i5 = cVar2.k.c != null ? cVar2.k.c.a.j() : 0;
            if (cVar2.k.c != null && cVar2.k.c.a == dVar) {
                i5 = dVar.t();
            }
        }
        float f4 = ((float) (i5 - 0)) - ((float) i3);
        float f5 = f4 / ((float) (i4 + 1));
        if (i == 0) {
            f = f5;
        } else {
            f = f4 / ((float) i);
            f5 = 0.0f;
        }
        while (cVar != null) {
            int i6 = cVar.i.c != null ? cVar.i.d() : 0;
            int i7 = cVar.k.c != null ? cVar.k.d() : 0;
            if (cVar.d() != 8) {
                float f6 = f5 + ((float) i6);
                eVar.a(cVar.i.f, (int) (0.5f + f6));
                if (cVar.C != a.MATCH_CONSTRAINT) {
                    l = ((float) cVar.l()) + f6;
                } else if (f3 == 0.0f) {
                    l = ((f - ((float) i6)) - ((float) i7)) + f6;
                } else {
                    l = ((((cVar.R * f4) / f3) - ((float) i6)) - ((float) i7)) + f6;
                }
                eVar.a(cVar.k.f, (int) (0.5f + l));
                if (i == 0) {
                    l += f;
                }
                f2 = l + ((float) i7);
            } else {
                float f7 = f5 - (f / 2.0f);
                eVar.a(cVar.i.f, (int) (0.5f + f7));
                eVar.a(cVar.k.f, (int) (f7 + 0.5f));
                f2 = f5;
            }
            c cVar5 = cVar.k.c != null ? cVar.k.c.a : null;
            if (!(cVar5 == null || cVar5.i.c == null || cVar5.i.c.a == cVar)) {
                cVar5 = null;
            }
            if (cVar5 == dVar) {
                cVar5 = null;
            }
            f5 = f2;
            cVar = cVar5;
        }
    }

    static void b(d dVar, e eVar, int i, c cVar) {
        float f;
        float f2;
        float m;
        int i2;
        int i3 = 0;
        int i4 = 0;
        float f3 = 0.0f;
        c cVar2 = null;
        c cVar3 = cVar;
        while (cVar3 != null) {
            if (!(cVar3.d() == 8)) {
                i2 = i4 + 1;
                if (cVar3.D != a.MATCH_CONSTRAINT) {
                    i3 = (cVar3.l.c != null ? cVar3.l.d() : 0) + i3 + cVar3.m() + (cVar3.j.c != null ? cVar3.j.d() : 0);
                } else {
                    f3 = cVar3.S + f3;
                }
            } else {
                i2 = i4;
            }
            c cVar4 = cVar3.l.c != null ? cVar3.l.c.a : null;
            if (cVar4 != null && (cVar4.j.c == null || !(cVar4.j.c == null || cVar4.j.c.a == cVar3))) {
                cVar4 = null;
            }
            cVar2 = cVar3;
            cVar3 = cVar4;
            i4 = i2;
        }
        int i5 = 0;
        if (cVar2 != null) {
            i5 = cVar2.l.c != null ? cVar2.l.c.a.j() : 0;
            if (cVar2.l.c != null && cVar2.l.c.a == dVar) {
                i5 = dVar.u();
            }
        }
        float f4 = ((float) (i5 - 0)) - ((float) i3);
        float f5 = f4 / ((float) (i4 + 1));
        if (i == 0) {
            f = f5;
        } else {
            f = f4 / ((float) i);
            f5 = 0.0f;
        }
        while (cVar != null) {
            int i6 = cVar.j.c != null ? cVar.j.d() : 0;
            int i7 = cVar.l.c != null ? cVar.l.d() : 0;
            if (cVar.d() != 8) {
                float f6 = f5 + ((float) i6);
                eVar.a(cVar.j.f, (int) (0.5f + f6));
                if (cVar.D != a.MATCH_CONSTRAINT) {
                    m = ((float) cVar.m()) + f6;
                } else if (f3 == 0.0f) {
                    m = ((f - ((float) i6)) - ((float) i7)) + f6;
                } else {
                    m = ((((cVar.S * f4) / f3) - ((float) i6)) - ((float) i7)) + f6;
                }
                eVar.a(cVar.l.f, (int) (0.5f + m));
                if (i == 0) {
                    m += f;
                }
                f2 = m + ((float) i7);
            } else {
                float f7 = f5 - (f / 2.0f);
                eVar.a(cVar.j.f, (int) (0.5f + f7));
                eVar.a(cVar.l.f, (int) (f7 + 0.5f));
                f2 = f5;
            }
            c cVar5 = cVar.l.c != null ? cVar.l.c.a : null;
            if (!(cVar5 == null || cVar5.j.c == null || cVar5.j.c.a == cVar)) {
                cVar5 = null;
            }
            if (cVar5 == dVar) {
                cVar5 = null;
            }
            f5 = f2;
            cVar = cVar5;
        }
    }

    static void a(d dVar, e eVar, c cVar) {
        boolean z;
        float E;
        int l;
        if (cVar.C == a.MATCH_CONSTRAINT) {
            cVar.a = 1;
        } else if (cVar.i.c == null || cVar.k.c == null) {
            if (cVar.i.c != null && cVar.i.c.a == dVar) {
                int d = cVar.i.d();
                int l2 = cVar.l() + d;
                cVar.i.f = eVar.a((Object) cVar.i);
                cVar.k.f = eVar.a((Object) cVar.k);
                eVar.a(cVar.i.f, d);
                eVar.a(cVar.k.f, l2);
                cVar.a = 2;
                cVar.c(d, l2);
            } else if (cVar.k.c != null && cVar.k.c.a == dVar) {
                cVar.i.f = eVar.a((Object) cVar.i);
                cVar.k.f = eVar.a((Object) cVar.k);
                int l3 = dVar.l() - cVar.k.d();
                int l4 = l3 - cVar.l();
                eVar.a(cVar.i.f, l4);
                eVar.a(cVar.k.f, l3);
                cVar.a = 2;
                cVar.c(l4, l3);
            } else if (cVar.i.c != null && cVar.i.c.a.a == 2) {
                g gVar = cVar.i.c.f;
                cVar.i.f = eVar.a((Object) cVar.i);
                cVar.k.f = eVar.a((Object) cVar.k);
                int d2 = (int) (gVar.d + ((float) cVar.i.d()) + 0.5f);
                int l5 = cVar.l() + d2;
                eVar.a(cVar.i.f, d2);
                eVar.a(cVar.k.f, l5);
                cVar.a = 2;
                cVar.c(d2, l5);
            } else if (cVar.k.c == null || cVar.k.c.a.a != 2) {
                boolean z2 = cVar.i.c != null;
                if (cVar.k.c != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z2 && !z) {
                    if (cVar instanceof e) {
                        e eVar2 = (e) cVar;
                        if (eVar2.D() == 1) {
                            cVar.i.f = eVar.a((Object) cVar.i);
                            cVar.k.f = eVar.a((Object) cVar.k);
                            if (eVar2.F() != -1) {
                                E = (float) eVar2.F();
                            } else if (eVar2.G() != -1) {
                                E = (float) (dVar.l() - eVar2.G());
                            } else {
                                E = eVar2.E() * ((float) dVar.l());
                            }
                            int i = (int) (E + 0.5f);
                            eVar.a(cVar.i.f, i);
                            eVar.a(cVar.k.f, i);
                            cVar.a = 2;
                            cVar.b = 2;
                            cVar.c(i, i);
                            cVar.d(0, dVar.m());
                            return;
                        }
                        return;
                    }
                    cVar.i.f = eVar.a((Object) cVar.i);
                    cVar.k.f = eVar.a((Object) cVar.k);
                    int j = cVar.j();
                    int l6 = cVar.l() + j;
                    eVar.a(cVar.i.f, j);
                    eVar.a(cVar.k.f, l6);
                    cVar.a = 2;
                }
            } else {
                g gVar2 = cVar.k.c.f;
                cVar.i.f = eVar.a((Object) cVar.i);
                cVar.k.f = eVar.a((Object) cVar.k);
                int d3 = (int) ((gVar2.d - ((float) cVar.k.d())) + 0.5f);
                int l7 = d3 - cVar.l();
                eVar.a(cVar.i.f, l7);
                eVar.a(cVar.k.f, d3);
                cVar.a = 2;
                cVar.c(l7, d3);
            }
        } else if (cVar.i.c.a == dVar && cVar.k.c.a == dVar) {
            int d4 = cVar.i.d();
            int d5 = cVar.k.d();
            if (dVar.C == a.MATCH_CONSTRAINT) {
                l = dVar.l() - d5;
            } else {
                d4 += (int) ((((float) (((dVar.l() - d4) - d5) - cVar.l())) * cVar.A) + 0.5f);
                l = cVar.l() + d4;
            }
            cVar.i.f = eVar.a((Object) cVar.i);
            cVar.k.f = eVar.a((Object) cVar.k);
            eVar.a(cVar.i.f, d4);
            eVar.a(cVar.k.f, l);
            cVar.a = 2;
            cVar.c(d4, l);
        } else {
            cVar.a = 1;
        }
    }

    static void b(d dVar, e eVar, c cVar) {
        boolean z;
        float E;
        int m;
        boolean z2 = true;
        if (cVar.D == a.MATCH_CONSTRAINT) {
            cVar.b = 1;
        } else if (cVar.j.c == null || cVar.l.c == null) {
            if (cVar.j.c != null && cVar.j.c.a == dVar) {
                int d = cVar.j.d();
                int m2 = cVar.m() + d;
                cVar.j.f = eVar.a((Object) cVar.j);
                cVar.l.f = eVar.a((Object) cVar.l);
                eVar.a(cVar.j.f, d);
                eVar.a(cVar.l.f, m2);
                if (cVar.y > 0) {
                    cVar.m.f = eVar.a((Object) cVar.m);
                    eVar.a(cVar.m.f, cVar.y + d);
                }
                cVar.b = 2;
                cVar.d(d, m2);
            } else if (cVar.l.c != null && cVar.l.c.a == dVar) {
                cVar.j.f = eVar.a((Object) cVar.j);
                cVar.l.f = eVar.a((Object) cVar.l);
                int m3 = dVar.m() - cVar.l.d();
                int m4 = m3 - cVar.m();
                eVar.a(cVar.j.f, m4);
                eVar.a(cVar.l.f, m3);
                if (cVar.y > 0) {
                    cVar.m.f = eVar.a((Object) cVar.m);
                    eVar.a(cVar.m.f, cVar.y + m4);
                }
                cVar.b = 2;
                cVar.d(m4, m3);
            } else if (cVar.j.c != null && cVar.j.c.a.b == 2) {
                g gVar = cVar.j.c.f;
                cVar.j.f = eVar.a((Object) cVar.j);
                cVar.l.f = eVar.a((Object) cVar.l);
                int d2 = (int) (gVar.d + ((float) cVar.j.d()) + 0.5f);
                int m5 = cVar.m() + d2;
                eVar.a(cVar.j.f, d2);
                eVar.a(cVar.l.f, m5);
                if (cVar.y > 0) {
                    cVar.m.f = eVar.a((Object) cVar.m);
                    eVar.a(cVar.m.f, cVar.y + d2);
                }
                cVar.b = 2;
                cVar.d(d2, m5);
            } else if (cVar.l.c != null && cVar.l.c.a.b == 2) {
                g gVar2 = cVar.l.c.f;
                cVar.j.f = eVar.a((Object) cVar.j);
                cVar.l.f = eVar.a((Object) cVar.l);
                int d3 = (int) ((gVar2.d - ((float) cVar.l.d())) + 0.5f);
                int m6 = d3 - cVar.m();
                eVar.a(cVar.j.f, m6);
                eVar.a(cVar.l.f, d3);
                if (cVar.y > 0) {
                    cVar.m.f = eVar.a((Object) cVar.m);
                    eVar.a(cVar.m.f, cVar.y + m6);
                }
                cVar.b = 2;
                cVar.d(m6, d3);
            } else if (cVar.m.c == null || cVar.m.c.a.b != 2) {
                boolean z3 = cVar.m.c != null;
                if (cVar.j.c != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (cVar.l.c == null) {
                    z2 = false;
                }
                if (!z3 && !z && !z2) {
                    if (cVar instanceof e) {
                        e eVar2 = (e) cVar;
                        if (eVar2.D() == 0) {
                            cVar.j.f = eVar.a((Object) cVar.j);
                            cVar.l.f = eVar.a((Object) cVar.l);
                            if (eVar2.F() != -1) {
                                E = (float) eVar2.F();
                            } else if (eVar2.G() != -1) {
                                E = (float) (dVar.m() - eVar2.G());
                            } else {
                                E = eVar2.E() * ((float) dVar.m());
                            }
                            int i = (int) (E + 0.5f);
                            eVar.a(cVar.j.f, i);
                            eVar.a(cVar.l.f, i);
                            cVar.b = 2;
                            cVar.a = 2;
                            cVar.d(i, i);
                            cVar.c(0, dVar.l());
                            return;
                        }
                        return;
                    }
                    cVar.j.f = eVar.a((Object) cVar.j);
                    cVar.l.f = eVar.a((Object) cVar.l);
                    int k = cVar.k();
                    int m7 = cVar.m() + k;
                    eVar.a(cVar.j.f, k);
                    eVar.a(cVar.l.f, m7);
                    if (cVar.y > 0) {
                        cVar.m.f = eVar.a((Object) cVar.m);
                        eVar.a(cVar.m.f, k + cVar.y);
                    }
                    cVar.b = 2;
                }
            } else {
                g gVar3 = cVar.m.c.f;
                cVar.j.f = eVar.a((Object) cVar.j);
                cVar.l.f = eVar.a((Object) cVar.l);
                int i2 = (int) ((gVar3.d - ((float) cVar.y)) + 0.5f);
                int m8 = cVar.m() + i2;
                eVar.a(cVar.j.f, i2);
                eVar.a(cVar.l.f, m8);
                cVar.m.f = eVar.a((Object) cVar.m);
                eVar.a(cVar.m.f, cVar.y + i2);
                cVar.b = 2;
                cVar.d(i2, m8);
            }
        } else if (cVar.j.c.a == dVar && cVar.l.c.a == dVar) {
            int d4 = cVar.j.d();
            int d5 = cVar.l.d();
            if (dVar.D == a.MATCH_CONSTRAINT) {
                m = cVar.m() + d4;
            } else {
                d4 = (int) ((((float) (((dVar.m() - d4) - d5) - cVar.m())) * cVar.B) + ((float) d4) + 0.5f);
                m = cVar.m() + d4;
            }
            cVar.j.f = eVar.a((Object) cVar.j);
            cVar.l.f = eVar.a((Object) cVar.l);
            eVar.a(cVar.j.f, d4);
            eVar.a(cVar.l.f, m);
            if (cVar.y > 0) {
                cVar.m.f = eVar.a((Object) cVar.m);
                eVar.a(cVar.m.f, cVar.y + d4);
            }
            cVar.b = 2;
            cVar.d(d4, m);
        } else {
            cVar.b = 1;
        }
    }
}
