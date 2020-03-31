package com.tencent.open.c;

import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class a extends WebView {
    public a(Context context) {
        super(context);
        a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (VERSION.SDK_INT >= 11) {
            removeJavascriptInterface("searchBoxJavaBridge_");
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WebSettings settings = getSettings();
        if (settings != null) {
            try {
                Method method = settings.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                if (method != null) {
                    method.invoke(this, new Object[]{"searchBoxJavaBridge_"});
                }
            } catch (Exception e) {
            }
        }
    }
}
