package com.lzy.imagepicker.b;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* compiled from: NavigationBarChangeListener */
public class b implements OnGlobalLayoutListener {
    private Rect a;
    private View b;
    private boolean c = false;
    private int d;
    private a e;

    /* compiled from: NavigationBarChangeListener */
    public interface a {
        void a(int i);

        void a(int i, int i2);
    }

    public b(View view, int i) {
        this.b = view;
        this.d = i;
        this.a = new Rect();
    }

    public void onGlobalLayout() {
        int i;
        int i2;
        this.a.setEmpty();
        this.b.getWindowVisibleDisplayFrame(this.a);
        if (this.d == 1) {
            i = this.b.getHeight() - (this.a.bottom - this.a.top);
        } else if (this.d == 2) {
            i = this.b.getWidth() - (this.a.right - this.a.left);
        } else {
            i = 0;
        }
        if (d.b(this.b.getContext())) {
            i2 = d.c(this.b.getContext());
        } else {
            i2 = 0;
        }
        if (i < i2 || i >= i2 * 2) {
            if (this.c && this.e != null) {
                this.e.a(this.d);
            }
            this.c = false;
            return;
        }
        if (!this.c && this.e != null) {
            this.e.a(this.d, i);
        }
        this.c = true;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public static b a(Activity activity) {
        return a(activity.findViewById(16908290), 1);
    }

    public static b a(View view, int i) {
        b bVar = new b(view, i);
        view.getViewTreeObserver().addOnGlobalLayoutListener(bVar);
        return bVar;
    }

    public static b a(Activity activity, int i) {
        return a(activity.findViewById(16908290), i);
    }
}
