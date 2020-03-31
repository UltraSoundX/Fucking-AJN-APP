package com.tencent.android.tpush.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.e.i;
import com.tencent.android.tpush.stat.XGPatchMonitor;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGPushServiceV4 extends Service {
    public static long a = 0;
    public static int b = 0;
    public static JSONArray c = null;
    private static Boolean d = null;
    private static Service e = null;

    static {
        StubApp.interface11(ReaderCallback.READER_SEARCH_IN_DOCUMENT);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void c() {
        c.a().a((Runnable) new Runnable() {
            public void run() {
                boolean z = false;
                if (h.a(StubApp.getOrigApplicationContext(XGPushServiceV4.this.getApplicationContext()), "com.tencent.android.tpush.debug," + StubApp.getOrigApplicationContext(XGPushServiceV4.this.getApplicationContext()).getPackageName(), 0) == 1) {
                    z = true;
                }
                XGPushConfig.enableDebug = true;
                if (z) {
                    a.a(2);
                } else {
                    a.a(3);
                }
            }
        });
    }

    public void onCreate() {
        super.onCreate();
        a = System.currentTimeMillis();
        e = this;
        Context origApplicationContext = StubApp.getOrigApplicationContext(getApplicationContext());
        a.a().b(origApplicationContext);
        if (VERSION.SDK_INT < 18) {
            startForeground(-1998, new Notification());
        }
        com.tencent.android.tpush.service.d.a.a(origApplicationContext);
        b.d(origApplicationContext);
        c();
        if (XGPushConfig.enableDebug) {
            a.f("XGPushService", "Service onCreate() : " + getPackageName());
        }
        b.a().b();
        a();
        try {
            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), XGPatchMonitor.TypeTryDyLoad, XGPatchMonitor.ActionReadyPatch, 0, "XGPushServiceV4 load Start", null);
            Class cls = Class.forName("com.tencent.a.a.a");
            Object invoke = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{origApplicationContext});
            cls.getMethod("doSomething", new Class[]{String.class}).invoke(invoke, new Object[]{"XGPushServiceV4 register"});
            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), XGPatchMonitor.TypeTryDyLoad, XGPatchMonitor.ActionParsePatch, 0, "XGPushServiceV4 loaded", null);
        } catch (Throwable th) {
            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), XGPatchMonitor.TypeTryDyLoad, XGPatchMonitor.ActionParsePatch, 1803, "XGPushServiceV4 load failed", null);
        }
    }

    public void a() {
        try {
            String a2 = com.tencent.android.tpush.service.e.h.a(b.f(), "service_state", "");
            a.c("XGPushService", "reportLastServiceState " + a2);
            if (!i.b(a2)) {
                com.tencent.android.tpush.service.d.a.a(StubApp.getOrigApplicationContext(getApplicationContext()), "SdkService", new JSONObject(a2));
                com.tencent.android.tpush.service.e.h.b(b.f(), "service_state", "");
            }
        } catch (Throwable th) {
            a.d("XGPushService", "reportLastServiceState", th);
        }
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    public static Service b() {
        return e;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, 1, i2);
        b++;
        if (d == null) {
            d = Boolean.valueOf(true);
        } else {
            d = Boolean.valueOf(false);
        }
        if (l.a(StubApp.getOrigApplicationContext(getApplicationContext())) > 0) {
            i.u(StubApp.getOrigApplicationContext(getApplicationContext()));
            return 2;
        }
        if (intent != null) {
            if (c == null) {
                c = new JSONArray();
            }
            String action = intent.getAction();
            if (!i.b(action) && c != null && c.length() < 10) {
                try {
                    action = action.replace(Constants.ACTION_PREFFIX, "");
                } catch (Throwable th) {
                }
                c.put(action);
            }
        }
        c();
        b.a().a(intent);
        return 1;
    }

    public void onDestroy() {
        b.a().c();
        super.onDestroy();
    }
}
