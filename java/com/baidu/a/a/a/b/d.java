package com.baidu.a.a.a.b;

import java.util.Comparator;

class d implements Comparator<a> {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    /* renamed from: a */
    public int compare(a aVar, a aVar2) {
        int i = aVar2.b - aVar.b;
        if (i != 0) {
            return i;
        }
        if (aVar.d && aVar2.d) {
            return 0;
        }
        if (aVar.d) {
            return -1;
        }
        if (aVar2.d) {
            return 1;
        }
        return i;
    }
}
