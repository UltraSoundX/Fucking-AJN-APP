package com.e23.ajn.calendar;

import android.annotation.SuppressLint;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* compiled from: DateUtil */
public class b {
    public static String[] a = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static int a(int i, int i2) {
        if (i2 > 12) {
            i++;
            i2 = 1;
        } else if (i2 < 1) {
            i--;
            i2 = 12;
        }
        int[] iArr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((i % 4 == 0 && i % 100 != 0) || i % ErrorCode.INFO_CODE_BASE == 0) {
            iArr[1] = 29;
        }
        try {
            return iArr[i2 - 1];
        } catch (Exception e) {
            e.getStackTrace();
            return 0;
        }
    }

    public static int a() {
        return Calendar.getInstance().get(1);
    }

    public static int b() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int c() {
        return Calendar.getInstance().get(5);
    }

    public static int b(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(c(i, i2));
        int i3 = instance.get(7) - 1;
        if (i3 < 0) {
            return 0;
        }
        return i3;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static Date c(int i, int i2) {
        String str = i + "-" + (i2 > 9 ? Integer.valueOf(i2) : "0" + i2) + "-01";
        boolean z = false;
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            return z;
        }
    }

    public static boolean a(a aVar) {
        return aVar.a == a() && aVar.b == b();
    }
}
