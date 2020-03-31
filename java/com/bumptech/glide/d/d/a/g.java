package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.d.a;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.f.b;
import java.io.File;

/* compiled from: FileDescriptorBitmapDataLoadProvider */
public class g implements b<ParcelFileDescriptor, Bitmap> {
    private final e<File, Bitmap> a;
    private final h b;
    private final b c = new b();
    private final com.bumptech.glide.d.b<ParcelFileDescriptor> d = a.b();

    public g(c cVar, com.bumptech.glide.d.a aVar) {
        this.a = new com.bumptech.glide.d.d.c.c(new q(cVar, aVar));
        this.b = new h(cVar, aVar);
    }

    public e<File, Bitmap> a() {
        return this.a;
    }

    public e<ParcelFileDescriptor, Bitmap> b() {
        return this.b;
    }

    public com.bumptech.glide.d.b<ParcelFileDescriptor> c() {
        return this.d;
    }

    public f<Bitmap> d() {
        return this.c;
    }
}
