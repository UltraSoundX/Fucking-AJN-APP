package com.e23.ajn.fragment.live.child;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.ToolbarSwipeBackFragment;
import com.e23.ajn.d.p;
import com.e23.ajn.views.ProgressWebView;
import com.e23.ajn.views.o;
import com.tencent.android.tpush.common.Constants;

public class LiveDetailFragment extends ToolbarSwipeBackFragment implements OnClickListener {
    protected TextView d;
    private View e;
    private Toolbar f;
    private String g;
    private String h;
    private String i;
    /* access modifiers changed from: private */
    public ProgressWebView j;
    private ImageView k;
    private ImageView l;
    private o m;
    private OnClickListener n = new OnClickListener() {
        public void onClick(View view) {
            LiveDetailFragment.this.i();
        }
    };

    final class a {
        a() {
        }

        @JavascriptInterface
        public void login() {
            Intent intent = new Intent(LiveDetailFragment.this.c, SwipeBackCommonActivity.class);
            intent.putExtra(SwipeBackCommonActivity.TAG, 21);
            LiveDetailFragment.this.startActivity(intent);
        }
    }

    public static LiveDetailFragment a(String str, String str2, String str3) {
        LiveDetailFragment liveDetailFragment = new LiveDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SwipeBackCommonActivity.URL, str3);
        bundle.putString(SwipeBackCommonActivity.TITLE, str);
        bundle.putString("THUMB", str2);
        liveDetailFragment.setArguments(bundle);
        return liveDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.g = getArguments().getString(SwipeBackCommonActivity.URL);
            this.h = getArguments().getString(SwipeBackCommonActivity.TITLE);
            this.i = getArguments().getString("THUMB");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = layoutInflater.inflate(R.layout.f158mall, viewGroup, false);
        h();
        return this.e;
    }

    public boolean a() {
        if (this.j == null || !this.j.canGoBack()) {
            return super.a();
        }
        this.j.goBack();
        return true;
    }

    private void h() {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.f = (Toolbar) this.e.findViewById(R.id.toolbar);
        this.k = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.k.setVisibility(0);
        this.k.setOnClickListener(this);
        this.l = (ImageView) this.e.findViewById(R.id.detail_toolbar_share);
        this.l.setVisibility(0);
        this.l.setOnClickListener(this.n);
        this.m = new o(this.c);
        this.d = (TextView) this.f.findViewById(R.id.toolbar_center_title);
        if (!TextUtils.isEmpty(this.h)) {
            this.d.setVisibility(0);
            this.d.setText(this.h);
        }
        this.j = (ProgressWebView) this.e.findViewById(R.id.fragment_web_h_web);
        WebSettings settings = this.j.getSettings();
        settings.setJavaScriptEnabled(true);
        this.j.addJavascriptInterface(new a(), "local_obj");
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        this.j.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (VERSION.SDK_INT >= 24) {
                    return false;
                }
                webView.loadUrl(str);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (p.a("is_logined", false)) {
                    LiveDetailFragment.this.j.loadUrl("javascript:setToken(\"" + p.b(Constants.FLAG_TOKEN, "") + "\")");
                }
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        this.j.setScrollBarStyle(33554432);
        if (!TextUtils.isEmpty(this.g)) {
            this.j.loadUrl(this.g);
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
        if (this.m != null && !this.m.isShowing()) {
            this.m.a(this.h, "", this.i, this.g);
            this.m.show();
        }
    }

    public void onResume() {
        super.onResume();
        this.j.onResume();
        this.j.loadUrl("javascript:setToken('" + p.b(Constants.FLAG_TOKEN, "") + "')");
    }
}
