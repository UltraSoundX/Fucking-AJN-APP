package com.sensetime.senseid.sdk.liveness.interactive.type;

import android.support.annotation.Keep;

@Keep
public final class FaceOcclusion {
    private int browOcclusionState;
    private int eyeOcclusionState;
    private int mouthOcclusionState;
    private int noseOcclusionState;

    public FaceOcclusion(@OcclusionState int i, @OcclusionState int i2, @OcclusionState int i3, @OcclusionState int i4) {
        this.browOcclusionState = i;
        this.eyeOcclusionState = i2;
        this.noseOcclusionState = i3;
        this.mouthOcclusionState = i4;
    }

    public final int getBrowOcclusionState() {
        return this.browOcclusionState;
    }

    public final int getEyeOcclusionState() {
        return this.eyeOcclusionState;
    }

    public final int getMouthOcclusionState() {
        return this.mouthOcclusionState;
    }

    public final int getNoseOcclusionState() {
        return this.noseOcclusionState;
    }

    public final boolean isOcclusion() {
        return (((this.browOcclusionState | this.eyeOcclusionState) | this.noseOcclusionState) | this.mouthOcclusionState) > 1;
    }
}
