package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.mobstat.ar.b;
import com.baidu.mobstat.ay.a;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import com.tencent.mid.core.Constants;
import com.tencent.mid.sotrage.StorageInterface;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class bb {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static final Pattern d = Pattern.compile("\\s*|\t|\r|\n");

    public static String a(Context context, String str) {
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return str2;
            }
            Object obj = null;
            if (applicationInfo.metaData != null) {
                obj = applicationInfo.metaData.get(str);
            }
            if (obj != null) {
                return obj.toString();
            }
            am.c().a("can't find information in AndroidManifest.xml for key " + str);
            return str2;
        } catch (Exception e) {
            return str2;
        }
    }

    public static String a(int i, Context context) {
        try {
            return b.c(i, a(context).getBytes());
        } catch (Exception e) {
            return "";
        }
    }

    public static String a(Context context) {
        return d.matcher(bc.a(context)).replaceAll("");
    }

    public static String b(Context context) {
        return a.a(a(context).getBytes()).toUpperCase(Locale.US);
    }

    public static int c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = e(context);
        } catch (Exception e) {
        }
        return displayMetrics.widthPixels;
    }

    public static int d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = e(context);
        } catch (Exception e) {
        }
        return displayMetrics.heightPixels;
    }

    public static DisplayMetrics e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            return 1;
        }
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    public static String h(Context context) {
        String str = "%s_%s_%s";
        String format = String.format("%s_%s_%s", new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
        try {
            if (at.e(context, "android.permission.ACCESS_FINE_LOCATION") || at.e(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                if (cellLocation == null) {
                    return format;
                }
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    return String.format("%s_%s_%s", new Object[]{String.format("%d", new Object[]{Integer.valueOf(gsmCellLocation.getCid())}), String.format("%d", new Object[]{Integer.valueOf(gsmCellLocation.getLac())}), Integer.valueOf(0)});
                }
                String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(StorageInterface.KEY_SPLITER);
                return String.format("%s_%s_%s", new Object[]{split[0], split[3], split[4]});
            }
        } catch (Exception e) {
        }
        return format;
    }

    public static String i(Context context) {
        String str = "";
        try {
            if (at.e(context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    return String.format("%s_%s_%s", new Object[]{Long.valueOf(lastKnownLocation.getTime()), Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude())});
                }
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static String b(int i, Context context) {
        String k = k(context);
        return TextUtils.isEmpty(k) ? "" : b.c(i, k.getBytes());
    }

    public static String j(Context context) {
        String str = "";
        if (VERSION.SDK_INT < 23) {
            return k(context);
        }
        return d();
    }

    public static String k(Context context) {
        try {
            if (at.e(context, Constants.PERMISSION_ACCESS_WIFI_STATE)) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(NetworkUtil.NETWORK_WIFI)).getConnectionInfo();
                if (connectionInfo != null) {
                    String macAddress = connectionInfo.getMacAddress();
                    if (!TextUtils.isEmpty(macAddress)) {
                        return macAddress;
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    @TargetApi(9)
    private static String d() {
        if (VERSION.SDK_INT < 9) {
            return "";
        }
        String str = "wlan0";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase(str)) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte valueOf : hardwareAddress) {
                        sb.append(String.format("%02x:", new Object[]{Byte.valueOf(valueOf)}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    private static String a(byte b2) {
        String str = "00" + Integer.toHexString(b2) + Config.TRACE_TODAY_VISIT_SPLIT;
        return str.substring(str.length() - 3);
    }

    public static String c(int i, Context context) {
        String d2 = d(i, context);
        String str = null;
        if (!TextUtils.isEmpty(d2)) {
            str = b.c(i, d2.getBytes());
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static String d(int i, Context context) {
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            a2 = e(i, context);
        }
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        return a2;
    }

    @SuppressLint({"NewApi"})
    public static String e(int i, Context context) {
        byte[] bArr;
        byte[] bArr2 = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration inetAddresses = networkInterface.getInetAddresses();
                while (true) {
                    if (!inetAddresses.hasMoreElements()) {
                        break;
                    }
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isAnyLocalAddress() && (inetAddress instanceof Inet4Address) && !inetAddress.isLoopbackAddress()) {
                        if (inetAddress.isSiteLocalAddress()) {
                            bArr = networkInterface.getHardwareAddress();
                        } else if (!inetAddress.isLinkLocalAddress()) {
                            bArr2 = networkInterface.getHardwareAddress();
                            break;
                        } else {
                            bArr = bArr2;
                        }
                        bArr2 = bArr;
                    }
                }
            }
        } catch (Exception e) {
        }
        if (bArr2 != null) {
            for (byte a2 : bArr2) {
                stringBuffer.append(a(a2));
            }
            return stringBuffer.substring(0, stringBuffer.length() - 1).replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        String b2 = b(i, context);
        if (b2 != null) {
            return b2.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        return b2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032 A[SYNTHETIC, Splitter:B:13:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0063 A[SYNTHETIC, Splitter:B:31:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a() {
        /*
            r0 = 0
            r7 = 13
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r1 = 20
            char[] r4 = new char[r1]     // Catch:{ Exception -> 0x006d, all -> 0x005d }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x006d, all -> 0x005d }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006d, all -> 0x005d }
            java.lang.String r5 = "/sys/class/net/eth0/address"
            r2.<init>(r5)     // Catch:{ Exception -> 0x006d, all -> 0x005d }
            r1.<init>(r2)     // Catch:{ Exception -> 0x006d, all -> 0x005d }
        L_0x0018:
            int r5 = r1.read(r4)     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            r2 = -1
            if (r5 == r2) goto L_0x0045
            int r2 = r4.length     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            if (r5 != r2) goto L_0x0036
            int r2 = r4.length     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            int r2 = r2 + -1
            char r2 = r4[r2]     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            if (r2 == r7) goto L_0x0036
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            r2.print(r4)     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            goto L_0x0018
        L_0x002f:
            r2 = move-exception
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ Exception -> 0x0067 }
        L_0x0035:
            return r0
        L_0x0036:
            r2 = 0
        L_0x0037:
            if (r2 >= r5) goto L_0x0018
            char r6 = r4[r2]     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            if (r6 == r7) goto L_0x0042
            char r6 = r4[r2]     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            r3.append(r6)     // Catch:{ Exception -> 0x002f, all -> 0x006b }
        L_0x0042:
            int r2 = r2 + 1
            goto L_0x0037
        L_0x0045:
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            java.lang.String r2 = r2.trim()     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            java.lang.String r3 = ":"
            java.lang.String r4 = ""
            java.lang.String r0 = r2.replaceAll(r3, r4)     // Catch:{ Exception -> 0x002f, all -> 0x006b }
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ Exception -> 0x005b }
            goto L_0x0035
        L_0x005b:
            r1 = move-exception
            goto L_0x0035
        L_0x005d:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x0061:
            if (r1 == 0) goto L_0x0066
            r1.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0066:
            throw r0
        L_0x0067:
            r1 = move-exception
            goto L_0x0035
        L_0x0069:
            r1 = move-exception
            goto L_0x0066
        L_0x006b:
            r0 = move-exception
            goto L_0x0061
        L_0x006d:
            r1 = move-exception
            r1 = r0
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bb.a():java.lang.String");
    }

    public static String a(Context context, int i) {
        String l = l(context);
        return TextUtils.isEmpty(l) ? "" : b.c(i, l.getBytes());
    }

    public static String l(Context context) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                String name = defaultAdapter.getName();
                if (name != null) {
                    return name;
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String f(int i, Context context) {
        String m = m(context);
        return TextUtils.isEmpty(m) ? "" : b.c(i, m.getBytes());
    }

    @SuppressLint({"NewApi"})
    public static String m(Context context) {
        String str = Build.BRAND;
        if ("4.1.1".equals(VERSION.RELEASE) && "TCT".equals(str)) {
            return "";
        }
        try {
            if (at.e(context, "android.permission.BLUETOOTH")) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    String address = defaultAdapter.getAddress();
                    if (address != null) {
                        return address;
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String n(Context context) {
        String o = o(context);
        if (TextUtils.isEmpty(o)) {
            return "";
        }
        return ar.a.a(o.getBytes());
    }

    public static String g(int i, Context context) {
        String o = o(context);
        if (TextUtils.isEmpty(o)) {
            return "";
        }
        return b.d(i, o.getBytes());
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083 A[Catch:{ Exception -> 0x010f }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String o(android.content.Context r14) {
        /*
            r5 = 1
            r3 = 0
            r13 = 30
            r1 = 0
            if (r14 != 0) goto L_0x000a
            java.lang.String r0 = ""
        L_0x0009:
            return r0
        L_0x000a:
            java.lang.String r0 = "android.permission.ACCESS_WIFI_STATE"
            boolean r0 = com.baidu.mobstat.at.e(r14, r0)
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = ""
            goto L_0x0009
        L_0x0015:
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r0 = com.baidu.mobstat.at.e(r14, r0)     // Catch:{ Exception -> 0x00b9 }
            if (r0 == 0) goto L_0x0114
            java.lang.String r0 = "location"
            java.lang.Object r0 = r14.getSystemService(r0)     // Catch:{ Exception -> 0x00b9 }
            android.location.LocationManager r0 = (android.location.LocationManager) r0     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r2 = "gps"
            boolean r0 = r0.isProviderEnabled(r2)     // Catch:{ Exception -> 0x00b9 }
        L_0x002b:
            r2 = r0
        L_0x002c:
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r14.getSystemService(r0)     // Catch:{ Throwable -> 0x00bd }
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch:{ Throwable -> 0x00bd }
            android.net.wifi.WifiInfo r4 = r0.getConnectionInfo()     // Catch:{ Throwable -> 0x00bd }
            java.util.List r0 = r0.getScanResults()     // Catch:{ Throwable -> 0x0111 }
            r7 = r0
            r8 = r4
        L_0x003e:
            if (r7 == 0) goto L_0x004e
            int r0 = r7.size()
            if (r0 == 0) goto L_0x004e
            com.baidu.mobstat.bb$1 r0 = new com.baidu.mobstat.bb$1
            r0.<init>()
            java.util.Collections.sort(r7, r0)
        L_0x004e:
            org.json.JSONArray r9 = new org.json.JSONArray
            r9.<init>()
            r6 = r1
        L_0x0054:
            if (r7 == 0) goto L_0x00c5
            int r0 = r7.size()
            if (r6 >= r0) goto L_0x00c5
            if (r6 >= r13) goto L_0x00c5
            java.lang.Object r0 = r7.get(r6)     // Catch:{ Exception -> 0x010f }
            android.net.wifi.ScanResult r0 = (android.net.wifi.ScanResult) r0     // Catch:{ Exception -> 0x010f }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010f }
            r10.<init>()     // Catch:{ Exception -> 0x010f }
            java.lang.String r4 = r0.BSSID     // Catch:{ Exception -> 0x010f }
            r10.append(r4)     // Catch:{ Exception -> 0x010f }
            java.lang.String r4 = "|"
            r10.append(r4)     // Catch:{ Exception -> 0x010f }
            java.lang.String r4 = r0.SSID     // Catch:{ Exception -> 0x010f }
            java.lang.String r11 = "\\|"
            java.lang.String r12 = ""
            java.lang.String r4 = r4.replaceAll(r11, r12)     // Catch:{ Exception -> 0x010f }
            int r11 = r4.length()     // Catch:{ Exception -> 0x010f }
            if (r11 <= r13) goto L_0x008a
            r11 = 0
            r12 = 30
            java.lang.String r4 = r4.substring(r11, r12)     // Catch:{ Exception -> 0x010f }
        L_0x008a:
            r10.append(r4)     // Catch:{ Exception -> 0x010f }
            java.lang.String r4 = "|"
            r10.append(r4)     // Catch:{ Exception -> 0x010f }
            int r4 = r0.level     // Catch:{ Exception -> 0x010f }
            r10.append(r4)     // Catch:{ Exception -> 0x010f }
            java.lang.String r4 = "|"
            r10.append(r4)     // Catch:{ Exception -> 0x010f }
            if (r8 == 0) goto L_0x00c3
            java.lang.String r0 = r0.BSSID     // Catch:{ Exception -> 0x010f }
            java.lang.String r4 = r8.getBSSID()     // Catch:{ Exception -> 0x010f }
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x010f }
            if (r0 == 0) goto L_0x00c3
            r0 = r5
        L_0x00ab:
            r10.append(r0)     // Catch:{ Exception -> 0x010f }
            java.lang.String r0 = r10.toString()     // Catch:{ Exception -> 0x010f }
            r9.put(r0)     // Catch:{ Exception -> 0x010f }
        L_0x00b5:
            int r0 = r6 + 1
            r6 = r0
            goto L_0x0054
        L_0x00b9:
            r0 = move-exception
            r2 = r1
            goto L_0x002c
        L_0x00bd:
            r0 = move-exception
            r0 = r3
        L_0x00bf:
            r7 = r3
            r8 = r0
            goto L_0x003e
        L_0x00c3:
            r0 = r1
            goto L_0x00ab
        L_0x00c5:
            int r0 = r9.length()
            if (r0 != 0) goto L_0x00ce
            r0 = r3
            goto L_0x0009
        L_0x00ce:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010a }
            r3.<init>()     // Catch:{ Exception -> 0x010a }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x010a }
            r3.append(r6)     // Catch:{ Exception -> 0x010a }
            java.lang.String r4 = "|"
            r3.append(r4)     // Catch:{ Exception -> 0x010a }
            if (r2 == 0) goto L_0x00e7
            r1 = r5
        L_0x00e7:
            r3.append(r1)     // Catch:{ Exception -> 0x010a }
            java.lang.String r1 = "|"
            r3.append(r1)     // Catch:{ Exception -> 0x010a }
            java.lang.String r1 = i(r14)     // Catch:{ Exception -> 0x010a }
            r3.append(r1)     // Catch:{ Exception -> 0x010a }
            java.lang.String r1 = "ap-list"
            r0.put(r1, r9)     // Catch:{ Exception -> 0x010a }
            java.lang.String r1 = "meta-data"
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x010a }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x010a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x010a }
            goto L_0x0009
        L_0x010a:
            r0 = move-exception
            java.lang.String r0 = ""
            goto L_0x0009
        L_0x010f:
            r0 = move-exception
            goto L_0x00b5
        L_0x0111:
            r0 = move-exception
            r0 = r4
            goto L_0x00bf
        L_0x0114:
            r0 = r1
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bb.o(android.content.Context):java.lang.String");
    }

    public static boolean p(Context context) {
        boolean z;
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
                z = false;
            } else {
                z = true;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public static String q(Context context) {
        String str = "";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return str;
            }
            String typeName = activeNetworkInfo.getTypeName();
            try {
                if (typeName.equals("WIFI") || activeNetworkInfo.getSubtypeName() == null) {
                    return typeName;
                }
                return activeNetworkInfo.getSubtypeName();
            } catch (Exception e) {
                return typeName;
            }
        } catch (Exception e2) {
            return str;
        }
    }

    public static String r(Context context) {
        if (context != null) {
            return context.getPackageName();
        }
        return "";
    }

    public static String h(int i, Context context) {
        String r = r(context);
        if (!TextUtils.isEmpty(r)) {
            try {
                return b.c(i, r.getBytes());
            } catch (Exception e) {
            }
        }
        return "";
    }

    private static String w(Context context) {
        String str;
        String str2 = a;
        if (str2 != null) {
            return str2;
        }
        try {
            List runningAppProcesses = ((ActivityManager) context.getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            int i = 0;
            while (true) {
                if (runningAppProcesses != null && i < runningAppProcesses.size()) {
                    RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) runningAppProcesses.get(i);
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == Process.myPid()) {
                        str = runningAppProcessInfo.processName;
                        break;
                    }
                    i++;
                } else {
                    str = str2;
                }
            }
        } catch (Exception e) {
            str = str2;
        }
        if (str == null) {
            str = "";
        }
        a = str;
        return str;
    }

    private static String b(Context context, String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf <= 0 || lastIndexOf + 1 >= str.length()) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }

    private static String c(Context context, String str) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        String str2 = applicationInfo.processName;
        if (str2 == null || str2.equals(str)) {
            str = null;
        }
        return str;
    }

    public static String s(Context context) {
        String str = b;
        if (str == null) {
            String w = w(context);
            str = b(context, w);
            if (TextUtils.isEmpty(str)) {
                str = c(context, w);
            }
            if (str == null) {
                str = "";
            }
            b = str;
        }
        return str;
    }

    public static String t(Context context) {
        String str = "";
        String w = w(context);
        if (w == null) {
            return str;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
        } catch (Exception e) {
        }
        if (packageInfo == null) {
            return str;
        }
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr == null) {
            return str;
        }
        int length = serviceInfoArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            ServiceInfo serviceInfo = serviceInfoArr[i];
            if (w.equals(serviceInfo.processName)) {
                str = serviceInfo.name;
                break;
            }
            i++;
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static boolean u(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        } catch (Exception e) {
            return false;
        }
    }

    public static String v(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME);
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Config.MODEL, memoryInfo.availMem);
            jSONObject.put("l", memoryInfo.lowMemory);
            jSONObject.put("t", memoryInfo.threshold);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_mem", jSONArray);
            jSONObject2.put("meta-data", sb.toString());
            return ar.a.a(jSONObject2.toString().getBytes());
        } catch (Exception e) {
            return "";
        }
    }

    public static String b() {
        if (c != null) {
            return c;
        }
        String str = "";
        if (!TextUtils.isEmpty(a("ro.miui.ui.version.name"))) {
            str = "miui";
        } else if (!TextUtils.isEmpty(a("ro.build.version.opporom"))) {
            str = "coloros";
        } else if (!TextUtils.isEmpty(a("ro.build.version.emui"))) {
            str = "emui";
        } else if (!TextUtils.isEmpty(a("ro.vivo.os.version"))) {
            str = "funtouch";
        } else if (!TextUtils.isEmpty(a("ro.smartisan.version"))) {
            str = "smartisan";
        }
        if (TextUtils.isEmpty(str)) {
            String a2 = a("ro.build.display.id");
            if (!TextUtils.isEmpty(a2) && a2.contains("Flyme")) {
                str = "flyme";
            }
        }
        c = str;
        return c;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050 A[SYNTHETIC, Splitter:B:23:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            r2.<init>()     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            java.lang.String r3 = "getprop "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            java.lang.StringBuilder r2 = r2.append(r6)     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Exception -> 0x003b, all -> 0x0049 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0066, all -> 0x005f }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0066, all -> 0x005f }
            java.io.InputStream r4 = r1.getInputStream()     // Catch:{ Exception -> 0x0066, all -> 0x005f }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0066, all -> 0x005f }
            r4 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0066, all -> 0x005f }
            java.lang.String r0 = r2.readLine()     // Catch:{ Exception -> 0x0069, all -> 0x0064 }
            if (r2 == 0) goto L_0x0035
            r2.close()     // Catch:{ Exception -> 0x0059 }
        L_0x0035:
            if (r1 == 0) goto L_0x003a
            r1.destroy()
        L_0x003a:
            return r0
        L_0x003b:
            r1 = move-exception
            r1 = r0
            r2 = r0
        L_0x003e:
            if (r2 == 0) goto L_0x0043
            r2.close()     // Catch:{ Exception -> 0x005b }
        L_0x0043:
            if (r1 == 0) goto L_0x003a
            r1.destroy()
            goto L_0x003a
        L_0x0049:
            r1 = move-exception
            r2 = r0
            r5 = r0
            r0 = r1
            r1 = r5
        L_0x004e:
            if (r2 == 0) goto L_0x0053
            r2.close()     // Catch:{ Exception -> 0x005d }
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.destroy()
        L_0x0058:
            throw r0
        L_0x0059:
            r2 = move-exception
            goto L_0x0035
        L_0x005b:
            r2 = move-exception
            goto L_0x0043
        L_0x005d:
            r2 = move-exception
            goto L_0x0053
        L_0x005f:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x004e
        L_0x0064:
            r0 = move-exception
            goto L_0x004e
        L_0x0066:
            r2 = move-exception
            r2 = r0
            goto L_0x003e
        L_0x0069:
            r3 = move-exception
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bb.a(java.lang.String):java.lang.String");
    }

    public static Boolean c() {
        Boolean valueOf = Boolean.valueOf(true);
        try {
            Class cls = Class.forName("com.baidu.disasterrecovery.MtjAdapter");
            if (cls != null) {
                Object invoke = cls.getDeclaredMethod("shouldUploadOther", new Class[0]).invoke(null, new Object[0]);
                if (invoke != null && (invoke instanceof Boolean)) {
                    return (Boolean) invoke;
                }
            }
            return valueOf;
        } catch (Exception e) {
            return valueOf;
        }
    }
}
