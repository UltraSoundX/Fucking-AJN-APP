package com.sensetime.senseid.sdk.liveness.interactive;

import android.content.Context;
import android.util.Base64;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.AbstractContentType;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceDistance;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceState;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

final class DetectResult extends AbstractContentType {
    private static final String PARAM_DATA = "data";
    private static final String PARAM_LIVENESS_ID = "liveness_id";
    private static final String PARAM_SILENT_MODE = "silent_mode";
    int bottom;
    double browScore;
    float coveredScore;
    double eyeScore;
    int faceCount;
    @FaceDistance
    int faceDistance;
    int faceId;
    FaceOcclusion faceOcclusion;
    @FaceState
    int faceState;
    float hacknessScore;
    List<byte[]> images;
    int left;
    int message;
    double mouthScore;
    double noseScore;
    boolean passed;
    byte[] protobuf;
    int right;
    int top;

    DetectResult() {
    }

    public final JSONObject generateAntihackJson(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PARAM_LIVENESS_ID, str);
            jSONObject.put(PARAM_SILENT_MODE, z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final JSONObject generateContentJson(Context context, long j, int i, String str) {
        JSONObject generateCommonContentJson = generateCommonContentJson();
        JSONObject jSONObject = generateCommonContentJson == null ? new JSONObject() : generateCommonContentJson;
        try {
            jSONObject.put("duration", j);
            jSONObject.put("result", i);
            jSONObject.put(PARAM_DATA, (this.protobuf == null || this.protobuf.length <= 0) ? "" : Base64.encodeToString(this.protobuf, 0));
            jSONObject.put("sdk_ver", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String toString() {
        return "DetectResult[Passed: " + this.passed + ", Message: " + this.message + ", Score: " + this.hacknessScore + ", Count: " + this.faceCount + ", Left: " + this.left + ", Top: " + this.top + ", Right: " + this.right + ", Bottom: " + this.bottom + ", ID: " + this.faceId + ", Distance: " + this.faceDistance + ", State: " + this.faceState + ", OcclusionScore: " + this.coveredScore + ", brow occlusionScore:" + this.browScore + ", eye occlusionScore: " + this.eyeScore + ", nose occlusionScore: " + this.noseScore + "mouth occlusionScore: " + this.mouthScore + "]";
    }
}
