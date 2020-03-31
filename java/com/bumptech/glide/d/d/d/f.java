package com.bumptech.glide.d.d.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.e;
import com.bumptech.glide.g.b.g;
import com.bumptech.glide.g.b.j;
import com.bumptech.glide.i;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

/* compiled from: GifFrameLoader */
class f {
    private final b a;
    private final com.bumptech.glide.b.a b;
    private final Handler c;
    private boolean d;
    private boolean e;
    private e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> f;
    private a g;
    private boolean h;

    /* compiled from: GifFrameLoader */
    static class a extends g<Bitmap> {
        private final Handler a;
        /* access modifiers changed from: private */
        public final int b;
        private final long c;
        private Bitmap d;

        public a(Handler handler, int i, long j) {
            this.a = handler;
            this.b = i;
            this.c = j;
        }

        public Bitmap a() {
            return this.d;
        }

        public void a(Bitmap bitmap, com.bumptech.glide.g.a.c<? super Bitmap> cVar) {
            this.d = bitmap;
            this.a.sendMessageAtTime(this.a.obtainMessage(1, this), this.c);
        }
    }

    /* compiled from: GifFrameLoader */
    public interface b {
        void b(int i);
    }

    /* compiled from: GifFrameLoader */
    private class c implements Callback {
        private c() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                f.this.a((a) message.obj);
                return true;
            }
            if (message.what == 2) {
                i.a((j<?>) (a) message.obj);
            }
            return false;
        }
    }

    /* compiled from: GifFrameLoader */
    static class d implements com.bumptech.glide.d.c {
        private final UUID a;

        public d() {
            this(UUID.randomUUID());
        }

        d(UUID uuid) {
            this.a = uuid;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return ((d) obj).a.equals(this.a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public f(Context context, b bVar, com.bumptech.glide.b.a aVar, int i, int i2) {
        this(bVar, aVar, null, a(context, aVar, i, i2, i.a(context).a()));
    }

    f(b bVar, com.bumptech.glide.b.a aVar, Handler handler, e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> eVar) {
        this.d = false;
        this.e = false;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper(), new c());
        }
        this.a = bVar;
        this.b = aVar;
        this.c = handler;
        this.f = eVar;
    }

    public void a(com.bumptech.glide.d.g<Bitmap> gVar) {
        if (gVar == null) {
            throw new NullPointerException("Transformation must not be null");
        }
        this.f = this.f.b((com.bumptech.glide.d.g<ResourceType>[]) new com.bumptech.glide.d.g[]{gVar});
    }

    public void a() {
        if (!this.d) {
            this.d = true;
            this.h = false;
            e();
        }
    }

    public void b() {
        this.d = false;
    }

    public void c() {
        b();
        if (this.g != null) {
            i.a((j<?>) this.g);
            this.g = null;
        }
        this.h = true;
    }

    public Bitmap d() {
        if (this.g != null) {
            return this.g.a();
        }
        return null;
    }

    private void e() {
        if (this.d && !this.e) {
            this.e = true;
            this.b.a();
            this.f.b((com.bumptech.glide.d.c) new d()).a(new a(this.c, this.b.d(), SystemClock.uptimeMillis() + ((long) this.b.b())));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar) {
        if (this.h) {
            this.c.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        a aVar2 = this.g;
        this.g = aVar;
        this.a.b(aVar.b);
        if (aVar2 != null) {
            this.c.obtainMessage(2, aVar2).sendToTarget();
        }
        this.e = false;
        e();
    }

    private static e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> a(Context context, com.bumptech.glide.b.a aVar, int i, int i2, com.bumptech.glide.d.b.a.c cVar) {
        h hVar = new h(cVar);
        g gVar = new g();
        return i.b(context).a(gVar, com.bumptech.glide.b.a.class).a(aVar).a(Bitmap.class).b(com.bumptech.glide.d.d.a.b()).b((com.bumptech.glide.d.e<DataType, ResourceType>) hVar).b(true).b(com.bumptech.glide.d.b.b.NONE).b(i, i2);
    }
}
