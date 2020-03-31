package cn.sharesdk.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import cn.sharesdk.framework.loopshare.LoopShareResultListener;
import cn.sharesdk.framework.loopshare.MoblinkActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import java.util.HashMap;

public class ShareSDK {
    public static final String SDK_TAG = "SHARESDK";
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME = "3.6.8";
    public static final String SHARESDK_MOBLINK_RESTORE = "sharesdk_moblink_restore";
    private static i a;
    private static boolean b = true;

    static {
        int i = 0;
        for (String parseInt : SDK_VERSION_NAME.split("\\.")) {
            i = (i * 100) + Integer.parseInt(parseInt);
        }
        SDK_VERSION_CODE = i;
        c();
    }

    private static synchronized void c() {
        synchronized (ShareSDK.class) {
            if (a == null) {
                i iVar = new i();
                iVar.d();
                a = iVar;
            }
        }
    }

    public static void registerService(Class<? extends Service> cls) {
        c();
        a.a(cls);
    }

    public static void unregisterService(Class<? extends Service> cls) {
        c();
        a.b(cls);
    }

    public static <T extends Service> T getService(Class<T> cls) {
        c();
        return a.c(cls);
    }

    public static void registerPlatform(Class<? extends CustomPlatform> cls) {
        c();
        a.d(cls);
    }

    public static void unregisterPlatform(Class<? extends CustomPlatform> cls) {
        c();
        a.e(cls);
    }

    public static void setConnTimeout(int i) {
        c();
        a.a(i);
    }

    public static void setReadTimeout(int i) {
        c();
        a.b(i);
    }

    public static void removeCookieOnAuthorize(boolean z) {
        c();
        a.b(z);
    }

    public static void deleteCache() {
        c();
        a.i();
    }

    public static Platform[] getPlatformList() {
        c();
        return a.e();
    }

    public static Platform getPlatform(String str) {
        c();
        return a.a(str);
    }

    public static void logDemoEvent(int i, Platform platform) {
        c();
        a.a(i, platform);
    }

    public static void logApiEvent(String str, int i) {
        c();
        a.a(str, i);
    }

    public static void setPlatformDevInfo(String str, HashMap<String, Object> hashMap) {
        c();
        a.a(str, hashMap);
    }

    public static String platformIdToName(int i) {
        c();
        return a.c(i);
    }

    public static int platformNameToId(String str) {
        c();
        return a.b(str);
    }

    public static boolean isRemoveCookieOnAuthorize() {
        c();
        return a.f();
    }

    public static void closeDebug() {
        b = false;
    }

    public static boolean isDebug() {
        return b;
    }

    public static void setEnableAuthTag(boolean z) {
        c();
        a.a(z);
    }

    public static void setActivity(Activity activity) {
        c();
        a.a(activity);
    }

    public static Activity getAuthActivity() {
        c();
        return a.a();
    }

    public static boolean getEnableAuthTag() {
        c();
        return a.b();
    }

    public static Bitmap getQRCodeBitmap(String str, int i, int i2) {
        c();
        return a.a(str, i, i2);
    }

    public static void mobLinkGetMobID(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        c();
        try {
            a.a(hashMap, moblinkActionListener);
        } catch (Throwable th) {
            SSDKLog.b().e("ShareSDK mobLinkGetMobID " + th, new Object[0]);
        }
    }

    public static void prepareLoopShare(LoopShareResultListener loopShareResultListener) {
        c();
        try {
            a.a(loopShareResultListener);
        } catch (Throwable th) {
            SSDKLog.b().e("ShareSDK prepareLoopShare " + th, new Object[0]);
        }
    }

    public static HashMap<String, Object> getCustomDataFromLoopShare() {
        c();
        return a.c();
    }

    static void a(String str, String str2) {
        c();
        a.a(str, str2);
    }

    static void a(int i, int i2) {
        c();
        a.a(i, i2);
    }

    static String b(String str, String str2) {
        c();
        return a.b(str, str2);
    }

    static String a(int i, String str) {
        c();
        return a.a(i, str);
    }

    static boolean a() {
        c();
        return a.g();
    }

    static boolean b() {
        c();
        return a.h();
    }

    static String a(String str, boolean z, int i, String str2) {
        c();
        return a.a(str, z, i, str2);
    }

    static String a(String str) {
        c();
        return a.c(str);
    }

    static String a(Bitmap bitmap) {
        c();
        return a.a(bitmap);
    }
}
