package com.tencent.smtt.utils;

import android.content.Context;
import com.stub.StubApp;
import java.io.File;

/* compiled from: TbsConfigFile */
public class o {
    private static o e = null;
    public boolean a = false;
    private Context b = null;
    private File c = null;
    private boolean d = false;
    private File f = null;

    public static synchronized o a(Context context) {
        o oVar;
        synchronized (o.class) {
            if (e == null) {
                e = new o(context);
            }
            oVar = e;
        }
        return oVar;
    }

    private o(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        b();
    }

    public static synchronized o a() {
        o oVar;
        synchronized (o.class) {
            oVar = e;
        }
        return oVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x005a A[SYNTHETIC, Splitter:B:35:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0066 A[SYNTHETIC, Splitter:B:42:0x0066] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r4 = this;
            monitor-enter(r4)
            r1 = 0
            java.io.File r0 = r4.f     // Catch:{ Throwable -> 0x0054 }
            if (r0 != 0) goto L_0x000c
            java.io.File r0 = r4.d()     // Catch:{ Throwable -> 0x0054 }
            r4.f = r0     // Catch:{ Throwable -> 0x0054 }
        L_0x000c:
            java.io.File r0 = r4.f     // Catch:{ Throwable -> 0x0054 }
            if (r0 != 0) goto L_0x001f
            if (r1 == 0) goto L_0x0015
            r1.close()     // Catch:{ Exception -> 0x0017 }
        L_0x0015:
            monitor-exit(r4)
            return
        L_0x0017:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001c }
            goto L_0x0015
        L_0x001c:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x001f:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0054 }
            java.io.File r2 = r4.f     // Catch:{ Throwable -> 0x0054 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0054 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0054 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0054 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            r0.<init>()     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            r0.load(r2)     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            java.lang.String r1 = "setting_forceUseSystemWebview"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r1, r3)     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            if (r1 != 0) goto L_0x0049
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            r4.a = r0     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
        L_0x0049:
            if (r2 == 0) goto L_0x0015
            r2.close()     // Catch:{ Exception -> 0x004f }
            goto L_0x0015
        L_0x004f:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001c }
            goto L_0x0015
        L_0x0054:
            r0 = move-exception
        L_0x0055:
            r0.printStackTrace()     // Catch:{ all -> 0x0063 }
            if (r1 == 0) goto L_0x0015
            r1.close()     // Catch:{ Exception -> 0x005e }
            goto L_0x0015
        L_0x005e:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001c }
            goto L_0x0015
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            if (r1 == 0) goto L_0x0069
            r1.close()     // Catch:{ Exception -> 0x006a }
        L_0x0069:
            throw r0     // Catch:{ all -> 0x001c }
        L_0x006a:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x001c }
            goto L_0x0069
        L_0x006f:
            r0 = move-exception
            r1 = r2
            goto L_0x0064
        L_0x0072:
            r0 = move-exception
            r1 = r2
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.o.b():void");
    }

    private File d() {
        File file;
        try {
            if (this.c == null) {
                this.c = new File(this.b.getDir("tbs", 0), "core_private");
                if (this.c == null || !this.c.isDirectory()) {
                    return null;
                }
            }
            file = new File(this.c, "debug.conf");
            if (file != null && !file.exists()) {
                file.createNewFile();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            file = null;
        }
        return file;
    }

    public void a(boolean z) {
        this.d = z;
        c();
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r6 = this;
            r2 = 0
            r0 = 0
            r1 = 0
            java.io.File r4 = r6.d()     // Catch:{ Throwable -> 0x0061, all -> 0x0077 }
            if (r4 != 0) goto L_0x001a
            r0.close()     // Catch:{ Exception -> 0x0010 }
        L_0x000c:
            r1.close()     // Catch:{ Exception -> 0x0015 }
        L_0x000f:
            return
        L_0x0010:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x0015:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000f
        L_0x001a:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0061, all -> 0x0077 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0061, all -> 0x0077 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0061, all -> 0x0077 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0061, all -> 0x0077 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r0.<init>()     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r0.load(r3)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            java.lang.String r1 = "setting_forceUseSystemWebview"
            boolean r5 = r6.a     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r0.setProperty(r1, r5)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            java.lang.String r1 = "result_systemWebviewForceUsed"
            boolean r5 = r6.d     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r0.setProperty(r1, r5)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x0093, all -> 0x008a }
            r2 = 0
            r0.store(r1, r2)     // Catch:{ Throwable -> 0x0097, all -> 0x008c }
            r3.close()     // Catch:{ Exception -> 0x005c }
        L_0x0053:
            r1.close()     // Catch:{ Exception -> 0x0057 }
            goto L_0x000f
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000f
        L_0x005c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0053
        L_0x0061:
            r0 = move-exception
            r1 = r2
        L_0x0063:
            r0.printStackTrace()     // Catch:{ all -> 0x008f }
            r2.close()     // Catch:{ Exception -> 0x0072 }
        L_0x0069:
            r1.close()     // Catch:{ Exception -> 0x006d }
            goto L_0x000f
        L_0x006d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000f
        L_0x0072:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0069
        L_0x0077:
            r0 = move-exception
            r3 = r2
        L_0x0079:
            r3.close()     // Catch:{ Exception -> 0x0080 }
        L_0x007c:
            r2.close()     // Catch:{ Exception -> 0x0085 }
        L_0x007f:
            throw r0
        L_0x0080:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x007c
        L_0x0085:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x007f
        L_0x008a:
            r0 = move-exception
            goto L_0x0079
        L_0x008c:
            r0 = move-exception
            r2 = r1
            goto L_0x0079
        L_0x008f:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x0079
        L_0x0093:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0063
        L_0x0097:
            r0 = move-exception
            r2 = r3
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.o.c():void");
    }
}
