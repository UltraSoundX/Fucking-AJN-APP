package com.ccb.b.b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class a extends b {
    public a(Context context, String str, String str2, int i, float f, Drawable drawable, Drawable drawable2) {
        super(context, str, str2, i, f, drawable, drawable2);
        setShowFloat(false);
    }

    public a(Context context, String str, int i, int i2) {
        super(context, str, i, i2);
        setShowFloat(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
