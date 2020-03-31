package com.c.a.c.b.b.a;

/* compiled from: AbstractContentBody */
public abstract class a implements b {
    protected com.c.a.c.b.b.g.a a = com.c.a.c.b.b.g.a.a;
    private final String b;
    private final String c;
    private final String d;

    public a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("MIME type may not be null");
        }
        this.b = str;
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            this.c = str.substring(0, indexOf);
            this.d = str.substring(indexOf + 1);
            return;
        }
        this.c = str;
        this.d = null;
    }

    public String a() {
        return this.b;
    }

    public void a(com.c.a.c.b.b.g.a aVar) {
        this.a = aVar;
    }
}
