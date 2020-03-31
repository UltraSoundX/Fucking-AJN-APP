package com.baidu.b.a;

import java.util.Hashtable;

class m implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ Hashtable e;
    final /* synthetic */ b f;

    m(b bVar, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f = bVar;
        this.a = i;
        this.b = z;
        this.c = str;
        this.d = str2;
        this.e = hashtable;
    }

    public void run() {
        if (d.a) {
            d.a("status = " + this.a + "; forced = " + this.b + "checkAK = " + this.f.b(this.c));
        }
        if (this.a == 601 || this.b || this.a == -1 || this.f.b(this.c)) {
            if (d.a) {
                d.a("authenticate sendAuthRequest");
            }
            String[] b2 = e.b(b.a);
            if (d.a) {
                d.a("authStrings.length:" + b2.length);
            }
            if (b2 == null || b2.length <= 1) {
                this.f.a(this.b, this.d, this.e, this.c);
                return;
            }
            if (d.a) {
                d.a("more sha1 auth");
            }
            this.f.a(this.b, this.d, this.e, b2, this.c);
        } else if (602 == this.a) {
            if (d.a) {
                d.a("authenticate wait  ");
            }
            if (b.d != null) {
                b.d.b();
            }
            this.f.a((String) null, this.c);
        } else {
            if (d.a) {
                d.a("authenticate else  ");
            }
            this.f.a((String) null, this.c);
        }
    }
}
