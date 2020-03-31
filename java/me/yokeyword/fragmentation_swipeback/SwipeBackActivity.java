package me.yokeyword.fragmentation_swipeback;

import android.os.Bundle;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.a.a;
import me.yokeyword.fragmentation_swipeback.a.c;

public class SwipeBackActivity extends SupportActivity implements a {
    final c b = new c(this);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b.a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.b.b(bundle);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return this.b.a();
    }

    public void setSwipeBackEnable(boolean z) {
        this.b.a(z);
    }

    public void setEdgeLevel(SwipeBackLayout.a aVar) {
        this.b.a(aVar);
    }

    public void setEdgeLevel(int i) {
        this.b.a(i);
    }

    public boolean swipeBackPriority() {
        return this.b.b();
    }
}
