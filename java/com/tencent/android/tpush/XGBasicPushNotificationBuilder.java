package com.tencent.android.tpush;

import android.app.Notification;
import android.content.Context;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGBasicPushNotificationBuilder extends XGPushNotificationBuilder {
    public Notification buildNotification(Context context) {
        return a(context);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
    }

    /* access modifiers changed from: protected */
    public void b(JSONObject jSONObject) {
    }

    public String getType() {
        return XGPushNotificationBuilder.BASIC_NOTIFICATION_BUILDER_TYPE;
    }
}
