package android.arch.lifecycle;

import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    /* access modifiers changed from: private */
    public static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    /* access modifiers changed from: private */
    public int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    /* access modifiers changed from: private */
    public final Object mDataLock = new Object();
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private android.arch.core.b.b<k<T>, b> mObservers = new android.arch.core.b.b<>();
    /* access modifiers changed from: private */
    public volatile Object mPendingData = NOT_SET;
    private final Runnable mPostValueRunnable = new Runnable() {
        public void run() {
            Object access$100;
            synchronized (LiveData.this.mDataLock) {
                access$100 = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(access$100);
        }
    };
    private int mVersion = -1;

    class LifecycleBoundObserver extends b implements GenericLifecycleObserver {
        final e a;

        LifecycleBoundObserver(e eVar, k<T> kVar) {
            super(kVar);
            this.a = eVar;
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            return this.a.getLifecycle().a().a(android.arch.lifecycle.c.b.STARTED);
        }

        public void a(e eVar, android.arch.lifecycle.c.a aVar) {
            if (this.a.getLifecycle().a() == android.arch.lifecycle.c.b.DESTROYED) {
                LiveData.this.removeObserver(this.c);
            } else {
                a(a());
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a(e eVar) {
            return this.a == eVar;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.a.getLifecycle().b(this);
        }
    }

    private class a extends b {
        a(k<T> kVar) {
            super(kVar);
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            return true;
        }
    }

    private abstract class b {
        final k<T> c;
        boolean d;
        int e = -1;

        /* access modifiers changed from: 0000 */
        public abstract boolean a();

        b(k<T> kVar) {
            this.c = kVar;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(e eVar) {
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
        }

        /* access modifiers changed from: 0000 */
        public void a(boolean z) {
            int i = 1;
            if (z != this.d) {
                this.d = z;
                boolean z2 = LiveData.this.mActiveCount == 0;
                LiveData liveData = LiveData.this;
                int access$300 = liveData.mActiveCount;
                if (!this.d) {
                    i = -1;
                }
                liveData.mActiveCount = i + access$300;
                if (z2 && this.d) {
                    LiveData.this.onActive();
                }
                if (LiveData.this.mActiveCount == 0 && !this.d) {
                    LiveData.this.onInactive();
                }
                if (this.d) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }
    }

    private void considerNotify(b bVar) {
        if (bVar.d) {
            if (!bVar.a()) {
                bVar.a(false);
            } else if (bVar.e < this.mVersion) {
                bVar.e = this.mVersion;
                bVar.c.onChanged(this.mData);
            }
        }
    }

    /* access modifiers changed from: private */
    public void dispatchingValue(b bVar) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (bVar == null) {
                d c = this.mObservers.c();
                while (c.hasNext()) {
                    considerNotify((b) ((Entry) c.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            } else {
                considerNotify(bVar);
                bVar = null;
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    public void observe(e eVar, k<T> kVar) {
        if (eVar.getLifecycle().a() != android.arch.lifecycle.c.b.DESTROYED) {
            LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(eVar, kVar);
            b bVar = (b) this.mObservers.a(kVar, lifecycleBoundObserver);
            if (bVar != null && !bVar.a(eVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (bVar == null) {
                eVar.getLifecycle().a(lifecycleBoundObserver);
            }
        }
    }

    public void observeForever(k<T> kVar) {
        a aVar = new a(kVar);
        b bVar = (b) this.mObservers.a(kVar, aVar);
        if (bVar != null && (bVar instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        } else if (bVar == null) {
            aVar.a(true);
        }
    }

    public void removeObserver(k<T> kVar) {
        assertMainThread("removeObserver");
        b bVar = (b) this.mObservers.b(kVar);
        if (bVar != null) {
            bVar.b();
            bVar.a(false);
        }
    }

    public void removeObservers(e eVar) {
        assertMainThread("removeObservers");
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (((b) entry.getValue()).a(eVar)) {
                removeObserver((k) entry.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void postValue(T t) {
        boolean z;
        synchronized (this.mDataLock) {
            z = this.mPendingData == NOT_SET;
            this.mPendingData = t;
        }
        if (z) {
            android.arch.core.a.a.a().b(this.mPostValueRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public void setValue(T t) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        dispatchingValue(null);
    }

    public T getValue() {
        T t = this.mData;
        if (t != NOT_SET) {
            return t;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int getVersion() {
        return this.mVersion;
    }

    /* access modifiers changed from: protected */
    public void onActive() {
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
    }

    public boolean hasObservers() {
        return this.mObservers.a() > 0;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    private static void assertMainThread(String str) {
        if (!android.arch.core.a.a.a().b()) {
            throw new IllegalStateException("Cannot invoke " + str + " on a background" + " thread");
        }
    }
}
