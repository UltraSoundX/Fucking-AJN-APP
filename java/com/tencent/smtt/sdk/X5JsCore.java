package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.nio.ByteBuffer;

public class X5JsCore {
    private static a a = a.UNINITIALIZED;
    private static a b = a.UNINITIALIZED;
    private static a c = a.UNINITIALIZED;
    private final Context d;
    private Object e = null;
    private WebView f = null;

    private enum a {
        UNINITIALIZED,
        UNAVAILABLE,
        AVAILABLE
    }

    private static Object a(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            t a2 = t.a();
            if (a2 != null && a2.b()) {
                return a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", str, clsArr, objArr);
            }
            Log.e("X5JsCore", "X5Jscore#" + str + " - x5CoreEngine is null or is not x5core.");
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean canUseX5JsCoreNewAPI(Context context) {
        if (c == a.UNINITIALIZED) {
            c = a.UNAVAILABLE;
            Object a2 = a("canUseX5JsCoreNewAPI", new Class[]{Context.class}, context);
            if (a2 == null || !(a2 instanceof Boolean) || !((Boolean) a2).booleanValue()) {
                return false;
            }
            c = a.AVAILABLE;
            return true;
        } else if (c == a.AVAILABLE) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canUseX5JsCore(Context context) {
        if (a == a.UNINITIALIZED) {
            a = a.UNAVAILABLE;
            Object a2 = a("canUseX5JsCore", new Class[]{Context.class}, context);
            if (a2 == null || !(a2 instanceof Boolean) || !((Boolean) a2).booleanValue()) {
                return false;
            }
            a("setJsValueFactory", new Class[]{Object.class}, JsValue.a());
            a = a.AVAILABLE;
            return true;
        } else if (a == a.AVAILABLE) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canX5JsCoreUseNativeBuffer(Context context) {
        boolean z;
        if (b != a.UNINITIALIZED) {
            if (b == a.AVAILABLE) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
        b = a.UNAVAILABLE;
        if (!canUseX5JsCore(context)) {
            return false;
        }
        Object a2 = a("canX5JsCoreUseBuffer", new Class[]{Context.class}, context);
        if (a2 == null || !(a2 instanceof Boolean) || !((Boolean) a2).booleanValue()) {
            return false;
        }
        b = a.AVAILABLE;
        return true;
    }

    protected static IX5JsVirtualMachine a(Context context, Looper looper) {
        if (canUseX5JsCore(context)) {
            Object a2 = a("createX5JsVirtualMachine", new Class[]{Context.class, Looper.class}, context, looper);
            if (a2 != null) {
                return (IX5JsVirtualMachine) a2;
            }
        }
        Log.e("X5JsCore", "X5JsCore#createVirtualMachine failure!");
        return null;
    }

    protected static Object a() {
        return a("currentContextData", new Class[0], new Object[0]);
    }

    @Deprecated
    public X5JsCore(Context context) {
        this.d = context;
        if (canUseX5JsCore(context)) {
            Object a2 = a("createX5JavaBridge", new Class[]{Context.class}, context);
            if (a2 != null) {
                this.e = a2;
                return;
            }
        }
        Log.e("X5JsCore", "X5JsCore create X5JavaBridge failure, use fallback!");
        this.f = new WebView(context);
        this.f.getSettings().setJavaScriptEnabled(true);
    }

    @Deprecated
    public void addJavascriptInterface(Object obj, String str) {
        if (this.e != null) {
            a("addJavascriptInterface", new Class[]{Object.class, String.class, Object.class}, obj, str, this.e);
        } else if (this.f != null) {
            this.f.addJavascriptInterface(obj, str);
            this.f.loadUrl("about:blank");
        }
    }

    @Deprecated
    public void removeJavascriptInterface(String str) {
        if (this.e != null) {
            a("removeJavascriptInterface", new Class[]{String.class, Object.class}, str, this.e);
        } else if (this.f != null) {
            this.f.removeJavascriptInterface(str);
        }
    }

    @Deprecated
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (this.e != null) {
            a("evaluateJavascript", new Class[]{String.class, ValueCallback.class, Object.class}, str, valueCallback, this.e);
        } else if (this.f != null) {
            this.f.evaluateJavascript(str, valueCallback);
        }
    }

    @Deprecated
    public void pauseTimers() {
        if (this.e != null) {
            a("pauseTimers", new Class[]{Object.class}, this.e);
        }
    }

    @Deprecated
    public void resumeTimers() {
        if (this.e != null) {
            a("resumeTimers", new Class[]{Object.class}, this.e);
        }
    }

    @Deprecated
    public void pause() {
        if (this.e != null) {
            a("pause", new Class[]{Object.class}, this.e);
        }
    }

    @Deprecated
    public void resume() {
        if (this.e != null) {
            a("resume", new Class[]{Object.class}, this.e);
        }
    }

    @Deprecated
    public int getNativeBufferId() {
        if (this.e != null && canX5JsCoreUseNativeBuffer(this.d)) {
            Object a2 = a("getNativeBufferId", new Class[]{Object.class}, this.e);
            if (a2 != null && (a2 instanceof Integer)) {
                return ((Integer) a2).intValue();
            }
        }
        return -1;
    }

    @Deprecated
    public void setNativeBuffer(int i, ByteBuffer byteBuffer) {
        if (this.e != null && canX5JsCoreUseNativeBuffer(this.d)) {
            a("setNativeBuffer", new Class[]{Object.class, Integer.TYPE, ByteBuffer.class}, this.e, Integer.valueOf(i), byteBuffer);
        }
    }

    @Deprecated
    public ByteBuffer getNativeBuffer(int i) {
        if (this.e != null && canX5JsCoreUseNativeBuffer(this.d)) {
            Object a2 = a("getNativeBuffer", new Class[]{Object.class, Integer.TYPE}, this.e, Integer.valueOf(i));
            if (a2 != null && (a2 instanceof ByteBuffer)) {
                return (ByteBuffer) a2;
            }
        }
        return null;
    }

    @Deprecated
    public void destroy() {
        if (this.e != null) {
            a("destroyX5JsCore", new Class[]{Object.class}, this.e);
            this.e = null;
        } else if (this.f != null) {
            this.f.clearHistory();
            this.f.clearCache(true);
            this.f.loadUrl("about:blank");
            this.f.freeMemory();
            this.f.pauseTimers();
            this.f.destroy();
            this.f = null;
        }
    }
}
