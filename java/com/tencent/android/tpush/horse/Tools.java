package com.tencent.android.tpush.horse;

import android.content.Context;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.horse.data.OptStrategyList;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.e.i;
import java.util.Iterator;

/* compiled from: ProGuard */
public class Tools {
    public static final String KEY = ".com.tencent.tpush.toolschannel";
    public static final String SHARE_NAME = ".com.tencent.tpush.toolschannel_name";
    public static final String STRATEGY = ".com.tencent.tpush.toolsstrategy";
    public static final String TOOLS_NAME_UNI_SUFFIX = ".com.tencent.tpush.tools";
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_HTTP = 2;
    public static final int TYPE_HTTP_WAP = 3;
    public static final int TYPE_TCP = 1;
    public static final int TYPE_TCP_OR_HTTP = 4;

    public static void clearOptKeyList(Context context) {
        if (context != null) {
            CacheManager.clearOptKeyList(context);
        }
    }

    public static void clearOptStrategyItem(Context context) {
        if (context != null) {
            if (XGPushConfig.enableDebug) {
                a.f(Constants.ServiceLogTag, "Action -> clearOptStrategyItem(" + context.getPackageName() + ")");
            }
            try {
                g.a(context, i.l(b.f()) + ".com.tencent.tpush.cache.redirect", "", true);
                Iterator it = CacheManager.getOptKeyList(context).iterator();
                while (it.hasNext()) {
                    CacheManager.addOptStrategyList(context, (String) it.next(), new OptStrategyList());
                }
            } catch (Exception e) {
                CacheManager.clearOptKeyList(b.f());
                a.e(Constants.ServiceLogTag, "clearOptStrategyItem error", e);
            }
            CacheManager.addOptStrategyList(context, i.l(context), new OptStrategyList());
        }
    }

    public static void clearCacheServerItems(Context context) {
        if (context != null) {
            if (XGPushConfig.enableDebug) {
                a.f(Constants.ServiceLogTag, "Action -> clearCacheServerItems(" + context.getPackageName() + ")");
            }
            try {
                CacheManager.clearDomainServerItem(context);
                CacheManager.saveDomain(context, "");
                CacheManager.saveDomainKeyList(context, null);
                com.tencent.android.tpush.service.a.a.a(context).a(0);
            } catch (Throwable th) {
                a.e(Constants.ServiceLogTag, "clearCacheServerItems error", th);
            }
        }
    }

    public static void clearRegisterInfo(Context context, long j) {
        if (context != null) {
            try {
                CacheManager.removeRegisterInfoByPkgName(context.getPackageName());
            } catch (Exception e) {
                a.d(Constants.ServiceLogTag, "clearRegisterInfo", e);
            }
        }
    }

    public static void setChannelType(Context context, int i) {
        if (context != null) {
            g.a(context, KEY, i);
        }
    }

    public static int getChannelType(Context context) {
        if (context != null) {
            return g.b(context, KEY, 0);
        }
        return 0;
    }

    public static void clearAll(Context context) {
        clearOptKeyList(context);
        clearCacheServerItems(context);
        clearOptStrategyItem(context);
        XGPushManager.clearLocalNotifications(context);
        g.a(context, Constants.IS_CACHE_CLEAR, Constants.IS_CLEAR_CACHE, true);
    }
}
