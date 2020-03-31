package com.zhouwei.mzbanner.transformer;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class CoverModeTransformer implements PageTransformer {
    private float a = 0.0f;
    private float b = 0.0f;
    private float c = 0.0f;
    private int d;
    private float e = 1.0f;
    private float f = 0.9f;
    private ViewPager g;

    public CoverModeTransformer(ViewPager viewPager) {
        this.g = viewPager;
    }

    public void transformPage(View view, float f2) {
        if (this.c == 0.0f) {
            float paddingLeft = (float) this.g.getPaddingLeft();
            this.c = paddingLeft / ((((float) this.g.getMeasuredWidth()) - paddingLeft) - ((float) this.g.getPaddingRight()));
        }
        float f3 = f2 - this.c;
        if (this.b == 0.0f) {
            this.b = (float) view.getWidth();
            this.a = (((2.0f - this.e) - this.f) * this.b) / 2.0f;
        }
        if (f3 <= -1.0f) {
            view.setTranslationX(this.a + ((float) this.d));
            view.setScaleX(this.f);
            view.setScaleY(this.f);
        } else if (((double) f3) <= 1.0d) {
            float abs = (this.e - this.f) * Math.abs(1.0f - Math.abs(f3));
            float f4 = (-this.a) * f3;
            if (((double) f3) <= -0.5d) {
                view.setTranslationX(((Math.abs(Math.abs(f3) - 0.5f) * ((float) this.d)) / 0.5f) + f4);
            } else if (f3 <= 0.0f) {
                view.setTranslationX(f4);
            } else if (((double) f3) >= 0.5d) {
                view.setTranslationX(f4 - ((Math.abs(Math.abs(f3) - 0.5f) * ((float) this.d)) / 0.5f));
            } else {
                view.setTranslationX(f4);
            }
            view.setScaleX(this.f + abs);
            view.setScaleY(this.f + abs);
        } else {
            view.setScaleX(this.f);
            view.setScaleY(this.f);
            view.setTranslationX((-this.a) - ((float) this.d));
        }
    }
}
