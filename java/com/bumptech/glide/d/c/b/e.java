package com.bumptech.glide.d.c.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.n;
import java.io.InputStream;

/* compiled from: StreamResourceLoader */
public class e extends n<InputStream> implements d<Integer> {

    /* compiled from: StreamResourceLoader */
    public static class a implements m<Integer, InputStream> {
        public l<Integer, InputStream> a(Context context, c cVar) {
            return new e(context, cVar.a(Uri.class, InputStream.class));
        }

        public void a() {
        }
    }

    public e(Context context, l<Uri, InputStream> lVar) {
        super(context, lVar);
    }
}
