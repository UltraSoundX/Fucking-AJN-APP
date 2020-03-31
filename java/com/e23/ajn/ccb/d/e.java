package com.e23.ajn.ccb.d;

import android.content.Context;
import android.util.Base64;
import com.c.a.e.c;
import com.ccb.crypto.tp.tool.d;
import com.e23.ajn.ccb.b.a;
import java.io.UnsupportedEncodingException;

/* compiled from: EsafeUtils */
public class e {
    private static d a;

    public static d a(Context context) {
        if (a == null) {
            a = new d(context, a.g);
            a.b();
            a.h();
        }
        return a;
    }

    public static String a(Context context, String str) {
        if (str == null) {
            return null;
        }
        String str2 = new String();
        try {
            String a2 = a(Base64.encodeToString(str.getBytes("utf-8"), 0));
            c.c("base64编码后，一路护航加密前");
            d a3 = a(context);
            if (!a3.b()) {
                return str2;
            }
            String c = a3.c(a2);
            c.c("e护航加密后");
            return c;
        } catch (UnsupportedEncodingException e) {
            if (!c.d) {
                return str2;
            }
            e.printStackTrace();
            return str2;
        }
    }

    private static String a(String str) {
        if (str == null) {
            return str;
        }
        return "&TXCODE=&BRANCHID=&USERID=&COMMPKG=" + str;
    }
}
