package com.e23.ajn.ccb.d;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/* compiled from: DateUtils */
public class b {
    public static String a = "yyyy-MM-dd HH:mm:ss";
    public static String b = "yyyyMMddHHmmss";
    public static String c = "yyyy/MM/dd";
    public static String d = "yyyyMMdd";
    public static String e = "yyyy-MM";
    public static String f = "yyyy-MM-dd HH:mm";
    public static String g = "yyyy/MM/dd HH:mm";
    public static String h = "yyyy年MM月dd日HH时mm分";
    public static String i = "yyyy年MM月dd日";
    public static String j = "MM/dd";
    public static String k = "HH:mm:ss";
    public static String l = "HH:mm";
    public static final SimpleDateFormat m = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat n = new SimpleDateFormat("yyyy年MM月dd日");
    private static String o = "DateUtil";

    public static String a(String str) {
        boolean z = false;
        try {
            return new SimpleDateFormat(str).format(new GregorianCalendar().getTime());
        } catch (Exception e2) {
            e2.printStackTrace();
            return z;
        }
    }
}
