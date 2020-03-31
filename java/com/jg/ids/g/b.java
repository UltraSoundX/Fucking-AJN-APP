package com.jg.ids.g;

import android.database.ContentObserver;
import android.os.Handler;

final class b extends ContentObserver {
    private String a;
    private int b;
    private /* synthetic */ a c;

    public b(a aVar, Handler handler, String str, int i) {
        this.c = aVar;
        super(null);
        this.a = str;
        this.b = i;
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        this.c.b(this.b, this.a);
    }
}
