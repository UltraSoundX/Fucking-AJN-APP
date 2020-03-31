package com.e23.ajn.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.ToolbarSwipeBackFragment;
import com.e23.ajn.d.e;
import com.e23.ajn.d.n;
import com.e23.ajn.d.p;
import com.e23.ajn.views.ProgressWebView;
import com.e23.ajn.views.k;
import com.e23.ajn.views.o;
import com.jaeger.library.a;
import com.tencent.android.tpush.common.Constants;

public class WebHasBarFragment extends ToolbarSwipeBackFragment implements OnClickListener {
    protected TextView d;
    private View e;
    private Toolbar f;
    private String g;
    private String h;
    /* access modifiers changed from: private */
    public ProgressWebView i;
    private ImageView j;
    private ImageView k;
    private o l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public String o;
    private OnClickListener p = new OnClickListener() {
        public void onClick(View view) {
            WebHasBarFragment.this.i();
        }
    };

    public static WebHasBarFragment a(String str) {
        WebHasBarFragment webHasBarFragment = new WebHasBarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SwipeBackCommonActivity.URL, str);
        webHasBarFragment.setArguments(bundle);
        return webHasBarFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        if (getArguments() != null) {
            this.g = getArguments().getString(SwipeBackCommonActivity.URL);
            if (!TextUtils.isEmpty(getArguments().getString(SwipeBackCommonActivity.TITLE))) {
                this.h = getArguments().getString(SwipeBackCommonActivity.TITLE);
            }
        }
        this.o = p.b("user_name", "");
        if (TextUtils.isEmpty(p.b("voteuid", ""))) {
            this.n = e.a(9);
            p.a("voteuid", this.n);
        } else {
            this.n = p.b("voteuid", "");
        }
        this.m = n.a(this.n + "ajnKey123");
    }

    public void onResume() {
        super.onResume();
        this.i.onResume();
        if (TextUtils.isEmpty(p.b("voteuid", ""))) {
            this.n = e.a(9);
            p.a("voteuid", this.n);
        } else {
            this.n = p.b("voteuid", "");
        }
        this.o = p.b("user_name", "");
        this.m = n.a(this.n + "ajnKey123");
        this.i.loadUrl("javascript:getUserInfo('" + this.n + "','" + this.m + "','" + this.o + "')");
        this.i.loadUrl("javascript:setToken('" + p.b(Constants.FLAG_TOKEN, "") + "')");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = layoutInflater.inflate(R.layout.f158mall, viewGroup, false);
        h();
        return this.e;
    }

    public boolean a() {
        if (this.i == null || !this.i.canGoBack()) {
            return super.a();
        }
        this.i.goBack();
        return true;
    }

    private void h() {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.f = (Toolbar) this.e.findViewById(R.id.toolbar);
        this.j = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.j.setVisibility(0);
        this.j.setOnClickListener(this);
        this.k = (ImageView) this.e.findViewById(R.id.detail_toolbar_share);
        this.k.setVisibility(0);
        this.k.setOnClickListener(this.p);
        this.l = new o(this.c);
        this.d = (TextView) this.f.findViewById(R.id.toolbar_center_title);
        if (!TextUtils.isEmpty(this.h)) {
            this.d.setVisibility(0);
            this.d.setText(this.h);
        }
        this.i = (ProgressWebView) this.e.findViewById(R.id.fragment_web_h_web);
        WebSettings settings = this.i.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        this.i.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith(com.tencent.smtt.sdk.WebView.SCHEME_TEL)) {
                    WebHasBarFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } else if (!str.startsWith("http:") && !str.startsWith("https:")) {
                    try {
                        WebHasBarFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        return true;
                    } catch (ActivityNotFoundException e) {
                        if (str.startsWith("alipays")) {
                            k.a(WebHasBarFragment.this.c, "未安装支付宝");
                        } else {
                            k.a(WebHasBarFragment.this.c, "未安装应用");
                        }
                        e.getMessage();
                        return true;
                    }
                } else if (VERSION.SDK_INT >= 24) {
                    return false;
                } else {
                    webView.loadUrl(str);
                    return true;
                }
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                WebHasBarFragment.this.m = n.a(WebHasBarFragment.this.n + "ajnKey123");
                WebHasBarFragment.this.i.loadUrl("javascript:getUserInfo('" + WebHasBarFragment.this.n + "','" + WebHasBarFragment.this.m + "','" + WebHasBarFragment.this.o + "')");
                WebHasBarFragment.this.i.loadUrl("javascript:setToken('" + p.b(Constants.FLAG_TOKEN, "") + "')");
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        this.i.setScrollBarStyle(33554432);
        if (!TextUtils.isEmpty(this.g)) {
            this.i.loadUrl(this.g);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.l != null && !this.l.isShowing()) {
            if (TextUtils.isEmpty(this.h)) {
                this.h = this.i.getTitle();
            }
            this.l.a(this.h, "", "http://appc.e23.cn/ajnapi/ic_launcher.png", this.g);
            this.l.show();
        }
    }
}
