package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsDownloadUpload.TbsUploadKey;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.f;
import com.tencent.smtt.utils.g;
import com.tencent.smtt.utils.g.a;
import com.tencent.smtt.utils.n;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    public static final String TBS_METADATA = "com.tencent.mm.BuildInfo.CLIENT_VERSION";
    static boolean a;
    private static String b;
    /* access modifiers changed from: private */
    public static Context c;
    private static Handler d;
    private static String e;
    private static Object f = new byte[0];
    /* access modifiers changed from: private */
    public static i g;
    private static HandlerThread h;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static long l = -1;

    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    public static HandlerThread getsTbsHandlerThread() {
        return h;
    }

    private static boolean c() {
        try {
            for (String sharedTbsCoreVersion : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(c, sharedTbsCoreVersion) > 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void setAppContext(Context context) {
        if (context != null && StubApp.getOrigApplicationContext(context.getApplicationContext()) != null) {
            c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
    }

    public static boolean needSendRequest(Context context, boolean z) {
        boolean z2;
        boolean z3 = true;
        c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        TbsLog.initIfNeed(context);
        if (!a(c, z)) {
            return false;
        }
        int m = l.a().m(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + m);
        if (m > 0) {
            return false;
        }
        if (a(c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        boolean contains = instance.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] hasNeedDownloadKey=" + contains);
        if (!contains) {
            z2 = true;
        } else {
            z2 = instance.mPreferences.getBoolean(TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] needDownload=" + z2);
        if (!z2 || !h()) {
            z3 = false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] ret=" + z3);
        return z3;
    }

    private static boolean a(Context context, boolean z, boolean z2) {
        boolean z3;
        String str;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (!z) {
            String string = instance.mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null);
            int i2 = instance.mPreferences.getInt(TbsConfigKey.KEY_APP_VERSIONCODE, 0);
            String string2 = instance.mPreferences.getString(TbsConfigKey.KEY_APP_METADATA, null);
            String a2 = b.a(c);
            int b2 = b.b(c);
            String a3 = b.a(c, TBS_METADATA);
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] appVersionName=" + a2 + " oldAppVersionName=" + string + " appVersionCode=" + b2 + " oldAppVersionCode=" + i2 + " appMetadata=" + a3 + " oldAppVersionMetadata=" + string2);
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = instance.mPreferences.getLong(TbsConfigKey.KEY_LAST_CHECK, 0);
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] timeLastCheck=" + j2 + " timeNow=" + currentTimeMillis);
            if (z2) {
                boolean contains = instance.mPreferences.contains(TbsConfigKey.KEY_LAST_CHECK);
                TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey=" + contains);
                if (contains && j2 == 0) {
                    j2 = currentTimeMillis;
                }
            }
            long j3 = instance.mPreferences.getLong(TbsConfigKey.KEY_LAST_REQUEST_SUCCESS, 0);
            long j4 = instance.mPreferences.getLong(TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, 0);
            long retryInterval = instance.getRetryInterval();
            TbsLog.i(LOGTAG, "retryInterval = " + retryInterval + " s");
            TbsPVConfig.releaseInstance();
            int emergentCoreVersion = TbsPVConfig.getInstance(c).getEmergentCoreVersion();
            int i3 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            if (currentTimeMillis - j2 > 1000 * retryInterval) {
                z3 = true;
                str = null;
            } else if (emergentCoreVersion > l.a().i(c) && emergentCoreVersion > i3) {
                z3 = true;
                str = null;
            } else if (TbsShareManager.isThirdPartyApp(c) && j3 > 0 && currentTimeMillis - j3 > retryInterval * 1000 && j4 < 11) {
                z3 = true;
                str = null;
            } else if (!TbsShareManager.isThirdPartyApp(c) || TbsShareManager.findCoreForThirdPartyApp(c) != 0 || e()) {
                if (a2 == null || b2 == 0 || a3 == null) {
                    if (TbsShareManager.isThirdPartyApp(c)) {
                        str = "timeNow - timeLastCheck is " + (currentTimeMillis - j2) + " TbsShareManager.findCoreForThirdPartyApp(sAppContext) is " + TbsShareManager.findCoreForThirdPartyApp(c) + " sendRequestWithSameHostCoreVersion() is " + e() + " appVersionName is " + a2 + " appVersionCode is " + b2 + " appMetadata is " + a3 + " oldAppVersionName is " + string + " oldAppVersionCode is " + i2 + " oldAppVersionMetadata is " + string2;
                        z3 = false;
                    }
                } else if (!a2.equals(string) || b2 != i2 || !a3.equals(string2)) {
                    z3 = true;
                    str = null;
                }
                str = null;
                z3 = false;
            } else {
                l.a().d(c);
                z3 = true;
                str = null;
            }
        } else {
            z3 = true;
            str = null;
        }
        if (!z3 && TbsShareManager.isThirdPartyApp(c)) {
            TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(c).tbsLogInfo();
            tbsLogInfo.setErrorCode(-119);
            tbsLogInfo.setFailDetail(str);
            TbsLogReport.getInstance(c).eventReport(EventType.TYPE_DOWNLOAD, tbsLogInfo);
        }
        return z3;
    }

    private static boolean a(Context context, boolean z) {
        Matcher matcher;
        String str = 0;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (VERSION.SDK_INT < 8) {
            instance.setDownloadInterruptCode(-102);
            return false;
        } else if (!QbSdk.c && TbsShareManager.isThirdPartyApp(c) && !c()) {
            return false;
        } else {
            if (!instance.mPreferences.contains(TbsConfigKey.KEY_IS_OVERSEA)) {
                if (z && !TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
                    TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                    z = false;
                }
                instance.a.put(TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                instance.commit();
                j = z;
                TbsLog.i(LOGTAG, "needDownload-first-called--isoversea = " + z);
            }
            if (!getOverSea(context) || VERSION.SDK_INT == 16 || VERSION.SDK_INT == 17 || VERSION.SDK_INT == 18) {
                e = instance.mPreferences.getString(TbsConfigKey.KEY_DEVICE_CPUABI, str);
                if (!TextUtils.isEmpty(e)) {
                    try {
                        matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                    } catch (Exception e2) {
                        matcher = str;
                    }
                    if (matcher != 0 && matcher.find()) {
                        TbsLog.e(LOGTAG, "can not support x86 devices!!");
                        instance.setDownloadInterruptCode(-104);
                        return false;
                    }
                }
                return true;
            }
            TbsLog.i(LOGTAG, "needDownload- return false,  because of  version is " + VERSION.SDK_INT + ", and overea");
            instance.setDownloadInterruptCode(-103);
            return false;
        }
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, true, null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        return needDownload(context, z, z2, true, tbsDownloaderCallback);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, boolean z3, TbsDownloaderCallback tbsDownloaderCallback) {
        TbsLog.i(LOGTAG, "needDownload,process=" + QbSdk.getCurrentProcessName(context) + "stack=" + Log.getStackTraceString(new Throwable()));
        TbsDownloadUpload.clear();
        TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
        instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_1));
        instance.commit();
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] oversea=" + z + ",isDownloadForeground=" + z2);
        boolean z4 = false;
        TbsLog.initIfNeed(context);
        if (l.b) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#1,return " + false);
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_FALSE_1));
            instance.commit();
            return false;
        }
        TbsLog.app_extra(LOGTAG, context);
        c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        TbsDownloadConfig instance2 = TbsDownloadConfig.getInstance(c);
        instance2.setDownloadInterruptCode(-100);
        if (!a(c, z)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#2,return " + false);
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_2));
            instance.commit();
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_FALSE_2));
            instance.commit();
            if (tbsDownloaderCallback == null) {
                return false;
            }
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            return false;
        }
        d();
        if (i) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            instance2.setDownloadInterruptCode(-105);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#3,return " + false);
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_3));
            instance.commit();
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_FALSE_3));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        boolean a2 = a(c, z2, false);
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needSendRequest=" + a2);
        if (a2) {
            a(z2, tbsDownloaderCallback, z3);
            instance2.setDownloadInterruptCode(-114);
        } else {
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_4));
            instance.commit();
        }
        d.removeMessages(102);
        Message.obtain(d, 102).sendToTarget();
        boolean z5 = false;
        if (QbSdk.c || !TbsShareManager.isThirdPartyApp(context)) {
            z5 = instance2.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + z5);
            if (z5 || TbsShareManager.isThirdPartyApp(context)) {
                z4 = instance2.mPreferences.getBoolean(TbsConfigKey.KEY_NEEDDOWNLOAD, false);
            } else {
                z4 = true;
            }
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#4,needDownload=" + z4 + ",hasNeedDownloadKey=" + z5);
        if (!z4) {
            int m = l.a().m(c);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#7,tbsLocalVersion=" + m + ",needSendRequest=" + a2);
            if (a2 || m <= 0) {
                d.removeMessages(103);
                if (m > 0 || a2) {
                    Message.obtain(d, 103, 1, 0, c).sendToTarget();
                } else {
                    Message.obtain(d, 103, 0, 0, c).sendToTarget();
                }
                instance2.setDownloadInterruptCode(-121);
            } else {
                instance2.setDownloadInterruptCode(-119);
            }
        } else if (!h()) {
            z4 = false;
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#5,set needDownload = false");
        } else {
            instance2.setDownloadInterruptCode(-118);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#6");
        }
        if (!a2 && tbsDownloaderCallback != null) {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + z4);
        instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(z4 ? ErrorCode.NEEDDOWNLOAD_TRUE : ErrorCode.NEEDDOWNLOAD_FALSE_4));
        instance.commit();
        return z4;
    }

    static boolean a(Context context) {
        return TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        int i2 = 1;
        synchronized (TbsDownloader.class) {
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
            instance.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_1));
            instance.commit();
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + c);
            if (l.b) {
                instance.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_2));
                instance.commit();
            } else {
                a = true;
                c = StubApp.getOrigApplicationContext(context.getApplicationContext());
                TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-200);
                if (VERSION.SDK_INT < 8) {
                    QbSdk.m.onDownloadFinish(110);
                    TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-201);
                    instance.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_3));
                    instance.commit();
                } else {
                    d();
                    if (i) {
                        QbSdk.m.onDownloadFinish(ErrorCode.THREAD_INIT_ERROR);
                        TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-202);
                        instance.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_4));
                        instance.commit();
                    } else {
                        if (z) {
                            stopDownload();
                        }
                        d.removeMessages(101);
                        d.removeMessages(100);
                        Message obtain = Message.obtain(d, 101, QbSdk.m);
                        if (!z) {
                            i2 = 0;
                        }
                        obtain.arg1 = i2;
                        obtain.sendToTarget();
                    }
                }
            }
        }
    }

    public static int getCoreShareDecoupleCoreVersionByContext(Context context) {
        return l.a().h(context);
    }

    public static int getCoreShareDecoupleCoreVersion() {
        return l.a().h(c);
    }

    public static boolean needDownloadDecoupleCore() {
        if (TbsShareManager.isThirdPartyApp(c) || a(c)) {
            return false;
        }
        long j2 = TbsDownloadConfig.getInstance(c).mPreferences.getLong(TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0);
        if (System.currentTimeMillis() - j2 < 1000 * TbsDownloadConfig.getInstance(c).getRetryInterval()) {
            return false;
        }
        int i2 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
        if (i2 <= 0 || i2 == l.a().h(c) || TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) == i2) {
            return false;
        }
        return true;
    }

    public static boolean startDecoupleCoreIfNeeded() {
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded ");
        if (TbsShareManager.isThirdPartyApp(c)) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #1");
        if (a(c) || d == null) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #2");
        long j2 = TbsDownloadConfig.getInstance(c).mPreferences.getLong(TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0);
        if (System.currentTimeMillis() - j2 < 1000 * TbsDownloadConfig.getInstance(c).getRetryInterval()) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #3");
        int i2 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
        if (i2 <= 0 || i2 == l.a().h(c)) {
            TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded no need, deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + l.a().h(c));
            return false;
        } else if (TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i2 || TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
            TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #4");
            a = true;
            d.removeMessages(108);
            Message obtain = Message.obtain(d, 108, QbSdk.m);
            obtain.arg1 = 0;
            obtain.sendToTarget();
            TbsDownloadConfig.getInstance(c).a.put(TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, Long.valueOf(System.currentTimeMillis()));
            return true;
        } else {
            TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded no need, KEY_TBS_DOWNLOAD_V is " + TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) + " deCoupleCoreVersion is " + i2 + " KEY_TBS_DOWNLOAD_V_TYPE is " + TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0));
            return false;
        }
    }

    public static void stopDownload() {
        if (!i) {
            TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
            if (g != null) {
                g.b();
            }
            if (d != null) {
                d.removeMessages(100);
                d.removeMessages(101);
                d.removeMessages(108);
            }
        }
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading] is " + a);
            z = a;
        }
        return z;
    }

    public static boolean isDownloadForeground() {
        return g != null && g.d();
    }

    private static synchronized void d() {
        synchronized (TbsDownloader.class) {
            if (h == null) {
                h = k.a();
                try {
                    g = new i(c);
                    d = new Handler(h.getLooper()) {
                        public void handleMessage(Message message) {
                            int m;
                            FileOutputStream fileOutputStream;
                            boolean z;
                            boolean z2;
                            FileLock fileLock = null;
                            boolean z3 = true;
                            switch (message.what) {
                                case 100:
                                    boolean z4 = message.arg1 == 1;
                                    if (message.arg2 == 1) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    boolean a = TbsDownloader.b(true, false, false, z2);
                                    if (message.obj != null && (message.obj instanceof TbsDownloaderCallback)) {
                                        TbsLog.i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish needStartDownload=" + a);
                                        String str = "";
                                        if (!(TbsDownloader.c == null || StubApp.getOrigApplicationContext(TbsDownloader.c.getApplicationContext()) == null || StubApp.getOrigApplicationContext(TbsDownloader.c.getApplicationContext()).getApplicationInfo() == null)) {
                                            str = StubApp.getOrigApplicationContext(TbsDownloader.c.getApplicationContext()).getApplicationInfo().packageName;
                                        }
                                        if (!a || z4) {
                                            ((TbsDownloaderCallback) message.obj).onNeedDownloadFinish(a, TbsDownloadConfig.getInstance(TbsDownloader.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                        } else if (TbsConfig.APP_WX.equals(str) || TbsConfig.APP_QQ.equals(str)) {
                                            TbsLog.i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish in mm or QQ callback needStartDownload = " + a);
                                            ((TbsDownloaderCallback) message.obj).onNeedDownloadFinish(a, TbsDownloadConfig.getInstance(TbsDownloader.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                        }
                                    }
                                    if (TbsShareManager.isThirdPartyApp(TbsDownloader.c) && a) {
                                        TbsDownloader.startDownload(TbsDownloader.c);
                                        return;
                                    }
                                    return;
                                case 101:
                                case 108:
                                    if (!TbsShareManager.isThirdPartyApp(TbsDownloader.c)) {
                                        fileOutputStream = f.b(TbsDownloader.c, false, "tbs_download_lock_file" + TbsDownloadConfig.getInstance(TbsDownloader.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) + ".txt");
                                        if (fileOutputStream != null) {
                                            fileLock = f.a(TbsDownloader.c, fileOutputStream);
                                            if (fileLock == null) {
                                                QbSdk.m.onDownloadFinish(ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING);
                                                TbsLog.i(TbsDownloader.LOGTAG, "file lock locked,wx or qq is downloading");
                                                TbsDownloadConfig.getInstance(TbsDownloader.c).setDownloadInterruptCode(-203);
                                                TbsLog.i(TbsDownloader.LOGTAG, "MSG_START_DOWNLOAD_DECOUPLECORE return #1");
                                                return;
                                            }
                                        } else if (f.a(TbsDownloader.c)) {
                                            TbsDownloadConfig.getInstance(TbsDownloader.c).setDownloadInterruptCode(-204);
                                            TbsLog.i(TbsDownloader.LOGTAG, "MSG_START_DOWNLOAD_DECOUPLECORE return #2");
                                            return;
                                        }
                                    } else {
                                        fileOutputStream = null;
                                    }
                                    boolean z5 = message.arg1 == 1;
                                    TbsDownloadConfig instance = TbsDownloadConfig.getInstance(TbsDownloader.c);
                                    if (108 == message.what) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    if (!TbsDownloader.b(false, z5, z, true)) {
                                        QbSdk.m.onDownloadFinish(110);
                                    } else if (z5 && l.a().b(TbsDownloader.c, TbsDownloadConfig.getInstance(TbsDownloader.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0))) {
                                        QbSdk.m.onDownloadFinish(ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR);
                                        instance.setDownloadInterruptCode(-213);
                                    } else if (instance.mPreferences.getBoolean(TbsConfigKey.KEY_NEEDDOWNLOAD, false)) {
                                        TbsDownloadConfig.getInstance(TbsDownloader.c).setDownloadInterruptCode(-215);
                                        i b = TbsDownloader.g;
                                        if (108 != message.what) {
                                            z3 = false;
                                        }
                                        b.b(z5, z3);
                                    } else {
                                        QbSdk.m.onDownloadFinish(110);
                                    }
                                    TbsLog.i(TbsDownloader.LOGTAG, "------freeFileLock called :");
                                    f.a(fileLock, fileOutputStream);
                                    return;
                                case 102:
                                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT");
                                    if (TbsShareManager.isThirdPartyApp(TbsDownloader.c)) {
                                        m = TbsShareManager.a(TbsDownloader.c, false);
                                    } else {
                                        m = l.a().m(TbsDownloader.c);
                                    }
                                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] localTbsVersion=" + m);
                                    TbsDownloader.g.a(m);
                                    TbsLogReport.getInstance(TbsDownloader.c).dailyReport();
                                    return;
                                case 103:
                                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE");
                                    if (message.arg1 == 0) {
                                        l.a().a((Context) message.obj, true);
                                        return;
                                    } else {
                                        l.a().a((Context) message.obj, false);
                                        return;
                                    }
                                case 104:
                                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_UPLOAD_TBSLOG");
                                    TbsLogReport.getInstance(TbsDownloader.c).reportTbsLog();
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                } catch (Exception e2) {
                    i = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
        return;
    }

    private static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback, boolean z2) {
        int i2;
        int i3 = 1;
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        if (z) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        obtain.arg1 = i2;
        if (!z2) {
            i3 = 0;
        }
        obtain.arg2 = i3;
        obtain.sendToTarget();
    }

    private static boolean e() {
        try {
            return TbsDownloadConfig.getInstance(c).mPreferences.getString(TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(g().toString());
        } catch (Exception e2) {
            return false;
        }
    }

    private static String[] f() {
        if (QbSdk.getOnlyDownload()) {
            return new String[]{StubApp.getOrigApplicationContext(c.getApplicationContext()).getPackageName()};
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        String packageName = StubApp.getOrigApplicationContext(c.getApplicationContext()).getPackageName();
        if (!packageName.equals(TbsShareManager.f(c))) {
            return coreProviderAppList;
        }
        int length = coreProviderAppList.length;
        String[] strArr = new String[(length + 1)];
        System.arraycopy(coreProviderAppList, 0, strArr, 0, length);
        strArr[length] = packageName;
        return strArr;
    }

    private static void a(JSONArray jSONArray) {
        String[] f2;
        String[] f3;
        boolean z;
        boolean z2;
        for (String str : f()) {
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(c, str);
            if (sharedTbsCoreVersion > 0) {
                Context packageContext = TbsShareManager.getPackageContext(c, str, true);
                if (packageContext == null || l.a().f(packageContext)) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i2) == sharedTbsCoreVersion) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                }
            }
        }
        for (String str2 : f()) {
            int coreShareDecoupleCoreVersion = TbsShareManager.getCoreShareDecoupleCoreVersion(c, str2);
            if (coreShareDecoupleCoreVersion > 0) {
                Context packageContext2 = TbsShareManager.getPackageContext(c, str2, true);
                if (packageContext2 == null || l.a().f(packageContext2)) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i3) == coreShareDecoupleCoreVersion) {
                            z = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(coreShareDecoupleCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str2);
                }
            }
        }
    }

    private static void b(JSONArray jSONArray) {
        boolean z = false;
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            int a2 = l.a().a(TbsShareManager.getHostCorePathAppDefined());
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    break;
                } else if (jSONArray.optInt(i2) == a2) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z) {
                jSONArray.put(a2);
            }
        }
    }

    private static JSONArray g() {
        if (!TbsShareManager.isThirdPartyApp(c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        a(jSONArray);
        c(jSONArray);
        b(jSONArray);
        return jSONArray;
    }

    private static void c(JSONArray jSONArray) {
        String[] f2;
        boolean z;
        boolean z2;
        if (!TbsPVConfig.getInstance(c).isDisableHostBackupCore()) {
            for (String str : f()) {
                int backupCoreVersion = TbsShareManager.getBackupCoreVersion(c, str);
                if (backupCoreVersion > 0) {
                    Context packageContext = TbsShareManager.getPackageContext(c, str, false);
                    if (packageContext == null || l.a().f(packageContext)) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= jSONArray.length()) {
                                z2 = false;
                                break;
                            } else if (jSONArray.optInt(i2) == backupCoreVersion) {
                                z2 = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z2) {
                            jSONArray.put(backupCoreVersion);
                        }
                    } else {
                        TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                    }
                }
                int backupDecoupleCoreVersion = TbsShareManager.getBackupDecoupleCoreVersion(c, str);
                if (backupDecoupleCoreVersion > 0) {
                    Context packageContext2 = TbsShareManager.getPackageContext(c, str, false);
                    if (packageContext2 == null || l.a().f(packageContext2)) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= jSONArray.length()) {
                                z = false;
                                break;
                            } else if (jSONArray.optInt(i3) == backupDecoupleCoreVersion) {
                                z = true;
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (!z) {
                            jSONArray.put(backupDecoupleCoreVersion);
                        }
                    } else {
                        TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x02f8  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x007b A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0099 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a7 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b5 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0124 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x012e A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x013b A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0175 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01bf A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01cd A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01d5 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x021b A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02b7 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02c3 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02e1 A[Catch:{ Exception -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02e4 A[Catch:{ Exception -> 0x0207 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject a(boolean r13, boolean r14, boolean r15) {
        /*
            r4 = 1
            r3 = 0
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[TbsDownloader.postJsonData]isQuery: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r13)
            java.lang.String r2 = " forDecoupleCore is "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r15)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.Context r0 = c
            java.lang.String r7 = b(r0)
            android.content.Context r0 = c
            java.lang.String r8 = com.tencent.smtt.utils.b.d(r0)
            android.content.Context r0 = c
            java.lang.String r9 = com.tencent.smtt.utils.b.c(r0)
            android.content.Context r0 = c
            java.lang.String r10 = com.tencent.smtt.utils.b.f(r0)
            java.lang.String r0 = ""
            java.lang.String r2 = ""
            java.lang.String r1 = ""
            java.util.TimeZone r1 = java.util.TimeZone.getDefault()
            java.lang.String r1 = r1.getID()
            if (r1 == 0) goto L_0x02fb
            r5 = r1
        L_0x0053:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x01f8 }
            java.lang.String r11 = "phone"
            java.lang.Object r0 = r0.getSystemService(r11)     // Catch:{ Exception -> 0x01f8 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x01f8 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x01f8 }
            if (r0 == 0) goto L_0x01fc
            java.lang.String r0 = r0.getSimCountryIso()     // Catch:{ Exception -> 0x01f8 }
        L_0x0065:
            if (r0 == 0) goto L_0x02f8
        L_0x0067:
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.sdk.j r1 = com.tencent.smtt.sdk.j.a(r1)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "tpatch_num"
            int r1 = r1.c(r11)     // Catch:{ Exception -> 0x0207 }
            r11 = 5
            if (r1 >= r11) goto L_0x01ff
            java.lang.String r1 = "REQUEST_TPATCH"
            r11 = 1
            r2.put(r1, r11)     // Catch:{ Exception -> 0x0207 }
        L_0x0081:
            java.lang.String r1 = "TIMEZONEID"
            r2.put(r1, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r1 = "COUNTRYISO"
            r2.put(r1, r0)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "PROTOCOLVERSION"
            r1 = 1
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x021b
            boolean r0 = com.tencent.smtt.sdk.QbSdk.c     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x0209
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            r1 = 0
            int r0 = com.tencent.smtt.sdk.TbsShareManager.a(r0, r1)     // Catch:{ Exception -> 0x0207 }
            r1 = r0
        L_0x00a5:
            if (r13 == 0) goto L_0x02b7
            java.lang.String r0 = "FUNCTION"
            r5 = 2
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
        L_0x00ad:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x02c3
            org.json.JSONArray r0 = g()     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = "TBSVLARR"
            r2.put(r5, r0)     // Catch:{ Exception -> 0x0207 }
            java.util.Map<java.lang.String, java.lang.Object> r5 = r6.a     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "last_thirdapp_sendrequest_coreversion"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0207 }
            r5.put(r11, r0)     // Catch:{ Exception -> 0x0207 }
            r6.commit()     // Catch:{ Exception -> 0x0207 }
            boolean r0 = com.tencent.smtt.sdk.QbSdk.c     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x00d6
            java.lang.String r0 = "THIRDREQ"
            r5 = 1
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
        L_0x00d6:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = "APPN"
            r2.put(r5, r0)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "APPVN"
            android.content.SharedPreferences r5 = r6.mPreferences     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "app_versionname"
            r12 = 0
            java.lang.String r5 = r5.getString(r11, r12)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = a(r5)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "APPVC"
            android.content.SharedPreferences r5 = r6.mPreferences     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "app_versioncode"
            r12 = 0
            int r5 = r5.getInt(r11, r12)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "APPMETA"
            android.content.SharedPreferences r5 = r6.mPreferences     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "app_metadata"
            r12 = 0
            java.lang.String r5 = r5.getString(r11, r12)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = a(r5)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "TBSSDKV"
            r5 = 43697(0xaab1, float:6.1233E-41)
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "TBSV"
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = "DOWNLOADDECOUPLECORE"
            if (r15 == 0) goto L_0x02e1
            r0 = r4
        L_0x0125:
            r2.put(r5, r0)     // Catch:{ Exception -> 0x0207 }
            java.util.Map<java.lang.String, java.lang.Object> r5 = r6.a     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "tbs_downloaddecouplecore"
            if (r15 == 0) goto L_0x02e4
            r0 = r4
        L_0x012f:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0207 }
            r5.put(r11, r0)     // Catch:{ Exception -> 0x0207 }
            r6.commit()     // Catch:{ Exception -> 0x0207 }
            if (r1 == 0) goto L_0x0146
            java.lang.String r0 = "TBSBACKUPV"
            com.tencent.smtt.sdk.i r5 = g     // Catch:{ Exception -> 0x0207 }
            int r5 = r5.b(r15)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
        L_0x0146:
            java.lang.String r0 = "CPU"
            java.lang.String r5 = e     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "UA"
            r2.put(r0, r7)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "IMSI"
            java.lang.String r5 = a(r8)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "IMEI"
            java.lang.String r5 = a(r9)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r0 = "ANDROID_ID"
            java.lang.String r5 = a(r10)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r5)     // Catch:{ Exception -> 0x0207 }
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)     // Catch:{ Exception -> 0x0207 }
            if (r0 != 0) goto L_0x0194
            if (r1 == 0) goto L_0x02ea
            java.lang.String r5 = "STATUS"
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            boolean r0 = com.tencent.smtt.sdk.QbSdk.a(r0, r1)     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x02e7
            r0 = r3
        L_0x0182:
            r2.put(r5, r0)     // Catch:{ Exception -> 0x0207 }
        L_0x0185:
            java.lang.String r0 = "TBSDV"
            com.tencent.smtt.sdk.l r1 = com.tencent.smtt.sdk.l.a()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r5 = c     // Catch:{ Exception -> 0x0207 }
            int r1 = r1.h(r5)     // Catch:{ Exception -> 0x0207 }
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
        L_0x0194:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Exception -> 0x0207 }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x0207 }
            java.lang.String r1 = "request_full_package"
            r5 = 0
            boolean r1 = r0.getBoolean(r1, r5)     // Catch:{ Exception -> 0x0207 }
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = "can_unlzma"
            r6 = 0
            java.lang.Object r0 = com.tencent.smtt.sdk.QbSdk.a(r0, r5, r6)     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x02f2
            boolean r5 = r0 instanceof java.lang.Boolean     // Catch:{ Exception -> 0x0207 }
            if (r5 == 0) goto L_0x02f2
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Exception -> 0x0207 }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x0207 }
        L_0x01b8:
            if (r0 == 0) goto L_0x01bd
            if (r1 != 0) goto L_0x01bd
            r3 = r4
        L_0x01bd:
            if (r3 == 0) goto L_0x01c5
            java.lang.String r0 = "REQUEST_LZMA"
            r1 = 1
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
        L_0x01c5:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            boolean r0 = getOverSea(r0)     // Catch:{ Exception -> 0x0207 }
            if (r0 == 0) goto L_0x01d3
            java.lang.String r0 = "OVERSEA"
            r1 = 1
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
        L_0x01d3:
            if (r14 == 0) goto L_0x01db
            java.lang.String r0 = "DOWNLOAD_FOREGROUND"
            r1 = 1
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
        L_0x01db:
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "[TbsDownloader.postJsonData] jsonData="
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r3 = r2.toString()
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            return r2
        L_0x01f8:
            r0 = move-exception
            r0.printStackTrace()
        L_0x01fc:
            r0 = r1
            goto L_0x0065
        L_0x01ff:
            java.lang.String r1 = "REQUEST_TPATCH"
            r11 = 0
            r2.put(r1, r11)     // Catch:{ Exception -> 0x0207 }
            goto L_0x0081
        L_0x0207:
            r0 = move-exception
            goto L_0x01db
        L_0x0209:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Exception -> 0x0207 }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x0207 }
            java.lang.String r1 = "tbs_download_version"
            r5 = 0
            int r0 = r0.getInt(r1, r5)     // Catch:{ Exception -> 0x0207 }
            r1 = r0
            goto L_0x00a5
        L_0x021b:
            if (r15 == 0) goto L_0x02a9
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0207 }
            int r0 = r0.i(r1)     // Catch:{ Exception -> 0x0207 }
        L_0x0227:
            if (r0 != 0) goto L_0x0276
            com.tencent.smtt.sdk.l r1 = com.tencent.smtt.sdk.l.a()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r5 = c     // Catch:{ Exception -> 0x0207 }
            boolean r1 = r1.l(r5)     // Catch:{ Exception -> 0x0207 }
            if (r1 == 0) goto L_0x0276
            r0 = -1
            java.lang.String r1 = "com.tencent.mobileqq"
            android.content.Context r5 = c     // Catch:{ Exception -> 0x0207 }
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo()     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = r5.packageName     // Catch:{ Exception -> 0x0207 }
            boolean r1 = r1.equals(r5)     // Catch:{ Exception -> 0x0207 }
            if (r1 == 0) goto L_0x0276
            com.tencent.smtt.sdk.TbsDownloadUpload.clear()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.sdk.TbsDownloadUpload r1 = com.tencent.smtt.sdk.TbsDownloadUpload.getInstance(r1)     // Catch:{ Exception -> 0x0207 }
            java.util.Map<java.lang.String, java.lang.Object> r5 = r1.a     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "tbs_local_core_version"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0207 }
            r5.put(r11, r12)     // Catch:{ Exception -> 0x0207 }
            r1.commit()     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.sdk.TbsPVConfig.releaseInstance()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.sdk.TbsPVConfig r1 = com.tencent.smtt.sdk.TbsPVConfig.getInstance(r1)     // Catch:{ Exception -> 0x0207 }
            int r1 = r1.getLocalCoreVersionMoreTimes()     // Catch:{ Exception -> 0x0207 }
            if (r1 != r4) goto L_0x0276
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0207 }
            int r0 = r0.i(r1)     // Catch:{ Exception -> 0x0207 }
        L_0x0276:
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0207 }
            r5.<init>()     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = "[TbsDownloader.postJsonData] tbsLocalVersion="
            java.lang.StringBuilder r5 = r5.append(r11)     // Catch:{ Exception -> 0x0207 }
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r11 = " isDownloadForeground="
            java.lang.StringBuilder r5 = r5.append(r11)     // Catch:{ Exception -> 0x0207 }
            java.lang.StringBuilder r5 = r5.append(r14)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0207 }
            com.tencent.smtt.utils.TbsLog.i(r1, r5)     // Catch:{ Exception -> 0x0207 }
            if (r14 == 0) goto L_0x02f5
            com.tencent.smtt.sdk.l r1 = com.tencent.smtt.sdk.l.a()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r5 = c     // Catch:{ Exception -> 0x0207 }
            boolean r1 = r1.l(r5)     // Catch:{ Exception -> 0x0207 }
            if (r1 == 0) goto L_0x02b5
        L_0x02a6:
            r1 = r0
            goto L_0x00a5
        L_0x02a9:
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Exception -> 0x0207 }
            android.content.Context r1 = c     // Catch:{ Exception -> 0x0207 }
            int r0 = r0.m(r1)     // Catch:{ Exception -> 0x0207 }
            goto L_0x0227
        L_0x02b5:
            r0 = r3
            goto L_0x02a6
        L_0x02b7:
            java.lang.String r5 = "FUNCTION"
            if (r1 != 0) goto L_0x02c1
            r0 = r3
        L_0x02bc:
            r2.put(r5, r0)     // Catch:{ Exception -> 0x0207 }
            goto L_0x00ad
        L_0x02c1:
            r0 = r4
            goto L_0x02bc
        L_0x02c3:
            org.json.JSONArray r0 = a(r15)     // Catch:{ Exception -> 0x0207 }
            android.content.Context r5 = c     // Catch:{ Exception -> 0x0207 }
            int r5 = com.tencent.smtt.utils.Apn.getApnType(r5)     // Catch:{ Exception -> 0x0207 }
            r11 = 3
            if (r5 == r11) goto L_0x00d6
            int r5 = r0.length()     // Catch:{ Exception -> 0x0207 }
            if (r5 == 0) goto L_0x00d6
            if (r1 != 0) goto L_0x00d6
            if (r13 == 0) goto L_0x00d6
            java.lang.String r5 = "TBSBACKUPARR"
            r2.put(r5, r0)     // Catch:{ Exception -> 0x0207 }
            goto L_0x00d6
        L_0x02e1:
            r0 = r3
            goto L_0x0125
        L_0x02e4:
            r0 = r3
            goto L_0x012f
        L_0x02e7:
            r0 = r4
            goto L_0x0182
        L_0x02ea:
            java.lang.String r0 = "STATUS"
            r1 = 0
            r2.put(r0, r1)     // Catch:{ Exception -> 0x0207 }
            goto L_0x0185
        L_0x02f2:
            r0 = r3
            goto L_0x01b8
        L_0x02f5:
            r1 = r0
            goto L_0x00a5
        L_0x02f8:
            r0 = r2
            goto L_0x0067
        L_0x02fb:
            r5 = r0
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(boolean, boolean, boolean):org.json.JSONObject");
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!k) {
                k = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsConfigKey.KEY_IS_OVERSEA)) {
                    j = instance.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + j);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + j);
            }
            z = j;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public static boolean b(boolean z, boolean z2, boolean z3, boolean z4) {
        int i2;
        long j2;
        if (z) {
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(c);
            instance.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_5));
            instance.commit();
        } else if (!z3) {
            TbsDownloadUpload instance2 = TbsDownloadUpload.getInstance(c);
            instance2.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_5));
            instance2.commit();
        }
        if (QbSdk.n == null || !QbSdk.n.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.n.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest]isQuery: " + z + " forDecoupleCore is " + z3);
            if (l.a().c(c)) {
                TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!");
                if (z) {
                    TbsDownloadUpload instance3 = TbsDownloadUpload.getInstance(c);
                    instance3.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_7));
                    instance3.commit();
                } else if (!z3) {
                    TbsDownloadUpload instance4 = TbsDownloadUpload.getInstance(c);
                    instance4.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_7));
                    instance4.commit();
                }
                return false;
            }
            final TbsDownloadConfig instance5 = TbsDownloadConfig.getInstance(c);
            File file = new File(f.a(c, 1), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            File file2 = new File(f.a(c, 2), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            File file3 = new File(f.a(c, 3), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            File file4 = new File(f.a(c, 4), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (!file4.exists()) {
                if (file3.exists()) {
                    file3.renameTo(file4);
                } else if (file2.exists()) {
                    file2.renameTo(file4);
                } else if (file.exists()) {
                    file.renameTo(file4);
                }
            }
            if (e == null) {
                e = b.a();
                instance5.a.put(TbsConfigKey.KEY_DEVICE_CPUABI, e);
                instance5.commit();
            }
            boolean z5 = false;
            if (!TextUtils.isEmpty(e)) {
                Matcher matcher = null;
                try {
                    matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                } catch (Exception e2) {
                }
                if (matcher != null && matcher.find()) {
                    if (TbsShareManager.isThirdPartyApp(c)) {
                        TbsLog.e(LOGTAG, "don't support x86 devices,skip send request");
                        TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(c).tbsLogInfo();
                        if (z) {
                            instance5.setDownloadInterruptCode(-104);
                            tbsLogInfo.setErrorCode(-104);
                        } else {
                            instance5.setDownloadInterruptCode(-205);
                            tbsLogInfo.setErrorCode(-205);
                        }
                        tbsLogInfo.setFailDetail("mycpu is " + e);
                        TbsLogReport.getInstance(c).eventReport(EventType.TYPE_DOWNLOAD, tbsLogInfo);
                    } else if (z) {
                        instance5.setDownloadInterruptCode(-104);
                    } else {
                        instance5.setDownloadInterruptCode(-205);
                    }
                    z5 = true;
                }
            }
            instance5.a.put(TbsConfigKey.KEY_APP_VERSIONNAME, b.a(c));
            instance5.a.put(TbsConfigKey.KEY_APP_VERSIONCODE, Integer.valueOf(b.b(c)));
            instance5.commit();
            JSONObject a2 = a(z, z2, z3);
            int i3 = -1;
            try {
                i2 = a2.getInt("TBSV");
            } catch (Exception e3) {
                i2 = i3;
            }
            if (z5 || i2 != -1) {
                long currentTimeMillis = System.currentTimeMillis();
                if (TbsShareManager.isThirdPartyApp(c)) {
                    long j3 = instance5.mPreferences.getLong(TbsConfigKey.KEY_REQUEST_FAIL, 0);
                    long j4 = instance5.mPreferences.getLong(TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, 0);
                    if (currentTimeMillis - j3 < instance5.getRetryInterval() * 1000) {
                        j2 = 1 + j4;
                    } else {
                        j2 = 1;
                    }
                    instance5.a.put(TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, Long.valueOf(j2));
                }
                instance5.a.put(TbsConfigKey.KEY_REQUEST_FAIL, Long.valueOf(currentTimeMillis));
                instance5.a.put(TbsConfigKey.KEY_APP_VERSIONNAME, b.a(c));
                instance5.a.put(TbsConfigKey.KEY_APP_VERSIONCODE, Integer.valueOf(b.b(c)));
                instance5.a.put(TbsConfigKey.KEY_APP_METADATA, b.a(c, TBS_METADATA));
                instance5.commit();
                if (z5) {
                    if (z) {
                        TbsDownloadUpload instance6 = TbsDownloadUpload.getInstance(c);
                        instance6.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_8));
                        instance6.commit();
                    } else if (!z3) {
                        TbsDownloadUpload instance7 = TbsDownloadUpload.getInstance(c);
                        instance7.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_8));
                        instance7.commit();
                    }
                    return false;
                }
            }
            if (i2 != -1 || z3) {
                try {
                    String d2 = n.a(c).d();
                    TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] postUrl=" + d2);
                    if (z) {
                        TbsDownloadUpload instance8 = TbsDownloadUpload.getInstance(c);
                        instance8.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_9));
                        instance8.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, Integer.valueOf(1));
                        instance8.commit();
                        TbsLog.i(LOGTAG, "sendRequest query 148");
                    } else if (!z3) {
                        TbsDownloadUpload instance9 = TbsDownloadUpload.getInstance(c);
                        instance9.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_9));
                        instance9.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, Integer.valueOf(1));
                        instance9.commit();
                        TbsLog.i(LOGTAG, "sendRequest download 168");
                    }
                    final boolean z6 = z;
                    return a(g.a(d2, a2.toString().getBytes("utf-8"), new a() {
                        public void a(int i) {
                            instance5.a.put(TbsConfigKey.KEY_LAST_CHECK, Long.valueOf(System.currentTimeMillis()));
                            instance5.commit();
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.sendRequest] httpResponseCode=" + i);
                            if (TbsShareManager.isThirdPartyApp(TbsDownloader.c) && i == 200) {
                                instance5.a.put(TbsConfigKey.KEY_LAST_REQUEST_SUCCESS, Long.valueOf(System.currentTimeMillis()));
                                instance5.a.put(TbsConfigKey.KEY_REQUEST_FAIL, Long.valueOf(0));
                                instance5.a.put(TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, Long.valueOf(0));
                                instance5.commit();
                            }
                            if (i < 300) {
                                return;
                            }
                            if (z6) {
                                instance5.setDownloadInterruptCode(-107);
                            } else {
                                instance5.setDownloadInterruptCode(-207);
                            }
                        }
                    }, false), i2, z, z2, z4);
                } catch (Throwable th) {
                    th.printStackTrace();
                    if (z) {
                        instance5.setDownloadInterruptCode(-106);
                    } else {
                        instance5.setDownloadInterruptCode(-206);
                    }
                    return false;
                }
            } else if (z) {
                TbsDownloadUpload instance10 = TbsDownloadUpload.getInstance(c);
                instance10.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_10));
                instance10.commit();
                return false;
            } else {
                if (!z3) {
                    TbsDownloadUpload instance11 = TbsDownloadUpload.getInstance(c);
                    instance11.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_10));
                    instance11.commit();
                }
                return false;
            }
        } else {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- SET_SENDREQUEST_AND_UPLOAD is false");
            if (z) {
                TbsDownloadUpload instance12 = TbsDownloadUpload.getInstance(c);
                instance12.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(ErrorCode.NEEDDOWNLOAD_6));
                instance12.commit();
            } else if (!z3) {
                TbsDownloadUpload instance13 = TbsDownloadUpload.getInstance(c);
                instance13.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(ErrorCode.STARTDOWNLOAD_6));
                instance13.commit();
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:141:0x034c, code lost:
        if (r2 > 0) goto L_0x034e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x030f  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x026b  */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r32, int r33, boolean r34, boolean r35, boolean r36) throws java.lang.Exception {
        /*
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[TbsDownloader.readResponse] response="
            java.lang.StringBuilder r3 = r3.append(r4)
            r0 = r32
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r4 = "isNeedInstall="
            java.lang.StringBuilder r3 = r3.append(r4)
            r0 = r36
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r32)
            if (r2 == 0) goto L_0x0048
            if (r34 == 0) goto L_0x0042
            r2 = -108(0xffffffffffffff94, float:NaN)
            r14.setDownloadInterruptCode(r2)
        L_0x0039:
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] return #1,response is empty..."
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r2 = 0
        L_0x0041:
            return r2
        L_0x0042:
            r2 = -208(0xffffffffffffff30, float:NaN)
            r14.setDownloadInterruptCode(r2)
            goto L_0x0039
        L_0x0048:
            org.json.JSONObject r15 = new org.json.JSONObject
            r0 = r32
            r15.<init>(r0)
            java.lang.String r2 = "RET"
            int r2 = r15.getInt(r2)
            if (r2 == 0) goto L_0x007e
            if (r34 == 0) goto L_0x0078
            r3 = -109(0xffffffffffffff93, float:NaN)
            r14.setDownloadInterruptCode(r3)
        L_0x005e:
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "[TbsDownloader.readResponse] return #2,returnCode="
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r2)
            r2 = 0
            goto L_0x0041
        L_0x0078:
            r3 = -209(0xffffffffffffff2f, float:NaN)
            r14.setDownloadInterruptCode(r3)
            goto L_0x005e
        L_0x007e:
            java.lang.String r2 = "RESPONSECODE"
            int r16 = r15.getInt(r2)
            java.lang.String r2 = "DOWNLOADURL"
            java.lang.String r17 = r15.getString(r2)
            java.lang.String r2 = "URLLIST"
            java.lang.String r3 = ""
            java.lang.String r18 = r15.optString(r2, r3)
            java.lang.String r2 = "TBSAPKSERVERVERSION"
            int r19 = r15.getInt(r2)
            java.lang.String r2 = "DOWNLOADMAXFLOW"
            int r20 = r15.getInt(r2)
            java.lang.String r2 = "DOWNLOAD_MIN_FREE_SPACE"
            int r21 = r15.getInt(r2)
            java.lang.String r2 = "DOWNLOAD_SUCCESS_MAX_RETRYTIMES"
            int r22 = r15.getInt(r2)
            java.lang.String r2 = "DOWNLOAD_FAILED_MAX_RETRYTIMES"
            int r23 = r15.getInt(r2)
            java.lang.String r2 = "DOWNLOAD_SINGLE_TIMEOUT"
            long r24 = r15.getLong(r2)
            java.lang.String r2 = "TBSAPKFILESIZE"
            long r26 = r15.getLong(r2)
            java.lang.String r2 = "RETRY_INTERVAL"
            r4 = 0
            long r12 = r15.optLong(r2, r4)
            java.lang.String r2 = "FLOWCTR"
            r3 = -1
            int r28 = r15.optInt(r2, r3)
            r2 = 0
            java.lang.String r3 = "USEBBACKUPVER"
            int r2 = r15.getInt(r3)     // Catch:{ Exception -> 0x0872 }
        L_0x00d2:
            java.util.Map<java.lang.String, java.lang.Object> r3 = r14.a
            java.lang.String r4 = "use_backup_version"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.put(r4, r2)
            if (r34 == 0) goto L_0x0101
            boolean r2 = com.tencent.smtt.sdk.QbSdk.i
            if (r2 == 0) goto L_0x0101
            android.content.Context r2 = c
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r2)
            if (r2 == 0) goto L_0x0101
            java.lang.String r2 = "BUGLY"
            r3 = 0
            int r2 = r15.optInt(r2, r3)     // Catch:{ Throwable -> 0x029b }
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r3 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x029b }
            android.content.Context r4 = c     // Catch:{ Throwable -> 0x029b }
            java.lang.String r5 = "bugly_switch.txt"
            r6 = 1
            if (r2 != r6) goto L_0x0298
            r2 = 1
        L_0x00fe:
            r3.setFunctionEnable(r4, r5, r2)     // Catch:{ Throwable -> 0x029b }
        L_0x0101:
            if (r34 == 0) goto L_0x01b0
            java.lang.String r2 = "TEMPLATESWITCH"
            r3 = 0
            int r3 = r15.optInt(r2, r3)     // Catch:{ Throwable -> 0x02c6 }
            r2 = r3 & 1
            if (r2 == 0) goto L_0x02ba
            r2 = 1
        L_0x010f:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x02c6 }
            android.content.Context r5 = c     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r6 = "cookie_switch.txt"
            r4.setFunctionEnable(r5, r6, r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02c6 }
            r5.<init>()     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r6 = "useCookieCompatiable:"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x02c6 }
            com.tencent.smtt.utils.TbsLog.w(r4, r2)     // Catch:{ Throwable -> 0x02c6 }
            r2 = r3 & 2
            if (r2 == 0) goto L_0x02bd
            r2 = 1
        L_0x0137:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x02c6 }
            android.content.Context r5 = c     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r6 = "disable_get_apk_version_switch.txt"
            r4.setFunctionEnable(r5, r6, r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02c6 }
            r5.<init>()     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r6 = "disableGetApkVersionByReadFile:"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x02c6 }
            com.tencent.smtt.utils.TbsLog.w(r4, r2)     // Catch:{ Throwable -> 0x02c6 }
            r2 = r3 & 4
            if (r2 == 0) goto L_0x02c0
            r2 = 1
        L_0x015f:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r4 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x02c6 }
            android.content.Context r5 = c     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r6 = "disable_unpreinit.txt"
            r4.setFunctionEnable(r5, r6, r2)     // Catch:{ Throwable -> 0x02c6 }
            com.tencent.smtt.sdk.QbSdk.setDisableUnpreinitBySwitch(r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02c6 }
            r5.<init>()     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r6 = "disableUnpreinitBySwitch:"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x02c6 }
            com.tencent.smtt.utils.TbsLog.i(r4, r2)     // Catch:{ Throwable -> 0x02c6 }
            r2 = r3 & 8
            if (r2 == 0) goto L_0x02c3
            r2 = 1
        L_0x018a:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r3 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x02c6 }
            android.content.Context r4 = c     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r5 = "disable_use_host_backup_core.txt"
            r3.setFunctionEnable(r4, r5, r2)     // Catch:{ Throwable -> 0x02c6 }
            com.tencent.smtt.sdk.QbSdk.setDisableUseHostBackupCoreBySwitch(r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02c6 }
            r4.<init>()     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r5 = "disableUseHostBackupCoreBySwitch:"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ Throwable -> 0x02c6 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x02c6 }
            com.tencent.smtt.utils.TbsLog.i(r3, r2)     // Catch:{ Throwable -> 0x02c6 }
        L_0x01b0:
            r9 = 0
            r8 = 0
            r4 = 0
            r7 = 0
            r2 = 0
            r6 = 1
            r3 = 1
            java.lang.String r5 = ""
            java.lang.String r10 = "PKGMD5"
            java.lang.String r10 = r15.getString(r10)     // Catch:{ Exception -> 0x02ee }
            java.lang.String r9 = "RESETX5"
            int r9 = r15.getInt(r9)     // Catch:{ Exception -> 0x0848 }
            java.lang.String r8 = "UPLOADLOG"
            int r8 = r15.getInt(r8)     // Catch:{ Exception -> 0x0852 }
            java.lang.String r7 = "RESETTOKEN"
            boolean r7 = r15.has(r7)     // Catch:{ Exception -> 0x085d }
            if (r7 == 0) goto L_0x087e
            java.lang.String r7 = "RESETTOKEN"
            int r2 = r15.getInt(r7)     // Catch:{ Exception -> 0x085d }
            if (r2 == 0) goto L_0x02e5
            r2 = 1
        L_0x01dc:
            r7 = r2
        L_0x01dd:
            java.lang.String r2 = "SETTOKEN"
            boolean r2 = r15.has(r2)     // Catch:{ Exception -> 0x0869 }
            if (r2 == 0) goto L_0x01eb
            java.lang.String r2 = "SETTOKEN"
            java.lang.String r5 = r15.getString(r2)     // Catch:{ Exception -> 0x0869 }
        L_0x01eb:
            java.lang.String r2 = "ENABLE_LOAD_RENAME_FILE_LOCK"
            boolean r2 = r15.has(r2)     // Catch:{ Exception -> 0x0869 }
            if (r2 == 0) goto L_0x01fd
            java.lang.String r2 = "ENABLE_LOAD_RENAME_FILE_LOCK"
            int r2 = r15.getInt(r2)     // Catch:{ Exception -> 0x0869 }
            if (r2 == 0) goto L_0x02e8
            r2 = 1
        L_0x01fc:
            r6 = r2
        L_0x01fd:
            java.lang.String r2 = "ENABLE_LOAD_RENAME_FILE_LOCK_WAIT"
            boolean r2 = r15.has(r2)     // Catch:{ Exception -> 0x0869 }
            if (r2 == 0) goto L_0x087b
            java.lang.String r2 = "ENABLE_LOAD_RENAME_FILE_LOCK_WAIT"
            int r2 = r15.getInt(r2)     // Catch:{ Exception -> 0x0869 }
            if (r2 == 0) goto L_0x02eb
            r2 = 1
        L_0x020e:
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r2
        L_0x0214:
            java.lang.String r2 = "RESETDECOUPLECORE"
            int r2 = r15.getInt(r2)     // Catch:{ Exception -> 0x02fe }
        L_0x021a:
            r3 = 0
            java.lang.String r4 = "RESETTODECOUPLECORE"
            int r3 = r15.getInt(r4)     // Catch:{ Exception -> 0x0845 }
        L_0x0221:
            java.lang.Object r4 = f
            monitor-enter(r4)
            if (r8 == 0) goto L_0x0233
            java.util.Map<java.lang.String, java.lang.Object> r8 = r14.a     // Catch:{ all -> 0x0302 }
            java.lang.String r29 = "tbs_deskey_token"
            java.lang.String r30 = ""
            r0 = r29
            r1 = r30
            r8.put(r0, r1)     // Catch:{ all -> 0x0302 }
        L_0x0233:
            boolean r8 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0302 }
            if (r8 != 0) goto L_0x0267
            int r8 = r5.length()     // Catch:{ all -> 0x0302 }
            r29 = 96
            r0 = r29
            if (r8 != r0) goto L_0x0267
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0302 }
            r8.<init>()     // Catch:{ all -> 0x0302 }
            java.lang.StringBuilder r5 = r8.append(r5)     // Catch:{ all -> 0x0302 }
            java.lang.String r8 = "&"
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ all -> 0x0302 }
            java.lang.String r8 = com.tencent.smtt.utils.h.c()     // Catch:{ all -> 0x0302 }
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ all -> 0x0302 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0302 }
            java.util.Map<java.lang.String, java.lang.Object> r8 = r14.a     // Catch:{ all -> 0x0302 }
            java.lang.String r29 = "tbs_deskey_token"
            r0 = r29
            r8.put(r0, r5)     // Catch:{ all -> 0x0302 }
        L_0x0267:
            monitor-exit(r4)     // Catch:{ all -> 0x0302 }
            r4 = 1
            if (r10 != r4) goto L_0x030f
            if (r34 == 0) goto L_0x0305
            r2 = -110(0xffffffffffffff92, float:NaN)
            r14.setDownloadInterruptCode(r2)
        L_0x0272:
            android.content.Context r4 = c
            r2 = 1
            if (r3 != r2) goto L_0x030c
            r2 = 1
        L_0x0278:
            com.tencent.smtt.sdk.QbSdk.reset(r4, r2)
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[TbsDownloader.readResponse] return #3,needResetTbs=1,isQuery="
            java.lang.StringBuilder r3 = r3.append(r4)
            r0 = r34
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r2 = 0
            goto L_0x0041
        L_0x0298:
            r2 = 0
            goto L_0x00fe
        L_0x029b:
            r2 = move-exception
            java.lang.String r3 = "qbsdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "throwable:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r2)
            goto L_0x0101
        L_0x02ba:
            r2 = 0
            goto L_0x010f
        L_0x02bd:
            r2 = 0
            goto L_0x0137
        L_0x02c0:
            r2 = 0
            goto L_0x015f
        L_0x02c3:
            r2 = 0
            goto L_0x018a
        L_0x02c6:
            r2 = move-exception
            java.lang.String r3 = "qbsdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "throwable:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r2)
            goto L_0x01b0
        L_0x02e5:
            r2 = 0
            goto L_0x01dc
        L_0x02e8:
            r2 = 0
            goto L_0x01fc
        L_0x02eb:
            r2 = 0
            goto L_0x020e
        L_0x02ee:
            r10 = move-exception
            r31 = r5
            r5 = r6
            r6 = r2
            r2 = r31
        L_0x02f5:
            r10 = r8
            r11 = r9
            r8 = r6
            r9 = r7
            r7 = r5
            r6 = r3
            r5 = r2
            goto L_0x0214
        L_0x02fe:
            r2 = move-exception
            r2 = r4
            goto L_0x021a
        L_0x0302:
            r2 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0302 }
            throw r2
        L_0x0305:
            r2 = -210(0xffffffffffffff2e, float:NaN)
            r14.setDownloadInterruptCode(r2)
            goto L_0x0272
        L_0x030c:
            r2 = 0
            goto L_0x0278
        L_0x030f:
            if (r7 != 0) goto L_0x0314
            r14.setTbsCoreLoadRenameFileLockEnable(r7)
        L_0x0314:
            if (r6 != 0) goto L_0x0319
            r14.setTbsCoreLoadRenameFileLockWaitEnable(r6)
        L_0x0319:
            r3 = 1
            if (r2 != r3) goto L_0x0321
            android.content.Context r2 = c
            com.tencent.smtt.sdk.QbSdk.resetDecoupleCore(r2)
        L_0x0321:
            r2 = 1
            if (r9 != r2) goto L_0x0336
            android.os.Handler r2 = d
            r3 = 104(0x68, float:1.46E-43)
            r2.removeMessages(r3)
            android.os.Handler r2 = d
            r3 = 104(0x68, float:1.46E-43)
            android.os.Message r2 = android.os.Message.obtain(r2, r3)
            r2.sendToTarget()
        L_0x0336:
            r4 = 86400(0x15180, double:4.26873E-319)
            r2 = 1
            r0 = r28
            if (r0 != r2) goto L_0x0875
            r2 = 604800(0x93a80, double:2.98811E-318)
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0878
            r2 = 604800(0x93a80, double:2.98811E-318)
        L_0x0348:
            r6 = 0
            int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0875
        L_0x034e:
            long r4 = getRetryIntervalInSeconds()
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 < 0) goto L_0x035c
            long r2 = getRetryIntervalInSeconds()
        L_0x035c:
            java.util.Map<java.lang.String, java.lang.Object> r4 = r14.a
            java.lang.String r5 = "retry_interval"
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4.put(r5, r2)
            r2 = 0
            r3 = 0
            if (r34 == 0) goto L_0x0420
            java.lang.String r4 = "DECOUPLECOREVERSION"
            int r2 = r15.getInt(r4)     // Catch:{ Exception -> 0x0842 }
        L_0x0371:
            android.content.Context r4 = c     // Catch:{ Exception -> 0x083f }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ Exception -> 0x083f }
            android.content.SharedPreferences r4 = r4.mPreferences     // Catch:{ Exception -> 0x083f }
            java.lang.String r5 = "tbs_downloaddecouplecore"
            r6 = 0
            int r3 = r4.getInt(r5, r6)     // Catch:{ Exception -> 0x083f }
        L_0x0380:
            if (r34 == 0) goto L_0x0396
            android.content.Context r4 = c
            boolean r4 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)
            if (r4 != 0) goto L_0x0396
            if (r2 != 0) goto L_0x0396
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()
            android.content.Context r4 = c
            int r2 = r2.h(r4)
        L_0x0396:
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "in response decoupleCoreVersion is "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r2)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r14.a
            java.lang.String r5 = "tbs_decouplecoreversion"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r4.put(r5, r6)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r14.a
            java.lang.String r5 = "tbs_downloaddecouplecore"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r4.put(r5, r6)
            android.content.Context r4 = c
            boolean r4 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)
            if (r4 != 0) goto L_0x03ef
            if (r2 <= 0) goto L_0x0431
            com.tencent.smtt.sdk.l r4 = com.tencent.smtt.sdk.l.a()
            android.content.Context r5 = c
            int r4 = r4.h(r5)
            if (r2 == r4) goto L_0x0431
            com.tencent.smtt.sdk.l r4 = com.tencent.smtt.sdk.l.a()
            android.content.Context r5 = c
            int r4 = r4.i(r5)
            if (r2 != r4) goto L_0x0431
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()
            android.content.Context r4 = c
            r2.n(r4)
        L_0x03ef:
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 == 0) goto L_0x0443
            android.content.Context r2 = c
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r2)
            if (r2 == 0) goto L_0x0443
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r4 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
            r14.commit()
            if (r34 == 0) goto L_0x0416
            android.content.Context r2 = c
            r3 = 0
            r0 = r19
            com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(r2, r0, r3)
        L_0x0416:
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] return #4,current app is third app..."
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r2 = 0
            goto L_0x0041
        L_0x0420:
            android.content.Context r4 = c     // Catch:{ Exception -> 0x0842 }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ Exception -> 0x0842 }
            android.content.SharedPreferences r4 = r4.mPreferences     // Catch:{ Exception -> 0x0842 }
            java.lang.String r5 = "tbs_decouplecoreversion"
            r6 = 0
            int r2 = r4.getInt(r5, r6)     // Catch:{ Exception -> 0x0842 }
            goto L_0x0371
        L_0x0431:
            if (r2 != 0) goto L_0x03ef
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0441 }
            android.content.Context r4 = c     // Catch:{ Throwable -> 0x0441 }
            java.io.File r2 = r2.p(r4)     // Catch:{ Throwable -> 0x0441 }
            com.tencent.smtt.utils.f.b(r2)     // Catch:{ Throwable -> 0x0441 }
            goto L_0x03ef
        L_0x0441:
            r2 = move-exception
            goto L_0x03ef
        L_0x0443:
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "in response responseCode is "
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r16
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r4)
            if (r16 != 0) goto L_0x04b0
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_responsecode"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r16)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r4 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
            if (r34 == 0) goto L_0x049d
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r4 = -111(0xffffffffffffff91, float:NaN)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.put(r3, r4)
        L_0x0485:
            r14.commit()
            android.content.Context r2 = c
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r2)
            if (r2 != 0) goto L_0x0493
            startDecoupleCoreIfNeeded()
        L_0x0493:
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] return #5,responseCode=0"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r2 = 0
            goto L_0x0041
        L_0x049d:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r4 = -211(0xffffffffffffff2d, float:NaN)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.put(r3, r4)
            r2 = -211(0xffffffffffffff2d, float:NaN)
            r14.setDownloadInterruptCode(r2)
            goto L_0x0485
        L_0x04b0:
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            android.content.SharedPreferences r2 = r2.mPreferences
            java.lang.String r4 = "tbs_download_version"
            r5 = 0
            int r4 = r2.getInt(r4, r5)
            r0 = r19
            if (r4 <= r0) goto L_0x04d1
            com.tencent.smtt.sdk.i r2 = g
            r2.c()
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()
            android.content.Context r5 = c
            r2.o(r5)
        L_0x04d1:
            r2 = 0
            android.content.Context r5 = c
            boolean r5 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r5)
            if (r5 != 0) goto L_0x050e
            com.tencent.smtt.sdk.l r5 = com.tencent.smtt.sdk.l.a()
            android.content.Context r6 = c
            r7 = 0
            int r5 = r5.e(r6, r7)
            r0 = r19
            if (r5 < r0) goto L_0x04ea
            r2 = 1
        L_0x04ea:
            java.lang.String r6 = "TbsDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "tmpCoreVersion is "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r7 = " tbsDownloadVersion is"
            java.lang.StringBuilder r5 = r5.append(r7)
            r0 = r19
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r6, r5)
        L_0x050e:
            r0 = r33
            r1 = r19
            if (r0 >= r1) goto L_0x051c
            boolean r5 = android.text.TextUtils.isEmpty(r17)
            if (r5 != 0) goto L_0x051c
            if (r2 == 0) goto L_0x05dc
        L_0x051c:
            r2 = 1
            if (r3 == r2) goto L_0x05dc
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r2.put(r3, r5)
            if (r34 == 0) goto L_0x05b4
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 == 0) goto L_0x0582
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r5 = -124(0xffffffffffffff84, float:NaN)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r3, r5)
        L_0x0540:
            r14.commit()
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "version error or downloadUrl empty ,return ahead tbsLocalVersion="
            java.lang.StringBuilder r3 = r3.append(r5)
            r0 = r33
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r5 = " tbsDownloadVersion="
            java.lang.StringBuilder r3 = r3.append(r5)
            r0 = r19
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r5 = " tbsLastDownloadVersion="
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = " downloadUrl="
            java.lang.StringBuilder r3 = r3.append(r4)
            r0 = r17
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r2 = 0
            goto L_0x0041
        L_0x0582:
            if (r19 > 0) goto L_0x0592
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r5 = -125(0xffffffffffffff83, float:NaN)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r3, r5)
            goto L_0x0540
        L_0x0592:
            r0 = r33
            r1 = r19
            if (r0 < r1) goto L_0x05a6
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r5 = -127(0xffffffffffffff81, float:NaN)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r3, r5)
            goto L_0x0540
        L_0x05a6:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r5 = -112(0xffffffffffffff90, float:NaN)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r3, r5)
            goto L_0x0540
        L_0x05b4:
            r2 = -212(0xffffffffffffff2c, float:NaN)
            boolean r3 = android.text.TextUtils.isEmpty(r17)
            if (r3 == 0) goto L_0x05ce
            r2 = -217(0xffffffffffffff27, float:NaN)
        L_0x05be:
            java.util.Map<java.lang.String, java.lang.Object> r3 = r14.a
            java.lang.String r5 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r3.put(r5, r6)
            r14.setDownloadInterruptCode(r2)
            goto L_0x0540
        L_0x05ce:
            if (r19 > 0) goto L_0x05d3
            r2 = -218(0xffffffffffffff26, float:NaN)
            goto L_0x05be
        L_0x05d3:
            r0 = r33
            r1 = r19
            if (r0 < r1) goto L_0x05be
            r2 = -219(0xffffffffffffff25, float:NaN)
            goto L_0x05be
        L_0x05dc:
            android.content.SharedPreferences r2 = r14.mPreferences
            java.lang.String r4 = "tbs_downloadurl"
            r5 = 0
            java.lang.String r2 = r2.getString(r4, r5)
            r0 = r17
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x060a
            com.tencent.smtt.sdk.i r2 = g
            r2.c()
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r4 = "tbs_download_failed_retrytimes"
            r5 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r4, r5)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r4 = "tbs_download_success_retrytimes"
            r5 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r4, r5)
        L_0x060a:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r4 = "tbs_download_version"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r19)
            r2.put(r4, r5)
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "put KEY_TBS_DOWNLOAD_V is "
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r19
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r4)
            if (r19 <= 0) goto L_0x0658
            r2 = 1
            if (r3 != r2) goto L_0x0713
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r4 = "tbs_download_version_type"
            r5 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r4, r5)
        L_0x0640:
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "put KEY_TBS_DOWNLOAD_V_TYPE is "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
        L_0x0658:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_downloadurl"
            r0 = r17
            r2.put(r3, r0)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_downloadurl_list"
            r0 = r18
            r2.put(r3, r0)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_responsecode"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r16)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_maxflow"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r20)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_min_free_space"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r21)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_success_max_retrytimes"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r22)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_failed_max_retrytimes"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r23)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_single_timeout"
            java.lang.Long r4 = java.lang.Long.valueOf(r24)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_apkfilesize"
            java.lang.Long r4 = java.lang.Long.valueOf(r26)
            r2.put(r3, r4)
            r14.commit()
            if (r11 == 0) goto L_0x06c3
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_apk_md5"
            r2.put(r3, r11)
        L_0x06c3:
            if (r35 != 0) goto L_0x0734
            if (r36 == 0) goto L_0x0734
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()
            android.content.Context r3 = c
            r0 = r19
            boolean r2 = r2.b(r3, r0)
            if (r2 == 0) goto L_0x0734
            if (r34 == 0) goto L_0x0721
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r4 = -113(0xffffffffffffff8f, float:NaN)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.put(r3, r4)
        L_0x06e4:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r4 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] ##6 set needDownload=false"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
        L_0x06f7:
            java.lang.String r2 = "stop_pre_oat"
            r3 = 0
            int r2 = r15.optInt(r2, r3)
            r3 = 1
            if (r2 != r3) goto L_0x070d
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_stop_preoat"
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
        L_0x070d:
            r14.commit()
            r2 = 1
            goto L_0x0041
        L_0x0713:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r4 = "tbs_download_version_type"
            r5 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.put(r4, r5)
            goto L_0x0640
        L_0x0721:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            r4 = -213(0xffffffffffffff2b, float:NaN)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.put(r3, r4)
            r2 = -213(0xffffffffffffff2b, float:NaN)
            r14.setDownloadInterruptCode(r2)
            goto L_0x06e4
        L_0x0734:
            if (r35 != 0) goto L_0x07ac
            if (r36 == 0) goto L_0x07ac
            com.tencent.smtt.sdk.i r3 = g
            r2 = 1
            r0 = r16
            if (r0 == r2) goto L_0x0744
            r2 = 2
            r0 = r16
            if (r0 != r2) goto L_0x079e
        L_0x0744:
            r2 = 1
        L_0x0745:
            r0 = r35
            boolean r2 = r3.a(r0, r2)
            if (r2 == 0) goto L_0x07ac
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r4 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r2)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r2.tbsLogInfo()
            r3 = 100
            r2.setErrorCode(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "use local backup apk in needDownload"
            java.lang.StringBuilder r3 = r3.append(r4)
            com.tencent.smtt.sdk.i r4 = g
            java.lang.String r4 = r4.a
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.setFailDetail(r3)
            android.content.Context r3 = c
            boolean r3 = a(r3)
            if (r3 == 0) goto L_0x07a0
            android.content.Context r3 = c
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r3)
            com.tencent.smtt.sdk.TbsLogReport$EventType r4 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
            r3.eventReport(r4, r2)
        L_0x0795:
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] ##7 set needDownload=false"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            goto L_0x06f7
        L_0x079e:
            r2 = 0
            goto L_0x0745
        L_0x07a0:
            android.content.Context r3 = c
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r3)
            com.tencent.smtt.sdk.TbsLogReport$EventType r4 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            r3.eventReport(r4, r2)
            goto L_0x0795
        L_0x07ac:
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            android.content.SharedPreferences r2 = r2.mPreferences
            java.lang.String r3 = "tbs_download_version_type"
            r4 = 0
            int r2 = r2.getInt(r3, r4)
            r3 = 1
            if (r2 != r3) goto L_0x0823
            com.tencent.smtt.sdk.i r2 = g
            boolean r2 = r2.a()
            if (r2 == 0) goto L_0x0823
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r4 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r2)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r2.tbsLogInfo()
            r3 = 100
            r2.setErrorCode(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "installDecoupleCoreFromBackup"
            java.lang.StringBuilder r3 = r3.append(r4)
            com.tencent.smtt.sdk.i r4 = g
            java.lang.String r4 = r4.a
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.setFailDetail(r3)
            android.content.Context r3 = c
            boolean r3 = a(r3)
            if (r3 == 0) goto L_0x0817
            android.content.Context r3 = c
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r3)
            com.tencent.smtt.sdk.TbsLogReport$EventType r4 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
            r3.eventReport(r4, r2)
        L_0x080e:
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] ##8 set needDownload=false"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            goto L_0x06f7
        L_0x0817:
            android.content.Context r3 = c
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r3)
            com.tencent.smtt.sdk.TbsLogReport$EventType r4 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            r3.eventReport(r4, r2)
            goto L_0x080e
        L_0x0823:
            if (r34 != 0) goto L_0x082a
            r2 = -216(0xffffffffffffff28, float:NaN)
            r14.setDownloadInterruptCode(r2)
        L_0x082a:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r14.a
            java.lang.String r3 = "tbs_needdownload"
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r2.put(r3, r4)
            java.lang.String r2 = "TbsDownload"
            java.lang.String r3 = "[TbsDownloader.readResponse] ##9 set needDownload=true"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            goto L_0x06f7
        L_0x083f:
            r4 = move-exception
            goto L_0x0380
        L_0x0842:
            r4 = move-exception
            goto L_0x0371
        L_0x0845:
            r4 = move-exception
            goto L_0x0221
        L_0x0848:
            r9 = move-exception
            r9 = r10
            r31 = r6
            r6 = r2
            r2 = r5
            r5 = r31
            goto L_0x02f5
        L_0x0852:
            r8 = move-exception
            r8 = r9
            r9 = r10
            r31 = r2
            r2 = r5
            r5 = r6
            r6 = r31
            goto L_0x02f5
        L_0x085d:
            r7 = move-exception
            r7 = r8
            r8 = r9
            r9 = r10
            r31 = r5
            r5 = r6
            r6 = r2
            r2 = r31
            goto L_0x02f5
        L_0x0869:
            r2 = move-exception
            r2 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            goto L_0x02f5
        L_0x0872:
            r3 = move-exception
            goto L_0x00d2
        L_0x0875:
            r2 = r4
            goto L_0x034e
        L_0x0878:
            r2 = r12
            goto L_0x0348
        L_0x087b:
            r2 = r3
            goto L_0x020e
        L_0x087e:
            r7 = r2
            goto L_0x01dd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean, boolean, boolean):boolean");
    }

    public static void setRetryIntervalInSeconds(Context context, long j2) {
        if (context != null) {
            if (context.getApplicationInfo().packageName.equals("com.tencent.qqlive")) {
                l = j2;
            }
            TbsLog.i(LOGTAG, "mRetryIntervalInSeconds is " + l);
        }
    }

    public static long getRetryIntervalInSeconds() {
        return l;
    }

    static String b(Context context) {
        String str;
        String str2;
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str3 = VERSION.RELEASE;
        try {
            str = new String(str3.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e2) {
            str = str3;
        }
        if (str == null) {
            stringBuffer.append("1.0");
        } else if (str.length() > 0) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        String language = locale.getLanguage();
        if (language != null) {
            stringBuffer.append(language.toLowerCase());
            String country = locale.getCountry();
            if (country != null) {
                stringBuffer.append("-");
                stringBuffer.append(country.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(VERSION.CODENAME)) {
            String str4 = Build.MODEL;
            try {
                str2 = new String(str4.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception e3) {
                str2 = str4;
            }
            if (str2 == null) {
                stringBuffer.append("; ");
            } else if (str2.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str2);
            }
        }
        String replaceAll = (Build.ID == null ? "" : Build.ID).replaceAll("[一-龥]", "");
        if (replaceAll == null) {
            stringBuffer.append(" Build/");
            stringBuffer.append("00");
        } else if (replaceAll.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(replaceAll);
        }
        String str5 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1";
        String format = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", new Object[]{stringBuffer});
        b = format;
        return format;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    @TargetApi(11)
    static void c(Context context) {
        SharedPreferences sharedPreferences;
        SharedPreferences sharedPreferences2;
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.getInstance(context).clear();
        i.c(context);
        if (VERSION.SDK_INT >= 11) {
            sharedPreferences = context.getSharedPreferences("tbs_extension_config", 4);
        } else {
            sharedPreferences = context.getSharedPreferences("tbs_extension_config", 0);
        }
        sharedPreferences.edit().clear().commit();
        if (VERSION.SDK_INT >= 11) {
            sharedPreferences2 = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4);
        } else {
            sharedPreferences2 = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0);
        }
        sharedPreferences2.edit().clear().commit();
    }

    private static boolean h() {
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        if (instance.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            instance.setDownloadInterruptCode(-115);
            return false;
        } else if (instance.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            instance.setDownloadInterruptCode(-116);
            return false;
        } else if (!f.b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            instance.setDownloadInterruptCode(-117);
            return false;
        } else {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0) <= 86400000) {
                long j2 = instance.mPreferences.getLong(TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j2);
                if (j2 >= instance.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    instance.setDownloadInterruptCode(-120);
                    return false;
                }
            }
            return true;
        }
    }

    protected static File a(int i2) {
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        File file = null;
        int length = coreProviderAppList.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            String str = coreProviderAppList[i3];
            if (!str.equals(c.getApplicationInfo().packageName)) {
                file = new File(f.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                if (file == null || !file.exists()) {
                    TbsLog.i(LOGTAG, "can not find local backup core file");
                } else if (com.tencent.smtt.utils.a.a(c, file) == i2) {
                    TbsLog.i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    break;
                } else {
                    TbsLog.i(LOGTAG, "version is not match");
                }
            }
            i3++;
        }
        return file;
    }

    protected static File b(int i2) {
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        File file = null;
        int length = coreProviderAppList.length;
        int i3 = 0;
        while (i3 < length) {
            String str = coreProviderAppList[i3];
            File file2 = new File(f.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file2 == null || !file2.exists() || com.tencent.smtt.utils.a.a(c, file2) != i2) {
                file = new File(f.a(c, str, 4, false), "x5.tbs.decouple");
                if (file == null || !file.exists() || com.tencent.smtt.utils.a.a(c, file) != i2) {
                    i3++;
                } else {
                    TbsLog.i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    return file;
                }
            } else {
                TbsLog.i(LOGTAG, "local tbs version fond,path = " + file2.getAbsolutePath());
                return file2;
            }
        }
        return file;
    }

    private static JSONArray a(boolean z) {
        String[] coreProviderAppList;
        File file;
        boolean z2;
        JSONArray jSONArray = new JSONArray();
        for (String str : TbsShareManager.getCoreProviderAppList()) {
            if (z) {
                file = new File(f.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            } else {
                file = new File(f.a(c, str, 4, false), "x5.tbs.decouple");
            }
            if (file != null && file.exists()) {
                long a2 = (long) com.tencent.smtt.utils.a.a(c, file);
                if (a2 > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (((long) jSONArray.optInt(i2)) == a2) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(a2);
                    }
                }
            }
        }
        return jSONArray;
    }
}
