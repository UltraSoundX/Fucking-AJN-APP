package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.encrypt.Rijndael;

/* compiled from: ProGuard */
public class XGPushShowedResult implements XGIResult {
    public static final int NOTIFICATION_ACTION_ACTIVITY = NotificationAction.activity.getType();
    public static final int NOTIFICATION_ACTION_INTENT = NotificationAction.intent.getType();
    public static final int NOTIFICATION_ACTION_INTENT_WIHT_ACTION = NotificationAction.intent_with_action.getType();
    public static final int NOTIFICATION_ACTION_PACKAGE = NotificationAction.action_package.getType();
    public static final int NOTIFICATION_ACTION_URL = NotificationAction.url.getType();
    long a = 0;
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    int f = 0;
    int g = NotificationAction.activity.getType();
    int h = 0;

    public int getPushChannel() {
        return this.h;
    }

    public int getNotifactionId() {
        return this.f;
    }

    public long getMsgId() {
        return this.a;
    }

    public String getTitle() {
        return this.b;
    }

    public String getContent() {
        return this.c;
    }

    public String getCustomContent() {
        return this.d;
    }

    public String getActivity() {
        return this.e;
    }

    public int getNotificationActionType() {
        return this.g;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("XGPushShowedResult [msgId=").append(this.a).append(", title=").append(this.b).append(", content=").append(this.c).append(", customContent=").append(this.d).append(", activity=").append(this.e).append(", notificationActionType").append(this.g).append("]");
        return sb.toString();
    }

    public void parseIntent(Intent intent) {
        this.a = intent.getLongExtra(MessageKey.MSG_ID, -1);
        this.e = intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME);
        this.b = Rijndael.decrypt(intent.getStringExtra("title"));
        this.c = Rijndael.decrypt(intent.getStringExtra("content"));
        this.g = intent.getIntExtra(Constants.FLAG_NOTIFICATION_ACTION_TYPE, NotificationAction.activity.getType());
        this.d = Rijndael.decrypt(intent.getStringExtra("custom_content"));
        this.f = intent.getIntExtra(MessageKey.NOTIFACTION_ID, 0);
    }
}
