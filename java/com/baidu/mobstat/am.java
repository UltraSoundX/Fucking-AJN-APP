package com.baidu.mobstat;

public class am extends ak {
    private static am c = new am();
    private boolean b;

    private am() {
    }

    public static am c() {
        return c;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public String a() {
        return "BaiduMobStat";
    }

    public boolean b() {
        return this.b;
    }
}
