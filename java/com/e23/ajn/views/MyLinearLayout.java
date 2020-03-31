package com.e23.ajn.views;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowInsets;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    private int[] a = new int[4];

    public MyLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public final boolean fitSystemWindows(Rect rect) {
        if (VERSION.SDK_INT >= 19) {
            this.a[0] = rect.left;
            this.a[1] = rect.top;
            this.a[2] = rect.right;
            rect.left = 0;
            rect.top = 0;
            rect.right = 0;
        }
        return super.fitSystemWindows(rect);
    }

    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (VERSION.SDK_INT < 20) {
            return windowInsets;
        }
        this.a[0] = windowInsets.getSystemWindowInsetLeft();
        Log.e("mInsets[0]", "" + this.a[0]);
        this.a[1] = windowInsets.getSystemWindowInsetTop();
        Log.e("mInsets[1]", "" + this.a[1]);
        this.a[2] = windowInsets.getSystemWindowInsetRight();
        Log.e("mInsets[2]", "" + this.a[2]);
        return super.onApplyWindowInsets(windowInsets.replaceSystemWindowInsets(0, 0, 0, windowInsets.getSystemWindowInsetBottom()));
    }
}
