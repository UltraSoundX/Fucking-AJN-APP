package com.tencent.bigdata.customdataacquisition.a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.stub.StubApp;
import com.tencent.bigdata.customdataacquisition.b.a;
import com.tencent.mid.core.Constants;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
    private static String a = null;

    public static String a(Context context) {
        if (a != null) {
            return a;
        }
        if (a.a(context, Constants.PERMISSION_ACCESS_WIFI_STATE)) {
            try {
                WifiManager wifiManager = (WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
                if (wifiManager != null) {
                    a = wifiManager.getConnectionInfo().getMacAddress();
                    return a != null ? a : "";
                }
            } catch (Exception e) {
                a.c("getMacAddress" + e.toString());
            }
        }
        return "";
    }

    public static JSONArray a(Context context, int i) {
        try {
            if (!a.a(context, Constants.PERMISSION_INTERNET) || !a.a(context, Constants.PERMISSION_ACCESS_NETWORK_STATE) || (!a.a(context, "android.permission.ACCESS_COARSE_LOCATION") && !a.a(context, "android.permission.ACCESS_FINE_LOCATION"))) {
                a.c("can not get the permisson for getWifiTopN");
                return null;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(NetworkUtil.NETWORK_WIFI);
            if (wifiManager != null) {
                List scanResults = wifiManager.getScanResults();
                if (scanResults != null && scanResults.size() > 0) {
                    Collections.sort(scanResults, new Comparator<ScanResult>() {
                        /* renamed from: a */
                        public int compare(ScanResult scanResult, ScanResult scanResult2) {
                            int abs = Math.abs(scanResult.level);
                            int abs2 = Math.abs(scanResult2.level);
                            if (abs > abs2) {
                                return 1;
                            }
                            return abs == abs2 ? 0 : -1;
                        }
                    });
                    JSONArray jSONArray = new JSONArray();
                    int i2 = 0;
                    while (i2 < scanResults.size() && i2 < i) {
                        ScanResult scanResult = (ScanResult) scanResults.get(i2);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("bs", scanResult.BSSID);
                        jSONObject.put("ss", scanResult.SSID);
                        jSONArray.put(jSONObject);
                        i2++;
                    }
                    return jSONArray;
                }
            }
            return null;
        } catch (Throwable th) {
            a.c(th.toString());
        }
    }

    public static String b(Context context) {
        try {
            if (a.a(context, Constants.PERMISSION_ACCESS_WIFI_STATE)) {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                            if (!inetAddress.isLoopbackAddress()) {
                                String hostAddress = inetAddress.getHostAddress();
                                if (InetAddressUtils.isIPv4Address(hostAddress)) {
                                    return hostAddress != null ? hostAddress : "";
                                }
                            }
                        }
                    }
                }
                return "";
            }
            a.c("Can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            a.c("getIpV4Address Exception:" + e.toString());
        }
    }

    public static WifiInfo c(Context context) {
        if (a.a(context, Constants.PERMISSION_ACCESS_WIFI_STATE)) {
            WifiManager wifiManager = (WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(NetworkUtil.NETWORK_WIFI);
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    public static String d(Context context) {
        try {
            WifiInfo c = c(context);
            if (c != null) {
                String bssid = c.getBSSID();
                return bssid != null ? bssid : "";
            }
        } catch (Throwable th) {
            a.c(th.toString());
        }
        return "";
    }

    public static String e(Context context) {
        try {
            WifiInfo c = c(context);
            if (c != null) {
                String ssid = c.getSSID();
                return ssid != null ? ssid : "";
            }
        } catch (Throwable th) {
            a.c(th.toString());
        }
        return "";
    }
}
