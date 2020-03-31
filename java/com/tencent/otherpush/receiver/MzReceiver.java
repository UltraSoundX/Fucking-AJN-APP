package com.tencent.otherpush.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;

/* compiled from: ProGuard */
public class MzReceiver extends MzPushMessageReceiver {
    public void onHandleIntent(Context context, Intent intent) {
    }

    public void onRegister(Context context, String str) {
        Log.i("MzReceiver", "onRegister " + str);
    }

    public void onMessage(Context context, String str) {
        Log.i("MzReceiver", "onMessage " + str);
    }

    public void onMessage(Context context, Intent intent) {
        Log.i("MzReceiver", "onMessage " + intent);
    }

    @Deprecated
    public void onUnRegister(Context context, boolean z) {
        Log.i("MzReceiver", "onUnRegister " + z);
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
        Log.i("MzReceiver", "onNotificationClicked " + str + str2 + str3);
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
        Log.i("MzReceiver", "onNotificationArrived " + str + str2 + str3);
    }

    public void onNotificationDeleted(Context context, String str, String str2, String str3) {
        Log.i("MzReceiver", "onNotificationDeleted " + str + str2 + str3);
    }

    public void onNotifyMessageArrived(Context context, String str) {
        Log.i("MzReceiver", "onNotifyMessageArrived " + str);
    }

    public void onPushStatus(Context context) {
        Log.i("MzReceiver", "onPushStatus ");
    }

    public void onRegisterStatus(Context context) {
        Log.i("MzReceiver", "onRegisterStatus ");
    }

    public void onUnRegisterStatus(Context context) {
        Log.i("MzReceiver", "onUnRegisterStatus ");
    }

    public void onSubTagsStatus(Context context) {
        Log.i("MzReceiver", "onSubTagsStatus ");
    }

    public void onSubAliasStatus(Context context) {
        Log.i("MzReceiver", "onSubAliasStatus ");
    }

    public void onUpdateNotificationBuilder() {
        Log.i("MzReceiver", "onUpdateNotificationBuilder ");
    }

    public void onReceive(Context context, Intent intent) {
    }
}
