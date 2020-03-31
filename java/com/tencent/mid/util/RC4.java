package com.tencent.mid.util;

public class RC4 {
    static final byte[] key = "03a976511e2cbe3a7f26808fb7af3c05".getBytes();

    public static byte[] encrypt(byte[] bArr) {
        return encrypt(bArr, key);
    }

    public static byte[] decrypt(byte[] bArr) {
        return decrypt(bArr, key);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v13, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r5v13, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r9v0, types: [byte[]] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] encrypt(byte[] r8, byte[] r9) {
        /*
            r7 = 256(0x100, float:3.59E-43)
            r0 = 0
            int[] r3 = new int[r7]
            int[] r4 = new int[r7]
            int r2 = r9.length
            r1 = 1
            if (r2 < r1) goto L_0x000d
            if (r2 <= r7) goto L_0x0015
        L_0x000d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "key must be between 1 and 256 bytes"
            r0.<init>(r1)
            throw r0
        L_0x0015:
            r1 = r0
        L_0x0016:
            if (r1 >= r7) goto L_0x0023
            r3[r1] = r1
            int r5 = r1 % r2
            byte r5 = r9[r5]
            r4[r1] = r5
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0023:
            r1 = r0
            r2 = r0
        L_0x0025:
            if (r1 >= r7) goto L_0x003a
            r5 = r3[r1]
            int r2 = r2 + r5
            r5 = r4[r1]
            int r2 = r2 + r5
            r2 = r2 & 255(0xff, float:3.57E-43)
            r5 = r3[r1]
            r6 = r3[r2]
            r3[r1] = r6
            r3[r2] = r5
            int r1 = r1 + 1
            goto L_0x0025
        L_0x003a:
            int r1 = r8.length
            byte[] r4 = new byte[r1]
            r1 = r0
            r2 = r0
        L_0x003f:
            int r5 = r8.length
            if (r0 >= r5) goto L_0x0065
            int r2 = r2 + 1
            r2 = r2 & 255(0xff, float:3.57E-43)
            r5 = r3[r2]
            int r1 = r1 + r5
            r1 = r1 & 255(0xff, float:3.57E-43)
            r5 = r3[r2]
            r6 = r3[r1]
            r3[r2] = r6
            r3[r1] = r5
            r5 = r3[r2]
            r6 = r3[r1]
            int r5 = r5 + r6
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r3[r5]
            byte r6 = r8[r0]
            r5 = r5 ^ r6
            byte r5 = (byte) r5
            r4[r0] = r5
            int r0 = r0 + 1
            goto L_0x003f
        L_0x0065:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mid.util.RC4.encrypt(byte[], byte[]):byte[]");
    }

    static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return encrypt(bArr, bArr2);
    }
}
