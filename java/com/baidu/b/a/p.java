package com.baidu.b.a;

import android.os.Handler;
import android.os.Looper;

class p extends Thread {
    Handler a = null;
    private Object b = new Object();
    private boolean c = false;

    p() {
    }

    p(String str) {
        super(str);
    }

    public void a() {
        if (d.a) {
            d.a("Looper thread quit()");
        }
        this.a.getLooper().quit();
    }

    public void b() {
        synchronized (this.b) {
            try {
                if (!this.c) {
                    this.b.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void c() {
        synchronized (this.b) {
            this.c = true;
            this.b.notifyAll();
        }
    }

    public void run() {
        Looper.prepare();
        this.a = new Handler();
        if (d.a) {
            d.a("new Handler() finish!!");
        }
        Looper.loop();
        if (d.a) {
            d.a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}
