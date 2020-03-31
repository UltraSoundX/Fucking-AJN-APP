package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.common.Constants;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGPushRegisterResult implements XGIResult {
    long a = 0;
    String b = "";
    String c = "";
    String d = "";
    short e = 0;
    String f = "";
    String g = "";
    int h = 0;

    public long getAccessId() {
        return this.a;
    }

    public String getDeviceId() {
        return this.b;
    }

    public String getAccount() {
        return this.c;
    }

    public String getTicket() {
        return this.d;
    }

    public short getTicketType() {
        return this.e;
    }

    public String getToken() {
        return this.f;
    }

    public String getOtherPushToken() {
        return this.g;
    }

    public int getPushChannel() {
        return this.h;
    }

    XGPushRegisterResult() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TPushRegisterMessage [accessId=").append(this.a).append(", deviceId=").append(this.b).append(", account=").append(this.c).append(", ticket=").append(this.d).append(", ticketType=").append(this.e).append(", token=").append(this.f).append("]");
        return sb.toString();
    }

    public void parseIntent(Intent intent) {
        try {
            this.a = intent.getLongExtra("accId", -1);
            this.b = intent.getStringExtra(Constants.FLAG_DEVICE_ID);
            this.c = intent.getStringExtra(Constants.FLAG_ACCOUNT);
            this.d = intent.getStringExtra(Constants.FLAG_TICKET);
            this.e = intent.getShortExtra(Constants.FLAG_TICKET_TYPE, 0);
            this.f = intent.getStringExtra(Constants.FLAG_TOKEN);
        } catch (Throwable th) {
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.FLAG_ACCOUNT, this.c);
            jSONObject.put(Constants.FLAG_TICKET, this.d);
            jSONObject.put(Constants.FLAG_DEVICE_ID, this.b);
            jSONObject.put(Constants.FLAG_TICKET_TYPE, this.e);
            jSONObject.put(Constants.FLAG_TOKEN, this.f);
        } catch (Throwable th) {
        }
        return jSONObject;
    }
}
