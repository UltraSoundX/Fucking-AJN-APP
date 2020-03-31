package android.arch.lifecycle;

import android.arch.lifecycle.c.a;

public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final b[] a;

    CompositeGeneratedAdaptersObserver(b[] bVarArr) {
        this.a = bVarArr;
    }

    public void a(e eVar, a aVar) {
        i iVar = new i();
        for (b a2 : this.a) {
            a2.a(eVar, aVar, false, iVar);
        }
        for (b a3 : this.a) {
            a3.a(eVar, aVar, true, iVar);
        }
    }
}
