package com.baidu.location.indoor;

import com.baidu.location.indoor.mapversion.b.a;
import com.baidu.location.indoor.mapversion.c.a.c;

class k implements c {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ g c;

    k(g gVar, String str, String str2) {
        this.c = gVar;
        this.a = str;
        this.b = str2;
    }

    public void a(boolean z, String str) {
        this.c.ab = z;
        if (z) {
            this.c.ac = a.a(this.b);
        }
    }
}
