package com.sensetime.senseid.sdk.liveness.interactive.common.type;

public final class ModelType {
    private final String a;
    private final ResultCode b;

    public ModelType(String str, ResultCode resultCode) {
        this.a = str;
        this.b = resultCode;
    }

    public final ResultCode getErrorCode() {
        return this.b;
    }

    public final String getModelFilePath() {
        return this.a;
    }
}
