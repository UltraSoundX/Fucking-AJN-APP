package cn.sharesdk.framework.utils.QRCodeUtil.a;

import java.lang.reflect.Array;
import java.util.Arrays;

/* compiled from: ByteMatrix */
public final class b {
    private final byte[][] a;
    private final int b;
    private final int c;

    public b(int i, int i2) {
        this.a = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i2, i});
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public byte a(int i, int i2) {
        return this.a[i2][i];
    }

    public byte[][] c() {
        return this.a;
    }

    public void a(int i, int i2, int i3) {
        this.a[i2][i] = (byte) i3;
    }

    public void a(int i, int i2, boolean z) {
        this.a[i2][i] = (byte) (z ? 1 : 0);
    }

    public void a(byte b2) {
        for (byte[] fill : this.a) {
            Arrays.fill(fill, b2);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.b * 2 * this.c) + 2);
        for (int i = 0; i < this.c; i++) {
            byte[] bArr = this.a[i];
            for (int i2 = 0; i2 < this.b; i2++) {
                switch (bArr[i2]) {
                    case 0:
                        sb.append(" 0");
                        break;
                    case 1:
                        sb.append(" 1");
                        break;
                    default:
                        sb.append("  ");
                        break;
                }
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
