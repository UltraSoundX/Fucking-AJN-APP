package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TbsDownloadUpload {
    private static TbsDownloadUpload b;
    Map<String, Object> a = new HashMap();
    private Context c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    public SharedPreferences mPreferences;

    public interface TbsUploadKey {
        public static final String KEY_LOCAL_CORE_VERSION = "tbs_local_core_version";
        public static final String KEY_NEEDDOWNLOAD_CODE = "tbs_needdownload_code";
        public static final String KEY_NEEDDOWNLOAD_RETURN = "tbs_needdownload_return";
        public static final String KEY_NEEDDOWNLOAD_SENT = "tbs_needdownload_sent";
        public static final String KEY_STARTDOWNLOAD_CODE = "tbs_startdownload_code";
        public static final String KEY_STARTDOWNLOAD_SENT = "tbs_startdownload_sent";
    }

    public TbsDownloadUpload(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_upload", 4);
        this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.c == null) {
            this.c = context;
        }
    }

    public static synchronized TbsDownloadUpload getInstance(Context context) {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            if (b == null) {
                b = new TbsDownloadUpload(context);
            }
            tbsDownloadUpload = b;
        }
        return tbsDownloadUpload;
    }

    public static synchronized TbsDownloadUpload getInstance() {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            tbsDownloadUpload = b;
        }
        return tbsDownloadUpload;
    }

    public static synchronized void clear() {
        synchronized (TbsDownloadUpload.class) {
            b = null;
        }
    }

    public void clearUploadCode() {
        this.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(0));
        this.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(0));
        this.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(0));
        this.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, Integer.valueOf(0));
        this.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, Integer.valueOf(0));
        this.a.put(TbsUploadKey.KEY_LOCAL_CORE_VERSION, Integer.valueOf(0));
        writeTbsDownloadInfo();
    }

    public synchronized int getNeedDownloadCode() {
        int i2;
        if (this.g == 1) {
            i2 = ErrorCode.NEEDDOWNLOAD_9;
        } else {
            i2 = this.d;
        }
        return i2;
    }

    public synchronized int getLocalCoreVersion() {
        return this.i;
    }

    public synchronized int getStartDownloadCode() {
        int i2;
        if (this.h == 1) {
            i2 = ErrorCode.STARTDOWNLOAD_9;
        } else {
            i2 = this.e;
        }
        return i2;
    }

    public synchronized int getNeedDownloadReturn() {
        return this.f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f2 A[SYNTHETIC, Splitter:B:56:0x00f2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void readTbsDownloadInfo(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r2 = 0
            android.content.Context r0 = r4.c     // Catch:{ Throwable -> 0x00dc, all -> 0x00ee }
            java.lang.String r1 = "download_upload"
            java.io.File r0 = a(r0, r1)     // Catch:{ Throwable -> 0x00dc, all -> 0x00ee }
            if (r0 != 0) goto L_0x001b
            if (r2 == 0) goto L_0x0011
            r2.close()     // Catch:{ Exception -> 0x0013 }
        L_0x0011:
            monitor-exit(r4)
            return
        L_0x0013:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0018 }
            goto L_0x0011
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x001b:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00dc, all -> 0x00ee }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00dc, all -> 0x00ee }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00dc, all -> 0x00ee }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x00dc, all -> 0x00ee }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ Throwable -> 0x00fd }
            r0.<init>()     // Catch:{ Throwable -> 0x00fd }
            r0.load(r1)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r2 = "tbs_needdownload_code"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00fd }
            if (r3 != 0) goto L_0x0048
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00fd }
            r3 = 0
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            r4.d = r2     // Catch:{ Throwable -> 0x00fd }
        L_0x0048:
            java.lang.String r2 = "tbs_startdownload_code"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00fd }
            if (r3 != 0) goto L_0x0063
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00fd }
            r3 = 0
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            r4.e = r2     // Catch:{ Throwable -> 0x00fd }
        L_0x0063:
            java.lang.String r2 = "tbs_needdownload_return"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00fd }
            if (r3 != 0) goto L_0x007e
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00fd }
            r3 = 0
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            r4.f = r2     // Catch:{ Throwable -> 0x00fd }
        L_0x007e:
            java.lang.String r2 = "tbs_needdownload_sent"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00fd }
            if (r3 != 0) goto L_0x0099
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00fd }
            r3 = 0
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            r4.g = r2     // Catch:{ Throwable -> 0x00fd }
        L_0x0099:
            java.lang.String r2 = "tbs_startdownload_sent"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00fd }
            if (r3 != 0) goto L_0x00b4
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00fd }
            r3 = 0
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            r4.h = r2     // Catch:{ Throwable -> 0x00fd }
        L_0x00b4:
            java.lang.String r2 = "tbs_local_core_version"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r0)     // Catch:{ Throwable -> 0x00fd }
            if (r2 != 0) goto L_0x00cf
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x00fd }
            r2 = 0
            int r0 = java.lang.Math.max(r0, r2)     // Catch:{ Throwable -> 0x00fd }
            r4.i = r0     // Catch:{ Throwable -> 0x00fd }
        L_0x00cf:
            if (r1 == 0) goto L_0x0011
            r1.close()     // Catch:{ Exception -> 0x00d6 }
            goto L_0x0011
        L_0x00d6:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0018 }
            goto L_0x0011
        L_0x00dc:
            r0 = move-exception
            r1 = r2
        L_0x00de:
            r0.printStackTrace()     // Catch:{ all -> 0x00fb }
            if (r1 == 0) goto L_0x0011
            r1.close()     // Catch:{ Exception -> 0x00e8 }
            goto L_0x0011
        L_0x00e8:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0018 }
            goto L_0x0011
        L_0x00ee:
            r0 = move-exception
            r1 = r2
        L_0x00f0:
            if (r1 == 0) goto L_0x00f5
            r1.close()     // Catch:{ Exception -> 0x00f6 }
        L_0x00f5:
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x00f6:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0018 }
            goto L_0x00f5
        L_0x00fb:
            r0 = move-exception
            goto L_0x00f0
        L_0x00fd:
            r0 = move-exception
            goto L_0x00de
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadUpload.readTbsDownloadInfo(android.content.Context):void");
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
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009c A[SYNTHETIC, Splitter:B:38:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a1 A[SYNTHETIC, Splitter:B:41:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00df A[SYNTHETIC, Splitter:B:67:0x00df] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e4 A[SYNTHETIC, Splitter:B:70:0x00e4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void writeTbsDownloadInfo() {
        /*
            r10 = this;
            r1 = 0
            monitor-enter(r10)
            java.lang.String r0 = "TbsDownloadUpload"
            java.lang.String r2 = "writeTbsDownloadInfo #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x0026 }
            r0 = 0
            r2 = 0
            android.content.Context r3 = r10.c     // Catch:{ Throwable -> 0x00fa, all -> 0x00db }
            java.lang.String r4 = "download_upload"
            java.io.File r4 = a(r3, r4)     // Catch:{ Throwable -> 0x00fa, all -> 0x00db }
            if (r4 != 0) goto L_0x002e
            if (r1 == 0) goto L_0x001a
            r0.close()     // Catch:{ Exception -> 0x0021 }
        L_0x001a:
            if (r1 == 0) goto L_0x001f
            r2.close()     // Catch:{ Exception -> 0x0029 }
        L_0x001f:
            monitor-exit(r10)
            return
        L_0x0021:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x001a
        L_0x0026:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x0029:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x001f
        L_0x002e:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00fa, all -> 0x00db }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00fa, all -> 0x00db }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00fa, all -> 0x00db }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00fa, all -> 0x00db }
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r5.<init>()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r5.load(r3)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r10.a     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.util.Set r0 = r0.keySet()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
        L_0x004a:
            boolean r0 = r2.hasNext()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            if (r0 == 0) goto L_0x00ac
            java.lang.Object r0 = r2.next()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.util.Map<java.lang.String, java.lang.Object> r6 = r10.a     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.Object r6 = r6.get(r0)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r7.<init>()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r8 = ""
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.StringBuilder r7 = r7.append(r6)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r5.setProperty(r0, r7)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r7 = "TbsDownloadUpload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r8.<init>()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r9 = "writeTbsDownloadInfo key is "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.StringBuilder r0 = r8.append(r0)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r8 = " value is "
            java.lang.StringBuilder r0 = r0.append(r8)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.StringBuilder r0 = r0.append(r6)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            com.tencent.smtt.utils.TbsLog.i(r7, r0)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            goto L_0x004a
        L_0x0095:
            r0 = move-exception
            r2 = r3
        L_0x0097:
            r0.printStackTrace()     // Catch:{ all -> 0x00f7 }
            if (r2 == 0) goto L_0x009f
            r2.close()     // Catch:{ Exception -> 0x00d6 }
        L_0x009f:
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ Exception -> 0x00a6 }
            goto L_0x001f
        L_0x00a6:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x001f
        L_0x00ac:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r10.a     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r0.clear()     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0095, all -> 0x00f2 }
            r0 = 0
            r5.store(r2, r0)     // Catch:{ Throwable -> 0x00fd, all -> 0x00f4 }
            if (r3 == 0) goto L_0x00c4
            r3.close()     // Catch:{ Exception -> 0x00d1 }
        L_0x00c4:
            if (r2 == 0) goto L_0x001f
            r2.close()     // Catch:{ Exception -> 0x00cb }
            goto L_0x001f
        L_0x00cb:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x001f
        L_0x00d1:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x00c4
        L_0x00d6:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x009f
        L_0x00db:
            r0 = move-exception
            r3 = r1
        L_0x00dd:
            if (r3 == 0) goto L_0x00e2
            r3.close()     // Catch:{ Exception -> 0x00e8 }
        L_0x00e2:
            if (r1 == 0) goto L_0x00e7
            r1.close()     // Catch:{ Exception -> 0x00ed }
        L_0x00e7:
            throw r0     // Catch:{ all -> 0x0026 }
        L_0x00e8:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x00e2
        L_0x00ed:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x00e7
        L_0x00f2:
            r0 = move-exception
            goto L_0x00dd
        L_0x00f4:
            r0 = move-exception
            r1 = r2
            goto L_0x00dd
        L_0x00f7:
            r0 = move-exception
            r3 = r2
            goto L_0x00dd
        L_0x00fa:
            r0 = move-exception
            r2 = r1
            goto L_0x0097
        L_0x00fd:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0097
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadUpload.writeTbsDownloadInfo():void");
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }
}
