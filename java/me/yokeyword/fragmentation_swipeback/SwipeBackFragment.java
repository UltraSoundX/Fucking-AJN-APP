package me.yokeyword.fragmentation_swipeback;

import android.os.Bundle;
import android.view.View;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation_swipeback.a.b;
import me.yokeyword.fragmentation_swipeback.a.d;

public class SwipeBackFragment extends SupportFragment implements b {
    final d a = new d(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.a(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a.a(view, bundle);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.a.a(z);
    }

    public void onDestroyView() {
        this.a.a();
        super.onDestroyView();
    }
}
