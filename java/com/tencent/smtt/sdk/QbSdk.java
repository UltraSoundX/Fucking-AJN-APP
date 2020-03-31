package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.provider.FontsContractCompat.Columns;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.api.MidEntity;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsDownloadUpload.TbsUploadKey;
import com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.a.c;
import com.tencent.smtt.sdk.b.a.d;
import com.tencent.smtt.utils.FileProvider;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.f;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.m;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class QbSdk {
    private static String A = null;
    /* access modifiers changed from: private */
    public static boolean B = false;
    private static boolean C = true;
    /* access modifiers changed from: private */
    public static TbsListener D = null;
    /* access modifiers changed from: private */
    public static TbsListener E = null;
    public static final int EXTENSION_INIT_FAILURE = -99999;
    private static boolean F = false;
    public static final String FILERADER_MENUDATA = "menuData";
    private static boolean G = false;
    public static String KEY_SET_SENDREQUEST_AND_UPLOAD = "SET_SENDREQUEST_AND_UPLOAD";
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int QBMODE = 2;
    public static final String SVNVERSION = "jnizz";
    public static final int TBSMODE = 1;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;
    static boolean a = false;
    static boolean b = false;
    static boolean c = true;
    static String d;
    static boolean e = false;
    static long f = 0;
    static long g = 0;
    static Object h = new Object();
    static boolean i = true;
    public static boolean isDefaultDialog = false;
    static boolean j = true;
    static boolean k = false;
    static volatile boolean l = a;
    static TbsListener m = new TbsListener() {
        public void onDownloadFinish(int i) {
            if (TbsDownloader.needDownloadDecoupleCore()) {
                TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is true", true);
                TbsDownloader.a = true;
                return;
            }
            TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is false", true);
            TbsDownloader.a = false;
            if (i == 100) {
            }
            if (QbSdk.D != null) {
                QbSdk.D.onDownloadFinish(i);
            }
            if (QbSdk.E != null) {
                QbSdk.E.onDownloadFinish(i);
            }
        }

        public void onInstallFinish(int i) {
            if (i == 200 || i == 220) {
            }
            QbSdk.setTBSInstallingStatus(false);
            TbsDownloader.a = false;
            if (TbsDownloader.startDecoupleCoreIfNeeded()) {
                TbsDownloader.a = true;
            } else {
                TbsDownloader.a = false;
            }
            if (QbSdk.D != null) {
                QbSdk.D.onInstallFinish(i);
            }
            if (QbSdk.E != null) {
                QbSdk.E.onInstallFinish(i);
            }
        }

        public void onDownloadProgress(int i) {
            if (QbSdk.E != null) {
                QbSdk.E.onDownloadProgress(i);
            }
            if (QbSdk.D != null) {
                QbSdk.D.onDownloadProgress(i);
            }
        }
    };
    public static boolean mDisableUseHostBackupCore = false;
    static Map<String, Object> n = null;
    private static int o = 0;
    private static String p = "";

    /* renamed from: q reason: collision with root package name */
    private static Class<?> f433q = null;
    private static Object r = null;
    private static boolean s = false;
    public static boolean sIsVersionPrinted = false;
    private static String[] t;
    private static String u = "NULL";
    private static String v = "UNKNOWN";
    private static boolean w = false;
    private static int x = 0;
    private static int y = ErrorCode.NEEDDOWNLOAD_TRUE;
    private static String z = null;

    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i2, WebView webView) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (str2 == TbsConfig.APP_WX || str2 == TbsConfig.APP_QQ) {
                    t a2 = t.a();
                    if (a2 != null && a2.b()) {
                        Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0]);
                        if (invokeStaticMethod != null) {
                            IX5WebViewBase iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod;
                            if (iX5WebViewBase != null) {
                                webView = (WebView) iX5WebViewBase.getView().getParent();
                            }
                        }
                    }
                }
            } catch (Exception e2) {
            }
        }
        return c.a(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQBForVideo(Context context, String str, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        return c.a(context, str, hashMap);
    }

    public static boolean startQBForDoc(Context context, String str, int i2, int i3, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i2));
        return c.a(context, str, i3, str2, hashMap, bundle);
    }

    public static boolean getIsSysWebViewForcedByOuter() {
        return b;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(Context context, boolean z2) {
        int i2;
        File file;
        String absolutePath;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: jnizz; SDK_VERSION_CODE: 43697; SDK_VERSION_NAME: 4.3.0.1148");
            sIsVersionPrinted = true;
        }
        if (a && !z2) {
            TbsLog.e("QbSdk", "QbSdk init: " + v, false);
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_SDKINIT_IS_SYS_FORCED, new Throwable(v));
            return false;
        } else if (b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_OUTER, new Throwable(u));
            return false;
        } else {
            if (!C) {
                d(context);
            }
            try {
                File q2 = l.a().q(context);
                if (q2 == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_TBSCORE_SHARE_DIR, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    if (o != 0) {
                        i2 = l.a().a(true, context);
                        if (o != i2) {
                            f433q = null;
                            r = null;
                            TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + i2, true);
                            TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + o, true);
                            TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_UNMATCH_TBSCORE_VER, new Throwable("sTbsVersion: " + o + "; tbsCoreInstalledVer: " + i2));
                            return false;
                        }
                    } else {
                        i2 = 0;
                    }
                    o = i2;
                } else if (o == 0 || o == TbsShareManager.d(context)) {
                    o = TbsShareManager.d(context);
                } else {
                    f433q = null;
                    r = null;
                    TbsLog.e("QbSdk", "QbSdk init (false) ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY!");
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY, new Throwable("sTbsVersion: " + o + "; AvailableTbsCoreVersion: " + TbsShareManager.d(context)));
                    return false;
                }
                if (f433q != null && r != null) {
                    return true;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    file = new File(l.a().q(context), "tbs_sdk_extension_dex.jar");
                } else if (TbsShareManager.j(context)) {
                    file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
                } else {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_HOST_UNAVAILABLE, new Throwable("isShareTbsCoreAvailable false!"));
                    return false;
                }
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                    int i3 = l.a().i(context);
                    if (new File(file.getParentFile(), "tbs_jars_fusion_dex.jar").exists()) {
                        if (i3 > 0) {
                            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i3));
                        } else {
                            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i3));
                        }
                        return false;
                    }
                    if (i3 > 0) {
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i3));
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i3));
                    }
                    return false;
                }
                if (TbsShareManager.getHostCorePathAppDefined() != null) {
                    absolutePath = TbsShareManager.getHostCorePathAppDefined();
                } else {
                    absolutePath = q2.getAbsolutePath();
                }
                TbsLog.i("QbSdk", "QbSdk init optDirExtension #1 is " + absolutePath);
                TbsLog.i("QbSdk", "new DexLoader #1 dexFile is " + file.getAbsolutePath());
                t.a().b(context);
                m.a(context);
                Context context2 = context;
                f433q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                loadTBSSDKExtension(context, file.getParent());
                k.a(r, "setClientVersion", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(1));
                return true;
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.THROWABLE_QBSDK_INIT, th);
                return false;
            }
        }
    }

    public static void loadTBSSDKExtension(Context context, String str) {
        Constructor constructor;
        String str2 = null;
        boolean z2 = true;
        boolean z3 = false;
        if (r == null) {
            synchronized (QbSdk.class) {
                if (r == null) {
                    if (f433q == null) {
                        TbsLog.i("QbSdk", "QbSdk loadTBSSDKExtension sExtensionClass is null");
                    }
                    try {
                        constructor = f433q.getConstructor(new Class[]{Context.class, Context.class, String.class, String.class, String.class});
                    } catch (Throwable th) {
                        z2 = z3;
                        constructor = null;
                    }
                    try {
                        if (TbsShareManager.isThirdPartyApp(context)) {
                            Context e2 = TbsShareManager.e(context);
                            if (e2 == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                                TbsLogReport.getInstance(StubApp.getOrigApplicationContext(context.getApplicationContext())).setLoadErrorCode((int) ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                                return;
                            } else if (z2) {
                                r = constructor.newInstance(new Object[]{context, e2, TbsShareManager.getHostCorePathAppDefined(), str, null});
                            } else if (e2 == null) {
                                r = f433q.getConstructor(new Class[]{Context.class, Context.class, String.class}).newInstance(new Object[]{context, e2, TbsShareManager.getHostCorePathAppDefined(), str, null});
                            } else {
                                r = f433q.getConstructor(new Class[]{Context.class, Context.class}).newInstance(new Object[]{context, e2});
                            }
                        } else if (!z2) {
                            Constructor constructor2 = f433q.getConstructor(new Class[]{Context.class, Context.class});
                            if (StubApp.getOrigApplicationContext(context.getApplicationContext()) != null) {
                                context = StubApp.getOrigApplicationContext(context.getApplicationContext());
                            }
                            r = constructor2.newInstance(new Object[]{context, context});
                        } else {
                            if (TbsConfig.APP_WX.equals(getCurrentProcessName(context)) && !WebView.mWebViewCreated) {
                                str2 = "notLoadSo";
                            }
                            if (StubApp.getOrigApplicationContext(context.getApplicationContext()) != null) {
                                context = StubApp.getOrigApplicationContext(context.getApplicationContext());
                            }
                            r = constructor.newInstance(new Object[]{context, context, null, str, str2});
                        }
                    } catch (Throwable th2) {
                        TbsLog.e("QbSdk", "throwable" + Log.getStackTraceString(th2));
                    }
                    return;
                }
                return;
            }
        }
        return;
    }

    public static void initForinitAndNotLoadSo(Context context) {
        if (f433q == null) {
            File q2 = l.a().q(context);
            if (q2 == null) {
                Log.e("QbSdk", "QbSdk initForinitAndNotLoadSo optDir == null");
                return;
            }
            File file = new File(q2, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                Log.e("QbSdk", "QbSdk initForinitAndNotLoadSo dexFile.exists()=false");
                return;
            }
            String absolutePath = q2.getAbsolutePath();
            t.a().b(context);
            m.a(context);
            String[] strArr = {file.getAbsolutePath()};
            f433q = new DexLoader(file.getParent(), context, strArr, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
        }
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        String absolutePath;
        try {
            if (context.getApplicationInfo().packageName.contains("com.moji.mjweather") && VERSION.SDK_INT == 19) {
                return true;
            }
            if (f433q == null) {
                File q2 = l.a().q(context);
                if (q2 == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
                if (TbsShareManager.getHostCorePathAppDefined() != null) {
                    absolutePath = TbsShareManager.getHostCorePathAppDefined();
                } else {
                    absolutePath = q2.getAbsolutePath();
                }
                TbsLog.i("QbSdk", "QbSdk init optDirExtension #2 is " + absolutePath);
                TbsLog.i("QbSdk", "new DexLoader #2 dexFile is " + file.getAbsolutePath());
                t.a().b(context);
                m.a(context);
                Context context2 = context;
                f433q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                if (r == null) {
                    if (TbsShareManager.e(context) == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                        TbsLogReport.getInstance(StubApp.getOrigApplicationContext(context.getApplicationContext())).setLoadErrorCode((int) ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                        return false;
                    }
                    loadTBSSDKExtension(context, file.getParent());
                }
            }
            Object a2 = k.a(r, "canLoadX5CoreForThirdApp", (Class<?>[]) new Class[0], new Object[0]);
            if (a2 == null || !(a2 instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    static boolean a(Context context) {
        try {
            if (f433q != null) {
                return true;
            }
            File q2 = l.a().q(context);
            if (q2 == null) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(q2, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                return false;
            }
            TbsLog.i("QbSdk", "new DexLoader #3 dexFile is " + file.getAbsolutePath());
            t.a().b(context);
            m.a(context);
            Context context2 = context;
            f433q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, q2.getAbsolutePath(), getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            loadTBSSDKExtension(context, file.getParent());
            return true;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    private static boolean c(Context context) {
        File file;
        String absolutePath;
        try {
            if (f433q != null) {
                return true;
            }
            File q2 = l.a().q(context);
            if (q2 == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(l.a().q(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.j(context)) {
                file = new File(TbsShareManager.c(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_HOST_UNAVAILABLE);
                return false;
            }
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_MISS_SDKEXTENSION_JAR_OLD, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            if (TbsShareManager.getHostCorePathAppDefined() != null) {
                absolutePath = TbsShareManager.getHostCorePathAppDefined();
            } else {
                absolutePath = q2.getAbsolutePath();
            }
            TbsLog.i("QbSdk", "QbSdk init optDirExtension #3 is " + absolutePath);
            TbsLog.i("QbSdk", "new DexLoader #4 dexFile is " + file.getAbsolutePath());
            t.a().b(context);
            m.a(context);
            Context context2 = context;
            f433q = new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            loadTBSSDKExtension(context, file.getParent());
            k.a(r, "setClientVersion", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(1));
            return true;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void setOnlyDownload(boolean z2) {
        k = z2;
    }

    public static boolean getOnlyDownload() {
        return k;
    }

    static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (context.getApplicationInfo().packageName.contains("com.tencent.portfolio")) {
                TbsLog.i("QbSdk", "clearPluginConfigFile #1");
                String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null);
                String str = context.getPackageManager().getPackageInfo("com.tencent.portfolio", 0).versionName;
                TbsLog.i("QbSdk", "clearPluginConfigFile oldAppVersionName is " + string + " newAppVersionName is " + str);
                if (string != null && !string.contains(str)) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("plugin_setting", 0);
                    if (sharedPreferences != null) {
                        Editor edit = sharedPreferences.edit();
                        edit.clear();
                        edit.commit();
                        TbsLog.i("QbSdk", "clearPluginConfigFile done");
                    }
                }
            }
            return true;
        } catch (Throwable th) {
            TbsLog.i("QbSdk", "clearPluginConfigFile error is " + th.getMessage());
            return false;
        }
    }

    static Bundle a(Context context, Bundle bundle) throws Exception {
        if (!a(context)) {
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.INCR_UPDATE_ERROR, "initForPatch return false!");
            return null;
        }
        Object a2 = k.a(r, "incrUpdate", (Class<?>[]) new Class[]{Context.class, Bundle.class}, context, bundle);
        if (a2 != null) {
            return (Bundle) a2;
        }
        TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.INCR_UPDATE_ERROR, "incrUpdate return null!");
        return null;
    }

    static boolean a(Context context, int i2) {
        return a(context, i2, 20000);
    }

    static boolean a(Context context, int i2, int i3) {
        if (n == null || !n.containsKey(KEY_SET_SENDREQUEST_AND_UPLOAD) || !n.get(KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            l.a().b(context, d.a == 0);
            if (!c(context)) {
                return true;
            }
            Object a2 = k.a(r, "isX5Disabled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(43697), Integer.valueOf(i3));
            if (a2 != null) {
                return ((Boolean) a2).booleanValue();
            }
            Object a3 = k.a(r, "isX5Disabled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(43697));
            if (a3 != null) {
                return ((Boolean) a3).booleanValue();
            }
            return true;
        }
        TbsLog.i("QbSdk", "[QbSdk.isX5Disabled] -- SET_SENDREQUEST_AND_UPLOAD is false");
        return true;
    }

    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fb A[SYNTHETIC, Splitter:B:48:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x014c A[SYNTHETIC, Splitter:B:78:0x014c] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canOpenWebPlus(android.content.Context r9) {
        /*
            r4 = 0
            r8 = 88888888(0x54c5638, float:9.60787E-36)
            r1 = 1
            r2 = 0
            int r0 = x
            if (r0 != 0) goto L_0x0010
            int r0 = com.tencent.smtt.sdk.a.a()
            x = r0
        L_0x0010:
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "canOpenWebPlus - totalRAM: "
            java.lang.StringBuilder r3 = r3.append(r5)
            int r5 = x
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r3)
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 7
            if (r0 < r3) goto L_0x0035
            int r0 = x
            int r3 = y
            if (r0 >= r3) goto L_0x0036
        L_0x0035:
            return r2
        L_0x0036:
            if (r9 == 0) goto L_0x0035
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            com.tencent.smtt.sdk.l r3 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            java.io.File r3 = r3.q(r9)     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            java.lang.String r5 = "tbs.conf"
            r0.<init>(r3, r5)     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            r3.<init>(r5)     // Catch:{ Throwable -> 0x00e1, all -> 0x00f7 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            r0.<init>()     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            r0.load(r3)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            java.lang.String r5 = "android_sdk_max_supported"
            java.lang.String r5 = r0.getProperty(r5)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            java.lang.String r6 = "android_sdk_min_supported"
            java.lang.String r6 = r0.getProperty(r6)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            java.lang.String r7 = android.os.Build.VERSION.SDK     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            if (r7 > r5) goto L_0x0077
            if (r7 >= r6) goto L_0x0097
        L_0x0077:
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            r1.<init>()     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            java.lang.String r4 = "canOpenWebPlus - sdkVersion: "
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            if (r3 == 0) goto L_0x0035
            r3.close()     // Catch:{ Exception -> 0x0095 }
            goto L_0x0035
        L_0x0095:
            r0 = move-exception
            goto L_0x0035
        L_0x0097:
            java.lang.String r5 = "tbs_core_version"
            java.lang.String r0 = r0.getProperty(r5)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x0168, all -> 0x0163 }
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ Exception -> 0x0152 }
        L_0x00a6:
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x0136, all -> 0x0148 }
            java.io.File r3 = com.tencent.smtt.sdk.l.s(r9)     // Catch:{ Throwable -> 0x0136, all -> 0x0148 }
            java.lang.String r6 = "tbs_extension.conf"
            r5.<init>(r3, r6)     // Catch:{ Throwable -> 0x0136, all -> 0x0148 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0136, all -> 0x0148 }
            r3.<init>(r5)     // Catch:{ Throwable -> 0x0136, all -> 0x0148 }
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            r4.<init>()     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            r4.load(r3)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            java.lang.String r5 = "tbs_local_version"
            java.lang.String r5 = r4.getProperty(r5)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            java.lang.String r6 = "app_versioncode_for_switch"
            java.lang.String r6 = r4.getProperty(r6)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            if (r0 == r8) goto L_0x00d6
            if (r5 != r8) goto L_0x00ff
        L_0x00d6:
            r0 = r2
        L_0x00d7:
            if (r3 == 0) goto L_0x00dc
            r3.close()     // Catch:{ Exception -> 0x0157 }
        L_0x00dc:
            if (r0 != 0) goto L_0x0150
        L_0x00de:
            r2 = r1
            goto L_0x0035
        L_0x00e1:
            r0 = move-exception
            r1 = r4
        L_0x00e3:
            r0.printStackTrace()     // Catch:{ all -> 0x0165 }
            java.lang.String r0 = "QbSdk"
            java.lang.String r3 = "canOpenWebPlus - canLoadX5 Exception"
            com.tencent.smtt.utils.TbsLog.i(r0, r3)     // Catch:{ all -> 0x0165 }
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ Exception -> 0x00f4 }
            goto L_0x0035
        L_0x00f4:
            r0 = move-exception
            goto L_0x0035
        L_0x00f7:
            r0 = move-exception
            r3 = r4
        L_0x00f9:
            if (r3 == 0) goto L_0x00fe
            r3.close()     // Catch:{ Exception -> 0x0155 }
        L_0x00fe:
            throw r0
        L_0x00ff:
            if (r0 <= r5) goto L_0x0103
            r0 = r2
            goto L_0x00d7
        L_0x0103:
            if (r0 != r5) goto L_0x016c
            if (r6 <= 0) goto L_0x010f
            int r0 = com.tencent.smtt.utils.b.b(r9)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            if (r6 == r0) goto L_0x010f
            r0 = r2
            goto L_0x00d7
        L_0x010f:
            java.lang.String r0 = "x5_disabled"
            java.lang.String r0 = r4.getProperty(r0)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            if (r0 == 0) goto L_0x0134
            android.content.Context r0 = r9.getApplicationContext()     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            java.lang.String r4 = "switch_backupcore_enable"
            r5 = 0
            boolean r0 = r0.getBoolean(r4, r5)     // Catch:{ Throwable -> 0x0160, all -> 0x015b }
            if (r0 != 0) goto L_0x0134
            r0 = r1
            goto L_0x00d7
        L_0x0134:
            r0 = r2
            goto L_0x00d7
        L_0x0136:
            r0 = move-exception
        L_0x0137:
            java.lang.String r0 = "QbSdk"
            java.lang.String r3 = "canOpenWebPlus - isX5Disabled Exception"
            com.tencent.smtt.utils.TbsLog.i(r0, r3)     // Catch:{ all -> 0x015d }
            if (r4 == 0) goto L_0x0143
            r4.close()     // Catch:{ Exception -> 0x0145 }
        L_0x0143:
            r0 = r1
            goto L_0x00dc
        L_0x0145:
            r0 = move-exception
            r0 = r1
            goto L_0x00dc
        L_0x0148:
            r0 = move-exception
            r3 = r4
        L_0x014a:
            if (r3 == 0) goto L_0x014f
            r3.close()     // Catch:{ Exception -> 0x0159 }
        L_0x014f:
            throw r0
        L_0x0150:
            r1 = r2
            goto L_0x00de
        L_0x0152:
            r3 = move-exception
            goto L_0x00a6
        L_0x0155:
            r1 = move-exception
            goto L_0x00fe
        L_0x0157:
            r3 = move-exception
            goto L_0x00dc
        L_0x0159:
            r1 = move-exception
            goto L_0x014f
        L_0x015b:
            r0 = move-exception
            goto L_0x014a
        L_0x015d:
            r0 = move-exception
            r3 = r4
            goto L_0x014a
        L_0x0160:
            r0 = move-exception
            r4 = r3
            goto L_0x0137
        L_0x0163:
            r0 = move-exception
            goto L_0x00f9
        L_0x0165:
            r0 = move-exception
            r3 = r1
            goto L_0x00f9
        L_0x0168:
            r0 = move-exception
            r1 = r3
            goto L_0x00e3
        L_0x016c:
            r0 = r2
            goto L_0x00d7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canOpenWebPlus(android.content.Context):boolean");
    }

    public static boolean isX5DisabledSync(Context context) {
        if (j.a(context).c() == 2) {
            return false;
        }
        if (!c(context)) {
            return true;
        }
        Object a2 = k.a(r, "isX5DisabledSync", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(l.a().i(context)), Integer.valueOf(43697));
        if (a2 != null) {
            return ((Boolean) a2).booleanValue();
        }
        return true;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        d a2 = d.a(true);
        a2.a(context, false, false);
        if (a2 == null || !a2.b()) {
            return false;
        }
        return a2.a().a(context, str, str2, bundle);
    }

    public static boolean canUseVideoFeatrue(Context context, int i2) {
        Object a2 = k.a(r, "canUseVideoFeatrue", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        if (a2 == null || !(a2 instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static boolean canLoadVideo(Context context) {
        Object a2 = k.a(r, "canLoadVideo", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(0));
        if (a2 == null) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL);
        } else if (!((Boolean) a2).booleanValue()) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_CANLOADVIDEO_RETURN_FALSE);
        }
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    static boolean a(Context context, boolean z2, boolean z3) {
        int i2;
        boolean z4;
        boolean z5 = true;
        boolean z6 = false;
        int disabledCoreVersion = TbsPVConfig.getInstance(context).getDisabledCoreVersion();
        if (disabledCoreVersion != 0 && disabledCoreVersion == l.a().i(context)) {
            TbsLog.e("QbSdk", "force use sys by remote switch");
        } else if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.i(context)) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY);
        } else if (!a(context, z2)) {
            TbsLog.e("QbSdk", "QbSdk.init failure!");
        } else {
            Object a2 = k.a(r, "canLoadX5Core", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(43697));
            if (a2 == null) {
                Object a3 = k.a(r, "canLoadX5", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
                if (a3 == null) {
                    TbsCoreLoadStat.getInstance().a(context, 308);
                } else if (!(a3 instanceof String) || !((String) a3).equalsIgnoreCase("AuthenticationFail")) {
                    if (a3 instanceof Boolean) {
                        o = d.d();
                        boolean a4 = a(context, d.d());
                        if (((Boolean) a3).booleanValue() && !a4) {
                            z6 = true;
                        }
                        if (!z6) {
                            TbsLog.e(TbsListener.tag_load_error, "318");
                            TbsLog.w(TbsListener.tag_load_error, "isX5Disable:" + a4);
                            TbsLog.w(TbsListener.tag_load_error, "(Boolean) ret:" + ((Boolean) a3));
                        }
                    }
                }
            } else if (!(a2 instanceof String) || !((String) a2).equalsIgnoreCase("AuthenticationFail")) {
                if (!(a2 instanceof Bundle)) {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, new Throwable("" + a2));
                    TbsLog.e(TbsListener.tag_load_error, "ret not instance of bundle");
                } else {
                    Bundle bundle = (Bundle) a2;
                    if (bundle.isEmpty()) {
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, new Throwable("" + a2));
                        TbsLog.e(TbsListener.tag_load_error, "empty bundle");
                    } else {
                        try {
                            i2 = bundle.getInt(Columns.RESULT_CODE, -1);
                        } catch (Exception e2) {
                            TbsLog.e("QbSdk", "bundle.getInt(KEY_RESULT_CODE) error : " + e2.toString());
                            i2 = -1;
                        }
                        if (i2 == 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (TbsShareManager.isThirdPartyApp(context)) {
                            d.a(TbsShareManager.d(context));
                            p = String.valueOf(TbsShareManager.d(context));
                            if (p.length() == 5) {
                                p = "0" + p;
                            }
                            if (p.length() != 6) {
                                p = "";
                            }
                        } else {
                            try {
                                if (VERSION.SDK_INT >= 12) {
                                    p = bundle.getString("tbs_core_version", "0");
                                } else {
                                    p = bundle.getString("tbs_core_version");
                                    if (p == null) {
                                        p = "0";
                                    }
                                }
                            } catch (Exception e3) {
                                p = "0";
                            }
                            try {
                                o = Integer.parseInt(p);
                            } catch (NumberFormatException e4) {
                                o = 0;
                            }
                            d.a(o);
                            if (o == 0) {
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sTbsVersion is 0"));
                            } else {
                                if ((o <= 0 || o > 25442) && o != 25472) {
                                    z5 = false;
                                }
                                if (z5) {
                                    TbsLog.e(TbsDownloader.LOGTAG, "is_obsolete --> delete old core:" + o);
                                    f.b(l.a().q(context));
                                    TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("is_obsolete --> delete old core:" + o));
                                }
                            }
                        }
                        try {
                            t = bundle.getStringArray("tbs_jarfiles");
                            if (!(t instanceof String[])) {
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + t));
                            } else {
                                try {
                                    d = bundle.getString("tbs_librarypath");
                                    Object obj = null;
                                    if (i2 != 0) {
                                        try {
                                            obj = k.a(r, "getErrorCodeForLogReport", (Class<?>[]) new Class[0], new Object[0]);
                                        } catch (Exception e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    switch (i2) {
                                        case -2:
                                            if (!(obj instanceof Integer)) {
                                                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_DISABLE_X5, new Throwable("detail: " + obj));
                                                break;
                                            } else {
                                                TbsCoreLoadStat.getInstance().a(context, ((Integer) obj).intValue(), new Throwable("detail: " + obj));
                                                break;
                                            }
                                        case -1:
                                            if (!(obj instanceof Integer)) {
                                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("detail: " + obj));
                                                break;
                                            } else {
                                                TbsCoreLoadStat.getInstance().a(context, ((Integer) obj).intValue(), new Throwable("detail: " + obj));
                                                break;
                                            }
                                        case 0:
                                            break;
                                        default:
                                            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_INITX5_FALSE_DEFAULT, new Throwable("detail: " + obj + "errcode" + i2));
                                            break;
                                    }
                                    z6 = z4;
                                } catch (Exception e6) {
                                }
                            }
                        } catch (Throwable th) {
                            TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, th);
                        }
                    }
                }
            }
            if (!z6) {
                TbsLog.e(TbsListener.tag_load_error, "319");
            }
        }
        return z6;
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        if (!a(context, false)) {
        }
        return false;
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(TID_QQNumber_Prefix.length());
            z = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static String getTID() {
        return z;
    }

    public static String getTbsResourcesPath(Context context) {
        return TbsShareManager.g(context);
    }

    public static void setQQBuildNumber(String str) {
        A = str;
    }

    public static String getQQBuildNumber() {
        return A;
    }

    static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!a) {
                a = true;
                v = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + v);
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_INNER, new Throwable(v));
            }
        }
    }

    public static void forceSysWebView() {
        b = true;
        u = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static void unForceSysWebView() {
        b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    public static void canOpenFile(final Context context, final String str, final ValueCallback<Boolean> valueCallback) {
        new Thread() {
            public void run() {
                t a2 = t.a();
                a2.a(context);
                final boolean z = false;
                if (a2.b()) {
                    z = a2.c().a(context, str);
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        valueCallback.onReceiveValue(Boolean.valueOf(z));
                    }
                });
            }
        }.start();
    }

    public static void closeFileReader(Context context) {
        t a2 = t.a();
        a2.a(context);
        if (a2.b()) {
            a2.c().p();
        }
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, null);
        }
    }

    public static void setDisableUseHostBackupCoreBySwitch(boolean z2) {
        mDisableUseHostBackupCore = z2;
        TbsLog.i("QbSdk", "setDisableUseHostBackupCoreBySwitch -- mDisableUseHostBackupCore is " + mDisableUseHostBackupCore);
    }

    public static void setDisableUnpreinitBySwitch(boolean z2) {
        B = z2;
        TbsLog.i("QbSdk", "setDisableUnpreinitBySwitch -- mDisableUnpreinitBySwitch is " + B);
    }

    public static synchronized boolean unPreInit(Context context) {
        synchronized (QbSdk.class) {
        }
        return true;
    }

    public static String getCurrentProcessName(Context context) {
        String str;
        try {
            int myPid = Process.myPid();
            String str2 = "";
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    str = runningAppProcessInfo.processName;
                } else {
                    str = str2;
                }
                str2 = str;
            }
            return str2;
        } catch (Throwable th) {
            return "";
        }
    }

    public static synchronized void preInit(final Context context, final PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            TbsLog.i("QbSdk", "preInit -- processName: " + getCurrentProcessName(context));
            TbsLog.i("QbSdk", "preInit -- stack: " + Log.getStackTraceString(new Throwable("#")));
            l = a;
            if (!s) {
                final AnonymousClass3 r0 = new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message message) {
                        switch (message.what) {
                            case 1:
                                QbSdk.B = TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_UNPREINIT);
                                if (QbSdk.j) {
                                    u c = t.a().c();
                                    if (c != null) {
                                        c.a(context);
                                    }
                                }
                                if (preInitCallback != null) {
                                    preInitCallback.onViewInitFinished(true);
                                }
                                TbsLog.writeLogToDisk();
                                return;
                            case 2:
                                if (preInitCallback != null) {
                                    preInitCallback.onViewInitFinished(false);
                                }
                                TbsLog.writeLogToDisk();
                                return;
                            case 3:
                                if (preInitCallback != null) {
                                    preInitCallback.onCoreInitFinished();
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                };
                AnonymousClass4 r2 = new Thread() {
                    public void run() {
                        int a2 = l.a().a(true, context);
                        TbsDownloader.setAppContext(context);
                        TbsLog.i("QbSdk", "QbSdk preinit ver is " + a2);
                        if (a2 == 0) {
                            l.a().b(context, true);
                        }
                        TbsLog.i("QbSdk", "preInit -- prepare initAndLoadSo");
                        d.a(true).a(context, false, false);
                        t a3 = t.a();
                        a3.a(context);
                        boolean b2 = a3.b();
                        r0.sendEmptyMessage(3);
                        if (!b2) {
                            r0.sendEmptyMessage(2);
                        } else {
                            r0.sendEmptyMessage(1);
                        }
                    }
                };
                r2.setName("tbs_preinit");
                r2.setPriority(10);
                r2.start();
                s = true;
            }
        }
    }

    public static void setUploadCode(Context context, int i2) {
        if (i2 >= 130 && i2 <= 139) {
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(i2));
            instance.commit();
        } else if (i2 >= 150 && i2 <= 159) {
            TbsDownloadUpload instance2 = TbsDownloadUpload.getInstance(context);
            instance2.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(i2));
            instance2.commit();
        }
    }

    public static void checkTbsValidity(Context context) {
        if (context != null && !m.b(context)) {
            TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedBy checkTbsValidity");
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CORE_CHECK_VALIDITY_FALSE);
            forceSysWebView();
        }
    }

    public static void disableAutoCreateX5Webview() {
        j = false;
    }

    public static boolean isTbsCoreInited() {
        d a2 = d.a(false);
        if (a2 == null || !a2.g()) {
            return false;
        }
        return true;
    }

    public static void initX5Environment(final Context context, final PreInitCallback preInitCallback) {
        boolean z2;
        if (context == null) {
            TbsLog.e("QbSdk", "initX5Environment,context=null");
            return;
        }
        b(context);
        E = new TbsListener() {
            public void onDownloadFinish(int i) {
            }

            public void onInstallFinish(int i) {
                QbSdk.preInit(context, preInitCallback);
            }

            public void onDownloadProgress(int i) {
            }
        };
        if (TbsShareManager.isThirdPartyApp(context)) {
            l a2 = l.a();
            if (d.a == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            a2.b(context, z2);
        }
        TbsDownloader.needDownload(context, false, false, true, new TbsDownloaderCallback() {
            public void onNeedDownloadFinish(boolean z, int i) {
                if (TbsShareManager.findCoreForThirdPartyApp(context) == 0 && !TbsShareManager.getCoreDisabled()) {
                    TbsShareManager.forceToLoadX5ForThirdApp(context, false);
                }
                if (QbSdk.i && TbsShareManager.isThirdPartyApp(context)) {
                    TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
                }
                QbSdk.preInit(context, preInitCallback);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0113 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void d(android.content.Context r10) {
        /*
            r9 = 3
            r6 = 4
            r5 = 0
            r1 = -1
            r0 = 1
            C = r0
            r2 = 0
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00cd }
            r3 = 11
            if (r0 < r3) goto L_0x0023
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            r3 = 4
            android.content.SharedPreferences r3 = r10.getSharedPreferences(r0, r3)     // Catch:{ Throwable -> 0x00cd }
        L_0x0015:
            java.lang.String r0 = "tbs_preload_x5_recorder"
            r2 = -1
            int r2 = r3.getInt(r0, r2)     // Catch:{ Throwable -> 0x017d }
            if (r2 < 0) goto L_0x0189
            int r2 = r2 + 1
            if (r2 <= r6) goto L_0x002b
        L_0x0022:
            return
        L_0x0023:
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            r3 = 0
            android.content.SharedPreferences r3 = r10.getSharedPreferences(r0, r3)     // Catch:{ Throwable -> 0x00cd }
            goto L_0x0015
        L_0x002b:
            r0 = r2
        L_0x002c:
            com.tencent.smtt.sdk.l r4 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0182 }
            int r4 = r4.i(r10)     // Catch:{ Throwable -> 0x0182 }
            if (r4 <= 0) goto L_0x0022
            if (r0 > r6) goto L_0x0045
            android.content.SharedPreferences$Editor r6 = r3.edit()     // Catch:{ Throwable -> 0x0186 }
            java.lang.String r7 = "tbs_preload_x5_recorder"
            android.content.SharedPreferences$Editor r0 = r6.putInt(r7, r0)     // Catch:{ Throwable -> 0x0186 }
            r0.commit()     // Catch:{ Throwable -> 0x0186 }
        L_0x0045:
            java.lang.String r0 = "tbs_preload_x5_counter"
            r6 = -1
            int r0 = r3.getInt(r0, r6)     // Catch:{ Throwable -> 0x0186 }
            if (r0 < 0) goto L_0x00ed
            android.content.SharedPreferences$Editor r6 = r3.edit()     // Catch:{ Throwable -> 0x0186 }
            java.lang.String r7 = "tbs_preload_x5_counter"
            int r0 = r0 + 1
            android.content.SharedPreferences$Editor r6 = r6.putInt(r7, r0)     // Catch:{ Throwable -> 0x0186 }
            r6.commit()     // Catch:{ Throwable -> 0x0186 }
        L_0x005d:
            if (r0 <= r9) goto L_0x0113
            java.lang.String r0 = "tbs_preload_x5_version"
            r1 = -1
            int r0 = r3.getInt(r0, r1)     // Catch:{ Throwable -> 0x00ae }
            android.content.SharedPreferences$Editor r1 = r3.edit()     // Catch:{ Throwable -> 0x00ae }
            if (r0 != r4) goto L_0x00f0
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x00ae }
            java.io.File r2 = r2.q(r10)     // Catch:{ Throwable -> 0x00ae }
            r3 = 0
            com.tencent.smtt.utils.f.a(r2, r3)     // Catch:{ Throwable -> 0x00ae }
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r10)     // Catch:{ Throwable -> 0x00ae }
            java.io.File r2 = r2.a()     // Catch:{ Throwable -> 0x00ae }
            if (r2 == 0) goto L_0x0086
            r3 = 0
            com.tencent.smtt.utils.f.a(r2, r3)     // Catch:{ Throwable -> 0x00ae }
        L_0x0086:
            java.lang.String r2 = "QbSdk"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ae }
            r3.<init>()     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r5 = "QbSdk - preload_x5_check: tbs core "
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Throwable -> 0x00ae }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r4 = " is deleted!"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00ae }
            com.tencent.smtt.utils.TbsLog.e(r2, r3)     // Catch:{ Throwable -> 0x00ae }
        L_0x00a4:
            java.lang.String r2 = "tbs_precheck_disable_version"
            r1.putInt(r2, r0)     // Catch:{ Throwable -> 0x00ae }
            r1.commit()     // Catch:{ Throwable -> 0x00ae }
            goto L_0x0022
        L_0x00ae:
            r0 = move-exception
            java.lang.String r1 = "QbSdk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "tbs_preload_x5_counter disable version exception:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
            goto L_0x0022
        L_0x00cd:
            r0 = move-exception
            r3 = r2
            r4 = r1
            r2 = r1
        L_0x00d1:
            java.lang.String r6 = "QbSdk"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "tbs_preload_x5_counter Inc exception:"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r7.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r6, r0)
        L_0x00ed:
            r0 = r1
            goto L_0x005d
        L_0x00f0:
            java.lang.String r2 = "QbSdk"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ae }
            r3.<init>()     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r5 = "QbSdk - preload_x5_check -- reset exception core_ver:"
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Throwable -> 0x00ae }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r4 = "; value:"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00ae }
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ Throwable -> 0x00ae }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00ae }
            com.tencent.smtt.utils.TbsLog.e(r2, r3)     // Catch:{ Throwable -> 0x00ae }
            goto L_0x00a4
        L_0x0113:
            if (r2 <= 0) goto L_0x012d
            if (r2 > r9) goto L_0x012d
            java.lang.String r0 = "QbSdk"
            java.lang.String r1 = "QbSdk - preload_x5_check -- before creation!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            com.tencent.smtt.sdk.t r0 = com.tencent.smtt.sdk.t.a()
            r0.a(r10)
            java.lang.String r0 = "QbSdk"
            java.lang.String r1 = "QbSdk - preload_x5_check -- after creation!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r1 = r5
        L_0x012d:
            java.lang.String r0 = "tbs_preload_x5_counter"
            r2 = -1
            int r0 = r3.getInt(r0, r2)     // Catch:{ Throwable -> 0x015f }
            if (r0 <= 0) goto L_0x0145
            android.content.SharedPreferences$Editor r2 = r3.edit()     // Catch:{ Throwable -> 0x015f }
            java.lang.String r3 = "tbs_preload_x5_counter"
            int r0 = r0 + -1
            android.content.SharedPreferences$Editor r0 = r2.putInt(r3, r0)     // Catch:{ Throwable -> 0x015f }
            r0.commit()     // Catch:{ Throwable -> 0x015f }
        L_0x0145:
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "QbSdk -- preload_x5_check result:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            goto L_0x0022
        L_0x015f:
            r0 = move-exception
            java.lang.String r2 = "QbSdk"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "tbs_preload_x5_counter Dec exception:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
            goto L_0x0145
        L_0x017d:
            r0 = move-exception
            r2 = r1
            r4 = r1
            goto L_0x00d1
        L_0x0182:
            r0 = move-exception
            r4 = r1
            goto L_0x00d1
        L_0x0186:
            r0 = move-exception
            goto L_0x00d1
        L_0x0189:
            r0 = r2
            r2 = r1
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.d(android.content.Context):void");
    }

    public static int getTbsSdkVersion() {
        return 43697;
    }

    public static int getTbsVersion(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        return l.a().i(context);
    }

    public static int getTbsVersionForCrash(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        int j2 = l.a().j(context);
        if (j2 != 0 || j.a(context).c() != 3) {
            return j2;
        }
        reset(context);
        return j2;
    }

    public static void continueLoadSo(Context context) {
        if (TbsConfig.APP_WX.equals(getCurrentProcessName(context)) && WebView.mWebViewCreated) {
            k.a(r, "continueLoadSo", (Class<?>[]) new Class[0], new Object[0]);
        }
    }

    public static boolean getJarFilesAndLibraryPath(Context context) {
        if (r == null) {
            TbsLog.i("QbSdk", "getJarFilesAndLibraryPath sExtensionObj is null");
            return false;
        }
        Bundle bundle = (Bundle) k.a(r, "canLoadX5CoreAndNotLoadSo", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(43697));
        if (bundle == null) {
            TbsLog.i("QbSdk", "getJarFilesAndLibraryPath bundle is null and coreverison is " + l.a().a(true, context));
            return false;
        }
        t = bundle.getStringArray("tbs_jarfiles");
        d = bundle.getString("tbs_librarypath");
        return true;
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        if (t instanceof String[]) {
            int length = t.length;
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = str + t[i2];
            }
            return strArr;
        }
        Object a2 = k.a(r, "getJarFiles", (Class<?>[]) new Class[]{Context.class, Context.class, String.class}, context, context2, str);
        if (!(a2 instanceof String[])) {
            a2 = new String[]{""};
        }
        return (String[]) a2;
    }

    public static boolean useSoftWare() {
        if (r == null) {
            return false;
        }
        Object a2 = k.a(r, "useSoftWare", (Class<?>[]) new Class[0], new Object[0]);
        if (a2 == null) {
            a2 = k.a(r, "useSoftWare", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(a.a()));
        }
        return a2 == null ? false : ((Boolean) a2).booleanValue();
    }

    public static void setTbsListener(TbsListener tbsListener) {
        D = tbsListener;
    }

    public static void setDownloadWithoutWifi(boolean z2) {
        F = z2;
    }

    public static boolean getDownloadWithoutWifi() {
        return F;
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(StubApp.getOrigApplicationContext(context.getApplicationContext())).mPreferences.getLong(TbsConfigKey.KEY_TBSAPKFILESIZE, 0);
        }
        return 0;
    }

    public static void setTBSInstallingStatus(boolean z2) {
        G = z2;
    }

    public static boolean getTBSInstalling() {
        return G;
    }

    static String a() {
        return p;
    }

    public static void reset(Context context) {
        reset(context, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != r3) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void reset(android.content.Context r5, boolean r6) {
        /*
            r0 = 1
            r1 = 0
            java.lang.String r2 = "QbSdk"
            java.lang.String r3 = "QbSdk reset!"
            com.tencent.smtt.utils.TbsLog.e(r2, r3, r0)
            com.tencent.smtt.sdk.TbsDownloader.stopDownload()     // Catch:{ Throwable -> 0x0081 }
            if (r6 == 0) goto L_0x009f
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r5)     // Catch:{ Throwable -> 0x0081 }
            if (r2 != 0) goto L_0x009f
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0081 }
            int r2 = r2.h(r5)     // Catch:{ Throwable -> 0x0081 }
            com.tencent.smtt.sdk.l r3 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0081 }
            int r3 = r3.i(r5)     // Catch:{ Throwable -> 0x0081 }
            r4 = 43300(0xa924, float:6.0676E-41)
            if (r2 <= r4) goto L_0x009f
            if (r2 == r3) goto L_0x009f
        L_0x002b:
            com.tencent.smtt.sdk.TbsDownloader.c(r5)     // Catch:{ Throwable -> 0x0081 }
            java.lang.String r1 = "tbs"
            r2 = 0
            java.io.File r1 = r5.getDir(r1, r2)     // Catch:{ Throwable -> 0x0081 }
            r2 = 0
            java.lang.String r3 = "core_share_decouple"
            com.tencent.smtt.utils.f.a(r1, r2, r3)     // Catch:{ Throwable -> 0x0081 }
            java.lang.String r1 = "QbSdk"
            java.lang.String r2 = "delete downloaded apk success"
            r3 = 1
            com.tencent.smtt.utils.TbsLog.i(r1, r2, r3)     // Catch:{ Throwable -> 0x0081 }
            java.lang.ThreadLocal<java.lang.Integer> r1 = com.tencent.smtt.sdk.l.a     // Catch:{ Throwable -> 0x0081 }
            r2 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0081 }
            r1.set(r2)     // Catch:{ Throwable -> 0x0081 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0081 }
            java.io.File r2 = r5.getFilesDir()     // Catch:{ Throwable -> 0x0081 }
            java.lang.String r3 = "bugly_switch.txt"
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0081 }
            if (r1 == 0) goto L_0x0063
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x0081 }
            if (r2 == 0) goto L_0x0063
            r1.delete()     // Catch:{ Throwable -> 0x0081 }
        L_0x0063:
            if (r0 == 0) goto L_0x0080
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0081 }
            java.io.File r0 = r0.p(r5)     // Catch:{ Throwable -> 0x0081 }
            com.tencent.smtt.sdk.l r1 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0081 }
            r2 = 0
            java.io.File r1 = r1.f(r5, r2)     // Catch:{ Throwable -> 0x0081 }
            com.tencent.smtt.utils.f.b(r0, r1)     // Catch:{ Throwable -> 0x0081 }
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0081 }
            r0.b(r5)     // Catch:{ Throwable -> 0x0081 }
        L_0x0080:
            return
        L_0x0081:
            r0 = move-exception
            java.lang.String r1 = "QbSdk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "QbSdk reset exception:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
            goto L_0x0080
        L_0x009f:
            r0 = r1
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.reset(android.content.Context, boolean):void");
    }

    public static void resetDecoupleCore(Context context) {
        TbsLog.e("QbSdk", "QbSdk resetDecoupleCore!", true);
        try {
            f.b(l.a().p(context));
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "QbSdk resetDecoupleCore exception:" + Log.getStackTraceString(th));
        }
    }

    public static void clear(Context context) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0071 A[SYNTHETIC, Splitter:B:15:0x0071] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void clearAllWebViewCache(android.content.Context r6, boolean r7) {
        /*
            r0 = 1
            java.lang.String r1 = "QbSdk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "clearAllWebViewCache("
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r3 = ", "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r7)
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            r1 = 0
            com.tencent.smtt.sdk.WebView r2 = new com.tencent.smtt.sdk.WebView     // Catch:{ Throwable -> 0x0052 }
            r2.<init>(r6)     // Catch:{ Throwable -> 0x0052 }
            com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension r2 = r2.getWebViewClientExtension()     // Catch:{ Throwable -> 0x0052 }
            if (r2 == 0) goto L_0x006f
            com.tencent.smtt.sdk.t r1 = com.tencent.smtt.sdk.t.a()     // Catch:{ Throwable -> 0x00de }
            if (r1 == 0) goto L_0x0048
            boolean r2 = r1.b()     // Catch:{ Throwable -> 0x00de }
            if (r2 == 0) goto L_0x0048
            com.tencent.smtt.sdk.u r1 = r1.c()     // Catch:{ Throwable -> 0x00de }
            r1.a(r6, r7)     // Catch:{ Throwable -> 0x00de }
        L_0x0048:
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "QbSdk"
            java.lang.String r1 = "is_in_x5_mode --> no need to clear system webview!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x0051:
            return
        L_0x0052:
            r0 = move-exception
        L_0x0053:
            java.lang.String r2 = "QbSdk"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "clearAllWebViewCache exception 2 -- "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
        L_0x006f:
            r0 = r1
            goto L_0x0048
        L_0x0071:
            android.webkit.WebView r0 = new android.webkit.WebView     // Catch:{ Throwable -> 0x00bf }
            r0.<init>(r6)     // Catch:{ Throwable -> 0x00bf }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00bf }
            r2 = 11
            if (r1 < r2) goto L_0x008b
            java.lang.String r1 = "searchBoxJavaBridge_"
            r0.removeJavascriptInterface(r1)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r1 = "accessibility"
            r0.removeJavascriptInterface(r1)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r1 = "accessibilityTraversal"
            r0.removeJavascriptInterface(r1)     // Catch:{ Throwable -> 0x00bf }
        L_0x008b:
            r1 = 1
            r0.clearCache(r1)     // Catch:{ Throwable -> 0x00bf }
            if (r7 == 0) goto L_0x009b
            android.webkit.CookieSyncManager.createInstance(r6)     // Catch:{ Throwable -> 0x00bf }
            android.webkit.CookieManager r0 = android.webkit.CookieManager.getInstance()     // Catch:{ Throwable -> 0x00bf }
            r0.removeAllCookie()     // Catch:{ Throwable -> 0x00bf }
        L_0x009b:
            android.webkit.WebViewDatabase r0 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ Throwable -> 0x00bf }
            r0.clearUsernamePassword()     // Catch:{ Throwable -> 0x00bf }
            android.webkit.WebViewDatabase r0 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ Throwable -> 0x00bf }
            r0.clearHttpAuthUsernamePassword()     // Catch:{ Throwable -> 0x00bf }
            android.webkit.WebViewDatabase r0 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch:{ Throwable -> 0x00bf }
            r0.clearFormData()     // Catch:{ Throwable -> 0x00bf }
            android.webkit.WebStorage r0 = android.webkit.WebStorage.getInstance()     // Catch:{ Throwable -> 0x00bf }
            r0.deleteAllData()     // Catch:{ Throwable -> 0x00bf }
            android.webkit.WebIconDatabase r0 = android.webkit.WebIconDatabase.getInstance()     // Catch:{ Throwable -> 0x00bf }
            r0.removeAllIcons()     // Catch:{ Throwable -> 0x00bf }
            goto L_0x0051
        L_0x00bf:
            r0 = move-exception
            java.lang.String r1 = "QbSdk"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "clearAllWebViewCache exception 1 -- "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
            goto L_0x0051
        L_0x00de:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.clearAllWebViewCache(android.content.Context, boolean):void");
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_COUNTS);
        if (context == null) {
            return -100;
        }
        t a2 = t.a();
        a2.a(context);
        if (!a2.b()) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_ISNOTX5CORE);
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = -102");
            return -102;
        } else if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a3 = a2.c().a(context, str, hashMap, null, valueCallback);
            if (a3 == 0) {
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_SUCCESS);
            } else {
                TbsLogReport.getInstance(context).setLoadErrorCode((int) ErrorCode.INFO_CODE_MINIQB_STARTMINIQBTOLOADURL_FAILED, "" + a3);
            }
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = " + a3);
            return a3;
        }
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        t a2 = t.a();
        a2.a(context);
        String str2 = "QbSdk.startMiniQBToLoadUrl";
        if (hashMap == null || !"5".equals(hashMap.get(LOGIN_TYPE_KEY_PARTNER_CALL_POS)) || !a2.b() || ((Bundle) a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0])) != null) {
        }
        if (c.a(context, str, hashMap, str2, null) == 0) {
            return true;
        }
        if (a2.b()) {
            if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
                return false;
            }
            if (a2.c().a(context, str, hashMap, null, valueCallback) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean startQBWithBrowserlist(Context context, String str, int i2) {
        boolean startQBToLoadurl = startQBToLoadurl(context, str, i2, null);
        if (!startQBToLoadurl) {
            openBrowserList(context, str, null);
        }
        return startQBToLoadurl;
    }

    public static int openFileReader(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_COUNTS);
        if (!checkContentProviderPrivilage(context)) {
            return -5;
        }
        if (str != null) {
            String substring = str.substring(str.lastIndexOf(".") + 1, str.length());
            if ("apk".equalsIgnoreCase(substring)) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && VERSION.SDK_INT >= 24) {
                    intent.addFlags(1);
                }
                Uri a2 = FileProvider.a(context, str);
                if (a2 == null) {
                    valueCallback.onReceiveValue("uri failed");
                    return -6;
                }
                intent.setDataAndType(a2, "application/vnd.android.package-archive");
                context.startActivity(intent);
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_APKFILE);
                Log.e("QbSdk", "open openFileReader ret = 4");
                return 4;
            }
            if (!c.b(context)) {
                Log.d("QbSdk", "openFileReader QQ browser not installed");
            } else if (!a(context, str, substring)) {
                Log.e("QbSdk", "openFileReader open in QB isQBSupport: false  ret = 3");
                openFileReaderListWithQBDownload(context, str, valueCallback);
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_NOTSUPPORT);
                return 3;
            } else {
                if (startQBForDoc(context, str, 4, 0, substring, a(context, (Map<String, String>) hashMap))) {
                    if (valueCallback != null) {
                        valueCallback.onReceiveValue("open QB");
                    }
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_NOTSUPPORT);
                    Log.e("QbSdk", "open openFileReader open QB ret = 1");
                    return 1;
                }
                Log.d("QbSdk", "openFileReader startQBForDoc return false");
            }
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            hashMap.put("local", "true");
            TbsLog.setWriteLogJIT(true);
            int startMiniQBToLoadUrl = startMiniQBToLoadUrl(context, str, hashMap, valueCallback);
            if (startMiniQBToLoadUrl != 0) {
                openFileReaderListWithQBDownload(context, str, valueCallback);
                TbsLogReport.getInstance(context).setLoadErrorCode(511, "" + startMiniQBToLoadUrl);
                return 3;
            }
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS);
            return 2;
        }
        if (valueCallback != null) {
            valueCallback.onReceiveValue("filepath error");
        }
        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL);
        Log.e("QbSdk", "open openFileReader filepath error ret -1");
        return -1;
    }

    public static boolean checkContentProviderPrivilage(Context context) {
        if (context == null || context.getApplicationInfo().targetSdkVersion < 24 || VERSION.SDK_INT < 24 || TbsConfig.APP_QQ.equals(context.getApplicationInfo().packageName)) {
            return true;
        }
        String str = "";
        try {
            if (!TextUtils.isEmpty(context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "android.support.v4.content.FileProvider"), 0).authority)) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(context.getApplicationInfo().packageName + ".provider", 128);
        if (resolveContentProvider == null) {
            Log.e("QbSdk", "Must declare com.tencent.smtt.utils.FileProvider in AndroidManifest above Android 7.0,please view document in x5.tencent.com");
        }
        if (resolveContentProvider == null) {
            return false;
        }
        return true;
    }

    private static boolean a(Context context, String str, String str2) {
        return isSuportOpenFile(str2, 2);
    }

    private static Bundle a(Context context, Map<String, String> map) {
        String str;
        if (map == null) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            String str2 = "style";
            if (map.get("style") == null) {
                str = "0";
            } else {
                str = (String) map.get("style");
            }
            bundle.putString(str2, str);
            try {
                bundle.putInt("topBarBgColor", Color.parseColor((String) map.get("topBarBgColor")));
            } catch (Exception e2) {
            }
            if (map != null) {
                if (map.containsKey(FILERADER_MENUDATA)) {
                    JSONObject jSONObject = new JSONObject((String) map.get(FILERADER_MENUDATA));
                    JSONArray jSONArray = jSONObject.getJSONArray("menuItems");
                    if (jSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        int i2 = 0;
                        while (true) {
                            int i3 = i2;
                            if (i3 >= jSONArray.length() || i3 >= 5) {
                                bundle.putParcelableArrayList("resArray", arrayList);
                            } else {
                                try {
                                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i3);
                                    arrayList.add(i3, BitmapFactory.decodeResource(context.getResources(), jSONObject2.getInt("iconResId")));
                                    jSONObject2.put("iconResId", i3);
                                } catch (Exception e3) {
                                }
                                i2 = i3 + 1;
                            }
                        }
                        bundle.putParcelableArrayList("resArray", arrayList);
                    }
                    bundle.putString(FILERADER_MENUDATA, jSONObject.toString());
                }
            }
            return bundle;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static String getMiniQBVersion(Context context) {
        t a2 = t.a();
        a2.a(context);
        if (a2 == null || !a2.b()) {
            return null;
        }
        return a2.c().f();
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        if (context == null) {
            return false;
        }
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        if (isMiniQBShortCutExist(context, str, str2)) {
            return false;
        }
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return false;
        }
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        DexLoader b2 = a2.c().b();
        TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
        Object invokeStaticMethod = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
        TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
        if (invokeStaticMethod != null) {
            return true;
        }
        return false;
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        Boolean bool;
        if (context == null) {
            return false;
        }
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return false;
        }
        Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        Boolean valueOf = Boolean.valueOf(false);
        if (invokeStaticMethod instanceof Boolean) {
            bool = (Boolean) invokeStaticMethod;
        } else {
            bool = valueOf;
        }
        return bool.booleanValue();
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        if (context == null || TbsDownloader.getOverSea(context)) {
            return false;
        }
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return false;
        }
        if (a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) != null) {
            return true;
        }
        return false;
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        String str3;
        String str4;
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            if (indexOf > 0) {
                str3 = str.substring(indexOf + 4);
            } else {
                str3 = null;
            }
            HashMap hashMap = new HashMap();
            String str5 = "unknown";
            try {
                str5 = context.getApplicationInfo().packageName;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, str5);
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "14004");
            if ("miniqb://home".equals(str3)) {
                str4 = "qb://navicard/addCard?cardId=168&cardName=168";
            } else {
                str4 = str3;
            }
            if (c.a(context, str4, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                t a2 = t.a();
                if (a2 != null && a2.b() && a2.c().a(context, str3, null, str2, null) == 0) {
                    return true;
                }
                webView.loadUrl(str3);
            }
        } else {
            webView.loadUrl(str);
        }
        return false;
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, ValueCallback<String> valueCallback) {
        openFileReaderListWithQBDownload(context, str, null, valueCallback);
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        if (context != null && !context.getApplicationInfo().packageName.equals("com.tencent.qim") && !context.getApplicationInfo().packageName.equals("com.tencent.tim") && !context.getApplicationInfo().packageName.equals("com.tencent.androidqqmail")) {
            String str2 = "";
            if (bundle != null) {
                str2 = bundle.getString("ChannelId");
            }
            String str3 = "选择其它应用打开";
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            String c2 = d.c(str);
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && VERSION.SDK_INT >= 24) {
                intent.addFlags(1);
            }
            Uri a2 = FileProvider.a(context, str);
            if (a2 == null) {
                valueCallback.onReceiveValue("uri failed");
                return;
            }
            intent.setDataAndType(a2, c2);
            isDefaultDialog = false;
            com.tencent.smtt.sdk.b.a.c cVar = new com.tencent.smtt.sdk.b.a.c(context, str3, intent, valueCallback, c2, str2);
            String a3 = cVar.a();
            if (a3 != null && !TextUtils.isEmpty(a3) && checkApkExist(context, a3)) {
                if (TbsConfig.APP_QB.equals(a3)) {
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_ID, StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName());
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "4");
                }
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra("big_brother_source_key", str2);
                }
                intent.setPackage(a3);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a3);
                }
            } else if ("com.tencent.rtxlite".equalsIgnoreCase(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName()) && isDefaultDialog) {
                new Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
            } else if (!isDefaultDialog) {
                try {
                    cVar.show();
                    cVar.setOnDismissListener(new OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (valueCallback != null) {
                                valueCallback.onReceiveValue("TbsReaderDialogClosed");
                            }
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                    valueCallback.onReceiveValue("TbsReaderDialogClosed");
                }
            } else if (valueCallback != null) {
                valueCallback.onReceiveValue("can not open");
            }
        }
    }

    public static boolean checkApkExist(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (NameNotFoundException e2) {
            return false;
        }
    }

    public static boolean isSuportOpenFile(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {"rar", "zip", "tar", "bz2", "gz", "7z", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub", "chm", "html", "htm", "xml", "mht", "url", "ini", "log", "bat", "php", "js", "lrc", "jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp", "mp3", "m4a", "aac", "amr", "wav", "ogg", "mid", "ra", "wma", "mpga", "ape", "flac", "RTSP", "RTP", "SDP", "RTMP", "mp4", "flv", "avi", "3gp", "3gpp", "webm", MidEntity.TAG_TIMESTAMPS, "ogv", "m3u8", "asf", "wmv", "rmvb", "rm", "f4v", "dat", "mov", "mpg", "mkv", "mpeg", "mpeg1", "mpeg2", "xvid", "dvd", "vcd", "vob", "divx"};
        String[] strArr2 = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub"};
        switch (i2) {
            case 1:
                return Arrays.asList(strArr2).contains(str.toLowerCase());
            case 2:
                return Arrays.asList(strArr).contains(str.toLowerCase());
            default:
                return false;
        }
    }

    public static void openBrowserList(Context context, String str, ValueCallback<String> valueCallback) {
        openBrowserList(context, str, null, valueCallback);
    }

    public static void openBrowserList(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        if (context != null) {
            String str2 = "";
            if (bundle != null) {
                str2 = bundle.getString("ChannelId");
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(str));
            String c2 = d.c(str);
            isDefaultDialog = false;
            com.tencent.smtt.sdk.b.a.c cVar = new com.tencent.smtt.sdk.b.a.c(context, "选择其它应用打开", intent, valueCallback, c2, str2);
            String a2 = cVar.a();
            if (a2 != null && !TextUtils.isEmpty(a2)) {
                if (TbsConfig.APP_QB.equals(a2)) {
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_ID, StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName());
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "4");
                }
                intent.setPackage(a2);
                intent.putExtra("big_brother_source_key", str2);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a2);
                }
            } else if (isDefaultDialog) {
                new Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("can not open");
                }
            } else {
                cVar.show();
                cVar.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (valueCallback != null) {
                            valueCallback.onReceiveValue("TbsReaderDialogClosed");
                        }
                    }
                });
            }
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        if (n == null) {
            n = map;
            return;
        }
        try {
            n.putAll(map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Map<String, Object> getSettings() {
        return n;
    }

    static Object a(Context context, String str, Bundle bundle) {
        if (!a(context)) {
            return Integer.valueOf(EXTENSION_INIT_FAILURE);
        }
        Object a2 = k.a(r, "miscCall", (Class<?>[]) new Class[]{String.class, Bundle.class}, str, bundle);
        if (a2 == null) {
            return null;
        }
        return a2;
    }

    protected static String b() {
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0]);
            if (invokeStaticMethod != null && (invokeStaticMethod instanceof String)) {
                return (String) invokeStaticMethod;
            }
        }
        return null;
    }

    public static void fileInfoDetect(Context context, String str, ValueCallback<String> valueCallback) {
        t a2 = t.a();
        if (a2 != null && a2.b()) {
            try {
                a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "fileInfoDetect", new Class[]{Context.class, String.class, ValueCallback.class}, context, str, valueCallback);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void disAllowThirdAppDownload() {
        c = false;
    }

    public static void initBuglyAsync(boolean z2) {
        i = z2;
    }

    public static void setNeedInitX5FirstTime(boolean z2) {
        w = z2;
    }

    public static boolean isNeedInitX5FirstTime() {
        return w;
    }

    public static int getTmpDirTbsVersion(Context context) {
        if (j.a(context).c() == 2) {
            return l.a().e(context, 0);
        }
        if (j.a(context).b("copy_status") == 1) {
            return l.a().e(context, 1);
        }
        return 0;
    }
}
