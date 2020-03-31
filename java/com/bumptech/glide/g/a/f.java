package com.bumptech.glide.g.a;

import android.view.View;
import android.view.animation.Animation;

/* compiled from: ViewAnimation */
public class f<R> implements c<R> {
    private final a a;

    /* compiled from: ViewAnimation */
    interface a {
        Animation a();
    }

    f(a aVar) {
        this.a = aVar;
    }

    public boolean a(R r, com.bumptech.glide.g.a.c.a aVar) {
        View a2 = aVar.a();
        if (a2 != null) {
            a2.clearAnimation();
            a2.startAnimation(this.a.a());
        }
        return false;
    }
}
