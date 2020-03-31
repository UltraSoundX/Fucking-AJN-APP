package com.sensetime.senseid.sdk.liveness.interactive.common;

import android.text.TextUtils;
import com.sensetime.senseid.sdk.liveness.interactive.common.network.AbstractHttpUtils;
import com.sensetime.senseid.sdk.liveness.interactive.common.network.HttpResult;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.AbstractContentType;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ModelType;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.FileUtil;
import java.io.File;

public abstract class AbstractFinanceLibrary {
    protected String mApiKey = null;
    protected String mApiSecret = null;

    /* access modifiers changed from: protected */
    public abstract void changeLibraryStatus(int i);

    /* access modifiers changed from: protected */
    public ResultCode checkLicense(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return ResultCode.STID_E_LICENSE_FILE_NOT_FOUND;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory() || !file.isFile()) {
            return ResultCode.STID_E_LICENSE_FILE_NOT_FOUND;
        }
        String stringFromFile = FileUtil.getStringFromFile(str);
        if (TextUtils.isEmpty(stringFromFile)) {
            return ResultCode.STID_E_LICENSE_INVALID;
        }
        if (getLibraryState() != 0) {
            return ResultCode.STID_E_CALL_API_IN_WRONG_STATE;
        }
        int initLicense = initLicense(stringFromFile, str2);
        if (initLicense == 0 || initLicense == -2067857409) {
            changeLibraryStatus(1);
            return ResultCode.OK;
        }
        switch (initLicense) {
            case -24:
                return ResultCode.STID_E_LICENSE_PLATFORM_NOT_SUPPORTED;
            case -23:
                return ResultCode.STID_E_LICENSE_VERSION_MISMATCH;
            case -15:
                return ResultCode.STID_E_LICENSE_EXPIRE;
            case -14:
                return ResultCode.STID_E_LICENSE_BUNDLE_ID_INVALID;
            case -9:
                return ResultCode.STID_E_MODEL_EXPIRE;
            case -7:
                return ResultCode.STID_E_LICENSE_FILE_NOT_FOUND;
            default:
                return ResultCode.STID_E_LICENSE_INVALID;
        }
    }

    /* access modifiers changed from: protected */
    public ResultCode createHandle(ModelType... modelTypeArr) {
        if (modelTypeArr == null) {
            return ResultCode.STID_E_INVALID_ARGUMENTS;
        }
        String[] strArr = new String[modelTypeArr.length];
        for (int i = 0; i < modelTypeArr.length; i++) {
            String modelFilePath = modelTypeArr[i].getModelFilePath();
            if (TextUtils.isEmpty(modelFilePath)) {
                return modelTypeArr[i].getErrorCode();
            }
            File file = new File(modelFilePath);
            if (!file.exists() || file.isDirectory() || !file.isFile()) {
                return modelTypeArr[i].getErrorCode();
            }
            strArr[i] = modelFilePath;
        }
        if (getLibraryState() != 1) {
            return ResultCode.STID_E_CALL_API_IN_WRONG_STATE;
        }
        int createHandleMethod = createHandleMethod(strArr);
        if (createHandleMethod == 0) {
            changeLibraryStatus(2);
            return ResultCode.OK;
        } else if (createHandleMethod == -2067857410) {
            changeLibraryStatus(0);
            return ResultCode.STID_E_CAPABILITY_NOT_SUPPORTED;
        } else {
            changeLibraryStatus(0);
            return createHandleMethod == -7 ? ResultCode.STID_E_MODEL_FILE_NOT_FOUND : ResultCode.STID_E_MODEL_INVALID;
        }
    }

    /* access modifiers changed from: protected */
    public ResultCode createHandle(String... strArr) {
        for (String isEmpty : strArr) {
            if (TextUtils.isEmpty(isEmpty)) {
                return ResultCode.STID_E_MODEL_FILE_NOT_FOUND;
            }
        }
        if (getLibraryState() != 1) {
            return ResultCode.STID_E_CALL_API_IN_WRONG_STATE;
        }
        int createHandleMethod = createHandleMethod(strArr);
        if (createHandleMethod == 0) {
            changeLibraryStatus(2);
            return ResultCode.OK;
        } else if (createHandleMethod == -2067857410) {
            changeLibraryStatus(0);
            return ResultCode.STID_E_CAPABILITY_NOT_SUPPORTED;
        } else {
            changeLibraryStatus(0);
            return createHandleMethod == -7 ? ResultCode.STID_E_MODEL_FILE_NOT_FOUND : ResultCode.STID_E_MODEL_INVALID;
        }
    }

    /* access modifiers changed from: protected */
    public abstract int createHandleMethod(String... strArr);

    /* access modifiers changed from: protected */
    public abstract AbstractHttpUtils getHttpUtils();

    /* access modifiers changed from: protected */
    public abstract int getLibraryState();

    /* access modifiers changed from: protected */
    public abstract String getLibraryVersion();

    /* access modifiers changed from: protected */
    public abstract int initLicense(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void notifyNetworkBegin();

    /* access modifiers changed from: protected */
    public abstract void onNetworkFinished(HttpResult httpResult, AbstractContentType abstractContentType);

    /* access modifiers changed from: protected */
    public abstract void releaseReferences();
}
