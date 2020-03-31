package android.arch.lifecycle;

import java.util.HashMap;

/* compiled from: ViewModelStore */
public class o {
    private final HashMap<String, m> a = new HashMap<>();

    /* access modifiers changed from: 0000 */
    public final void a(String str, m mVar) {
        m mVar2 = (m) this.a.put(str, mVar);
        if (mVar2 != null) {
            mVar2.onCleared();
        }
    }

    /* access modifiers changed from: 0000 */
    public final m a(String str) {
        return (m) this.a.get(str);
    }

    public final void a() {
        for (m onCleared : this.a.values()) {
            onCleared.onCleared();
        }
        this.a.clear();
    }
}
