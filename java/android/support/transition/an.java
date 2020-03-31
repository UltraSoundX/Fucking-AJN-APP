package android.support.transition;

import android.os.IBinder;

/* compiled from: WindowIdApi14 */
class an implements ap {
    private final IBinder a;

    an(IBinder iBinder) {
        this.a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof an) && ((an) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
