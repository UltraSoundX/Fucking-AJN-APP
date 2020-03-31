package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

public class PrefOperate {
    public static void loadMetaDataConfig(Context context) {
        SendStrategyEnum sendStrategyEnum = SendStrategyEnum.APP_START;
        String str = "";
        try {
            String a = bb.a(context, Config.EXCEPTION_LOG_META_NAME);
            if (!TextUtils.isEmpty(a) && "true".equals(a)) {
                ExceptionAnalysis.getInstance().openExceptionAnalysis(context, false);
            }
        } catch (Exception e) {
        }
        String str2 = "";
        try {
            String a2 = bb.a(context, Config.SEND_STRATEGY_META_NAME);
            if (!TextUtils.isEmpty(a2)) {
                if (a2.equals(SendStrategyEnum.APP_START.name())) {
                    sendStrategyEnum = SendStrategyEnum.APP_START;
                    av.a().a(context, sendStrategyEnum.ordinal());
                } else if (a2.equals(SendStrategyEnum.ONCE_A_DAY.name())) {
                    sendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                    av.a().a(context, sendStrategyEnum.ordinal());
                    av.a().b(context, 24);
                } else if (a2.equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
                    sendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                    av.a().a(context, sendStrategyEnum.ordinal());
                }
            }
        } catch (Exception e2) {
        }
        String str3 = "";
        try {
            String a3 = bb.a(context, Config.TIME_INTERVAL_META_NAME);
            if (!TextUtils.isEmpty(a3)) {
                int parseInt = Integer.parseInt(a3);
                if (sendStrategyEnum.ordinal() == SendStrategyEnum.SET_TIME_INTERVAL.ordinal() && parseInt > 0 && parseInt <= 24) {
                    av.a().b(context, parseInt);
                }
            }
        } catch (Exception e3) {
        }
        String str4 = "";
        try {
            String a4 = bb.a(context, Config.ONLY_WIFI_META_NAME);
            if (TextUtils.isEmpty(a4)) {
                return;
            }
            if ("true".equals(a4)) {
                av.a().a(context, true);
            } else if ("false".equals(a4)) {
                av.a().a(context, false);
            }
        } catch (Exception e4) {
        }
    }

    public static void setAppKey(String str) {
        CooperService.instance().getHeadObject().e = str;
    }

    public static String getAppKey(Context context) {
        return CooperService.instance().getAppKey(context);
    }

    public static void setAppChannel(String str) {
        if (str == null || str.equals("")) {
            am.c().c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().l = str;
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (str == null || str.equals("")) {
            am.c().c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().l = str;
        if (z && str != null && !str.equals("")) {
            av.a().d(context, str);
            av.a().b(context, true);
        }
        if (!z) {
            av.a().d(context, "");
            av.a().b(context, false);
        }
    }
}
