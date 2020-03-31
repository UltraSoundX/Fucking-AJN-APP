package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;

/* compiled from: CheckAppKey */
public class a {
    public static volatile boolean a = false;
    public static String b = null;

    public static boolean a() {
        String appkey = MobSDK.getAppkey();
        if (a || TextUtils.isEmpty(appkey)) {
            return false;
        }
        if (TextUtils.isEmpty(b)) {
            SSDKLog.b().d("CheckAppKeyAsynchronously verify the appkey", new Object[0]);
            new Thread() {
                public void run() {
                    try {
                        b.a().b();
                    } catch (Throwable th) {
                        SSDKLog.b().d("CheckAppKeyAsyn verify the appkey catch " + th, new Object[0]);
                    }
                }
            }.start();
            return true;
        }
        SSDKLog.b().d("CheckAppKeyDetermine whether successAppKey is equal to mobsdk.getappkey", new Object[0]);
        return appkey.equals(b);
    }
}
