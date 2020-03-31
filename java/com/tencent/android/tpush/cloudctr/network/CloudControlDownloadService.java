package com.tencent.android.tpush.cloudctr.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: ProGuard */
public class CloudControlDownloadService extends IntentService {
    static ConcurrentLinkedQueue<DownloadItem> a = new ConcurrentLinkedQueue<>();
    private static Bundle b;
    /* access modifiers changed from: private */
    public DownloadItem c;

    static {
        StubApp.interface11(4939);
    }

    /* access modifiers changed from: private */
    public static Intent b(DownloadItem downloadItem) {
        Intent intent = new Intent("com.tencent.android.tpush.cloudcontrol.action.DOWNLOAD_FILE_FINISH");
        intent.putExtra("EXT_DOWNLOAD_ITEM", downloadItem);
        return intent;
    }

    public static DownloadItem a(Intent intent) {
        Serializable serializableExtra = intent.getSerializableExtra("EXT_DOWNLOAD_ITEM");
        if (serializableExtra instanceof DownloadItem) {
            return (DownloadItem) serializableExtra;
        }
        return null;
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        intent.setClass(origApplicationContext, CloudControlDownloadService.class);
        DownloadItem downloadItem = new DownloadItem();
        downloadItem.setMd5(str5);
        downloadItem.setDownloadUrl(str);
        downloadItem.setDownloadSavedDir(str3);
        downloadItem.setFileName(str2);
        downloadItem.setBusinessCode(str4);
        intent.putExtra("EXT_DOWNLOAD_ITEM", downloadItem);
        origApplicationContext.startService(intent);
    }

    public static void a(Context context, DownloadItem downloadItem) {
        Intent intent = new Intent();
        Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        intent.setClass(origApplicationContext, CloudControlDownloadService.class);
        intent.putExtra("EXT_DOWNLOAD_ITEM", downloadItem);
        origApplicationContext.startService(intent);
    }

    public CloudControlDownloadService() {
        super("CloudControl DownloadService");
    }

    public void onCreate() {
        a.c("DownloadService", "onCreate()");
        super.onCreate();
        if (b == null) {
            b = new Bundle();
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        try {
            a.c("DownloadService", "action:onHandleIntent");
            Serializable serializableExtra = intent.getSerializableExtra("EXT_DOWNLOAD_ITEM");
            if (serializableExtra instanceof DownloadItem) {
                this.c = (DownloadItem) serializableExtra;
                if (this.c == null) {
                    a.g("DownloadService", "NULL msg item");
                } else if (!Environment.getExternalStorageState().equals("mounted")) {
                    a.g("DownloadService", "SDCard can not work");
                } else if (this.c._isDownloadFinished) {
                    a.c("DownloadService", "The Msg download is already finished.");
                } else {
                    if (!a.contains(this.c)) {
                        a.offer(this.c);
                    }
                    Thread.currentThread().setPriority(1);
                    new CloudControlDownloadControl(this, this.c, b, new CloudControlDownloadControl.a() {
                        public void a(String str, boolean z) {
                            a.c("DownloadService", "download file Succeed");
                            CloudControlDownloadService.this.c._isDownloadFinished = true;
                            CloudControlDownloadService.a.remove(CloudControlDownloadService.this.c);
                            CloudControlDownloadService.this.c._isDownloadInterrupted = true;
                            CloudControlDownloadService.this.c.setDownloadSucceed(true);
                            CloudControlDownloadService.this.sendBroadcast(CloudControlDownloadService.b(CloudControlDownloadService.this.c));
                        }

                        public void a(int i) {
                            if (CloudControlDownloadControl.a(i)) {
                                CloudControlDownloadService.this.c._isEverDownloadFailed = true;
                            }
                            CloudControlDownloadService.this.c._isDownloadInterrupted = true;
                            CloudControlDownloadService.this.c.setDownloadSucceed(false);
                            CloudControlDownloadService.this.sendBroadcast(CloudControlDownloadService.b(CloudControlDownloadService.this.c));
                        }
                    }, 3000);
                }
            }
        } catch (Exception e) {
            a.i("DownloadService", "download file error");
        }
    }
}
