package com.e23.ajn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.views.ProgressWebView;

public class WebNoBarFragment extends BaseSupportFragment {
    private View a;
    private String d;
    private ProgressWebView e;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.d = getArguments().getString(SwipeBackCommonActivity.URL);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.f159mis_activity_default, viewGroup, false);
        h();
        return this.a;
    }

    public boolean a() {
        if (this.e == null || !this.e.canGoBack()) {
            return super.a();
        }
        this.e.goBack();
        return true;
    }

    private void h() {
        this.e = (ProgressWebView) this.a.findViewById(R.id.fragment_web_n_web);
        WebSettings settings = this.e.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        this.e.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Intent intent = new Intent(WebNoBarFragment.this.c, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 1);
                intent.putExtra(SwipeBackCommonActivity.URL, str);
                WebNoBarFragment.this.startActivity(intent);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        this.e.setScrollBarStyle(33554432);
        if (!TextUtils.isEmpty(this.d)) {
            this.e.loadUrl(this.d);
        }
    }
}
