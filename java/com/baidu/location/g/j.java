package com.baidu.location.g;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.d;
import com.baidu.location.e.a;
import com.baidu.location.e.b;
import com.baidu.location.e.e;
import com.baidu.location.e.h;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.core.Constants;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

public class j {
    public static float A = 2.2f;
    public static float B = 2.3f;
    public static float C = 3.8f;
    public static int D = 3;
    public static int E = 10;
    public static int F = 2;
    public static int G = 7;
    public static int H = 20;
    public static int I = 70;
    public static int J = 120;
    public static float K = 2.0f;
    public static float L = 10.0f;
    public static float M = 50.0f;
    public static float N = 200.0f;
    public static int O = 16;
    public static float P = 0.9f;
    public static int Q = 10000;
    public static float R = 0.5f;
    public static float S = 0.0f;
    public static float T = 0.1f;
    public static int U = 30;
    public static int V = 100;
    public static int W = 0;
    public static int X = 0;
    public static int Y = 0;
    public static int Z = 420000;
    public static boolean a = false;
    private static String aA = "http://loc.map.baidu.com/rtbu.php";
    private static String aB = "http://loc.map.baidu.com/iofd.php";
    private static String aC = "http://loc.map.baidu.com/wloc";
    public static boolean aa = true;
    public static boolean ab = true;
    public static int ac = 20;
    public static int ad = ErrorCode.ERROR_CODE_LOAD_BASE;
    public static int ae = 1000;
    public static int af = Integer.MAX_VALUE;
    public static long ag = 900000;
    public static long ah = 420000;
    public static long ai = 180000;
    public static long aj = 0;
    public static long ak = 15;
    public static long al = 300000;
    public static int am = 1000;
    public static int an = 0;
    public static int ao = Config.SESSION_PERIOD;
    public static int ap = Config.SESSION_PERIOD;
    public static float aq = 10.0f;
    public static float ar = 6.0f;
    public static float as = 10.0f;
    public static int at = 60;
    public static int au = 70;
    public static int av = 6;
    private static String aw = "http://loc.map.baidu.com/sdk.php";
    private static String ax = "http://loc.map.baidu.com/user_err.php";
    private static String ay = "http://loc.map.baidu.com/oqur.php";
    private static String az = "http://loc.map.baidu.com/tcu.php";
    public static boolean b = false;
    public static boolean c = false;
    public static int d = 0;
    public static String e = "http://loc.map.baidu.com/sdk_ep.php";
    public static String f = "https://loc.map.baidu.com/sdk.php";
    public static String g = "no";
    public static boolean h = false;
    public static boolean i = false;
    public static boolean j = false;
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    public static String n = "gcj02";
    public static String o = "";
    public static boolean p = true;

    /* renamed from: q reason: collision with root package name */
    public static int f379q = 3;
    public static double r = 0.0d;
    public static double s = 0.0d;
    public static double t = 0.0d;
    public static double u = 0.0d;
    public static int v = 0;
    public static byte[] w = null;
    public static boolean x = false;
    public static int y = 0;
    public static float z = 1.1f;

    public static int a(Context context, String str) {
        boolean z2;
        try {
            z2 = context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
        } catch (Exception e2) {
            z2 = true;
        }
        return !z2 ? 0 : 1;
    }

    public static int a(String str, String str2, String str3) {
        int i2 = ExploreByTouchHelper.INVALID_ID;
        if (str == null || str.equals("")) {
            return i2;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return i2;
        }
        int length = indexOf + str2.length();
        int indexOf2 = str.indexOf(str3, length);
        if (indexOf2 == -1) {
            return i2;
        }
        String substring = str.substring(length, indexOf2);
        if (substring == null || substring.equals("")) {
            return i2;
        }
        try {
            return Integer.parseInt(substring);
        } catch (NumberFormatException e2) {
            return i2;
        }
    }

    public static String a() {
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(5);
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(i2), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
    }

    public static String a(a aVar, h hVar, Location location, String str, int i2) {
        return a(aVar, hVar, location, str, i2, false);
    }

    public static String a(a aVar, h hVar, Location location, String str, int i2, boolean z2) {
        StringBuffer stringBuffer = new StringBuffer(1024);
        if (aVar != null) {
            String b2 = b.a().b(aVar);
            if (b2 != null) {
                stringBuffer.append(b2);
            }
        }
        if (hVar != null) {
            String d2 = i2 == 0 ? z2 ? hVar.b() : hVar.c() : hVar.d();
            if (d2 != null) {
                stringBuffer.append(d2);
            }
        }
        if (location != null) {
            String b3 = (d == 0 || i2 == 0) ? e.b(location) : e.c(location);
            if (b3 != null) {
                stringBuffer.append(b3);
            }
        }
        boolean z3 = false;
        if (i2 == 0) {
            z3 = true;
        }
        String a2 = b.a().a(z3);
        if (a2 != null) {
            stringBuffer.append(a2);
        }
        if (str != null) {
            stringBuffer.append(str);
        }
        String d3 = d.a().d();
        if (!TextUtils.isEmpty(d3)) {
            stringBuffer.append("&bc=").append(d3);
        }
        if (aVar != null) {
            String a3 = b.a().a(aVar);
            if (a3 != null && a3.length() + stringBuffer.length() < 750) {
                stringBuffer.append(a3);
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        if (location == null || hVar == null) {
            f379q = 3;
        } else {
            try {
                float speed = location.getSpeed();
                int i3 = d;
                int h2 = hVar.h();
                int a4 = hVar.a();
                boolean i4 = hVar.i();
                if (speed < ar && ((i3 == 1 || i3 == 0) && (h2 < at || i4))) {
                    f379q = 1;
                } else if (speed >= as || (!(i3 == 1 || i3 == 0 || i3 == 3) || (h2 >= au && a4 <= av))) {
                    f379q = 3;
                } else {
                    f379q = 2;
                }
            } catch (Exception e2) {
                f379q = 3;
            }
        }
        return stringBuffer2;
    }

    public static String a(File file, String str) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        return Jni.b(o + ";" + str);
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo state : allNetworkInfo) {
            if (state.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(BDLocation bDLocation) {
        int o2 = bDLocation.o();
        return (o2 > 100 && o2 < 200) || o2 == 62;
    }

    public static int b(Context context) {
        try {
            return System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception e2) {
            return 2;
        }
    }

    public static String b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            byte[] address = inetAddress.getAddress();
                            String str = "";
                            int i2 = 0;
                            while (true) {
                                int i3 = i2;
                                String str2 = str;
                                if (i3 >= address.length) {
                                    return str2;
                                }
                                String hexString = Integer.toHexString(address[i3] & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
                                if (hexString.length() == 1) {
                                    hexString = '0' + hexString;
                                }
                                str = str2 + hexString;
                                i2 = i3 + 1;
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
        }
        return null;
    }

    public static boolean b(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.baidu.a.a.a.a.b.a(str3.getBytes())));
            Signature instance = Signature.getInstance("SHA1WithRSA");
            instance.initVerify(generatePublic);
            instance.update(str.getBytes());
            return instance.verify(com.baidu.a.a.a.a.b.a(str2.getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int c(Context context) {
        char c2 = 65535;
        if (VERSION.SDK_INT < 19) {
            return -2;
        }
        try {
            return Secure.getInt(context.getContentResolver(), "location_mode", -1);
        } catch (Exception e2) {
            return c2;
        }
    }

    public static String c() {
        String str = "";
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet6Address) && inetAddress.getHostAddress() != null && !inetAddress.getHostAddress().startsWith("fe80:")) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
            return str;
        } catch (SocketException e2) {
            return str;
        } catch (Throwable th) {
            return str;
        }
    }

    public static String d() {
        return aw;
    }

    public static String d(Context context) {
        int a2 = a(context, "android.permission.ACCESS_COARSE_LOCATION");
        int a3 = a(context, "android.permission.ACCESS_FINE_LOCATION");
        return "&per=" + a2 + "|" + a3 + "|" + a(context, Constants.PERMISSION_READ_PHONE_STATE);
    }

    public static String e() {
        return az;
    }

    public static String e(Context context) {
        int i2;
        int i3 = -1;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    i2 = activeNetworkInfo.getType();
                    i3 = i2;
                    return "&netc=" + i3;
                }
            } catch (Exception e2) {
            }
        }
        i2 = -1;
        i3 = i2;
        return "&netc=" + i3;
    }

    public static String f() {
        return "https://daup.map.baidu.com/cltr/rcvr";
    }

    public static String g() {
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return null;
            }
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path + "/baidu/tempdata");
            if (file.exists()) {
                return path;
            }
            file.mkdirs();
            return path;
        } catch (Exception e2) {
            return null;
        }
    }

    public static String h() {
        String g2 = g();
        if (g2 == null) {
            return null;
        }
        return g2 + "/baidu/tempdata";
    }

    public static String i() {
        try {
            File file = new File(f.c().getFilesDir() + File.separator + "lldt");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception e2) {
            return null;
        }
    }

    public static String j() {
        try {
            File file = new File(f.c().getFilesDir() + File.separator + "/baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return f.c().getFilesDir().getPath();
        } catch (Exception e2) {
            return null;
        }
    }
}
