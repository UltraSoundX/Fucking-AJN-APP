package com.bumptech.glide.d;

import com.bumptech.glide.d.b.k;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: MultiTransformation */
public class d<T> implements g<T> {
    private final Collection<? extends g<T>> a;
    private String b;

    @SafeVarargs
    public d(g<T>... gVarArr) {
        if (gVarArr.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.a = Arrays.asList(gVarArr);
    }

    public k<T> a(k<T> kVar, int i, int i2) {
        k<T> kVar2 = kVar;
        for (g a2 : this.a) {
            k<T> a3 = a2.a(kVar2, i, i2);
            if (kVar2 != null && !kVar2.equals(kVar) && !kVar2.equals(a3)) {
                kVar2.d();
            }
            kVar2 = a3;
        }
        return kVar2;
    }

    public String a() {
        if (this.b == null) {
            StringBuilder sb = new StringBuilder();
            for (g a2 : this.a) {
                sb.append(a2.a());
            }
            this.b = sb.toString();
        }
        return this.b;
    }
}
