package com.sensetime.senseid.sdk.liveness.interactive;

import android.content.Context;
import android.graphics.Rect;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.PixelFormat;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.SyncTaskService;
import com.sensetime.senseid.sdk.liveness.interactive.type.BoundInfo;

final class f extends SyncTaskService {
    /* access modifiers changed from: private */
    public e mLibrary = null;
    /* access modifiers changed from: private */
    public b mListenerWrap = null;

    f() {
    }

    private void nofityError(ResultCode resultCode) {
        if (this.mListenerWrap != null) {
            this.mListenerWrap.onError(resultCode);
        }
    }

    private boolean verifyBoundInfo(Rect rect, BoundInfo boundInfo) {
        return boundInfo == null ? rect != null && rect.right - rect.left > 0 && rect.bottom - rect.top > 0 : boundInfo.getX() >= 0 && boundInfo.getY() >= 0 && boundInfo.getRadius() >= 0 && rect != null && rect.right - rect.left > 0 && rect.bottom - rect.top > 0 && rect.contains(new Rect(boundInfo.getX() - boundInfo.getRadius(), boundInfo.getY() - boundInfo.getRadius(), boundInfo.getX() + boundInfo.getRadius(), boundInfo.getY() + boundInfo.getRadius()));
    }

    /* access modifiers changed from: 0000 */
    public final void cancel() {
        execute(6, new Runnable() {
            public final void run() {
                f.this.mLibrary.stopDetection();
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final String getLibraryVersion() {
        if (this.mLibrary == null) {
            return null;
        }
        return this.mLibrary.getLibraryVersion();
    }

    /* access modifiers changed from: 0000 */
    public final void initialized(Context context, String str, String str2, String str3, String str4, String str5, OnLivenessListener onLivenessListener) {
        if (onLivenessListener == null) {
            throw new IllegalArgumentException("Listener can not be null, please set valid listener for initialization");
        }
        this.mListenerWrap = new b(onLivenessListener);
        final Context context2 = context;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        execute(1, new Runnable() {
            public final void run() {
                f.this.mLibrary.init(context2, str6, str7, str8, str9, str10, f.this.mListenerWrap);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void inputData(byte[] bArr, PixelFormat pixelFormat, Size size, Rect rect, boolean z, int i, BoundInfo boundInfo) {
        if (!verifyBoundInfo(rect, boundInfo)) {
            nofityError(ResultCode.STID_E_INVALID_ARGUMENTS);
        } else if (!hasTasks(2)) {
            final byte[] bArr2 = bArr;
            final PixelFormat pixelFormat2 = pixelFormat;
            final Size size2 = size;
            final Rect rect2 = rect;
            final boolean z2 = z;
            final int i2 = i;
            final BoundInfo boundInfo2 = boundInfo;
            execute(2, new Runnable() {
                public final void run() {
                    f.this.mLibrary.inputData(bArr2, pixelFormat2, size2, rect2, z2, i2, boundInfo2);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(int i) {
        super.onPostExecute(i);
        if (i == 5) {
            shutdown();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void release() {
        if (this.mListenerWrap != null) {
            this.mListenerWrap.setListener(null);
            this.mListenerWrap = null;
        }
        this.mLibrary.releaseReferences();
        execute(5, new Runnable() {
            public final void run() {
                f.this.mLibrary.cancel();
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void setBrowOcclusion(final boolean z) {
        execute(4, new Runnable() {
            public final void run() {
                f.this.mLibrary.setBrowOcclusion(z);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void setDetectTimeout(long j) {
        if (j < 0) {
            nofityError(ResultCode.STID_E_INVALID_ARGUMENTS);
        } else {
            this.mLibrary.setDetectTimeout(1000 * j);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void setMotionList(final int[] iArr, final int i) {
        execute(3, new Runnable() {
            public final void run() {
                f.this.mLibrary.setMotions(iArr, i);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void setThreshold(float f) {
        if (f <= 0.0f || Float.compare(f, 1.0f) > 0) {
            nofityError(ResultCode.STID_E_INVALID_ARGUMENTS);
        } else {
            this.mLibrary.setThreshold(f);
        }
    }

    public final void start() {
        this.mLibrary = new e();
        super.start();
    }
}
