package com.tencent.android.tpush.cloudctr.network;

import android.content.Context;
import android.os.Bundle;
import com.baidu.mobstat.Config;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/* compiled from: ProGuard */
public class CloudControlDownloadControl {
    public static boolean b = true;
    public boolean a = false;
    private long c = 0;
    private long d = 0;
    private Bundle e;

    /* compiled from: ProGuard */
    public class DownLoadException extends Exception {
        public DownLoadException(String str) {
            super(str);
        }
    }

    /* compiled from: ProGuard */
    public interface a {
        void a(int i);

        void a(String str, boolean z);
    }

    CloudControlDownloadControl(Context context, DownloadItem downloadItem, Bundle bundle, a aVar, int i) {
        int i2 = 0;
        com.tencent.android.tpush.b.a.a("CloudCtrDownload", "Create downloadControl");
        this.a = false;
        this.e = bundle;
        while (b) {
            if (this.a) {
                com.tencent.android.tpush.b.a.e("CloudCtrDownload", "Download is already stopped. Dont start again.");
                aVar.a(1);
                return;
            } else if (downloadItem._downloadRetryTimes == 0) {
                com.tencent.android.tpush.b.a.g("CloudCtrDownload", "try to connect too much. stop download now.");
                if (aVar != null) {
                    this.a = true;
                    CloudControlDownloadService.a.remove(downloadItem);
                    aVar.a(2);
                    return;
                }
                return;
            } else if (i2 >= 3) {
                com.tencent.android.tpush.b.a.g("CloudCtrDownload", "check md5 error too much. stop download now.");
                if (aVar != null) {
                    this.a = true;
                    CloudControlDownloadService.a.remove(downloadItem);
                    aVar.a(2);
                    return;
                }
                return;
            } else {
                int a2 = a(context, aVar, downloadItem);
                downloadItem._downloadRetryTimes--;
                if (a2 == -1) {
                    com.tencent.android.tpush.b.a.c("CloudCtrDownload", "Connect time out, try rest - " + downloadItem._downloadRetryTimes);
                    try {
                        Thread.sleep((long) i);
                    } catch (InterruptedException e2) {
                    }
                } else if (a2 == 0) {
                    com.tencent.android.tpush.b.a.c("CloudCtrDownload", "Download again, try rest - " + downloadItem._downloadRetryTimes);
                    try {
                        Thread.sleep((long) i);
                    } catch (InterruptedException e3) {
                    }
                } else if (a2 == 1) {
                    com.tencent.android.tpush.b.a.c("CloudCtrDownload", "Download succeed.");
                    this.a = true;
                    return;
                } else if (a2 == 2) {
                    com.tencent.android.tpush.b.a.c("CloudCtrDownload", "md5 check error, try again - " + downloadItem._downloadRetryTimes);
                    i2++;
                } else if (a2 == -3) {
                    this.a = true;
                    CloudControlDownloadService.a.remove(downloadItem);
                    aVar.a(3);
                    return;
                } else {
                    com.tencent.android.tpush.b.a.c("CloudCtrDownload", "Other exception!!");
                    this.a = true;
                    CloudControlDownloadService.a.remove(downloadItem);
                    aVar.a(2);
                    return;
                }
            }
        }
        com.tencent.android.tpush.b.a.e("CloudCtrDownload", "network is not available, dont download");
        this.a = true;
        aVar.a(1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:208:?, code lost:
        r8.flush();
        com.tencent.android.tpush.b.a.c("CloudCtrDownload", "Download finished");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x04b3, code lost:
        if (r0 == null) goto L_0x04e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x04bb, code lost:
        if (r0.length() != r16) goto L_0x04e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x04c3, code lost:
        if (com.tencent.android.tpush.cloudctr.b.b.a(r14, r0) == false) goto L_0x04e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x04c5, code lost:
        r22.e.remove(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x04cc, code lost:
        if (r24 == null) goto L_0x04d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x04ce, code lost:
        r24.a(r0.getAbsolutePath(), false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x04d8, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        com.tencent.android.tpush.b.a.g("CloudCtrDownload", "The download file is not valid, download again");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x04ec, code lost:
        if (r0.delete() != false) goto L_0x04fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x04ee, code lost:
        com.tencent.android.tpush.b.a.i("CloudCtrDownload", "delete file fail !!!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x04f5, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x04fe, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:?, code lost:
        return -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:?, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:?, code lost:
        return -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:?, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:?, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:?, code lost:
        return -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:?, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0176, code lost:
        if (r0 == null) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x017e, code lost:
        if (r0.length() != r18) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0186, code lost:
        if (com.tencent.android.tpush.cloudctr.b.b.a(r14, r0) == false) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x018c, code lost:
        if (r15.exists() == false) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x018e, code lost:
        r15.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0197, code lost:
        if (r0.renameTo(r15) != false) goto L_0x01a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0199, code lost:
        com.tencent.android.tpush.b.a.g("CloudCtrDownload", "rename file error");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01a0, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r22.e.remove(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01af, code lost:
        if (r24 == null) goto L_0x01bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01b1, code lost:
        r24.a(r15.getAbsolutePath(), false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01bb, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        com.tencent.android.tpush.b.a.g("CloudCtrDownload", "The download file is not valid, download again");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01ce, code lost:
        if (r0.delete() != false) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01d0, code lost:
        com.tencent.android.tpush.b.a.i("CloudCtrDownload", "delete file fail !!!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d7, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01e0, code lost:
        a(r5, r6, r7, r8, r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(android.content.Context r23, com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl.a r24, com.tencent.android.tpush.cloudctr.network.DownloadItem r25) {
        /*
            r22 = this;
            java.lang.String r4 = r25.getDownloadUrl()
            java.lang.String r14 = r25.getMd5()
            java.lang.String r6 = r25.getDownloadSavedDir()
            java.lang.String r7 = r25.getFileName()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r8 = ".downloading"
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r15 = r5.toString()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x0639
            boolean r5 = android.text.TextUtils.isEmpty(r6)
            if (r5 != 0) goto L_0x0639
            boolean r5 = android.text.TextUtils.isEmpty(r7)
            if (r5 != 0) goto L_0x0639
            java.lang.String r5 = "CloudCtrDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "action:download - url:"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r4)
            java.lang.String r9 = ", saveFilePath:"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r9 = ", fileName:"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r7)
            java.lang.String r8 = r8.toString()
            com.tencent.android.tpush.b.a.e(r5, r8)
            r0 = r22
            r0.a(r6)
            r0 = r22
            android.os.Bundle r5 = r0.e
            r8 = -1
            long r16 = r5.getLong(r4, r8)
            r10 = 0
            r5 = 0
            r13 = 0
            r8 = 0
            r12 = 0
            r9 = 0
            r18 = 0
            int r18 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r18 > 0) goto L_0x038e
            java.io.File r16 = new java.io.File
            r0 = r16
            r0.<init>(r6, r15)
            java.io.File r15 = new java.io.File
            r15.<init>(r6, r7)
            boolean r6 = r16.exists()
            if (r6 == 0) goto L_0x0099
            long r18 = r16.length()
            r20 = 0
            int r6 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r6 > 0) goto L_0x02c9
        L_0x0099:
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r17.<init>()
            java.lang.String r18 = "Download file: "
            java.lang.StringBuilder r17 = r17.append(r18)
            r0 = r17
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r7 = r7.toString()
            com.tencent.android.tpush.b.a.a(r6, r7)
            r6 = -1
            r0 = r22
            org.apache.http.client.methods.HttpGet r6 = r0.a(r4, r6)
            org.apache.http.impl.client.DefaultHttpClient r7 = r22.a()
            org.apache.http.HttpResponse r6 = r7.execute(r6)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            if (r6 == 0) goto L_0x025b
            org.apache.http.StatusLine r7 = r6.getStatusLine()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            int r7 = r7.getStatusCode()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r17 = 200(0xc8, float:2.8E-43)
            r0 = r17
            if (r7 != r0) goto L_0x020f
            org.apache.http.HttpEntity r9 = r6.getEntity()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            boolean r7 = r9.isStreaming()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            if (r7 == 0) goto L_0x01fc
            r0 = r22
            long r18 = r0.a(r6)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r0 = r22
            android.os.Bundle r6 = r0.e     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r0 = r18
            r6.putLong(r4, r0)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r0 = r22
            r1 = r18
            int r6 = r0.a(r1)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r0 = r25
            r0._downloadRetryTimes = r6     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.io.InputStream r5 = r9.getContent()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            if (r5 == 0) goto L_0x01e9
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r6.<init>(r5)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r16.delete()     // Catch:{ NumberFormatException -> 0x0710, ClientProtocolException -> 0x0701, IllegalStateException -> 0x06ef, FileNotFoundException -> 0x06e3, IOException -> 0x06d7, DownLoadException -> 0x06cb, all -> 0x06bc }
            r16.createNewFile()     // Catch:{ NumberFormatException -> 0x0710, ClientProtocolException -> 0x0701, IllegalStateException -> 0x06ef, FileNotFoundException -> 0x06e3, IOException -> 0x06d7, DownLoadException -> 0x06cb, all -> 0x06bc }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ NumberFormatException -> 0x0710, ClientProtocolException -> 0x0701, IllegalStateException -> 0x06ef, FileNotFoundException -> 0x06e3, IOException -> 0x06d7, DownLoadException -> 0x06cb, all -> 0x06bc }
            r0 = r16
            r7.<init>(r0)     // Catch:{ NumberFormatException -> 0x0710, ClientProtocolException -> 0x0701, IllegalStateException -> 0x06ef, FileNotFoundException -> 0x06e3, IOException -> 0x06d7, DownLoadException -> 0x06cb, all -> 0x06bc }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ NumberFormatException -> 0x0715, ClientProtocolException -> 0x0706, IllegalStateException -> 0x06f4, FileNotFoundException -> 0x06e8, IOException -> 0x06dc, DownLoadException -> 0x06d0, all -> 0x06c2 }
            r8.<init>(r7)     // Catch:{ NumberFormatException -> 0x0715, ClientProtocolException -> 0x0706, IllegalStateException -> 0x06f4, FileNotFoundException -> 0x06e8, IOException -> 0x06dc, DownLoadException -> 0x06d0, all -> 0x06c2 }
            r12 = 1024(0x400, float:1.435E-42)
            byte[] r12 = new byte[r12]     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
        L_0x0119:
            int r13 = r6.read(r12)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r17 = -1
            r0 = r17
            if (r13 == r0) goto L_0x0173
            r0 = r22
            boolean r0 = r0.a     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r17 = r0
            if (r17 == 0) goto L_0x014c
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "stop download by user, throw Exception."
            com.tencent.android.tpush.b.a.g(r4, r10)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl$DownLoadException r4 = new com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl$DownLoadException     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            java.lang.String r10 = "stop download by user."
            r0 = r22
            r4.<init>(r10)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            throw r4     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
        L_0x013c:
            r4 = move-exception
        L_0x013d:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "NumberFormatException, get content length from http fail."
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x06c7 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
        L_0x014b:
            return r4
        L_0x014c:
            r17 = 0
            r0 = r17
            r8.write(r12, r0, r13)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            long r0 = (long) r13     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r20 = r0
            long r10 = r10 + r20
            r0 = r22
            r0.c = r10     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r0 = r18
            r2 = r22
            r2.d = r0     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            goto L_0x0119
        L_0x0163:
            r4 = move-exception
        L_0x0164:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = ""
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x06c7 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0173:
            r8.flush()     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            if (r16 == 0) goto L_0x01c3
            long r10 = r16.length()     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            int r10 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r10 != 0) goto L_0x01c3
            r0 = r16
            boolean r10 = com.tencent.android.tpush.cloudctr.b.b.a(r14, r0)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            if (r10 == 0) goto L_0x01c3
            boolean r10 = r15.exists()     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            if (r10 == 0) goto L_0x0191
            r15.delete()     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
        L_0x0191:
            r0 = r16
            boolean r10 = r0.renameTo(r15)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            if (r10 != 0) goto L_0x01a8
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "rename file error"
            com.tencent.android.tpush.b.a.g(r4, r10)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x01a8:
            r0 = r22
            android.os.Bundle r10 = r0.e     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r10.remove(r4)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            if (r24 == 0) goto L_0x01bb
            java.lang.String r4 = r15.getAbsolutePath()     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r10 = 0
            r0 = r24
            r0.a(r4, r10)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
        L_0x01bb:
            r10 = 1
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x01c3:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "The download file is not valid, download again"
            com.tencent.android.tpush.b.a.g(r4, r10)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            boolean r4 = r16.delete()     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            if (r4 != 0) goto L_0x01e0
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "delete file fail !!!"
            com.tencent.android.tpush.b.a.i(r4, r10)     // Catch:{ NumberFormatException -> 0x013c, ClientProtocolException -> 0x0163, IllegalStateException -> 0x06f8, FileNotFoundException -> 0x06ec, IOException -> 0x06e0, DownLoadException -> 0x06d4 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x01e0:
            r10 = 2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x01e9:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "NULL response stream."
            com.tencent.android.tpush.b.a.g(r4, r6)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r10 = 0
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x01fc:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "data mode from server is not stream."
            com.tencent.android.tpush.b.a.i(r4, r6)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r10 = -2
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x020f:
            r6 = 404(0x194, float:5.66E-43)
            if (r7 != r6) goto L_0x0237
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r7.<init>()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.lang.String r10 = "The resource does not exist - "
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.lang.StringBuilder r4 = r7.append(r4)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            com.tencent.android.tpush.b.a.c(r6, r4)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r10 = -3
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0237:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r6.<init>()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.lang.String r10 = "network connect status code unexpected - "
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            java.lang.String r6 = r6.toString()     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            com.tencent.android.tpush.b.a.g(r4, r6)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r10 = -2
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x025b:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "NULL response"
            com.tencent.android.tpush.b.a.g(r4, r6)     // Catch:{ NumberFormatException -> 0x070a, ClientProtocolException -> 0x06fb, IllegalStateException -> 0x026e, FileNotFoundException -> 0x0282, IOException -> 0x0296, DownLoadException -> 0x02aa, all -> 0x02be }
            r10 = 0
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x026e:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x0272:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = ""
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x06c7 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0282:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x0286:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = ""
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x06c7 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0296:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x029a:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = ""
            com.tencent.android.tpush.b.a.b(r10, r11, r4)     // Catch:{ all -> 0x06c7 }
            r10 = -1
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x02aa:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x02ae:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "Download exception"
            com.tencent.android.tpush.b.a.c(r10, r11, r4)     // Catch:{ all -> 0x06c7 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x02be:
            r4 = move-exception
            r10 = r4
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x02c3:
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            throw r10
        L_0x02c9:
            long r6 = r16.length()
            r8 = 0
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 <= 0) goto L_0x0384
            r6 = -1
            r0 = r22
            org.apache.http.client.methods.HttpGet r4 = r0.a(r4, r6)
            org.apache.http.impl.client.DefaultHttpClient r5 = r22.a()
            org.apache.http.HttpResponse r4 = r5.execute(r4)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            r0 = r22
            long r4 = r0.a(r4)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            long r6 = r16.length()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x032a
            r0 = r16
            boolean r6 = com.tencent.android.tpush.cloudctr.b.b.a(r14, r0)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            if (r6 == 0) goto L_0x032a
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r5 = "Existed file size is same with target. Use it directly."
            com.tencent.android.tpush.b.a.c(r4, r5)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            boolean r4 = r15.exists()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            if (r4 == 0) goto L_0x0309
            r15.delete()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
        L_0x0309:
            r0 = r16
            boolean r4 = r0.renameTo(r15)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            if (r4 != 0) goto L_0x031b
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r5 = "rename file error"
            com.tencent.android.tpush.b.a.g(r4, r5)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            r4 = -2
            goto L_0x014b
        L_0x031b:
            if (r24 == 0) goto L_0x0327
            java.lang.String r4 = r15.getAbsolutePath()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            r5 = 1
            r0 = r24
            r0.a(r4, r5)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
        L_0x0327:
            r4 = 1
            goto L_0x014b
        L_0x032a:
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            r7.<init>()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            java.lang.String r8 = "Exsit file length:"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            long r8 = r16.length()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            java.lang.String r8 = ", fileTotalLength:"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            java.lang.StringBuilder r4 = r7.append(r4)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            java.lang.String r4 = r4.toString()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            com.tencent.android.tpush.b.a.e(r6, r4)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            boolean r4 = r16.delete()     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            if (r4 != 0) goto L_0x0360
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r5 = "delete file fail !!!"
            com.tencent.android.tpush.b.a.i(r4, r5)     // Catch:{ ClientProtocolException -> 0x0363, IOException -> 0x036e, DownLoadException -> 0x0379 }
            r4 = -2
            goto L_0x014b
        L_0x0360:
            r4 = 2
            goto L_0x014b
        L_0x0363:
            r4 = move-exception
            java.lang.String r5 = "CloudCtrDownload"
            java.lang.String r6 = "ClientProtocolException"
            com.tencent.android.tpush.b.a.d(r5, r6, r4)
            r4 = -2
            goto L_0x014b
        L_0x036e:
            r4 = move-exception
            java.lang.String r5 = "CloudCtrDownload"
            java.lang.String r6 = "IOException"
            com.tencent.android.tpush.b.a.b(r5, r6, r4)
            r4 = -1
            goto L_0x014b
        L_0x0379:
            r4 = move-exception
            java.lang.String r5 = "CloudCtrDownload"
            java.lang.String r6 = "Downloadexception"
            com.tencent.android.tpush.b.a.c(r5, r6, r4)
            r4 = -2
            goto L_0x014b
        L_0x0384:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r5 = "unexpected !!"
            com.tencent.android.tpush.b.a.i(r4, r5)
            r4 = -2
            goto L_0x014b
        L_0x038e:
            java.lang.String r7 = "CloudCtrDownload"
            java.lang.String r18 = "Had record, keep download."
            r0 = r18
            com.tencent.android.tpush.b.a.e(r7, r0)
            java.io.File r18 = new java.io.File
            r0 = r18
            r0.<init>(r6, r15)
            boolean r6 = r18.exists()
            if (r6 == 0) goto L_0x046b
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.String r7 = "File exsit, getting the file length."
            com.tencent.android.tpush.b.a.a(r6, r7)
            long r6 = r18.length()
            r10 = r6
        L_0x03b0:
            java.lang.String r15 = "CloudCtrDownload"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r19.<init>()
            java.lang.String r20 = "startPostion: "
            java.lang.StringBuilder r19 = r19.append(r20)
            r0 = r19
            java.lang.StringBuilder r19 = r0.append(r6)
            java.lang.String r19 = r19.toString()
            r0 = r19
            com.tencent.android.tpush.b.a.e(r15, r0)
            r0 = r25
            int r15 = r0._downloadRetryTimes
            r19 = -1
            r0 = r19
            if (r15 != r0) goto L_0x03eb
            java.lang.String r15 = "CloudCtrDownload"
            java.lang.String r19 = "Reset download retry times because it ever failed."
            r0 = r19
            com.tencent.android.tpush.b.a.c(r15, r0)
            r0 = r22
            r1 = r16
            int r15 = r0.a(r1)
            r0 = r25
            r0._downloadRetryTimes = r15
        L_0x03eb:
            r0 = r22
            org.apache.http.client.methods.HttpGet r6 = r0.a(r4, r6)
            org.apache.http.impl.client.DefaultHttpClient r7 = r22.a()
            org.apache.http.HttpResponse r6 = r7.execute(r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            if (r6 == 0) goto L_0x05cb
            org.apache.http.StatusLine r7 = r6.getStatusLine()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            int r7 = r7.getStatusCode()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r15 = 200(0xc8, float:2.8E-43)
            if (r7 == r15) goto L_0x040b
            r15 = 206(0xce, float:2.89E-43)
            if (r7 != r15) goto L_0x0554
        L_0x040b:
            org.apache.http.HttpEntity r9 = r6.getEntity()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            boolean r7 = r9.isStreaming()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            if (r7 == 0) goto L_0x0541
            r0 = r22
            long r6 = r0.a(r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            long r6 = r6 + r10
            int r6 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r6 != 0) goto L_0x051a
            java.io.InputStream r5 = r9.getContent()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            if (r5 == 0) goto L_0x0507
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r6.<init>(r5)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ NumberFormatException -> 0x06b3, ClientProtocolException -> 0x06a4, IllegalStateException -> 0x0692, FileNotFoundException -> 0x0686, IOException -> 0x067d, DownLoadException -> 0x0674, all -> 0x0668 }
            r13 = 1
            r0 = r18
            r7.<init>(r0, r13)     // Catch:{ NumberFormatException -> 0x06b3, ClientProtocolException -> 0x06a4, IllegalStateException -> 0x0692, FileNotFoundException -> 0x0686, IOException -> 0x067d, DownLoadException -> 0x0674, all -> 0x0668 }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ NumberFormatException -> 0x06b8, ClientProtocolException -> 0x06a9, IllegalStateException -> 0x0697, FileNotFoundException -> 0x068b, IOException -> 0x0681, DownLoadException -> 0x0678, all -> 0x066d }
            r8.<init>(r7)     // Catch:{ NumberFormatException -> 0x06b8, ClientProtocolException -> 0x06a9, IllegalStateException -> 0x0697, FileNotFoundException -> 0x068b, IOException -> 0x0681, DownLoadException -> 0x0678, all -> 0x066d }
            r12 = 1024(0x400, float:1.435E-42)
            byte[] r12 = new byte[r12]     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
        L_0x043c:
            int r13 = r6.read(r12)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            r15 = -1
            if (r13 == r15) goto L_0x04a9
            r0 = r22
            boolean r15 = r0.a     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            if (r15 == 0) goto L_0x0484
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "stop download by user, throw JPushException."
            com.tencent.android.tpush.b.a.g(r4, r10)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl$DownLoadException r4 = new com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl$DownLoadException     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            java.lang.String r10 = "stop download by user."
            r0 = r22
            r4.<init>(r10)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            throw r4     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
        L_0x045a:
            r4 = move-exception
        L_0x045b:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "NumberFormatException, get content length from http fail."
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x0671 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x046b:
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.String r7 = "File had been delete, start from 0."
            com.tencent.android.tpush.b.a.a(r6, r7)
            r6 = 0
            r18.createNewFile()     // Catch:{ IOException -> 0x0479 }
            goto L_0x03b0
        L_0x0479:
            r4 = move-exception
            java.lang.String r5 = "CloudCtrDownload"
            java.lang.String r6 = "createNewFile fail."
            com.tencent.android.tpush.b.a.d(r5, r6, r4)
            r4 = -2
            goto L_0x014b
        L_0x0484:
            r15 = 0
            r8.write(r12, r15, r13)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            long r0 = (long) r13     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            r20 = r0
            long r10 = r10 + r20
            r0 = r22
            r0.c = r10     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            r0 = r16
            r2 = r22
            r2.d = r0     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            goto L_0x043c
        L_0x0498:
            r4 = move-exception
        L_0x0499:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "ClientProtocolException"
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x0671 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x04a9:
            r8.flush()     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "Download finished"
            com.tencent.android.tpush.b.a.c(r10, r11)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            if (r18 == 0) goto L_0x04e1
            long r10 = r18.length()     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            int r10 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r10 != 0) goto L_0x04e1
            r0 = r18
            boolean r10 = com.tencent.android.tpush.cloudctr.b.b.a(r14, r0)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            if (r10 == 0) goto L_0x04e1
            r0 = r22
            android.os.Bundle r10 = r0.e     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            r10.remove(r4)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            if (r24 == 0) goto L_0x04d8
            java.lang.String r4 = r18.getAbsolutePath()     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            r10 = 0
            r0 = r24
            r0.a(r4, r10)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
        L_0x04d8:
            r10 = 1
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x04e1:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "The download file is not valid, download again"
            com.tencent.android.tpush.b.a.g(r4, r10)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            if (r4 != 0) goto L_0x04fe
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r10 = "delete file fail !!!"
            com.tencent.android.tpush.b.a.i(r4, r10)     // Catch:{ NumberFormatException -> 0x045a, ClientProtocolException -> 0x0498, IllegalStateException -> 0x069b, FileNotFoundException -> 0x068f, IOException -> 0x0684, DownLoadException -> 0x067b }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x04fe:
            r10 = 2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0507:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "NULL response stream"
            com.tencent.android.tpush.b.a.g(r4, r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r10 = 0
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x051a:
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.String r7 = "File length between last and now were different."
            com.tencent.android.tpush.b.a.i(r6, r7)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r0 = r22
            android.os.Bundle r6 = r0.e     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r6.remove(r4)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            if (r4 != 0) goto L_0x0535
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "delete file fail !!!"
            com.tencent.android.tpush.b.a.i(r4, r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
        L_0x0535:
            r10 = 0
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0541:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "data mode from server is not stream."
            com.tencent.android.tpush.b.a.i(r4, r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r10 = -2
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0554:
            r6 = 416(0x1a0, float:5.83E-43)
            if (r7 != r6) goto L_0x057f
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.String r7 = "server file length change at the same url, delete all info and download again at 0."
            com.tencent.android.tpush.b.a.i(r6, r7)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r0 = r22
            android.os.Bundle r6 = r0.e     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r6.remove(r4)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            if (r4 != 0) goto L_0x0573
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "delete file fail !!!"
            com.tencent.android.tpush.b.a.i(r4, r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
        L_0x0573:
            r10 = 0
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x057f:
            r6 = 404(0x194, float:5.66E-43)
            if (r7 != r6) goto L_0x05a7
            java.lang.String r6 = "CloudCtrDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r7.<init>()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.lang.String r10 = "The resource does not exist - "
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.lang.StringBuilder r4 = r7.append(r4)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            com.tencent.android.tpush.b.a.c(r6, r4)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r10 = -3
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x05a7:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r6.<init>()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.lang.String r10 = "network connect status code unexpected - "
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            java.lang.String r6 = r6.toString()     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            com.tencent.android.tpush.b.a.g(r4, r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r10 = -2
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x05cb:
            java.lang.String r4 = "CloudCtrDownload"
            java.lang.String r6 = "NULL response"
            com.tencent.android.tpush.b.a.g(r4, r6)     // Catch:{ NumberFormatException -> 0x06ad, ClientProtocolException -> 0x069e, IllegalStateException -> 0x05de, FileNotFoundException -> 0x05f2, IOException -> 0x0606, DownLoadException -> 0x061a, all -> 0x062e }
            r10 = 0
            r4 = r22
            r6 = r13
            r7 = r8
            r8 = r12
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x05de:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x05e2:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "ClientProtocolException"
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x0671 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x05f2:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x05f6:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "FileNotFoundException"
            com.tencent.android.tpush.b.a.d(r10, r11, r4)     // Catch:{ all -> 0x0671 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x0606:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x060a:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "IOException"
            com.tencent.android.tpush.b.a.b(r10, r11, r4)     // Catch:{ all -> 0x0671 }
            r10 = -1
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x061a:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x061e:
            java.lang.String r10 = "CloudCtrDownload"
            java.lang.String r11 = "Downloadexception"
            com.tencent.android.tpush.b.a.c(r10, r11, r4)     // Catch:{ all -> 0x0671 }
            r10 = -2
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x014b
        L_0x062e:
            r4 = move-exception
            r10 = r4
            r7 = r8
            r6 = r13
            r8 = r12
        L_0x0633:
            r4 = r22
            r4.a(r5, r6, r7, r8, r9)
            throw r10
        L_0x0639:
            java.lang.String r5 = "CloudCtrDownload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Param error !! url:"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r4 = r8.append(r4)
            java.lang.String r8 = " savefilePath:"
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r6 = " fileName:"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.tencent.android.tpush.b.a.i(r5, r4)
            r4 = -2
            goto L_0x014b
        L_0x0668:
            r4 = move-exception
            r10 = r4
            r7 = r8
            r8 = r12
            goto L_0x0633
        L_0x066d:
            r4 = move-exception
            r10 = r4
            r8 = r12
            goto L_0x0633
        L_0x0671:
            r4 = move-exception
            r10 = r4
            goto L_0x0633
        L_0x0674:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x061e
        L_0x0678:
            r4 = move-exception
            r8 = r12
            goto L_0x061e
        L_0x067b:
            r4 = move-exception
            goto L_0x061e
        L_0x067d:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x060a
        L_0x0681:
            r4 = move-exception
            r8 = r12
            goto L_0x060a
        L_0x0684:
            r4 = move-exception
            goto L_0x060a
        L_0x0686:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x05f6
        L_0x068b:
            r4 = move-exception
            r8 = r12
            goto L_0x05f6
        L_0x068f:
            r4 = move-exception
            goto L_0x05f6
        L_0x0692:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x05e2
        L_0x0697:
            r4 = move-exception
            r8 = r12
            goto L_0x05e2
        L_0x069b:
            r4 = move-exception
            goto L_0x05e2
        L_0x069e:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
            goto L_0x0499
        L_0x06a4:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x0499
        L_0x06a9:
            r4 = move-exception
            r8 = r12
            goto L_0x0499
        L_0x06ad:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
            goto L_0x045b
        L_0x06b3:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x045b
        L_0x06b8:
            r4 = move-exception
            r8 = r12
            goto L_0x045b
        L_0x06bc:
            r4 = move-exception
            r10 = r4
            r7 = r8
            r8 = r12
            goto L_0x02c3
        L_0x06c2:
            r4 = move-exception
            r10 = r4
            r8 = r12
            goto L_0x02c3
        L_0x06c7:
            r4 = move-exception
            r10 = r4
            goto L_0x02c3
        L_0x06cb:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x02ae
        L_0x06d0:
            r4 = move-exception
            r8 = r12
            goto L_0x02ae
        L_0x06d4:
            r4 = move-exception
            goto L_0x02ae
        L_0x06d7:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x029a
        L_0x06dc:
            r4 = move-exception
            r8 = r12
            goto L_0x029a
        L_0x06e0:
            r4 = move-exception
            goto L_0x029a
        L_0x06e3:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x0286
        L_0x06e8:
            r4 = move-exception
            r8 = r12
            goto L_0x0286
        L_0x06ec:
            r4 = move-exception
            goto L_0x0286
        L_0x06ef:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x0272
        L_0x06f4:
            r4 = move-exception
            r8 = r12
            goto L_0x0272
        L_0x06f8:
            r4 = move-exception
            goto L_0x0272
        L_0x06fb:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
            goto L_0x0164
        L_0x0701:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x0164
        L_0x0706:
            r4 = move-exception
            r8 = r12
            goto L_0x0164
        L_0x070a:
            r4 = move-exception
            r7 = r8
            r6 = r13
            r8 = r12
            goto L_0x013d
        L_0x0710:
            r4 = move-exception
            r7 = r8
            r8 = r12
            goto L_0x013d
        L_0x0715:
            r4 = move-exception
            r8 = r12
            goto L_0x013d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl.a(android.content.Context, com.tencent.android.tpush.cloudctr.network.CloudControlDownloadControl$a, com.tencent.android.tpush.cloudctr.network.DownloadItem):int");
    }

    private void a(InputStream inputStream, BufferedInputStream bufferedInputStream, FileOutputStream fileOutputStream, BufferedOutputStream bufferedOutputStream, HttpEntity httpEntity) {
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
            } catch (IOException e2) {
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
            }
        }
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (IOException e4) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e5) {
            }
        }
        if (httpEntity != null) {
            try {
                httpEntity.consumeContent();
            } catch (IOException e6) {
            }
        }
    }

    private DefaultHttpClient a() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, Config.SESSION_PERIOD);
        HttpConnectionParams.setSoTimeout(basicHttpParams, Config.SESSION_PERIOD);
        return new DefaultHttpClient(basicHttpParams);
    }

    private HttpGet a(String str, long j) {
        HttpGet httpGet = new HttpGet(str);
        httpGet.addHeader("Connection", "Close");
        if (j >= 0) {
            httpGet.addHeader("Range", "bytes=" + j + "-");
        }
        return httpGet;
    }

    private void a(String str) {
        File file = new File(str);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    private long a(HttpResponse httpResponse) {
        long longValue = Long.valueOf(httpResponse.getFirstHeader("Content-Length").getValue()).longValue();
        if (longValue > 0) {
            return longValue;
        }
        throw new DownLoadException("get the file total length from http is 0.");
    }

    private int a(long j) {
        int i;
        long j2 = j / 10240;
        if (j2 < 1) {
            i = 10;
        } else if (j2 > 5) {
            i = 50;
        } else {
            i = (int) (j2 * 10);
        }
        return (int) (((double) i) * 1.1d);
    }

    public static boolean a(int i) {
        return 2 == i || 3 == i;
    }
}
