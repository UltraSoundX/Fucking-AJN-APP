package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public final class JsVirtualMachine {
    private final Context a;
    private final IX5JsVirtualMachine b;
    private final HashSet<WeakReference<a>> c;

    private static class a implements IX5JsContext {
        private WebView a;

        public a(Context context) {
            this.a = new WebView(context);
            this.a.getSettings().setJavaScriptEnabled(true);
        }

        public void a() {
            if (this.a != null) {
                this.a.onPause();
            }
        }

        public void b() {
            if (this.a != null) {
                this.a.onResume();
            }
        }

        public void addJavascriptInterface(Object obj, String str) {
            if (this.a != null) {
                this.a.addJavascriptInterface(obj, str);
                this.a.loadUrl("about:blank");
            }
        }

        public void destroy() {
            if (this.a != null) {
                this.a.clearHistory();
                this.a.clearCache(true);
                this.a.loadUrl("about:blank");
                this.a.freeMemory();
                this.a.pauseTimers();
                this.a.destroy();
                this.a = null;
            }
        }

        public void evaluateJavascript(String str, final ValueCallback<String> valueCallback, URL url) {
            if (this.a != null) {
                this.a.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() {
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                        valueCallback.onReceiveValue(str);
                    }
                });
            }
        }

        public IX5JsValue evaluateScript(String str, URL url) {
            if (this.a != null) {
                this.a.evaluateJavascript(str, null);
            }
            return null;
        }

        public void evaluateScriptAsync(String str, final ValueCallback<IX5JsValue> valueCallback, URL url) {
            if (this.a != null) {
                this.a.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() {
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                        valueCallback.onReceiveValue(null);
                    }
                });
            }
        }

        public void removeJavascriptInterface(String str) {
            if (this.a != null) {
                this.a.removeJavascriptInterface(str);
            }
        }

        public void setExceptionHandler(ValueCallback<IX5JsError> valueCallback) {
        }

        public void setPerContextData(Object obj) {
        }

        public void setName(String str) {
        }

        public void stealValueFromOtherCtx(String str, IX5JsContext iX5JsContext, String str2) {
        }

        public int getNativeBufferId() {
            return -1;
        }

        public byte[] getNativeBuffer(int i) {
            return null;
        }

        public int setNativeBuffer(int i, byte[] bArr) {
            return -1;
        }
    }

    public JsVirtualMachine(Context context) {
        this(context, null);
    }

    public JsVirtualMachine(Context context, Looper looper) {
        this.c = new HashSet<>();
        this.a = context;
        this.b = X5JsCore.a(context, looper);
    }

    public boolean isFallback() {
        return this.b == null;
    }

    /* access modifiers changed from: protected */
    public IX5JsContext a() {
        if (this.b != null) {
            return this.b.createJsContext();
        }
        a aVar = new a(this.a);
        this.c.add(new WeakReference(aVar));
        return aVar;
    }

    public void destroy() {
        if (this.b != null) {
            this.b.destroy();
            return;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() != null) {
                ((a) weakReference.get()).destroy();
            }
        }
    }

    public Looper getLooper() {
        if (this.b != null) {
            return this.b.getLooper();
        }
        return Looper.myLooper();
    }

    public void onPause() {
        if (this.b != null) {
            this.b.onPause();
            return;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() != null) {
                ((a) weakReference.get()).a();
            }
        }
    }

    public void onResume() {
        if (this.b != null) {
            this.b.onResume();
            return;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() != null) {
                ((a) weakReference.get()).b();
            }
        }
    }
}
