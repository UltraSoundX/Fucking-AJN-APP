package com.tencent.bigdata.dataacquisition.a;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.stub.StubApp;
import com.tencent.mid.core.Constants;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    private static String a = null;
    private static DisplayMetrics b = null;
    private static int c = -1;
    private static long d = -1;
    private static C0074a e = null;

    /* renamed from: com.tencent.bigdata.dataacquisition.a.a$a reason: collision with other inner class name */
    static class C0074a {

        /* renamed from: com.tencent.bigdata.dataacquisition.a.a$a$a reason: collision with other inner class name */
        class C0075a implements FileFilter {
            C0075a() {
            }

            public boolean accept(File file) {
                return Pattern.matches("cpu[0-9]", file.getName());
            }
        }

        C0074a() {
        }

        static int a() {
            try {
                return new File("/sys/devices/system/cpu/").listFiles(new C0075a()).length;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }

        static int b() {
            int i = 0;
            InputStream inputStream = null;
            String str = "";
            try {
                inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
                byte[] bArr = new byte[24];
                while (inputStream.read(bArr) != -1) {
                    str = str + new String(bArr);
                }
                String trim = str.trim();
                if (trim.length() > 0) {
                    i = Integer.valueOf(trim).intValue();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                com.tencent.bigdata.dataacquisition.b.a.a("getMaxCpuFreq err", (Throwable) e2);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                    }
                }
            }
            return i * 1000;
        }

        static int c() {
            int i = 0;
            InputStream inputStream = null;
            String str = "";
            try {
                inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
                byte[] bArr = new byte[24];
                while (inputStream.read(bArr) != -1) {
                    str = str + new String(bArr);
                }
                String trim = str.trim();
                if (trim.length() > 0) {
                    i = Integer.valueOf(trim).intValue();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e2) {
                com.tencent.bigdata.dataacquisition.b.a.a("getMinCpuFreq", (Throwable) e2);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                    }
                }
            }
            return i * 1000;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x003b A[SYNTHETIC, Splitter:B:22:0x003b] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0045 A[SYNTHETIC, Splitter:B:28:0x0045] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static java.lang.String d() {
            /*
                r2 = 0
                java.io.FileReader r0 = new java.io.FileReader     // Catch:{ Throwable -> 0x0032, all -> 0x0041 }
                java.lang.String r1 = "/proc/cpuinfo"
                r0.<init>(r1)     // Catch:{ Throwable -> 0x0032, all -> 0x0041 }
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0032, all -> 0x0041 }
                r1.<init>(r0)     // Catch:{ Throwable -> 0x0032, all -> 0x0041 }
                java.lang.String r0 = r1.readLine()     // Catch:{ Throwable -> 0x0051 }
                boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0051 }
                if (r2 != 0) goto L_0x002a
                java.lang.String r2 = ":\\s+"
                r3 = 2
                java.lang.String[] r0 = r0.split(r2, r3)     // Catch:{ Throwable -> 0x0051 }
                int r2 = r0.length     // Catch:{ Throwable -> 0x0051 }
                if (r2 <= 0) goto L_0x002a
                r2 = 1
                r0 = r0[r2]     // Catch:{ Throwable -> 0x0051 }
                if (r1 == 0) goto L_0x0029
                r1.close()     // Catch:{ Exception -> 0x0049 }
            L_0x0029:
                return r0
            L_0x002a:
                if (r1 == 0) goto L_0x002f
                r1.close()     // Catch:{ Exception -> 0x004b }
            L_0x002f:
                java.lang.String r0 = ""
                goto L_0x0029
            L_0x0032:
                r0 = move-exception
                r1 = r2
            L_0x0034:
                java.lang.String r2 = "getCpuName"
                com.tencent.bigdata.dataacquisition.b.a.a(r2, r0)     // Catch:{ all -> 0x004f }
                if (r1 == 0) goto L_0x002f
                r1.close()     // Catch:{ Exception -> 0x003f }
                goto L_0x002f
            L_0x003f:
                r0 = move-exception
                goto L_0x002f
            L_0x0041:
                r0 = move-exception
                r1 = r2
            L_0x0043:
                if (r1 == 0) goto L_0x0048
                r1.close()     // Catch:{ Exception -> 0x004d }
            L_0x0048:
                throw r0
            L_0x0049:
                r1 = move-exception
                goto L_0x0029
            L_0x004b:
                r0 = move-exception
                goto L_0x002f
            L_0x004d:
                r1 = move-exception
                goto L_0x0048
            L_0x004f:
                r0 = move-exception
                goto L_0x0043
            L_0x0051:
                r0 = move-exception
                goto L_0x0034
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bigdata.dataacquisition.a.a.C0074a.d():java.lang.String");
        }
    }

    static class b {
        private static int a = -1;

        public static boolean a() {
            if (a == 1) {
                return true;
            }
            if (a == 0) {
                return false;
            }
            String[] strArr = {"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
            int i = 0;
            while (i < strArr.length) {
                try {
                    File file = new File(strArr[i] + "su");
                    if (file == null || !file.exists()) {
                        i++;
                    } else {
                        a = 1;
                        return true;
                    }
                } catch (Exception e) {
                }
            }
            a = 0;
            return false;
        }
    }

    public static Integer a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String a() {
        if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
            return "Intel";
        }
        String str = "";
        try {
            byte[] bArr = new byte[1024];
            new RandomAccessFile("/proc/cpuinfo", "r").read(bArr);
            String str2 = new String(bArr);
            int indexOf = str2.indexOf(0);
            return indexOf != -1 ? str2.substring(0, indexOf) : str2;
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("getCpuString", th);
            return str;
        }
    }

    public static String b() {
        String str;
        String a2 = a();
        if (a2.contains("ARMv5")) {
            str = "armv5";
        } else if (a2.contains("ARMv6")) {
            str = "armv6";
        } else if (a2.contains("ARMv7")) {
            str = "armv7";
        } else if (!a2.contains("Intel")) {
            return "unknown";
        } else {
            str = "x86";
        }
        return a2.contains("neon") ? str + "_neon" : a2.contains("vfpv3") ? str + "_vfpv3" : a2.contains(" vfp") ? str + "_vfp" : str + "_none";
    }

    public static boolean b(Context context) {
        try {
            if (com.tencent.bigdata.dataacquisition.b.a.a(context, Constants.PERMISSION_ACCESS_NETWORK_STATE)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        return true;
                    }
                    com.tencent.bigdata.dataacquisition.b.a.a("Network error");
                }
            }
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("isNetworkAvailable error", th);
        }
        return false;
    }

    public static byte c(Context context) {
        if (context != null) {
            try {
                if (com.tencent.bigdata.dataacquisition.b.a.a(context, Constants.PERMISSION_ACCESS_NETWORK_STATE)) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    if (connectivityManager == null) {
                        return 0;
                    }
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null) {
                        return -1;
                    }
                    if (!activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                        return -1;
                    }
                    if (activeNetworkInfo.getType() == 1) {
                        return 1;
                    }
                    if (activeNetworkInfo.getType() != 0) {
                        return 0;
                    }
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return 2;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 15:
                            return 3;
                        case 13:
                            return 4;
                        default:
                            return 0;
                    }
                }
            } catch (Exception e2) {
                com.tencent.bigdata.dataacquisition.b.a.a("getNetworkType: ", (Throwable) e2);
            }
        }
        return -1;
    }

    public static String c() {
        long d2 = d() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf(d2);
    }

    public static long d() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static boolean d(Context context) {
        try {
            if (com.tencent.bigdata.dataacquisition.b.a.a(context, Constants.PERMISSION_ACCESS_NETWORK_STATE)) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    return "WIFI".equalsIgnoreCase(activeNetworkInfo.getTypeName());
                }
            }
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("Check isWiFiActive error", th);
        }
        return false;
    }

    public static String e(Context context) {
        try {
            if (!com.tencent.bigdata.dataacquisition.b.a.a(context, Constants.PERMISSION_INTERNET) || !com.tencent.bigdata.dataacquisition.b.a.a(context, Constants.PERMISSION_ACCESS_NETWORK_STATE)) {
                com.tencent.bigdata.dataacquisition.b.a.b("can not get the permission of android.permission.ACCESS_WIFI_STATE");
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                String typeName = activeNetworkInfo.getTypeName();
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (typeName != null) {
                    return typeName.equalsIgnoreCase("WIFI") ? "WIFI" : typeName.equalsIgnoreCase("MOBILE") ? extraInfo == null ? "MOBILE" : extraInfo : extraInfo == null ? typeName : extraInfo;
                }
            }
            return null;
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.b(th);
        }
    }

    public static boolean e() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e2) {
            com.tencent.bigdata.dataacquisition.b.a.a("isSDCardMounted", (Throwable) e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040 A[SYNTHETIC, Splitter:B:16:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a A[SYNTHETIC, Splitter:B:22:0x004a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long f() {
        /*
            r0 = 0
            long r2 = d
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x000b
            long r0 = d
        L_0x000a:
            return r0
        L_0x000b:
            java.lang.String r2 = "/proc/meminfo"
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ IOException -> 0x003c, all -> 0x0046 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x003c, all -> 0x0046 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003c, all -> 0x0046 }
            r5 = 8192(0x2000, float:1.14794E-41)
            r2.<init>(r4, r5)     // Catch:{ IOException -> 0x003c, all -> 0x0046 }
            java.lang.String r3 = r2.readLine()     // Catch:{ IOException -> 0x0054, all -> 0x0052 }
            java.lang.String r4 = "\\s+"
            java.lang.String[] r3 = r3.split(r4)     // Catch:{ IOException -> 0x0054, all -> 0x0052 }
            r4 = 1
            r3 = r3[r4]     // Catch:{ IOException -> 0x0054, all -> 0x0052 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x0054, all -> 0x0052 }
            int r0 = r3.intValue()     // Catch:{ IOException -> 0x0054, all -> 0x0052 }
            int r0 = r0 * 1024
            long r0 = (long) r0
            if (r2 == 0) goto L_0x0037
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x0037:
            d = r0
            long r0 = d
            goto L_0x000a
        L_0x003c:
            r2 = move-exception
            r2 = r3
        L_0x003e:
            if (r2 == 0) goto L_0x0037
            r2.close()     // Catch:{ Exception -> 0x0044 }
            goto L_0x0037
        L_0x0044:
            r2 = move-exception
            goto L_0x0037
        L_0x0046:
            r0 = move-exception
            r2 = r3
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ Exception -> 0x0050 }
        L_0x004d:
            throw r0
        L_0x004e:
            r2 = move-exception
            goto L_0x0037
        L_0x0050:
            r1 = move-exception
            goto L_0x004d
        L_0x0052:
            r0 = move-exception
            goto L_0x0048
        L_0x0054:
            r3 = move-exception
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bigdata.dataacquisition.a.a.f():long");
    }

    public static DisplayMetrics f(Context context) {
        if (b == null) {
            b = new DisplayMetrics();
            ((WindowManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("window")).getDefaultDisplay().getMetrics(b);
        }
        return b;
    }

    public static String g(Context context) {
        if (a == null) {
            a = Build.MODEL;
        }
        return a;
    }

    public static boolean h(Context context) {
        return ((SensorManager) context.getSystemService("sensor")) != null;
    }

    public static int i(Context context) {
        if (c >= 0) {
            return c;
        }
        try {
            if (b.a()) {
                return 1;
            }
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("call hasRootAccess Error:", th);
        }
        return 0;
    }

    public static String j(Context context) {
        try {
            if (com.tencent.bigdata.dataacquisition.b.a.a(context, Constants.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equals("mounted")) {
                    return null;
                }
                String path = Environment.getExternalStorageDirectory().getPath();
                if (path == null) {
                    return null;
                }
                StatFs statFs = new StatFs(path);
                long blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000;
                return String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(blockCount);
            }
            com.tencent.bigdata.dataacquisition.b.a.a("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("getExternalStorageInfo err:", th);
            return null;
        }
    }

    public static int k(Context context) {
        if (com.tencent.bigdata.dataacquisition.b.a.a(context, "android.permission.BLUETOOTH")) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                return -1;
            }
            if (defaultAdapter.isEnabled()) {
                return 1;
            }
        }
        return 0;
    }

    public static String l(Context context) {
        return String.valueOf(p(context) / 1000000) + "/" + String.valueOf(f() / 1000000);
    }

    public static JSONObject m(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            q(context);
            int b2 = C0074a.b();
            if (b2 > 0) {
                jSONObject.put("fx", b2 / 1000000);
            }
            q(context);
            int c2 = C0074a.c();
            if (c2 > 0) {
                jSONObject.put("fn", c2 / 1000000);
            }
            q(context);
            int a2 = C0074a.a();
            if (a2 > 0) {
                jSONObject.put("n", a2);
            }
            q(context);
            String d2 = C0074a.d();
            if (d2 != null && d2.length() == 0) {
                q(context);
                jSONObject.put("na", C0074a.d());
            }
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("getCpuInfo", th);
        }
        return jSONObject;
    }

    public static String n(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < sensorList.size(); i++) {
                        sb.append(((Sensor) sensorList.get(i)).getType());
                        if (i != sensorList.size() - 1) {
                            sb.append(StorageInterface.KEY_SPLITER);
                        }
                    }
                    return sb.toString();
                }
            }
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("getAllSensors", th);
        }
        return "";
    }

    public static JSONArray o(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null && sensorList.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= sensorList.size()) {
                            return jSONArray;
                        }
                        Sensor sensor = (Sensor) sensorList.get(i2);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("name", sensor.getName());
                        jSONObject.put("vendor", sensor.getVendor());
                        jSONArray.put(jSONObject);
                        i = i2 + 1;
                    }
                }
            }
        } catch (Throwable th) {
            com.tencent.bigdata.dataacquisition.b.a.a("getSensors:" + th.toString());
        }
        return null;
    }

    private static long p(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    private static synchronized C0074a q(Context context) {
        C0074a aVar;
        synchronized (a.class) {
            if (e == null) {
                e = new C0074a();
            }
            aVar = e;
        }
        return aVar;
    }
}
