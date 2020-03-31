package com.bumptech.glide.d.c;

import com.bumptech.glide.d.a.c;
import java.net.URL;

/* compiled from: UrlLoader */
public class r<T> implements l<URL, T> {
    private final l<d, T> a;

    public r(l<d, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(URL url, int i, int i2) {
        return this.a.a(new d(url), i, i2);
    }
}
