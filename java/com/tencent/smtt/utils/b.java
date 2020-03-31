package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* compiled from: AppUtil */
public class b {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";

    public static String a(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            return z;
        }
    }

    public static int b(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            return z;
        }
    }

    public static String a(Context context, String str) {
        String str2;
        boolean z = false;
        try {
            try {
                return String.valueOf(Integer.toHexString(Integer.parseInt(String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str)))));
            } catch (Exception e2) {
                return str2;
            }
        } catch (Exception e3) {
            return z;
        }
    }

    public static String c(Context context) {
        String str = "";
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String d(Context context) {
        String str = "";
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0060 A[SYNTHETIC, Splitter:B:27:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0065 A[SYNTHETIC, Splitter:B:30:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006f A[SYNTHETIC, Splitter:B:36:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0074 A[SYNTHETIC, Splitter:B:39:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a() {
        /*
            r2 = 0
            java.lang.String r0 = c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = c
        L_0x000b:
            return r0
        L_0x000c:
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x004e, all -> 0x006b }
            java.lang.String r1 = "getprop ro.product.cpu.abi"
            java.lang.Process r0 = r0.exec(r1)     // Catch:{ Throwable -> 0x004e, all -> 0x006b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x004e, all -> 0x006b }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Throwable -> 0x004e, all -> 0x006b }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x004e, all -> 0x006b }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0085 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0085 }
            java.lang.String r0 = r1.readLine()     // Catch:{ Throwable -> 0x0088, all -> 0x0082 }
            java.lang.String r2 = "x86"
            boolean r0 = r0.contains(r2)     // Catch:{ Throwable -> 0x0088, all -> 0x0082 }
            if (r0 == 0) goto L_0x0043
            java.lang.String r0 = "i686"
            java.lang.String r0 = a(r0)     // Catch:{ Throwable -> 0x0088, all -> 0x0082 }
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ IOException -> 0x0078 }
        L_0x003b:
            if (r3 == 0) goto L_0x000b
            r3.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x000b
        L_0x0041:
            r1 = move-exception
            goto L_0x000b
        L_0x0043:
            java.lang.String r0 = "os.arch"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ Throwable -> 0x0088, all -> 0x0082 }
            java.lang.String r0 = a(r0)     // Catch:{ Throwable -> 0x0088, all -> 0x0082 }
            goto L_0x0036
        L_0x004e:
            r0 = move-exception
            r1 = r0
            r3 = r2
        L_0x0051:
            java.lang.String r0 = "os.arch"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ all -> 0x0080 }
            java.lang.String r0 = a(r0)     // Catch:{ all -> 0x0080 }
            r1.printStackTrace()     // Catch:{ all -> 0x0080 }
            if (r2 == 0) goto L_0x0063
            r2.close()     // Catch:{ IOException -> 0x007a }
        L_0x0063:
            if (r3 == 0) goto L_0x000b
            r3.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x000b
        L_0x0069:
            r1 = move-exception
            goto L_0x000b
        L_0x006b:
            r0 = move-exception
            r3 = r2
        L_0x006d:
            if (r2 == 0) goto L_0x0072
            r2.close()     // Catch:{ IOException -> 0x007c }
        L_0x0072:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch:{ IOException -> 0x007e }
        L_0x0077:
            throw r0
        L_0x0078:
            r1 = move-exception
            goto L_0x003b
        L_0x007a:
            r1 = move-exception
            goto L_0x0063
        L_0x007c:
            r1 = move-exception
            goto L_0x0072
        L_0x007e:
            r1 = move-exception
            goto L_0x0077
        L_0x0080:
            r0 = move-exception
            goto L_0x006d
        L_0x0082:
            r0 = move-exception
            r2 = r1
            goto L_0x006d
        L_0x0085:
            r0 = move-exception
            r1 = r0
            goto L_0x0051
        L_0x0088:
            r0 = move-exception
            r2 = r1
            r1 = r0
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a():java.lang.String");
    }

    public static String e(Context context) {
        if (TextUtils.isEmpty(d)) {
            if (VERSION.SDK_INT < 23) {
                try {
                    WifiManager wifiManager = (WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
                    WifiInfo connectionInfo = wifiManager == null ? null : wifiManager.getConnectionInfo();
                    d = connectionInfo == null ? "" : connectionInfo.getMacAddress();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                d = b();
            }
        }
        return d;
    }

    public static String b() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte valueOf : hardwareAddress) {
                        sb.append(String.format("%02X:", new Object[]{Byte.valueOf(valueOf)}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e2) {
        }
        return Config.DEF_MAC_ID;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    public static String f(Context context) {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        try {
            e = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c A[Catch:{ Throwable -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r4, boolean r5, java.io.File r6) {
        /*
            java.lang.String r0 = ""
            if (r6 == 0) goto L_0x000a
            boolean r0 = r6.exists()
            if (r0 != 0) goto L_0x000d
        L_0x000a:
            java.lang.String r0 = ""
        L_0x000c:
            return r0
        L_0x000d:
            if (r5 == 0) goto L_0x0038
            r2 = 0
            r0 = 2
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x00d2, all -> 0x00e2 }
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00d2, all -> 0x00e2 }
            java.lang.String r3 = "r"
            r1.<init>(r6, r3)     // Catch:{ Exception -> 0x00d2, all -> 0x00e2 }
            r1.read(r0)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x00ef }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r0 = "PK"
            boolean r0 = r2.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x00ef }
            if (r0 != 0) goto L_0x0035
            java.lang.String r0 = ""
            r1.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x000c
        L_0x0030:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000c
        L_0x0035:
            r1.close()     // Catch:{ IOException -> 0x00cc }
        L_0x0038:
            android.content.Context r0 = r4.getApplicationContext()     // Catch:{ Throwable -> 0x0061 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Throwable -> 0x0061 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ Throwable -> 0x0061 }
            java.lang.String r1 = "com.jd.jrapp"
            boolean r0 = r0.contains(r1)     // Catch:{ Throwable -> 0x0061 }
            if (r0 == 0) goto L_0x0069
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Throwable -> 0x0061 }
            java.lang.String r0 = a(r6)     // Catch:{ Throwable -> 0x0061 }
            if (r0 == 0) goto L_0x0069
            java.lang.String r1 = "AppUtil"
            java.lang.String r2 = "[AppUtil.getSignatureFromApk]  #2"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ Throwable -> 0x0061 }
            goto L_0x000c
        L_0x0061:
            r0 = move-exception
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #3"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x0069:
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #4"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r0 = 0
            java.lang.String r0 = a(r4, r6, r0)
            java.lang.String r1 = "AppUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[AppUtil.getSignatureFromApk]  android api signature="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = a(r6)
            java.lang.String r1 = "AppUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[AppUtil.getSignatureFromApk]  java get signature="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
        L_0x00ab:
            if (r0 != 0) goto L_0x000c
            r0 = 1
            java.lang.String r0 = a(r4, r6, r0)
            java.lang.String r1 = "AppUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[AppUtil.getSignatureFromApk]  android reflection signature="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            goto L_0x000c
        L_0x00cc:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0038
        L_0x00d2:
            r0 = move-exception
            r1 = r2
        L_0x00d4:
            r0.printStackTrace()     // Catch:{ all -> 0x00ed }
            r1.close()     // Catch:{ IOException -> 0x00dc }
            goto L_0x0038
        L_0x00dc:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0038
        L_0x00e2:
            r0 = move-exception
            r1 = r2
        L_0x00e4:
            r1.close()     // Catch:{ IOException -> 0x00e8 }
        L_0x00e7:
            throw r0
        L_0x00e8:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00e7
        L_0x00ed:
            r0 = move-exception
            goto L_0x00e4
        L_0x00ef:
            r0 = move-exception
            goto L_0x00d4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, boolean, java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[Catch:{ Exception -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[Catch:{ Exception -> 0x003c }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r4, java.io.File r5, boolean r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x0024
            java.lang.String r1 = r5.getAbsolutePath()     // Catch:{ Exception -> 0x003c }
            r2 = 65
            android.content.pm.PackageInfo r1 = a(r1, r2)     // Catch:{ Exception -> 0x003c }
        L_0x000d:
            if (r1 == 0) goto L_0x003a
            android.content.pm.Signature[] r2 = r1.signatures     // Catch:{ Exception -> 0x003c }
            if (r2 == 0) goto L_0x0033
            android.content.pm.Signature[] r2 = r1.signatures     // Catch:{ Exception -> 0x003c }
            int r2 = r2.length     // Catch:{ Exception -> 0x003c }
            if (r2 <= 0) goto L_0x0033
            android.content.pm.Signature[] r1 = r1.signatures     // Catch:{ Exception -> 0x003c }
            r2 = 0
            r1 = r1[r2]     // Catch:{ Exception -> 0x003c }
        L_0x001d:
            if (r1 == 0) goto L_0x0023
            java.lang.String r0 = r1.toCharsString()     // Catch:{ Exception -> 0x003c }
        L_0x0023:
            return r0
        L_0x0024:
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ Exception -> 0x003c }
            java.lang.String r2 = r5.getAbsolutePath()     // Catch:{ Exception -> 0x003c }
            r3 = 65
            android.content.pm.PackageInfo r1 = r1.getPackageArchiveInfo(r2, r3)     // Catch:{ Exception -> 0x003c }
            goto L_0x000d
        L_0x0033:
            java.lang.String r1 = "AppUtil"
            java.lang.String r2 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.w(r1, r2)     // Catch:{ Exception -> 0x003c }
        L_0x003a:
            r1 = r0
            goto L_0x001d
        L_0x003c:
            r1 = move-exception
            java.lang.String r1 = "AppUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getSign "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r3 = "failed"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, java.io.File, boolean):java.lang.String");
    }

    private static String a(File file) {
        String str;
        try {
            JarFile jarFile = new JarFile(file);
            byte[] bArr = new byte[8192];
            String a2 = a(a(jarFile, jarFile.getJarEntry("AndroidManifest.xml"), bArr)[0].getEncoded());
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) entries.nextElement();
                String name = jarEntry.getName();
                if (name != null) {
                    Certificate[] a3 = a(jarFile, jarEntry, bArr);
                    if (a3 != null) {
                        str = a(a3[0].getEncoded());
                    } else {
                        str = null;
                    }
                    if (str == null) {
                        if (!name.startsWith("META-INF/")) {
                            return null;
                        }
                    } else if (!str.equals(a2)) {
                        return null;
                    }
                }
            }
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static PackageInfo a(String str, int i) {
        Class cls;
        try {
            Class cls2 = Class.forName("android.content.pm.PackageParser");
            Class[] declaredClasses = cls2.getDeclaredClasses();
            int length = declaredClasses.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    cls = null;
                    break;
                }
                cls = declaredClasses[i2];
                if (cls.getName().compareTo("android.content.pm.PackageParser$Package") == 0) {
                    break;
                }
                i2++;
            }
            Constructor constructor = cls2.getConstructor(new Class[]{String.class});
            Method declaredMethod = cls2.getDeclaredMethod("parsePackage", new Class[]{File.class, String.class, DisplayMetrics.class, Integer.TYPE});
            Method declaredMethod2 = cls2.getDeclaredMethod("collectCertificates", new Class[]{cls, Integer.TYPE});
            Method declaredMethod3 = cls2.getDeclaredMethod("generatePackageInfo", new Class[]{cls, int[].class, Integer.TYPE, Long.TYPE, Long.TYPE});
            constructor.setAccessible(true);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            declaredMethod3.setAccessible(true);
            Object newInstance = constructor.newInstance(new Object[]{str});
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Object invoke = declaredMethod.invoke(newInstance, new Object[]{new File(str), str, displayMetrics, Integer.valueOf(0)});
            if (invoke == null) {
                return null;
            }
            if ((i & 64) != 0) {
                declaredMethod2.invoke(newInstance, new Object[]{invoke, Integer.valueOf(0)});
            }
            return (PackageInfo) declaredMethod3.invoke(null, new Object[]{invoke, null, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(0)});
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) throws Exception {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, bArr.length) != -1);
        inputStream.close();
        if (jarEntry != null) {
            return jarEntry.getCertificates();
        }
        return null;
    }

    private static String a(byte[] bArr) {
        int i;
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            byte b2 = bArr[i2];
            int i3 = (b2 >> 4) & 15;
            cArr[i2 * 2] = (char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48);
            byte b3 = b2 & 15;
            int i4 = (i2 * 2) + 1;
            if (b3 >= 10) {
                i = (b3 + 97) - 10;
            } else {
                i = b3 + 48;
            }
            cArr[i4] = (char) i;
        }
        return new String(cArr);
    }
}
