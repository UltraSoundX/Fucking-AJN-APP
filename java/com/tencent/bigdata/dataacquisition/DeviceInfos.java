package com.tencent.bigdata.dataacquisition;

import android.content.Context;
import android.util.DisplayMetrics;
import com.tencent.bigdata.dataacquisition.a.a;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceInfos {
    public static final byte NETWORK_TYPE_2G = 2;
    public static final byte NETWORK_TYPE_3G = 3;
    public static final byte NETWORK_TYPE_4G = 4;
    public static final byte NETWORK_TYPE_UNCONNECTED = -1;
    public static final byte NETWORK_TYPE_UNKNOWN = 0;
    public static final byte NETWORK_TYPE_WIFI = 1;

    public static int checkBluetooth(Context context) {
        return a.k(context);
    }

    public static JSONObject getCpuInfo(Context context) {
        return a.m(context);
    }

    public static String getCpuString() {
        return a.a();
    }

    public static String getCpuType() {
        return a.b();
    }

    public static String getDeviceModel(Context context) {
        return a.g(context);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return a.f(context);
    }

    public static String getExternalStorageInfo(Context context) {
        return a.j(context);
    }

    public static String getLinkedWay(Context context) {
        return a.e(context);
    }

    public static byte getNetworkType(Context context) {
        return a.c(context);
    }

    public static String getRomMemory() {
        return a.c();
    }

    public static JSONArray getSensorsJson(Context context) {
        return a.o(context);
    }

    public static String getSensorsString(Context context) {
        return a.n(context);
    }

    public static String getSystemMemory(Context context) {
        return a.l(context);
    }

    public static Integer getTelephonyNetworkType(Context context) {
        return a.a(context);
    }

    public static long getTotalInternalMemorySize() {
        return a.d();
    }

    public static int hasRootAccess(Context context) {
        return a.i(context);
    }

    public static boolean haveGravity(Context context) {
        return a.h(context);
    }

    public static boolean isNetworkAvailable(Context context) {
        return a.b(context);
    }

    public static boolean isSDCardMounted() {
        return a.e();
    }

    public static boolean isWifiNet(Context context) {
        return a.d(context);
    }
}
