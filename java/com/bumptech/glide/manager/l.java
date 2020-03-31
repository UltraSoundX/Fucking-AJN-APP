package com.bumptech.glide.manager;

import com.bumptech.glide.g.b;
import com.bumptech.glide.i.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: RequestTracker */
public class l {
    private final Set<b> a = Collections.newSetFromMap(new WeakHashMap());
    private final List<b> b = new ArrayList();
    private boolean c;

    public void a(b bVar) {
        this.a.add(bVar);
        if (!this.c) {
            bVar.b();
        } else {
            this.b.add(bVar);
        }
    }

    public void b(b bVar) {
        this.a.remove(bVar);
        this.b.remove(bVar);
    }

    public void a() {
        this.c = true;
        for (b bVar : h.a((Collection<T>) this.a)) {
            if (bVar.f()) {
                bVar.e();
                this.b.add(bVar);
            }
        }
    }

    public void b() {
        this.c = false;
        for (b bVar : h.a((Collection<T>) this.a)) {
            if (!bVar.g() && !bVar.i() && !bVar.f()) {
                bVar.b();
            }
        }
        this.b.clear();
    }

    public void c() {
        for (b d : h.a((Collection<T>) this.a)) {
            d.d();
        }
        this.b.clear();
    }

    public void d() {
        for (b bVar : h.a((Collection<T>) this.a)) {
            if (!bVar.g() && !bVar.i()) {
                bVar.e();
                if (!this.c) {
                    bVar.b();
                } else {
                    this.b.add(bVar);
                }
            }
        }
    }
}
