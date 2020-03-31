package com.ccb.b.b;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

public class n extends TextView {
    private final boolean a = true;
    private final int b = Color.parseColor("#09b6f2");
    private int c = 16;

    public n(Context context, String str) {
        super(context);
        setText(str);
        getPaint().setFakeBoldText(true);
        setGravity(17);
        setTextColor(this.b);
        setTextSize((float) this.c);
        setOnTouchListener(new o(this, str));
    }

    public void setOnClickListener(String str) {
        super.setOnClickListener(new p(this));
    }
}
