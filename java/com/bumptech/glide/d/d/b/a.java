package com.bumptech.glide.d.d.b;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.d.b.k;

/* compiled from: DrawableResource */
public abstract class a<T extends Drawable> implements k<T> {
    protected final T a;

    public a(T t) {
        if (t == null) {
            throw new NullPointerException("Drawable must not be null!");
        }
        this.a = t;
    }

    /* renamed from: a */
    public final T b() {
        return this.a.getConstantState().newDrawable();
    }
}
