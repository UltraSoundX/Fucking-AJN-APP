package com.ccb.b;

import com.ccb.b.a.g;
import com.ccb.b.b.b;
import com.ccb.b.b.l;

public class m implements l {
    static g a;
    public static boolean c = true;
    long b = 0;
    private String d = "V1.0";

    public void a(g gVar) {
        a = gVar;
    }

    public boolean a(b bVar, int i, String str) {
        if (str != null) {
            a.a(str);
            return true;
        }
        switch (i) {
            case 4:
                c = false;
                a.b("cancel");
                return true;
            case 7:
                c = true;
                a.b("finish");
                return true;
            case 59:
                c = false;
                a.b("shiftSys");
                return true;
            case 66:
                c = true;
                a.b("finish");
                return true;
            case 67:
                a.b("delete");
                return true;
            default:
                a.a();
                return false;
        }
    }
}
