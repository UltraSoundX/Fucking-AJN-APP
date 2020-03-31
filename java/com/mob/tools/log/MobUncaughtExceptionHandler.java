package com.mob.tools.log;

import com.mob.tools.MobLog;
import java.lang.Thread.UncaughtExceptionHandler;

public class MobUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static boolean disable;
    private static boolean isDebug = false;
    private static UncaughtExceptionHandler oriHandler;

    public static void disable() {
        disable = true;
    }

    public static void closeLog() {
        isDebug = false;
    }

    public static void openLog() {
        isDebug = true;
    }

    public static void register() {
        if (!disable) {
            oriHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new MobUncaughtExceptionHandler());
        }
    }

    private MobUncaughtExceptionHandler() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (isDebug) {
            MobLog.getInstance().wtf(th);
        }
        MobLog.getInstance().crash(th);
        if (oriHandler != null) {
            oriHandler.uncaughtException(thread, th);
        }
    }
}
