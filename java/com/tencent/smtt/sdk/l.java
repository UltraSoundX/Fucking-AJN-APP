package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.f;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: TbsInstaller */
class l {
    public static ThreadLocal<Integer> a = new ThreadLocal<Integer>() {
        /* renamed from: a */
        public Integer initialValue() {
            return Integer.valueOf(0);
        }
    };
    static boolean b = false;
    static final FileFilter c = new FileFilter() {
        public boolean accept(File file) {
            String name = file.getName();
            if (name == null || name.endsWith(".jar_is_first_load_dex_flag_file")) {
                return false;
            }
            if (VERSION.SDK_INT >= 21 && name.endsWith(".dex")) {
                return false;
            }
            if (VERSION.SDK_INT >= 26 && name.endsWith(".prof")) {
                return false;
            }
            if (VERSION.SDK_INT < 26 || !name.equals("oat")) {
                return true;
            }
            return false;
        }
    };
    private static l d = null;
    private static final ReentrantLock i = new ReentrantLock();
    private static final Lock j = new ReentrantLock();
    private static FileLock l = null;
    private static Handler m = null;
    private static final Long[][] n = {new Long[]{Long.valueOf(44006), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44005), Long.valueOf(39094008)}, new Long[]{Long.valueOf(43910), Long.valueOf(38917816)}, new Long[]{Long.valueOf(44027), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44028), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44029), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44030), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44032), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44033), Long.valueOf(39094008)}, new Long[]{Long.valueOf(44034), Long.valueOf(39094008)}, new Long[]{Long.valueOf(43909), Long.valueOf(38917816)}};
    private static int o = 0;
    private static boolean p = false;
    private int e = 0;
    private FileLock f;
    private FileOutputStream g;
    private boolean h = false;
    private boolean k = false;

    private l() {
        if (m == null) {
            m = new Handler(k.a().getLooper()) {
                public void handleMessage(Message message) {
                    QbSdk.setTBSInstallingStatus(true);
                    switch (message.what) {
                        case 1:
                            TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE");
                            Object[] objArr = (Object[]) message.obj;
                            l.this.b((Context) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
                            return;
                        case 2:
                            TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_COPY_TBS_CORE");
                            Object[] objArr2 = (Object[]) message.obj;
                            l.this.a((Context) objArr2[0], (Context) objArr2[1], ((Integer) objArr2[2]).intValue());
                            return;
                        case 3:
                            TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE_EX");
                            Object[] objArr3 = (Object[]) message.obj;
                            l.this.b((Context) objArr3[0], (Bundle) objArr3[1]);
                            return;
                        case 4:
                            TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_UNZIP_TBS_CORE");
                            Object[] objArr4 = (Object[]) message.obj;
                            l.this.b((Context) objArr4[0], (File) objArr4[1], ((Integer) objArr4[2]).intValue());
                            QbSdk.setTBSInstallingStatus(false);
                            super.handleMessage(message);
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    static synchronized l a() {
        l lVar;
        synchronized (l.class) {
            if (d == null) {
                synchronized (l.class) {
                    if (d == null) {
                        d = new l();
                    }
                }
            }
            lVar = d;
        }
        return lVar;
    }

    public int a(boolean z, Context context) {
        if (z || ((Integer) a.get()).intValue() <= 0) {
            a.set(Integer.valueOf(i(context)));
        }
        return ((Integer) a.get()).intValue();
    }

    private synchronized boolean c(Context context, boolean z) {
        boolean z2 = false;
        boolean z3 = true;
        synchronized (this) {
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch");
            try {
                if (t(context)) {
                    boolean tryLock = i.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch Locked =" + tryLock);
                    if (tryLock) {
                        int b2 = j.a(context).b("tpatch_status");
                        int a2 = a(false, context);
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch copyStatus =" + b2);
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer =" + a2);
                        if (b2 == 1) {
                            if (a2 == 0) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer = 0", true);
                                y(context);
                            } else if (z) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer != 0", true);
                                y(context);
                            }
                            i.unlock();
                            z2 = z3;
                        }
                        z3 = false;
                        try {
                            i.unlock();
                            z2 = z3;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            z2 = z3;
                            th = th2;
                            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.COPY_EXCEPTION, th.toString());
                            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromTpatch exception:" + Log.getStackTraceString(th));
                            return z2;
                        }
                    }
                    b();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return z2;
    }

    private synchronized boolean d(Context context, boolean z) {
        boolean z2 = false;
        boolean z3 = true;
        synchronized (this) {
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
            try {
                if (t(context)) {
                    boolean tryLock = i.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
                    if (tryLock) {
                        int b2 = j.a(context).b("copy_status");
                        int a2 = a(false, context);
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b2);
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a2);
                        if (b2 == 1) {
                            if (a2 == 0) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                                z(context);
                            } else if (z) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                                z(context);
                            }
                            i.unlock();
                            z2 = z3;
                        }
                        z3 = false;
                        try {
                            i.unlock();
                            z2 = z3;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            z2 = z3;
                            th = th2;
                            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.COPY_EXCEPTION, th.toString());
                            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
                            return z2;
                        }
                    }
                    b();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return z2;
    }

    private synchronized boolean e(Context context, boolean z) {
        boolean z2 = true;
        boolean z3 = false;
        synchronized (this) {
            if (context != null) {
                if (TbsConfig.APP_WX.equals(StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().packageName)) {
                    TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.INSTALL_FROM_UNZIP, " ");
                }
            }
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
            TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #1 ");
            try {
                if (t(context)) {
                    TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #2 ");
                    boolean tryLock = i.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
                    if (tryLock) {
                        int c2 = j.a(context).c();
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c2);
                        int a2 = a(false, context);
                        if (c2 == 2) {
                            TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #4 ");
                            if (a2 == 0) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                                x(context);
                            } else if (z) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                                x(context);
                            }
                            i.unlock();
                            z3 = z2;
                        }
                        z2 = false;
                        try {
                            i.unlock();
                            z3 = z2;
                        } catch (Exception e2) {
                            Exception exc = e2;
                            z3 = z2;
                            e = exc;
                            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e);
                            e.printStackTrace();
                            return z3;
                        }
                    }
                    b();
                }
            } catch (Exception e3) {
                e = e3;
            } catch (Throwable th) {
                i.unlock();
                throw th;
            }
        }
        return z3;
    }

    private synchronized boolean f(Context context, boolean z) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, boolean z) {
        int i2;
        String str;
        int i3;
        int i4;
        int i5;
        boolean z2 = false;
        if (z) {
            this.k = true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (t(context)) {
            if (i.tryLock()) {
                try {
                    i4 = j.a(context).c();
                    i3 = j.a(context).b();
                    str = j.a(context).d("install_apk_path");
                    i5 = j.a(context).c("copy_core_ver");
                    i2 = j.a(context).b("copy_status");
                } finally {
                    i.unlock();
                }
            } else {
                i2 = -1;
                str = null;
                i3 = 0;
                i4 = -1;
                i5 = 0;
            }
            b();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + i4);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + i3);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + str);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + i5);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + i2);
            if (TbsShareManager.isThirdPartyApp(context)) {
                c(context, TbsShareManager.a(context, false));
                return;
            }
            int i6 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsConfigKey.KEY_RESPONSECODE, 0);
            if (i6 == 1 || i6 == 2 || i6 == 4) {
                z2 = true;
            }
            if (!(z2 || i6 == 0 || i6 == 5)) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                a(context, bundle);
            }
            if (i4 > -1 && i4 < 2) {
                a(context, str, i3);
            }
            if (i2 == 0) {
                b(context, i5);
            }
        }
    }

    public static void a(Context context) {
        if (v(context)) {
            return;
        }
        if (a(context, "core_unzip_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
            TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME");
        } else if (a(context, "core_share_backup_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
            TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME");
        } else if (a(context, "core_copy_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
            TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME");
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Context context, boolean z) {
        if (!QbSdk.b) {
            if (VERSION.SDK_INT < 8) {
                TbsLog.e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
                return;
            }
            TbsLog.i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #1 ");
            if (TbsShareManager.isThirdPartyApp(context) && j.a(context).b("remove_old_core") == 1 && z) {
                try {
                    f.b(a().q(context));
                    TbsLog.i("TbsInstaller", "thirdAPP success--> delete old core_share Directory");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                j.a(context).a("remove_old_core", 0);
            }
            if (v(context)) {
                TbsLog.i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #2 ");
                if (a(context, "core_unzip_tmp") && e(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!", true);
                } else if (a(context, "core_share_backup_tmp") && f(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromBackup!!", true);
                } else if (a(context, "core_copy_tmp") && d(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!", true);
                } else if (a(context, "tpatch_tmp") && c(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromTpatch!!", true);
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                }
            }
        }
    }

    static boolean a(Context context, String str) {
        File file = new File(context.getDir("tbs", 0), str);
        if (file == null || !file.exists()) {
            TbsLog.i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #1");
            return false;
        }
        File file2 = new File(file, "tbs.conf");
        if (file2 == null || !file2.exists()) {
            TbsLog.i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #2");
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #3");
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, String str, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        m.sendMessage(message);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x04da  */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r13, java.lang.String r14, int r15) {
        /*
            r12 = this;
            r11 = 2
            r5 = 0
            r4 = 1
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r3 = -501(0xfffffffffffffe0b, float:NaN)
            r2.setInstallInterruptCode(r3)
            boolean r2 = r12.c(r13)
            if (r2 == 0) goto L_0x0023
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "isTbsLocalInstalled --> no installation!"
            com.tencent.smtt.utils.TbsLog.i(r2, r3, r4)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r3 = -502(0xfffffffffffffe0a, float:NaN)
            r2.setInstallInterruptCode(r3)
        L_0x0022:
            return
        L_0x0023:
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread tbsApkPath="
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r3 = r3.append(r14)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer="
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r3 = r3.append(r15)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "TbsInstaller-continueInstallTbsCore currentProcessName="
            java.lang.StringBuilder r3 = r3.append(r6)
            android.content.pm.ApplicationInfo r6 = r13.getApplicationInfo()
            java.lang.String r6 = r6.processName
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread currentProcessId="
            java.lang.StringBuilder r3 = r3.append(r6)
            int r6 = android.os.Process.myPid()
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread currentThreadName="
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            java.lang.String r6 = r6.getName()
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 11
            if (r2 < r3) goto L_0x00ec
            java.lang.String r2 = "tbs_preloadx5_check_cfg_file"
            r3 = 4
            android.content.SharedPreferences r2 = r13.getSharedPreferences(r2, r3)
        L_0x00ba:
            java.lang.String r3 = "tbs_precheck_disable_version"
            r6 = -1
            int r2 = r2.getInt(r3, r6)
            if (r2 != r15) goto L_0x00f3
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller-installTbsCoreInThread -- version:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r15)
            java.lang.String r4 = " is disabled by preload_x5_check!"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r3 = -503(0xfffffffffffffe09, float:NaN)
            r2.setInstallInterruptCode(r3)
            goto L_0x0022
        L_0x00ec:
            java.lang.String r2 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r2 = r13.getSharedPreferences(r2, r5)
            goto L_0x00ba
        L_0x00f3:
            boolean r2 = com.tencent.smtt.utils.f.b(r13)
            if (r2 != 0) goto L_0x0136
            long r2 = com.tencent.smtt.utils.q.a()
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            long r4 = r4.getDownloadMinFreeSpace()
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r7 = -504(0xfffffffffffffe08, float:NaN)
            r6.setInstallInterruptCode(r7)
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)
            r7 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "rom is not enough when installing tbs core! curAvailROM="
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r2 = r8.append(r2)
            java.lang.String r3 = ",minReqRom="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.setInstallErrorCode(r7, r2)
            goto L_0x0022
        L_0x0136:
            boolean r2 = r12.t(r13)
            if (r2 != 0) goto L_0x0147
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r3 = -505(0xfffffffffffffe07, float:NaN)
            r2.setInstallInterruptCode(r3)
            goto L_0x0022
        L_0x0147:
            java.util.concurrent.locks.Lock r2 = j
            boolean r2 = r2.tryLock()
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TbsInstaller-installTbsCoreInThread locked ="
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r6)
            if (r2 == 0) goto L_0x0719
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r3 = -507(0xfffffffffffffe05, float:NaN)
            r2.setInstallInterruptCode(r3)
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.lock()
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "copy_core_ver"
            int r2 = r2.c(r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            int r3 = r3.b()     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r7.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread tbsCoreCopyVer ="
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r7 = r7.append(r2)     // Catch:{ all -> 0x04ca }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r7.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread tbsCoreInstallVer ="
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r7 = r7.append(r3)     // Catch:{ all -> 0x04ca }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r7.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer ="
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r7 = r7.append(r15)     // Catch:{ all -> 0x04ca }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ all -> 0x04ca }
            if (r3 <= 0) goto L_0x01d3
            if (r15 > r3) goto L_0x01d7
        L_0x01d3:
            if (r2 <= 0) goto L_0x01da
            if (r15 <= r2) goto L_0x01da
        L_0x01d7:
            r12.o(r13)     // Catch:{ all -> 0x04ca }
        L_0x01da:
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            int r2 = r2.c()     // Catch:{ all -> 0x04ca }
            int r3 = r12.i(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r7.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread installStatus1="
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r7 = r7.append(r2)     // Catch:{ all -> 0x04ca }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r7.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r8 = "TbsInstaller-installTbsCoreInThread tbsCoreInstalledVer="
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r7 = r7.append(r3)     // Catch:{ all -> 0x04ca }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ all -> 0x04ca }
            if (r2 < 0) goto L_0x0294
            if (r2 >= r11) goto L_0x0294
            java.lang.String r3 = "TbsInstaller"
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread -- retry....."
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r3, r6, r7)     // Catch:{ all -> 0x04ca }
            r7 = r2
            r8 = r4
        L_0x0224:
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -508(0xfffffffffffffe04, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r3.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread installStatus2="
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            if (r7 >= r4) goto L_0x0595
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "STEP 2/2 begin installation....."
            r6 = 1
            com.tencent.smtt.utils.TbsLog.i(r2, r3, r6)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -509(0xfffffffffffffe03, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            if (r8 == 0) goto L_0x02be
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "unzip_retry_num"
            int r2 = r2.c(r3)     // Catch:{ all -> 0x04ca }
            r3 = 10
            if (r2 <= r3) goto L_0x02b5
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 201(0xc9, float:2.82E-43)
            java.lang.String r4 = "exceed unzip retry num!"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            r12.E(r13)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -510(0xfffffffffffffe02, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x02b0 }
            r2.unlock()     // Catch:{ Exception -> 0x02b0 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x02b0 }
            r2.unlock()     // Catch:{ Exception -> 0x02b0 }
        L_0x0289:
            r12.b()     // Catch:{ Exception -> 0x028e }
            goto L_0x0022
        L_0x028e:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x0294:
            r6 = 3
            if (r2 != r6) goto L_0x02ac
            if (r3 < 0) goto L_0x02ac
            if (r15 > r3) goto L_0x02a0
            r3 = 88888888(0x54c5638, float:9.60787E-36)
            if (r15 != r3) goto L_0x02ac
        L_0x02a0:
            r2 = -1
            r12.o(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "TbsInstaller"
            java.lang.String r6 = "TbsInstaller-installTbsCoreInThread -- update TBS....."
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r3, r6, r7)     // Catch:{ all -> 0x04ca }
        L_0x02ac:
            r7 = r2
            r8 = r5
            goto L_0x0224
        L_0x02b0:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0289
        L_0x02b5:
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            int r2 = r2 + 1
            r3.b(r2)     // Catch:{ all -> 0x04ca }
        L_0x02be:
            if (r14 != 0) goto L_0x02fa
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "install_apk_path"
            java.lang.String r2 = r2.d(r3)     // Catch:{ all -> 0x04ca }
            if (r2 != 0) goto L_0x02fb
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 202(0xca, float:2.83E-43)
            java.lang.String r4 = "apk path is null!"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -511(0xfffffffffffffe01, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x02f5 }
            r2.unlock()     // Catch:{ Exception -> 0x02f5 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x02f5 }
            r2.unlock()     // Catch:{ Exception -> 0x02f5 }
        L_0x02ea:
            r12.b()     // Catch:{ Exception -> 0x02ef }
            goto L_0x0022
        L_0x02ef:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x02f5:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x02ea
        L_0x02fa:
            r2 = r14
        L_0x02fb:
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r6.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r9 = "TbsInstaller-installTbsCoreInThread apkPath ="
            java.lang.StringBuilder r6 = r6.append(r9)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r6 = r6.append(r2)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r3, r6)     // Catch:{ all -> 0x04ca }
            int r6 = r12.c(r13, r2)     // Catch:{ all -> 0x04ca }
            if (r6 != 0) goto L_0x0347
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -512(0xfffffffffffffe00, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 203(0xcb, float:2.84E-43)
            java.lang.String r4 = "apk version is 0!"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x0342 }
            r2.unlock()     // Catch:{ Exception -> 0x0342 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x0342 }
            r2.unlock()     // Catch:{ Exception -> 0x0342 }
        L_0x0337:
            r12.b()     // Catch:{ Exception -> 0x033c }
            goto L_0x0022
        L_0x033c:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x0342:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0337
        L_0x0347:
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r9 = "install_apk_path"
            r3.a(r9, r2)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            r9 = 0
            r3.c(r6, r9)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r9 = -548(0xfffffffffffffddc, float:NaN)
            r3.setInstallInterruptCode(r9)     // Catch:{ all -> 0x04ca }
            boolean r3 = com.tencent.smtt.sdk.TbsDownloader.a(r13)     // Catch:{ all -> 0x04ca }
            if (r3 == 0) goto L_0x039a
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x04ca }
            r3.<init>(r2)     // Catch:{ all -> 0x04ca }
            r2 = 1
            boolean r2 = r12.a(r13, r3, r2)     // Catch:{ all -> 0x04ca }
            if (r2 != 0) goto L_0x03ca
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 207(0xcf, float:2.9E-43)
            java.lang.String r4 = "unzipTbsApk failed"
            com.tencent.smtt.sdk.TbsLogReport$EventType r6 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_INSTALL_DECOUPLE     // Catch:{ all -> 0x04ca }
            r2.setInstallErrorCode(r3, r4, r6)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x0395 }
            r2.unlock()     // Catch:{ Exception -> 0x0395 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x0395 }
            r2.unlock()     // Catch:{ Exception -> 0x0395 }
        L_0x038a:
            r12.b()     // Catch:{ Exception -> 0x038f }
            goto L_0x0022
        L_0x038f:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x0395:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x038a
        L_0x039a:
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x04ca }
            r3.<init>(r2)     // Catch:{ all -> 0x04ca }
            boolean r2 = r12.a(r13, r3)     // Catch:{ all -> 0x04ca }
            if (r2 != 0) goto L_0x03ca
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 207(0xcf, float:2.9E-43)
            java.lang.String r4 = "unzipTbsApk failed"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x03c5 }
            r2.unlock()     // Catch:{ Exception -> 0x03c5 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x03c5 }
            r2.unlock()     // Catch:{ Exception -> 0x03c5 }
        L_0x03ba:
            r12.b()     // Catch:{ Exception -> 0x03bf }
            goto L_0x0022
        L_0x03bf:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x03c5:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x03ba
        L_0x03ca:
            if (r8 == 0) goto L_0x043d
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "unlzma_status"
            int r2 = r2.b(r3)     // Catch:{ all -> 0x04ca }
            r3 = 5
            if (r2 <= r3) goto L_0x0434
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 223(0xdf, float:3.12E-43)
            java.lang.String r4 = "exceed unlzma retry num!"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -553(0xfffffffffffffdd7, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            r12.E(r13)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.i.c(r13)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.a     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "tbs_needdownload"
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x04ca }
            r2.put(r3, r4)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.a     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "request_full_package"
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x04ca }
            r2.put(r3, r4)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r2.commit()     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x042f }
            r2.unlock()     // Catch:{ Exception -> 0x042f }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x042f }
            r2.unlock()     // Catch:{ Exception -> 0x042f }
        L_0x0424:
            r12.b()     // Catch:{ Exception -> 0x0429 }
            goto L_0x0022
        L_0x0429:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x042f:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0424
        L_0x0434:
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            int r2 = r2 + 1
            r3.d(r2)     // Catch:{ all -> 0x04ca }
        L_0x043d:
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "unlzma begin"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance()     // Catch:{ all -> 0x04ca }
            android.content.SharedPreferences r2 = r2.mPreferences     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "tbs_responsecode"
            r9 = 0
            int r3 = r2.getInt(r3, r9)     // Catch:{ all -> 0x04ca }
            int r2 = r12.i(r13)     // Catch:{ all -> 0x04ca }
            if (r2 == 0) goto L_0x0547
            java.lang.String r2 = "can_unlzma"
            r9 = 0
            java.lang.Object r2 = com.tencent.smtt.sdk.QbSdk.a(r13, r2, r9)     // Catch:{ all -> 0x04ca }
            if (r2 == 0) goto L_0x072a
            boolean r9 = r2 instanceof java.lang.Boolean     // Catch:{ all -> 0x04ca }
            if (r9 == 0) goto L_0x072a
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x04ca }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x04ca }
        L_0x046a:
            if (r2 == 0) goto L_0x0547
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ all -> 0x04ca }
            r2.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r9 = "responseCode"
            r2.putInt(r9, r3)     // Catch:{ all -> 0x04ca }
            boolean r3 = com.tencent.smtt.sdk.TbsDownloader.a(r13)     // Catch:{ all -> 0x04ca }
            if (r3 == 0) goto L_0x04bb
            java.lang.String r3 = "unzip_temp_path"
            java.io.File r9 = r12.p(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ all -> 0x04ca }
            r2.putString(r3, r9)     // Catch:{ all -> 0x04ca }
        L_0x0489:
            java.lang.String r3 = "unlzma"
            java.lang.Object r2 = com.tencent.smtt.sdk.QbSdk.a(r13, r3, r2)     // Catch:{ all -> 0x04ca }
            if (r2 != 0) goto L_0x04e2
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "unlzma return null"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 222(0xde, float:3.11E-43)
            java.lang.String r9 = "unlzma is null"
            r2.setInstallErrorCode(r3, r9)     // Catch:{ all -> 0x04ca }
            r2 = r5
        L_0x04a4:
            if (r2 != 0) goto L_0x0547
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x0541 }
            r2.unlock()     // Catch:{ Exception -> 0x0541 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x0541 }
            r2.unlock()     // Catch:{ Exception -> 0x0541 }
        L_0x04b0:
            r12.b()     // Catch:{ Exception -> 0x04b5 }
            goto L_0x0022
        L_0x04b5:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x04bb:
            java.lang.String r3 = "unzip_temp_path"
            r9 = 0
            java.io.File r9 = r12.f(r13, r9)     // Catch:{ all -> 0x04ca }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ all -> 0x04ca }
            r2.putString(r3, r9)     // Catch:{ all -> 0x04ca }
            goto L_0x0489
        L_0x04ca:
            r2 = move-exception
        L_0x04cb:
            java.util.concurrent.locks.ReentrantLock r3 = i     // Catch:{ Exception -> 0x070d }
            r3.unlock()     // Catch:{ Exception -> 0x070d }
            java.util.concurrent.locks.Lock r3 = j     // Catch:{ Exception -> 0x070d }
            r3.unlock()     // Catch:{ Exception -> 0x070d }
        L_0x04d5:
            r12.b()     // Catch:{ Exception -> 0x0713 }
        L_0x04d8:
            if (r5 == 0) goto L_0x04e1
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m
            r4 = 232(0xe8, float:3.25E-43)
            r3.onInstallFinish(r4)
        L_0x04e1:
            throw r2
        L_0x04e2:
            boolean r3 = r2 instanceof java.lang.Boolean     // Catch:{ all -> 0x04ca }
            if (r3 == 0) goto L_0x050b
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x04ca }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x04ca }
            if (r2 == 0) goto L_0x04f7
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "unlzma success"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            r2 = r4
            goto L_0x04a4
        L_0x04f7:
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "unlzma return false"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 222(0xde, float:3.11E-43)
            java.lang.String r9 = "unlzma return false"
            r2.setInstallErrorCode(r3, r9)     // Catch:{ all -> 0x04ca }
        L_0x0509:
            r2 = r5
            goto L_0x04a4
        L_0x050b:
            boolean r3 = r2 instanceof android.os.Bundle     // Catch:{ all -> 0x04ca }
            if (r3 == 0) goto L_0x0511
            r2 = r4
            goto L_0x04a4
        L_0x0511:
            boolean r3 = r2 instanceof java.lang.Throwable     // Catch:{ all -> 0x04ca }
            if (r3 == 0) goto L_0x0509
            java.lang.String r9 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r3.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r10 = "unlzma failure because Throwable"
            java.lang.StringBuilder r10 = r3.append(r10)     // Catch:{ all -> 0x04ca }
            r0 = r2
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x04ca }
            r3 = r0
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r3 = r10.append(r3)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r9, r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r9 = 222(0xde, float:3.11E-43)
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x04ca }
            r3.setInstallErrorCode(r9, r2)     // Catch:{ all -> 0x04ca }
            goto L_0x0509
        L_0x0541:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x04b0
        L_0x0547:
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "unlzma finished"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            r3 = 1
            r2.c(r6, r3)     // Catch:{ all -> 0x04ca }
            r2 = r6
        L_0x0557:
            if (r7 >= r11) goto L_0x06f5
            if (r8 == 0) goto L_0x05f5
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "dexopt_retry_num"
            int r3 = r3.c(r6)     // Catch:{ all -> 0x04ca }
            r6 = 10
            if (r3 <= r6) goto L_0x05ec
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 208(0xd0, float:2.91E-43)
            java.lang.String r4 = "exceed dexopt retry num!"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -514(0xfffffffffffffdfe, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            r12.E(r13)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x05e7 }
            r2.unlock()     // Catch:{ Exception -> 0x05e7 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x05e7 }
            r2.unlock()     // Catch:{ Exception -> 0x05e7 }
        L_0x058a:
            r12.b()     // Catch:{ Exception -> 0x058f }
            goto L_0x0022
        L_0x058f:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x0595:
            boolean r2 = com.tencent.smtt.sdk.TbsDownloader.a(r13)     // Catch:{ all -> 0x04ca }
            if (r2 == 0) goto L_0x05e4
            if (r14 != 0) goto L_0x05d7
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = "install_apk_path"
            java.lang.String r2 = r2.d(r3)     // Catch:{ all -> 0x04ca }
            if (r2 != 0) goto L_0x05d8
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = 202(0xca, float:2.83E-43)
            java.lang.String r4 = "apk path is null!"
            r2.setInstallErrorCode(r3, r4)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -511(0xfffffffffffffe01, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x05d2 }
            r2.unlock()     // Catch:{ Exception -> 0x05d2 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x05d2 }
            r2.unlock()     // Catch:{ Exception -> 0x05d2 }
        L_0x05c7:
            r12.b()     // Catch:{ Exception -> 0x05cc }
            goto L_0x0022
        L_0x05cc:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x05d2:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x05c7
        L_0x05d7:
            r2 = r14
        L_0x05d8:
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x04ca }
            r3.<init>(r2)     // Catch:{ all -> 0x04ca }
            r2 = 1
            boolean r2 = r12.a(r13, r3, r2)     // Catch:{ all -> 0x04ca }
            if (r2 != 0) goto L_0x05e4
        L_0x05e4:
            r2 = r5
            goto L_0x0557
        L_0x05e7:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x058a
        L_0x05ec:
            com.tencent.smtt.sdk.j r6 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            int r3 = r3 + 1
            r6.a(r3)     // Catch:{ all -> 0x04ca }
        L_0x05f5:
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r6 = -549(0xfffffffffffffddb, float:NaN)
            r3.setInstallInterruptCode(r6)     // Catch:{ all -> 0x04ca }
            r3 = 0
            boolean r3 = r12.j(r13, r3)     // Catch:{ all -> 0x04ca }
            if (r3 != 0) goto L_0x0628
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -515(0xfffffffffffffdfd, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x0623 }
            r2.unlock()     // Catch:{ Exception -> 0x0623 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x0623 }
            r2.unlock()     // Catch:{ Exception -> 0x0623 }
        L_0x0618:
            r12.b()     // Catch:{ Exception -> 0x061d }
            goto L_0x0022
        L_0x061d:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0022
        L_0x0623:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0618
        L_0x0628:
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r13)     // Catch:{ all -> 0x04ca }
            r6 = 2
            r3.c(r2, r6)     // Catch:{ all -> 0x04ca }
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "STEP 2/2 installation completed! you can restart!"
            r6 = 1
            com.tencent.smtt.utils.TbsLog.i(r2, r3, r6)     // Catch:{ all -> 0x04ca }
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r3.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "STEP 2/2 installation completed! you can restart! version:"
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r3 = r3.append(r15)     // Catch:{ all -> 0x04ca }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -516(0xfffffffffffffdfc, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x04ca }
            r3 = 11
            if (r2 < r3) goto L_0x06b8
            java.lang.String r2 = "tbs_preloadx5_check_cfg_file"
            r3 = 4
            android.content.SharedPreferences r2 = r13.getSharedPreferences(r2, r3)     // Catch:{ all -> 0x04ca }
        L_0x0666:
            android.content.SharedPreferences$Editor r2 = r2.edit()     // Catch:{ Throwable -> 0x06c0 }
            java.lang.String r3 = "tbs_preload_x5_counter"
            r6 = 0
            r2.putInt(r3, r6)     // Catch:{ Throwable -> 0x06c0 }
            java.lang.String r3 = "tbs_preload_x5_recorder"
            r6 = 0
            r2.putInt(r3, r6)     // Catch:{ Throwable -> 0x06c0 }
            java.lang.String r3 = "tbs_preload_x5_version"
            r2.putInt(r3, r15)     // Catch:{ Throwable -> 0x06c0 }
            r2.commit()     // Catch:{ Throwable -> 0x06c0 }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Throwable -> 0x06c0 }
            r3 = -517(0xfffffffffffffdfb, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ Throwable -> 0x06c0 }
        L_0x0687:
            r2 = 88888888(0x54c5638, float:9.60787E-36)
            if (r15 != r2) goto L_0x068f
            r12.a(r15, r14, r13)     // Catch:{ all -> 0x04ca }
        L_0x068f:
            boolean r2 = r12.k     // Catch:{ all -> 0x04ca }
            if (r2 == 0) goto L_0x06e7
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            int r3 = r12.u(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "continueInstallWithout core success"
            r2.setInstallErrorCode(r3, r6)     // Catch:{ all -> 0x04ca }
        L_0x06a0:
            java.util.concurrent.locks.ReentrantLock r2 = i     // Catch:{ Exception -> 0x0703 }
            r2.unlock()     // Catch:{ Exception -> 0x0703 }
            java.util.concurrent.locks.Lock r2 = j     // Catch:{ Exception -> 0x0703 }
            r2.unlock()     // Catch:{ Exception -> 0x0703 }
        L_0x06aa:
            r12.b()     // Catch:{ Exception -> 0x0708 }
        L_0x06ad:
            if (r4 == 0) goto L_0x0022
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r3 = 232(0xe8, float:3.25E-43)
            r2.onInstallFinish(r3)
            goto L_0x0022
        L_0x06b8:
            java.lang.String r2 = "tbs_preloadx5_check_cfg_file"
            r3 = 0
            android.content.SharedPreferences r2 = r13.getSharedPreferences(r2, r3)     // Catch:{ all -> 0x04ca }
            goto L_0x0666
        L_0x06c0:
            r2 = move-exception
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ca }
            r6.<init>()     // Catch:{ all -> 0x04ca }
            java.lang.String r7 = "Init tbs_preload_x5_counter#1 exception:"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x04ca }
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)     // Catch:{ all -> 0x04ca }
            java.lang.StringBuilder r2 = r6.append(r2)     // Catch:{ all -> 0x04ca }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.utils.TbsLog.e(r3, r2)     // Catch:{ all -> 0x04ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x04ca }
            r3 = -518(0xfffffffffffffdfa, float:NaN)
            r2.setInstallInterruptCode(r3)     // Catch:{ all -> 0x04ca }
            goto L_0x0687
        L_0x06e7:
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r13)     // Catch:{ all -> 0x04ca }
            int r3 = r12.u(r13)     // Catch:{ all -> 0x04ca }
            java.lang.String r6 = "success"
            r2.setInstallErrorCode(r3, r6)     // Catch:{ all -> 0x04ca }
            goto L_0x06a0
        L_0x06f5:
            if (r7 != r11) goto L_0x0727
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ all -> 0x06ff }
            r3 = 200(0xc8, float:2.8E-43)
            r2.onInstallFinish(r3)     // Catch:{ all -> 0x06ff }
            goto L_0x06a0
        L_0x06ff:
            r2 = move-exception
            r5 = r4
            goto L_0x04cb
        L_0x0703:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x06aa
        L_0x0708:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x06ad
        L_0x070d:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x04d5
        L_0x0713:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x04d8
        L_0x0719:
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r3 = -519(0xfffffffffffffdf9, float:NaN)
            r2.setInstallInterruptCode(r3)
            r12.b()
            goto L_0x0022
        L_0x0727:
            r4 = r5
            goto L_0x06a0
        L_0x072a:
            r2 = r5
            goto L_0x046a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.b(android.content.Context, java.lang.String, int):void");
    }

    private int u(Context context) {
        boolean z = true;
        if (j.a(context).d() != 1) {
            z = false;
        }
        boolean a2 = TbsDownloader.a(context);
        if (z) {
            if (a2) {
                return ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS;
            }
            return ErrorCode.INCRUPDATE_INSTALL_SUCCESS;
        } else if (a2) {
            return ErrorCode.DECOUPLE_INSTLL_SUCCESS;
        } else {
            return 200;
        }
    }

    public void b(Context context) {
        g(context, true);
        j.a(context).c(h(context), 2);
    }

    public void a(Context context, int i2) {
        g(context, true);
        j.a(context).c(i2, 2);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009d, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a7, code lost:
        r1 = r0;
        r0 = false;
        r2 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0098 A[SYNTHETIC, Splitter:B:37:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0027] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(android.content.Context r11) {
        /*
            r10 = this;
            r1 = 1
            r2 = 0
            java.io.File r0 = r10.q(r11)
            java.io.File r5 = new java.io.File
            java.lang.String r3 = "tbs.conf"
            r5.<init>(r0, r3)
            if (r5 == 0) goto L_0x0015
            boolean r0 = r5.exists()
            if (r0 != 0) goto L_0x0017
        L_0x0015:
            r0 = r2
        L_0x0016:
            return r0
        L_0x0017:
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r4 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0082, all -> 0x0094 }
            r6.<init>(r5)     // Catch:{ Throwable -> 0x0082, all -> 0x0094 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0082, all -> 0x0094 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x0082, all -> 0x0094 }
            r0.load(r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
            java.lang.String r4 = "tbs_local_installation"
            java.lang.String r6 = "false"
            java.lang.String r0 = r0.getProperty(r4, r6)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
            boolean r4 = r0.booleanValue()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
            if (r4 == 0) goto L_0x00b0
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            long r8 = r5.lastModified()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            long r6 = r6 - r8
            r8 = 259200000(0xf731400, double:1.280618154E-315)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x007e
            r0 = r1
        L_0x004d:
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            r6.<init>()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            java.lang.String r7 = "TBS_LOCAL_INSTALLATION is:"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            java.lang.StringBuilder r6 = r6.append(r4)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            java.lang.String r7 = " expired="
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
            if (r0 != 0) goto L_0x0080
        L_0x0071:
            r0 = r4 & r1
            if (r3 == 0) goto L_0x0016
            r3.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0016
        L_0x0079:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x007e:
            r0 = r2
            goto L_0x004d
        L_0x0080:
            r1 = r2
            goto L_0x0071
        L_0x0082:
            r0 = move-exception
            r1 = r0
            r0 = r2
            r2 = r4
        L_0x0086:
            r1.printStackTrace()     // Catch:{ all -> 0x00a3 }
            if (r2 == 0) goto L_0x0016
            r2.close()     // Catch:{ IOException -> 0x008f }
            goto L_0x0016
        L_0x008f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x0094:
            r0 = move-exception
            r3 = r4
        L_0x0096:
            if (r3 == 0) goto L_0x009b
            r3.close()     // Catch:{ IOException -> 0x009c }
        L_0x009b:
            throw r0
        L_0x009c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x009b
        L_0x00a1:
            r0 = move-exception
            goto L_0x0096
        L_0x00a3:
            r0 = move-exception
            r3 = r2
            goto L_0x0096
        L_0x00a6:
            r0 = move-exception
            r1 = r0
            r0 = r2
            r2 = r3
            goto L_0x0086
        L_0x00ab:
            r0 = move-exception
            r1 = r0
            r2 = r3
            r0 = r4
            goto L_0x0086
        L_0x00b0:
            r0 = r2
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.c(android.content.Context):boolean");
    }

    private static boolean v(Context context) {
        if (context == null) {
            TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #1");
            return true;
        }
        try {
            if (new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf").exists()) {
                TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #2");
                return true;
            }
            TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #3");
            return false;
        } catch (Exception e2) {
            TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #4");
            return true;
        }
    }

    private void g(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf");
            if (!z) {
                f.b(file);
            } else if (file == null || !file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e2) {
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead Exception message is " + e2.getMessage() + " Exception cause is " + e2.getCause());
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A[SYNTHETIC, Splitter:B:19:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047 A[SYNTHETIC, Splitter:B:22:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0051 A[SYNTHETIC, Splitter:B:28:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0056 A[SYNTHETIC, Splitter:B:31:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(android.content.Context r8) {
        /*
            r7 = this;
            r1 = 0
            java.io.File r0 = r7.q(r8)     // Catch:{ Throwable -> 0x005a }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x005a }
            java.lang.String r2 = "tbs.conf"
            r3.<init>(r0, r2)     // Catch:{ Throwable -> 0x005a }
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Throwable -> 0x005a }
            r4.<init>()     // Catch:{ Throwable -> 0x005a }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x003e, all -> 0x004d }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x003e, all -> 0x004d }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x003e, all -> 0x004d }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x003e, all -> 0x004d }
            r4.load(r2)     // Catch:{ Throwable -> 0x006d, all -> 0x0066 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x006d, all -> 0x0066 }
            r5.<init>(r3)     // Catch:{ Throwable -> 0x006d, all -> 0x0066 }
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x006d, all -> 0x0066 }
            r0.<init>(r5)     // Catch:{ Throwable -> 0x006d, all -> 0x0066 }
            java.lang.String r1 = "tbs_local_installation"
            java.lang.String r3 = "false"
            r4.setProperty(r1, r3)     // Catch:{ Throwable -> 0x0071, all -> 0x0068 }
            r1 = 0
            r4.store(r0, r1)     // Catch:{ Throwable -> 0x0071, all -> 0x0068 }
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch:{ IOException -> 0x005c }
        L_0x0038:
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch:{ IOException -> 0x005e }
        L_0x003d:
            return
        L_0x003e:
            r0 = move-exception
            r0 = r1
        L_0x0040:
            if (r0 == 0) goto L_0x0045
            r0.close()     // Catch:{ IOException -> 0x0060 }
        L_0x0045:
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x003d
        L_0x004b:
            r0 = move-exception
            goto L_0x003d
        L_0x004d:
            r0 = move-exception
            r2 = r1
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0054:
            if (r2 == 0) goto L_0x0059
            r2.close()     // Catch:{ IOException -> 0x0064 }
        L_0x0059:
            throw r0     // Catch:{ Throwable -> 0x005a }
        L_0x005a:
            r0 = move-exception
            goto L_0x003d
        L_0x005c:
            r0 = move-exception
            goto L_0x0038
        L_0x005e:
            r0 = move-exception
            goto L_0x003d
        L_0x0060:
            r0 = move-exception
            goto L_0x0045
        L_0x0062:
            r1 = move-exception
            goto L_0x0054
        L_0x0064:
            r1 = move-exception
            goto L_0x0059
        L_0x0066:
            r0 = move-exception
            goto L_0x004f
        L_0x0068:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x004f
        L_0x006d:
            r0 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x0040
        L_0x0071:
            r1 = move-exception
            r1 = r2
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.d(android.content.Context):void");
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0090 A[SYNTHETIC, Splitter:B:27:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0095 A[SYNTHETIC, Splitter:B:30:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a7 A[SYNTHETIC, Splitter:B:39:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ac A[SYNTHETIC, Splitter:B:42:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r7, java.lang.String r8, android.content.Context r9) {
        /*
            r6 = this;
            r4 = 1
            r2 = 0
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            r0.delete()
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Local tbs apk("
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r3 = ") is deleted!"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1, r4)
            java.lang.String r0 = "tbs"
            r1 = 0
            java.io.File r0 = r9.getDir(r0, r1)
            java.io.File r1 = new java.io.File
            java.lang.String r3 = "core_unzip_tmp"
            r1.<init>(r0, r3)
            if (r1 == 0) goto L_0x007e
            boolean r0 = r1.canRead()
            if (r0 == 0) goto L_0x007e
            java.io.File r0 = new java.io.File
            java.lang.String r3 = "tbs.conf"
            r0.<init>(r1, r3)
            java.util.Properties r4 = new java.util.Properties
            r4.<init>()
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0089, all -> 0x00a3 }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0089, all -> 0x00a3 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0089, all -> 0x00a3 }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x0089, all -> 0x00a3 }
            r4.load(r3)     // Catch:{ Throwable -> 0x00c3, all -> 0x00ba }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00c3, all -> 0x00ba }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x00c3, all -> 0x00ba }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x00c3, all -> 0x00ba }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x00c3, all -> 0x00ba }
            java.lang.String r0 = "tbs_local_installation"
            java.lang.String r2 = "true"
            r4.setProperty(r0, r2)     // Catch:{ Throwable -> 0x00c7, all -> 0x00bc }
            r0 = 0
            r4.store(r1, r0)     // Catch:{ Throwable -> 0x00c7, all -> 0x00bc }
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "TBS_LOCAL_INSTALLATION is set!"
            r4 = 1
            com.tencent.smtt.utils.TbsLog.i(r0, r2, r4)     // Catch:{ Throwable -> 0x00c7, all -> 0x00bc }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ IOException -> 0x007f }
        L_0x0079:
            if (r3 == 0) goto L_0x007e
            r3.close()     // Catch:{ IOException -> 0x0084 }
        L_0x007e:
            return
        L_0x007f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0079
        L_0x0084:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x007e
        L_0x0089:
            r0 = move-exception
            r1 = r2
        L_0x008b:
            r0.printStackTrace()     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x0093
            r1.close()     // Catch:{ IOException -> 0x009e }
        L_0x0093:
            if (r2 == 0) goto L_0x007e
            r2.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x007e
        L_0x0099:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x007e
        L_0x009e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0093
        L_0x00a3:
            r0 = move-exception
            r3 = r2
        L_0x00a5:
            if (r2 == 0) goto L_0x00aa
            r2.close()     // Catch:{ IOException -> 0x00b0 }
        L_0x00aa:
            if (r3 == 0) goto L_0x00af
            r3.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x00af:
            throw r0
        L_0x00b0:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00aa
        L_0x00b5:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00af
        L_0x00ba:
            r0 = move-exception
            goto L_0x00a5
        L_0x00bc:
            r0 = move-exception
            r2 = r1
            goto L_0x00a5
        L_0x00bf:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x00a5
        L_0x00c3:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x008b
        L_0x00c7:
            r0 = move-exception
            r2 = r3
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(int, java.lang.String, android.content.Context):void");
    }

    /* access modifiers changed from: 0000 */
    public boolean b(Context context, int i2) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context d2 = d(context, i2);
        if (d2 != null) {
            Object[] objArr = {d2, context, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 2;
            message.obj = objArr;
            m.sendMessage(message);
            return true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Object[] objArr = {context, bundle};
            Message message = new Message();
            message.what = 3;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, File file, int i2) {
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmp,ctx=" + context + "File=" + file + "coreVersion=" + i2);
        if (file != null && context != null) {
            Object[] objArr = {context, file, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 4;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0362  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0429  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x04a1  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x04a6  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r14, android.os.Bundle r15) {
        /*
            r13 = this;
            r2 = 2
            r11 = -1
            r10 = 5
            r1 = 1
            r5 = 0
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller installLocalTbsCoreExInThreadthread "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            java.lang.String r4 = r4.getName()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.Throwable r4 = new java.lang.Throwable
            r4.<init>()
            java.lang.String r4 = android.util.Log.getStackTraceString(r4)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r3)
            boolean r0 = r13.c(r14)
            if (r0 == 0) goto L_0x0042
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r1 = -539(0xfffffffffffffde5, float:NaN)
            r0.setInstallInterruptCode(r1)
        L_0x0041:
            return
        L_0x0042:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r3 = "TbsInstaller-installLocalTesCoreExInThread"
            com.tencent.smtt.utils.TbsLog.i(r0, r3)
            if (r15 == 0) goto L_0x0041
            if (r14 == 0) goto L_0x0041
            boolean r0 = com.tencent.smtt.utils.f.b(r14)
            if (r0 != 0) goto L_0x008f
            long r0 = com.tencent.smtt.utils.q.a()
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            long r2 = r2.getDownloadMinFreeSpace()
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r5 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "rom is not enough when patching tbs core! curAvailROM="
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r0 = r6.append(r0)
            java.lang.String r1 = ",minReqRom="
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r4.setInstallErrorCode(r5, r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r1 = -540(0xfffffffffffffde4, float:NaN)
            r0.setInstallInterruptCode(r1)
            goto L_0x0041
        L_0x008f:
            boolean r0 = r13.t(r14)
            if (r0 != 0) goto L_0x009f
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r1 = -541(0xfffffffffffffde3, float:NaN)
            r0.setInstallInterruptCode(r1)
            goto L_0x0041
        L_0x009f:
            java.util.concurrent.locks.Lock r0 = j
            boolean r0 = r0.tryLock()
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "TbsInstaller-installLocalTesCoreExInThread locked="
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r4)
            if (r0 == 0) goto L_0x0567
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r3 = "tbs_responsecode"
            int r6 = r0.getInt(r3, r5)
            r0 = 0
            r3 = 1
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r6 != r10) goto L_0x01be
            int r4 = r13.c(r14, r15)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r4 != r1) goto L_0x00ed
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x059a, all -> 0x0575 }
            java.lang.String r7 = "tpatch_num"
            int r3 = r3.c(r7)     // Catch:{ Exception -> 0x059a, all -> 0x0575 }
            com.tencent.smtt.sdk.j r7 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x059a, all -> 0x0575 }
            java.lang.String r8 = "tpatch_num"
            int r3 = r3 + 1
            r7.a(r8, r3)     // Catch:{ Exception -> 0x059a, all -> 0x0575 }
        L_0x00ed:
            java.util.concurrent.locks.Lock r3 = j
            r3.unlock()
            r13.b()
            if (r6 != r10) goto L_0x00fc
            r13.h(r14, r4)
            goto L_0x0041
        L_0x00fc:
            if (r4 != 0) goto L_0x014e
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)
            java.lang.String r3 = "incrupdate_retry_num"
            r2.a(r3, r5)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r3 = -544(0xfffffffffffffde0, float:NaN)
            r2.setInstallInterruptCode(r3)
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)
            r2.c(r5, r11)
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)
            r2.c(r1)
            java.lang.String r1 = "apk_path"
            java.lang.String r1 = r0.getString(r1)
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            com.tencent.smtt.sdk.i.a(r2, r14)
            java.lang.String r2 = "tbs_core_ver"
            int r0 = r0.getInt(r2)
            r13.b(r14, r1, r0)
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r0 == 0) goto L_0x0149
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)
            r0.c(r11)
        L_0x0149:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5)
            goto L_0x0041
        L_0x014e:
            if (r4 != r2) goto L_0x0158
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            goto L_0x0149
        L_0x0158:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r2 = -546(0xfffffffffffffdde, float:NaN)
            r0.setInstallInterruptCode(r2)
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.a
            java.lang.String r2 = "tbs_needdownload"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r0.put(r2, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r0.commit()
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r0 == 0) goto L_0x01a1
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r1 = 235(0xeb, float:3.3E-43)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "decouple incrUpdate fail! patch ret="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.setInstallErrorCode(r1, r2)
            goto L_0x0149
        L_0x01a1:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r1 = 217(0xd9, float:3.04E-43)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "incrUpdate fail! patch ret="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.setInstallErrorCode(r1, r2)
            goto L_0x0149
        L_0x01be:
            int r3 = r13.i(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 <= 0) goto L_0x01ce
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            int r3 = r3.d()     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 != r1) goto L_0x01ed
        L_0x01ce:
            r3 = 0
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r13.b()
            if (r6 != r10) goto L_0x01e1
            r13.h(r14, r2)
            goto L_0x0041
        L_0x01e1:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5)
            goto L_0x0041
        L_0x01ed:
            if (r6 == r1) goto L_0x01f4
            if (r6 == r2) goto L_0x01f4
            r3 = 4
            if (r6 != r3) goto L_0x0279
        L_0x01f4:
            r3 = r1
        L_0x01f5:
            if (r3 != 0) goto L_0x05ab
            if (r6 == 0) goto L_0x05ab
            com.tencent.smtt.sdk.j r3 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r4 = "incrupdate_retry_num"
            int r3 = r3.c(r4)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 <= r10) goto L_0x0288
            java.lang.String r3 = "TbsInstaller"
            java.lang.String r4 = "TbsInstaller-installLocalTesCoreExInThread exceed incrupdate num"
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r3 = "old_apk_location"
            java.lang.String r3 = r15.getString(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r4 = "new_apk_location"
            java.lang.String r4 = r15.getString(r4)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r7 = "diff_file_location"
            java.lang.String r7 = r15.getString(r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            boolean r8 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r8 != 0) goto L_0x022c
            java.io.File r8 = new java.io.File     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r8.<init>(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            com.tencent.smtt.utils.f.b(r8)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
        L_0x022c:
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 != 0) goto L_0x023a
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            com.tencent.smtt.utils.f.b(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
        L_0x023a:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 != 0) goto L_0x0248
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r3.<init>(r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            com.tencent.smtt.utils.f.b(r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
        L_0x0248:
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.util.Map<java.lang.String, java.lang.Object> r3 = r3.a     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r4 = "tbs_needdownload"
            r7 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r3.put(r4, r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r3.commit()     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r4 = 224(0xe0, float:3.14E-43)
            java.lang.String r7 = "incrUpdate exceed retry max num"
            r3.setInstallErrorCode(r4, r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r13.b()
            if (r6 != r10) goto L_0x027c
            r13.h(r14, r2)
            goto L_0x0041
        L_0x0279:
            r3 = r5
            goto L_0x01f5
        L_0x027c:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5)
            goto L_0x0041
        L_0x0288:
            com.tencent.smtt.sdk.j r4 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r7 = "incrupdate_retry_num"
            int r3 = r3 + 1
            r4.a(r7, r3)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.io.File r3 = s(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 == 0) goto L_0x05ab
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            java.lang.String r7 = "x5.tbs"
            r4.<init>(r3, r7)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r4 == 0) goto L_0x05ab
            boolean r3 = r4.exists()     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 == 0) goto L_0x05ab
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            r4 = -550(0xfffffffffffffdda, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            android.os.Bundle r3 = com.tencent.smtt.sdk.QbSdk.a(r14, r15)     // Catch:{ Exception -> 0x0594, all -> 0x0493 }
            if (r3 != 0) goto L_0x02e9
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            r4 = 228(0xe4, float:3.2E-43)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            r7.<init>()     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            java.lang.String r8 = "result null : "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            java.lang.String r8 = "new_core_ver"
            int r8 = r15.getInt(r8)     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            r0.setInstallErrorCode(r4, r7)     // Catch:{ Exception -> 0x05a1, all -> 0x057c }
            r0 = r1
        L_0x02da:
            java.util.concurrent.locks.Lock r4 = j
            r4.unlock()
            r13.b()
            if (r6 != r10) goto L_0x0367
            r13.h(r14, r0)
            goto L_0x0041
        L_0x02e9:
            java.lang.String r0 = "patch_result"
            int r0 = r3.getInt(r0)     // Catch:{ Exception -> 0x05a6, all -> 0x0581 }
            if (r0 == 0) goto L_0x02da
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            r7 = 228(0xe4, float:3.2E-43)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            r8.<init>()     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            java.lang.String r9 = "result "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            java.lang.String r9 = " : "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            java.lang.String r9 = "new_core_ver"
            int r9 = r15.getInt(r9)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            r4.setInstallErrorCode(r7, r8)     // Catch:{ Exception -> 0x031e, all -> 0x0586 }
            goto L_0x02da
        L_0x031e:
            r4 = move-exception
            r12 = r4
            r4 = r3
            r3 = r0
            r0 = r12
        L_0x0323:
            java.lang.String r7 = "TbsInstaller"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x058d }
            r8.<init>()     // Catch:{ all -> 0x058d }
            java.lang.String r9 = "installLocalTbsCoreExInThread exception:"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x058d }
            java.lang.String r9 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x058d }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x058d }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x058d }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ all -> 0x058d }
            r0.printStackTrace()     // Catch:{ all -> 0x058d }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ all -> 0x0590 }
            r7 = -543(0xfffffffffffffde1, float:NaN)
            r3.setInstallInterruptCode(r7)     // Catch:{ all -> 0x0590 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ all -> 0x0590 }
            r7 = 218(0xda, float:3.05E-43)
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0590 }
            r3.setInstallErrorCode(r7, r0)     // Catch:{ all -> 0x0590 }
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r13.b()
            if (r6 != r10) goto L_0x0429
            r13.h(r14, r1)
            goto L_0x0041
        L_0x0367:
            if (r0 != 0) goto L_0x03b9
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)
            java.lang.String r2 = "incrupdate_retry_num"
            r0.a(r2, r5)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r2 = -544(0xfffffffffffffde0, float:NaN)
            r0.setInstallInterruptCode(r2)
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)
            r0.c(r5, r11)
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)
            r0.c(r1)
            java.lang.String r0 = "apk_path"
            java.lang.String r0 = r3.getString(r0)
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            com.tencent.smtt.sdk.i.a(r1, r14)
            java.lang.String r1 = "tbs_core_ver"
            int r1 = r3.getInt(r1)
            r13.b(r14, r0, r1)
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r0 == 0) goto L_0x03b4
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)
            r0.c(r11)
        L_0x03b4:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5)
            goto L_0x0041
        L_0x03b9:
            if (r0 != r2) goto L_0x03c3
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            goto L_0x03b4
        L_0x03c3:
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r3 = -546(0xfffffffffffffdde, float:NaN)
            r2.setInstallInterruptCode(r3)
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.a
            java.lang.String r3 = "tbs_needdownload"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r2.put(r3, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r1.commit()
            boolean r1 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r1 == 0) goto L_0x040c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r2 = 235(0xeb, float:3.3E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "decouple incrUpdate fail! patch ret="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            r1.setInstallErrorCode(r2, r0)
            goto L_0x03b4
        L_0x040c:
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r2 = 217(0xd9, float:3.04E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "incrUpdate fail! patch ret="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            r1.setInstallErrorCode(r2, r0)
            goto L_0x03b4
        L_0x0429:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r2 = -546(0xfffffffffffffdde, float:NaN)
            r0.setInstallInterruptCode(r2)
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.a
            java.lang.String r2 = "tbs_needdownload"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            r0.put(r2, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r0.commit()
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r0 == 0) goto L_0x0476
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r2 = 235(0xeb, float:3.3E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "decouple incrUpdate fail! patch ret="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r0.setInstallErrorCode(r2, r1)
        L_0x0471:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5)
            goto L_0x0041
        L_0x0476:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r2 = 217(0xd9, float:3.04E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "incrUpdate fail! patch ret="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r0.setInstallErrorCode(r2, r1)
            goto L_0x0471
        L_0x0493:
            r3 = move-exception
            r4 = r0
            r0 = r3
            r3 = r2
        L_0x0497:
            java.util.concurrent.locks.Lock r7 = j
            r7.unlock()
            r13.b()
            if (r6 != r10) goto L_0x04a6
            r13.h(r14, r3)
            goto L_0x0041
        L_0x04a6:
            if (r3 != 0) goto L_0x04f7
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)
            java.lang.String r3 = "incrupdate_retry_num"
            r2.a(r3, r5)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r3 = -544(0xfffffffffffffde0, float:NaN)
            r2.setInstallInterruptCode(r3)
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)
            r2.c(r5, r11)
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)
            r2.c(r1)
            java.lang.String r1 = "apk_path"
            java.lang.String r1 = r4.getString(r1)
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            com.tencent.smtt.sdk.i.a(r2, r14)
            java.lang.String r2 = "tbs_core_ver"
            int r2 = r4.getInt(r2)
            r13.b(r14, r1, r2)
            boolean r1 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r1 == 0) goto L_0x04f3
            com.tencent.smtt.sdk.j r1 = com.tencent.smtt.sdk.j.a(r14)
            r1.c(r11)
        L_0x04f3:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5)
            throw r0
        L_0x04f7:
            if (r3 != r2) goto L_0x0501
            java.lang.String r1 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            goto L_0x04f3
        L_0x0501:
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r4 = -546(0xfffffffffffffdde, float:NaN)
            r2.setInstallInterruptCode(r4)
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r4 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r2, r4)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.a
            java.lang.String r4 = "tbs_needdownload"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r2.put(r4, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r1.commit()
            boolean r1 = com.tencent.smtt.sdk.TbsDownloader.a(r14)
            if (r1 == 0) goto L_0x054a
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r2 = 235(0xeb, float:3.3E-43)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "decouple incrUpdate fail! patch ret="
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r1.setInstallErrorCode(r2, r3)
            goto L_0x04f3
        L_0x054a:
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)
            r2 = 217(0xd9, float:3.04E-43)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "incrUpdate fail! patch ret="
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r1.setInstallErrorCode(r2, r3)
            goto L_0x04f3
        L_0x0567:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r1 = -547(0xfffffffffffffddd, float:NaN)
            r0.setInstallInterruptCode(r1)
            r13.b()
            goto L_0x0041
        L_0x0575:
            r3 = move-exception
            r12 = r3
            r3 = r4
            r4 = r0
            r0 = r12
            goto L_0x0497
        L_0x057c:
            r0 = move-exception
            r4 = r3
            r3 = r1
            goto L_0x0497
        L_0x0581:
            r0 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x0497
        L_0x0586:
            r4 = move-exception
            r12 = r4
            r4 = r3
            r3 = r0
            r0 = r12
            goto L_0x0497
        L_0x058d:
            r0 = move-exception
            goto L_0x0497
        L_0x0590:
            r0 = move-exception
            r3 = r1
            goto L_0x0497
        L_0x0594:
            r3 = move-exception
            r4 = r0
            r0 = r3
            r3 = r2
            goto L_0x0323
        L_0x059a:
            r3 = move-exception
            r12 = r3
            r3 = r4
            r4 = r0
            r0 = r12
            goto L_0x0323
        L_0x05a1:
            r0 = move-exception
            r4 = r3
            r3 = r1
            goto L_0x0323
        L_0x05a6:
            r0 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x0323
        L_0x05ab:
            r3 = r0
            r0 = r2
            goto L_0x02da
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.b(android.content.Context, android.os.Bundle):void");
    }

    private void h(Context context, int i2) {
        TbsLog.i("TbsInstaller", "proceedTpatchStatus,result=" + i2);
        switch (i2) {
            case 0:
                if (!TbsDownloader.a(context)) {
                    g(context, true);
                    j.a(context).b(TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0), 1);
                    break;
                } else {
                    i(context, 6);
                    break;
                }
        }
        QbSdk.setTBSInstallingStatus(false);
    }

    private int c(Context context, Bundle bundle) {
        try {
            Bundle a2 = QbSdk.a(context, bundle);
            TbsLog.i("TbsInstaller", "tpatch finished,ret is" + a2);
            int i2 = a2.getInt("patch_result");
            if (i2 == 0) {
                String string = bundle.getString("new_apk_location");
                int i3 = bundle.getInt("new_core_ver");
                int a3 = a(new File(string));
                if (i3 != a3) {
                    TbsLog.i("TbsInstaller", "version not equals!!!" + i3 + "patchVersion:" + a3);
                    TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.TPATCH_VERSION_FAILED, "version=" + i3 + ",patchVersion=" + a3);
                    return 1;
                }
                File file = new File(bundle.getString("backup_apk"));
                String a4 = b.a(context, true, file);
                if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(a4)) {
                    TbsLog.i("TbsInstaller", "tpatch sig not equals!!!" + file + "signature:" + a4);
                    TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.TPATCH_BACKUP_NOT_VALID, "version=" + i3 + ",patchVersion=" + a3);
                    f.b(file);
                    return 0;
                } else if (TbsDownloader.a(context)) {
                    TbsLog.i("TbsInstaller", "Tpatch decouple success!");
                    TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS, "");
                    return 0;
                } else {
                    TbsLog.i("TbsInstaller", "Tpatch success!");
                    TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.TPATCH_INSTALL_SUCCESS, "");
                    return 0;
                }
            } else {
                String string2 = bundle.getString("new_apk_location");
                if (!TextUtils.isEmpty(string2)) {
                    f.b(new File(string2));
                }
                TbsLogReport.getInstance(context).setInstallErrorCode(i2, "tpatch fail,patch error_code=" + i2);
                return 1;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.DECOUPLE_TPATCH_FAIL, "patch exception" + Log.getStackTraceString(e2));
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(Context context, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i2 > 0) {
            int i3 = i(context);
            if (i3 != i2) {
                Context e2 = TbsShareManager.e(context);
                if (e2 != null || TbsShareManager.getHostCorePathAppDefined() != null) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                    a(context, e2);
                } else if (i3 <= 0) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                    QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0365, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x05d8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x05d9, code lost:
        r2 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x02fd A[SYNTHETIC, Splitter:B:100:0x02fd] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x035a A[SYNTHETIC, Splitter:B:124:0x035a] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0365 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:84:0x02d1] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0368 A[SYNTHETIC, Splitter:B:133:0x0368] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0445 A[ADDED_TO_REGION, Catch:{ Exception -> 0x036c }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0494 A[Catch:{ Exception -> 0x036c }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x04a8 A[Catch:{ Exception -> 0x036c }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x04da A[Catch:{ Exception -> 0x036c }] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x050b  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x050e A[SYNTHETIC, Splitter:B:185:0x050e] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x051a A[Catch:{ Exception -> 0x036c }] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x05e2  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x05e5  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02f7 A[SYNTHETIC, Splitter:B:96:0x02f7] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:179:0x04e1=Splitter:B:179:0x04e1, B:181:0x04f9=Splitter:B:181:0x04f9, B:135:0x036b=Splitter:B:135:0x036b} */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r13, android.content.Context r14, int r15) {
        /*
            r12 = this;
            r2 = -1
            r6 = 1
            r5 = 0
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r1 = -524(0xfffffffffffffdf4, float:NaN)
            r0.setInstallInterruptCode(r1)
            boolean r0 = r12.c(r14)
            if (r0 == 0) goto L_0x0013
        L_0x0012:
            return
        L_0x0013:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "TbsInstaller-copyTbsCoreInThread start!  tbsCoreTargetVer is "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r15)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 11
            if (r0 < r1) goto L_0x0068
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            r1 = 4
            android.content.SharedPreferences r0 = r14.getSharedPreferences(r0, r1)
        L_0x0038:
            java.lang.String r1 = "tbs_precheck_disable_version"
            int r0 = r0.getInt(r1, r2)
            if (r0 != r15) goto L_0x006f
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "TbsInstaller-copyTbsCoreInThread -- version:"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r15)
            java.lang.String r2 = " is disabled by preload_x5_check!"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r1 = -525(0xfffffffffffffdf3, float:NaN)
            r0.setInstallInterruptCode(r1)
            goto L_0x0012
        L_0x0068:
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r0 = r14.getSharedPreferences(r0, r5)
            goto L_0x0038
        L_0x006f:
            boolean r0 = r12.t(r14)
            if (r0 != 0) goto L_0x007f
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r1 = -526(0xfffffffffffffdf2, float:NaN)
            r0.setInstallInterruptCode(r1)
            goto L_0x0012
        L_0x007f:
            java.util.concurrent.locks.Lock r0 = j
            boolean r0 = r0.tryLock()
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread #1 locked is "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r3)
            if (r0 == 0) goto L_0x05c3
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.lock()
            r1 = 0
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r3 = "copy_core_ver"
            int r3 = r0.c(r3)     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r4 = "copy_status"
            int r0 = r0.b(r4)     // Catch:{ Exception -> 0x05d1 }
            if (r3 != r15) goto L_0x00da
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Exception -> 0x05d1 }
            r2 = 220(0xdc, float:3.08E-43)
            r0.onInstallFinish(r2)     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x05d1 }
            r2 = -528(0xfffffffffffffdf0, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ Exception -> 0x05d1 }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x00da:
            int r4 = r12.i(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r7 = "TbsInstaller"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x05d1 }
            r8.<init>()     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r9 = "TbsInstaller-copyTbsCoreInThread tbsCoreInstalledVer="
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x05d1 }
            java.lang.StringBuilder r8 = r8.append(r4)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ Exception -> 0x05d1 }
            if (r4 != r15) goto L_0x012f
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Exception -> 0x05d1 }
            r2 = 220(0xdc, float:3.08E-43)
            r0.onInstallFinish(r2)     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x05d1 }
            r2 = -528(0xfffffffffffffdf0, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x05d1 }
            r2.<init>()     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r3 = "TbsInstaller-copyTbsCoreInThread return have same version is "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x05d1 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ Exception -> 0x05d1 }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x012f:
            com.tencent.smtt.sdk.j r7 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x05d1 }
            int r7 = r7.b()     // Catch:{ Exception -> 0x05d1 }
            if (r7 <= 0) goto L_0x013b
            if (r15 > r7) goto L_0x013f
        L_0x013b:
            if (r3 <= 0) goto L_0x0142
            if (r15 <= r3) goto L_0x0142
        L_0x013f:
            r12.o(r14)     // Catch:{ Exception -> 0x05d1 }
        L_0x0142:
            r3 = 3
            if (r0 != r3) goto L_0x015a
            if (r4 <= 0) goto L_0x015a
            if (r15 > r4) goto L_0x014e
            r3 = 88888888(0x54c5638, float:9.60787E-36)
            if (r15 != r3) goto L_0x015a
        L_0x014e:
            r12.o(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r3 = "TbsInstaller-copyTbsCoreInThread -- update TBS....."
            r4 = 1
            com.tencent.smtt.utils.TbsLog.i(r0, r3, r4)     // Catch:{ Exception -> 0x05d1 }
            r0 = r2
        L_0x015a:
            boolean r2 = com.tencent.smtt.utils.f.b(r14)     // Catch:{ Exception -> 0x05d1 }
            if (r2 != 0) goto L_0x01aa
            long r2 = com.tencent.smtt.utils.q.a()     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x05d1 }
            long r4 = r0.getDownloadMinFreeSpace()     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x05d1 }
            r6 = -529(0xfffffffffffffdef, float:NaN)
            r0.setInstallInterruptCode(r6)     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x05d1 }
            r6 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x05d1 }
            r7.<init>()     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r8 = "rom is not enough when copying tbs core! curAvailROM="
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x05d1 }
            java.lang.StringBuilder r2 = r7.append(r2)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r3 = ",minReqRom="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x05d1 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x05d1 }
            r0.setInstallErrorCode(r6, r2)     // Catch:{ Exception -> 0x05d1 }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x01aa:
            if (r0 <= 0) goto L_0x01be
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r14)     // Catch:{ Exception -> 0x05d1 }
            if (r2 != 0) goto L_0x01f0
            boolean r2 = com.tencent.smtt.sdk.TbsDownloader.a(r14)     // Catch:{ Exception -> 0x05d1 }
            if (r2 == 0) goto L_0x01f0
            int r2 = r12.h(r14)     // Catch:{ Exception -> 0x05d1 }
            if (r15 == r2) goto L_0x01f0
        L_0x01be:
            if (r0 != 0) goto L_0x0226
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r2 = "copy_retry_num"
            int r0 = r0.c(r2)     // Catch:{ Exception -> 0x05d1 }
            r2 = 2
            if (r0 <= r2) goto L_0x021b
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x05d1 }
            r2 = 211(0xd3, float:2.96E-43)
            java.lang.String r3 = "exceed copy retry num!"
            r0.setInstallErrorCode(r2, r3)     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x05d1 }
            r2 = -530(0xfffffffffffffdee, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ Exception -> 0x05d1 }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x01f0:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x05d1 }
            r2.<init>()     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r3 = "TbsInstaller-copyTbsCoreInThread return have copied is "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x05d1 }
            int r3 = r12.h(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x05d1 }
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ Exception -> 0x05d1 }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x021b:
            com.tencent.smtt.sdk.j r2 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x05d1 }
            java.lang.String r3 = "copy_retry_num"
            int r0 = r0 + 1
            r2.a(r3, r0)     // Catch:{ Exception -> 0x05d1 }
        L_0x0226:
            java.io.File r0 = r12.q(r13)     // Catch:{ Exception -> 0x05d1 }
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r14)     // Catch:{ Exception -> 0x05d1 }
            if (r2 != 0) goto L_0x02c8
            boolean r2 = com.tencent.smtt.sdk.TbsDownloader.a(r14)     // Catch:{ Exception -> 0x05d1 }
            if (r2 == 0) goto L_0x02c1
            java.io.File r4 = r12.p(r14)     // Catch:{ Exception -> 0x05d1 }
        L_0x023a:
            if (r0 == 0) goto L_0x0568
            if (r4 == 0) goto L_0x0568
            com.tencent.smtt.sdk.j r1 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x036c }
            r2 = 0
            r1.a(r15, r2)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.p r1 = new com.tencent.smtt.utils.p     // Catch:{ Exception -> 0x036c }
            r1.<init>()     // Catch:{ Exception -> 0x036c }
            r1.a(r0)     // Catch:{ Exception -> 0x036c }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r8 = -551(0xfffffffffffffdd9, float:NaN)
            r7.setInstallInterruptCode(r8)     // Catch:{ Exception -> 0x036c }
            java.io.FileFilter r7 = c     // Catch:{ Exception -> 0x036c }
            boolean r7 = com.tencent.smtt.utils.f.a(r0, r4, r7)     // Catch:{ Exception -> 0x036c }
            boolean r8 = com.tencent.smtt.sdk.TbsDownloader.a(r14)     // Catch:{ Exception -> 0x036c }
            if (r8 == 0) goto L_0x026a
            com.tencent.smtt.sdk.TbsShareManager.b(r14)     // Catch:{ Exception -> 0x036c }
        L_0x026a:
            java.lang.String r8 = "TbsInstaller"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036c }
            r9.<init>()     // Catch:{ Exception -> 0x036c }
            java.lang.String r10 = "TbsInstaller-copyTbsCoreInThread time="
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Exception -> 0x036c }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x036c }
            long r2 = r10 - r2
            java.lang.StringBuilder r2 = r9.append(r2)     // Catch:{ Exception -> 0x036c }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.TbsLog.i(r8, r2)     // Catch:{ Exception -> 0x036c }
            if (r7 == 0) goto L_0x0540
            r1.b(r0)     // Catch:{ Exception -> 0x036c }
            boolean r0 = r1.a()     // Catch:{ Exception -> 0x036c }
            if (r0 != 0) goto L_0x02cf
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-copyTbsCoreInThread copy-verify fail!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Exception -> 0x036c }
            r0 = 1
            com.tencent.smtt.utils.f.a(r4, r0)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 213(0xd5, float:2.98E-43)
            java.lang.String r2 = "TbsCopy-Verify fail after copying tbs core!"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r1 = -531(0xfffffffffffffded, float:NaN)
            r0.setInstallInterruptCode(r1)     // Catch:{ Exception -> 0x036c }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x02c1:
            r2 = 1
            java.io.File r4 = r12.f(r14, r2)     // Catch:{ Exception -> 0x05d1 }
            goto L_0x023a
        L_0x02c8:
            r2 = 1
            java.io.File r4 = r12.f(r14, r2)     // Catch:{ Exception -> 0x05d1 }
            goto L_0x023a
        L_0x02cf:
            r3 = 0
            r2 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0352, all -> 0x0365 }
            java.lang.String r1 = "1"
            r0.<init>(r4, r1)     // Catch:{ Exception -> 0x0352, all -> 0x0365 }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x0352, all -> 0x0365 }
            r1.<init>()     // Catch:{ Exception -> 0x0352, all -> 0x0365 }
            if (r0 == 0) goto L_0x0349
            boolean r2 = r0.exists()     // Catch:{ Exception -> 0x05d8, all -> 0x0365 }
            if (r2 == 0) goto L_0x0349
            if (r1 == 0) goto L_0x0349
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ Exception -> 0x05d8, all -> 0x0365 }
            r7.<init>(r0)     // Catch:{ Exception -> 0x05d8, all -> 0x0365 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x05d8, all -> 0x0365 }
            r2.<init>(r7)     // Catch:{ Exception -> 0x05d8, all -> 0x0365 }
            r1.load(r2)     // Catch:{ Exception -> 0x05dc }
            r0 = r6
        L_0x02f5:
            if (r2 == 0) goto L_0x05e5
            r2.close()     // Catch:{ IOException -> 0x034c }
            r2 = r0
        L_0x02fb:
            if (r2 == 0) goto L_0x05df
            java.io.File[] r3 = r4.listFiles()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r7 = -552(0xfffffffffffffdd8, float:NaN)
            r0.setInstallInterruptCode(r7)     // Catch:{ Exception -> 0x036c }
            r0 = r5
        L_0x030b:
            int r7 = r3.length     // Catch:{ Exception -> 0x036c }
            if (r0 >= r7) goto L_0x05df
            r7 = r3[r0]     // Catch:{ Exception -> 0x036c }
            java.lang.String r8 = "1"
            java.lang.String r9 = r7.getName()     // Catch:{ Exception -> 0x036c }
            boolean r8 = r8.equals(r9)     // Catch:{ Exception -> 0x036c }
            if (r8 != 0) goto L_0x0346
            java.lang.String r8 = r7.getName()     // Catch:{ Exception -> 0x036c }
            java.lang.String r9 = ".dex"
            boolean r8 = r8.endsWith(r9)     // Catch:{ Exception -> 0x036c }
            if (r8 != 0) goto L_0x0346
            java.lang.String r8 = "tbs.conf"
            java.lang.String r9 = r7.getName()     // Catch:{ Exception -> 0x036c }
            boolean r8 = r8.equals(r9)     // Catch:{ Exception -> 0x036c }
            if (r8 != 0) goto L_0x0346
            boolean r8 = r7.isDirectory()     // Catch:{ Exception -> 0x036c }
            if (r8 != 0) goto L_0x0346
            java.lang.String r8 = r7.getName()     // Catch:{ Exception -> 0x036c }
            java.lang.String r9 = ".prof"
            boolean r8 = r8.endsWith(r9)     // Catch:{ Exception -> 0x036c }
            if (r8 == 0) goto L_0x03b4
        L_0x0346:
            int r0 = r0 + 1
            goto L_0x030b
        L_0x0349:
            r2 = r3
            r0 = r5
            goto L_0x02f5
        L_0x034c:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ Exception -> 0x036c }
            r2 = r0
            goto L_0x02fb
        L_0x0352:
            r0 = move-exception
            r1 = r2
            r2 = r3
        L_0x0355:
            r0.printStackTrace()     // Catch:{ all -> 0x05d4 }
            if (r2 == 0) goto L_0x05e2
            r2.close()     // Catch:{ IOException -> 0x035f }
            r2 = r6
            goto L_0x02fb
        L_0x035f:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x036c }
            r2 = r6
            goto L_0x02fb
        L_0x0365:
            r0 = move-exception
        L_0x0366:
            if (r3 == 0) goto L_0x036b
            r3.close()     // Catch:{ IOException -> 0x03a0 }
        L_0x036b:
            throw r0     // Catch:{ Exception -> 0x036c }
        L_0x036c:
            r0 = move-exception
            r1 = r4
        L_0x036e:
            com.tencent.smtt.sdk.TbsLogReport r2 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ all -> 0x03a5 }
            r3 = 215(0xd7, float:3.01E-43)
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x03a5 }
            r2.setInstallErrorCode(r3, r0)     // Catch:{ all -> 0x03a5 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ all -> 0x03a5 }
            r2 = -537(0xfffffffffffffde7, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x03a5 }
            r0 = 0
            com.tencent.smtt.utils.f.a(r1, r0)     // Catch:{ Exception -> 0x0596 }
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x0596 }
            r1 = 0
            r2 = -1
            r0.a(r1, r2)     // Catch:{ Exception -> 0x0596 }
        L_0x0391:
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x03a0:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x036c }
            goto L_0x036b
        L_0x03a5:
            r0 = move-exception
            java.util.concurrent.locks.ReentrantLock r1 = i
            r1.unlock()
            java.util.concurrent.locks.Lock r1 = j
            r1.unlock()
            r12.b()
            throw r0
        L_0x03b4:
            java.lang.String r8 = com.tencent.smtt.utils.a.a(r7)     // Catch:{ Exception -> 0x036c }
            java.lang.String r9 = r7.getName()     // Catch:{ Exception -> 0x036c }
            java.lang.String r10 = ""
            java.lang.String r9 = r1.getProperty(r9, r10)     // Catch:{ Exception -> 0x036c }
            java.lang.String r10 = ""
            boolean r10 = r9.equals(r10)     // Catch:{ Exception -> 0x036c }
            if (r10 != 0) goto L_0x03f4
            boolean r10 = r8.equals(r9)     // Catch:{ Exception -> 0x036c }
            if (r10 == 0) goto L_0x03f4
            java.lang.String r8 = "TbsInstaller"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036c }
            r9.<init>()     // Catch:{ Exception -> 0x036c }
            java.lang.String r10 = "md5_check_success for ("
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Exception -> 0x036c }
            java.lang.String r7 = r7.getName()     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r7 = r9.append(r7)     // Catch:{ Exception -> 0x036c }
            java.lang.String r9 = ")"
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ Exception -> 0x036c }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.TbsLog.i(r8, r7)     // Catch:{ Exception -> 0x036c }
            goto L_0x0346
        L_0x03f4:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036c }
            r1.<init>()     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = "md5_check_failure for ("
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = r7.getName()     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = ")"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = " targetMd5:"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r1 = r1.append(r9)     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = ", realMd5:"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ Exception -> 0x036c }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.TbsLog.e(r0, r1)     // Catch:{ Exception -> 0x036c }
            r0 = r5
        L_0x042b:
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036c }
            r3.<init>()     // Catch:{ Exception -> 0x036c }
            java.lang.String r5 = "copyTbsCoreInThread - md5_check_success:"
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch:{ Exception -> 0x036c }
            if (r2 == 0) goto L_0x0475
            if (r0 != 0) goto L_0x0475
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "copyTbsCoreInThread - md5 incorrect -> delete destTmpDir!"
            com.tencent.smtt.utils.TbsLog.e(r0, r1)     // Catch:{ Exception -> 0x036c }
            r0 = 1
            com.tencent.smtt.utils.f.a(r4, r0)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 213(0xd5, float:2.98E-43)
            java.lang.String r2 = "TbsCopy-Verify md5 fail after copying tbs core!"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r1 = -532(0xfffffffffffffdec, float:NaN)
            r0.setInstallInterruptCode(r1)     // Catch:{ Exception -> 0x036c }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x0475:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-copyTbsCoreInThread success!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Exception -> 0x036c }
            r0 = 1
            r12.g(r14, r0)     // Catch:{ Exception -> 0x036c }
            java.io.File r1 = com.tencent.smtt.sdk.i.b(r13)     // Catch:{ Exception -> 0x036c }
            if (r1 == 0) goto L_0x049c
            boolean r0 = r1.exists()     // Catch:{ Exception -> 0x036c }
            if (r0 == 0) goto L_0x049c
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x036c }
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r14)     // Catch:{ Exception -> 0x036c }
            if (r0 == 0) goto L_0x050b
            java.lang.String r0 = "x5.oversea.tbs.org"
        L_0x0496:
            r2.<init>(r1, r0)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.i.a(r2, r14)     // Catch:{ Exception -> 0x036c }
        L_0x049c:
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 1
            r0.a(r15, r1)     // Catch:{ Exception -> 0x036c }
            boolean r0 = r12.k     // Catch:{ Exception -> 0x036c }
            if (r0 == 0) goto L_0x050e
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 220(0xdc, float:3.08E-43)
            java.lang.String r2 = "continueInstallWithout core success"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
        L_0x04b3:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r1 = -533(0xfffffffffffffdeb, float:NaN)
            r0.setInstallInterruptCode(r1)     // Catch:{ Exception -> 0x036c }
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036c }
            r1.<init>()     // Catch:{ Exception -> 0x036c }
            java.lang.String r2 = "TbsInstaller-copyTbsCoreInThread success -- version:"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r1 = r1.append(r15)     // Catch:{ Exception -> 0x036c }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Exception -> 0x036c }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x036c }
            r1 = 11
            if (r0 < r1) goto L_0x051a
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            r1 = 4
            android.content.SharedPreferences r0 = r14.getSharedPreferences(r0, r1)     // Catch:{ Exception -> 0x036c }
        L_0x04e1:
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Throwable -> 0x0522 }
            java.lang.String r1 = "tbs_preload_x5_counter"
            r2 = 0
            r0.putInt(r1, r2)     // Catch:{ Throwable -> 0x0522 }
            java.lang.String r1 = "tbs_preload_x5_recorder"
            r2 = 0
            r0.putInt(r1, r2)     // Catch:{ Throwable -> 0x0522 }
            java.lang.String r1 = "tbs_preload_x5_version"
            r0.putInt(r1, r15)     // Catch:{ Throwable -> 0x0522 }
            r0.commit()     // Catch:{ Throwable -> 0x0522 }
        L_0x04f9:
            com.tencent.smtt.utils.q.a(r14)     // Catch:{ Exception -> 0x036c }
        L_0x04fc:
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r12.b()
            goto L_0x0012
        L_0x050b:
            java.lang.String r0 = "x5.tbs.org"
            goto L_0x0496
        L_0x050e:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 220(0xdc, float:3.08E-43)
            java.lang.String r2 = "success"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
            goto L_0x04b3
        L_0x051a:
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            r1 = 0
            android.content.SharedPreferences r0 = r14.getSharedPreferences(r0, r1)     // Catch:{ Exception -> 0x036c }
            goto L_0x04e1
        L_0x0522:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x036c }
            r2.<init>()     // Catch:{ Exception -> 0x036c }
            java.lang.String r3 = "Init tbs_preload_x5_counter#2 exception:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x036c }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ Exception -> 0x036c }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x036c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.utils.TbsLog.e(r1, r0)     // Catch:{ Exception -> 0x036c }
            goto L_0x04f9
        L_0x0540:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-copyTbsCoreInThread fail!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.j r0 = com.tencent.smtt.sdk.j.a(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 2
            r0.a(r15, r1)     // Catch:{ Exception -> 0x036c }
            r0 = 0
            com.tencent.smtt.utils.f.a(r4, r0)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r1 = -534(0xfffffffffffffdea, float:NaN)
            r0.setInstallInterruptCode(r1)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 212(0xd4, float:2.97E-43)
            java.lang.String r2 = "copy fail!"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
            goto L_0x04fc
        L_0x0568:
            if (r0 != 0) goto L_0x057e
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 213(0xd5, float:2.98E-43)
            java.lang.String r2 = "src-dir is null when copying tbs core!"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r1 = -535(0xfffffffffffffde9, float:NaN)
            r0.setInstallInterruptCode(r1)     // Catch:{ Exception -> 0x036c }
        L_0x057e:
            if (r4 != 0) goto L_0x04fc
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r14)     // Catch:{ Exception -> 0x036c }
            r1 = 214(0xd6, float:3.0E-43)
            java.lang.String r2 = "dst-dir is null when copying tbs core!"
            r0.setInstallErrorCode(r1, r2)     // Catch:{ Exception -> 0x036c }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x036c }
            r1 = -536(0xfffffffffffffde8, float:NaN)
            r0.setInstallInterruptCode(r1)     // Catch:{ Exception -> 0x036c }
            goto L_0x04fc
        L_0x0596:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x03a5 }
            r2.<init>()     // Catch:{ all -> 0x03a5 }
            java.lang.String r3 = "[TbsInstaller-copyTbsCoreInThread] delete dstTmpDir throws exception:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x03a5 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x03a5 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x03a5 }
            java.lang.String r3 = ","
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x03a5 }
            java.lang.Throwable r0 = r0.getCause()     // Catch:{ all -> 0x03a5 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x03a5 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x03a5 }
            com.tencent.smtt.utils.TbsLog.e(r1, r0)     // Catch:{ all -> 0x03a5 }
            goto L_0x0391
        L_0x05c3:
            r12.b()
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r1 = -538(0xfffffffffffffde6, float:NaN)
            r0.setInstallInterruptCode(r1)
            goto L_0x0012
        L_0x05d1:
            r0 = move-exception
            goto L_0x036e
        L_0x05d4:
            r0 = move-exception
            r3 = r2
            goto L_0x0366
        L_0x05d8:
            r0 = move-exception
            r2 = r3
            goto L_0x0355
        L_0x05dc:
            r0 = move-exception
            goto L_0x0355
        L_0x05df:
            r0 = r6
            goto L_0x042b
        L_0x05e2:
            r2 = r6
            goto L_0x02fb
        L_0x05e5:
            r2 = r0
            goto L_0x02fb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(android.content.Context, android.content.Context, int):void");
    }

    private boolean e(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e2) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public Context b(Context context, String str) {
        try {
            if (context.getPackageName() == str || !TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                return context.createPackageContext(str, 2);
            }
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public boolean b(Context context, File file, int i2) {
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread #1");
        boolean a2 = a(context, file, false);
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread result is " + a2);
        if (a2) {
            a().a(context, i2);
        }
        return a2;
    }

    private boolean a(Context context, File file) {
        return a(context, file, false);
    }

    /* JADX INFO: used method not loaded: com.tencent.smtt.utils.f.b(java.io.File):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10).setInstallInterruptCode(-523);
        com.tencent.smtt.sdk.TbsLogReport.getInstance(r10).setInstallErrorCode((int) com.tencent.smtt.sdk.TbsListener.ErrorCode.UNZIP_IO_ERROR, (java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        com.tencent.smtt.utils.f.b(r4);
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + r4.exists());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0164, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0165, code lost:
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01e7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01e8, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs done");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ef, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01f5, code lost:
        r0 = false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x011d A[ExcHandler: IOException (r2v17 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:23:0x0090] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.content.Context r10, java.io.File r11, boolean r12) {
        /*
            r9 = this;
            r0 = 1
            r1 = 0
            java.lang.String r2 = "TbsInstaller"
            java.lang.String r3 = "TbsInstaller-unzipTbs start"
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            boolean r2 = com.tencent.smtt.utils.f.c(r11)
            if (r2 != 0) goto L_0x0024
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r10)
            r2 = 204(0xcc, float:2.86E-43)
            java.lang.String r3 = "apk is invalid!"
            r0.setInstallErrorCode(r2, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)
            r2 = -520(0xfffffffffffffdf8, float:NaN)
            r0.setInstallInterruptCode(r2)
        L_0x0023:
            return r1
        L_0x0024:
            java.lang.String r2 = "tbs"
            r3 = 0
            java.io.File r3 = r10.getDir(r2, r3)     // Catch:{ Throwable -> 0x006c }
            if (r12 == 0) goto L_0x0064
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x006c }
            java.lang.String r4 = "core_share_decouple"
            r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x006c }
        L_0x0034:
            if (r2 == 0) goto L_0x0045
            boolean r3 = r2.exists()     // Catch:{ Throwable -> 0x006c }
            if (r3 == 0) goto L_0x0045
            boolean r3 = com.tencent.smtt.sdk.TbsDownloader.a(r10)     // Catch:{ Throwable -> 0x006c }
            if (r3 != 0) goto L_0x0045
            com.tencent.smtt.utils.f.b(r2)     // Catch:{ Throwable -> 0x006c }
        L_0x0045:
            if (r12 == 0) goto L_0x008a
            r2 = 2
            java.io.File r2 = r9.f(r10, r2)
            r4 = r2
        L_0x004d:
            if (r4 != 0) goto L_0x0090
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r10)
            r2 = 205(0xcd, float:2.87E-43)
            java.lang.String r3 = "tmp unzip dir is null!"
            r0.setInstallErrorCode(r2, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)
            r2 = -521(0xfffffffffffffdf7, float:NaN)
            r0.setInstallInterruptCode(r2)
            goto L_0x0023
        L_0x0064:
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x006c }
            java.lang.String r4 = "core_unzip_tmp"
            r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x006c }
            goto L_0x0034
        L_0x006c:
            r2 = move-exception
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "TbsInstaller-unzipTbs -- delete unzip folder if exists exception"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r3, r2)
            goto L_0x0045
        L_0x008a:
            java.io.File r2 = r9.f(r10, r1)
            r4 = r2
            goto L_0x004d
        L_0x0090:
            com.tencent.smtt.utils.f.a(r4)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            if (r12 == 0) goto L_0x0099
            r2 = 1
            com.tencent.smtt.utils.f.a(r4, r2)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
        L_0x0099:
            boolean r2 = com.tencent.smtt.utils.f.a(r11, r4)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            if (r2 == 0) goto L_0x00a3
            boolean r2 = r9.a(r4, r10)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
        L_0x00a3:
            if (r12 == 0) goto L_0x00d4
            java.lang.String[] r5 = r4.list()     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r3 = r1
        L_0x00aa:
            int r6 = r5.length     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            if (r3 >= r6) goto L_0x00c6
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r7 = r5[r3]     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r6.<init>(r4, r7)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            java.lang.String r7 = r6.getName()     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            java.lang.String r8 = ".dex"
            boolean r7 = r7.endsWith(r8)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            if (r7 == 0) goto L_0x00c3
            r6.delete()     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
        L_0x00c3:
            int r3 = r3 + 1
            goto L_0x00aa
        L_0x00c6:
            java.io.File r3 = s(r10)     // Catch:{ Exception -> 0x01f0, IOException -> 0x011d }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x01f0, IOException -> 0x011d }
            java.lang.String r6 = "x5.tbs"
            r5.<init>(r3, r6)     // Catch:{ Exception -> 0x01f0, IOException -> 0x011d }
            r5.delete()     // Catch:{ Exception -> 0x01f0, IOException -> 0x011d }
        L_0x00d4:
            if (r2 != 0) goto L_0x0108
            com.tencent.smtt.utils.f.b(r4)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r5 = -522(0xfffffffffffffdf6, float:NaN)
            r3.setInstallInterruptCode(r5)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r5.<init>()     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            java.lang.String r6 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            boolean r6 = r4.exists()     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            com.tencent.smtt.utils.TbsLog.e(r3, r5)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
        L_0x00fe:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r1 = r2
            goto L_0x0023
        L_0x0108:
            r3 = 1
            r9.g(r10, r3)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            if (r12 == 0) goto L_0x00fe
            java.io.File r3 = r9.p(r10)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r5 = 1
            com.tencent.smtt.utils.f.a(r3, r5)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            r4.renameTo(r3)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            com.tencent.smtt.sdk.TbsShareManager.b(r10)     // Catch:{ IOException -> 0x011d, Exception -> 0x0182 }
            goto L_0x00fe
        L_0x011d:
            r2 = move-exception
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ all -> 0x01e7 }
            r5 = -523(0xfffffffffffffdf5, float:NaN)
            r3.setInstallInterruptCode(r5)     // Catch:{ all -> 0x01e7 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r10)     // Catch:{ all -> 0x01e7 }
            r5 = 206(0xce, float:2.89E-43)
            r3.setInstallErrorCode(r5, r2)     // Catch:{ all -> 0x01e7 }
            if (r4 == 0) goto L_0x01f5
            boolean r2 = r4.exists()     // Catch:{ all -> 0x01e7 }
            if (r2 == 0) goto L_0x01f5
        L_0x0138:
            if (r0 == 0) goto L_0x015b
            if (r4 == 0) goto L_0x015b
            com.tencent.smtt.utils.f.b(r4)     // Catch:{ Throwable -> 0x0164 }
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0164 }
            r2.<init>()     // Catch:{ Throwable -> 0x0164 }
            java.lang.String r3 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0164 }
            boolean r3 = r4.exists()     // Catch:{ Throwable -> 0x0164 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0164 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0164 }
            com.tencent.smtt.utils.TbsLog.e(r0, r2)     // Catch:{ Throwable -> 0x0164 }
        L_0x015b:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)
            goto L_0x0023
        L_0x0164:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
            goto L_0x015b
        L_0x0182:
            r2 = move-exception
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ all -> 0x01e7 }
            r5 = -523(0xfffffffffffffdf5, float:NaN)
            r3.setInstallInterruptCode(r5)     // Catch:{ all -> 0x01e7 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r10)     // Catch:{ all -> 0x01e7 }
            r5 = 207(0xcf, float:2.9E-43)
            r3.setInstallErrorCode(r5, r2)     // Catch:{ all -> 0x01e7 }
            if (r4 == 0) goto L_0x01f3
            boolean r2 = r4.exists()     // Catch:{ all -> 0x01e7 }
            if (r2 == 0) goto L_0x01f3
        L_0x019d:
            if (r0 == 0) goto L_0x01c0
            if (r4 == 0) goto L_0x01c0
            com.tencent.smtt.utils.f.b(r4)     // Catch:{ Throwable -> 0x01c9 }
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01c9 }
            r2.<init>()     // Catch:{ Throwable -> 0x01c9 }
            java.lang.String r3 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x01c9 }
            boolean r3 = r4.exists()     // Catch:{ Throwable -> 0x01c9 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x01c9 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x01c9 }
            com.tencent.smtt.utils.TbsLog.e(r0, r2)     // Catch:{ Throwable -> 0x01c9 }
        L_0x01c0:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)
            goto L_0x0023
        L_0x01c9:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
            goto L_0x01c0
        L_0x01e7:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.String r2 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            throw r0
        L_0x01f0:
            r3 = move-exception
            goto L_0x00d4
        L_0x01f3:
            r0 = r1
            goto L_0x019d
        L_0x01f5:
            r0 = r1
            goto L_0x0138
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(android.content.Context, java.io.File, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004b A[SYNTHETIC, Splitter:B:13:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00bb A[SYNTHETIC, Splitter:B:39:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c9 A[SYNTHETIC, Splitter:B:46:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0162 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0182  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.io.File r11, android.content.Context r12) {
        /*
            r10 = this;
            r3 = 0
            r2 = 1
            r4 = 0
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r5 = "finalCheckForTbsCoreValidity - "
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r5 = ", "
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r0 = "1"
            r1.<init>(r11, r0)     // Catch:{ Exception -> 0x00b3 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Exception -> 0x00b3 }
            r0.<init>()     // Catch:{ Exception -> 0x00b3 }
            if (r1 == 0) goto L_0x00ab
            boolean r5 = r1.exists()     // Catch:{ Exception -> 0x0179 }
            if (r5 == 0) goto L_0x00ab
            if (r0 == 0) goto L_0x00ab
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0179 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0179 }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0179 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0179 }
            r0.load(r5)     // Catch:{ Exception -> 0x017c, all -> 0x0175 }
            r1 = r2
        L_0x0049:
            if (r5 == 0) goto L_0x004e
            r5.close()     // Catch:{ IOException -> 0x00ae }
        L_0x004e:
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "finalCheckForTbsCoreValidity - need_check:"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r5)
            if (r1 == 0) goto L_0x0180
            java.io.File[] r5 = r11.listFiles()
            r3 = r4
        L_0x006d:
            int r6 = r5.length
            if (r3 >= r6) goto L_0x0180
            r6 = r5[r3]
            java.lang.String r7 = "1"
            java.lang.String r8 = r6.getName()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x00a8
            java.lang.String r7 = r6.getName()
            java.lang.String r8 = ".dex"
            boolean r7 = r7.endsWith(r8)
            if (r7 != 0) goto L_0x00a8
            java.lang.String r7 = "tbs.conf"
            java.lang.String r8 = r6.getName()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x00a8
            boolean r7 = r6.isDirectory()
            if (r7 != 0) goto L_0x00a8
            java.lang.String r7 = r6.getName()
            java.lang.String r8 = ".prof"
            boolean r7 = r7.endsWith(r8)
            if (r7 == 0) goto L_0x00d2
        L_0x00a8:
            int r3 = r3 + 1
            goto L_0x006d
        L_0x00ab:
            r5 = r3
            r1 = r4
            goto L_0x0049
        L_0x00ae:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x004e
        L_0x00b3:
            r0 = move-exception
            r1 = r0
            r0 = r3
        L_0x00b6:
            r1.printStackTrace()     // Catch:{ all -> 0x00c6 }
            if (r3 == 0) goto L_0x0182
            r3.close()     // Catch:{ IOException -> 0x00c0 }
            r1 = r2
            goto L_0x004e
        L_0x00c0:
            r1 = move-exception
            r1.printStackTrace()
            r1 = r2
            goto L_0x004e
        L_0x00c6:
            r0 = move-exception
        L_0x00c7:
            if (r3 == 0) goto L_0x00cc
            r3.close()     // Catch:{ IOException -> 0x00cd }
        L_0x00cc:
            throw r0
        L_0x00cd:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00cc
        L_0x00d2:
            java.lang.String r7 = com.tencent.smtt.utils.a.a(r6)
            java.lang.String r8 = r6.getName()
            java.lang.String r9 = ""
            java.lang.String r8 = r0.getProperty(r8, r9)
            java.lang.String r9 = ""
            boolean r9 = r8.equals(r9)
            if (r9 != 0) goto L_0x0111
            boolean r9 = r8.equals(r7)
            if (r9 == 0) goto L_0x0111
            java.lang.String r7 = "TbsInstaller"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "md5_check_success for ("
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r6 = r6.getName()
            java.lang.StringBuilder r6 = r8.append(r6)
            java.lang.String r8 = ")"
            java.lang.StringBuilder r6 = r6.append(r8)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r7, r6)
            goto L_0x00a8
        L_0x0111:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "md5_check_failure for ("
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = r6.getName()
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = ")"
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = " targetMd5:"
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.StringBuilder r3 = r3.append(r8)
            java.lang.String r5 = ", realMd5:"
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r3)
            r0 = r4
        L_0x0148:
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "finalCheckForTbsCoreValidity - md5_check_success:"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r5)
            if (r1 == 0) goto L_0x016c
            if (r0 != 0) goto L_0x016c
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "finalCheckForTbsCoreValidity - Verify failed after unzipping!"
            com.tencent.smtt.utils.TbsLog.e(r0, r1)
        L_0x016b:
            return r4
        L_0x016c:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "finalCheckForTbsCoreValidity success!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r4 = r2
            goto L_0x016b
        L_0x0175:
            r0 = move-exception
            r3 = r5
            goto L_0x00c7
        L_0x0179:
            r1 = move-exception
            goto L_0x00b6
        L_0x017c:
            r1 = move-exception
            r3 = r5
            goto L_0x00b6
        L_0x0180:
            r0 = r2
            goto L_0x0148
        L_0x0182:
            r1 = r2
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(java.io.File, android.content.Context):boolean");
    }

    public boolean e(Context context) {
        try {
            File file = new File(f.a(context, 4), "x5.tbs.decouple");
            File f2 = a().f(context, 2);
            f.a(f2);
            f.a(f2, true);
            f.a(file, f2);
            String[] list = f2.list();
            for (String file2 : list) {
                File file3 = new File(f2, file2);
                if (file3.getName().endsWith(".dex")) {
                    file3.delete();
                }
            }
            i(context, 2);
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private void i(Context context, int i2) {
        File f2 = a().f(context, i2);
        a().g(context, true);
        File p2 = p(context);
        f.a(p2, true);
        f2.renameTo(p2);
        TbsShareManager.b(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005a A[Catch:{ Exception -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b1 A[Catch:{ Exception -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b5 A[Catch:{ Exception -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bd A[Catch:{ Exception -> 0x007f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean j(android.content.Context r9, int r10) {
        /*
            r8 = this;
            r2 = 1
            r3 = 0
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "TbsInstaller-doTbsDexOpt start - dirMode: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r10)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            switch(r10) {
                case 0: goto L_0x0037;
                case 1: goto L_0x0098;
                case 2: goto L_0x009e;
                default: goto L_0x001d;
            }
        L_0x001d:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007f }
            r1.<init>()     // Catch:{ Exception -> 0x007f }
            java.lang.String r4 = "doDexoptOrDexoat mode error: "
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Exception -> 0x007f }
            java.lang.StringBuilder r1 = r1.append(r10)     // Catch:{ Exception -> 0x007f }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x007f }
            com.tencent.smtt.utils.TbsLog.e(r0, r1)     // Catch:{ Exception -> 0x007f }
            r2 = r3
        L_0x0036:
            return r2
        L_0x0037:
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a(r9)     // Catch:{ Exception -> 0x007f }
            if (r0 != 0) goto L_0x0036
            r0 = 0
            java.io.File r0 = r8.f(r9, r0)     // Catch:{ Exception -> 0x007f }
        L_0x0042:
            java.lang.String r1 = "java.vm.version"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{ Throwable -> 0x00a5 }
            if (r1 == 0) goto L_0x00a3
            java.lang.String r4 = "2"
            boolean r1 = r1.startsWith(r4)     // Catch:{ Throwable -> 0x00a5 }
            if (r1 == 0) goto L_0x00a3
            r1 = r2
        L_0x0053:
            r4 = r1
        L_0x0054:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x007f }
            r5 = 23
            if (r1 != r5) goto L_0x00b1
            r1 = r2
        L_0x005b:
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ Exception -> 0x007f }
            android.content.SharedPreferences r5 = r5.mPreferences     // Catch:{ Exception -> 0x007f }
            java.lang.String r6 = "tbs_stop_preoat"
            r7 = 0
            boolean r5 = r5.getBoolean(r6, r7)     // Catch:{ Exception -> 0x007f }
            if (r4 == 0) goto L_0x006f
            if (r1 == 0) goto L_0x006f
            if (r5 != 0) goto L_0x006f
            r3 = r2
        L_0x006f:
            if (r3 == 0) goto L_0x00b3
            boolean r1 = r8.c(r9, r0)     // Catch:{ Exception -> 0x007f }
            if (r1 == 0) goto L_0x00b3
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "doTbsDexOpt -- doDexoatForArtVm"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Exception -> 0x007f }
            goto L_0x0036
        L_0x007f:
            r0 = move-exception
            r0.printStackTrace()
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r9)
            r3 = 209(0xd1, float:2.93E-43)
            java.lang.String r0 = r0.toString()
            r1.setInstallErrorCode(r3, r0)
        L_0x0090:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-doTbsDexOpt done"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            goto L_0x0036
        L_0x0098:
            r0 = 1
            java.io.File r0 = r8.f(r9, r0)     // Catch:{ Exception -> 0x007f }
            goto L_0x0042
        L_0x009e:
            java.io.File r0 = r8.q(r9)     // Catch:{ Exception -> 0x007f }
            goto L_0x0042
        L_0x00a3:
            r1 = r3
            goto L_0x0053
        L_0x00a5:
            r1 = move-exception
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r9)     // Catch:{ Exception -> 0x007f }
            r5 = 226(0xe2, float:3.17E-43)
            r4.setInstallErrorCode(r5, r1)     // Catch:{ Exception -> 0x007f }
            r4 = r3
            goto L_0x0054
        L_0x00b1:
            r1 = r3
            goto L_0x005b
        L_0x00b3:
            if (r4 == 0) goto L_0x00bd
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "doTbsDexOpt -- is ART mode, skip!"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ Exception -> 0x007f }
            goto L_0x0090
        L_0x00bd:
            java.lang.String r1 = "TbsInstaller"
            java.lang.String r3 = "doTbsDexOpt -- doDexoptForDavlikVM"
            com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch:{ Exception -> 0x007f }
            boolean r2 = r8.b(r9, r0)     // Catch:{ Exception -> 0x007f }
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.j(android.content.Context, int):boolean");
    }

    public synchronized boolean a(final Context context, final Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (!p) {
            p = true;
            new Thread() {
                public void run() {
                    File q2;
                    TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
                    try {
                        if (context2 == null) {
                            q2 = new File(TbsShareManager.getHostCorePathAppDefined());
                        } else if (!TbsShareManager.isThirdPartyApp(context)) {
                            q2 = l.this.q(context2);
                        } else if (TbsShareManager.c(context) == null || !TbsShareManager.c(context).contains("decouple")) {
                            q2 = l.this.q(context2);
                        } else {
                            q2 = l.this.p(context2);
                        }
                        File q3 = l.this.q(context);
                        int i = VERSION.SDK_INT;
                        if (i != 19 && i < 21) {
                            f.a(q2, q3, (FileFilter) new FileFilter() {
                                public boolean accept(File file) {
                                    return file.getName().endsWith(".dex");
                                }
                            });
                        }
                        f.a(q2, q3, (FileFilter) new FileFilter() {
                            public boolean accept(File file) {
                                return file.getName().endsWith("tbs.conf");
                            }
                        });
                        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean f(Context context) {
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            return true;
        }
        try {
            Signature signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0];
            if (context.getPackageName().equals(TbsConfig.APP_QB)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
                return true;
            } else if (context.getPackageName().equals(TbsConfig.APP_WX)) {
                if (!signature.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    return false;
                }
                return true;
            } else if (context.getPackageName().equals(TbsConfig.APP_QQ)) {
                if (!signature.toCharsString().equals("30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049")) {
                    return false;
                }
                return true;
            } else if (context.getPackageName().equals(TbsConfig.APP_DEMO)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
                return true;
            } else if (context.getPackageName().equals(TbsConfig.APP_QZONE)) {
                if (!signature.toCharsString().equals("308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677")) {
                    return false;
                }
                return true;
            } else if (!context.getPackageName().equals("com.tencent.qqpimsecure") || signature.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e2) {
            TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore getPackageInfo fail");
            return false;
        }
    }

    public Context d(Context context, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i2);
        if (i2 <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (int i3 = 0; i3 < coreProviderAppList.length; i3++) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i3]) && e(context, coreProviderAppList[i3])) {
                Context b2 = b(context, coreProviderAppList[i3]);
                if (b2 == null) {
                    continue;
                } else if (!f(b2)) {
                    TbsLog.e("TbsInstaller", "TbsInstaller--getTbsCoreHostContext " + coreProviderAppList[i3] + " illegal signature go on next");
                } else {
                    int i4 = i(b2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + i4);
                    if (i4 != 0 && i4 == i2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext targetApp=" + coreProviderAppList[i3]);
                        return b2;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int c(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    public void g(Context context) {
        boolean z = true;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable th) {
        }
        if (z && l != null) {
            f.a(context, l);
        }
    }

    private boolean w(Context context) {
        boolean z;
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #1 ");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable th) {
            z = true;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #2  enabled is " + z);
        if (!z) {
            l = t.a().b(context);
        } else {
            l = f.f(context);
        }
        if (l == null) {
            TbsLog.i("TbsInstaller", "getTbsCoreRenameFileLock## failed!");
            return false;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock true ");
        return true;
    }

    private void x(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (!w(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            A(context);
            B(context);
            TbsLog.i("TbsInstaller", "after renameTbsCoreShareDir");
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.i("TbsInstaller", "prepare to shareTbsCore");
                TbsShareManager.a(context);
            } else {
                TbsLog.i("TbsInstaller", "is thirdapp and not chmod");
            }
            j.a(context).a(0);
            j.a(context).b(0);
            j.a(context).d(0);
            j.a(context).a("incrupdate_retry_num", 0);
            j.a(context).c(0, 3);
            j.a(context).a("");
            j.a(context).a("tpatch_num", 0);
            j.a(context).c(-1);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().h(context) || i2 != a().i(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().h(context) + " getTbsCoreInstalledVerInNolock is " + a().i(context));
                } else {
                    n(context);
                }
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, m(context), true);
            }
            a.set(Integer.valueOf(0));
            o = 0;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.RENAME_EXCEPTION, "exception when renameing from unzip:" + th.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
        g(context);
    }

    private void y(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromTpatch");
        if (!w(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            A(context);
            D(context);
            TbsShareManager.a(context);
            j.a(context).b(0, -1);
            j.a(context).a("tpatch_num", 0);
            a.set(Integer.valueOf(0));
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.TPATCH_ENABLE_EXCEPTION, "exception when renameing from tpatch:" + e2.toString());
        }
        g(context);
    }

    private void z(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (!w(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            A(context);
            C(context);
            TbsShareManager.a(context);
            j.a(context).a(0, 3);
            j.a(context).a("tpatch_num", 0);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().h(context) || i2 != a().i(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().h(context) + " getTbsCoreInstalledVerInNolock is " + a().i(context));
                } else {
                    n(context);
                }
            }
            a.set(Integer.valueOf(0));
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.RENAME_EXCEPTION, "exception when renameing from copy:" + e2.toString());
        }
        g(context);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005f A[SYNTHETIC, Splitter:B:36:0x005f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            r2 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            java.lang.String r4 = "tbs.conf"
            r3.<init>(r1, r4)     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            if (r3 == 0) goto L_0x0019
            boolean r1 = r3.exists()     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            if (r1 != 0) goto L_0x0021
        L_0x0019:
            if (r2 == 0) goto L_0x0003
            r2.close()     // Catch:{ IOException -> 0x001f }
            goto L_0x0003
        L_0x001f:
            r1 = move-exception
            goto L_0x0003
        L_0x0021:
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            r4.<init>()     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            r5.<init>(r3)     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0052, all -> 0x005c }
            r4.load(r1)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r1.close()     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r2 = "tbs_core_version"
            java.lang.String r2 = r4.getProperty(r2)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            if (r2 != 0) goto L_0x0046
            if (r1 == 0) goto L_0x0003
            r1.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0003
        L_0x0044:
            r1 = move-exception
            goto L_0x0003
        L_0x0046:
            int r0 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            if (r1 == 0) goto L_0x0003
            r1.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0003
        L_0x0050:
            r1 = move-exception
            goto L_0x0003
        L_0x0052:
            r1 = move-exception
            r1 = r2
        L_0x0054:
            if (r1 == 0) goto L_0x0003
            r1.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x0003
        L_0x005a:
            r1 = move-exception
            goto L_0x0003
        L_0x005c:
            r0 = move-exception
        L_0x005d:
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0062:
            throw r0
        L_0x0063:
            r1 = move-exception
            goto L_0x0062
        L_0x0065:
            r0 = move-exception
            r2 = r1
            goto L_0x005d
        L_0x0068:
            r2 = move-exception
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(java.lang.String):int");
    }

    /* access modifiers changed from: 0000 */
    public int e(Context context, int i2) {
        return a(f(context, i2));
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006d A[SYNTHETIC, Splitter:B:33:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.io.File r6) {
        /*
            r5 = this;
            r0 = 0
            r2 = 0
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            r3.<init>()     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.lang.String r4 = "TbsInstaller--getTbsVersion  tbsShareDir is "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.lang.String r3 = "tbs.conf"
            r1.<init>(r6, r3)     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            if (r1 == 0) goto L_0x0029
            boolean r3 = r1.exists()     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            if (r3 != 0) goto L_0x002f
        L_0x0029:
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x0071 }
        L_0x002e:
            return r0
        L_0x002f:
            java.util.Properties r3 = new java.util.Properties     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            r3.<init>()     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0060, all -> 0x006a }
            r3.load(r1)     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            r1.close()     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            java.lang.String r2 = "tbs_core_version"
            java.lang.String r2 = r3.getProperty(r2)     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            if (r2 != 0) goto L_0x0054
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x002e
        L_0x0052:
            r1 = move-exception
            goto L_0x002e
        L_0x0054:
            int r0 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x0078, all -> 0x0075 }
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x002e
        L_0x005e:
            r1 = move-exception
            goto L_0x002e
        L_0x0060:
            r1 = move-exception
            r1 = r2
        L_0x0062:
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x002e
        L_0x0068:
            r1 = move-exception
            goto L_0x002e
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch:{ IOException -> 0x0073 }
        L_0x0070:
            throw r0
        L_0x0071:
            r1 = move-exception
            goto L_0x002e
        L_0x0073:
            r1 = move-exception
            goto L_0x0070
        L_0x0075:
            r0 = move-exception
            r2 = r1
            goto L_0x006b
        L_0x0078:
            r2 = move-exception
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(java.io.File):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0055 A[SYNTHETIC, Splitter:B:30:0x0055] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String d(android.content.Context r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 == 0) goto L_0x0008
        L_0x0007:
            return r0
        L_0x0008:
            r1 = 0
            java.io.File r2 = r6.q(r7)     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            java.lang.String r4 = "tbs.conf"
            r3.<init>(r2, r4)     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            if (r3 == 0) goto L_0x001c
            boolean r2 = r3.exists()     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            if (r2 != 0) goto L_0x0024
        L_0x001c:
            if (r0 == 0) goto L_0x0007
            r1.close()     // Catch:{ IOException -> 0x0022 }
            goto L_0x0007
        L_0x0022:
            r1 = move-exception
            goto L_0x0007
        L_0x0024:
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            r2.<init>()     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0045, all -> 0x004f }
            r2.load(r1)     // Catch:{ Exception -> 0x005d, all -> 0x005b }
            r1.close()     // Catch:{ Exception -> 0x005d, all -> 0x005b }
            java.lang.String r0 = r2.getProperty(r8)     // Catch:{ Exception -> 0x005d, all -> 0x005b }
            if (r1 == 0) goto L_0x0007
            r1.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0007
        L_0x0043:
            r1 = move-exception
            goto L_0x0007
        L_0x0045:
            r1 = move-exception
            r1 = r0
        L_0x0047:
            if (r1 == 0) goto L_0x0007
            r1.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0007
        L_0x004d:
            r1 = move-exception
            goto L_0x0007
        L_0x004f:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()     // Catch:{ IOException -> 0x0059 }
        L_0x0058:
            throw r0
        L_0x0059:
            r1 = move-exception
            goto L_0x0058
        L_0x005b:
            r0 = move-exception
            goto L_0x0053
        L_0x005d:
            r2 = move-exception
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.d(android.content.Context, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0059 A[SYNTHETIC, Splitter:B:33:0x0059] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int h(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            r2 = 0
            java.io.File r1 = r6.p(r7)     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            java.lang.String r4 = "tbs.conf"
            r3.<init>(r1, r4)     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            if (r3 == 0) goto L_0x0015
            boolean r1 = r3.exists()     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            if (r1 != 0) goto L_0x001b
        L_0x0015:
            if (r2 == 0) goto L_0x001a
            r2.close()     // Catch:{ IOException -> 0x005d }
        L_0x001a:
            return r0
        L_0x001b:
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            r4.<init>()     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x004c, all -> 0x0056 }
            r4.load(r1)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r1.close()     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            java.lang.String r2 = "tbs_core_version"
            java.lang.String r2 = r4.getProperty(r2)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            if (r2 != 0) goto L_0x0040
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x001a
        L_0x003e:
            r1 = move-exception
            goto L_0x001a
        L_0x0040:
            int r0 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x001a
        L_0x004a:
            r1 = move-exception
            goto L_0x001a
        L_0x004c:
            r1 = move-exception
            r1 = r2
        L_0x004e:
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x001a
        L_0x0054:
            r1 = move-exception
            goto L_0x001a
        L_0x0056:
            r0 = move-exception
        L_0x0057:
            if (r2 == 0) goto L_0x005c
            r2.close()     // Catch:{ IOException -> 0x005f }
        L_0x005c:
            throw r0
        L_0x005d:
            r1 = move-exception
            goto L_0x001a
        L_0x005f:
            r1 = move-exception
            goto L_0x005c
        L_0x0061:
            r0 = move-exception
            r2 = r1
            goto L_0x0057
        L_0x0064:
            r2 = move-exception
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.h(android.content.Context):int");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f1 A[SYNTHETIC, Splitter:B:44:0x00f1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int i(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            r3 = 0
            java.io.File r1 = r6.q(r7)     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            java.lang.String r4 = "tbs.conf"
            r2.<init>(r1, r4)     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            if (r2 == 0) goto L_0x0015
            boolean r1 = r2.exists()     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            if (r1 != 0) goto L_0x0039
        L_0x0015:
            if (r3 == 0) goto L_0x001a
            r3.close()     // Catch:{ IOException -> 0x001b }
        L_0x001a:
            return r0
        L_0x001b:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r1)
            goto L_0x001a
        L_0x0039:
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            r1.<init>()     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            r4.<init>(r2)     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            r2.<init>(r4)     // Catch:{ Exception -> 0x00a9, all -> 0x00ed }
            r1.load(r2)     // Catch:{ Exception -> 0x0115 }
            r2.close()     // Catch:{ Exception -> 0x0115 }
            java.lang.String r3 = "tbs_core_version"
            java.lang.String r1 = r1.getProperty(r3)     // Catch:{ Exception -> 0x0115 }
            if (r1 != 0) goto L_0x007a
            if (r2 == 0) goto L_0x001a
            r2.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x001a
        L_0x005c:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r1)
            goto L_0x001a
        L_0x007a:
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0115 }
            int r3 = o     // Catch:{ Exception -> 0x0115 }
            if (r3 != 0) goto L_0x0084
            o = r1     // Catch:{ Exception -> 0x0115 }
        L_0x0084:
            if (r2 == 0) goto L_0x0089
            r2.close()     // Catch:{ IOException -> 0x008b }
        L_0x0089:
            r0 = r1
            goto L_0x001a
        L_0x008b:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            goto L_0x0089
        L_0x00a9:
            r1 = move-exception
            r2 = r3
        L_0x00ab:
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r4.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r5 = "TbsInstaller--getTbsCoreInstalledVerInNolock Exception="
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ all -> 0x0113 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0113 }
            com.tencent.smtt.utils.TbsLog.i(r3, r1)     // Catch:{ all -> 0x0113 }
            if (r2 == 0) goto L_0x001a
            r2.close()     // Catch:{ IOException -> 0x00ce }
            goto L_0x001a
        L_0x00ce:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r1)
            goto L_0x001a
        L_0x00ed:
            r0 = move-exception
            r2 = r3
        L_0x00ef:
            if (r2 == 0) goto L_0x00f4
            r2.close()     // Catch:{ IOException -> 0x00f5 }
        L_0x00f4:
            throw r0
        L_0x00f5:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r1)
            goto L_0x00f4
        L_0x0113:
            r0 = move-exception
            goto L_0x00ef
        L_0x0115:
            r1 = move-exception
            goto L_0x00ab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.i(android.content.Context):int");
    }

    /* access modifiers changed from: 0000 */
    public int j(Context context) {
        if (o != 0) {
            return o;
        }
        return i(context);
    }

    /* access modifiers changed from: 0000 */
    public void k(Context context) {
        if (o == 0) {
            o = i(context);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean l(Context context) {
        File file = new File(q(context), "tbs.conf");
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01d3 A[SYNTHETIC, Splitter:B:74:0x01d3] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01de A[Catch:{ Throwable -> 0x0205 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int m(android.content.Context r7) {
        /*
            r6 = this;
            r1 = 0
            boolean r0 = r6.t(r7)
            if (r0 != 0) goto L_0x0009
            r0 = -1
        L_0x0008:
            return r0
        L_0x0009:
            java.util.concurrent.locks.ReentrantLock r0 = i
            boolean r0 = r0.tryLock()
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock locked="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            if (r0 == 0) goto L_0x021f
            r3 = 0
            java.io.File r0 = r6.q(r7)     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            java.lang.String r4 = "tbs.conf"
            r2.<init>(r0, r4)     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            if (r2 == 0) goto L_0x003d
            boolean r0 = r2.exists()     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            if (r0 != 0) goto L_0x008c
        L_0x003d:
            if (r3 == 0) goto L_0x0042
            r3.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0042:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x0072 }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x0072 }
            if (r0 == 0) goto L_0x004f
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x0072 }
            r0.unlock()     // Catch:{ Throwable -> 0x0072 }
        L_0x004f:
            r6.b()
            r0 = r1
            goto L_0x0008
        L_0x0054:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            goto L_0x0042
        L_0x0072:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsRenameLock.unlock exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
            goto L_0x004f
        L_0x008c:
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            r0.<init>()     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0161, all -> 0x01cf }
            r0.load(r2)     // Catch:{ Exception -> 0x0227 }
            r2.close()     // Catch:{ Exception -> 0x0227 }
            java.lang.String r3 = "tbs_core_version"
            java.lang.String r0 = r0.getProperty(r3)     // Catch:{ Exception -> 0x0227 }
            if (r0 != 0) goto L_0x00f9
            if (r2 == 0) goto L_0x00ae
            r2.close()     // Catch:{ IOException -> 0x00c1 }
        L_0x00ae:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x00df }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x00df }
            if (r0 == 0) goto L_0x00bb
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x00df }
            r0.unlock()     // Catch:{ Throwable -> 0x00df }
        L_0x00bb:
            r6.b()
            r0 = r1
            goto L_0x0008
        L_0x00c1:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            goto L_0x00ae
        L_0x00df:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsRenameLock.unlock exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
            goto L_0x00bb
        L_0x00f9:
            java.lang.ThreadLocal<java.lang.Integer> r3 = a     // Catch:{ Exception -> 0x0227 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0227 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0227 }
            r3.set(r0)     // Catch:{ Exception -> 0x0227 }
            java.lang.ThreadLocal<java.lang.Integer> r0 = a     // Catch:{ Exception -> 0x0227 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0227 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0227 }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x0227 }
            if (r2 == 0) goto L_0x0117
            r2.close()     // Catch:{ IOException -> 0x0129 }
        L_0x0117:
            java.util.concurrent.locks.ReentrantLock r1 = i     // Catch:{ Throwable -> 0x0147 }
            boolean r1 = r1.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x0147 }
            if (r1 == 0) goto L_0x0124
            java.util.concurrent.locks.ReentrantLock r1 = i     // Catch:{ Throwable -> 0x0147 }
            r1.unlock()     // Catch:{ Throwable -> 0x0147 }
        L_0x0124:
            r6.b()
            goto L_0x0008
        L_0x0129:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r1)
            goto L_0x0117
        L_0x0147:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsRenameLock.unlock exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r1)
            goto L_0x0124
        L_0x0161:
            r0 = move-exception
            r2 = r3
        L_0x0163:
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0225 }
            r4.<init>()     // Catch:{ all -> 0x0225 }
            java.lang.String r5 = "TbsInstaller--getTbsCoreInstalledVerWithLock Exception="
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0225 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0225 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ all -> 0x0225 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0225 }
            com.tencent.smtt.utils.TbsLog.i(r3, r0)     // Catch:{ all -> 0x0225 }
            if (r2 == 0) goto L_0x0184
            r2.close()     // Catch:{ IOException -> 0x0197 }
        L_0x0184:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x01b5 }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x01b5 }
            if (r0 == 0) goto L_0x0191
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x01b5 }
            r0.unlock()     // Catch:{ Throwable -> 0x01b5 }
        L_0x0191:
            r6.b()
            r0 = r1
            goto L_0x0008
        L_0x0197:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            goto L_0x0184
        L_0x01b5:
            r0 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsRenameLock.unlock exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
            goto L_0x0191
        L_0x01cf:
            r0 = move-exception
            r2 = r3
        L_0x01d1:
            if (r2 == 0) goto L_0x01d6
            r2.close()     // Catch:{ IOException -> 0x01e7 }
        L_0x01d6:
            java.util.concurrent.locks.ReentrantLock r1 = i     // Catch:{ Throwable -> 0x0205 }
            boolean r1 = r1.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x0205 }
            if (r1 == 0) goto L_0x01e3
            java.util.concurrent.locks.ReentrantLock r1 = i     // Catch:{ Throwable -> 0x0205 }
            r1.unlock()     // Catch:{ Throwable -> 0x0205 }
        L_0x01e3:
            r6.b()
            throw r0
        L_0x01e7:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerWithLock IOException="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r1)
            goto L_0x01d6
        L_0x0205:
            r1 = move-exception
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TbsRenameLock.unlock exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r1)
            goto L_0x01e3
        L_0x021f:
            r6.b()
            r0 = r1
            goto L_0x0008
        L_0x0225:
            r0 = move-exception
            goto L_0x01d1
        L_0x0227:
            r0 = move-exception
            goto L_0x0163
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.m(android.content.Context):int");
    }

    private void A(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        f.a(q(context), false);
    }

    private void B(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File f2 = f(context, 0);
        File q2 = q(context);
        if (f2 == null || q2 == null) {
            TbsLog.i("TbsInstaller", "renameTbsCoreShareDir return,tmpTbsCoreUnzipDir=" + f2 + "tbsSharePath=" + q2);
            return;
        }
        boolean renameTo = f2.renameTo(q2);
        TbsLog.i("TbsInstaller", "renameTbsCoreShareDir rename success=" + renameTo);
        if (context != null && TbsConfig.APP_WX.equals(StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().packageName)) {
            if (renameTo) {
                TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.RENAME_SUCCESS, " ");
            } else {
                TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.RENAME_FAIL, " ");
            }
        }
        g(context, false);
    }

    public boolean n(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple #0");
        File q2 = q(context);
        File p2 = p(context);
        try {
            f.a(p2, true);
            f.a(q2, p2, (FileFilter) new FileFilter() {
                public boolean accept(File file) {
                    return !file.getName().endsWith(".dex") && !file.getName().endsWith(".jar_is_first_load_dex_flag_file");
                }
            });
            TbsShareManager.b(context);
            TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple success!!!");
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private void C(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File f2 = f(context, 1);
        File q2 = q(context);
        if (f2 != null && q2 != null) {
            f2.renameTo(q2);
            g(context, false);
        }
    }

    private void D(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsTpatchCoreDir");
        File f2 = f(context, 5);
        File q2 = q(context);
        if (f2 != null && q2 != null) {
            f2.renameTo(q2);
            g(context, false);
        }
    }

    private void E(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File f2 = f(context, 0);
        if (f2 != null) {
            f.a(f2, false);
        }
        j.a(context).c(0, 5);
        j.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    /* access modifiers changed from: 0000 */
    public void o(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        j.a(context).a(0);
        j.a(context).b(0);
        j.a(context).d(0);
        j.a(context).a("incrupdate_retry_num", 0);
        if (!TbsDownloader.a(context)) {
            j.a(context).c(0, -1);
            j.a(context).a("");
            j.a(context).a("copy_retry_num", 0);
            j.a(context).c(-1);
            j.a(context).a(0, -1);
            f.a(f(context, 0), true);
            f.a(f(context, 1), true);
        }
    }

    /* access modifiers changed from: 0000 */
    public File b(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share");
        if (file == null) {
            TbsLog.i("TbsInstaller", "getTbsCoreShareDir,tbsShareDir = null");
            return null;
        } else if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        } else {
            TbsLog.i("TbsInstaller", "getTbsCoreShareDir,mkdir false");
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public File p(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_share_decouple");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public File c(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share_decouple");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public File q(Context context) {
        return b((Context) null, context);
    }

    /* access modifiers changed from: 0000 */
    public File r(Context context) {
        File file = new File(context.getDir("tbs", 0), "share");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    static File s(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public File f(Context context, int i2) {
        return a(context, i2, true);
    }

    /* access modifiers changed from: 0000 */
    public File a(Context context, int i2, boolean z) {
        File dir = context.getDir("tbs", 0);
        String str = "";
        switch (i2) {
            case 0:
                str = "core_unzip_tmp";
                break;
            case 1:
                str = "core_copy_tmp";
                break;
            case 2:
                str = "core_unzip_tmp_decouple";
                break;
            case 3:
                str = "core_share_backup";
                break;
            case 4:
                str = "core_share_backup_tmp";
                break;
            case 5:
                str = "tpatch_tmp";
                break;
            case 6:
                str = "tpatch_decouple_tmp";
                break;
        }
        TbsLog.i("TbsInstaller", "type=" + i2 + "needMakeDir=" + z + "folder=" + str);
        File file = new File(dir, str);
        if (file != null) {
            if (!file.isDirectory()) {
                if (!z) {
                    TbsLog.i("TbsInstaller", "getCoreDir,no need mkdir");
                    return null;
                } else if (!file.mkdir()) {
                    TbsLog.i("TbsInstaller", "getCoreDir,mkdir false");
                    return null;
                }
            }
            return file;
        }
        TbsLog.i("TbsInstaller", "getCoreDir,tmpTbsShareDir = null");
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean g(Context context, int i2) {
        File file;
        Long[][] lArr;
        try {
            boolean isThirdPartyApp = TbsShareManager.isThirdPartyApp(context);
            if (!isThirdPartyApp) {
                file = q(context);
            } else if (TbsShareManager.j(context)) {
                File file2 = new File(TbsShareManager.c(context));
                if (file2.getAbsolutePath().contains(TbsConfig.APP_DEMO)) {
                    return true;
                }
                file = file2;
            } else {
                TbsLog.e("TbsInstaller", "321");
                return false;
            }
            if (file != null) {
                for (Long[] lArr2 : n) {
                    if (i2 == lArr2[0].intValue()) {
                        File file3 = new File(file, "libmttwebview.so");
                        if (file3 == null || !file3.exists() || file3.length() != lArr2[1].longValue()) {
                            if (!isThirdPartyApp) {
                                f.b(context.getDir("tbs", 0));
                            }
                            a.set(Integer.valueOf(0));
                            TbsLog.e("TbsInstaller", "322");
                            return false;
                        }
                        TbsLog.d("TbsInstaller", "check so success: " + i2 + "; file: " + file3);
                        return true;
                    }
                }
                return true;
            }
            TbsLog.e("TbsInstaller", "323");
            return false;
        } catch (Throwable th) {
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getMessage is " + th.getMessage());
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getCause is " + th.getCause());
            return false;
        }
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean t(Context context) {
        boolean z = true;
        synchronized (this) {
            if (this.e > 0) {
                TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
                this.e++;
            } else {
                this.g = f.b(context, true, "tbslock.txt");
                if (this.g != null) {
                    this.f = f.a(context, this.g);
                    if (this.f == null) {
                        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock tbsFileLockFileLock == null");
                        z = false;
                    } else {
                        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
                        this.e++;
                    }
                } else {
                    TbsLog.i("TbsInstaller", "getTbsInstallingFileLock get install fos failed");
                    z = false;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void b() {
        if (this.e <= 0) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock currentTbsFileLockStackCount=" + this.e + "call stack:" + Log.getStackTraceString(new Throwable()));
        } else if (this.e > 1) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
            this.e--;
        } else if (this.e == 1) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
            f.a(this.f, this.g);
            this.e = 0;
        }
    }

    private boolean b(Context context, File file) {
        try {
            File[] listFiles = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.getName().endsWith(".jar");
                }
            });
            int length = listFiles.length;
            if (VERSION.SDK_INT < 16 && context.getPackageName() != null && context.getPackageName().equalsIgnoreCase(TbsConfig.APP_DEMO)) {
                try {
                    Thread.sleep(Config.BPLUS_DELAY_TIME);
                } catch (Exception e2) {
                }
            }
            ClassLoader classLoader = context.getClassLoader();
            for (int i2 = 0; i2 < length; i2++) {
                TbsLog.i("TbsInstaller", "jarFile: " + listFiles[i2].getAbsolutePath());
                new DexClassLoader(listFiles[i2].getAbsolutePath(), file.getAbsolutePath(), null, classLoader);
            }
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.DEXOPT_EXCEPTION, e3.toString());
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return false;
        }
    }

    private boolean c(Context context, File file) {
        File[] listFiles;
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
            String a2 = c.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a2)) {
                TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.DEXOAT_EXCEPTION, "can not find oat command");
                return false;
            }
            for (File file4 : file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.getName().endsWith(".jar");
                }
            })) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a2.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().q(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode((int) ErrorCode.DEXOAT_EXCEPTION, (Throwable) e2);
            return false;
        }
    }
}
