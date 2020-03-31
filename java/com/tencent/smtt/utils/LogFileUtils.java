package com.tencent.smtt.utils;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LogFileUtils {
    private static OutputStream a = null;

    /* JADX WARNING: Unknown top exception splitter block from list: {B:41:0x0083=Splitter:B:41:0x0083, B:19:0x004b=Splitter:B:19:0x004b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void writeDataToStorage(java.io.File r8, java.lang.String r9, byte[] r10, java.lang.String r11, boolean r12) {
        /*
            r0 = 0
            java.lang.Class<com.tencent.smtt.utils.LogFileUtils> r2 = com.tencent.smtt.utils.LogFileUtils.class
            monitor-enter(r2)
            byte[] r1 = encrypt(r9, r11)     // Catch:{ all -> 0x0084 }
            if (r1 == 0) goto L_0x000c
            r11 = r0
            r0 = r1
        L_0x000c:
            java.io.File r1 = r8.getParentFile()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r1.mkdirs()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            boolean r1 = r8.isFile()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            if (r1 == 0) goto L_0x0030
            boolean r1 = r8.exists()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            if (r1 == 0) goto L_0x0030
            long r4 = r8.length()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r6 = 2097152(0x200000, double:1.0361308E-317)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x0030
            r8.delete()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r8.createNewFile()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
        L_0x0030:
            java.io.OutputStream r1 = a     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            if (r1 != 0) goto L_0x0040
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r1.<init>(r8, r12)     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            a = r3     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
        L_0x0040:
            if (r11 == 0) goto L_0x0056
            java.io.OutputStream r0 = a     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            byte[] r1 = r11.getBytes()     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r0.write(r1)     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
        L_0x004b:
            java.io.OutputStream r0 = a     // Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x0054
            java.io.OutputStream r0 = a     // Catch:{ Throwable -> 0x0089 }
            r0.flush()     // Catch:{ Throwable -> 0x0089 }
        L_0x0054:
            monitor-exit(r2)
            return
        L_0x0056:
            java.io.OutputStream r1 = a     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r1.write(r10)     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            java.io.OutputStream r1 = a     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r1.write(r0)     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            java.io.OutputStream r0 = a     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r1 = 2
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r1 = {10, 10} // fill-array     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            r0.write(r1)     // Catch:{ Throwable -> 0x006c, all -> 0x0079 }
            goto L_0x004b
        L_0x006c:
            r0 = move-exception
            java.io.OutputStream r0 = a     // Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x0054
            java.io.OutputStream r0 = a     // Catch:{ Throwable -> 0x0077 }
            r0.flush()     // Catch:{ Throwable -> 0x0077 }
            goto L_0x0054
        L_0x0077:
            r0 = move-exception
            goto L_0x0054
        L_0x0079:
            r0 = move-exception
            java.io.OutputStream r1 = a     // Catch:{ all -> 0x0084 }
            if (r1 == 0) goto L_0x0083
            java.io.OutputStream r1 = a     // Catch:{ Throwable -> 0x0087 }
            r1.flush()     // Catch:{ Throwable -> 0x0087 }
        L_0x0083:
            throw r0     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0087:
            r1 = move-exception
            goto L_0x0083
        L_0x0089:
            r0 = move-exception
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.LogFileUtils.writeDataToStorage(java.io.File, java.lang.String, byte[], java.lang.String, boolean):void");
    }

    public static void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.e("LOG_FILE", "Couldn't close stream!", e);
            }
        }
    }

    public static byte[] encrypt(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(str.getBytes("UTF-8"), "RC4"));
            return instance.update(bytes);
        } catch (Throwable th) {
            Log.e("LOG_FILE", "encrypt exception:" + th.getMessage());
            return null;
        }
    }

    public static String createKey() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static byte[] encryptKey(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(str.getBytes("UTF-8"), "RC4"));
            return instance.update(bytes);
        } catch (Throwable th) {
            Log.e("LOG_FILE", "encrypt exception:" + th.getMessage());
            return null;
        }
    }

    public static byte[] createHeaderText(String str, String str2) {
        try {
            byte[] encryptKey = encryptKey(str, str2);
            String format = String.format("%03d", new Object[]{Integer.valueOf(encryptKey.length)});
            byte[] bArr = new byte[(encryptKey.length + 3)];
            bArr[0] = (byte) format.charAt(0);
            bArr[1] = (byte) format.charAt(1);
            bArr[2] = (byte) format.charAt(2);
            System.arraycopy(encryptKey, 0, bArr, 3, encryptKey.length);
            return bArr;
        } catch (Exception e) {
            return null;
        }
    }
}
