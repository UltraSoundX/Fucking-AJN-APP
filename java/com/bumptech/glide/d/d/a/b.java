package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.f;
import com.bumptech.glide.i.d;
import com.bumptech.glide.i.h;
import java.io.OutputStream;

/* compiled from: BitmapEncoder */
public class b implements f<Bitmap> {
    private CompressFormat a;
    private int b;

    public b() {
        this(null, 90);
    }

    public b(CompressFormat compressFormat, int i) {
        this.a = compressFormat;
        this.b = i;
    }

    public boolean a(k<Bitmap> kVar, OutputStream outputStream) {
        Bitmap bitmap = (Bitmap) kVar.b();
        long a2 = d.a();
        CompressFormat a3 = a(bitmap);
        bitmap.compress(a3, this.b, outputStream);
        if (Log.isLoggable("BitmapEncoder", 2)) {
            Log.v("BitmapEncoder", "Compressed with type: " + a3 + " of size " + h.a(bitmap) + " in " + d.a(a2));
        }
        return true;
    }

    public String a() {
        return "BitmapEncoder.com.bumptech.glide.load.resource.bitmap";
    }

    private CompressFormat a(Bitmap bitmap) {
        if (this.a != null) {
            return this.a;
        }
        if (bitmap.hasAlpha()) {
            return CompressFormat.PNG;
        }
        return CompressFormat.JPEG;
    }
}
