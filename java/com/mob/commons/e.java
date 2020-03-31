package com.mob.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: MobChannel */
public class e {
    private static e a;
    private HashMap<String, Object> b = c();

    private e() {
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        ArrayList products = MobProductCollector.getProducts();
        if (products != null && !products.isEmpty()) {
            Iterator it = products.iterator();
            while (it.hasNext()) {
                MobProduct mobProduct = (MobProduct) it.next();
                if (!this.b.containsKey(mobProduct.getProductTag())) {
                    this.b.put(mobProduct.getProductTag(), Integer.valueOf(0));
                }
            }
        }
    }

    public static e a() {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e();
                }
            }
        }
        return a;
    }

    public synchronized void a(MobProduct mobProduct, int i) {
        if (mobProduct != null) {
            this.b.put(mobProduct.getProductTag(), Integer.valueOf(i));
            a(this.b);
        }
    }

    public synchronized HashMap<String, Object> b() {
        return this.b;
    }

    private HashMap<String, Object> c() {
        try {
            return g.u();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private void a(HashMap<String, Object> hashMap) {
        try {
            g.c(hashMap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
