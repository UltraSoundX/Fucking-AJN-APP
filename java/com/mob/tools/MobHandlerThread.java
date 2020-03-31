package com.mob.tools;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Process;

public class MobHandlerThread extends Thread {
    private Looper looper;
    private int priority;
    private int tid;

    public MobHandlerThread() {
        this.tid = -1;
        this.priority = 0;
    }

    public MobHandlerThread(int i) {
        this.tid = -1;
        this.priority = i;
    }

    /* access modifiers changed from: protected */
    public void onLooperPrepared() {
    }

    /* access modifiers changed from: protected */
    public void onLooperPrepared(Looper looper2) {
    }

    public void run() {
        try {
            realRun();
            this.tid = Process.myTid();
            Looper.prepare();
            synchronized (this) {
                this.looper = Looper.myLooper();
                notifyAll();
            }
            Process.setThreadPriority(this.priority);
            onLooperPrepared(this.looper);
            onLooperPrepared();
            Looper.loop();
            this.tid = -1;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    @Deprecated
    public void realRun() {
    }

    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        }
        synchronized (this) {
            while (isAlive() && this.looper == null) {
                try {
                    wait();
                } catch (Throwable th) {
                }
            }
        }
        return this.looper;
    }

    public boolean quit() {
        Looper looper2 = getLooper();
        if (looper2 == null) {
            return false;
        }
        looper2.quit();
        return true;
    }

    public int getThreadId() {
        return this.tid;
    }

    public static Handler newHandler(Callback callback) {
        return newHandler(null, null, callback);
    }

    public static Handler newHandler(String str, Callback callback) {
        return newHandler(str, null, callback);
    }

    public static Handler newHandler(Runnable runnable, Callback callback) {
        return newHandler(null, runnable, callback);
    }

    public static Handler newHandler(String str, final Runnable runnable, final Callback callback) {
        final Handler[] handlerArr = new Handler[1];
        AnonymousClass1 r2 = new MobHandlerThread() {
            public void run() {
                if (runnable != null) {
                    runnable.run();
                }
                MobHandlerThread.super.run();
            }

            /* access modifiers changed from: protected */
            public void onLooperPrepared(Looper looper) {
                synchronized (handlerArr) {
                    handlerArr[0] = new Handler(looper, callback);
                    handlerArr.notifyAll();
                }
            }
        };
        synchronized (handlerArr) {
            if (0 != 0) {
                try {
                    r2.setName(null);
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
            }
            r2.start();
            handlerArr.wait();
        }
        return handlerArr[0];
    }
}
