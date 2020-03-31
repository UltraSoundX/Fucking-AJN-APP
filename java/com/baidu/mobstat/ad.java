package com.baidu.mobstat;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

class ad implements UncaughtExceptionHandler {
    private static final ad a = new ad();
    private UncaughtExceptionHandler b;
    private Context c;

    public static ad a() {
        return a;
    }

    private ad() {
    }

    public void a(Context context) {
        this.c = context;
        if (this.b == null) {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        ExceptionAnalysis.getInstance().saveCrashInfo(this.c, th, true);
        if (!this.b.equals(this)) {
            this.b.uncaughtException(thread, th);
        }
    }
}
