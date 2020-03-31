package com.bumptech.glide.d.c;

import android.net.Uri;
import com.bumptech.glide.d.a.c;
import java.io.File;

/* compiled from: FileLoader */
public class b<T> implements l<File, T> {
    private final l<Uri, T> a;

    public b(l<Uri, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(File file, int i, int i2) {
        return this.a.a(Uri.fromFile(file), i, i2);
    }
}
