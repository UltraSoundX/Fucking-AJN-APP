package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import com.bumptech.glide.d.a;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.e;
import java.io.InputStream;

/* compiled from: StreamBitmapDecoder */
public class q implements e<InputStream, Bitmap> {
    private final f a;
    private c b;
    private a c;
    private String d;

    public q(c cVar, a aVar) {
        this(f.a, cVar, aVar);
    }

    public q(f fVar, c cVar, a aVar) {
        this.a = fVar;
        this.b = cVar;
        this.c = aVar;
    }

    public k<Bitmap> a(InputStream inputStream, int i, int i2) {
        return c.a(this.a.a(inputStream, this.b, i, i2, this.c), this.b);
    }

    public String a() {
        if (this.d == null) {
            this.d = "StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap" + this.a.a() + this.c.name();
        }
        return this.d;
    }
}
