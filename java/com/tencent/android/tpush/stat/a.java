package com.tencent.android.tpush.stat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.stub.StubApp;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.android.tpush.stat.a.d;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import org.apache.http.HttpHost;

/* compiled from: ProGuard */
public class a {
    private static volatile a d = null;
    private volatile int a = 2;
    private volatile String b = "";
    private volatile HttpHost c = null;
    private Context e = null;
    private d f = null;

    public String a() {
        return this.b;
    }

    private a(Context context) {
        this.e = StubApp.getOrigApplicationContext(context.getApplicationContext());
        d.a(context);
        this.f = c.b();
        f();
        d();
    }

    public boolean b() {
        return this.a == 1;
    }

    public boolean c() {
        return this.a != 0;
    }

    public static a a(Context context) {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a(context);
                }
            }
        }
        return d;
    }

    private void f() {
        this.a = 0;
        this.c = null;
        this.b = null;
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        if (DeviceInfos.isNetworkAvailable(this.e)) {
            this.b = DeviceInfos.getLinkedWay(this.e);
            if (b.b()) {
                this.f.b((Object) "NETWORK name:" + this.b);
            }
            if (c.b(this.b)) {
                if ("WIFI".equalsIgnoreCase(this.b)) {
                    this.a = 1;
                } else {
                    this.a = 2;
                }
                this.c = c.b(this.e);
                return;
            }
            return;
        }
        if (b.b()) {
            this.f.b((Object) "NETWORK TYPE: network is close.");
        }
        f();
    }

    public void e() {
        try {
            StubApp.getOrigApplicationContext(this.e.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    a.this.d();
                }
            }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("registerBroadcast", "", th);
        }
    }
}
