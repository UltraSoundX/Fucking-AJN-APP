package com.sensetime.senseid.sdk.liveness.interactive;

import android.support.annotation.Keep;

@Keep
public interface LivenessState {
    void checkResult(DetectResult detectResult);
}
