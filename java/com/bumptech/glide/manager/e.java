package com.bumptech.glide.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bumptech.glide.manager.c.a;
import com.stub.StubApp;

/* compiled from: DefaultConnectivityMonitor */
class e implements c {
    private final Context a;
    /* access modifiers changed from: private */
    public final a b;
    /* access modifiers changed from: private */
    public boolean c;
    private boolean d;
    private final BroadcastReceiver e = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean a2 = e.this.c;
            e.this.c = e.this.a(context);
            if (a2 != e.this.c) {
                e.this.b.a(e.this.c);
            }
        }
    };

    public e(Context context, a aVar) {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.b = aVar;
    }

    private void a() {
        if (!this.d) {
            this.c = a(this.a);
            this.a.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.d = true;
        }
    }

    private void b() {
        if (this.d) {
            this.a.unregisterReceiver(this.e);
            this.d = false;
        }
    }

    /* access modifiers changed from: private */
    public boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void d() {
        a();
    }

    public void e() {
        b();
    }

    public void f() {
    }
}
