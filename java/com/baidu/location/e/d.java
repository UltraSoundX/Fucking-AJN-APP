package com.baidu.location.e;

import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;

public final class d {
    public static String a(int i) {
        if (i.j()) {
            return "WIFI";
        }
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NetworkUtil.NETWORK_CLASS_2_G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NetworkUtil.NETWORK_CLASS_3_G;
            case 13:
                return NetworkUtil.NETWORK_CLASS_4_G;
            default:
                return "unknown";
        }
    }
}
