package com.baidu.location.e;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;

public class i {
    public static long a = 0;
    private static i b = null;
    private WifiManager c = null;
    private a d = null;
    private h e = null;
    private long f = 0;
    private long g = 0;
    private boolean h = false;
    /* access modifiers changed from: private */
    public Handler i = new Handler();
    private long j = 0;
    private long k = 0;

    private class a extends BroadcastReceiver {
        private long b;
        private boolean c;

        private a() {
            this.b = 0;
            this.c = false;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                String action = intent.getAction();
                if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                    i.a = System.currentTimeMillis() / 1000;
                    i.this.i.post(new j(this));
                } else if (action.equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(State.CONNECTED) && System.currentTimeMillis() - this.b >= Config.BPLUS_DELAY_TIME) {
                    this.b = System.currentTimeMillis();
                    if (!this.c) {
                        this.c = true;
                    }
                }
            }
        }
    }

    private i() {
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                b = new i();
            }
            iVar = b;
        }
        return iVar;
    }

    private String a(long j2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j2 & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 24) & 255)));
        return stringBuffer.toString();
    }

    public static boolean a(h hVar, h hVar2) {
        boolean a2 = a(hVar, hVar2, 0.7f);
        long currentTimeMillis = System.currentTimeMillis() - com.baidu.location.a.a.c;
        if (currentTimeMillis <= 0 || currentTimeMillis >= 30000 || !a2 || hVar2.g() - hVar.g() <= 30) {
            return a2;
        }
        return false;
    }

    public static boolean a(h hVar, h hVar2, float f2) {
        int i2;
        if (hVar == null || hVar2 == null) {
            return false;
        }
        List<ScanResult> list = hVar.a;
        List<ScanResult> list2 = hVar2.a;
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null) {
            return false;
        }
        int size = list.size();
        int size2 = list2.size();
        if (size == 0 && size2 == 0) {
            return true;
        }
        if (size == 0 || size2 == 0) {
            return false;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < size) {
            String str = ((ScanResult) list.get(i3)).BSSID;
            if (str != null) {
                int i5 = 0;
                while (true) {
                    if (i5 >= size2) {
                        i2 = i4;
                        break;
                    } else if (str.equals(((ScanResult) list2.get(i5)).BSSID)) {
                        i2 = i4 + 1;
                        break;
                    } else {
                        i5++;
                    }
                }
            } else {
                i2 = i4;
            }
            i3++;
            i4 = i2;
        }
        return ((float) i4) >= ((float) size) * f2;
    }

    public static boolean j() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) f.c().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        } catch (Exception e2) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.c != null) {
            try {
                List scanResults = this.c.getScanResults();
                if (scanResults != null) {
                    h hVar = new h(scanResults, System.currentTimeMillis());
                    if (this.e == null || !hVar.a(this.e)) {
                        this.e = hVar;
                    }
                }
            } catch (Exception e2) {
            }
        }
    }

    public void b() {
        this.j = 0;
    }

    public synchronized void c() {
        if (!this.h) {
            if (f.f) {
                this.c = (WifiManager) StubApp.getOrigApplicationContext(f.c().getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
                this.d = new a();
                try {
                    f.c().registerReceiver(this.d, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                } catch (Exception e2) {
                }
                this.h = true;
            }
        }
    }

    public List<WifiConfiguration> d() {
        try {
            if (this.c != null) {
                return this.c.getConfiguredNetworks();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public synchronized void e() {
        if (this.h) {
            try {
                f.c().unregisterReceiver(this.d);
                a = 0;
            } catch (Exception e2) {
            }
            this.d = null;
            this.c = null;
            this.h = false;
        }
    }

    public boolean f() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.g > 0 && currentTimeMillis - this.g <= Config.BPLUS_DELAY_TIME) {
            return false;
        }
        this.g = currentTimeMillis;
        b();
        return g();
    }

    public boolean g() {
        if (this.c == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f > 0) {
            if (currentTimeMillis - this.f <= this.j + Config.BPLUS_DELAY_TIME || currentTimeMillis - (a * 1000) <= this.j + Config.BPLUS_DELAY_TIME) {
                return false;
            }
            if (j() && currentTimeMillis - this.f <= OkHttpUtils.DEFAULT_MILLISECONDS + this.j) {
                return false;
            }
        }
        return i();
    }

    @SuppressLint({"NewApi"})
    public String h() {
        String str = "";
        if (this.c == null) {
            return str;
        }
        try {
            return (this.c.isWifiEnabled() || (VERSION.SDK_INT > 17 && this.c.isScanAlwaysAvailable())) ? "&wifio=1" : str;
        } catch (Exception | NoSuchMethodError e2) {
            return str;
        }
    }

    @SuppressLint({"NewApi"})
    public boolean i() {
        long currentTimeMillis = System.currentTimeMillis() - this.k;
        if (currentTimeMillis >= 0 && currentTimeMillis <= 2000) {
            return false;
        }
        this.k = System.currentTimeMillis();
        try {
            if (!this.c.isWifiEnabled() && (VERSION.SDK_INT <= 17 || !this.c.isScanAlwaysAvailable())) {
                return false;
            }
            this.c.startScan();
            this.f = System.currentTimeMillis();
            return true;
        } catch (Exception | NoSuchMethodError e2) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    public boolean k() {
        try {
            if ((!this.c.isWifiEnabled() && (VERSION.SDK_INT <= 17 || !this.c.isScanAlwaysAvailable())) || j()) {
                return false;
            }
            h hVar = new h(this.c.getScanResults(), 0);
            return hVar != null && hVar.e();
        } catch (Exception | NoSuchMethodError e2) {
            return false;
        }
    }

    public WifiInfo l() {
        if (this.c == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = this.c.getConnectionInfo();
            if (connectionInfo == null || connectionInfo.getBSSID() == null || connectionInfo.getRssi() <= -100) {
                return null;
            }
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                String replace = bssid.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
                if ("000000000000".equals(replace) || "".equals(replace)) {
                    return null;
                }
            }
            return connectionInfo;
        } catch (Error | Exception e2) {
            return null;
        }
    }

    public String m() {
        StringBuffer stringBuffer = new StringBuffer();
        WifiInfo l = a().l();
        if (l == null || l.getBSSID() == null) {
            return null;
        }
        String replace = l.getBSSID().replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        int rssi = l.getRssi();
        String n = a().n();
        if (rssi < 0) {
            rssi = -rssi;
        }
        if (replace == null || rssi >= 100) {
            return null;
        }
        stringBuffer.append("&wf=");
        stringBuffer.append(replace);
        stringBuffer.append(";");
        stringBuffer.append("" + rssi + ";");
        String ssid = l.getSSID();
        if (ssid != null && (ssid.contains("&") || ssid.contains(";"))) {
            ssid = ssid.replace("&", "_");
        }
        stringBuffer.append(ssid);
        stringBuffer.append("&wf_n=1");
        if (n != null) {
            stringBuffer.append("&wf_gw=");
            stringBuffer.append(n);
        }
        return stringBuffer.toString();
    }

    public String n() {
        if (this.c == null) {
            return null;
        }
        DhcpInfo dhcpInfo = this.c.getDhcpInfo();
        if (dhcpInfo != null) {
            return a((long) dhcpInfo.gateway);
        }
        return null;
    }

    public h o() {
        return (this.e == null || !this.e.j()) ? q() : this.e;
    }

    public h p() {
        return (this.e == null || !this.e.k()) ? q() : this.e;
    }

    public h q() {
        if (this.c != null) {
            try {
                return new h(this.c.getScanResults(), this.f);
            } catch (Exception e2) {
            }
        }
        return new h(null, 0);
    }

    public String r() {
        try {
            WifiInfo connectionInfo = this.c.getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        } catch (Error | Exception e2) {
            return null;
        }
    }
}
