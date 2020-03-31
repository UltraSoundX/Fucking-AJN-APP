package com.bumptech.glide.d.d.d;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.g;

/* compiled from: GifDrawableTransformation */
public class e implements g<b> {
    private final g<Bitmap> a;
    private final c b;

    public e(g<Bitmap> gVar, c cVar) {
        this.a = gVar;
        this.b = cVar;
    }

    public k<b> a(k<b> kVar, int i, int i2) {
        b bVar = (b) kVar.b();
        Bitmap b2 = ((b) kVar.b()).b();
        Bitmap bitmap = (Bitmap) this.a.a(new com.bumptech.glide.d.d.a.c(b2, this.b), i, i2).b();
        if (!bitmap.equals(b2)) {
            return new d(new b(bVar, bitmap, this.a));
        }
        return kVar;
    }

    public String a() {
        return this.a.a();
    }
}
