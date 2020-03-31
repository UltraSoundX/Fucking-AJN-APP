package com.tencent.tauth;

/* compiled from: ProGuard */
public class d {
    public int a;
    public String b;
    public String c;

    public d(int i, String str, String str2) {
        this.b = str;
        this.a = i;
        this.c = str2;
    }

    public String toString() {
        return "errorCode: " + this.a + ", errorMsg: " + this.b + ", errorDetail: " + this.c;
    }
}
