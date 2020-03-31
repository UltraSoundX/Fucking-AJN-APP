package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.stub.StubApp;
import java.util.LinkedHashSet;
import java.util.Set;

public class ActivityLifeObserver {
    private static final ActivityLifeObserver b = new ActivityLifeObserver();
    private boolean a;
    /* access modifiers changed from: private */
    public Set<IActivityLifeCallback> c = new LinkedHashSet();

    public interface IActivityLifeCallback {
        void onActivityCreated(Activity activity, Bundle bundle);

        void onActivityDestroyed(Activity activity);

        void onActivityPaused(Activity activity);

        void onActivityResumed(Activity activity);

        void onActivitySaveInstanceState(Activity activity, Bundle bundle);

        void onActivityStarted(Activity activity);

        void onActivityStopped(Activity activity);
    }

    public static ActivityLifeObserver instance() {
        return b;
    }

    public void addObserver(IActivityLifeCallback iActivityLifeCallback) {
        synchronized (this.c) {
            this.c.add(iActivityLifeCallback);
        }
    }

    public void clearObservers() {
        synchronized (this.c) {
            this.c.clear();
        }
    }

    public void removeObserver(IActivityLifeCallback iActivityLifeCallback) {
        synchronized (this.c) {
            this.c.remove(iActivityLifeCallback);
        }
    }

    public void registerActivityLifeCallback(Context context) {
        if (!this.a && VERSION.SDK_INT >= 14) {
            doRegister(context);
            this.a = true;
        }
    }

    @TargetApi(14)
    public void doRegister(Context context) {
        try {
            ((Application) StubApp.getOrigApplicationContext(context.getApplicationContext())).registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                public void onActivityResumed(Activity activity) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivityResumed : ActivityLifeObserver.this.c) {
                            onActivityResumed.onActivityResumed(activity);
                        }
                    }
                }

                public void onActivityPaused(Activity activity) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivityPaused : ActivityLifeObserver.this.c) {
                            onActivityPaused.onActivityPaused(activity);
                        }
                    }
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivityCreated : ActivityLifeObserver.this.c) {
                            onActivityCreated.onActivityCreated(activity, bundle);
                        }
                    }
                }

                public void onActivityStarted(Activity activity) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivityStarted : ActivityLifeObserver.this.c) {
                            onActivityStarted.onActivityStarted(activity);
                        }
                    }
                }

                public void onActivityStopped(Activity activity) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivityStopped : ActivityLifeObserver.this.c) {
                            onActivityStopped.onActivityStopped(activity);
                        }
                    }
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivitySaveInstanceState : ActivityLifeObserver.this.c) {
                            onActivitySaveInstanceState.onActivitySaveInstanceState(activity, bundle);
                        }
                    }
                }

                public void onActivityDestroyed(Activity activity) {
                    synchronized (ActivityLifeObserver.this.c) {
                        for (IActivityLifeCallback onActivityDestroyed : ActivityLifeObserver.this.c) {
                            onActivityDestroyed.onActivityDestroyed(activity);
                        }
                    }
                }
            });
        } catch (Exception e) {
            am.c().a("registerActivityLifecycleCallbacks encounter exception");
        }
    }
}
