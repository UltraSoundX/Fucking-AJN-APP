package com.jg.ids;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public abstract class a extends f {
    private static Handler b = new Handler();
    private Handler c = null;
    private HandlerThread d = null;

    /* access modifiers changed from: protected */
    public abstract void a(Message message);

    /* access modifiers changed from: protected */
    public abstract void b();

    public a(Context context, String str) {
        super(context);
        try {
            this.d = new HandlerThread(str);
            this.d.start();
            this.c = new b(this, this.d.getLooper());
            if (c() && d(context)) {
                b();
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public final void b(Message message) {
        this.c.sendMessage(message);
    }

    /* access modifiers changed from: protected */
    public final Message a() {
        return this.c.obtainMessage();
    }

    protected static void a(Runnable runnable) {
        b.post(runnable);
    }

    /* access modifiers changed from: protected */
    public void a(int i, String str) {
        switch (i) {
            case 0:
                a((Runnable) new c(this, str));
                return;
            case 1:
                a((Runnable) new d(this, str));
                return;
            case 2:
                a((Runnable) new e(this, str));
                return;
            default:
                return;
        }
    }
}
