package com.bumptech.glide.d.b.b;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: DiskCacheWriteLocker */
final class c {
    private final Map<com.bumptech.glide.d.c, a> a = new HashMap();
    private final b b = new b();

    /* compiled from: DiskCacheWriteLocker */
    private static class a {
        final Lock a;
        int b;

        private a() {
            this.a = new ReentrantLock();
        }
    }

    /* compiled from: DiskCacheWriteLocker */
    private static class b {
        private final Queue<a> a;

        private b() {
            this.a = new ArrayDeque();
        }

        /* access modifiers changed from: 0000 */
        public a a() {
            a aVar;
            synchronized (this.a) {
                aVar = (a) this.a.poll();
            }
            if (aVar == null) {
                return new a();
            }
            return aVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar) {
            synchronized (this.a) {
                if (this.a.size() < 10) {
                    this.a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    /* access modifiers changed from: 0000 */
    public void a(com.bumptech.glide.d.c cVar) {
        a aVar;
        synchronized (this) {
            aVar = (a) this.a.get(cVar);
            if (aVar == null) {
                aVar = this.b.a();
                this.a.put(cVar, aVar);
            }
            aVar.b++;
        }
        aVar.a.lock();
    }

    /* access modifiers changed from: 0000 */
    public void b(com.bumptech.glide.d.c cVar) {
        a aVar;
        int i;
        synchronized (this) {
            aVar = (a) this.a.get(cVar);
            if (aVar == null || aVar.b <= 0) {
                StringBuilder append = new StringBuilder().append("Cannot release a lock that is not held, key: ").append(cVar).append(", interestedThreads: ");
                if (aVar == null) {
                    i = 0;
                } else {
                    i = aVar.b;
                }
                throw new IllegalArgumentException(append.append(i).toString());
            }
            int i2 = aVar.b - 1;
            aVar.b = i2;
            if (i2 == 0) {
                a aVar2 = (a) this.a.remove(cVar);
                if (!aVar2.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVar2 + ", key: " + cVar);
                }
                this.b.a(aVar2);
            }
        }
        aVar.a.unlock();
    }
}
