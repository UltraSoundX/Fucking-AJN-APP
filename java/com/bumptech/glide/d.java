package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.f;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.d.b.b;
import com.bumptech.glide.d.d.e.a;
import com.bumptech.glide.d.d.f.c;
import com.bumptech.glide.f.e;
import java.io.InputStream;

/* compiled from: DrawableTypeRequest */
public class d<ModelType> extends c<ModelType> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final c i;

    private static <A, Z, R> e<A, g, Z, R> a(i iVar, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = iVar.a(cls, cls2);
        }
        return new e<>(new f(lVar, lVar2), cVar, iVar.b(g.class, cls));
    }

    d(Class<ModelType> cls, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, Context context, i iVar, com.bumptech.glide.manager.l lVar3, com.bumptech.glide.manager.g gVar, c cVar) {
        e a = a(iVar, lVar, lVar2, a.class, b.class, null);
        super(context, cls, a, iVar, lVar3, gVar);
        this.g = lVar;
        this.h = lVar2;
        this.i = cVar;
    }

    public b<ModelType> h() {
        return (b) this.i.a(new b(this, this.g, this.h, this.i));
    }

    public h<ModelType> i() {
        return (h) this.i.a(new h(this, this.g, this.i));
    }
}
