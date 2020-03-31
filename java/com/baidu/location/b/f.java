package com.baidu.location.b;

import android.os.Environment;
import android.text.TextUtils;
import com.baidu.location.e.i;
import com.baidu.location.g.a;
import com.baidu.location.g.j;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.HttpURLConnection;
import java.net.URL;

public class f implements UncaughtExceptionHandler {
    private static f a = null;
    private int b = 0;

    private f() {
    }

    public static f a() {
        if (a == null) {
            a = new f();
        }
        return a;
    }

    private String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    private void a(File file, String str, String str2) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(280);
            randomAccessFile.writeInt(12346);
            randomAccessFile.seek(300);
            randomAccessFile.writeLong(System.currentTimeMillis());
            byte[] bytes = str.getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.seek(600);
            byte[] bytes2 = str2.getBytes();
            randomAccessFile.writeInt(bytes2.length);
            randomAccessFile.write(bytes2, 0, bytes2.length);
            if (!a(str, str2)) {
                randomAccessFile.seek(280);
                randomAccessFile.writeInt(1326);
            }
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (!i.j()) {
            return false;
        }
        try {
            URL url = new URL(j.e);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("e0");
            stringBuffer.append("=");
            stringBuffer.append(str);
            stringBuffer.append("&");
            stringBuffer.append("e1");
            stringBuffer.append("=");
            stringBuffer.append(str2);
            stringBuffer.append("&");
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(a.b);
            httpURLConnection.setReadTimeout(a.b);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(stringBuffer.toString().getBytes());
            outputStream.flush();
            outputStream.close();
            return httpURLConnection.getResponseCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }

    public void b() {
        String str;
        String str2 = null;
        try {
            File file = new File((Environment.getExternalStorageDirectory().getPath() + "/traces") + "/error_fs2.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(280);
                if (1326 == randomAccessFile.readInt()) {
                    randomAccessFile.seek(308);
                    int readInt = randomAccessFile.readInt();
                    if (readInt <= 0 || readInt >= 2048) {
                        str = null;
                    } else {
                        byte[] bArr = new byte[readInt];
                        randomAccessFile.read(bArr, 0, readInt);
                        str = new String(bArr, 0, readInt);
                    }
                    randomAccessFile.seek(600);
                    int readInt2 = randomAccessFile.readInt();
                    if (readInt2 > 0 && readInt2 < 2048) {
                        byte[] bArr2 = new byte[readInt2];
                        randomAccessFile.read(bArr2, 0, readInt2);
                        str2 = new String(bArr2, 0, readInt2);
                    }
                    if (a(str, str2)) {
                        randomAccessFile.seek(280);
                        randomAccessFile.writeInt(12346);
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a4 A[Catch:{ Exception -> 0x013f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ad A[SYNTHETIC, Splitter:B:25:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0144  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
            r9 = this;
            r1 = 0
            r4 = 0
            int r0 = r9.b
            int r0 = r0 + 1
            r9.b = r0
            int r0 = r9.b
            r2 = 2
            if (r0 <= r2) goto L_0x0015
            int r0 = android.os.Process.myPid()
            android.os.Process.killProcess(r0)
        L_0x0014:
            return
        L_0x0015:
            long r2 = java.lang.System.currentTimeMillis()
            long r6 = com.baidu.location.f.a.b()
            long r2 = r2 - r6
            r6 = 10000(0x2710, double:4.9407E-320)
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0071
            r0 = 1089722122(0x40f3d70a, float:7.62)
            float r2 = com.baidu.location.f.a()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0071
            com.baidu.location.g.c r0 = com.baidu.location.g.c.a()
            long r2 = r0.c()
            long r6 = java.lang.System.currentTimeMillis()
            long r2 = r6 - r2
            r6 = 40000(0x9c40, double:1.97626E-319)
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0106
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = com.baidu.location.g.j.i()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = com.baidu.location.f.b()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x0071
            r0.delete()
        L_0x0071:
            java.lang.String r2 = r9.a(r11)     // Catch:{ Exception -> 0x0113 }
            if (r2 == 0) goto L_0x0147
            java.lang.String r0 = "com.baidu.location"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x013f }
            if (r0 == 0) goto L_0x0147
            r3 = 1
        L_0x0080:
            com.baidu.location.g.b r0 = com.baidu.location.g.b.a()     // Catch:{ Exception -> 0x013f }
            r5 = 0
            java.lang.String r0 = r0.a(r5)     // Catch:{ Exception -> 0x013f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013f }
            r5.<init>()     // Catch:{ Exception -> 0x013f }
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ Exception -> 0x013f }
            com.baidu.location.a.a r5 = com.baidu.location.a.a.a()     // Catch:{ Exception -> 0x013f }
            java.lang.String r5 = r5.d()     // Catch:{ Exception -> 0x013f }
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ Exception -> 0x013f }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x013f }
            if (r0 == 0) goto L_0x0144
            java.lang.String r0 = com.baidu.location.Jni.a(r0)     // Catch:{ Exception -> 0x013f }
        L_0x00a8:
            r8 = r3
            r3 = r0
            r0 = r8
        L_0x00ab:
            if (r0 == 0) goto L_0x00fd
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013d }
            r0.<init>()     // Catch:{ Exception -> 0x013d }
            java.io.File r4 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x013d }
            java.lang.String r4 = r4.getPath()     // Catch:{ Exception -> 0x013d }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Exception -> 0x013d }
            java.lang.String r4 = "/traces"
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Exception -> 0x013d }
            java.lang.String r4 = r0.toString()     // Catch:{ Exception -> 0x013d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013d }
            r0.<init>()     // Catch:{ Exception -> 0x013d }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Exception -> 0x013d }
            java.lang.String r5 = "/error_fs2.dat"
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ Exception -> 0x013d }
            java.lang.String r5 = r0.toString()     // Catch:{ Exception -> 0x013d }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x013d }
            r0.<init>(r5)     // Catch:{ Exception -> 0x013d }
            boolean r5 = r0.exists()     // Catch:{ Exception -> 0x013d }
            if (r5 != 0) goto L_0x0119
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x013d }
            r5.<init>(r4)     // Catch:{ Exception -> 0x013d }
            boolean r4 = r5.exists()     // Catch:{ Exception -> 0x013d }
            if (r4 != 0) goto L_0x00f4
            r5.mkdirs()     // Catch:{ Exception -> 0x013d }
        L_0x00f4:
            boolean r4 = r0.createNewFile()     // Catch:{ Exception -> 0x013d }
            if (r4 != 0) goto L_0x0142
        L_0x00fa:
            r9.a(r1, r3, r2)     // Catch:{ Exception -> 0x013d }
        L_0x00fd:
            int r0 = android.os.Process.myPid()
            android.os.Process.killProcess(r0)
            goto L_0x0014
        L_0x0106:
            com.baidu.location.g.c r0 = com.baidu.location.g.c.a()
            long r2 = java.lang.System.currentTimeMillis()
            r0.b(r2)
            goto L_0x0071
        L_0x0113:
            r0 = move-exception
            r0 = r1
        L_0x0115:
            r2 = r0
            r3 = r1
            r0 = r4
            goto L_0x00ab
        L_0x0119:
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x013d }
            java.lang.String r4 = "rw"
            r1.<init>(r0, r4)     // Catch:{ Exception -> 0x013d }
            r4 = 300(0x12c, double:1.48E-321)
            r1.seek(r4)     // Catch:{ Exception -> 0x013d }
            long r4 = r1.readLong()     // Catch:{ Exception -> 0x013d }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x013d }
            long r4 = r6 - r4
            r6 = 86400000(0x5265c00, double:4.2687272E-316)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x0139
            r9.a(r0, r3, r2)     // Catch:{ Exception -> 0x013d }
        L_0x0139:
            r1.close()     // Catch:{ Exception -> 0x013d }
            goto L_0x00fd
        L_0x013d:
            r0 = move-exception
            goto L_0x00fd
        L_0x013f:
            r0 = move-exception
            r0 = r2
            goto L_0x0115
        L_0x0142:
            r1 = r0
            goto L_0x00fa
        L_0x0144:
            r0 = r1
            goto L_0x00a8
        L_0x0147:
            r3 = r4
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.f.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
