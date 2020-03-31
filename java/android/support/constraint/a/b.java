package android.support.constraint.a;

import android.support.constraint.a.g.a;

/* compiled from: ArrayRow */
public class b {
    g a = null;
    float b = 0.0f;
    boolean c = false;
    final a d;
    boolean e = false;

    public b(c cVar) {
        this.d = new a(this, cVar);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.d.a(this);
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        return this.a != null && (this.a.f == a.UNRESTRICTED || this.b >= 0.0f);
    }

    public String toString() {
        return c();
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        String str;
        String str2;
        boolean z;
        float f;
        String str3;
        String str4 = "";
        if (this.a == null) {
            str = str4 + "0";
        } else {
            str = str4 + this.a;
        }
        String str5 = str + " = ";
        if (this.b != 0.0f) {
            str2 = str5 + this.b;
            z = true;
        } else {
            str2 = str5;
            z = false;
        }
        int i = this.d.a;
        String str6 = str2;
        for (int i2 = 0; i2 < i; i2++) {
            g a2 = this.d.a(i2);
            if (a2 != null) {
                float b2 = this.d.b(i2);
                String gVar = a2.toString();
                if (!z) {
                    if (b2 < 0.0f) {
                        str6 = str6 + "- ";
                        f = b2 * -1.0f;
                    } else {
                        f = b2;
                    }
                } else if (b2 > 0.0f) {
                    str6 = str6 + " + ";
                    f = b2;
                } else {
                    str6 = str6 + " - ";
                    f = b2 * -1.0f;
                }
                if (f == 1.0f) {
                    str3 = str6 + gVar;
                } else {
                    str3 = str6 + f + " " + gVar;
                }
                str6 = str3;
                z = true;
            }
        }
        if (!z) {
            return str6 + "0.0";
        }
        return str6;
    }

    public void d() {
        this.a = null;
        this.d.a();
        this.b = 0.0f;
        this.e = false;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(g gVar) {
        return this.d.b(gVar);
    }

    /* access modifiers changed from: 0000 */
    public b a(g gVar, int i) {
        this.a = gVar;
        gVar.d = (float) i;
        this.b = (float) i;
        this.e = true;
        return this;
    }

    public b b(g gVar, int i) {
        if (i < 0) {
            this.b = (float) (i * -1);
            this.d.a(gVar, 1.0f);
        } else {
            this.b = (float) i;
            this.d.a(gVar, -1.0f);
        }
        return this;
    }

    public b a(g gVar, g gVar2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = (float) i;
        }
        if (!z) {
            this.d.a(gVar, -1.0f);
            this.d.a(gVar2, 1.0f);
        } else {
            this.d.a(gVar, 1.0f);
            this.d.a(gVar2, -1.0f);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public b c(g gVar, int i) {
        this.d.a(gVar, (float) i);
        return this;
    }

    public b a(g gVar, g gVar2, g gVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = (float) i;
        }
        if (!z) {
            this.d.a(gVar, -1.0f);
            this.d.a(gVar2, 1.0f);
            this.d.a(gVar3, 1.0f);
        } else {
            this.d.a(gVar, 1.0f);
            this.d.a(gVar2, -1.0f);
            this.d.a(gVar3, -1.0f);
        }
        return this;
    }

    public b b(g gVar, g gVar2, g gVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = (float) i;
        }
        if (!z) {
            this.d.a(gVar, -1.0f);
            this.d.a(gVar2, 1.0f);
            this.d.a(gVar3, -1.0f);
        } else {
            this.d.a(gVar, 1.0f);
            this.d.a(gVar2, -1.0f);
            this.d.a(gVar3, 1.0f);
        }
        return this;
    }

    public b a(float f, float f2, float f3, g gVar, int i, g gVar2, int i2, g gVar3, int i3, g gVar4, int i4) {
        if (f2 == 0.0f || f == f3) {
            this.b = (float) (((-i) - i2) + i3 + i4);
            this.d.a(gVar, 1.0f);
            this.d.a(gVar2, -1.0f);
            this.d.a(gVar4, 1.0f);
            this.d.a(gVar3, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.b = ((float) ((-i) - i2)) + (((float) i3) * f4) + (((float) i4) * f4);
            this.d.a(gVar, 1.0f);
            this.d.a(gVar2, -1.0f);
            this.d.a(gVar4, f4);
            this.d.a(gVar3, -f4);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public b a(g gVar, g gVar2, int i, float f, g gVar3, g gVar4, int i2) {
        if (gVar2 == gVar3) {
            this.d.a(gVar, 1.0f);
            this.d.a(gVar4, 1.0f);
            this.d.a(gVar2, -2.0f);
        } else if (f == 0.5f) {
            this.d.a(gVar, 1.0f);
            this.d.a(gVar2, -1.0f);
            this.d.a(gVar3, -1.0f);
            this.d.a(gVar4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.b = (float) ((-i) + i2);
            }
        } else if (f <= 0.0f) {
            this.d.a(gVar, -1.0f);
            this.d.a(gVar2, 1.0f);
            this.b = (float) i;
        } else if (f >= 1.0f) {
            this.d.a(gVar3, -1.0f);
            this.d.a(gVar4, 1.0f);
            this.b = (float) i2;
        } else {
            this.d.a(gVar, (1.0f - f) * 1.0f);
            this.d.a(gVar2, (1.0f - f) * -1.0f);
            this.d.a(gVar3, -1.0f * f);
            this.d.a(gVar4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.b = (((float) (-i)) * (1.0f - f)) + (((float) i2) * f);
            }
        }
        return this;
    }

    public b a(g gVar, g gVar2) {
        this.d.a(gVar, 1.0f);
        this.d.a(gVar2, -1.0f);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public b a(g gVar, g gVar2, g gVar3, float f) {
        this.d.a(gVar, -1.0f);
        this.d.a(gVar2, 1.0f - f);
        this.d.a(gVar3, f);
        return this;
    }

    public b a(g gVar, g gVar2, g gVar3, g gVar4, float f) {
        this.d.a(gVar, -1.0f);
        this.d.a(gVar2, 1.0f);
        this.d.a(gVar3, f);
        this.d.a(gVar4, -f);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(b bVar) {
        this.d.a(this, bVar);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        if (this.b < 0.0f) {
            this.b *= -1.0f;
            this.d.b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        g c2 = this.d.c();
        if (c2 != null) {
            b(c2);
        }
        if (this.d.a == 0) {
            this.e = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(g gVar) {
        if (this.a != null) {
            this.d.a(this.a, -1.0f);
            this.a = null;
        }
        float a2 = this.d.a(gVar) * -1.0f;
        this.a = gVar;
        if (a2 != 1.0f) {
            this.b /= a2;
            this.d.a(a2);
        }
    }
}
