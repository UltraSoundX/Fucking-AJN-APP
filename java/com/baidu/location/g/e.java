package com.baidu.location.g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import com.baidu.location.f;
import java.util.Map;

public abstract class e {
    private static String a = "10.0.0.172";
    private static int b = 80;
    public static int g = a.g;
    protected static int p = 0;
    public String h = null;
    public int i = 3;
    public String j = null;
    public Map<String, Object> k = null;
    public String l = null;
    public byte[] m = null;
    public byte[] n = null;
    public String o = null;

    private static int a(Context context, NetworkInfo networkInfo) {
        if (!(networkInfo == null || networkInfo.getExtraInfo() == null)) {
            String lowerCase = networkInfo.getExtraInfo().toLowerCase();
            if (lowerCase != null) {
                if (lowerCase.startsWith("cmwap") || lowerCase.startsWith("uniwap") || lowerCase.startsWith("3gwap")) {
                    String defaultHost = Proxy.getDefaultHost();
                    if (defaultHost == null || defaultHost.equals("") || defaultHost.equals("null")) {
                        defaultHost = "10.0.0.172";
                    }
                    a = defaultHost;
                    return a.d;
                } else if (lowerCase.startsWith("ctwap")) {
                    String defaultHost2 = Proxy.getDefaultHost();
                    if (defaultHost2 == null || defaultHost2.equals("") || defaultHost2.equals("null")) {
                        defaultHost2 = "10.0.0.200";
                    }
                    a = defaultHost2;
                    return a.d;
                } else if (lowerCase.startsWith("cmnet") || lowerCase.startsWith("uninet") || lowerCase.startsWith("ctnet") || lowerCase.startsWith("3gnet")) {
                    return a.e;
                }
            }
        }
        String defaultHost3 = Proxy.getDefaultHost();
        if (defaultHost3 != null && defaultHost3.length() > 0) {
            if ("10.0.0.172".equals(defaultHost3.trim())) {
                a = "10.0.0.172";
                return a.d;
            } else if ("10.0.0.200".equals(defaultHost3.trim())) {
                a = "10.0.0.200";
                return a.d;
            }
        }
        return a.e;
    }

    /* access modifiers changed from: private */
    public void b() {
        g = c();
    }

    private int c() {
        Context c = f.c();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService("connectivity");
            if (connectivityManager == null) {
                return a.g;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return a.g;
            }
            if (activeNetworkInfo.getType() != 1) {
                return a(c, activeNetworkInfo);
            }
            String defaultHost = Proxy.getDefaultHost();
            return (defaultHost == null || defaultHost.length() <= 0) ? a.f : a.h;
        } catch (Exception e) {
            return a.g;
        }
    }

    public abstract void a();

    public abstract void a(boolean z);

    public void a(boolean z, String str) {
        try {
            new g(this, str, z).start();
        } catch (Throwable th) {
            a(false);
        }
    }

    public void b(String str) {
        try {
            new h(this, str).start();
        } catch (Throwable th) {
            a(false);
        }
    }

    public void d() {
        try {
            new f(this).start();
        } catch (Throwable th) {
            a(false);
        }
    }

    public void e() {
        a(false, "loc.map.baidu.com");
    }
}
