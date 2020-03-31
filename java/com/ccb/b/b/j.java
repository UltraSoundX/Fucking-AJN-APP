package com.ccb.b.b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class j extends b {
    public j(Context context, String str, String str2, int i, float f, Drawable drawable, Drawable drawable2) {
        super(context, str, str2, i, f, drawable, drawable2);
        setShowFloat(false);
        a(-16777216, -1);
    }

    public j(Context context, String str, String str2, int i, float f) {
        this(context, str, str2, i, f, m.a("letter_normal.png"), m.a("letter_press.png"));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
