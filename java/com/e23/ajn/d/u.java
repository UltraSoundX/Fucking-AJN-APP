package com.e23.ajn.d;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* compiled from: TimeUtils */
public class u {
    private static final TimeZone a = TimeZone.getTimeZone("GMT+08:00");

    public static String a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(1000 * j);
        Calendar instance3 = Calendar.getInstance();
        instance3.setTimeInMillis(System.currentTimeMillis() - 86400000);
        int timeInMillis = ((int) (instance.getTimeInMillis() / 60000)) - ((int) (instance2.getTimeInMillis() / 60000));
        if (timeInMillis < 1) {
            return "刚刚";
        }
        if (timeInMillis < 60) {
            return timeInMillis + "分钟前";
        }
        int i = instance.get(5);
        int i2 = instance2.get(5);
        int i3 = instance3.get(5);
        int i4 = instance.get(2);
        int i5 = instance2.get(2);
        int i6 = instance3.get(2);
        int i7 = instance.get(1);
        int i8 = instance2.get(1);
        int i9 = instance3.get(1);
        if (i == i2 && i4 == i5 && i7 == i8) {
            return new SimpleDateFormat("HH:mm").format(Long.valueOf(instance2.getTimeInMillis()));
        }
        if (i3 == i2 && i6 == i5 && i9 == i8) {
            return new SimpleDateFormat("昨天HH:mm").format(Long.valueOf(instance2.getTimeInMillis()));
        }
        return new SimpleDateFormat("MM月dd日HH:mm").format(Long.valueOf(instance2.getTimeInMillis()));
    }

    public static String b(long j) {
        if (j == 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(1000 * j));
    }

    public static String c(long j) {
        if (j == 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(1000 * j));
    }
}
