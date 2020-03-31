package cn.sharesdk.framework.utils.QRCodeUtil;

/* compiled from: GenericGFPoly */
final class h {
    private final g a;
    private final int[] b;

    h(g gVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = gVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        int i = 1;
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.b = new int[]{0};
            return;
        }
        this.b = new int[(length - i)];
        System.arraycopy(iArr, i, this.b, 0, this.b.length);
    }

    /* access modifiers changed from: 0000 */
    public int[] a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.b.length - 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.b[0] == 0;
    }

    /* access modifiers changed from: 0000 */
    public int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    /* access modifiers changed from: 0000 */
    public h a(h hVar) {
        if (!this.a.equals(hVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c()) {
            return hVar;
        } else {
            if (hVar.c()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = hVar.b;
            if (iArr.length <= iArr2.length) {
                int[] iArr3 = iArr2;
                iArr2 = iArr;
                iArr = iArr3;
            }
            int[] iArr4 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr4, 0, length);
            for (int i = length; i < iArr.length; i++) {
                iArr4[i] = g.b(iArr2[i - length], iArr[i]);
            }
            return new h(this.a, iArr4);
        }
    }

    /* access modifiers changed from: 0000 */
    public h b(h hVar) {
        if (!this.a.equals(hVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c() || hVar.c()) {
            return this.a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = hVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    iArr3[i + i3] = g.b(iArr3[i + i3], this.a.c(i2, iArr2[i3]));
                }
            }
            return new h(this.a, iArr3);
        }
    }

    /* access modifiers changed from: 0000 */
    public h a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(length + i)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.a.c(this.b[i3], i2);
            }
            return new h(this.a, iArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public h[] c(h hVar) {
        if (!this.a.equals(hVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (hVar.c()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            h a2 = this.a.a();
            int c = this.a.c(hVar.a(hVar.b()));
            h hVar2 = a2;
            h hVar3 = this;
            while (hVar3.b() >= hVar.b() && !hVar3.c()) {
                int b2 = hVar3.b() - hVar.b();
                int c2 = this.a.c(hVar3.a(hVar3.b()), c);
                h a3 = hVar.a(b2, c2);
                hVar2 = hVar2.a(this.a.a(b2, c2));
                hVar3 = hVar3.a(a3);
            }
            return new h[]{hVar2, hVar3};
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(b() * 8);
        for (int b2 = b(); b2 >= 0; b2--) {
            int a2 = a(b2);
            if (a2 != 0) {
                if (a2 < 0) {
                    sb.append(" - ");
                    a2 = -a2;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b2 == 0 || a2 != 1) {
                    int b3 = this.a.b(a2);
                    if (b3 == 0) {
                        sb.append('1');
                    } else if (b3 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(b3);
                    }
                }
                if (b2 != 0) {
                    if (b2 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b2);
                    }
                }
            }
        }
        return sb.toString();
    }
}
