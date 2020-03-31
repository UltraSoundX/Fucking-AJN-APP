package com.e23.ajn.d;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebStorage;
import com.bumptech.glide.i;
import java.io.File;

/* compiled from: GlideCacheUtil */
public class j {
    private static j a;

    public static j a() {
        if (a == null) {
            a = new j();
        }
        return a;
    }

    public void a(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    public void run() {
                        i.a(context).i();
                    }
                }).start();
            } else {
                i.a(context).i();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                i.a(context).h();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c(Context context) {
        WebStorage.getInstance().deleteAllData();
        a(context);
        b(context);
        a(context.getExternalCacheDir() + "image_manager_disk_cache", true);
    }

    public long d(Context context) {
        try {
            return a(new File(context.getCacheDir() + "/" + "image_manager_disk_cache"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private long a(File file) throws Exception {
        long j;
        Exception e;
        long length;
        try {
            File[] listFiles = file.listFiles();
            int length2 = listFiles.length;
            j = 0;
            int i = 0;
            while (i < length2) {
                try {
                    File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        length = a(file2);
                    } else {
                        length = file2.length();
                    }
                    j += length;
                    i++;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return j;
                }
            }
        } catch (Exception e3) {
            Exception exc = e3;
            j = 0;
            e = exc;
            e.printStackTrace();
            return j;
        }
        return j;
    }

    private void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    for (File absolutePath : file.listFiles()) {
                        a(absolutePath.getAbsolutePath(), true);
                    }
                }
                if (!z) {
                    return;
                }
                if (!file.isDirectory()) {
                    file.delete();
                } else if (file.listFiles().length == 0) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
