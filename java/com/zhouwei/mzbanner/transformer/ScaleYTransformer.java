package com.zhouwei.mzbanner.transformer;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class ScaleYTransformer implements PageTransformer {
    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            view.setScaleY(0.9f);
        } else if (f <= 1.0f) {
            view.setScaleY(Math.max(0.9f, 1.0f - Math.abs(f)));
        } else {
            view.setScaleY(0.9f);
        }
    }
}
