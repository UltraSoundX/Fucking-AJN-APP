package com.tencent.android.tpush.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;

/* compiled from: ProGuard */
public class f {
    private static final String a = f.class.getSimpleName();
    private static volatile f c = null;
    private Context b = null;
    private boolean d = true;
    private Handler e = null;
    private volatile boolean f = false;
    private long g = 0;

    private f(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.d = c();
        HandlerThread handlerThread = new HandlerThread(f.class.getName());
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
    }

    public static f a(Context context) {
        if (c == null) {
            synchronized (f.class) {
                if (c == null) {
                    c = new f(context);
                }
            }
        }
        return c;
    }

    private String b() {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.b.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private boolean c() {
        String b2 = b();
        if (TextUtils.isEmpty(b2) || !b2.contains("xg_service")) {
            a.e(a, "not xg_service");
            return false;
        }
        a.e(a, "is xg_service");
        return true;
    }

    public void a() {
    }
}
