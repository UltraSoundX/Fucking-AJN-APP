package android.support.transition;

import android.view.View;
import android.view.WindowId;

/* compiled from: WindowIdApi18 */
class ao implements ap {
    private final WindowId a;

    ao(View view) {
        this.a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ao) && ((ao) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
