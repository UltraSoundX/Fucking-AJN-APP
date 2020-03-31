package com.e23.ajn.calendar;

import java.io.Serializable;

/* compiled from: CustomDate */
public class a implements Serializable {
    public int a;
    public int b;
    public int c;
    public int d;

    public a(int i, int i2, int i3) {
        if (i2 > 12) {
            i++;
            i2 = 1;
        } else if (i2 < 1) {
            i--;
            i2 = 12;
        }
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public a() {
        this.a = b.a();
        this.b = b.b();
        this.c = b.c();
    }

    public static a a(a aVar, int i) {
        return new a(aVar.a, aVar.b, i);
    }

    public String toString() {
        return this.a + "-" + this.b + "-" + this.c;
    }
}
