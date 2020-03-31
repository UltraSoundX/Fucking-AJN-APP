package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: Scene */
public class n {
    private ViewGroup a;
    private Runnable b;

    public void a() {
        if (a(this.a) == this && this.b != null) {
            this.b.run();
        }
    }

    static void a(View view, n nVar) {
        view.setTag(R.id.transition_current_scene, nVar);
    }

    static n a(View view) {
        return (n) view.getTag(R.id.transition_current_scene);
    }
}
