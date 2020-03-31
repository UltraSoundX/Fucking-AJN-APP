package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build.VERSION;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* compiled from: SDKEngine */
class d {
    static int a = 0;
    static boolean b = false;
    private static d e = null;
    private static int h = 0;
    private static int i = 3;
    private static String k = null;
    private r c = null;
    private r d = null;
    private boolean f = false;
    private boolean g = false;
    private File j = null;

    private d() {
    }

    public static d a(boolean z) {
        if (e == null && z) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d();
                }
            }
        }
        return e;
    }

    public synchronized void a(Context context, boolean z, boolean z2) {
        boolean z3;
        File file;
        Context context2;
        File file2;
        String absolutePath;
        boolean z4 = true;
        synchronized (this) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INIT, null, new Object[0]);
            TbsLog.initIfNeed(context);
            TbsLog.i("SDKEngine", "init -- context: " + context + ", isPreIniting: " + z2);
            a++;
            TbsCoreLoadStat.getInstance().a();
            l.a().b(context, a == 1);
            l.a().k(context);
            TbsShareManager.forceToLoadX5ForThirdApp(context, true);
            boolean a2 = QbSdk.a(context, z, z2);
            boolean z5 = VERSION.SDK_INT >= 7;
            if (!a2 || !z5) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3) {
                long currentTimeMillis = System.currentTimeMillis();
                z3 = l.a().g(context, d());
                TbsLog.i("SDKEngine", "isTbsCoreLegal: " + z3 + "; cost: " + (System.currentTimeMillis() - currentTimeMillis));
            }
            if (!z3) {
                String str = "can_load_x5=" + a2 + "; is_compatible=" + z5;
                TbsLog.e("SDKEngine", "SDKEngine.init canLoadTbs=false; failure: " + str);
                if (!QbSdk.a || !this.f) {
                    this.f = false;
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_LOAD_TBS, new Throwable(str));
                }
                this.j = l.s(context);
                this.g = true;
            } else if (!this.f) {
                try {
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, null, new Object[0]);
                        if (TbsShareManager.j(context)) {
                            file = new File(TbsShareManager.c(context));
                            file2 = l.a().q(context);
                            context2 = TbsShareManager.e(context);
                            if (file2 == null) {
                                this.f = false;
                                QbSdk.a(context, "SDKEngine::useSystemWebView by error_tbs_core_dexopt_dir null!");
                            }
                        } else {
                            this.f = false;
                            QbSdk.a(context, "SDKEngine::useSystemWebView by error_host_unavailable");
                        }
                    } else {
                        TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, null, new Object[0]);
                        file2 = l.a().q(context);
                        if (!(h == 25436 || h == 25437)) {
                            z4 = false;
                        }
                        if (z4) {
                            context2 = StubApp.getOrigApplicationContext(context.getApplicationContext());
                        } else {
                            context2 = context;
                        }
                        if (file2 == null) {
                            this.f = false;
                            QbSdk.a(context, "SDKEngine::useSystemWebView by tbs_core_share_dir null!");
                        } else {
                            file = file2;
                        }
                    }
                    String[] dexLoaderFileList = QbSdk.getDexLoaderFileList(context, context2, file.getAbsolutePath());
                    for (int i2 = 0; i2 < dexLoaderFileList.length; i2++) {
                    }
                    if (TbsShareManager.getHostCorePathAppDefined() != null) {
                        absolutePath = TbsShareManager.getHostCorePathAppDefined();
                    } else {
                        absolutePath = file2.getAbsolutePath();
                    }
                    TbsLog.i("SDKEngine", "SDKEngine init optDir is " + absolutePath);
                    if (this.d != null) {
                        this.c = this.d;
                        this.c.a(context, context2, file.getAbsolutePath(), absolutePath, dexLoaderFileList, QbSdk.d);
                    } else {
                        this.c = new r(context, context2, file.getAbsolutePath(), absolutePath, dexLoaderFileList, QbSdk.d);
                    }
                    this.f = true;
                } catch (Throwable th) {
                    TbsLog.e("SDKEngine", "useSystemWebView by exception: " + th);
                    if (th == null) {
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.TEST_THROWABLE_IS_NULL);
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.TEST_THROWABLE_ISNOT_NULL, th);
                    }
                    this.f = false;
                    QbSdk.a(context, "SDKEngine::useSystemWebView by exception: " + th);
                }
                this.j = l.s(context);
                this.g = true;
            }
        }
    }

    public r a() {
        if (this.f) {
            return this.c;
        }
        return null;
    }

    public boolean b() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public r c() {
        return this.c;
    }

    public static int d() {
        return h;
    }

    static void a(int i2) {
        h = i2;
    }

    public String e() {
        if (this.c == null || QbSdk.a) {
            return "system webview get nothing...";
        }
        return this.c.a();
    }

    /* access modifiers changed from: 0000 */
    public boolean f() {
        if (b) {
            if (k == null) {
                return false;
            }
            int i2 = i();
            if (i2 == 0) {
                b(1);
            } else if (i2 + 1 > i) {
                return false;
            } else {
                b(i2 + 1);
            }
        }
        return b;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(boolean z) {
        b = z;
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean g() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public void a(String str) {
        k = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x005d A[SYNTHETIC, Splitter:B:31:0x005d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int i() {
        /*
            r5 = this;
            r0 = 0
            r3 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            java.io.File r2 = r5.j     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            java.lang.String r4 = "count.prop"
            r1.<init>(r2, r4)     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            if (r2 != 0) goto L_0x001c
            if (r3 == 0) goto L_0x0016
            r3.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0016:
            return r0
        L_0x0017:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x001c:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0049, all -> 0x0059 }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x0068 }
            r1.<init>()     // Catch:{ Exception -> 0x0068 }
            r1.load(r2)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r3 = k     // Catch:{ Exception -> 0x0068 }
            java.lang.String r4 = "1"
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch:{ Exception -> 0x0068 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0068 }
            int r0 = r1.intValue()     // Catch:{ Exception -> 0x0068 }
            if (r2 == 0) goto L_0x0016
            r2.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0016
        L_0x0044:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x0049:
            r1 = move-exception
            r2 = r3
        L_0x004b:
            r1.printStackTrace()     // Catch:{ all -> 0x0066 }
            if (r2 == 0) goto L_0x0016
            r2.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0016
        L_0x0054:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0016
        L_0x0059:
            r0 = move-exception
            r2 = r3
        L_0x005b:
            if (r2 == 0) goto L_0x0060
            r2.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0060:
            throw r0
        L_0x0061:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0060
        L_0x0066:
            r0 = move-exception
            goto L_0x005b
        L_0x0068:
            r1 = move-exception
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.d.i():int");
    }

    private void b(int i2) {
        String valueOf = String.valueOf(i2);
        Properties properties = new Properties();
        properties.setProperty(k, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.j, "count.prop")), null);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public boolean h() {
        return QbSdk.useSoftWare();
    }
}
