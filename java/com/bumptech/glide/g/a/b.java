package com.bumptech.glide.g.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.g.a.c.a;

/* compiled from: DrawableCrossFadeViewAnimation */
public class b<T extends Drawable> implements c<T> {
    private final c<T> a;
    private final int b;

    public b(c<T> cVar, int i) {
        this.a = cVar;
        this.b = i;
    }

    public boolean a(T t, a aVar) {
        Drawable b2 = aVar.b();
        if (b2 != null) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{b2, t});
            transitionDrawable.setCrossFadeEnabled(true);
            transitionDrawable.startTransition(this.b);
            aVar.a(transitionDrawable);
            return true;
        }
        this.a.a(t, aVar);
        return false;
    }
}
