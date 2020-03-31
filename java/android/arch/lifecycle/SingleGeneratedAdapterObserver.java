package android.arch.lifecycle;

import android.arch.lifecycle.c.a;

public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final b a;

    SingleGeneratedAdapterObserver(b bVar) {
        this.a = bVar;
    }

    public void a(e eVar, a aVar) {
        this.a.a(eVar, aVar, false, null);
        this.a.a(eVar, aVar, true, null);
    }
}
