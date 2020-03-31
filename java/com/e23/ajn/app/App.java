package com.e23.ajn.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.e23.ajn.d.m;
import com.mob.MobSDK;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import me.yokeyword.fragmentation.b;
import me.yokeyword.fragmentation.helper.a;
import org.litepal.LitePal;

public class App extends Application {
    private static List<WeakReference<Activity>> a = new LinkedList();
    private static App c = null;
    public static App softApplication;
    public static String version = "";
    private HashMap<String, Object> b = new HashMap<>();
    private boolean d;

    public void onCreate() {
        softApplication = this;
        super.onCreate();
        c = this;
        LitePal.initialize(this);
        MobSDK.init(this, "27c1b6c8a3e2e", "3a9ae461ff09a8e1f056c1f162f71667");
        m.a(this);
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        b.d().a(2).a(false).a((a) new a() {
            public void a(Exception exc) {
            }
        }).a();
        QbSdk.initX5Environment(StubApp.getOrigApplicationContext(getApplicationContext()), new PreInitCallback() {
            public void onViewInitFinished(boolean z) {
            }

            public void onCoreInitFinished() {
            }
        });
    }

    public int getCurrentVersionCode() {
        try {
            App instance = getInstance();
            return instance.getPackageManager().getPackageInfo(instance.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            return 1;
        }
    }

    public String getCurrentVersionName() {
        try {
            App instance = getInstance();
            return instance.getPackageManager().getPackageInfo(instance.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    public static WeakReference<Activity> addActivity(Activity activity) {
        WeakReference<Activity> weakReference;
        synchronized (a) {
            weakReference = new WeakReference<>(activity);
            a.add(weakReference);
        }
        return weakReference;
    }

    public int getCurrentRunningActivitySize() {
        return a.size();
    }

    public String getTopActivity() {
        List runningTasks = ((ActivityManager) StubApp.getOrigApplicationContext(getApplicationContext()).getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(1);
        if (runningTasks != null) {
            return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName().toString();
        }
        return null;
    }

    public static WeakReference<Activity> getCurrentActivity() {
        return (WeakReference) a.get(a.size() - 1);
    }

    public void removeActivity(WeakReference<Activity> weakReference) {
        synchronized (a) {
            a.remove(weakReference);
        }
    }

    public void exit() {
        synchronized (a) {
            for (WeakReference weakReference : a) {
                Activity activity = (Activity) weakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
            a.clear();
        }
    }

    public static App getInstance() {
        if (c != null) {
            return c;
        }
        throw new NullPointerException("app not create should be terminated!");
    }

    public boolean isAppRunning() {
        return this.d;
    }

    public void setAppRunning(boolean z) {
        this.d = z;
    }

    public void onTerminate() {
        super.onTerminate();
        exit();
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        com.tencent.android.otherPush.b.a((Context) this);
        android.support.multidex.a.a((Context) this);
    }
}
