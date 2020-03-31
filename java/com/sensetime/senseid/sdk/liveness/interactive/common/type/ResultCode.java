package com.sensetime.senseid.sdk.liveness.interactive.common.type;

import android.support.annotation.Keep;

@Keep
public enum ResultCode {
    OK,
    STID_E_CALL_API_IN_WRONG_STATE,
    STID_E_LICENSE_INVALID,
    STID_E_MODEL_INVALID,
    STID_E_LICENSE_FILE_NOT_FOUND,
    STID_E_LICENSE_BUNDLE_ID_INVALID,
    STID_E_LICENSE_EXPIRE,
    STID_E_LICENSE_VERSION_MISMATCH,
    STID_E_LICENSE_PLATFORM_NOT_SUPPORTED,
    STID_E_MODEL_FILE_NOT_FOUND,
    STID_E_DETECTION_MODEL_FILE_NOT_FOUND,
    STID_E_ALIGNMENT_MODEL_FILE_NOT_FOUND,
    STID_E_FRAME_SELECTOR_MODEL_FILE_NOT_FOUND,
    STID_E_ANTI_SPOOFING_MODEL_FILE_NOT_FOUND,
    STID_E_API_KEY_INVALID,
    STID_E_MODEL_EXPIRE,
    STID_E_TIMEOUT,
    STID_E_SERVER_ACCESS,
    STID_E_CHECK_CONFIG_FAIL,
    STID_E_NOFACE_DETECTED,
    STID_E_FACE_COVERED,
    STID_E_DETECT_FAIL,
    STID_E_HACK,
    STID_E_SERVER_TIMEOUT,
    STID_E_SERVER_DETECT_FAIL,
    STID_E_INVALID_ARGUMENTS,
    STID_E_CAPABILITY_NOT_SUPPORTED
}
