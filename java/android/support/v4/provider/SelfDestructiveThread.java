package android.support.v4.provider;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SelfDestructiveThread {
    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private Callback mCallback = new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    SelfDestructiveThread.this.onDestruction();
                    break;
                case 1:
                    SelfDestructiveThread.this.onInvokeRunnable((Runnable) message.obj);
                    break;
            }
            return true;
        }
    };
    private final int mDestructAfterMillisec;
    private int mGeneration;
    private Handler mHandler;
    private final Object mLock = new Object();
    private final int mPriority;
    private HandlerThread mThread;
    private final String mThreadName;

    public interface ReplyCallback<T> {
        void onReply(T t);
    }

    public SelfDestructiveThread(String str, int i, int i2) {
        this.mThreadName = str;
        this.mPriority = i;
        this.mDestructAfterMillisec = i2;
        this.mGeneration = 0;
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mThread != null;
        }
        return z;
    }

    public int getGeneration() {
        int i;
        synchronized (this.mLock) {
            i = this.mGeneration;
        }
        return i;
    }

    private void post(Runnable runnable) {
        synchronized (this.mLock) {
            if (this.mThread == null) {
                this.mThread = new HandlerThread(this.mThreadName, this.mPriority);
                this.mThread.start();
                this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
                this.mGeneration++;
            }
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, runnable));
        }
    }

    public <T> void postAndReply(final Callable<T> callable, final ReplyCallback<T> replyCallback) {
        final Handler handler = new Handler();
        post(new Runnable() {
            public void run() {
                final Object obj;
                try {
                    obj = callable.call();
                } catch (Exception e) {
                    obj = null;
                }
                handler.post(new Runnable() {
                    public void run() {
                        replyCallback.onReply(obj);
                    }
                });
            }
        });
    }

    public <T> T postAndWait(Callable<T> callable, int i) throws InterruptedException {
        T t;
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        final Callable<T> callable2 = callable;
        post(new Runnable() {
            public void run() {
                try {
                    atomicReference.set(callable2.call());
                } catch (Exception e) {
                }
                reentrantLock.lock();
                try {
                    atomicBoolean.set(false);
                    newCondition.signal();
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                t = atomicReference.get();
            } else {
                long nanos = TimeUnit.MILLISECONDS.toNanos((long) i);
                do {
                    try {
                        nanos = newCondition.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                    }
                    if (!atomicBoolean.get()) {
                        t = atomicReference.get();
                        reentrantLock.unlock();
                    }
                } while (nanos > 0);
                throw new InterruptedException("timeout");
            }
            return t;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void onInvokeRunnable(Runnable runnable) {
        runnable.run();
        synchronized (this.mLock) {
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), (long) this.mDestructAfterMillisec);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onDestruction() {
        synchronized (this.mLock) {
            if (!this.mHandler.hasMessages(1)) {
                this.mThread.quit();
                this.mThread = null;
                this.mHandler = null;
            }
        }
    }
}
