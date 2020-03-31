package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback;
import com.stub.StubApp;

public class AutoTrack {
    public static final int JOB_TYPE_BGSEND = 2;
    public static final int JOB_TYPE_SESSIONTIME = 1;

    public static class MyActivityLifeCallback implements IActivityLifeCallback {
        private int a;

        public MyActivityLifeCallback(int i) {
            this.a = i;
        }

        public void onActivityResumed(Activity activity) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
            if (this.a == 1) {
                BDStatCore.instance().autoTrackSessionStartTime(origApplicationContext);
            } else if (this.a == 2) {
                BDStatCore.instance().cancelSendLogCheck();
            }
        }

        public void onActivityPaused(Activity activity) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
            if (this.a == 1) {
                BDStatCore.instance().autoTrackSessionEndTime(origApplicationContext);
            } else if (this.a == 2) {
                BDStatCore.instance().doSendLogCheck(origApplicationContext);
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }
}
