package com.bumptech.glide.d.d.c;

import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.c.o;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.f.b;
import java.io.File;
import java.io.InputStream;

/* compiled from: StreamFileDataLoadProvider */
public class d implements b<InputStream, File> {
    private static final a a = new a();
    private final e<File, File> b = new a();
    private final com.bumptech.glide.d.b<InputStream> c = new o();

    /* compiled from: StreamFileDataLoadProvider */
    private static class a implements e<InputStream, File> {
        private a() {
        }

        public k<File> a(InputStream inputStream, int i, int i2) {
            throw new Error("You cannot decode a File from an InputStream by default, try either #diskCacheStratey(DiskCacheStrategy.SOURCE) to avoid this call or #decoder(ResourceDecoder) to replace this Decoder");
        }

        public String a() {
            return "";
        }
    }

    public e<File, File> a() {
        return this.b;
    }

    public e<InputStream, File> b() {
        return a;
    }

    public com.bumptech.glide.d.b<InputStream> c() {
        return this.c;
    }

    public f<File> d() {
        return com.bumptech.glide.d.d.b.b();
    }
}
