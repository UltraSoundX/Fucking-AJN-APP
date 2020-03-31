package com.lzy.imagepicker.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;

/* compiled from: GridSpacingItemDecoration */
public class b extends h {
    private int a;
    private int b;
    private boolean c;

    public b(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
    }

    public void a(Rect rect, View view, RecyclerView recyclerView, s sVar) {
        int f = recyclerView.f(view);
        int i = f % this.a;
        if (this.c) {
            rect.left = this.b - ((this.b * i) / this.a);
            rect.right = ((i + 1) * this.b) / this.a;
            if (f < this.a) {
                rect.top = this.b;
            }
            rect.bottom = this.b;
            return;
        }
        rect.left = (this.b * i) / this.a;
        rect.right = this.b - (((i + 1) * this.b) / this.a);
        if (f >= this.a) {
            rect.top = this.b;
        }
    }
}
