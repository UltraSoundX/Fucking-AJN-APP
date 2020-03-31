package com.bumptech.glide.d.b.b;

import android.annotation.SuppressLint;
import com.bumptech.glide.d.b.b.h.a;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.c;
import com.bumptech.glide.i.e;

/* compiled from: LruResourceCache */
public class g extends e<c, k<?>> implements h {
    private a a;

    public /* synthetic */ k a(c cVar) {
        return (k) super.c(cVar);
    }

    public /* bridge */ /* synthetic */ k b(c cVar, k kVar) {
        return (k) super.b(cVar, kVar);
    }

    public g(int i) {
        super(i);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(c cVar, k<?> kVar) {
        if (this.a != null) {
            this.a.b(kVar);
        }
    }

    /* access modifiers changed from: protected */
    public int a(k<?> kVar) {
        return kVar.c();
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 60) {
            a();
        } else if (i >= 40) {
            b(b() / 2);
        }
    }
}
