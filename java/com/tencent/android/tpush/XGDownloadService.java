package com.tencent.android.tpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.io.File;

/* compiled from: ProGuard */
public class XGDownloadService extends Service {
    /* access modifiers changed from: private */
    public static final String c = XGDownloadService.class.getSimpleName();
    private int a = 0;
    /* access modifiers changed from: private */
    public String b = "";
    /* access modifiers changed from: private */
    public File d = null;
    /* access modifiers changed from: private */
    public File e = null;
    /* access modifiers changed from: private */
    public NotificationManager f = null;
    /* access modifiers changed from: private */
    public Notification g = null;
    private Intent h = null;
    /* access modifiers changed from: private */
    public PendingIntent i = null;
    /* access modifiers changed from: private */
    public Handler j = new Handler() {
        public void handleMessage(Message message) {
            int i = message.arg1;
            message.getData();
            switch (message.what) {
                case 0:
                    Uri fromFile = Uri.fromFile(XGDownloadService.this.e);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
                    XGDownloadService.this.i = PendingIntent.getActivity(XGDownloadService.this, i, intent, 0);
                    XGDownloadService.this.g.flags = 16;
                    XGDownloadService.this.g.defaults = 1;
                    XGDownloadService.this.f.notify(i, XGDownloadService.this.g);
                    XGDownloadService.this.stopSelf();
                    return;
                case 1:
                    XGDownloadService.this.g.flags = 16;
                    XGDownloadService.this.f.notify(i, XGDownloadService.this.g);
                    return;
                default:
                    XGDownloadService.this.stopSelf();
                    return;
            }
        }
    };

    /* compiled from: ProGuard */
    class a implements Runnable {
        Message a = XGDownloadService.this.j.obtainMessage();
        private Intent c;
        private int d;

        public a(Intent intent, int i) {
            this.c = intent;
            this.d = i;
        }

        public void run() {
            this.a.what = 0;
            this.a.arg1 = this.d;
            new Bundle();
            this.a.setData(this.c.getExtras());
            try {
                if (!XGDownloadService.this.d.exists()) {
                    XGDownloadService.this.d.mkdirs();
                }
                if (!XGDownloadService.this.e.exists()) {
                    XGDownloadService.this.e.createNewFile();
                }
                if (XGDownloadService.this.a(XGDownloadService.this.b, XGDownloadService.this.e, this.d) > 0) {
                    XGDownloadService.this.j.sendMessage(this.a);
                }
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d(XGDownloadService.c, "downloadRunnable", e);
                XGDownloadService.this.j.sendMessage(this.a);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(java.lang.String r18, java.io.File r19, int r20) {
        /*
            r17 = this;
            r7 = 0
            r8 = 0
            r3 = 0
            r5 = 0
            r4 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00a2 }
            r0 = r18
            r2.<init>(r0)     // Catch:{ all -> 0x00a2 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ all -> 0x00a2 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = "User-Agent"
            java.lang.String r6 = "PacificHttpClient"
            r2.setRequestProperty(r3, r6)     // Catch:{ all -> 0x0038 }
            r3 = 10000(0x2710, float:1.4013E-41)
            r2.setConnectTimeout(r3)     // Catch:{ all -> 0x0038 }
            r3 = 20000(0x4e20, float:2.8026E-41)
            r2.setReadTimeout(r3)     // Catch:{ all -> 0x0038 }
            int r10 = r2.getContentLength()     // Catch:{ all -> 0x0038 }
            int r3 = r2.getResponseCode()     // Catch:{ all -> 0x0038 }
            r6 = 404(0x194, float:5.66E-43)
            if (r3 != r6) goto L_0x0050
            java.lang.Exception r3 = new java.lang.Exception     // Catch:{ all -> 0x0038 }
            java.lang.String r6 = "fail!"
            r3.<init>(r6)     // Catch:{ all -> 0x0038 }
            throw r3     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r3 = move-exception
            r16 = r3
            r3 = r4
            r4 = r5
            r5 = r2
            r2 = r16
        L_0x0040:
            if (r5 == 0) goto L_0x0045
            r5.disconnect()
        L_0x0045:
            if (r4 == 0) goto L_0x004a
            r4.close()
        L_0x004a:
            if (r3 == 0) goto L_0x004f
            r3.close()
        L_0x004f:
            throw r2
        L_0x0050:
            java.io.InputStream r6 = r2.getInputStream()     // Catch:{ all -> 0x0038 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x00aa }
            r3 = 0
            r0 = r19
            r5.<init>(r0, r3)     // Catch:{ all -> 0x00aa }
            r3 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r3]     // Catch:{ all -> 0x0089 }
            r3 = r7
        L_0x0061:
            int r7 = r6.read(r4)     // Catch:{ all -> 0x0089 }
            if (r7 <= 0) goto L_0x0092
            r11 = 0
            r5.write(r4, r11, r7)     // Catch:{ all -> 0x0089 }
            long r12 = (long) r7     // Catch:{ all -> 0x0089 }
            long r8 = r8 + r12
            if (r3 == 0) goto L_0x0079
            r12 = 100
            long r12 = r12 * r8
            long r14 = (long) r10     // Catch:{ all -> 0x0089 }
            long r12 = r12 / r14
            int r7 = (int) r12     // Catch:{ all -> 0x0089 }
            int r7 = r7 + -10
            if (r7 <= r3) goto L_0x0061
        L_0x0079:
            int r3 = r3 + 10
            r0 = r17
            android.app.NotificationManager r7 = r0.f     // Catch:{ all -> 0x0089 }
            r0 = r17
            android.app.Notification r11 = r0.g     // Catch:{ all -> 0x0089 }
            r0 = r20
            r7.notify(r0, r11)     // Catch:{ all -> 0x0089 }
            goto L_0x0061
        L_0x0089:
            r3 = move-exception
            r4 = r6
            r16 = r5
            r5 = r2
            r2 = r3
            r3 = r16
            goto L_0x0040
        L_0x0092:
            if (r2 == 0) goto L_0x0097
            r2.disconnect()
        L_0x0097:
            if (r6 == 0) goto L_0x009c
            r6.close()
        L_0x009c:
            if (r5 == 0) goto L_0x00a1
            r5.close()
        L_0x00a1:
            return r8
        L_0x00a2:
            r2 = move-exception
            r16 = r4
            r4 = r5
            r5 = r3
            r3 = r16
            goto L_0x0040
        L_0x00aa:
            r3 = move-exception
            r5 = r2
            r2 = r3
            r3 = r4
            r4 = r6
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.XGDownloadService.a(java.lang.String, java.io.File, int):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r7, int r8, int r9) {
        /*
            r6 = this;
            r1 = 0
            java.lang.String r0 = "packageDownloadUrl"
            java.lang.String r0 = r7.getStringExtra(r0)
            r6.b = r0
            java.lang.String r2 = "NOTIFY_ID"
            r0 = 0
            int r0 = com.tencent.android.tpush.common.h.a(r6, r2, r0)     // Catch:{ Throwable -> 0x008e }
            r3 = 2147483646(0x7ffffffe, float:NaN)
            if (r0 < r3) goto L_0x0016
            r0 = r1
        L_0x0016:
            int r1 = r0 + 1
            com.tencent.android.tpush.common.h.b(r6, r2, r1)     // Catch:{ Throwable -> 0x009b }
            r1 = r0
        L_0x001c:
            boolean r0 = com.tencent.bigdata.dataacquisition.DeviceInfos.isSDCardMounted()
            if (r0 == 0) goto L_0x0055
            java.io.File r0 = new java.io.File
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r3 = "app/download/"
            r0.<init>(r2, r3)
            r6.d = r0
            java.io.File r0 = new java.io.File
            java.io.File r2 = r6.d
            java.lang.String r2 = r2.getPath()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "downloadApp"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r4 = ".apk"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.<init>(r2, r3)
            r6.e = r0
        L_0x0055:
            java.lang.String r0 = "notification"
            java.lang.Object r0 = r6.getSystemService(r0)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            r6.f = r0
            android.app.Notification r0 = new android.app.Notification
            r0.<init>()
            r6.g = r0
            android.app.Notification r0 = r6.g
            android.content.pm.ApplicationInfo r2 = r6.getApplicationInfo()
            int r2 = r2.icon
            r0.icon = r2
            android.app.Notification r0 = r6.g
            java.lang.String r2 = "开始下载"
            r0.tickerText = r2
            android.app.NotificationManager r0 = r6.f
            android.app.Notification r2 = r6.g
            r0.notify(r1, r2)
            com.tencent.android.tpush.common.c r0 = com.tencent.android.tpush.common.c.a()
            com.tencent.android.tpush.XGDownloadService$a r2 = new com.tencent.android.tpush.XGDownloadService$a
            r2.<init>(r7, r1)
            r0.a(r2)
            int r0 = super.onStartCommand(r7, r8, r9)
            return r0
        L_0x008e:
            r0 = move-exception
            r5 = r0
            r0 = r1
            r1 = r5
        L_0x0092:
            java.lang.String r2 = c
            java.lang.String r3 = ""
            com.tencent.android.tpush.b.a.d(r2, r3, r1)
            r1 = r0
            goto L_0x001c
        L_0x009b:
            r1 = move-exception
            goto L_0x0092
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.XGDownloadService.onStartCommand(android.content.Intent, int, int):int");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
