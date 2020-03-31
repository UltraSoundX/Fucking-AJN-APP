package com.bumptech.glide.d.b.c;

import android.os.Process;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: FifoPriorityThreadPoolExecutor */
public class a extends ThreadPoolExecutor {
    private final AtomicInteger a;
    private final c b;

    /* renamed from: com.bumptech.glide.d.b.c.a$a reason: collision with other inner class name */
    /* compiled from: FifoPriorityThreadPoolExecutor */
    public static class C0040a implements ThreadFactory {
        int a = 0;

        public Thread newThread(Runnable runnable) {
            AnonymousClass1 r0 = new Thread(runnable, "fifo-pool-thread-" + this.a) {
                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            this.a++;
            return r0;
        }
    }

    /* compiled from: FifoPriorityThreadPoolExecutor */
    static class b<T> extends FutureTask<T> implements Comparable<b<?>> {
        private final int a;
        private final int b;

        public b(Runnable runnable, T t, int i) {
            super(runnable, t);
            if (!(runnable instanceof b)) {
                throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized");
            }
            this.a = ((b) runnable).b();
            this.b = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.b == bVar.b && this.a == bVar.a) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.a * 31) + this.b;
        }

        /* renamed from: a */
        public int compareTo(b<?> bVar) {
            int i = this.a - bVar.a;
            if (i == 0) {
                return this.b - bVar.b;
            }
            return i;
        }
    }

    /* compiled from: FifoPriorityThreadPoolExecutor */
    public enum c {
        IGNORE,
        LOG {
            /* access modifiers changed from: protected */
            public void a(Throwable th) {
                if (Log.isLoggable("PriorityExecutor", 6)) {
                    Log.e("PriorityExecutor", "Request threw uncaught throwable", th);
                }
            }
        },
        THROW {
            /* access modifiers changed from: protected */
            public void a(Throwable th) {
                super.a(th);
                throw new RuntimeException(th);
            }
        };

        /* access modifiers changed from: protected */
        public void a(Throwable th) {
        }
    }

    public a(int i) {
        this(i, c.LOG);
    }

    public a(int i, c cVar) {
        this(i, i, 0, TimeUnit.MILLISECONDS, new C0040a(), cVar);
    }

    public a(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, c cVar) {
        super(i, i2, j, timeUnit, new PriorityBlockingQueue(), threadFactory);
        this.a = new AtomicInteger();
        this.b = cVar;
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new b(runnable, t, this.a.getAndIncrement());
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            Future future = (Future) runnable;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    this.b.a(e);
                } catch (ExecutionException e2) {
                    this.b.a(e2);
                }
            }
        }
    }
}
