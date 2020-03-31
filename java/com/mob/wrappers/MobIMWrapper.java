package com.mob.wrappers;

import com.mob.tools.proguard.PublicMemberKeeper;

public class MobIMWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    private static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (MobIMWrapper.class) {
            if (state == 0) {
                state = isAvailable("MOBIM");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }
}
