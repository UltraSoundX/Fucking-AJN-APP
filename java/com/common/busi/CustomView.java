package com.common.busi;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.security.MessageDigest;

public class CustomView {
    public static native String getAppkey();

    public static native long getPT();

    public static native long performFixit(long j);

    public static native long prepareFixit(String str, String str2, int i, String str3, String str4, String str5, int i2, int i3);

    public static void wp(Context context, String str, String str2) {
        try {
            Class.forName("com.common.busi.WpEntry").getDeclaredMethod("wp", new Class[]{Context.class, String.class, String.class}).invoke(null, new Object[]{context, str, str2});
        } catch (Throwable th) {
        }
    }

    public static void pull(Context context, String str, String str2) {
        try {
            Class.forName("com.stub.stub02.p.PullEntry").getDeclaredMethod("pull", new Class[]{Context.class, String.class, String.class}).invoke(null, new Object[]{context, str, str2});
        } catch (Throwable th) {
        }
    }

    public static void upgrade(Context context, String str, String str2) {
        try {
            Class.forName("com.common.custom.soupgrade.SoEntry").getDeclaredMethod("upgrade", new Class[]{Context.class, String.class, String.class}).invoke(null, new Object[]{context, str, str2});
        } catch (Throwable th) {
        }
    }

    public static void hotfix(Context context, String str, String str2) {
        try {
            Class.forName("com.common.custom.hotfix.HotfixEntry").getDeclaredMethod("hotfix", new Class[]{Context.class, String.class, String.class}).invoke(null, new Object[]{context, str, str2});
        } catch (Throwable th) {
        }
    }

    public static void ls(Context context, String str, String str2) {
        try {
            Class.forName("com.stub.stub05.Sdk").getDeclaredMethod("init", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (Throwable th) {
        }
    }

    public static void appUpdate(Context context, String str, String str2) {
        try {
            Class.forName("com.stub.stub08.UpdateEntry").getDeclaredMethod("appUpdate", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (Throwable th) {
        }
    }

    public static void lc(Context context, String str, String str2) {
        try {
            Class.forName("com.jg.bh.BH").getDeclaredMethod("updateConfig", new Class[]{Context.class, String.class}).invoke(null, new Object[]{context, str});
        } catch (Throwable th) {
        }
    }

    public static void uc(Context context, String str, String str2) {
        try {
            final int intValue = Integer.valueOf(str).intValue();
            try {
                final String m2Var = getm2(context);
                final String packageName = context.getPackageName();
                final String str3 = VERSION.RELEASE;
                final int i = VERSION.SDK_INT;
                final String str4 = Build.MANUFACTURER;
                final String str5 = Build.MODEL;
                new Thread() {
                    public void run() {
                        try {
                            Looper.myLooper();
                            Looper.prepare();
                            long prepareFixit = CustomView.prepareFixit(packageName, str3, i, str4, str5, m2Var, 0, intValue);
                            if (prepareFixit != 0) {
                                AnonymousClass1 r2 = new Handler() {
                                    public void handleMessage(Message message) {
                                        long longValue = ((Long) message.obj).longValue();
                                        if (longValue != 0) {
                                            CustomView.performFixit(longValue);
                                        }
                                    }
                                };
                                Message message = new Message();
                                message.obj = Long.valueOf(prepareFixit);
                                r2.sendMessage(message);
                                Looper.loop();
                            }
                        } catch (Throwable th) {
                        }
                    }
                }.start();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
        }
    }

    public static String getm2(Context context) {
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            String string = System.getString(context.getContentResolver(), "android_id");
            return md5Decode(deviceId + string + Build.SERIAL);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String md5Decode(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & DeviceInfos.NETWORK_TYPE_UNCONNECTED) < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b & DeviceInfos.NETWORK_TYPE_UNCONNECTED));
            }
            return sb.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
