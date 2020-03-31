package com.sensetime.senseid.sdk.liveness.interactive;

import android.graphics.Rect;
import android.support.annotation.Keep;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceDistance;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceState;
import java.util.List;

@Keep
public interface OnLivenessListener {
    void onAligned();

    void onDetectOver(ResultCode resultCode, byte[] bArr, List<byte[]> list, Rect rect);

    void onError(ResultCode resultCode);

    void onInitialized();

    void onMotionSet(int i, int i2);

    void onStatusUpdate(@FaceState int i, FaceOcclusion faceOcclusion, @FaceDistance int i2);
}
