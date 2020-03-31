package com.sensetime.senseid.sdk.liveness.interactive;

import android.content.Context;
import android.graphics.Rect;
import com.sensetime.senseid.sdk.liveness.interactive.common.HandleResult;
import com.sensetime.senseid.sdk.liveness.interactive.common.network.HttpResult;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.AbstractContentType;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceDistance;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceState;
import java.util.List;

final class e extends AbstractInteractiveLivenessLibrary {
    private OnLivenessListener mLiveListener;
    private float mThreshold = 0.95f;

    e() {
    }

    /* access modifiers changed from: protected */
    public final void cancel() {
        releaseReferences();
        release();
    }

    /* access modifiers changed from: protected */
    public final int createWrapperHandle(String... strArr) {
        if (strArr == null || strArr.length != 4) {
            return -1;
        }
        HandleResult nativeCreateWrapperHandle = AbstractInteractiveLivenessLibrary.nativeCreateWrapperHandle(strArr[0], strArr[1], strArr[2], strArr[3]);
        this.mHandle = nativeCreateWrapperHandle.getResultCode() == 0 ? nativeCreateWrapperHandle.getHandle() : null;
        return nativeCreateWrapperHandle.getResultCode();
    }

    /* access modifiers changed from: 0000 */
    public final boolean init(Context context, String str, String str2, String str3, String str4, String str5, OnLivenessListener onLivenessListener) {
        this.mLiveListener = onLivenessListener;
        ResultCode init = init(context, str, null, str2, str3, str4, str5);
        if (init != ResultCode.OK) {
            notifyError(init);
            return false;
        }
        if (this.mLiveListener != null) {
            this.mLiveListener.onInitialized();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final void notifyAligned() {
        if (this.mLiveListener != null) {
            this.mLiveListener.onAligned();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyDetectOver(ResultCode resultCode, byte[] bArr, List<byte[]> list, Rect rect) {
        if (this.mLiveListener != null) {
            this.mLiveListener.onDetectOver(resultCode, bArr, list, rect);
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyError(ResultCode resultCode) {
        if (this.mLiveListener != null) {
            this.mLiveListener.onError(resultCode);
        }
        cancel();
    }

    /* access modifiers changed from: protected */
    public final void notifyMotionSet(int i, int i2) {
        if (this.mLiveListener != null) {
            this.mLiveListener.onMotionSet(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyNetworkBegin() {
    }

    /* access modifiers changed from: protected */
    public final void notifyStatusUpdate(@FaceState int i, FaceOcclusion faceOcclusion, @FaceDistance int i2) {
        if (this.mLiveListener != null) {
            this.mLiveListener.onStatusUpdate(i, faceOcclusion, i2);
        }
    }

    /* access modifiers changed from: protected */
    public final void onNetworkFinished(HttpResult httpResult, AbstractContentType abstractContentType) {
    }

    /* access modifiers changed from: protected */
    public final void onStatusUpdate(@FaceState int i, FaceOcclusion faceOcclusion, @FaceDistance int i2) {
        notifyStatusUpdate(i, faceOcclusion, i2);
    }

    /* access modifiers changed from: protected */
    public final void processDetectResult(ResultCode resultCode, DetectResult detectResult, long j) {
        if (ResultCode.OK != resultCode) {
            notifyDetectOver(resultCode, detectResult.protobuf, detectResult.images, new Rect(detectResult.left, detectResult.top, detectResult.right, detectResult.bottom));
        } else if (Float.compare(detectResult.hacknessScore, 0.0f) < 0 || detectResult.hacknessScore >= this.mThreshold) {
            notifyDetectOver(ResultCode.STID_E_HACK, detectResult.protobuf, detectResult.images, new Rect(detectResult.left, detectResult.top, detectResult.right, detectResult.bottom));
        } else {
            notifyDetectOver(ResultCode.OK, detectResult.protobuf, detectResult.images, new Rect(detectResult.left, detectResult.top, detectResult.right, detectResult.bottom));
        }
    }

    /* access modifiers changed from: protected */
    public final void releaseReferences() {
        this.mLiveListener = null;
    }

    /* access modifiers changed from: protected */
    public final void setDetectTimeout(long j) {
        this.mDetectTimeout = j;
    }

    /* access modifiers changed from: 0000 */
    public final void setMotions(int[] iArr, @MotionComplexity int i) {
        ResultCode motionList = setMotionList(iArr, i);
        if (!ResultCode.OK.equals(motionList)) {
            notifyError(motionList);
        }
    }

    public final void setThreshold(float f) {
        this.mThreshold = f;
    }

    /* access modifiers changed from: protected */
    public final void startLocalAntiHack(DetectResult detectResult) {
        detectResult.hacknessScore = wrapperLocalHacking();
    }
}
