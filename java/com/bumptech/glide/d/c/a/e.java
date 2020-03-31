package com.bumptech.glide.d.c.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.d;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.q;
import com.stub.StubApp;

/* compiled from: FileDescriptorUriLoader */
public class e extends q<ParcelFileDescriptor> implements b<Uri> {

    /* compiled from: FileDescriptorUriLoader */
    public static class a implements m<Uri, ParcelFileDescriptor> {
        public l<Uri, ParcelFileDescriptor> a(Context context, c cVar) {
            return new e(context, cVar.a(d.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public e(Context context, l<d, ParcelFileDescriptor> lVar) {
        super(context, lVar);
    }

    /* access modifiers changed from: protected */
    public com.bumptech.glide.d.a.c<ParcelFileDescriptor> a(Context context, Uri uri) {
        return new com.bumptech.glide.d.a.e(context, uri);
    }

    /* access modifiers changed from: protected */
    public com.bumptech.glide.d.a.c<ParcelFileDescriptor> a(Context context, String str) {
        return new com.bumptech.glide.d.a.d(StubApp.getOrigApplicationContext(context.getApplicationContext()).getAssets(), str);
    }
}
