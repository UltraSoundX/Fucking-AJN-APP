package com.baidu.location.f;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.baidu.location.a.h;
import com.baidu.location.a.j;
import com.baidu.location.a.k;
import com.baidu.location.a.m;
import com.baidu.location.a.o;
import com.baidu.location.a.u;
import com.baidu.location.a.v;
import com.baidu.location.a.w;
import com.baidu.location.b.d;
import com.baidu.location.e;
import com.baidu.location.e.i;
import com.baidu.location.f;
import com.baidu.location.g.b;
import com.baidu.location.indoor.g;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.ref.WeakReference;

public class a extends Service implements e {
    static C0030a a = null;
    private static long f = 0;
    Messenger b = null;
    private Looper c;
    private HandlerThread d;
    private boolean e = false;

    /* renamed from: com.baidu.location.f.a$a reason: collision with other inner class name */
    public static class C0030a extends Handler {
        private final WeakReference<a> a;

        public C0030a(Looper looper, a aVar) {
            super(looper);
            this.a = new WeakReference<>(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null) {
                if (f.f) {
                    switch (message.what) {
                        case 11:
                            aVar.a(message);
                            break;
                        case 12:
                            aVar.b(message);
                            break;
                        case 15:
                            aVar.c(message);
                            break;
                        case 22:
                            k.c().b(message);
                            break;
                        case 28:
                            k.c().a(true, true);
                            break;
                        case 41:
                            k.c().i();
                            break;
                        case 110:
                            g.a().c();
                            break;
                        case 111:
                            g.a().d();
                            break;
                        case 112:
                            g.a().b();
                            break;
                        case 113:
                            Object obj = message.obj;
                            if (obj != null) {
                                g.a().a((Bundle) obj);
                                break;
                            }
                            break;
                        case 114:
                            Object obj2 = message.obj;
                            if (obj2 != null) {
                                g.a().b((Bundle) obj2);
                                break;
                            }
                            break;
                        case ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_INNER /*401*/:
                            try {
                                message.getData();
                                break;
                            } catch (Exception e) {
                                break;
                            }
                        case ErrorCode.INFO_CAN_NOT_LOAD_TBS /*405*/:
                            byte[] byteArray = message.getData().getByteArray("errorid");
                            if (byteArray != null && byteArray.length > 0) {
                                new String(byteArray);
                                break;
                            }
                        case ErrorCode.INFO_MISS_SDKEXTENSION_JAR_OLD /*406*/:
                            h.a().e();
                            break;
                        case 705:
                            com.baidu.location.a.a.a().a(message.getData().getBoolean("foreground"));
                            break;
                    }
                }
                if (message.what == 1) {
                    aVar.d();
                }
                if (message.what == 0) {
                    aVar.c();
                }
                super.handleMessage(message);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        Log.d("baidu_location_service", "baidu location service register ...");
        com.baidu.location.a.a.a().a(message);
        com.baidu.location.d.g.a();
        com.baidu.location.b.e.a().d();
        o.b().c();
    }

    public static long b() {
        return f;
    }

    /* access modifiers changed from: private */
    public void b(Message message) {
        com.baidu.location.a.a.a().b(message);
    }

    /* access modifiers changed from: private */
    public void c() {
        j.a().a(f.c());
        com.baidu.location.b.e.a().b();
        b.a();
        w.a().e();
        m.a().b();
        h.a().b();
        com.baidu.location.e.e.a().b();
        com.baidu.location.e.b.a().b();
        k.c().d();
        com.baidu.location.d.a.a().c();
        d.a().b();
        com.baidu.location.b.f.a().b();
        com.baidu.location.b.a.a().b();
        i.a().c();
    }

    /* access modifiers changed from: private */
    public void c(Message message) {
        com.baidu.location.a.a.a().c(message);
    }

    /* access modifiers changed from: private */
    public void d() {
        i.a().e();
        com.baidu.location.d.g.a().n();
        com.baidu.location.e.e.a().e();
        w.a().f();
        com.baidu.location.b.e.a().c();
        d.a().c();
        com.baidu.location.b.b.a().c();
        com.baidu.location.b.a.a().c();
        com.baidu.location.a.b.a().b();
        com.baidu.location.e.b.a().c();
        k.c().e();
        g.a().d();
        h.a().c();
        v.d();
        com.baidu.location.a.a.a().b();
        m.a().c();
        Log.d("baidu_location_service", "baidu location service has stoped ...");
        if (!this.e) {
            Process.killProcess(Process.myPid());
        }
    }

    public double a() {
        return 7.619999885559082d;
    }

    public void a(Context context) {
        f = System.currentTimeMillis();
        this.d = u.a();
        this.c = this.d.getLooper();
        if (this.c == null) {
            a = new C0030a(Looper.getMainLooper(), this);
        } else {
            a = new C0030a(this.c, this);
        }
        this.b = new Messenger(a);
        a.sendEmptyMessage(0);
        Log.d("baidu_location_service", "baidu location service start1 ...20181201..." + Process.myPid());
    }

    public boolean a(Intent intent) {
        return false;
    }

    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        boolean z = false;
        if (extras != null) {
            b.g = extras.getString(SettingsContentProvider.KEY);
            b.f = extras.getString("sign");
            this.e = extras.getBoolean("kill_process");
            z = extras.getBoolean("cache_exception");
        }
        if (!z) {
            Thread.setDefaultUncaughtExceptionHandler(com.baidu.location.b.f.a());
        }
        return this.b.getBinder();
    }

    public void onDestroy() {
        try {
            a.sendEmptyMessage(1);
        } catch (Exception e2) {
            Log.d("baidu_location_service", "baidu location service stop exception...");
            d();
            Process.killProcess(Process.myPid());
        }
        Log.d("baidu_location_service", "baidu location service stop ...");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public void onTaskRemoved(Intent intent) {
        Log.d("baidu_location_service", "baidu location service remove task...");
    }
}
