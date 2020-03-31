package com.ccb.b.b;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class i extends LinearLayout {
    private LinearLayout a;

    public i(Context context) {
        this(context, 0.0f);
    }

    public i(Context context, float f) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.weight = 1.0f;
        layoutParams.setMargins(0, 1, 0, 1);
        setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        addView(linearLayout, layoutParams2);
        this.a = new LinearLayout(context);
        LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.weight = f;
        addView(this.a, layoutParams3);
        addView(new LinearLayout(context), layoutParams2);
        if (VERSION.SDK_INT > 10) {
            this.a.setMotionEventSplittingEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void a(h hVar) {
        this.a.addView(hVar);
    }

    public void a(b bVar) {
        this.a.addView(bVar);
    }
}
