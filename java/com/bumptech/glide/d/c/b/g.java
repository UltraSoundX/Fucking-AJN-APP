package com.bumptech.glide.d.c.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.d.a.h;
import com.bumptech.glide.d.a.i;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.d;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.q;
import com.stub.StubApp;
import java.io.InputStream;

/* compiled from: StreamUriLoader */
public class g extends q<InputStream> implements d<Uri> {

    /* compiled from: StreamUriLoader */
    public static class a implements m<Uri, InputStream> {
        public l<Uri, InputStream> a(Context context, c cVar) {
            return new g(context, cVar.a(d.class, InputStream.class));
        }

        public void a() {
        }
    }

    public g(Context context, l<d, InputStream> lVar) {
        super(context, lVar);
    }

    /* access modifiers changed from: protected */
    public com.bumptech.glide.d.a.c<InputStream> a(Context context, Uri uri) {
        return new i(context, uri);
    }

    /* access modifiers changed from: protected */
    public com.bumptech.glide.d.a.c<InputStream> a(Context context, String str) {
        return new h(StubApp.getOrigApplicationContext(context.getApplicationContext()).getAssets(), str);
    }
}
