package com.tencent.smtt.sdk;

import android.content.Context;
import com.stub.StubApp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class TbsBaseConfig {
    public static final String TAG = "TbsBaseConfig";
    Map<String, String> a;
    private Context b;

    public abstract String getConfigFileName();

    public void init(Context context) {
        this.a = new HashMap();
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.b == null) {
            this.b = context;
        }
        refreshSyncMap(context);
    }

    private static File a(Context context, String str) {
        l.a();
        File s = l.s(context);
        if (s == null) {
            return null;
        }
        File file = new File(s, str);
        if (file != null && file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear() {
        this.a.clear();
        commit();
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0070 A[SYNTHETIC, Splitter:B:41:0x0070] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void refreshSyncMap(android.content.Context r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            r2 = 0
            android.content.Context r0 = r6.b     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            java.lang.String r1 = r6.getConfigFileName()     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            java.io.File r0 = a(r0, r1)     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            if (r0 != 0) goto L_0x001d
            if (r2 == 0) goto L_0x0013
            r2.close()     // Catch:{ Exception -> 0x0015 }
        L_0x0013:
            monitor-exit(r6)
            return
        L_0x0015:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001a }
            goto L_0x0013
        L_0x001a:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x001d:
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.a     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            r1.clear()     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x007b, all -> 0x006c }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Throwable -> 0x0052 }
            r2.<init>()     // Catch:{ Throwable -> 0x0052 }
            r2.load(r1)     // Catch:{ Throwable -> 0x0052 }
            java.util.Set r0 = r2.stringPropertyNames()     // Catch:{ Throwable -> 0x0052 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ Throwable -> 0x0052 }
        L_0x003c:
            boolean r0 = r3.hasNext()     // Catch:{ Throwable -> 0x0052 }
            if (r0 == 0) goto L_0x0061
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0052 }
            java.util.Map<java.lang.String, java.lang.String> r4 = r6.a     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r5 = r2.getProperty(r0)     // Catch:{ Throwable -> 0x0052 }
            r4.put(r0, r5)     // Catch:{ Throwable -> 0x0052 }
            goto L_0x003c
        L_0x0052:
            r0 = move-exception
        L_0x0053:
            r0.printStackTrace()     // Catch:{ all -> 0x0079 }
            if (r1 == 0) goto L_0x0013
            r1.close()     // Catch:{ Exception -> 0x005c }
            goto L_0x0013
        L_0x005c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001a }
            goto L_0x0013
        L_0x0061:
            if (r1 == 0) goto L_0x0013
            r1.close()     // Catch:{ Exception -> 0x0067 }
            goto L_0x0013
        L_0x0067:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x001a }
            goto L_0x0013
        L_0x006c:
            r0 = move-exception
            r1 = r2
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0073:
            throw r0     // Catch:{ all -> 0x001a }
        L_0x0074:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x001a }
            goto L_0x0073
        L_0x0079:
            r0 = move-exception
            goto L_0x006e
        L_0x007b:
            r0 = move-exception
            r1 = r2
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.refreshSyncMap(android.content.Context):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a1 A[SYNTHETIC, Splitter:B:38:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a6 A[SYNTHETIC, Splitter:B:41:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e4 A[SYNTHETIC, Splitter:B:67:0x00e4] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e9 A[SYNTHETIC, Splitter:B:70:0x00e9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void writeTbsDownloadInfo() {
        /*
            r10 = this;
            r1 = 0
            monitor-enter(r10)
            java.lang.String r0 = "TbsBaseConfig"
            java.lang.String r2 = "writeTbsDownloadInfo #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x0028 }
            r0 = 0
            r2 = 0
            android.content.Context r3 = r10.b     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            java.lang.String r4 = r10.getConfigFileName()     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            java.io.File r4 = a(r3, r4)     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            if (r4 != 0) goto L_0x0030
            if (r1 == 0) goto L_0x001c
            r0.close()     // Catch:{ Exception -> 0x0023 }
        L_0x001c:
            if (r1 == 0) goto L_0x0021
            r2.close()     // Catch:{ Exception -> 0x002b }
        L_0x0021:
            monitor-exit(r10)
            return
        L_0x0023:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x001c
        L_0x0028:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x002b:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x0021
        L_0x0030:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00ff, all -> 0x00e0 }
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r5.<init>()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r5.load(r3)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r5.clear()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.util.Map<java.lang.String, java.lang.String> r0 = r10.a     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.util.Set r0 = r0.keySet()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
        L_0x004f:
            boolean r0 = r2.hasNext()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            if (r0 == 0) goto L_0x00b1
            java.lang.Object r0 = r2.next()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.util.Map<java.lang.String, java.lang.String> r6 = r10.a     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.Object r6 = r6.get(r0)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r7.<init>()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r8 = ""
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.StringBuilder r7 = r7.append(r6)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r5.setProperty(r0, r7)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r7 = "TbsBaseConfig"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r8.<init>()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r9 = "writeTbsDownloadInfo key is "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.StringBuilder r0 = r8.append(r0)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r8 = " value is "
            java.lang.StringBuilder r0 = r0.append(r8)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.StringBuilder r0 = r0.append(r6)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            com.tencent.smtt.utils.TbsLog.i(r7, r0)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            goto L_0x004f
        L_0x009a:
            r0 = move-exception
            r2 = r3
        L_0x009c:
            r0.printStackTrace()     // Catch:{ all -> 0x00fc }
            if (r2 == 0) goto L_0x00a4
            r2.close()     // Catch:{ Exception -> 0x00db }
        L_0x00a4:
            if (r1 == 0) goto L_0x0021
            r1.close()     // Catch:{ Exception -> 0x00ab }
            goto L_0x0021
        L_0x00ab:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x0021
        L_0x00b1:
            java.util.Map<java.lang.String, java.lang.String> r0 = r10.a     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r0.clear()     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x009a, all -> 0x00f7 }
            r0 = 0
            r5.store(r2, r0)     // Catch:{ Throwable -> 0x0102, all -> 0x00f9 }
            if (r3 == 0) goto L_0x00c9
            r3.close()     // Catch:{ Exception -> 0x00d6 }
        L_0x00c9:
            if (r2 == 0) goto L_0x0021
            r2.close()     // Catch:{ Exception -> 0x00d0 }
            goto L_0x0021
        L_0x00d0:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x0021
        L_0x00d6:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x00c9
        L_0x00db:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x00a4
        L_0x00e0:
            r0 = move-exception
            r3 = r1
        L_0x00e2:
            if (r3 == 0) goto L_0x00e7
            r3.close()     // Catch:{ Exception -> 0x00ed }
        L_0x00e7:
            if (r1 == 0) goto L_0x00ec
            r1.close()     // Catch:{ Exception -> 0x00f2 }
        L_0x00ec:
            throw r0     // Catch:{ all -> 0x0028 }
        L_0x00ed:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x00e7
        L_0x00f2:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0028 }
            goto L_0x00ec
        L_0x00f7:
            r0 = move-exception
            goto L_0x00e2
        L_0x00f9:
            r0 = move-exception
            r1 = r2
            goto L_0x00e2
        L_0x00fc:
            r0 = move-exception
            r3 = r2
            goto L_0x00e2
        L_0x00ff:
            r0 = move-exception
            r2 = r1
            goto L_0x009c
        L_0x0102:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.writeTbsDownloadInfo():void");
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }
}
