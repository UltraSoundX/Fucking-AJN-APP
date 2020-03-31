package com.github.chrisbanes.photoview;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

/* compiled from: Compat */
class a {
    public static void a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            b(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    @TargetApi(16)
    private static void b(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }
}
