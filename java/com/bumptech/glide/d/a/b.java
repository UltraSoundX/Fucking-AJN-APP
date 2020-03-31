package com.bumptech.glide.d.a;

import com.bumptech.glide.k;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: ByteArrayFetcher */
public class b implements c<InputStream> {
    private final byte[] a;
    private final String b;

    public b(byte[] bArr, String str) {
        this.a = bArr;
        this.b = str;
    }

    /* renamed from: b */
    public InputStream a(k kVar) {
        return new ByteArrayInputStream(this.a);
    }

    public void a() {
    }

    public String b() {
        return this.b;
    }

    public void c() {
    }
}
