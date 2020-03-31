package com.bumptech.glide.d.c.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.c.b;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import java.io.File;

/* compiled from: FileDescriptorFileLoader */
public class a extends b<ParcelFileDescriptor> implements b<File> {

    /* renamed from: com.bumptech.glide.d.c.a.a$a reason: collision with other inner class name */
    /* compiled from: FileDescriptorFileLoader */
    public static class C0042a implements m<File, ParcelFileDescriptor> {
        public l<File, ParcelFileDescriptor> a(Context context, c cVar) {
            return new a(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public a(l<Uri, ParcelFileDescriptor> lVar) {
        super(lVar);
    }
}
