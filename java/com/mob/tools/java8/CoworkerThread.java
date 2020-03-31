package com.mob.tools.java8;

import com.mob.tools.java8.Closure.IClosure;
import com.mob.tools.java8.Closure.IClosure1V;

public class CoworkerThread {

    public static class Builder<W> {
        private IClosure<W> producer;

        Builder(IClosure<W> iClosure) {
            this.producer = iClosure;
        }

        public Builder2<W> consumeWork(IClosure1V<W> iClosure1V) {
            return new Builder2<>(this.producer, iClosure1V);
        }
    }

    public static class Builder2<W> {
        private IClosure1V<W> consumer;
        private IClosure<W> producer;

        Builder2(IClosure<W> iClosure, IClosure1V<W> iClosure1V) {
            this.producer = iClosure;
            this.consumer = iClosure1V;
        }

        public Builder3<W> threadCount(int i) {
            return new Builder3<>(this.producer, this.consumer, i);
        }

        public void start() throws Throwable {
            new Builder3(this.producer, this.consumer, 5).start();
        }
    }

    public static class Builder3<W> {
        /* access modifiers changed from: private */
        public IClosure1V<W> consumer;
        /* access modifiers changed from: private */
        public int finished;
        /* access modifiers changed from: private */
        public IClosure<W> producer;
        /* access modifiers changed from: private */
        public Throwable t;
        /* access modifiers changed from: private */
        public int threadCount;

        Builder3(IClosure<W> iClosure, IClosure1V<W> iClosure1V, int i) {
            this.producer = iClosure;
            this.consumer = iClosure1V;
            this.threadCount = i;
        }

        public synchronized void start() throws Throwable {
            synchronized (this) {
                if (!(this.producer == null || this.consumer == null)) {
                    this.finished = 0;
                    for (int i = 0; i < this.threadCount; i++) {
                        new Thread() {
                            public void run() {
                                Object call;
                                while (Builder3.this.t == null) {
                                    try {
                                        synchronized (Builder3.this.producer) {
                                            call = Builder3.this.producer.call();
                                        }
                                        if (call == null) {
                                            break;
                                        }
                                        Builder3.this.consumer.call(call);
                                    } catch (Throwable th) {
                                        Builder3.this.t = th;
                                    }
                                }
                                synchronized (Builder3.this) {
                                    Builder3.this.finished = Builder3.this.finished + 1;
                                    if (Builder3.this.finished == Builder3.this.threadCount) {
                                        Builder3.this.notify();
                                    }
                                }
                            }
                        }.start();
                    }
                    wait();
                    if (this.t != null) {
                        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                        StackTraceElement[] stackTrace2 = this.t.getStackTrace();
                        StackTraceElement[] stackTraceElementArr = new StackTraceElement[((stackTrace.length + stackTrace2.length) - 2)];
                        System.arraycopy(stackTrace2, 0, stackTraceElementArr, 0, stackTrace2.length);
                        System.arraycopy(stackTrace, 2, stackTraceElementArr, stackTrace2.length, stackTrace.length - 2);
                        this.t.setStackTrace(stackTraceElementArr);
                        throw this.t;
                    }
                }
            }
        }
    }

    public static <W> Builder<W> produceWork(IClosure<W> iClosure) {
        return new Builder<>(iClosure);
    }
}
