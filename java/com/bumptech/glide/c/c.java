package com.bumptech.glide.c;

import com.tencent.bigdata.dataacquisition.DeviceInfos;

/* compiled from: NeuQuant */
class c {
    protected int a;
    protected byte[] b;
    protected int c;
    protected int d;
    protected int[][] e;
    protected int[] f = new int[256];
    protected int[] g = new int[256];
    protected int[] h = new int[256];
    protected int[] i = new int[32];

    public c(byte[] bArr, int i2, int i3) {
        this.b = bArr;
        this.c = i2;
        this.d = i3;
        this.e = new int[256][];
        for (int i4 = 0; i4 < 256; i4++) {
            this.e[i4] = new int[4];
            int[] iArr = this.e[i4];
            int i5 = (i4 << 12) / 256;
            iArr[2] = i5;
            iArr[1] = i5;
            iArr[0] = i5;
            this.h[i4] = 256;
            this.g[i4] = 0;
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[768];
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[this.e[i2][3]] = i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            int i5 = iArr[i4];
            int i6 = i3 + 1;
            bArr[i3] = (byte) this.e[i5][0];
            int i7 = i6 + 1;
            bArr[i6] = (byte) this.e[i5][1];
            i3 = i7 + 1;
            bArr[i7] = (byte) this.e[i5][2];
        }
        return bArr;
    }

    public void b() {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i6 < 256) {
            int[] iArr = this.e[i6];
            int i7 = iArr[1];
            int i8 = i6;
            for (int i9 = i6 + 1; i9 < 256; i9++) {
                int[] iArr2 = this.e[i9];
                if (iArr2[1] < i7) {
                    i7 = iArr2[1];
                    i8 = i9;
                }
            }
            int[] iArr3 = this.e[i8];
            if (i6 != i8) {
                int i10 = iArr3[0];
                iArr3[0] = iArr[0];
                iArr[0] = i10;
                int i11 = iArr3[1];
                iArr3[1] = iArr[1];
                iArr[1] = i11;
                int i12 = iArr3[2];
                iArr3[2] = iArr[2];
                iArr[2] = i12;
                int i13 = iArr3[3];
                iArr3[3] = iArr[3];
                iArr[3] = i13;
            }
            if (i7 != i5) {
                this.f[i5] = (i4 + i6) >> 1;
                for (int i14 = i5 + 1; i14 < i7; i14++) {
                    this.f[i14] = i6;
                }
                i3 = i7;
                i2 = i6;
            } else {
                i2 = i4;
                i3 = i5;
            }
            i6++;
            i4 = i2;
            i5 = i3;
        }
        this.f[i5] = (i4 + 255) >> 1;
        for (int i15 = i5 + 1; i15 < 256; i15++) {
            this.f[i15] = 255;
        }
    }

    public void c() {
        int i2;
        int i3;
        int i4;
        if (this.c < 1509) {
            this.d = 1;
        }
        this.a = ((this.d - 1) / 3) + 30;
        byte[] bArr = this.b;
        int i5 = this.c;
        int i6 = this.c / (this.d * 3);
        int i7 = i6 / 100;
        for (int i8 = 0; i8 < 32; i8++) {
            this.i[i8] = (((1024 - (i8 * i8)) * 256) / 1024) * 1024;
        }
        if (this.c < 1509) {
            i2 = 3;
        } else if (this.c % 499 != 0) {
            i2 = 1497;
        } else if (this.c % 491 != 0) {
            i2 = 1473;
        } else if (this.c % 487 != 0) {
            i2 = 1461;
        } else {
            i2 = 1509;
        }
        int i9 = 0;
        int i10 = 32;
        int i11 = 2048;
        int i12 = 0;
        int i13 = 1024;
        while (i12 < i6) {
            int i14 = (bArr[i9 + 0] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 4;
            int i15 = (bArr[i9 + 1] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 4;
            int i16 = (bArr[i9 + 2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 4;
            int b2 = b(i14, i15, i16);
            b(i13, b2, i14, i15, i16);
            if (i10 != 0) {
                a(i10, b2, i14, i15, i16);
            }
            int i17 = i9 + i2;
            if (i17 >= i5) {
                i3 = i17 - this.c;
            } else {
                i3 = i17;
            }
            int i18 = i12 + 1;
            if (i7 == 0) {
                i4 = 1;
            } else {
                i4 = i7;
            }
            if (i18 % i4 == 0) {
                int i19 = i13 - (i13 / this.a);
                int i20 = i11 - (i11 / 30);
                int i21 = i20 >> 6;
                if (i21 <= 1) {
                    i21 = 0;
                }
                for (int i22 = 0; i22 < i21; i22++) {
                    this.i[i22] = ((((i21 * i21) - (i22 * i22)) * 256) / (i21 * i21)) * i19;
                }
                i9 = i3;
                i7 = i4;
                i10 = i21;
                i11 = i20;
                i12 = i18;
                i13 = i19;
            } else {
                i9 = i3;
                i7 = i4;
                i12 = i18;
            }
        }
    }

    public int a(int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8 = this.f[i3];
        int i9 = -1;
        int i10 = 1000;
        int i11 = i8 - 1;
        int i12 = i8;
        while (true) {
            if (i12 >= 256 && i11 < 0) {
                return i9;
            }
            if (i12 < 256) {
                int[] iArr = this.e[i12];
                int i13 = iArr[1] - i3;
                if (i13 >= i10) {
                    i6 = i10;
                    i5 = 256;
                    i7 = i9;
                } else {
                    i5 = i12 + 1;
                    if (i13 < 0) {
                        i13 = -i13;
                    }
                    int i14 = iArr[0] - i2;
                    if (i14 < 0) {
                        i14 = -i14;
                    }
                    int i15 = i14 + i13;
                    if (i15 < i10) {
                        int i16 = iArr[2] - i4;
                        if (i16 < 0) {
                            i16 = -i16;
                        }
                        i6 = i15 + i16;
                        if (i6 < i10) {
                            i7 = iArr[3];
                        }
                    }
                    i6 = i10;
                    i7 = i9;
                }
            } else {
                i5 = i12;
                i6 = i10;
                i7 = i9;
            }
            if (i11 >= 0) {
                int[] iArr2 = this.e[i11];
                int i17 = i3 - iArr2[1];
                if (i17 >= i6) {
                    i9 = i7;
                    i11 = -1;
                    i10 = i6;
                    i12 = i5;
                } else {
                    i11--;
                    if (i17 < 0) {
                        i17 = -i17;
                    }
                    int i18 = iArr2[0] - i2;
                    if (i18 < 0) {
                        i18 = -i18;
                    }
                    int i19 = i18 + i17;
                    if (i19 < i6) {
                        int i20 = iArr2[2] - i4;
                        if (i20 < 0) {
                            i20 = -i20;
                        }
                        int i21 = i20 + i19;
                        if (i21 < i6) {
                            i9 = iArr2[3];
                            i12 = i5;
                            i10 = i21;
                        }
                    }
                }
            }
            i9 = i7;
            i10 = i6;
            i12 = i5;
        }
    }

    public byte[] d() {
        c();
        e();
        b();
        return a();
    }

    public void e() {
        for (int i2 = 0; i2 < 256; i2++) {
            int[] iArr = this.e[i2];
            iArr[0] = iArr[0] >> 4;
            int[] iArr2 = this.e[i2];
            iArr2[1] = iArr2[1] >> 4;
            int[] iArr3 = this.e[i2];
            iArr3[2] = iArr3[2] >> 4;
            this.e[i2][3] = i2;
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9 = i3 - i2;
        if (i9 < -1) {
            i7 = -1;
        } else {
            i7 = i9;
        }
        int i10 = i3 + i2;
        if (i10 > 256) {
            i10 = 256;
        }
        int i11 = 1;
        int i12 = i3 - 1;
        int i13 = i3 + 1;
        while (true) {
            if (i13 < i10 || i12 > i7) {
                int i14 = i11 + 1;
                int i15 = this.i[i11];
                if (i13 < i10) {
                    i8 = i13 + 1;
                    int[] iArr = this.e[i13];
                    try {
                        iArr[0] = iArr[0] - (((iArr[0] - i4) * i15) / 262144);
                        iArr[1] = iArr[1] - (((iArr[1] - i5) * i15) / 262144);
                        iArr[2] = iArr[2] - (((iArr[2] - i6) * i15) / 262144);
                    } catch (Exception e2) {
                    }
                } else {
                    i8 = i13;
                }
                if (i12 > i7) {
                    int i16 = i12 - 1;
                    int[] iArr2 = this.e[i12];
                    try {
                        iArr2[0] = iArr2[0] - (((iArr2[0] - i4) * i15) / 262144);
                        iArr2[1] = iArr2[1] - (((iArr2[1] - i5) * i15) / 262144);
                        iArr2[2] = iArr2[2] - ((i15 * (iArr2[2] - i6)) / 262144);
                        i12 = i16;
                        i13 = i8;
                        i11 = i14;
                    } catch (Exception e3) {
                        i12 = i16;
                        i13 = i8;
                        i11 = i14;
                    }
                } else {
                    i13 = i8;
                    i11 = i14;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.e[i3];
        iArr[0] = iArr[0] - (((iArr[0] - i4) * i2) / 1024);
        iArr[1] = iArr[1] - (((iArr[1] - i5) * i2) / 1024);
        iArr[2] = iArr[2] - (((iArr[2] - i6) * i2) / 1024);
    }

    /* access modifiers changed from: protected */
    public int b(int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8 = Integer.MAX_VALUE;
        int i9 = -1;
        int i10 = Integer.MAX_VALUE;
        int i11 = -1;
        int i12 = 0;
        while (i12 < 256) {
            int[] iArr = this.e[i12];
            int i13 = iArr[0] - i2;
            if (i13 < 0) {
                i13 = -i13;
            }
            int i14 = iArr[1] - i3;
            if (i14 < 0) {
                i14 = -i14;
            }
            int i15 = i14 + i13;
            int i16 = iArr[2] - i4;
            if (i16 < 0) {
                i16 = -i16;
            }
            int i17 = i15 + i16;
            if (i17 < i10) {
                i5 = i17;
                i6 = i12;
            } else {
                i5 = i10;
                i6 = i11;
            }
            int i18 = i17 - (this.g[i12] >> 12);
            if (i18 < i8) {
                i7 = i12;
            } else {
                i18 = i8;
                i7 = i9;
            }
            int i19 = this.h[i12] >> 10;
            int[] iArr2 = this.h;
            iArr2[i12] = iArr2[i12] - i19;
            int[] iArr3 = this.g;
            iArr3[i12] = (i19 << 10) + iArr3[i12];
            i12++;
            i8 = i18;
            i9 = i7;
            i11 = i6;
            i10 = i5;
        }
        int[] iArr4 = this.h;
        iArr4[i11] = iArr4[i11] + 64;
        int[] iArr5 = this.g;
        iArr5[i11] = iArr5[i11] - 65536;
        return i9;
    }
}
