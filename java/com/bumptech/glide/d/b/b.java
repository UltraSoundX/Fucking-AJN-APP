package com.bumptech.glide.d.b;

/* compiled from: DiskCacheStrategy */
public enum b {
    ALL(true, true),
    NONE(false, false),
    SOURCE(true, false),
    RESULT(false, true);
    
    private final boolean e;
    private final boolean f;

    private b(boolean z, boolean z2) {
        this.e = z;
        this.f = z2;
    }

    public boolean a() {
        return this.e;
    }

    public boolean b() {
        return this.f;
    }
}
