package com.e23.ajn.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import java.util.List;

/* compiled from: SystemUtils */
public class t {
    public static boolean a(Context context, String str) {
        List runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            if (((RunningAppProcessInfo) runningAppProcesses.get(i)).processName.equals(str)) {
                Log.i("NotificationLaunch", String.format("the %s is running, isAppAlive return true", new Object[]{str}));
                return true;
            }
        }
        Log.i("NotificationLaunch", String.format("the %s is not running, isAppAlive return false", new Object[]{str}));
        return false;
    }
}
