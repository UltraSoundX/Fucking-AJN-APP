package com.baidu.mobstat;

import android.app.Activity;
import android.os.Bundle;
import com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback;
import org.json.JSONArray;

public class af {
    private static volatile boolean a = true;
    private static volatile boolean b = false;

    public static class a implements IActivityLifeCallback {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    public static JSONArray a() {
        return new JSONArray();
    }
}
