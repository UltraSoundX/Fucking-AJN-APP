package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.tencent.mid.core.Constants;

public final class NetworkUtil {
    public static final String NETWORK_CLASS_2_G = "2G";
    public static final String NETWORK_CLASS_3_G = "3G";
    public static final String NETWORK_CLASS_4_G = "4G";
    public static final String NETWORK_MOBILE = "mobile";
    public static final String NETWORK_UNKNOWN = "unknown";
    public static final String NETWORK_WIFI = "wifi";

    private NetworkUtil() {
    }

    public static String getCurrentNetworkType(Context context) {
        if (context.getPackageManager().checkPermission(Constants.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
            return "unknown";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "unknown";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return "unknown";
        }
        State state = activeNetworkInfo.getState();
        if (state != null && (state == State.CONNECTED || state == State.CONNECTING)) {
            if (activeNetworkInfo.getType() == 1) {
                return NETWORK_WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return NETWORK_CLASS_2_G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return NETWORK_CLASS_3_G;
                    case 13:
                        return NETWORK_CLASS_4_G;
                    default:
                        return NETWORK_MOBILE;
                }
            }
        }
        return "unknown";
    }
}
