package com.e23.ajn.ccb.d;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* compiled from: DeviceUtils */
public class c {
    public static String a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return !TextUtils.isEmpty(telephonyManager.getDeviceId()) ? telephonyManager.getDeviceId() : "";
    }
}
