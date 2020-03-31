package com.bumptech.glide.d.d.d;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.b.a.C0036a;
import com.bumptech.glide.b.d;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.f;
import com.bumptech.glide.d.g;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: GifResourceEncoder */
public class j implements f<b> {
    private static final a a = new a();
    private final C0036a b;
    private final c c;
    private final a d;

    /* compiled from: GifResourceEncoder */
    static class a {
        a() {
        }

        public com.bumptech.glide.b.a a(C0036a aVar) {
            return new com.bumptech.glide.b.a(aVar);
        }

        public d a() {
            return new d();
        }

        public com.bumptech.glide.c.a b() {
            return new com.bumptech.glide.c.a();
        }

        public k<Bitmap> a(Bitmap bitmap, c cVar) {
            return new com.bumptech.glide.d.d.a.c(bitmap, cVar);
        }
    }

    public j(c cVar) {
        this(cVar, a);
    }

    j(c cVar, a aVar) {
        this.c = cVar;
        this.b = new a(cVar);
        this.d = aVar;
    }

    /* JADX INFO: finally extract failed */
    public boolean a(k<b> kVar, OutputStream outputStream) {
        long a2 = com.bumptech.glide.i.d.a();
        b bVar = (b) kVar.b();
        g c2 = bVar.c();
        if (c2 instanceof com.bumptech.glide.d.d.d) {
            return a(bVar.d(), outputStream);
        }
        com.bumptech.glide.b.a a3 = a(bVar.d());
        com.bumptech.glide.c.a b2 = this.d.b();
        if (!b2.a(outputStream)) {
            return false;
        }
        int i = 0;
        while (i < a3.c()) {
            k a4 = a(a3.f(), c2, bVar);
            try {
                if (!b2.a((Bitmap) a4.b())) {
                    a4.d();
                    return false;
                }
                b2.a(a3.a(a3.d()));
                a3.a();
                a4.d();
                i++;
            } catch (Throwable th) {
                a4.d();
                throw th;
            }
        }
        boolean a5 = b2.a();
        if (!Log.isLoggable("GifEncoder", 2)) {
            return a5;
        }
        Log.v("GifEncoder", "Encoded gif with " + a3.c() + " frames and " + bVar.d().length + " bytes in " + com.bumptech.glide.i.d.a(a2) + " ms");
        return a5;
    }

    private boolean a(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("GifEncoder", 3)) {
                Log.d("GifEncoder", "Failed to write data to output stream in GifResourceEncoder", e);
            }
            return false;
        }
    }

    private com.bumptech.glide.b.a a(byte[] bArr) {
        d a2 = this.d.a();
        a2.a(bArr);
        com.bumptech.glide.b.c b2 = a2.b();
        com.bumptech.glide.b.a a3 = this.d.a(this.b);
        a3.a(b2, bArr);
        a3.a();
        return a3;
    }

    private k<Bitmap> a(Bitmap bitmap, g<Bitmap> gVar, b bVar) {
        k a2 = this.d.a(bitmap, this.c);
        k<Bitmap> a3 = gVar.a(a2, bVar.getIntrinsicWidth(), bVar.getIntrinsicHeight());
        if (!a2.equals(a3)) {
            a2.d();
        }
        return a3;
    }

    public String a() {
        return "";
    }
}
