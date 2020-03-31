package com.sensetime.senseid.sdk.liveness.interactive.common.type;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.DeviceUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractContentType {
    protected static final String PARAM_DURATION = "duration";
    protected static final String PARAM_RESULT = "result";
    protected static final String PARAM_SDK_VERSION = "sdk_ver";
    public static final int RESULT_MISS = 3;
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_TIMEOUT = 2;

    /* access modifiers changed from: protected */
    public JSONObject generateCommonContentJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.DEVICE_PART, URLEncoder.encode(Build.MANUFACTURER + "-" + Build.DEVICE, "UTF-8"));
            jSONObject.put("platform", 1);
            jSONObject.put("sys_ver", VERSION.RELEASE);
            jSONObject.put("root", DeviceUtil.isRoot());
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public abstract JSONObject generateContentJson(Context context, long j, int i, String str);
}
