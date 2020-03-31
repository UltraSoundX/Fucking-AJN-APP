package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.FakeActivity;
import java.lang.reflect.Method;

/* compiled from: DownLoadWebPage */
public class a extends FakeActivity {
    private LinearLayout a;
    private WebView b;
    private String c = "http://qzs.qq.com/open/mobile/login/qzsjump.html?sdkv=3.3.0.lite&display=mobile";
    /* access modifiers changed from: private */
    public String d = "http://app.qq.com/detail/com.tencent.mobileqq?autodownload=1&norecommend=1&rootvia=opensdk";
    /* access modifiers changed from: private */
    public SSOListener e;

    public void a(SSOListener sSOListener) {
        this.e = sSOListener;
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-1));
        a(this.activity);
        this.activity.setContentView(this.a);
    }

    private void a(Activity activity) {
        b();
        this.b.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                try {
                    if (!str.startsWith("download://")) {
                        return false;
                    }
                    a.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a.this.d)));
                    return true;
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                    a.this.e.onFailed(th);
                    a.this.e = null;
                    a.this.a();
                    return false;
                }
            }
        });
        this.b.loadUrl(this.c);
    }

    private void b() {
        this.a = new LinearLayout(getContext());
        this.a.setOrientation(1);
        new LayoutParams(-1, -1);
        this.b = new WebView(getContext());
        this.a.addView(this.b, new LayoutParams(-1, 0, 11.0f));
        c();
    }

    private void c() {
        if (VERSION.SDK_INT > 10 && VERSION.SDK_INT < 17) {
            try {
                Method method = this.b.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.b, new Object[]{"searchBoxJavaBridge_"});
                method.invoke(this.b, new Object[]{"accessibility"});
                method.invoke(this.b, new Object[]{"accessibilityTraversal"});
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
        }
        WebSettings settings = this.b.getSettings();
        settings.setCacheMode(2);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        this.b.setVerticalScrollBarEnabled(false);
        this.b.setHorizontalScrollBarEnabled(false);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return super.onKeyEvent(i, keyEvent);
        }
        if (this.b == null || !this.b.canGoBack()) {
            this.e.onCancel();
            this.e = null;
            a();
            return true;
        }
        this.b.goBack();
        return true;
    }

    public void a() {
        finish();
    }
}
