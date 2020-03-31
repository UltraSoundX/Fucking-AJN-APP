package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.ToolbarSwipeBackFragment;
import com.e23.ajn.views.ProgressWebView;
import com.e23.ajn.views.o;

public class MineYaoQingMaFragment extends ToolbarSwipeBackFragment {
    private View d;
    private Toolbar e;
    private String f;
    private String g;
    private TextView h;
    private ImageView i;
    private ImageView j;
    private ProgressWebView k;
    private o l;
    private OnClickListener m = new OnClickListener() {
        public void onClick(View view) {
            MineYaoQingMaFragment.this.i();
        }
    };

    final class a {
        a() {
        }

        @JavascriptInterface
        public void showshare() {
            MineYaoQingMaFragment.this.c.runOnUiThread(new Runnable() {
                public void run() {
                    MineYaoQingMaFragment.this.i();
                }
            });
        }
    }

    public static MineYaoQingMaFragment a(String str, String str2) {
        MineYaoQingMaFragment mineYaoQingMaFragment = new MineYaoQingMaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SwipeBackCommonActivity.URL, str);
        bundle.putString(SwipeBackCommonActivity.TITLE, str2);
        mineYaoQingMaFragment.setArguments(bundle);
        return mineYaoQingMaFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f = getArguments().getString(SwipeBackCommonActivity.URL);
            if (!TextUtils.isEmpty(getArguments().getString(SwipeBackCommonActivity.TITLE))) {
                this.g = getArguments().getString(SwipeBackCommonActivity.TITLE);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f158mall, viewGroup, false);
        h();
        return this.d;
    }

    public boolean a() {
        if (this.k == null || !this.k.canGoBack()) {
            return super.a();
        }
        this.k.goBack();
        return true;
    }

    private void h() {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.e = (Toolbar) this.d.findViewById(R.id.toolbar);
        this.i = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.i.setVisibility(0);
        this.i.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MineYaoQingMaFragment.this.c.onBackPressed();
            }
        });
        this.h = (TextView) this.e.findViewById(R.id.toolbar_center_title);
        if (!TextUtils.isEmpty(this.g)) {
            this.h.setVisibility(0);
            this.h.setText(R.string.fragment_mine_tv_yqm);
        }
        this.j = (ImageView) this.d.findViewById(R.id.detail_toolbar_share);
        this.j.setVisibility(0);
        this.j.setOnClickListener(this.m);
        this.l = new o(this.c);
        this.k = (ProgressWebView) this.d.findViewById(R.id.fragment_web_h_web);
        WebSettings settings = this.k.getSettings();
        settings.setJavaScriptEnabled(true);
        this.k.addJavascriptInterface(new a(), "local_obj");
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        this.k.setWebChromeClient(new WebChromeClient());
        this.k.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (VERSION.SDK_INT >= 24) {
                    return false;
                }
                webView.loadUrl(str);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        this.k.setScrollBarStyle(33554432);
        if (!TextUtils.isEmpty(this.f)) {
            this.k.loadUrl(this.f);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.l != null && !this.l.isShowing()) {
            this.l.a(this.k.getTitle(), "", "http://appc.e23.cn/ajnapi/ic_launcher.png", this.f.replace("promo_a", NotificationCompat.CATEGORY_PROMO));
            this.l.show();
        }
    }
}
