package android.support.v7.widget;

import android.support.v4.widget.ExploreByTouchHelper;

/* compiled from: RtlSpacingHelper */
class aj {
    private int a = 0;
    private int b = 0;
    private int c = ExploreByTouchHelper.INVALID_ID;
    private int d = ExploreByTouchHelper.INVALID_ID;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    aj() {
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.g ? this.b : this.a;
    }

    public int d() {
        return this.g ? this.a : this.b;
    }

    public void a(int i, int i2) {
        this.c = i;
        this.d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.b = i2;
        }
    }

    public void b(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.b = i2;
        }
    }

    public void a(boolean z) {
        if (z != this.g) {
            this.g = z;
            if (!this.h) {
                this.a = this.e;
                this.b = this.f;
            } else if (z) {
                this.a = this.d != Integer.MIN_VALUE ? this.d : this.e;
                this.b = this.c != Integer.MIN_VALUE ? this.c : this.f;
            } else {
                this.a = this.c != Integer.MIN_VALUE ? this.c : this.e;
                this.b = this.d != Integer.MIN_VALUE ? this.d : this.f;
            }
        }
    }
}
