package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.f;
import java.io.File;
import java.io.IOException;

public class TbsShareManager {
    private static Context a;
    private static boolean b;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    private static String f = null;
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;
    private static String j = null;
    private static boolean k = false;
    private static boolean l = false;
    public static boolean mHasQueryed = false;

    static void a(Context context) {
        TbsLog.i("TbsShareManager", "shareTbsCore #1");
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            a(context, tbsLinuxToolsJni, l.a().q(context));
            File r = l.a().r(context);
            TbsLog.i("TbsShareManager", "shareTbsCore tbsShareDir is " + r.getAbsolutePath());
            tbsLinuxToolsJni.a(r.getAbsolutePath(), "755");
        } catch (Throwable th) {
            TbsLog.i("TbsShareManager", "shareTbsCore tbsShareDir error is " + th.getMessage() + " ## " + th.getCause());
            th.printStackTrace();
        }
    }

    static void b(Context context) {
        try {
            a(context, new TbsLinuxToolsJni(context), l.a().p(context));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        File[] listFiles;
        TbsLog.i("TbsShareManager", "shareAllDirsAndFiles #1");
        if (file != null && file.exists() && file.isDirectory()) {
            TbsLog.i("TbsShareManager", "shareAllDirsAndFiles dir is " + file.getAbsolutePath());
            tbsLinuxToolsJni.a(file.getAbsolutePath(), "755");
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    if (file2.getAbsolutePath().indexOf(".so") > 0) {
                        tbsLinuxToolsJni.a(file2.getAbsolutePath(), "755");
                    } else {
                        tbsLinuxToolsJni.a(file2.getAbsolutePath(), "644");
                    }
                } else if (file2.isDirectory()) {
                    a(context, tbsLinuxToolsJni, file2);
                } else {
                    TbsLog.e("TbsShareManager", "unknown file type.", true);
                }
            }
        }
    }

    public static void setHostCorePathAppDefined(String str) {
        c = str;
    }

    public static String getHostCorePathAppDefined() {
        return c;
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
            if (a != null && a.equals(StubApp.getOrigApplicationContext(context.getApplicationContext()))) {
                return b;
            }
            a = StubApp.getOrigApplicationContext(context.getApplicationContext());
            String packageName = a.getPackageName();
            for (String equals : getCoreProviderAppList()) {
                if (packageName.equals(equals)) {
                    b = false;
                    return false;
                }
            }
            b = true;
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE};
    }

    public static long getHostCoreVersions(Context context) {
        String[] coreProviderAppList;
        long j2 = 0;
        for (String str : getCoreProviderAppList()) {
            if (str.equalsIgnoreCase(TbsConfig.APP_WX)) {
                j2 += ((long) getSharedTbsCoreVersion(context, str)) * 10000000000L;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QQ)) {
                j2 += ((long) getSharedTbsCoreVersion(context, str)) * 100000;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QZONE)) {
                j2 += (long) getSharedTbsCoreVersion(context, str);
            }
        }
        return j2;
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return l.a().i(packageContext);
        }
        return 0;
    }

    public static int getCoreShareDecoupleCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return l.a().h(packageContext);
        }
        return 0;
    }

    public static int getBackupCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(f.a(getPackageContext(context, str, false), 4)), "x5.tbs.org");
            if (file.exists()) {
                return a.b(file);
            }
            return 0;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static int getBackupDecoupleCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(f.a(getPackageContext(context, str, false), 4)), "x5.tbs.decouple");
            if (file.exists()) {
                return a.b(file);
            }
            return 0;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static File getBackupCoreFile(Context context, String str) {
        try {
            File file = new File(new File(f.a(getPackageContext(context, str, false), 4)), "x5.tbs.org");
            if (file.exists()) {
                return file;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static File getBackupDecoupleCoreFile(Context context, String str) {
        try {
            File file = new File(new File(f.a(getPackageContext(context, str, true), 4)), "x5.tbs.decouple");
            if (file.exists()) {
                return file;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static boolean getCoreDisabled() {
        return g;
    }

    static String c(Context context) {
        j(context);
        return d;
    }

    static String a() {
        return d;
    }

    static int d(Context context) {
        return a(context, true);
    }

    static int a(Context context, boolean z) {
        b(context, z);
        return e;
    }

    static Context e(Context context) {
        Context context2 = null;
        j(context);
        if (f != null) {
            Context packageContext = getPackageContext(context, f, true);
            if (l.a().f(packageContext)) {
                context2 = packageContext;
            }
        }
        if (c != null) {
            return a;
        }
        return context2;
    }

    private static boolean k(Context context) {
        if (f == null) {
            return false;
        }
        if (e == getSharedTbsCoreVersion(context, f)) {
            return true;
        }
        if (e == getCoreShareDecoupleCoreVersion(context, f)) {
            return true;
        }
        return false;
    }

    private static boolean l(Context context) {
        if (QbSdk.getOnlyDownload()) {
            return false;
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        for (String str : coreProviderAppList) {
            if (e > 0 && e == getSharedTbsCoreVersion(context, str)) {
                Context packageContext = getPackageContext(context, str, true);
                if (l.a().f(context)) {
                    d = l.a().b(context, packageContext).getAbsolutePath();
                    f = str;
                    return true;
                }
            }
        }
        for (String str2 : coreProviderAppList) {
            if (e > 0 && e == getCoreShareDecoupleCoreVersion(context, str2)) {
                Context packageContext2 = getPackageContext(context, str2, true);
                if (l.a().f(context)) {
                    d = l.a().c(context, packageContext2).getAbsolutePath();
                    f = str2;
                    return true;
                }
            }
        }
        return false;
    }

    public static int findCoreForThirdPartyApp(Context context) {
        boolean z;
        n(context);
        TbsLog.i("TbsShareManager", "core_info mAvailableCoreVersion is " + e + " mAvailableCorePath is " + d + " mSrcPackageName is " + f);
        if (f == null) {
            TbsLog.e("TbsShareManager", "mSrcPackageName is null !!!");
        }
        if (f == null || !f.equals("AppDefined")) {
            if (!k(context) && !l(context)) {
                e = 0;
                d = null;
                f = null;
                TbsLog.i("TbsShareManager", "core_info error checkCoreInfo is false and checkCoreInOthers is false ");
            }
        } else if (e != l.a().a(c)) {
            e = 0;
            d = null;
            f = null;
            TbsLog.i("TbsShareManager", "check AppDefined core is error src is " + e + " dest is " + l.a().a(c));
        }
        if (e > 0) {
            String str = "com.jd.jrapp";
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (!("com.tencent.android.qqdownloader".equals(applicationInfo.packageName) || str.equals(applicationInfo.packageName))) {
                z = QbSdk.a(context, e);
            } else {
                z = false;
            }
            if (z || g) {
                e = 0;
                d = null;
                f = null;
                TbsLog.i("TbsShareManager", "core_info error QbSdk.isX5Disabled ");
            }
        }
        return e;
    }

    private static boolean m(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), "", "", Integer.toString(0));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0085 A[SYNTHETIC, Splitter:B:32:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008a A[SYNTHETIC, Splitter:B:35:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0094 A[SYNTHETIC, Splitter:B:41:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099 A[SYNTHETIC, Splitter:B:44:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r8, boolean r9) {
        /*
            r1 = 0
            r0 = 0
            r2 = 0
            java.lang.String r3 = "core_info"
            java.io.File r4 = getTbsShareFile(r8, r3)     // Catch:{ Throwable -> 0x007e, all -> 0x0090 }
            if (r4 != 0) goto L_0x0016
            if (r1 == 0) goto L_0x0010
            r0.close()     // Catch:{ Exception -> 0x009d }
        L_0x0010:
            if (r1 == 0) goto L_0x0015
            r2.close()     // Catch:{ Exception -> 0x00a0 }
        L_0x0015:
            return
        L_0x0016:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x007e, all -> 0x0090 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x007e, all -> 0x0090 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x007e, all -> 0x0090 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x007e, all -> 0x0090 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r0.<init>()     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r0.load(r3)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.lang.String r2 = "core_disabled"
            r5 = 0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r0.setProperty(r2, r5)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            if (r9 == 0) goto L_0x0063
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.io.File r2 = r2.q(r8)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            android.content.Context r5 = com.stub.StubApp.getOrigApplicationContext(r5)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            int r6 = com.tencent.smtt.utils.b.b(r8)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.lang.String r7 = "core_packagename"
            r0.setProperty(r7, r5)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.lang.String r5 = "core_path"
            r0.setProperty(r5, r2)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.lang.String r2 = "app_version"
            java.lang.String r5 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r0.setProperty(r2, r5)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
        L_0x0063:
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ab }
            r1 = 0
            r0.store(r2, r1)     // Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
            if (r3 == 0) goto L_0x0076
            r3.close()     // Catch:{ Exception -> 0x00a3 }
        L_0x0076:
            if (r2 == 0) goto L_0x0015
            r2.close()     // Catch:{ Exception -> 0x007c }
            goto L_0x0015
        L_0x007c:
            r0 = move-exception
            goto L_0x0015
        L_0x007e:
            r0 = move-exception
            r2 = r1
        L_0x0080:
            r0.printStackTrace()     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ Exception -> 0x00a5 }
        L_0x0088:
            if (r1 == 0) goto L_0x0015
            r1.close()     // Catch:{ Exception -> 0x008e }
            goto L_0x0015
        L_0x008e:
            r0 = move-exception
            goto L_0x0015
        L_0x0090:
            r0 = move-exception
            r3 = r1
        L_0x0092:
            if (r3 == 0) goto L_0x0097
            r3.close()     // Catch:{ Exception -> 0x00a7 }
        L_0x0097:
            if (r1 == 0) goto L_0x009c
            r1.close()     // Catch:{ Exception -> 0x00a9 }
        L_0x009c:
            throw r0
        L_0x009d:
            r0 = move-exception
            goto L_0x0010
        L_0x00a0:
            r0 = move-exception
            goto L_0x0015
        L_0x00a3:
            r0 = move-exception
            goto L_0x0076
        L_0x00a5:
            r0 = move-exception
            goto L_0x0088
        L_0x00a7:
            r2 = move-exception
            goto L_0x0097
        L_0x00a9:
            r1 = move-exception
            goto L_0x009c
        L_0x00ab:
            r0 = move-exception
            goto L_0x0092
        L_0x00ad:
            r0 = move-exception
            r1 = r2
            goto L_0x0092
        L_0x00b0:
            r0 = move-exception
            r3 = r2
            goto L_0x0092
        L_0x00b3:
            r0 = move-exception
            r2 = r3
            goto L_0x0080
        L_0x00b6:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.c(android.content.Context, boolean):void");
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        if (context == null || l.a().a(context, (File[]) null)) {
            return false;
        }
        int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO);
        if (sharedTbsCoreVersion <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, l.a().q(getPackageContext(context, TbsConfig.APP_DEMO, true)).getAbsolutePath(), "1");
        return true;
    }

    /* JADX INFO: used method not loaded: com.tencent.smtt.sdk.j.a(android.content.Context):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.smtt.sdk.j.a(java.lang.String, int):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.smtt.utils.f.b(java.io.File):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02f9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01ea, code lost:
        if (r6.equals(com.stub.StubApp.getOrigApplicationContext(r11.getApplicationContext()).getPackageName()) != false) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01ec, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + r12);
        com.tencent.smtt.sdk.j.a(a).a("remove_old_core", 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0210, code lost:
        writeProperties(r11, java.lang.Integer.toString(r12), r6, r8, java.lang.Integer.toString(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r0 = getTbsShareFile(r11, "core_info");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0223, code lost:
        if (i != false) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0225, code lost:
        if (r0 == null) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0227, code lost:
        r2 = new com.tencent.smtt.sdk.TbsLinuxToolsJni(a);
        r2.a(r0.getAbsolutePath(), "644");
        r2.a(com.tencent.smtt.sdk.l.a().r(r11).getAbsolutePath(), "755");
        i = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0255, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0256, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x028f, code lost:
        if (r6.equals(com.stub.StubApp.getOrigApplicationContext(r11.getApplicationContext()).getPackageName()) != false) goto L_0x02bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0291, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        com.tencent.smtt.utils.f.b(com.tencent.smtt.sdk.l.a().q(r11));
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void writeCoreInfoForThirdPartyApp(android.content.Context r11, int r12, boolean r13) {
        /*
            r0 = 0
            r1 = 1
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r3 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r3)
            java.lang.String r2 = "TbsShareManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r4.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = "writeCoreInfoForThirdPartyApp coreVersion is "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r4 = r4.append(r12)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.TbsLog.i(r2, r4)     // Catch:{ all -> 0x0059 }
            if (r12 != 0) goto L_0x002f
            m(r11)     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = a     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ all -> 0x0059 }
            r1 = -401(0xfffffffffffffe6f, float:NaN)
            r0.setDownloadInterruptCode(r1)     // Catch:{ all -> 0x0059 }
        L_0x002d:
            monitor-exit(r3)
            return
        L_0x002f:
            int r2 = h(r11)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = "TbsShareManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r5.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r6 = "writeCoreInfoForThirdPartyApp coreVersionFromConfig is "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ all -> 0x0059 }
            if (r2 >= 0) goto L_0x005c
            android.content.Context r0 = a     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ all -> 0x0059 }
            r1 = -402(0xfffffffffffffe6e, float:NaN)
            r0.setDownloadInterruptCode(r1)     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x0059:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x005c:
            if (r12 != r2) goto L_0x0078
            int r0 = d(r11)     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0069
            if (r13 != 0) goto L_0x0069
            a(r11, r12)     // Catch:{ all -> 0x0059 }
        L_0x0069:
            c(r11, r13)     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = a     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ all -> 0x0059 }
            r1 = -403(0xfffffffffffffe6d, float:NaN)
            r0.setDownloadInterruptCode(r1)     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x0078:
            if (r12 >= r2) goto L_0x0089
            m(r11)     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = a     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ all -> 0x0059 }
            r1 = -404(0xfffffffffffffe6c, float:NaN)
            r0.setDownloadInterruptCode(r1)     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x0089:
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            int r2 = r2.i(r11)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = "TbsShareManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r5.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r6 = "writeCoreInfoForThirdPartyApp coreVersionFromCoreShare is "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ all -> 0x0059 }
            if (r12 >= r2) goto L_0x00bb
            m(r11)     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = a     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ all -> 0x0059 }
            r1 = -404(0xfffffffffffffe6c, float:NaN)
            r0.setDownloadInterruptCode(r1)     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x00bb:
            java.lang.String[] r4 = d(r11, r13)     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = c     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x01ac
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = c     // Catch:{ all -> 0x0059 }
            int r2 = r2.a(r5)     // Catch:{ all -> 0x0059 }
            if (r12 != r2) goto L_0x0117
            java.lang.String r0 = java.lang.Integer.toString(r12)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "AppDefined"
            java.lang.String r2 = c     // Catch:{ all -> 0x0059 }
            r4 = 1
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x0059 }
            writeProperties(r11, r0, r1, r2, r4)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = "core_info"
            java.io.File r0 = getTbsShareFile(r11, r0)     // Catch:{ Throwable -> 0x0111 }
            boolean r1 = i     // Catch:{ Throwable -> 0x0111 }
            if (r1 != 0) goto L_0x002d
            if (r0 == 0) goto L_0x002d
            com.tencent.smtt.sdk.TbsLinuxToolsJni r1 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x0111 }
            android.content.Context r2 = a     // Catch:{ Throwable -> 0x0111 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0111 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x0111 }
            java.lang.String r2 = "644"
            r1.a(r0, r2)     // Catch:{ Throwable -> 0x0111 }
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0111 }
            java.io.File r0 = r0.r(r11)     // Catch:{ Throwable -> 0x0111 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x0111 }
            java.lang.String r2 = "755"
            r1.a(r0, r2)     // Catch:{ Throwable -> 0x0111 }
            r0 = 1
            i = r0     // Catch:{ Throwable -> 0x0111 }
            goto L_0x002d
        L_0x0111:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x0117:
            com.tencent.smtt.sdk.l r2 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = c     // Catch:{ all -> 0x0059 }
            int r2 = r2.a(r5)     // Catch:{ all -> 0x0059 }
            if (r12 <= r2) goto L_0x01ac
            int r5 = r4.length     // Catch:{ all -> 0x0059 }
            r2 = r0
        L_0x0125:
            if (r2 >= r5) goto L_0x01ac
            r6 = r4[r2]     // Catch:{ all -> 0x0059 }
            int r7 = getSharedTbsCoreVersion(r11, r6)     // Catch:{ all -> 0x0059 }
            if (r12 != r7) goto L_0x014d
            r7 = 1
            android.content.Context r6 = getPackageContext(r11, r6, r7)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r7 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            java.io.File r7 = r7.q(r6)     // Catch:{ all -> 0x0059 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.b.b(r11)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r8 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            boolean r6 = r8.f(r6)     // Catch:{ all -> 0x0059 }
            if (r6 != 0) goto L_0x0150
        L_0x014d:
            int r2 = r2 + 1
            goto L_0x0125
        L_0x0150:
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = c     // Catch:{ all -> 0x0059 }
            r0.<init>(r1)     // Catch:{ all -> 0x0059 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0059 }
            r1.<init>(r7)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.TbsShareManager$1 r2 = new com.tencent.smtt.sdk.TbsShareManager$1     // Catch:{ all -> 0x0059 }
            r2.<init>()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.f.a(r1, r0, r2)     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r0 = java.lang.Integer.toString(r12)     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r1 = "AppDefined"
            java.lang.String r2 = c     // Catch:{ Throwable -> 0x01a6 }
            r4 = 1
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ Throwable -> 0x01a6 }
            writeProperties(r11, r0, r1, r2, r4)     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r0 = "core_info"
            java.io.File r0 = getTbsShareFile(r11, r0)     // Catch:{ Throwable -> 0x01a6 }
            boolean r1 = i     // Catch:{ Throwable -> 0x01a6 }
            if (r1 != 0) goto L_0x002d
            if (r0 == 0) goto L_0x002d
            com.tencent.smtt.sdk.TbsLinuxToolsJni r1 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x01a6 }
            android.content.Context r2 = a     // Catch:{ Throwable -> 0x01a6 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r2 = "644"
            r1.a(r0, r2)     // Catch:{ Throwable -> 0x01a6 }
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x01a6 }
            java.io.File r0 = r0.r(r11)     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x01a6 }
            java.lang.String r2 = "755"
            r1.a(r0, r2)     // Catch:{ Throwable -> 0x01a6 }
            r0 = 1
            i = r0     // Catch:{ Throwable -> 0x01a6 }
            goto L_0x002d
        L_0x01a6:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x01ac:
            int r5 = r4.length     // Catch:{ all -> 0x0059 }
            r2 = r0
        L_0x01ae:
            if (r2 >= r5) goto L_0x024c
            r6 = r4[r2]     // Catch:{ all -> 0x0059 }
            int r7 = getSharedTbsCoreVersion(r11, r6)     // Catch:{ all -> 0x0059 }
            if (r12 != r7) goto L_0x025a
            r7 = 1
            android.content.Context r7 = getPackageContext(r11, r6, r7)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r8 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            java.io.File r8 = r8.q(r7)     // Catch:{ all -> 0x0059 }
            java.lang.String r8 = r8.getAbsolutePath()     // Catch:{ all -> 0x0059 }
            int r9 = com.tencent.smtt.utils.b.b(r11)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r10 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            boolean r7 = r10.f(r7)     // Catch:{ all -> 0x0059 }
            if (r7 != 0) goto L_0x01da
        L_0x01d7:
            int r2 = r2 + 1
            goto L_0x01ae
        L_0x01da:
            android.content.Context r0 = r11.getApplicationContext()     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0059 }
            boolean r0 = r6.equals(r0)     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0210
            java.lang.String r0 = "TbsShareManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r2.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = "thirdAPP pre--> delete old core_share Directory:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r2 = r2.append(r12)     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = a     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r0)     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = "remove_old_core"
            r4 = 1
            r0.a(r2, r4)     // Catch:{ all -> 0x0059 }
        L_0x0210:
            java.lang.String r0 = java.lang.Integer.toString(r12)     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = java.lang.Integer.toString(r9)     // Catch:{ all -> 0x0059 }
            writeProperties(r11, r0, r6, r8, r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = "core_info"
            java.io.File r0 = getTbsShareFile(r11, r0)     // Catch:{ Throwable -> 0x0255 }
            boolean r2 = i     // Catch:{ Throwable -> 0x0255 }
            if (r2 != 0) goto L_0x024b
            if (r0 == 0) goto L_0x024b
            com.tencent.smtt.sdk.TbsLinuxToolsJni r2 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x0255 }
            android.content.Context r4 = a     // Catch:{ Throwable -> 0x0255 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0255 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x0255 }
            java.lang.String r4 = "644"
            r2.a(r0, r4)     // Catch:{ Throwable -> 0x0255 }
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x0255 }
            java.io.File r0 = r0.r(r11)     // Catch:{ Throwable -> 0x0255 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x0255 }
            java.lang.String r4 = "755"
            r2.a(r0, r4)     // Catch:{ Throwable -> 0x0255 }
            r0 = 1
            i = r0     // Catch:{ Throwable -> 0x0255 }
        L_0x024b:
            r0 = r1
        L_0x024c:
            if (r0 != 0) goto L_0x002d
            if (r13 != 0) goto L_0x002d
            a(r11, r12)     // Catch:{ all -> 0x0059 }
            goto L_0x002d
        L_0x0255:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x024b
        L_0x025a:
            int r7 = getCoreShareDecoupleCoreVersion(r11, r6)     // Catch:{ all -> 0x0059 }
            if (r12 != r7) goto L_0x01d7
            r7 = 1
            android.content.Context r7 = getPackageContext(r11, r6, r7)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r8 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            java.io.File r8 = r8.p(r7)     // Catch:{ all -> 0x0059 }
            java.lang.String r8 = r8.getAbsolutePath()     // Catch:{ all -> 0x0059 }
            int r9 = com.tencent.smtt.utils.b.b(r11)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r10 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            boolean r7 = r10.f(r7)     // Catch:{ all -> 0x0059 }
            if (r7 == 0) goto L_0x01d7
            android.content.Context r0 = r11.getApplicationContext()     // Catch:{ all -> 0x0059 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0059 }
            boolean r0 = r6.equals(r0)     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x02bb
            java.lang.String r0 = "TbsShareManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r2.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = "thirdAPP pre--> delete old core_share Directory:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r2 = r2.append(r12)     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ all -> 0x0059 }
            java.io.File r0 = r0.q(r11)     // Catch:{ all -> 0x0059 }
            com.tencent.smtt.utils.f.b(r0)     // Catch:{ Throwable -> 0x02f9 }
            java.lang.String r0 = "TbsShareManager"
            java.lang.String r2 = "thirdAPP success--> delete old core_share Directory"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ Throwable -> 0x02f9 }
        L_0x02bb:
            java.lang.String r0 = java.lang.Integer.toString(r12)     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = java.lang.Integer.toString(r9)     // Catch:{ all -> 0x0059 }
            writeProperties(r11, r0, r6, r8, r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = "core_info"
            java.io.File r0 = getTbsShareFile(r11, r0)     // Catch:{ Throwable -> 0x02fe }
            boolean r2 = i     // Catch:{ Throwable -> 0x02fe }
            if (r2 != 0) goto L_0x02f6
            if (r0 == 0) goto L_0x02f6
            com.tencent.smtt.sdk.TbsLinuxToolsJni r2 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x02fe }
            android.content.Context r4 = a     // Catch:{ Throwable -> 0x02fe }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x02fe }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x02fe }
            java.lang.String r4 = "644"
            r2.a(r0, r4)     // Catch:{ Throwable -> 0x02fe }
            com.tencent.smtt.sdk.l r0 = com.tencent.smtt.sdk.l.a()     // Catch:{ Throwable -> 0x02fe }
            java.io.File r0 = r0.r(r11)     // Catch:{ Throwable -> 0x02fe }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x02fe }
            java.lang.String r4 = "755"
            r2.a(r0, r4)     // Catch:{ Throwable -> 0x02fe }
            r0 = 1
            i = r0     // Catch:{ Throwable -> 0x02fe }
        L_0x02f6:
            r0 = r1
            goto L_0x024c
        L_0x02f9:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x02bb
        L_0x02fe:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            goto L_0x02f6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(android.content.Context, int, boolean):void");
    }

    private static String[] d(Context context, boolean z) {
        if (QbSdk.getOnlyDownload()) {
            return new String[]{StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName()};
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        if (!z) {
            return coreProviderAppList;
        }
        return new String[]{StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName()};
    }

    private static void a(Context context, int i2) {
        if (!TbsPVConfig.getInstance(a).isDisableHostBackupCore() && l.a().t(context)) {
            String[] strArr = {TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE, context.getPackageName()};
            TbsLog.i("TbsShareManager", "find host backup core to unzip #1" + Log.getStackTraceString(new Throwable()));
            int length = strArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                String str = strArr[i3];
                if (i2 == getBackupCoreVersion(context, str)) {
                    if (!l.a().f(getPackageContext(context, str, false))) {
                        continue;
                    } else {
                        File backupCoreFile = getBackupCoreFile(context, str);
                        if (a.a(context, backupCoreFile, 0, i2)) {
                            TbsLog.i("TbsShareManager", "find host backup core to unzip normal coreVersion is " + i2 + " packageName is " + str);
                            l.a().b(context, backupCoreFile, i2);
                            break;
                        }
                    }
                } else if (i2 == getBackupDecoupleCoreVersion(context, str)) {
                    if (l.a().f(getPackageContext(context, str, false))) {
                        File backupDecoupleCoreFile = getBackupDecoupleCoreFile(context, str);
                        if (a.a(context, backupDecoupleCoreFile, 0, i2)) {
                            TbsLog.i("TbsShareManager", "find host backup core to unzip decouple coreVersion is " + i2 + " packageName is " + str);
                            l.a().b(context, backupDecoupleCoreFile, i2);
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
                i3++;
            }
            l.a().b();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ef A[SYNTHETIC, Splitter:B:45:0x00ef] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f4 A[SYNTHETIC, Splitter:B:48:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x010d A[SYNTHETIC, Splitter:B:59:0x010d] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0112 A[SYNTHETIC, Splitter:B:62:0x0112] */
    /* JADX WARNING: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeProperties(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r1 = 0
            r0 = 0
            java.lang.String r2 = "TbsShareManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "writeProperties coreVersion is "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r4 = " corePackageName is "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r8)
            java.lang.String r4 = " corePath is "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r9)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            java.lang.String r2 = "TbsShareManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "writeProperties -- stack: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.Throwable r4 = new java.lang.Throwable
            java.lang.String r5 = "#"
            r4.<init>(r5)
            java.lang.String r4 = android.util.Log.getStackTraceString(r4)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r2 = 0
            r3 = 0
            java.lang.String r4 = "core_info"
            java.io.File r4 = getTbsShareFile(r6, r4)     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            if (r4 != 0) goto L_0x007b
            android.content.Context r0 = a     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            r4 = -405(0xfffffffffffffe6b, float:NaN)
            r0.setDownloadInterruptCode(r4)     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            if (r1 == 0) goto L_0x006b
            r2.close()     // Catch:{ Exception -> 0x0071 }
        L_0x006b:
            if (r1 == 0) goto L_0x0070
            r3.close()     // Catch:{ Exception -> 0x0076 }
        L_0x0070:
            return
        L_0x0071:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006b
        L_0x0076:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0070
        L_0x007b:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x012b, all -> 0x0109 }
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r5.<init>()     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r5.load(r3)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            int r0 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x0120 }
        L_0x0091:
            if (r0 == 0) goto L_0x00dd
            java.lang.String r0 = "core_version"
            r5.setProperty(r0, r7)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            java.lang.String r0 = "core_disabled"
            r2 = 0
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r5.setProperty(r0, r2)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            java.lang.String r0 = "core_packagename"
            r5.setProperty(r0, r8)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            java.lang.String r0 = "core_path"
            r5.setProperty(r0, r9)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            java.lang.String r0 = "app_version"
            r5.setProperty(r0, r10)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
        L_0x00b1:
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r0 = 0
            r5.store(r2, r0)     // Catch:{ Throwable -> 0x012e, all -> 0x0125 }
            r0 = 0
            l = r0     // Catch:{ Throwable -> 0x012e, all -> 0x0125 }
            android.content.Context r0 = a     // Catch:{ Throwable -> 0x012e, all -> 0x0125 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Throwable -> 0x012e, all -> 0x0125 }
            r1 = -406(0xfffffffffffffe6a, float:NaN)
            r0.setDownloadInterruptCode(r1)     // Catch:{ Throwable -> 0x012e, all -> 0x0125 }
            if (r3 == 0) goto L_0x00d2
            r3.close()     // Catch:{ Exception -> 0x00ff }
        L_0x00d2:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch:{ Exception -> 0x00d8 }
            goto L_0x0070
        L_0x00d8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0070
        L_0x00dd:
            java.lang.String r0 = "core_disabled"
            r2 = 1
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            r5.setProperty(r0, r2)     // Catch:{ Throwable -> 0x00e8, all -> 0x0123 }
            goto L_0x00b1
        L_0x00e8:
            r0 = move-exception
            r2 = r3
        L_0x00ea:
            r0.printStackTrace()     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x00f2
            r2.close()     // Catch:{ Exception -> 0x0104 }
        L_0x00f2:
            if (r1 == 0) goto L_0x0070
            r1.close()     // Catch:{ Exception -> 0x00f9 }
            goto L_0x0070
        L_0x00f9:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0070
        L_0x00ff:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00d2
        L_0x0104:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00f2
        L_0x0109:
            r0 = move-exception
            r3 = r1
        L_0x010b:
            if (r3 == 0) goto L_0x0110
            r3.close()     // Catch:{ Exception -> 0x0116 }
        L_0x0110:
            if (r1 == 0) goto L_0x0115
            r1.close()     // Catch:{ Exception -> 0x011b }
        L_0x0115:
            throw r0
        L_0x0116:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0110
        L_0x011b:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0115
        L_0x0120:
            r2 = move-exception
            goto L_0x0091
        L_0x0123:
            r0 = move-exception
            goto L_0x010b
        L_0x0125:
            r0 = move-exception
            r1 = r2
            goto L_0x010b
        L_0x0128:
            r0 = move-exception
            r3 = r2
            goto L_0x010b
        L_0x012b:
            r0 = move-exception
            r2 = r1
            goto L_0x00ea
        L_0x012e:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x00ea
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeProperties(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0057 A[SYNTHETIC, Splitter:B:35:0x0057] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.lang.String f(android.content.Context r6) {
        /*
            r0 = 0
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r3 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r3)
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r2 = getTbsShareFile(r6, r2)     // Catch:{ Throwable -> 0x0045, all -> 0x0052 }
            if (r2 != 0) goto L_0x0014
            if (r0 == 0) goto L_0x0012
            r1.close()     // Catch:{ Exception -> 0x005e }
        L_0x0012:
            monitor-exit(r3)
            return r0
        L_0x0014:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0045, all -> 0x0052 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0045, all -> 0x0052 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0045, all -> 0x0052 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0045, all -> 0x0052 }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x0066 }
            r1.<init>()     // Catch:{ Throwable -> 0x0066 }
            r1.load(r2)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r4 = "core_packagename"
            java.lang.String r5 = ""
            java.lang.String r1 = r1.getProperty(r4, r5)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r1)     // Catch:{ Throwable -> 0x0066 }
            if (r4 != 0) goto L_0x003d
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ Exception -> 0x0060 }
        L_0x003b:
            r0 = r1
            goto L_0x0012
        L_0x003d:
            if (r2 == 0) goto L_0x0012
            r2.close()     // Catch:{ Exception -> 0x0043 }
            goto L_0x0012
        L_0x0043:
            r1 = move-exception
            goto L_0x0012
        L_0x0045:
            r1 = move-exception
            r2 = r0
        L_0x0047:
            r1.printStackTrace()     // Catch:{ all -> 0x0064 }
            if (r2 == 0) goto L_0x0012
            r2.close()     // Catch:{ Exception -> 0x0050 }
            goto L_0x0012
        L_0x0050:
            r1 = move-exception
            goto L_0x0012
        L_0x0052:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0055:
            if (r2 == 0) goto L_0x005a
            r2.close()     // Catch:{ Exception -> 0x0062 }
        L_0x005a:
            throw r0     // Catch:{ all -> 0x005b }
        L_0x005b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x005e:
            r1 = move-exception
            goto L_0x0012
        L_0x0060:
            r0 = move-exception
            goto L_0x003b
        L_0x0062:
            r1 = move-exception
            goto L_0x005a
        L_0x0064:
            r0 = move-exception
            goto L_0x0055
        L_0x0066:
            r1 = move-exception
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.f(android.content.Context):java.lang.String");
    }

    static String g(Context context) {
        try {
            n(context);
            if (d == null || TextUtils.isEmpty(d)) {
                return null;
            }
            StringBuilder sb = new StringBuilder(d);
            sb.append(File.separator);
            sb.append("res.apk");
            return sb.toString();
        } catch (Throwable th) {
            Log.e("", "getTbsResourcesPath exception: " + Log.getStackTraceString(th));
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x007f A[SYNTHETIC, Splitter:B:46:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0094 A[SYNTHETIC, Splitter:B:56:0x0094] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized int h(android.content.Context r6) {
        /*
            r0 = 0
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r3 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r3)
            java.lang.String r1 = "TbsShareManager"
            java.lang.String r2 = "readCoreVersionFromConfig #1"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ all -> 0x0027 }
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r2 = getTbsShareFile(r6, r2)     // Catch:{ Throwable -> 0x0079, all -> 0x0090 }
            if (r2 != 0) goto L_0x002a
            java.lang.String r2 = "TbsShareManager"
            java.lang.String r4 = "readCoreVersionFromConfig #2"
            com.tencent.smtt.utils.TbsLog.i(r2, r4)     // Catch:{ Throwable -> 0x0079, all -> 0x0090 }
            if (r1 == 0) goto L_0x0020
            r1.close()     // Catch:{ Exception -> 0x0022 }
        L_0x0020:
            monitor-exit(r3)
            return r0
        L_0x0022:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0027 }
            goto L_0x0020
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x002a:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0079, all -> 0x0090 }
            r4.<init>(r2)     // Catch:{ Throwable -> 0x0079, all -> 0x0090 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0079, all -> 0x0090 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0079, all -> 0x0090 }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            r1.<init>()     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            r1.load(r2)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            java.lang.String r4 = "core_version"
            java.lang.String r5 = ""
            java.lang.String r1 = r1.getProperty(r4, r5)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r1)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            if (r4 != 0) goto L_0x0067
            java.lang.String r0 = "TbsShareManager"
            java.lang.String r4 = "readCoreVersionFromConfig #3"
            com.tencent.smtt.utils.TbsLog.i(r0, r4)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            r1 = 0
            int r0 = java.lang.Math.max(r0, r1)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            if (r2 == 0) goto L_0x0020
            r2.close()     // Catch:{ Exception -> 0x0062 }
            goto L_0x0020
        L_0x0062:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0027 }
            goto L_0x0020
        L_0x0067:
            java.lang.String r1 = "TbsShareManager"
            java.lang.String r4 = "readCoreVersionFromConfig #4"
            com.tencent.smtt.utils.TbsLog.i(r1, r4)     // Catch:{ Throwable -> 0x00a2, all -> 0x009d }
            if (r2 == 0) goto L_0x0020
            r2.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0020
        L_0x0074:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0027 }
            goto L_0x0020
        L_0x0079:
            r0 = move-exception
        L_0x007a:
            r0.printStackTrace()     // Catch:{ all -> 0x009f }
            if (r1 == 0) goto L_0x0082
            r1.close()     // Catch:{ Exception -> 0x008b }
        L_0x0082:
            java.lang.String r0 = "TbsShareManager"
            java.lang.String r1 = "readCoreVersionFromConfig #5"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x0027 }
            r0 = -2
            goto L_0x0020
        L_0x008b:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0027 }
            goto L_0x0082
        L_0x0090:
            r0 = move-exception
            r2 = r1
        L_0x0092:
            if (r2 == 0) goto L_0x0097
            r2.close()     // Catch:{ Exception -> 0x0098 }
        L_0x0097:
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0098:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0027 }
            goto L_0x0097
        L_0x009d:
            r0 = move-exception
            goto L_0x0092
        L_0x009f:
            r0 = move-exception
            r2 = r1
            goto L_0x0092
        L_0x00a2:
            r0 = move-exception
            r1 = r2
            goto L_0x007a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.h(android.content.Context):int");
    }

    public static boolean getCoreFormOwn() {
        return k;
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x0106 A[SYNTHETIC, Splitter:B:69:0x0106] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:50:0x00e7=Splitter:B:50:0x00e7, B:71:0x0109=Splitter:B:71:0x0109, B:19:0x001f=Splitter:B:19:0x001f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void n(android.content.Context r7) {
        /*
            boolean r0 = l
            if (r0 == 0) goto L_0x0005
        L_0x0004:
            return
        L_0x0005:
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r3 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r3)
            boolean r0 = l     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x0011
            monitor-exit(r3)     // Catch:{ all -> 0x000e }
            goto L_0x0004
        L_0x000e:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x000e }
            throw r0
        L_0x0011:
            r2 = 0
            java.lang.String r0 = "core_info"
            java.io.File r0 = getTbsShareFile(r7, r0)     // Catch:{ Throwable -> 0x0111, all -> 0x0102 }
            if (r0 != 0) goto L_0x0026
            if (r2 == 0) goto L_0x001f
            r2.close()     // Catch:{ Exception -> 0x0021 }
        L_0x001f:
            monitor-exit(r3)     // Catch:{ all -> 0x000e }
            goto L_0x0004
        L_0x0021:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x000e }
            goto L_0x001f
        L_0x0026:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0111, all -> 0x0102 }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x0111, all -> 0x0102 }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0111, all -> 0x0102 }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x0111, all -> 0x0102 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x00ee }
            r0.<init>()     // Catch:{ Throwable -> 0x00ee }
            r0.load(r1)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r2 = "core_version"
            java.lang.String r4 = ""
            java.lang.String r2 = r0.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ Throwable -> 0x00ee }
            if (r4 != 0) goto L_0x0082
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00ee }
            r4 = 0
            int r2 = java.lang.Math.max(r2, r4)     // Catch:{ Throwable -> 0x00ee }
            e = r2     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r2 = "TbsShareManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ee }
            r4.<init>()     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r5 = "loadProperties -- mAvailableCoreVersion: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x00ee }
            int r5 = e     // Catch:{ Throwable -> 0x00ee }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r5 = " "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x00ee }
            java.lang.Throwable r5 = new java.lang.Throwable     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r6 = "#"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)     // Catch:{ Throwable -> 0x00ee }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x00ee }
            com.tencent.smtt.utils.TbsLog.i(r2, r4)     // Catch:{ Throwable -> 0x00ee }
        L_0x0082:
            java.lang.String r2 = "core_packagename"
            java.lang.String r4 = ""
            java.lang.String r2 = r0.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ Throwable -> 0x00ee }
            if (r4 != 0) goto L_0x0094
            f = r2     // Catch:{ Throwable -> 0x00ee }
        L_0x0094:
            java.lang.String r2 = f     // Catch:{ Throwable -> 0x00ee }
            if (r2 == 0) goto L_0x00ad
            android.content.Context r2 = a     // Catch:{ Throwable -> 0x00ee }
            if (r2 == 0) goto L_0x00ad
            java.lang.String r2 = f     // Catch:{ Throwable -> 0x00ee }
            android.content.Context r4 = a     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ Throwable -> 0x00ee }
            boolean r2 = r2.equals(r4)     // Catch:{ Throwable -> 0x00ee }
            if (r2 == 0) goto L_0x00ea
            r2 = 1
            k = r2     // Catch:{ Throwable -> 0x00ee }
        L_0x00ad:
            java.lang.String r2 = "core_path"
            java.lang.String r4 = ""
            java.lang.String r2 = r0.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ Throwable -> 0x00ee }
            if (r4 != 0) goto L_0x00bf
            d = r2     // Catch:{ Throwable -> 0x00ee }
        L_0x00bf:
            java.lang.String r2 = "app_version"
            java.lang.String r4 = ""
            java.lang.String r2 = r0.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ee }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ Throwable -> 0x00ee }
            if (r4 != 0) goto L_0x00d1
            j = r2     // Catch:{ Throwable -> 0x00ee }
        L_0x00d1:
            java.lang.String r2 = "core_disabled"
            java.lang.String r4 = "false"
            java.lang.String r0 = r0.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ee }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Throwable -> 0x00ee }
            g = r0     // Catch:{ Throwable -> 0x00ee }
            r0 = 1
            l = r0     // Catch:{ Throwable -> 0x00ee }
            if (r1 == 0) goto L_0x00e7
            r1.close()     // Catch:{ Exception -> 0x00fd }
        L_0x00e7:
            monitor-exit(r3)     // Catch:{ all -> 0x000e }
            goto L_0x0004
        L_0x00ea:
            r2 = 0
            k = r2     // Catch:{ Throwable -> 0x00ee }
            goto L_0x00ad
        L_0x00ee:
            r0 = move-exception
        L_0x00ef:
            r0.printStackTrace()     // Catch:{ all -> 0x010f }
            if (r1 == 0) goto L_0x00e7
            r1.close()     // Catch:{ Exception -> 0x00f8 }
            goto L_0x00e7
        L_0x00f8:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x000e }
            goto L_0x00e7
        L_0x00fd:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x000e }
            goto L_0x00e7
        L_0x0102:
            r0 = move-exception
            r1 = r2
        L_0x0104:
            if (r1 == 0) goto L_0x0109
            r1.close()     // Catch:{ Exception -> 0x010a }
        L_0x0109:
            throw r0     // Catch:{ all -> 0x000e }
        L_0x010a:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x000e }
            goto L_0x0109
        L_0x010f:
            r0 = move-exception
            goto L_0x0104
        L_0x0111:
            r0 = move-exception
            r1 = r2
            goto L_0x00ef
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.n(android.content.Context):void");
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        int i2 = 0;
        try {
            if (QbSdk.isNeedInitX5FirstTime() && isThirdPartyApp(context) && !QbSdk.getOnlyDownload()) {
                File r = l.a().r(context);
                if (r != null) {
                    if (z) {
                        File file = new File(r, "core_info");
                        if (file != null && file.exists()) {
                            return;
                        }
                    }
                    if (c != null) {
                        int a2 = l.a().a(c);
                        if (a2 > 0) {
                            d = c;
                            f = "AppDefined";
                            e = a2;
                            TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1 -- mAvailableCoreVersion: " + e + " " + Log.getStackTraceString(new Throwable("#")));
                            writeProperties(context, Integer.toString(e), f, d, Integer.toString(1));
                            return;
                        }
                    }
                    TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1");
                    int h2 = h(context);
                    int i3 = l.a().i(context);
                    TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromConfig is " + h2);
                    TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromCoreShare is " + i3);
                    String[] coreProviderAppList = getCoreProviderAppList();
                    for (String str : coreProviderAppList) {
                        int coreShareDecoupleCoreVersion = getCoreShareDecoupleCoreVersion(context, str);
                        if (coreShareDecoupleCoreVersion >= h2 && coreShareDecoupleCoreVersion >= i3 && coreShareDecoupleCoreVersion > 0) {
                            d = l.a().c(context, getPackageContext(context, str, true)).getAbsolutePath();
                            f = str;
                            e = coreShareDecoupleCoreVersion;
                            TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2 -- mAvailableCoreVersion: " + e + " " + Log.getStackTraceString(new Throwable("#")));
                            if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                int b2 = b.b(context);
                                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2");
                                writeProperties(context, Integer.toString(e), f, d, Integer.toString(b2));
                                return;
                            }
                            e = 0;
                            d = null;
                            f = null;
                        }
                    }
                    for (String str2 : coreProviderAppList) {
                        int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str2);
                        if (sharedTbsCoreVersion >= h2 && sharedTbsCoreVersion >= i3 && sharedTbsCoreVersion > 0) {
                            d = l.a().b(context, getPackageContext(context, str2, true)).getAbsolutePath();
                            f = str2;
                            e = sharedTbsCoreVersion;
                            TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #3 -- mAvailableCoreVersion: " + e + " " + Log.getStackTraceString(new Throwable("#")));
                            if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                writeProperties(context, Integer.toString(e), f, d, Integer.toString(b.b(context)));
                                return;
                            }
                            e = 0;
                            d = null;
                            f = null;
                        }
                    }
                    if (TbsPVConfig.getInstance(a).isDisableHostBackupCore()) {
                        return;
                    }
                    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                        int length = coreProviderAppList.length;
                        while (i2 < length) {
                            String str3 = coreProviderAppList[i2];
                            int backupCoreVersion = getBackupCoreVersion(context, str3);
                            if (backupCoreVersion < h2 || backupCoreVersion < i3 || backupCoreVersion <= 0) {
                                int backupDecoupleCoreVersion = getBackupDecoupleCoreVersion(context, str3);
                                if (backupDecoupleCoreVersion < h2 || backupDecoupleCoreVersion < i3 || backupDecoupleCoreVersion <= 0) {
                                    i2++;
                                } else {
                                    TbsLog.i("TbsShareManager", "find host backup core to unzip forceload decouple coreVersion is " + backupDecoupleCoreVersion + " packageName is " + str3);
                                    l.a().a(context, getBackupCoreFile(context, str3), backupDecoupleCoreVersion);
                                    TbsLog.i("TbsShareManager", "find host backup decouple core to unzip forceload after unzip ");
                                    return;
                                }
                            } else {
                                TbsLog.i("TbsShareManager", "find host backup core to unzip forceload coreVersion is " + backupCoreVersion + " packageName is " + str3);
                                l.a().a(context, getBackupCoreFile(context, str3), backupCoreVersion);
                                TbsLog.i("TbsShareManager", "find host backup core to unzip forceload after unzip ");
                                return;
                            }
                        }
                        return;
                    }
                    TbsLog.i("TbsShareManager", "in mainthread so do not find host backup core to install ");
                }
            }
        } catch (Exception e2) {
        }
    }

    public static File getTbsShareFile(Context context, String str) {
        File r = l.a().r(context);
        if (r == null) {
            return null;
        }
        File file = new File(r, str);
        if (file != null && file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static boolean i(Context context) {
        try {
            if (e == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (e == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, null, new Object[0]);
                return false;
            }
            if (c == null) {
                if (e != 0 && getSharedTbsCoreVersion(context, f) == e) {
                    return true;
                }
            } else if (e != 0 && l.a().a(c) == e) {
                return true;
            }
            if (l(context)) {
                return true;
            }
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CORE_EXIST_NOT_LOAD, new Throwable("mAvailableCoreVersion=" + e + "; mSrcPackageName=" + f + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, f) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
            d = null;
            e = 0;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, null, new Object[0]);
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, null, new Object[0]);
            return false;
        }
    }

    static boolean j(Context context) {
        return b(context, true);
    }

    static boolean b(Context context, boolean z) {
        if (i(context)) {
            return true;
        }
        if (z) {
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        }
        return false;
    }

    public static Context getPackageContext(Context context, String str, boolean z) {
        if (z) {
            try {
                if (!context.getPackageName().equals(str) && TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                    TbsLog.i("TbsShareManager", "gray no core app,skip get context");
                    return null;
                }
            } catch (NameNotFoundException e2) {
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }
        return context.createPackageContext(str, 2);
    }
}
