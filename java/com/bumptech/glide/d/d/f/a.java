package com.bumptech.glide.d.d.f;

import android.graphics.Bitmap;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.d.a.j;
import com.bumptech.glide.d.d.b.b;

/* compiled from: GifBitmapWrapperDrawableTranscoder */
public class a implements c<com.bumptech.glide.d.d.e.a, b> {
    private final c<Bitmap, j> a;

    public a(c<Bitmap, j> cVar) {
        this.a = cVar;
    }

    public k<b> a(k<com.bumptech.glide.d.d.e.a> kVar) {
        com.bumptech.glide.d.d.e.a aVar = (com.bumptech.glide.d.d.e.a) kVar.b();
        k b = aVar.b();
        if (b != null) {
            return this.a.a(b);
        }
        return aVar.c();
    }

    public String a() {
        return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
