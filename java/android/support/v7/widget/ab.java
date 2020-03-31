package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;

/* compiled from: LayoutState */
class ab {
    boolean a = true;
    int b;
    int c;
    int d;
    int e;
    int f = 0;
    int g = 0;
    boolean h;
    boolean i;

    ab() {
    }

    /* access modifiers changed from: 0000 */
    public boolean a(s sVar) {
        return this.c >= 0 && this.c < sVar.e();
    }

    /* access modifiers changed from: 0000 */
    public View a(o oVar) {
        View c2 = oVar.c(this.c);
        this.c += this.d;
        return c2;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.b + ", mCurrentPosition=" + this.c + ", mItemDirection=" + this.d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
