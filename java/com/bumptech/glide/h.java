package com.bumptech.glide;

import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.d.d.b;
import com.bumptech.glide.d.d.f.c;
import com.bumptech.glide.f.e;
import java.io.InputStream;

/* compiled from: GifTypeRequest */
public class h<ModelType> extends g<ModelType> {
    private final l<ModelType, InputStream> g;
    private final c h;

    private static <A, R> e<A, InputStream, b, R> a(i iVar, l<A, InputStream> lVar, Class<R> cls, c<b, R> cVar) {
        if (lVar == null) {
            return null;
        }
        if (cVar == null) {
            cVar = iVar.a(b.class, cls);
        }
        return new e<>(lVar, cVar, iVar.b(InputStream.class, b.class));
    }

    h(e<ModelType, ?, ?, ?> eVar, l<ModelType, InputStream> lVar, c cVar) {
        super(a(eVar.c, lVar, b.class, null), b.class, eVar);
        this.g = lVar;
        this.h = cVar;
        c();
    }
}
