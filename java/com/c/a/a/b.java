package com.c.a.a;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* compiled from: LruMemoryCache */
public class b<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private a<K, Long> i;

    public b(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = i2;
        this.a = new LinkedHashMap<>(0, 0.75f, true);
        this.i = new a<>(0, 0.75f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r1 = c(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.e++;
        r0 = r4.a.put(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        if (r0 == null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        r4.a.put(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        if (r0 == null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        a(false, r5, r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r4.b += b(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0063, code lost:
        a(r4.c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V a(K r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x000b
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "key == null"
            r0.<init>(r1)
            throw r0
        L_0x000b:
            monitor-enter(r4)
            com.c.a.a.a<K, java.lang.Long> r1 = r4.i     // Catch:{ all -> 0x0053 }
            boolean r1 = r1.containsKey(r5)     // Catch:{ all -> 0x0053 }
            if (r1 != 0) goto L_0x0019
            r4.b(r5)     // Catch:{ all -> 0x0053 }
            monitor-exit(r4)     // Catch:{ all -> 0x0053 }
        L_0x0018:
            return r0
        L_0x0019:
            java.util.LinkedHashMap<K, V> r1 = r4.a     // Catch:{ all -> 0x0053 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0053 }
            if (r1 == 0) goto L_0x002a
            int r0 = r4.g     // Catch:{ all -> 0x0053 }
            int r0 = r0 + 1
            r4.g = r0     // Catch:{ all -> 0x0053 }
            monitor-exit(r4)     // Catch:{ all -> 0x0053 }
            r0 = r1
            goto L_0x0018
        L_0x002a:
            int r1 = r4.h     // Catch:{ all -> 0x0053 }
            int r1 = r1 + 1
            r4.h = r1     // Catch:{ all -> 0x0053 }
            monitor-exit(r4)     // Catch:{ all -> 0x0053 }
            java.lang.Object r1 = r4.c(r5)
            if (r1 == 0) goto L_0x0018
            monitor-enter(r4)
            int r0 = r4.e     // Catch:{ all -> 0x0060 }
            int r0 = r0 + 1
            r4.e = r0     // Catch:{ all -> 0x0060 }
            java.util.LinkedHashMap<K, V> r0 = r4.a     // Catch:{ all -> 0x0060 }
            java.lang.Object r0 = r0.put(r5, r1)     // Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x0056
            java.util.LinkedHashMap<K, V> r2 = r4.a     // Catch:{ all -> 0x0060 }
            r2.put(r5, r0)     // Catch:{ all -> 0x0060 }
        L_0x004b:
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x0063
            r2 = 0
            r4.a(r2, r5, r1, r0)
            goto L_0x0018
        L_0x0053:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0053 }
            throw r0
        L_0x0056:
            int r2 = r4.b     // Catch:{ all -> 0x0060 }
            int r3 = r4.b(r5, r1)     // Catch:{ all -> 0x0060 }
            int r2 = r2 + r3
            r4.b = r2     // Catch:{ all -> 0x0060 }
            goto L_0x004b
        L_0x0060:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            throw r0
        L_0x0063:
            int r0 = r4.c
            r4.a(r0)
            r0 = r1
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.a.b.a(java.lang.Object):java.lang.Object");
    }

    public final V a(K k, V v, long j) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.d++;
            this.b += b(k, v);
            put = this.a.put(k, v);
            this.i.put(k, Long.valueOf(j));
            if (put != null) {
                this.b -= b(k, put);
            }
        }
        if (put != null) {
            a(false, k, put, v);
        }
        a(this.c);
        return put;
    }

    private void a(int i2) {
        Object key;
        Object value;
        while (true) {
            synchronized (this) {
                if (this.b > i2 && !this.a.isEmpty()) {
                    Entry entry = (Entry) this.a.entrySet().iterator().next();
                    key = entry.getKey();
                    value = entry.getValue();
                    this.a.remove(key);
                    this.i.remove(key);
                    this.b -= b(key, value);
                    this.f++;
                }
            }
            a(true, key, value, null);
        }
    }

    public final V b(K k) {
        V remove;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            remove = this.a.remove(k);
            this.i.remove(k);
            if (remove != null) {
                this.b -= b(k, remove);
            }
        }
        if (remove != null) {
            a(false, k, remove, null);
        }
        return remove;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, K k, V v, V v2) {
    }

    /* access modifiers changed from: protected */
    public V c(K k) {
        return null;
    }

    private int b(K k, V v) {
        int a2 = a(k, v);
        if (a2 <= 0) {
            this.b = 0;
            for (Entry entry : this.a.entrySet()) {
                this.b = a(entry.getKey(), entry.getValue()) + this.b;
            }
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public int a(K k, V v) {
        return 1;
    }

    public final synchronized String toString() {
        String format;
        int i2 = 0;
        synchronized (this) {
            int i3 = this.g + this.h;
            if (i3 != 0) {
                i2 = (this.g * 100) / i3;
            }
            format = String.format("LruMemoryCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i2)});
        }
        return format;
    }
}
