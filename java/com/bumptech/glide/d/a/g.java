package com.bumptech.glide.d.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.k;
import com.stub.StubApp;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: LocalUriFetcher */
public abstract class g<T> implements c<T> {
    private final Uri a;
    private final Context b;
    private T c;

    /* access modifiers changed from: protected */
    public abstract void a(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    public g(Context context, Uri uri) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.a = uri;
    }

    public final T a(k kVar) throws Exception {
        this.c = b(this.a, this.b.getContentResolver());
        return this.c;
    }

    public void a() {
        if (this.c != null) {
            try {
                a(this.c);
            } catch (IOException e) {
                if (Log.isLoggable("LocalUriFetcher", 2)) {
                    Log.v("LocalUriFetcher", "failed to close data", e);
                }
            }
        }
    }

    public void c() {
    }

    public String b() {
        return this.a.toString();
    }
}
