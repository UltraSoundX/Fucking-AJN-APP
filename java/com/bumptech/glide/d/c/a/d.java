package com.bumptech.glide.d.c.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.c.p;

/* compiled from: FileDescriptorStringLoader */
public class d extends p<ParcelFileDescriptor> implements b<String> {

    /* compiled from: FileDescriptorStringLoader */
    public static class a implements m<String, ParcelFileDescriptor> {
        public l<String, ParcelFileDescriptor> a(Context context, c cVar) {
            return new d(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public d(l<Uri, ParcelFileDescriptor> lVar) {
        super(lVar);
    }
}
