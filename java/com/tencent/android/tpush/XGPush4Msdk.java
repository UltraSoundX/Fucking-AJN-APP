package com.tencent.android.tpush;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.Tools;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.Set;

/* compiled from: ProGuard */
public class XGPush4Msdk {
    private static long a = 0;
    private static long b = 0;
    private static String c = "";

    /* access modifiers changed from: private */
    public static String b(Context context) {
        return context.getPackageName() + Config.TRACE_TODAY_VISIT_SPLIT + "XG_DEBUG_SERVER_INFO";
    }

    public static void setDebugServerInfo(final Context context, String str, int i) {
        if (!i.b(str)) {
            h.b(context, b(context), str + StorageInterface.KEY_SPLITER + i);
        } else {
            c.a().a((Runnable) new Runnable() {
                public void run() {
                    if (!i.b(h.a(context, XGPush4Msdk.b(context), (String) null))) {
                        h.a(context, XGPush4Msdk.b(context));
                        Tools.clearCacheServerItems(context);
                        Tools.clearOptStrategyItem(context);
                    }
                }
            });
        }
    }

    public static String getDebugServerInfo(Context context) {
        return h.a(context, b(context), (String) null);
    }

    public static boolean isDebugServerInfoStrategyItem(Context context) {
        try {
            String debugServerInfo = getDebugServerInfo(b.f());
            if (i.b(debugServerInfo)) {
                return false;
            }
            String[] split = debugServerInfo.split(StorageInterface.KEY_SPLITER);
            if (split.length != 2 || split[0].length() <= 4) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            a.d("XGPush4Msdk", " .isDebugServerInfoStrategyItem", th);
            return false;
        }
    }

    private static boolean a(long j, long j2, long j3) {
        return j >= j2 && j < j3;
    }

    public static void setQQAppId(Context context, long j) {
        long j2;
        if (a(j, 0, 200000)) {
            j2 = 90000000;
        } else if (a(j, 99000000, 100000000)) {
            j2 = 0;
        } else if (a(j, 100200000, 100600000)) {
            j2 = -10000000;
        } else if (a(j, 101000000, 101400000)) {
            j2 = -10400000;
        } else if (a(j, 900000000, 900100000)) {
            j2 = -809000000;
        } else if (a(j, 1000000000, 1000100000)) {
            j2 = -908900000;
        } else if (a(j, 1101000000, 1104500000)) {
            j2 = -1009800000;
        } else if (a(j, 1150000000, 1150100000)) {
            j2 = -1055300000;
        } else if (a(j, 100600000, 101000000)) {
            j2 = -5800000;
        } else if (a(j, 1104500000, 1109300000)) {
            j2 = -1009300000;
        } else if (a(j, 1109300000, 1119300000)) {
            j2 = -1029300000;
        } else if (a(j, 1119300000, 1120000000)) {
            j2 = -1049300000;
        } else {
            Log.e(Constants.MSDK_TAG, "手Q的appid：" + j + " 不在固定的范围，请联系msdk和信鸽的同事解决之。");
            j2 = 0;
        }
        long j3 = j2 + 2100000000 + j;
        a = j;
        b = j3;
        h.b(context, "TPUSH_QQ_ACCESS_ID", b);
        h.a(context, "TPUSH_QQ_APP_ID");
        c = "MSDK_" + j;
        h.b(context, "__en__TPUSH_QQ_ACCESS_KEY", Rijndael.encrypt(c));
        h.a(context, "TPUSH_QQ_ACCESS_KEY");
    }

    public static long getQQAccessId(Context context) {
        if (b <= 0) {
            b = h.a(context, "TPUSH_QQ_ACCESS_ID", b);
        }
        return b;
    }

    public static void setQQAppKey(Context context, String str) {
    }

    public static String getQQAppKey(Context context) {
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        String a2 = h.a(context, "__en__TPUSH_QQ_ACCESS_KEY", c);
        if (!TextUtils.isEmpty(a2)) {
            c = Rijndael.decrypt(a2);
        } else {
            c = h.a(context, "TPUSH_QQ_ACCESS_KEY", "");
            h.b(context, "TPUSH_QQ_ACCESS_KEY", "");
        }
        return c;
    }

    public static void setTag(Context context, String str) {
        a.c(Constants.MSDK_TAG, "setTag: tagName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        XGPushManager.a(context, str, 1, getQQAccessId(context), str);
    }

    public static void deleteTag(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "deleteTag: tagName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        XGPushManager.a(context, str, 2, getQQAccessId(context), str);
    }

    public static void setTags(Context context, String str, Set<String> set) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "setTags: operateName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (context == null || set == null || set.isEmpty()) {
            a.j(Constants.MSDK_TAG, "the parameter context or tags of setTags is invalid.");
            return;
        }
        String a2 = XGPushManager.a(set, "setTags");
        if (a2 == null) {
            a.j(Constants.MSDK_TAG, "setTags -> getTagsFromSet return null!!!");
            return;
        }
        if (XGPushConfig.enableDebug) {
            a.f(Constants.MSDK_TAG, "Action -> setTags with all tags = " + a2);
        }
        XGPushManager.a(context, a2, 6, getQQAccessId(context), str);
    }

    public static void addTags(Context context, String str, Set<String> set) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "addTags: operateName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (context == null || set == null || set.isEmpty()) {
            a.j(Constants.MSDK_TAG, "the parameter context or tags of addTags is invalid.");
            return;
        }
        String a2 = XGPushManager.a(set, "addTags");
        if (a2 == null) {
            a.j(Constants.MSDK_TAG, "addTags -> getTagsFromSet return null!!!");
            return;
        }
        if (XGPushConfig.enableDebug) {
            a.f(Constants.MSDK_TAG, "addTags -> setTags with all tags = " + a2);
        }
        XGPushManager.a(context, a2, 5, getQQAccessId(context), str);
    }

    public static void deleteTags(Context context, String str, Set<String> set) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "deleteTags: operateName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (context == null || set == null || set.isEmpty()) {
            a.j(Constants.MSDK_TAG, "the parameter context or tags of deleteTags is invalid.");
            return;
        }
        String a2 = XGPushManager.a(set, "deleteTags");
        if (a2 == null) {
            a.j(Constants.MSDK_TAG, "deleteTags -> getTagsFromSet return null!!!");
            return;
        }
        if (XGPushConfig.enableDebug) {
            a.f(Constants.MSDK_TAG, "deleteTags -> setTags with all tags = " + a2);
        }
        XGPushManager.a(context, a2, 7, getQQAccessId(context), str);
    }

    public static void cleanTags(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "cleanTags: operateName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (context == null) {
            a.j(Constants.MSDK_TAG, "the parameter context of cleanTags is invalid");
            return;
        }
        if (XGPushConfig.enableDebug) {
            a.f(Constants.MSDK_TAG, "Action -> cleanTags");
        }
        XGPushManager.a(context, "*", 8, getQQAccessId(context), str);
    }

    public static void registerPush(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        XGIOperateCallback xGIOperateCallback2;
        if (XGPushConfig.enableDebug) {
            a.e(Constants.MSDK_TAG, "registerPush: account=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (xGIOperateCallback == null) {
            xGIOperateCallback2 = new XGIOperateCallback() {
                public void onSuccess(Object obj, int i) {
                    a.e(Constants.MSDK_TAG, "xg register push onSuccess. token:" + obj);
                }

                public void onFail(Object obj, int i, String str) {
                    a.i(Constants.MSDK_TAG, "xg register push onFail. token:" + obj + ", errCode:" + i + ",msg:" + str);
                }
            };
        } else {
            xGIOperateCallback2 = xGIOperateCallback;
        }
        if (!i.b(str)) {
            XGPushManager.a(context, str, "0", 0, null, xGIOperateCallback2, getQQAccessId(context), getQQAppKey(context), null, null, null, 0);
            return;
        }
        XGPushManager.a(context, null, null, -1, null, xGIOperateCallback2, getQQAccessId(context), getQQAppKey(context), null, null, null, 0);
    }

    public static void unregisterPush(Context context, XGIOperateCallback xGIOperateCallback) {
        XGIOperateCallback xGIOperateCallback2;
        if (XGPushConfig.enableDebug) {
            a.e(Constants.MSDK_TAG, "unregisterPush,qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (xGIOperateCallback == null) {
            xGIOperateCallback2 = new XGIOperateCallback() {
                public void onSuccess(Object obj, int i) {
                    a.e(Constants.MSDK_TAG, "xg unregisterPush push onSuccess. flag:" + i);
                }

                public void onFail(Object obj, int i, String str) {
                    a.i(Constants.MSDK_TAG, "xg unregisterPush push onFail. token:" + obj + ", errCode:" + i + ",msg:" + str);
                }
            };
        } else {
            xGIOperateCallback2 = xGIOperateCallback;
        }
        XGPushManager.a(context, xGIOperateCallback2, getQQAccessId(context), getQQAppKey(context), null, null, null);
    }

    public static long addLocalNotification(Context context, XGLocalMessage xGLocalMessage) {
        if (XGPushConfig.enableDebug) {
            a.e(Constants.MSDK_TAG, "addLocalNotification:msg=" + xGLocalMessage.toString() + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        return XGPushManager.a(context, xGLocalMessage, getQQAccessId(context));
    }

    public static void setPushNotificationBuilder(Context context, int i, XGPushNotificationBuilder xGPushNotificationBuilder) {
        if (context == null) {
            throw new IllegalArgumentException("context is null.");
        } else if (i < 5000 || i > 6000) {
            throw new IllegalArgumentException("notificationBulderId超过范围[5000, 6000].");
        } else if (xGPushNotificationBuilder != null) {
            com.tencent.android.tpush.c.b.a(context, i, xGPushNotificationBuilder);
        }
    }

    public static void setDefaultNotificationBuilder(Context context, XGPushNotificationBuilder xGPushNotificationBuilder) {
        XGPushManager.setDefaultNotificationBuilder(context, xGPushNotificationBuilder);
    }
}
