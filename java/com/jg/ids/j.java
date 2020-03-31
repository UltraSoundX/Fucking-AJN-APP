package com.jg.ids;

import android.text.TextUtils;

public enum j {
    UNSUPPORT(-1, "unsupport"),
    HUA_WEI(0, "HUAWEI"),
    XIAOMI(1, "Xiaomi"),
    VIVO(2, "vivo"),
    OPPO(3, "oppo"),
    MOTO(4, "motorola"),
    LENOVO(5, "lenovo"),
    ASUS(6, "asus"),
    SAMSUNG(7, "samsung"),
    MEIZU(8, "meizu"),
    ALPS(9, "alps"),
    NUBIA(10, "nubia");
    
    private String m;

    private j(int i, String str) {
        this.m = str;
    }

    public static j a(String str) {
        j[] values;
        if (TextUtils.isEmpty(str)) {
            return UNSUPPORT;
        }
        for (j jVar : values()) {
            if (jVar.m.equalsIgnoreCase(str)) {
                return jVar;
            }
        }
        return UNSUPPORT;
    }
}
