package com.bumptech.glide;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.f;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.d.f.c;
import com.bumptech.glide.f.e;
import java.io.InputStream;

/* compiled from: BitmapTypeRequest */
public class b<ModelType> extends a<ModelType, Bitmap> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final i i;
    private final c j;

    private static <A, R> e<A, g, Bitmap, R> a(i iVar, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<R> cls, c<Bitmap, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = iVar.a(Bitmap.class, cls);
        }
        return new e<>(new f(lVar, lVar2), cVar, iVar.b(g.class, Bitmap.class));
    }

    b(e<ModelType, ?, ?, ?> eVar, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, c cVar) {
        super(a(eVar.c, lVar, lVar2, Bitmap.class, null), Bitmap.class, eVar);
        this.g = lVar;
        this.h = lVar2;
        this.i = eVar.c;
        this.j = cVar;
    }
}
