package com.mob.tools;

import com.mob.tools.log.NLog;

public class MobLog {
    private static NLog logger;

    public static synchronized NLog getInstance() {
        NLog nLog;
        synchronized (MobLog.class) {
            if (logger == null) {
                logger = NLog.getInstance("MOBTOOLS");
            }
            nLog = logger;
        }
        return nLog;
    }
}
