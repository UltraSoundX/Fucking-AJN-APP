package com.bumptech.glide.d.b.b;

import android.content.Context;
import com.bumptech.glide.d.b.b.d.a;
import java.io.File;

/* compiled from: InternalCacheDiskCacheFactory */
public final class f extends d {
    public f(Context context) {
        this(context, "image_manager_disk_cache", 262144000);
    }

    public f(final Context context, final String str, int i) {
        super(new a() {
            public File a() {
                File cacheDir = context.getCacheDir();
                if (cacheDir == null) {
                    return null;
                }
                return str != null ? new File(cacheDir, str) : cacheDir;
            }
        }, i);
    }
}
