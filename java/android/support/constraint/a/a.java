package android.support.constraint.a;

import java.util.Arrays;

/* compiled from: ArrayLinkedVariables */
public class a {
    int a = 0;
    private final b b;
    private final c c;
    private int d = 8;
    private g e = null;
    private int[] f = new int[this.d];
    private int[] g = new int[this.d];
    private float[] h = new float[this.d];
    private int i = -1;
    private int j = -1;
    private boolean k = false;

    a(b bVar, c cVar) {
        this.b = bVar;
        this.c = cVar;
    }

    public final void a(g gVar, float f2) {
        if (f2 == 0.0f) {
            a(gVar);
        } else if (this.i == -1) {
            this.i = 0;
            this.h[this.i] = f2;
            this.f[this.i] = gVar.a;
            this.g[this.i] = -1;
            this.a++;
            if (!this.k) {
                this.j++;
            }
        } else {
            int i2 = this.i;
            int i3 = 0;
            int i4 = -1;
            while (i2 != -1 && i3 < this.a) {
                if (this.f[i2] == gVar.a) {
                    this.h[i2] = f2;
                    return;
                }
                if (this.f[i2] < gVar.a) {
                    i4 = i2;
                }
                i3++;
                i2 = this.g[i2];
            }
            int i5 = this.j + 1;
            if (this.k) {
                if (this.f[this.j] == -1) {
                    i5 = this.j;
                } else {
                    i5 = this.f.length;
                }
            }
            if (i5 >= this.f.length && this.a < this.f.length) {
                int i6 = 0;
                while (true) {
                    if (i6 >= this.f.length) {
                        break;
                    } else if (this.f[i6] == -1) {
                        i5 = i6;
                        break;
                    } else {
                        i6++;
                    }
                }
            }
            if (i5 >= this.f.length) {
                i5 = this.f.length;
                this.d *= 2;
                this.k = false;
                this.j = i5 - 1;
                this.h = Arrays.copyOf(this.h, this.d);
                this.f = Arrays.copyOf(this.f, this.d);
                this.g = Arrays.copyOf(this.g, this.d);
            }
            this.f[i5] = gVar.a;
            this.h[i5] = f2;
            if (i4 != -1) {
                this.g[i5] = this.g[i4];
                this.g[i4] = i5;
            } else {
                this.g[i5] = this.i;
                this.i = i5;
            }
            this.a++;
            if (!this.k) {
                this.j++;
            }
            if (this.a >= this.f.length) {
                this.k = true;
            }
        }
    }

    public final void b(g gVar, float f2) {
        if (f2 != 0.0f) {
            if (this.i == -1) {
                this.i = 0;
                this.h[this.i] = f2;
                this.f[this.i] = gVar.a;
                this.g[this.i] = -1;
                this.a++;
                if (!this.k) {
                    this.j++;
                    return;
                }
                return;
            }
            int i2 = this.i;
            int i3 = 0;
            int i4 = -1;
            while (i2 != -1 && i3 < this.a) {
                int i5 = this.f[i2];
                if (i5 == gVar.a) {
                    float[] fArr = this.h;
                    fArr[i2] = fArr[i2] + f2;
                    if (this.h[i2] == 0.0f) {
                        if (i2 == this.i) {
                            this.i = this.g[i2];
                        } else {
                            this.g[i4] = this.g[i2];
                        }
                        this.c.c[i5].b(this.b);
                        if (this.k) {
                            this.j = i2;
                        }
                        this.a--;
                        return;
                    }
                    return;
                }
                if (this.f[i2] < gVar.a) {
                    i4 = i2;
                }
                i3++;
                i2 = this.g[i2];
            }
            int i6 = this.j + 1;
            if (this.k) {
                if (this.f[this.j] == -1) {
                    i6 = this.j;
                } else {
                    i6 = this.f.length;
                }
            }
            if (i6 >= this.f.length && this.a < this.f.length) {
                int i7 = 0;
                while (true) {
                    if (i7 >= this.f.length) {
                        break;
                    } else if (this.f[i7] == -1) {
                        i6 = i7;
                        break;
                    } else {
                        i7++;
                    }
                }
            }
            if (i6 >= this.f.length) {
                i6 = this.f.length;
                this.d *= 2;
                this.k = false;
                this.j = i6 - 1;
                this.h = Arrays.copyOf(this.h, this.d);
                this.f = Arrays.copyOf(this.f, this.d);
                this.g = Arrays.copyOf(this.g, this.d);
            }
            this.f[i6] = gVar.a;
            this.h[i6] = f2;
            if (i4 != -1) {
                this.g[i6] = this.g[i4];
                this.g[i4] = i6;
            } else {
                this.g[i6] = this.i;
                this.i = i6;
            }
            this.a++;
            if (!this.k) {
                this.j++;
            }
            if (this.j >= this.f.length) {
                this.k = true;
                this.j = this.f.length - 1;
            }
        }
    }

    public final float a(g gVar) {
        if (this.e == gVar) {
            this.e = null;
        }
        if (this.i == -1) {
            return 0.0f;
        }
        int i2 = this.i;
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.a) {
            int i5 = this.f[i2];
            if (i5 == gVar.a) {
                if (i2 == this.i) {
                    this.i = this.g[i2];
                } else {
                    this.g[i4] = this.g[i2];
                }
                this.c.c[i5].b(this.b);
                this.a--;
                this.f[i2] = -1;
                if (this.k) {
                    this.j = i2;
                }
                return this.h[i2];
            }
            i3++;
            int i6 = i2;
            i2 = this.g[i2];
            i4 = i6;
        }
        return 0.0f;
    }

    public final void a() {
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.a = 0;
    }

    /* access modifiers changed from: 0000 */
    public final boolean b(g gVar) {
        if (this.i == -1) {
            return false;
        }
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            if (this.f[i2] == gVar.a) {
                return true;
            }
            i2 = this.g[i2];
            i3++;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            float[] fArr = this.h;
            fArr[i2] = fArr[i2] * -1.0f;
            i2 = this.g[i2];
            i3++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(float f2) {
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            float[] fArr = this.h;
            fArr[i2] = fArr[i2] / f2;
            i2 = this.g[i2];
            i3++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            this.c.c[this.f[i2]].a(bVar);
            i2 = this.g[i2];
            i3++;
        }
    }

    /* access modifiers changed from: 0000 */
    public g c() {
        float f2;
        g gVar;
        g gVar2;
        g gVar3 = null;
        int i2 = 0;
        int i3 = this.i;
        g gVar4 = null;
        while (i3 != -1 && i2 < this.a) {
            float f3 = this.h[i3];
            if (f3 < 0.0f) {
                if (f3 > (-0.001f)) {
                    this.h[i3] = 0.0f;
                    f2 = 0.0f;
                }
                f2 = f3;
            } else {
                if (f3 < 0.001f) {
                    this.h[i3] = 0.0f;
                    f2 = 0.0f;
                }
                f2 = f3;
            }
            if (f2 != 0.0f) {
                gVar = this.c.c[this.f[i3]];
                if (gVar.f == android.support.constraint.a.g.a.UNRESTRICTED) {
                    if (f2 < 0.0f) {
                        return gVar;
                    }
                    if (gVar4 == null) {
                        gVar2 = gVar3;
                        i2++;
                        i3 = this.g[i3];
                        gVar3 = gVar2;
                        gVar4 = gVar;
                    }
                } else if (f2 < 0.0f && (gVar3 == null || gVar.c < gVar3.c)) {
                    g gVar5 = gVar4;
                    gVar2 = gVar;
                    gVar = gVar5;
                    i2++;
                    i3 = this.g[i3];
                    gVar3 = gVar2;
                    gVar4 = gVar;
                }
            }
            gVar = gVar4;
            gVar2 = gVar3;
            i2++;
            i3 = this.g[i3];
            gVar3 = gVar2;
            gVar4 = gVar;
        }
        if (gVar4 != null) {
            return gVar4;
        }
        return gVar3;
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar, b bVar2) {
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            if (this.f[i2] == bVar2.a.a) {
                float f2 = this.h[i2];
                a(bVar2.a);
                a aVar = bVar2.d;
                int i4 = aVar.i;
                int i5 = 0;
                while (i4 != -1 && i5 < aVar.a) {
                    b(this.c.c[aVar.f[i4]], aVar.h[i4] * f2);
                    i4 = aVar.g[i4];
                    i5++;
                }
                bVar.b += bVar2.b * f2;
                bVar2.a.b(bVar);
                i2 = this.i;
                i3 = 0;
            } else {
                i2 = this.g[i2];
                i3++;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar, b[] bVarArr) {
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            g gVar = this.c.c[this.f[i2]];
            if (gVar.b != -1) {
                float f2 = this.h[i2];
                a(gVar);
                b bVar2 = bVarArr[gVar.b];
                if (!bVar2.e) {
                    a aVar = bVar2.d;
                    int i4 = aVar.i;
                    int i5 = 0;
                    while (i4 != -1 && i5 < aVar.a) {
                        b(this.c.c[aVar.f[i4]], aVar.h[i4] * f2);
                        i4 = aVar.g[i4];
                        i5++;
                    }
                }
                bVar.b += bVar2.b * f2;
                bVar2.a.b(bVar);
                i2 = this.i;
                i3 = 0;
            } else {
                i2 = this.g[i2];
                i3++;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final g a(int i2) {
        int i3 = this.i;
        int i4 = 0;
        while (i3 != -1 && i4 < this.a) {
            if (i4 == i2) {
                return this.c.c[this.f[i3]];
            }
            i3 = this.g[i3];
            i4++;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final float b(int i2) {
        int i3 = this.i;
        int i4 = 0;
        while (i3 != -1 && i4 < this.a) {
            if (i4 == i2) {
                return this.h[i3];
            }
            i3 = this.g[i3];
            i4++;
        }
        return 0.0f;
    }

    public final float c(g gVar) {
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            if (this.f[i2] == gVar.a) {
                return this.h[i2];
            }
            i2 = this.g[i2];
            i3++;
        }
        return 0.0f;
    }

    public String toString() {
        String str = "";
        int i2 = this.i;
        int i3 = 0;
        while (i2 != -1 && i3 < this.a) {
            str = ((str + " -> ") + this.h[i2] + " : ") + this.c.c[this.f[i2]];
            i2 = this.g[i2];
            i3++;
        }
        return str;
    }
}
