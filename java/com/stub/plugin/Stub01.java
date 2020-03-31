package com.stub.plugin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import java.lang.reflect.Method;

public class Stub01 extends Activity implements OnDismissListener, OnClickListener, OnTouchListener {
    public static transient boolean isMeTopActivity = false;
    private Class<?> delegateClz = null;
    private Object delegateImpl = null;
    private String source = null;

    public static boolean isMeTopActivity() {
        return isMeTopActivity;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        try {
            Intent intent = getIntent();
            if (intent != null) {
                if (intent.hasExtra("appupdate")) {
                    Util.setThemeWithSdkVersion(this);
                } else if (intent.hasExtra("pull") || intent.hasExtra("ls")) {
                }
            }
            super.onCreate(bundle);
            if (intent != null) {
                this.source = intent.getStringExtra("source");
                if (TextUtils.isEmpty(this.source)) {
                    this.source = "com.stub.stub01.StartActivity";
                }
            }
            this.delegateClz = Class.forName(this.source);
            MyLog.log("Stub01", "onCreate", "source= " + this.source);
            if (this.delegateClz != null) {
                this.delegateImpl = this.delegateClz.newInstance();
                if (this.delegateImpl != null && this.delegateClz != null) {
                    Method method = ReflectionUtil.getMethod(this.delegateClz, "onCreate", Bundle.class, Object.class);
                    ReflectionUtil.invoke(this.delegateImpl, method, bundle, this);
                }
            }
        } catch (Throwable th) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            isMeTopActivity = false;
            MyLog.log("Stub01", "onResume", "enter", "source= " + this.source);
            if (!(this.delegateImpl == null || this.delegateClz == null)) {
                ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "onResume", new Class[0]), new Object[0]);
            }
        } catch (Throwable th) {
        }
        isMeTopActivity = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        try {
            isMeTopActivity = false;
            MyLog.log("Stub01", "onStop", "enter", "source= " + this.source);
            if (this.delegateImpl != null && this.delegateClz != null) {
                ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "onStop", new Class[0]), new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        try {
            MyLog.log("Stub01", "onPause", "enter", "source= " + this.source);
            if (this.delegateImpl != null && this.delegateClz != null) {
                ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "onPause", new Class[0]), new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            MyLog.log("Stub01", "onDestroy", "enter", "source= " + this.source);
            isMeTopActivity = false;
            if (this.delegateImpl != null && this.delegateClz != null) {
                ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "onDestroy", new Class[0]), new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        try {
            MyLog.log("Stub01", "onKeyDown", "enter", "source= " + this.source);
            if (!(this.delegateClz == null || this.delegateImpl == null)) {
                Method method = ReflectionUtil.getMethod(this.delegateClz, "onKeyDown", Integer.TYPE, KeyEvent.class);
                Object invoke = ReflectionUtil.invoke(this.delegateImpl, method, Integer.valueOf(i), keyEvent);
                if (invoke != null) {
                    return ((Boolean) invoke).booleanValue();
                }
            }
        } catch (Throwable th) {
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            MyLog.log("Stub01", "onTouchEvent", "enter", "source= " + this.source);
            if (!(this.delegateClz == null || this.delegateImpl == null)) {
                Method method = ReflectionUtil.getMethod(this.delegateClz, "onTouchEvent", MotionEvent.class);
                Object invoke = ReflectionUtil.invoke(this.delegateImpl, method, motionEvent);
                if (invoke != null) {
                    return ((Boolean) invoke).booleanValue();
                }
            }
        } catch (Throwable th) {
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            MyLog.log("Stub01", "onWindowFocusChanged", "enter", "source= " + this.source);
            if (this.delegateClz == null || this.delegateImpl == null) {
                super.onWindowFocusChanged(z);
                return;
            }
            Method method = ReflectionUtil.getMethod(this.delegateClz, "onWindowFocusChanged", Boolean.class);
            ReflectionUtil.invoke(this.delegateImpl, method, Boolean.valueOf(z));
        } catch (Throwable th) {
        }
    }

    public void onClick(View view) {
        try {
            MyLog.log("Stub01", "onClick", "enter", "source= " + this.source);
            if (this.delegateClz != null && this.delegateImpl != null) {
                Method method = ReflectionUtil.getMethod(this.delegateClz, "onClick", View.class);
                ReflectionUtil.invoke(this.delegateImpl, method, view);
            }
        } catch (Throwable th) {
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        try {
            MyLog.log("Stub01", "onDismiss", "enter", "source= " + this.source);
            if (this.delegateClz != null && this.delegateImpl != null) {
                Method method = ReflectionUtil.getMethod(this.delegateClz, "onDismiss", DialogInterface.class);
                ReflectionUtil.invoke(this.delegateImpl, method, dialogInterface);
            }
        } catch (Throwable th) {
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            MyLog.log("Stub01", "onTouch", "enter", "source= " + this.source);
            if (!(this.delegateClz == null || this.delegateImpl == null)) {
                Method method = ReflectionUtil.getMethod(this.delegateClz, "onTouch", View.class, MotionEvent.class);
                Object invoke = ReflectionUtil.invoke(this.delegateImpl, method, view, motionEvent);
                if (invoke != null) {
                    return ((Boolean) invoke).booleanValue();
                }
            }
        } catch (Throwable th) {
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onBackPressed() {
        super.onBackPressed();
        try {
            MyLog.log("Stub01", "onBackPressed", "enter", "source= " + this.source);
            if (this.delegateClz != null && this.delegateImpl != null) {
                ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "onBackPressed", new Class[0]), new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    public Resources getResources() {
        Resources resources;
        Class cls;
        try {
            if (this.source != null && !this.source.contains("com.stub.stub05") && !this.source.contains("rpa")) {
                return super.getResources();
            }
            if (TextUtils.isEmpty(this.source)) {
                Intent intent = getIntent();
                if (intent != null) {
                    this.source = intent.getStringExtra("source");
                }
            }
            try {
                if (this.source.contains("com.stub.stub05")) {
                    cls = Class.forName("com.stub.stub05.util.ResourcesHelper");
                } else if (this.source.contains("adsdk")) {
                    cls = Class.forName("com.stub.adsdk.rpa.util.ResourcesHelper");
                } else {
                    cls = null;
                }
            } catch (ClassNotFoundException e) {
                cls = null;
            }
            Object invokeStatic = ReflectionUtil.invokeStatic(ReflectionUtil.getMethod(cls, "getResources", new Class[0]), new Object[0]);
            resources = invokeStatic != null ? (Resources) invokeStatic : null;
            if (resources == null) {
                return super.getResources();
            }
            return resources;
        } catch (Throwable th) {
            resources = null;
        }
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        try {
            MyLog.log("Stub01", "startActivity", "enter", "source= " + this.source);
            ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "startActivity", Intent.class), new Object[0]);
        } catch (Throwable th) {
        }
    }

    public void finish() {
        super.finish();
        try {
            MyLog.log("Stub01", "finish", "enter", "source= " + this.source);
            ReflectionUtil.invoke(this.delegateImpl, ReflectionUtil.getMethod(this.delegateClz, "finish", new Class[0]), new Object[0]);
        } catch (Throwable th) {
        }
    }
}
