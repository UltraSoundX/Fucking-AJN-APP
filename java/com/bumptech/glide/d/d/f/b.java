package com.bumptech.glide.d.d.f;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.d.a.j;

/* compiled from: GlideBitmapDrawableTranscoder */
public class b implements c<Bitmap, j> {
    private final Resources a;
    private final c b;

    public b(Resources resources, c cVar) {
        this.a = resources;
        this.b = cVar;
    }

    public k<j> a(k<Bitmap> kVar) {
        return new com.bumptech.glide.d.d.a.k(new j(this.a, (Bitmap) kVar.b()), this.b);
    }

    public String a() {
        return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
