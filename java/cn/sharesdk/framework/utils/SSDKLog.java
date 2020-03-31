package cn.sharesdk.framework.utils;

import cn.sharesdk.framework.ShareSDK;
import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.log.NLog;

public class SSDKLog {
    private static NLog a;

    public static NLog a() {
        a = NLog.getInstance(ShareSDK.SDK_TAG);
        DefaultLogsCollector.get().addSDK(ShareSDK.SDK_TAG, ShareSDK.SDK_VERSION_CODE);
        return a;
    }

    public static NLog b() {
        if (a == null) {
            a();
        }
        return a;
    }
}
