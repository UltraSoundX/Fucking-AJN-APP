package com.mob.commons.logcollector;

import com.mob.tools.log.LogCollector;
import com.mob.tools.proguard.ProtectedMemberKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;

public abstract class LogsCollector implements LogCollector, ProtectedMemberKeeper, PublicMemberKeeper {
    /* access modifiers changed from: protected */
    public abstract String getSDKTag();

    /* access modifiers changed from: protected */
    public abstract int getSDKVersion();

    public LogsCollector() {
        DefaultLogsCollector.get().addSDK(getSDKTag(), getSDKVersion());
    }

    public final void log(String str, int i, int i2, String str2, String str3) {
        DefaultLogsCollector.get().log(str, i, i2, str2, str3);
    }
}
