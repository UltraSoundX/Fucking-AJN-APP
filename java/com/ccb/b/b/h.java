package com.ccb.b.b;

import android.content.Context;
import android.os.Build.VERSION;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class h extends LinearLayout {
    public h(Context context, float f) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.weight = f;
        setLayoutParams(layoutParams);
        setOrientation(1);
        if (VERSION.SDK_INT > 10) {
            setMotionEventSplittingEnabled(false);
        }
    }

    public void a(i iVar) {
        addView(iVar);
    }
}
