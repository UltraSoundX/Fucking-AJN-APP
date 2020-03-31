package com.c.a.e;

import java.util.ArrayList;
import java.util.List;

/* compiled from: CharsetUtils */
public class a {
    public static final List<String> a = new ArrayList();

    public static String a(String str, String str2, int i) {
        try {
            return new String(str.getBytes(a(str, i)), str2);
        } catch (Throwable th) {
            c.a(th);
            return str;
        }
    }

    public static String a(String str, int i) {
        String str2 = "ISO-8859-1";
        for (String str3 : a) {
            if (b(str, str3, i)) {
                return str3;
            }
        }
        return str2;
    }

    public static boolean b(String str, String str2, int i) {
        try {
            if (str.length() > i) {
                str = str.substring(0, i);
            }
            return str.equals(new String(str.getBytes(str2), str2));
        } catch (Throwable th) {
            return false;
        }
    }

    static {
        a.add("ISO-8859-1");
        a.add("GB2312");
        a.add("GBK");
        a.add("GB18030");
        a.add("US-ASCII");
        a.add("ASCII");
        a.add("ISO-2022-KR");
        a.add("ISO-8859-2");
        a.add("ISO-2022-JP");
        a.add("ISO-2022-JP-2");
        a.add("UTF-8");
    }
}
