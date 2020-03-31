package com.e23.ajn.d;

import android.text.TextUtils;
import java.util.List;
import java.util.Random;

/* compiled from: CheckUtils */
public class e {
    public static boolean a(List list) {
        if (list == null || list.size() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean b(List list) {
        return !a(list);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String trim = str.trim();
        if (trim.equals(" ") || trim.equals("") || "NULL".equals(trim) || "null".equals(trim)) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        return !a(str);
    }

    public static String a(int i) {
        String str = "123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str.charAt(random.nextInt(str.length())));
        }
        return stringBuffer.toString();
    }
}
