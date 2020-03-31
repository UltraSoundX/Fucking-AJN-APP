package com.bumptech.glide.d.d.a;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.b.a.c;
import java.io.IOException;

/* compiled from: VideoBitmapDecoder */
public class s implements a<ParcelFileDescriptor> {
    private static final a a = new a();
    private a b;
    private int c;

    /* compiled from: VideoBitmapDecoder */
    static class a {
        a() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    public s() {
        this(a, -1);
    }

    s(a aVar, int i) {
        this.b = aVar;
        this.c = i;
    }

    public Bitmap a(ParcelFileDescriptor parcelFileDescriptor, c cVar, int i, int i2, com.bumptech.glide.d.a aVar) throws IOException {
        Bitmap frameAtTime;
        MediaMetadataRetriever a2 = this.b.a();
        a2.setDataSource(parcelFileDescriptor.getFileDescriptor());
        if (this.c >= 0) {
            frameAtTime = a2.getFrameAtTime((long) this.c);
        } else {
            frameAtTime = a2.getFrameAtTime();
        }
        a2.release();
        parcelFileDescriptor.close();
        return frameAtTime;
    }

    public String a() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
