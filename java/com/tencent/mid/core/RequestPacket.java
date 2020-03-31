package com.tencent.mid.core;

import android.content.Context;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.sotrage.UnifiedStorage;
import com.tencent.mid.util.Env;
import com.tencent.mid.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestPacket extends PacketInterface {
    protected RequestPacket(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public int getType() {
        return 2;
    }

    private String getJsonStr(String str) {
        return !Util.isEmpty(str) ? str : "-";
    }

    /* access modifiers changed from: protected */
    public void onEncode(JSONObject jSONObject) throws JSONException {
        jSONObject.put("mid", "0");
        jSONObject.put(MidEntity.TAG_IMEI, getJsonStr(CustomDeviceInfos.getDeviceId(this.context)));
        jSONObject.put(MidEntity.TAG_IMSI, getJsonStr(CustomDeviceInfos.getImsi(this.context)));
        jSONObject.put(MidEntity.TAG_MAC, getJsonStr(CustomDeviceInfos.getMacAddress(this.context)));
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, System.currentTimeMillis() / 1000);
        MidEntity localMidEntity = ServiceIMPL.getLocalMidEntity(this.context);
        if (localMidEntity != null && localMidEntity.isMidValid()) {
            jSONObject.put("mid", localMidEntity.getMid());
        }
        String readNewVersionMidStr = UnifiedStorage.getInstance(this.context).readNewVersionMidStr();
        if (Util.isMidValid(readNewVersionMidStr)) {
            jSONObject.put(MidConstants.NEW_MID_TAG, readNewVersionMidStr);
        } else {
            jSONObject.put(MidConstants.NEW_MID_TAG, "0");
        }
        try {
            new Env(this.context).encode(jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
