package com.e23.ajn.d;

import com.b.a.c.a;
import com.b.a.e;
import com.b.a.j;
import com.b.a.m;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: StringUtils */
public class s {
    public static <T> ArrayList<T> a(String str, Class<T> cls) {
        ArrayList arrayList = (ArrayList) new e().a(str, new a<ArrayList<m>>() {
        }.b());
        ArrayList<T> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new e().a((j) (m) it.next(), cls));
        }
        return arrayList2;
    }
}
