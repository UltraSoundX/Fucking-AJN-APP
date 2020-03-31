package com.bumptech.glide.d.b.a;

import com.bumptech.glide.d.b.a.h;
import java.util.Queue;

/* compiled from: BaseKeyPool */
abstract class b<T extends h> {
    private final Queue<T> a = com.bumptech.glide.i.h.a(20);

    /* access modifiers changed from: protected */
    public abstract T b();

    b() {
    }

    /* access modifiers changed from: protected */
    public T c() {
        T t = (h) this.a.poll();
        if (t == null) {
            return b();
        }
        return t;
    }

    public void a(T t) {
        if (this.a.size() < 20) {
            this.a.offer(t);
        }
    }
}
