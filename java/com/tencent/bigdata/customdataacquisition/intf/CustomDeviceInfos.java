package com.tencent.bigdata.customdataacquisition.intf;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import com.tencent.bigdata.customdataacquisition.a.a;
import com.tencent.bigdata.customdataacquisition.a.b;
import com.tencent.bigdata.customdataacquisition.a.c;
import com.tencent.bigdata.customdataacquisition.a.d;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomDeviceInfos {
    public static String getAndroidId(Context context) {
        return c.d(context);
    }

    public static String getDeviceId(Context context) {
        return c.a(context);
    }

    public static String getImsi(Context context) {
        return c.b(context);
    }

    public static List<PackageInfo> getInstalledPackages(Context context) {
        return a.d(context);
    }

    public static String getIp(Context context) {
        return d.b(context);
    }

    public static String getMacAddress(Context context) {
        return d.a(context);
    }

    public static Map<String, Integer> getRecentTasks(Context context) {
        return a.c(context);
    }

    public static JSONObject getReportLocationJson(Context context) {
        return b.f(context);
    }

    public static Map<String, RunningAppProcessInfo> getRunningAppProces(Context context) {
        return a.b(context);
    }

    public static Map<String, Integer> getRunningProcess(Context context) {
        return a.a(context);
    }

    public static String getSimOperator(Context context) {
        return c.c(context);
    }

    public static String getWiFiBBSID(Context context) {
        return d.d(context);
    }

    public static String getWiFiSSID(Context context) {
        return d.e(context);
    }

    public static WifiInfo getWifiInfo(Context context) {
        return d.c(context);
    }

    public static JSONArray getWifiTopN(Context context, int i) {
        return d.a(context, i);
    }
}
