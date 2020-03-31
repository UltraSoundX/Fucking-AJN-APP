package com.baidu.location.e;

import android.location.OnNmeaMessageListener;

class f implements OnNmeaMessageListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void onNmeaMessage(String str, long j) {
        if (this.a.b(str)) {
            this.a.a(str);
        }
    }
}
