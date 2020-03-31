package android.support.constraint.a.a;

import android.support.constraint.a.a.b.c;
import java.util.ArrayList;

/* compiled from: Guideline */
public class e extends c {
    protected float T = -1.0f;
    protected int U = -1;
    protected int V = -1;
    private b W = this.j;
    private int X = 0;
    private boolean Y = false;
    private int Z = 0;
    private g aa = new g();
    private int ab = 8;

    public e() {
        this.f315q.clear();
        this.f315q.add(this.W);
    }

    public void k(int i) {
        if (this.X != i) {
            this.X = i;
            this.f315q.clear();
            if (this.X == 1) {
                this.W = this.i;
            } else {
                this.W = this.j;
            }
            this.f315q.add(this.W);
        }
    }

    public int D() {
        return this.X;
    }

    public b a(c cVar) {
        switch (cVar) {
            case LEFT:
            case RIGHT:
                if (this.X == 1) {
                    return this.W;
                }
                break;
            case TOP:
            case BOTTOM:
                if (this.X == 0) {
                    return this.W;
                }
                break;
        }
        return null;
    }

    public ArrayList<b> y() {
        return this.f315q;
    }

    public void e(float f) {
        if (f > -1.0f) {
            this.T = f;
            this.U = -1;
            this.V = -1;
        }
    }

    public void l(int i) {
        if (i > -1) {
            this.T = -1.0f;
            this.U = i;
            this.V = -1;
        }
    }

    public void m(int i) {
        if (i > -1) {
            this.T = -1.0f;
            this.U = -1;
            this.V = i;
        }
    }

    public float E() {
        return this.T;
    }

    public int F() {
        return this.U;
    }

    public int G() {
        return this.V;
    }

    public void a(android.support.constraint.a.e eVar, int i) {
        b bVar;
        d dVar = (d) c();
        if (dVar != null) {
            b a = dVar.a(c.LEFT);
            b a2 = dVar.a(c.RIGHT);
            if (this.X == 0) {
                b a3 = dVar.a(c.TOP);
                bVar = dVar.a(c.BOTTOM);
                a = a3;
            } else {
                bVar = a2;
            }
            if (this.U != -1) {
                eVar.a(android.support.constraint.a.e.a(eVar, eVar.a((Object) this.W), eVar.a((Object) a), this.U, false));
            } else if (this.V != -1) {
                eVar.a(android.support.constraint.a.e.a(eVar, eVar.a((Object) this.W), eVar.a((Object) bVar), -this.V, false));
            } else if (this.T != -1.0f) {
                eVar.a(android.support.constraint.a.e.a(eVar, eVar.a((Object) this.W), eVar.a((Object) a), eVar.a((Object) bVar), this.T, this.Y));
                if (this.Z > 0) {
                }
            }
        }
    }

    public void b(android.support.constraint.a.e eVar, int i) {
        if (c() != null) {
            int b = eVar.b((Object) this.W);
            if (this.X == 1) {
                b(b);
                c(0);
                e(c().m());
                d(0);
                return;
            }
            b(0);
            c(b);
            d(c().l());
            e(0);
        }
    }
}
