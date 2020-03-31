package com.tencent.mid.core;

import android.content.Context;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.sotrage.UnifiedStorage;
import com.tencent.mid.util.Logger;
import com.tencent.mid.util.Util;

public class ServiceIMPL {
    /* access modifiers changed from: private */
    public static Logger logger = Util.getLogger();

    private static boolean checkPermissions(Context context, MidCallback midCallback) {
        return true;
    }

    private ServiceIMPL() {
    }

    public static MidEntity getLocalMidEntity(Context context) {
        return UnifiedStorage.getInstance(context).readMidEntity();
    }

    public static void clear(Context context) {
        if (context != null) {
            UnifiedStorage.getInstance(context).clear();
        }
    }

    public static void requestMid(Context context, final MidCallback midCallback) {
        logger.i("requestMid, callback=" + midCallback);
        requestMidEntity(context, new MidCallback() {
            public void onSuccess(Object obj) {
                if (obj != null) {
                    MidEntity parse = MidEntity.parse(obj.toString());
                    ServiceIMPL.logger.d("success to get mid:" + parse.getMid());
                    midCallback.onSuccess(parse.getMid());
                }
            }

            public void onFail(int i, String str) {
                ServiceIMPL.logger.e((Object) "failed to get mid, errorcode:" + i + " ,msg:" + str);
                midCallback.onFail(i, str);
            }
        });
    }

    public static void requestMidEntity(Context context, MidCallback midCallback) {
        if (checkPermissions(context, midCallback)) {
            MidEntity localMidEntity = getLocalMidEntity(context);
            if (localMidEntity == null || !localMidEntity.isMidValid()) {
                logger.i("requestMidEntity -> request new mid entity.");
                WorkThread.getInstance().doWork(new ServiceRunnable(context, 1, midCallback));
                return;
            }
            logger.i("requestMidEntity -> get local mid entity:" + localMidEntity.toString());
            midCallback.onSuccess(localMidEntity.toString());
            WorkThread.getInstance().doWork(new ServiceRunnable(context, 2, midCallback));
        }
    }

    public static String getMid(Context context) {
        if (context == null) {
            logger.e((Object) "context==null in getMid()");
            return null;
        }
        String readMidString = UnifiedStorage.getInstance(context).readMidString();
        if (Util.isMidValid(readMidString)) {
            return readMidString;
        }
        AnonymousClass2 r1 = new MidCallback() {
            public void onSuccess(Object obj) {
                if (obj != null) {
                    ServiceIMPL.logger.d("success to get mid:" + MidEntity.parse(obj.toString()).getMid());
                }
            }

            public void onFail(int i, String str) {
                ServiceIMPL.logger.e((Object) "failed to get mid, errorcode:" + i + " ,msg:" + str);
            }
        };
        logger.d("getMid -> request new mid entity.");
        WorkThread.getInstance().doWork(new ServiceRunnable(context, 1, r1));
        return readMidString;
    }

    public static long getGuid(Context context) {
        if (context != null) {
            return UnifiedStorage.getInstance(context).readGuid();
        }
        logger.e((Object) "context==null in getGuid()");
        return 0;
    }

    public static String getLocalMid(Context context) {
        if (context != null) {
            return UnifiedStorage.getInstance(context).readMidString();
        }
        logger.e((Object) "context==null in getMid()");
        return null;
    }

    public static boolean isMidValid(String str) {
        return Util.isMidValid(str);
    }

    public static void enableDebug(boolean z) {
        Util.getLogger().setDebugEnable(z);
    }

    public static boolean isEnableDebug() {
        return Util.getLogger().isDebugEnable();
    }
}
