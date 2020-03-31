package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.i.h;
import com.bumptech.glide.l;
import com.stub.StubApp;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RequestManagerRetriever */
public class j implements Callback {
    private static final j c = new j();
    final Map<FragmentManager, RequestManagerFragment> a = new HashMap();
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> b = new HashMap();
    private volatile l d;
    private final Handler e = new Handler(Looper.getMainLooper(), this);

    public static j a() {
        return c;
    }

    j() {
    }

    private l b(Context context) {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new l(StubApp.getOrigApplicationContext(context.getApplicationContext()), new b(), new f());
                }
            }
        }
        return this.d;
    }

    public l a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (h.c() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return a((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return a((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return a(((ContextWrapper) context).getBaseContext());
            }
        }
        return b(context);
    }

    public l a(FragmentActivity fragmentActivity) {
        if (h.d()) {
            return a(StubApp.getOrigApplicationContext(fragmentActivity.getApplicationContext()));
        }
        b((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager());
    }

    @TargetApi(11)
    public l a(Activity activity) {
        if (h.d() || VERSION.SDK_INT < 11) {
            return a(StubApp.getOrigApplicationContext(activity.getApplicationContext()));
        }
        b(activity);
        return a((Context) activity, activity.getFragmentManager());
    }

    @TargetApi(17)
    private static void b(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(17)
    public RequestManagerFragment a(FragmentManager fragmentManager) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) this.a.get(fragmentManager);
        if (requestManagerFragment2 != null) {
            return requestManagerFragment2;
        }
        RequestManagerFragment requestManagerFragment3 = new RequestManagerFragment();
        this.a.put(fragmentManager, requestManagerFragment3);
        fragmentManager.beginTransaction().add(requestManagerFragment3, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(1, fragmentManager).sendToTarget();
        return requestManagerFragment3;
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(11)
    public l a(Context context, FragmentManager fragmentManager) {
        RequestManagerFragment a2 = a(fragmentManager);
        l b2 = a2.b();
        if (b2 != null) {
            return b2;
        }
        l lVar = new l(context, a2.a(), a2.c());
        a2.a(lVar);
        return lVar;
    }

    /* access modifiers changed from: 0000 */
    public SupportRequestManagerFragment a(android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) this.b.get(fragmentManager);
        if (supportRequestManagerFragment2 != null) {
            return supportRequestManagerFragment2;
        }
        SupportRequestManagerFragment supportRequestManagerFragment3 = new SupportRequestManagerFragment();
        this.b.put(fragmentManager, supportRequestManagerFragment3);
        fragmentManager.beginTransaction().add((Fragment) supportRequestManagerFragment3, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(2, fragmentManager).sendToTarget();
        return supportRequestManagerFragment3;
    }

    /* access modifiers changed from: 0000 */
    public l a(Context context, android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment a2 = a(fragmentManager);
        l b2 = a2.b();
        if (b2 != null) {
            return b2;
        }
        l lVar = new l(context, a2.a(), a2.c());
        a2.a(lVar);
        return lVar;
    }

    public boolean handleMessage(Message message) {
        Object remove;
        Object obj = null;
        boolean z = true;
        switch (message.what) {
            case 1:
                obj = (FragmentManager) message.obj;
                remove = this.a.remove(obj);
                break;
            case 2:
                obj = (android.support.v4.app.FragmentManager) message.obj;
                remove = this.b.remove(obj);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && remove == null && Log.isLoggable("RMRetriever", 5)) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj);
        }
        return z;
    }
}
