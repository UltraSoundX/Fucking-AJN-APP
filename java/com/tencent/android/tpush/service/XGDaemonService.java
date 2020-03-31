package com.tencent.android.tpush.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.tencent.android.tpush.b.a;

/* compiled from: ProGuard */
public class XGDaemonService extends Service {
    public void onCreate() {
        super.onCreate();
        a.e("XGDaemonService", "XGDaemonService onCreate");
        b.a((Service) this);
        a.e("XGDaemonService", "stopSelf");
        stopSelf();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
