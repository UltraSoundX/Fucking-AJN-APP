package com.tencent.mid.sotrage;

import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Logger;
import com.tencent.mid.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckEntity {
    public static String DAYS = "mdays";
    public static String FREQ = "mfreq";
    public static String TIMES = "times";
    public static String TS = MidEntity.TAG_TIMESTAMPS;
    private static Logger logger = Util.getLogger();
    private int lastCheckTimes = 1;
    private long lastCheckTimestamps = 0;
    private int maxDays = 3;
    private int maxFreq = 1024;

    public CheckEntity() {
    }

    public CheckEntity(String str) {
        if (Util.isStringValid(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(TS)) {
                    this.lastCheckTimestamps = jSONObject.getLong(TS);
                }
                if (!jSONObject.isNull(FREQ)) {
                    this.maxFreq = jSONObject.getInt(FREQ);
                }
                if (!jSONObject.isNull(TIMES)) {
                    this.lastCheckTimes = jSONObject.getInt(TIMES);
                }
                if (!jSONObject.isNull(DAYS)) {
                    this.maxDays = jSONObject.getInt(DAYS);
                }
            } catch (JSONException e) {
                logger.w(e.toString());
            }
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TS, this.lastCheckTimestamps);
            jSONObject.put(TIMES, this.lastCheckTimes);
            jSONObject.put(FREQ, this.maxFreq);
            jSONObject.put(DAYS, this.maxDays);
        } catch (JSONException e) {
            logger.w(e.toString());
        }
        return jSONObject.toString();
    }

    public int getMaxDays() {
        return this.maxDays;
    }

    public void setMaxDays(int i) {
        this.maxDays = i;
    }

    public long getLastCheckTimestamps() {
        return this.lastCheckTimestamps;
    }

    public void setLastCheckTimestamps(long j) {
        this.lastCheckTimestamps = j;
    }

    public int getLastCheckTimes() {
        return this.lastCheckTimes;
    }

    public void setLastCheckTimes(int i) {
        this.lastCheckTimes = i;
    }

    public int getMaxFreq() {
        return this.maxFreq;
    }

    public void setMaxFreq(int i) {
        this.maxFreq = i;
    }
}
