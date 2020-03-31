package android.arch.lifecycle;

import android.arch.lifecycle.c.a;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    private final Object a;
    private final C0001a b = a.a.b(this.a.getClass());

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
    }

    public void a(e eVar, a aVar) {
        this.b.a(eVar, aVar, this.a);
    }
}
