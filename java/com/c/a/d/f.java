package com.c.a.d;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: PriorityObjectBlockingQueue */
public class f<E> extends AbstractQueue<E> implements Serializable, BlockingQueue<E> {
    transient a<E> a;
    private final int b;
    private final AtomicInteger c;
    private transient a<E> d;
    private final ReentrantLock e;
    private final Condition f;
    private final ReentrantLock g;
    private final Condition h;

    /* compiled from: PriorityObjectBlockingQueue */
    private class a implements Iterator<E> {
        private a<E> b;
        private a<E> c;
        private E d;

        a() {
            f.this.a();
            try {
                this.b = f.this.a.a;
                if (this.b != null) {
                    this.d = this.b.b();
                }
            } finally {
                f.this.b();
            }
        }

        public boolean hasNext() {
            return this.b != null;
        }

        /* JADX WARNING: Incorrect type for immutable var: ssa=com.c.a.d.a<E>, code=com.c.a.d.a, for r3v0, types: [com.c.a.d.a, com.c.a.d.a<E>] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.c.a.d.a<E> a(com.c.a.d.a r3) {
            /*
                r2 = this;
            L_0x0000:
                com.c.a.d.a<T> r0 = r3.a
                if (r0 != r3) goto L_0x000b
                com.c.a.d.f r0 = com.c.a.d.f.this
                com.c.a.d.a<E> r0 = r0.a
                com.c.a.d.a<T> r0 = r0.a
            L_0x000a:
                return r0
            L_0x000b:
                if (r0 == 0) goto L_0x000a
                java.lang.Object r1 = r0.b()
                if (r1 != 0) goto L_0x000a
                r3 = r0
                goto L_0x0000
            */
            throw new UnsupportedOperationException("Method not decompiled: com.c.a.d.f.a.a(com.c.a.d.a):com.c.a.d.a");
        }

        public E next() {
            f.this.a();
            try {
                if (this.b == null) {
                    throw new NoSuchElementException();
                }
                E e = this.d;
                this.c = this.b;
                this.b = a(this.b);
                this.d = this.b == null ? null : this.b.b();
                return e;
            } finally {
                f.this.b();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r4.a.a(r0, r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void remove() {
            /*
                r4 = this;
                com.c.a.d.a<E> r0 = r4.c
                if (r0 != 0) goto L_0x000a
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r0.<init>()
                throw r0
            L_0x000a:
                com.c.a.d.f r0 = com.c.a.d.f.this
                r0.a()
                com.c.a.d.a<E> r2 = r4.c     // Catch:{ all -> 0x002a }
                r0 = 0
                r4.c = r0     // Catch:{ all -> 0x002a }
                com.c.a.d.f r0 = com.c.a.d.f.this     // Catch:{ all -> 0x002a }
                com.c.a.d.a<E> r1 = r0.a     // Catch:{ all -> 0x002a }
                com.c.a.d.a<T> r0 = r1.a     // Catch:{ all -> 0x002a }
            L_0x001a:
                if (r0 != 0) goto L_0x0022
            L_0x001c:
                com.c.a.d.f r0 = com.c.a.d.f.this
                r0.b()
                return
            L_0x0022:
                if (r0 != r2) goto L_0x0031
                com.c.a.d.f r2 = com.c.a.d.f.this     // Catch:{ all -> 0x002a }
                r2.a(r0, r1)     // Catch:{ all -> 0x002a }
                goto L_0x001c
            L_0x002a:
                r0 = move-exception
                com.c.a.d.f r1 = com.c.a.d.f.this
                r1.b()
                throw r0
            L_0x0031:
                com.c.a.d.a<T> r1 = r0.a     // Catch:{ all -> 0x002a }
                r3 = r1
                r1 = r0
                r0 = r3
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.c.a.d.f.a.remove():void");
        }
    }

    private void c() {
        ReentrantLock reentrantLock = this.e;
        reentrantLock.lock();
        try {
            this.f.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void d() {
        ReentrantLock reentrantLock = this.g;
        reentrantLock.lock();
        try {
            this.h.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private synchronized E a(a<E> aVar) {
        E e2;
        if (aVar == null) {
            e2 = e();
        } else {
            b(aVar);
            e2 = null;
        }
        return e2;
    }

    private void b(a<E> aVar) {
        boolean z;
        a aVar2 = this.a;
        while (true) {
            if (aVar2.a == null) {
                z = false;
                break;
            }
            a<T> aVar3 = aVar2.a;
            if (aVar3.a().ordinal() > aVar.a().ordinal()) {
                aVar2.a = aVar;
                aVar.a = aVar3;
                z = true;
                break;
            }
            aVar2 = aVar2.a;
        }
        if (!z) {
            this.d.a = aVar;
            this.d = aVar;
        }
    }

    private E e() {
        a<E> aVar = this.a;
        a<T> aVar2 = aVar.a;
        aVar.a = aVar;
        this.a = aVar2;
        E b2 = aVar2.b();
        aVar2.a(null);
        return b2;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.g.lock();
        this.e.lock();
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.e.unlock();
        this.g.unlock();
    }

    public f() {
        this(Integer.MAX_VALUE);
    }

    public f(int i) {
        this.c = new AtomicInteger();
        this.e = new ReentrantLock();
        this.f = this.e.newCondition();
        this.g = new ReentrantLock();
        this.h = this.g.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.b = i;
        a<E> aVar = new a<>(null);
        this.a = aVar;
        this.d = aVar;
    }

    public int size() {
        return this.c.get();
    }

    public int remainingCapacity() {
        return this.b - this.c.get();
    }

    public void put(E e2) throws InterruptedException {
        if (e2 == null) {
            throw new NullPointerException();
        }
        a aVar = new a(e2);
        ReentrantLock reentrantLock = this.g;
        AtomicInteger atomicInteger = this.c;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == this.b) {
            try {
                this.h.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        a(aVar);
        int andIncrement = atomicInteger.getAndIncrement();
        if (andIncrement + 1 < this.b) {
            this.h.signal();
        }
        if (andIncrement == 0) {
            c();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean offer(E e2, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e2 == null) {
            throw new NullPointerException();
        }
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.g;
        AtomicInteger atomicInteger = this.c;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == this.b) {
            try {
                if (nanos <= 0) {
                    reentrantLock.unlock();
                    return false;
                }
                nanos = this.h.awaitNanos(nanos);
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        a(new a(e2));
        int andIncrement = atomicInteger.getAndIncrement();
        if (andIncrement + 1 < this.b) {
            this.h.signal();
        }
        reentrantLock.unlock();
        if (andIncrement == 0) {
            c();
        }
        return true;
    }

    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException();
        }
        AtomicInteger atomicInteger = this.c;
        if (atomicInteger.get() == this.b) {
            return false;
        }
        int i = -1;
        a aVar = new a(e2);
        ReentrantLock reentrantLock = this.g;
        reentrantLock.lock();
        try {
            if (atomicInteger.get() < this.b) {
                a(aVar);
                i = atomicInteger.getAndIncrement();
                if (i + 1 < this.b) {
                    this.h.signal();
                }
            }
            if (i == 0) {
                c();
            }
            if (i >= 0) {
                return true;
            }
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public E take() throws InterruptedException {
        AtomicInteger atomicInteger = this.c;
        ReentrantLock reentrantLock = this.e;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                this.f.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        E a2 = a(null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.f.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.b) {
            d();
        }
        return a2;
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        E e2 = null;
        long nanos = timeUnit.toNanos(j);
        AtomicInteger atomicInteger = this.c;
        ReentrantLock reentrantLock = this.e;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                if (atomicInteger.get() != 0) {
                    e2 = a(null);
                    int andDecrement = atomicInteger.getAndDecrement();
                    if (andDecrement > 1) {
                        this.f.signal();
                    }
                    reentrantLock.unlock();
                    if (andDecrement == this.b) {
                        d();
                    }
                } else if (nanos <= 0) {
                    break;
                } else {
                    nanos = this.f.awaitNanos(nanos);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return e2;
    }

    /* JADX INFO: finally extract failed */
    public E poll() {
        E e2 = null;
        AtomicInteger atomicInteger = this.c;
        if (atomicInteger.get() != 0) {
            int i = -1;
            ReentrantLock reentrantLock = this.e;
            reentrantLock.lock();
            try {
                if (atomicInteger.get() > 0) {
                    e2 = a(null);
                    i = atomicInteger.getAndDecrement();
                    if (i > 1) {
                        this.f.signal();
                    }
                }
                reentrantLock.unlock();
                if (i == this.b) {
                    d();
                }
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        return e2;
    }

    public E peek() {
        E e2 = null;
        if (this.c.get() != 0) {
            ReentrantLock reentrantLock = this.e;
            reentrantLock.lock();
            try {
                a<T> aVar = this.a.a;
                if (aVar != null) {
                    e2 = aVar.b();
                    reentrantLock.unlock();
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return e2;
    }

    /* access modifiers changed from: 0000 */
    public void a(a<E> aVar, a<E> aVar2) {
        aVar.a(null);
        aVar2.a = aVar.a;
        if (this.d == aVar) {
            this.d = aVar2;
        }
        if (this.c.getAndDecrement() == this.b) {
            this.h.signal();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        a();
        try {
            a aVar = this.a;
            for (a aVar2 = aVar.a; aVar2 != null; aVar2 = aVar2.a) {
                if (obj.equals(aVar2.b())) {
                    a(aVar2, aVar);
                    b();
                    return true;
                }
                aVar = aVar2;
            }
            b();
            return false;
        } catch (Throwable th) {
            b();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        a();
        try {
            for (a<T> aVar = this.a.a; aVar != null; aVar = aVar.a) {
                if (obj.equals(aVar.b())) {
                    b();
                    return true;
                }
            }
            b();
            return false;
        } catch (Throwable th) {
            b();
            throw th;
        }
    }

    public Object[] toArray() {
        a();
        try {
            Object[] objArr = new Object[this.c.get()];
            int i = 0;
            a<T> aVar = this.a.a;
            while (aVar != null) {
                int i2 = i + 1;
                objArr[i] = aVar.b();
                aVar = aVar.a;
                i = i2;
            }
            return objArr;
        } finally {
            b();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        a();
        try {
            int i = this.c.get();
            if (tArr.length < i) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
            }
            int i2 = 0;
            a<T> aVar = this.a.a;
            while (aVar != null) {
                int i3 = i2 + 1;
                tArr[i2] = aVar.b();
                aVar = aVar.a;
                i2 = i3;
            }
            if (tArr.length > i2) {
                tArr[i2] = null;
            }
            return tArr;
        } finally {
            b();
        }
    }

    public void clear() {
        a();
        try {
            a aVar = this.a;
            while (true) {
                a aVar2 = aVar.a;
                if (aVar2 == null) {
                    break;
                }
                aVar.a = aVar;
                aVar2.a(null);
                aVar = aVar2;
            }
            this.a = this.d;
            if (this.c.getAndSet(0) == this.b) {
                this.h.signal();
            }
        } finally {
            b();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        ReentrantLock reentrantLock;
        a aVar;
        int i2;
        boolean z = true;
        boolean z2 = false;
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else if (i <= 0) {
            return 0;
        } else {
            reentrantLock = this.e;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.c.get());
                aVar = this.a;
                i2 = 0;
                while (i2 < min) {
                    a aVar2 = aVar.a;
                    collection.add(aVar2.b());
                    aVar2.a(null);
                    aVar.a = aVar;
                    i2++;
                    aVar = aVar2;
                }
                if (i2 > 0) {
                    this.a = aVar;
                    if (this.c.getAndAdd(-i2) != this.b) {
                        z = false;
                    }
                } else {
                    z = false;
                }
                reentrantLock.unlock();
                if (z) {
                    d();
                }
                return min;
            } catch (Throwable th) {
                Throwable th2 = th;
                z2 = z;
                th = th2;
            }
        }
        reentrantLock.unlock();
        if (z2) {
            d();
        }
        throw th;
    }

    public Iterator<E> iterator() {
        return new a();
    }
}
