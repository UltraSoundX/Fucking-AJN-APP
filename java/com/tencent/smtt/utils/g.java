package com.tencent.smtt.utils;

import android.os.Build.VERSION;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

/* compiled from: HttpUtil */
public class g {

    /* compiled from: HttpUtil */
    public interface a {
        void a(int i);
    }

    public static String a(String str, byte[] bArr, a aVar, boolean z) {
        String b;
        byte[] bArr2;
        String str2;
        if (z) {
            try {
                b = i.a().c();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            b = h.a().b();
        }
        String str3 = str + b;
        if (z) {
            try {
                bArr2 = i.a().a(bArr);
            } catch (Exception e2) {
                e2.printStackTrace();
                bArr2 = bArr;
            }
        } else {
            bArr2 = h.a().a(bArr);
        }
        if (bArr2 == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Content-Length", String.valueOf(bArr2.length));
        HttpURLConnection a2 = a(str3, (Map<String, String>) hashMap);
        if (a2 != null) {
            b(a2, bArr2);
            str2 = a(a2, aVar, z);
        } else {
            str2 = null;
        }
        return str2;
    }

    public static String a(String str, Map<String, String> map, byte[] bArr, a aVar, boolean z) {
        if (bArr == null) {
            return null;
        }
        HttpURLConnection a2 = a(str, map);
        if (a2 == null) {
            return null;
        }
        if (z) {
            a(a2, bArr);
        } else {
            b(a2, bArr);
        }
        return a(a2, aVar, false);
    }

    private static HttpURLConnection a(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnection;
        Exception e;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", "close");
                } else {
                    httpURLConnection.setRequestProperty("http.keepAlive", "false");
                }
                for (Entry entry : map.entrySet()) {
                    httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return httpURLConnection;
            }
        } catch (Exception e3) {
            Exception exc = e3;
            httpURLConnection = null;
            e = exc;
            e.printStackTrace();
            return httpURLConnection;
        }
        return httpURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream(), 204800));
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.flush();
                a(null);
                a(gZIPOutputStream);
            } catch (Exception e) {
                e = e;
                try {
                    e.printStackTrace();
                    a(null);
                    a(gZIPOutputStream);
                } catch (Throwable th) {
                    th = th;
                    a(null);
                    a(gZIPOutputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            gZIPOutputStream = null;
            e.printStackTrace();
            a(null);
            a(gZIPOutputStream);
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = null;
            a(null);
            a(gZIPOutputStream);
            throw th;
        }
    }

    private static void b(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a(outputStream);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.util.zip.InflaterInputStream] */
    /* JADX WARNING: type inference failed for: r2v13, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r2v18, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r2v20 */
    /* JADX WARNING: type inference failed for: r2v21 */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: type inference failed for: r2v22 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 10 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.net.HttpURLConnection r7, com.tencent.smtt.utils.g.a r8, boolean r9) {
        /*
            r1 = 0
            int r0 = r7.getResponseCode()     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            if (r8 == 0) goto L_0x000a
            r8.a(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
        L_0x000a:
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L_0x00a8
            java.io.InputStream r0 = r7.getInputStream()     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            java.lang.String r2 = r7.getContentEncoding()     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            if (r2 == 0) goto L_0x004a
            java.lang.String r3 = "gzip"
            boolean r3 = r2.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            if (r3 == 0) goto L_0x004a
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
        L_0x0025:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x00a4, all -> 0x0099 }
            r0.<init>()     // Catch:{ Throwable -> 0x00a4, all -> 0x0099 }
            r3 = 128(0x80, float:1.794E-43)
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
        L_0x002e:
            int r4 = r2.read(r3)     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            r5 = -1
            if (r4 == r5) goto L_0x0066
            r5 = 0
            r0.write(r3, r5, r4)     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            goto L_0x002e
        L_0x003a:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r0
            r0 = r6
        L_0x003f:
            r0.printStackTrace()     // Catch:{ all -> 0x00a0 }
            a(r3)
            a(r2)
            r2 = r1
        L_0x0049:
            return r2
        L_0x004a:
            if (r2 == 0) goto L_0x0064
            java.lang.String r3 = "deflate"
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            if (r2 == 0) goto L_0x0064
            java.util.zip.InflaterInputStream r2 = new java.util.zip.InflaterInputStream     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            java.util.zip.Inflater r3 = new java.util.zip.Inflater     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            r4 = 1
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            r2.<init>(r0, r3)     // Catch:{ Throwable -> 0x0060, all -> 0x0090 }
            goto L_0x0025
        L_0x0060:
            r0 = move-exception
            r2 = r1
            r3 = r1
            goto L_0x003f
        L_0x0064:
            r2 = r0
            goto L_0x0025
        L_0x0066:
            if (r9 == 0) goto L_0x007c
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            byte[] r4 = r0.toByteArray()     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            java.lang.String r5 = "utf-8"
            r3.<init>(r4, r5)     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            r1 = r2
            r2 = r3
        L_0x0075:
            a(r1)
            a(r0)
            goto L_0x0049
        L_0x007c:
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            com.tencent.smtt.utils.h r4 = com.tencent.smtt.utils.h.a()     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            byte[] r5 = r0.toByteArray()     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            byte[] r4 = r4.c(r5)     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x003a, all -> 0x009b }
            r1 = r2
            r2 = r3
            goto L_0x0075
        L_0x0090:
            r0 = move-exception
            r2 = r1
        L_0x0092:
            a(r2)
            a(r1)
            throw r0
        L_0x0099:
            r0 = move-exception
            goto L_0x0092
        L_0x009b:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0092
        L_0x00a0:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0092
        L_0x00a4:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x003f
        L_0x00a8:
            r0 = r1
            r2 = r1
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.g.a(java.net.HttpURLConnection, com.tencent.smtt.utils.g$a, boolean):java.lang.String");
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
