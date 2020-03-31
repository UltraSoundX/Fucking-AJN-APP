package com.sensetime.senseid.sdk.liveness.interactive.common;

import android.support.annotation.Keep;

@Keep
abstract class AbstractJniResult {
    @Keep
    private int mResultCode;

    AbstractJniResult(int i) {
        this.mResultCode = i;
    }

    public int getResultCode() {
        return this.mResultCode;
    }
}
