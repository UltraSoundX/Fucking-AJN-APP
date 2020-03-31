package com.sensetime.senseid.sdk.liveness.interactive.common;

import android.support.annotation.Keep;

@Keep
public class HandleResult extends AbstractJniResult {
    private Object mHandle;

    public HandleResult(int i, Object obj) {
        super(i);
        this.mHandle = obj;
    }

    public Object getHandle() {
        return this.mHandle;
    }

    public /* bridge */ /* synthetic */ int getResultCode() {
        return super.getResultCode();
    }
}
