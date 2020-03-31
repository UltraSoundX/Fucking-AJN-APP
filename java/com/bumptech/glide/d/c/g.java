package com.bumptech.glide.d.c;

import android.os.ParcelFileDescriptor;
import java.io.InputStream;

/* compiled from: ImageVideoWrapper */
public class g {
    private final InputStream a;
    private final ParcelFileDescriptor b;

    public g(InputStream inputStream, ParcelFileDescriptor parcelFileDescriptor) {
        this.a = inputStream;
        this.b = parcelFileDescriptor;
    }

    public InputStream a() {
        return this.a;
    }

    public ParcelFileDescriptor b() {
        return this.b;
    }
}
