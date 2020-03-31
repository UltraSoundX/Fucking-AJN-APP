package com.c.a.d;

/* compiled from: PriorityObjectBlockingQueue */
class a<T> {
    a<T> a;
    private boolean b = false;
    private e<?> c;

    a(T t) {
        a(t);
    }

    public b a() {
        return this.c.a;
    }

    public T b() {
        if (this.c == null) {
            return null;
        }
        if (this.b) {
            return this.c;
        }
        return this.c.b;
    }

    public void a(T t) {
        if (t == null) {
            this.c = null;
        } else if (t instanceof e) {
            this.c = (e) t;
            this.b = true;
        } else {
            this.c = new e<>(b.DEFAULT, t);
        }
    }
}
