package com.nanchen.compresshelper;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import java.io.File;

/* compiled from: CompressHelper */
public class b {
    private static volatile b a;
    private Context b;
    private float c = 720.0f;
    private float d = 960.0f;
    private CompressFormat e = CompressFormat.JPEG;
    private Config f = Config.ARGB_8888;
    private int g = 80;
    private String h;
    private String i;
    private String j;

    public static b a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    private b(Context context) {
        this.b = context;
        this.h = context.getCacheDir().getPath() + File.pathSeparator + "CompressHelper";
    }

    public File a(File file) {
        return a.a(this.b, Uri.fromFile(file), this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
    }
}
