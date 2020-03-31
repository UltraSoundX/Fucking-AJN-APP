package com.bumptech.glide.d.d.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.b.a.C0036a;
import com.bumptech.glide.b.d;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.e;
import com.bumptech.glide.i.h;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* compiled from: GifResourceDecoder */
public class i implements e<InputStream, b> {
    private static final b a = new b();
    private static final a b = new a();
    private final Context c;
    private final b d;
    private final c e;
    private final a f;
    private final a g;

    /* compiled from: GifResourceDecoder */
    static class a {
        private final Queue<com.bumptech.glide.b.a> a = h.a(0);

        a() {
        }

        public synchronized com.bumptech.glide.b.a a(C0036a aVar) {
            com.bumptech.glide.b.a aVar2;
            aVar2 = (com.bumptech.glide.b.a) this.a.poll();
            if (aVar2 == null) {
                aVar2 = new com.bumptech.glide.b.a(aVar);
            }
            return aVar2;
        }

        public synchronized void a(com.bumptech.glide.b.a aVar) {
            aVar.g();
            this.a.offer(aVar);
        }
    }

    /* compiled from: GifResourceDecoder */
    static class b {
        private final Queue<d> a = h.a(0);

        b() {
        }

        public synchronized d a(byte[] bArr) {
            d dVar;
            dVar = (d) this.a.poll();
            if (dVar == null) {
                dVar = new d();
            }
            return dVar.a(bArr);
        }

        public synchronized void a(d dVar) {
            dVar.a();
            this.a.offer(dVar);
        }
    }

    public i(Context context, c cVar) {
        this(context, cVar, a, b);
    }

    i(Context context, c cVar, b bVar, a aVar) {
        this.c = context;
        this.e = cVar;
        this.f = aVar;
        this.g = new a(cVar);
        this.d = bVar;
    }

    public d a(InputStream inputStream, int i, int i2) {
        byte[] a2 = a(inputStream);
        d a3 = this.d.a(a2);
        com.bumptech.glide.b.a a4 = this.f.a((C0036a) this.g);
        try {
            d a5 = a(a2, i, i2, a3, a4);
            return a5;
        } finally {
            this.d.a(a3);
            this.f.a(a4);
        }
    }

    private d a(byte[] bArr, int i, int i2, d dVar, com.bumptech.glide.b.a aVar) {
        com.bumptech.glide.b.c b2 = dVar.b();
        if (b2.a() <= 0 || b2.b() != 0) {
            return null;
        }
        Bitmap a2 = a(aVar, b2, bArr);
        if (a2 == null) {
            return null;
        }
        return new d(new b(this.c, this.g, this.e, com.bumptech.glide.d.d.d.b(), i, i2, b2, bArr, a2));
    }

    private Bitmap a(com.bumptech.glide.b.a aVar, com.bumptech.glide.b.c cVar, byte[] bArr) {
        aVar.a(cVar, bArr);
        aVar.a();
        return aVar.f();
    }

    public String a() {
        return "";
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e2) {
            Log.w("GifResourceDecoder", "Error reading data from stream", e2);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
