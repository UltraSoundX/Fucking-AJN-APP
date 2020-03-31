package com.e23.ajn.views;

import android.widget.ProgressBar;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/* compiled from: ProgressX5WebChromeClient */
public class m extends WebChromeClient {
    private ProgressBar a;

    public m(ProgressBar progressBar) {
        this.a = progressBar;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.a.setVisibility(8);
        } else {
            if (this.a.getVisibility() == 8) {
                this.a.setVisibility(0);
            }
            this.a.setProgress(i);
        }
        super.onProgressChanged(webView, i);
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsConfirm(webView, str, str2, jsResult);
    }
}
