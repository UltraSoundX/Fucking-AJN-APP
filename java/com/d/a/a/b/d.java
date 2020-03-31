package com.d.a.a.b;

import com.d.a.a.i;
import com.d.a.a.j;
import com.d.a.e;
import java.util.ArrayList;

/* compiled from: PlatformPagePort */
public class d extends i {
    public d(e eVar) {
        super(eVar);
    }

    public void onCreate() {
        requestPortraitOrientation();
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public j a(ArrayList<Object> arrayList) {
        return new c(this, arrayList);
    }
}
