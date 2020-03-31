package android.support.constraint.a;

import java.util.Arrays;

/* compiled from: SolverVariable */
public class g {
    private static int i = 1;
    public int a = -1;
    int b = -1;
    public int c = 0;
    public float d;
    float[] e = new float[6];
    a f;
    b[] g = new b[8];
    int h = 0;
    private String j;

    /* compiled from: SolverVariable */
    public enum a {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public g(a aVar) {
        this.f = aVar;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.e[i2] = 0.0f;
        }
    }

    /* access modifiers changed from: 0000 */
    public String b() {
        String str = this + "[";
        for (int i2 = 0; i2 < this.e.length; i2++) {
            String str2 = str + this.e[i2];
            if (i2 < this.e.length - 1) {
                str = str2 + ", ";
            } else {
                str = str2 + "] ";
            }
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        int i2 = 0;
        while (i2 < this.h) {
            if (this.g[i2] != bVar) {
                i2++;
            } else {
                return;
            }
        }
        if (this.h >= this.g.length) {
            this.g = (b[]) Arrays.copyOf(this.g, this.g.length * 2);
        }
        this.g[this.h] = bVar;
        this.h++;
    }

    /* access modifiers changed from: 0000 */
    public void b(b bVar) {
        for (int i2 = 0; i2 < this.h; i2++) {
            if (this.g[i2] == bVar) {
                for (int i3 = 0; i3 < (this.h - i2) - 1; i3++) {
                    this.g[i2 + i3] = this.g[i2 + i3 + 1];
                }
                this.h--;
                return;
            }
        }
    }

    public void c() {
        this.j = null;
        this.f = a.UNKNOWN;
        this.c = 0;
        this.a = -1;
        this.b = -1;
        this.d = 0.0f;
        this.h = 0;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public String toString() {
        return "" + this.j;
    }
}
