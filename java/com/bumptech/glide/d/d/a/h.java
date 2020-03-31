package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.a;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.e;
import java.io.IOException;

/* compiled from: FileDescriptorBitmapDecoder */
public class h implements e<ParcelFileDescriptor, Bitmap> {
    private final s a;
    private final c b;
    private a c;

    public h(c cVar, a aVar) {
        this(new s(), cVar, aVar);
    }

    public h(s sVar, c cVar, a aVar) {
        this.a = sVar;
        this.b = cVar;
        this.c = aVar;
    }

    public k<Bitmap> a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws IOException {
        return c.a(this.a.a(parcelFileDescriptor, this.b, i, i2, this.c), this.b);
    }

    public String a() {
        return "FileDescriptorBitmapDecoder.com.bumptech.glide.load.data.bitmap";
    }
}
