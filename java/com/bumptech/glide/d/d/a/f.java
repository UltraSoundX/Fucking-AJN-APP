package com.bumptech.glide.d.d.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.d.a.l.a;
import com.bumptech.glide.i.h;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

/* compiled from: Downsampler */
public abstract class f implements a<InputStream> {
    public static final f a = new f() {
        /* access modifiers changed from: protected */
        public int a(int i, int i2, int i3, int i4) {
            return Math.min(i2 / i4, i / i3);
        }

        public String a() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final f b = new f() {
        /* access modifiers changed from: protected */
        public int a(int i, int i2, int i3, int i4) {
            int i5 = 1;
            int ceil = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i5 = 0;
            }
            return max << i5;
        }

        public String a() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final f c = new f() {
        /* access modifiers changed from: protected */
        public int a(int i, int i2, int i3, int i4) {
            return 0;
        }

        public String a() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }
    };
    private static final Set<a> d = EnumSet.of(a.JPEG, a.PNG_A, a.PNG);
    private static final Queue<Options> e = h.a(0);

    /* access modifiers changed from: protected */
    public abstract int a(int i, int i2, int i3, int i4);

    public Bitmap a(InputStream inputStream, c cVar, int i, int i2, com.bumptech.glide.d.a aVar) {
        int i3;
        int c2;
        com.bumptech.glide.i.a a2 = com.bumptech.glide.i.a.a();
        byte[] b2 = a2.b();
        byte[] b3 = a2.b();
        Options b4 = b();
        o oVar = new o(inputStream, b3);
        com.bumptech.glide.i.c a3 = com.bumptech.glide.i.c.a(oVar);
        com.bumptech.glide.i.f fVar = new com.bumptech.glide.i.f(a3);
        try {
            a3.mark(5242880);
            try {
                c2 = new l(a3).c();
                i3 = c2;
            } catch (IOException e2) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot determine the image orientation from header", e2);
                }
                try {
                    i3 = 0;
                } catch (IOException e3) {
                    if (Log.isLoggable("Downsampler", 5)) {
                        Log.w("Downsampler", "Cannot reset the input stream", e3);
                    }
                    i3 = 0;
                }
            } finally {
                try {
                    a3.reset();
                } catch (IOException e4) {
                    if (Log.isLoggable("Downsampler", 5)) {
                        Log.w("Downsampler", "Cannot reset the input stream", e4);
                    }
                }
            }
        } catch (IOException e5) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot reset the input stream", e5);
            }
            i3 = c2;
        } catch (Throwable th) {
            a2.a(b2);
            a2.a(b3);
            a3.b();
            a(b4);
            throw th;
        }
        b4.inTempStorage = b2;
        int[] a4 = a(fVar, oVar, b4);
        int i4 = a4[0];
        int i5 = a4[1];
        Bitmap a5 = a(fVar, oVar, b4, cVar, i4, i5, a(r.a(i3), i4, i5, i, i2), aVar);
        IOException a6 = a3.a();
        if (a6 != null) {
            throw new RuntimeException(a6);
        }
        Bitmap bitmap = null;
        if (a5 != null) {
            bitmap = r.a(a5, cVar, i3);
            if (!a5.equals(bitmap) && !cVar.a(a5)) {
                a5.recycle();
            }
        }
        a2.a(b2);
        a2.a(b3);
        a3.b();
        a(b4);
        return bitmap;
    }

    private int a(int i, int i2, int i3, int i4, int i5) {
        int i6;
        if (i5 == Integer.MIN_VALUE) {
            i5 = i3;
        }
        if (i4 == Integer.MIN_VALUE) {
            i4 = i2;
        }
        if (i == 90 || i == 270) {
            i6 = a(i3, i2, i4, i5);
        } else {
            i6 = a(i2, i3, i4, i5);
        }
        return Math.max(1, i6 == 0 ? 0 : Integer.highestOneBit(i6));
    }

    private Bitmap a(com.bumptech.glide.i.f fVar, o oVar, Options options, c cVar, int i, int i2, int i3, com.bumptech.glide.d.a aVar) {
        Config a2 = a((InputStream) fVar, aVar);
        options.inSampleSize = i3;
        options.inPreferredConfig = a2;
        if ((options.inSampleSize == 1 || 19 <= VERSION.SDK_INT) && a((InputStream) fVar)) {
            a(options, cVar.b((int) Math.ceil(((double) i) / ((double) i3)), (int) Math.ceil(((double) i2) / ((double) i3)), a2));
        }
        return b(fVar, oVar, options);
    }

    private static boolean a(InputStream inputStream) {
        if (19 <= VERSION.SDK_INT) {
            return true;
        }
        inputStream.mark(1024);
        try {
            boolean contains = d.contains(new l(inputStream).b());
            try {
                return contains;
            } catch (IOException e2) {
                if (!Log.isLoggable("Downsampler", 5)) {
                    return contains;
                }
                Log.w("Downsampler", "Cannot reset the input stream", e2);
                return contains;
            }
        } catch (IOException e3) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine the image type from header", e3);
            }
            try {
            } catch (IOException e4) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e4);
                }
            }
            return false;
        } finally {
            try {
                inputStream.reset();
            } catch (IOException e5) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e5);
                }
            }
        }
    }

    private static Config a(InputStream inputStream, com.bumptech.glide.d.a aVar) {
        boolean z;
        if (aVar == com.bumptech.glide.d.a.ALWAYS_ARGB_8888 || aVar == com.bumptech.glide.d.a.PREFER_ARGB_8888 || VERSION.SDK_INT == 16) {
            return Config.ARGB_8888;
        }
        inputStream.mark(1024);
        try {
            z = new l(inputStream).a();
            try {
            } catch (IOException e2) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e2);
                }
            }
        } catch (IOException e3) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine whether the image has alpha or not from header for format " + aVar, e3);
            }
            try {
                z = false;
            } catch (IOException e4) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e4);
                }
                z = false;
            }
        } finally {
            try {
                inputStream.reset();
            } catch (IOException e5) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e5);
                }
            }
        }
        if (z) {
            return Config.ARGB_8888;
        }
        return Config.RGB_565;
    }

    public int[] a(com.bumptech.glide.i.f fVar, o oVar, Options options) {
        options.inJustDecodeBounds = true;
        b(fVar, oVar, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap b(com.bumptech.glide.i.f fVar, o oVar, Options options) {
        if (options.inJustDecodeBounds) {
            fVar.mark(5242880);
        } else {
            oVar.a();
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(fVar, null, options);
        try {
            if (options.inJustDecodeBounds) {
                fVar.reset();
            }
        } catch (IOException e2) {
            if (Log.isLoggable("Downsampler", 6)) {
                Log.e("Downsampler", "Exception loading inDecodeBounds=" + options.inJustDecodeBounds + " sample=" + options.inSampleSize, e2);
            }
        }
        return decodeStream;
    }

    @TargetApi(11)
    private static void a(Options options, Bitmap bitmap) {
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = bitmap;
        }
    }

    @TargetApi(11)
    private static synchronized Options b() {
        Options options;
        synchronized (f.class) {
            synchronized (e) {
                options = (Options) e.poll();
            }
            if (options == null) {
                options = new Options();
                b(options);
            }
        }
        return options;
    }

    private static void a(Options options) {
        b(options);
        synchronized (e) {
            e.offer(options);
        }
    }

    @TargetApi(11)
    private static void b(Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }
}
