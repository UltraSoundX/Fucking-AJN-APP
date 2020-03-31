package cn.sharesdk.framework.authorize;

import android.webkit.WebView;
import cn.sharesdk.framework.g;

/* compiled from: AuthorizeWebviewClient */
public abstract class c extends g {
    protected g activity;
    protected AuthorizeListener listener;
    protected String redirectUri;

    /* access modifiers changed from: protected */
    public abstract void onComplete(String str);

    public c(g gVar) {
        this.activity = gVar;
        AuthorizeHelper a = gVar.a();
        this.redirectUri = a.getRedirectUri();
        this.listener = a.getAuthorizeListener();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        AuthorizeListener authorizeListener = this.activity.a().getAuthorizeListener();
        this.activity.finish();
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable(str + " (" + i + "): " + str2));
        }
    }
}
