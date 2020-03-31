package cn.sharesdk.tencent.qq;

import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.g;
import cn.sharesdk.framework.utils.SSDKLog;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;

/* compiled from: QQAuthorizeWebviewClient */
public class b extends c {
    public b(g gVar) {
        super(gVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, final String str) {
        if (str.startsWith(this.redirectUri)) {
            webView.setVisibility(4);
            webView.stopLoading();
            this.activity.finish();
            new Thread() {
                public void run() {
                    try {
                        b.this.onComplete(str);
                    } catch (Throwable th) {
                        SSDKLog.b().d(th);
                    }
                }
            }.start();
        } else {
            webView.loadUrl(str);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onComplete(String str) {
        if (str.startsWith(this.redirectUri)) {
            str = str.substring(str.indexOf(35) + 1);
        }
        String[] split = str.split("&");
        HashMap hashMap = new HashMap();
        for (String split2 : split) {
            String[] split3 = split2.split("=");
            if (split3.length < 2) {
                hashMap.put(URLDecoder.decode(split3[0]), "");
            } else {
                hashMap.put(URLDecoder.decode(split3[0]), URLDecoder.decode(split3[1] == null ? "" : split3[1]));
            }
        }
        a(hashMap);
    }

    private void a(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("access_token");
        String str2 = (String) hashMap.get("expires_in");
        String str3 = (String) hashMap.get("error");
        String str4 = (String) hashMap.get("error_description");
        String str5 = (String) hashMap.get("pf");
        String str6 = (String) hashMap.get("pfkey");
        String str7 = (String) hashMap.get("pay_token");
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = c.a(this.activity.a().getPlatform()).c(str);
                if (c == null || c.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(new Throwable());
                    }
                } else if (c.containsKey("openid")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", str);
                    bundle.putString("open_id", String.valueOf(c.get("openid")));
                    bundle.putString("expires_in", str2);
                    bundle.putString("pf", str5);
                    bundle.putString("pfkey", str6);
                    bundle.putString("pay_token", str7);
                    if (this.listener != null) {
                        this.listener.onComplete(bundle);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(new Throwable());
                }
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(th);
                }
            }
        } else if (!TextUtils.isEmpty(str3)) {
            String str8 = str4 + " (" + str3 + ")";
            if (this.listener != null) {
                this.listener.onError(new Throwable(str8));
            }
        } else {
            this.listener.onError(new Throwable());
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        try {
            Method method = sslErrorHandler.getClass().getMethod("proceed", new Class[0]);
            method.setAccessible(true);
            method.invoke(sslErrorHandler, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
