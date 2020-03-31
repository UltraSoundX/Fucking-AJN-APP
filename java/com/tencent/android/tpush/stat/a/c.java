package com.tencent.android.tpush.stat.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Process;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.e.e;
import com.tencent.android.tpush.service.e.i;
import com.tencent.android.tpush.stat.b;
import com.tencent.mid.core.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;

/* compiled from: ProGuard */
public class c {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static Random d = null;
    private static Map<Long, String> e = new HashMap(10);
    private static String f = "";
    private static d g = null;
    private static String h = null;
    private static long i = -1;
    private static int j = 0;
    private static String k = "__MTA_FIRST_ACTIVATE__";
    private static int l = -1;

    private static synchronized Random d() {
        Random random;
        synchronized (c.class) {
            if (d == null) {
                d = new Random();
            }
            random = d;
        }
        return random;
    }

    public static String a(Context context, long j2) {
        try {
            if (e.containsKey(Long.valueOf(j2))) {
                return (String) e.get(Long.valueOf(j2));
            }
            if (context != null) {
                List<ResolveInfo> e2 = i.e(context);
                if (e2 != null) {
                    for (ResolveInfo resolveInfo : e2) {
                        String str = resolveInfo.activityInfo.packageName;
                        if (str != null) {
                            RegisterEntity registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                            if (registerInfoByPkgName != null && registerInfoByPkgName.accessId == j2) {
                                String str2 = registerInfoByPkgName.xgSDKVersion + "";
                                e.put(Long.valueOf(registerInfoByPkgName.accessId), str2);
                                return str2;
                            }
                        }
                    }
                }
            }
            return "0";
        } catch (Throwable th) {
        }
    }

    public static String a(Context context) {
        try {
            return String.valueOf(4.03f);
        } catch (Throwable th) {
            return "0";
        }
    }

    public static int a() {
        return d().nextInt(Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static HttpHost b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission(Constants.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            String defaultHost = Proxy.getDefaultHost();
            if (defaultHost != null && defaultHost.trim().length() > 0) {
                return new HttpHost(defaultHost, Proxy.getDefaultPort());
            }
            return null;
        } catch (Throwable th) {
            g.b(th);
        }
    }

    public static String b(Context context, long j2) {
        return e.a(context).a(j2);
    }

    public static synchronized d b() {
        d dVar;
        synchronized (c.class) {
            if (g == null) {
                g = new d("XgStat");
                g.a(true);
            }
            dVar = g;
        }
        return dVar;
    }

    public static long c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + 86400000;
        } catch (Throwable th) {
            g.b(th);
            return System.currentTimeMillis() + 86400000;
        }
    }

    public static Long a(String str, String str2, int i2, int i3, Long l2) {
        if (str == null || str2 == null) {
            return l2;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = "\\" + str2;
        }
        String[] split = str.split(str2);
        if (split.length != i3) {
            return l2;
        }
        try {
            Long valueOf = Long.valueOf(0);
            int i4 = 0;
            while (i4 < split.length) {
                Long valueOf2 = Long.valueOf(((long) i2) * (valueOf.longValue() + Long.valueOf(split[i4]).longValue()));
                i4++;
                valueOf = valueOf2;
            }
            return valueOf;
        } catch (NumberFormatException e2) {
            return l2;
        }
    }

    public static long a(String str) {
        return a(str, ".", 100, 3, Long.valueOf(0)).longValue();
    }

    public static boolean b(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static String c(Context context) {
        try {
            if (h != null) {
                return h;
            }
            int myPid = Process.myPid();
            Iterator it = ((ActivityManager) context.getSystemService(com.tencent.android.tpush.common.Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                if (runningAppProcessInfo.pid == myPid) {
                    h = runningAppProcessInfo.processName;
                    break;
                }
            }
            return h;
        } catch (Throwable th) {
        }
    }

    public static String a(Context context, String str) {
        if (!b.e()) {
            return str;
        }
        if (h == null) {
            h = c(context);
        }
        if (h != null) {
            return str + "_" + h;
        }
        return str;
    }

    public static boolean d(Context context) {
        if (i < 0) {
            i = e.a(context, "mta.qq.com.checktime", 0);
        }
        return Math.abs(System.currentTimeMillis() - i) > 86400000;
    }

    public static void e(Context context) {
        i = System.currentTimeMillis();
        e.b(context, "mta.qq.com.checktime", i);
    }

    public static int a(Context context, boolean z) {
        if (z) {
            j = f(context);
        }
        return j;
    }

    public static int f(Context context) {
        return e.a(context, "mta.qq.com.difftime", 0);
    }

    public static void a(Context context, int i2) {
        j = i2;
        e.b(context, "mta.qq.com.difftime", i2);
    }
}
