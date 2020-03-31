package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class SyncTaskService {
    private HandlerThread a;
    private Handler b;

    static class a extends Handler {
        private final SyncTaskService a;

        a(SyncTaskService syncTaskService, Looper looper) {
            super(looper);
            this.a = syncTaskService;
        }

        public final void dispatchMessage(Message message) {
            this.a.onPreExecute(message.what);
            if (message.getCallback() != null) {
                message.getCallback().run();
            }
            this.a.onPostExecute(message.what);
        }
    }

    /* access modifiers changed from: protected */
    public void execute(int i, Runnable runnable) {
        if (this.a != null) {
            Message obtain = Message.obtain(this.b, runnable);
            obtain.what = i;
            this.b.sendMessage(obtain);
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasTasks(int i) {
        if (this.b == null) {
            return false;
        }
        return this.b.hasMessages(i);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(int i) {
    }

    /* access modifiers changed from: protected */
    public void onPreExecute(int i) {
    }

    public void shutdown() {
        if (this.a == null) {
            throw new IllegalStateException("Service is not started.");
        }
        this.a.quit();
        this.a = null;
        this.b = null;
    }

    public void start() {
        if (this.a != null) {
            throw new IllegalStateException("Service is started.");
        }
        this.a = new HandlerThread("SenseService");
        this.a.start();
        this.b = new a(this, this.a.getLooper());
    }
}
