package uk.co.senab.photoview;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

/* compiled from: Compat */
public class a {
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

    public static int a(int i) {
        if (VERSION.SDK_INT >= 11) {
            return c(i);
        }
        return b(i);
    }

    @TargetApi(5)
    private static int b(int i) {
        return (65280 & i) >> 8;
    }

    @TargetApi(11)
    private static int c(int i) {
        return (65280 & i) >> 8;
    }
}
