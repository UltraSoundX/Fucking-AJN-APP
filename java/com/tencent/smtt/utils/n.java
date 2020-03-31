package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import com.stub.StubApp;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/* compiled from: TbsCommonConfig */
public class n {
    private static n c = null;
    private Context a = null;
    private File b = null;
    private String d = "http://log.tbs.qq.com/ajax?c=pu&v=2&k=";
    private String e = "http://log.tbs.qq.com/ajax?c=pu&tk=";
    private String f = "http://wup.imtt.qq.com:8080";
    private String g = "http://log.tbs.qq.com/ajax?c=dl&k=";
    private String h = "http://cfg.imtt.qq.com/tbs?v=2&mk=";
    private String i = "http://log.tbs.qq.com/ajax?c=ul&v=2&k=";
    private String j = "http://mqqad.html5.qq.com/adjs";
    private String k = "http://log.tbs.qq.com/ajax?c=ucfu&k=";

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (c == null) {
                c = new n(context);
            }
            nVar = c;
        }
        return nVar;
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            nVar = c;
        }
        return nVar;
    }

    @TargetApi(11)
    private n(Context context) {
        TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        g();
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f9 A[SYNTHETIC, Splitter:B:54:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0108 A[SYNTHETIC, Splitter:B:62:0x0108] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void g() {
        /*
            r5 = this;
            monitor-enter(r5)
            r1 = 0
            java.io.File r0 = r5.h()     // Catch:{ Throwable -> 0x00cd, all -> 0x0104 }
            if (r0 != 0) goto L_0x001e
            java.lang.String r0 = "TbsCommonConfig"
            java.lang.String r2 = "Config file is null, default values will be applied"
            com.tencent.smtt.utils.TbsLog.e(r0, r2)     // Catch:{ Throwable -> 0x00cd, all -> 0x0104 }
            if (r1 == 0) goto L_0x0014
            r1.close()     // Catch:{ IOException -> 0x0016 }
        L_0x0014:
            monitor-exit(r5)
            return
        L_0x0016:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001b }
            goto L_0x0014
        L_0x001b:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        L_0x001e:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00cd, all -> 0x0104 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00cd, all -> 0x0104 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00cd, all -> 0x0104 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00cd, all -> 0x0104 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            r0.<init>()     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            r0.load(r2)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r1 = "pv_post_url"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x0042
            r5.d = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x0042:
            java.lang.String r1 = "wup_proxy_domain"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x0054
            r5.f = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x0054:
            java.lang.String r1 = "tbs_download_stat_post_url"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x0066
            r5.g = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x0066:
            java.lang.String r1 = "tbs_downloader_post_url"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x0078
            r5.h = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x0078:
            java.lang.String r1 = "tbs_log_post_url"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x008a
            r5.i = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x008a:
            java.lang.String r1 = "tips_url"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x009c
            r5.j = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x009c:
            java.lang.String r1 = "tbs_cmd_post_url"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r3 != 0) goto L_0x00ae
            r5.k = r1     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x00ae:
            java.lang.String r1 = "pv_post_url_tk"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
            if (r1 != 0) goto L_0x00c0
            r5.e = r0     // Catch:{ Throwable -> 0x0116, all -> 0x0111 }
        L_0x00c0:
            if (r2 == 0) goto L_0x0014
            r2.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x0014
        L_0x00c7:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001b }
            goto L_0x0014
        L_0x00cd:
            r0 = move-exception
        L_0x00ce:
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x0113 }
            r2.<init>()     // Catch:{ all -> 0x0113 }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ all -> 0x0113 }
            r3.<init>(r2)     // Catch:{ all -> 0x0113 }
            r0.printStackTrace(r3)     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = "TbsCommonConfig"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r3.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r4 = "exceptions occurred1:"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0113 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x0113 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0113 }
            com.tencent.smtt.utils.TbsLog.e(r0, r2)     // Catch:{ all -> 0x0113 }
            if (r1 == 0) goto L_0x0014
            r1.close()     // Catch:{ IOException -> 0x00fe }
            goto L_0x0014
        L_0x00fe:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001b }
            goto L_0x0014
        L_0x0104:
            r0 = move-exception
            r2 = r1
        L_0x0106:
            if (r2 == 0) goto L_0x010b
            r2.close()     // Catch:{ IOException -> 0x010c }
        L_0x010b:
            throw r0     // Catch:{ all -> 0x001b }
        L_0x010c:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x001b }
            goto L_0x010b
        L_0x0111:
            r0 = move-exception
            goto L_0x0106
        L_0x0113:
            r0 = move-exception
            r2 = r1
            goto L_0x0106
        L_0x0116:
            r0 = move-exception
            r1 = r2
            goto L_0x00ce
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.n.g():void");
    }

    private File h() {
        File file;
        Throwable th;
        try {
            if (this.b == null) {
                this.b = new File(f.a(this.a, 5));
                if (this.b == null || !this.b.isDirectory()) {
                    return null;
                }
            }
            file = new File(this.b, "tbsnet.conf");
            if (!file.exists()) {
                TbsLog.e("TbsCommonConfig", "Get file(" + file.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.w("TbsCommonConfig", "pathc:" + file.getCanonicalPath());
                return file;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            file = null;
            th = th4;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
            return file;
        }
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.e;
    }
}
