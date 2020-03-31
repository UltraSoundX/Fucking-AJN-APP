package com.sensetime.senseid.sdk.liveness.interactive;

import android.graphics.Rect;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.MainThreadExecutor;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceDistance;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceState;
import java.util.List;

final class b implements OnLivenessListener {
    /* access modifiers changed from: private */
    public OnLivenessListener mListener;

    b(OnLivenessListener onLivenessListener) {
        this.mListener = onLivenessListener;
    }

    public final void onAligned() {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            public final void run() {
                if (b.this.mListener != null) {
                    b.this.mListener.onAligned();
                }
            }
        });
    }

    public final void onDetectOver(ResultCode resultCode, byte[] bArr, List<byte[]> list, Rect rect) {
        final ResultCode resultCode2 = resultCode;
        final byte[] bArr2 = bArr;
        final List<byte[]> list2 = list;
        final Rect rect2 = rect;
        MainThreadExecutor.getInstance().execute(new Runnable() {
            public final void run() {
                if (b.this.mListener != null) {
                    b.this.mListener.onDetectOver(resultCode2, bArr2, list2, rect2);
                }
            }
        });
    }

    public final void onError(final ResultCode resultCode) {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            public final void run() {
                if (b.this.mListener != null) {
                    b.this.mListener.onError(resultCode);
                }
            }
        });
    }

    public final void onInitialized() {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            public final void run() {
                if (b.this.mListener != null) {
                    b.this.mListener.onInitialized();
                }
            }
        });
    }

    public final void onMotionSet(final int i, final int i2) {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            public final void run() {
                if (b.this.mListener != null) {
                    b.this.mListener.onMotionSet(i, i2);
                }
            }
        });
    }

    public final void onStatusUpdate(@FaceState final int i, final FaceOcclusion faceOcclusion, @FaceDistance final int i2) {
        MainThreadExecutor.getInstance().execute(new Runnable() {
            public final void run() {
                if (b.this.mListener != null) {
                    b.this.mListener.onStatusUpdate(i, faceOcclusion, i2);
                }
            }
        });
    }

    public final void setListener(OnLivenessListener onLivenessListener) {
        this.mListener = onLivenessListener;
    }
}
