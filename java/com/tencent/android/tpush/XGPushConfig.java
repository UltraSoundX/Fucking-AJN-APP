package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.b;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.d.d;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.e.i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class XGPushConfig {
    public static final String TPUSH_ACCESS_CHANNAL = "XG_V4_CHANNEL_ID";
    public static final String TPUSH_ACCESS_ID = "XG_V2_ACCESS_ID";
    public static final String TPUSH_ACCESS_KEY = "XG_V2_ACCESS_KEY";
    public static final String TPUSH_IS_FOREIGINPUSH = "TPUSH_IS_FOREIGINPUSH";
    public static boolean _isHuaweiDebug = false;
    /* access modifiers changed from: private */
    public static final String a = XGPushConfig.class.getSimpleName();
    private static String b = "";
    private static String c = "";
    private static long d = -1;
    private static long e = -1;
    public static Boolean enableApplist = null;
    public static boolean enableDebug = false;
    public static Boolean enableLocation = null;
    public static Boolean enableNotification = null;
    private static String f = "";
    private static Boolean g = null;
    private static Boolean h = null;
    private static SharedPreferences i = null;
    public static Boolean isUsedFcmPush = null;
    public static Boolean isUsedOtherPush = null;

    public static synchronized long getChannelId(Context context) {
        long j;
        synchronized (XGPushConfig.class) {
            if (context == null) {
                j = e;
            } else if (e != -1) {
                j = e;
            } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                j = e;
            } else {
                if (e == -1) {
                    Object a2 = b.a(context, TPUSH_ACCESS_CHANNAL, (Object) null);
                    if (a2 != null) {
                        try {
                            e = Long.valueOf(a2.toString()).longValue();
                        } catch (Exception e2) {
                            a.c(Constants.LogTag, "get channelId from getMetaData failed: ", e2);
                            e = -1;
                        }
                    }
                }
                if (e == -1) {
                    a.c(Constants.LogTag, "channelId没有初始化");
                }
                j = e;
            }
        }
        return j;
    }

    public static synchronized long getAccessId(Context context) {
        long j;
        synchronized (XGPushConfig.class) {
            if (context == null) {
                j = d;
            } else if (d != -1) {
                j = d;
            } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                j = d;
            } else {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (defaultSharedPreferences != null) {
                    String string = defaultSharedPreferences.getString(TPUSH_ACCESS_ID, null);
                    if (string != null) {
                        try {
                            d = Long.valueOf(Rijndael.decrypt(string)).longValue();
                        } catch (Exception e2) {
                            d = -1;
                            a.c(a, "get accessId error", e2);
                        }
                    }
                }
                if (d == -1) {
                    Object a2 = b.a(context, TPUSH_ACCESS_ID, (Object) null);
                    if (a2 != null) {
                        try {
                            d = Long.valueOf(a2.toString()).longValue();
                        } catch (Exception e3) {
                            a.c(Constants.LogTag, "get accessId from getMetaData failed: ", e3);
                            d = -1;
                        }
                    }
                }
                if (d == -1) {
                    a.i(Constants.LogTag, "accessId没有初始化");
                }
                j = d;
            }
        }
        return j;
    }

    public static void setAccessId(final Context context, final long j) {
        if (context == null) {
            a.i(a, "null  context");
            return;
        }
        d = j;
        c.a().a((Runnable) new Runnable() {
            public void run() {
                if (TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                    SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    if (defaultSharedPreferences != null) {
                        Editor edit = defaultSharedPreferences.edit();
                        edit.putString(XGPushConfig.TPUSH_ACCESS_ID, Rijndael.encrypt(String.valueOf(j)));
                        edit.commit();
                    }
                }
            }
        });
    }

    public static synchronized String getAccessKey(Context context) {
        String str = null;
        synchronized (XGPushConfig.class) {
            if (!i.b(f)) {
                str = f;
            } else if (TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (defaultSharedPreferences != null) {
                    String string = defaultSharedPreferences.getString(TPUSH_ACCESS_KEY, null);
                    if (i.b(string)) {
                        f = Rijndael.decrypt(string);
                    }
                }
                if (i.b(f)) {
                    Object a2 = b.a(context, TPUSH_ACCESS_KEY, (Object) null);
                    if (a2 != null) {
                        f = a2.toString();
                    }
                }
                if (i.b(f)) {
                    a.i(a, "accessKey is null");
                }
                str = f;
            }
        }
        return str;
    }

    public static void setAccessKey(final Context context, final String str) {
        if (context == null || str == null) {
            a.i(Constants.LogTag, "null context or null accessKey");
            return;
        }
        f = str;
        c.a().a((Runnable) new Runnable() {
            public void run() {
                if (TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                    SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    if (defaultSharedPreferences != null) {
                        Editor edit = defaultSharedPreferences.edit();
                        edit.putString(XGPushConfig.TPUSH_ACCESS_KEY, Rijndael.encrypt(str));
                        edit.commit();
                    }
                }
            }
        });
    }

    public static String getToken(Context context) {
        if (context != null) {
            return CacheManager.getToken(context);
        }
        a.i(Constants.LogTag, "null context");
        return null;
    }

    public static void enableDebug(final Context context, final boolean z) {
        if (context != null) {
            enableDebug = z;
            c.a().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        if (z) {
                            a.a(2);
                        } else {
                            a.a(3);
                        }
                        h.b(context, "com.tencent.android.tpush.debug," + context.getPackageName(), z ? 1 : 0);
                        Intent intent = new Intent("com.tencent.android.tpush.action.ENABLE_DEBUG.V4");
                        intent.putExtra("debugMode", z);
                        context.sendBroadcast(intent);
                    } catch (Throwable th) {
                        a.d(XGPushConfig.a, "enableDebug ", th);
                    }
                }
            });
        }
    }

    public static boolean isEnableDebug(Context context) {
        return h.a(context, new StringBuilder().append("com.tencent.android.tpush.debug,").append(context.getPackageName()).toString(), 0) != 0;
    }

    public static void setLocationEnable(Context context, boolean z) {
        if (enableLocation == null || enableLocation.booleanValue() != z) {
            enableLocation = Boolean.valueOf(z);
            h.b(context, "com.tencent.android.tpush.enable_location," + context.getPackageName(), z ? 1 : 0);
        }
    }

    public static boolean isLocationEnable(Context context) {
        boolean z = true;
        if (enableLocation == null) {
            if (h.a(context, "com.tencent.android.tpush.enable_location," + context.getPackageName(), 1) == 0) {
                z = false;
            }
            enableLocation = Boolean.valueOf(z);
        }
        return enableLocation.booleanValue();
    }

    public static void setReportApplistEnable(Context context, boolean z) {
        if (enableApplist == null || enableApplist.booleanValue() != z) {
            enableApplist = Boolean.valueOf(z);
            h.b(context, "com.tencent.android.tpush.enable_applist," + context.getPackageName(), z ? 1 : 0);
        }
    }

    public static boolean isReportApplistEnable(Context context) {
        if (enableApplist == null) {
            enableApplist = Boolean.valueOf(h.a(context, new StringBuilder().append("com.tencent.android.tpush.enable_applist,").append(context.getPackageName()).toString(), 1) != 0);
        }
        if (com.tencent.android.tpush.service.a.a.a(context).F == -1) {
            return enableApplist.booleanValue();
        }
        if (com.tencent.android.tpush.service.a.a.a(context).F != 1) {
            return false;
        }
        return true;
    }

    public static void setReportNotificationStatusEnable(Context context, boolean z) {
        if (enableNotification == null || enableNotification.booleanValue() != z) {
            enableNotification = Boolean.valueOf(z);
            h.b(context, "com.tencent.android.tpush.enable_NOTIICATION," + context.getPackageName(), z ? 1 : 0);
        }
    }

    public static boolean isReportNotificationStatusEnable(Context context) {
        if (enableNotification == null) {
            enableNotification = Boolean.valueOf(h.a(context, new StringBuilder().append("com.tencent.android.tpush.enable_NOTIICATION,").append(context.getPackageName()).toString(), 1) != 0);
        }
        if (com.tencent.android.tpush.service.a.a.a(context).G == -1) {
            return enableNotification.booleanValue();
        }
        if (com.tencent.android.tpush.service.a.a.a(context).G != 1) {
            return false;
        }
        return true;
    }

    public static List<Long> getAccessidList(Context context) {
        ArrayList arrayList = new ArrayList(2);
        if (context != null) {
            long accessId = getAccessId(context);
            if (accessId > 0) {
                arrayList.add(Long.valueOf(accessId));
            }
            long qQAccessId = XGPush4Msdk.getQQAccessId(context);
            if (qQAccessId > 0) {
                arrayList.add(Long.valueOf(qQAccessId));
            }
            Object a2 = b.a(context, TPUSH_ACCESS_ID, (Object) null);
            if (a2 != null) {
                try {
                    long longValue = Long.valueOf(a2.toString()).longValue();
                    if (!arrayList.contains(Long.valueOf(longValue))) {
                        arrayList.add(Long.valueOf(longValue));
                    }
                } catch (Exception e2) {
                    a.c(a, "get accessId from getMetaData failed: ", e2);
                }
            }
        }
        return arrayList;
    }

    public static void setInstallChannel(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            b = str;
        }
    }

    public static String getInstallChannel(Context context) {
        return b;
    }

    public static void setGameServer(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            c = str;
        }
    }

    public static String getGameServer(Context context) {
        return c;
    }

    public static void setHeartbeatIntervalMs(Context context, int i2) {
        if (context != null && i2 >= 5000 && i2 < 1800000) {
            try {
                com.tencent.android.tpush.service.e.h.b(context, "com.tencent.android.xg.wx.HeartbeatIntervalMs", i2);
            } catch (Exception e2) {
                a.d(a, "setHeartbeatIntervalMs", e2);
            }
        }
    }

    public static boolean isUsedOtherPush(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        if (isUsedOtherPush == null) {
            if (h.a(context, "com.tencent.android.tpush.other.push," + context.getPackageName(), 0) != 0) {
                z = true;
            }
            isUsedOtherPush = Boolean.valueOf(z);
        }
        return isUsedOtherPush.booleanValue();
    }

    public static void enableOtherPush(Context context, boolean z) {
        if (context != null) {
            if (isUsedOtherPush == null || isUsedOtherPush.booleanValue() != z) {
                isUsedOtherPush = Boolean.valueOf(z);
                h.b(context, "com.tencent.android.tpush.other.push," + context.getPackageName(), z ? 1 : 0);
            }
        }
    }

    public static boolean isUsedFcmPush(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        if (isUsedFcmPush == null) {
            if (h.a(context, "com.tencent.android.tpush.fcm," + context.getPackageName(), 0) != 0) {
                z = true;
            }
            isUsedFcmPush = Boolean.valueOf(z);
        }
        return isUsedFcmPush.booleanValue();
    }

    public static void enableFcmPush(Context context, boolean z) {
        if (context != null) {
            if (isUsedFcmPush == null || isUsedFcmPush.booleanValue() != z) {
                isUsedFcmPush = Boolean.valueOf(z);
                h.b(context, "com.tencent.android.tpush.fcm," + context.getPackageName(), z ? 1 : 0);
            }
        }
    }

    public static void setReportDebugMode(Context context, boolean z) {
        if (context != null) {
            h.b(context, context.getPackageName() + ".report.mode", z ? 1 : 0);
        }
    }

    public static boolean getReportDebugMode(Context context) {
        if (h.a(context, context.getPackageName() + ".report.mode", 0) != 0) {
            return true;
        }
        return false;
    }

    public static void setMiPushAppId(Context context, String str) {
        d.a(context, str);
    }

    public static void setMiPushAppKey(Context context, String str) {
        d.b(context, str);
    }

    public static void setMzPushAppId(Context context, String str) {
        d.c(context, str);
    }

    public static void setMzPushAppKey(Context context, String str) {
        d.d(context, str);
    }

    public static void setfcmSenderId(Context context, String str) {
        com.tencent.android.tpush.d.a.b(context, str);
    }

    public static void setForeiginPushEnable(Context context, boolean z) {
    }

    public static boolean isForeiginPush(Context context) {
        if (g == null) {
            try {
                Object a2 = b.a(context, TPUSH_IS_FOREIGINPUSH, (Object) null);
                if (a2 == null) {
                    g = Boolean.valueOf(false);
                    return g.booleanValue();
                } else if ("true".equals(a2.toString())) {
                    g = Boolean.valueOf(true);
                } else {
                    g = Boolean.valueOf(false);
                }
            } catch (Throwable th) {
                g = Boolean.valueOf(false);
            }
        }
        return g.booleanValue();
    }

    public static void setHuaweiDebug(boolean z) {
        _isHuaweiDebug = z;
    }

    public static boolean isHuaweiDebug() {
        return _isHuaweiDebug;
    }

    public static boolean isForeignWeakAlarmMode(Context context) {
        int i2;
        boolean z = true;
        if (h != null) {
            return h.booleanValue();
        }
        if (PreferenceManager.getDefaultSharedPreferences(context) != null) {
            i2 = h.a(context, "com.tencent.android.tpush.enable_FOREIGIN_XG_WEAK_ALARM," + context.getPackageName(), -1);
        } else {
            i2 = -1;
        }
        if (i2 == -1) {
            Object a2 = b.a(context, Constants.META_STR_FOREIGIN_XG_WEAK_ALARM, (Object) null);
            if (a2 != null && a2.toString().equals("true")) {
                i2 = 1;
            }
        }
        if (i2 != 1) {
            z = false;
        }
        h = Boolean.valueOf(z);
        return h.booleanValue();
    }

    public static void setForeignWeakAlarmMode(Context context, boolean z) {
        if (h == null || h.booleanValue() != z) {
            h = Boolean.valueOf(z);
            h.b(context, "com.tencent.android.tpush.enable_FOREIGIN_XG_WEAK_ALARM," + context.getPackageName(), z ? 1 : 0);
        }
    }

    public static void setNotificationShowEnable(Context context, boolean z) {
        if (context != null) {
            try {
                if (i == null) {
                    i = context.getSharedPreferences(Constants.APP_PREF_NAME, 0);
                }
                Editor edit = i.edit();
                edit.putBoolean(Constants.SETTINGS_ENABLE_SHOW_NOTIFICATION, z);
                edit.commit();
            } catch (Exception e2) {
                a.d(a, "setNotificationShowEnable", e2);
            }
        }
    }

    public static boolean isNotificationShowEnable(Context context) {
        if (context == null) {
            return true;
        }
        try {
            if (i == null) {
                i = context.getSharedPreferences(Constants.APP_PREF_NAME, 0);
            }
            return i.getBoolean(Constants.SETTINGS_ENABLE_SHOW_NOTIFICATION, true);
        } catch (Exception e2) {
            a.d(a, "isNotificationShowEnable", e2);
            return true;
        }
    }
}
