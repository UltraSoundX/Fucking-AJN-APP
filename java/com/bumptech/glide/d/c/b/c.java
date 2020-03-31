package com.bumptech.glide.d.c.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.d.c.b;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import java.io.File;
import java.io.InputStream;

/* compiled from: StreamFileLoader */
public class c extends b<InputStream> implements d<File> {

    /* compiled from: StreamFileLoader */
    public static class a implements m<File, InputStream> {
        public l<File, InputStream> a(Context context, com.bumptech.glide.d.c.c cVar) {
            return new c(cVar.a(Uri.class, InputStream.class));
        }

        public void a() {
        }
    }

    public c(l<Uri, InputStream> lVar) {
        super(lVar);
    }
}
