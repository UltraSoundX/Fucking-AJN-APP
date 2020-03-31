package me.yokeyword.fragmentation.b;

import android.support.v4.app.FragmentManager;

/* compiled from: Action */
public abstract class a {
    public FragmentManager h;
    public int i;
    public long j;

    public abstract void a();

    public a() {
        this.i = 0;
        this.j = 0;
    }

    public a(int i2) {
        this.i = 0;
        this.j = 0;
        this.i = i2;
    }

    public a(int i2, FragmentManager fragmentManager) {
        this(i2);
        this.h = fragmentManager;
    }
}
