package me.yokeyword.fragmentation_swipeback.a;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup.LayoutParams;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation.SwipeBackLayout.a;

/* compiled from: SwipeBackActivityDelegate */
public class c {
    private FragmentActivity a;
    private SwipeBackLayout b;

    public c(a aVar) {
        if (!(aVar instanceof FragmentActivity) || !(aVar instanceof me.yokeyword.fragmentation.c)) {
            throw new RuntimeException("Must extends FragmentActivity/AppCompatActivity and implements ISupportActivity");
        }
        this.a = (FragmentActivity) aVar;
    }

    public void a(Bundle bundle) {
        c();
    }

    public void b(Bundle bundle) {
        this.b.a(this.a);
    }

    public SwipeBackLayout a() {
        return this.b;
    }

    public void a(boolean z) {
        this.b.setEnableGesture(z);
    }

    public void a(a aVar) {
        this.b.setEdgeLevel(aVar);
    }

    public void a(int i) {
        this.b.setEdgeLevel(i);
    }

    public boolean b() {
        return this.a.getSupportFragmentManager().getBackStackEntryCount() <= 1;
    }

    private void c() {
        this.a.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.a.getWindow().getDecorView().setBackgroundColor(0);
        this.b = new SwipeBackLayout(this.a);
        this.b.setLayoutParams(new LayoutParams(-1, -1));
    }
}
