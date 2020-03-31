package com.c.a.a;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: KeyExpiryMap */
public class a<K, V> extends ConcurrentHashMap<K, Long> {
    public a(int i, float f) {
        super(i, f, 16);
    }

    public a() {
    }

    /* renamed from: a */
    public synchronized Long get(Object obj) {
        Long l;
        if (containsKey(obj)) {
            l = (Long) super.get(obj);
        } else {
            l = null;
        }
        return l;
    }

    /* renamed from: a */
    public synchronized Long put(K k, Long l) {
        if (containsKey(k)) {
            remove(k);
        }
        return (Long) super.put(k, l);
    }

    public synchronized boolean containsKey(Object obj) {
        boolean z;
        Long l = (Long) super.get(obj);
        if (l == null || System.currentTimeMillis() >= l.longValue()) {
            remove(obj);
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    /* renamed from: b */
    public synchronized Long remove(Object obj) {
        return (Long) super.remove(obj);
    }

    public synchronized void clear() {
        super.clear();
    }
}
