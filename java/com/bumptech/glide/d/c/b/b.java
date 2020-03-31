package com.bumptech.glide.d.c.b;

import android.content.Context;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import java.io.InputStream;

/* compiled from: StreamByteArrayLoader */
public class b implements d<byte[]> {
    private final String a;

    /* compiled from: StreamByteArrayLoader */
    public static class a implements m<byte[], InputStream> {
        public l<byte[], InputStream> a(Context context, c cVar) {
            return new b();
        }

        public void a() {
        }
    }

    public b() {
        this("");
    }

    @Deprecated
    public b(String str) {
        this.a = str;
    }

    public com.bumptech.glide.d.a.c<InputStream> a(byte[] bArr, int i, int i2) {
        return new com.bumptech.glide.d.a.b(bArr, this.a);
    }
}
