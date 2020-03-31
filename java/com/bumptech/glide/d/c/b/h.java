package com.bumptech.glide.d.c.b;

import android.content.Context;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.d;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.r;
import java.io.InputStream;
import java.net.URL;

/* compiled from: StreamUrlLoader */
public class h extends r<InputStream> {

    /* compiled from: StreamUrlLoader */
    public static class a implements m<URL, InputStream> {
        public l<URL, InputStream> a(Context context, c cVar) {
            return new h(cVar.a(d.class, InputStream.class));
        }

        public void a() {
        }
    }

    public h(l<d, InputStream> lVar) {
        super(lVar);
    }
}
