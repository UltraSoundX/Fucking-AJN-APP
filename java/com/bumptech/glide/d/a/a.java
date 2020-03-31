package com.bumptech.glide.d.a;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.k;
import java.io.IOException;

/* compiled from: AssetPathFetcher */
public abstract class a<T> implements c<T> {
    private final String a;
    private final AssetManager b;
    private T c;

    /* access modifiers changed from: protected */
    public abstract T a(AssetManager assetManager, String str) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void a(T t) throws IOException;

    public a(AssetManager assetManager, String str) {
        this.b = assetManager;
        this.a = str;
    }

    public T a(k kVar) throws Exception {
        this.c = a(this.b, this.a);
        return this.c;
    }

    public void a() {
        if (this.c != null) {
            try {
                a(this.c);
            } catch (IOException e) {
                if (Log.isLoggable("AssetUriFetcher", 2)) {
                    Log.v("AssetUriFetcher", "Failed to close data", e);
                }
            }
        }
    }

    public String b() {
        return this.a;
    }

    public void c() {
    }
}
