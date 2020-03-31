package com.bumptech.glide.d.c.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.n;

/* compiled from: FileDescriptorResourceLoader */
public class c extends n<ParcelFileDescriptor> implements b<Integer> {

    /* compiled from: FileDescriptorResourceLoader */
    public static class a implements m<Integer, ParcelFileDescriptor> {
        public l<Integer, ParcelFileDescriptor> a(Context context, com.bumptech.glide.d.c.c cVar) {
            return new c(context, cVar.a(Uri.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public c(Context context, l<Uri, ParcelFileDescriptor> lVar) {
        super(context, lVar);
    }
}
