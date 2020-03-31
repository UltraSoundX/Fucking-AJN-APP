package com.bumptech.glide.d.c;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.d.a.c;

/* compiled from: UriLoader */
public abstract class q<T> implements l<Uri, T> {
    private final Context a;
    private final l<d, T> b;

    /* access modifiers changed from: protected */
    public abstract c<T> a(Context context, Uri uri);

    /* access modifiers changed from: protected */
    public abstract c<T> a(Context context, String str);

    public q(Context context, l<d, T> lVar) {
        this.a = context;
        this.b = lVar;
    }

    public final c<T> a(Uri uri, int i, int i2) {
        String scheme = uri.getScheme();
        if (a(scheme)) {
            if (!a.a(uri)) {
                return a(this.a, uri);
            }
            return a(this.a, a.b(uri));
        } else if (this.b == null) {
            return null;
        } else {
            if ("http".equals(scheme) || "https".equals(scheme)) {
                return this.b.a(new d(uri.toString()), i, i2);
            }
            return null;
        }
    }

    private static boolean a(String str) {
        return "file".equals(str) || "content".equals(str) || "android.resource".equals(str);
    }
}
