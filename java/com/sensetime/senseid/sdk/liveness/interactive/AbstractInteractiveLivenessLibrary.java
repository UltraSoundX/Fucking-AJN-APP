package com.sensetime.senseid.sdk.liveness.interactive;

import android.os.SystemClock;
import com.sensetime.senseid.sdk.liveness.interactive.common.HandleResult;
import com.sensetime.senseid.sdk.liveness.interactive.common.network.AbstractLivenessUtils;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceDistance;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractInteractiveLivenessLibrary extends a {
    private static boolean sIsLibraryLoaded;
    /* access modifiers changed from: private */
    public long mAlignedStartTime;
    /* access modifiers changed from: private */
    public int mCurrentMotionIndex;
    /* access modifiers changed from: private */
    public int[] mMotionList;
    /* access modifiers changed from: private */
    public boolean mNeedBrowOcclusion = false;
    private g mStateWrapper;
    private int sStatus;

    private class a implements g {
        private FaceOcclusion mFaceOcclusion;

        private a() {
        }

        public final FaceOcclusion getOcclusion() {
            return this.mFaceOcclusion;
        }

        public final boolean isStateValid(int i) {
            return 2 == i || 4 == i;
        }

        public final DetectResult wrapperInput(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d) {
            DetectResult access$900 = AbstractInteractiveLivenessLibrary.nativeWrapperTracking(AbstractInteractiveLivenessLibrary.this.mHandle, bArr, i, i2, i3, i4, i5, AbstractInteractiveLivenessLibrary.this.mNeedBrowOcclusion, d);
            int i6 = AbstractInteractiveLivenessLibrary.this.mNeedBrowOcclusion ? access$900.browScore >= 0.2d ? 2 : 1 : 0;
            access$900.faceOcclusion = new FaceOcclusion(i6, access$900.eyeScore >= 0.2d ? 2 : 1, access$900.noseScore >= 0.1d ? 2 : 1, access$900.mouthScore >= 0.2d ? 2 : 1);
            this.mFaceOcclusion = access$900.faceOcclusion;
            return access$900;
        }
    }

    private class b implements LivenessState {
        private b() {
        }

        public final void checkResult(DetectResult detectResult) {
            if (detectResult.faceState != 0 || detectResult.faceDistance != 0) {
                AbstractInteractiveLivenessLibrary.this.mAlignedStartTime = -1;
            } else if (AbstractInteractiveLivenessLibrary.this.mAlignedStartTime < 0) {
                AbstractInteractiveLivenessLibrary.this.mAlignedStartTime = SystemClock.elapsedRealtime();
            } else if (SystemClock.elapsedRealtime() - AbstractInteractiveLivenessLibrary.this.mAlignedStartTime > 1000) {
                AbstractInteractiveLivenessLibrary.this.mAlignedStartTime = -1;
                AbstractInteractiveLivenessLibrary.this.notifyAligned();
                return;
            } else {
                return;
            }
            AbstractInteractiveLivenessLibrary.this.onStatusUpdate(detectResult.faceState, detectResult.faceOcclusion, detectResult.faceDistance);
        }
    }

    private class c implements LivenessState {
        private c() {
        }

        private void doEnd(ResultCode resultCode, DetectResult detectResult, long j) {
            AbstractInteractiveLivenessLibrary.this.mStartTime = -1;
            if (AbstractInteractiveLivenessLibrary.this.stop()) {
                preProcessResult(detectResult);
                if (resultCode == ResultCode.OK) {
                    AbstractInteractiveLivenessLibrary.this.startLocalAntiHack(detectResult);
                }
                AbstractInteractiveLivenessLibrary.this.processDetectResult(resultCode, detectResult, j);
            }
        }

        private DetectResult preProcessResult(DetectResult detectResult) {
            byte[] result = AbstractInteractiveLivenessLibrary.this.getResult();
            if (result != null && result.length > 0) {
                detectResult.protobuf = Arrays.copyOf(result, result.length);
            }
            DetectResult imagesAndFaces = AbstractInteractiveLivenessLibrary.this.getImagesAndFaces();
            List<byte[]> list = imagesAndFaces == null ? null : imagesAndFaces.images;
            if (list != null && !list.isEmpty()) {
                detectResult.images = list;
            }
            return detectResult;
        }

        public final void checkResult(DetectResult detectResult) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - AbstractInteractiveLivenessLibrary.this.mStartTime;
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - AbstractInteractiveLivenessLibrary.this.mFirstFrameTime;
            if (detectResult.faceState == 2 || detectResult.faceState == -1 || detectResult.faceState == 1) {
                doEnd(ResultCode.STID_E_NOFACE_DETECTED, detectResult, elapsedRealtime2);
            } else if (detectResult.faceState == 3) {
                doEnd(ResultCode.STID_E_FACE_COVERED, detectResult, elapsedRealtime2);
            } else if (!detectResult.passed) {
                if (AbstractInteractiveLivenessLibrary.this.mDetectTimeout > 0 && elapsedRealtime > AbstractInteractiveLivenessLibrary.this.mDetectTimeout) {
                    doEnd(ResultCode.STID_E_TIMEOUT, detectResult, elapsedRealtime2);
                }
            } else if (AbstractInteractiveLivenessLibrary.this.mCurrentMotionIndex == AbstractInteractiveLivenessLibrary.this.mMotionList.length - 1) {
                doEnd(ResultCode.OK, detectResult, elapsedRealtime2);
            } else {
                AbstractInteractiveLivenessLibrary.this.mStartTime = -1;
                AbstractInteractiveLivenessLibrary.this.mCurrentMotionIndex = AbstractInteractiveLivenessLibrary.this.mCurrentMotionIndex + 1;
                AbstractInteractiveLivenessLibrary.this.setMotion(AbstractInteractiveLivenessLibrary.this.mMotionList[AbstractInteractiveLivenessLibrary.this.mCurrentMotionIndex], true);
            }
        }
    }

    private class d implements g {
        private FaceOcclusion mFaceOcclusion;

        private d() {
        }

        public final FaceOcclusion getOcclusion() {
            return this.mFaceOcclusion;
        }

        public final boolean isStateValid(int i) {
            return 3 == i;
        }

        public final DetectResult wrapperInput(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d) {
            DetectResult access$1000 = AbstractInteractiveLivenessLibrary.nativeWrapperInput(AbstractInteractiveLivenessLibrary.this.mHandle, bArr, i, i2, i3, i4, i5, d);
            int i6 = access$1000.coveredScore >= 0.42f ? 2 : 1;
            access$1000.faceOcclusion = new FaceOcclusion(i6, i6, i6, i6);
            this.mFaceOcclusion = access$1000.faceOcclusion;
            return access$1000;
        }
    }

    static {
        sIsLibraryLoaded = false;
        try {
            System.loadLibrary("stidinteractive_liveness");
            System.loadLibrary("jni_liveness_interactive");
            sIsLibraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    AbstractInteractiveLivenessLibrary() {
        int i = 0;
        if (!sIsLibraryLoaded) {
            i = -1;
        }
        this.sStatus = i;
        this.mCurrentMotionIndex = -1;
        this.mAlignedStartTime = -1;
        this.mMotionList = null;
        this.mStateWrapper = null;
    }

    private int[] checkMotions(int[] iArr) {
        int i = 0;
        if (iArr == null || iArr.length <= 0) {
            return iArr;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 == 0 || i2 == 3 || i2 == 2 || i2 == 1) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        int[] iArr2 = new int[arrayList.size()];
        while (true) {
            int i3 = i;
            if (i3 >= arrayList.size()) {
                return iArr2;
            }
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
            i = i3 + 1;
        }
    }

    protected static native HandleResult nativeCreateWrapperHandle(String str, String str2, String str3, String str4);

    private static native void nativeDestroyWrapperHandle(Object obj);

    private static native DetectResult nativeGetImagesAndFaces(Object obj);

    private static native String nativeGetLibraryVersion();

    private native int nativeInitLicense(String str);

    private static native int nativeSetMotion(Object obj, int i);

    private static native int nativeWrapperAddSequentialInfo(Object obj, int i, String str);

    private static native int nativeWrapperBegin(Object obj, int i);

    private static native int nativeWrapperEnd(Object obj);

    private static native byte[] nativeWrapperGetResult(Object obj);

    private static native float nativeWrapperHacking(Object obj);

    /* access modifiers changed from: private */
    public static native DetectResult nativeWrapperInput(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, double d2);

    private static native int nativeWrapperSetStaticInfo(Object obj, int i, String str);

    /* access modifiers changed from: private */
    public static native DetectResult nativeWrapperTracking(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, boolean z, double d2);

    /* access modifiers changed from: private */
    public boolean setMotion(int i, boolean z) {
        boolean z2 = false;
        if (this.sStatus == 3) {
            this.mStartTime = -1;
            if (nativeSetMotion(this.mHandle, i) == 0) {
                z2 = true;
            }
            if (z && z2) {
                notifyMotionSet(this.mCurrentMotionIndex, i);
            }
        }
        return z2;
    }

    /* access modifiers changed from: protected */
    public void changeLibraryStatus(int i) {
        this.sStatus = i;
    }

    /* access modifiers changed from: protected */
    public void destroyWrapperHandle() {
        nativeDestroyWrapperHandle(this.mHandle);
    }

    /* access modifiers changed from: protected */
    public AbstractLivenessUtils getHttpUtils() {
        return new InteractiveLivenessHttpUtils();
    }

    /* access modifiers changed from: protected */
    public int getLibraryState() {
        return this.sStatus;
    }

    /* access modifiers changed from: protected */
    public String getLibraryVersion() {
        return nativeGetLibraryVersion();
    }

    /* access modifiers changed from: protected */
    public String getVersionName() {
        return "3.7.3";
    }

    /* access modifiers changed from: protected */
    public int initLicense(String str, String str2) {
        return nativeInitLicense(str);
    }

    /* access modifiers changed from: protected */
    public abstract void notifyAligned();

    /* access modifiers changed from: protected */
    public abstract void notifyMotionSet(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void onStatusUpdate(@FaceState int i, FaceOcclusion faceOcclusion, @FaceDistance int i2);

    /* access modifiers changed from: protected */
    public abstract void processDetectResult(ResultCode resultCode, DetectResult detectResult, long j);

    /* access modifiers changed from: protected */
    public void setBrowOcclusion(boolean z) {
        this.mNeedBrowOcclusion = z;
    }

    /* access modifiers changed from: 0000 */
    public ResultCode setMotionList(int[] iArr, @MotionComplexity int i) {
        this.mMotionList = checkMotions(iArr);
        if (this.mMotionList == null || this.mMotionList.length <= 0) {
            setState(new b());
            this.mStateWrapper = new a();
            return ResultCode.OK;
        } else if (i <= 0 || i > 4) {
            return ResultCode.STID_E_INVALID_ARGUMENTS;
        } else {
            ResultCode prepare = prepare(i);
            setState(new c());
            this.mStateWrapper = new d();
            this.mCurrentMotionIndex = 0;
            setMotion(this.mMotionList[0], true);
            return prepare;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void startLocalAntiHack(DetectResult detectResult);

    /* access modifiers changed from: 0000 */
    public boolean stopDetection() {
        this.mStartTime = -1;
        this.mFirstFrameTime = -1;
        return !stop();
    }

    /* access modifiers changed from: protected */
    public void wrapperAddSequentialInfo(int i, String str) {
        nativeWrapperAddSequentialInfo(this.mHandle, i, str);
    }

    /* access modifiers changed from: protected */
    public int wrapperBegin(int i) {
        return nativeWrapperBegin(this.mHandle, i);
    }

    /* access modifiers changed from: protected */
    public int wrapperEnd() {
        return nativeWrapperEnd(this.mHandle);
    }

    /* access modifiers changed from: protected */
    public DetectResult wrapperGetImagesAndFaces() {
        return nativeGetImagesAndFaces(this.mHandle);
    }

    /* access modifiers changed from: protected */
    public FaceOcclusion wrapperGetOcclusion() {
        return this.mStateWrapper == null ? new FaceOcclusion(0, 0, 0, 0) : this.mStateWrapper.getOcclusion();
    }

    /* access modifiers changed from: protected */
    public byte[] wrapperGetResult() {
        return nativeWrapperGetResult(this.mHandle);
    }

    /* access modifiers changed from: protected */
    public DetectResult wrapperInput(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d2) {
        if (this.mStateWrapper == null) {
            return null;
        }
        return this.mStateWrapper.wrapperInput(bArr, i, i2, i3, i4, i5, d2);
    }

    /* access modifiers changed from: protected */
    public float wrapperLocalHacking() {
        return nativeWrapperHacking(this.mHandle);
    }

    /* access modifiers changed from: protected */
    public void wrapperSetStaticInfo(int i, String str) {
        nativeWrapperSetStaticInfo(this.mHandle, i, str);
    }

    /* access modifiers changed from: protected */
    public boolean wrapperStateValid(int i) {
        if (this.mStateWrapper == null) {
            return false;
        }
        return this.mStateWrapper.isStateValid(i);
    }
}
