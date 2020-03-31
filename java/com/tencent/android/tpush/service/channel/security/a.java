package com.tencent.android.tpush.service.channel.security;

/* compiled from: ProGuard */
public class a {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());

    /* renamed from: com.tencent.android.tpush.service.channel.security.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    static abstract class C0070a {
        public byte[] a;
        public int b;

        C0070a() {
        }
    }

    /* compiled from: ProGuard */
    static class b extends C0070a {
        private static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e;
        private int f;
        private final int[] g;

        public b(int i, byte[] bArr) {
            this.a = bArr;
            this.g = (i & 8) == 0 ? c : d;
            this.e = 0;
            this.f = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:63:0x005c A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(byte[] r11, int r12, int r13, boolean r14) {
            /*
                r10 = this;
                int r0 = r10.e
                r1 = 6
                if (r0 != r1) goto L_0x0007
                r0 = 0
            L_0x0006:
                return r0
            L_0x0007:
                int r4 = r13 + r12
                int r3 = r10.e
                int r1 = r10.f
                r0 = 0
                byte[] r5 = r10.a
                int[] r6 = r10.g
                r2 = r12
            L_0x0013:
                if (r2 >= r4) goto L_0x0133
                if (r3 != 0) goto L_0x0067
            L_0x0017:
                int r7 = r2 + 4
                if (r7 > r4) goto L_0x005a
                byte r1 = r11[r2]
                r1 = r1 & 255(0xff, float:3.57E-43)
                r1 = r6[r1]
                int r1 = r1 << 18
                int r7 = r2 + 1
                byte r7 = r11[r7]
                r7 = r7 & 255(0xff, float:3.57E-43)
                r7 = r6[r7]
                int r7 = r7 << 12
                r1 = r1 | r7
                int r7 = r2 + 2
                byte r7 = r11[r7]
                r7 = r7 & 255(0xff, float:3.57E-43)
                r7 = r6[r7]
                int r7 = r7 << 6
                r1 = r1 | r7
                int r7 = r2 + 3
                byte r7 = r11[r7]
                r7 = r7 & 255(0xff, float:3.57E-43)
                r7 = r6[r7]
                r1 = r1 | r7
                if (r1 < 0) goto L_0x005a
                int r7 = r0 + 2
                byte r8 = (byte) r1
                r5[r7] = r8
                int r7 = r0 + 1
                int r8 = r1 >> 8
                byte r8 = (byte) r8
                r5[r7] = r8
                int r7 = r1 >> 16
                byte r7 = (byte) r7
                r5[r0] = r7
                int r0 = r0 + 3
                int r2 = r2 + 4
                goto L_0x0017
            L_0x005a:
                if (r2 < r4) goto L_0x0067
                r2 = r1
            L_0x005d:
                if (r14 != 0) goto L_0x0105
                r10.e = r3
                r10.f = r2
                r10.b = r0
                r0 = 1
                goto L_0x0006
            L_0x0067:
                int r12 = r2 + 1
                byte r2 = r11[r2]
                r2 = r2 & 255(0xff, float:3.57E-43)
                r2 = r6[r2]
                switch(r3) {
                    case 0: goto L_0x0076;
                    case 1: goto L_0x0086;
                    case 2: goto L_0x0097;
                    case 3: goto L_0x00b7;
                    case 4: goto L_0x00ed;
                    case 5: goto L_0x00fc;
                    default: goto L_0x0072;
                }
            L_0x0072:
                r2 = r3
            L_0x0073:
                r3 = r2
                r2 = r12
                goto L_0x0013
            L_0x0076:
                if (r2 < 0) goto L_0x007e
                int r1 = r3 + 1
                r9 = r2
                r2 = r1
                r1 = r9
                goto L_0x0073
            L_0x007e:
                r7 = -1
                if (r2 == r7) goto L_0x0072
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x0086:
                if (r2 < 0) goto L_0x008e
                int r1 = r1 << 6
                r1 = r1 | r2
                int r2 = r3 + 1
                goto L_0x0073
            L_0x008e:
                r7 = -1
                if (r2 == r7) goto L_0x0072
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x0097:
                if (r2 < 0) goto L_0x009f
                int r1 = r1 << 6
                r1 = r1 | r2
                int r2 = r3 + 1
                goto L_0x0073
            L_0x009f:
                r7 = -2
                if (r2 != r7) goto L_0x00ae
                int r2 = r0 + 1
                int r3 = r1 >> 4
                byte r3 = (byte) r3
                r5[r0] = r3
                r0 = 4
                r9 = r2
                r2 = r0
                r0 = r9
                goto L_0x0073
            L_0x00ae:
                r7 = -1
                if (r2 == r7) goto L_0x0072
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x00b7:
                if (r2 < 0) goto L_0x00d1
                int r1 = r1 << 6
                r1 = r1 | r2
                int r2 = r0 + 2
                byte r3 = (byte) r1
                r5[r2] = r3
                int r2 = r0 + 1
                int r3 = r1 >> 8
                byte r3 = (byte) r3
                r5[r2] = r3
                int r2 = r1 >> 16
                byte r2 = (byte) r2
                r5[r0] = r2
                int r0 = r0 + 3
                r2 = 0
                goto L_0x0073
            L_0x00d1:
                r7 = -2
                if (r2 != r7) goto L_0x00e4
                int r2 = r0 + 1
                int r3 = r1 >> 2
                byte r3 = (byte) r3
                r5[r2] = r3
                int r2 = r1 >> 10
                byte r2 = (byte) r2
                r5[r0] = r2
                int r0 = r0 + 2
                r2 = 5
                goto L_0x0073
            L_0x00e4:
                r7 = -1
                if (r2 == r7) goto L_0x0072
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x00ed:
                r7 = -2
                if (r2 != r7) goto L_0x00f3
                int r2 = r3 + 1
                goto L_0x0073
            L_0x00f3:
                r7 = -1
                if (r2 == r7) goto L_0x0072
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x00fc:
                r7 = -1
                if (r2 == r7) goto L_0x0072
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x0105:
                switch(r3) {
                    case 0: goto L_0x0108;
                    case 1: goto L_0x010f;
                    case 2: goto L_0x0115;
                    case 3: goto L_0x011e;
                    case 4: goto L_0x012d;
                    default: goto L_0x0108;
                }
            L_0x0108:
                r10.e = r3
                r10.b = r0
                r0 = 1
                goto L_0x0006
            L_0x010f:
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x0115:
                int r1 = r0 + 1
                int r2 = r2 >> 4
                byte r2 = (byte) r2
                r5[r0] = r2
                r0 = r1
                goto L_0x0108
            L_0x011e:
                int r1 = r0 + 1
                int r4 = r2 >> 10
                byte r4 = (byte) r4
                r5[r0] = r4
                int r0 = r1 + 1
                int r2 = r2 >> 2
                byte r2 = (byte) r2
                r5[r1] = r2
                goto L_0x0108
            L_0x012d:
                r0 = 6
                r10.e = r0
                r0 = 0
                goto L_0x0006
            L_0x0133:
                r2 = r1
                goto L_0x005d
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.security.a.b.a(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] a(String str, int i) {
        return a(str.getBytes(), i);
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, new byte[((i2 * 3) / 4)]);
        if (!bVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.b == bVar.a.length) {
            return bVar.a;
        } else {
            byte[] bArr2 = new byte[bVar.b];
            System.arraycopy(bVar.a, 0, bArr2, 0, bVar.b);
            return bArr2;
        }
    }

    private a() {
    }
}
