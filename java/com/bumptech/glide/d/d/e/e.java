package com.bumptech.glide.d.d.e;

import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.c.g;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: GifBitmapWrapperStreamResourceDecoder */
public class e implements com.bumptech.glide.d.e<InputStream, a> {
    private final com.bumptech.glide.d.e<g, a> a;

    public e(com.bumptech.glide.d.e<g, a> eVar) {
        this.a = eVar;
    }

    public k<a> a(InputStream inputStream, int i, int i2) throws IOException {
        return this.a.a(new g(inputStream, null), i, i2);
    }

    public String a() {
        return this.a.a();
    }
}
