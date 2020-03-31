package com.e23.ajn.ccb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.e23.ajn.R;
import com.stub.StubApp;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

public class CcbWebViewActivity extends Activity implements OnClickListener {
    String a = "CcbWebViewActivity";
    CookieStore b = new BasicCookieStore();
    a c;
    private WebView d = null;

    public class a {
        Context a;

        a(Context context) {
            this.a = context;
        }

        @JavascriptInterface
        public void showToast(String str) {
            Toast.makeText(this.a, str, 1).show();
        }

        @JavascriptInterface
        public void closeWebView(String str) {
            CcbWebViewActivity.this.a(str);
        }

        @JavascriptInterface
        public void startWebView(String str) {
            Intent intent = new Intent(this.a, CcbWebViewActivity.class);
            intent.putExtra("url", str);
            this.a.startActivity(intent);
        }
    }

    static {
        StubApp.interface11(3636);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void a() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainActivity);
        this.c = new a(this);
        WebSettings settings = this.d.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(2);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setDefaultFontSize(18);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        this.d.setHorizontalScrollBarEnabled(false);
        this.d.setVerticalScrollBarEnabled(true);
        this.d.setWebViewClient(new WebViewClient() {
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                String cookie = CookieManager.getInstance().getCookie(str);
                if (cookie != null) {
                    for (String split : cookie.split(";")) {
                        String[] split2 = split.split("=");
                        if (split2 != null && split2.length > 1) {
                            CcbWebViewActivity.this.b.addCookie(new BasicClientCookie(split2[0], split2[1]));
                        }
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.i(CcbWebViewActivity.this.a, "polling:shouldOverrideUrlLoading");
                return super.shouldOverrideUrlLoading(webView, str);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                Log.i(CcbWebViewActivity.this.a, "polling:shouldOverrideUrlLoading request");
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
        });
        this.d.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        this.d.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this.d, true);
        }
        this.d.addJavascriptInterface(this.c, com.ccb.a.a.CCB_JS_OBJECT);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            this.d.loadUrl(getIntent().getStringExtra("url"));
        }
        this.d.removeJavascriptInterface("searchBoxJavaBridge_");
        this.d.removeJavascriptInterface("accessibility");
        this.d.removeJavascriptInterface("accessibilityTraversal");
    }

    public void onClick(View view) {
        if (2131820742 == view.getId()) {
            a("");
        }
    }

    public void onBackPressed() {
        a("");
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        Intent intent = new Intent();
        intent.putExtra("PARAMS", str);
        setResult(4, intent);
        finish();
    }
}
