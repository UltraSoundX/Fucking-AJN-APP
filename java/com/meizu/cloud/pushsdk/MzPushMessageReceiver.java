package com.meizu.cloud.pushsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* compiled from: ProGuard */
public class MzPushMessageReceiver extends BroadcastReceiver {
    public static final String TAG = "MzPushMessageReceiver";

    public void onHandleIntent(Context context, Intent intent) {
    }

    public void onRegister(Context context, String str) {
        Log.i(TAG, "onRegister " + str);
    }

    public void onMessage(Context context, String str) {
        Log.i(TAG, "onMessage " + str);
    }

    public void onMessage(Context context, Intent intent) {
        Log.i(TAG, "onMessage " + intent);
    }

    @Deprecated
    public void onUnRegister(Context context, boolean z) {
        Log.i(TAG, "onUnRegister " + z);
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
        Log.i(TAG, "onNotificationClicked " + str + str2 + str3);
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
        Log.i(TAG, "onNotificationArrived " + str + str2 + str3);
    }

    public void onNotificationDeleted(Context context, String str, String str2, String str3) {
        Log.i(TAG, "onNotificationDeleted " + str + str2 + str3);
    }

    public void onNotifyMessageArrived(Context context, String str) {
        Log.i(TAG, "onNotifyMessageArrived " + str);
    }

    public void onPushStatus(Context context) {
        Log.i(TAG, "onPushStatus ");
    }

    public void onRegisterStatus(Context context) {
        Log.i(TAG, "onRegisterStatus ");
    }

    public void onUnRegisterStatus(Context context) {
        Log.i(TAG, "onUnRegisterStatus ");
    }

    public void onSubTagsStatus(Context context) {
        Log.i(TAG, "onSubTagsStatus ");
    }

    public void onSubAliasStatus(Context context) {
        Log.i(TAG, "onSubAliasStatus ");
    }

    public void onUpdateNotificationBuilder() {
        Log.i(TAG, "onUpdateNotificationBuilder ");
    }

    public void onReceive(Context context, Intent intent) {
    }
}
