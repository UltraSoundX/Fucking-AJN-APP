package com.bumptech.glide.d.d.e;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.d.d.b;
import com.bumptech.glide.d.f;
import java.io.OutputStream;

/* compiled from: GifBitmapWrapperResourceEncoder */
public class d implements f<a> {
    private final f<Bitmap> a;
    private final f<b> b;
    private String c;

    public d(f<Bitmap> fVar, f<b> fVar2) {
        this.a = fVar;
        this.b = fVar2;
    }

    public boolean a(k<a> kVar, OutputStream outputStream) {
        a aVar = (a) kVar.b();
        k b2 = aVar.b();
        if (b2 != null) {
            return this.a.a(b2, outputStream);
        }
        return this.b.a(aVar.c(), outputStream);
    }

    public String a() {
        if (this.c == null) {
            this.c = this.a.a() + this.b.a();
        }
        return this.c;
    }
}
