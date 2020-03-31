package com.bumptech.glide.c;

import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: LZWEncoder */
class b {
    int a;
    int b = 12;
    int c;
    int d = 4096;
    int[] e = new int[ReaderCallback.COPY_SELECT_TEXT];
    int[] f = new int[ReaderCallback.COPY_SELECT_TEXT];
    int g = ReaderCallback.COPY_SELECT_TEXT;
    int h = 0;
    boolean i = false;
    int j;
    int k;
    int l;
    int m = 0;
    int n = 0;
    int[] o = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
    int p;

    /* renamed from: q reason: collision with root package name */
    byte[] f390q = new byte[256];
    private int r;
    private int s;
    private byte[] t;
    private int u;
    private int v;
    private int w;

    b(int i2, int i3, byte[] bArr, int i4) {
        this.r = i2;
        this.s = i3;
        this.t = bArr;
        this.u = Math.max(2, i4);
    }

    /* access modifiers changed from: 0000 */
    public void a(byte b2, OutputStream outputStream) throws IOException {
        byte[] bArr = this.f390q;
        int i2 = this.p;
        this.p = i2 + 1;
        bArr[i2] = b2;
        if (this.p >= 254) {
            c(outputStream);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(OutputStream outputStream) throws IOException {
        a(this.g);
        this.h = this.k + 2;
        this.i = true;
        b(this.k, outputStream);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.e[i3] = -1;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, OutputStream outputStream) throws IOException {
        int i3 = 0;
        this.j = i2;
        this.i = false;
        this.a = this.j;
        this.c = b(this.a);
        this.k = 1 << (i2 - 1);
        this.l = this.k + 1;
        this.h = this.k + 2;
        this.p = 0;
        int a2 = a();
        for (int i4 = this.g; i4 < 65536; i4 *= 2) {
            i3++;
        }
        int i5 = 8 - i3;
        int i6 = this.g;
        a(i6);
        b(this.k, outputStream);
        while (true) {
            int a3 = a();
            if (a3 != -1) {
                int i7 = (a3 << this.b) + a2;
                int i8 = (a3 << i5) ^ a2;
                if (this.e[i8] == i7) {
                    a2 = this.f[i8];
                } else if (this.e[i8] >= 0) {
                    int i9 = i6 - i8;
                    if (i8 == 0) {
                        i9 = 1;
                    }
                    while (true) {
                        i8 -= i9;
                        if (i8 < 0) {
                            i8 += i6;
                        }
                        if (this.e[i8] != i7) {
                            if (this.e[i8] < 0) {
                                break;
                            }
                        } else {
                            a2 = this.f[i8];
                            break;
                        }
                    }
                } else {
                    b(a2, outputStream);
                    if (this.h < this.d) {
                        int[] iArr = this.f;
                        int i10 = this.h;
                        this.h = i10 + 1;
                        iArr[i8] = i10;
                        this.e[i8] = i7;
                        a2 = a3;
                    } else {
                        a(outputStream);
                        a2 = a3;
                    }
                }
            } else {
                b(a2, outputStream);
                b(this.l, outputStream);
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(OutputStream outputStream) throws IOException {
        outputStream.write(this.u);
        this.v = this.r * this.s;
        this.w = 0;
        a(this.u + 1, outputStream);
        outputStream.write(0);
    }

    /* access modifiers changed from: 0000 */
    public void c(OutputStream outputStream) throws IOException {
        if (this.p > 0) {
            outputStream.write(this.p);
            outputStream.write(this.f390q, 0, this.p);
            this.p = 0;
        }
    }

    /* access modifiers changed from: 0000 */
    public final int b(int i2) {
        return (1 << i2) - 1;
    }

    private int a() {
        if (this.v == 0) {
            return -1;
        }
        this.v--;
        byte[] bArr = this.t;
        int i2 = this.w;
        this.w = i2 + 1;
        return bArr[i2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2, OutputStream outputStream) throws IOException {
        this.m &= this.o[this.n];
        if (this.n > 0) {
            this.m |= i2 << this.n;
        } else {
            this.m = i2;
        }
        this.n += this.a;
        while (this.n >= 8) {
            a((byte) (this.m & 255), outputStream);
            this.m >>= 8;
            this.n -= 8;
        }
        if (this.h > this.c || this.i) {
            if (this.i) {
                int i3 = this.j;
                this.a = i3;
                this.c = b(i3);
                this.i = false;
            } else {
                this.a++;
                if (this.a == this.b) {
                    this.c = this.d;
                } else {
                    this.c = b(this.a);
                }
            }
        }
        if (i2 == this.l) {
            while (this.n > 0) {
                a((byte) (this.m & 255), outputStream);
                this.m >>= 8;
                this.n -= 8;
            }
            c(outputStream);
        }
    }
}
