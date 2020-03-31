package me.yokeyword.fragmentation_swipeback.a;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import me.yokeyword.fragmentation.SwipeBackLayout;

/* compiled from: SwipeBackFragmentDelegate */
public class d {
    private Fragment a;
    private me.yokeyword.fragmentation.d b;
    private SwipeBackLayout c;

    public d(b bVar) {
        if (!(bVar instanceof Fragment) || !(bVar instanceof me.yokeyword.fragmentation.d)) {
            throw new RuntimeException("Must extends Fragment and implements ISupportFragment!");
        }
        this.a = (Fragment) bVar;
        this.b = (me.yokeyword.fragmentation.d) bVar;
    }

    public void a(Bundle bundle) {
        b();
    }

    public void a(View view, Bundle bundle) {
        if (view instanceof SwipeBackLayout) {
            this.b.b().a(((SwipeBackLayout) view).getChildAt(0));
            return;
        }
        this.b.b().a(view);
    }

    public View a(View view) {
        this.c.b(this.b, view);
        return this.c;
    }

    public void a(boolean z) {
        if (z && this.c != null) {
            this.c.b();
        }
    }

    public void b(boolean z) {
        this.c.setEnableGesture(z);
    }

    public void a() {
        this.c.a();
    }

    private void b() {
        if (this.a.getContext() != null) {
            this.c = new SwipeBackLayout(this.a.getContext());
            this.c.setLayoutParams(new LayoutParams(-1, -1));
            this.c.setBackgroundColor(0);
        }
    }
}
