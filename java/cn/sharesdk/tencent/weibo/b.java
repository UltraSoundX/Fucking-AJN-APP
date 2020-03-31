package cn.sharesdk.tencent.weibo;

import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

/* compiled from: Cryptor */
public class b {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;
    private boolean i = true;
    private int j;
    private Random k = new Random();

    public static long a(byte[] bArr, int i2, int i3) {
        int i4;
        long j2 = 0;
        if (i3 > 8) {
            i4 = i2 + 8;
        } else {
            i4 = i2 + i3;
        }
        while (i2 < i4) {
            j2 = (j2 << 8) | ((long) (bArr[i2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED));
            i2++;
        }
        return (4294967295L & j2) | (j2 >>> 32);
    }

    public byte[] a(byte[] bArr, int i2, int i3, byte[] bArr2) {
        this.e = 0;
        this.d = 0;
        this.h = bArr2;
        byte[] bArr3 = new byte[(i2 + 8)];
        if (i3 % 8 != 0 || i3 < 16) {
            return null;
        }
        this.b = a(bArr, i2);
        this.f = this.b[0] & 7;
        int i4 = (i3 - this.f) - 10;
        if (i4 < 0) {
            return null;
        }
        for (int i5 = i2; i5 < bArr3.length; i5++) {
            bArr3[i5] = 0;
        }
        this.c = new byte[i4];
        this.e = 0;
        this.d = 8;
        this.j = 8;
        this.f++;
        this.g = 1;
        byte[] bArr4 = bArr3;
        while (this.g <= 2) {
            if (this.f < 8) {
                this.f++;
                this.g++;
            }
            if (this.f == 8) {
                if (!b(bArr, i2, i3)) {
                    return null;
                }
                bArr4 = bArr;
            }
        }
        int i6 = i4;
        byte[] bArr5 = bArr4;
        int i7 = 0;
        byte[] bArr6 = bArr5;
        while (i6 != 0) {
            if (this.f < 8) {
                this.c[i7] = (byte) (bArr6[(this.e + i2) + this.f] ^ this.b[this.f]);
                i7++;
                i6--;
                this.f++;
            }
            if (this.f == 8) {
                this.e = this.d - 8;
                if (!b(bArr, i2, i3)) {
                    return null;
                }
                bArr6 = bArr;
            }
        }
        this.g = 1;
        byte[] bArr7 = bArr6;
        while (this.g < 8) {
            if (this.f < 8) {
                if ((bArr7[(this.e + i2) + this.f] ^ this.b[this.f]) != 0) {
                    return null;
                }
                this.f++;
            }
            if (this.f == 8) {
                this.e = this.d;
                if (!b(bArr, i2, i3)) {
                    return null;
                }
                bArr7 = bArr;
            }
            this.g++;
        }
        return this.c;
    }

    public byte[] b(byte[] bArr, int i2, int i3, byte[] bArr2) {
        this.a = new byte[8];
        this.b = new byte[8];
        this.f = 1;
        this.g = 0;
        this.e = 0;
        this.d = 0;
        this.h = bArr2;
        this.i = true;
        this.f = (i3 + 10) % 8;
        if (this.f != 0) {
            this.f = 8 - this.f;
        }
        this.c = new byte[(this.f + i3 + 10)];
        this.a[0] = (byte) ((b() & 248) | this.f);
        for (int i4 = 1; i4 <= this.f; i4++) {
            this.a[i4] = (byte) (b() & 255);
        }
        this.f++;
        for (int i5 = 0; i5 < 8; i5++) {
            this.b[i5] = 0;
        }
        this.g = 1;
        while (this.g <= 2) {
            if (this.f < 8) {
                this.a[this.f] = (byte) (b() & 255);
                this.f++;
                this.g++;
            }
            if (this.f == 8) {
                a();
            }
        }
        int i6 = i2;
        int i7 = i3;
        while (i7 > 0) {
            if (this.f < 8) {
                this.a[this.f] = bArr[i6];
                this.f++;
                i6++;
                i7--;
            }
            if (this.f == 8) {
                a();
            }
        }
        this.g = 1;
        while (this.g <= 7) {
            if (this.f < 8) {
                this.a[this.f] = 0;
                this.f++;
                this.g++;
            }
            if (this.f == 8) {
                a();
            }
        }
        return this.c;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        return b(bArr, 0, bArr.length, bArr2);
    }

    private byte[] a(byte[] bArr) {
        int i2 = 16;
        try {
            long a2 = a(bArr, 0, 4);
            long a3 = a(bArr, 4, 4);
            long a4 = a(this.h, 0, 4);
            long a5 = a(this.h, 4, 4);
            long a6 = a(this.h, 8, 4);
            long a7 = a(this.h, 12, 4);
            long j2 = 0;
            long j3 = -1640531527 & 4294967295L;
            while (i2 > 0) {
                i2--;
                j2 = (j2 + j3) & 4294967295L;
                a2 = (a2 + ((((a3 << 4) + a4) ^ (a3 + j2)) ^ ((a3 >>> 5) + a5))) & 4294967295L;
                a3 = (a3 + ((((a2 << 4) + a6) ^ (a2 + j2)) ^ ((a2 >>> 5) + a7))) & 4294967295L;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt((int) a2);
            dataOutputStream.writeInt((int) a3);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            return null;
        }
    }

    private byte[] a(byte[] bArr, int i2) {
        int i3 = 16;
        try {
            long a2 = a(bArr, i2, 4);
            long a3 = a(bArr, i2 + 4, 4);
            long a4 = a(this.h, 0, 4);
            long a5 = a(this.h, 4, 4);
            long a6 = a(this.h, 8, 4);
            long a7 = a(this.h, 12, 4);
            long j2 = -478700656 & 4294967295L;
            long j3 = -1640531527 & 4294967295L;
            while (i3 > 0) {
                i3--;
                a3 = (a3 - ((((a2 << 4) + a6) ^ (a2 + j2)) ^ ((a2 >>> 5) + a7))) & 4294967295L;
                a2 = (a2 - ((((a3 << 4) + a4) ^ (a3 + j2)) ^ ((a3 >>> 5) + a5))) & 4294967295L;
                j2 = (j2 - j3) & 4294967295L;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt((int) a2);
            dataOutputStream.writeInt((int) a3);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            return null;
        }
    }

    private byte[] b(byte[] bArr) {
        return a(bArr, 0);
    }

    private void a() {
        this.f = 0;
        while (this.f < 8) {
            if (this.i) {
                byte[] bArr = this.a;
                int i2 = this.f;
                bArr[i2] = (byte) (bArr[i2] ^ this.b[this.f]);
            } else {
                byte[] bArr2 = this.a;
                int i3 = this.f;
                bArr2[i3] = (byte) (bArr2[i3] ^ this.c[this.e + this.f]);
            }
            this.f++;
        }
        System.arraycopy(a(this.a), 0, this.c, this.d, 8);
        this.f = 0;
        while (this.f < 8) {
            byte[] bArr3 = this.c;
            int i4 = this.d + this.f;
            bArr3[i4] = (byte) (bArr3[i4] ^ this.b[this.f]);
            this.f++;
        }
        System.arraycopy(this.a, 0, this.b, 0, 8);
        this.e = this.d;
        this.d += 8;
        this.f = 0;
        this.i = false;
    }

    private boolean b(byte[] bArr, int i2, int i3) {
        this.f = 0;
        while (this.f < 8) {
            if (this.j + this.f >= i3) {
                return true;
            }
            byte[] bArr2 = this.b;
            int i4 = this.f;
            bArr2[i4] = (byte) (bArr2[i4] ^ bArr[(this.d + i2) + this.f]);
            this.f++;
        }
        this.b = b(this.b);
        if (this.b == null) {
            return false;
        }
        this.j += 8;
        this.d += 8;
        this.f = 0;
        return true;
    }

    private int b() {
        return this.k.nextInt();
    }

    public byte[] a(byte[] bArr, byte[] bArr2, int i2) {
        byte[] a2 = a(bArr, 0, bArr.length, bArr2);
        return a2 == null ? a(i2) : a2;
    }

    private byte[] a(int i2) {
        byte[] bArr = new byte[i2];
        this.k.nextBytes(bArr);
        return bArr;
    }
}
