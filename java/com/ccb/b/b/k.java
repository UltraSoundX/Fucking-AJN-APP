package com.ccb.b.b;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class k extends j {
    public k(Context context, String str, String str2, int i, float f, Drawable drawable, Drawable drawable2) {
        super(context, str, str2, i, f, drawable, drawable2);
        setShowFloat(false);
    }

    public k(Context context, String str, String str2, int i, float f) {
        this(context, str, str2, i, f, m.a("number_normal.png"), m.a("number_press.png"));
        setShowFloat(false);
    }
}
