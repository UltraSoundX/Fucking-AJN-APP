package com.e23.ajn.d;

import android.os.Process;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/* compiled from: BgThread */
public abstract class c extends Thread {
    private static ThreadPoolExecutor a = ((ThreadPoolExecutor) Executors.newFixedThreadPool(2));

    public abstract void a();

    public c() {
        int i = 4;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors < 4) {
            i = availableProcessors;
        }
        a.setCorePoolSize(i);
        a.setMaximumPoolSize(i);
    }

    public synchronized void start() {
        a.execute(this);
    }

    public final void run() {
        Process.setThreadPriority(10);
        a();
    }
}
