package com.bumptech.glide.d.c;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.b;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ImageVideoWrapperEncoder */
public class h implements b<g> {
    private final b<InputStream> a;
    private final b<ParcelFileDescriptor> b;
    private String c;

    public h(b<InputStream> bVar, b<ParcelFileDescriptor> bVar2) {
        this.a = bVar;
        this.b = bVar2;
    }

    public boolean a(g gVar, OutputStream outputStream) {
        if (gVar.a() != null) {
            return this.a.a(gVar.a(), outputStream);
        }
        return this.b.a(gVar.b(), outputStream);
    }

    public String a() {
        if (this.c == null) {
            this.c = this.a.a() + this.b.a();
        }
        return this.c;
    }
}
