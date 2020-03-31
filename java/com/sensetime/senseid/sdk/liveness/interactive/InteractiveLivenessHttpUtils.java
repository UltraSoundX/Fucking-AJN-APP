package com.sensetime.senseid.sdk.liveness.interactive;

import com.sensetime.senseid.sdk.liveness.interactive.common.network.AbstractLivenessUtils;

class InteractiveLivenessHttpUtils extends AbstractLivenessUtils {
    InteractiveLivenessHttpUtils() {
    }

    private static native String nativeGetBundleId();

    private static native String nativeGetSignature(String str, String str2, String str3, String str4, String str5);

    /* access modifiers changed from: protected */
    public String getBundleId() {
        return nativeGetBundleId();
    }

    /* access modifiers changed from: protected */
    public String getHost() {
        return "v2-auth-api.visioncloudapi.com";
    }

    /* access modifiers changed from: protected */
    public String getSignature(String str, String str2, String str3, String str4, String str5) {
        return nativeGetSignature(str, str2, str3, str4, str5);
    }
}
