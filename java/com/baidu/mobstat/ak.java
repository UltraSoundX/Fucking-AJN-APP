package com.baidu.mobstat;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

public abstract class ak {
    public static int a = 2;

    public abstract String a();

    public abstract boolean b();

    public void a(String str) {
        a(3, str);
    }

    public void a(Throwable th) {
        a(3, d(th));
    }

    public void b(String str) {
        a(5, str);
    }

    public void b(Throwable th) {
        a(5, d(th));
    }

    public void c(String str) {
        a(6, str);
    }

    public void c(Throwable th) {
        a(6, d(th));
    }

    private String d(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private void a(int i, String str) {
        if (b() && i >= a) {
            Log.println(i, a(), str);
        }
    }
}
