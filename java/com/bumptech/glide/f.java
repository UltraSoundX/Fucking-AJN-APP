package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.d.f.c;
import com.bumptech.glide.f.e;
import com.bumptech.glide.manager.g;

/* compiled from: GenericTranscodeRequest */
public class f<ModelType, DataType, ResourceType> extends e<ModelType, DataType, ResourceType, ResourceType> {
    private final l<ModelType, DataType> g;
    private final Class<DataType> h;
    private final Class<ResourceType> i;
    private final c j;

    private static <A, T, Z, R> com.bumptech.glide.f.f<A, T, Z, R> a(i iVar, l<A, T> lVar, Class<T> cls, Class<Z> cls2, c<Z, R> cVar) {
        return new e(lVar, cVar, iVar.b(cls, cls2));
    }

    f(Context context, i iVar, Class<ModelType> cls, l<ModelType, DataType> lVar, Class<DataType> cls2, Class<ResourceType> cls3, com.bumptech.glide.manager.l lVar2, g gVar, c cVar) {
        super(context, cls, a(iVar, lVar, cls2, cls3, com.bumptech.glide.d.d.f.e.b()), cls3, iVar, lVar2, gVar);
        this.g = lVar;
        this.h = cls2;
        this.i = cls3;
        this.j = cVar;
    }
}
