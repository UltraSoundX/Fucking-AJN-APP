package com.sensetime.senseid.sdk.liveness.interactive;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.sensetime.senseid.sdk.liveness.interactive.common.AbstractFinanceLibrary;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ModelType;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.PixelFormat;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.DeviceUtil;
import com.sensetime.senseid.sdk.liveness.interactive.type.BoundInfo;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.stub.StubApp;
import com.zhy.http.okhttp.OkHttpUtils;

abstract class a extends AbstractFinanceLibrary {
    private Context mApplicationContext = null;
    private String mCustomerId = null;
    long mDetectTimeout = OkHttpUtils.DEFAULT_MILLISECONDS;
    long mFirstFrameTime = -1;
    Object mHandle = null;
    /* access modifiers changed from: private */
    public boolean mIsAddInfo = false;
    private boolean mIsSensorListenerSet = false;
    private float mRateFaceClose = 0.8f;
    private float mRateFaceFar = 0.4f;
    private SensorEventListener mSensorListener = new SensorEventListener() {
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
            if (!a.this.mIsAddInfo) {
                switch (sensorEvent.sensor.getType()) {
                    case 1:
                        a.this.mSensorType = c.ACCLERATION.getValue();
                        a.this.mSequentialInfo = sensorEvent.values[0] + " " + sensorEvent.values[1] + " " + sensorEvent.values[2] + " ";
                        return;
                    case 2:
                        a.this.mSensorType = c.MAGNETIC_FIELD.getValue();
                        a.this.mSequentialInfo = sensorEvent.values[0] + " " + sensorEvent.values[1] + " " + sensorEvent.values[2] + " ";
                        return;
                    case 9:
                        a.this.mSensorType = c.GRAVITY.getValue();
                        a.this.mSequentialInfo = sensorEvent.values[0] + " " + sensorEvent.values[1] + " " + sensorEvent.values[2] + " ";
                        return;
                    case 11:
                        a.this.mSensorType = c.ROTATION_RATE.getValue();
                        a.this.mSequentialInfo = sensorEvent.values[0] + " " + sensorEvent.values[1] + " " + sensorEvent.values[2] + " ";
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private SensorManager mSensorManager = null;
    /* access modifiers changed from: private */
    public int mSensorType = -1;
    /* access modifiers changed from: private */
    public String mSequentialInfo = null;
    long mStartTime = -1;
    private LivenessState mState = null;

    a() {
    }

    private void addSequentialInfo(int i, String str) {
        if (getLibraryState() == 3 && i >= 0 && !TextUtils.isEmpty(str)) {
            wrapperAddSequentialInfo(i, str);
        }
    }

    private void generateFaceDistance(DetectResult detectResult, Rect rect) {
        int i = rect.right - rect.left;
        int i2 = rect.bottom - rect.top;
        float f = i < i2 ? ((float) (detectResult.right - detectResult.left)) / ((float) i) : ((float) (detectResult.bottom - detectResult.top)) / ((float) i2);
        int i3 = (this.mRateFaceFar <= 0.0f || f >= this.mRateFaceFar) ? (this.mRateFaceClose <= 0.0f || f <= this.mRateFaceClose) ? 0 : -1 : 1;
        detectResult.faceDistance = i3;
    }

    private void generateFaceState(DetectResult detectResult, Rect rect, BoundInfo boundInfo) {
        if (detectResult.faceCount <= 0) {
            detectResult.faceState = 1;
        } else if (rect == null) {
            detectResult.faceState = -1;
        } else {
            Rect rect2 = new Rect(detectResult.left, detectResult.top, detectResult.right, detectResult.bottom);
            if (!rect.contains(rect2)) {
                detectResult.faceState = 2;
                return;
            }
            if (boundInfo != null) {
                if (Math.sqrt(Math.pow((double) (rect2.centerY() - boundInfo.getY()), 2.0d) + Math.pow((double) (rect2.centerX() - boundInfo.getX()), 2.0d)) > ((double) boundInfo.getRadius())) {
                    detectResult.faceState = 2;
                    return;
                }
            }
            detectResult.faceOcclusion = wrapperGetOcclusion();
            detectResult.faceState = detectResult.faceOcclusion.isOcclusion() ? 3 : detectResult.faceState;
        }
    }

    private void registerSensorListener() {
        if (this.mSensorManager != null) {
            this.mSensorManager.registerListener(this.mSensorListener, this.mSensorManager.getDefaultSensor(1), 3);
            this.mSensorManager.registerListener(this.mSensorListener, this.mSensorManager.getDefaultSensor(11), 3);
            this.mSensorManager.registerListener(this.mSensorListener, this.mSensorManager.getDefaultSensor(9), 3);
            this.mSensorManager.registerListener(this.mSensorListener, this.mSensorManager.getDefaultSensor(2), 3);
            this.mIsSensorListenerSet = true;
        }
    }

    private void setStaticInfo(int i, String str) {
        if (getLibraryState() == 3 && i >= 0 && !TextUtils.isEmpty(str)) {
            wrapperSetStaticInfo(i, str);
        }
    }

    private ResultCode start(int i) {
        if (getLibraryState() != 2 && getLibraryState() != 4 && getLibraryState() != 3) {
            return ResultCode.STID_E_CALL_API_IN_WRONG_STATE;
        }
        if (wrapperBegin(i) == 0) {
            changeLibraryStatus(3);
            if (!this.mIsSensorListenerSet) {
                registerSensorListener();
            }
            setStaticInfo(d.DEVICE.getKeyValue(), Build.MODEL);
            setStaticInfo(d.OS.getKeyValue(), "Android");
            setStaticInfo(d.SDK_VERSION.getKeyValue(), getVersionName());
            setStaticInfo(d.SYS_VERSION.getKeyValue(), VERSION.RELEASE);
            setStaticInfo(d.IS_ROOT.getKeyValue(), String.valueOf(DeviceUtil.isRoot()));
            setStaticInfo(d.CUSTOMER_ID.getKeyValue(), this.mCustomerId);
            return ResultCode.OK;
        }
        changeLibraryStatus(-1);
        return ResultCode.STID_E_CHECK_CONFIG_FAIL;
    }

    private void unregisterSensorListener() {
        if (this.mSensorManager != null) {
            this.mSensorManager.unregisterListener(this.mSensorListener);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void cancel();

    /* access modifiers changed from: protected */
    public int createHandleMethod(String... strArr) {
        return createWrapperHandle(strArr);
    }

    /* access modifiers changed from: protected */
    public abstract int createWrapperHandle(String... strArr);

    /* access modifiers changed from: protected */
    public abstract void destroyWrapperHandle();

    /* access modifiers changed from: 0000 */
    public DetectResult getImagesAndFaces() {
        if (getLibraryState() != 4) {
            return null;
        }
        return wrapperGetImagesAndFaces();
    }

    /* access modifiers changed from: 0000 */
    public byte[] getResult() {
        if (getLibraryState() != 4) {
            return null;
        }
        return wrapperGetResult();
    }

    /* access modifiers changed from: protected */
    public abstract String getVersionName();

    /* access modifiers changed from: 0000 */
    public ResultCode init(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (context != null) {
            this.mSensorManager = (SensorManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("sensor");
            this.mCustomerId = context.getPackageName();
            this.mApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        ResultCode checkLicense = checkLicense(str, str2);
        if (checkLicense != ResultCode.OK) {
            return checkLicense;
        }
        this.mStartTime = -1;
        return createHandle(new ModelType(str3, ResultCode.STID_E_DETECTION_MODEL_FILE_NOT_FOUND), new ModelType(str4, ResultCode.STID_E_ALIGNMENT_MODEL_FILE_NOT_FOUND), new ModelType(str5, ResultCode.STID_E_FRAME_SELECTOR_MODEL_FILE_NOT_FOUND), new ModelType(str6, ResultCode.STID_E_ANTI_SPOOFING_MODEL_FILE_NOT_FOUND));
    }

    /* access modifiers changed from: 0000 */
    public void inputData(byte[] bArr, PixelFormat pixelFormat, Size size, Rect rect, boolean z, int i, BoundInfo boundInfo) {
        if (this.mHandle != null && bArr != null && bArr.length > 0 && size != null && rect != null && i >= 0 && wrapperStateValid(getLibraryState())) {
            if (this.mStartTime < 0) {
                this.mStartTime = SystemClock.elapsedRealtime();
            }
            if (this.mFirstFrameTime < 0) {
                this.mFirstFrameTime = SystemClock.elapsedRealtime();
            }
            int i2 = i / 90;
            if (this.mIsSensorListenerSet) {
                this.mIsAddInfo = true;
                addSequentialInfo(this.mSensorType, this.mSequentialInfo);
                this.mIsAddInfo = false;
            }
            DetectResult wrapperInput = wrapperInput(bArr, pixelFormat.getCode(), size.getWidth(), size.getHeight(), size.getWidth() * pixelFormat.getStride(), i2, ((double) System.currentTimeMillis()) / 1000.0d);
            if (wrapperInput.faceCount > 0) {
                generateFaceDistance(wrapperInput, rect);
            }
            generateFaceState(wrapperInput, rect, boundInfo);
            this.mState.checkResult(wrapperInput);
        }
    }

    /* access modifiers changed from: 0000 */
    public ResultCode prepare(int i) {
        ResultCode start = start(i);
        return start != ResultCode.OK ? start : ResultCode.OK;
    }

    /* access modifiers changed from: 0000 */
    public void release() {
        if (this.mHandle != null) {
            this.mFirstFrameTime = -1;
            if (getLibraryState() == 2 || getLibraryState() == 3 || getLibraryState() == 4 || getLibraryState() == -1) {
                stop();
                destroyWrapperHandle();
                this.mHandle = null;
                changeLibraryStatus(0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void setDetectTimeout(long j);

    /* access modifiers changed from: 0000 */
    public void setState(LivenessState livenessState) {
        this.mState = livenessState;
    }

    /* access modifiers changed from: protected */
    public boolean stop() {
        if (getLibraryState() != 3) {
            return false;
        }
        int wrapperEnd = wrapperEnd();
        unregisterSensorListener();
        if (wrapperEnd == 0) {
            changeLibraryStatus(4);
            return true;
        }
        changeLibraryStatus(-1);
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void wrapperAddSequentialInfo(int i, String str);

    /* access modifiers changed from: protected */
    public abstract int wrapperBegin(int i);

    /* access modifiers changed from: protected */
    public abstract int wrapperEnd();

    /* access modifiers changed from: protected */
    public abstract DetectResult wrapperGetImagesAndFaces();

    /* access modifiers changed from: protected */
    public abstract FaceOcclusion wrapperGetOcclusion();

    /* access modifiers changed from: protected */
    public abstract byte[] wrapperGetResult();

    /* access modifiers changed from: protected */
    public abstract DetectResult wrapperInput(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d);

    /* access modifiers changed from: protected */
    public abstract void wrapperSetStaticInfo(int i, String str);

    /* access modifiers changed from: protected */
    public abstract boolean wrapperStateValid(int i);
}
