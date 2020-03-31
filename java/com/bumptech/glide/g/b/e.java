package com.bumptech.glide.g.b;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.g.a.c.a;

/* compiled from: ImageViewTarget */
public abstract class e<Z> extends k<ImageView, Z> implements a {
    /* access modifiers changed from: protected */
    public abstract void a(Z z);

    public e(ImageView imageView) {
        super(imageView);
    }

    public Drawable b() {
        return ((ImageView) this.a).getDrawable();
    }

    public void a(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void c(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void a(Exception exc, Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void b(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void a(Z z, c<? super Z> cVar) {
        if (cVar == null || !cVar.a(z, this)) {
            a(z);
        }
    }
}
