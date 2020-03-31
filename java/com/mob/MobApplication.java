package com.mob;

import android.app.Application;
import com.mob.tools.proguard.ProtectedMemberKeeper;

public class MobApplication extends Application implements ProtectedMemberKeeper {
    /* access modifiers changed from: protected */
    public String getAppkey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getAppSecret() {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        MobSDK.init(this, getAppkey(), getAppSecret());
    }
}
