package com.sensetime.senseid.sdk.liveness.interactive.common.network;

import com.sensetime.senseid.sdk.liveness.interactive.common.type.AbstractContentType;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;

public abstract class AbstractLivenessUtils extends AbstractHttpUtils {
    public void checkAntihackSync(HttpListener httpListener, String str, String str2, String str3, String str4, String str5, AbstractContentType abstractContentType) {
        sendRequestSync(httpListener, str, str2, str3, str4, str5, abstractContentType, NetworkUtil.NETWORK_MOBILE, "liveness", "antihack_detection");
    }

    public void checkLivenessIdAsync(String str, String str2, String str3, String str4, String str5, AbstractContentType abstractContentType) {
        sendRequestAsync(str, str2, str3, str4, str5, abstractContentType, NetworkUtil.NETWORK_MOBILE, "resource", "liveness_data", "file");
    }

    public void checkLivenessIdSync(HttpListener httpListener, String str, String str2, String str3, String str4, String str5, AbstractContentType abstractContentType) {
        sendRequestSync(httpListener, str, str2, str3, str4, str5, abstractContentType, NetworkUtil.NETWORK_MOBILE, "resource", "liveness_data", "file");
    }
}
