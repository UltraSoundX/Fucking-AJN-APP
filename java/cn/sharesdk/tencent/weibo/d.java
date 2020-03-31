package cn.sharesdk.tencent.weibo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.g;
import com.mob.tools.utils.ResHelper;

/* compiled from: TencentWeiboAuthorizeWebviewClient */
public class d extends c {
    private int a = 0;
    private boolean b;

    public d(g gVar) {
        super(gVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.redirectUri != null && str.startsWith(this.redirectUri)) {
            webView.stopLoading();
            this.activity.finish();
            onComplete(str);
        } else if (str.startsWith("wtloginmqq")) {
            int stringRes = ResHelper.getStringRes(this.activity.getContext(), "ssdk_use_login_button");
            if (stringRes > 0) {
                Toast.makeText(this.activity.getContext(), stringRes, 0).show();
            }
        } else {
            webView.loadUrl(str);
        }
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.redirectUri != null && str.startsWith(this.redirectUri)) {
            webView.stopLoading();
            this.activity.finish();
            onComplete(str);
        } else if (str.startsWith("wtloginmqq")) {
            int stringRes = ResHelper.getStringRes(this.activity.getContext(), "ssdk_use_login_button");
            if (stringRes > 0) {
                Toast.makeText(this.activity.getContext(), stringRes, 0).show();
            }
        } else {
            if (str.endsWith("omasflag=")) {
                this.a++;
                if (this.a == 2) {
                    webView.stopLoading();
                    this.activity.finish();
                    if (this.listener != null) {
                        this.listener.onCancel();
                        return;
                    }
                    return;
                }
            }
            super.onPageStarted(webView, str, bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onComplete(String str) {
        if (!this.b) {
            this.b = true;
            final Bundle urlToBundle = ResHelper.urlToBundle(str);
            if (!urlToBundle.containsKey("errorCode")) {
                new Thread() {
                    public void run() {
                        try {
                            Bundle b2 = f.a(d.this.activity.a().getPlatform()).b(urlToBundle.getString("code"));
                            if (b2 == null) {
                                if (d.this.listener != null) {
                                    d.this.listener.onError(new Throwable());
                                }
                            } else if (!b2.containsKey("errorCode")) {
                                urlToBundle.putAll(b2);
                                if (d.this.listener != null) {
                                    d.this.listener.onComplete(urlToBundle);
                                }
                            } else if (d.this.listener != null) {
                                d.this.listener.onError(new Throwable(b2.get("errorMsg") + "(" + b2.get("errorCode") + ")"));
                            }
                        } catch (Throwable th) {
                            if (d.this.listener != null) {
                                d.this.listener.onError(th);
                            }
                        }
                    }
                }.start();
            } else if (this.listener != null) {
                this.listener.onError(new Throwable(urlToBundle.getString("errorMsg") + "(" + urlToBundle.getInt("errorCode") + ")"));
            }
        }
    }
}
