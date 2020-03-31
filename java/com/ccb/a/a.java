package com.ccb.a;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.ccb.crypto.tp.tool.d;
import java.util.HashMap;
import java.util.Map;

public class a {
    public static final String CCB_JS_OBJECT = "ccb_android_js_object";
    private Handler a = new Handler();
    /* access modifiers changed from: private */
    public d b;
    /* access modifiers changed from: private */
    public Context c;
    /* access modifiers changed from: private */
    public LinearLayout d;
    /* access modifiers changed from: private */
    public WebView e;
    /* access modifiers changed from: private */
    public HashMap<String, com.ccb.b.a> f;
    /* access modifiers changed from: private */
    public HashMap<String, EditText> g;
    private Map<String, String[]> h;

    public a(Context context, String str, LinearLayout linearLayout, WebView webView) {
        this.b = new d(context, str);
        this.c = context;
        this.f = new HashMap<>();
        this.g = new HashMap<>();
        this.h = new HashMap();
        this.d = linearLayout;
        this.e = webView;
    }

    @JavascriptInterface
    public void openPlainKyb(String str, int i, int i2) {
        this.a.post(new b(this, str, i, i2));
    }

    @JavascriptInterface
    public void openKyb(String str, int i, int i2) {
        this.a.post(new d(this, str, i, i2));
    }

    @JavascriptInterface
    public boolean compareKeyboardInput(String str, String str2) {
        if (this.f.get(str) == null || this.f.get(str2) == null) {
            return false;
        }
        return ((com.ccb.b.a) this.f.get(str)).a().equals(((com.ccb.b.a) this.f.get(str2)).a());
    }

    @JavascriptInterface
    public String getSafeKeyboardOutput(String str) {
        com.ccb.b.a aVar = (com.ccb.b.a) this.f.get(str);
        if (aVar == null) {
            return "";
        }
        aVar.e(true);
        return aVar.a();
    }

    @JavascriptInterface
    public String tranEncrypt(String str) {
        return this.b.c(str);
    }

    @JavascriptInterface
    public String tranDecrypt(String str) {
        return this.b.d(str);
    }

    @JavascriptInterface
    public String localEncrypt(String str) {
        return this.b.e(str);
    }

    @JavascriptInterface
    public String localDecrypt(String str) {
        return this.b.f(str);
    }

    @JavascriptInterface
    public String jumpEncrypt(String str) {
        return this.b.g(str);
    }

    @JavascriptInterface
    public String jumpDecrypt(String str) {
        return this.b.h(str);
    }

    @JavascriptInterface
    public boolean verify() {
        return this.b.b();
    }

    @JavascriptInterface
    public String calc_HMAC(String str) {
        return this.b.i(str);
    }

    @JavascriptInterface
    public String getVersion() {
        return this.b.a();
    }

    @JavascriptInterface
    public String getSYS_CODE() {
        return this.b.c();
    }

    @JavascriptInterface
    public String getMP_CODE() {
        return this.b.d();
    }

    @JavascriptInterface
    public String getAPP_NAME() {
        return this.b.e();
    }

    @JavascriptInterface
    public String getDeviceTag() {
        return this.b.f();
    }

    @JavascriptInterface
    public boolean openKyb(String str, int i) {
        if (i <= 0) {
            return false;
        }
        String[] strArr = new String[i];
        if (this.h.get(str) != null) {
            this.h.remove(str);
        }
        this.h.put(str, strArr);
        return true;
    }

    @JavascriptInterface
    public String getKeyboardOutput(String str) {
        String[] strArr = (String[]) this.h.get(str);
        if (strArr == null || strArr.length < 1) {
            return null;
        }
        int i = 0;
        while (true) {
            if (i >= strArr.length) {
                i = 0;
                break;
            } else if (TextUtils.isEmpty(strArr[i])) {
                break;
            } else {
                i++;
            }
        }
        if (i == 0) {
            return "";
        }
        return this.b.b(this.b.a(strArr, i));
    }

    @JavascriptInterface
    public boolean inputChar(String str, String str2) {
        String[] strArr = (String[]) this.h.get(str);
        if (strArr == null || strArr.length < 1) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= strArr.length) {
                i = 0;
                break;
            } else if (TextUtils.isEmpty(strArr[i])) {
                break;
            } else {
                i++;
            }
        }
        if (!str2.equals("delete")) {
            strArr[i] = this.b.a(str2);
        } else if (i == 0) {
            return true;
        } else {
            strArr[i - 1] = null;
        }
        return true;
    }
}
