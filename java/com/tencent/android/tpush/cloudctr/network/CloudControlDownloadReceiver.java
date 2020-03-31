package com.tencent.android.tpush.cloudctr.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.cloudctr.CloudControlManager;

/* compiled from: ProGuard */
public class CloudControlDownloadReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        a.c("CloudControlDownloadReceiver", "onReceive");
        DownloadItem a = CloudControlDownloadService.a(intent);
        if (a != null) {
            CloudControlManager.a().a(StubApp.getOrigApplicationContext(context.getApplicationContext()), a, false);
        }
    }
}
