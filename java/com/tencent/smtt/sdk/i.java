package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.f;
import com.tencent.smtt.utils.q;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: TbsApkDownloader */
class i {
    private static int d = 5;
    private static int e = 1;
    private static final String[] f = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    private Set<String> A;
    private int B = d;
    private boolean C;
    String a;
    String[] b = null;
    int c = 0;
    private Context g;
    private String h;
    private String i;
    private String j;
    private File k;
    private long l;
    private int m = Config.SESSION_PERIOD;
    private int n = 20000;
    private boolean o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private int f436q;
    private boolean r;
    private boolean s;
    private HttpURLConnection t;
    private String u;
    private TbsLogInfo v;
    private String w;
    private int x;
    private boolean y;
    private Handler z;

    public i(Context context) throws NullPointerException {
        this.g = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.v = TbsLogReport.getInstance(this.g).tbsLogInfo();
        this.A = new HashSet();
        this.u = "tbs_downloading_" + this.g.getPackageName();
        l.a();
        this.k = l.s(this.g);
        if (this.k == null) {
            throw new NullPointerException("TbsCorePrivateDir is null!");
        }
        e();
        this.w = null;
        this.x = -1;
    }

    private void e() {
        this.p = 0;
        this.f436q = 0;
        this.l = -1;
        this.j = null;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
    }

    private void a(String str) throws Exception {
        URL url = new URL(str);
        if (this.t != null) {
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        this.t = (HttpURLConnection) url.openConnection();
        this.t.setRequestProperty("User-Agent", TbsDownloader.b(this.g));
        this.t.setRequestProperty("Accept-Encoding", "identity");
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.n);
        this.t.setReadTimeout(this.m);
    }

    private void f() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        if (this.t != null) {
            if (!this.r) {
                this.v.setResolveIp(a(this.t.getURL()));
            }
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.t = null;
        }
        int i2 = this.v.a;
        if (this.r || !this.y) {
            TbsDownloader.a = false;
            return;
        }
        this.v.setEventTime(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.g);
        if (apnInfo == null) {
            apnInfo = "";
        }
        int apnType = Apn.getApnType(this.g);
        this.v.setApn(apnInfo);
        this.v.setNetworkType(apnType);
        if (apnType != this.x || !apnInfo.equals(this.w)) {
            this.v.setNetworkChange(0);
        }
        if ((this.v.a == 0 || this.v.a == 107) && this.v.getDownFinalFlag() == 0) {
            if (!Apn.isNetworkAvailable(this.g)) {
                a(101, (String) null, true);
            } else if (!k()) {
                a(101, (String) null, true);
            }
        }
        if (TbsDownloader.a(this.g)) {
            TbsLogReport.getInstance(this.g).eventReport(EventType.TYPE_DOWNLOAD_DECOUPLE, this.v);
        } else {
            TbsLogReport.getInstance(this.g).eventReport(EventType.TYPE_DOWNLOAD, this.v);
        }
        this.v.resetArgs();
        if (i2 != 100) {
            QbSdk.m.onDownloadFinish(i2);
        }
    }

    private boolean b(int i2) {
        try {
            File file = new File(this.k, "x5.tbs");
            File a2 = a(this.g);
            if (a2 == null) {
                return false;
            }
            File file2 = new File(a2, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            file.delete();
            f.b(file2, file);
            if (a.a(this.g, file, 0, i2)) {
                return true;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] verifyTbsApk error!!");
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e2.getMessage());
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(boolean r10, boolean r11) {
        /*
            r9 = this;
            r8 = -214(0xffffffffffffff2a, float:NaN)
            r1 = 1
            r2 = 0
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r0 != r3) goto L_0x000b
        L_0x000a:
            return r2
        L_0x000b:
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r3 = "use_backup_version"
            int r0 = r0.getInt(r3, r2)
            com.tencent.smtt.sdk.l r3 = com.tencent.smtt.sdk.l.a()
            android.content.Context r4 = r9.g
            int r4 = r3.i(r4)
            if (r0 != 0) goto L_0x00d7
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r3 = "tbs_download_version"
            int r0 = r0.getInt(r3, r2)
            java.lang.String r3 = "by default key"
            r9.a = r3
            r3 = r0
        L_0x0038:
            if (r3 == 0) goto L_0x000a
            if (r3 == r4) goto L_0x000a
            if (r11 == 0) goto L_0x0106
            java.io.File r4 = com.tencent.smtt.sdk.TbsDownloader.a(r3)
            if (r4 == 0) goto L_0x00e6
            boolean r0 = r4.exists()
            if (r0 == 0) goto L_0x00e6
            java.io.File r5 = new java.io.File
            android.content.Context r0 = r9.g
            r6 = 4
            java.lang.String r6 = com.tencent.smtt.utils.f.a(r0, r6)
            android.content.Context r0 = r9.g
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r0)
            if (r0 == 0) goto L_0x00de
            java.lang.String r0 = "x5.oversea.tbs.org"
        L_0x005d:
            r5.<init>(r6, r0)
            android.content.Context r0 = r9.g     // Catch:{ Exception -> 0x00e2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Exception -> 0x00e2 }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x00e2 }
            java.lang.String r6 = "tbs_download_version_type"
            r7 = 0
            int r0 = r0.getInt(r6, r7)     // Catch:{ Exception -> 0x00e2 }
            if (r0 == r1) goto L_0x00e6
            com.tencent.smtt.utils.f.b(r4, r5)     // Catch:{ Exception -> 0x00e2 }
            r0 = r1
        L_0x0075:
            boolean r5 = r9.g()
            if (r5 == 0) goto L_0x00f6
            boolean r3 = r9.b(r3)
            if (r3 == 0) goto L_0x0106
            android.content.Context r3 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r3.a
            java.lang.String r4 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
            r3.put(r4, r5)
            android.content.Context r3 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            r3.setDownloadInterruptCode(r8)
            r9.c(r2)
            if (r0 == 0) goto L_0x00d4
            r0 = 100
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "use local backup apk in startDownload"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r9.a
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r9.a(r0, r2, r1)
            android.content.Context r0 = r9.g
            boolean r0 = com.tencent.smtt.sdk.TbsDownloader.a(r0)
            if (r0 == 0) goto L_0x00e8
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r3 = r9.v
            r0.eventReport(r2, r3)
        L_0x00cf:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r9.v
            r0.resetArgs()
        L_0x00d4:
            r2 = r1
            goto L_0x000a
        L_0x00d7:
            java.lang.String r3 = "by new key"
            r9.a = r3
            r3 = r0
            goto L_0x0038
        L_0x00de:
            java.lang.String r0 = "x5.tbs.org"
            goto L_0x005d
        L_0x00e2:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00e6:
            r0 = r2
            goto L_0x0075
        L_0x00e8:
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r3 = r9.v
            r0.eventReport(r2, r3)
            goto L_0x00cf
        L_0x00f6:
            r9.h()
            android.content.Context r0 = r9.g
            r6 = 0
            boolean r0 = com.tencent.smtt.utils.a.a(r0, r4, r6, r3)
            if (r0 != 0) goto L_0x0106
            com.tencent.smtt.utils.f.b(r4)
        L_0x0106:
            boolean r0 = r9.c(r2, r11)
            if (r0 == 0) goto L_0x012c
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            r0.put(r3, r4)
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            r0.setDownloadInterruptCode(r8)
            r9.c(r2)
            r2 = r1
            goto L_0x000a
        L_0x012c:
            boolean r0 = r9.d(r1)
            if (r0 != 0) goto L_0x000a
            boolean r0 = r9.d(r1)
            if (r0 != 0) goto L_0x000a
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsApkDownloader] delete file failed!"
            com.tencent.smtt.utils.TbsLog.e(r0, r1)
            android.content.Context r0 = r9.g
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            r1 = -301(0xfffffffffffffed3, float:NaN)
            r0.setDownloadInterruptCode(r1)
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.i.a(boolean, boolean):boolean");
    }

    public boolean a() {
        TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #1");
        try {
            File file = new File(f.a(this.g, 4), "x5.tbs.decouple");
            if (file == null || !file.exists()) {
                File b2 = TbsDownloader.b(TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_DECOUPLECOREVERSION, -1));
                if (b2 != null && b2.exists()) {
                    f.b(b2, file);
                }
            } else {
                TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #2");
            }
            if (a.a(this.g, file, 0, TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_DECOUPLECOREVERSION, -1))) {
                TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #3");
                return l.a().e(this.g);
            }
        } catch (Exception e2) {
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0733, code lost:
        if (r33 != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0735, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r4));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r32.g).commit();
     */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0610  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0830 A[Catch:{ all -> 0x0b81 }] */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0861  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:494:0x01b8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(boolean r33, boolean r34) {
        /*
            r32 = this;
            com.tencent.smtt.sdk.l r4 = com.tencent.smtt.sdk.l.a()
            r0 = r32
            android.content.Context r5 = r0.g
            boolean r4 = r4.c(r5)
            if (r4 == 0) goto L_0x0021
            if (r33 != 0) goto L_0x0021
            r4 = 0
            com.tencent.smtt.sdk.TbsDownloader.a = r4
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -322(0xfffffffffffffebe, float:NaN)
            r4.setDownloadInterruptCode(r5)
        L_0x0020:
            return
        L_0x0021:
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            android.content.SharedPreferences r4 = r4.mPreferences
            java.lang.String r5 = "tbs_responsecode"
            r6 = 0
            int r4 = r4.getInt(r5, r6)
            r5 = 1
            if (r4 == r5) goto L_0x003b
            r5 = 2
            if (r4 == r5) goto L_0x003b
            r5 = 4
            if (r4 != r5) goto L_0x0050
        L_0x003b:
            r4 = 1
            r17 = r4
        L_0x003e:
            if (r34 != 0) goto L_0x0054
            r0 = r32
            r1 = r33
            r2 = r17
            boolean r4 = r0.a(r1, r2)
            if (r4 == 0) goto L_0x0054
            r4 = 0
            com.tencent.smtt.sdk.TbsDownloader.a = r4
            goto L_0x0020
        L_0x0050:
            r4 = 0
            r17 = r4
            goto L_0x003e
        L_0x0054:
            r0 = r33
            r1 = r32
            r1.C = r0
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            android.content.SharedPreferences r4 = r4.mPreferences
            java.lang.String r5 = "tbs_downloadurl"
            r6 = 0
            java.lang.String r4 = r4.getString(r5, r6)
            r0 = r32
            r0.h = r4
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            android.content.SharedPreferences r4 = r4.mPreferences
            java.lang.String r5 = "tbs_downloadurl_list"
            r6 = 0
            java.lang.String r4 = r4.getString(r5, r6)
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "backupUrlStrings:"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r4)
            java.lang.String r6 = r6.toString()
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r5, r6, r7)
            r5 = 0
            r0 = r32
            r0.b = r5
            r5 = 0
            r0 = r32
            r0.c = r5
            if (r33 != 0) goto L_0x00c1
            if (r4 == 0) goto L_0x00c1
            java.lang.String r5 = ""
            java.lang.String r6 = r4.trim()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x00c1
            java.lang.String r5 = r4.trim()
            java.lang.String r6 = ";"
            java.lang.String[] r5 = r5.split(r6)
            r0 = r32
            r0.b = r5
        L_0x00c1:
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "[TbsApkDownloader.startDownload] mDownloadUrl="
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r32
            java.lang.String r7 = r0.h
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " backupUrlStrings="
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r6 = " mLocation="
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r32
            java.lang.String r6 = r0.j
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r6 = " mCanceled="
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r32
            boolean r6 = r0.r
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r6 = " mHttpRequest="
            java.lang.StringBuilder r4 = r4.append(r6)
            r0 = r32
            java.net.HttpURLConnection r6 = r0.t
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r5, r4)
            r0 = r32
            java.lang.String r4 = r0.h
            if (r4 != 0) goto L_0x0133
            r0 = r32
            java.lang.String r4 = r0.j
            if (r4 != 0) goto L_0x0133
            com.tencent.smtt.sdk.TbsListener r4 = com.tencent.smtt.sdk.QbSdk.m
            r5 = 110(0x6e, float:1.54E-43)
            r4.onDownloadFinish(r5)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -302(0xfffffffffffffed2, float:NaN)
            r4.setDownloadInterruptCode(r5)
            goto L_0x0020
        L_0x0133:
            r0 = r32
            java.net.HttpURLConnection r4 = r0.t
            if (r4 == 0) goto L_0x0155
            r0 = r32
            boolean r4 = r0.r
            if (r4 != 0) goto L_0x0155
            com.tencent.smtt.sdk.TbsListener r4 = com.tencent.smtt.sdk.QbSdk.m
            r5 = 110(0x6e, float:1.54E-43)
            r4.onDownloadFinish(r5)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -303(0xfffffffffffffed1, float:NaN)
            r4.setDownloadInterruptCode(r5)
            goto L_0x0020
        L_0x0155:
            if (r33 != 0) goto L_0x0186
            r0 = r32
            java.util.Set<java.lang.String> r4 = r0.A
            r0 = r32
            android.content.Context r5 = r0.g
            java.lang.String r5 = com.tencent.smtt.utils.Apn.getWifiSSID(r5)
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x0186
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "[TbsApkDownloader.startDownload] WIFI Unavailable"
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            com.tencent.smtt.sdk.TbsListener r4 = com.tencent.smtt.sdk.QbSdk.m
            r5 = 110(0x6e, float:1.54E-43)
            r4.onDownloadFinish(r5)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -304(0xfffffffffffffed0, float:NaN)
            r4.setDownloadInterruptCode(r5)
            goto L_0x0020
        L_0x0186:
            r32.e()
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "STEP 1/2 begin downloading..."
            r6 = 1
            com.tencent.smtt.utils.TbsLog.i(r4, r5, r6)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            long r24 = r4.getDownloadMaxflow()
            r6 = 0
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            android.content.SharedPreferences r4 = r4.mPreferences
            java.lang.String r5 = "tbs_downloadflow"
            r8 = 0
            long r4 = r4.getLong(r5, r8)
            if (r33 == 0) goto L_0x0246
            int r7 = e
            r0 = r32
            r0.B = r7
        L_0x01b8:
            r0 = r32
            int r7 = r0.p
            r0 = r32
            int r8 = r0.B
            if (r7 <= r8) goto L_0x024e
        L_0x01c2:
            r0 = r32
            boolean r4 = r0.r
            if (r4 != 0) goto L_0x0241
            r0 = r32
            boolean r4 = r0.s
            if (r4 == 0) goto L_0x0214
            r0 = r32
            java.lang.String[] r4 = r0.b
            if (r4 != 0) goto L_0x01df
            if (r6 != 0) goto L_0x01df
            r4 = 1
            r0 = r32
            r1 = r17
            boolean r6 = r0.c(r4, r1)
        L_0x01df:
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r0.v
            if (r6 == 0) goto L_0x0ead
            r4 = 1
        L_0x01e6:
            r5.setUnpkgFlag(r4)
            if (r17 != 0) goto L_0x0eb3
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r0.v
            if (r6 == 0) goto L_0x0eb0
            r4 = 1
        L_0x01f2:
            r5.setPatchUpdateFlag(r4)
        L_0x01f5:
            if (r6 == 0) goto L_0x0ebd
            r4 = 1
            r0 = r32
            r0.c(r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -317(0xfffffffffffffec3, float:NaN)
            r4.setDownloadInterruptCode(r5)
            r4 = 100
            java.lang.String r5 = "success"
            r7 = 1
            r0 = r32
            r0.a(r4, r5, r7)
        L_0x0214:
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            if (r6 == 0) goto L_0x0ed2
            android.content.SharedPreferences r5 = r4.mPreferences
            java.lang.String r7 = "tbs_download_success_retrytimes"
            r8 = 0
            int r5 = r5.getInt(r7, r8)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r4.a
            java.lang.String r8 = "tbs_download_success_retrytimes"
            int r5 = r5 + 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r7.put(r8, r5)
        L_0x0234:
            r4.commit()
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r0.v
            if (r6 == 0) goto L_0x0ef8
            r4 = 1
        L_0x023e:
            r5.setDownFinalFlag(r4)
        L_0x0241:
            r32.f()
            goto L_0x0020
        L_0x0246:
            int r7 = d
            r0 = r32
            r0.B = r7
            goto L_0x01b8
        L_0x024e:
            r0 = r32
            int r7 = r0.f436q
            r8 = 8
            if (r7 <= r8) goto L_0x026e
            r4 = 123(0x7b, float:1.72E-43)
            r5 = 0
            r7 = 1
            r0 = r32
            r0.a(r4, r5, r7)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -306(0xfffffffffffffece, float:NaN)
            r4.setDownloadInterruptCode(r5)
            goto L_0x01c2
        L_0x026e:
            long r20 = java.lang.System.currentTimeMillis()
            if (r33 != 0) goto L_0x0372
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            android.content.SharedPreferences r7 = r7.mPreferences     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "tbs_downloadstarttime"
            r10 = 0
            long r8 = r7.getLong(r8, r10)     // Catch:{ Throwable -> 0x056e }
            long r8 = r20 - r8
            r10 = 86400000(0x5265c00, double:4.2687272E-316)
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x0315
            java.lang.String r7 = "TbsDownload"
            java.lang.String r8 = "[TbsApkDownloader.startDownload] OVER DOWNLOAD_PERIOD"
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "tbs_downloadstarttime"
            java.lang.Long r9 = java.lang.Long.valueOf(r20)     // Catch:{ Throwable -> 0x056e }
            r7.put(r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "tbs_downloadflow"
            r10 = 0
            java.lang.Long r9 = java.lang.Long.valueOf(r10)     // Catch:{ Throwable -> 0x056e }
            r7.put(r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r7.commit()     // Catch:{ Throwable -> 0x056e }
            r8 = 0
        L_0x02cb:
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            boolean r4 = com.tencent.smtt.utils.f.b(r4)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            if (r4 != 0) goto L_0x0371
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "DownloadBegin FreeSpace too small"
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r4, r5, r7)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r4 = 105(0x69, float:1.47E-43)
            r5 = 0
            r7 = 1
            r0 = r32
            r0.a(r4, r5, r7)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r5 = -308(0xfffffffffffffecc, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r4.a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r7 = java.lang.Long.valueOf(r8)
            r4.put(r5, r7)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0315:
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r8.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "[TbsApkDownloader.startDownload] downloadFlow="
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r8 = r8.append(r4)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ Throwable -> 0x056e }
            int r7 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r7 < 0) goto L_0x0f4f
            java.lang.String r7 = "TbsDownload"
            java.lang.String r8 = "STEP 1/2 begin downloading...failed because you exceeded max flow!"
            r9 = 1
            com.tencent.smtt.utils.TbsLog.i(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r7 = 112(0x70, float:1.57E-43)
            r8 = 0
            r9 = 1
            r0 = r32
            r0.a(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -307(0xfffffffffffffecd, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0371:
            r4 = r8
        L_0x0372:
            r7 = 1
            r0 = r32
            r0.y = r7     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            java.lang.String r7 = r0.j     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x0511
            r0 = r32
            java.lang.String r7 = r0.j     // Catch:{ Throwable -> 0x056e }
        L_0x0381:
            java.lang.String r8 = "TbsDownload"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r9.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = "try url:"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r9 = r9.append(r7)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = ",mRetryTimes:"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            int r10 = r0.p     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x056e }
            r10 = 1
            com.tencent.smtt.utils.TbsLog.i(r8, r9, r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            java.lang.String r8 = r0.i     // Catch:{ Throwable -> 0x056e }
            boolean r8 = r7.equals(r8)     // Catch:{ Throwable -> 0x056e }
            if (r8 != 0) goto L_0x03b9
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r8 = r0.v     // Catch:{ Throwable -> 0x056e }
            r8.setDownloadUrl(r7)     // Catch:{ Throwable -> 0x056e }
        L_0x03b9:
            r0 = r32
            r0.i = r7     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r7)     // Catch:{ Throwable -> 0x056e }
            r18 = 0
            r0 = r32
            boolean r7 = r0.o     // Catch:{ Throwable -> 0x056e }
            if (r7 != 0) goto L_0x0431
            long r18 = r32.j()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r8.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "[TbsApkDownloader.startDownload] range="
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r18
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r8 = r0.l     // Catch:{ Throwable -> 0x056e }
            r10 = 0
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 > 0) goto L_0x0517
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r8.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "STEP 1/2 begin downloading...current"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r18
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x056e }
            r9 = 1
            com.tencent.smtt.utils.TbsLog.i(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "Range"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r9.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = "bytes="
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r18
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = "-"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x056e }
            r7.setRequestProperty(r8, r9)     // Catch:{ Throwable -> 0x056e }
        L_0x0431:
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r8 = r0.v     // Catch:{ Throwable -> 0x056e }
            r10 = 0
            int r7 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1))
            if (r7 != 0) goto L_0x05e6
            r7 = 0
        L_0x043c:
            r8.setDownloadCancel(r7)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            int r7 = com.tencent.smtt.utils.Apn.getApnType(r7)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r8 = r0.g     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = com.tencent.smtt.utils.Apn.getApnInfo(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            java.lang.String r9 = r0.w     // Catch:{ Throwable -> 0x056e }
            if (r9 != 0) goto L_0x05e9
            r0 = r32
            int r9 = r0.x     // Catch:{ Throwable -> 0x056e }
            r10 = -1
            if (r9 != r10) goto L_0x05e9
            r0 = r32
            r0.w = r8     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.x = r7     // Catch:{ Throwable -> 0x056e }
        L_0x0464:
            r0 = r32
            int r7 = r0.p     // Catch:{ Throwable -> 0x056e }
            r8 = 1
            if (r7 < r8) goto L_0x0478
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "Referer"
            r0 = r32
            java.lang.String r9 = r0.h     // Catch:{ Throwable -> 0x056e }
            r7.addRequestProperty(r8, r9)     // Catch:{ Throwable -> 0x056e }
        L_0x0478:
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            int r7 = r7.getResponseCode()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "TbsDownload"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r9.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = "[TbsApkDownloader.startDownload] responseCode="
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r9 = r9.append(r7)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.utils.TbsLog.i(r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r8 = r0.v     // Catch:{ Throwable -> 0x056e }
            r8.setHttpCode(r7)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x04dc
            r0 = r32
            android.content.Context r8 = r0.g     // Catch:{ Throwable -> 0x056e }
            boolean r8 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r8)     // Catch:{ Throwable -> 0x056e }
            if (r8 != 0) goto L_0x04dc
            r0 = r32
            android.content.Context r8 = r0.g     // Catch:{ Throwable -> 0x056e }
            int r8 = com.tencent.smtt.utils.Apn.getApnType(r8)     // Catch:{ Throwable -> 0x056e }
            r9 = 3
            if (r8 != r9) goto L_0x04c0
            r0 = r32
            android.content.Context r8 = r0.g     // Catch:{ Throwable -> 0x056e }
            int r8 = com.tencent.smtt.utils.Apn.getApnType(r8)     // Catch:{ Throwable -> 0x056e }
            if (r8 != 0) goto L_0x04dc
        L_0x04c0:
            boolean r8 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ Throwable -> 0x056e }
            if (r8 != 0) goto L_0x04dc
            r32.b()     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsListener r8 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Throwable -> 0x056e }
            if (r8 == 0) goto L_0x04d4
            com.tencent.smtt.sdk.TbsListener r8 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Throwable -> 0x056e }
            r9 = 111(0x6f, float:1.56E-43)
            r8.onDownloadFinish(r9)     // Catch:{ Throwable -> 0x056e }
        L_0x04d4:
            java.lang.String r8 = "TbsDownload"
            java.lang.String r9 = "Download is canceled due to NOT_WIFI error!"
            r10 = 0
            com.tencent.smtt.utils.TbsLog.i(r8, r9, r10)     // Catch:{ Throwable -> 0x056e }
        L_0x04dc:
            r0 = r32
            boolean r8 = r0.r     // Catch:{ Throwable -> 0x056e }
            if (r8 == 0) goto L_0x062f
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -309(0xfffffffffffffecb, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0511:
            r0 = r32
            java.lang.String r7 = r0.h     // Catch:{ Throwable -> 0x056e }
            goto L_0x0381
        L_0x0517:
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r8.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "#1 STEP 1/2 begin downloading...current/total="
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r18
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "/"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r10 = r0.l     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x056e }
            r9 = 1
            com.tencent.smtt.utils.TbsLog.i(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "Range"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r9.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = "bytes="
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r18
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = "-"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r10 = r0.l     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x056e }
            r7.setRequestProperty(r8, r9)     // Catch:{ Throwable -> 0x056e }
            goto L_0x0431
        L_0x056e:
            r7 = move-exception
        L_0x056f:
            boolean r8 = r7 instanceof javax.net.ssl.SSLHandshakeException     // Catch:{ all -> 0x060b }
            if (r8 == 0) goto L_0x0e60
            if (r33 != 0) goto L_0x0e60
            r0 = r32
            java.lang.String[] r8 = r0.b     // Catch:{ all -> 0x060b }
            if (r8 == 0) goto L_0x0e60
            r8 = 0
            r0 = r32
            boolean r8 = r0.a(r8)     // Catch:{ all -> 0x060b }
            if (r8 == 0) goto L_0x0e60
            java.lang.String r8 = "TbsDownload"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x060b }
            r9.<init>()     // Catch:{ all -> 0x060b }
            java.lang.String r10 = "[startdownload]url:"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x060b }
            r0 = r32
            java.lang.String r10 = r0.j     // Catch:{ all -> 0x060b }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x060b }
            java.lang.String r10 = " download exception："
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x060b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x060b }
            java.lang.StringBuilder r7 = r9.append(r7)     // Catch:{ all -> 0x060b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x060b }
            com.tencent.smtt.utils.TbsLog.e(r8, r7)     // Catch:{ all -> 0x060b }
            r7 = 125(0x7d, float:1.75E-43)
            r8 = 0
            r9 = 1
            r0 = r32
            r0.a(r7, r8, r9)     // Catch:{ all -> 0x060b }
        L_0x05b7:
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ all -> 0x060b }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x060b }
            r8 = -316(0xfffffffffffffec4, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ all -> 0x060b }
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x05e6:
            r7 = 1
            goto L_0x043c
        L_0x05e9:
            r0 = r32
            int r9 = r0.x     // Catch:{ Throwable -> 0x056e }
            if (r7 != r9) goto L_0x05f9
            r0 = r32
            java.lang.String r9 = r0.w     // Catch:{ Throwable -> 0x056e }
            boolean r9 = r8.equals(r9)     // Catch:{ Throwable -> 0x056e }
            if (r9 != 0) goto L_0x0464
        L_0x05f9:
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r9 = r0.v     // Catch:{ Throwable -> 0x056e }
            r10 = 0
            r9.setNetworkChange(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.w = r8     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.x = r7     // Catch:{ Throwable -> 0x056e }
            goto L_0x0464
        L_0x060b:
            r6 = move-exception
            r8 = r4
            r4 = r6
        L_0x060e:
            if (r33 != 0) goto L_0x062e
            r0 = r32
            android.content.Context r5 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.a
            java.lang.String r6 = "tbs_downloadflow"
            java.lang.Long r7 = java.lang.Long.valueOf(r8)
            r5.put(r6, r7)
            r0 = r32
            android.content.Context r5 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            r5.commit()
        L_0x062e:
            throw r4
        L_0x062f:
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 == r8) goto L_0x0637
            r8 = 206(0xce, float:2.89E-43)
            if (r7 != r8) goto L_0x0b9f
        L_0x0637:
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            int r7 = r7.getContentLength()     // Catch:{ Throwable -> 0x056e }
            long r8 = (long) r7     // Catch:{ Throwable -> 0x056e }
            long r8 = r8 + r18
            r0 = r32
            r0.l = r8     // Catch:{ Throwable -> 0x056e }
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r8.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "[TbsApkDownloader.startDownload] mContentLength="
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r10 = r0.l     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r7 = r0.v     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r8 = r0.l     // Catch:{ Throwable -> 0x056e }
            r7.setPkgSize(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            android.content.SharedPreferences r7 = r7.mPreferences     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "tbs_apkfilesize"
            r10 = 0
            long r8 = r7.getLong(r8, r10)     // Catch:{ Throwable -> 0x056e }
            r10 = 0
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 == 0) goto L_0x076d
            r0 = r32
            long r10 = r0.l     // Catch:{ Throwable -> 0x056e }
            int r7 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r7 == 0) goto L_0x076d
            java.lang.String r7 = "TbsDownload"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r10.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r11 = "DownloadBegin tbsApkFileSize="
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r10 = r10.append(r8)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r11 = "  but contentLength="
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r12 = r0.l     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r10 = r10.append(r12)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x056e }
            r11 = 1
            com.tencent.smtt.utils.TbsLog.i(r7, r10, r11)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x0755
            boolean r7 = r32.m()     // Catch:{ Throwable -> 0x056e }
            if (r7 != 0) goto L_0x06cc
            boolean r7 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x0755
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            boolean r7 = com.tencent.smtt.utils.Apn.isNetworkAvailable(r7)     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x0755
        L_0x06cc:
            r0 = r32
            java.lang.String[] r7 = r0.b     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x06fd
            r7 = 0
            r0 = r32
            boolean r7 = r0.a(r7)     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x06fd
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x06fd:
            r7 = 113(0x71, float:1.58E-43)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x056e }
            r10.<init>()     // Catch:{ Throwable -> 0x056e }
            java.lang.String r11 = "tbsApkFileSize="
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r8 = r10.append(r8)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r9 = "  but contentLength="
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            long r10 = r0.l     // Catch:{ Throwable -> 0x056e }
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x056e }
            r9 = 1
            r0 = r32
            r0.a(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -310(0xfffffffffffffeca, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
        L_0x0733:
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0755:
            r7 = 101(0x65, float:1.42E-43)
            java.lang.String r8 = "WifiNetworkUnAvailable"
            r9 = 1
            r0 = r32
            r0.a(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -304(0xfffffffffffffed0, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            goto L_0x0733
        L_0x076d:
            r10 = 0
            r9 = 0
            r8 = 0
            java.lang.String r7 = "TbsDownload"
            java.lang.String r11 = "[TbsApkDownloader.startDownload] begin readResponse"
            com.tencent.smtt.utils.TbsLog.i(r7, r11)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ IOException -> 0x0f23, all -> 0x0efe }
            java.io.InputStream r15 = r7.getInputStream()     // Catch:{ IOException -> 0x0f23, all -> 0x0efe }
            if (r15 == 0) goto L_0x0f49
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            java.lang.String r7 = r7.getContentEncoding()     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            if (r7 == 0) goto L_0x0810
            java.lang.String r9 = "gzip"
            boolean r9 = r7.contains(r9)     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            if (r9 == 0) goto L_0x0810
            java.util.zip.GZIPInputStream r14 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            r14.<init>(r15)     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
        L_0x0798:
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r7]     // Catch:{ IOException -> 0x0f26, all -> 0x0f0f }
            r26 = r0
            java.io.FileOutputStream r16 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0f26, all -> 0x0f0f }
            java.io.File r7 = new java.io.File     // Catch:{ IOException -> 0x0f26, all -> 0x0f0f }
            r0 = r32
            java.io.File r8 = r0.k     // Catch:{ IOException -> 0x0f26, all -> 0x0f0f }
            java.lang.String r9 = "x5.tbs.temp"
            r7.<init>(r8, r9)     // Catch:{ IOException -> 0x0f26, all -> 0x0f0f }
            r8 = 1
            r0 = r16
            r0.<init>(r7, r8)     // Catch:{ IOException -> 0x0f26, all -> 0x0f0f }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0f2b, all -> 0x0f16 }
            r7 = 0
            r10 = r20
            r8 = r4
            r20 = r18
            r4 = r18
        L_0x07bd:
            r0 = r32
            boolean r0 = r0.r     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r18 = r0
            if (r18 == 0) goto L_0x0884
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "STEP 1/2 begin downloading...Canceled!"
            r10 = 1
            com.tencent.smtt.utils.TbsLog.i(r4, r5, r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r5 = -309(0xfffffffffffffecb, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r4 = r8
        L_0x07db:
            if (r7 == 0) goto L_0x0a60
            r0 = r32
            r1 = r16
            r0.a(r1)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r14)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r15)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0810:
            if (r7 == 0) goto L_0x0881
            java.lang.String r9 = "deflate"
            boolean r7 = r7.contains(r9)     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            if (r7 == 0) goto L_0x0881
            java.util.zip.InflaterInputStream r14 = new java.util.zip.InflaterInputStream     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            java.util.zip.Inflater r7 = new java.util.zip.Inflater     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            r9 = 1
            r7.<init>(r9)     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            r14.<init>(r15, r7)     // Catch:{ IOException -> 0x0827, all -> 0x0f07 }
            goto L_0x0798
        L_0x0827:
            r7 = move-exception
            r9 = r15
        L_0x0829:
            r7.printStackTrace()     // Catch:{ all -> 0x0b81 }
            boolean r11 = r7 instanceof java.net.SocketTimeoutException     // Catch:{ all -> 0x0b81 }
            if (r11 != 0) goto L_0x0834
            boolean r11 = r7 instanceof java.net.SocketException     // Catch:{ all -> 0x0b81 }
            if (r11 == 0) goto L_0x0aa7
        L_0x0834:
            r11 = 100000(0x186a0, float:1.4013E-40)
            r0 = r32
            r0.m = r11     // Catch:{ all -> 0x0b81 }
            r12 = 0
            r0 = r32
            r0.a(r12)     // Catch:{ all -> 0x0b81 }
            r11 = 103(0x67, float:1.44E-43)
            r0 = r32
            java.lang.String r7 = r0.a(r7)     // Catch:{ all -> 0x0b81 }
            r12 = 0
            r0 = r32
            r0.a(r11, r7, r12)     // Catch:{ all -> 0x0b81 }
            r0 = r32
            r0.a(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r9)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0881:
            r14 = r15
            goto L_0x0798
        L_0x0884:
            r18 = 0
            r19 = 8192(0x2000, float:1.14794E-41)
            r0 = r26
            r1 = r18
            r2 = r19
            int r27 = r14.read(r0, r1, r2)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r27 > 0) goto L_0x08da
            r0 = r32
            java.lang.String[] r4 = r0.b     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r4 == 0) goto L_0x08be
            r4 = 1
            r0 = r32
            r1 = r17
            boolean r4 = r0.c(r4, r1)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r4 != 0) goto L_0x08be
            if (r33 != 0) goto L_0x08b5
            r4 = 0
            r0 = r32
            boolean r4 = r0.a(r4)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r4 == 0) goto L_0x08b5
            r4 = 1
            r7 = r4
            r4 = r8
            goto L_0x07db
        L_0x08b5:
            r4 = 1
            r0 = r32
            r0.s = r4     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r6 = 0
            r4 = r8
            goto L_0x07db
        L_0x08be:
            r4 = 1
            r0 = r32
            r0.s = r4     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r0 = r32
            java.lang.String[] r4 = r0.b     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r4 == 0) goto L_0x08ca
            r6 = 1
        L_0x08ca:
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r5 = -311(0xfffffffffffffec9, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r4 = r8
            goto L_0x07db
        L_0x08da:
            r18 = 0
            r0 = r16
            r1 = r26
            r2 = r18
            r3 = r27
            r0.write(r1, r2, r3)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r16.flush()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r33 != 0) goto L_0x098f
            r0 = r27
            long r0 = (long) r0     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r18 = r0
            long r8 = r8 + r18
            int r18 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r18 < 0) goto L_0x0936
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "STEP 1/2 begin downloading...failed because you exceeded max flow!"
            r10 = 1
            com.tencent.smtt.utils.TbsLog.i(r4, r5, r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r4 = 112(0x70, float:1.57E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r5.<init>()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.String r10 = "downloadFlow="
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.String r10 = " downloadMaxflow="
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r0 = r24
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r10 = 1
            r0 = r32
            r0.a(r4, r5, r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r5 = -307(0xfffffffffffffecd, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r4 = r8
            goto L_0x07db
        L_0x0936:
            r0 = r32
            android.content.Context r0 = r0.g     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r18 = r0
            boolean r18 = com.tencent.smtt.utils.f.b(r18)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            if (r18 != 0) goto L_0x098f
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "DownloadEnd FreeSpace too small "
            r10 = 1
            com.tencent.smtt.utils.TbsLog.i(r4, r5, r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r4 = 105(0x69, float:1.47E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r5.<init>()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.String r10 = "freespace="
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            long r10 = com.tencent.smtt.utils.q.a()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.String r10 = ",and minFreeSpace="
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r0 = r32
            android.content.Context r10 = r0.g     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            com.tencent.smtt.sdk.TbsDownloadConfig r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            long r10 = r10.getDownloadMinFreeSpace()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r10 = 1
            r0 = r32
            r0.a(r4, r5, r10)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r5 = -308(0xfffffffffffffecc, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0f32, all -> 0x0f1b }
            r4 = r8
            goto L_0x07db
        L_0x098f:
            r18 = r8
            r0 = r27
            long r8 = (long) r0
            r0 = r32
            long r22 = r0.a(r10, r8)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r0 = r27
            long r8 = (long) r0     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            long r8 = r8 + r20
            long r20 = r10 - r12
            r28 = 1000(0x3e8, double:4.94E-321)
            int r20 = (r20 > r28 ? 1 : (r20 == r28 ? 0 : -1))
            if (r20 <= 0) goto L_0x0f45
            java.lang.String r12 = "TbsDownload"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r13.<init>()     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            java.lang.String r20 = "#2 STEP 1/2 begin downloading...current/total="
            r0 = r20
            java.lang.StringBuilder r13 = r13.append(r0)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            java.lang.StringBuilder r13 = r13.append(r8)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            java.lang.String r20 = "/"
            r0 = r20
            java.lang.StringBuilder r13 = r13.append(r0)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r0 = r32
            long r0 = r0.l     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r20 = r0
            r0 = r20
            java.lang.StringBuilder r13 = r13.append(r0)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r20 = 1
            r0 = r20
            com.tencent.smtt.utils.TbsLog.i(r12, r13, r0)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            com.tencent.smtt.sdk.TbsListener r12 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            if (r12 == 0) goto L_0x09f9
            double r12 = (double) r8     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r0 = r32
            long r0 = r0.l     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r20 = r0
            r0 = r20
            double r0 = (double) r0     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r20 = r0
            double r12 = r12 / r20
            r20 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r12 = r12 * r20
            int r12 = (int) r12     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            com.tencent.smtt.sdk.TbsListener r13 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r13.onDownloadProgress(r12)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
        L_0x09f9:
            if (r33 != 0) goto L_0x0a51
            long r12 = r8 - r4
            r20 = 1048576(0x100000, double:5.180654E-318)
            int r12 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1))
            if (r12 <= 0) goto L_0x0a51
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            boolean r4 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r4)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            if (r4 != 0) goto L_0x0a50
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            int r4 = com.tencent.smtt.utils.Apn.getApnType(r4)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r5 = 3
            if (r4 != r5) goto L_0x0a23
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            int r4 = com.tencent.smtt.utils.Apn.getApnType(r4)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            if (r4 != 0) goto L_0x0a50
        L_0x0a23:
            boolean r4 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            if (r4 != 0) goto L_0x0a50
            r32.b()     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            com.tencent.smtt.sdk.TbsListener r4 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            if (r4 == 0) goto L_0x0a37
            com.tencent.smtt.sdk.TbsListener r4 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r5 = 111(0x6f, float:1.56E-43)
            r4.onDownloadFinish(r5)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
        L_0x0a37:
            java.lang.String r4 = "TbsDownload"
            java.lang.String r5 = "Download is paused due to NOT_WIFI error!"
            r8 = 0
            com.tencent.smtt.utils.TbsLog.i(r4, r5, r8)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r5 = -304(0xfffffffffffffed0, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0f3b, all -> 0x0f1e }
            r4 = r18
            goto L_0x07db
        L_0x0a50:
            r4 = r8
        L_0x0a51:
            r30 = r10
            r10 = r4
            r4 = r30
        L_0x0a56:
            r12 = r4
            r20 = r8
            r4 = r10
            r8 = r18
            r10 = r22
            goto L_0x07bd
        L_0x0a60:
            r8 = r4
        L_0x0a61:
            r0 = r32
            r1 = r16
            r0.a(r1)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r0 = r32
            r0.a(r14)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r0 = r32
            r0.a(r15)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r0 = r32
            boolean r4 = r0.s     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            if (r4 != 0) goto L_0x0a85
            r0 = r32
            android.content.Context r4 = r0.g     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r5 = -319(0xfffffffffffffec1, float:NaN)
            r4.setDownloadInterruptCode(r5)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
        L_0x0a85:
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r4.a
            java.lang.String r5 = "tbs_downloadflow"
            java.lang.Long r7 = java.lang.Long.valueOf(r8)
            r4.put(r5, r7)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0aa7:
            if (r33 != 0) goto L_0x0b26
            r0 = r32
            android.content.Context r11 = r0.g     // Catch:{ all -> 0x0b81 }
            boolean r11 = com.tencent.smtt.utils.f.b(r11)     // Catch:{ all -> 0x0b81 }
            if (r11 != 0) goto L_0x0b26
            r7 = 105(0x69, float:1.47E-43)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0b81 }
            r11.<init>()     // Catch:{ all -> 0x0b81 }
            java.lang.String r12 = "freespace="
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0b81 }
            long r12 = com.tencent.smtt.utils.q.a()     // Catch:{ all -> 0x0b81 }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0b81 }
            java.lang.String r12 = ",and minFreeSpace="
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0b81 }
            r0 = r32
            android.content.Context r12 = r0.g     // Catch:{ all -> 0x0b81 }
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ all -> 0x0b81 }
            long r12 = r12.getDownloadMinFreeSpace()     // Catch:{ all -> 0x0b81 }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0b81 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0b81 }
            r12 = 1
            r0 = r32
            r0.a(r7, r11, r12)     // Catch:{ all -> 0x0b81 }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ all -> 0x0b81 }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x0b81 }
            r11 = -308(0xfffffffffffffecc, float:NaN)
            r7.setDownloadInterruptCode(r11)     // Catch:{ all -> 0x0b81 }
            r0 = r32
            r0.a(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r9)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0b26:
            r12 = 0
            r0 = r32
            r0.a(r12)     // Catch:{ all -> 0x0b81 }
            boolean r11 = r32.i()     // Catch:{ all -> 0x0b81 }
            if (r11 != 0) goto L_0x0b72
            r11 = 106(0x6a, float:1.49E-43)
            r0 = r32
            java.lang.String r7 = r0.a(r7)     // Catch:{ all -> 0x0b81 }
            r12 = 0
            r0 = r32
            r0.a(r11, r7, r12)     // Catch:{ all -> 0x0b81 }
        L_0x0b41:
            r0 = r32
            r0.a(r10)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r9)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0b72:
            r11 = 104(0x68, float:1.46E-43)
            r0 = r32
            java.lang.String r7 = r0.a(r7)     // Catch:{ all -> 0x0b81 }
            r12 = 0
            r0 = r32
            r0.a(r11, r7, r12)     // Catch:{ all -> 0x0b81 }
            goto L_0x0b41
        L_0x0b81:
            r7 = move-exception
            r14 = r8
            r15 = r9
            r16 = r10
            r8 = r4
            r4 = r7
        L_0x0b88:
            r0 = r32
            r1 = r16
            r0.a(r1)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r0 = r32
            r0.a(r14)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            r0 = r32
            r0.a(r15)     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
            throw r4     // Catch:{ Throwable -> 0x0b9a, all -> 0x0efb }
        L_0x0b9a:
            r4 = move-exception
            r7 = r4
            r4 = r8
            goto L_0x056f
        L_0x0b9f:
            r8 = 300(0x12c, float:4.2E-43)
            if (r7 < r8) goto L_0x0c1f
            r8 = 307(0x133, float:4.3E-43)
            if (r7 > r8) goto L_0x0c1f
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "Location"
            java.lang.String r7 = r7.getHeaderField(r8)     // Catch:{ Throwable -> 0x056e }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Throwable -> 0x056e }
            if (r8 != 0) goto L_0x0be7
            r0 = r32
            r0.j = r7     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            int r7 = r0.f436q     // Catch:{ Throwable -> 0x056e }
            int r7 = r7 + 1
            r0 = r32
            r0.f436q = r7     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0be7:
            r7 = 124(0x7c, float:1.74E-43)
            r8 = 0
            r9 = 1
            r0 = r32
            r0.a(r7, r8, r9)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -312(0xfffffffffffffec8, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0c1f:
            r8 = 102(0x66, float:1.43E-43)
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ Throwable -> 0x056e }
            r10 = 0
            r0 = r32
            r0.a(r8, r9, r10)     // Catch:{ Throwable -> 0x056e }
            r8 = 416(0x1a0, float:5.83E-43)
            if (r7 != r8) goto L_0x0c9f
            r7 = 1
            r0 = r32
            r1 = r17
            boolean r7 = r0.c(r7, r1)     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x0c6a
            r6 = 1
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -214(0xffffffffffffff2a, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0c6a:
            r7 = 0
            r0 = r32
            r0.d(r7)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -313(0xfffffffffffffec7, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0c9f:
            r8 = 403(0x193, float:5.65E-43)
            if (r7 == r8) goto L_0x0ca7
            r8 = 406(0x196, float:5.69E-43)
            if (r7 != r8) goto L_0x0ce0
        L_0x0ca7:
            r0 = r32
            long r8 = r0.l     // Catch:{ Throwable -> 0x056e }
            r10 = -1
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 != 0) goto L_0x0ce0
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -314(0xfffffffffffffec6, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0ce0:
            r8 = 202(0xca, float:2.83E-43)
            if (r7 != r8) goto L_0x0d06
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0d06:
            r0 = r32
            int r8 = r0.p     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            int r9 = r0.B     // Catch:{ Throwable -> 0x056e }
            if (r8 >= r9) goto L_0x0d7e
            r8 = 503(0x1f7, float:7.05E-43)
            if (r7 != r8) goto L_0x0d7e
            r0 = r32
            java.net.HttpURLConnection r7 = r0.t     // Catch:{ Throwable -> 0x056e }
            java.lang.String r8 = "Retry-After"
            java.lang.String r7 = r7.getHeaderField(r8)     // Catch:{ Throwable -> 0x056e }
            long r8 = java.lang.Long.parseLong(r7)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            r0.a(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            boolean r7 = r0.r     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x0d5c
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -309(0xfffffffffffffecb, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0d5c:
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0d7e:
            r0 = r32
            int r8 = r0.p     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            int r9 = r0.B     // Catch:{ Throwable -> 0x056e }
            if (r8 >= r9) goto L_0x0df6
            r8 = 408(0x198, float:5.72E-43)
            if (r7 == r8) goto L_0x0d98
            r8 = 504(0x1f8, float:7.06E-43)
            if (r7 == r8) goto L_0x0d98
            r8 = 502(0x1f6, float:7.03E-43)
            if (r7 == r8) goto L_0x0d98
            r8 = 408(0x198, float:5.72E-43)
            if (r7 != r8) goto L_0x0df6
        L_0x0d98:
            r8 = 0
            r0 = r32
            r0.a(r8)     // Catch:{ Throwable -> 0x056e }
            r0 = r32
            boolean r7 = r0.r     // Catch:{ Throwable -> 0x056e }
            if (r7 == 0) goto L_0x0dd4
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -309(0xfffffffffffffecb, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0dd4:
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0df6:
            long r8 = r32.j()     // Catch:{ Throwable -> 0x056e }
            r10 = 0
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 > 0) goto L_0x0e31
            r0 = r32
            boolean r8 = r0.o     // Catch:{ Throwable -> 0x056e }
            if (r8 != 0) goto L_0x0e31
            r8 = 410(0x19a, float:5.75E-43)
            if (r7 == r8) goto L_0x0e31
            r7 = 1
            r0 = r32
            r0.o = r7     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01b8
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r9 = java.lang.Long.valueOf(r4)
            r7.put(r8, r9)
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            r7.commit()
            goto L_0x01b8
        L_0x0e31:
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ Throwable -> 0x056e }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x056e }
            r8 = -315(0xfffffffffffffec5, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x056e }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0e60:
            r7.printStackTrace()     // Catch:{ all -> 0x060b }
            r8 = 0
            r0 = r32
            r0.a(r8)     // Catch:{ all -> 0x060b }
            r8 = 107(0x6b, float:1.5E-43)
            r0 = r32
            java.lang.String r7 = r0.a(r7)     // Catch:{ all -> 0x060b }
            r9 = 0
            r0 = r32
            r0.a(r8, r7, r9)     // Catch:{ all -> 0x060b }
            r0 = r32
            boolean r7 = r0.r     // Catch:{ all -> 0x060b }
            if (r7 == 0) goto L_0x05b7
            r0 = r32
            android.content.Context r7 = r0.g     // Catch:{ all -> 0x060b }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x060b }
            r8 = -309(0xfffffffffffffecb, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ all -> 0x060b }
            if (r33 != 0) goto L_0x01c2
            r0 = r32
            android.content.Context r7 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.a
            java.lang.String r8 = "tbs_downloadflow"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r7.put(r8, r4)
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r4.commit()
            goto L_0x01c2
        L_0x0ead:
            r4 = 0
            goto L_0x01e6
        L_0x0eb0:
            r4 = 2
            goto L_0x01f2
        L_0x0eb3:
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r4 = r0.v
            r5 = 0
            r4.setPatchUpdateFlag(r5)
            goto L_0x01f5
        L_0x0ebd:
            r0 = r32
            android.content.Context r4 = r0.g
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            r5 = -318(0xfffffffffffffec2, float:NaN)
            r4.setDownloadInterruptCode(r5)
            r4 = 0
            r0 = r32
            r0.d(r4)
            goto L_0x0214
        L_0x0ed2:
            android.content.SharedPreferences r5 = r4.mPreferences
            java.lang.String r7 = "tbs_download_failed_retrytimes"
            r8 = 0
            int r5 = r5.getInt(r7, r8)
            java.util.Map<java.lang.String, java.lang.Object> r7 = r4.a
            java.lang.String r8 = "tbs_download_failed_retrytimes"
            int r5 = r5 + 1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            r7.put(r8, r9)
            int r7 = r4.getDownloadFailedMaxRetrytimes()
            if (r5 != r7) goto L_0x0234
            r0 = r32
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r0.v
            r7 = 2
            r5.setDownloadCancel(r7)
            goto L_0x0234
        L_0x0ef8:
            r4 = 0
            goto L_0x023e
        L_0x0efb:
            r4 = move-exception
            goto L_0x060e
        L_0x0efe:
            r7 = move-exception
            r14 = r8
            r15 = r9
            r16 = r10
            r8 = r4
            r4 = r7
            goto L_0x0b88
        L_0x0f07:
            r7 = move-exception
            r14 = r8
            r16 = r10
            r8 = r4
            r4 = r7
            goto L_0x0b88
        L_0x0f0f:
            r7 = move-exception
            r16 = r10
            r8 = r4
            r4 = r7
            goto L_0x0b88
        L_0x0f16:
            r7 = move-exception
            r8 = r4
            r4 = r7
            goto L_0x0b88
        L_0x0f1b:
            r4 = move-exception
            goto L_0x0b88
        L_0x0f1e:
            r4 = move-exception
            r8 = r18
            goto L_0x0b88
        L_0x0f23:
            r7 = move-exception
            goto L_0x0829
        L_0x0f26:
            r7 = move-exception
            r8 = r14
            r9 = r15
            goto L_0x0829
        L_0x0f2b:
            r7 = move-exception
            r8 = r14
            r9 = r15
            r10 = r16
            goto L_0x0829
        L_0x0f32:
            r4 = move-exception
            r7 = r4
            r10 = r16
            r4 = r8
            r8 = r14
            r9 = r15
            goto L_0x0829
        L_0x0f3b:
            r4 = move-exception
            r7 = r4
            r8 = r14
            r9 = r15
            r10 = r16
            r4 = r18
            goto L_0x0829
        L_0x0f45:
            r10 = r4
            r4 = r12
            goto L_0x0a56
        L_0x0f49:
            r14 = r8
            r16 = r10
            r8 = r4
            goto L_0x0a61
        L_0x0f4f:
            r8 = r4
            goto L_0x02cb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.i.b(boolean, boolean):void");
    }

    public boolean a(boolean z2) {
        if ((z2 && !m() && (!QbSdk.getDownloadWithoutWifi() || !Apn.isNetworkAvailable(this.g))) || this.b == null || this.c < 0 || this.c >= this.b.length) {
            return false;
        }
        String[] strArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        this.j = strArr[i2];
        this.p = 0;
        this.f436q = 0;
        this.l = -1;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
        return true;
    }

    private long a(long j2, long j3) {
        long currentTimeMillis = System.currentTimeMillis();
        this.v.setDownConsumeTime(currentTimeMillis - j2);
        this.v.setDownloadSize(j3);
        return currentTimeMillis;
    }

    private void a(int i2, String str, boolean z2) {
        if (z2 || this.p > this.B) {
            this.v.setErrorCode(i2);
            this.v.setFailDetail(str);
        }
    }

    private String a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    private void c(boolean z2) {
        q.a(this.g);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.g);
        instance.a.put(TbsConfigKey.KEY_FULL_PACKAGE, Boolean.valueOf(false));
        instance.a.put(TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.valueOf(false));
        instance.a.put(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, Integer.valueOf(-123));
        instance.commit();
        QbSdk.m.onDownloadFinish(z2 ? 100 : 120);
        int i2 = instance.mPreferences.getInt(TbsConfigKey.KEY_RESPONSECODE, 0);
        boolean a2 = TbsDownloader.a(this.g);
        if (i2 == 5) {
            Bundle a3 = a(i2, a2);
            if (a3 != null) {
                l.a().b(this.g, a3);
            }
        } else if (i2 == 3 || i2 > 10000) {
            File a4 = a(this.g);
            if (a4 != null) {
                l.a().b(this.g, a(i2, a4, a2));
                return;
            }
            c();
            instance.a.put(TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.valueOf(true));
            instance.commit();
        } else {
            l.a().a(this.g, new File(this.k, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            a(new File(this.k, "x5.tbs"), this.g);
        }
    }

    public Bundle a(int i2, boolean z2) {
        File q2;
        int i3;
        String str;
        File f2;
        if (z2) {
            q2 = l.a().p(this.g);
            i3 = l.a().h(this.g);
        } else {
            q2 = l.a().q(this.g);
            i3 = l.a().i(this.g);
        }
        File file = new File(this.k, "x5.tbs");
        if (file.exists()) {
            str = file.getAbsolutePath();
        } else {
            str = null;
        }
        if (str == null) {
            return null;
        }
        int i4 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        if (z2) {
            f2 = l.a().f(this.g, 6);
        } else {
            f2 = l.a().f(this.g, 5);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i2);
        bundle.putInt("old_core_ver", i3);
        bundle.putInt("new_core_ver", i4);
        bundle.putString("old_apk_location", q2.getAbsolutePath());
        bundle.putString("new_apk_location", f2.getAbsolutePath());
        bundle.putString("diff_file_location", str);
        String a2 = f.a(this.g, 7);
        File file2 = new File(a2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        bundle.putString("backup_apk", new File(a2, i4 + ".tbs").getAbsolutePath());
        return bundle;
    }

    public Bundle a(int i2, File file, boolean z2) {
        File file2;
        if (z2) {
            file2 = new File(file, "x5.tbs.decouple");
        } else {
            file2 = new File(file, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        }
        int a2 = a.a(this.g, file2);
        File file3 = new File(this.k, "x5.tbs");
        String str = file3.exists() ? file3.getAbsolutePath() : null;
        int i3 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i2);
        bundle.putInt("old_core_ver", a2);
        bundle.putInt("new_core_ver", i3);
        bundle.putString("old_apk_location", file2.getAbsolutePath());
        bundle.putString("new_apk_location", str);
        bundle.putString("diff_file_location", str);
        return bundle;
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
            }
        }
    }

    private void a(long j2) {
        this.p++;
        if (j2 <= 0) {
            try {
                j2 = l();
            } catch (Exception e2) {
                return;
            }
        }
        Thread.sleep(j2);
    }

    private boolean g() {
        File file = new File(f.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        int i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i2 == 0) {
            i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return a.a(this.g, file, 0, i2);
    }

    private boolean c(boolean z2, boolean z3) {
        boolean z4;
        Exception exc;
        long j2;
        long j3 = 0;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z2);
        File file = new File(this.k, !z2 ? "x5.tbs" : "x5.tbs.temp");
        if (!file.exists()) {
            return false;
        }
        String string = TbsDownloadConfig.getInstance(this.g).mPreferences.getString(TbsConfigKey.KEY_TBSAPK_MD5, null);
        String a2 = a.a(file);
        if (string == null || !string.equals(a2)) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z2 + " md5 failed");
            if (!z2) {
                return false;
            }
            this.v.setCheckErrorDetail("fileMd5 not match");
            return false;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] md5(" + a2 + ") successful!");
        if (z2) {
            long j4 = TbsDownloadConfig.getInstance(this.g).mPreferences.getLong(TbsConfigKey.KEY_TBSAPKFILESIZE, 0);
            if (file == null || !file.exists()) {
                j2 = 0;
            } else if (j4 > 0) {
                j2 = file.length();
                if (j4 == j2) {
                    j3 = j2;
                }
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z2 + " filelength failed");
            this.v.setCheckErrorDetail("fileLength:" + j2 + ",contentLength:" + j4);
            return false;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] length(" + j3 + ") successful!");
        int i2 = -1;
        if (z3 && !z2) {
            i2 = a.a(this.g, file);
            int i3 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            if (i3 != i2) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z2 + " versionCode failed");
                if (!z2) {
                    return false;
                }
                this.v.setCheckErrorDetail("fileVersion:" + i2 + ",configVersion:" + i3);
                return false;
            }
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] tbsApkVersionCode(" + i2 + ") successful!");
        if (z3 && !z2) {
            String a3 = b.a(this.g, false, file);
            if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(a3)) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z2 + " signature failed");
                if (!z2) {
                    return false;
                }
                this.v.setCheckErrorDetail("signature:" + (a3 == null ? "null" : Integer.valueOf(a3.length())));
                return false;
            }
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] signature successful!");
        if (z2) {
            try {
                z4 = file.renameTo(new File(this.k, "x5.tbs"));
                exc = null;
            } catch (Exception e2) {
                exc = e2;
                z4 = false;
            }
            if (!z4) {
                a(109, a((Throwable) exc), true);
                return false;
            }
        } else {
            z4 = false;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] rename(" + z4 + ") successful!");
        return true;
    }

    private boolean d(boolean z2) {
        File file;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z2);
        if (z2) {
            file = new File(this.k, "x5.tbs");
        } else {
            file = new File(this.k, "x5.tbs.temp");
        }
        if (file == null || !file.exists()) {
            return true;
        }
        return file.delete();
    }

    private void h() {
        try {
            File file = new File(f.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file != null && file.exists()) {
                file.delete();
                File[] listFiles = file.getParentFile().listFiles();
                Pattern compile = Pattern.compile(a.a(TbsDownloader.a(this.g)) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean i() {
        File file = new File(this.k, "x5.tbs.temp");
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    private long j() {
        File file = new File(this.k, "x5.tbs.temp");
        if (file == null || !file.exists()) {
            return 0;
        }
        return file.length();
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r4v8 */
    /* JADX WARNING: type inference failed for: r4v9, types: [java.io.Closeable, java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r4v12 */
    /* JADX WARNING: type inference failed for: r4v13 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean k() {
        /*
            r7 = this;
            r0 = 0
            r3 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()
            java.lang.String r2 = "www.qq.com"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            r4.<init>()     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            java.lang.String r5 = "ping "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ Throwable -> 0x0055, all -> 0x0065 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x007f, all -> 0x0072 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x007f, all -> 0x0072 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0083, all -> 0x0075 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0083, all -> 0x0075 }
            r1 = r0
        L_0x002e:
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x0088, all -> 0x0077 }
            if (r3 == 0) goto L_0x0045
            java.lang.String r6 = "TTL"
            boolean r6 = r3.contains(r6)     // Catch:{ Throwable -> 0x0088, all -> 0x0077 }
            if (r6 != 0) goto L_0x0044
            java.lang.String r6 = "ttl"
            boolean r3 = r3.contains(r6)     // Catch:{ Throwable -> 0x0088, all -> 0x0077 }
            if (r3 == 0) goto L_0x004f
        L_0x0044:
            r0 = 1
        L_0x0045:
            r7.a(r5)
            r7.a(r4)
            r7.a(r2)
        L_0x004e:
            return r0
        L_0x004f:
            int r1 = r1 + 1
            r3 = 5
            if (r1 < r3) goto L_0x002e
            goto L_0x0045
        L_0x0055:
            r1 = move-exception
            r2 = r3
            r4 = r3
        L_0x0058:
            r1.printStackTrace()     // Catch:{ all -> 0x007a }
            r7.a(r4)
            r7.a(r3)
            r7.a(r2)
            goto L_0x004e
        L_0x0065:
            r0 = move-exception
            r4 = r3
            r5 = r3
        L_0x0068:
            r7.a(r5)
            r7.a(r4)
            r7.a(r3)
            throw r0
        L_0x0072:
            r0 = move-exception
            r4 = r3
            goto L_0x0068
        L_0x0075:
            r0 = move-exception
            goto L_0x0068
        L_0x0077:
            r0 = move-exception
            r3 = r2
            goto L_0x0068
        L_0x007a:
            r0 = move-exception
            r5 = r4
            r4 = r3
            r3 = r2
            goto L_0x0068
        L_0x007f:
            r1 = move-exception
            r2 = r3
            r4 = r5
            goto L_0x0058
        L_0x0083:
            r1 = move-exception
            r2 = r3
            r3 = r4
            r4 = r5
            goto L_0x0058
        L_0x0088:
            r1 = move-exception
            r3 = r4
            r4 = r5
            goto L_0x0058
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.i.k():boolean");
    }

    private String a(URL url) {
        String str = "";
        try {
            return InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        } catch (Error e3) {
            e3.printStackTrace();
            return str;
        }
    }

    private long l() {
        switch (this.p) {
            case 1:
            case 2:
                return 20000 * ((long) this.p);
            case 3:
            case 4:
                return 20000 * 5;
            default:
                return 20000 * 10;
        }
    }

    @TargetApi(8)
    static File a(Context context) {
        File file;
        try {
            if (VERSION.SDK_INT >= 8) {
                file = new File(f.a(context, 4));
            } else {
                file = null;
            }
            if (file == null || file.exists() || file.isDirectory()) {
                return file;
            }
            file.mkdirs();
            return file;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    @TargetApi(8)
    static File b(Context context) {
        try {
            if (VERSION.SDK_INT < 8) {
                return null;
            }
            File a2 = a(context, 4);
            if (a2 == null) {
                a2 = a(context, 3);
            }
            if (a2 == null) {
                a2 = a(context, 2);
            }
            if (a2 == null) {
                return a(context, 1);
            }
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private static File a(Context context, int i2) {
        File file = new File(f.a(context, i2));
        if (file == null || !file.exists() || !file.isDirectory()) {
            return null;
        }
        File file2 = new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        if (file2 == null || !file2.exists()) {
            return null;
        }
        return file;
    }

    public int b(boolean z2) {
        File a2 = a(this.g);
        if (z2) {
            if (a2 == null) {
                return 0;
            }
            return a.a(this.g, new File(a2, "x5.tbs.decouple"));
        } else if (a2 == null) {
            return 0;
        } else {
            return a.a(this.g, new File(a2, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org"));
        }
    }

    public void b() {
        this.r = true;
        if (TbsShareManager.isThirdPartyApp(this.g)) {
            TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(this.g).tbsLogInfo();
            tbsLogInfo.setErrorCode(-309);
            tbsLogInfo.setFailDetail((Throwable) new Exception());
            if (TbsDownloader.a(this.g)) {
                TbsLogReport.getInstance(this.g).eventReport(EventType.TYPE_DOWNLOAD_DECOUPLE, tbsLogInfo);
            } else {
                TbsLogReport.getInstance(this.g).eventReport(EventType.TYPE_DOWNLOAD, tbsLogInfo);
            }
        }
    }

    public void c() {
        b();
        d(false);
        d(true);
    }

    public void a(int i2) {
        if (l.a().t(this.g)) {
            l.a().b();
            try {
                File file = new File(this.k, "x5.tbs");
                int a2 = a.a(this.g, file);
                if (-1 == a2 || (i2 > 0 && i2 == a2)) {
                    f.b(file);
                }
            } catch (Exception e2) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r10, android.content.Context r11) {
        /*
            r9 = 1
            r0 = 0
            java.lang.Class<com.tencent.smtt.utils.a> r3 = com.tencent.smtt.utils.a.class
            monitor-enter(r3)
            if (r10 == 0) goto L_0x000d
            boolean r1 = r10.exists()     // Catch:{ all -> 0x009f }
            if (r1 != 0) goto L_0x000f
        L_0x000d:
            monitor-exit(r3)     // Catch:{ all -> 0x009f }
        L_0x000e:
            return
        L_0x000f:
            java.io.File r4 = a(r11)     // Catch:{ Exception -> 0x009b }
            if (r4 == 0) goto L_0x009c
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11)     // Catch:{ Exception -> 0x009b }
            android.content.SharedPreferences r1 = r1.mPreferences     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = "tbs_download_version_type"
            r5 = 0
            int r1 = r1.getInt(r2, r5)     // Catch:{ Exception -> 0x009b }
            if (r1 != r9) goto L_0x008d
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = "x5.tbs.decouple"
            r1.<init>(r4, r2)     // Catch:{ Exception -> 0x009b }
        L_0x002b:
            r1.delete()     // Catch:{ Exception -> 0x009b }
            com.tencent.smtt.utils.f.b(r10, r1)     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = r1.getName()     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = "tbs.org"
            boolean r2 = r2.contains(r5)     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = r1.getName()     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = "x5.tbs.decouple"
            boolean r1 = r1.contains(r5)     // Catch:{ Exception -> 0x009b }
            if (r1 != 0) goto L_0x0049
            if (r2 == 0) goto L_0x00e7
        L_0x0049:
            java.io.File[] r2 = r4.listFiles()     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009b }
            r5.<init>()     // Catch:{ Exception -> 0x009b }
            java.lang.String r6 = com.tencent.smtt.utils.a.a(r1)     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x009b }
            java.lang.String r6 = "(.*)"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x009b }
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)     // Catch:{ Exception -> 0x009b }
            int r6 = r2.length     // Catch:{ Exception -> 0x009b }
        L_0x0069:
            if (r0 >= r6) goto L_0x00a5
            r7 = r2[r0]     // Catch:{ Exception -> 0x009b }
            java.lang.String r8 = r7.getName()     // Catch:{ Exception -> 0x009b }
            java.util.regex.Matcher r8 = r5.matcher(r8)     // Catch:{ Exception -> 0x009b }
            boolean r8 = r8.find()     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x008a
            boolean r8 = r7.isFile()     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x008a
            boolean r8 = r7.exists()     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x008a
            r7.delete()     // Catch:{ Exception -> 0x009b }
        L_0x008a:
            int r0 = r0 + 1
            goto L_0x0069
        L_0x008d:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x009b }
            boolean r2 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r11)     // Catch:{ Exception -> 0x009b }
            if (r2 == 0) goto L_0x00a2
            java.lang.String r2 = "x5.oversea.tbs.org"
        L_0x0097:
            r1.<init>(r4, r2)     // Catch:{ Exception -> 0x009b }
            goto L_0x002b
        L_0x009b:
            r0 = move-exception
        L_0x009c:
            monitor-exit(r3)     // Catch:{ all -> 0x009f }
            goto L_0x000e
        L_0x009f:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x009f }
            throw r0
        L_0x00a2:
            java.lang.String r2 = "x5.tbs.org"
            goto L_0x0097
        L_0x00a5:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11)     // Catch:{ Exception -> 0x009b }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = "tbs_download_version"
            r5 = 0
            int r0 = r0.getInt(r2, r5)     // Catch:{ Exception -> 0x009b }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009b }
            r5.<init>()     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = com.tencent.smtt.utils.a.a(r1)     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r1 = r5.append(r1)     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = "."
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Exception -> 0x009b }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x009b }
            r2.<init>(r4, r0)     // Catch:{ Exception -> 0x009b }
            if (r2 == 0) goto L_0x00e4
            boolean r0 = r2.exists()     // Catch:{ Exception -> 0x009b }
            if (r0 == 0) goto L_0x00e4
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsApkDownloader.backupTbsApk]delete bacup config file error "
            com.tencent.smtt.utils.TbsLog.e(r0, r1)     // Catch:{ Exception -> 0x009b }
            monitor-exit(r3)     // Catch:{ all -> 0x009f }
            goto L_0x000e
        L_0x00e4:
            r2.createNewFile()     // Catch:{ Exception -> 0x009b }
        L_0x00e7:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11)     // Catch:{ Exception -> 0x009b }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = "tbs_download_version_type"
            r2 = 0
            int r0 = r0.getInt(r1, r2)     // Catch:{ Exception -> 0x009b }
            if (r0 == r9) goto L_0x009c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11)     // Catch:{ Exception -> 0x009b }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = "tbs_decouplecoreversion"
            r2 = 0
            int r0 = r0.getInt(r1, r2)     // Catch:{ Exception -> 0x009b }
            int r1 = com.tencent.smtt.utils.a.a(r11, r10)     // Catch:{ Exception -> 0x009b }
            if (r0 != r1) goto L_0x009c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11)     // Catch:{ Exception -> 0x009b }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = "tbs_responsecode"
            r2 = 0
            int r0 = r0.getInt(r1, r2)     // Catch:{ Exception -> 0x009b }
            r1 = 5
            if (r0 == r1) goto L_0x011c
            r1 = 3
            if (r0 != r1) goto L_0x013a
        L_0x011c:
            java.lang.String r1 = "TbsApkDownloader"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009b }
            r2.<init>()     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = "response code="
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = "return backup decouple apk"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ Exception -> 0x009b }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x009b }
            com.tencent.smtt.utils.TbsLog.i(r1, r0)     // Catch:{ Exception -> 0x009b }
        L_0x013a:
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = "x5.tbs.decouple"
            r0.<init>(r4, r1)     // Catch:{ Exception -> 0x009b }
            int r1 = com.tencent.smtt.utils.a.a(r11, r10)     // Catch:{ Exception -> 0x009b }
            int r2 = com.tencent.smtt.utils.a.a(r11, r0)     // Catch:{ Exception -> 0x009b }
            if (r1 == r2) goto L_0x009c
            r0.delete()     // Catch:{ Exception -> 0x009b }
            com.tencent.smtt.utils.f.b(r10, r0)     // Catch:{ Exception -> 0x009b }
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.i.a(java.io.File, android.content.Context):void");
    }

    public static void c(Context context) {
        try {
            l.a();
            File s2 = l.s(context);
            new File(s2, "x5.tbs").delete();
            new File(s2, "x5.tbs.temp").delete();
            File a2 = a(context);
            if (a2 != null) {
                new File(a2, "x5.tbs.org").delete();
                new File(a2, "x5.oversea.tbs.org").delete();
                File[] listFiles = a2.listFiles();
                Pattern compile = Pattern.compile(a.a(true) + "(.*)");
                for (File file : listFiles) {
                    if (compile.matcher(file.getName()).find() && file.isFile() && file.exists()) {
                        file.delete();
                    }
                }
                Pattern compile2 = Pattern.compile(a.a(false) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile2.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception e2) {
        }
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v3, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v23, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], ?[OBJECT, ARRAY]]
  uses: [java.lang.Object, java.lang.CharSequence, ?[int, boolean, OBJECT, ARRAY, byte, short, char], java.net.HttpURLConnection]
  mth insns count: 96
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d6 A[SYNTHETIC, Splitter:B:37:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e1 A[SYNTHETIC, Splitter:B:44:0x00e1] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f3  */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m() {
        /*
            r9 = this;
            r3 = 0
            r1 = 1
            r2 = 0
            android.content.Context r0 = r9.g
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r4 = 3
            if (r0 != r4) goto L_0x00c6
            r0 = r1
        L_0x000d:
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi="
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            if (r0 == 0) goto L_0x008e
            android.content.Context r0 = r9.g
            java.lang.String r4 = com.tencent.smtt.utils.Apn.getWifiSSID(r0)
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsApkDwonloader.detectWifiNetworkAvailable] localBSSID="
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r5)
            java.net.URL r0 = new java.net.URL     // Catch:{ Throwable -> 0x00cf, all -> 0x00de }
            java.lang.String r5 = "http://pms.mb.qq.com/rsp204"
            r0.<init>(r5)     // Catch:{ Throwable -> 0x00cf, all -> 0x00de }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Throwable -> 0x00cf, all -> 0x00de }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Throwable -> 0x00cf, all -> 0x00de }
            r3 = 0
            r0.setInstanceFollowRedirects(r3)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            r3 = 10000(0x2710, float:1.4013E-41)
            r0.setConnectTimeout(r3)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            r3 = 10000(0x2710, float:1.4013E-41)
            r0.setReadTimeout(r3)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            r3 = 0
            r0.setUseCaches(r3)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            r0.getInputStream()     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            int r3 = r0.getResponseCode()     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            java.lang.String r5 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            r6.<init>()     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            java.lang.String r7 = "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode="
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ Throwable -> 0x00ee, all -> 0x00e7 }
            r5 = 204(0xcc, float:2.86E-43)
            if (r3 != r5) goto L_0x00c9
        L_0x0087:
            if (r0 == 0) goto L_0x00f5
            r0.disconnect()     // Catch:{ Exception -> 0x00cb }
            r3 = r4
            r2 = r1
        L_0x008e:
            if (r2 != 0) goto L_0x00b6
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x00b6
            java.util.Set<java.lang.String> r0 = r9.A
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x00b6
            java.util.Set<java.lang.String> r0 = r9.A
            r0.add(r3)
            r9.n()
            android.os.Handler r0 = r9.z
            r1 = 150(0x96, float:2.1E-43)
            android.os.Message r0 = r0.obtainMessage(r1, r3)
            android.os.Handler r1 = r9.z
            r4 = 120000(0x1d4c0, double:5.9288E-319)
            r1.sendMessageDelayed(r0, r4)
        L_0x00b6:
            if (r2 == 0) goto L_0x00c5
            java.util.Set<java.lang.String> r0 = r9.A
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x00c5
            java.util.Set<java.lang.String> r0 = r9.A
            r0.remove(r3)
        L_0x00c5:
            return r2
        L_0x00c6:
            r0 = r2
            goto L_0x000d
        L_0x00c9:
            r1 = r2
            goto L_0x0087
        L_0x00cb:
            r0 = move-exception
            r3 = r4
            r2 = r1
            goto L_0x008e
        L_0x00cf:
            r0 = move-exception
            r1 = r3
        L_0x00d1:
            r0.printStackTrace()     // Catch:{ all -> 0x00eb }
            if (r1 == 0) goto L_0x00f3
            r1.disconnect()     // Catch:{ Exception -> 0x00db }
            r3 = r4
            goto L_0x008e
        L_0x00db:
            r0 = move-exception
            r3 = r4
            goto L_0x008e
        L_0x00de:
            r0 = move-exception
        L_0x00df:
            if (r3 == 0) goto L_0x00e4
            r3.disconnect()     // Catch:{ Exception -> 0x00e5 }
        L_0x00e4:
            throw r0
        L_0x00e5:
            r1 = move-exception
            goto L_0x00e4
        L_0x00e7:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x00df
        L_0x00eb:
            r0 = move-exception
            r3 = r1
            goto L_0x00df
        L_0x00ee:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x00d1
        L_0x00f3:
            r3 = r4
            goto L_0x008e
        L_0x00f5:
            r3 = r4
            r2 = r1
            goto L_0x008e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.i.m():boolean");
    }

    private void n() {
        if (this.z == null) {
            this.z = new Handler(k.a().getLooper()) {
                public void handleMessage(Message message) {
                    if (message.what == 150) {
                        i.this.m();
                    }
                }
            };
        }
    }

    public boolean d() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.C);
        return this.C;
    }
}
