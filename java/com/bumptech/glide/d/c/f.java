package com.bumptech.glide.d.c;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.a.c;
import java.io.InputStream;

/* compiled from: ImageVideoModelLoader */
public class f<A> implements l<A, g> {
    private final l<A, InputStream> a;
    private final l<A, ParcelFileDescriptor> b;

    /* compiled from: ImageVideoModelLoader */
    static class a implements c<g> {
        private final c<InputStream> a;
        private final c<ParcelFileDescriptor> b;

        public a(c<InputStream> cVar, c<ParcelFileDescriptor> cVar2) {
            this.a = cVar;
            this.b = cVar2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x0013 A[SYNTHETIC, Splitter:B:7:0x0013] */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.d.c.g a(com.bumptech.glide.k r6) throws java.lang.Exception {
            /*
                r5 = this;
                r2 = 0
                r4 = 2
                com.bumptech.glide.d.a.c<java.io.InputStream> r0 = r5.a
                if (r0 == 0) goto L_0x004a
                com.bumptech.glide.d.a.c<java.io.InputStream> r0 = r5.a     // Catch:{ Exception -> 0x0022 }
                java.lang.Object r0 = r0.a(r6)     // Catch:{ Exception -> 0x0022 }
                java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ Exception -> 0x0022 }
                r1 = r0
            L_0x000f:
                com.bumptech.glide.d.a.c<android.os.ParcelFileDescriptor> r0 = r5.b
                if (r0 == 0) goto L_0x001c
                com.bumptech.glide.d.a.c<android.os.ParcelFileDescriptor> r0 = r5.b     // Catch:{ Exception -> 0x0037 }
                java.lang.Object r0 = r0.a(r6)     // Catch:{ Exception -> 0x0037 }
                android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0     // Catch:{ Exception -> 0x0037 }
                r2 = r0
            L_0x001c:
                com.bumptech.glide.d.c.g r0 = new com.bumptech.glide.d.c.g
                r0.<init>(r1, r2)
                return r0
            L_0x0022:
                r0 = move-exception
                java.lang.String r1 = "IVML"
                boolean r1 = android.util.Log.isLoggable(r1, r4)
                if (r1 == 0) goto L_0x0032
                java.lang.String r1 = "IVML"
                java.lang.String r3 = "Exception fetching input stream, trying ParcelFileDescriptor"
                android.util.Log.v(r1, r3, r0)
            L_0x0032:
                com.bumptech.glide.d.a.c<android.os.ParcelFileDescriptor> r1 = r5.b
                if (r1 != 0) goto L_0x004a
                throw r0
            L_0x0037:
                r0 = move-exception
                java.lang.String r3 = "IVML"
                boolean r3 = android.util.Log.isLoggable(r3, r4)
                if (r3 == 0) goto L_0x0047
                java.lang.String r3 = "IVML"
                java.lang.String r4 = "Exception fetching ParcelFileDescriptor"
                android.util.Log.v(r3, r4, r0)
            L_0x0047:
                if (r1 != 0) goto L_0x001c
                throw r0
            L_0x004a:
                r1 = r2
                goto L_0x000f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.c.f.a.a(com.bumptech.glide.k):com.bumptech.glide.d.c.g");
        }

        public void a() {
            if (this.a != null) {
                this.a.a();
            }
            if (this.b != null) {
                this.b.a();
            }
        }

        public String b() {
            if (this.a != null) {
                return this.a.b();
            }
            return this.b.b();
        }

        public void c() {
            if (this.a != null) {
                this.a.c();
            }
            if (this.b != null) {
                this.b.c();
            }
        }
    }

    public f(l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2) {
        if (lVar == null && lVar2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.a = lVar;
        this.b = lVar2;
    }

    public c<g> a(A a2, int i, int i2) {
        c cVar;
        c cVar2;
        if (this.a != null) {
            cVar = this.a.a(a2, i, i2);
        } else {
            cVar = null;
        }
        if (this.b != null) {
            cVar2 = this.b.a(a2, i, i2);
        } else {
            cVar2 = null;
        }
        if (cVar == null && cVar2 == null) {
            return null;
        }
        return new a(cVar, cVar2);
    }
}
