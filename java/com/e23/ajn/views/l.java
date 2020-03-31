package com.e23.ajn.views;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/* compiled from: ProgressWebChromeClient */
public class l extends WebChromeClient {
    private ProgressBar a;

    public l(ProgressBar progressBar) {
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
