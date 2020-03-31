package com.tencent.android.tpush.rpc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.stub.StubApp;
import com.tencent.android.tpush.rpc.a.C0062a;
import com.tencent.android.tpush.service.b;

/* compiled from: ProGuard */
public class XGRemoteService extends Service {
    private C0062a a = new d();

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        b.a(getApplication(), "pullup", 1000);
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        b.d(StubApp.getOrigApplicationContext(getApplicationContext()));
        return this.a;
    }
}
