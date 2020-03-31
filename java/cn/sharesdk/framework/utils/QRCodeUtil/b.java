package cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.Arrays;

/* compiled from: BitArray */
public final class b implements Cloneable {
    private int[] a;
    private int b;

    public b() {
        this.b = 0;
        this.a = new int[1];
    }

    b(int[] iArr, int i) {
        this.a = iArr;
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return (this.b + 7) / 8;
    }

    private void b(int i) {
        if (i > this.a.length * 32) {
            int[] c = c(i);
            System.arraycopy(this.a, 0, c, 0, this.a.length);
            this.a = c;
        }
    }

    public boolean a(int i) {
        return (this.a[i / 32] & (1 << (i & 31))) != 0;
    }

    public void a(boolean z) {
        b(this.b + 1);
        if (z) {
            int[] iArr = this.a;
            int i = this.b / 32;
            iArr[i] = iArr[i] | (1 << (this.b & 31));
        }
        this.b++;
    }

    public void a(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        b(this.b + i2);
        while (i2 > 0) {
            a(((i >> (i2 + -1)) & 1) == 1);
            i2--;
        }
    }

    public void a(b bVar) {
        int i = bVar.b;
        b(this.b + i);
        for (int i2 = 0; i2 < i; i2++) {
            a(bVar.a(i2));
        }
    }

    public void b(b bVar) {
        if (this.a.length != bVar.a.length) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i = 0; i < this.a.length; i++) {
            int[] iArr = this.a;
            iArr[i] = iArr[i] ^ bVar.a[i];
        }
    }

    public void a(int i, byte[] bArr, int i2, int i3) {
        int i4 = 0;
        int i5 = i;
        while (i4 < i3) {
            int i6 = i5;
            int i7 = 0;
            for (int i8 = 0; i8 < 8; i8++) {
                if (a(i6)) {
                    i7 |= 1 << (7 - i8);
                }
                i6++;
            }
            bArr[i2 + i4] = (byte) i7;
            i4++;
            i5 = i6;
        }
    }

    private static int[] c(int i) {
        return new int[((i + 31) / 32)];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.b != bVar.b || !Arrays.equals(this.a, bVar.a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(a(i) ? 'X' : '.');
        }
        return sb.toString();
    }

    /* renamed from: c */
    public b clone() {
        return new b((int[]) this.a.clone(), this.b);
    }
}
