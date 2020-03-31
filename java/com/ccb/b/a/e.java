package com.ccb.b.a;

import com.ccb.crypto.tp.tool.d;

public class e implements c {
    public static int a = 150;
    private static boolean f = true;
    int b = 1;
    byte[][] c = new byte[(a * this.b)][];
    String[] d = new String[(a * this.b)];
    private int e = 0;
    private d g;

    public e(d dVar) {
        this.g = dVar;
    }

    public void a(String str) {
        try {
            this.d[this.e] = this.g.a(str);
            this.e++;
            f = true;
        } catch (Exception e2) {
        }
    }

    public void a(int i) {
        if (this.e > 0) {
            this.e--;
            this.d[this.e] = null;
            if (this.e == 0) {
                f = false;
            }
        }
    }

    public void a() {
        this.d = new String[(a * this.b)];
        this.e = 0;
        f = false;
    }

    public a b() {
        a aVar = new a();
        if (this.g == null) {
            return null;
        }
        String b2 = this.g.b(c());
        this.d = new String[(a * this.b)];
        aVar.a(0);
        aVar.a(b2);
        aVar.b("none");
        aVar.b(this.e);
        return aVar;
    }

    public String c() {
        if (f) {
            return this.g.a(this.d, this.e);
        }
        return null;
    }
}
