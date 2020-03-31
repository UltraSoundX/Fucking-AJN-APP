package com.bumptech.glide.d.b.b;

import android.util.Log;
import com.bumptech.glide.a.a;
import com.bumptech.glide.a.a.C0035a;
import com.bumptech.glide.d.b.b.a.b;
import com.bumptech.glide.d.c;
import java.io.File;
import java.io.IOException;

/* compiled from: DiskLruCacheWrapper */
public class e implements a {
    private static e a = null;
    private final c b = new c();
    private final j c;
    private final File d;
    private final int e;
    private a f;

    public static synchronized a a(File file, int i) {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e(file, i);
            }
            eVar = a;
        }
        return eVar;
    }

    protected e(File file, int i) {
        this.d = file;
        this.e = i;
        this.c = new j();
    }

    private synchronized a b() throws IOException {
        if (this.f == null) {
            this.f = a.a(this.d, 1, 1, (long) this.e);
        }
        return this.f;
    }

    private synchronized void c() {
        this.f = null;
    }

    public File a(c cVar) {
        try {
            a.c a2 = b().a(this.c.a(cVar));
            if (a2 != null) {
                return a2.a(0);
            }
            return null;
        } catch (IOException e2) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e2);
            return null;
        }
    }

    public void a(c cVar, b bVar) {
        C0035a b2;
        String a2 = this.c.a(cVar);
        this.b.a(cVar);
        try {
            b2 = b().b(a2);
            if (b2 != null) {
                if (bVar.a(b2.a(0))) {
                    b2.a();
                }
                b2.c();
            }
            this.b.b(cVar);
        } catch (IOException e2) {
            try {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e2);
                }
            } finally {
                this.b.b(cVar);
            }
        } catch (Throwable th) {
            b2.c();
            throw th;
        }
    }

    public void b(c cVar) {
        try {
            b().c(this.c.a(cVar));
        } catch (IOException e2) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to delete from disk cache", e2);
            }
        }
    }

    public synchronized void a() {
        try {
            b().a();
            c();
        } catch (IOException e2) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to clear disk cache", e2);
            }
        }
    }
}
