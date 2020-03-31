package com.baidu.mobstat;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class ay {

    public static class a {
        public static String a(byte[] bArr) {
            try {
                return ay.b(MessageDigest.getInstance("md5"), bArr);
            } catch (Exception e) {
                return "";
            }
        }
    }

    public static class b {
        public static String a(byte[] bArr) {
            try {
                return ay.b(MessageDigest.getInstance("SHA-256"), bArr);
            } catch (Exception e) {
                return "";
            }
        }

        public static String a(File file) {
            try {
                return ay.b(MessageDigest.getInstance("SHA-256"), file);
            } catch (NoSuchAlgorithmException e) {
                return "";
            }
        }
    }

    /* access modifiers changed from: private */
    public static String b(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        return a(messageDigest.digest());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0036 A[SYNTHETIC, Splitter:B:25:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.security.MessageDigest r5, java.io.File r6) {
        /*
            boolean r0 = r6.isFile()
            if (r0 == 0) goto L_0x003a
            r1 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0046, all -> 0x0033 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0046, all -> 0x0033 }
            r1 = 4048(0xfd0, float:5.672E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x002a, all -> 0x0041 }
        L_0x0010:
            int r2 = r0.read(r1)     // Catch:{ Exception -> 0x002a, all -> 0x0041 }
            r3 = -1
            if (r2 != r3) goto L_0x0025
            if (r0 == 0) goto L_0x001c
            r0.close()     // Catch:{ Exception -> 0x003d }
        L_0x001c:
            byte[] r0 = r5.digest()
            java.lang.String r0 = a(r0)
        L_0x0024:
            return r0
        L_0x0025:
            r3 = 0
            r5.update(r1, r3, r2)     // Catch:{ Exception -> 0x002a, all -> 0x0041 }
            goto L_0x0010
        L_0x002a:
            r1 = move-exception
        L_0x002b:
            if (r0 == 0) goto L_0x001c
            r0.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x001c
        L_0x0031:
            r0 = move-exception
            goto L_0x001c
        L_0x0033:
            r0 = move-exception
        L_0x0034:
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch:{ Exception -> 0x003f }
        L_0x0039:
            throw r0
        L_0x003a:
            java.lang.String r0 = ""
            goto L_0x0024
        L_0x003d:
            r0 = move-exception
            goto L_0x001c
        L_0x003f:
            r1 = move-exception
            goto L_0x0039
        L_0x0041:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0034
        L_0x0046:
            r0 = move-exception
            r0 = r1
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ay.b(java.security.MessageDigest, java.io.File):java.lang.String");
    }

    private static String a(byte[] bArr) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = (bArr[i2] >> 4) & 15;
            byte b2 = bArr[i2] & 15;
            sb.append((char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48));
            if (b2 >= 10) {
                i = (b2 + 97) - 10;
            } else {
                i = b2 + 48;
            }
            sb.append((char) i);
        }
        return sb.toString();
    }
}
