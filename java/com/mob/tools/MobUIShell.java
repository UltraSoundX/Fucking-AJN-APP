package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.stub.StubApp;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class MobUIShell extends Activity {
    private static HashMap<String, FakeActivity> executors = new HashMap<>();
    private FakeActivity executor;

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static {
        StubApp.interface11(4370);
        MobLog.getInstance().d("===============================", new Object[0]);
        MobLog.getInstance().d("MobTools " + "2019-09-18".replace("-0", "-").replace("-", "."), new Object[0]);
        MobLog.getInstance().d("===============================", new Object[0]);
    }

    public static Uri toMobUIShellUri(String str, HashMap<String, Object> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : hashMap.keySet()) {
            arrayList.add(new KVPair(str2, String.valueOf(hashMap.get(str2))));
        }
        return Uri.parse("mobui://" + str + "?" + ResHelper.encodeUrl(arrayList));
    }

    protected static String registerExecutor(Object obj) {
        return registerExecutor(String.valueOf(System.currentTimeMillis()), obj);
    }

    protected static String registerExecutor(String str, Object obj) {
        executors.put(str, (FakeActivity) obj);
        return str;
    }

    public final void setTheme(int i) {
        if (initExecutor()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i2 = 0;
            while (i2 < stackTrace.length) {
                if (stackTrace[i2].toString().startsWith("java.lang.Thread.getStackTrace")) {
                    i2 += 2;
                    if (i2 < stackTrace.length) {
                        int onSetTheme = this.executor.onSetTheme(i, stackTrace[i2].toString().startsWith("android.app.ActivityThread.performLaunchActivity"));
                        if (onSetTheme > 0) {
                            super.setTheme(onSetTheme);
                            return;
                        }
                        return;
                    }
                }
                i2++;
            }
        }
        super.setTheme(i);
    }

    private boolean initExecutor() {
        if (this.executor == null) {
            Intent intent = getIntent();
            Uri data = intent.getData();
            if (data != null && "mobui".equals(data.getScheme())) {
                this.executor = activityForName(data.getHost());
                if (this.executor != null) {
                    MobLog.getInstance().i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
                    this.executor.setActivity(this);
                    return true;
                }
            }
            String str = "";
            String str2 = "";
            try {
                String stringExtra = intent.getStringExtra("launch_time");
                String stringExtra2 = intent.getStringExtra("executor_name");
                this.executor = (FakeActivity) executors.remove(stringExtra);
                if (this.executor == null) {
                    this.executor = (FakeActivity) executors.remove(intent.getScheme());
                    if (this.executor == null) {
                        this.executor = getDefault();
                        if (this.executor == null) {
                            MobLog.getInstance().w((Throwable) new RuntimeException("Executor lost! launchTime = " + stringExtra + ", executorName: " + stringExtra2));
                            return false;
                        }
                    }
                }
                MobLog.getInstance().i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
                this.executor.setActivity(this);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
                return false;
            }
        }
        return true;
    }

    private FakeActivity activityForName(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith(".")) {
                    str = getPackageName() + str;
                }
                String importClass = ReflectHelper.importClass(str);
                if (!TextUtils.isEmpty(importClass)) {
                    Object newInstance = ReflectHelper.newInstance(importClass, new Object[0]);
                    if (newInstance != null && (newInstance instanceof FakeActivity)) {
                        return (FakeActivity) newInstance;
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return null;
    }

    public FakeActivity getDefault() {
        String str;
        try {
            str = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString("defaultActivity");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            str = null;
        }
        return activityForName(str);
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(this).inflate(i, null));
    }

    public void setContentView(View view) {
        if (view != null) {
            super.setContentView(view);
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        if (view != null) {
            if (layoutParams == null) {
                super.setContentView(view);
            } else {
                super.setContentView(view, layoutParams);
            }
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (this.executor == null) {
            super.onNewIntent(intent);
        } else {
            this.executor.onNewIntent(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onStart", new Object[0]);
            this.executor.onStart();
        }
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onResume", new Object[0]);
            this.executor.onResume();
        }
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onPause", new Object[0]);
            this.executor.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onStop", new Object[0]);
            this.executor.onStop();
        }
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onRestart", new Object[0]);
            this.executor.onRestart();
        }
        super.onRestart();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.executor != null) {
            this.executor.sendResult();
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onDestroy", new Object[0]);
            this.executor.onDestroy();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.executor != null) {
            this.executor.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z;
        try {
            if (this.executor != null) {
                z = this.executor.onKeyEvent(i, keyEvent);
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean z;
        try {
            if (this.executor != null) {
                z = this.executor.onKeyEvent(i, keyEvent);
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
            return super.onKeyUp(i, keyEvent);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.executor != null) {
            this.executor.onConfigurationChanged(configuration);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.executor != null) {
            this.executor.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void finish() {
        if (this.executor == null || !this.executor.onFinish()) {
            super.finish();
        }
    }

    public void setRequestedOrientation(int i) {
        if (VERSION.SDK_INT != 26 || !isTranslucentOrFloating()) {
            super.setRequestedOrientation(i);
        }
    }

    public Object getExecutor() {
        return this.executor;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        if (this.executor != null) {
            return this.executor.onCreateOptionsMenu(menu);
        }
        return onCreateOptionsMenu;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        if (this.executor != null) {
            return this.executor.onOptionsItemSelected(menuItem);
        }
        return onOptionsItemSelected;
    }

    public void startActivityForResult(Intent intent, int i) {
        if (this.executor != null) {
            this.executor.beforeStartActivityForResult(intent, i, null);
        }
        super.startActivityForResult(intent, i);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (this.executor != null) {
            this.executor.beforeStartActivityForResult(intent, i, bundle);
        }
        if (VERSION.SDK_INT >= 16) {
            super.startActivityForResult(intent, i, bundle);
        } else {
            super.startActivityForResult(intent, i);
        }
    }

    private boolean fixOrientation() {
        if (VERSION.SDK_INT > 27) {
            return false;
        }
        try {
            Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
            declaredField.setAccessible(true);
            ((ActivityInfo) declaredField.get(this)).screenOrientation = -1;
            declaredField.setAccessible(false);
            return true;
        } catch (Exception e) {
            MobLog.getInstance().w(e, "Fix orientation for 8.0 encountered exception", new Object[0]);
            return false;
        }
    }

    private boolean isTranslucentOrFloating() {
        boolean z;
        Exception e;
        if (VERSION.SDK_INT > 27) {
            return false;
        }
        try {
            TypedArray obtainStyledAttributes = this.executor.activity.obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null));
            Method method = ActivityInfo.class.getMethod("isTranslucentOrFloating", new Class[]{TypedArray.class});
            method.setAccessible(true);
            z = ((Boolean) method.invoke(null, new Object[]{obtainStyledAttributes})).booleanValue();
            try {
                method.setAccessible(false);
                return z;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exc = e3;
            z = false;
            e = exc;
            MobLog.getInstance().w((Throwable) e);
            return z;
        }
    }
}
