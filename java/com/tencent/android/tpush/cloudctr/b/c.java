package com.tencent.android.tpush.cloudctr.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tencent.android.tpush.b.a;
import java.lang.Thread.State;

/* compiled from: ProGuard */
public class c {
    private String a = "common work thread";
    private HandlerThread b;
    private Handler c;

    public c(String str) {
        if (str != null) {
            this.a = str;
        }
        a();
    }

    public void a(Runnable runnable) {
        a();
        if (this.c != null) {
            this.c.post(runnable);
        }
    }

    private void a() {
        try {
            synchronized (c.class) {
                if (this.b == null || !this.b.isAlive() || this.b.isInterrupted() || this.b.getState() == State.TERMINATED) {
                    this.b = new HandlerThread(this.a);
                    this.b.start();
                    Looper looper = this.b.getLooper();
                    if (looper != null) {
                        this.c = new Handler(looper);
                    } else {
                        a.i(this.a, "Create new working thread false, cause thread.getLooper()==null");
                    }
                }
            }
        } catch (Throwable th) {
        }
    }
}
