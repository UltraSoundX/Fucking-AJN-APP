package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.e;
import java.io.InputStream;

/* compiled from: ImageVideoBitmapDecoder */
public class m implements e<g, Bitmap> {
    private final e<InputStream, Bitmap> a;
    private final e<ParcelFileDescriptor, Bitmap> b;

    public m(e<InputStream, Bitmap> eVar, e<ParcelFileDescriptor, Bitmap> eVar2) {
        this.a = eVar;
        this.b = eVar2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.d.b.k<android.graphics.Bitmap> a(com.bumptech.glide.d.c.g r5, int r6, int r7) throws java.io.IOException {
        /*
            r4 = this;
            r1 = 0
            java.io.InputStream r0 = r5.a()
            if (r0 == 0) goto L_0x002d
            com.bumptech.glide.d.e<java.io.InputStream, android.graphics.Bitmap> r2 = r4.a     // Catch:{ IOException -> 0x001c }
            com.bumptech.glide.d.b.k r0 = r2.a(r0, r6, r7)     // Catch:{ IOException -> 0x001c }
        L_0x000d:
            if (r0 != 0) goto L_0x001b
            android.os.ParcelFileDescriptor r1 = r5.b()
            if (r1 == 0) goto L_0x001b
            com.bumptech.glide.d.e<android.os.ParcelFileDescriptor, android.graphics.Bitmap> r0 = r4.b
            com.bumptech.glide.d.b.k r0 = r0.a(r1, r6, r7)
        L_0x001b:
            return r0
        L_0x001c:
            r0 = move-exception
            java.lang.String r2 = "ImageVideoDecoder"
            r3 = 2
            boolean r2 = android.util.Log.isLoggable(r2, r3)
            if (r2 == 0) goto L_0x002d
            java.lang.String r2 = "ImageVideoDecoder"
            java.lang.String r3 = "Failed to load image from stream, trying FileDescriptor"
            android.util.Log.v(r2, r3, r0)
        L_0x002d:
            r0 = r1
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.d.a.m.a(com.bumptech.glide.d.c.g, int, int):com.bumptech.glide.d.b.k");
    }

    public String a() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
