package com.bumptech.glide.d.d;

import com.bumptech.glide.d.b.k;

/* compiled from: SimpleResource */
public class c<T> implements k<T> {
    protected final T a;

    public c(T t) {
        if (t == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = t;
    }

    public final T b() {
        return this.a;
    }

    public final int c() {
        return 1;
    }

    public void d() {
    }
}
