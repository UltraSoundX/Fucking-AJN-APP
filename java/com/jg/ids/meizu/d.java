package com.jg.ids.meizu;

public final class d {
    public String a = null;
    public int b = 0;
    public long c = (System.currentTimeMillis() + 86400000);

    public d(String str, int i) {
    }

    public final String toString() {
        return "ValueData{value='" + this.a + '\'' + ", code=" + this.b + ", expired=" + this.c + '}';
    }
}
