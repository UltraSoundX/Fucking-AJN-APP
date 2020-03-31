package com.e23.ajn.views;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* compiled from: AndroidBug5497Workaround */
public class b {
    private View a;
    private int b;
    private LayoutParams c;

    public static void a(View view) {
        new b(view);
    }

    private b(View view) {
        if (view != null) {
            this.a = view;
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    b.this.a();
                }
            });
            this.c = this.a.getLayoutParams();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        int b2 = b();
        if (b2 != this.b) {
            this.c.height = b2;
            this.a.requestLayout();
            this.b = b2;
        }
    }

    private int b() {
        Rect rect = new Rect();
        this.a.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }
}
