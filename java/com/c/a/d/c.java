package com.c.a.d;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PriorityAsyncTask */
public abstract class c<Params, Progress, Result> {
    public static final Executor a = new d();
    private static final b b = new b(null);
    private final C0048c<Params, Result> c = new C0048c<Params, Result>() {
        public Result call() throws Exception {
            c.this.g.set(true);
            Process.setThreadPriority(10);
            return c.this.d(c.this.c((Params[]) this.b));
        }
    };
    private final FutureTask<Result> d = new FutureTask<Result>(this.c) {
        /* access modifiers changed from: protected */
        public void done() {
            try {
                c.this.c(get());
            } catch (InterruptedException e) {
                com.c.a.e.c.a(e.getMessage());
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                c.this.c(null);
            }
        }
    };
    private volatile boolean e = false;
    private final AtomicBoolean f = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final AtomicBoolean g = new AtomicBoolean();
    private b h;

    /* compiled from: PriorityAsyncTask */
    private static class a<Data> {
        final c a;
        final Data[] b;

        a(c cVar, Data... dataArr) {
            this.a = cVar;
            this.b = dataArr;
        }
    }

    /* compiled from: PriorityAsyncTask */
    private static class b extends Handler {
        /* synthetic */ b(b bVar) {
            this();
        }

        private b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.a.e(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.b((Progress[]) aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.c.a.d.c$c reason: collision with other inner class name */
    /* compiled from: PriorityAsyncTask */
    private static abstract class C0048c<Params, Result> implements Callable<Result> {
        Params[] b;

        private C0048c() {
        }

        /* synthetic */ C0048c(C0048c cVar) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Result c(Params... paramsArr);

    public void a(b bVar) {
        this.h = bVar;
    }

    /* access modifiers changed from: private */
    public void c(Result result) {
        if (!this.g.get()) {
            d(result);
        }
    }

    /* access modifiers changed from: private */
    public Result d(Result result) {
        b.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(Result result) {
    }

    /* access modifiers changed from: protected */
    public void b(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    public void b(Result result) {
        b();
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public final boolean c() {
        return this.f.get();
    }

    public final c<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.e) {
            throw new IllegalStateException("Cannot execute task: the task is already executed.");
        }
        this.e = true;
        a();
        this.c.b = paramsArr;
        executor.execute(new g(this.h, this.d));
        return this;
    }

    /* access modifiers changed from: protected */
    public final void d(Progress... progressArr) {
        if (!c()) {
            b.obtainMessage(2, new a(this, progressArr)).sendToTarget();
        }
    }

    /* access modifiers changed from: private */
    public void e(Result result) {
        if (c()) {
            b(result);
        } else {
            a(result);
        }
    }
}
