package com.c.a.d;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PriorityExecutor */
public class d implements Executor {
    private static final ThreadFactory a = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "PriorityExecutor #" + this.a.getAndIncrement());
        }
    };
    private final BlockingQueue<Runnable> b;
    private final ThreadPoolExecutor c;

    public d() {
        this(5);
    }

    public d(int i) {
        this.b = new f();
        this.c = new ThreadPoolExecutor(i, 256, 1, TimeUnit.SECONDS, this.b, a);
    }

    public void execute(Runnable runnable) {
        this.c.execute(runnable);
    }
}
