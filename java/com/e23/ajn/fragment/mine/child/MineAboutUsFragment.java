package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.e23.ajn.app.App;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.views.ProgressWebView;
import com.e23.ajn.views.o;
import com.jaeger.library.a;

public class MineAboutUsFragment extends BaseSupportFragment implements OnClickListener {
    private Toolbar a;
    private TextView d;
    private ImageView e;
    private ImageView f;
    private ProgressWebView g;
    private o h;
    private OnClickListener i = new OnClickListener() {
        public void onClick(View view) {
            MineAboutUsFragment.this.i();
        }
    };

    public static MineAboutUsFragment h() {
        Bundle bundle = new Bundle();
        MineAboutUsFragment mineAboutUsFragment = new MineAboutUsFragment();
        mineAboutUsFragment.setArguments(bundle);
        return mineAboutUsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f139gwm_item, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.a = (Toolbar) view.findViewById(R.id.toolbar);
        this.d = (TextView) view.findViewById(R.id.toolbar_center_title);
        this.d.setText(getString(R.string.about_us));
        this.d.setVisibility(0);
        this.e = (ImageView) view.findViewById(R.id.toolbar_left_back);
        this.e.setOnClickListener(this);
        this.e.setVisibility(0);
        this.g = (ProgressWebView) view.findViewById(R.id.fragment_detail_web);
        this.f = (ImageView) view.findViewById(R.id.detail_toolbar_share);
        this.f.setVisibility(0);
        this.f.setOnClickListener(this.i);
        this.h = new o(this.c);
        WebSettings settings = this.g.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        this.g.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        this.g.setScrollBarStyle(33554432);
        this.g.loadUrl("http://appc.e23.cn/ajnapi/gywm.php?ver=" + App.getInstance().getCurrentVersionName());
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
        if (this.h != null && !this.h.isShowing()) {
            this.h.a(this.g.getTitle(), "", "http://appc.e23.cn/ajnapi/ic_launcher.png", "http://appc.e23.cn/ajnapi/gywm.php?ver=" + App.getInstance().getCurrentVersionName());
            this.h.show();
        }
    }
}
