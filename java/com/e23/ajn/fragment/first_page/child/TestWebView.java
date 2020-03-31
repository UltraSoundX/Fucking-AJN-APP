package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.baidu.mobstat.Config;
import com.e23.ajn.base.BaseSwipeBackActivity;
import com.e23.ajn.c.b;
import com.stub.StubApp;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.Call;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class TestWebView extends BaseSwipeBackActivity {
    /* access modifiers changed from: private */
    public WebView c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public ArrayList<String> e = new ArrayList<>();

    final class a {
        a() {
        }

        @JavascriptInterface
        public String getJson() {
            return TestWebView.this.d;
        }

        @JavascriptInterface
        public void showSource(String str) {
            Iterator it = Jsoup.parse(str).select("img").iterator();
            while (it.hasNext()) {
                TestWebView.this.e.add(((Element) it.next()).absUrl("src"));
            }
        }

        @JavascriptInterface
        public void openImage(String str) {
        }
    }

    static {
        StubApp.interface11(3784);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void a() {
        ((GetBuilder) OkHttpUtils.get().url("http://appc.e23.cn/index.php?m=api&c=content&a=show")).params(b.a(null)).addParams(Config.FEED_LIST_ITEM_CUSTOM_ID, "298595").build().execute(new StringCallback() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(String str, int i) {
                TestWebView.this.d = str;
                System.out.println("dataStr=>" + TestWebView.this.d);
                TestWebView.this.c.loadUrl("file:///android_asset/html/index.html");
            }
        });
    }

    private void b() {
        WebSettings settings = this.c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setCacheMode(2);
        this.c.addJavascriptInterface(new a(), "local_obj");
        this.c.setWebChromeClient(new WebChromeClient());
        this.c.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                webView.getSettings().setBlockNetworkImage(false);
                webView.loadUrl("javascript:window.local_obj.showSource(''+document.getElementById('content').innerHTML+'');");
                webView.loadUrl("javascript:(function(){var objs = document.getElementById('content').getElementsByTagName(\"img\"); for(var i=0;i<objs.length;i++)  {    objs[i].onclick=function()      {          window.local_obj.openImage(this.src);      }  }})()");
            }
        });
        this.c.setScrollBarStyle(33554432);
    }
}
