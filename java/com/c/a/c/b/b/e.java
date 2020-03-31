package com.c.a.c.b.b;

/* compiled from: MinimalField */
class e {
    private final String a;
    private final String b;

    e(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append(": ");
        sb.append(this.b);
        return sb.toString();
    }
}
