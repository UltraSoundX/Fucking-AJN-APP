package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;

public class DateSorter {
    public static int DAY_COUNT = 5;
    private android.webkit.DateSorter a;
    private IX5DateSorter b;

    static {
        if (a()) {
        }
    }

    public DateSorter(Context context) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            this.a = new android.webkit.DateSorter(context);
        } else {
            this.b = a2.c().h(context);
        }
    }

    public int getIndex(long j) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return this.a.getIndex(j);
        }
        return this.b.getIndex(j);
    }

    public String getLabel(int i) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return this.a.getLabel(i);
        }
        return this.b.getLabel(i);
    }

    public long getBoundary(int i) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return this.a.getBoundary(i);
        }
        return this.b.getBoundary(i);
    }

    private static boolean a() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return false;
        }
        return true;
    }
}
