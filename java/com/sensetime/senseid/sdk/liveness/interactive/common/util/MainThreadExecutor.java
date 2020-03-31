package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class MainThreadExecutor implements Executor {
    private final Handler a = new Handler(Looper.getMainLooper());

    static class a {
        /* access modifiers changed from: private */
        public static final MainThreadExecutor a = new MainThreadExecutor();
    }

    public static MainThreadExecutor getInstance() {
        return a.a;
    }

    public final void execute(Runnable runnable) {
        this.a.post(runnable);
    }
}
