package com.d.a;

import com.d.a.a.a;

/* compiled from: OnekeyShareTheme */
public enum d {
    CLASSIC(0, new a());
    
    private final int b;
    private final e c;

    private d(int i, e eVar) {
        this.b = i;
        this.c = eVar;
    }

    public e a() {
        return this.c;
    }

    public static d a(int i) {
        d[] values;
        for (d dVar : values()) {
            if (dVar.b == i) {
                return dVar;
            }
        }
        return CLASSIC;
    }
}
