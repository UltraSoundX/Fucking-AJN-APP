package com.bumptech.glide.d.c.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.p;
import java.io.InputStream;

/* compiled from: StreamStringLoader */
public class f extends p<InputStream> implements d<String> {

    /* compiled from: StreamStringLoader */
    public static class a implements m<String, InputStream> {
        public l<String, InputStream> a(Context context, c cVar) {
            return new f(cVar.a(Uri.class, InputStream.class));
        }

        public void a() {
        }
    }

    public f(l<Uri, InputStream> lVar) {
        super(lVar);
    }
}
