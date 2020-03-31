package com.sensetime.senseid.sdk.liveness.interactive;

import android.support.annotation.Keep;

@Keep
public final class NativeMotion {
    public static final int CV_LIVENESS_BLINK = 0;
    public static final int CV_LIVENESS_HEADNOD = 3;
    public static final int CV_LIVENESS_HEADYAW = 2;
    public static final int CV_LIVENESS_MOUTH = 1;

    private NativeMotion() {
    }
}
