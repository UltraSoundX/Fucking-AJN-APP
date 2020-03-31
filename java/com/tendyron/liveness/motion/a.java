package com.tendyron.liveness.motion;

import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;

/* compiled from: ActivityUtils */
public class a {
    public static final int a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    public static final int g = 8;
    public static final int h = 9;
    public static final int i = 10;
    public static final int j = 11;
    public static final int k = 12;
    public static final int l = 13;
    public static final int m = 14;
    public static final int n = 15;
    public static final int o = 16;
    public static final int p = 17;

    /* renamed from: q reason: collision with root package name */
    public static final int f438q = 18;
    public static final int r = 19;
    public static final int s = 20;
    public static final int t = 21;

    static int a(ResultCode resultCode) {
        switch (resultCode) {
            case STID_E_LICENSE_FILE_NOT_FOUND:
                return 4;
            case STID_E_CALL_API_IN_WRONG_STATE:
                return 5;
            case STID_E_LICENSE_EXPIRE:
                return 6;
            case STID_E_LICENSE_VERSION_MISMATCH:
                return 17;
            case STID_E_LICENSE_PLATFORM_NOT_SUPPORTED:
                return 18;
            case STID_E_LICENSE_BUNDLE_ID_INVALID:
                return 7;
            case STID_E_LICENSE_INVALID:
                return 8;
            case STID_E_MODEL_INVALID:
                return 10;
            case STID_E_MODEL_FILE_NOT_FOUND:
                return 11;
            case STID_E_API_KEY_INVALID:
                return 12;
            case STID_E_MODEL_EXPIRE:
                return 13;
            case STID_E_TIMEOUT:
                return 9;
            case STID_E_SERVER_ACCESS:
                return 14;
            case STID_E_HACK:
            case STID_E_DETECT_FAIL:
            case STID_E_CHECK_CONFIG_FAIL:
                return 15;
            case STID_E_NOFACE_DETECTED:
                return 16;
            case STID_E_FACE_COVERED:
                return 19;
            case STID_E_SERVER_TIMEOUT:
                return 20;
            case STID_E_INVALID_ARGUMENTS:
                return 21;
            default:
                return -1;
        }
    }
}
