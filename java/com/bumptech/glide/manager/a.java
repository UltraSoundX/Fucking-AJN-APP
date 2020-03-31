package com.bumptech.glide.manager;

import com.bumptech.glide.i.h;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: ActivityFragmentLifecycle */
class a implements g {
    private final Set<h> a = Collections.newSetFromMap(new WeakHashMap());
    private boolean b;
    private boolean c;

    a() {
    }

    public void a(h hVar) {
        this.a.add(hVar);
        if (this.c) {
            hVar.f();
        } else if (this.b) {
            hVar.d();
        } else {
            hVar.e();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.b = true;
        for (h d : h.a((Collection<T>) this.a)) {
            d.d();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.b = false;
        for (h e : h.a((Collection<T>) this.a)) {
            e.e();
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.c = true;
        for (h f : h.a((Collection<T>) this.a)) {
            f.f();
        }
    }
}
