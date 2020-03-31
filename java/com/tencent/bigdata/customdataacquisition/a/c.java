package com.tencent.bigdata.customdataacquisition.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.bigdata.customdataacquisition.b.a;
import com.tencent.mid.core.Constants;

public class c {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;

    @SuppressLint({"MissingPermission"})
    public static String a(Context context) {
        if (a != null) {
            return a;
        }
        try {
            if (a.a(context, Constants.PERMISSION_READ_PHONE_STATE)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    a = telephonyManager.getDeviceId();
                    return a != null ? a : "";
                }
            }
        } catch (Throwable th) {
            a.c("getDeviceIMEI error" + th.toString());
        }
        return "";
    }

    @SuppressLint({"MissingPermission"})
    public static String b(Context context) {
        if (b != null) {
            return b;
        }
        try {
            if (a.a(context, Constants.PERMISSION_READ_PHONE_STATE)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    b = telephonyManager.getSubscriberId();
                    return b != null ? b : "";
                }
            } else {
                a.c("Could not get permission of android.permission.READ_PHONE_STATE");
            }
        } catch (Throwable th) {
            a.c("get subscriber id error:" + th.toString());
        }
        return "";
    }

    public static String c(Context context) {
        if (c != null) {
            return c;
        }
        try {
            if (a.a(context, Constants.PERMISSION_READ_PHONE_STATE)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    c = telephonyManager.getSimOperator();
                    return c != null ? c : "";
                }
            }
        } catch (Throwable th) {
            a.c("getSimOperator error" + th.toString());
        }
        return "";
    }

    public static String d(Context context) {
        if (d != null) {
            return d;
        }
        try {
            d = Secure.getString(context.getContentResolver(), "android_id");
            return d != null ? d : "";
        } catch (Throwable th) {
            a.c("getAndroidId error" + th.toString());
            return "";
        }
    }
}
