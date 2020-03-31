package com.tencent.android.tpush.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.stub.StubApp;

/* compiled from: ProGuard */
public class a {
    private static volatile b a = null;

    /* renamed from: com.tencent.android.tpush.common.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private static class C0059a implements Runnable {
        private Context a = null;
        private Intent b = null;

        public C0059a(Context context, Intent intent) {
            this.a = context;
            this.b = intent;
        }

        public void run() {
            String action = this.b.getAction();
            if (action != null && !"android.intent.action.PACKAGE_ADDED".equals(action) && !"android.intent.action.PACKAGE_REPLACED".equals(action) && "android.intent.action.PACKAGE_REMOVED".equals(action)) {
            }
        }
    }

    /* compiled from: ProGuard */
    private static class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && context != null) {
                c.a().a((Runnable) new C0059a(context, intent));
            }
        }
    }

    public static void a(Context context) {
        try {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new b();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addDataScheme("package");
                        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                        StubApp.getOrigApplicationContext(context.getApplicationContext()).registerReceiver(a, intentFilter);
                    }
                }
            }
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "AppChangesHandler setupHandler error", e);
        }
    }
}
