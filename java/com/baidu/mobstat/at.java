package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

public final class at {
    private static final Proxy a = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
    private static final Proxy b = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    public static String a() {
        boolean z = false;
        try {
            return Environment.getExternalStorageState();
        } catch (Exception e) {
            return z;
        }
    }

    public static File a(String str) {
        File file;
        if (!"mounted".equals(a())) {
            return null;
        }
        try {
            file = Environment.getExternalStorageDirectory();
        } catch (Exception e) {
            file = null;
        }
        if (file != null) {
            return new File(file, str);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        com.baidu.mobstat.az.a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r3 = r1;
        r1 = r0;
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:4:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            if (r4 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            r0 = 0
            if (r7 == 0) goto L_0x001f
            r1 = 32768(0x8000, float:4.5918E-41)
        L_0x0009:
            java.io.FileOutputStream r0 = r4.openFileOutput(r5, r1)     // Catch:{ Exception -> 0x0021, all -> 0x0026 }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0021, all -> 0x002e }
            java.lang.String r2 = "utf-8"
            byte[] r2 = r6.getBytes(r2)     // Catch:{ Exception -> 0x0021, all -> 0x002e }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0021, all -> 0x002e }
            com.baidu.mobstat.az.a(r1, r0)     // Catch:{ Exception -> 0x0021, all -> 0x002e }
            com.baidu.mobstat.az.a(r0)
            goto L_0x0002
        L_0x001f:
            r1 = 0
            goto L_0x0009
        L_0x0021:
            r1 = move-exception
            com.baidu.mobstat.az.a(r0)
            goto L_0x0002
        L_0x0026:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x002a:
            com.baidu.mobstat.az.a(r1)
            throw r0
        L_0x002e:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.at.a(android.content.Context, java.lang.String, java.lang.String, boolean):void");
    }

    public static void a(String str, String str2, boolean z) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            File a2 = a(str);
            if (a2 != null) {
                if (!a2.exists()) {
                    File parentFile = a2.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                    }
                }
                fileOutputStream = new FileOutputStream(a2, z);
                try {
                    az.a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStream);
                } catch (Exception e) {
                    fileOutputStream2 = fileOutputStream;
                    az.a(fileOutputStream2);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    fileOutputStream2 = fileOutputStream;
                    th = th2;
                    az.a(fileOutputStream2);
                    throw th;
                }
            } else {
                fileOutputStream = null;
            }
            az.a(fileOutputStream);
        } catch (Exception e2) {
            az.a(fileOutputStream2);
        } catch (Throwable th3) {
            th = th3;
            az.a(fileOutputStream2);
            throw th;
        }
    }

    public static String a(Context context, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = context.openFileInput(str);
            try {
                byte[] a2 = a((InputStream) fileInputStream);
                if (a2 != null) {
                    String str2 = new String(a2, "utf-8");
                    az.a(fileInputStream);
                    return str2;
                }
                az.a(fileInputStream);
                return "";
            } catch (Exception e) {
                fileInputStream2 = fileInputStream;
                az.a(fileInputStream2);
                return "";
            } catch (Throwable th2) {
                th = th2;
                az.a(fileInputStream);
                throw th;
            }
        } catch (Exception e2) {
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream = null;
            th = th4;
            az.a(fileInputStream);
            throw th;
        }
    }

    public static String b(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        File a2 = a(str);
        if (a2 != null && a2.exists()) {
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream = new FileInputStream(a2);
                try {
                    byte[] a3 = a((InputStream) fileInputStream);
                    if (a3 != null) {
                        String str2 = new String(a3, "utf-8");
                        az.a(fileInputStream);
                        return str2;
                    }
                    az.a(fileInputStream);
                } catch (Exception e) {
                    fileInputStream2 = fileInputStream;
                    az.a(fileInputStream2);
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    az.a(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                az.a(fileInputStream2);
                return "";
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = null;
                th = th4;
                az.a(fileInputStream);
                throw th;
            }
        }
        return "";
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (az.a(inputStream, byteArrayOutputStream)) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public static boolean b(Context context, String str) {
        return context.deleteFile(str);
    }

    public static boolean c(String str) {
        File a2 = a(str);
        if (a2 == null || !a2.isFile()) {
            return false;
        }
        return a2.delete();
    }

    public static boolean c(Context context, String str) {
        return context.getFileStreamPath(str).exists();
    }

    public static HttpURLConnection d(Context context, String str) throws IOException {
        return a(context, str, 50000, 50000);
    }

    @SuppressLint({"DefaultLocale"})
    public static HttpURLConnection a(Context context, String str, int i, int i2) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if (networkInfo2 == null || !networkInfo2.isAvailable()) {
            if (networkInfo != null && networkInfo.isAvailable()) {
                String extraInfo = networkInfo.getExtraInfo();
                String str2 = extraInfo != null ? extraInfo.toLowerCase() : "";
                if (str2.startsWith("cmwap") || str2.startsWith("uniwap") || str2.startsWith("3gwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(a);
                } else if (str2.startsWith("ctwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(b);
                }
            }
            httpURLConnection = null;
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (httpURLConnection == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        return httpURLConnection;
    }

    public static boolean e(Context context, String str) {
        boolean z = false;
        try {
            if (context.checkCallingOrSelfPermission(str) == 0) {
                z = true;
            }
        } catch (Exception e) {
        }
        if (!z) {
            am.c().b("[WARNING] not have permission " + str + ", please add it in AndroidManifest.xml according our developer doc");
        }
        return z;
    }
}
