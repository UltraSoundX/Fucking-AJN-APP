package android.support.constraint.a.a;

import android.support.constraint.a.g;
import com.baidu.mobstat.Config;
import java.util.HashSet;

/* compiled from: ConstraintAnchor */
public class b {
    final c a;
    final c b;
    b c;
    int d = 0;
    int e = -1;
    g f;
    int g = Integer.MAX_VALUE;
    private C0004b h = C0004b.NONE;
    private a i = a.RELAXED;
    private int j = 0;

    /* compiled from: ConstraintAnchor */
    public enum a {
        RELAXED,
        STRICT
    }

    /* renamed from: android.support.constraint.a.a.b$b reason: collision with other inner class name */
    /* compiled from: ConstraintAnchor */
    public enum C0004b {
        NONE,
        STRONG,
        WEAK
    }

    /* compiled from: ConstraintAnchor */
    public enum c {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public b(c cVar, c cVar2) {
        this.a = cVar;
        this.b = cVar2;
    }

    public g a() {
        return this.f;
    }

    public void a(android.support.constraint.a.c cVar) {
        if (this.f == null) {
            this.f = new g(android.support.constraint.a.g.a.UNRESTRICTED);
        } else {
            this.f.c();
        }
    }

    public c b() {
        return this.a;
    }

    public c c() {
        return this.b;
    }

    public int d() {
        if (this.a.d() == 8) {
            return 0;
        }
        if (this.e <= -1 || this.c == null || this.c.a.d() != 8) {
            return this.d;
        }
        return this.e;
    }

    public C0004b e() {
        return this.h;
    }

    public b f() {
        return this.c;
    }

    public a g() {
        return this.i;
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public int h() {
        return this.j;
    }

    public void i() {
        this.c = null;
        this.d = 0;
        this.e = -1;
        this.h = C0004b.STRONG;
        this.j = 0;
        this.i = a.RELAXED;
    }

    public boolean a(b bVar, int i2, C0004b bVar2, int i3) {
        return a(bVar, i2, -1, bVar2, i3, false);
    }

    public boolean a(b bVar, int i2, int i3, C0004b bVar2, int i4, boolean z) {
        if (bVar == null) {
            this.c = null;
            this.d = 0;
            this.e = -1;
            this.h = C0004b.NONE;
            this.j = 2;
            return true;
        } else if (!z && !a(bVar)) {
            return false;
        } else {
            this.c = bVar;
            if (i2 > 0) {
                this.d = i2;
            } else {
                this.d = 0;
            }
            this.e = i3;
            this.h = bVar2;
            this.j = i4;
            return true;
        }
    }

    public boolean j() {
        return this.c != null;
    }

    public boolean a(b bVar) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (bVar == null) {
            return false;
        }
        c c2 = bVar.c();
        if (c2 != this.b) {
            switch (this.b) {
                case CENTER:
                    if (c2 == c.BASELINE || c2 == c.CENTER_X || c2 == c.CENTER_Y) {
                        z3 = false;
                    }
                    return z3;
                case LEFT:
                case RIGHT:
                    if (c2 == c.LEFT || c2 == c.RIGHT) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (bVar.b() instanceof e) {
                        if (z || c2 == c.CENTER_X) {
                            return true;
                        }
                        return false;
                    }
                    break;
                case TOP:
                case BOTTOM:
                    if (c2 == c.TOP || c2 == c.BOTTOM) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (bVar.b() instanceof e) {
                        if (z || c2 == c.CENTER_Y) {
                            return true;
                        }
                        return false;
                    }
                    break;
                default:
                    return false;
            }
            return z;
        } else if (this.b == c.CENTER) {
            return false;
        } else {
            if (this.b != c.BASELINE || (bVar.b().v() && b().v())) {
                return true;
            }
            return false;
        }
    }

    public String toString() {
        return this.a.e() + Config.TRACE_TODAY_VISIT_SPLIT + this.b.toString() + (this.c != null ? " connected to " + this.c.a(new HashSet<>()) : "");
    }

    private String a(HashSet<b> hashSet) {
        if (!hashSet.add(this)) {
            return "<-";
        }
        return this.a.e() + Config.TRACE_TODAY_VISIT_SPLIT + this.b.toString() + (this.c != null ? " connected to " + this.c.a(hashSet) : "");
    }
}
