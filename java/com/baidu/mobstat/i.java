package com.baidu.mobstat;

public class i {
    private long a = -1;
    private String b;
    private String c;

    public i(long j, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        this.a = j;
        this.b = str;
        this.c = str2;
    }

    public long a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }
}
