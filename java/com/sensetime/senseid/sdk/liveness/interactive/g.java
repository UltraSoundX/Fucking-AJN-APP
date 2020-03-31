package com.sensetime.senseid.sdk.liveness.interactive;

import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;

interface g {
    FaceOcclusion getOcclusion();

    boolean isStateValid(int i);

    DetectResult wrapperInput(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d);
}
