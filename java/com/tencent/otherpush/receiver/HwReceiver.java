package com.tencent.otherpush.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: ProGuard */
public class HwReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                String action = intent.getAction();
                if ("com.huawei.android.push.intent.REGISTRATION".equals(action)) {
                    byte[] byteArrayExtra = intent.getByteArrayExtra("device_token");
                    if (byteArrayExtra != null) {
                        new String(byteArrayExtra, "UTF-8");
                        return;
                    }
                    return;
                }
                if ("com.huawei.android.push.intent.RECEIVE".equals(action) || "com.huawei.intent.action.PUSH_STATE".equals(action)) {
                }
            } catch (Throwable th) {
            }
        }
    }
}
