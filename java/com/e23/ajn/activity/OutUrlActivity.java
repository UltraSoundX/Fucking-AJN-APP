package com.e23.ajn.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSupportActivity;
import com.e23.ajn.d.e;
import com.e23.ajn.d.n;
import com.e23.ajn.d.p;
import com.e23.ajn.views.X5WebView;
import com.e23.ajn.views.m;
import com.e23.ajn.views.o;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class OutUrlActivity extends BaseSupportActivity {
    public static final String ARG_DESC = "desc";
    public static final String ARG_THUMB = "thumb";
    public static final String ARG_TITLE = "title";
    public static final String ARG_URL = "url";
    private Toolbar b;
    /* access modifiers changed from: private */
    public TextView c;
    private ImageView d;
    private ImageView e;
    private ProgressBar f;
    /* access modifiers changed from: private */
    public X5WebView g;
    private String h;
    private String i;
    private String j;
    private String k;
    private o l;
    /* access modifiers changed from: private */
    public boolean m = false;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public String p;

    /* renamed from: q reason: collision with root package name */
    private OnClickListener f398q = new OnClickListener() {
        public void onClick(View view) {
            OutUrlActivity.this.b();
        }
    };
    private ValueCallback<Uri> r;
    private ValueCallback<Uri[]> s;

    final class a {
        a() {
        }

        @JavascriptInterface
        public void login() {
            Intent intent = new Intent(OutUrlActivity.this, SwipeBackCommonActivity.class);
            intent.putExtra(SwipeBackCommonActivity.TAG, 21);
            OutUrlActivity.this.startActivity(intent);
        }
    }

    static {
        StubApp.interface11(3493);
    }

    public native void onCreate(Bundle bundle);

    private void a() {
        com.jaeger.library.a.b(this, 0, null);
        com.jaeger.library.a.a((Activity) this);
        this.b = (Toolbar) findViewById(R.id.toolbar);
        this.d = (ImageView) this.b.findViewById(R.id.toolbar_left_back);
        this.d.setVisibility(0);
        this.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                OutUrlActivity.this.onBackPressed();
            }
        });
        this.c = (TextView) this.b.findViewById(R.id.toolbar_center_title);
        this.f = (ProgressBar) findViewById(R.id.mProgressbar);
        this.e = (ImageView) findViewById(R.id.detail_toolbar_share);
        this.e.setVisibility(0);
        this.e.setOnClickListener(this.f398q);
        this.l = new o(this);
        this.g = (X5WebView) findViewById(R.id.fragment_web_h_web);
        WebSettings settings = this.g.getSettings();
        settings.setUseWideViewPort(true);
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        this.g.addJavascriptInterface(new a(), "local_obj");
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        settings.setLoadWithOverviewMode(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        this.g.setWebChromeClient(new m(this.f));
        this.g.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.startsWith("http:") && !str.startsWith("https:")) {
                    try {
                        OutUrlActivity.this.m = true;
                        OutUrlActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        return true;
                    } catch (ActivityNotFoundException e) {
                        if (str.startsWith("alipays")) {
                            Toast.makeText(OutUrlActivity.this, "未安装支付宝", 1).show();
                        } else {
                            Toast.makeText(OutUrlActivity.this, "未安装应用", 1).show();
                        }
                        e.getMessage();
                        return true;
                    }
                } else if (str.contains("loingInAJNAPP")) {
                    Intent intent = new Intent(OutUrlActivity.this, SwipeBackCommonActivity.class);
                    intent.putExtra(SwipeBackCommonActivity.TAG, 21);
                    OutUrlActivity.this.startActivity(intent);
                    return true;
                } else {
                    webView.loadUrl(str);
                    return false;
                }
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (!TextUtils.isEmpty(OutUrlActivity.this.g.getTitle())) {
                    OutUrlActivity.this.c.setVisibility(0);
                    OutUrlActivity.this.c.setText(OutUrlActivity.this.g.getTitle());
                }
                OutUrlActivity.this.n = n.a(OutUrlActivity.this.o + "ajnKey123");
                OutUrlActivity.this.g.loadUrl("javascript:getUserInfo('" + OutUrlActivity.this.o + "','" + OutUrlActivity.this.n + "','" + OutUrlActivity.this.p + "')");
                OutUrlActivity.this.g.loadUrl("javascript:setToken('" + p.b(Constants.FLAG_TOKEN, "") + "')");
                webView.getSettings().setBlockNetworkImage(false);
            }
        });
        if (!TextUtils.isEmpty(this.k)) {
            this.g.loadUrl(this.k);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.l != null && !this.l.isShowing()) {
            if (TextUtils.isEmpty(this.h)) {
                this.h = this.g.getTitle();
            }
            this.l.a(this.h, this.j, "http://appc.e23.cn/ajnapi/ic_launcher.png", this.k);
            this.l.show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.g.destroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 15) {
            return;
        }
        if (this.r != null || this.s != null) {
            Object data = (intent == null || i3 != -1) ? null : intent.getData();
            if (this.s != null) {
                a(i2, i3, intent);
            } else if (this.r != null) {
                this.r.onReceiveValue(data);
                this.r = null;
            }
        }
    }

    @TargetApi(21)
    private void a(int i2, int i3, Intent intent) {
        Uri[] uriArr;
        if (i2 == 15 && this.s != null) {
            if (i3 != -1 || intent == null) {
                uriArr = null;
            } else {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    Uri[] uriArr2 = new Uri[clipData.getItemCount()];
                    for (int i4 = 0; i4 < clipData.getItemCount(); i4++) {
                        uriArr2[i4] = clipData.getItemAt(i4).getUri();
                    }
                    uriArr = uriArr2;
                } else {
                    uriArr = null;
                }
                if (dataString != null) {
                    uriArr = new Uri[]{Uri.parse(dataString)};
                }
            }
            this.s.onReceiveValue(uriArr);
            this.s = null;
        }
    }

    public void onResume() {
        super.onResume();
        this.g.onResume();
        if (TextUtils.isEmpty(p.b("voteuid", ""))) {
            this.o = e.a(9);
            p.a("voteuid", this.o);
        } else {
            this.o = p.b("voteuid", "");
        }
        this.p = p.b("user_name", "");
        this.n = n.a(this.o + "ajnKey123");
        this.g.loadUrl("javascript:getUserInfo('" + this.o + "','" + this.n + "','" + this.p + "')");
        this.g.loadUrl("javascript:setToken('" + p.b(Constants.FLAG_TOKEN, "") + "')");
        if (this.m && this.g.canGoBack()) {
            this.g.goBack();
        }
        StatService.onResume(this);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || i2 != 4 || !this.g.canGoBack()) {
            return super.onKeyDown(i2, keyEvent);
        }
        this.g.goBack();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        StatService.onPause(this);
    }
}
