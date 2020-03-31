package com.e23.ajn.views.expandableselector.a;

import android.view.View;

/* compiled from: ExpandableSelectorAnimator */
public class a {
    private final View a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private boolean f = true;
    private boolean g;

    public a(View view, int i, int i2, int i3, int i4) {
        this.a = view;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public boolean a() {
        return this.f;
    }

    public boolean b() {
        return !a();
    }

    public void a(boolean z) {
        this.g = z;
    }
}
