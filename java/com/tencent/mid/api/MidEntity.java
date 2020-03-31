package com.tencent.mid.api;

import com.tencent.mid.util.Logger;
import com.tencent.mid.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class MidEntity {
    public static final String TAG_GUID = "guid";
    public static final String TAG_IMEI = "imei";
    public static final String TAG_IMSI = "imsi";
    public static final String TAG_MAC = "mac";
    public static final String TAG_MID = "mid";
    public static final String TAG_TIMESTAMPS = "ts";
    public static final String TAG_VER = "ver";
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_NEW = 2;
    private static Logger logger = Util.getLogger();
    private long guid = 0;
    private String imei = null;
    private String imsi = null;
    private String mac = null;
    private String mid = "0";
    private long timestamps = 0;
    private int version = 0;

    public long getGuid() {
        return this.guid;
    }

    public void setGuid(long j) {
        this.guid = j;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public long getTimestamps() {
        return this.timestamps;
    }

    public void setTimestamps(long j) {
        this.timestamps = j;
    }

    public boolean isMidValid() {
        return Util.isMidValid(this.mid);
    }

    /* access modifiers changed from: 0000 */
    public void update(String str) {
        setMid(str);
    }

    public static MidEntity parse(String str) {
        MidEntity midEntity = new MidEntity();
        if (Util.isStringValid(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(TAG_IMEI)) {
                    midEntity.setImei(jSONObject.getString(TAG_IMEI));
                }
                if (!jSONObject.isNull(TAG_IMSI)) {
                    midEntity.setImsi(jSONObject.getString(TAG_IMSI));
                }
                if (!jSONObject.isNull(TAG_MAC)) {
                    midEntity.setMac(jSONObject.getString(TAG_MAC));
                }
                if (!jSONObject.isNull("mid")) {
                    midEntity.setMid(jSONObject.getString("mid"));
                }
                if (!jSONObject.isNull(TAG_TIMESTAMPS)) {
                    midEntity.setTimestamps(jSONObject.getLong(TAG_TIMESTAMPS));
                }
                if (!jSONObject.isNull(TAG_VER)) {
                    midEntity.version = jSONObject.optInt(TAG_VER, 0);
                }
                if (!jSONObject.isNull("guid")) {
                    midEntity.guid = jSONObject.optLong("guid", 0);
                }
            } catch (JSONException e) {
                logger.w(e.toString());
            }
        }
        return midEntity;
    }

    public int compairTo(MidEntity midEntity) {
        if (midEntity == null) {
            return 1;
        }
        if (!isMidValid() || !midEntity.isMidValid()) {
            if (!isMidValid()) {
                return -1;
            }
            return 1;
        } else if (this.mid.equals(midEntity.mid)) {
            return 0;
        } else {
            if (this.timestamps < midEntity.timestamps) {
                return -1;
            }
            return 1;
        }
    }

    public String toString() {
        return encode().toString();
    }

    /* access modifiers changed from: 0000 */
    public JSONObject encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            Util.jsonPut(jSONObject, TAG_IMEI, this.imei);
            Util.jsonPut(jSONObject, TAG_IMSI, this.imsi);
            Util.jsonPut(jSONObject, TAG_MAC, this.mac);
            Util.jsonPut(jSONObject, "mid", this.mid);
            try {
                jSONObject.put("guid", this.guid);
            } catch (Throwable th) {
            }
            jSONObject.put(TAG_TIMESTAMPS, this.timestamps);
        } catch (JSONException e) {
            logger.w(e.toString());
        }
        return jSONObject;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }
}
