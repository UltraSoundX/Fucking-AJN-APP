package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

class b {
    static b a = new b();

    b() {
    }

    public synchronized void a(Context context) {
        String n = bb.n(context);
        if (!TextUtils.isEmpty(n)) {
            k.AP_LIST.a(System.currentTimeMillis(), n);
        }
    }
}
