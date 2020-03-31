package com.zhouwei.mzbanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;

public class CustomViewPager extends ViewPager {
    private ArrayList<Integer> a = new ArrayList<>();
    private SparseArray<Integer> b = new SparseArray<>();

    public CustomViewPager(Context context) {
        super(context);
        a();
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setClipToPadding(false);
        setOverScrollMode(2);
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        if (i2 == 0 || this.b.size() != i) {
            this.a.clear();
            this.b.clear();
            int a2 = a(this);
            for (int i3 = 0; i3 < i; i3++) {
                int abs = Math.abs(a2 - a(getChildAt(i3)));
                if (this.b.get(abs) != null) {
                    abs++;
                }
                this.a.add(Integer.valueOf(abs));
                this.b.append(abs, Integer.valueOf(i3));
            }
            Collections.sort(this.a);
        }
        return ((Integer) this.b.get(((Integer) this.a.get((i - 1) - i2)).intValue())).intValue();
    }

    private int a(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[0] + (view.getWidth() / 2);
    }
}
